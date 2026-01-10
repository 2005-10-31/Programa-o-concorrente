import java.io.BufferedReader;
import java.io.FileReader;

class Consumidor extends Thread {
    private Buffer buffer;
    private String nomeProcurado;

    public Consumidor(Buffer buffer, String nomeProcurado) {
        this.buffer = buffer;
        this.nomeProcurado = nomeProcurado;
    }
    @Override
    public void run() {
        try {
            while (!buffer.isEncontrado()) {
                Registro r = buffer.consumir();
                if (r == null) return;

                if (r.nome.equalsIgnoreCase(nomeProcurado)) {
                    buffer.setEncontrado(r);
                    return;
                }
            }
        } catch (InterruptedException  ignored) {}
    }
}


