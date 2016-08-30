/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sorts;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 *
 * @author jmlb
 */
public class Sorts {

    public static void main(String[] args) {
        
        final int SIZE = 10;
        Random r = new Random();
        int[] A = new int[SIZE];
        
        for (int j = 0; j < 10; j++) {
            

            for (int i = 0; i < SIZE; i++){
                A[i] = r.nextInt(Integer.MAX_VALUE);
            }
        
            long start = System.nanoTime();
            //BubbleSort(A);
            //SelectionSort(A);
            //InsertionSort(A);
            //MergeSort(A);
            //QuickSort(A, 0, A.length-1); //Quicksort lleva parámetros adicionales
            //RadixSort(A);
            HeapSort(A);
            long end = System.nanoTime();
        
            long tot = end - start;
            double tots = (double)tot / 1000000000.0;
            

            //print(A, A.length);
            System.out.println("Corrida " + j + " Tiempo: " + tots);
        
        }
    }

    public static int[] BubbleSort(int[] num) {
        int temp;
        for(int i=0; i < num.length-1; i++){
 
            for(int j=1; j < num.length-i; j++){
                if(num[j-1] > num[j]){
                    temp=num[j-1];
                    num[j-1] = num[j];
                    num[j] = temp;
                }
            }
        }
        return num;
    }

    public static int[] InsertionSort(int[] num) {
        int temp;
        for (int i = 1; i < num.length; i++) {
            for(int j = i ; j > 0 ; j--){
                if(num[j] < num[j-1]){
                    temp = num[j];
                    num[j] = num[j-1];
                    num[j-1] = temp;
                }
            }
        }
        return num;

    }

    public static int[] SelectionSort(int[] num) {
        for (int i = 0; i < num.length - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < num.length; j++)
                if (num[j] < num[index])
                    index = j;
      
            int smallerNumber = num[index]; 
            num[index] = num[i];
            num[i] = smallerNumber;
        }
        return num;
        
    }

    //Merge Sort
    public static void MergeSort(int[] array) {
        if (array.length > 1) {

            int[] left = leftHalf(array);
            int[] right = rightHalf(array);

            MergeSort(left);
            MergeSort(right);

            merge(array, left, right);
        }
    }

    public static int[] leftHalf(int[] array) {
        int size1 = array.length / 2;
        int[] left = new int[size1];
        for (int i = 0; i < size1; i++) {
            left[i] = array[i];
        }
        return left;
    }

    public static int[] rightHalf(int[] array) {
        int size1 = array.length / 2;
        int size2 = array.length - size1;
        int[] right = new int[size2];
        for (int i = 0; i < size2; i++) {
            right[i] = array[i + size1];
        }
        return right;
    }

    public static void merge(int[] result,
            int[] left, int[] right) {
        int i1 = 0;
        int i2 = 0;

        for (int i = 0; i < result.length; i++) {
            if (i2 >= right.length || (i1 < left.length
                    && left[i1] <= right[i2])) {
                result[i] = left[i1];
                i1++;
            } else {
                result[i] = right[i2];
                i2++;
            }
        }
    }
    //Merge Sort

    //Radix Sort
    public static int[] RadixSort(int[] input){

        // Largest place for a 32-bit int is the 1 billion's place
        for(int place=1; place <= 1000000000; place *= 10){
            // Use counting sort at each digit's place
            input = countingSort(input, place);
        }

        return input;
    }

    private static int[] countingSort(int[] input, int place){
        int[] out = new int[input.length];

        int[] count = new int[10];

        for(int i=0; i < input.length; i++){
            int digit = getDigit(input[i], place);
            count[digit] += 1;
        }

        for(int i=1; i < count.length; i++){
            count[i] += count[i-1];
        }

        for(int i = input.length-1; i >= 0; i--){
            int digit = getDigit(input[i], place);

            out[count[digit]-1] = input[i];
            count[digit]--;
        }

        return out;

    }

    private static int getDigit(int value, int digitPlace){
        return ((value/digitPlace ) % 10);
    }
    //Radix Sort

    public static int[] QuickSort(int[] arr, int low, int high) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        if (low >= high) {
            return arr;
        }

        // pick the pivot
        int middle = low + (high - low) / 2;
        int pivot = arr[middle];

        // make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }

            while (arr[j] > pivot) {
                j--;
            }

            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        // recursively sort two sub parts
        if (low < j) {
            QuickSort(arr, low, j);
        }

        if (high > i) {
            QuickSort(arr, i, high);
        }

        return arr;
    }

    //HeapSort
    private static int N;
    public static int[] HeapSort(int arr[])
    {       
        heapify(arr);        
        for (int i = N; i > 0; i--)
        {
            swap(arr,0, i);
            N = N-1;
            maxheap(arr, 0);
        }
        return arr;
    }     
    /* Function to build a heap */   
    public static void heapify(int arr[])
    {
        N = arr.length-1;
        for (int i = N/2; i >= 0; i--)
            maxheap(arr, i);        
    }
    /* Function to swap largest element in heap */        
    public static void maxheap(int arr[], int i)
    { 
        int left = 2*i ;
        int right = 2*i + 1;
        int max = i;
        if (left <= N && arr[left] > arr[i])
            max = left;
        if (right <= N && arr[right] > arr[max])        
            max = right;
 
        if (max != i)
        {
            swap(arr, i, max);
            maxheap(arr, max);
        }
    }    
    /* Function to swap two numbers in an array */
    public static void swap(int arr[], int i, int j)
    {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp; 
    }
    //HeapSort

    static void print(int arr[], int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

}
