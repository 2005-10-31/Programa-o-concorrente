import java.util.Timer;
import java.util.TimerTask;

public class RelatorioTimer {
    private final Timer timer = new Timer();

    public void iniciarAgendamento() {
        timer.schedule(new TimerTask() {
            int contador = 1;

            @Override
            public void run() {
                try {
                    // alterna entre diferentes conteúdos
                    String conteudo = (contador % 2 == 0) ? "Conteúdo do relatório das matrículas"
                            : "Conteúdo do relatório das propinas";

                    Thread t = new Thread(new ProcessadorDeRelatorios(contador, conteudo));
                    t.start();
                    contador++;

                    // simula exceção em um dos threads
                    if (contador == 3) {
                        Thread tErro = new Thread(new ProcessadorDeRelatorios(0, "Erro simulado"));
                        tErro.start();
                    }
                } catch (Exception e) {
                    System.out.println("Erro no agendamento: " + e.getMessage());
                }
            }
        }, 0, 5000);

        // cancelar após 20 segundos
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timer.cancel();
                System.out.println("Agendamento de relatórios encerrado.");
            }
        }, 20000);
    }
}
