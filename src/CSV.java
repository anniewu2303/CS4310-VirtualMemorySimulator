/**
 * Author:        Joshua Chen
 * Annie Wu
 * <p>
 * Assignment:    Program 2
 * Class:         CS 4310 - Operating Systems
 * Instructor:    Dominick Atanasio
 * Date:          24 March 2019
 * <p>
 * - Your program will output the following information in a CSV file (with appropriate headers):
 * - The address,
 * - Read or write (0 or 1),
 * - The value read or written,
 * - Soft miss (0 = false, 1 = true),
 * - Hard miss (0 = false, 1 = true),
 * - A hit (0 = false, 1 = true),
 * - page number of the evicted page,
 * - was that page’s dirty bit set.
 * - The header of the CSV should be:
 * Address, r/w, value, soft, hard, hit, evicted_pg#, dirty_evicted_page
 * - There should be one csv for every test file.
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class CSV {

    private String address, rw, value, soft, hard, hit, evictedPageNumber, dirtyEvictedPage;
    private PrintStream csv;
    
    /*
        + "" turns the parameter into a string for writing to output file
    */

    /**
     * initialize test_#.csv with header
     *
     * @param outputfile
     * @throws FileNotFoundException
     */
    public void initializeCSV(String outputfile) throws FileNotFoundException {
        FileOutputStream output = new FileOutputStream(outputfile);
        csv = new PrintStream(output);
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
        }
        else {
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
            soft = "0";
        }
        else {
            soft = "1";
        }
        output();
    }

    /**
     * 0 = false
     * 1 = true
     */
    public void hit(boolean bool) {
        if (bool == false) {
            soft = "0";
        }
        else {
            soft = "1";
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

    public void dirtyEvictedPage(int d) {
        dirtyEvictedPage = d + "";
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
                hit != null && evictedPageNumber != null && dirtyEvictedPage != null) {
            csv.println(address + ", " + rw + ", " + value + ", " + soft + ", " + hard + ", " +
                    hit + ", " + evictedPageNumber + ", " + dirtyEvictedPage);

            address = null;
            rw = null;
            value = null;
            soft = null;
            hard = null;
            hit = null;
            evictedPageNumber = null;
            dirtyEvictedPage = null;
        }
    }
}
