package br.ufes.afonsothamya.deputados.io;

import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Locale;

import br.ufes.afonsothamya.deputados.Eleicao;
import br.ufes.afonsothamya.deputados.registrados.*;

public class Impressora {
    public void imprimeCandidatosEleitos(Eleicao deputados, LinkedList<Candidato> candidatos) {
        NumberFormat nf = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
        int contador = 1;

        System.out.println("Número de vagas: " + deputados.getVagas() + "\n");

        if (deputados.getTipoConsulta().equals("--federal"))
            System.out.println("Deputados federais eleitos:");
        else
            System.out.println("Deputados estaduais eleitos:");

        for (Candidato c : candidatos) {

            if ((c.getSituaçãoEleitoral() == 2) || (c.getSituaçãoEleitoral() == 3)) {
                System.out.print(contador + " - ");

                if (c.getNumFederação() != -1)
                    System.out.print("*");

                System.out.println(
                        c.getNomeUrna() + " (" + c.getSiglaPartido() + ", " + nf.format(c.getNumVotos()) + " votos)");
                contador++;
            }
        }
        System.out.printf("\n");
    }

    public void imprimeCandidatosMaisVotados(Eleicao deputados, LinkedList<Candidato> candidatos) {
        NumberFormat nf = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
        LinkedList<Candidato> prejudicados = new LinkedList<>();
        LinkedList<Candidato> beneficiados = new LinkedList<>();
        int contador = 1;

        System.out.println("Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):");

        for (Candidato c : candidatos) {
            if ((contador <= 30) && !c.ehEleito()) {
                prejudicados.add(c);
            } else if ((contador > 30) && c.ehEleito()) {
                beneficiados.add(c);
            }
            if (contador <= 30) {
                System.out.print(contador + " - ");

                if (c.getNumFederação() != -1)
                    System.out.print("*");

                System.out.println(
                        c.getNomeUrna() + " (" + c.getSiglaPartido() + ", " + nf.format(c.getNumVotos()) + " votos)");
                contador++;
            }
        }

        System.out.println(
                "\nTeriam sido eleitos se a votação fosse majoritária, e não foram eleitos:\n(com sua posição no ranking de mais votados)");
        for (Candidato c : prejudicados) {
            System.out.print((candidatos.indexOf(c) + 1) + " - ");

            if (c.getNumFederação() != -1)
                System.out.print("*");

            System.out.println(
                    c.getNomeUrna() + " (" + c.getSiglaPartido() + ", " + nf.format(c.getNumVotos()) + " votos)");
        }

        System.out.println(
                "\nEleitos, que se beneficiaram do sistema proporcional:\n(com sua posição no ranking de mais votados)");
        for (Candidato c : beneficiados) {
            System.out.print((candidatos.indexOf(c) + 1) + " - ");

            if (c.getNumFederação() != -1)
                System.out.print("*");

            System.out.println(
                    c.getNomeUrna() + " (" + c.getSiglaPartido() + ", " + nf.format(c.getNumVotos()) + " votos)");
        }
    }

    public void imprimePartidoVotos(Eleicao deputados, LinkedList<Partido> partidos) {
        NumberFormat nf = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
        int contador = 1;
        int totalVotos = 0;
        int eleitos = 0;

        System.out.println("\nVotação dos partidos e número de candidatos eleitos: ");
        for (Partido p : partidos) {
            System.out.print(contador + " - " + p.getSiglaPartido() + " - " + p.getNumPartido() + ", ");
            totalVotos = p.getQtdVotosNominais() + p.getnumVotos();
            if (totalVotos > 1)
                System.out.print(nf.format(totalVotos) + " votos (");
            else
                System.out.print(nf.format(totalVotos) + " voto (");

            if (p.getQtdVotosNominais() > 1)
                System.out.print(nf.format(p.getQtdVotosNominais()) + " nominais e ");
            else
                System.out.print(nf.format(p.getQtdVotosNominais()) + " nominal e ");

            System.out.print(nf.format(p.getnumVotos()) + " de legenda), ");
            eleitos = p.getNmrCandidatosEleitos();
            System.out.print(eleitos);
            if (eleitos > 1) {
                System.out.println(" candidatos eleitos");
            } else
                System.out.println(" candidato eleito");

            contador++;
        }
    }
}