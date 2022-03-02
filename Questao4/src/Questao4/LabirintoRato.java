package Questao4;

import java.awt.Point;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LabirintoRato implements Runnable {

    private char[][] labirinto = {
        {'*', 'E', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*'},
        {'*', '@', '*', '@', '@', '@', '@', '@', '*', '@', '@', '@', '@', '@', '@', '@', '@', '@', '@', '@', '@', '*', '@', '@', '@', '@', '@', '@', '@', '@', '@', '@', '*'},
        {'*', '@', '*', '*', '*', '*', '*', '@', '@', '@', '*', '@', '@', '@', '*', '*', '*', '*', '*', '*', '*', '*', '@', '*', '*', '*', '*', '@', '*', '@', '*', '@', '*'},
        {'*', '@', '@', '@', '@', '@', '@', '@', '*', '@', '@', '@', '*', '@', '@', '@', '@', '*', '@', '@', '@', '@', '@', '*', '@', '@', '@', '@', '*', '@', '*', '@', '*'},
        {'*', '@', '*', '*', '*', '*', '*', '*', '*', '@', '*', '@', '*', '*', '*', '*', '@', '*', '@', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '@', '@', '@', '*'},
        {'*', '@', '@', '@', '@', '@', '*', '@', '@', '@', '*', '@', '*', '@', '@', '@', '@', '@', '@', '@', '*', '@', '@', '*', '@', '@', '@', '@', '*', '*', '*', '@', '*'},
        {'*', '@', '*', '*', '*', '*', '*', '@', '*', '*', '*', '@', '*', '@', '*', '*', '@', '*', '*', '@', '@', '@', '*', '*', '@', '*', '*', '@', '@', '*', '*', '@', '*'},
        {'*', '@', '@', '@', '@', '@', '@', '@', '*', '@', '@', '@', '*', '@', '*', '@', '@', '@', '@', '@', '*', '@', '@', '@', '@', '*', '@', '@', '*', '@', '@', '@', '*'},
        {'*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', 'S', '*', '*', '*', '*', '*', '*'}
    };

    // Usado para armazenar e alterar a direcao das faces do algoritmo
    private final String[] direcao = {"Frente", "Direita", "Atrás", "Esquerda"};
    private int direcaoIndice;

    private String direcaoAtual; // Representação em string da direcao atual

    private int iteracoes; // Usado para contar iterações

    public Point endPartida = new Point(0, 1);

    private boolean ratoVencedor = false;

    private boolean shouldExit = false;

    public boolean isRatoVencedor() {
        return ratoVencedor;
    }

    public void setRatoVencedor(boolean ratoVencedor) {
        this.ratoVencedor = ratoVencedor;
    }

    public void setShouldExit(boolean shouldExit) {
        this.shouldExit = shouldExit;
    }

    @Override
    public void run() {
        direcaoIndice = 2; // Comece virando para atrás.
        direcaoAtual = direcao[direcaoIndice];
        Point pontoPartida = new Point(0, 1); // Ponto de partida do labirinto
        iteracoes = -1; // A primeira iteração virá como iteração 0.

        while (!isExit(pontoPartida)) {
            if (shouldExit) {
                break;
            }

            iteracoes++; // Adiciona uma contagem para cada função de adição

            // Depois de 60 iterações o rato aprende a mão direita.
            if (iteracoes == 0) {
                sigaFrente(pontoPartida);
                labirinto[endPartida.x][endPartida.y] = '*'; // '*' quando a entrada está localizada
            } else if (iteracoes % 100 <= 60) {
                resolverLabirinto(pontoPartida);
            } else if (iteracoes % 100 > 60) {
                resolverLabirintoMaoDireita(pontoPartida);
            }

            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(LabirintoRato.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        labirinto[endPartida.x][endPartida.y] = 'E'; // 'E' quando a entrada está localizada
        labirinto[pontoPartida.x][pontoPartida.y] = 'R'; // 'R' quando a saída está localizada

        if (isExit(pontoPartida)) {
            this.ratoVencedor = true;

        }
    }

    private void resolverLabirinto(Point p) {
        labirinto[p.x][p.y] = 'R'; // 'R' maiúsculo marca o local atual do rato

        Random random = new Random();

        int indiceAleatorio, direcaoPossivelIndice = 0;

        // Construí as possibilidade de caminho para o rato
        String[] direcaoPossivel = new String[4];
        if (olhaFrente(p) != '*') {
            direcaoPossivel[direcaoPossivelIndice++] = "Frente";
        }
        if (olhaDireita(p) != '*') {
            direcaoPossivel[direcaoPossivelIndice++] = "Direita";
        }
        if (olhaEsquerda(p) != '*') {
            direcaoPossivel[direcaoPossivelIndice++] = "Esquerda";
        }
        if (olhaAtras(p) != '*') {
            direcaoPossivel[direcaoPossivelIndice++] = "Atrás";
        }

        /**
         * Depois de 30 iterações o rato aprende em priorizar em andar para
         * frente ou direita ou esquerda.
         */
        if (iteracoes > 30) {
            if (direcaoPossivelIndice != 1) {
                indiceAleatorio = random.nextInt(direcaoPossivelIndice - 1);
            } else {
                indiceAleatorio = random.nextInt(direcaoPossivelIndice);
            }
        } else {
            indiceAleatorio = random.nextInt(direcaoPossivelIndice);
        }

        switch (direcaoPossivel[indiceAleatorio]) {
            case "Frente":
                sigaFrente(p);
                break;
            case "Direita":
                VireDireita();
                sigaFrente(p);
                break;
            case "Esquerda":
                vireEsquerda();
                sigaFrente(p);
                break;
            case "Atrás":
                VireDireita();
                VireDireita();
                sigaFrente(p);
                break;
        }

    }

    /**
     * Localize a saída usando o mão direito o algoritmo de parede
     */
    private void resolverLabirintoMaoDireita(Point p) {

        labirinto[p.x][p.y] = 'R'; // 'R' maiúsculo marca o local atual

        if (olhaDireita(p) != '*') {
            VireDireita();
            sigaFrente(p);
        } else if (olhaFrente(p) != '*') {
            sigaFrente(p);
        } else if (olhaEsquerda(p) != '*') {
            vireEsquerda();
            sigaFrente(p);
        } else { // Dead End
            VireDireita();
            VireDireita();
            sigaFrente(p);
        }

    }

    /**
     * Muda a direcao para a frente virando à direita.
     */
    private void VireDireita() {
        direcaoIndice = (direcaoIndice + 1) % 4;
        direcaoAtual = direcao[direcaoIndice];
    }

    /**
     * Muda a direcao para a frente virando à esquerda.
     */
    private void vireEsquerda() {
        direcaoIndice = (direcaoIndice + 3) % 4;
        direcaoAtual = direcao[direcaoIndice];
    }

    /**
     * O método determina o que está à direita.
     *
     * @return caractere que está atualmente à direita
     */
    private char olhaDireita(Point p) {
        switch (direcaoAtual) {
            case "Frente":
                return labirinto[p.x][p.y + 1];
            case "Direita":
                return labirinto[p.x + 1][p.y];
            case "Atrás":
                return labirinto[p.x][p.y - 1];
            // direcaoAtual equals "Esquerda"
            default:
                return labirinto[p.x - 1][p.y];
        }
    }

    /**
     * O método determina o que está à frente.
     *
     * @return caractere que está atualmente à frente
     */
    private char olhaFrente(Point p) {
        switch (direcaoAtual) {
            case "Frente":
                return labirinto[p.x - 1][p.y];
            case "Direita":
                return labirinto[p.x][p.y + 1];
            case "Atrás":
                return labirinto[p.x + 1][p.y];
            // direcaoAtual equals "Esquerda"
            default:
                return labirinto[p.x][p.y - 1];
        }
    }

    /**
     * O método determina o que está à esquerda.
     *
     * @return caractere que está atualmente à esquerda
     */
    private char olhaEsquerda(Point p) {
        switch (direcaoAtual) {
            case "Frente":
                return labirinto[p.x][p.y - 1];
            case "Direita":
                return labirinto[p.x - 1][p.y];
            case "Atrás":
                return labirinto[p.x][p.y + 1];
            // direcaoAtual equals "Esquerda"
            default:
                return labirinto[p.x + 1][p.y];
        }
    }

    /**
     * O método determina o que está à atrás.
     *
     * @return caractere que está atualmente à atrás
     */
    private char olhaAtras(Point p) {
        switch (direcaoAtual) {
            case "Frente":
                return labirinto[p.x + 1][p.y];
            case "Direita":
                return labirinto[p.x][p.y - 1];
            case "Atrás":
                return labirinto[p.x - 1][p.y];
            // direcaoAtual equals "Esquerda"
            default:
                return labirinto[p.x][p.y + 1];
        }
    }

    private void sigaFrente(Point p) {
        labirinto[p.x][p.y] = 'r'; // Marca o ponto atual antes de mover
        switch (direcaoAtual) {
            case "Frente":
                p.x--;
                break;
            case "Direita":
                p.y++;
                break;
            case "Atrás":
                p.x++;
                break;
            // direcaoAtual equals "Esquerda"
            default:
                p.y--;
                break;
        }
    }

    /**
     * O método determina se o o ponto atual no labirinto é uma saída.
     */
    private boolean isExit(Point p) {
        // Combina direcao e localização na borda do labirinto para determinar uma saída
        if (direcaoAtual.equals("Atrás") && p.x == 8) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Imprima o labirinto (instanciado como uma matriz de caracteres 2D) para o
     * console
     */
    protected void imprimeLabirinto() {
        for (char[] labirinto1 : labirinto) {
            for (char chr : labirinto1) {
                System.out.print(chr + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
