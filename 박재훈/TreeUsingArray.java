import exeption.CustomNoSuchElementException;

import java.util.Arrays;

public class TreeUsingArray implements Tree{
    private int[] arr;
    private static final int INIT_CAPACITY = 5;
    private int capacity;
    public TreeUsingArray() {
        //배열의 크기는 기본값으로 지정해놓고 부족하면 늘릴 것임
        //인덱스는 1부터 사용(부모 자식 인덱스 계산 편리)
        arr = new int[INIT_CAPACITY];
        capacity = INIT_CAPACITY;
        Arrays.fill(arr, -1);
        //루트노드는 1로 고정
        arr[1] = 1;
    }

    public int getNode(int idx){
        if(idx < capacity) {
            return arr[idx];
        }else{
            throw new CustomNoSuchElementException();
        }
    }

    public void addNodes(int parentIdx, int leftChildValue, int rightChildValue){
        //추가하려는 자식 노드가 배열 용량을 벗어나면 용량을 두배로 늘린후 추가
        if(2*parentIdx+1 >= capacity) {
            int[] temp = new int[capacity];
            System.arraycopy(arr, 0, temp, 0, capacity);
            int oldCapacity = capacity;
            capacity *= 2;
            arr = new int[capacity];
            Arrays.fill(arr,-1);
            System.arraycopy(temp, 0, arr, 0, oldCapacity);
        }
        arr[2 * parentIdx] = leftChildValue;
        arr[2 * parentIdx + 1] = rightChildValue;
    }

    public void deleteNode(int idx){
        delete(idx, true);
    }
    public void delete(int idx, boolean first){
        //범위 내의 인덱스라면 노드와 그 자식 노드 삭제
        if(idx < capacity) {
            arr[idx] = -1;
            delete(idx * 2, false);
            delete(idx*2+1, false);
            
            //처음 호출할 때의 인자가 범위 밖이면 예외를 뱉고 재귀 돌다가 범위 밖인거면 그냥 종료
        }else if(first){
            throw new CustomNoSuchElementException();
        }
    }

    public void preOrder(int idx){
        //빈 노드가 아닐 경우에 preOrder는 먼저 출력 후 자식 노드 순회
        if(arr[idx]!=-1) {
            System.out.print(arr[idx]+" ");
            preOrder(idx * 2);
            preOrder(idx * 2 + 1);
        }
    }

    @Override
    public void preOrder() {
        //루트노드부터 순회
        preOrder(1);
    }

    public void inOrder(int idx){
        if(arr[idx]!=-1) {
            inOrder(idx * 2);
            System.out.print(arr[idx]+" ");
            inOrder(idx * 2 + 1);
        }
    }

    @Override
    public void inOrder() {
        inOrder(1);
    }

    public void postOrder(int idx){
        if(arr[idx]!=-1) {
            postOrder(idx * 2);
            postOrder(idx * 2 + 1);
            System.out.print(arr[idx]+" ");
        }
    }
    @Override
    public void postOrder() {
        postOrder(1);
    }
}
