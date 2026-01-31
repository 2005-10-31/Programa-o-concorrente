public class Garcom implements Runnable {
    private final FilaDePedidos fila;
    private final String nome;

    public Garcom(FilaDePedidos fila, String nome) {
        this.fila = fila;
        this.nome = nome;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String prato = fila.retirarPedido();
                System.out.println(nome + " serviu: " + prato);
                Thread.sleep(3000); // simula tempo de servir
            }
        } catch (InterruptedException e) {
            System.out.println(nome + " terminou.");
        }
    }
}
