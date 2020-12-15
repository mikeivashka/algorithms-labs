package com.company.lab1;

import com.company.utils.Pair;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;


public class SumOfArrayElementsFinder {

    public static void main(String[] args) throws IOException {

        try (FileOutputStream fileOutputStream = new FileOutputStream("output.txt")) {
            long timeStarted;
            long timeFinished;
            double averageTime = 0;
            for (int i = (int) 10e6; i <= 50e6; i += 1e6) {
                for (int j = 0; j < 5; j++) {
                    timeStarted = System.currentTimeMillis();
                    findSumOfNegativeAndOther(generateArray(i));
                    timeFinished = System.currentTimeMillis();
                    averageTime += timeFinished - timeStarted;
                }
                averageTime/=5;
                String strToWrite = ("{" + i /1e6 + ", " + averageTime + "},\n");
                fileOutputStream.write(strToWrite.getBytes());
                System.out.println(strToWrite);
            }
        } catch (FileNotFoundException ignored) {
        }

    }

    private static Pair<Long, Long> findSumOfNegativeAndOther(int[] array) {
        Long negativeSum = 0L;
        Long positiveSum = 0L;
        for (int element : array) {
            if (element >= 0) {
                positiveSum += element;
            } else {
                negativeSum += element;
            }
        }
        return new Pair<>(positiveSum, negativeSum);
    }

    private static int[] generateArray(int length) {
        int[] randomArray = new int[length];
        Arrays.stream(randomArray).forEach(i -> i = new Random().nextInt(1000) - 500);
        return randomArray;
    }
}
