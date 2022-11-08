import java.text.Format;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class App {
    public static void main(String[] args) throws Exception {
        // obtenção dos dados passados como parâmetros na entrada padrão
        String modalidade =  args[0];
        String caminhoCandidato = args[1];
        String caminhoVotação = args[2];

        // formatação da data passada como parâmetro na entrada padrão
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dia = LocalDate.parse(args[3], formatador);
        dia.format(formatador);

    }
}
