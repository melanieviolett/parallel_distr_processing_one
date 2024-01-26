import java.util.Arrays;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.File;

public class AssignmentOne implements Runnable {
    private boolean[] isPrimeArray;
    private int begin;
    private int end;

    public AssignmentOne(boolean[] isPrimeArray, int begin, int end) {
        this.isPrimeArray = isPrimeArray;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = 2; (i * i) <= end; i++) {
            if (isPrimeArray[i]) {
                for (int j = Math.max(((begin / i) * i), (i * i)); j <= end; j += i) {
                    isPrimeArray[j] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        final long startTime = System.currentTimeMillis();
        int limit = (int)Math.pow(10,8);
        boolean[] isPrimeArray = new boolean[limit + 1];

        for (int i = 0; i <= limit; i++) {
            isPrimeArray[i] = true;
        }

        int numOfThreads = 8; 
        Thread[] threads = new Thread[numOfThreads];

        for (int i = 0; i < numOfThreads; i++) {
            int begin = (i * limit) / numOfThreads + 2;
            int end = ((i + 1) * limit) / numOfThreads;
            AssignmentOne objAssignmentOne = new AssignmentOne(isPrimeArray, begin, end);
            threads[i] = new Thread(objAssignmentOne);
            threads[i].start();
        }

        
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                System.out.println("An Interrupted Exception has occurred.");
            }
        }

        long total = 0;
        long numOfPrimes = 0;
        int index = 0;
        long[] topTenPrimes = new long[10];

        for (int i = limit; i >= 2; i--) {
            if (isPrimeArray[i]) {
                if (index <= 9)
                {
                    topTenPrimes[index] = i;
                    index++;
                }
                total = total + i;
                numOfPrimes = numOfPrimes + 1;
            }
        }

        Arrays.sort(topTenPrimes);
        final long endTime = System.currentTimeMillis();

        File fileName = new File("primes.txt");

        try {
            PrintWriter pw = new PrintWriter(fileName);

            pw.print(endTime - startTime + "ms ");
            pw.print(numOfPrimes + " ");
            pw.println(total);

            for (int i = 0; i < topTenPrimes.length; i++) {
                pw.print(topTenPrimes[i] + " ");
            }
            pw.println();
            pw.close();
        }
        catch (FileNotFoundException ex) {
            System.out.println("File not found exception has occurred.");
        }

    }
}
