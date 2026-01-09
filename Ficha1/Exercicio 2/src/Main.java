import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        SensorTemperatura sensor = new SensorTemperatura();
        Thread t = new Thread(sensor);

        System.out.println("Iniciando monitorização de temperatura...\n");

        t.start();


        while (t.isAlive()) {
            List<Integer> leituras = sensor.getLeituras();

            if (!leituras.isEmpty()) {
                int ultima = leituras.get(leituras.size() - 1);

                if (ultima > 30) {
                    System.out.println("\n⚠️ ALERTA: Temperatura acima de 30°C! Interrompendo monitorização...");
                    t.interrupt();
                    break;
                }
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                break;
            }
        }

        System.out.println("\nLeituras registadas: " + sensor.getLeituras());
        System.out.println("Fim da monitorização.");
    }
}
