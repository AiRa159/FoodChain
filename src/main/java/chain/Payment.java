package chain;

import parties.Party;

public class Payment {

    /**
     * Payment sender
     */
    private Party sender;

    /**
     * Payment recipient
     */
    private Party recipient;

    /**
     * Payment amount
     */
    private int total;

    /**
     * Payment time
     */
    private String time;

    /**
     * Constructor for creating a new payment
     * @param sender
     * @param recipient
     * @param total
     */
    public Payment(Party sender, Party recipient, int total) {
        this.sender = sender;
        this.recipient = recipient;
        this.total = total;
        this.time = FoodChain.getInstance().getLocalDateTime();
    }

    /**
     * @return information about payment
     */
    @Override
    public String toString(){
        return "=========Payment========="
                + "\nSender name: " + sender.getName()
                + "\nRecipient name: " + recipient.getName()
                + "\nTotal: " + total
                + "\nTime: " + time;
    }

    /**
     * @return payment sender
     */
    public Party getSender() {
        return sender;
    }

    /**
     * @return payment recipient
     */
    public Party getRecipient() {
        return recipient;
    }

    /**
     * @return payment amount
     */
    public int getTotal() {
        return total;
    }

    /**
     * @return payment date and time
     */
    public String getTime() {
        return time;
    }
}
