import java.util.*;
class MemoryBlock {
int blockSize;
int startAddress;
int endAddress; 
String status ;
boolean isAllocated;
String processID;
int internalFragmentation;

public MemoryBlock(int blockSize, int startAddress) {   //Constretor 
this.blockSize = blockSize;
this.startAddress = startAddress;
this.endAddress = startAddress + blockSize - 1;
isAllocated = false;
processID = "Null"; //set to null 
internalFragmentation = 0;
}
}

