/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab_report_5;
/**
 * @author Maria
 */
class Node {
    int data;
    Node next;
    
    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedListMergeSort {
    
    // Function to merge two sorted linked lists
    private Node merge(Node left, Node right) {
        // Create a dummy node to build the result
        Node dummy = new Node(0);
        Node current = dummy;
        
        // Traverse both lists and merge them
        while (left != null && right != null) {
            if (left.data <= right.data) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }
            current = current.next;
        }
        
        // Attach remaining elements of left or right list
        if (left != null) {
            current.next = left;
        } else {
            current.next = right;
        }
        
        return dummy.next;
    }
    
    // Function to find the middle of the linked list
    private Node getMiddle(Node head) {
        if (head == null) {
            return head;
        }
        
        Node slow = head;
        Node fast = head.next;
        
        // Fast pointer moves two steps, slow pointer moves one step
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    // Main merge sort function
    public Node mergeSort(Node head) {
        // Base case: empty list or single element
        if (head == null || head.next == null) {
            return head;
        }
        
        // Get the middle of the list
        Node middle = getMiddle(head);
        Node nextOfMiddle = middle.next;
        
        // Split the list into two halves
        middle.next = null;
        
        // Recursively sort both halves
        Node left = mergeSort(head);
        Node right = mergeSort(nextOfMiddle);
        
        // Merge the sorted halves
        return merge(left, right);
    }
    
    // Utility function to print the linked list
    public void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
    
    // Utility function to add a node to the linked list
    public Node addNode(Node head, int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        return head;
    }
    
    public static void main(String[] args) {
        LinkedListMergeSort sorter = new LinkedListMergeSort();
        Node head = null;
        
        // Create the linked list from the lab exercise
        int[] values = {20, 7, 15, 9, 35, 4, 1, 11, 7, 16};
        for (int value : values) {
            head = sorter.addNode(head, value);
        }
        
        System.out.println("Original linked list:");
        sorter.printList(head);
        
        // Sort the linked list
        head = sorter.mergeSort(head);
        
        System.out.println("Sorted linked list:");
        sorter.printList(head);
    }
}