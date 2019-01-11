/**
 * 冒泡排序算法
 * 第一层循环是整个排序的循环次数
 * 第二个循环是把数组中最大的数放到末尾
 */
public class test {
    public static void main(String[] arge) {
        int[] array = {25, 24, 12, 76, 101, 96, 28};
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length -1- i; j++) {
                if (array[j] > array[j+1]) {
                    array[j] ^= array[j+1];
                    array[j+1] ^= array[j];
                    array[j] ^= array[j+1];
                }
            }
        }
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
