package Questao1;

import java.text.ParseException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MenuInterface {

    private static Scanner teclado = new Scanner(System.in);
    private static SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    private static void cadastrarEstudante(Agenda estudante) {
        System.out.print("Nome: ");
        String Nome = teclado.nextLine();
        System.out.print("Curso: ");
        String Curso = teclado.nextLine();
        System.out.print("Período Letivo Atual: ");
        Integer Periodo = Integer.parseInt(teclado.nextLine());

        System.out.print("Informe o número de disciplinas: ");
        Integer nDisciplinas = Integer.parseInt(teclado.nextLine());

        estudante.cadastrarAluno(Nome, Curso, Periodo, nDisciplinas);

        for (int i = 0; i < nDisciplinas; i++) {
            System.out.printf("##------------Disciplina %d------------##\n", i);
            System.out.print("Nome da disciplina: ");
            String NomeD = teclado.nextLine();
            System.out.print("Nome do docente: ");
            String NomeDocente = teclado.nextLine();
            System.out.print("Local da disciplina: ");
            String Local = teclado.nextLine();
            System.out.print("Horário da disciplina: ");
            String Horario = teclado.nextLine();

            Disciplina d = new Disciplina(NomeD, NomeDocente, Local, Horario);
            estudante.periodoLetivo.adicionarTurma(d);
        }

    }

    private static void mostrarAgenda(Agenda estudante) {
        estudante.mostrarEstudante();
    }

    private static void cadastrarAtividade(Agenda estudante) throws ParseException {
        System.out.println("##--------Códigos da Disciplina--------##");
        estudante.periodoLetivo.imprimeCodTurmasMatriculadas();

        System.out.print("De qual disciplina é a atividade (código): ");
        Integer cod = Integer.parseInt(teclado.nextLine());

        System.out.print("Nome da Atividade: ");
        String NomeAtividade = teclado.nextLine();

        System.out.print("Data de entrega: ");
        String date = teclado.nextLine();
        Date data = formato.parse(date);

        System.out.print("A atividade possui nota (Sim ou Não): ");
        String condNota = teclado.nextLine();

        if ("Sim".equals(condNota)) {
            System.out.print("Informe a nota: ");
            Integer Nota = Integer.parseInt(teclado.nextLine());
            Atividade atv = new Atividade(NomeAtividade, data, Nota);
            estudante.periodoLetivo.turmaMatriculadas[cod].adicionarAtividade(atv);
        } else {
            Atividade atv = new Atividade(NomeAtividade, data);
            estudante.periodoLetivo.turmaMatriculadas[cod].adicionarAtividade(atv);
        }

    }

    private static void autoSoma(Agenda estudante) {
        System.out.println("##--------Códigos da Disciplina--------##");
        estudante.periodoLetivo.imprimeCodTurmasMatriculadas();

        System.out.print("De qual disciplina (código): ");
        Integer cod = Integer.parseInt(teclado.nextLine());

        try {
            Integer autoSoma;
            autoSoma = estudante.somaNota(estudante.periodoLetivo.turmaMatriculadas[cod]);
            System.out.printf("A auto soma das atividades da disciplina %d: %d", cod, autoSoma);
        } catch (java.lang.ArrayIndexOutOfBoundsException exception) {
            System.out.println("erro: " + exception);
        }

    }

    private static void mediaAtividade(Agenda estudante) {
        System.out.println("##--------Códigos da Disciplina--------##");
        estudante.periodoLetivo.imprimeCodTurmasMatriculadas();

        System.out.print("De qual disciplina (código): ");
        Integer cod = Integer.parseInt(teclado.nextLine());

        try {
            Integer autoSoma;
            autoSoma = estudante.somaNota(estudante.periodoLetivo.turmaMatriculadas[cod]);

            int div = (int) Math.ceil((double) autoSoma / 10);

            double mediaAtividade;
            mediaAtividade = estudante.mediaNota(autoSoma, div);

            System.out.printf("A média das atividades da disciplina %d: %2f", cod, mediaAtividade);
        } catch (java.lang.ArrayIndexOutOfBoundsException exception) {
            System.out.println("erro: " + exception);
        }
    }

    public void menu(Agenda estudante) throws ParseException {
        int opcao;
        do {

            System.out.print("\n\n##---------AGENDA DO ESTUDANTE---------##\n\n");
            System.out.print("|---------------------------------------|\n");
            System.out.print("| Opção 1 - Cadastro do Aluno           |\n");
            System.out.print("| Opção 2 - Consulta de Cadastro        |\n");
            System.out.print("| Opção 3 - Cadastrar Atividade         |\n");
            System.out.print("| Opção 4 - Auto Soma das Atividades    |\n");
            System.out.print("| Opção 5 - Média das notas Atividades  |\n");
            System.out.print("| Opção 6 - Sair                        |\n");
            System.out.print("|---------------------------------------|\n");
            System.out.print("Digite uma opção: ");

            opcao = Integer.parseInt(teclado.nextLine());

            switch (opcao) {
                case 1:
                    cadastrarEstudante(estudante);
                    break;

                case 2:
                    mostrarAgenda(estudante);
                    break;

                case 3:
                    cadastrarAtividade(estudante);
                    break;

                case 4:
                    autoSoma(estudante);
                    break;

                case 5:
                    mediaAtividade(estudante);
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;

                case 6:
                    System.out.println("\nAté logo!");
                    teclado.close();
            }

        } while (opcao != 6);

    }

}
