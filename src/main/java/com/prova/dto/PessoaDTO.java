
package com.prova.dto;

public class PessoaDTO {
    private String nome;
    private TrabalhoDTO trabalho;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TrabalhoDTO getTrabalho() {
        return trabalho;
    }

    public void setTrabalho(TrabalhoDTO trabalho) {
        this.trabalho = trabalho;
    }
}
