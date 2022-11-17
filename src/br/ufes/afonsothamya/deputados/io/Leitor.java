package br.ufes.afonsothamya.deputados.io;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.ufes.afonsothamya.deputados.Eleicao;
import br.ufes.afonsothamya.deputados.registrados.*;

public class Leitor {
    InputStream isArquivoCandidatos;
    InputStream isArquivoVotos;
    InputStreamReader isrArquivoCandidatos;
    InputStreamReader isrArquivoVotos;
    BufferedReader brArquivoCandidatos;
    BufferedReader brArquivoVotos;

    // TODO: tratar corretamente a UnsupportedEncondingException
    public Leitor(String caminhoArquivoCandidatos, String caminhoArquivoVotos) throws UnsupportedEncodingException {
        try {
            this.isArquivoCandidatos = new FileInputStream(caminhoArquivoCandidatos);
            this.isrArquivoCandidatos = new InputStreamReader(isArquivoCandidatos, "ISO-8859-1");
            this.brArquivoCandidatos = new BufferedReader(isrArquivoCandidatos);

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo " + caminhoArquivoCandidatos + " não foi encontrado!");
            System.exit(1);
        }

        try {
            this.isArquivoVotos = new FileInputStream(caminhoArquivoVotos);
            this.isrArquivoVotos = new InputStreamReader(isArquivoVotos, "ISO-8859-1");
            this.brArquivoVotos = new BufferedReader(isrArquivoVotos);

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo " + caminhoArquivoVotos + " não foi encontrado!");
            System.exit(1);
        }
    }

    // função para realizar a leitura dos arquivos
    public void leituraCandidatos(Eleicao deputados) {
        int cargo;
        String[] colunas;
        Candidato pessoa;
        Partido grupo;

        if (deputados.getTipoConsulta().equals("--federal"))
            cargo = 6;
        else
            cargo = 7;

        try {
            String linha = brArquivoCandidatos.readLine();
            linha = brArquivoCandidatos.readLine();

            while (linha != null) {
                colunas = linha.split(";");

                grupo = deputados.temEssePartido(colunas[28].replace("\"", ""));
                if (grupo == null) {
                    grupo = new Partido(cargo, adaptaStringInt(colunas[27]), colunas[28].replace("\"", ""));
                    deputados.adicionaPartidos(grupo);
                }
                if (cargo == adaptaStringInt(colunas[13])
                        && (2 == adaptaStringInt(colunas[24]) || 16 == adaptaStringInt(colunas[24]))) {
                    pessoa = new Candidato(colunas[18].replace("\"", ""), colunas[17].replace("\"", ""), cargo,
                            adaptaStringInt(colunas[16]),
                            adaptaStringInt(colunas[27]), colunas[28].replace("\"", ""),
                            LocalDate.parse(colunas[42].replace("\"", ""), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                            adaptaStringInt(colunas[56]), adaptaStringInt(colunas[45]),
                            adaptaStringInt(colunas[30]));
                    grupo.adicionaCandidatos(pessoa);
                    deputados.adicionaCandidatos(pessoa);

                }

                linha = brArquivoCandidatos.readLine();

            }

        } catch (IOException e) {
            System.out.println("Erro de I/O");
            System.exit(1);
        }

    }

    // função para realizar a leitura dos arquivos
    public void leituraVotos(Eleicao deputados) {
        int cargo;
        String[] colunas;
        Partido grupo; // mudar nome da variável "p" Feito
        Candidato pessoa;

        if (deputados.getTipoConsulta().equals("--federal"))
            cargo = 6;

        else
            cargo = 7;

        try {

            String linha = brArquivoVotos.readLine();
            linha = brArquivoVotos.readLine();
            while (linha != null) {
                colunas = linha.split(";");

                // ignorar linhas com o NR_VOTAVEL == 95, 96, 97, 98 Feito
                if ((cargo == adaptaStringInt(colunas[17]))
                        && !(94 < adaptaStringInt(colunas[19]) && 99 > adaptaStringInt(colunas[19]))) {
                    pessoa = deputados.getCandidatoKey(adaptaStringInt(colunas[19]));
                    if (pessoa == null) {
                        grupo = deputados.temEssePartido(adaptaStringInt(colunas[19]));
                        if (grupo != null) {
                            grupo.addNumVotos(adaptaStringInt(colunas[21]));
                        }
                    } else {
                        pessoa.addNumVotos(adaptaStringInt(colunas[21]));
                    }
                }

                linha = brArquivoVotos.readLine();
            }

        } catch (IOException e) {
            System.out.println("Erro de I/O");
            System.exit(1);
        }
    }

    public static Integer adaptaStringInt(String numero) {
        int result;

        result = Integer.parseInt(numero.replace("\"", ""));
        return result;
    }
}
