package channels;

import chain.Payment;
import chain.TransactionBlock;
import parties.Party;

public class MoneyChannel extends Channel{

    /**
     * Constructor
     */
    public MoneyChannel() {
        super(ChannelType.Money_Channel);
    }

    /**
     * Payment processing
     * @param payment
     * @return
     */
    public boolean processThePayment(Payment payment){
        if(parties.contains(payment.getSender()) && parties.contains(payment.getRecipient())){
            if(payment.getSender().getMoney() >= payment.getTotal()) {
                payment.getSender().payment(payment.getTotal());
                Party recipient = payment.getRecipient();
                recipient.income(payment.getTotal());
                recipient.done();
                new TransactionBlock(payment.toString());
                return true;
            }
        }
        return false;
    }
}
