import java.util.ArrayList;
import java.util.List;

public class ArvoreAVL {
    private AvlNode root = null;

    public ArvoreAVL( ) {
        root = null;
    }

    public void clear() {
        root = null;
    }
    public boolean isEmpty() {
        return root == null;
    }

    public AvlNode getRootNode (){
        return root;
    }

    private static int height( AvlNode t ) {
        return t == null ? -1 : t.height;
    }

    private static int max( int lhs, int rhs ) {
        return lhs > rhs ? lhs : rhs;
    }

    private int getFactor (AvlNode t) {
        return height( t.left ) - height( t.right );
    }

    public boolean insert (int x) {
        root = insert (x, root);
        return true;
    }

    private AvlNode insert (int x, AvlNode t) {
        if( t == null )
            t = new AvlNode( x, null, null );
        else if( x<t.key ) t.left = insert( x, t.left );
        else if( x>t.key) t.right = insert( x, t.right );
        t = balance (t);
        return t;
    }

    public boolean insertByCpf (int index, List<Pessoa> pessoas) {
        root = insertByCpf(index, root, pessoas);
        return true;
    }

    private AvlNode insertByCpf(int index, AvlNode t, List<Pessoa> pessoas) {
        if( t == null ) {
            t = new AvlNode(index, null, null);
        }
        else if( pessoas.get(index).getCpf() < pessoas.get(t.key).getCpf()) {
            t.left = insertByCpf( index, t.left, pessoas);
        }

        else if( pessoas.get(index).getCpf() > pessoas.get(t.key).getCpf()){
            t.right = insertByCpf( index, t.right, pessoas );
        }

        t = balance (t);
        return t;
    }

    public boolean insertByNome (int index, List<Pessoa> pessoas) {
        root = insertByNome(index, root, pessoas);
        return true;
    }

    private AvlNode insertByNome(int index, AvlNode t, List<Pessoa> pessoas) {
        if( t == null ) {
            t = new AvlNode(index, null, null);
        }
        else if( pessoas.get(index).getNome().compareTo(pessoas.get(t.key).getNome()) < 0) {
            t.left = insertByNome( index, t.left, pessoas);
        }

        else if( pessoas.get(index).getNome().compareTo(pessoas.get(t.key).getNome()) > 0){
            t.right = insertByNome( index, t.right, pessoas );
        }

        t = balance (t);
        return t;
    }




    public AvlNode balance (AvlNode t) {
        if ( getFactor(t) == 2 ) {
            if (getFactor (t.left)>0) t = doRightRotation( t );
            else t = doDoubleRightRotation( t );
        }
        else if ( getFactor(t) == -2 ) {
            if ( getFactor(t.right)<0 ) t = doLeftRotation( t );
            else t = doDoubleLeftRotation( t );
        }
        t.height = max( height( t.left ), height( t.right ) ) + 1;
        return t;
    }

    private static AvlNode doRightRotation( AvlNode k2 ) {
        AvlNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = max( height( k2.left ), height( k2.right ) ) + 1;
        k1.height = max( height( k1.left ), k2.height ) + 1;
        return k1;
    }

    private static AvlNode doLeftRotation( AvlNode k1 ) {
        AvlNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = max( height( k1.left ), height( k1.right ) ) + 1;
        k2.height = max( height( k2.right ), k1.height ) + 1;
        return k2;
    }

    private static AvlNode doDoubleRightRotation( AvlNode k3 ) {
        k3.left = doLeftRotation( k3.left );
        return doRightRotation( k3 );
    }

    private static AvlNode doDoubleLeftRotation( AvlNode k1 ) {
        k1.right = doRightRotation( k1.right );
        return doLeftRotation( k1 );
    }

    public AvlNode search(int el) {
        return search(root,el);
    }

    protected AvlNode search(AvlNode p, int el) {
        while (p != null) {
            /* se valor procuradp == chave do nó retorna referência ao nó */
            if (el==p.key) return p;
                /* se valor procurado < chave do nó, procurar na sub-árvore esquerda deste nó */
            else if (el<p.key) p = p.left;
                /* se valor procurado > chave do nó, procurar na sub-árvore direita deste nó */
            else p = p.right;
        }
        // caso chave não foi achada, retorna null
        return null;
    }

    public AvlNode searchByCpf(long cpf, List<Pessoa> pessoas) {
        return searchByCpf(root,cpf, pessoas);
    }

    protected void searchByNome(AvlNode node, String nome, List<Pessoa> pessoas, List<AvlNode> nodes) {

        if(node != null) {
            if (pessoas.get(node.key).getNome().startsWith(nome)) {
                nodes.add(node);
                if(node.left != null && pessoas.get(node.left.key).getNome().startsWith(nome)){
                    searchByNome(node.left, nome, pessoas, nodes);
                }
                if(node.right != null && pessoas.get(node.right.key).getNome().startsWith(nome)){
                    searchByNome(node.right, nome, pessoas, nodes);
                }
            }
            else if (pessoas.get(node.key).getNome().compareTo(nome) < 0) {
                searchByNome(node.right, nome, pessoas, nodes);
            } else if (pessoas.get(node.key).getNome().compareTo(nome) > 0) {
                searchByNome(node.left, nome, pessoas, nodes);
            }
        }
    }

    public List<AvlNode> searchByNome(String nome, List<Pessoa> pessoas) {
        ArrayList<AvlNode> nodes = new ArrayList<AvlNode>();
        searchByNome(root, nome, pessoas, nodes);
        return nodes;
    }

    protected AvlNode searchByCpf(AvlNode node, long cpf, List<Pessoa> pessoas) {
        while (node != null) {
            /* se valor procuradp == chave do nó retorna referência ao nó */
            if (cpf == pessoas.get(node.key).getCpf()) return node;
                /* se valor procurado < chave do nó, procurar na sub-árvore esquerda deste nó */
            else if (cpf < pessoas.get(node.key).getCpf()) node = node.left;
                /* se valor procurado > chave do nó, procurar na sub-árvore direita deste nó */
            else node = node.right;
        }
        // caso chave não foi achada, retorna null
        return null;
    }

    public void inorder() {
        inorder(root);
    }
    protected void inorder(AvlNode p) {
        if (p != null) {
            inorder(p.left);
            System.out.print(p.key+" - ");
            inorder(p.right);
        }
    }

    protected AvlNode searchFather (int el) {
        AvlNode p = root;
        AvlNode prev = null;
        while (p != null && !(p.key==el)) {  // acha o nó p com a chave el
            prev = p;
            if (p.key<el)
                p = p.right;
            else p = p.left;
        }
        if (p!=null && p.key==el) return prev;
        return null;
    }

}
