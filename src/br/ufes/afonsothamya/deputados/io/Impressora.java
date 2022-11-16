package br.ufes.afonsothamya.deputados.io;

import java.util.Collections;
import java.util.LinkedList;

import br.ufes.afonsothamya.deputados.Eleicao;
import br.ufes.afonsothamya.deputados.registrados.*;

public class Impressora {
    public void imprimeCandidatosEleitos(Eleicao deputados) {
        LinkedList<Candidato> candidatos;
        int contador = 0;

        candidatos = deputados.getCandidatos();

        candidatos.sort(null);

        Collections.sort(null, null);

        for (Candidato c : candidatos) {

            if (c.getCargo() == 6)
                System.out.println("Deputados federais eleitos:");
            else
                System.out.println("Deputados estaduais eleitos:");

            if ((c.getSituaçãoEleitoral() == 2) || (c.getSituaçãoEleitoral() == 3)) {
                System.out.print(contador + " - ");

                if (c.getNumFederação() == -1)
                    System.out.print("*");
                // problema - o candidato não tem o campo "quantidade de votos" K
                else
                    System.out.println(c.getNomeUrna() + "(" + c.getSiglaPartido() + "," + c.getNumVotos() + "votos)");
            }

            contador++;
        }
    }
}