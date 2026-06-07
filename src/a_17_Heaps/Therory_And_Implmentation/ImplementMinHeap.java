package a_17_Heaps.Therory_And_Implmentation;

import java.util.ArrayList;
import java.util.List;

/**
 * Min-Heap implementation backed by a dynamic ArrayList.
 * <p>
 * Core invariant: every parent <= its children (smallest element at root).
 * <p>
 * Index formulas:
 * parent    = (i - 1) / 2
 * leftChild = 2i + 1
 * rightChild= 2i + 2
 * <p>
 * Operations:
 * insert(key)          → add at end, heapify-UP      O(log N)
 * extractMin()         → swap root↔last, remove last, heapify-DOWN  O(log N)
 * changeKey(i, newVal) → update, then heapify-UP or DOWN depending on direction  O(log N)
 * getMin()             → peek root  O(1)
 */
public class ImplementMinHeap {

    /*
     * Mental Model:
     *
     *   heapifyUp(i):
     *       while i > 0 and arr[i] < arr[parent]  → swap, move up
     *
     *   heapifyDown(i):
     *       smallest = i, check left, check right
     *       if smallest != i → swap, move down
     *       else             → stop
     *
     *   insert    → add at end,          heapifyUp(size-1)
     *   extractMin→ swap root↔last, remove last, heapifyDown(0)
     *   changeKey → smaller: heapifyUp   larger: heapifyDown
     */

    private final List<Integer> heap;
    private int size;

    // ─────────────────────────────────────────────────────────────
    // Constructor
    // ─────────────────────────────────────────────────────────────
    public ImplementMinHeap() {
        heap = new ArrayList<>();
        size = 0;
    }

    // ─────────────────────────────────────────────────────────────
    // Insert: add at end → bubble UP
    // ─────────────────────────────────────────────────────────────
    public void insert(int key) {
        heap.add(key);          // place at end
        heapifyUp(size);        // restore heap property
        size++;
    }

    // ─────────────────────────────────────────────────────────────
    // Extract min: swap root↔last → remove last → bubble DOWN
    // ─────────────────────────────────────────────────────────────
    public int extractMin() {
        if (isEmpty()) throw new IllegalStateException("Heap is empty");

        int min = heap.get(0);

        swap(0, size - 1);      // move min to end
        heap.remove(size - 1);  // remove it
        size--;

        if (size > 0) heapifyDown(0); // restore heap property
        return min;
    }

    // ─────────────────────────────────────────────────────────────
    // Change key at index: heapify direction depends on new value
    // ─────────────────────────────────────────────────────────────
    public void changeKey(int index, int newVal) {
        int old = heap.get(index);
        heap.set(index, newVal);

        if (newVal < old) {
            heapifyUp(index);   // smaller → bubble up
        } else {
            heapifyDown(index); // larger  → bubble down
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Accessors
    // ─────────────────────────────────────────────────────────────
    public int getMin() {
        if (isEmpty()) throw new IllegalStateException("Heap is empty");
        return heap.get(0); // root = smallest
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
    // heapifyUp: bubble element at index up until parent is smaller
    // ─────────────────────────────────────────────────────────────
    private void heapifyUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap.get(i) < heap.get(parent)) {
                swap(i, parent); // violation → bubble up
                i = parent;
            } else {
                break;           // heap property restored
            }
        }
    }

    // ─────────────────────────────────────────────────────────────
    // heapifyDown: bubble element at index down until both children are larger
    // ─────────────────────────────────────────────────────────────
    private void heapifyDown(int i) {
        while (true) {
            int smallest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < size && heap.get(left) < heap.get(smallest)) smallest = left;
            if (right < size && heap.get(right) < heap.get(smallest)) smallest = right;

            if (smallest != i) {
                swap(i, smallest); // violation → bubble down
                i = smallest;
            } else {
                break;             // heap property restored
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
        ImplementMinHeap h = new ImplementMinHeap();

        // TC1: Insert and getMin
        h.insert(5);
        h.insert(3);
        h.insert(8);
        h.insert(1);
        h.insert(4);
        System.out.println("TC1 getMin  : " + h.getMin());      // 1
        System.out.println("TC1 size    : " + h.heapSize());    // 5

        // TC2: ExtractMin — should return elements in sorted order
        System.out.print("TC2 extract : ");
        while (!h.isEmpty()) System.out.print(h.extractMin() + " "); // 1 3 4 5 8
        System.out.println();

        // TC3: changeKey — decrease (triggers heapifyUp)
        h.insert(10);
        h.insert(20);
        h.insert(30);
        System.out.println("TC3 before  : " + h.getMin());      // 10
        h.changeKey(2, 1);                                        // change 30 → 1
        System.out.println("TC3 after   : " + h.getMin());      // 1

        // TC4: changeKey — increase (triggers heapifyDown)
        h.clear();
        h.insert(1);
        h.insert(5);
        h.insert(3);
        h.changeKey(0, 100);                                      // change root 1 → 100
        System.out.println("TC4 after   : " + h.getMin());      // 3

        // TC5: isEmpty on fresh heap
        ImplementMinHeap empty = new ImplementMinHeap();
        System.out.println("TC5 isEmpty : " + empty.isEmpty()); // true
    }
}
