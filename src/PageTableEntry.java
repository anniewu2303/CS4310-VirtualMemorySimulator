/*
 Author:        Joshua Chen
                Annie Wu 
				
 Assignment:    Program 2 
 Class:         CS 4310 - Operating Systems 
 Instructor:    Dominick Atanasio 
 Date:          24 March 2019 


Table entry for the virtual page table:
	| V | R | D | PageFrame# |
*/

public class PageTableEntry {
    private int frame;
    private boolean valid, ref, dirty;

    public PageTableEntry(boolean valid, boolean ref, boolean dirty, int frame) {
        this.valid = valid;
        this.ref = ref;
        this.dirty = dirty;
        this.frame = frame;
    }
    
    public boolean getValidBit() {
        return valid;
    }
    
    publicvoid setValidBit(boolean v) {
        valid = v;
    }
    
    public boolean getRefBit() {
        return ref;
    }
    
    public void setRefBit(boolean r) {
        ref = r;
    }
    
    public boolean getDirtyBit() {
        return dirty;
    }

    public void setDirtyBit (boolean d) {
        dirty = d;
    }

    public int getFrame_Num() {
        return frame;
    }

    public void setFrame_Num(int f) {
        frame = f;
    }
       
}
