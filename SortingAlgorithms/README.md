# SortingAlgorithms
Implementation of various sorting algorithms (various space and runtime complexities) that can be used to sort data, applied to int []'s

**HeapSort.java**-    Sort using a min heap data structure to store and sort the input data

**MergeSort.java**-   Sort which breaks input data into subranges sorts the subranges and recombines sorted sub ranges

**RadixSort.java**-   Sort which sorts input data by looking at values digit by digit, and places values into buckets by digit

**QuickSort.java** -  Sort which sorts input data by picking a pivot and grouping values based on lower/equal and higher than the pivot and then sorts sub groups  

**BubbleSort.java** - Sort which sorts input data by comparing elements pairwise and swapping elements when necessarry, repeating passes until sorted

**CountSort.java**-   Sort which sorts input data by taking an inventory/ count of the occurences of each value in the input and then outputs each value the appropriate number of times to the input array, (requires knowledge of smallest and largest possible int vals)

**InsertionSort.java** - Sort which sorts input data by keeping track of a sorted frontier and inserting new elements into the previously sorted portion of the array

**ShellSort.java** - Sort which sorts input data by expanding on the ideas of insertion sort and bubble sort. Sorts all elements which are a gap apart using a comparison sort like insertion/bubble sort with a sequence of gaps, ultimately ending in doing an improved comparison sort (a gap of 1)
