package products.state;


import chain.Report;
import parties.AParty;
import parties.Party;
import products.Product;

public abstract class State {

    /**
     * State type
     */
    private final StateType type;

    /**
     * Product
     */
    private final Product product;

    /**
     * Constructor for creating a new state
     * @param product
     * @param type
     */
    public State(Product product, StateType type) {
        this.product = product;
        this.type = type;
    }

    /**
     * Simulates next tick
     */
    public void nextTick(Party party) {
        switch (party.getType()){
            case Processor:
                product.setState(new InProductionState(product));
                break;
            case Storehouse:
                product.setState(new StoredState(product));
                break;
            case Dealer:
                product.setState(new InTransitionState(product));
                break;
            case Seller:
                product.setState(new SoldState(product));
                break;
        }
        product.addReport(new Report("State changed from " + this.type.toString() + " to " + product.getState().getType()));
    }

    /**
     * change state of product
     */
    public void nextState(){
        product.setState(new InTransitionState(product));
    }

    /**
     * @return state type
     */
    public StateType getType() {
        return type;
    }

    /**
     * @return product
     */
    public Product getProduct() {
        return product;
    }
}

