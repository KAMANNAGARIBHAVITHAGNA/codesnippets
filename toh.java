import java.util.Stack;
import java.util.Scanner;

public class TowerOfHanoi {
    private static class Rod {
        String name;
        Stack<Integer> disks;

        Rod(String name) {
            this.name = name;
            this.disks = new Stack<>();
        }

        void addDisk(int disk) {
            if (!disks.isEmpty() && disks.peek() <= disk) {
                throw new IllegalStateException("Cannot place larger disk on smaller disk.");
            }
            disks.push(disk);
        }

        int removeDisk() {
            if (disks.isEmpty()) {
                throw new IllegalStateException("No disks to remove.");
            }
            return disks.pop();
        }

        @Override
        public String toString() {
            return name + " " + disks.toString();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of disks: ");
        int numDisks = scanner.nextInt();

        Rod source = new Rod("Source");
        Rod target = new Rod("Target");
        Rod auxiliary = new Rod("Auxiliary");

        // Initialize source rod with disks
        for (int i = numDisks; i >= 1; i--) {
            source.addDisk(i);
        }

        System.out.println("Initial state:");
        printRods(source, target, auxiliary);

        solveHanoi(numDisks, source, target, auxiliary);

        System.out.println("Final state:");
        printRods(source, target, auxiliary);

        scanner.close();
    }

    private static void solveHanoi(int n, Rod source, Rod target, Rod auxiliary) {
        if (n == 1) {
            moveDisk(source, target);
            return;
        }

        // Move n-1 disks from source to auxiliary
        solveHanoi(n - 1, source, auxiliary, target);

        // Move the nth disk from source to target
        moveDisk(source, target);

        // Move the n-1 disks from auxiliary to target
        solveHanoi(n - 1, auxiliary, target, source);
    }

    private static void moveDisk(Rod source, Rod target) {
        int disk = source.removeDisk();
        target.addDisk(disk);
        System.out.println("Move disk " + disk + " from " + source.name + " to " + target.name);
    }

    private static void printRods(Rod source, Rod target, Rod auxiliary) {
        System.out.println(source);
        System.out.println(target);
        System.out.println(auxiliary);
        System.out.println();
    }
}
