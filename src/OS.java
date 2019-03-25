import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author: Joshua Chen, Annie Wu
 * @date Mar 24, 2019
 * <p>
 * Assignment:  Project 2 - Virtual Memory Simulator
 * Class:       CS 4310 - Operating Systems
 * Instructor:  Dominick Atanasio
 * <p>
 * Operating System
 * <p>
 * Has "Access" to the Physical Memory (RAM).
 * Implements the Clock Page Replacement Algorithm.
 */

public class OS {
    private String pageFilesPath;
    private PhysicalMemory ram;
    private PageTable table;
    private CSV csv;

    public OS(String pageFilesPath) {
        this.pageFilesPath = pageFilesPath;
        this.ram = new PhysicalMemory();
        this.csv = new CSV();
    }

    // Clock Algorithm
    public int addEntry(String address, boolean isDirty, int data) throws IOException {
        int head = ram.getHead();

        // Clock is Full
        if (head == ram.getNumPageFrames()) {
            PageFrame pageToEvict = null;
            PageFrame headFrame = ram.getHeadFrame();

            while (pageToEvict == null) {

                // If R bit is 1, set to 0 and move head
                if (headFrame.isRef()) {
                    headFrame.setRefBit(false);
                }
                // If R bit is 0, evict page
                else {
                    pageToEvict = headFrame;
                }
                ram.moveHead();
            }
            evict(pageToEvict);
        }
        return ram.addEntry(address, isDirty, data);
    }

    //OS resets all ref bits in page table every 10 instructions

    public void resetAllRef() {
        for (int x=0; x<256; x++) {
            table.resetRefBit(x);
        }
    }

    public void setPageTable (PageTable pt) {
        this.table = pt;
    }

    private void evict(PageFrame pageFrameToEvict) throws IOException {
        String address = Integer.toHexString(pageFrameToEvict.getPhysAddr());
        String vpn = address.substring(0, 2);
        //int pageFrameNum = ram.getPageFrameNum(pageFrameToEvict);
        int offset = Integer.parseInt(address.substring(2, 4), 16);

        csv.evictedPageNumber(vpn);
        csv.dirty(pageFrameToEvict.isDirty());

        // Write to Disk if page frame is dirty
        if (pageFrameToEvict.isDirty()) {
            String pageFileName = pageFilesPath + "_working_set/" + vpn + ".pg";
            BufferedReader reader = new BufferedReader(new FileReader(pageFileName));

            // Copy Line or Write Value to File
            StringBuffer strBuffer = new StringBuffer();
            String line;
            int lineNum = 0;
            while ((line = reader.readLine()) != null) {
                // Replace Line with New Value
                if (lineNum == offset - 1) {
                    strBuffer.append(pageFrameToEvict.getData() + "\n");
                } else {
                    strBuffer.append(line + "\n");
                }
                lineNum += 1;
            }
            String inputStr = strBuffer.toString();
            reader.close();

            FileOutputStream writeToFile = new FileOutputStream(pageFileName);
            writeToFile.write(inputStr.getBytes());
            writeToFile.close();
        }

    }
}
