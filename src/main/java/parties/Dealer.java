package parties;

import chain.Payment;
import chain.Report;
import chain.Request;
import chain.TransactionBlock;
import parties.operations.Information;

public class Dealer extends AParty{

    /**
     * The seller from whom the request was received
     */
    private Party seller;

    /**
     * Constructor for creating a new Dealer
     */
    public Dealer(String name, int money, int margin) {
        super(PartyType.Dealer, name, money, margin);
    }

    /**
     * Request processing
     * @param request
     */
    @Override
    public void processRequest(Request request) {
        seller = request.getSender();
        this.reports.add(new Report(this.getName() + "(" + this.getType() + ") accepted request from seller and and passed it to the storehouses\n"));
        super.sendRequest(new Request(this, PartyType.Storehouse, request.getInformation()));
    }

    /**
     * Receiving the products and sending them to the seller
     * @param request
     */
    public void accept(Request request){
        if(pay(new Payment(this, request.getSender(), request.getInformation().getProduct().getPrice()))) {
            product = request.getInformation().getProduct();
            product.nextTick(this);
            product.setPrice(request.getInformation().getProduct().getPrice() + getMargin());
            product.addReport(new Report("Delivered by " + this.getName() + "(" + this.getType() + ")\n"));
            request.getInformation().setText("delivered");

            this.reports.add(new Report(this.getName() + "(" + this.getType() + ") received product from " + request.getSender().getName()
                    + " and and passed it to the " + seller.getName() + "\nPrice: " + product.getPrice() + this.getMargin() + "\n"));
            new TransactionBlock(this, request.getInformation());
            super.sendRequest(new Request(this, seller, new Information(product,  request.getInformation().getText())));
        }else {
            System.out.println(this.getName() + "(" + this.getType() + ") did not accept the request due to lack of funds" + "\n");
            return;
        }
    }

    @Override
    public void done() {
        seller = null;
        product = null;
    }

}
