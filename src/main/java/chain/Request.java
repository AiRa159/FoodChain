package chain;

import parties.Party;
import parties.PartyType;
import parties.operations.Information;
import products.Product;

public class Request {

    /**
     * Destination of request(type)
     */
    private PartyType destinationType;

    /**
     * Destination of request(party)
     */
    private Party destination;

    /**
     * Sender of request
     */
    private Party sender;

    /**
     * Information(body of request)
     */
    private Information information;

    /**
     * Constructor for creating a new request
     * @param sender
     * @param destination
     * @param information
     */
    public Request(Party sender, PartyType destination, Information information) {
        this.destinationType = destination;
        this.sender = sender;
        this.information = information;
    }

    /**
     * Constructor for creating a new request
     * @param sender
     * @param destination
     * @param information
     */
    public Request(Party sender, Party destination, Information information){
        this.sender = sender;
        this.destination = destination;
        this.information = information;
    }

    /**
     * @return destination(type)
     */
    public PartyType getDestination() { return destinationType; }

    /**
     * @return destination(party)
     */
    public Party getDestinationParty(){ return destination; }

    /**
     * @return sender
     */
    public Party getSender() { return sender; }

    /**
     * @return information
     */
    public Information getInformation() { return information; }

}
