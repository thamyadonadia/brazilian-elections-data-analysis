import java.time.LocalDate;
import java.util.LinkedList;

import registrados.Candidato;
import registrados.Partido;

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
        return candidatos;
    }

    public void setCandidatos(LinkedList<Candidato> candidatos){
        this.candidatos = candidatos;
    }

    public LocalDate getDia(){
        return dia;
    }

    public LinkedList<Partido> getPartidos(){
        return partidos;
    }

    public void setPartidos(LinkedList<Partido> partidos){
        this.partidos = partidos;
    }

    public String getTipoConsulta(){
        return tipoConsulta;
    }
    
}