import java.io.*;
import java.util.Scanner;

/**
 * @author: Joshua Chen, Annie Wu
 * @date Mar 24, 2019
 * <p>
 * Assignment:  Project 2 - Virtual Memory Simulator
 * Class:       CS 4310 - Operating Systems
 * Instructor:  Dominick Atanasio
 * <p>
 * MMU is told (by the CPU) whether to Read From or Write To an Address.
 * <p>
 * If Reading:
 * - MMU sets the r-bit.
 * <p>
 * If Writing:
 * - MMU sets the r-bit.
 * - MMU sets the d-bit.
 * <p>
 * MMU Updates the Page Table with the Current Record(s) in the TLB when:
 * - Before the MMU evicts a record in the TLB.
 * - The CPU traps the OS on a hard miss.
 * - The CPU interrupts for a clock interrupt.
 * <p>
 * If MMU is Evicting a Record in TLB:
 * - Only update that one record in the page table.
 * Otherwise:
 * - Update all records in the page table with those in the TLB.
 * <p>
 * When Encountering a Miss:
 * If TLB is full, then overwrite a record in TLB
 */

public class MMU {
    private String pageFilesPath;
    private PageTable pageTable;    // Virtual Memory
    private OS os;
    private TLB tlb;
    private CSV csv;

    public MMU(OS os, String pageFilesPath) {
        this.pageTable = new PageTable();
        this.pageFilesPath = pageFilesPath;
        this.os = os;
        this.os.setPageTable(this.pageTable);
        this.tlb = new TLB();
        this.csv = new CSV();
    }

    /**
     * Accepts an address.
     * Reads the value at the address.
     *
     * @param address
     * @throws IOException
     */
    public void read(String address) throws IOException {
        String value = null; //value to read
        String vpn = address.substring(0, 2);
        int offset = Integer.parseInt(address.substring(2, 4), 16);

        // Page to Read From
        String pageFileName = pageFilesPath + "_working_set/" + vpn + ".pg";
        Scanner page = new Scanner(new File(pageFileName));

        for (int i = 0; page.hasNextLine(); i++) {
            if (i == offset) {
                value = page.nextLine();
                break;
            }
            page.nextLine();
        }
        System.out.println(value);

        int pageFrameNum = getFrameNumber(address, vpn, Integer.parseInt(value), false);
        csv.value(Integer.parseInt(value));
    }

    /**
     * Accepts an Address and a New Value.
     * Replaces the value at the address with the newValue.
     *
     * @param address
     * @param newValue
     * @throws IOException
     */
    public void write(String address, int newValue) throws IOException {
        csv.value(newValue);

        String vpn = address.substring(0, 2);
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

        int pageFrameNum = getFrameNumber(address, vpn, newValue, true);
    }

    /**
     * Sets R bit on Read or Write,
     * Sets D bit on Write
     *
     * @param address
     * @param vpn
     * @param isDirty
     * @return
     */
    private int getFrameNumber(String address, String vpn, int data, boolean isDirty) throws IOException {
        // Check TLB
        TlbEntry tlbEntry = tlb.getTLBEntry(vpn);

        // Hit
        if (tlbEntry != null) {
            System.out.println("Hit");
            tlbEntry.setRefBit(true);
            tlbEntry.setDirtyBit(isDirty);

            csv.hit(true);
            csv.soft(false);
            csv.hard(false);
            csv.dirty(isDirty);

            return tlbEntry.getPageFrameNum();
        } else {
            // Check Page Table
            PageTableEntry ptEntry = pageTable.getPageTableEntry(vpn);

            // Soft Miss
            if (ptEntry != null) {
                System.out.println("Soft Miss");
                ptEntry.setRefBit(true);
                ptEntry.setDirtyBit(isDirty);
                tlb.addEntry(vpn, ptEntry.getPageFrameNum(), isDirty);   // add to TLB (dirty)

                csv.soft(true);
                csv.hard(false);
                csv.hit(false);
                csv.evictedPageNumber("N/A");         //no evicted page
                csv.dirty(isDirty);

                return ptEntry.getPageFrameNum();
            }

            // Hard Miss
            else {
                System.out.println("Hard Miss");
                int pageFrameNum = os.addEntry(address, isDirty, data);
                pageTable.update(vpn, pageFrameNum, isDirty);
                tlb.addEntry(vpn, pageFrameNum, isDirty);               // add to TLB (dirty)

                csv.hard(true);
                csv.soft(false);
                csv.hit(false);
                csv.evictedPageNumber(vpn);
                csv.dirty(isDirty);

                return pageFrameNum;
            }
        }
    }
}
