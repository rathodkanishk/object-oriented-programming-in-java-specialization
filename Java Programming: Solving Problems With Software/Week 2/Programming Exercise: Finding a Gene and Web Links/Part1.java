
/**
 * Part1 of StringsFirstAssignments
 * 
 * @author (Kanishk Rathod) 
 * @version (25/07/2020)
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        // Find index position of start codon "ATG"
        int startIndex = dna.indexOf("ATG");
        // If there is no "ATG" return the empty string ""
        if (startIndex == -1) {
            return "";
        }
        // Find the index position of stop codon "TAA" appearing after startIndex
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
        // If there is no "TAA" return the empty string ""
        if (stopIndex == -1) {
            return "";
        }
        // Find the substring between "ATG" and "TAA"
        String result = dna.substring(startIndex, stopIndex + 3);
        // If the length of the substring between ATG and TAA is a multiple of 3
        if (result.length() % 3 == 0) {
            // Then return the substring that starts with ATG and ends with that TAA
            result = dna.substring(startIndex, stopIndex + 3);
        }
        else {
            result = "";
        }
        // Result is the answer
        return result;
    }
    public void testSimpleGene() {
        // Test 5 dna strings
        // DNA with no "ATG"
        String dna = "ACGTACGACTAAGACCAT";
        System.out.println("DNA strand is " + dna);
        System.out.println("Gene is " + findSimpleGene(dna));
        // DNA with no "TAA"
        dna = "ACGTACATGGACGACCAT";
        System.out.println("DNA strand is " + dna);
        System.out.println("Gene is " + findSimpleGene(dna));
        // DNA with no "ATG" or "TAA"
        dna = "ACGTACGACGACCAT";
        System.out.println("DNA strand is " + dna);
        System.out.println("Gene is " + findSimpleGene(dna));
        // DNA with "ATG", "TAA" and the substring is not a multiple of 3
        dna = "ATGACGTATAACATGGACGACCAT";
        System.out.println("DNA strand is " + dna);
        System.out.println("Gene is " + findSimpleGene(dna));
        // DNA with "ATG", "TAA" and the substring is a multiple of 3
        dna = "ATGACGTATTAACATGGACGACCAT";
        System.out.println("DNA strand is " + dna);
        System.out.println("Gene is " + findSimpleGene(dna));         
    }
}
