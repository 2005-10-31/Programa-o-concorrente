import java.util.concurrent.Semaphore;

public class Garfo {
    private final Semaphore semaforo = new Semaphore(1);

    public void pegar(int idFilosofo, String lado) throws InterruptedException {
        semaforo.acquire();
        System.out.println("Filósofo " + idFilosofo + " pegou o garfo " + lado + ".");
    }

    public void largar(int idFilosofo, String lado) {
        semaforo.release();
        System.out.println("Filósofo " + idFilosofo + " largou o garfo " + lado + ".");
    }
}
