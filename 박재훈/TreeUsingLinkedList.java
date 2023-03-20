import exeption.CustomNoSuchElementException;

import java.util.LinkedList;

public class TreeUsingLinkedList implements Tree{
    static class TreeNode {
        int value, index;
        TreeNode leftChild;
        TreeNode rightChild;
        TreeNode parent;

        public TreeNode(int value, int index, TreeNode parent) {
            this.value = value;
            this.index = index;
            leftChild = null;
            rightChild = null;
            this.parent = parent;
        }

    }

    private TreeNode root;
    private LinkedList<TreeNode> list;
    private int size;
    public TreeUsingLinkedList(int size) {
        this.size = size;
        list = new LinkedList<>();
        root = new TreeNode(1, 0, null);
        list.add(root);
    }

    public int getCurrentSize(){
        return list.size();
    }

    public TreeNode getNode(int idx){
        if(idx < size) {
            return list.get(idx);
        }else {
            throw new CustomNoSuchElementException();
        }
    }

    public void addNodes(int parentIdx, int leftChildValue, int rightChildValue){
        TreeNode parent = list.get(parentIdx);
        //부모노드, 현재 리스트크기(자식을 저장할 인덱스로 사용), 왼쪽 오른쪽 자식 값을 인자로
        addChildren(parent, list.size(), leftChildValue, rightChildValue);
    }
    public void addChildren(TreeNode parent, int index, int leftChildValue, int rightChildValue){
        if(leftChildValue != -1) {
            TreeNode left = new TreeNode(leftChildValue, index++, parent);
            parent.leftChild = left;
            list.add(left);
        }
        if(rightChildValue != -1) {
            TreeNode right = new TreeNode(rightChildValue, index, parent);
            parent.rightChild = right;
            list.add(right);
        }
    }
    public void deleteNode(int idx) {
        delete(idx, true);
    }
    public void delete(int idx, boolean first){
        idx--;
        if(idx < size && idx >= 0) {
            TreeNode node = list.get(idx);
            if(node != null) {
                //리스트의 get으로 받은 node는 null 처리를 바로 해줄 수 없어서(node = null 해도 부모의 leftChild나 rightChild가 바뀌지 않음)
                //부모로 거슬러 올라간 후 null 처리
                if(node.parent.leftChild == node){
                    node.parent.leftChild = null;
                }else{
                    node.parent.rightChild = null;
                }
                size--;

                if (node.leftChild != null) {
                    delete(node.leftChild.index+1, false);
                }
                if (node.rightChild != null) {
                    delete(node.rightChild.index+1, false);
                }
            }
        }else if(first){
            //처음 호출할 때의 인자가 범위 밖이면 예외를 뱉고 재귀 돌다가 범위 밖인거면 그냥 종료
            throw new CustomNoSuchElementException();
        }
    }
  
    public void preOrder(TreeNode node){
        if(node != null) {
            System.out.print(node.value+" ");
            preOrder(node.leftChild);
            preOrder(node.rightChild);
        }
    }

    @Override
    public void preOrder() {
        //루트노드부터 순회
        preOrder(root);
    }

    public void inOrder(TreeNode node){
        if(node != null) {
            inOrder(node.leftChild);
            System.out.print(node.value+" ");
            inOrder(node.rightChild);
        }
    }

    @Override
    public void inOrder() {
        inOrder(root);
    }

    public void postOrder(TreeNode node){
        if(node != null) {
            postOrder(node.leftChild);
            postOrder(node.rightChild);
            System.out.print(node.value+" ");
        }
    }
    @Override
    public void postOrder() {
        postOrder(root);
    }
}
