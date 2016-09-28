import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private static class Job implements Comparable<Job> {
        private final Integer duration;
        private final Long startTime;

        public Job(Integer duration, Long startTime) {
            this.duration = duration;
            this.startTime = startTime;
        }

        public int getDuration() {
            return duration;
        }

        public Long getEndTime(){
            return duration + startTime;
        }

        @Override
        public int compareTo(Job o) {
            return this.getEndTime().compareTo(o.getEndTime());
        }
    }

    private static class Thread implements Comparable<Thread> {
        private final Integer index;
        private Job currentJob;

        public Thread(Integer index) {
            this.index = index;
            this.currentJob = new Job(0, 0L);
        }

        @Override
        public int compareTo(Thread o) {

            int job = this.getCurrentJob().compareTo(o.getCurrentJob());

            // now break ties with index
            if (job == 0) {
                job = this.getIndex().compareTo(o.getIndex());
            }
            return job;
        }

        public Job getCurrentJob() {
            return currentJob;
        }

        public void setCurrentJob(Job currentJob) {
            this.currentJob = currentJob;
        }

        public Integer getIndex() {
            return index;
        }
    }

    private void assignJobs() {
        // TODO: replace this code with a faster algorithm.
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];

        long[] nextFreeTime = new long[numWorkers];

        PriorityQueue<Thread> threads = new PriorityQueue<>();

        // create threads 
        for (int i = 0; i < numWorkers; i++) {
            threads.add(new Thread(i));
        }

        for (int i = 0; i < jobs.length; i++) {
            // get job
            int duration = jobs[i];
            // find next worker
            Thread bWorker = threads.poll();
            // create a new job with duration and start time.
            bWorker.setCurrentJob(new Job(duration, nextFreeTime[bWorker.getIndex()]));
            assignedWorker[i] = bWorker.getIndex();
            startTime[i] = nextFreeTime[bWorker.getIndex()];

            nextFreeTime[bWorker.getIndex()] += duration;
            threads.add(bWorker);


/*
            int bestWorker = 0;
            for (int j = 0; j < numWorkers; ++j) {
                if (nextFreeTime[j] < nextFreeTime[bestWorker])
                    bestWorker = j;
            }
            assignedWorker[i] = bestWorker;
            startTime[i] = nextFreeTime[bestWorker];
            nextFreeTime[bestWorker] += duration;
            */
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
