package edu.bsu.cs;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class EmployeeBST {
    public Node constructBalancedBST(List<Employee> employeeList){
        int lowIndex = 0;
        int highIndex = employeeList.size()-1;
        return bstHelper(employeeList, lowIndex, highIndex);
    }

    private Node bstHelper(List<Employee> employeeList, int lowIndex, int highIndex){
        if (lowIndex > highIndex){
            return null;
        }
        if (lowIndex == highIndex){
            return new Node(null,null,employeeList.get(lowIndex));
        }
        int mid = lowIndex+(highIndex-lowIndex)/2;
        Node root = new Node(null,null,employeeList.get(mid));
        root.setLeft(bstHelper(employeeList,lowIndex,mid-1));
        root.setRight(bstHelper(employeeList,mid+1,highIndex));

        return root;
    }

    public void levelOrderTraversal(Node root){
        if (root == null) {
            return;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()){
            int levelSize = queue.size();
            System.out.println("Level: " + level + " has " + levelSize + " employees");
            for (int i = 0; i<levelSize; i++){
                Node temp = queue.poll();
                System.out.println(temp.getData());
                if (temp.getLeft()!=null){
                    queue.add(temp.getLeft());
                }
                if (temp.getRight()!=null){
                    queue.add(temp.getRight());
                }
            }
            level++;
        }
    }

    public Node searchRetire(Node root){
        if (root == null){
            return null;
        }
        if (root.getData().getAge()>65){
            return root;
        }
        Node retireEmployeeLeft = searchRetire(root.getLeft());
        if (retireEmployeeLeft != null){
            return retireEmployeeLeft;
        }
        Node retireEmployeeRight = searchRetire(root.getRight());
        {return retireEmployeeRight;}
    }

    public Node removeRetire(Node root, Employee e){
        if (root == null) return null;

        if (e.getId() < root.getData().getId()){
            root.setLeft(removeRetire(root.getLeft(),e));
            return root;
        } else if (e.getId() > root.getData().getId()){
            root.setRight(removeRetire(root.getRight(),e));
            return root;
        } else {
            if (root.getLeft() == null) {
                return root.getRight();
            } else if (root.getRight() == null) {
                return root.getLeft();
            }
            root.setData(findMin(root.getRight()).getData());

            root.setRight(removeRetire(root.getRight(),root.getData()));
        }
        return root;
    }

    private Node findMin(Node node) {
        while (node.getLeft() != null){
            node = node.getLeft();
        }
        return node;
    }

    public boolean search(Node root, int id){
        if (root == null) return false;

        if (id == root.getData().getId()) return true;
        if (id < root.getData().getId()){
            return search(root.getLeft(),id);
        } else{
            return search(root.getRight(),id);
        }
    }

    public Node insertNewHire(Node root, Employee e){
        if (root == null) return new Node(null,null,e);

        if (e.getId() < root.getData().getId()){
            root.setLeft(insertNewHire(root.getLeft(),e));
        } else{
            root.setRight(insertNewHire(root.getRight(),e));
        }
        return root;
    }

    public void inorderTraversal(Node root){
        if (root != null){
            inorderTraversal(root.getLeft());
            System.out.println(root.getData());
            inorderTraversal(root.getRight());
        }
    }
}
