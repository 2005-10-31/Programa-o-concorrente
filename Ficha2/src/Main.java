public class Main {
    public static void main(String[] args) {

        Estoque estoque = new Estoque(10);

        Thread[] clientes = new Thread[15]; // 3.2 Criar 15 clientes

        for (int i = 0; i < 15; i++) {
            clientes[i] = new Thread(new Cliente(i + 1, estoque));
            clientes[i].start();

            try {
                Thread.sleep(1000); // 3.3 Pausa entre tentativas
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Esperar todos os clientes terminarem
        for (Thread cliente : clientes) {
            try {
                cliente.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 3.4 Quantidade final de estoque
        System.out.println("Quantidade final de estoque: " + estoque.getQuantidade());
    }
}
