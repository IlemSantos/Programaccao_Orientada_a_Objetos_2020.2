package Questao1;

import java.text.ParseException;

public class Agenda {

    /* Dados Pessoais */
    protected String nome;

    /* Dados do Curso */
    protected String curso;
    protected Integer periodoLetivoAtual;
    protected Periodo periodoLetivo;

    public Agenda() {
        this.periodoLetivo = new Periodo(0);
    }

    public void cadastrarAluno(String Nome, String Curso, Integer Periodo, Integer nDisciplinas) {
        this.nome = Nome;
        this.curso = Curso;
        this.periodoLetivoAtual = Periodo;
        this.periodoLetivo = new Periodo(nDisciplinas);
    }

    public void mostrarEstudante() {
        System.out.println("Nome: " + nome + "\n"
                + "Curso: " + curso + "\n"
                + "Período Letivo: " + periodoLetivoAtual);
        periodoLetivo.imprimeTurmasMatriculadas();
    }

    /* Questão 2 */
    public Integer somaNota(Disciplina Turma) {
        Integer sumNota = 0;
        for (Atividade atividadePendente : Turma.atividadePendentes) {
            if (atividadePendente != null && atividadePendente.nota != null) {
                sumNota += atividadePendente.nota;
            }
        }
        return sumNota;
    }

    /* Questão 2 */
    public double mediaNota(double sumNota, double sumAtv) {
        double mediaNota;
        mediaNota = sumNota / sumAtv;
        return mediaNota;
    }

    public static void main(String[] args) throws ParseException {
        MenuInterface menu = new MenuInterface();

        Agenda Aluno1 = new Agenda();
//        Agenda Aluno2 = new Agenda();

        menu.menu(Aluno1);
    }
}
