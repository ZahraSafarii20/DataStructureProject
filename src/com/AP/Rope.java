package com.AP;

import java.util.ArrayList;

public class Rope {

    Node root;
    String data;
    ArrayList<Rope> ropes = new ArrayList<>();

    public Rope(){
        root = new Node();
    }

    public void Status(){
        for (int i = 0; i < ropes.size(); i++){
            int j = i+1;
            System.out.println(j+". "+ropes.get(i).data);
        }
    }

    public void Insert(int s1, int index, int s2){

    }

    public void New (String str){
        Node newNode = new Node(str);
        Node tmpRoot = new Node();

        tmpRoot.right = newNode;
        tmpRoot.left = root;

        if(tmpRoot.left.right == null){
            tmpRoot.weight = tmpRoot.left.weight;
        }else{
            tmpRoot.weight = tmpRoot.left.weight;
        }
        root = tmpRoot;
    }

    public void Index (int str, int index) {

        Node tmpNode = root;
        if (index > tmpNode.weight){
            index -= tmpNode.weight;
            System.out.println(tmpNode.right.data.charAt(index));
        }

        while (index < tmpNode.weight){
            tmpNode = tmpNode.left;
        }

        index -= tmpNode.weight;
        System.out.println(tmpNode.right.data.charAt(index));
    }

    public void Concat(int s1, int s2){
        s1--;
        Rope firstRope = ropes.get(s1);
        s2--;
        Rope secondRope = ropes.get(s2);
        Node newNode = new Node();
        newNode.right = secondRope.root;
        newNode.left = firstRope.root;
        newNode.weight = firstRope.root.data.length();
        firstRope.root.parent = newNode;
        secondRope.root.parent = newNode;
        Rope newRope = new Rope();
        newRope.root = newNode;
        ropes.add(s1, newRope);
        ropes.remove(s2);
    }



    public void Split(int s, int index){
        Rope rope = ropes.get(s);
        String str = "";
        Boolean found = false;
        Node tmp = rope.root;
        if (index > tmp.weight){
            found = true;
            index -= tmp.weight;

        }
    }

    public void Report(int i, int j){
        String str = this.RopeToString();
        for (int m = i + 1; m < j; m++){
            System.out.print(str.charAt(m));
        }
    }

    public String RopeToString(){
        StringBuilder sb = new StringBuilder();
        sb.append(root.nodeToString());
        String result = sb.toString();
        return result;
    }


}