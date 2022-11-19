package br.ufes.afonsothamya.deputados.registrados;

import java.util.LinkedList;

public class Partido implements Comparable<Partido> {
    private int cargo;
    private int numVotos;
    private String siglaPartido;
    private int numPartido;
    private LinkedList<Candidato> candidatos;

    public Partido(int cargo, int numPartido, String siglaPartido) {
        this.cargo = cargo;
        this.numVotos = 0;
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

    public int getnumVotos() {
        return numVotos;
    }

    public void addNumVotos(int numVotos) {
        this.numVotos += numVotos;
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

    public int getNmrCandidatosEleitos() {
        int eleitos = 0;
        for (Candidato c : candidatos) {
            if (c.ehEleito()) eleitos++;
        }

        return eleitos;
    }

    public LinkedList<Candidato> getCandidatos(){
        return new LinkedList<Candidato>(candidatos);
    }

    @Override
    public int compareTo(Partido o) {
        int result = (o.getQtdVotosNominais() + o.getnumVotos()) - (this.getQtdVotosNominais() + this.getnumVotos());

        if (result != 0) return result;
        else return this.getNumPartido() - o.getNumPartido();
    }

    // ordena os candidatos dentro de um mesmo partido
    public void ordenaCandidatos(){
        candidatos.sort(null);
    }
}
