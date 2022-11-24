package br.ufes.afonsothamya.deputados.eleicao;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

import br.ufes.afonsothamya.deputados.registrados.*;

public class Eleicao {
    private HashMap<Integer, Candidato> candidatos;
    private LocalDate dia;
    private LinkedList<Partido> partidos;
    private String tipoConsulta;
    private int vagas;

    public Eleicao(LocalDate dia, String tipoConsulta) {
        this.dia = dia;
        this.tipoConsulta = tipoConsulta;
        this.candidatos = new HashMap<Integer, Candidato>();
        this.partidos = new LinkedList<Partido>();
        this.vagas = 0;
    }

    public LinkedList<Candidato> getCandidatos() {
        return new LinkedList<Candidato>(candidatos.values());
    }

    public void adicionaCandidatos(Candidato pessoa) {
        candidatos.put(pessoa.getNúmeroUrna(), pessoa);
        if ((pessoa.getSituaçãoEleitoral() == 2) || (pessoa.getSituaçãoEleitoral() == 3)) {
            this.vagas++;
        }
    }

    public LocalDate getDia() {
        return dia;
    }

    public LinkedList<Partido> getPartidos() {
        return new LinkedList<Partido>(partidos);

    }

    public void adicionaPartidos(Partido grupo) {
        partidos.add(grupo);
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public int getVagas() {
        return vagas;
    }
    public Partido temEssePartido(String nm_Partido) {
        for (Partido p : partidos) {
            if (p.getSiglaPartido().equals(nm_Partido)) {
                return p;
            }
        }

        return null;
    }

    // rever nome 
    public Partido temEssePartido(int nr_Partido) {
        for (Partido p : partidos) {
            if (p.getNumPartido() == nr_Partido) {
                return p;
            }
        }
        return null;
    }

    public Candidato getCandidatoKey(int nr_votavel) {
        return this.candidatos.get(nr_votavel);
    }

    public int getNumeroCandidatosEleitos(){
        int numCandidatosEleitos = 0;
        
        for(Candidato c: this.getCandidatos()){
            if(c.ehEleito()){
                numCandidatosEleitos++;
            }
        }

        return numCandidatosEleitos;
    }

}