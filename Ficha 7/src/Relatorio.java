import java.util.Date;

public class Relatorio {
    private final int userId;
    private final long timestamp;
    private final String conteudo;

    public Relatorio(int userId, String conteudo) {
        this.userId = userId;
        this.timestamp = new Date().getTime();
        this.conteudo = conteudo;
    }

    @Override
    public String toString() {
        return "Relatório [UserID: " + userId + ", Timestamp: " + timestamp + ", Conteúdo: " + conteudo + "]";
    }
}
