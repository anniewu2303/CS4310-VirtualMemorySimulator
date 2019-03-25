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

    private String address, rw, value, soft, hard, hit, evictedPageNumber, dirty;
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
    public void initializeCSV(String outputFile) throws FileNotFoundException {
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
    public void soft(boolean bool) {
        if (bool == false) {
            soft = "0";
        } else {
            soft = "1";
        }
        output();
    }

    /**
     * 0 = false
     * 1 = true
     */
    public void hard(boolean bool) {
        if (bool == false) {
            hard = "0";
        } else {
            hard = "1";
        }
        output();
    }

    /**
     * 0 = false
     * 1 = true
     */
    public void hit(boolean bool) {
        if (bool == false) {
            hit = "0";
        } else {
            hit = "1";
        }
        output();
    }

    /**
     * Output page number that is evicted or N/A if none
     *
     * @param evicted
     */
    public void evictedPageNumber(String evicted) {
        evictedPageNumber = evicted;
        output();
    }

    /**
     * 0 = false
     * 1 = true
     */
    public void dirty(boolean bool) {
        if (bool == false) {
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
                hit != null && evictedPageNumber != null && dirty != null) {

            csv.println(address + ", " + rw + ", " + value + ", " + soft + ", " + hard + ", " +
                    hit + ", " + evictedPageNumber + ", " + dirty);

            address = null;
            rw = null;
            value = null;
            soft = null;
            hard = null;
            hit = null;
            evictedPageNumber = null;
            dirty = null;
        }
    }
}
