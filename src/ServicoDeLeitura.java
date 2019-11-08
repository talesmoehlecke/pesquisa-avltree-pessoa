import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ServicoDeLeitura {
    public List<Pessoa> leArquivo(String caminhoDoArquivo) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(caminhoDoArquivo));
        List<Pessoa> pessoas = new ArrayList<Pessoa>();
        String linha;

        while ((linha = csvReader.readLine()) != null) {
            String[] atributosPessoa = linha.split(";");
            Pessoa pessoa = transformarStringEmPessoa(atributosPessoa);
            pessoas.add(pessoa);
        }
        csvReader.close();
        return pessoas;
    }

    private Pessoa transformarStringEmPessoa(String[] atributosPessoa) {
        long cpf = Long.parseLong(atributosPessoa[0]);
        long rg = Long.parseLong(atributosPessoa[1]);
        String nome = atributosPessoa[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate dataDeNascimento = LocalDate.parse(atributosPessoa[3], formatter);
        String cidade = atributosPessoa[4];

        return new Pessoa(cpf, rg, nome, dataDeNascimento, cidade);
    }
}
