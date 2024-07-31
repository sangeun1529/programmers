package level.two;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Solution {

    int generateInt() {
        return 10;
    }

    public static void main(String[] args) {
        System.out.println("--run coding test--");

        System.out.println("answer : " + new Solution().generateInt());
    }
}