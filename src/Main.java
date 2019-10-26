import java.util.List;

public class Main {
    public static void main(String args[]) {
        ServicoDeLeitura servicoDeLeitura = new ServicoDeLeitura();

        try {
            List<Pessoa> pessoas = servicoDeLeitura.leArquivo("C:\\Users\\I518547\\Desktop\\arquivo.csv");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
