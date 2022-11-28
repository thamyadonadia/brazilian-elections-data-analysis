package br.ufes.afonsothamya.deputados.eleicao;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import br.ufes.afonsothamya.deputados.registrados.*;

public class Eleicao {
    private Map<Integer, Candidato> candidatos;
    private LocalDate dia;
    private List<Partido> partidos;
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
        //verifica se é eleito para incrementar
        //o numero de vagas na eleição
        if (pessoa.ehEleito()) {
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
        //percorre lista de partidos da eleição em busca de um
        //com o mesmo nome do partido, se encontrar retorna tal
        for (Partido p : partidos) {
            if (p.getSiglaPartido().equals(nm_Partido)) {
                return p;
            }
        }
        //senão, retorna nulo
        return null;
    }

    // rever nome 
    public Partido temEssePartido(int nr_Partido) {
        //percorre lista de partidos da eleição em busca de um
        //com o mesmo numero de urna do partido, se encontrar retorna tal
        for (Partido p : partidos) {
            if (p.getNumPartido() == nr_Partido) {
                return p;
            }
        }
        //senão, retorna nulo
        return null;
    }

    public Candidato getCandidatoKey(int nr_votavel) {
        return this.candidatos.get(nr_votavel);
    }

    public int getNumeroCandidatosEleitos(){
        int numCandidatosEleitos = 0;
        
        //verifica dentro da lista de candidatos 
        //todos que possuem o status eleito
        for(Candidato c: this.getCandidatos()){
            if(c.ehEleito()){
                numCandidatosEleitos++;
            }
        }

        return numCandidatosEleitos;
    }

}