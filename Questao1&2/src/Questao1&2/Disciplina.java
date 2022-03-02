package Questao1;

public class Disciplina {

    /* Dados Gerais */
    protected String nome;
    protected String docente;
    protected String local;
    protected String horario;
    protected Atividade[] atividadePendentes;
    protected Integer totalAtividade;

    public Disciplina() {
        this.atividadePendentes = new Atividade[10];
        this.totalAtividade = 0;
    }

    public Disciplina(String Nome, String Docente, String Local, String Horario) {
        this();
        this.nome = Nome;
        this.docente = Docente;
        this.local = Local;
        this.horario = Horario;
    }

    public void adicionarAtividade(Atividade atv) {
        try {
            this.atividadePendentes[totalAtividade++] = atv;
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("erro: " + exception);
        }
    }

    public void mostrarDisciplina() {
        System.out.println("Nome da disciplina: " + nome + "\n"
                + "Docente: " + docente + "\n"
                + "Local: " + local + "\n"
                + "Horário: " + horario);
    }

}
