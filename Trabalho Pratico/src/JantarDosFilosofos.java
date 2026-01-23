import java.util.concurrent.Semaphore;

public class JantarDosFilosofos {
    public static void main(String[] args) {
        int n = 5; // número de filósofos (pode ser alterado dinamicamente)
        Garfo[] garfos = new Garfo[n];
        for (int i = 0; i < n; i++) {
            garfos[i] = new Garfo();
        }

        Semaphore mutex = new Semaphore(n - 1); // evita deadlock

        for (int i = 0; i < n; i++) {
            Filosofo f = new Filosofo(i, garfos[i], garfos[(i + 1) % n], mutex);
            f.start();
        }
    }
}
