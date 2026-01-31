import java.util.LinkedList;
import java.util.Queue;

public class FilaDePedidos {
    private final Queue<String> fila = new LinkedList<>();
    private final int capacidadeMaxima;
    private final boolean usarNotifyAll; // controla se usamos notifyAll ou notify

    public FilaDePedidos(int capacidadeMaxima, boolean usarNotifyAll) {
        this.capacidadeMaxima = capacidadeMaxima;
        this.usarNotifyAll = usarNotifyAll;
    }

    public synchronized void adicionarPedido(String pedido) throws InterruptedException {
        while (fila.size() == capacidadeMaxima) {
            wait(); // espera espaço
        }
        fila.add(pedido);
        System.out.println("Pedido adicionado: " + pedido);

        if (usarNotifyAll) {
            notifyAll(); // acorda todos os consumidores
        } else {
            notify(); // acorda apenas um consumidor
        }
    }

    public synchronized String retirarPedido() throws InterruptedException {
        while (fila.isEmpty()) {
            wait(); // espera até ter pedido
        }
        String pedido = fila.poll();
        System.out.println("Pedido retirado: " + pedido);

        if (usarNotifyAll) {
            notifyAll(); // acorda todos os produtores
        } else {
            notify(); // acorda apenas um produtor
        }
        return pedido;
    }
}
