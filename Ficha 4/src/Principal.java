public class Principal {
    public static void main(String[] args) throws InterruptedException {
        // Cenário 1: Usando notifyAll()
        System.out.println("=== CENÁRIO 1: notifyAll() ===");
        executarSimulacao(true);

        // Cenário 2: Usando notify()
        System.out.println("\n=== CENÁRIO 2: notify() ===");
        executarSimulacao(false);
    }

    private static void executarSimulacao(boolean usarNotifyAll) throws InterruptedException {
        FilaDePedidos fila = new FilaDePedidos(6, usarNotifyAll);

        Thread cozinheiro1 = new Thread(new Cozinheiro(fila, "Cozinheiro 1"));
        Thread cozinheiro2 = new Thread(new Cozinheiro(fila, "Cozinheiro 2"));
        Thread garcom1 = new Thread(new Garcom(fila, "Garçom 1"));
        Thread garcom2 = new Thread(new Garcom(fila, "Garçom 2"));

        cozinheiro1.start();
        cozinheiro2.start();
        garcom1.start();
        garcom2.start();

        // roda por 15 segundos
        Thread.sleep(15000);

        cozinheiro1.interrupt();
        cozinheiro2.interrupt();
        garcom1.interrupt();
        garcom2.interrupt();
    }
}
