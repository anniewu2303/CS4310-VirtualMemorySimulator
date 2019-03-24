/**
 * @author Joshua Chen
 * Date Created: Mar 24, 2019
 */
public class PageFrame {
    private PageFrame next;
    private boolean refBit;
    private int physAddr;

    public PageFrame(int physAddr) {
        this.refBit = true;
        this.physAddr = physAddr;
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

    public void setPhysAddr(int physAddr) {
        this.physAddr = physAddr;
    }

    public PageFrame getNext() {
        return next;
    }

    public void setNext(PageFrame next) {
        this.next = next;
    }
}
