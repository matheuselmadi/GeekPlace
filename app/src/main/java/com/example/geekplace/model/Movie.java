package com.example.geekplace.model;

import java.io.Serializable;

public class Movie implements Serializable {
    String nome, descricao, nota;

    public Movie() {
    }

    public Movie(String nome, String descricao, String nota) {
        this.nome = nome;
        this.descricao = descricao;
        this.nota = nota;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
