package br.ufes.afonsothamya.deputados.relatorios;

import java.util.LinkedList;

import br.ufes.afonsothamya.deputados.Eleicao;
import br.ufes.afonsothamya.deputados.io.Impressora;
import br.ufes.afonsothamya.deputados.registrados.*;

public class Relatorio {
    private Eleicao deputados;
    private Impressora imprime;
    private LinkedList<Candidato> candidatosOrdenadosVoto;
    private LinkedList<Partido> partidosOrdenadosEleito;

    public Relatorio(Eleicao deputados) {
        this.deputados = deputados;
        candidatosOrdenadosVoto = deputados.getCandidatos();
        candidatosOrdenadosVoto.sort(null);
        partidosOrdenadosEleito = deputados.getPartidos();
        partidosOrdenadosEleito.sort(null);
        imprime = new Impressora();
    }


    // PROBLEMAO: precisamos passar uma cópia da lista e não a lista oficial 



    // retornar uma string que vai ser passada para a impressora ou chamar a
    // impressora aqui
    // talvez seja melhor a segunda opção porque caso tenha algum erro, só muda a
    // impressora
    // relatório 1
    public void numeroVagas() {
        imprime.imprimeNumeroVagas(deputados);
    }

    // relatório 2
    public void deputadosEleitos() {
        // os candidatos precisam ser dispostos em ordem decressente de votos, talvez
        // seja
        // bom ordenar aqui mesmo
        imprime.imprimeCandidatosEleitos(deputados, candidatosOrdenadosVoto);
    }

    // relatórios 3, 4 e 5
    public void deputadosMaisVotados() {
        // os candidatos precisam ser dispostos em ordem decressente de votos, talvez
        // seja
        // bom ordenar aqui mesmo
        imprime.imprimeCandidatosMaisVotados(deputados, candidatosOrdenadosVoto);
    }

    // relatório 6
    public void partidosEleitos() {
        imprime.imprimePartidoVotos(deputados, partidosOrdenadosEleito);
    }

    // relatório 8
    public void primeiroUltimoPartidos() {
        this.ordenaCandidatosPartidos();
        imprime.imprimePrimeiroUltimoPartidos(deputados, partidosOrdenadosEleito);
    }

    // colocar como static?
    public void ordenaCandidatosPartidos(){
        for(Partido p: partidosOrdenadosEleito){
            p.ordenaCandidatos();
        }
    }

    // tomando como base essa função: vale mais a pena deixar apenas a impressão
    // na impressora e fazer o tratamento e as análises aqui mesmo
    public void eleitosPorSexo(){
        // colocar uma função na eleição.java para retornar o número de partidos e de candidatos?
        double totalCandidatosEleitos = 0;
        double totalMulheresEleitas = 0; 
        double totalHomensEleitos = 0;
        
        for(Candidato c : deputados.getCandidatos()){
            if(c.ehEleito()){
                totalCandidatosEleitos++;
                if(c.getGenero() == 2) totalHomensEleitos++;
                else if(c.getGenero() == 4) totalMulheresEleitas++;  
            }
        }   

        double porcentagemHomens = (totalHomensEleitos / totalCandidatosEleitos) * 100;
        double porcentagemMulheres = (totalMulheresEleitas / totalCandidatosEleitos) * 100;
       
        imprime.imprimeDistribuicaoEleitosPorSexo(totalMulheresEleitas, porcentagemMulheres, totalHomensEleitos, porcentagemHomens);
    }
}
