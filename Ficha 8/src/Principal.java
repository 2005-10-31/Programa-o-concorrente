public class Principal {
    public static void main(String[] args) throws InterruptedException {
        Biblioteca biblioteca = new Biblioteca();

        // Cadastro inicial
        biblioteca.adicionarLivro(new Livro("Java Concurrency"));
        biblioteca.adicionarLivro(new Livro("Estruturas de Dados"));
        biblioteca.adicionarLivro(new Livro("Sistemas Operacionais"));

        // Threads simulando usuários
        Thread usuario1 = new Thread(() -> {
            try {
                biblioteca.emprestarLivro("Java Concurrency");
                Thread.sleep(3000);
                biblioteca.devolverLivro("Java Concurrency");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread usuario2 = new Thread(() -> {
            try {
                biblioteca.emprestarLivro("Java Concurrency");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consulta = new Thread(() -> {
            boolean disponivel = biblioteca.verificarDisponibilidade("Java Concurrency");
            System.out.println("Disponibilidade de 'Java Concurrency': " + disponivel);
        });

        usuario1.start();
        usuario2.start();
        consulta.start();

        usuario1.join();
        usuario2.join();
        consulta.join();

        System.out.println("Simulação concluída.");
    }
}
