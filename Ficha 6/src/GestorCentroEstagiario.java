import java.util.List;

public class GestorCentroEstagiario {
    private final Object lock = new Object();
    private final List<Thread> computadores;
    private final List<Computador> instancias;

    public GestorCentroEstagiario(List<Thread> computadores, List<Computador> instancias) {
        this.computadores = computadores;
        this.instancias = instancias;
    }

    public void iniciar() {
        for (Thread t : computadores) {
            t.start();
        }
    }

    public void liberarComputadores() {
        synchronized (lock) {
            System.out.println("Liberando computadores para o próximo ciclo...");
            lock.notifyAll();
        }
    }

    public void monitorarEstados() {
        for (Thread t : computadores) {
            System.out.println("Estado de computador " + t.getName() + ": " + t.getState());
        }
    }

    public void encerrar() {
        System.out.println("Encerrando a fábrica...");
        for (Computador c : instancias) {
            c.encerrar();
        }
    }

    public Object getLock() {
        return lock;
    }
}
