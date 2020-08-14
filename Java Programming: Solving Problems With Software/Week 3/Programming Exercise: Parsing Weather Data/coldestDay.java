
/**
 * ParsingWeatherData Assignment (Week 3).
 * 
 * @author (Kanishk Rathod) 
 * @version (14/08/2020)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class coldestDay {
    // Part 1 //
    public CSVRecord coldestHourInFile(CSVParser parser) {
        // Start with smallestSoFar as nothing
        CSVRecord smallestSoFar = null;
        // For each row in the CSV File
        for (CSVRecord currentRow: parser) {
            // If largestSoFar is nothing, update to currentRow
            if (smallestSoFar == null) {
                smallestSoFar = currentRow;
            }
            // Else 
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                // Check if currentRow temp < largestSoFar    
                if (currentTemp < smallestTemp && currentTemp != -9999) {
                    // If so update smallestSoFar to currentRow
                    smallestSoFar = currentRow;                
                }
            }
        }
        // smallestSoFar is the answer
        return smallestSoFar;
    }

    public void testColdestInDay() {
        FileResource fr = new FileResource();
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + smallest.get("TemperatureF") + " at " + smallest.get("DateUTC"));
    }

    // Part 2 //
    public String fileWithColdestTemperature() {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource ();
        String filename = "";
        // Iterate over files
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // Use method to get smallest in file
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if (smallestSoFar == null) {
                smallestSoFar = currentRow;
                filename = f.getName();
            }
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                // Check if currentRow temp < largestSoFar    
                if (currentTemp < smallestTemp && currentTemp != -9999) {
                    // If so update smallestSoFar to currentRow
                    smallestSoFar = currentRow;
                    // If so update the filename to the current file's name
                    filename = f.getName();
                }
            }
        }
        // Get name of the file with lowest temperature
        return filename;
    }

    public void testFileWithColdestTemperature() {
        String filename = fileWithColdestTemperature();
        FileResource fr = new FileResource("nc_weather/2014/" + filename);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestHour = coldestHourInFile(parser);
        System.out.println("Coldest day was in file " + filename);
        System.out.println("Coldest temperature on that day was " + coldestHour.get("TemperatureF"));
        for (CSVRecord record : fr.getCSVParser()) {
            System.out.print(record.get("DateUTC"));
            System.out.print(": ");
            System.out.println(record.get("TemperatureF"));
        }
    }

    // Part 3 //
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        // lowest record variable
        CSVRecord lowestSoFar = null;
        // For each record in the parser
        for (CSVRecord currentRow : parser) {
            // If lowestSoFar is empty, current row is lowestHumidity
            if (lowestSoFar == null) {
                lowestSoFar = currentRow;
            }
            // Else
            else {
                if (lowestSoFar.get("Humidity").equals("N/A")) {
                    lowestSoFar = currentRow;
                }
                if (!currentRow.get("Humidity").equals("N/A") && !lowestSoFar.get("Humidity").equals("N/A")) {
                    int currentHumidity = Integer.parseInt(currentRow.get("Humidity"));
                    int lowestHumidity = Integer.parseInt(lowestSoFar.get("Humidity"));
                    // Check if currentRow humidity < lowestHumidity
                    if (currentHumidity < lowestHumidity) {
                        // If so update lowestSoFar to currentRow
                        lowestSoFar = currentRow;
                    }
                }
            }
        }
        // lowestSoFar is the answer
        return lowestSoFar;
    }

    public void testLowestHumidityInFile() {
        // Start with lines given in assignment
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        // Print the lowest humidity and the time it occurred
        System.out.print("Lowest Humidity was ");
        System.out.print(csv.get("Humidity"));
        System.out.print(" at ");
        System.out.print(csv.get("DateUTC"));        
    }

    // Part 4 //
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            if (lowestSoFar == null) {
                lowestSoFar = lowestHumidityInFile(fr.getCSVParser());
            }
            for (CSVRecord currentRow : parser) {
                if (!currentRow.get("Humidity").equals("N/A") && !lowestSoFar.get("Humidity").equals("N/A")) {
                    int currentHumidity = Integer.parseInt(currentRow.get("Humidity"));
                    int lowestHumidity = Integer.parseInt(lowestSoFar.get("Humidity"));
                    // Check if currentRow humidity < lowestHumidity
                    if (currentHumidity < lowestHumidity) {
                        // If so update lowestSoFar to currentRow
                        lowestSoFar = currentRow;
                    }
                }
            }
        }
        return lowestSoFar;
    }

    public void testLowestHumidityInManyFiles() {
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.print("Lowest Humidity was ");
        System.out.print(record.get("Humidity"));
        System.out.print(" at ");
        System.out.println(record.get("DateUTC"));
    }

    // Part 5 //
    public double averageTemperatureInFile(CSVParser parser) {
        int numOfDays = 0;
        double tempTotal = 0;
        for (CSVRecord currentRow : parser) {
            numOfDays += 1;
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            tempTotal = tempTotal + currentTemp;
        }
        double averageTemp = tempTotal / numOfDays;
        return averageTemp;
    }

    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.print("Average temperature in file is ");
        System.out.println(averageTemperatureInFile(parser));
    }

    // Part 6 //
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double temp = 0;
        int num = 0;
        double avgTemp = 0;
        for (CSVRecord currentRow : parser) {
            int currentHumidity = Integer.parseInt(currentRow.get("Humidity"));
            if (currentHumidity >= value) {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                temp = temp + currentTemp;
                num = num + 1;
            }
        }
        avgTemp = temp / num;
        if (num == 0) {
            avgTemp = 1;
        }
        return avgTemp;
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int value = 80;
        double avgTemp = averageTemperatureWithHighHumidityInFile(parser, value);
        if (avgTemp == 1) {
            System.out.println("No temperatures with that humidity");
        }
        else {
            System.out.print("Average Temp when high Humidity is ");
            System.out.println(avgTemp);
        }
    }
}