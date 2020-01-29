import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */


        Thread t1 = new Thread(new Worker(1, 20));
        Thread t2 = new Thread(new Worker(21, 40));
        Thread t3 = new Thread(new Worker(41, 60));
        Thread t4 = new Thread(new Worker(61, 80));
        Thread t5 = new Thread(new Worker(81, 100));

        t1.start();
        t2.start();
        t3.start();

        t4.start();

        t5.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();






    }
}

class Worker extends Thread {

    private int start, end;

    public Worker(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public void run(){
        for(int i = start ;i<end; i++){
            System.out.println(i);

        }
    }
}