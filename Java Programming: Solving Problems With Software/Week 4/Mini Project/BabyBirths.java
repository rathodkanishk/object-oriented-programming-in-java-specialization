
/**
 * Week 4 - Mini Project on BabyBirths
 * 
 * @author (Kanishk Rathod) 
 * @version (24/08/2020)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void printNames() {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) + " Gender " + rec.get(1) + " Num Born " + rec.get(2));
            }
        }
    }

    // PART 1 //
    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalGirls = 0;
        int totalBoys = 0;
        int totalNames = 0;
        int girlsNames = 0;
        int boysNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            totalNames += 1;
            if (rec.get(1).equals("F")) {
                totalGirls += numBorn;
                girlsNames += 1;
            }
            else {
                totalBoys += numBorn;
                boysNames += 1;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total boys = " + totalBoys);
        System.out.println("total names = " + totalNames);
        System.out.println("girls names = " + girlsNames);
        System.out.println("boys names = " + boysNames);
    }

    public void testTotalBirths() {
        FileResource fr = new FileResource("us_babynames_by_year/yob1905.csv");
        totalBirths(fr);
    }

    // PART 2 //
    public int getRank(int year, String name, String gender) {
        int rankF = 0;
        int rankM = 0;
        int rank = -1;
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        for (CSVRecord record : fr.getCSVParser(false)) {
            // If the gender of the record is F, increase rankF by 1
            if (record.get(1).equals("F")) {
                rankF +=1;
            }
            // Otherwise, increase rankM by 1
            else {
                rankM +=1;                
            }
            // If the record contains the name and the gender
            if (record.get(0).equals(name) && record.get(1).equals(gender)) {                
                // If the gender is F, set rank as rankF
                if (gender.equals("F")) {
                    rank = rankF;
                }
                // Otherwise set rank as rankM
                else {
                    rank = rankM;
                }                
            }
        }        
        return rank;        
    }

    // PART 3 //
    public String getName(int year, int rank, String gender) {
        int rankF = 0;
        int rankM = 0;
        String nameFound = "NO NAME";
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        for (CSVRecord record : fr.getCSVParser(false)) {
            // If the gender of the record is F, increase rankF by 1
            if (record.get(1).equals("F")) {
                rankF +=1;
            }
            // Otherwise, increase rankM by 1
            else {
                rankM +=1;                
            }
            // If gender is F & rankF equals rank , OR if gender is M & rankM equals rank
            if (gender.equals("F") && rankF == rank) {
                nameFound = record.get(0);
            }
            if (gender.equals("M") && rankM == rank) {
                nameFound = record.get(0);
            }            
        }
        return nameFound;
    }

    // PART 4 //
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + " if they were born in " + newYear);
    }

    // PART 5 //
    public int yearOfHighestRank(String name, String gender) {
        int year = -1;
        int rank = -1;
        DirectoryResource dr = new DirectoryResource();        
        // For the files selected
        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            fileName = fileName.replaceAll("[^\\d]", "");
            int currYear = Integer.parseInt(fileName); 
            FileResource fr = new FileResource(f);
            int currRank = -1;
            // Go through each record
            for (CSVRecord rec : fr.getCSVParser(false)) {
                // If the record contains the name and gender, store its rank as currRank
                if (rec.get(0).equals(name) && rec.get(1).equals(gender)) {                    
                    currRank = getRank(currYear, name, gender);                    
                }                
            }            
            // If rank is -1 and current rank is NOT -1, update rank to current rank, year to current year                        
            if (rank == -1 && currRank != -1) {
                rank = currRank;
                year = currYear;
            }
            // If current rank is lesser than rank AND current rank is not -1, update rank and year to current
            if (currRank < rank && currRank != -1) {
                rank = currRank;
                year = currYear;
            }
        }        
        return year;        
    }

    // PART 6 //
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        double total = 0.0;
        int count = 0;
        // For the files selected
        for (File f : dr.selectedFiles()) {
            // Select year based on the file name
            String fileName = f.getName();
            fileName = fileName.replaceAll("[^\\d]", "");
            int yob = Integer.parseInt(fileName);            
            // Create a file resource out of it
            FileResource fr = new FileResource(f);
            int rank = -1;
            count += 1;
            // For each record in the file
            for (CSVRecord rec : fr.getCSVParser()) {
                if (rec.get(0).equals(name) && rec.get(1).equals(gender)) {
                    // Set rank as the rank of the name in the file
                    rank = getRank(yob, name, gender);                    
                    // If the rank is NOT -1, add it to total
                    if (rank != -1) {
                        total += rank;
                    }
                    // If the rank is -1, return -1.0
                    if (rank == -1) {
                        return -1.0;
                    }
                }
            }
        }        
        double avgRank = total / count;        
        return avgRank;
    }

    // PART 7 //
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year + ".csv");
        int runningTotal = 0;
        // For each record in the file
        for (CSVRecord rec : fr.getCSVParser(false)) {
            // If the name matches, return the runningTotal
            if (rec.get(0).equals(name) && rec.get(1).equals(gender)) {
                return runningTotal;
            }
            // If the gender matches, add number of births to running total
            if (rec.get(1).equals(gender)) {
                int births = Integer.parseInt(rec.get(2));                
                runningTotal += births;
            }            
        }
        // If its an error, return -1
        return -1;
    }

    // I used this object to test the other methods, edit as per your wish //
    public void testObjects() {
        System.out.println("Mason is rank " + getRank(2012,"Mason","M"));  
        System.out.println("Rank 2 is " + getName(2012,2,"M"));
        whatIsNameInYear("Isabella", 2012, 2014, "F");
    }
}
