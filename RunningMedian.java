import java.util.*;

public class RunningMedian {
    // Max heap for the lower half
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    // Min heap for the upper half
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public void addNumber(int num) {
        // Step 1: Add to appropriate heap
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }

        // Step 2: Balance the heaps
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return maxHeap.peek(); // maxHeap is guaranteed to have 1 extra element if odd count
    }

    public void printHeaps() {
        System.out.println("Max Heap (Left): " + new ArrayList<>(maxHeap));
        System.out.println("Min Heap (Right): " + new ArrayList<>(minHeap));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RunningMedian rm = new RunningMedian();

        while (true) {
            System.out.print("Enter number (or 'exit'): ");
            String input = sc.next();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }

            try {
                int num = Integer.parseInt(input);
                rm.addNumber(num);
                rm.printHeaps();
                System.out.println("Current Median: " + rm.getMedian());
                System.out.println(); // newline for readability
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number or 'exit'.");
            }
        }
        sc.close();
    }
}
