public class Principal {
    public static void main(String[] args) {
        // Handler global para exceções não capturadas
        Thread.setDefaultUncaughtExceptionHandler((thread, exception) -> {
            System.out.println("Exceção capturada no thread " + thread.getName() + ": " + exception.getMessage());
        });

        RelatorioTimer relatorioTimer = new RelatorioTimer();
        relatorioTimer.iniciarAgendamento();
    }
}
