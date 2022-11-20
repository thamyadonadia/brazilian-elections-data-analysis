package br.ufes.afonsothamya.deputados.io;

import java.text.NumberFormat;
import java.util.LinkedList;
import java.util.Locale;

import br.ufes.afonsothamya.deputados.Eleicao;
import br.ufes.afonsothamya.deputados.registrados.*;

// TODO: tirar o imprime na frente das funções
public class Impressora {
    public void imprimeNumeroVagas(Eleicao deputados) {
        System.out.println("Número de vagas: " + deputados.getVagas() + "\n");
    }

    public void imprimeCandidatosEleitos(Eleicao deputados, LinkedList<Candidato> candidatosOrdenados) {
        NumberFormat nf = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
        int contador = 1;

        if (deputados.getTipoConsulta().equals("--federal")) System.out.println("Deputados federais eleitos:");
        else System.out.println("Deputados estaduais eleitos:");

        for (Candidato c : candidatosOrdenados) {

            if ((c.getSituaçãoEleitoral() == 2) || (c.getSituaçãoEleitoral() == 3)) {
                System.out.print(contador + " - ");

                if (c.getNumFederação() != -1) System.out.print("*");

                System.out.println(c.getNomeUrna() + " (" + c.getSiglaPartido() + ", " + nf.format(c.getNumVotos()) + " votos)");
                contador++;
            }
        }
        System.out.printf("\n");
    }

    public void imprimeCandidatosMaisVotados(Eleicao deputados, LinkedList<Candidato> candidatosOrdenados) {
        NumberFormat nf = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
        LinkedList<Candidato> prejudicados = new LinkedList<>();
        LinkedList<Candidato> beneficiados = new LinkedList<>();
        int contador = 1; int numeroVagas = deputados.getVagas();

        System.out.println("Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):");

        for (Candidato c : candidatosOrdenados) {
            if (contador <= numeroVagas) {
                System.out.print(contador + " - ");

                if (c.getNumFederação() != -1) System.out.print("*");

                System.out.println(c.getNomeUrna() + " (" + c.getSiglaPartido() + ", " + nf.format(c.getNumVotos()) + " votos)");
                
            }

            if ((contador <= numeroVagas) &&  !(c.ehEleito())) {
                prejudicados.add(c);
            } else if ((contador > numeroVagas) && c.ehEleito()) {
                beneficiados.add(c);
            }
            contador++;
        }

        // impressão do relatório 4
        System.out.println("\nTeriam sido eleitos se a votação fosse majoritária, e não foram eleitos:\n(com sua posição no ranking de mais votados)");
        for (Candidato c : prejudicados) {
            System.out.print((candidatosOrdenados.indexOf(c) + 1) + " - ");

            if (c.getNumFederação() != -1) System.out.print("*");

            System.out.println(c.getNomeUrna() + " (" + c.getSiglaPartido() + ", " + nf.format(c.getNumVotos()) + " votos)");
        }

        // impressão do relatório 5
        System.out.println("\nEleitos, que se beneficiaram do sistema proporcional:\n(com sua posição no ranking de mais votados)");
        for (Candidato c : beneficiados) {
            System.out.print((candidatosOrdenados.indexOf(c) + 1) + " - ");

            if (c.getNumFederação() != -1) System.out.print("*");

            System.out.println(c.getNomeUrna() + " (" + c.getSiglaPartido() + ", " + nf.format(c.getNumVotos()) + " votos)");
        }
    }

    public void imprimePartidoVotos(Eleicao deputados, LinkedList<Partido> partidosOrdenados) {
        NumberFormat nf = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
        int contador = 1; int totalVotos = 0; int eleitos = 0;

        System.out.println("\nVotação dos partidos e número de candidatos eleitos: ");
        for (Partido p : partidosOrdenados) {
            System.out.print(contador + " - " + p.getSiglaPartido() + " - " + p.getNumPartido() + ", ");

            totalVotos = p.getQtdVotosNominais() + p.getnumVotos();

            if (totalVotos > 1) System.out.print(nf.format(totalVotos) + " votos (");
            else System.out.print(nf.format(totalVotos) + " voto (");

            if (p.getQtdVotosNominais() > 1) System.out.print(nf.format(p.getQtdVotosNominais()) + " nominais e ");
            else System.out.print(nf.format(p.getQtdVotosNominais()) + " nominal e ");

            System.out.print(nf.format(p.getnumVotos()) + " de legenda), ");

            eleitos = p.getNmrCandidatosEleitos();
            System.out.print(eleitos);

            if (eleitos > 1) System.out.println(" candidatos eleitos");
            else System.out.println(" candidato eleito");

            contador++;
        }
    }

    // relatório 8
    public void imprimePrimeiroUltimoPartidos(Eleicao deputados, LinkedList<Partido> partidosOrdenados) {
        NumberFormat nf = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
        int contador = 1;

        System.out.println("\nPrimeiro e último colocados de cada partido:");
        LinkedList<Candidato> candidatosMaisVotados = new LinkedList<>();

        for(Partido p: partidosOrdenados){
            if(p.getCandidatos().size()>0){
                candidatosMaisVotados.add(p.getCandidatos().getFirst());
            }   
        }

        candidatosMaisVotados.sort(null);

        for(Candidato c: candidatosMaisVotados){

            System.out.print(contador + " - " + c.getSiglaPartido() + " - " + c.getNumPartido());
            System.out.print(", " + c.getNomeUrna() + " (" + c.getNúmeroUrna() + ", " + nf.format(c.getNumVotos()) + " votos) / ");
            
            Partido p = c.getRelaçãoPartidária();
            Candidato ultimo = p.getCandidatos().getLast(); 
           
            System.out.print(ultimo.getNomeUrna() + " (" + ultimo.getNúmeroUrna() + ", " + nf.format(ultimo.getNumVotos()) + " votos)\n");
        
            contador++;
        }
    }


    public void imprimeDistribuicaoEleitosPorSexo(double totalMulheresEleitas, double porcentagemMulheres, double totalHomensEleitos, double porcentagemHomens){
        NumberFormat nf = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
        nf.setMinimumFractionDigits(2); nf.setMaximumFractionDigits(2);
        //TODO: não consegui colocar em duas casas decimais 
        System.out.println("\nEleitos, por gênero:");
        System.out.println("Feminino:  " + (int)totalMulheresEleitas + " (" + nf.format(porcentagemMulheres) + "%)");
        System.out.println("Masculino: " + (int)totalHomensEleitos + " (" + nf.format(porcentagemHomens) + "%)");
    }

    public void imprimeDistribuicaoEleitosPorIdade(double divisaoIdade[], double porcentagemIdade[]){
        NumberFormat nf = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
        nf.setMinimumFractionDigits(2); nf.setMaximumFractionDigits(2);

        System.out.println("\nEleitos, por faixa etária (na data da eleição):");
    
        System.out.println("      Idade < 30: " + (int)divisaoIdade[0] + " (" + nf.format(porcentagemIdade[0]) + "%)");
        System.out.println("30 <= Idade < 40: " + (int)divisaoIdade[1] + " (" + nf.format(porcentagemIdade[1]) + "%)");
        System.out.println("40 <= Idade < 50: " + (int)divisaoIdade[2] + " (" + nf.format(porcentagemIdade[2]) + "%)");
        System.out.println("50 <= Idade < 60: " + (int)divisaoIdade[3] + " (" + nf.format(porcentagemIdade[3]) + "%)");
        System.out.println("60 <= Idade     : " + (int)divisaoIdade[4] + " (" + nf.format(porcentagemIdade[4]) + "%)");
    }

    public void distribuicaoVotos(int totalVotosValidos, int totalVotosNominais, int totalVotosLegenda, double porcentagemVotosNominais, double porcentagemVotosLegenda){
        NumberFormat nfPorcentagem = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
        nfPorcentagem.setMinimumFractionDigits(2);nfPorcentagem.setMaximumFractionDigits(2);

        NumberFormat nf = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));

        System.out.println("\nTotal de votos válidos:    " + nf.format(totalVotosValidos));
        System.out.println("Total de votos nominais:   " + nf.format(totalVotosNominais) + " (" + nfPorcentagem.format(porcentagemVotosNominais) + "%)" );
        System.out.print("Total de votos de legenda: " +  nf.format(totalVotosLegenda) + " (" + nfPorcentagem.format(porcentagemVotosLegenda) + "%)" );
    }
   
}