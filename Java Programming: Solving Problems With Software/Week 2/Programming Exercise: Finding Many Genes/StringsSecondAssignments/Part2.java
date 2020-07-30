
/**
 * Part2 of StringsSecondAssignments
 * 
 * @author (Kanishk Rathod) 
 * @version (30/07/2020)
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        // Declare int occurrences as the number of times stringa occurs in stringb.
        int occurrences = 0;
        // Declare and initialise startIndex as first occurrence of stringa
        int startIndex = 0;
        // Repeat steps
        while (true) {
            // Search for occurrence of stringa in stringb
            startIndex = stringb.indexOf(stringa, startIndex);
            // If startIndex = -1, break the loop
            if (startIndex == -1) {
                break;
            }
            // Add 1 to the occurrences counter i.e. since new occurrence of stringa is found
            occurrences = occurrences + 1;
            // Update startIndex to be startIndex + length of stringa
            startIndex = startIndex + stringa.length();
        }
        return occurrences;
    }

    public void testHowMany() {
        // Example test case in the assignment
        String stringb = "ATGAACGAATTGAATC";
        String stringa = "GAA";
        System.out.println(stringa + " occurs " + howMany(stringa, stringb) + " times in " + stringb);

        stringb = "ATAAAA";
        stringa = "AA";
        System.out.println(stringa + " occurs " + howMany(stringa, stringb) + " times in " + stringb);
    }
}
