/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab_report_5;

/**
 *
 * @author Maria
 */
public class DebugMergeSort {
    private static int step = 1;

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            
            System.out.println("Step " + step++ + ": Dividing (" + left + "-" + right + ")");
            System.out.println("Left half: " + arrayToString(arr, left, mid));
            
            mergeSort(arr, left, mid);
            
            System.out.println("Step " + step++ + ": Dividing (" + (mid+1) + "-" + right + ")");
            System.out.println("Right half: " + arrayToString(arr, mid+1, right));
            
            mergeSort(arr, mid + 1, right);
            
            System.out.println("Step " + step++ + ": Merging (" + left + "-" + mid + ") and (" + (mid+1) + "-" + right + ")");
            merge(arr, left, mid, right);
            
            System.out.println("After merge: " + arrayToString(arr, left, right));
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
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
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    private static String arrayToString(int[] arr, int start, int end) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = start; i <= end; i++) {
            sb.append(arr[i]);
            if (i < end) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] arr = {20, 7, 15, 9, 35, 4, 1, 11, 7, 16};
        System.out.println("Original array: " + arrayToString(arr, 0, arr.length-1));
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("\nFinal sorted array: " + arrayToString(arr, 0, arr.length-1));
    }
}