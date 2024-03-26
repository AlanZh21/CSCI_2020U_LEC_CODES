package org.example;

public class ThreadDemoMain {
    public static void main(String[] args)
    {
        System.out.println("Starting Thread-0.");

        Thread thread0 = new ThreadDemo(20);
        thread0.start();

        try
        {
            thread0.join(9);
        }
        catch(InterruptedException e)
        {
            System.out.println("Thread 1 interrupted.");
        }

        System.out.println("Starting Thread-1.");

        Thread thread1 = new ThreadDemo(15);
        thread1.start();
    }
}
