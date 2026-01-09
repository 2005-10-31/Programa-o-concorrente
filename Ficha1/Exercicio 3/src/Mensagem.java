public class Mensagem {
    private String remetente;
    private String destinatario;
    private String conteudo;

    // Construtor
    public Mensagem(String remetente, String destinatario, String conteudo) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.conteudo = conteudo;
    }

    // Getters
    public String getRemetente() {
        return remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getConteudo() {
        return conteudo;
    }

    // Método para exibir a mensagem de forma legível
    @Override
    public String toString() {
        return "Mensagem {" +
                "remetente='" + remetente + '\'' +
                ", destinatario='" + destinatario + '\'' +
                ", conteudo='" + conteudo + '\'' +
                '}';
    }
}
