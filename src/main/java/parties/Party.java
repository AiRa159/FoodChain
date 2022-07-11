package parties;

import chain.Payment;
import chain.Report;
import chain.Request;
import channels.Channel;
import products.Product;
import products.state.StateType;

import java.util.List;

public interface Party {

    /**
     * @return Id of party
     */
    String getId();

    /**
     * @return Type of party
     */
    PartyType getType();

    /**
     * @return Amount of money
     */
    int getMoney();

    /**
     * @return Margin value
     */
    int getMargin();

    /**
     * @return Name of party
     */
    String getName();

    /**
     * @return Array of report
     */
    List<Report> getReports();

    /**
     * @return Current product
     */
    Product getProduct();

    /**
     * Charges money of party
     * @param value Price of payment
     */
    void payment(int value);

    /**
     * Add money to party
     * @param value Amount of income
     */
    void income(int value);

    /**
     * Send request with changed instruction
     * @param request
     */
    void sendRequest(Request request);

    /**
     * Accept request from other party
     * @param request
     */
    void processRequest(Request request);

    /**
     * Make a payment
     * @param payment
     * @return
     */
    boolean pay(Payment payment);

    /**
     * Clean objects
     */
    void done();

    /**
     * Join the channel
     * @param channel
     */
    void joinChannel(Channel channel);

    /**
     * Leave the channel
     * @param channel
     */
    void leaveChannel(Channel channel);

    /**
     * Report generation
     * @param filePath
     */
    void generateReport(String filePath);

    /**
     * Show information about party
     * @return
     */
    String toString();

    /**
     * Adds to reports when product state is changed
     * @param product
     * @param previousType
     * @param type
     */
    void notifiedOfProductStateChanged(Product product, StateType previousType, StateType type);
}
