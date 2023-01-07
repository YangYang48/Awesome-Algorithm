package hash_table_problem;


import java.util.ArrayList;
import java.util.TreeMap;

/**
 * 349（set）、350（map）：
 *      1、C++ 中 map find 不到元素时输出 0
 *
 * O(nlogn)
 * O(n)
 */
public class Solution350 {

    public int[] intersect(int[] nums1, int[] nums2) {

        TreeMap<Integer, Integer> record = new TreeMap<>();
        for(Integer item:nums1) {
            if (!record.containsKey(item)) {
                record.put(item, 1);
            } else {
                record.put(item, record.get(item) + 1);
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for(Integer item:nums2) {
            if (record.containsKey(item) && record.get(item) > 0) {
                result.add(item);
                record.put(item, record.get(item) - 1);
            }
        }

        int[] res = new int[result.size()];
        int i = 0;
        for(Integer item:result) {
            res[i++] = item;
        }

        return res;
    }

}