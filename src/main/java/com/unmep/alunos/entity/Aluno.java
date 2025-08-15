package com.unmep.alunos.entity;

import jakarta.persistence.*;

@Entity
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String primeiroNome;

    private String ultimoNome;

    private Double nota1;

    private Double nota2;

    private Double nota3;

    private Double nota4;

    private Double media;

    private Integer faltas;


    // Retorna se o aluno está aprovado
    @Transient
    public Boolean isAprovado(){
        return this.media >= 7.0 && this.faltas < 7;
    }

    // Retorna se o aluno está reprovado
    @Transient
    public Boolean isReprovado(){
        return !isAprovado();
    }

    // Retorna o motivo da reprovação, se houver
    @Transient
    public String getMotivoReprovacao() {
        if (this.media < 7.0) {
            return "Média insuficiente";
        } else if (this.faltas >= 7) {
            return "Excesso de faltas";
        }
        return null;
    }

    // Construtor vazio
    public Aluno() {
    }

    // Construtor da entidade Aluno
    public Aluno(Long id, String primeiroNome, String ultimoNome, Double nota1, Double nota2, Double nota3, Double nota4, Double media, Integer faltas) {
        this.id = id;
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.nota4 = nota4;
        this.media = media;
        this.faltas = faltas;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public Double getNota1() {
        return nota1;
    }

    public void setNota1(Double nota1) {
        this.nota1 = nota1;
    }

    public Double getNota2() {
        return nota2;
    }

    public void setNota2(Double nota2) {
        this.nota2 = nota2;
    }

    public Double getNota3() {
        return nota3;
    }

    public void setNota3(Double nota3) {
        this.nota3 = nota3;
    }

    public Double getNota4() {
        return nota4;
    }

    public void setNota4(Double nota4) {
        this.nota4 = nota4;
    }

    public Integer getFaltas() {
        return faltas;
    }

    public void setFaltas(Integer faltas) {
        this.faltas = faltas;
    }
}
