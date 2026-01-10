import java.util.Map;

public class Main {
    public static void main(String[] args) {
        RegistroDeNotas registro = new RegistroDeNotas();

        Thread t1 = new Thread(new ProcessadorDeNotas("notas1.txt", registro));
        Thread t2 = new Thread(new ProcessadorDeNotas("notas2.txt", registro));
        Thread t3 = new Thread(new ProcessadorDeNotas("notas3.txt", registro));

        t1.start();
        t2.start();
        t3.start();

        try {
            Thread.sleep(10000);
            t1.interrupt();
            t2.interrupt();
            t3.interrupt();

            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nMédias finais dos alunos:");
        for (Map.Entry<Integer, Double> entry : registro.getMedias().entrySet()) {
            System.out.println("Aluno " + entry.getKey() + " -> Média: " + entry.getValue());
        }
    }
}
