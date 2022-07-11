package products.params;

import products.Product;

public class MeatParamsStrategy implements ParamsStrategy{

    @Override
    public void setParams(Product product) {
        product.setStorageTime(120);
        product.setTemperature(-8);
    }
}
