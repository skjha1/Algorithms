package dynamicprogramming.basic;

import java.util.Arrays;

// https://www.geeksforgeeks.org/longest-increasing-subsequence-dp-3/

public class LongestIncreasingSubsequence {

    class SimpleRecursiveSolution {
        // T(n): Exp
        public int lis(int[] a) {
            int maxValue = Arrays.stream(a).max().getAsInt() + 1;
            return lisUtil(a, a.length, maxValue);
        }

        private int lisUtil(int[] a, int n, int maxValue) {
            if (n == 0)
                return 0;
            
            // if last element can be included, then
            // solution will be max of solutions
            // including or excluding last element
            // else it will be solution excluding
            // last element
            if (a[n-1] < maxValue)
                return Math.max(1 + lisUtil(a, n-1, a[n-1]), 
                            lisUtil(a, n-1, maxValue));
            else
                return lisUtil(a, n-1, maxValue);
        }
    }
    
    class DPSolution {
        // top-down memoization
        // T(n): O(n*maxValue), S(n): O(n*maxValue)
        public int lisMemoized(int[] a) {
            int n = a.length;
            int maxValue = Arrays.stream(a).max().getAsInt() + 1;
            int[][] res = new int[n+1][maxValue+1];
            for (int i = 0; i <= n; i++)
                Arrays.fill(res[i], -1);
            
            return lisMemoizedUtil(a, n, maxValue, res);
        }

        private int lisMemoizedUtil(int[] a, int n, int maxValue, int[][] res) {
            if (n == 0)
                return 0;
            
            if (res[n][maxValue] != -1)
                return res[n][maxValue];
            
            if (a[n-1] < maxValue)
                res[n][maxValue] = Math.max(1 + lisMemoizedUtil(a, n-1, a[n-1], res), 
                        lisMemoizedUtil(a, n-1, maxValue, res));
            else
                res[n][maxValue] = lisMemoizedUtil(a, n-1, maxValue, res);
            
            return res[n][maxValue];
        }
        
        // bottom-up tabulation
        // T(n): O(n*maxValue), S(n): O(n*maxValue)
        public int lisBottomUp(int[] a) {
            int n = a.length;
            int maxValue = Arrays.stream(a).max().getAsInt() + 1;
            int[][] res = new int[n+1][maxValue+1];
            
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= maxValue; j++) {
                    if (i == 0 || j == 0)
                        res[i][j] = 0;
                    else if (a[i-1] < j)
                        res[i][j] = Math.max(1 + res[i-1][a[i-1]], res[i-1][j]);
                    else
                        res[i][j] = res[i-1][j];
                }
            }
            return res[n][maxValue];
        }
        
        /*
         * An O(n^2) solution as discussed at:
         * https://www.geeksforgeeks.org/longest-increasing-subsequence-dp-3/
         * 
         * Let a[0..n-1] be the input array and L(i) be the
         * length of the LIS ending at index 'i' such that 
         * a[i] is the last element of the LIS.
         * 
         * Then, L(i) can be written recursively as:
         * L(i) = 1 + max(L(j)) 
         *                 , where 0 < j < i and a[j] < a[i]
         *         = 1, if no such j exists
         * 
         * To find the LIS for a given array, we need to 
         * return max(L(i)) for 0 < i < n
         */
        // T(n): O(n^2), S(n): O(n)
        public int lis(int[] a) {
            int n = a.length;
            int[] res = new int[n];
            
            res[0] = 1;
            for (int i = 1; i < n; i++) {
                res[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (a[j] < a[i]) {
                        if (res[i] < res[j] + 1) {
                            res[i] = res[j] + 1;
                        }
                    }
                }
            }
            
            // get maximum from res[] array
            return Arrays.stream(res).max().getAsInt();
        }
    }
    
    /*
     * NOTE:
     * This problem has a O(nlgn) solution discussed at:
     * https://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
     */
    
    public static void main(String[] args) {
        int[] a = {10, 22, 9, 33, 21, 50, 41, 60};
        LongestIncreasingSubsequence obj = new LongestIncreasingSubsequence();
        
        System.out.println(obj.new SimpleRecursiveSolution().lis(a)); // 5
        System.out.println(obj.new DPSolution().lisMemoized(a)); // 5
        System.out.println(obj.new DPSolution().lisBottomUp(a)); // 5
        System.out.println(obj.new DPSolution().lis(a)); // 5
    }
}
