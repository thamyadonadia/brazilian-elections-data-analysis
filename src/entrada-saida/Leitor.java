import java.io.*;
import java.util.*;
import registrados.Candidato;

public class Leitor{
    InputStream isArquivoCandidatos; InputStream isArquivoVotos;
    InputStreamReader isrArquivoCandidatos;
    BufferedReader brArquivoCandidatos; 

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

                if(cargo==Integer.parseInt(colunas[13])&& (2==Integer.parseInt(colunas[24]) || 16==Integer.parseInt(colunas[24]))){
                    pessoa = new Candidato(colunas[18].replace("\"", ""), cargo, Integer.parseInt(colunas[16]), , null, cargo, cargo)
                    




                }

                linha = brArquivoCandidatos.readLine();
            }
        }
        catch(IOException e){
            System.out.println("Erro de I/O");
            System.exit(1);
        }
    }

   


}
