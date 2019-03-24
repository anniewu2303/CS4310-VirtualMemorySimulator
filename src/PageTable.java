/**
 Author:        Joshua Chen
                Annie Wu 
				
 Assignment:    Program 2 
 Class:         CS 4310 - Operating Systems 
 Instructor:    Dominick Atanasio 
 Date:          24 March 2019 



- The page pageTableEntries will be an array of PageTableEntry (so the page pageTableEntries will be a one-dimensional array).

- You will use the virtual page number (V-Page#) as the currEntry to the elements of this array.

Table entry for the virtual page pageTableEntries:
	| V | R | D | PageFrame# |
*/

public class PageTable {

    private PageTableEntry[] pageTableEntries;
    private int currEntry = 0; //currEntry in page pageTableEntries
    private static int DEFAULT_MAX_ENTRIES = 256;

    public PageTable() {
        this(DEFAULT_MAX_ENTRIES);
    }

    /*
    Initialize array of page pageTableEntries entries
    */
    public PageTable(int maxEntries) {
        pageTableEntries = new PageTableEntry[maxEntries];
    }

    /*
    Check if page number is in the pageTableEntries.
        If it is, get the frame number.
        Else, the frame number does not exist (-1)
    */
    public int checkPageNumber(int pageNumber) {
        int frameNumber = -1;

        //if the page number is valid in the page pageTableEntries, get the frame number
        if (pageTableEntries[pageNumber].isValid()) {
            frameNumber = pageTableEntries[pageNumber].getPageFrameNum();
        }
        return frameNumber;
    }

    /*
    Update an entry at this page number with this frame number
    */
    public void update(int pageNumber, int frameNumber) {
        pageTableEntries[pageNumber].setPageFrameNum(frameNumber);
        pageTableEntries[pageNumber].setValidBit(true);          //valid
        pageTableEntries[pageNumber].setRefBit(true);              //just referneced
        pageTableEntries[pageNumber].setDirtyBit(false);         //not written to
    }

    /*
    Get dirty bit from pageTableEntries at this page number
    */
    public boolean getDirtyBit(int pageNumber) {
        return pageTableEntries[pageNumber].isDirty();
    }

    /*
    Set dirty bit to 1 at this page number
    */
    public void setDirtyBit(int pageNumber) {
        pageTableEntries[pageNumber].setDirtyBit(true);
    }

    /*
    Get reference bit from the pageTableEntries at this page number
    */
    public boolean getStartingRef(int pageNumber) {
        return pageTableEntries[pageNumber].isRef();
    }

    /*
    Set reference bit to 1 at this page number
    */
    public void setRefBit(int pageNumber) {
        pageTableEntries[pageNumber].setRefBit(true);
    }

    /*
    Reset reference bit to 0 at this page number
    */
    public void resetRefBit(int pageNumber) {
        pageTableEntries[pageNumber].setRefBit(false);
    }

    /*
    Get currEntry in page pageTableEntries
    */
    public int getTableIndex() {
        return currEntry;
    }
}
