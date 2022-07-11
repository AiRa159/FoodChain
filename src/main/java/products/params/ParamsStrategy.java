package products.params;

import products.Product;

public interface ParamsStrategy {

    /**
     * Set parameters to product
     * @param product
     */
    void setParams(Product product);
}
