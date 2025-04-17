import java.util.*;

public class MemorySimulator {
public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);

// Memory initialization
System.out.print("Enter the total number of blocks: ");
int num = scanner.nextInt();
int[] sizes = new int[num];

System.out.print("Enter the size of each block in KB:");
for (int i = 0; i < num; i++) 
sizes[i] = scanner.nextInt();


// Strategy Selection
System.out.print("Enter allocation strategy (1 for first-fit, 2 for best-fit, 3 for worst-fit): ");
int strategy = scanner.nextInt();


// Memory Manager Initialization
MemoryManager MM = new MemoryManager(sizes, strategy);


// Main Menu 
int choice = 0;

do {
try {
System.out.println("\n1) Allocates memory blocks ");
System.out.println("2) De-allocates memory blocks");
System.out.println("3) Print report about the current state of memory and internal Fragmentation");
System.out.println("4) Exit");
System.out.println("=========================================================================");

System.out.print("Enter your choice: ");
choice = scanner.nextInt();

switch (choice) {
case 1:
System.out.print("Enter the process ID and size of process: ");
String pid = scanner.next();
int size = scanner.nextInt();
MM.allocate(pid, size);
break;

case 2:
System.out.print("Enter the process ID: ");
String dPid = scanner.next();
MM.deallocate(dPid);
break;

case 3:
MM.printReport();
break;

case 4:
System.out.println("Exiting program...");
break;

default:
System.out.println("Invalid input! Please enter a number between 1-4");

}//End Switch
}//End Try


catch (InputMismatchException e) {
System.out.println("Invalid input!, Please enter a number between 1-4");
scanner.nextLine();
choice = 0;
}

} while (choice != 4);

}
}
