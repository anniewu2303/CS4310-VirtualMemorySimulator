/**
 * @author Joshua Chen
 * Date Created: Mar 14, 2019
 */
public class TLB {
    private TlbEntries entries[];
    private final static int DEFAULT_MAX_ENTRIES = 16;

    public TLB() {
        this(DEFAULT_MAX_ENTRIES);
    }

    public TLB(int max_entries) {
        this.entries = new TlbEntries[max_entries];
    }
}
