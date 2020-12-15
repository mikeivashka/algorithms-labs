package com.company.lab2;

public class BinarySearch {
    public static int binarySearch(int[] sortedArray, int key, int low, int high) {
        int middle = (low + high) / 2;
        if (high < low) {
            return -1;
        }
        if (key == sortedArray[middle]) {
            return middle;
        } else if (key < sortedArray[middle]) {
            return binarySearch(sortedArray, key, low, middle - 1);
        } else {
            return binarySearch(sortedArray, key, middle + 1, high);
        }
    }

    public static void main(String[] args) {
//        System.out.println("Size of SORTED array: ");
//        Scanner scanner = new Scanner(System.in);
//        int size = scanner.nextInt();
//        int[] arrayToBeSorted = new int[size];
//        System.out.println("Elements of SORTED array: ");
//        for (int i = 0; i < size; i++) {
//            arrayToBeSorted[i] = scanner.nextInt();
//        }
//        System.out.println("Element to be found: ");
//        int elementToBeFound = scanner.nextInt();
//        System.out.println("Position of element is: " + binarySearch(arrayToBeSorted, elementToBeFound, 0, size - 1));
        int[] arr = new int[(int)1e8*5];
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = i;
        }

        long timeBefore = System.currentTimeMillis();
        System.out.println(binarySearch(arr, 0, 0, arr.length-1));
        System.out.println(System.currentTimeMillis() - timeBefore);

    }
}
