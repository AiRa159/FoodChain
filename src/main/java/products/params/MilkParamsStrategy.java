package products.params;

import products.Product;

public class MilkParamsStrategy implements ParamsStrategy{

    @Override
    public void setParams(Product product) {
        product.setStorageTime(4);
        product.setTemperature(2);
    }
}
