package com.unmep.alunos.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

// Dto para receber os dados de aluno pelo json
public class AlunoDTO{

    @JsonProperty("primeiro_nome")
    private String primeiroNome;

    @JsonProperty("ultimo_nome")
    private String ultimoNome;

    @JsonProperty("nota_1")
    private Double nota1;

    @JsonProperty("nota_2")
    private Double nota2;

    @JsonProperty("nota_3")
    private Double nota3;

    @JsonProperty("nota_4")
    private Double nota4;

    private Integer faltas;

    // Construtor vazio
    public AlunoDTO() {
    }

    // Construtor com todos os campos
    public AlunoDTO(String primeiroNome, String ultimoNome, Double nota1, Double nota2, Double nota3, Double nota4, Integer faltas) {
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.nota4 = nota4;
        this.faltas = faltas;
    }

    // Getters e Setters
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

    // Métodos equals, hashCode e toString
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AlunoDTO alunoDTO = (AlunoDTO) o;
        return Objects.equals(primeiroNome, alunoDTO.primeiroNome) && Objects.equals(ultimoNome, alunoDTO.ultimoNome) && Objects.equals(nota1, alunoDTO.nota1) && Objects.equals(nota2, alunoDTO.nota2) && Objects.equals(nota3, alunoDTO.nota3) && Objects.equals(nota4, alunoDTO.nota4) && Objects.equals(faltas, alunoDTO.faltas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primeiroNome, ultimoNome, nota1, nota2, nota3, nota4, faltas);
    }

    @Override
    public String toString() {
        return "AlunoDTO{" +
                "primeiroNome='" + primeiroNome + '\'' +
                ", ultimoNome='" + ultimoNome + '\'' +
                ", nota1=" + nota1 +
                ", nota2=" + nota2 +
                ", nota3=" + nota3 +
                ", nota4=" + nota4 +
                ", faltas=" + faltas +
                '}';
    }
}
