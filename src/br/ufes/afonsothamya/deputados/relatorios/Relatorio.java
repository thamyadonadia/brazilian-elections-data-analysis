package br.ufes.afonsothamya.deputados.relatorios;

import java.time.Period;
import java.util.LinkedList;

import br.ufes.afonsothamya.deputados.eleicao.Eleicao;
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

    // relatório 1
    public void numeroVagas() {
        imprime.numeroVagas(deputados.getVagas());
    }

    // relatório 2
    public void deputadosEleitos() {
        imprime.candidatosEleitos(deputados, candidatosOrdenadosVoto);
    }

    // relatórios 3, 4 e 5
    public void deputadosMaisVotados() {
        LinkedList<Candidato> prejudicados = new LinkedList<>();
        LinkedList<Candidato> beneficiados = new LinkedList<>();
        LinkedList<Candidato> candidatosOrdenadosVotosTotal = new LinkedList<>();
        int numeroVagas = deputados.getVagas(); int contador = 1; 


        for (Candidato c : candidatosOrdenadosVoto) {
            if (contador <= numeroVagas) {
                candidatosOrdenadosVotosTotal.add(c);
            }

            if ((contador <= numeroVagas) &&  !(c.ehEleito())) {
                prejudicados.add(c);
            } else if ((contador > numeroVagas) && c.ehEleito()) {
                beneficiados.add(c);
            }
            contador++;
        }
        
        imprime.candidatosMaisVotados(candidatosOrdenadosVotosTotal, prejudicados, beneficiados, candidatosOrdenadosVoto);
    }

    // relatório 6
    public void partidosEleitos() {
        imprime.partidoVotos(deputados, partidosOrdenadosEleito);
    }

    // relatório 8
    public void primeiroUltimoPartidos() {
        for(Partido p: partidosOrdenadosEleito){
            p.ordenaCandidatos();
        }

        LinkedList<Candidato> candidatosMaisVotados = new LinkedList<>();

        for(Partido p: partidosOrdenadosEleito){
            if(p.getCandidatos().size()>0){
                candidatosMaisVotados.add(p.getCandidatos().getFirst());
            }   
        }

        candidatosMaisVotados.sort(null);

        imprime.primeiroUltimoPartidos(candidatosMaisVotados);
    }

    // relatório 9
    public void eleitosPorIdade(){
        double divisaoIdade[] = new double[5]; 
        double porcentagemIdade[] = new double[5];
        double totalCandidatosEleitos = deputados.getNumeroCandidatosEleitos();
        Period periodo; int idade;

        for(Candidato c: deputados.getCandidatos()){
            if(c.ehEleito()){
                
                periodo = Period.between(c.getNascimento(), deputados.getDia());
                idade = periodo.getYears();

                if(idade < 30) divisaoIdade[0]++;
                else if(idade < 40) divisaoIdade[1]++;
                else if(idade < 50) divisaoIdade[2]++;
                else if(idade < 60) divisaoIdade[3]++;
                else divisaoIdade[4]++;
            }
        }
        
        porcentagemIdade[0] = (divisaoIdade[0] / totalCandidatosEleitos) * 100;
        porcentagemIdade[1] = (divisaoIdade[1] / totalCandidatosEleitos) * 100;
        porcentagemIdade[2] = (divisaoIdade[2] / totalCandidatosEleitos) * 100;
        porcentagemIdade[3] = (divisaoIdade[3] / totalCandidatosEleitos) * 100;
        porcentagemIdade[4] = (divisaoIdade[4] / totalCandidatosEleitos) * 100;

        imprime.distribuicaoEleitosPorIdade(divisaoIdade, porcentagemIdade);
    }

    // relatório 10
    public void eleitosPorSexo(){
        double totalCandidatosEleitos = deputados.getNumeroCandidatosEleitos();
        double totalMulheresEleitas = 0; 
        double totalHomensEleitos = 0;
        
        for(Candidato c : deputados.getCandidatos()){
            if(c.ehEleito()){
                if(c.getGenero() == 2) totalHomensEleitos++;
                else if(c.getGenero() == 4) totalMulheresEleitas++;  
            }
        }   

        double porcentagemHomens = (totalHomensEleitos / totalCandidatosEleitos) * 100;
        double porcentagemMulheres = (totalMulheresEleitas / totalCandidatosEleitos) * 100;
       
        imprime.distribuicaoEleitosPorSexo(totalMulheresEleitas, porcentagemMulheres, totalHomensEleitos, porcentagemHomens);
    }

    // relatório 11
    public void distribuicaoVotos(){
        int totalVotosValidos = 0, totalVotosNominais = 0, totalVotosLegenda = 0;
        double porcentagemVotosNominais, porcentagemVotosLegenda;

        for(Partido p: deputados.getPartidos()){
            totalVotosLegenda += p.getnumVotos();
        }

        for(Candidato c: deputados.getCandidatos()){
            totalVotosNominais += c.getNumVotos();
        }

        totalVotosValidos = totalVotosLegenda + totalVotosNominais;
        porcentagemVotosLegenda = ((double)totalVotosLegenda / (double)totalVotosValidos) * 100;
        porcentagemVotosNominais = ((double)totalVotosNominais / (double)totalVotosValidos) * 100;
        imprime.distribuicaoVotos(totalVotosValidos, totalVotosNominais, totalVotosLegenda, porcentagemVotosNominais, porcentagemVotosLegenda);
    }
}
    
