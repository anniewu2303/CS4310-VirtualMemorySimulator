/*
 Author:        Joshua Chen
                Annie Wu 
				
 Assignment:    Program 2 
 Class:         CS 4310 - Operating Systems 
 Instructor:    Dominick Atanasio 
 Date:          24 March 2019 
 
- Your program will output the following information in a CSV file (with appropriate headers):
	- The address, 
	- Read or write (0 or 1),
	- The value read or written, 
	- Soft miss (0 = false, 1 = true), 
	- Hard miss (0 = false, 1 = true), 
	- A hit (0 = false, 1 = true), 
	- page number of the evicted page, 
	- was that page’s dirty bit set.
- The header of the CSV should be:
	Address, r/w, value, soft, hard, hit, evicted_pg#, dirty_evicted_page
- There should be one csv for every test file.

*/

public class CSV {
    
    private String address, rw, value, soft, hard, hit, evictedPageNumber, dirtyEvictedPage;
    
    /*
        + "" turns the parameter into a string for writing to output file
    */
    
    
    public void address(String a) {
        address = a;
        output();
    }
    
    /*
    0 = read
    1 = write
    */
    public void rw(int r) {
        rw = r + "";
        output();
    }
    
    public void value(int v) {
        value = v + "";
        output();
    }
    
    /*
    0 = false
    1 = true
    */
    public void soft(int s) {
        soft = s + "";
        output();
    }
    
    /*
    0 = false
    1 = true
    */
    public void hard(int h) {
        hard = h + "";
        output();
    }
    
    /*
    0 = false
    1 = true
    */
    public void hit(int h) {
        hit = h + "";
        output();
    }
    
    public void evictedPageNumber(int e) {
        evictedPageNumber = e + "";
        output();
    }
    
    /*
    0 = not set
    1 = set
    */
    public void dirtyEvictedPage(int d) {
        dirtyEvictedPage = d + "";
        output();
    }

    public void header() {
        System.out.println("Address, r/w, value, soft, hard, hit, evicted_pg#, dirty_evicted_page");
    }
    
    /*
    Output to CSV file if we have all of the info 
    */
    public void output() {
        if () {
            
        }
    }
    
    /*
    Format output file test_#.csv
    */
    public void createOutputFile(String fileName) {
        
    }
}
