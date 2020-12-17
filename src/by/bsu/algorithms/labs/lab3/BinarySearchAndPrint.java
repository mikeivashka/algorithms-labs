package by.bsu.algorithms.labs.lab3;

import java.util.Scanner;

import static by.bsu.algorithms.labs.lab2.BinarySearch.binarySearch;

public class BinarySearchAndPrint {
    public static void recursivePrint(int[] arr, int startIndex) {
        System.out.print(arr[startIndex] + " ");
        if (startIndex == arr.length - 1) return;
        recursivePrint(arr, startIndex + 1);
    }

    public static void main(String[] args) {
        System.out.println("Size of SORTED array: ");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] arrayToBeSorted = new int[size];
        System.out.println("Elements of SORTED array: ");
        for (int i = 0; i < size; i++) {
            arrayToBeSorted[i] = scanner.nextInt();
        }
        System.out.println("Element to be found: ");
        int elementToBeFound = scanner.nextInt();
        System.out.println("Position of element is: " + binarySearch(arrayToBeSorted, elementToBeFound, 0, size - 1));
        recursivePrint(arrayToBeSorted, 0);

    }
}
