import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProcessadorDeNotas implements Runnable {
    private final String arquivo;
    private final RegistroDeNotas registro;

    public ProcessadorDeNotas(String arquivo, RegistroDeNotas registro) {
        this.arquivo = arquivo;
        this.registro = registro;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Thread que processa " + arquivo + " foi interrompida.");
                    return;
                }

                String[] partes = linha.split(",");
                int id = Integer.parseInt(partes[0].split(":")[1].trim());
                double nota = Double.parseDouble(partes[3].split(":")[1].trim());

                System.out.println("Processando linha do aluno " + id + " com nota " + nota);
                registro.atualizarMedia(id, nota);

                // Simula tempo de processamento
                Thread.sleep(500);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao processar arquivo " + arquivo + ": " + e.getMessage());
        }
    }
}
