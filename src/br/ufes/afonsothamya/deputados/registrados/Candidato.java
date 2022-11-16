package br.ufes.afonsothamya.deputados.registrados;

import java.time.LocalDate;

public class Candidato {
    private String nomeUrna;
    private int cargo;
    private int númeroUrna;
    private Partido relaçãoPartidária;
    private int numPartido;
    private String siglaPartido;
    private LocalDate nascimento;
    private int situaçãoEleitoral;
    private int genero;
    private int numFederação;
    private int numVotos;

    public Candidato(String nomeUrna, int cargo, int númeroUrna, int numPartido, String siglaPartido,
            LocalDate nascimento, int situaçãoEleitoral, int genero, int numFederação) {
        this.nomeUrna = nomeUrna;
        this.cargo = cargo;
        this.númeroUrna = númeroUrna;
        this.numPartido = numPartido;
        this.siglaPartido = siglaPartido;
        this.nascimento = nascimento;
        this.situaçãoEleitoral = situaçãoEleitoral;
        this.genero = genero;
        this.numFederação = numFederação;
        this.numVotos = 0;
    }

    public String getNomeUrna() {
        return nomeUrna;
    }

    public int getCargo() {
        return cargo;
    }

    public int getNúmeroUrna() {
        return númeroUrna;
    }

    public Partido getRelaçãoPartidária() {
        return relaçãoPartidária;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public int getSituaçãoEleitoral() {
        return situaçãoEleitoral;
    }

    public int getGenero() {
        return genero;
    }

    public String getSiglaPartido() {
        return siglaPartido;
    }

    public int getNumPartido() {
        return numPartido;
    }

    public int getNumFederação() {
        return numFederação;
    }

    public int getNumVotos() {
        return numVotos;
    }

    public void addNumVotos(int numVotos) {
        this.numVotos += numVotos;
    }

    public void setRelaçãoPartidária(Partido relaçãoPartidária) {
        this.relaçãoPartidária = relaçãoPartidária;
    }

    public int comparaNumVotos(Candidato comparado) {
        return this.numVotos - comparado.numVotos;
    }
}
