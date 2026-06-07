package a_17_Heaps.Therory_And_Implmentation;

import java.util.ArrayList;
import java.util.List;

/**
 * Max-Heap implementation backed by a dynamic ArrayList.
 * <p>
 * Core invariant: every parent >= its children (largest element at root).
 * <p>
 * Index formulas:
 * parent     = (i - 1) / 2
 * leftChild  = 2i + 1
 * rightChild = 2i + 2
 * <p>
 * Operations:
 * insert(key)          → add at end, heapify-UP      O(log N)
 * extractMax()         → swap root↔last, remove last, heapify-DOWN  O(log N)
 * changeKey(i, newVal) → update, then heapify-UP or DOWN depending on direction  O(log N)
 * getMax()             → peek root  O(1)
 * <p>
 * Contrast with MinHeap — only the comparison direction flips:
 * MinHeap: swap when child  < parent   (smaller bubbles up)
 * MaxHeap: swap when child  > parent   (larger  bubbles up)
 */
public class ImplementMaxHeap {

    /*
     * Mental Model:
     *
     *   heapifyUp(i):
     *       while i > 0 and arr[i] > arr[parent]  → swap, move up
     *
     *   heapifyDown(i):
     *       largest = i, check left, check right
     *       if largest != i → swap, move down
     *       else            → stop
     *
     *   insert    → add at end,           heapifyUp(size-1)
     *   extractMax→ swap root↔last, remove last, heapifyDown(0)
     *   changeKey → larger:  heapifyUp    smaller: heapifyDown
     */

    private final List<Integer> heap;
    private int size;

    // ─────────────────────────────────────────────────────────────
    // Constructor
    // ─────────────────────────────────────────────────────────────
    public ImplementMaxHeap() {
        heap = new ArrayList<>();
        size = 0;
    }

    // ─────────────────────────────────────────────────────────────
    // Insert: add at end → bubble UP
    // ─────────────────────────────────────────────────────────────
    public void insert(int key) {
        heap.add(key);      // place at end
        heapifyUp(size);    // restore heap property
        size++;
    }

    // ─────────────────────────────────────────────────────────────
    // Extract max: swap root↔last → remove last → bubble DOWN
    // ─────────────────────────────────────────────────────────────
    public int extractMax() {
        if (isEmpty()) throw new IllegalStateException("Heap is empty");

        int max = heap.get(0);

        swap(0, size - 1);      // move max to end
        heap.remove(size - 1);  // remove it
        size--;

        if (size > 0) heapifyDown(0); // restore heap property
        return max;
    }

    // ─────────────────────────────────────────────────────────────
    // Change key at index: heapify direction depends on new value
    // ─────────────────────────────────────────────────────────────
    public void changeKey(int index, int newVal) {
        int old = heap.get(index);
        heap.set(index, newVal);

        if (newVal > old) {
            heapifyUp(index);   // larger  → bubble up
        } else {
            heapifyDown(index); // smaller → bubble down
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Accessors
    // ─────────────────────────────────────────────────────────────
    public int getMax() {
        if (isEmpty()) throw new IllegalStateException("Heap is empty");
        return heap.get(0); // root = largest
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int heapSize() {
        return size;
    }

    public void clear() {
        heap.clear();
        size = 0;
    }

    // ─────────────────────────────────────────────────────────────
    // heapifyUp: bubble element at index up until parent is larger
    // ─────────────────────────────────────────────────────────────
    private void heapifyUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap.get(i) > heap.get(parent)) {   // ← flipped vs MinHeap
                swap(i, parent); // violation → bubble up
                i = parent;
            } else {
                break;           // heap property restored
            }
        }
    }

    // ─────────────────────────────────────────────────────────────
    // heapifyDown: bubble element at index down until both children are smaller
    // ─────────────────────────────────────────────────────────────
    private void heapifyDown(int i) {
        while (true) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < size && heap.get(left) > heap.get(largest)) largest = left;  // ← flipped
            if (right < size && heap.get(right) > heap.get(largest)) largest = right; // ← flipped

            if (largest != i) {
                swap(i, largest); // violation → bubble down
                i = largest;
            } else {
                break;            // heap property restored
            }
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Shared helper
    // ─────────────────────────────────────────────────────────────
    private void swap(int a, int b) {
        int temp = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b, temp);
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        ImplementMaxHeap h = new ImplementMaxHeap();

        // TC1: Insert and getMax
        h.insert(5);
        h.insert(3);
        h.insert(8);
        h.insert(1);
        h.insert(4);
        System.out.println("TC1 getMax  : " + h.getMax());      // 8
        System.out.println("TC1 size    : " + h.heapSize());    // 5

        // TC2: ExtractMax — should return elements in reverse-sorted order
        System.out.print("TC2 extract : ");
        while (!h.isEmpty()) System.out.print(h.extractMax() + " "); // 8 5 4 3 1
        System.out.println();

        // TC3: changeKey — increase (triggers heapifyUp)
        h.insert(10);
        h.insert(20);
        h.insert(30);
        System.out.println("TC3 before  : " + h.getMax());      // 30
        h.changeKey(2, 100);                                      // change 10 → 100
        System.out.println("TC3 after   : " + h.getMax());      // 100

        // TC4: changeKey — decrease (triggers heapifyDown)
        h.clear();
        h.insert(10);
        h.insert(5);
        h.insert(8);
        h.changeKey(0, 1);                                        // change root 10 → 1
        System.out.println("TC4 after   : " + h.getMax());      // 8

        // TC5: isEmpty on fresh heap
        ImplementMaxHeap empty = new ImplementMaxHeap();
        System.out.println("TC5 isEmpty : " + empty.isEmpty()); // true
    }
}

