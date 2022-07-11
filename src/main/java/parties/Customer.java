package parties;

import chain.Payment;
import chain.Report;
import chain.Request;
import parties.operations.Information;
import products.Product;
import products.ProductType;

public class Customer extends AParty{

    /**
     * The seller who accepts request
     */
    private Seller seller;

    /**
     * Constructor for creating a new Customer
     */
    public Customer(String name, int money, int margin) {
        super(PartyType.Customer, name, money, margin);
    }

    /**
     * Product purchase operation
     * @param type
     * @param seller
     * @return result of purchase(text)
     */
    public String buy(ProductType type, Seller seller){
        this.seller = seller;
        product = new Product(type);
        this.reports.add(new Report(this.getName() + "(" + this.getType() + ") wants to buy " + type.toString() + "\n"));
        super.sendRequest(new Request(this, seller, new Information(product,"")));

        if(product != null){
            this.reports.add(new Report(this.getName() + "(" + this.getType() + ") received the product " + type.toString() +
                    "\nPayed " + product.getPrice() + "\n"));
            return "The product is purchased!";
        }else
            this.reports.add(new Report(this.getName() + "(" + this.getType() + ") did not receive the product " + type.toString() +
                    ". Not in the store or not enough funds\n"));
            return "The product was not purchased";

    }

    /**
     * Product receipt operation
     * @param product
     */
    public void receive(Product product) {
        if(product != null){
            if(pay(new Payment(this, seller, product.getPrice()))){
                this.product = product;
            }else{
                this.product = null;
                System.out.println(this.getName() + " haven't got enough money");
                return;
            }
        }else
            this.product = null;
    }

}
