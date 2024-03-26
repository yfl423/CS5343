import java.util.Arrays;

public class TertiarySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 2, 6, 1, 0, 7};
        testCase1(arr, 6);
        testCase2(arr, 5);
    }

    /**
     * The target exists in the arr
     */
    static void testCase1(int[] arr, int target) {
        System.out.println(tertiarySearch(arr, target));
    }

    /**
     * The target does not exist in the arr
     */
    static void testCase2(int[] arr, int target) {
        System.out.println(tertiarySearch(arr, target));
    }

    /**
     * Return the index of element in the given arr which equals to target;
     * Return -1 if not existed
     **/
    static int tertiarySearch(int[] arr, int target) {
        Arrays.sort(arr);
        System.out.println("arr: " + Arrays.toString(arr));
        System.out.println("target: " + target);
        return tertiarySearch(arr, 0, arr.length - 1, target);
    }

    static int tertiarySearch(int[] arr, int start, int end, int target) {
        if (start + 1 == end) {
            return arr[start] == target ? start : (arr[start + 1] == target ? start + 1 : (arr[end] == target ? end : -1));
        }
        int p1 = start + (end - start) / 3;
        int p2 = p1 + (end - start) / 3;
        if (target < arr[start]) return -1;
        if (target == arr[start]) return start;
        if (target < arr[p1]) return tertiarySearch(arr, start, p1, target);
        if (target == arr[p1]) return p1;
        if (target < arr[p2]) return tertiarySearch(arr, p1, p2, target);
        if (target == arr[p2]) return p2;
        if (target < arr[end]) return tertiarySearch(arr, p2, end, target);
        if (target == end) return end;
        return -1;
    }
}
