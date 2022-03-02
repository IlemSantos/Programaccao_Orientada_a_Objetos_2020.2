package Questao1;

public class Periodo {

    protected Integer periodoLetivo;
    protected Disciplina[] turmaMatriculadas;
    protected Integer totalTurma;

    public Periodo(Integer nDisciplinas) {
        this.totalTurma = 0;
        turmaMatriculadas = new Disciplina[nDisciplinas];
    }

    public void adicionarTurma(Disciplina Turma) {
        this.turmaMatriculadas[totalTurma++] = Turma;
    }

    public void imprimeCodTurmasMatriculadas() {
        for (int i = 0; i < turmaMatriculadas.length; i++) {
            System.out.printf("Disciplina %d: ", i);
            System.out.println(turmaMatriculadas[i].nome);
        }
    }

    public void imprimeTurmasMatriculadas() {

        for (int i = 0; i < turmaMatriculadas.length; i++) {
            System.out.printf("##------------Disciplina %d------------##\n", i);
            turmaMatriculadas[i].mostrarDisciplina();
        }
    }
}
