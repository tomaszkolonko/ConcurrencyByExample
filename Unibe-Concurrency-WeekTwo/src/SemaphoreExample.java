import java.util.concurrent.*;

public class SemaphoreExample {
	private static final int NUM_THREADS = 8;
	private static final int NUM_RUNS = 20;
	
	private static Semaphore sema = new Semaphore(1);
	private static int sharedNumber = 42;
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread[] threads = new Thread[NUM_THREADS];
		for(int i = 0; i < NUM_THREADS; i++) {
			threads[i] = new MyThread();
			threads[i].start();
	
		}
		
		//Thread.sleep(1000);
		
		for(int i = 0; i < NUM_THREADS; i++) {
			threads[i].join();
		}
		
		System.out.println("ended the simulation with shared number being: " + sharedNumber);		
		
	}
	
	static class MyThread extends Thread {
		public void run() {
			int i = 0;
			while(i < NUM_RUNS) {
				try {
					sema.acquire();
					System.out.println("acquiring semaphore for thread: " + Thread.currentThread().getName());
				} catch (InterruptedException e) {
					System.err.println("sema did not acquire lock");
				}
				i++;
				sharedNumber++;
				sema.release();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println("jaja");
				}
			}
		}
	}

}
