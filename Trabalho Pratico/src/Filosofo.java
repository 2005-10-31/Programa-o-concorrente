import java.util.concurrent.Semaphore;

public class Filosofo extends Thread {
    private final int id;
    private final Garfo esquerdo;
    private final Garfo direito;
    private final Semaphore mutex;

    public Filosofo(int id, Garfo esquerdo, Garfo direito, Semaphore mutex) {
        this.id = id;
        this.esquerdo = esquerdo;
        this.direito = direito;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                comer();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando.");
        Thread.sleep((int) (Math.random() * 2000));
    }

    private void comer() throws InterruptedException {
        mutex.acquire(); // garante que apenas n-1 filósofos tentem comer
        esquerdo.pegar(id, "esquerdo");
        direito.pegar(id, "direito");

        System.out.println("Filósofo " + id + " está comendo.");
        Thread.sleep((int) (Math.random() * 2000));

        esquerdo.largar(id, "esquerdo");
        direito.largar(id, "direito");
        mutex.release();
    }
}
