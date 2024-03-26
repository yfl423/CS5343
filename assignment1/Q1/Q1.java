public class Q1 {
    static final String bubbleSort = "BUBBLE_SORT";
    static final String selectionSort = "SELECTION_SORT";

    public static void main(String[] args) {
        ListNode data = null;
        String sortMethod = null;
        try {
            for (String arg : args) {
                switch (arg.substring(0, 2)) {
                    case "-h": {
                        printInstruction();
                        return;
                    }
                    case "-n": {
                        int n = Integer.parseInt(arg.split(":")[1]);
                        data = buildListNode(n);
                        break;
                    }
                    case "-s": {
                        sortMethod = arg.split(":")[1];
                        break;
                    }
                    default:
                        throw new RuntimeException();
                }
            }

            if (data == null) {
                data = buildListNode(15);
            }
            if (sortMethod == null) {
                sortMethod = bubbleSort;
            }

            System.out.println("Before sort, the ListNode is as followed: " + data);

            data = doSort(data, sortMethod);

            System.out.println("After sort, the ListNode is as followed: " + data);
        } catch (RuntimeException e) {
            throw new IllegalCommandLineException();
        }
    }

    static void printInstruction() {
        System.out.println(
                "-h: show all information\n" +
                        "-s: specify sort method, [BUBBLE_SORT/SELECTION_SORT]\n" +
                        "-n: specify the length of single list\n");
    }

    static ListNode buildListNode(int size) throws RuntimeException {
        if (size <= 0) throw new RuntimeException();
        ListNode head = new ListNode((int) (Math.random() * 100) + 1);
        int s = 1;
        ListNode p = head;
        while (s < size) {
            p.next = new ListNode((int) (Math.random() * 100) + 1);
            p = p.next;
            s++;
        }
        return head;
    }

    static ListNode doSort(ListNode head, String sortMethod) throws RuntimeException {
        if (!sortMethod.equals(bubbleSort) && !sortMethod.equals(selectionSort)) throw new RuntimeException();
        if (head == null || head.next == null) return null;
        if (bubbleSort.equals(sortMethod)) {
            ListNode dummy = new ListNode();
            dummy.next = head;
            ListNode last = null;
            while (last != dummy.next) {
                ListNode prev = dummy;
                ListNode curr = dummy.next;
                while (curr.next != last) {
                    ListNode next = curr.next;
                    if (curr.val > next.val) {
                        ListNode temp = next.next;
                        prev.next = next;
                        next.next = curr;
                        curr.next = temp;
                    }
                    prev = prev.next;
                    curr = prev.next;
                }
                last = curr;
            }
            return dummy.next;
        }

        // Selection Sort Flow
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode sorted = dummy;
        while (sorted.next != null) {
            ListNode p = sorted.next;
            ListNode maxPrev = sorted;
            ListNode min = p;
            while (p.next != null) {
                if (p.next.val < min.val) {
                    maxPrev = p;
                    min = p.next;
                }
                p = p.next;
            }
            maxPrev.next = min.next;
            ListNode sortedNext = sorted.next;
            sorted.next = min;
            min.next = sortedNext;

            sorted = sorted.next;
        }
        return dummy.next;
    }


    static class ListNode {
        int val;
        ListNode next;

        public ListNode() {

        }

        public ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode p = this;
            while (p != null) {
                sb.append(p.val);
                if (p.next != null) sb.append("->");
                p = p.next;
            }
            return sb.toString();
        }
    }

    static class IllegalCommandLineException extends RuntimeException {
        public IllegalCommandLineException() {
            super("illegal command line");
        }
    }
}
