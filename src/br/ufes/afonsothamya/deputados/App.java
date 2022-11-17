package br.ufes.afonsothamya.deputados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.ufes.afonsothamya.deputados.io.Impressora;
import br.ufes.afonsothamya.deputados.io.Leitor;

public class App {
    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            System.out.println(
                    "uso: java -jar deputados.jar --<modalidade> <arquivo_candidatos> <arquivo_votação> <dia da votacao>");
            System.exit(1);
        }

        // obtenção dos dados passados como parâmetros na entrada padrão
        String modalidade = args[0];
        String caminhoArquivoCandidatos = args[1];
        String caminhoArquivoVotos = args[2];

        // formatação da data passada como parâmetro na entrada padrão
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dia = LocalDate.parse(args[3], formatador);
        dia.format(formatador);

        Eleicao processo = new Eleicao(dia, modalidade);
        Leitor reader = new Leitor(caminhoArquivoCandidatos, caminhoArquivoVotos);
        reader.leituraCandidatos(processo);
        reader.leituraVotos(processo);

        Impressora printer = new Impressora();
        printer.imprimeCandidatosEleitos(processo);

    }
}
