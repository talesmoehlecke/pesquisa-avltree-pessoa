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

//        arvoreAVL.insert(2);
//        arvoreAVL.insert(3);
//        arvoreAVL.insert(1);
//        arvoreAVL.insert(5);
//        arvoreAVL.insert(4);
        Scanner scanner = new Scanner(System.in) ;
        scanner.nextLine();


    }
}
