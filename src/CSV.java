/**
 * @author: Joshua Chen, Annie Wu
 * @date Mar 24, 2019
 * <p>
 * Assignment:  Project 2 - Virtual Memory Simulator
 * Class:       CS 4310 - Operating Systems
 * Instructor:  Dominick Atanasio
 * <p>
 * CSV Output File Contains:
 * - Address
 * - Read or Write (0 or 1)
 * - Value that is Read or Written
 * - Soft miss (0 = false, 1 = true)
 * - Hard miss (0 = false, 1 = true)
 * - Hit (0 = false, 1 = true)
 * - Page Number of the Evicted Page
 * - Dirty Bit of the Page Number
 */

import java.io.*;

public class CSV {

    private String address, rw, value, soft, hard, hit, evictedPageNum, dirty;
    private PrintWriter csv;

    /*
        + "" turns the parameter into a string for writing to output file
    */

    /**
     * Initialize test_#.csv File
     *
     * @param outputFile
     * @throws FileNotFoundException
     */
    public CSV (String outputFile) throws FileNotFoundException {
        File file = new File(outputFile);
        FileOutputStream output = new FileOutputStream(file);
        csv = new PrintWriter(output);
        header();
    }

    public void address(String a) {
        address = a;
        output();
    }

    /**
     * 0 = read
     * 1 = write
     */
    public void rw(int r) {
        rw = r + "";
        output();
    }

    public void value(int v) {
        value = v + "";
        output();
    }

    /**
     * 0 = false
     * 1 = true
     */
    public void soft() {
        soft = "1";
        hard = "0";
        hit = "0";
        output();
    }

    /**
     * 0 = false
     * 1 = true
     */
    public void hard() {
        soft = "0";
        hard = "1";
        hit = "0";
        output();
    }

    /**
     * 0 = false
     * 1 = true
     */
    public void hit() {
        soft = "0";
        hard = "0";
        hit = "1";
        output();
    }

    /**
     * Output page number that is evicted or N/A if none
     *
     * @param evicted
     */
    public void evictedPageNumber(String evicted) {
        evictedPageNum = evicted;
        output();
    }

    /**
     * 0 = false
     * 1 = true
     */
    public void dirty(boolean isDirty) {
        if (!isDirty) {
            dirty = "0";
        } else {
            dirty = "1";
        }
        output();
    }

    public void header() {
        csv.println("Address, r/w, value, soft, hard, hit, evicted_pg#, dirty_evicted_page");
    }

    /**
     * Output to CSV file if we have all of the info and then reset all info
     */
    public void output() {
        if (address != null && rw != null && value != null && soft != null && hard != null &&
                hit != null && evictedPageNum != null && dirty != null) {

            csv.printf("%s, %s, %s, %s, %s, %s, %s, %s\n", address, rw, value, soft, hard, hit, evictedPageNum, dirty);
            System.out.printf("%s, %s, %s, %s, %s, %s, %s, %s\n", address, rw, value, soft, hard, hit, evictedPageNum, dirty);

            address = null;
            rw = null;
            value = null;
            soft = null;
            hard = null;
            hit = null;
            evictedPageNum = null;
            dirty = null;
        }
    }
}
