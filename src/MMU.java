
/*
 Author:        Joshua Chen
                Annie Wu 
				
 Assignment:    Program 2 
 Class:         CS 4310 - Operating Systems 
 Instructor:    Dominick Atanasio 
 Date:          24 March 2019 


- Your CPU will read the test file VM addresses and hand them to the MMU for fetching or writing. 
  - If the address is preceded by a zero, then the MMU should only read value. 
  - If the address is preceded by a one, then the address will be followed by an integer value 
  that needs to be written to physical memory. 
- In the latter case, the page containing the data must have it’s dirty bit set by the MMU in both the TLB and the page table. 
- This page must be written back to disk if your page replacement algorithm decides to replace the file.
- The MMU should set the r-bit on a read or write of data in the TLB. 
- The MMU should set the dirty bit on a write.
- Before the MMU evicts a record in the TLB, the CPU traps to the OS on a Hard Miss, or the CPU
interrupts for a clock interrupt, the MMU should update the page table with the current records(s) in the TLB.
- If the MMU is evicting a record in the TLB it will only need to update that one record in the page table, 
otherwise, it should update all records in the page table with those in the TLB. 
- This further reduces the amount of reads and writes on the
page table by the MMU.
- When the MMU encounters a soft or hard miss, if the TLB is full it will need to overwrite a record in the TLB with the
entry of the page being called for after the CPU traps to the OS. You can use the FIFO replacement algorithm for this.

*/

public class MMU {

    /*
    Read value from physical memory
    */
    public int readValue(String thisAddress) {
        return 0;
    }

    /*
    Write this value to physical memory
    */
    public void writeValue(String thisAddress, int thisValue) {

    }

}
