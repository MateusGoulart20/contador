package exercicios.contador.models;

public class Tarefa {
    private String descricao;
    private boolean concluida;
    private String pessoaResponsavel;

    public Tarefa(String string, boolean b, String string2) {
        this.descricao = string;
        this.concluida = b;
        this.pessoaResponsavel = string2;
    }
    public Tarefa(){}

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public boolean isConcluida() {
        return concluida;
    }
    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
    public String getpessoaResponsavel() {
        return pessoaResponsavel;
    }
    public void setpessoaResponsavel(String pessoaResponsavel) {
        this.pessoaResponsavel = pessoaResponsavel;
    }

    public String toString(){
        return "Tarefa{" +
               "descricao='" + descricao + '\'' +
               ", concluida=" + concluida +
               ", pessoaResponsavel='" + pessoaResponsavel + '\'' +
               '}';
    }
}
