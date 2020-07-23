# Programming Exercise: Finding a Gene and Web Links

A PDF of the programming exercise can be found in the **Resources** tab.

For files related to this assignment, visit the DukeLearnToProgram Project Resources page for this course: http://www.dukelearntoprogram.com/course2/files.php. Also linked in the **Resources** tab.

You can also find the frequently asked questions page for this course’s assignments on DukeLearnToProgram: http://www.dukelearntoprogram.com/course2/faq.php. Also linked in the **Resources** tab.

## Part 1: Finding a Gene - Using the Simplified Algorithm

This assignment is to write the code from the lesson from scratch by following the steps below. This will help you see if you really understood how to put the code together, and might identify a part that you did not fully understand. If you get stuck, then you can go back and watch the coding videos that go with this lesson again. We recommend you try this with many of the future Java coding examples before starting programming exercises.

Specifically, you should do the following:

1. Create a new Java project named StringsFirstAssignments. You can put all the classes for this programming exercise in this project.
2. Create a new Java Class named Part1. The following methods go in this class.
3. Write the method findSimpleGene that has one String parameter dna, representing a string of DNA. This method does the following:
  * Finds the index position of the start codon “ATG”. If there is no “ATG”, return the empty string.
  * Finds the index position of the first stop codon “TAA” appearing after the “ATG” that was found. If there is no such “TAA”, return the empty string.
  * If the length of the substring between the “ATG” and “TAA” is a multiple of 3, then return the substring that starts with that “ATG” and ends with that “TAA”.
4. Write the void method testSimpleGene that has no parameters. You should create five DNA strings. The strings should have specific test cases, such as: DNA with no “ATG”, DNA with no “TAA”, DNA with no “ATG” or “TAA”, DNA with ATG, TAA and the substring between them is a multiple of 3 (a gene), and DNA with ATG, TAA and the substring between them is not a multiple of 3. For each DNA string you should:
  * Print the DNA string.
  * See if there is a gene by calling findSimpleGene with this string as the parameter. If a gene exists following our algorithm above, then print the gene, otherwise print the empty string.
