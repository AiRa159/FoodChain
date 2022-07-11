import chain.FoodChain;
import chain.TransactionChain;
import channels.MoneyChannel;
import channels.ProductChannel;
import parties.*;
import products.ProductType;

public class Main {
    public static void main(String[] args){
        FoodChain builder = FoodChain.getInstance();
        builder.setConfig("A");
        ProductChannel productChannel = new ProductChannel();
        MoneyChannel moneyChannel = new MoneyChannel();

        Farmer farmer = (Farmer) builder.initializeFarmer(productChannel, moneyChannel);
        Processor processor = (Processor) builder.initializeProcessor(productChannel, moneyChannel);
        Storehouse storehouse = (Storehouse) builder.initializeStorehouse(productChannel, moneyChannel);
        Dealer dealer = (Dealer) builder.initializeDealer(productChannel, moneyChannel);
        Seller seller = (Seller) builder.initializeSeller(productChannel, moneyChannel);
        Customer customer = (Customer) builder.initializeCustomer(productChannel, moneyChannel);

        farmer.createProduct(ProductType.Milk);
        farmer.sendRequest();
        System.out.println(customer.buy(ProductType.Milk, seller));

        new TransactionChain().generateReport("fullSystemReport.txt");
        seller.generateReport("sellerReports");
        customer.getProduct().generateReport("productReport");

    }
}
