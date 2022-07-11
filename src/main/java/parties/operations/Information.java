package parties.operations;

import products.Product;

public class Information {

    /**
     * Processed product
     */
    private Product product;

    /**
     * Text of information
     */
    private String text;

    /**
     * Constructor for creating a new information
     * @param product
     * @param text
     */
    public Information(Product product, String text){
        this.product = product;
        this.text = text;
    }

    /**
     * @return product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @return text
     */
    public String getText() { return text; }

    /**
     * Set text
     * @param text
     */
    public void setText(String text) { this.text = text; }
}
