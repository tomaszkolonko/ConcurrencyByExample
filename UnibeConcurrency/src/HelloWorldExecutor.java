import java.util.concurrent.*;


public class HelloWorldExecutor {

	private static final int NUM_THREADS = 3;
	private static final int NUM_RUNS = 20;

	// implement the Runnable task
	static class MyRun implements Runnable {
		public void run() {
			String threadName = Thread.currentThread().getName();
			System.out.println("Hello World From Thread: " + threadName);
		}
	}

	public static void main(String[] args) throws InterruptedException
	{
		MyRun run = new MyRun();

		ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
		for(int i = 0; i < NUM_RUNS; i++) {
			executor.submit(run);
		}
		
		// create executor service with NUM_THREADS fixed thread pool

		// execute task NUM_RUNS times	
		

	 	Thread.sleep(1000);
	 	
	 	executor.shutdown();

		// don't forget to shut the executor down
	}
}
