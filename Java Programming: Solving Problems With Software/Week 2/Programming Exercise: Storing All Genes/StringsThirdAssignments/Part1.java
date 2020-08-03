
/**
 * Part1 of StringsThirdAssignments
 * 
 * @author (Kanishk Rathod) 
 * @version (02/08/2020)
 */

// IMPORTANT: This version of the code is what I used to answer all the questions for the Week 2 Quiz. It was modified to answer the questions, and hence will be different from the expected solution for the programming exercise "Storing all Genes". 

import edu.duke.*;
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        // Search for 1st occurrence stopCodon after startIndex
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        // While currIndex is not equal to -1
        while (currIndex != -1) {
            // If stopIndex - currIndex % 3 == 0, break
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            }
            // Else search for next stopCodon after currIndex + 3
            else {
                // Search for stopCodon from currIndex +1
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        // If currIndex = -1, return length of dna i.e. if you exit loop return dna.length()
        return dna.length();
    }

    public String findGene(String dna, int where) {
        // Find the index of the first occurrence of the start codon "ATG"
        int startIndex = dna.indexOf("ATG", where);
        // If there is no "ATG", return the empty string
        if (startIndex == -1) {
            return "";
        }
        // Find the index of the first occurrence of the stop codon "TAA" (use findStopCodon)
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        // Find the index of the first occurrence of the stop codon "TAG" (use findStopCodon)
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        // Find the index of the first occurrence of the stop codon "TGA" (use findStopCodon)
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        // minIndex is the minimum of taaIndex, tagIndex, tgaIndex
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        // If there is no valid stop codon, return the empty string i.e. if minIndex is the length of dna
        if (minIndex == dna.length()) {
            return "";
        }
        // Return the gene formed from the "ATG" and the closest stop codon that is a multiple of 3 away
        return dna.substring(startIndex, minIndex + 3);
    }

    public void testFindGene() {
        int startIndex = 0;
        // DNA with no ATG and no stop codons
        String dna = "GACTGC";
        String gene = findGene(dna, startIndex);
        System.out.println("The DNA is " + dna);
        System.out.println("The gene found is " + gene);
        // DNA with ATG and no valid stop codons
        dna = "ATGGACTGCTAC";
        gene = findGene(dna, startIndex);
        System.out.println("The DNA is " + dna);
        System.out.println("The gene found is " + gene);
        // DNA with no ATG
        dna = "GACTGCTAATAGTGA";
        gene = findGene(dna, startIndex);
        System.out.println("The DNA is " + dna);
        System.out.println("The gene found is " + gene);
        // DNA with ATG and one valid stop codon
        dna = "ATGGACTGACTACTG";
        gene = findGene(dna, startIndex);
        System.out.println("The DNA is " + dna);
        System.out.println("The gene found is " + gene);
        // DNA with ATG and multiple valid stop codons
        dna = "ATGGACTAAGCTCTGAATA";
        gene = findGene(dna, startIndex);
        System.out.println("The DNA is " + dna);
        System.out.println("The gene found is " + gene);
    }

    public void testFindStopCodon() {
        // DNA with ATG and TAA, and a multiple of 3
        String dna = "ATGACCCAATATTAA";
        int startIndex = dna.indexOf("ATG");
        String stopCodon = "TAA";
        System.out.println("The stop codon " + stopCodon + " is on index " + findStopCodon(dna,startIndex,stopCodon));
        // DNA with ATG and TAA, not a multiple of 3, finds the next TAA
        dna = "ATGACCCAATATAAGTAA";
        System.out.println("The stop codon " + stopCodon + " is on index " + findStopCodon(dna,startIndex,stopCodon));
        // DNA with ATG but no TAA, returns length of dna
        dna = "ATGACCCAATAG";
        System.out.println("The stop codon " + stopCodon + " is on index " + findStopCodon(dna,startIndex,stopCodon));
    }

    public void printAllGenes(String dna) {
        // Set startIndex = 0
        int startIndex = 0;
        // Repeat the following steps
        while (true) {
            // Find the next gene after startIndex
            String currentGene = findGene(dna, startIndex);
            // If no gene was found, leave this loop
            if (currentGene.isEmpty()) {
                break;
            }
            // Print the gene found
            System.out.println(currentGene);
            // Update startIndex to just past the end of the gene
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }

    public StorageResource getAllGenes(String dna) {
        // Declare new StorageResource
        StorageResource sr = new StorageResource();
        // Set startIndex = 0
        int startIndex = 0;
        // Repeat the following steps
        while (true) {
            // Find the next gene after startIndex
            String currentGene = findGene(dna, startIndex);
            // If no gene was found, leave this loop
            if (currentGene.isEmpty()) {
                break;
            }
            // Store the gene found
            sr.add(currentGene);
            // Update startIndex to just past the end of the gene
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return sr;
    }

    public void testGetAllGenes() {
        // DNA string
        String dna = "ATGTAAATGTAGATGTGA";
        StorageResource sr = getAllGenes(dna);
        // For each string in testAllGenes print strings
        for (String s: sr.data()) {
            System.out.println(s);
        }
    }

    // PART 2 //

    public double cgRatio(String dna) {
        // Initialise count variables
        int cCount = 0;
        int gCount = 0;
        // Initialise cIndex and gIndex
        int cIndex = dna.indexOf("C",0);
        int gIndex = dna.indexOf("G",0);
        // Find C's in the DNA string
        while (cIndex != -1) {
            // Increase counter by 1
            cCount = cCount + 1;
            // Update cIndex to search from the next index in the string
            cIndex = dna.indexOf("C", cIndex + 1);
        }
        // Find G's in the DNA string
        while (gIndex != -1) {
            // Increase counter by 1
            gCount = gCount + 1;
            // Update cIndex to search from the next index in the string
            gIndex = dna.indexOf("G", gIndex + 1);
        }
        // cgRatio = (C's + G's)/Length of DNA string
        double cgRatio = (double) (cCount + gCount) / dna.length();
        return cgRatio;
    }

    public int countCTG(String dna) {
        // Initialise count variable
        int count = 0;
        // Search for CTG from index 0
        int ctgIndex = dna.indexOf("CTG", 0);
        // While CTGs are found
        while (ctgIndex != -1) {
            // Increase count by 1
            count = count + 1;
            // Update ctgIndex to be the index right after the new CTG found
            ctgIndex = dna.indexOf("CTG", ctgIndex + 3);
        }
        return count;
    }

    // Test method I used to test cgRatio and countCTG
    /*
    public void ctgTest() {
        String dna = "CTGCTGCTG";
        System.out.println("CTG count: " + countCTG(dna));
        System.out.println("cgRatio: " + cgRatio(dna)); 
    }
     */

    // PART 3 //

    public void processGenes(StorageResource sr) {
        // Declaring count variables, and longest gene length variable
        int longestGene = 0;
        int charCount = 0;
        int cgRatioCount = 0;
        // for strings in sr
        for (String s: sr.data()) {
            // Print all strings longer than 9 characters
            if (s.length() > 60) {
                charCount = charCount + 1;
                System.out.println("Gene longer than 60 characters: " + s);
            }
            // Print the strings in sr whose cgRatio > 0.35
            if (cgRatio(s) > 0.35) {
                cgRatioCount = cgRatioCount + 1;
                System.out.println("cGRatio > 0.35: " + s);
            }
            // If s > longestGene, update longestGene
            if (s.length() > longestGene) {
                longestGene = s.length();
            }
        }
        // Print the number of strings in sr longer than 9 characters
        System.out.println("Genes longer than 60 characters: " + charCount);
        // Print the number of strings in sr whose cgRatio > 0.35
        System.out.println("Genes with cgRatio > 0.35: " + cgRatioCount);
        // Print the length of the longest gene in sr
        System.out.println("Length of longest gene: " + longestGene);
    }

    public void testProcessGenes() {
        // Open the file and convert it to a string
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        // Ensure dna string is in uppercase before processing it
        dna = dna.toUpperCase();
        // Create StorageResource from the string created and run processGenes on it
        StorageResource sr = getAllGenes(dna);
        System.out.println("Number of genes found: " + sr.size());
        System.out.println("Number of times CTG appears: " + countCTG(dna));
        processGenes(sr);
    }
}
