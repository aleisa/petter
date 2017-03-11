
package test;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class test {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double result = 0;
          int[] nums3 = Arrays.copyOf(nums1,nums1.length+nums2.length);
          System.arraycopy(nums2,0,nums3,nums1.length,nums2.length);
          Arrays.sort(nums3);
          int i = nums3.length%2;
          if(i==0){
               result = nums3[nums3.length/2-1]+nums3[nums3.length/2];
              result = result/2;
          }else{
              int index = nums3.length/2;
              result = nums3[index];
          }
          return result;
    }

    public static void main(String[] args) {
        int[] a = {11,2};
        int[] b = {7,3,16,22};
        test test = new test();
        double median = test.findMedianSortedArrays(a,b);
        System.out.print(median);
    }
}


