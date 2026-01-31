public class Computador implements Runnable {
    private final String nome;
    private final Object lock;
    private volatile boolean ativo = true;

    public Computador(String nome, Object lock) {
        this.nome = nome;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            while (ativo) {
                // Simula operação
                System.out.println(nome + " está operando...");
                Thread.sleep(2000);

                // Simula estado WAITING
                synchronized (lock) {
                    System.out.println(nome + " aguardando liberação para o próximo ciclo.");
                    lock.wait(); // entra em WAITING até ser liberado
                }
            }
            System.out.println(nome + " foi encerrada.");
        } catch (InterruptedException e) {
            System.out.println(nome + " interrompida.");
        }
    }

    public void encerrar() {
        ativo = false;
        synchronized (lock) {
            lock.notifyAll(); // acorda para sair do loop
        }
    }
}

