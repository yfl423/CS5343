import java.util.*;

/**
 * We have discussed Binary Search Trees.(BST)
 * Write a program to implement a delete operation from BST.
 * You will have to write the program to insert nodes in the BST also (we already did the algorithm in detail in the class for insert).
 * Insert the following nodes in the order mentioned here.
 * 40, 60, 20, 80, 50, 10, 30, 15, 5, 35, 25, 45, 55, 70, 90, 32, 33, 48, 46
 * Do an inorder traversal.
 * make screen shot
 * Now delete 40 (you decide predecessor or successor).
 * Do inorder traversal again.
 * Make screen shot
 * Now delete 20
 * Do inroder traversal
 * make screen shot.
 * Submit the code.
 * Submit the screen shots.
 **/
public class Main {

    static Map<Integer, TreeNode> registerTable = new HashMap<>();

    public static void main(String[] args) {
        // [[],[10,20],[50,60],[10,40],[5,15],[5,10],[25,55]]
        MyCalendarThree myCalendarThree = new MyCalendarThree();
        myCalendarThree.book(10,20);
        myCalendarThree.book(50,60);
        myCalendarThree.book(10,40);
        myCalendarThree.book(5,15);
        myCalendarThree.book(5,10);
        myCalendarThree.book(25,55);
    }
}

class MyCalendarThree {
    List<List<int[]>> calendar = new ArrayList<>();


    public MyCalendarThree() {

    }

    public int book(int startTime, int endTime) {
        process(new int[]{startTime, endTime}, 0);
        return calendar.size();
    }

    void process(int[] curr, int idx){

        if(idx == calendar.size()){
            calendar.add(new ArrayList<>());
            calendar.get(idx).add(curr);
            return;
        }

        // boolean overlap = false;
        List<int[]> list = calendar.get(idx);

        for(int[] arr: list){
            if(arr[0] < curr[1] && arr[1] > curr[0]){
                int[] o = new int[]{Math.max(curr[0],arr[0]), Math.min(curr[1],arr[1])};
                process(o, idx+1);
            }
        }
        list.add(curr);
    }
}

