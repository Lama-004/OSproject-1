
import java.util.*;
class MemoryManager {
List<MemoryBlock> blocks;
int allocationStrategy; // 1: First-Fit, 2: Best-Fit, 3: Worst-Fit

public MemoryManager(List<Integer> sizes, int strategy) {
this.blocks = new ArrayList<>();
this.allocationStrategy = strategy;
int address = 0;
for (int i = 0; i < sizes.size(); i++) {
 int size = sizes.get(i);
 blocks.add(new MemoryBlock(address, size));
 address += size;
}
System.out.println("Memory blocks initialized...");
System.out.println("==========================================================");
System.out.println("Block# | Size | Start-End | Status |");
System.out.println("==========================================================");
int index = 0;
for (MemoryBlock block : blocks) {
System.out.printf("Block%-3d | %-4d | %5d-%-5d | %-9s ", index, block.blockSize, block.startAddress, block.endAddress ,block.isAllocated ? "Allocated" : "Free" ); // regluar if else 
}
System.out.println("==========================================================");
}


// Allocation function
public void allocate(String pid, int size) {
MemoryBlock selectedBlock = null;

// First-Fit strategy
if (allocationStrategy == 1) {
for (MemoryBlock block : blocks) {
if (!block.isAllocated && block.blockSize >= size) {
selectedBlock = block;
break;
}
}
}

// Best-Fit strategy
else if (allocationStrategy == 2) {
int minDiff = Integer.MAX_VALUE; // for each loop  
for (MemoryBlock block : blocks) {
if (!block.isAllocated && block.blockSize >= size) {
int diff = block.blockSize - size;
if (diff < minDiff) {
minDiff = diff;
selectedBlock = block;
}
}
}
}

// Worst-Fit strategy
else if (allocationStrategy == 3) {
int maxDiff = -1;
for (MemoryBlock block : blocks) {
if (!block.isAllocated && block.blockSize >= size) {
int diff = block.blockSize - size;
if (diff > maxDiff) {
maxDiff = diff;
selectedBlock = block;
}
}
}
}

if (selectedBlock != null) {
selectedBlock.isAllocated = true;
selectedBlock.processID = pid;
selectedBlock.internalFragmentation = selectedBlock.blockSize - size; //
System.out.println(pid + " allocated at address " + selectedBlock.startAddress
+ ", internal fragmentation: " + selectedBlock.internalFragmentation + "KB");
} else {
System.out.println("ERROR: Cannot allocate memory to " + pid + ". Not enough space.");
}
}

// Deallocation
public void deallocate(String pid) {
boolean found = false;
for (MemoryBlock block : blocks) {
if (block.isAllocated && block.processID.equals(pid)) {
block.isAllocated = false;
block.processID = "Null";
block.internalFragmentation = 0;
found = true;
System.out.println("Process " + pid + " deallocated.");
break;
}
}
if (!found) {
System.out.println("ERROR: Process ID not found.");
}
}

// Print status report
public void printReport() {
System.out.println("==========================================================");
System.out.println("Block# | Size | Start-End | Status | Process | Fragmentation");
System.out.println("==========================================================");
int index = 0;
for (MemoryBlock block : blocks) {
System.out.printf("Block%-3d | %-4d | %5d-%-5d | %-9s | %-8s | %dKB\n",
index, block.blockSize, block.startAddress, block.endAddress,
block.isAllocated ? "Allocated" : "Free",  // regluar if else 
block.processID, block.internalFragmentation);
index++;
}
System.out.println("==========================================================");
}
}
