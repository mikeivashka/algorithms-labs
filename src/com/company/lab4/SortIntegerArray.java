package com.company.lab4;

public class SortIntegerArray {
    static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    static void shakerSort(int[] arr) {
        int leftBorder = 0;
        int rightBorder = arr.length - 1;
        int number = -1;
        do {
            for (int i = leftBorder; i < rightBorder; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                    number = i;
                }
            }
            rightBorder = number;
            for (int i = rightBorder; i > leftBorder; i--) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                    number = i;
                }
            }
            leftBorder = number;
        } while (leftBorder < rightBorder);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


}
