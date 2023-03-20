import java.util.LinkedList;

public interface Tree {
    void preOrder();
    void inOrder();
    void postOrder();
    void addNodes(int parentIdx, int leftChildValue, int rightChildValue);
    void deleteNode(int idx);
}
