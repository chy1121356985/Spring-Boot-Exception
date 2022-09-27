package auto;

public class ThreadDemo implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i%2==0){
                System.out.println(i+"是偶数");
            }
        }
    }

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        ThreadDemo threadDemo1 = new ThreadDemo();
        Thread thread = new Thread(threadDemo);
        thread.start();
        thread.start();

    }
}
