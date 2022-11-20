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

                // caso o partido ainda não exista, ele é criado
                if (grupo == null) {
                    grupo = new Partido(cargo, adaptaStringInt(colunas[27]), colunas[28].replace("\"", ""));
                    deputados.adicionaPartidos(grupo);
                }

                if (cargo == adaptaStringInt(colunas[13])
                        && (2 == adaptaStringInt(colunas[68]) || 16 == adaptaStringInt(colunas[68])|| 1==adaptaDestinoInt(colunas[67]))) {
                    pessoa = new Candidato(colunas[18].replace("\"", ""), colunas[17].replace("\"", ""), cargo,
                            adaptaStringInt(colunas[16]),  grupo,
                            adaptaStringInt(colunas[27]), colunas[28].replace("\"", ""),
                            LocalDate.parse(colunas[42].replace("\"", ""), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                            adaptaStringInt(colunas[56]), adaptaStringInt(colunas[45]),
                            adaptaStringInt(colunas[30]),adaptaDestinoInt(colunas[67]));

                    grupo.adicionaCandidatos(pessoa); // adiciona o candidato no partido dele
                    deputados.adicionaCandidatos(pessoa); // adiciona o candidato na lista de candidatos da eleição
                    pessoa.setRelaçãoPartidária(grupo);
                }

                linha = brArquivoCandidatos.readLine();
            }

        } catch (IOException e) {
            System.out.println("Erro de I/O");
            System.exit(1);
        }
    }

    // função para realizar a leitura do arquivo de votos
    public void leituraVotos(Eleicao deputados) {
        int cargo;
        String[] colunas;
        Partido grupo;
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

                if ((cargo == adaptaStringInt(colunas[17]))
                        && !(94 < adaptaStringInt(colunas[19]) && 99 > adaptaStringInt(colunas[19]))) {

                    // coluna 19 representa o número de urna do candidato == key pra hash map
                    pessoa = deputados.getCandidatoKey(adaptaStringInt(colunas[19]));

                    if (pessoa == null) {
                        grupo = deputados.temEssePartido(adaptaStringInt(colunas[19]));

                        if (grupo != null) {
                            grupo.addNumVotos(adaptaStringInt(colunas[21]));
                        }

                    } else {
                        if(pessoa.getDestinoVotos()==1){
                            pessoa.getRelaçãoPartidária().addNumVotos(adaptaStringInt(colunas[21]));
                        }
                        else{
                            pessoa.addNumVotos(adaptaStringInt(colunas[21]));
                        }
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

    public static Integer adaptaDestinoInt(String codigo){
        String codigoResult;

        codigoResult = codigo.replace("\"", "");
        if(codigoResult.equals("Válido (legenda)")){
            return 1;
        }

        return 0;
    }
}
