package parties;

import chain.Payment;
import chain.Report;
import chain.ReportGenerator;
import chain.Request;
import channels.Channel;
import channels.MoneyChannel;
import channels.ProductChannel;
import products.Product;
import products.state.StateType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class AParty implements Party{

    /**
     * Identifier of party
     */
    private String id;

    /**
     * Name of party
     */
    private String name;

    /**
     * Amount of money
     */
    private int money;

    /**
     * Margin  of party
     */
    private int margin;

    /**
     * Type of party
     */
    private PartyType type;

    /**
     * Array of reports
     */
    protected List<Report> reports;

    /**
     * Product channel
     */
    private ProductChannel productChannel;

    /**
     * Money channel
     */
    private MoneyChannel moneyChannel;

    /**
     * Current product
     */
    protected Product product;

    /**
     * Constructor for creating a new party
     * @param type Type of party
     */
    public AParty(PartyType type, String name, int money, int margin) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.name = name;
        this.money = money;
        this.margin = margin;
        this.reports = new ArrayList<>();
        this.productChannel = null;
        this.productChannel = null;
    }

    @Override
    public String getId(){ return this.id; }

    @Override
    public int getMoney() { return this.money; }

    @Override
    public PartyType getType() { return this.type; }

    @Override
    public String getName() { return name; }

    @Override
    public int getMargin() { return margin; }

    @Override
    public List<Report> getReports() { return reports; }

    @Override
    public Product getProduct() { return product; }

    @Override
    public void payment(int value) { this.money -= value; }

    @Override
    public void income(int value) { this.money += value; }

    @Override
    public void sendRequest(Request request) {
        if(request != null){
            if(request.getDestinationParty() != null) {
                if (request.getSender().getType() == PartyType.Seller && request.getDestinationParty().getType() == PartyType.Customer) {
                    productChannel.sell(request);
                }else
                    if(request.getSender().getType() == PartyType.Customer && request.getDestinationParty().getType() == PartyType.Seller){
                        productChannel.acceptRequest(request);
                }else
                    productChannel.accept(request);
            }else {
                if (request.getSender().getType() == PartyType.Dealer && request.getDestination() == PartyType.Storehouse) {
                    productChannel.order(request);
                }else
                    productChannel.acceptRequest(request);
            }
        }
    }

    @Override
    public void processRequest(Request request) { }

    @Override
    public boolean pay(Payment payment) {
        if(payment != null){
            return moneyChannel.processThePayment(payment);
        }
        return false;
    }

    @Override
    public void done() {}

    @Override
    public void joinChannel(Channel channel) {
        switch (channel.getType()){
            case Money_Channel:
                this.moneyChannel = (MoneyChannel)channel;
                channel.addParty(this);
                break;
            case Product_Channel:
                this.productChannel = (ProductChannel)channel;
                channel.addParty(this);
                break;
        }
    }

    @Override
    public void leaveChannel(Channel channel) {
        switch (channel.getType()){
            case Money_Channel:
                this.moneyChannel = null;
                channel.removeParty(this);
                break;
            case Product_Channel:
                this.productChannel = null;
                channel.removeParty(this);
                break;
        }
    }

    @Override
    public void generateReport(String filePath) {
        ReportGenerator generator = new ReportGenerator();
        generator.generateReport(filePath, reports);
    }

    @Override
    public String toString() {
        return "Name: " + getName() +
                "\nType: " + getType() +
                "\nMoney: " + getMoney() +
                "\nMargin: " + getMargin() + "\n";
    }

    @Override
    public void notifiedOfProductStateChanged(Product product, StateType previousType, StateType type) {
        this.reports.add(new Report(product.getType() + " product with id " + product.getCertificate().getId() + " from " + this.getName()+ " (" + this.getType() + ") changed state from "
                + previousType.toString() + " to " + type.toString() + "\n"));    }

}
