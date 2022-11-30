package br.ufes.afonsothamya.deputados.registrados;

import java.util.LinkedList;
import java.util.List;

public class Partido implements Comparable<Partido> {
    private int cargo;
    private int numVotos; // votos de legenda
    private String siglaPartido;
    private int numPartido;
    private List<Candidato> candidatos;

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

    /**
     * Coleta a soma votos de cada candidato
     * 
     * @return numero de votos nominais
     */
    public int getQtdVotosNominais() {
        int votosN = 0;
        for (Candidato c : candidatos) {
            votosN += c.getNumVotos();
        }
        return votosN;
    }

    /**
     * Retorna numero de candidatos do partido que foram eleitos
     * 
     * <li>Percorre a lista e incrementa caso a função ehEleito 
     * retorne verdadeiro
     * 
     * @return numero de candidatos eleitos
     */
    public int getNmrCandidatosEleitos() {
        int eleitos = 0;
        for (Candidato c : candidatos) {
            if (c.ehEleito()) eleitos++;
        }

        return eleitos;
    }

    /**
     * Retorna uma lista encadeada nova de candidatos.
     *
     * @return cópia de lista de candidatos
     */
    public LinkedList<Candidato> getCandidatos(){
        return new LinkedList<Candidato>(candidatos);
    }

    /**
     * Compara partidos pelo total de votos.
     * 
     * @param o partido a ser comparado
     * 
     * @return -1 se menor, 0 se igual, 1 se maior
     */
    @Override
    public int compareTo(Partido o) {
        int result = (o.getQtdVotosNominais() + o.getnumVotos()) - (this.getQtdVotosNominais() + this.getnumVotos());

        if (result != 0) return result;
        else return this.getNumPartido() - o.getNumPartido();
    }

    /**
     * Ordena os candidatos dentro da lista de candidatos do partido.
     */
    public void ordenaCandidatos(){
        candidatos.sort(null);
    }
}
