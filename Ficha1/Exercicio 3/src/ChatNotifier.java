public class ChatNotifier implements Runnable {
    private Mensagem mensagem;
    private int intervalo; // intervalo em milissegundos entre notificações

    public ChatNotifier(Mensagem mensagem, int intervalo) {
        this.mensagem = mensagem;
        this.intervalo = intervalo;
    }

    @Override
    public void run() {
        try {
            // Simula envio de notificação para o destinatário
            System.out.println("Iniciando notificações para " + mensagem.getDestinatario());

            // Aqui vamos simular 3 notificações para cada mensagem
            for (int i = 1; i <= 3; i++) {
                System.out.println("🔔 Notificação " + i + ": Nova mensagem de "
                        + mensagem.getRemetente() + " → "
                        + mensagem.getConteudo());
                Thread.sleep(intervalo); // espera o intervalo definido
            }

            System.out.println("Todas as notificações para " + mensagem.getDestinatario() + " foram enviadas!");
        } catch (InterruptedException e) {
            System.out.println("Envio de notificações interrompido para " + mensagem.getDestinatario());
        }
    }
}
