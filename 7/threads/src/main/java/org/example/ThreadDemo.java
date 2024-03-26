package org.example;

public class ThreadDemo extends Thread{
    private int number;

    public ThreadDemo(int number)
    {
        this.number = number;
    }
    public void run() {
        int counter = 0;

        do {
            System.out.println(this.getName() + " prints " + counter);
            counter++;
        } while (counter!=number);
    }
}
