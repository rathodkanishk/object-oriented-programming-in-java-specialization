
/**
 * Part2 of StringsFirstAssignments
 * 
 * @author (Kanishk Rathod) 
 * @version (25/07/2020)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
                // If the first character of dna is upper case, startCodon and stopCodon are upper case
        if (Character.isUpperCase(dna.charAt(0))) {
            startCodon = startCodon.toUpperCase();
            stopCodon = stopCodon.toUpperCase();
        }
        // Else the startCodon and stopCodon are lower case
        else {
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }
        // Find index position of startCodon
        int startIndex = dna.indexOf(startCodon);
        // If there is no startCodon return the empty string ""
        if (startIndex == -1) {
            return "";
        }
        // Find the index position of stopCodon appearing after startIndex
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        // If there is no stopCodon return the empty string ""
        if (stopIndex == -1) {
            return "";
        }
        // Find the substring between startCodon and stopCodon inclusive
        result = dna.substring(startIndex, stopIndex + 3);
        // If the length of the substring between startCodon and stopCodon is a multiple of 3
        if (result.length() % 3 == 0) {
            // Then return the substring that starts with startCodon and ends with that stopCodon
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
        String startCodon = "ATG";
        String stopCodon = "TAA";
        System.out.println("DNA strand is " + dna);
        System.out.println("Gene is " + findSimpleGene(dna, startCodon, stopCodon));
        // DNA with no "TAA"
        dna = "ACGTACATGGACGACCAT";
        System.out.println("DNA strand is " + dna);
        System.out.println("Gene is " + findSimpleGene(dna, startCodon, stopCodon));
        // DNA with no "ATG" or "TAA"
        dna = "ACGTACGACGACCAT";
        System.out.println("DNA strand is " + dna);
        System.out.println("Gene is " + findSimpleGene(dna, startCodon, stopCodon));
        // DNA with "ATG", "TAA" and the substring is not a multiple of 3
        dna = "ATGACGTATAACATGGACGACCAT";
        System.out.println("DNA strand is " + dna);
        System.out.println("Gene is " + findSimpleGene(dna, startCodon, stopCodon));
        // DNA with "ATG", "TAA" and the substring is a multiple of 3, lower case dna
        dna = "atgacgtagtaacatggacgacct";
        System.out.println("DNA strand is " + dna);
        System.out.println("Gene is " + findSimpleGene(dna, startCodon, stopCodon));  
        // DNA same as above but upper case
        dna = "ATGACGTATTAACATGGACGACCAT";
        System.out.println("DNA strand is " + dna);
        System.out.println("Gene is " + findSimpleGene(dna, startCodon, stopCodon));         
    }
}
