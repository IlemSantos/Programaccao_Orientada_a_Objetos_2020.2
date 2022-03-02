package Questao4;

/**
 *
 * @author ilem_santos
 */
public class JogoLabirinto {

    public static void main(String[] args) throws InterruptedException {
        LabirintoRato rato01 = new LabirintoRato();
        LabirintoRato rato02 = new LabirintoRato();
        LabirintoRato rato03 = new LabirintoRato();

        Thread thread01 = new Thread((Runnable) rato01);
        Thread thread02 = new Thread((Runnable) rato02);
        Thread thread03 = new Thread((Runnable) rato03);

        thread01.start();
        thread02.start();
        thread03.start();

        while (thread01.isAlive() && thread02.isAlive() && thread03.isAlive()) {
            continue;
        }

        // Interrompe as threads
        rato01.setShouldExit(true);
        rato02.setShouldExit(true);
        rato03.setShouldExit(true);

        System.out.println("Labirinto do Rato 1");
        rato01.imprimeLabirinto();
        System.out.println("Labirinto do Rato 2");
        rato02.imprimeLabirinto();
        System.out.println("Labirinto do Rato 3");
        rato03.imprimeLabirinto();

        if (rato01.isRatoVencedor()) {
            System.out.println("O Rato 1 é o grande vencedor!");
        }
        if (rato02.isRatoVencedor()) {
            System.out.println("O Rato 2 é o grande vencedor!");
        }
        if (rato03.isRatoVencedor()) {
            System.out.println("O Rato 3 é o grande vencedor!");
        }
    }

}
