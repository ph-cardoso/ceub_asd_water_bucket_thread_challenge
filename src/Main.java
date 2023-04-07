public class Main {
    public static void main(String[] args) {
        Balde balde = new Balde();
        int tempoDeTrabalho = 120000; // 2 minutos em milissegundos

        Ajudante ajudante1 = new Ajudante(balde, tempoDeTrabalho);
        Ajudante ajudante2 = new Ajudante(balde, tempoDeTrabalho);
        Ajudante ajudante3 = new Ajudante(balde, tempoDeTrabalho);

        Lavador lavador = new Lavador(balde, tempoDeTrabalho);

        Thread threadAjudante1 = new Thread(ajudante1);
        Thread threadAjudante2 = new Thread(ajudante2);
        Thread threadAjudante3 = new Thread(ajudante3);
        Thread threadLavador = new Thread(lavador);

        threadLavador.start();
        threadAjudante1.start();
        threadAjudante2.start();
        threadAjudante3.start();

        try {
            // espera o lavador terminar de trabalhar
            threadLavador.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

