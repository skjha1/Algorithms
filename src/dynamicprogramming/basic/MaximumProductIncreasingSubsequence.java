package dynamicprogramming.basic;

import java.util.Arrays;

// https://www.geeksforgeeks.org/maximum-product-increasing-subsequence/

public class MaximumProductIncreasingSubsequence {
    
    /* This problem is similar to Maximum Sum 
     * Increasing Subsequence problem
     */
    
    // T(n): O(n^2), S(n): O(n)
    public int maxSumIS(int[] a) {
        int n = a.length;
        int[] mpis = new int[n];
        
        mpis[0] = a[0];
        for (int i = 1; i < n; i++) {
            mpis[i] = a[i];
            for (int j = 0; j < i; j++) {
                if (a[j] < a[i]) {
                    if (mpis[i] < mpis[j] * a[i]) {
                        mpis[i] = mpis[j] * a[i];
                    }
                }
            }
        }
        
        // get maximum from mpis[] array
        return Arrays.stream(mpis).max().getAsInt();
    }
    
    public static void main(String[] args) {
        int[] a = { 3, 100, 4, 5, 150, 6 };
        MaximumProductIncreasingSubsequence obj = new MaximumProductIncreasingSubsequence();
        System.out.println(obj.maxSumIS(a)); // 45000
    }
    
}
