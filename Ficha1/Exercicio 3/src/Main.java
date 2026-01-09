public class Main {
    public static void main(String[] args) {
        // Criando mensagens
        Mensagem msg1 = new Mensagem("Kelly", "Roberto", "Olá Roberto, tudo bem?");
        Mensagem msg2 = new Mensagem("Roberto", "Kelly", "Oi Kelly, tudo ótimo!");
        Mensagem msg3 = new Mensagem("Lilian", "Kelly", "Kelly, vamos estudar programação?");

        // Criando threads de ChatNotifier com intervalos diferentes
        Thread t1 = new Thread(new ChatNotifier(msg1, 1000)); // intervalo de 1 segundo
        Thread t2 = new Thread(new ChatNotifier(msg2, 2000)); // intervalo de 2 segundos
        Thread t3 = new Thread(new ChatNotifier(msg3, 3000)); // intervalo de 3 segundos

        // Iniciando as threads
        t1.start();
        t2.start();
        t3.start();

        try {
            // join() garante que o main espere cada thread terminar
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Só será exibido depois que todas as threads terminarem
        System.out.println("\n✅ Todas as notificações enviadas!");
    }
}
