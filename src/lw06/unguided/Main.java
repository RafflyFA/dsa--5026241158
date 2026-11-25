package lw06.unguided;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner read = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));
        BinarySearchTree BST = new BinarySearchTree();
        String[] numbers = read.nextLine().trim().split("\\s+");
        for(int i = 0; i < numbers.length; i++){
            BST.insert(Integer.parseInt(numbers[i]));
        }
        int target = read.nextInt();
        System.out.print("Preorder : ");
        BST.preOrder();
        System.out.print("Inorder : ");
        BST.inOrder();
        System.out.print("Postorder : ");
        BST.postOrder();
        System.out.println("Search " + target + " : " + BST.search(target));
        System.out.println("Nodes : " + BST.countNodes());
        System.out.println("Leaves : " + BST.countLeaves());
        read.close();
    }
}
