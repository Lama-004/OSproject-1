import java.util.*;

public class MemorySimulator {
public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);

// Memory initialization
System.out.print("Enter total number of blocks: ");
int m = scanner.nextInt();

List<Integer> sizes = new ArrayList<>();
System.out.print("Enter the size of each block in KB (space-separated): ");
for (int i = 0; i < m; i++) {
sizes.add(scanner.nextInt());
}

// Strategy selection
System.out.print("Enter allocation strategy (1 for First-Fit, 2 for Best-Fit, 3 for Worst-Fit): ");
int strategy = scanner.nextInt();

MemoryManager memory = new MemoryManager(sizes, strategy);
System.out.println("Memory blocks initialized...");

// Main menu loop
int choice = -1;
do {
try {
System.out.println("\n1) Allocate memory");
System.out.println("2) Deallocate memory");
System.out.println("3) Print memory report");
System.out.println("4) Exit");
System.out.print("Enter your choice: ");

choice = Integer.parseInt(scanner.nextLine().trim());

switch (choice) {
case 1:
System.out.print("Enter Process ID and size (in KB): ");
String pid = scanner.next();
int size = scanner.nextInt();
scanner.nextLine(); // consume leftover newline
memory.allocate(pid, size);
break;

case 2:
System.out.print("Enter Process ID to deallocate: ");
String dePid = scanner.nextLine();
memory.deallocate(dePid);
break;

case 3:

memory.printReport();
break;

case 4:
System.out.println("Exiting program...");
break;

default:
System.out.println("Invalid choice! Please enter 1, 2, 3, or 4.");
}

} catch (NumberFormatException e) {
System.out.println("Invalid input! Please enter a number between 1 and 4.");
choice = -1; // reset to keep loop going
} catch (InputMismatchException e) {
System.out.println("Mismatched input type. Please try again.");
scanner.nextLine(); // clear invalid input
choice = -1;
}
} 
while (choice != 4);

}
}
