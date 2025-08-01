/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab_report_5;

/**
 *
 * @author Maria
 */
public class DescendingMergeSort {
    public static void mergeSortDesc(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortDesc(arr, left, mid);
            mergeSortDesc(arr, mid + 1, right);
            mergeDesc(arr, left, mid, right);
        }
    }

    private static void mergeDesc(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] >= R[j]) {  // Changed comparison for descending order
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    public static void main(String[] args) {
        int[] arr = {20, 7, 15, 9, 35, 4, 1, 11, 7, 16};
        System.out.println("Original array: ");
        printArray(arr);
        
        mergeSortDesc(arr, 0, arr.length - 1);
        
        System.out.println("\nSorted array in descending order:");
        printArray(arr);
    }

    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}