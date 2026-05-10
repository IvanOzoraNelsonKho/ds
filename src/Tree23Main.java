import com.datastruct.nmTree;

public class Tree23Main {
    public static void main(String[] args) {
        nmTree<Integer, String> theTree = new nmTree<>();

        int[] keys = {42, 22, 58, 12, 51, 15, 30, 71, 8, 23, 77, 2, 80, 3, 10, 17, 5, 62, 9, 55, 88};
        
        for (int k : keys) {
            theTree.insert(k, "ini angka " + k); 
        }

        System.out.println("\nHasil 2-3 Tree:");
        theTree.displayTree();
    }
}