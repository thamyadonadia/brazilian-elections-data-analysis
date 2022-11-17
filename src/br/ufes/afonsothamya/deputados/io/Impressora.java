package br.ufes.afonsothamya.deputados.io;

import java.util.Comparator;
import java.util.LinkedList;

import br.ufes.afonsothamya.deputados.Eleicao;
import br.ufes.afonsothamya.deputados.registrados.*;

public class Impressora {
    public void imprimeCandidatosEleitos(Eleicao deputados) {
        LinkedList<Candidato> candidatos;
        int contador = 1;

        candidatos = deputados.getCandidatos();

        candidatos.sort(Comparator.comparing(Candidato::getNumVotos));

        System.out.println("Número de vagas: " + "test" + "\n\n");

        if (deputados.getTipoConsulta() == "--federal")
            System.out.println("Deputados federais eleitos:");
        else
            System.out.println("Deputados estaduais eleitos:");

        for (Candidato c : candidatos) {

            if ((c.getSituaçãoEleitoral() == 2) || (c.getSituaçãoEleitoral() == 3)) {
                System.out.print(contador + " - ");

                if (c.getNumFederação() != -1)
                    System.out.print("*");

                System.out.println(c.getNomeUrna() + " (" + c.getSiglaPartido() + ", " + c.getNumVotos() + " votos)");
                contador++;
            }
        }
    }
}