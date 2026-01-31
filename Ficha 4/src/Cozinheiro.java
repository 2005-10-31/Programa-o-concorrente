public class Cozinheiro implements Runnable {
    private final FilaDePedidos fila;
    private final String nome;

    public Cozinheiro(FilaDePedidos fila, String nome) {
        this.fila = fila;
        this.nome = nome;
    }

    @Override
    public void run() {
        int contador = 1;
        try {
            while (true) {
                Thread.sleep(2000); // simula tempo de preparo
                String prato = "Prato " + contador + " do " + nome;
                fila.adicionarPedido(prato);
                contador++;
            }
        } catch (InterruptedException e) {
            System.out.println(nome + " terminou.");
        }
    }
}
