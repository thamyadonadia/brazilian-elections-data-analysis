package br.ufes.afonsothamya.deputados.relatorios;

import br.ufes.afonsothamya.deputados.Eleicao;
import br.ufes.afonsothamya.deputados.io.Impressora;
import br.ufes.afonsothamya.deputados.registrados.*;

public class Relatorio {
    private Eleicao deputados;
    private Impressora imprime;

    public Relatorio(Eleicao deputados) {
        this.deputados = deputados;
    }

    // retornar uma string que vai ser passada para a impressora ou chamar a
    // impressora aqui
    // talvez seja melhor a segunda opção porque caso tenha algum erro, só muda a
    // impressora
    public String numeroVagas() {
        int numVagas = 0;

        // fazer esse calculo na eleição ou aqui?
        for (Candidato c : deputados.getCandidatos()) {
            if ((c.getSituaçãoEleitoral() == 2) || (c.getSituaçãoEleitoral() == 3)) {
                numVagas++;
            }
        }

        return "Número de vagas: " + numVagas + "\n";
    }

    public void deputadosEleitos() {
        // os candidatos precisam ser dispostos em ordem descrente de votos, talvez seja
        // bom ordenar aqui mesmo
        imprime.imprimeCandidatosEleitos(deputados);
    }

}
