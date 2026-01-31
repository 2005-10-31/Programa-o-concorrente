import java.util.*;
import java.util.concurrent.locks.*;

public class Biblioteca {
    private final List<Livro> catalogo = new ArrayList<>();
    private final ReentrantLock lockCatalogo = new ReentrantLock(); // cadastro de livros
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock(); // empréstimos
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();
    private final Condition livroDisponivel = writeLock.newCondition(); // devolução
    private final StampedLock stampedLock = new StampedLock(); // consultas otimistas

    // Cadastro de livros
    public void adicionarLivro(Livro livro) {
        lockCatalogo.lock();
        try {
            catalogo.add(livro);
            System.out.println("Livro adicionado: " + livro.getTitulo());
        } finally {
            lockCatalogo.unlock();
        }
    }

    public void removerLivro(String titulo) {
        lockCatalogo.lock();
        try {
            catalogo.removeIf(l -> l.getTitulo().equals(titulo));
            System.out.println("Livro removido: " + titulo);
        } finally {
            lockCatalogo.unlock();
        }
    }

    // Empréstimo de livros
    public void emprestarLivro(String titulo) throws InterruptedException {
        writeLock.lock();
        try {
            Optional<Livro> livroOpt = catalogo.stream()
                    .filter(l -> l.getTitulo().equals(titulo) && !l.isEmprestado())
                    .findFirst();

            while (livroOpt.isEmpty()) {
                System.out.println("Livro " + titulo + " não disponível. Aguardando devolução...");
                livroDisponivel.await(); // espera devolução
                livroOpt = catalogo.stream()
                        .filter(l -> l.getTitulo().equals(titulo) && !l.isEmprestado())
                        .findFirst();
            }

            Livro livro = livroOpt.get();
            livro.setEmprestado(true);
            System.out.println("Livro emprestado: " + titulo);
        } finally {
            writeLock.unlock();
        }
    }

    // Devolução de livros
    public void devolverLivro(String titulo) {
        writeLock.lock();
        try {
            catalogo.stream()
                    .filter(l -> l.getTitulo().equals(titulo) && l.isEmprestado())
                    .findFirst()
                    .ifPresent(l -> {
                        l.setEmprestado(false);
                        System.out.println("Livro devolvido: " + titulo);
                        livroDisponivel.signalAll(); // notifica todos
                    });
        } finally {
            writeLock.unlock();
        }
    }

    // Consulta de disponibilidade com StampedLock
    public boolean verificarDisponibilidade(String titulo) {
        long stamp = stampedLock.tryOptimisticRead();
        boolean disponivel = catalogo.stream()
                .anyMatch(l -> l.getTitulo().equals(titulo) && !l.isEmprestado());

        if (!stampedLock.validate(stamp)) {
            // fallback para leitura com lock
            stamp = stampedLock.readLock();
            try {
                disponivel = catalogo.stream()
                        .anyMatch(l -> l.getTitulo().equals(titulo) && !l.isEmprestado());
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        return disponivel;
    }
}
