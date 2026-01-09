public class ProcessadorDePedidos implements Runnable{
    private Pedido pedido;

    public ProcessadorDePedidos(Pedido pedido){
        this.pedido = pedido;
    }
    @Override
    public void run() {
        try {
            System.out.println("Iniciando o processamento do " + pedido);
            for (int i = 1; i <= 3; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Pedido" + pedido.getId() + "Interrompido!");
                    return;
                }
                System.out.println("Processando etapa" + i + " do pedido " + pedido.getId());
                Thread.sleep(2000);
            }
            System.out.println("Pedido" + pedido.getId() + "processado com sucesso");
        } catch (InterruptedException e) {
            System.out.println("Processamento do pedido " + pedido.getId() + " foi interrompido durante o sleep.");
        }
    }
}
