package parties;

import chain.Payment;
import chain.Report;
import chain.Request;
import chain.TransactionBlock;
import parties.operations.Information;
import products.Product;
import products.ProductType;

public class Processor extends AParty{

    /**
     * Constructor for creating a new Processor
     * @param name
     */
    public Processor(String name, int money, int margin) { super(PartyType.Processor, name, money, margin); }

    /**
     * Request processing
     * @param request
     */
    @Override
    public void processRequest(Request request) {
        if(pay(new Payment(this, request.getSender(), request.getInformation().getProduct().getPrice()))){
            product = request.getInformation().getProduct();
            product.nextTick(this);
            product.setName(super.getName());
            request.getInformation().setText("packed");

            this.reports.add(new Report(this.getName() + "(" + this.getType() + ") accepted request.\nPayed " + product.getPrice() +
                    "\nPacked " + product.getType().toString() + "\n"));
            product.addReport(new Report("Processed by " + this.getName() + "(" + this.getType() + ")\n"));

            product.nextState();
            if(product.getType() == ProductType.Meat) {
                int price = 10 + getMargin();
                product.setPrice(price);
            }else if(product.getType() == ProductType.Milk){
                int price = 5 + this.getMargin();
                product.setPrice(price);
            }

            this.reports.add(new Report(this.getName() + "(" + this.getType() + ") send product to Storehouse.\nPrice: " + product.getPrice() + "\n"));
            new TransactionBlock(this, request.getInformation());
            super.sendRequest(new Request(this, PartyType.Storehouse, new Information(product, request.getInformation().getText())));
        }else {
            System.out.println(this.getName() + "(" + this.getType() + ") did not accept the request due to lack of funds" + "\n");
            return;
        }
    }

    @Override
    public void done() {
        product = null;
    }
}
