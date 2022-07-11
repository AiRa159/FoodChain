package chain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportGenerator {

    /**
     * Report generator
     * @param filepath
     * @param reports
     */
    public void generateReport(String filepath, List<Report> reports){
        createFile(filepath);
        writeToFile(filepath, reports);
    }

    /**
     * Report generator
     * @param filepath
     * @param chain
     */
    public void generateReport(String filepath, TransactionChain chain){
        createFile(filepath);
        writeToFile(filepath, chain);
    }

    /**
     * File creation
     * @param filepath
     */
    private void createFile(String filepath){
        try {
            File myObj = new File(filepath);
            if (myObj.createNewFile()) {
                System.out.println("Report file created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Filling in the file
     * @param filepath
     * @param reports
     */
    private void writeToFile( String filepath, List<Report> reports){
        try {
            FileWriter myWriter = new FileWriter(filepath);
            if(reports.size() == 0){
                myWriter.write("Nothing to report");
            }else
                myWriter.write("Complete report (" + FoodChain.getInstance().getLocalDateTime() + ")\n");
            for (Report r:reports) {
                myWriter.write(r.toString());
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Filling in the file
     * @param filepath
     * @param chain
     */
    private void writeToFile( String filepath, TransactionChain chain){
        try {
            FileWriter myWriter = new FileWriter(filepath);
            if (chain.getFirstBlock() == null) {
                myWriter.write("Nothing to report");
            } else {
                myWriter.write("Complete FoodChain transaction report (" + FoodChain.getInstance().getLocalDateTime() + ")\n");
                TransactionChainIterator iterator = new TransactionChainIterator();
                while (iterator.hasNext()) {
                    iterator.next();
                    myWriter.write(iterator.currentBlock.toString());
                    myWriter.write("\n");
                }
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
