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

public class Seller extends AParty{

    /**
     * Array of products in shop
     */
    private List<Product> products;

    /**
     * Current request
     */
    private Request currRequest;

    /**
     * Answer of system(true - wants to buy if not in the store, false - does not want to buy if not in the store)
     */
    private boolean answer;

    /**
     * Constructor for creating a new Distributor
     */
    public Seller(String name, int money, int margin) {
        super(PartyType.Seller, name, money, margin);
        this.products = new ArrayList<>();
        this.answer = true;
    }

    /**
     * Request processing
     * @param request
     */
    @Override
    public void processRequest(Request request) {
        currRequest = request;
        this.reports.add(new Report(this.getName() + "(" + this.getType() + ") accepted request from "
                + request.getSender().getName() + "(" + this.getType() + ")\n"));
        product = getProduct(request.getInformation().getProduct().getType());
        if(product != null){
            sell(request);
        }else
            if(answer) {
                buy(request.getInformation().getProduct().getType());
            }else {
                System.out.println(this.getName() + "(" + this.getType() + ") did not sell the product because it is out of stock\n");
                super.sendRequest(new Request(this, request.getSender(), new Information(product, "")));
            }
    }

    /**
     * Product purchase
     * @param type
     */
    private void buy(ProductType type) {
        this.reports.add(new Report(this.getName() + "(" + this.getType() + ") ordered " + type.toString() + "\n"));
        super.sendRequest(new Request(this, PartyType.Dealer, new Information(new Product(type), "ordered")));
    }

    /**
     * Sale
     * @param request
     */
    private void sell(Request request){
        product = getProduct(request.getInformation().getProduct().getType());
        if(product.getType() == ProductType.Milk){
            product.setPrice(20 + getMargin());
        }else if(product.getType() == ProductType.Meat){
            product.setPrice(80 + getMargin());
        }
        products.remove(product);
        product.nextTick(this);
        request.getInformation().setText("sold");

        product.addReport(new Report("Sold by " + this.getName() + "(" + this.getType() + ")"));
        this.reports.add(new Report(this.getName() + "(" + this.getType() + ") sold " + product.getType() +
                " for " + request.getSender().getName() + ".\nPrice: " + product.getPrice() + "\n"));
        new TransactionBlock(this, request.getInformation());
        super.sendRequest(new Request(this, request.getSender(), new Information(product, request.getInformation().getText())));
    }

    /**
     * Receiving the products and sending them to the customer
     * @param request
     */
    public void accept(Request request){
        if(pay(new Payment(this, request.getSender(), request.getInformation().getProduct().getPrice()))){
            this.reports.add(new Report(this.getName() + "(" + this.getType() + ") received product.\nPayed " + request.getInformation().getProduct().getPrice() + "\n"));
            products.add(request.getInformation().getProduct());
            sell(currRequest);
        }else {
            System.out.println(this.getName() + "(" + this.getType() + ") did not accept the request due to lack of funds" + "\n");
            return;
        }
    }

    /**
     * Find product in shop
     * @param product
     * @return product if exists
     */
    private Product getProduct(ProductType product){
        for (Product p : products) {
            if (p.getType() == product) {
                return p;
            }
        }
        return null;
    }

    /**
     * Set answer of system
     * @param answer
     */
    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
