
/**
 * Part4 of StringsFirstAssignments
 * 
 * @author (Kanishk Rathod) 
 * @version (25/07/2020)
 */
import edu.duke.*;

public class Part4 {
    public void getLinks() {
    URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
    // Read the file in the link word by word
    for (String s : ur.words()) {
        // Convert string s to lower case
        String lowerCase = s.toLowerCase();
        // Check to see if youtube.com is in it
        if (lowerCase.contains("youtube.com")) {
            // If yes, find the double quote to the left and right of youtube.com
            int startIndex = s.indexOf("\"");
            int stopIndex = s.lastIndexOf("\"");
            // Print everything between the double quotes
            System.out.println(s.substring(startIndex + 1, stopIndex));
        }
    }
}
}