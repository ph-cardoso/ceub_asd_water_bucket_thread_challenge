public class Balde {
    private final int capacidadeMaxima = 100; // capacidade máxima do balde em litros
    private int capacidadeAtual = 0; // quantidade atual de litros no balde
    private int vezesLavadorFoiAoBalde = 0; // contador de vezes que o lavador foi ao balde

    public synchronized void encher(int litros) throws InterruptedException {
        // aguarda enquanto o balde está cheio
        while (capacidadeAtual + litros > capacidadeMaxima) {
            wait();
        }

        // enche o balde com a quantidade de litros
        capacidadeAtual += litros;
        System.out.println("O ajudante encheu " + litros + " litros no balde. Capacidade atual: " + capacidadeAtual + " litros.");

        // notifica a classe Lavador que o balde foi alterado
        notifyAll();
    }

    public synchronized void esvaziar() throws InterruptedException {
        // aguarda enquanto o balde está vazio
        while (capacidadeAtual == 0) {
            wait();
        }

        // aguarda 3 segundos na primeira vez que vai ao balde
        if (vezesLavadorFoiAoBalde == 0) {
            System.out.println("O lavador vai ao balde pela primeira vez. Aguardando 3 segundos...");
            Thread.sleep(3000);
        }

        // esvazia o balde com 10 litros por vez
        capacidadeAtual -= 10;
        System.out.println("O lavador esvaziou 10 litros do balde. Capacidade atual: " + capacidadeAtual + " litros.");

        // incrementa o contador de vezes que o lavador foi ao balde
        vezesLavadorFoiAoBalde++;

        // notifica a classe Ajudante que o balde foi alterado
        notifyAll();

        // aguarda 1 segundo antes de esvaziar mais 10 litros
        Thread.sleep(1000);
    }

    public int getCapacidadeAtual() {
        return capacidadeAtual;
    }

    public int getVezesLavadorFoiAoBalde() {
        return vezesLavadorFoiAoBalde;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }
}