package products.state;

import products.Product;

public class StoredState extends State{

    /**
     * Constructor for creating a new StoredState
     * @param product
     */
    public StoredState(Product product) {
        super(product, StateType.StoredType);
    }

}
