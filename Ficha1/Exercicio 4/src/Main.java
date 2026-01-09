import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Lista compartilhada entre os threads
        List<String> listaCompartilhada = new ArrayList<>();

        // Criando instâncias de LeitorDeLog
        LeitorDeLog leitor1 = new LeitorDeLog("log1.txt", listaCompartilhada);
        LeitorDeLog leitor2 = new LeitorDeLog("log2.txt", listaCompartilhada);
        LeitorDeLog leitor3 = new LeitorDeLog("log3.txt", listaCompartilhada);

        // Criando threads
        Thread t1 = new Thread(leitor1);
        Thread t2 = new Thread(leitor2);
        Thread t3 = new Thread(leitor3);

        // Iniciando threads
        t1.start();
        t2.start();
        t3.start();

        try {
            // join() garante que o main espere todas as threads terminarem
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Exibe total de linhas processadas
        System.out.println("\n✅ Total de linhas processadas: " + listaCompartilhada.size());
    }
}
