package com.AP;

import java.util.ArrayList;

public class Rope {

    Node root;
    int length;
    ArrayList<Rope> ropes = new ArrayList<>();
    public Rope(){
        root = new Node();
    }

    public Rope(String text) {
        // Creates a Rope with a single RopeNode containing `text'.
        this.root = new Node(text);
        this.length = text.length();
    }

    public void New (String text) {
        Rope newRope = new Rope();
        int string_length = text.length();
        this.root = new Node();
        for (int i = 0; i < string_length; i = i + text.length()){
            if(string_length - i < 12){
                newRope.rope_append_from_string(text.substring(i, string_length));
                newRope.length += string_length - i;
            }
            else this.rope_append_from_string(text.substring(i, i + 12));
            newRope.length += 12;
        }
        ropes.add(newRope);
    }

    public void rope_append_from_string(String new_string){
        // Helper method to add `new_string' to end of Rope
        root = root.string_append(new_string);
    }

    public void Status(){
        for (int i = 0; i < ropes.size(); i++){
            int j = i+1;
            System.out.println(j+". "+ropes.get(i).RopeToString());
        }
    }

    public void Insert(int s1, int index, int s2){
        Rope r1 = ropes.get(s1-1);
        Rope r2 = ropes.get(s2-1);
        r1.root.resetIterators();
        r1.root.insertOnNode(r2.root, index, 0, false);
        ropes.add(ropes.size(), r1);
    }

    public void Delete(int str, int i, int j){
        Rope r = ropes.get(str-1);
        New(this.substring(str-1, 0, i));
        New(this.substring(str-1 ,i ,j));
        New(this.substring(str-1 ,j ,r.RopeToString().length()));
        ropes.remove(str-1);
        Concat(ropes.size()-2,ropes.size());
        ropes.remove(ropes.size()-1);
    }

    public void Index (int s, int index) {
        Rope rope = ropes.get(s - 1);
        Node tmpNode = rope.root;
        if (index > tmpNode.weight){
            index -= tmpNode.weight;
            System.out.println(tmpNode.right.data.charAt(index));
        }

        while (index < tmpNode.weight){
            tmpNode = tmpNode.left;
        }

//        index -= tmpNode.weight;
//        System.out.println(tmpNode.right.data.charAt(index));
    }

    public void Concat(int s1, int s2){
        s1--;
        Rope firstRope = ropes.get(s1);
        s2--;
        Rope secondRope = ropes.get(s2);
        Node newNode = new Node();
        newNode.right = secondRope.root;
        newNode.left = firstRope.root;
        newNode.weight = firstRope.root.nodeToString().length();
        firstRope.root.parent = newNode;
        secondRope.root.parent = newNode;
        Rope newRope = new Rope();
        newRope.root = newNode;
        ropes.remove(s2);
        ropes.remove(s1);
        ropes.add(s1, newRope);
    }



    public void Split(int s, int index){
        Rope r = ropes.get(s-1);
        New(substring(s-1,0, index));
        New(substring(s-1, index, r.RopeToString().length()));
        ropes.remove(s-1);
    }

    public void Report(int rope, int i, int j) {
        Rope r = ropes.get(rope-1);
        String s = r.RopeToString();
        for (int m = i+1; m < j; m++){
            System.out.print(s.charAt(m));
        }
        System.out.println();
    }

    public Rope subRope(int start, int end){
        this.root.twin = root;
        this.root.subNode(start, end, 0);
        return this;
    }

    public String RopeToString(){
        StringBuilder sb = new StringBuilder();
        sb.append(root.nodeToString());
        String result = sb.toString();
        return result;
    }

    public int findHeightLeft(Node node) {
        int height = 0;
        while(node!=null) {
            height++;
            node = node.left;
        }
        return height;
    }

    public int findHeightRight(Node node) {
        int height = 0;
        while(node!=null) {
            height++;
            node = node.right;
        }
        return height;
    }

    public int countNodes(Node root) {
        if(root == null) return 0;

        int leftHeight = findHeightLeft(root);
        int rightHeight = findHeightRight(root);

        if(leftHeight == rightHeight) return (1<<leftHeight) - 1;

        int leftNodes = countNodes(root.left);
        int rightNodes = countNodes(root.right);

        return 1 + leftNodes + rightNodes;
    }

    public String substring(int s, int start, int end) {
        String str = "";
        boolean found = false;
        Rope r = ropes.get(s);
        Node tmp = r.root;
        if (end > tmp.weight) {
            found = true;
            end -= tmp.weight;
            if (start > tmp.weight) {
                start -= tmp.weight;
                str = tmp.right.data.substring(start, end);
                return str;
            } else
                str = tmp.right.data.substring(0, end);

        }
        if (!found) {
            while (end <= tmp.weight)
                tmp = tmp.left;
            end -= tmp.weight;
            if (start >= tmp.weight) {
                start -= tmp.weight;
                str = tmp.right.data.substring(start, end) + str;
                return str;
            }
            str = tmp.right.data.substring(0, end);
        }
        tmp = tmp.left;
        while (start < tmp.weight){
            str = tmp.right.data + str;
            tmp = tmp.left;
        }
        start -= tmp.weight;
        if (tmp.right != null){
            str = tmp.right.data.substring(start) + str;
        }
        return str;

    }

}