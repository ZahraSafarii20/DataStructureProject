package com.AP;

public class Node {
    public String data = null;
    public Node left = null;
    public Node right = null;
    public Node parent = null;
    public Node twin;
    public int weight = 0;
    static int i_current_pos;
    static int i_last_pos;

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

    public void subNode(int start, int end, int from){
        if(this.left == null && this.right == null){
            i_last_pos = i_current_pos;
            i_current_pos = i_current_pos + this.weight;
            if(start >= i_last_pos && end < i_current_pos && this.data != null) {
                if(start >= i_last_pos && start < i_current_pos && this.data != null){

                }
            }
        } else if(this.left == null){
            right.subNode(start, end, i_current_pos);
        } else if(this.right == null){
            left.subNode(start, end, i_current_pos);
        } else 	{
            this.left.subNode(start, end, i_current_pos);
            this.right.subNode(start, end, i_current_pos);
        }
    }

    public Node string_append(String new_string){
        // Creates a new Rope from `new_string' and appends.
        Node new_node = new Node(new_string);
        Node root_node = new Node();
        root_node.left = this;
        root_node.right = new_node;
        this.parent = root_node;
        new_node.parent = root_node;
        return root_node;
    }
    public void insertOnNode(Node text, int index, int from, boolean break_now){
        if(!break_now){
            if(this.left == null && this.right == null){
                i_last_pos = i_current_pos;
                i_current_pos = i_current_pos + this.weight;
                if(index >= i_last_pos && index < i_current_pos && this.data != null) {
                    Node new_right = new Node(this.data.substring(index-i_last_pos));
                    Node new_left_left  = new Node(this.data.substring(0,index-i_last_pos));
                    Node new_left = new Node();
                    this.right = new_right;
                    this.left = new_left;
                    new_left.left = new_left_left;
                    new_left.right = text;
                    break_now = true;
                }
            } else if(this.left == null){
                right.insertOnNode(text, index, i_current_pos, false);
            } else if(this.right == null){
                left.insertOnNode(text, index, i_current_pos, false);
            } else 	{
                this.left.insertOnNode(text, index, i_current_pos, false);
                this.right.insertOnNode(text, index, i_current_pos, false);
            }
        }
    }
    public void resetIterators(){
        i_current_pos = 0;
        i_last_pos = 0;
    }

}