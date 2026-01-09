import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SensorTemperatura implements Runnable{
    private Random random = new Random();
    private List<Integer> leituras  = new ArrayList<>();

    @Override
    public void run() {
        try {
            for (int i = 0; i < 15; i++){
                int temperatura = 15 + random.nextInt(21);
                leituras.add(temperatura);
                System.out.println("Leitura de temperatura" + temperatura + "ºC");

                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Monitoramento de temperatura interrompido");
        }
    }
    public List<Integer> getLeituras() {
        return leituras;
    }
}




