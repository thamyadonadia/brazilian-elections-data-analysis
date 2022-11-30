package br.ufes.afonsothamya.deputados.io;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import br.ufes.afonsothamya.deputados.eleicao.Eleicao;
import br.ufes.afonsothamya.deputados.registrados.*;

public class Impressora {

    /**
     * Imprime o número de vagas da eleição
     * <li>referente ao relatório 01
     * @param numVagas da eleição
     */
    public void numeroVagas(int numVagas) {
        System.out.println("Número de vagas: " + numVagas + "\n");
    }

    /**
     * Imprime os candidatos eleitos.
     * <li>referente ao relatório 02
     * @param deputados Eleição a ser prcessada
     * @param candidatosOrdenados Lista de candidatos ordenada por voto
     */
    public void candidatosEleitos(Eleicao deputados, List<Candidato> candidatosOrdenados) {
        NumberFormat nf = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
        int contador = 1;

        //verificação de modalidade para print específico
        if (deputados.getTipoConsulta().equals("--federal")) System.out.println("Deputados federais eleitos:");
        else System.out.println("Deputados estaduais eleitos:");

        //percorre lista de candidatos ordenados por voto
        for (Candidato c : candidatosOrdenados) {

            //se eleito, imprime suas informações
            if (c.ehEleito()) {
                System.out.print(contador + " - ");

                if (c.getNumFederação() != -1) System.out.print("*");

                System.out.println(c.getNomeUrna() + " (" + c.getSiglaPartido() + ", " + nf.format(c.getNumVotos()) + " votos)");
                contador++;
            }
        }
        System.out.printf("\n");
    }

    /**
     * Imprime candidatos mais votados e diferenças entre a lista e os eleitos.
     * <li>referente ao relatório 03, 04 e 05
     * @param candidatosOrdenadosVotos Lista de candidatos ordenada por voto com o tamanho de vagas
     * @param prejudicados Lista de pessoas prejudicadas pelo sistema atual
     * @param beneficiados Lista de pessoas beneficiadas pelo sistema atual
     * @param candidatosOrdenadosVotosTotal Lista de candidatos da eleição ordenada por voto
     */
    public void candidatosMaisVotados(List<Candidato> candidatosOrdenadosVotos, List<Candidato> prejudicados, List<Candidato> beneficiados, List<Candidato> candidatosOrdenadosVotosTotal) {
        NumberFormat nf = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
        int contador = 1; 

        System.out.println("Candidatos mais votados (em ordem decrescente de votação e respeitando número de vagas):");

        // impressão do relatório 3 (mais votados)
        for (Candidato c : candidatosOrdenadosVotos) {
    
            System.out.print(contador + " - ");

            if (c.getNumFederação() != -1) System.out.print("*");

            System.out.println(c.getNomeUrna() + " (" + c.getSiglaPartido() + ", " + nf.format(c.getNumVotos()) + " votos)");
                
            contador++;
        }

        // impressão do relatório 4 (mais votados mas não eleitos)
        System.out.println("\nTeriam sido eleitos se a votação fosse majoritária, e não foram eleitos:\n(com sua posição no ranking de mais votados)");
        for (Candidato c : prejudicados) {
            System.out.print((candidatosOrdenadosVotosTotal.indexOf(c) + 1) + " - ");

            if (c.getNumFederação() != -1) System.out.print("*");

            System.out.println(c.getNomeUrna() + " (" + c.getSiglaPartido() + ", " + nf.format(c.getNumVotos()) + " votos)");
        }

        // impressão do relatório 5 (eleitos mas não entre os mais votados)
        System.out.println("\nEleitos, que se beneficiaram do sistema proporcional:\n(com sua posição no ranking de mais votados)");
        for (Candidato c : beneficiados) {
            System.out.print((candidatosOrdenadosVotosTotal.indexOf(c) + 1) + " - ");

            if (c.getNumFederação() != -1) System.out.print("*");

            System.out.println(c.getNomeUrna() + " (" + c.getSiglaPartido() + ", " + nf.format(c.getNumVotos()) + " votos)");
        }
    }

    /**
     * Imprime informação geral dos partidos
     * <li> votos e candidatos eleitos de cada partido
     * <li>referente ao relaorio 06
     * @param deputados Eleição a ser processada
     * @param partidosOrdenados Lista de partidos ordenado por voto
     */
    public void partidoVotos(Eleicao deputados, List<Partido> partidosOrdenados) {
        NumberFormat nf = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
        int contador = 1; int totalVotos = 0; int eleitos = 0;

        // percorre a lista de partidos ordenados por votos totais imprimindo suas informações
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


    /**
     * Imprime primero e último colocado em cada partido
     * <li>referente ao relatório 08
     * @param candidatosMaisVotados lista de candidatos ordenados
     */
    public void primeiroUltimoPartidos(List<Candidato> candidatosMaisVotados) {
        NumberFormat nf = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
        int contador = 1;

        System.out.println("\nPrimeiro e último colocados de cada partido:");

        for(Candidato c: candidatosMaisVotados){

            // imprime primeiro a info do partido e do candidato mais votado
            System.out.print(contador + " - " + c.getSiglaPartido() + " - " + c.getNumPartido());
            System.out.print(", " + c.getNomeUrna() + " (" + c.getNúmeroUrna() + ", " + nf.format(c.getNumVotos()) + " votos) / ");
            
            // apartir desse candidato, é coletado o menos votado
            // do seu partido por meio de referências
            Partido p = c.getRelaçãoPartidária();
            Candidato ultimo = p.getCandidatos().getLast(); 
           
            //imprime info do ultimo colocado
            System.out.print(ultimo.getNomeUrna() + " (" + ultimo.getNúmeroUrna() + ", " + nf.format(ultimo.getNumVotos()));
            if(ultimo.getNumVotos()>1) System.out.print(" votos)\n");
            else System.out.print(" voto)\n");
        
            contador++;
        }
    }

    /**
     * Imprime a distribuição dos eleitos por idade
     * <li>referente ao relatório 09
     * @param divisaoIdade vetor de numero de eleitos
     * @param porcentagemIdade vetor de porcentagem de eleitos
     */
    public void distribuicaoEleitosPorIdade(double divisaoIdade[], double porcentagemIdade[]){
        NumberFormat nf = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
        nf.setMinimumFractionDigits(2); nf.setMaximumFractionDigits(2);

        System.out.println("\nEleitos, por faixa etária (na data da eleição):");
    
        System.out.println("      Idade < 30: " + (int)divisaoIdade[0] + " (" + nf.format(porcentagemIdade[0]) + "%)");
        System.out.println("30 <= Idade < 40: " + (int)divisaoIdade[1] + " (" + nf.format(porcentagemIdade[1]) + "%)");
        System.out.println("40 <= Idade < 50: " + (int)divisaoIdade[2] + " (" + nf.format(porcentagemIdade[2]) + "%)");
        System.out.println("50 <= Idade < 60: " + (int)divisaoIdade[3] + " (" + nf.format(porcentagemIdade[3]) + "%)");
        System.out.println("60 <= Idade     : " + (int)divisaoIdade[4] + " (" + nf.format(porcentagemIdade[4]) + "%)");
    }

    /**
     * Imprime a distribuição dos eleitos por sexo
     * <li>referente ao relatório 10
     * @param totalMulheresEleitas
     * @param porcentagemMulheres
     * @param totalHomensEleitos
     * @param porcentagemHomens
     */
    public void distribuicaoEleitosPorSexo(double totalMulheresEleitas, double porcentagemMulheres, double totalHomensEleitos, double porcentagemHomens){
        NumberFormat nf = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
        nf.setMinimumFractionDigits(2); nf.setMaximumFractionDigits(2);

        System.out.println("\nEleitos, por gênero:");
        System.out.println("Feminino:  " + (int)totalMulheresEleitas + " (" + nf.format(porcentagemMulheres) + "%)");
        System.out.println("Masculino: " + (int)totalHomensEleitos + " (" + nf.format(porcentagemHomens) + "%)");
    }

    /**
     * Imprime total de votos totais, nominais e de legenda
     * <li> referente ao relatório 11
     * @param totalVotosValidos 
     * @param totalVotosNominais
     * @param totalVotosLegenda
     * @param porcentagemVotosNominais
     * @param porcentagemVotosLegenda
     */
    public void distribuicaoVotos(int totalVotosValidos, int totalVotosNominais, int totalVotosLegenda, double porcentagemVotosNominais, double porcentagemVotosLegenda){
        NumberFormat nfPorcentagem = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
        nfPorcentagem.setMinimumFractionDigits(2);nfPorcentagem.setMaximumFractionDigits(2);

        NumberFormat nf = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));

        System.out.println("\nTotal de votos válidos:    " + nf.format(totalVotosValidos));
        System.out.println("Total de votos nominais:   " + nf.format(totalVotosNominais) + " (" + nfPorcentagem.format(porcentagemVotosNominais) + "%)" );
        System.out.print("Total de votos de legenda: " +  nf.format(totalVotosLegenda) + " (" + nfPorcentagem.format(porcentagemVotosLegenda) + "%)" );
    }
}