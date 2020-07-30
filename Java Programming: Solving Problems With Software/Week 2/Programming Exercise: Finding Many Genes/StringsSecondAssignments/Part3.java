
/**
 * Part3 of StringsSecondAssignments
 * 
 * @author (Kanishk Rathod) 
 * @version (30/07/2020)
 */
public class Part3 {
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
    
    public int countGenes(String dna) {
        // Declare count variable
        int count = 0;
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
            // Add 1 to count
            count = count + 1;
            // Update startIndex to just past the end of the gene
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return count;
    }
    
    public void testCountGenes() {
        // Test case from assignment
        String dna = "ATGTAAGATGCCCTAGT";
        System.out.println("DNA string: " + dna);
        System.out.println("Genes found: " + countGenes(dna));
        // DNA with many more genes
        dna = "ATGTAAGATGCCCTAGTATGTAAGATGCCCTAGTATGTAAGATGCCCTAGT";
        System.out.println("DNA string: " + dna);
        System.out.println("Genes found: " + countGenes(dna));
        // DNA with no ATG
        dna = "TAAGACGCCCTAGT";
        System.out.println("DNA string: " + dna);
        System.out.println("Genes found: " + countGenes(dna));
        // DNA with no stop codon
        dna = "ATGTACGATGCCCTCGT";
        System.out.println("DNA string: " + dna);
        System.out.println("Genes found: " + countGenes(dna));
    }
}
