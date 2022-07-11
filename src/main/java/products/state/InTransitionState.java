package products.state;

import products.Product;

public class InTransitionState extends State{

    /**
     * Constructor for creating a new InTransitionState
     * @param product
     */
    public InTransitionState(Product product) {
        super(product, StateType.InTransitionType);
    }
}
