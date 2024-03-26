import java.util.*;

/**
 * assignment-3
 * Implement heap sort.
 * 1. Make an array of 15 numbers. Make sure the array is not sorted AND the numbers do not make a heap.
 * You may hard code this array in your main program.
 * 2. Convert the array into a heap, using Floyd's algorithm.  Print the new array
 * 3. Sort the array into descending order using heap sort method. Print the array.
 **/
public class Heap {
    public static void main(String[] args) {
        int[] arr = prepareRandomArray(15);
        System.out.println("Raw Array: " + Arrays.toString(arr));
        buildHeap(arr);
        System.out.println("After heap build: " + Arrays.toString(arr));
        heapSort(arr);
        System.out.println("After heap sort: " + Arrays.toString(arr));
    }

    /**
     * Use Floyd’s Algorithm to build heap
     **/
    public static void buildHeap(int[] arr) {
        int[] helper = new int[arr.length + 1]; // Starting from 1
        for (int i = 1; i < helper.length; i++) helper[i] = arr[i - 1];
        for (int i = helper.length / 2; i >= 1; i--) heapify(helper, i, helper.length - 1);
        for (int i = 0; i < arr.length; i++) arr[i] = helper[i + 1]; // Copy back
    }

    /**
     * heapSort: based on heapify
     **/
    public static void heapSort(int[] arr) {
        int[] helper = new int[arr.length + 1]; // Starting from 1
        for (int i = 1; i < helper.length; i++) helper[i] = arr[i - 1];
        for (int validEnd = helper.length - 1; validEnd >= 1; validEnd--) {
            heapify(helper, 1, validEnd);
            swap(helper, 1, validEnd);
        }
        for (int i = 0; i < arr.length; i++) arr[i] = helper[i + 1]; // Copy back
    }

    /**
     * heapify: is used to maintain the property of heap, arr means current array, curr means current node, validEnd means the last valid index
     **/
    private static void heapify(int[] arr, int curr, int validEnd) {
        int left, right;
        while (curr <= validEnd && (left = getLeftChild(curr)) <= validEnd) {
            right = getRightChild(curr);
            int cVal = arr[curr];
            int lVal = arr[left];
            int rVal = right <= validEnd ? arr[right] : Integer.MAX_VALUE;
            if (rVal == Integer.MAX_VALUE) {
                if (cVal >= lVal) return;
                swap(arr, curr, left);
                curr = left;
            } else {
                if (cVal >= lVal && cVal >= rVal) return;
                if (cVal < lVal && cVal >= rVal) {
                    swap(arr, curr, left);
                    curr = left;
                } else if (cVal < rVal && cVal >= lVal) {
                    swap(arr, curr, right);
                    curr = right;
                } else { // curr is smaller than both children, choose the larger to swap
                    if (lVal > rVal) {
                        swap(arr, curr, left);
                        curr = left;
                    } else {
                        swap(arr, curr, right);
                        curr = right;
                    }
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int getLeftChild(int curr) {
        return 2 * curr;
    }

    public static int getRightChild(int curr) {
        return 2 * curr + 1;
    }

    /**
     * prepareRandomArray: Create a random array the size of which is specified, all elements of array is unique
     **/
    private static int[] prepareRandomArray(int size) {
        int[] arr = new int[size];
        Set<Integer> visited = new HashSet<>();
        int curr;
        for (int i = 0; i < arr.length; i++) {
            do curr = (int) (Math.random() * size); while (visited.contains(curr));
            arr[i] = curr;
            visited.add(curr);
        }
        return arr;
    }
}
