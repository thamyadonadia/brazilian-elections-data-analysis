package br.ufes.afonsothamya.deputados.registrados;

import java.time.LocalDate;

public class Candidato implements Comparable<Candidato> {
    private String nomeUrna;
    private String nomeCompleto;
    private int cargo;
    private int númeroUrna;
    private Partido relaçãoPartidária;
    private int numPartido;
    private String siglaPartido;
    private LocalDate nascimento;
    private int situaçãoEleitoral;
    private int genero;
    private int numFederação;
    private int numVotos; // votos nominais
    private int destinoVotos;

    public Candidato(String nomeUrna, String nomeCompleto, int cargo, int númeroUrna,Partido relaçãoPartidária, int numPartido,
            String siglaPartido, LocalDate nascimento, int situaçãoEleitoral, int genero, int numFederação, int destinoVotos) {
        this.nomeUrna = nomeUrna;
        this.nomeCompleto = nomeCompleto;
        this.cargo = cargo;
        this.númeroUrna = númeroUrna;
        this.relaçãoPartidária = relaçãoPartidária;
        this.numPartido = numPartido;
        this.siglaPartido = siglaPartido;
        this.nascimento = nascimento;
        this.situaçãoEleitoral = situaçãoEleitoral;
        this.genero = genero;
        this.numFederação = numFederação;
        this.destinoVotos = destinoVotos;
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

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setRelaçãoPartidária(Partido relaçãoPartidária) {
        this.relaçãoPartidária = relaçãoPartidária;
    }

    public int comparaNumVotos(Candidato comparado) {
        return this.numVotos - comparado.numVotos;
    }

    public int getDestinoVotos() {
        return destinoVotos;
    }

    /**
     * Verifica se o candidato é eleito
     * @return booleano para status do candidato
     */
    public boolean ehEleito() {
        return (this.getSituaçãoEleitoral() == 2) || (this.getSituaçãoEleitoral() == 3);
    }

    //compara o número de votos, se igual, compara idade
    //ordenando em ordem crescente de votos e mais velho
    
    /**
     * Compara candidatos pelo total de votos.
     * 
     * @param o candidato a ser comparado
     * 
     * @return -1 se menor, 0 se igual, 1 se maior
     */
    @Override
    public int compareTo(Candidato o) {
        int result = o.numVotos - this.numVotos;

        if (result == 0) {
            return this.getNascimento().compareTo(o.getNascimento());
        } else {
            return result;
        }
    }
}
