package parties;

import certificates.Certificate;
import chain.Report;
import chain.Request;
import chain.TransactionBlock;
import parties.operations.Information;
import products.Product;
import products.ProductType;

public class Farmer extends AParty{

    /**
     * Product certificate
     */
    private Certificate certificate;

    /**
     * Request information
     */
    private Information information;

    /**
     * Constructor for creating a new Farmer
     */
    public Farmer(String name, int money, int margin) {
        super(PartyType.Farmer, name, money, margin);
    }

    /**
     * Product creation
     * @param type
     */
    public void createProduct(ProductType type){
        product = new Product(type);
        this.certificate = new Certificate(this, product);
        product.setCertificate(certificate);
        product.setProductParams();

        if(type == ProductType.Milk) {
            product.setPrice(10 + getMargin());
        }else if(type == ProductType.Meat){
            product.setPrice(15 + getMargin());
        }
        information = new Information(product, "received");

        this.reports.add(new Report(this.getName() + "(" + this.getType() + ") created " + type.toString()
                + " and added the certificate with id " + certificate.getId() + "\n"));
        product.addReport(new Report("Created by " + this.getName() + "(" + this.getType() + ")\n"));
        new TransactionBlock(this, information);
    }

    /**
     * Request sending
     */
    public void sendRequest() {
        this.reports.add(new Report(this.getName() + "(" + this.getType() + ") send product to Processor.\nPrice: " + product.getPrice() + "\n"));
        product.nextState();
        super.sendRequest(new Request(this, PartyType.Processor, information));
    }

    @Override
    public void done(){
        this.certificate = null;
        this.information = null;
        this.product = null;
    }
}
