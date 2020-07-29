
/**
 * Part3 of StringsFirstAssignments
 * 
 * @author (Kanishk Rathod) 
 * @version (25/07/2020)
 */
public class Part3 {
    public boolean twoOccurrences (String stringa, String stringb) {
    int occurrences = 0;
    // Find start index of the first occurrence of string a
    int firstOccurIndex = stringb.indexOf(stringa);
        // If firstOccurIndex > -1 then occurences += 1
        if (firstOccurIndex > -1) {
            occurrences = occurrences + 1;
        }
    // Look for 2nd occurence  
    // Create substring from firstOccurIndex to end of string
    String s = stringb.substring(firstOccurIndex + stringa.length(), stringb.length());
        // If stringa appears in string b AFTER firstOccurIndex (i.e. in String s); occurrences + 1;
        if (s.contains(stringa)) {
            occurrences = occurrences + 1;
        }
        // If occurrences >= 2; return true, else return false
        if (occurrences >= 2) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public String lastPart(String stringa, String stringb) {
    // Find first occurrences of stringa in stringb
    int occurrences = 0;
    int firstOccurIndex = stringb.indexOf(stringa);
        if (firstOccurIndex > -1) {
            occurrences = occurrences + 1;
        }
        // If stringa occurs in stringb, return part from stringb that follows stringa
        if (occurrences > 0) {
            String s = stringb.substring(firstOccurIndex + stringa.length(), stringb.length());
            return s;
        }
        // Else return stringb
        else {
            return stringb;
        }       
    }
    
    public void testing () {
        // Declare & initialise two string variables
        String a = "a";
        String b = "banana";
        // Call twoOccurrences on string
        boolean occurrences = twoOccurrences(a, b);
        // Call lastPart on string
        String last = lastPart(a, b);
        // Print the strings and the result of calling twoOccurrences
        System.out.println("String a is " + a);
        System.out.println("String b is " + b);
        System.out.println("Result of calling twoOccurences is " + occurrences);
        System.out.println("The part of the string after " + a + " in " + b + " is " + last);
        
        a = "ana";
        occurrences = twoOccurrences(a, b);
        last = lastPart(a, b);
        System.out.println("String a is " + a);
        System.out.println("String b is " + b);
        System.out.println("Result of calling twoOccurences is " + occurrences);
        System.out.println("The part of the string after " + a + " in " + b + " is " + last);
        
        a = "zoo";
        b = "forest";
        last = lastPart(a, b);
        occurrences = twoOccurrences(a, b);
        System.out.println("String a is " + a);
        System.out.println("String b is " + b);
        System.out.println("Result of calling twoOccurences is " + occurrences);
        System.out.println("The part of the string after " + a + " in " + b + " is " + last);                
    }
}
