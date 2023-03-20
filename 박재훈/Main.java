import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("**********배열로 구현한 트리*********");
        System.out.println("트리의 노드 개수를 입력하세요>>");
        int size = sc.nextInt();
        TreeUsingArray tree = new TreeUsingArray();

        System.out.println("각 노드의 왼쪽 자식, 오른쪽 자식을 공백으로 구분하여 입력하세요>>");
        System.out.println("참고) 루트 노드는 항상 1입니다. 자식이 없으면 -1 입력해주세요.");
        int cnt = 0, i = 1;
        while(cnt < size){
            if(tree.getNode(i) != -1) {
                System.out.print((++cnt)+") " +tree.getNode(i) + "의 자식노드 : ");
                int left = sc.nextInt();
                int right = sc.nextInt();
                tree.addNodes(i, left, right);
            }
            i++;
        }

        System.out.println("PreOrder 순회 결과>>");
        tree.preOrder();

        System.out.println("\nInOrder 순회 결과>>");
        tree.inOrder();

        System.out.println("\nPostOrder 순회 결과>>");
        tree.postOrder();


        System.out.println("\n**********링크드리스트로 구현한 트리*********");
        System.out.println("트리의 노드 개수를 입력하세요>>");
        size = sc.nextInt();
        TreeUsingLinkedList tree2 = new TreeUsingLinkedList(size);

        System.out.println("각 노드의 왼쪽 자식, 오른쪽 자식을 공백으로 구분하여 입력하세요>>");
        System.out.println("참고) 루트 노드는 항상 1입니다. 자식이 없으면 -1 입력해주세요.");

        for (int j = 0; j < size; j++) {
            TreeUsingLinkedList.TreeNode node = tree2.getNode(j);
            System.out.print((j+1)+") " +node.value + "의 자식노드 : ");
            int left = sc.nextInt();
            int right = sc.nextInt();

            tree2.addNodes(j, left, right);
        }

        System.out.println("PreOrder 순회 결과>>");
        tree2.preOrder();

        System.out.println("\nInOrder 순회 결과>>");
        tree2.inOrder();

        System.out.println("\nPostOrder 순회 결과>>");
        tree2.postOrder();


    }
}
