
public class HelloWorldThreads {

	private static final int NUM_THREADS = 8;
	private static final int NUM_RUNNABLES = 2;

	static class PrinterThread extends Thread {
		public void run() {
			System.out.println("Hello World From Thread: " + getName());
		}
	}

	public static void main(String[] args) throws InterruptedException
	{
		Thread threads[] = new Thread[NUM_THREADS];
	
		Runnable task = () -> {
			String threadName = Thread.currentThread().getName();
			System.out.println("Hello from runnable with thread: " + threadName);
			// important to catch all exceptions !!!
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		task.run();

		for (int i = 0; i < NUM_THREADS; i++){
			threads[i] = new PrinterThread();
			threads[i].start();			
		}
		
		// TODO: HOW CAN A THREAD BE JOINED WHEN NO REFERENCE IS AVAILABE?
		for (int i = 0; i < NUM_RUNNABLES; i++) {
			(new Thread(task)).start();
		}

	 	Thread.sleep(1000);

		for (int i = 0; i < NUM_THREADS; i++){
			threads[i].join();
		}
		
		System.out.println("Finished Simulation");
		
	}
}
