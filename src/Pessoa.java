import java.time.LocalDate;

public class Pessoa {
    private long cpf;
    private long rg;
    private String nome;
    private String  dataDeNascimento;
    private String cidade;

    public Pessoa(long cpf, long rg, String nome, String dataDeNascimento, String cidade) {
        this.cpf = cpf;
        this.rg = rg;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.cidade = cidade;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public long getRg() {
        return rg;
    }

    public void setRg(long rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
