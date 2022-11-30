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

    /**
     * Adiciona candidato a eleição
     * <li>se for eleito, incrementa numero de vagas
     * @param pessoa a ser adicionada
     */
    public void adicionaCandidatos(Candidato pessoa) {
        candidatos.put(pessoa.getNúmeroUrna(), pessoa);
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

    /**
     * Percorre a lista de Eleição e verifica se há partido com o nome.
     * @param nm_Partido nome do partido a ser procurado
     * @return Partido com nome inserido, senão houver, nulo
     */
    public Partido temEssePartido(String nm_Partido) {
        for (Partido p : partidos) {
            if (p.getSiglaPartido().equals(nm_Partido)) {
                return p;
            }
        }
        //senão, retorna nulo
        return null;
    }

    /**
     * Percorre a lista de Eleição e verifica se há partido com o número.
     * @param nr_Partido número do partido a ser procurado
     * @return Partido com número inserido, senão houver, nulo
     */ 
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

    /**
     * Percorre lista de candidatos e retorna número de eleitos
     * @return número de candidatos eleitos
     */
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