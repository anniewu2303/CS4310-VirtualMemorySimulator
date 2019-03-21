/**
 * @author Joshua Chen
 * Date Created: Mar 21, 2019
 */
public class PhysicalMemory {
    private int[][] ram;

    public PhysicalMemory(int numPageFrames, int pageSize) {
        this.ram = new int[numPageFrames][pageSize];
    }
}
