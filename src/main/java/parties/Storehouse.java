package parties;

import chain.Payment;
import chain.Report;
import chain.Request;
import chain.TransactionBlock;
import parties.operations.Information;
import products.Product;
import products.ProductType;

import java.util.ArrayList;
import java.util.List;

public class Storehouse extends AParty {

    /**
     * Array of products in storehouse
     */
    private List<Product> products;

    /**
     * Constructor for creating a new Storehouse
     */
    public Storehouse(String name, int money, int margin) {
        super(PartyType.Storehouse, name, money, margin);
        this.products = new ArrayList<>();
    }

    /**
     * Receiving the goods
     * @param request
     */
    @Override
    public void processRequest(Request request) {
        if(pay(new Payment(this, request.getSender(), request.getInformation().getProduct().getPrice()))){
            product = request.getInformation().getProduct();
            product.nextTick(this);
            products.add(product);
            request.getInformation().setText("stored. Params: storage time = " + product.getStorageTime()
                    + " day(s); temperature = " + product.getTemperature() + " degree(s)\n");

            this.reports.add(new Report(this.getName() + "(" + this.getType() + ") accepted request.\nPayed " + product.getPrice() +
                    "\nStored " + product.getType().toString() + product.getStorageTime() + " day(s) at a temperature of " + product.getTemperature() + " degree(s)\n"));
            product.addReport(new Report("Stored by " + this.getName() + "(" + this.getType() + ") "
                    + product.getStorageTime() + " day(s) at a temperature of " + product.getTemperature() + " degree(s)\n"));
            new TransactionBlock(this, request.getInformation());
        }else {
            System.out.println(this.getName() + "(" + this.getType() + ") did not accept the request due to lack of funds" + "\n");
            return;
        }
    }

    /**
     * Processing request from dealer
     * @param request
     */
    public void process(Request request){
        if(products.size() > 0) {
            for (Product p : products) {
                if (p.getType() == request.getInformation().getProduct().getType()) {
                    p.nextState();
                    if (p.getType() == ProductType.Meat) {

                        int price = 10 + this.getMargin();
                        p.setPrice(price);
                        this.reports.add(new Report(this.getName() + "(" + this.getType() + ") passed "
                                + product.getType().toString() + " for "
                                + request.getSender().getName() + "(" + request.getSender().getType() + ").\nPrice: " + price + "\n"));
                        super.sendRequest(new Request(this, request.getSender(), new Information(p,"stored")));
                        break;

                    } else if (p.getType() == ProductType.Milk) {

                        int price = 5 + this.getMargin();
                        p.setPrice(price);
                        this.reports.add(new Report(this.getName() + "(" + this.getType() + ") passed "
                                + product.getType().toString() + " for "
                                + request.getSender().getName() + "(" + request.getSender().getType() + ").\nPrice: " + price + "\n"));
                        super.sendRequest(new Request(this, request.getSender(), new Information(p,"stored")));
                        break;
                    }
                }
            }
        }else {
            System.out.println(this.getName() + "(" + this.getType() + ") can't accept the request. The storehouse is empty\n");
            return;
        }
    }

    @Override
    public void done() {
        products.remove(product);
        product = null;
    }

    /**
     * @return current product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * @return array of products
     */
    public List<Product> getProducts() {
        return products;
    }
}

