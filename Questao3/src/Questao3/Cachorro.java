package Questao3;

public abstract class Cachorro {

    protected String nome;
    protected String dono;
    protected String tipoPelagem;
    protected String tipoFocinho;
    protected String raça;
    protected String sentimento;
    protected String corPelos;
    protected String lantido;
    private Boolean estaCorrendo;
    private Boolean estaDoente;
    private Integer quantidadeDePatas = 4;

    public void EmitirSom() {
        System.out.println(lantido);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public String getTipoPelagem() {
        return tipoPelagem;
    }

    public void setTipoPelagem(String tipoPelagem) {
        this.tipoPelagem = tipoPelagem;
    }

    public String getTipoFocinho() {
        return tipoFocinho;
    }

    public void setTipoFocinho(String tipoFocinho) {
        this.tipoFocinho = tipoFocinho;
    }

    public String getRaça() {
        return raça;
    }

    public void setRaça(String raça) {
        this.raça = raça;
    }

    public String getCorPelos() {
        return corPelos;
    }

    public void setCorPelos(String corPelos) {
        this.corPelos = corPelos;
    }

    public void Correr() {
        this.estaCorrendo = true;

    }

    public void Passear() {
        Correr();

    }

    public void MudarDono(String dono) {
        this.dono = dono;
    }

    public void FicarDoente() {
        this.estaDoente = true;
    }

    public void PodeAcidentar() {
        FicarDoente();

    }

    public void PerderPata() {
        this.quantidadeDePatas--;
    }

}
