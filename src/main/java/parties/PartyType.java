package parties;

public enum PartyType {

    /**
     * Farmer - food sources
     */
    Farmer,

    /**
     * Processor - processes food from a farmer
     */
    Processor,

    /**
     * Storehouse - stores product
     */
    Storehouse,

    /**
     * Dealer - delivers food from storehouse to the store
     */
    Dealer,

    /**
     * Distributor - sells product from the store to the customer
     */
    Seller,

    /**
     * Customer - a user who orders product
     */
    Customer
}
