package edu.bsu.cs;

public class Node {
    private Node left;
    private Node right;
    private Employee data;

    public Node getLeft(){return this.left;}
    public void setLeft(Node left) {this.left = left;}
    public Node getRight() {return right;}
    public void setRight(Node right) {this.right = right;}
    public Employee getData() {return this.data;}
    public void setData(Employee data) {this.data = data;}

    public Node(Node left, Node right, Employee data){
        this.left = left;
        this.right = right;
        this.data = data;
    }

    @Override
    public String toString(){
        return """
                
                """;
    }
}
