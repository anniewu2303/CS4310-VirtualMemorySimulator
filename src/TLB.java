/*
 Author:        Joshua Chen
                Annie Wu 
				
 Assignment:    Program 2 
 Class:         CS 4310 - Operating Systems 
 Instructor:    Dominick Atanasio 
 Date:          24 March 2019 
 
 
The TLB will be a one-dimensional array if TlbEntries. 
A TlbEntry consists of a virtual page number and a frame number. 
The TLB is small and must be scanned on every lookup. 
The arrays used to implement the page table and TLB will be arrays of data structures that represent the tables’ entries.

Table entry for the TLB:
    | V-Page# | V | R | D | PageFrame# |
*/

public class TLB {
    private TlbEntries entries[];
    private final static int DEFAULT_MAX_ENTRIES = 16;

    public TLB() {
        this(DEFAULT_MAX_ENTRIES);
    }

    public TLB(int max_entries) {
        this.entries = new TlbEntries[max_entries];
    }
    
    /*
    Initialize TLB
    */
    public void start() {

    }
    
    /*
    Check if page number is in the TLB. 
	*/
    public int checkPageNumber(int pageNumber) {

    }

    /*
    Update this page number in TLB with this frame number
    */
    public void update(int pageNumber, int frameNumber) {

    }

    /*
    Update dirty bit
    */
    public void updateDirtyBit(int pageNumber) {

    }
}
