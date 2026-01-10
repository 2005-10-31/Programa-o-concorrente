import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RegistroDeNotas {
    private final Map<Integer, Double> medias = new ConcurrentHashMap<>();
    private final Map<Integer, Integer> contagemNotas = new ConcurrentHashMap<>();

    public synchronized void atualizarMedia(int id, double novaNota) {
        double somaAtual = medias.getOrDefault(id, 0.0) * contagemNotas.getOrDefault(id, 0);
        int qtdAtual = contagemNotas.getOrDefault(id, 0);

        somaAtual += novaNota;
        qtdAtual++;

        double novaMedia = somaAtual / qtdAtual;

        medias.put(id, novaMedia);
        contagemNotas.put(id, qtdAtual);

        System.out.println("Atualizada média do aluno " + id + ": " + novaMedia);
    }

    public Map<Integer, Double> getMedias() {
        return medias;
    }
}
