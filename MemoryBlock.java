class MemoryBlock {
int blockSize;
int startAddress;
int endAddress; 
String status;
boolean Allocated;
String processID;
int internalFragmentation;

public MemoryBlock(int Size, int start) { 
blockSize = Size;
startAddress = start;
endAddress = startAddress + blockSize - 1;
Allocated = false;
processID = "Null";
internalFragmentation = 0;
}

}
