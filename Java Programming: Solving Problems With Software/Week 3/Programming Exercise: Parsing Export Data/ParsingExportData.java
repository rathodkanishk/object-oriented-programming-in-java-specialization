
/**
 * Week 3 Programming Exercise: Parsing Export Data
 * 
 * @author (Kanishk Rathod) 
 * @version (08/08/2020)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExportData {
    // Part 1    
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();       
        // Test Part 2
        System.out.println(countryInfo(parser, "Germany"));
        System.out.println("---------------------------------");
        // Test Part 3
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "gold", "diamonds");
        System.out.println("---------------------------------");
        //Test Part 4
        parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "gold"));
        System.out.println("---------------------------------");
        // Test Part 5
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999");      
    }
    
    // Part 2 
    public String countryInfo(CSVParser parser, String country) {
        // Initialise return string variable
        String info = "NOT FOUND";
        // For each row in the CSV file
        for (CSVRecord record : parser ) {
            // Look at "Country" column
            String check = record.get("Country");
            // If the record contains "country"
            if (check.contains(country)) {
                // Get its Exports
                String exports = record.get("Exports");
                // Get its Value
                String value = record.get("Value (dollars)");
                // Set info to the format as prescribed
                info = country + ": " + exports + ": " + value;
            }
        }
        return info;
    }
    
    // Part 3
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        // For each row in the CSV File
        for (CSVRecord record: parser) {
            // Look at the exports column
            String export = record.get("Exports");
            // If the record contains exportItem1 AND exportItem2
            if (export.contains(exportItem1) && export.contains(exportItem2)) {
                // Get name of the country
                String country = record.get("Country");
                // Print name of the country
                System.out.println(country);
            }
        }   
    } 
    
    // Part 4
    public int numberOfExporters(CSVParser parser, String exportItem1) {
        // Initialise return variable
        int count = 0;
        // For each row in the CSV file
        for (CSVRecord record : parser) {
            // Look at the exports column
            String export = record.get("Exports");
            // If the record contains exportItem1
            if (export.contains(exportItem1)) {
                // Increase count by 1
                count += 1;
            }
        }
        return count;
    }
    
    //Part 5
    public void bigExporters(CSVParser parser, String amount) {
        // For each row in the CSV file
        for (CSVRecord record: parser) {
            // Look at the Value (dollars) column
            String value = record.get("Value (dollars)");
            // If value > amount
            if (value.length() > amount.length()) {
                //Print the name of the country and its value
                String country = record.get("Country");
                System.out.println(country + " " + value);
            }
        }
    }
}
