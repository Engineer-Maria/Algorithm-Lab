/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab_report_5;

/**
 *
 * @author Maria
 */
public class InversionCount {
    private static long inversionCount = 0;

    public static void mergeSortWithInversionCount(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortWithInversionCount(arr, left, mid);
            mergeSortWithInversionCount(arr, mid + 1, right);
            mergeWithInversionCount(arr, left, mid, right);
        }
    }

    private static void mergeWithInversionCount(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
                inversionCount += (mid - left + 1) - i;  // Count inversions
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 2, 4, 6};
        System.out.println("Original array: ");
        printArray(arr);
        
        mergeSortWithInversionCount(arr, 0, arr.length - 1);
        
        System.out.println("\nSorted array:");
        printArray(arr);
        System.out.println("\nTotal inversions: " + inversionCount);
    }

    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}