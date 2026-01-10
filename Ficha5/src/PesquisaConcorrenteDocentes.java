public class PesquisaConcorrenteDocentes {

    public static void main(String[] args) throws InterruptedException {

        String nomeProcurado = "Ana Silva";

        String[] ficheiros = {
                "docentes.txt"
        };
        Buffer buffer = new Buffer();

        Produtor[] produtores = new Produtor[ficheiros.length];
        for (int i = 0; i < ficheiros.length; i++) {
            produtores[i] = new Produtor(buffer, ficheiros[i]);
            produtores[i].start();
        }

        Consumidor[] consumidores = new Consumidor[3];
        for (int i = 0; i < consumidores.length; i++) {
            consumidores[i] = new Consumidor(buffer, nomeProcurado);
            consumidores[i].start();
        }

        for (Produtor p : produtores) p.join();
        for (Consumidor c : consumidores) c.join();

        Registro res = buffer.getResultado();
        if (res != null) {
            System.out.println("Nome encontrado no ficheiro: "
                    + res.ficheiro + ", na linha: " + res.linha);
        } else {
            System.out.println("Nome não encontrado em nenhum dos ficheiros.");
        }
    }
}
