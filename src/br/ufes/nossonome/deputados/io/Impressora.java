package br.ufes.nossonome.deputados.io;

import br.ufes.nossonome.deputados.registrados.*;
import br.ufes.nossonome.deputados.Eleição;

public class Impressora{
    public void imprimeCandidatosEleitos(Eleição deputados){
        int contador;

        for(Candidato c : deputados.getCandidatos()){
            if(c.getCargo() == 6) System.out.println("Deputados federais eleitos:");
            else System.out.println("Deputados estaduais eleitos:");

            if((c.getSituaçãoEleitoral() == 2) || (c.getSituaçãoEleitoral() == 3)){
                System.out.print(contador + " - ");

                if(c.getNumFederação() == -1) System.out.print("*");
                // problema - o candidato não tem o campo "quantidade de votos" K
                else System.out.println(c.getNomeUrna() + "(" + c.getSiglaPartido() + "," + c.getQtdVotos() + "votos)"); 
            }

            contador++;
        }
    }
}