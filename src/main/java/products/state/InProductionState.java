package products.state;

import products.Product;

public class InProductionState extends State{

    /**
     * Constructor for creating a new InProductionState
     * @param product
     */
    public InProductionState(Product product) {
        super(product, StateType.InProcessType);
    }

}
