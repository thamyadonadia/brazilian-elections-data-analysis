package br.ufes.nossonome.deputados.io;

import java.io.*;
import java.time.LocalDate;

import br.ufes.nossonome.deputados.Eleição;
import br.ufes.nossonome.deputados.registrados.*;

public class Leitor{
    InputStream isArquivoCandidatos; InputStream isArquivoVotos;
    InputStreamReader isrArquivoCandidatos; InputStreamReader isrArquivoVotos;
    BufferedReader brArquivoCandidatos; BufferedReader brArquivoVotos; 

    // TODO: tratar corretamente a UnsupportedEncondingException
    public Leitor(String caminhoArquivoCandidatos, String caminhoArquivoVotos) throws UnsupportedEncodingException{
        try{
            this.isArquivoCandidatos = new FileInputStream(caminhoArquivoCandidatos);
            this.isrArquivoCandidatos = new InputStreamReader(isArquivoCandidatos, "ISO-8859-1");
            this.brArquivoCandidatos = new BufferedReader(isrArquivoCandidatos);

        } catch (FileNotFoundException e){
            System.out.println("Arquivo " + caminhoArquivoCandidatos + " não foi encontrado!");
            System.exit(1);
        }

        try{
            this.isArquivoVotos = new FileInputStream(caminhoArquivoVotos);
            this.isrArquivoVotos = new InputStreamReader(isArquivoVotos, "ISO-8859-1");
            this.brArquivoVotos = new BufferedReader(isrArquivoVotos);

        }catch(FileNotFoundException e){
            System.out.println("Arquivo " + caminhoArquivoVotos + " não foi encontrado!");
            System.exit(1);
        }
    }

    // função para realizar a leitura dos arquivos
    public void leituraCandidatos(Eleição deputados){
        int cargo; String[] colunas; Candidato pessoa;

        if(deputados.getTipoConsulta()=="--federal") cargo=6;
        else cargo=7;
        
        try{
            String linha = brArquivoCandidatos.readLine();
            linha = brArquivoCandidatos.readLine();

            while (linha!=null) {
                colunas = linha.split(";");

                if(cargo==Integer.parseInt(colunas[13]) && (2==Integer.parseInt(colunas[24]) || 16==Integer.parseInt(colunas[24]))){
                    pessoa = new Candidato(colunas[18].replace("\"", ""), cargo, Integer.parseInt(colunas[16]), Integer.parseInt(colunas[29]), colunas[28].replace("\"", ""), LocalDate.parse(colunas[42]), Integer.parseInt(colunas[22]), Integer.parseInt(colunas[45]),Integer.parseInt(colunas[30]));

                    deputados.adicionaCandidatos(pessoa);
                }

                linha = brArquivoCandidatos.readLine();
            }
        
        }catch(IOException e){
            System.out.println("Erro de I/O");
            System.exit(1);
        }
    }

        // função para realizar a leitura dos arquivos
        public void leituraVotos(Eleição deputados){
            int cargo; String[] colunas; Partido p; // mudar nome da variável "p"
    
            if(deputados.getTipoConsulta()=="--federal") cargo=6;
            else cargo=7;
            
            try{
                String linha = brArquivoVotos.readLine();
                linha = brArquivoVotos.readLine();
    
                while (linha!=null) {
                    colunas = linha.split(";");
                    
                    // ignorar linhas com o NR_VOTAVEL == 95, 96, 97, 98
                    if(cargo==Integer.parseInt(colunas[13])){
                        
                    }
    
                    linha = brArquivoCandidatos.readLine();
                }
            
            }catch(IOException e){
                System.out.println("Erro de I/O");
                System.exit(1);
            }
        }
}

