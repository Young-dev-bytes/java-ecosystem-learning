import org.junit.Test;

import java.util.Arrays;

public class AlgorithmTests {

    @Test
    public void reverse_string() {
        reverse2("Young");
        System.out.println(reverse("Young"));
    }

    @Test
    public void merged_sorted_array() {
        // Input:  A[] = [1, 5], B[] = [4, 6, 7]
        // Output: [1, 4, 5, 6, 7]
        int[] nums1 = {1, 5, 9};
        int m = nums1.length;
        int[] nums2 = {4, 6, 7};
        int n = nums2.length;


        mergedSortedArray(nums1, m, nums2, n);

    }

    private void mergedSortedArray(int[] nums1, int m, int[] nums2, int n) {
        int[] ints = new int[m + n];
        for (int i = 0; i < n; i++) {
            ints[i] = nums1[i];
        }
        for (int i = 0; i < m; i++) {
            ints[i + n] = nums2[i];
        }
        Arrays.sort(ints);
        System.out.println(Arrays.toString(ints));

    }


    public static String reverse(String input) {

        char[] in = input.toCharArray();
        int begin = 0;
        int end = in.length - 1;
        char temp;
        while (begin < end) {
            temp = in[begin];
            in[begin] = in[end];
            in[end] = temp;
            begin++;
            end--;
        }
        return new String(in);
    }

    public static String reverse2(String str) {
        String res = new StringBuilder(str).reverse().toString();
        System.out.println(res);
        return res;
    }

}
