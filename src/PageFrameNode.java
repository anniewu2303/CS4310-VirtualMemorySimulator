/**
 * @author Joshua Chen
 * Date Created: Mar 24, 2019
 */
public class PageFrameNode {
    private PageFrameNode next;
    private boolean refBit;
    private int physAddr;

    public PageFrameNode(int physAddr) {
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

    public PageFrameNode getNext() {
        return next;
    }

    public void setNext(PageFrameNode next) {
        this.next = next;
    }
}
