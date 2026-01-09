public class Pedido {

    private int id;
    private String cliente;
    private double valor;

    public Pedido(int id, String cliente, double valor){
        this.id = id;
        this.cliente = cliente;
        this.valor = valor;
    }
    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", cliente=" + cliente + ", valor=" + valor + '}';
    }
}
