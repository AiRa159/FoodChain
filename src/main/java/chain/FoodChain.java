package chain;

import channels.MoneyChannel;
import channels.ProductChannel;
import parties.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FoodChain {

    /**
     *  Array of parties
     */
    private List<Party> parties;

    /**
     * Instance of PartyFactory
     */
    private PartyFactory factory;

    /**
     * Instance of system(FoodChain)
     */
    private static FoodChain foodChain;

    /**
     * Local date and time
     */
    private LocalDateTime localDateTime;

    /**
     * System configuration
     */
    private String config;

    /**
     * @return Object of the class(singleton)
     */
    public static FoodChain getInstance(){
        if(foodChain == null){
            foodChain = new FoodChain();
        }
        return foodChain;
    }

    /**
     * Constructor
     */
    public FoodChain() {
        parties = new ArrayList<>();
        factory = new PartyFactory();
        localDateTime = LocalDateTime.now();
    }

    /**
     * @return Local date in correct format
     */
    public String getLocalDateTime() {
        return localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    /**
     * Setter for configuration
     * @param config
     */
    public void setConfig(String config){
        this.config = config;
        switch (config){
            case "A":
                confA();
            case "B":
                confA();
        }
    }

    /**
     * Configuration "A"
     */
    private void confA(){
        parties.add(factory.createParty(PartyType.Farmer, "Bob", 0, 20));
        parties.add(factory.createParty(PartyType.Processor, "Albert", 200, 15));
        parties.add(factory.createParty(PartyType.Storehouse, "AlbertStorage", 500, 10));
        parties.add(factory.createParty(PartyType.Dealer, "AlbertDealer", 100, 20));
        parties.add(factory.createParty(PartyType.Seller, "Albert", 100, 10));
        parties.add(factory.createParty(PartyType.Customer, "Sam", 150, 0));
    }

    /**
     * Initialization of farmer
     * @param productChannel
     * @param moneyChannel
     * @return initialized farmer
     */
    public Party initializeFarmer(ProductChannel productChannel, MoneyChannel moneyChannel){
        for(Party p : parties){
            if(p instanceof Farmer) {
                p.joinChannel(productChannel);
                p.joinChannel(moneyChannel);
                return p;
            }
        }
        return null;
    }

    /**
     * Initialization of processor
     * @param productChannel
     * @param moneyChannel
     * @return initialized processor
     */
    public Party initializeProcessor(ProductChannel productChannel, MoneyChannel moneyChannel){
        for(Party p : parties){
            if(p instanceof Processor) {
                p.joinChannel(productChannel);
                p.joinChannel(moneyChannel);
                return p;
            }
        }
        return null;
    }

    /**
     * Initialization of storehouse
     * @param productChannel
     * @param moneyChannel
     * @return initialized storehouse
     */
    public Party initializeStorehouse(ProductChannel productChannel, MoneyChannel moneyChannel){
        for(Party p : parties){
            if(p instanceof Storehouse) {
                p.joinChannel(productChannel);
                p.joinChannel(moneyChannel);
                return p;
            }
        }
        return null;
    }

    /**
     * Initialization of dealer
     * @param productChannel
     * @param moneyChannel
     * @return initialized dealer
     */
    public Party initializeDealer(ProductChannel productChannel, MoneyChannel moneyChannel){
        for(Party p : parties){
            if(p instanceof Dealer) {
                p.joinChannel(productChannel);
                p.joinChannel(moneyChannel);
                return p;
            }
        }
        return null;
    }

    /**
     * Initialization of seller
     * @param productChannel
     * @param moneyChannel
     * @return initialized seller
     */
    public Party initializeSeller(ProductChannel productChannel, MoneyChannel moneyChannel){
        for(Party p : parties){
            if(p instanceof Seller) {
                p.joinChannel(productChannel);
                p.joinChannel(moneyChannel);
                switch (config){
                    case "A":
                        ((Seller) p).setAnswer(true);
                        break;
                    case "B":
                        ((Seller) p).setAnswer(false);
                        break;
                }
                return p;
            }
        }
        return null;
    }

    /**
     * Initialization of customer
     * @param productChannel
     * @param moneyChannel
     * @return initialized customer
     */
    public Party initializeCustomer(ProductChannel productChannel, MoneyChannel moneyChannel){
        for(Party p : parties){
            if(p instanceof Customer) {
                p.joinChannel(productChannel);
                p.joinChannel(moneyChannel);
                return p;
            }
        }
        return null;
    }
}
