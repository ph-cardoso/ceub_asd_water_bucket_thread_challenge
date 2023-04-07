public class Lavador implements Runnable {
    private final Balde balde;
    private final int tempoDeTrabalho;

    public Lavador(Balde balde, int tempoDeTrabalho) {
        this.balde = balde;
        this.tempoDeTrabalho = tempoDeTrabalho;
    }

    public void run() {
        long tempoInicial = System.currentTimeMillis();
        boolean baldeVazio = false;

        while (!baldeVazio && System.currentTimeMillis() - tempoInicial < tempoDeTrabalho) {
            try {
                // esvazia o balde com 10 litros por vez
                balde.esvaziar();

                // verifica se o balde está vazio
                if (balde.getCapacidadeAtual() == 0) {
                    baldeVazio = true;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("O lavador esvaziou o balde " + balde.getVezesLavadorFoiAoBalde() + " vezes.");
    }
}
