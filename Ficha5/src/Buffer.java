import java.util.LinkedList;
import java.util.Queue;

class Buffer {
    private Queue<Registro> buffer = new LinkedList<>();
    private int capacidade = 20;
    private boolean encontrado = false;
    private Registro resultado = null;

    public synchronized void produzir(Registro r) throws InterruptedException {
        while (buffer.size() == capacidade && !encontrado) {
            wait();
        }
        if (encontrado) return;

        buffer.add(r);
        notifyAll();
    }

    public synchronized Registro consumir() throws InterruptedException {
        while (buffer.isEmpty() && !encontrado) {
            wait();
        }
        if (encontrado) return null;

        Registro r = buffer.poll();
        notifyAll();
        return r;
    }

    public synchronized void setEncontrado(Registro r) {
        encontrado = true;
        resultado = r;
        notifyAll();
    }

    public synchronized boolean isEncontrado() {
        return encontrado;
    }

    public synchronized Registro getResultado() {
        return resultado;
    }
}
