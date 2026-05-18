
class MaxHeap {
    private int[] arr;
    private int size;
    private int capacity; // maximum number of elements

    MaxHeap(int capacity) {
        arr = new int[capacity];
        size = 0;
        this.capacity = capacity;
    }

    void insert(int value) {
        if(size == capacity) {
            System.out.println("Overflow");
            return;
        }

        arr[size] = value;
        int index = size;
        size++;

        // Step-up operation
        while(index > 0 && arr[(index - 1)/2] < arr[index]) {
            // Do swap
            int temp = arr[index];
            arr[index] = arr[(index - 1)/2];
            arr[(index - 1)/2] = temp;
            // Update index to parent index
            index = (index - 1) / 2;
        }
    }

    // Delete helpers
    private void heapifyRecursive(int index) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int largestIndex = index;

        if(left < size && arr[left] > arr[largestIndex]) largestIndex = left;
        if(right < size && arr[right] > arr[largestIndex]) largestIndex = right;

        if(largestIndex != index) {
            // Swap 
            int temp = arr[largestIndex];
            arr[largestIndex] = arr[index];
            arr[index] = temp;
            // Call heapify on largestIndex
            heapifyRecursive(largestIndex);
        }
    }

    private void heapifyIterative(int index) {
        while(index < size) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;
            int largestIndex = index;

            if(left < size && arr[left] > arr[largestIndex]) largestIndex = left;
            if(right < size && arr[right] > arr[largestIndex]) largestIndex = right;

            if(largestIndex != index) {
                // Swap 
                int temp = arr[largestIndex];
                arr[largestIndex] = arr[index];
                arr[index] = temp;

                // Update index
                index = largestIndex;
            }else {
                break;
            }
        }
    }

    void delete() {
        if(size == 0) {
            System.out.println("Underflow");
            return;
        }

        // Replace/Swap arr[0] with arr[lastIndex] - Why ? To maintain complete binary tree property.
        arr[0] = arr[size - 1];
        size--;

        // Bring '0'th index element to correct position -> heapify(0) == step-down
        heapifyRecursive(0);
    }

    int peek() {
        if(size == 0) {
            System.out.println("Underflow");
            return -1;
        }
        return arr[0];
    }

    int getSize() {
        return size;
    }

    void printHeap() {
        System.out.print("Heap: ");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}

public class Method1 {
    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap(10);

        // --- Insert ---
        System.out.println("=== Inserting: 10, 40, 20, 50, 30 ===");
        heap.insert(10);
        heap.insert(40);
        heap.insert(20);
        heap.insert(50);
        heap.insert(30);
        heap.printHeap();                            // Heap: 50 40 20 10 30
        System.out.println("Peek (max): " + heap.peek()); // 50

        // --- Delete (removes max) ---
        System.out.println("\n=== Deleting max ===");
        heap.delete();
        heap.printHeap();                            // Heap: 40 30 20 10
        System.out.println("Peek (max): " + heap.peek()); // 40

        heap.delete();
        heap.printHeap();                            // Heap: 30 10 20
        System.out.println("Peek (max): " + heap.peek()); // 30
    }
}