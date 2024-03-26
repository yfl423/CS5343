import java.util.*;

/**
 * Implement Hash table.
 * Pick 20 random words.  Each word must be of different lengths, maximum length 8 characters  and minimum length 3 characters. By random I do not mean some random program, I mean just pick some workds of various lengths as mentioned.
 * The words will be of letters a-zA-Z and the space character.
 * Insert them into a hash table.
 * You can use a library for only the hash function.
 * The collision resolution scheme should be open addressing - quadratic.
 * Initially the table size is 31.  The program should increase the table size and rehash at load factor of .5
 * So after you inserted about 15 or 16 words, your program automatically doubles the table size and re-inserts (automatically) the old words and then continue the insert of additional words.
 * You do not have to insert the words manually (one by one) but you can add the words in a file and let your program read from the file
 * At the end print the total number of collisions you get.
 * Submit your code and print screen of your execution
 **/
public class Main {
    static int collisionCnt;

    public static void main(String[] args) {
        List<String> wordList = new ArrayList<>();
        WordBuilder wordBuilder = new WordBuilder(3, 8);
        for (int i = 0; i < 20; i++) {
            wordList.add(wordBuilder.build());
        }
        System.out.println("All 20 Words are as following:");
        System.out.println(wordList);
        System.out.println("====Start to insert each into hashTable...====");
        MyHashTable<String> hashTable = new MyHashTable<>();
        for (String w : wordList) hashTable.add(w);
        System.out.println("After all are inserted, the total times of hash collision is " + collisionCnt);

    }

    static class WordBuilder {
        private Set<Character> characterSet = new HashSet<>();
        private int lowerLimit;
        private int upperLimit;

        public WordBuilder(int lowerLimit, int upperLimit) {
            this.lowerLimit = lowerLimit;
            this.upperLimit = upperLimit;
            for (char c = 'a'; c <= 'z'; c++) characterSet.add(c);
            for (char c = 'A'; c <= 'Z'; c++) characterSet.add(c);
            characterSet.add(' ');
        }

        public String build() {
            StringBuilder sb = new StringBuilder();
            int len = lowerLimit + (int) (Math.random() * (upperLimit - lowerLimit + 1));
            for (int i = 0; i < len; i++) {
                int j = 0, r = (int) (Math.random() * characterSet.size());
                for (char c : characterSet) {
                    if (j++ == r) {
                        sb.append(c);
                        break;
                    }
                }
            }
            return sb.toString();
        }
    }

    static class MyHashTable<T> {
        private final int initSize = 32;
        private final double loadFactor = 0.5f;
        private Object[] data = new Object[initSize];
        private int size;


        public MyHashTable() {
        }

        public int size() {
            return this.size;
        }

        public void add(T obj) {
            insertData(obj);
            size++;
            if ((double) (size) / (double) data.length >= loadFactor) { // Enlarge and rehash
                Object[] temp = data;
                data = new Object[data.length * 2];
                for (Object o : temp) if (o != null) insertData(o);
            }
        }

        /**
         * Insert Object into data, and resolve possible hash conflicts by quadratic probing
         */
        private void insertData(Object obj) {
            int idx = (obj.hashCode() % data.length + data.length) % data.length;
            int i = 1;
            while (data[idx] != null) {
                System.out.println("NO." + ++collisionCnt + " hash collision occurs, when inserting " + (T) obj + ", conflicted with existing " + (T) data[idx]);
                // quadratic probing
                idx = (idx + i * i) % data.length;
                i++;
            }
            data[idx] = obj;
        }
    }
}
