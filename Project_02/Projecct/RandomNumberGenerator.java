import java.util.*;
import java.io.*;

/**
 * Class RandomNumberGenerator constructs a random number generator with the current time as the seed. A RandomNumberGenerator object can be used to get
 * a variety of random integers.
 * 
 * @version (10/15/2016)
 */

public class RandomNumberGenerator
{
    private Random rnd;
    
    /**
     * Constructs a new random number generator seeded with the current time in nanoseconds
     */
    public RandomNumberGenerator()
    {   
        long s = (int)System.nanoTime();
        rnd = new Random(s);
    }
    
    /**
     * Computes the next random number in the inputted range using the constructed random number generator
     * @param range upper bound of desired range for outputted int (lower bound is 0)
     * @return random int in range (0, inputed number) inclusive
     */
    public int returnRandom(int range)
    {
        return rnd.nextInt((range+1)); //random number from 0 to range inclusive
    }
    
    /**
     * Computes the next random number within a normal distribution calculated with the inputted mean and standard deviation, using the constructed random
     * number generator.
     * @param mean mean of values, used to generate a random number within a normal distribution
     * @param stdDev standard deviation of values, used to generate a random number within a normal distribution
     * @return random int weighted by normal distribution calculated using mean and standard deviation
     */
    public int returnGaussian(double mean, double stdDev)
    {
        return (int)(mean+rnd.nextGaussian() * stdDev);
    }
} 

