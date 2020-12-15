package com.company.lab4;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Runner {
    private static final Random rand = new Random();

    private static int[] generateArray(int size) {
        int[] randomNumbers = new int[size];
        for (int i = 0; i < randomNumbers.length; i++) {
            randomNumbers[i] = rand.nextInt();
        }
        return randomNumbers;
    }

    public static void main(String[] args) {
        long timeStarted;
        long timeFinished;
        double averageTime = 0;
        try (FileOutputStream fileOutputStream = new FileOutputStream("output4.txt")) {
            for (int i = 100_000; i <= 120_000; i += 10_000) {
                for (int j = 0; j < 5; j++) {
                    timeStarted = System.currentTimeMillis();
                    int[] testArr = generateArray(i);
                    int[] copyArr = Arrays.copyOf(testArr, testArr.length);
                    SortIntegerArray.shakerSort(testArr);
                    timeFinished = System.currentTimeMillis();
                    averageTime += timeFinished - timeStarted;
                    Arrays.sort(copyArr);
                    assert Arrays.equals(testArr, copyArr);
                }
                averageTime /= 5;
                String strToWrite = ("{" + i / 1e3 + ", " + averageTime + "},\n");
                fileOutputStream.write(strToWrite.getBytes());
                System.out.println(strToWrite);
            }
        } catch (IOException ignored) {

        }
    }
}
