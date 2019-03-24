import java.io.*;
import java.util.Scanner;

/**
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
    private String pageFilesPath;
    private PageTable pageTable;    // Virtual Memory
    private TLB tlb;
    private OS os;

    public MMU(String pageFilesPath) {
        this.pageTable = new PageTable();
        this.pageFilesPath = pageFilesPath;
        this.tlb = new TLB();
        this.os = new OS();
    }

    public void read(String address) throws IOException {
        String result = null;
        String vpn = address.substring(0, 2);
        int pageFrameNum = getFrameNumber(address, vpn, false);
        int offset = Integer.parseInt(address.substring(2, 4), 16);

        // Page to Read From
        String pageFileName = pageFilesPath + "_working_set/" + vpn + ".pg";
        Scanner page = new Scanner(new File(pageFileName));

        for (int i = 0; page.hasNextLine(); i++) {
            if (i == offset) {
                result = page.nextLine();
                break;
            }
            page.nextLine();
        }
        System.out.println(result);
    }

    public void write(String address, int newValue) throws IOException {
        String vpn = address.substring(0, 2);
        int pageFrameNum = getFrameNumber(address, vpn, true);
        int offset = Integer.parseInt(address.substring(2, 4), 16);

        // Page to Write To
        String pageFileName = pageFilesPath + "_working_set/" + vpn + ".pg";
        BufferedReader reader = new BufferedReader(new FileReader(pageFileName));

        // Copy Line or Write Value to File
        StringBuffer strBuffer = new StringBuffer();
        String line;
        int lineNum = 0;
        while ((line = reader.readLine()) != null) {
            // Replace Line with New Value
            if (lineNum == offset - 1) {
                strBuffer.append(newValue + "\n");
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

    private int getFrameNumber(String address, String vpn, boolean isDirty) {
        // Check TLB
        TlbEntry tlbEntry = tlb.getTLBEntry(vpn);

        // Hit
        if (tlbEntry != null) {
            System.out.println("Hit");
            tlbEntry.setRefBit(true);
            tlbEntry.setDirtyBit(isDirty);
            return tlbEntry.getPageFrameNum();
        }

        // Check Page Table
        PageTableEntry ptEntry = pageTable.getPageTableEntry(vpn);

        // Soft Miss
        if (ptEntry != null) {
            System.out.println("Soft Miss");
            ptEntry.setRefBit(true);
            ptEntry.setDirtyBit(isDirty);
            tlb.addEntry(vpn, ptEntry.getPageFrameNum(), isDirty);   // add to TLB (dirty)
            return ptEntry.getPageFrameNum();
        }

        // Hard Miss
        else {
            System.out.println("Hard Miss");
            int pageFrameNum = os.addEntry(address);
            pageTable.update(vpn, pageFrameNum, isDirty);
            tlb.addEntry(vpn, pageFrameNum, isDirty);               // add to TLB (dirty)
            return pageFrameNum;
        }
    }
}
