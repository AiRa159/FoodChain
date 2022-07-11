package channels;

import chain.Request;
import parties.*;

public class ProductChannel extends Channel{

    /**
     * Constructor
     */
    public ProductChannel() { super(ChannelType.Product_Channel); }

    /**
     * Request processing
     * @param req
     */
    public void acceptRequest(Request req){
        if(this.parties.contains(req.getSender())){
            for(Party p : this.parties){
                if(req.getDestinationParty() == null){
                    if(p.getType() == req.getDestination()){
                        p.processRequest(req);
                        break;
                    }
                }else
                    if(p.getType() == req.getDestinationParty().getType()){
                        p.processRequest(req);
                        break;
                    }
            }
        }
    }

    /**
     * Selling
     * @param request
     */
    public void sell(Request request){
        if(this.parties.contains(request.getSender()) && this.parties.contains(request.getDestinationParty())){
            Customer dest = (Customer) request.getDestinationParty();
            dest.receive(request.getInformation().getProduct());
        }
    }

    /**
     * Ordering
     * @param request
     */
    public void order(Request request){
        if(this.parties.contains(request.getSender())){
            for(Party p : this.parties){
                if(p.getType() == request.getDestination()){
                    Storehouse st = (Storehouse) p;
                    st.process(request);
                    break;
                }
            }
        }
    }

    /**
     * Accepting
     * @param request
     */
    public void accept(Request request){
        if(request.getDestinationParty().getType() == PartyType.Dealer) {
            Dealer dealer = (Dealer) request.getDestinationParty();
            dealer.accept(request);
        }else if(request.getDestinationParty().getType() == PartyType.Seller){
            Seller seller = (Seller) request.getDestinationParty();
            seller.accept(request);
        }
    }
}
