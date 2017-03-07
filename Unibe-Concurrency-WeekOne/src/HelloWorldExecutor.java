import java.util.concurrent.*;


public class HelloWorldExecutor {

	private static final int NUM_THREADS = 8;
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
		// Create Runnable task
		MyRun runnableTask = new MyRun();

		// ******************* FIXED THREAD POOL ************************** //
		// create executor service with NUM_THREADS fixed thread pool
		ExecutorService executorFixed = Executors.newFixedThreadPool(NUM_THREADS);
		
		// execute task NUM_RUNS times	
		for(int i = 0; i < NUM_RUNS; i++) {
			executorFixed.submit(runnableTask);
		}

		System.out.println("\n");
		
	 	Thread.sleep(1000);
	 	
	 	// don't forget to shut the executor down
	 	executorFixed.shutdown();
	 	
	 	
	 	// ******************* SINGLE THREAD POOL ************************** //
	 	ExecutorService executorSingle = Executors.newSingleThreadExecutor();
	 	for(int i = 0; i < NUM_RUNS; i++) {
	 		executorSingle.submit(runnableTask);
	 	}
	 	
	 	Thread.sleep(1000);
	 	
	 	executorSingle.shutdown(); 	
	}
}
