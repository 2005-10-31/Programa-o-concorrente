public class Estoque {
    private volatile int quantidade;

    public Estoque(int quantidadeInicial) {
        this.quantidade = quantidadeInicial;
    }

    public synchronized boolean comprarProduto() {
        if (quantidade > 0) {
            quantidade--; // reduz o estoque em 1
            System.out.println("Compra realizada! Estoque restante: " + quantidade);
            return true;
        } else {
            System.out.println("Produto esgotado. Nenhuma compra realizada.");
            return false;
        }
    }
    public int getQuantidade() {
        return quantidade;
    }
}
