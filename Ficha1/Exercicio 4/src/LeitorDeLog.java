import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LeitorDeLog implements Runnable {
    private String nomeArquivo;
    private List<String> listaCompartilhada;

    public LeitorDeLog(String nomeArquivo, List<String> listaCompartilhada) {
        this.nomeArquivo = nomeArquivo;
        this.listaCompartilhada = listaCompartilhada;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                synchronized (listaCompartilhada) {
                    listaCompartilhada.add(linha);
                }
                System.out.println("[" + nomeArquivo + "] Linha lida: " + linha);
                Thread.sleep(100); // intervalo de 100 ms
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo " + nomeArquivo + ": " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Leitura interrompida do arquivo " + nomeArquivo);
        }
    }
}



