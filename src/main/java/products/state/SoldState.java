package products.state;

import products.Product;

public class SoldState extends State{

    /**
     * Constructor for creating a new SoldState
     * @param product
     */
    public SoldState(Product product) {
        super(product, StateType.SoldType);
    }
}
