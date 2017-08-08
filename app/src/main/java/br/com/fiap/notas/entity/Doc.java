package br.com.fiap.notas.entity;

/**
 * Created by logonrm on 24/04/2017.
 */

public class Doc {
    private String id;
    private String rev;
    private String titulo;
    private String conteudo;
    private String assunto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRev() {
        return rev;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    @Override
    public String toString() {
        return "Doc{" +
                "id='" + id + '\'' +
                ", rev='" + rev + '\'' +
                ", titulo='" + titulo + '\'' +
                ", conteudo='" + conteudo + '\'' +
                ", assunto='" + assunto + '\'' +
                '}';
    }
}
