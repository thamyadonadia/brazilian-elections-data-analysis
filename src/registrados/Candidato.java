package registrados;

import java.time.LocalDate;

public class Candidato {
    private String nomeUrna;
    private int cargo;
    private int númeroUrna;
    private Partido relaçãoPartidária;
    private LocalDate nascimento;
    private int situaçãoEleitoral;
    private int genero;


    public Candidato(String nomeUrna, int cargo, int númeroUrna, Partido relaçãoPartidária, LocalDate nascimento, int situaçãoEleitoral, int genero) {
        this.nomeUrna = nomeUrna;
        this.cargo = cargo;
        this.númeroUrna = númeroUrna;
        this.relaçãoPartidária = relaçãoPartidária;
        this.nascimento = nascimento;
        this.situaçãoEleitoral = situaçãoEleitoral;
        this.genero = genero;
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
}
