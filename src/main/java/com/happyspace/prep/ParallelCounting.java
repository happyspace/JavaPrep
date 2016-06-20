package com.happyspace.prep;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Implement a system that counts in parallel but organizes results in a
 * deterministic order. For numbers this would be in natural order.
 * Natural order should not be achieved through sorting.
 */
public class ParallelCounting {

    /**
     * An arbitrary limit. Max threads will vary be OSs and processors.
     * The number of running threads will be determined by the number
     * of cores the OS sees (hyperthreading etc.)
     */
    private static final int MAX_THREADS = 1000;

    /**
     * Program receives a number indicating a number of threads.
     *
     * @param args Number of threads to create and the number
     *             of numbers to count.
     */
    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("Please specify the number of threads.");
            System.exit(1);
        }
        int numberOfThreads = 0;
        try {
            numberOfThreads = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
            System.exit(1);
        }
        if (numberOfThreads <= 0 || numberOfThreads > MAX_THREADS){
            System.out.format("Please enter a number between 1 - %d. /n", MAX_THREADS);
        }
        AtomicBoolean shutdown = new AtomicBoolean(false);
        // create a channel to report results.
        ConcurrentLinkedDeque<CountResult> resultsQueue = new ConcurrentLinkedDeque<>();
        // list of tasks
        List<CountingTask> tasks = new ArrayList<>(numberOfThreads);
        // list of threads
        List<Thread> threads = new ArrayList<>(numberOfThreads);
        // two barriers to avoid race condition between
        // generation and reporting.
        CyclicBarrier generateBarrier = new CyclicBarrier(numberOfThreads);
        CyclicBarrier reportingBarrier = new CyclicBarrier(numberOfThreads,
                new CyclicBarrierRunnable(resultsQueue, numberOfThreads));
        // create threads and start them.
        for (int i = 0; i < numberOfThreads; i++) {
            CountingTask countingTask = new CountingTask(generateBarrier,
                    reportingBarrier,
                    resultsQueue,
                    i + 1,
                    numberOfThreads,
                    shutdown);
            tasks.add(countingTask);
            Thread thread = new Thread(countingTask);
            thread.setName("th " + String.valueOf(i + 1));
            threads.add(thread);
            thread.start();
        }
    }

    /**
     * A runnable that will run after each generation of the reporting CyclicBarrier.
     * Reports results from a completed generation.
     * Note that the next generation will be underway as we are reporting.
     */
    private static class CyclicBarrierRunnable implements Runnable {

        private final ConcurrentLinkedDeque<CountResult> resultsQueue;
        private final int threadNum;

        public CyclicBarrierRunnable(ConcurrentLinkedDeque<CountResult> resultsQueue,
                                     int threadNum) {
            this.resultsQueue = resultsQueue;
            this.threadNum = threadNum;
        }
        // just report the results from the last generation.
        public void run() {
            // tread ids are one based.
            CountResult[] order = new CountResult[threadNum + 1];
            // reorder results.
            for (int i = 0; i < threadNum; i++) {
                CountResult result = resultsQueue.removeLast();
                order[result.ordinal] = result;
            }
            for (CountResult countResult : order) {
                if(countResult != null) {
                    System.out.println(countResult.threadName + ": " + String.valueOf(countResult.count));
                }
            }
        }
    }

    /**
     * A runnable that will count by a number. If the system
     * was started with 5. Each task will count by 5.
     */
    private static class CountingTask implements Runnable {

        private final AtomicBoolean shutdown;
        private final AtomicInteger count = new AtomicInteger(0);
        private final int countBy;
        private final int ordinal;
        private final CyclicBarrier generateBarrier;
        private final CyclicBarrier reportBarrier;
        private final ConcurrentLinkedDeque<CountResult> results;
        private boolean firstRun;

        /**
         * @param generateBarrier for each generation of numbers.
         * @param initialCount number to start counting.
         * @param shutdown should threads terminate.
         * @param countBy the number to count by.
         */
        public CountingTask(CyclicBarrier generateBarrier,
                            CyclicBarrier reportBarrier,
                            ConcurrentLinkedDeque<CountResult> results,
                            int initialCount,
                            int countBy,
                            AtomicBoolean shutdown){
            this.generateBarrier = generateBarrier;
            this.reportBarrier = reportBarrier;
            this.results = results;
            this.shutdown = shutdown;
            this.count.getAndSet(initialCount);
            this.ordinal = initialCount;
            this.countBy = countBy;
            this.firstRun = true;
        }

        /**
         * Run count and reporting of count results.
         */
        public void run() {
            for (; ; ) {
                if (!firstRun) {
                    count.addAndGet(countBy);
                } else {
                    firstRun = false;
                }

                try {
                    // wait for tasks to finish count in this generation.
                    generateBarrier.await();
                    // report result
                    results.add(new CountResult(ordinal, count.get(), Thread.currentThread().getName()));
                    // wait for all tasks to report in this generation.
                    reportBarrier.await();
                    Thread.sleep(50);
                // not really developed but explicitly indicate when the program should shut down.

                } catch (InterruptedException e) {
                    if(this.shutdown.get()){
                        break;
                    }
                }
                // something definitely when wrong
                // likely need to restart computation.
                // see {@link java.util.concurrent.CyclicBarrier#restart}
                catch (BrokenBarrierException e) {
                    break;
                }
            }
        }
    }

    /**
     * A wrapper class for computation results.
     */
    private static class CountResult {
        private final int ordinal;
        private final int count;
        private final String threadName;

        /**
         * @param ordinal the position within each generation for this result.
         * @param count the result.
         * @param threadName the name of the thread.
         */
        private CountResult(int ordinal, int count, String threadName) {
            this.ordinal = ordinal;
            this.count = count;
            this.threadName = threadName;
        }
    }
}
