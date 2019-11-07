import sun.security.x509.AVA;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        ServicoDeLeitura servicoDeLeitura = new ServicoDeLeitura();
        List<Pessoa> pessoas = null;
        try {
            pessoas = servicoDeLeitura.leArquivo("C:\\Users\\I518547\\Desktop\\arquivo.csv");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        ArvoreAVL arvoreAVLParaCpf = new ArvoreAVL();
        ArvoreAVL arvoreAVLParaNome = new ArvoreAVL();
        for (int i = 0; i < pessoas.size(); i++) {
            arvoreAVLParaCpf.insertByCpf(i, pessoas);
            arvoreAVLParaNome.insertByNome(i, pessoas);
        }

        List<AvlNode> resultadoDaPesquisa = arvoreAVLParaNome.searchByNome("g", pessoas);

        Scanner scanner = new Scanner(System.in) ;
        String entradaDeComando ;
        System.out.println("Arquivo lido com sucesso! Deseja pesquisar no arquivo?");
        System.out.println("1 - Por CPF \n 2 - Por nome \n 3 - Por intervalo de data");
        System.out.println("Digite o numero correspondente:");
        entradaDeComando = scanner.nextLine();

        String entradaDePesquisa;

        if(entradaDeComando.equals("1")){
            System.out.println("Digite o cpf:");
            entradaDePesquisa = scanner.nextLine();
            long cpf = Long.parseLong(entradaDePesquisa);
            AvlNode nodoDeCpf = arvoreAVLParaCpf.searchByCpf(cpf, pessoas);
            Pessoa pessoa = pessoas.get(nodoDeCpf.key);
            imprimiDadosDaPessoa(pessoa);


        } else if(entradaDeComando.equals("2")){
            System.out.println("Digite o nome:");
            entradaDePesquisa = scanner.next();
            List<AvlNode> nodosDeNomes = arvoreAVLParaNome.searchByNome(entradaDePesquisa, pessoas);
            for (AvlNode nodoDeNome : nodosDeNomes){
                Pessoa pessoa = pessoas.get(nodoDeNome.key);
                imprimiDadosDaPessoa(pessoa);
            }

        } else if (entradaDeComando.equals("3")){

        } else System.out.println("Digite uma entrada válida!");



    }

    private static void imprimiDadosDaPessoa(Pessoa pessoa) {
        System.out.println("------------------------------------------");
        System.out.println("Nome: "+ pessoa.getNome());
        System.out.println("CPF:" + pessoa.getCpf());
        System.out.println("Cidade:" + pessoa.getCidade());
        System.out.println("RG:" + pessoa.getRg());
        System.out.println("Data de nascimento:" + pessoa.getDataDeNascimento());
        System.out.println("------------------------------------------");
    }
}
