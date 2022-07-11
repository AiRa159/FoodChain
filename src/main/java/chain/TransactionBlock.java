package chain;

import parties.Party;
import parties.operations.Information;
import products.Product;

public class TransactionBlock {

    /**
     * Creator of block
     */
    private final Party party;

    /**
     * Product of transaction block
     */
    private final Product product;

    /**
     * Text of transaction block
     */
    private final String text;

    /**
     * Next block
     */
    private TransactionBlock nextBlock = null;

    /**
     * Constructor for creating a new block
     * @param party
     * @param information
     */
    public TransactionBlock(Party party, Information information) {
        this.party = party;
        this.product = information.getProduct();
        this.text = information.getText();
        pushToBlockchain();
    }

    /**
     * Constructor for creating a new block
     * @param text
     */
    public TransactionBlock(String text) {
        this.party = null;
        this.product = null;
        this.text = text;
        pushToBlockchain();
    }

    /**
     * Add to block chain
     */
    private void pushToBlockchain(){
        TransactionChain.setFirstBlock(this);
        if (TransactionChain.lastBlock == null) {
            TransactionChain.lastBlock = this;
        } else {
            TransactionChain.lastBlock.setNextBlock(this);
        }
        TransactionChain.add(this);
    }

    /**
     * @return next block
     */
    protected TransactionBlock getNextBlock() {
        return nextBlock;
    }

    /**
     * Set next block
     * @param nextBlock
     */
    private void setNextBlock(TransactionBlock nextBlock) {
        this.nextBlock = nextBlock;
    }

    /**
     * Show information about block
     * @return
     */
    public String toString(){
        if(product != null && party != null) {
            return product.getType().toString() + " " + text + " by\n" +
                    party.toString();
        }else
            return text + "\n";
    }
}