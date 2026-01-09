import java.util.ArrayList;
import java.util.List;

public class Main{
    public static void main(String[] args) {
        //Criar lista de pedidos
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido(1, "Kelly", 250.75));
        pedidos.add(new Pedido(2, "Lilian", 120.50));
        pedidos.add(new Pedido(3, "Roberto", 400.00));

        List<Thread> threads = new ArrayList<>();

        // Criar e inicializar 3 threads, cada uma processando um pedido diferente
        for (Pedido pedido : pedidos) {
            Thread t = new Thread(new ProcessadorDePedidos(pedido));
            threads.add(t);
            t.start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n>>> Evento de parada do sistema! Interrompendo todas as threads...\n");
        for (Thread t : threads) {
            t.interrupt();
        }
    }
}
