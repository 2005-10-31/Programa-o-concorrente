import java.util.ArrayList;
import java.util.List;

public class Principal {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        // Criar instâncias de computadores
        Computador c1 = new Computador("Computador 1", lock);
        Computador c2 = new Computador("Computador 2", lock);
        Computador c3 = new Computador("Computador 3", lock);

        Thread t1 = new Thread(c1, "Thread-0");
        Thread t2 = new Thread(c2, "Thread-1");
        Thread t3 = new Thread(c3, "Thread-2");

        List<Thread> threads = new ArrayList<>();
        threads.add(t1);
        threads.add(t2);
        threads.add(t3);

        List<Computador> instancias = new ArrayList<>();
        instancias.add(c1);
        instancias.add(c2);
        instancias.add(c3);

        GestorCentroEstagiario gestor = new GestorCentroEstagiario(threads, instancias);

        // Iniciar threads
        gestor.iniciar();

        // Simular execução por 15 segundos
        long inicio = System.currentTimeMillis();
        while (System.currentTimeMillis() - inicio < 15000) {
            Thread.sleep(3000);
            gestor.monitorarEstados();
            gestor.liberarComputadores();
        }

        // Encerrar threads
        gestor.encerrar();

        // Esperar threads terminarem
        for (Thread t : threads) {
            t.join();
            System.out.println("Estado de computador " + t.getName() + ": " + t.getState());
        }

        System.out.println("Centro de estágio da UM encerrado.");
    }
}
