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

    public Eleicao(LocalDate dia, String tipoConsulta) {
        this.dia = dia;
        this.tipoConsulta = tipoConsulta;
    }

    public LinkedList<Candidato> getCandidatos() {
        return new LinkedList<Candidato>(candidatos.values());
    }

    // TODO: talvez trocar o nome do parâmetro?
    public void adicionaCandidatos(Candidato c) {
        candidatos.put(c.getNúmeroUrna(), c);
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

    // criado pra conseguir gerar os partidos
    public Partido temEssePartido(String nm_Partido) {
        for (Partido p : partidos) {
            if (p.getSiglaPartido() == nm_Partido) {
                return p;
            }
        }

        return null;
    }

}