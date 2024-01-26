# Programming Assignment 1: Parallel and Distributed Processing 

## To compile and run this program, run the following commands in the terminal:
## javac AssignmentOne.java
## java AssignmentOne.java


##### My approach to this problem was to apply the Sieve of Eratosthenes algorithm, which allows someone to quickly find all of the prime numbers up to a certain limit. This algorithm essentially works by labelling multiples of prime numbers as not prime. Then, I made sure for there to be load balancing implemented in the program to distribute the work in that algorithm evenly among the threads. Each thread is responsible for its own portion of the range. Then, iterate through the array which indicates prime and not prime numbers at the end to find the values that need to be in the output of this program.

##### My reasoning about why my approach is efficient is that each thread runs the algorithm on its own particular range, so the load is evenly balanced across the 8 threads. My approach is correct in that it uses the Sieve of Eratosthenes algorithm to correctly identify all primes in the range, and consequently is able to find the sum of all the primes within the range, the max 10 primes in the range, and also the number of primes in the range.

##### From experimental evaluation of the approach, I have concluded that on average the runtime of my solution is approximately 550ms. Additionally, by changing inital value of the numOfThreads variable, the solution that results is still correct. By changing the inital value of the limit variable, the results are also still verified to be correct.


