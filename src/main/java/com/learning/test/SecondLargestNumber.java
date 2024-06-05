package com.learning.test;

/**
 * Problem Statement:
 *
 * You are given an array of integers. Write a function to find the second largest number in the array.
 *
 * For example:
 * Input: [3, 6, 8, 1, 9, 2]
 * Output: 8
 *
 * Input: [5, 5, 5, 5]
 * Output: null
 *
 * You need to implement the following method:
 *
 * public static Integer findSecondLargest(int[] arr) {
 * 	// your logic here
 * }
 *
 * Instructions:
 *
 * The candidate needs to implement the findSecondLargest method in Java.
 * The method should take a single integer array parameter, which represents the input array.
 * The method should return an integer that is the second largest number in the array, or null if there is no second largest number.
 * The implementation should be efficient in terms of time and space complexity.
 * The candidate can assume that the input array contains at least two distinct integers.
 *
 *
 * Assessment:
 *
 * The candidate's implementation will be evaluated based on the following criteria:
 *
 * Correctness: The implementation should return the correct output for all test cases.
 * Efficiency: The implementation should be efficient in terms of time and space complexity.
 * Code quality: The implementation should be well-structured and easy to read and understand.
 * Note: The candidate can use any data structure or algorithm to solve this problem. However, a simple approach would be to sort the array and return the second last element.
 */

public class SecondLargestNumber {

    public static Integer findSecondLargest(int[] arr) {

        int firstLargest = arr[0];
        int secondLargest = arr[1];

        if (firstLargest <= secondLargest) {
            int temp = secondLargest;
            secondLargest = firstLargest;
            firstLargest = temp;
        }

        for(int i = 2; i<arr.length; i++) {
            int candidate = arr[i];
            if (candidate >= firstLargest) {
                secondLargest = firstLargest;
                firstLargest = candidate;
            } else {
                if (candidate >= secondLargest) {
                    secondLargest = candidate;
                }
            }
        }
        return firstLargest == secondLargest ? null : secondLargest;
    }

    public static void main(String[] args) {
        System.out.println(findSecondLargest(new int[]{3, 6, 8, 1, 9, 2}));
        System.out.println(findSecondLargest(new int[]{5, 5, 5, 5}));
    }
}
