package com.AP;

public class Main {

    public static void main(String[] args) {
        Rope r = new Rope();
        r.New("Hello World");
        r.New("bye");
        r.Status();
//        r.Report(1,0,8);
//        r.Index(1,4);
//        r.Insert(1, 3,2);
//        r.Status();
//        r.Concat(1,2);
//        r.Status();
//        r.Report(1,0,5);
//        r.Split(1,5);
//        r.Status();
        r.Delete(1,2,5);
        r.Status();
    }
}
