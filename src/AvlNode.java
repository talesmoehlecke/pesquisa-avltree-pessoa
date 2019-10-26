public class AvlNode {
    protected int height;       // Height
    protected int key;
    protected AvlNode left, right;

    public AvlNode ( int theElement ) {
        this( theElement, null, null );
    }

    public AvlNode ( int theElement, AvlNode lt, AvlNode rt ) {
        key = theElement;
        left = lt;
        right = rt;
        height   = 0;
    }
}