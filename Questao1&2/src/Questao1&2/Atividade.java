package Questao1;

import java.util.Date;

public class Atividade {

    protected String nome;
    protected Date dataEntrega;
    protected Integer nota;

    public Atividade(String Nome, Date dataEntrega) {
        this.nome = Nome;
        this.dataEntrega = dataEntrega;
    }

    public Atividade(String Nome, Date dataEntrega, Integer Nota) {
        this.nome = Nome;
        this.dataEntrega = dataEntrega;
        this.nota = Nota;
    }

}
