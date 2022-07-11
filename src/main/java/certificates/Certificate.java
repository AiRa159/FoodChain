package certificates;

import parties.Party;
import products.Product;

import java.util.UUID;

public class Certificate {

    /**
     * Unique id of certificate
     */
    private final String id;

    /**
     * The party that issues the certificate
     */
    private final Party party;

    /**
     * Product for which certificate had been issued
     */
    private final Product product;

    /**
     * Constructor for creating a new certificate
     * @param party
     * @param product
     */
    public Certificate(Party party, Product product) {
        this.id = UUID.randomUUID().toString();
        this.party = party;
        this.product = product;
    }

    /**
     * @return Id of Certificate
     */
    public String getId() {
        return id;
    }

    /**
     * @return Party of Certificate
     */
    public Party getParty() {
        return party;
    }

    /**
     * @return Product of Certificate
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Show information about certificate
     * @return
     */
    @Override
    public String toString() {
        return "Certification of " + product.getType().toString()  +
                " made by " + party.getName() +
                " with id: " + id;
    }
}
