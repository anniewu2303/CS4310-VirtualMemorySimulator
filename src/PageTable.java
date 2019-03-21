/*
 Author:        Joshua Chen
                Annie Wu 
				
 Assignment:    Program 2 
 Class:         CS 4310 - Operating Systems 
 Instructor:    Dominick Atanasio 
 Date:          24 March 2019 



- The page table will be an array of PageTableEntry (so the page table will be a one-dimensional array).

- You will use the virtual page number (V-Page#) as the index to the elements of this array.

Table entry for the virtual page table:
	| V | R | D | PageFrame# |
*/

public class VirtualPageTable {

    private PageTableEntry[] table;
    private int index = 0; //index in page table 
    
    /*
    Initialize array of page table entries
    */
    public PageTable() {
        table = new PageTableEntry[   ]; 
        for (int x=0; x<   ; x++) {
            table[x] = new PageTableEntry(false, false, false, -1); //V, R, D, frame#
        }
    }

	/*
	Check if page number is in the table. 
		If it is, get the frame number.
		Else, the frame number does not exist (-1)
	*/
	public int checkPageNumber(int pageNumber) {
		int frameNumber = -1;
        
		//if the page number is valid in the page table, get the frame number 
		if (table[pageNumber].getValid() == 1) {
			frameNumber = table[pageNumber].getFrame();
        }
        return frameNumber;
    }

    /*
    Update an entry at this page number with this frame number
    */
    public void update(int pageNumber, int frameNumber) {
        table[pageNumber].setFrame(frameNumber);    
        table[pageNumber].setValid(true);          //valid
        table[pageNumber].setR(true);              //just referneced
        table[pageNumber].setDirty(false);         //not written to
    }

    /*
    Get dirty bit from table at this page number
    */
    public boolean getDirtyBit(int pageNumber) {
        return table[pageNumber].getDirtyBit();
    }
    
    /*
    Set dirty bit to 1 at this page number
    */
    public void setDirtyBit(int pageNumber) {
        table[pageNumber].setDirtyBit(1);
    }
    
    /*
    Get reference bit from the table at this page number
    */
    public boolean getStartingRef(int pageNumber) {
        return table[pageNumber].getRefBit();
    }

    /*
    Set reference bit to 1 at this page number
    */
    public void setRefBit(int pageNumber) {
        table[pageNumber].setRefBit(1);
    }
    
    /*
    Reset reference bit to 0 at this page number
    */
    public void resetRefBit(int pageNumber) {
        table[pageNumber].setRefBit(0);
    }
	
    /*
    Get index in page table
    */
    public int getTableIndex() {
        return index;
    }
}
