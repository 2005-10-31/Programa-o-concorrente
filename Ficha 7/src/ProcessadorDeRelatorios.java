public class ProcessadorDeRelatorios implements Runnable {
    private static final ThreadLocal<Integer> userIdThreadLocal = new ThreadLocal<>();
    private final int userId;
    private final String conteudo;

    public ProcessadorDeRelatorios(int userId, String conteudo) {
        this.userId = userId;
        this.conteudo = conteudo;
    }

    @Override
    public void run() {
        // Agora o ThreadLocal é configurado na thread correta
        userIdThreadLocal.set(userId);

        Integer id = userIdThreadLocal.get();
        if (id == null || id == 0) {
            throw new RuntimeException("UserID não configurado para o thread atual.");
        }

        Relatorio relatorio = new Relatorio(id, conteudo);
        System.out.println("Relatório processado: " + relatorio);

        // Boa prática: limpar o ThreadLocal no fim
        userIdThreadLocal.remove();
    }
}
