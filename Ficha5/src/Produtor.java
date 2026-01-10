import java.io.BufferedReader;
import java.io.FileReader;

class Produtor extends Thread {
    private Buffer buffer;
    private String ficheiro;

    public Produtor(Buffer buffer, String ficheiro) {
        this.buffer = buffer;
        this.ficheiro = ficheiro;
    }

    @Override
    public void run () {
        try (
                BufferedReader br = new BufferedReader(new FileReader(ficheiro))) {
            String linha;
            int numLinha = 0;

            while ((linha = br.readLine()) != null && !buffer.isEncontrado()) {
                numLinha++;
                buffer.produzir(new Registro(linha.trim(), ficheiro, numLinha));
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler ficheiro: " + ficheiro);
        }
    }
}


