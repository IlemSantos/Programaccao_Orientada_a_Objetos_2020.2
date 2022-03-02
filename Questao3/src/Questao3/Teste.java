package Questao3;

public class Teste {

    public static void main(String[] args) {
        Pitbull dog = new Pitbull();

        dog.setNome("Apolo");
        dog.setDono("Juliano");
        dog.setCorPelos("Caramelo");

        System.out.println("Nome: " + dog.getNome());
        System.out.println("Dono: " + dog.getDono());
        System.out.println("Cor de pelos: " + dog.getCorPelos());
        System.out.println();

        dog.EmitirSom();
        System.out.println();

        dog.MudarDono("Marcelo");

        System.out.println("Nome: " + dog.getNome());
        System.out.println("Dono: " + dog.getDono());
        System.out.println("Cor de pelos: " + dog.getCorPelos());

    }

}
