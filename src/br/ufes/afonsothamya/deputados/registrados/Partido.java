package br.ufes.afonsothamya.deputados.registrados;

import java.util.LinkedList;

public class Partido {
    private int cargo;
    private int qtdVotos;
    private String siglaPartido;
    private int numPartido;
    private LinkedList<Candidato> candidatos;

    public Partido(int cargo, int numPartido, String siglaPartido) {
        this.cargo = cargo;
        this.qtdVotos = 0;
        this.siglaPartido = siglaPartido;
        this.numPartido = numPartido;
        this.candidatos = new LinkedList<>();
    }

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    public int getQtdVotos() {
        return qtdVotos;
    }

    public void setQtdVotos(int qtdVotos) {
        this.qtdVotos = qtdVotos;
    }

    public String getSiglaPartido() {
        return siglaPartido;
    }

    public void setSiglaPartido(String siglaPartido) {
        this.siglaPartido = siglaPartido;
    }

    public int getNumPartido() {
        return numPartido;
    }

    public void setNumPartido(int numPartido) {
        this.numPartido = numPartido;
    }

    public void adicionaCandidatos(Candidato c) {
        candidatos.add(c);
    }

    public int getQtdVotosNominais() {
        int votosN = 0;
        for (Candidato c : candidatos) {
            votosN += c.getNumVotos();
        }
        return votosN;
    }

}
