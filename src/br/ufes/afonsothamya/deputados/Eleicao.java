package br.ufes.afonsothamya.deputados;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

import br.ufes.afonsothamya.deputados.registrados.*;

public class Eleicao {
    // modificado para Hashmap
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

    // TODO: talvez trocar o nome do parâmetro?
    public void adicionaCandidatos(Candidato c) {
        candidatos.put(c.getNúmeroUrna(), c);
        if ((c.getSituaçãoEleitoral() == 2) || (c.getSituaçãoEleitoral() == 3)) {
            this.vagas++;
        }
    }

    public LocalDate getDia() {
        return dia;
    }

    public LinkedList<Partido> getPartidos() {
        return new LinkedList<Partido>(partidos);

    }

    // TODO: talvez trocar o nome do parâmetro?
    public void adicionaPartidos(Partido p) {
        partidos.add(p);
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public int getVagas() {
        return vagas;
    }

    // criado pra conseguir gerar os partidos
    public Partido temEssePartido(String nm_Partido) {
        for (Partido p : partidos) {
            if (p.getSiglaPartido().equals(nm_Partido)) {
                return p;
            }
        }

        return null;
    }

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

}