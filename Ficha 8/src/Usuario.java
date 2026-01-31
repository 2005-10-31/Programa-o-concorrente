import java.util.concurrent.locks.*;

public class Usuario {
    private final String nome;
    private final Lock lockUsuario = new ReentrantLock();
    private final Condition condicao = lockUsuario.newCondition();

    public Usuario(String nome) {
        this.nome = nome;
    }

    public void atualizarNome(String novoNome) {
        lockUsuario.lock();
        try {
            System.out.println("Atualizando usuário " + nome + " para " + novoNome);
            condicao.signalAll();
        } finally {
            lockUsuario.unlock();
        }
    }

    public String getNome() {
        return nome;
    }
}
