package br.ufes.nossonome.deputados;

import java.time.LocalDate;
import java.util.LinkedList;

import br.ufes.nossonome.deputados.registrados.*;

public class Eleição {
    private LinkedList<Candidato> candidatos;
    private LocalDate dia;
    private LinkedList<Partido> partidos;
    private String tipoConsulta;

    
    public Eleição(LocalDate dia, String tipoConsulta){
        this.dia = dia;
        this.tipoConsulta = tipoConsulta;
    }

    public LinkedList<Candidato> getCandidatos(){
        return new LinkedList<Candidato>(candidatos);
    }

    // TODO: talvez trocar o nome do parâmetro?
    public void adicionaCandidatos(Candidato c){ 
        candidatos.add(c);
    }

    public LocalDate getDia(){
        return dia;
    }

    public LinkedList<Partido> getPartidos(){
        return new LinkedList<Partido>(partidos);

    }

    //  TODO: talvez trocar o nome do parâmetro?
    public void adicionaPartidos(Partido p){
        partidos.add(p);
    }

    public String getTipoConsulta(){
        return tipoConsulta;
    }
    
}