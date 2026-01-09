public class Cliente implements Runnable {
    private int id;
    private Estoque estoque;

    public Cliente(int id, Estoque estoque) {
        this.id = id;
        this.estoque = estoque;
    }
    @Override
    public void run() {
        boolean sucesso = estoque.comprarProduto();
        if (sucesso) {
            System.out.println("Cliente " + id + " realizou a compra com sucesso!");
        } else {
            System.out.println("Cliente " + id + " não conseguiu comprar: produto fora de estoque.");
        }
    }
}
