public class CSV {
    
    private String address, rw, value, soft, hard, hit, evictedPageNumber, dirtyEvictedPage;
    
    /*
        + "" turns the parameter into a string 
    */
    public void address(String a) {
        address = a;
        output();
    }
    
    public stic void rw(int r) {
        rw = r + "";
        output();
    }
    
    public void value(int v) {
        value = v + "";
        output();
    }
    
    public static void soft(int s) {
        soft = s + "";
        output();
    }
    
    public void hard(int h) {
        hard = h + "";
        output();
    }
    
    public void hit(int h) {
        hit = h + "";
        output();
    }
    
    public void evictedPageNumber(int e) {
        evictedPageNumber = e + "";
        output();
    }
    
    public void dirtyEvictedPage(int d) {
        dirtyEvictedPage = d + "";
        output();
    }
    
    public void headerCSV() {
        System.out.println("Address, r/w, value, soft, hard, hit, evicted_pg#, dirty_evicted_page");
    }
    
    public void output() {

    }

    public void setOutputFileName(String string) {

    }
    
}
