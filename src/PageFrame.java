/**
 * @author Joshua Chen
 * Date Created: Mar 24, 2019
 * <p>
 * Represents Individual Page Frames for Physical Memory.
 */
public class PageFrame {
    private boolean refBit, dirtyBit;
    private int physAddr;
    private int data;

    public PageFrame(boolean isDirty, int physAddr, int data) {
        this.refBit = true;
        this.dirtyBit = isDirty;
        this.physAddr = physAddr;
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public boolean isRef() {
        return refBit;
    }

    public void setRefBit(boolean refBit) {
        this.refBit = refBit;
    }

    public int getPhysAddr() {
        return physAddr;
    }

    public boolean isRefBit() {
        return refBit;
    }

    public boolean isDirty() {
        return dirtyBit;
    }

    public void setDirtyBit(boolean dirtyBit) {
        this.dirtyBit = dirtyBit;
    }

    public void setPhysAddr(int physAddr) {
        this.physAddr = physAddr;
    }
}
