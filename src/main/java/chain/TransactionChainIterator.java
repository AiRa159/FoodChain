package chain;

import products.ProductType;

public class TransactionChainIterator {

    /**
     * Current block of the chain
     */
    TransactionBlock currentBlock = null;

    /**
     * @return true if next block exists, false if no
     */
    protected boolean hasNext() {
        if (currentBlock == null && TransactionChain.firstBlock != null) {
            return true;
            }
        if (currentBlock == null) {
            return true;
        }
        return currentBlock.getNextBlock() != null;

    }

    /**
     * Moves to the next block
     */
    protected void next() {
        if (hasNext()) {
            if (currentBlock == null) {
                currentBlock = TransactionChain.firstBlock;
            } else {
                currentBlock = currentBlock.getNextBlock();
            }
        }
    }

}
