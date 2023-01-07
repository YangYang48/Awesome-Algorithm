package sort_problem;

/**
 * 大顶堆实现堆排序：
 * 1、构建初始堆，将待排序列构成一个大顶堆：序列对应一个完全二叉树；从最后一个分支结点（n/2）开始，
 * 到根（1）为止，依次对每个分支结点进行下沉，以便形成以每个分支结点为根的堆，当最后对树根结点进行
 * 调整后，整个树就变成了一个堆。
 * 2、将堆顶元素与堆尾元素交换，并从待排序列中移除堆尾元素。
 * 3、使用下沉操作下沉堆顶元素以重新构建大顶堆。
 * 4、重复2~3，直到待排序列中只剩下一个元素(即堆顶元素)。
 *
 * 下沉操作：
 * 1、仅当当前节点有左子节点时进入while循环体。
 * 2、设立下沉后的位置为j，默认为左子节点的位置。
 * 3、如果当前节点有右子节点且左子节点小于右子节点时，下沉后的位置j取右子节点的位置（j++）。
 * 4、如果当前节点的位置k小于下沉后的位置j时，交换k与j的值，完成这一次的下沉操作。
 * 5、更新当前节点的位置为j，如果当前节点还有左子节点则又会进入while循环体进行上述的下沉操作。
 *
 * 把最大元素和当前堆中数组的最后一个元素交换位置，并且不删除它，
 * 那么就可以得到一个从尾到头的递减序列，从正向来看就是一个
 * 递增序列，这就是堆排序。
 *
 * 一个堆的高度为 logN，因此在堆中插入元素和删除最大元素的复杂度都为 logN。
 * 对于堆排序，由于要对 N 个节点进行下沉操作，因此复杂度为 NlogN。
 * 堆排序是一种原地排序，没有利用额外的空间。
 * 现代操作系统很少使用堆排序，因为它无法利用局部性原理进行缓存，
 * 也就是数组元素很少和相邻的元素进行比较和交换。
 */
public class HeapSort<T extends Comparable<T>> extends Sort<T> {

    /**
     * 数组第 0 个位置不能有元素
     */
    @Override
    public void sort(T[] nums) {
        int N = nums.length - 1;
        // 1、构建初始堆，将待排序列构成一个大顶堆：
        // 序列对应一个完全二叉树；从最后一个分支结点（n/2）开始，
        // 到根（1）为止，依次对每个分支结点进行调整（下沉），
        // 以便形成以每个分支结点为根的堆，当最后对树根结点进行
        // 调整后，整个树就变成了一个堆。
        for (int k = N / 2; k >= 1; k--) {
            sink(nums, k, N);
        }

        while (N > 1) {
            // 2、将堆顶元素与堆尾元素交换，并从待排序列中移除堆尾元素。
            swap(nums, 1, N--);
            // 3、使用下沉操作下沉堆顶元素以重新构建大顶堆。
            sink(nums, 1, N);
        }
    }

    private void sink(T[] nums, int k, int N) {
        // 1）、仅当当前节点有左子节点时进入while循环体。
        while (2 * k <= N) {
            // 2）、设立下沉后的位置为j，默认为左子节点。
            int j = 2 * k;

            // 3）、如果当前节点有右子节点且左子节点小于右子节点时，下沉后的位置j取右子节点的位置（j++）。
            if (j < N && less(nums, j, j + 1)) {
                j++;
            }

            // 4）、如果当前节点的位置k小于下沉后的位置j时，交换k与j的值，完成这一次的下沉操作。
            if (!less(nums, k, j)) {
                break;
            }
            swap(nums, k, j);

            // 5）、更新当前节点的位置为j，如果当前节点还有左子节点则又会进入while循环体进行上述的下沉操作。
            k = j;
        }
    }

    private boolean less(T[] nums, int i, int j) {
        return nums[i].compareTo(nums[j]) < 0;
    }
}