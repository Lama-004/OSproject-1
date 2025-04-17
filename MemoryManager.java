import java.util.*;

class MemoryManager {

   List<MemoryBlock> blocks; //List of all blocks
   int allocationStrategy; //1:first-fit, 2:best-fit, 3:worst-fit

   public MemoryManager(int[] sizes, int strategy) {
      blocks = new ArrayList<>();
      allocationStrategy = strategy;
      int address = 0;
   
   // blocks(List) Initialization
      for (int i = 0; i < sizes.length; i++) {
         int size = sizes[i];
         MemoryBlock m = new MemoryBlock(size, address);
         blocks.add(m); 
         address += size;
      }
   
   // Initial Report Printing
      System.out.println("Memory blocks are created...");
      System.out.println("Memory blocks:");
      System.out.println("=======================================================================");
      System.out.printf("%-10s %-10s %-15s %-10s\n", "Block#", "Size", "Start-End", "Status");
      System.out.println("=======================================================================");
   
      int blockNum = 0;
      for (MemoryBlock block : blocks) {
         if (block.Allocated)
            System.out.printf("Block%-6d %-10d %-15s %-10s\n", blockNum, block.blockSize, block.startAddress + "-" + block.endAddress, "allocated");
         else
            System.out.printf("Block%-6d %-10d %-15s %-10s\n", blockNum, block.blockSize, block.startAddress + "-" + block.endAddress, "free");
         blockNum++;
      }
   
      System.out.println("=======================================================================");
   }

   public void allocate(String pid, int size) {
   
      MemoryBlock selectedBlock = null;
      for (MemoryBlock block : blocks) { 
         if (!block.Allocated && block.blockSize >= size) {
         
         // First-Fit
            if (allocationStrategy == 1) { 
               selectedBlock = block;
               break;
            }
            
            // Best-Fit      
            else if (allocationStrategy == 2) {
               if (selectedBlock == null || block.blockSize < selectedBlock.blockSize) 
                  selectedBlock = block;
            }  
            
            // Worst-Fit       
            else if (allocationStrategy == 3) { 
               if (selectedBlock == null || block.blockSize > selectedBlock.blockSize) 
                  selectedBlock = block;
            }
         
         } //End If
      } //End for
   
   
   
      if (selectedBlock != null) {
         selectedBlock.Allocated = true;
         selectedBlock.processID = pid;
         selectedBlock.internalFragmentation = selectedBlock.blockSize - size;
         System.out.println(pid + "  Allocated at address " + selectedBlock.startAddress + ", and the internal fragmentation is " + selectedBlock.internalFragmentation);
         System.out.print("===========================================================================");
      
      } else {
         System.out.println("Memory cannot be allocated");
      }
   }



   public void deallocate(String pid) {
      boolean found = false;
      for (MemoryBlock block : blocks) { 
         if (block.Allocated && block.processID.equals(pid)) {
            block.Allocated = false;
            block.processID = "Null";
            block.internalFragmentation = 0;
            found = true;
            System.out.println("Process " + pid + " deallocated");
            break;
         }
      }
   
      if (!found) 
         System.out.println("Process not found");
   
   }


   public void printReport() {
      System.out.println("Memory blocks:");
      System.out.println("==============================================================================");
      System.out.printf("%-10s %-10s %-15s %-10s %-12s %-20s\n","Block#", "size", "start-end", "status", "ProcessID", "InternalFragmentation");
      System.out.println("==============================================================================");
   
      int blockNum = 0;
      for (MemoryBlock block : blocks) {
         if(block.Allocated)
            System.out.printf("Block%-6d %-10d %-15s %-10s %-12s %-20d\n", blockNum, block.blockSize, block.startAddress + "-" + block.endAddress,"allocated", block.processID, block.internalFragmentation);
         else
            System.out.printf("Block%-6d %-10d %-15s %-10s %-12s %-20d\n", blockNum, block.blockSize, block.startAddress + "-" + block.endAddress,"free", block.processID, block.internalFragmentation);
         blockNum++;
      }
   
      System.out.println("==============================================================================");
   }
}
