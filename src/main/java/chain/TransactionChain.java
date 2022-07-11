package chain;

public class TransactionChain {

    /**
     * First block of chain
     */
    protected static TransactionBlock firstBlock = null;

    /**
     * Last block of chain
     */
    protected static TransactionBlock lastBlock = null;

    /**
     * Set first block
     * @param block
     */
    protected static void setFirstBlock(TransactionBlock block) {
        if (firstBlock == null) {
            firstBlock = block;
            lastBlock = block;
        }
    }

    /**
     * Add new block
     * @param block
     */
    protected static void add(TransactionBlock block){
        lastBlock = block;
    }

    /**
     * Report generation
     * @param filename
     */
    public void generateReport(String filename){
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.generateReport(filename, this);
    }

    /**
     * @return first block
     */
    public TransactionBlock getFirstBlock() {
        return firstBlock;
    }

}
