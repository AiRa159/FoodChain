package parties;

public class PartyFactory {

    /**
     * Method for creating new party
     * @param type Type of party
     * @return new party with name
     */
    public Party createParty(PartyType type, String name, int money, int margin){
        switch (type) {
            case Farmer:
                return new Farmer(name, money, margin);
            case Processor:
                return new Processor(name, money, margin);
            case Storehouse:
                return new Storehouse(name, money, margin);
            case Dealer:
                return new Dealer(name, money, margin);
            case Seller:
                return new Seller(name, money, margin);
            case Customer:
                return new Customer(name, money, margin);
            default:
                return null;
        }
    }
}
