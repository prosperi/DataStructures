package me.prosperri.datastructures.lab_03_01;

import java.util.Random;

/**
 * @author Zura Mestiashvili 
 * @version 1.0.0
 */

public class Wheel{
    long seed;
    int bound;
    Random rnd;
    
    public Wheel(long seed){
        this.seed = seed;
        this.bound = 100;
        this.rnd = new Random(seed);
    }
    
    public int spin(){
        // For 1-N(exclusive) range we should substract bound 1 and than add 1 to generated random number
        return this.rnd.nextInt(bound-1) + 1;
    }
    
}
