package com.AP;

public class Node {
    public String data = null;
    public Node left = null;
    public Node right = null;
    public Node parent = null;
    public int weight = 0;

    public Node(String data){
        this.data = data;
        this.weight = data.length();
    }

    public Node(){

    }

    public static void printNode(Node node){
        if(node != null){
            printNode(node.left);
            if(node.data != null){
                System.out.println(node.data);
            }
            printNode(node.right);
        }
    }

    public String nodeToString(){
        if(this.left == null && this.right == null){
            if(this.data!=null)
                return this.data;
            else
                return "";
        } else if(this.left == null){
            return this.right.nodeToString();
        } else if(this.right == null){
            return left.nodeToString();
        } else 	{
            return this.left.nodeToString() + this.right.nodeToString();
        }
    }
}