package products;

import certificates.Certificate;
import chain.Report;
import chain.ReportGenerator;
import parties.Party;
import products.params.MeatParamsStrategy;
import products.params.MilkParamsStrategy;
import products.state.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents product
 */
public class Product{

    /**
     * Certificate of product
     */
    private Certificate certificate;

    /**
     * Type of product
     */
    private final ProductType type;

    /**
     * Name of product
     */
    private String name;

    /**
     * Price of product
     */
    private int price;

    /**
     * State of product
     */
    private State state;

    /**
     * Storing temperature
     */
    private int temperature;

    /**
     * Storing time
     */
    private int storageTime;

    /**
     * Array of reports
     */
    private List<Report> reports;

    /**
     * Constructor for creating a new product
     * @param type
     */
    public Product(ProductType type) {
        this.type = type;
        this.state = new InProductionState(this);
        this.reports = new ArrayList<>();
    }

    /**
     * This operation does Farmer
     */
    public void setProductParams(){
        switch (this.type){
            case Milk:
                new MilkParamsStrategy().setParams(this);
                break;
            case Meat:
                new MeatParamsStrategy().setParams(this);
                break;
        }
    }

    public void nextTick(Party party){
        state.nextTick(party);
    }

    public void nextState(){
        state.nextState();
    }

    /**
     * Show information about product
     * @return
     */
    public String toString(){
        return "===Produkt===" +
                "\nType: " + getType() +
                "\nName: " + getName() +
                "\nPrice: " + getPrice() +
                "\nTemp: " + getTemperature() +
                "\nStorage time: " + getStorageTime() +
                "\nState: " + getState().getType().toString() + "\n";
    }

    /**
     * @return type
     */
    public ProductType getType() {
        return type;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set product name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Set ptoduct price
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return certificate
     */
    public Certificate getCertificate() { return certificate;
    }

    /**
     * Set product certificate
     * @param certificate
     */
    public void setCertificate(Certificate certificate){
        if(certificate != null){
            this.certificate = certificate;
        }
    }

    /**
     * Set product state
     * @param state
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * @return state
     */
     public State getState() {
        return state;
    }

    /**
     * @return temperature
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * Set product temperature
     * @param temperature
     */
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    /**
     * @return storage time
     */
    public int getStorageTime() {
        return storageTime;
    }

    /**
     * Set product storage time
     * @param storageTime
     */
    public void setStorageTime(int storageTime) {
        this.storageTime = storageTime;
    }

    /**
     * @return array of reports
     */
    public List<Report> getReports() { return reports; }

    /**
     * Add new report
     * @param report
     */
    public void addReport(Report report) { reports.add(report); }

    /**
     * Report generation
     * @param filePath
     */
    public void generateReport(String filePath) {
        ReportGenerator generator = new ReportGenerator();
        generator.generateReport(filePath, reports);
    }
}


