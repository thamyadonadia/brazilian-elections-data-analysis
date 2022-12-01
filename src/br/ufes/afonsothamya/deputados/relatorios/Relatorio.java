package br.ufes.afonsothamya.deputados.relatorios;

import java.time.Period;
import java.util.LinkedList;
import java.util.List;

import br.ufes.afonsothamya.deputados.eleicao.Eleicao;
import br.ufes.afonsothamya.deputados.io.Impressora;
import br.ufes.afonsothamya.deputados.registrados.*;

public class Relatorio {
    private Eleicao deputados;
    private Impressora imprime;
    private List<Candidato> candidatosOrdenadosVoto;
    private List<Partido> partidosOrdenadosEleito;

    public Relatorio(Eleicao deputados) {
        this.deputados = deputados;
        // armazena uma lista ordenada de deputados
        candidatosOrdenadosVoto = deputados.getCandidatos();
        candidatosOrdenadosVoto.sort(null);
        // faz o mesmo com partidos
        partidosOrdenadosEleito = deputados.getPartidos();
        partidosOrdenadosEleito.sort(null);
        imprime = new Impressora();
    }

    /**
     * Coleta o número de vagas e passa para impressora
     * <li>referente ao relatório 01
     */
    public void numeroVagas() {
        imprime.numeroVagas(deputados.getVagas());
    }

    /**
     * Coleta a lista ordenada de candidatos e passa para impressora
     * <li>referente ao relatório 02
     */
    public void deputadosEleitos() {
        imprime.candidatosEleitos(deputados, candidatosOrdenadosVoto);
    }

    /**
     * Percorre a lista de candidadtos para coletar dados e passa para impressora
     * <li>coleta os candidatos prejudicados e beneficiados pelo sistema de eleição
     * <li>referente aos relatórios 03 ,04 e 05
     */
    public void deputadosMaisVotados() {
        List<Candidato> prejudicados = new LinkedList<>();
        List<Candidato> beneficiados = new LinkedList<>();
        List<Candidato> candidatosOrdenadosVotosTotal = new LinkedList<>();
        int numeroVagas = deputados.getVagas(); int contador = 1; 


        for (Candidato c : candidatosOrdenadosVoto) {
            // mais votados
            if (contador <= numeroVagas) {
                candidatosOrdenadosVotosTotal.add(c);
            }

            // mais votados mas não eleitos
            if ((contador <= numeroVagas) &&  !(c.ehEleito())) {
                prejudicados.add(c);
            } 
            //eleitos mas não entre os mais votados
            else if ((contador > numeroVagas) && c.ehEleito()) {
                beneficiados.add(c);
            }
            contador++;
        }
        
        imprime.candidatosMaisVotados(candidatosOrdenadosVotosTotal, prejudicados, beneficiados, candidatosOrdenadosVoto);
    }

    /**
     * Coleta os partidos mais votados e passa para impressora
     * <li>referente ao relatório 06
     */
    public void partidosEleitos() {
        imprime.partidoVotos(deputados, partidosOrdenadosEleito);
    }

    /**
     * Ordena os candidatos de cada partido, coleta o mais votado de cada e passa para
     * impressora
     * <li>referente ao relatório 08
     */
    public void primeiroUltimoPartidos() {
        //ordena a lista de candidatos dentro da cada partido
        for(Partido p: partidosOrdenadosEleito){
            p.ordenaCandidatos();
        }

        List<Candidato> candidatosMaisVotados = new LinkedList<>();

        //coleta o candidato mais votado em cada partido, se possuir candidato
        for(Partido p: partidosOrdenadosEleito){
            if(p.getCandidatos().size()>0){
                candidatosMaisVotados.add(p.getCandidatos().getFirst());
            }   
        }

        //ordena a lista por numero de votos
        candidatosMaisVotados.sort(null);

        imprime.primeiroUltimoPartidos(candidatosMaisVotados);
    }

    /**
     * Calcula dados sobre idade dos eleitos, passa os dados para impressora
     * <li>referente ao relatório 09
     */
    public void eleitosPorIdade(){
        double divisaoIdade[] = new double[5]; 
        double porcentagemIdade[] = new double[5];
        double totalCandidatosEleitos = deputados.getNumeroCandidatosEleitos();
        Period periodo; int idade;

        for(Candidato c: deputados.getCandidatos()){
            if(c.ehEleito()){

                // calcula a idade de cada candidato
                periodo = Period.between(c.getNascimento(), deputados.getDia());
                idade = periodo.getYears();

                // contabiliza em um veto
                if(idade < 30) divisaoIdade[0]++;
                else if(idade < 40) divisaoIdade[1]++;
                else if(idade < 50) divisaoIdade[2]++;
                else if(idade < 60) divisaoIdade[3]++;
                else divisaoIdade[4]++;
            }
        }
        
        // calcula as porcentagens
        porcentagemIdade[0] = (divisaoIdade[0] / totalCandidatosEleitos) * 100;
        porcentagemIdade[1] = (divisaoIdade[1] / totalCandidatosEleitos) * 100;
        porcentagemIdade[2] = (divisaoIdade[2] / totalCandidatosEleitos) * 100;
        porcentagemIdade[3] = (divisaoIdade[3] / totalCandidatosEleitos) * 100;
        porcentagemIdade[4] = (divisaoIdade[4] / totalCandidatosEleitos) * 100;

        imprime.distribuicaoEleitosPorIdade(divisaoIdade, porcentagemIdade);
    }

    /**
     * Calcula dados sobre o sexo dos eleitos, passsa os dados para impressora
     * <li>referente ao relatório 10
     */
    public void eleitosPorSexo(){
        double totalCandidatosEleitos = deputados.getNumeroCandidatosEleitos();
        double totalMulheresEleitas = 0; 
        double totalHomensEleitos = 0;
        
        for(Candidato c : deputados.getCandidatos()){
            if(c.ehEleito()){
                //contabiliza homens e mulheres
                if(c.getGenero() == 2) totalHomensEleitos++;
                else if(c.getGenero() == 4) totalMulheresEleitas++;  
            }
        }   

        //calcula as porcentagens
        double porcentagemHomens = (totalHomensEleitos / totalCandidatosEleitos) * 100;
        double porcentagemMulheres = (totalMulheresEleitas / totalCandidatosEleitos) * 100;
       
        imprime.distribuicaoEleitosPorSexo(totalMulheresEleitas, porcentagemMulheres, totalHomensEleitos, porcentagemHomens);
    }

    /**
     * Calcula a distribuição dos votos pela eleição, passa para impressora
     * <li>referente ao relatório 11
     */
    public void distribuicaoVotos(){
        int totalVotosValidos = 0, totalVotosNominais = 0, totalVotosLegenda = 0;
        double porcentagemVotosNominais, porcentagemVotosLegenda;

        // coleta o numero de votos totais de legenda de cada partido
        for(Partido p: deputados.getPartidos()){
            totalVotosLegenda += p.getnumVotos();
        }

        // coleta o numero de votos totais normais de cada candidato
        for(Candidato c: deputados.getCandidatos()){
            totalVotosNominais += c.getNumVotos();
        }

        // calcula o total de votos validos e as porcentagens
        totalVotosValidos = totalVotosLegenda + totalVotosNominais;
        porcentagemVotosLegenda = ((double)totalVotosLegenda / (double)totalVotosValidos) * 100;
        porcentagemVotosNominais = ((double)totalVotosNominais / (double)totalVotosValidos) * 100;
        imprime.distribuicaoVotos(totalVotosValidos, totalVotosNominais, totalVotosLegenda, porcentagemVotosNominais, porcentagemVotosLegenda);
    }
}
    
