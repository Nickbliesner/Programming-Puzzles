
# SearchingAlgorithms
Implementation of various searching algorithms (various space and runtime complexities) for searching through mainly sorted data, applied to int []'s

**BinarySearch.java**-    Search which employs the sorted properties of the data to eliminate half the search space each iteration based on comparisons to middle values

**ExponentialSearch.java**-   Search which employs the sorted properties to exponentially search for which subarray would contain the target and then binary search that sub array

**FibonacciSearch.java**-   Search which employs the sorted properties to use fibonacci numbers to narrow down the search space each step

**FindUnimodalMin.java** -  Searches for the min of a unimodal function (only decreasing then increasing) along a given interval  

**InterpolationSearch.java** - Search which employs the sorted properties to use linear approximation to estimate the the target's index

**JumpSearch.java**- Search which employs the sorted properties to jump through the data a gap size each time until the gap where the target could be is found and then is searched linearly 

**LinearSearch.java** - Search (usually for unsorted data) which looks at data item by item until the target can be found or is absent

**TernarySearch.java** - Search which employs the sorted properties to eliminate two thirds of the search space each iteration based on comparsions to the lower third and upper third values
