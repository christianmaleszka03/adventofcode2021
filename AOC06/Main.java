import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        Integer[] inputArr = {4,3,3,5,4,1,2,1,3,1,1,1,1,1,2,4,1,3,3,1,1,1,1,2,3,1,1,1,4,1,1,2,1,2,2,1,1,1,1,1,5,1,1,2,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,5,1,4,2,1,1,2,1,3,1,1,2,2,1,1,1,1,1,1,1,1,1,1,4,1,3,2,2,3,1,1,1,4,1,1,1,1,5,1,1,1,5,1,1,3,1,1,2,4,1,1,3,2,4,1,1,1,1,1,5,5,1,1,1,1,1,1,4,1,1,1,3,2,1,1,5,1,1,1,1,1,1,1,5,4,1,5,1,3,4,1,1,1,1,2,1,2,1,1,1,2,2,1,2,3,5,1,1,1,1,3,5,1,1,1,2,1,1,4,1,1,5,1,4,1,2,1,3,1,5,1,4,3,1,3,2,1,1,1,2,2,1,1,1,1,4,5,1,1,1,1,1,3,1,3,4,1,1,4,1,1,3,1,3,1,1,4,5,4,3,2,5,1,1,1,1,1,1,2,1,5,2,5,3,1,1,1,1,1,3,1,1,1,1,5,1,2,1,2,1,1,1,1,2,1,1,1,1,1,1,1,3,3,1,1,5,1,3,5,5,1,1,1,2,1,2,1,5,1,1,1,1,2,1,1,1,2,1};
        Integer[] testArr = {3,4,3,1,2};

        System.out.println(challenge2(inputArr));
    }

    static public int challenge1(Integer[] inputArr) {
        ArrayList<Integer> inputList = new ArrayList<Integer>(Arrays.asList(inputArr));
        for(int i = 0; i < 80; i++) {
            increaseOneDay(inputList);
            System.out.println(inputList.size());
        }

        return inputList.size();
    }

    static public long challenge2(Integer[] inputArr) {
        long[] counts = new long[9];
        for(int i = 0; i < inputArr.length; i++) {
            counts[inputArr[i]] = counts[inputArr[i]] + 1;
        }
        System.out.println(Arrays.toString(inputArr));
        System.out.println(Arrays.toString(counts));

        for(int i = 0; i < 256; i++) {
            counts = increaseOneDayNew(counts);
            System.out.println(Arrays.toString(counts));
        }
        long result = 0;
        for(int i = 0; i < counts.length; i++) {
            result += counts[i];
        }
        return result;
    }

    static private long[] increaseOneDayNew(long[] pArr) {
        long[] result = new long[9];
        for(int i = 1; i < pArr.length; i++) {
            result[i-1] = pArr[i];
        }
        result[8] = pArr[0];
        result[6] = pArr[7] + pArr[0];
        return result;
    }

    static private void increaseOneDay(ArrayList<Integer> pList) {
        for(int i = 0; i < pList.size(); i++) {
            if(pList.get(i) == 0) {
                pList.set(i, 6);
                pList.add(9);
            } else {
                pList.set(i, pList.get(i)-1);
            }
        }
    }

}
