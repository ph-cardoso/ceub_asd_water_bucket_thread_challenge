public class Ajudante implements Runnable {
    private final Balde balde;
    private final int tempoDeTrabalho;

    public Ajudante(Balde balde, int tempoDeTrabalho) {
        this.balde = balde;
        this.tempoDeTrabalho = tempoDeTrabalho;
    }

    public void run() {
        long tempoInicial = System.currentTimeMillis();

        while (System.currentTimeMillis() - tempoInicial < tempoDeTrabalho) {
            try {
                // enche o balde com 3 litros por vez
                balde.encher(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Se o balde estiver cheio, aguarda 2 segundos antes de tentar encher novamente
            if (balde.getCapacidadeAtual() == balde.getCapacidadeMaxima()) {
                try {
                    System.out.println("O balde está cheio. Aguardando 2 segundos...");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}