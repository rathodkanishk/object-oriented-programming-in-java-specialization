# Programming Exercise: Calculating a Shape’s Perimeter

In these exercises, you will use the Shape and Point classes to answer questions about a Shape that is made up of a collection of points from the x-y plane, as shown in this lesson. The shape is defined by drawing a line between two adjacent points, for every pair of adjacent points, and also a line between the first and last point. Be sure to consult the documentation on DukeLearnToProgram to understand how the Shape, Point, DirectoryResource and FileResource classes work: http://www.dukelearntoprogram.com/course2/doc/javadoc/index.html?course=2.

# Assignment 1: Calculating information about shapes

In this assignment, you will complete the PerimeterAssignmentRunner class to calculate lots of interesting facts about shapes. This class has been started for you in the BlueJ project called assignmentPerimeter (go to: http://www.dukelearntoprogram.com/course2/files.php and download the Calculating the Perimeter of a Shape BlueJ project). This project also contains several data files. In addition, you will need to look at the documentation for the Shape class and the Point class.

**The PerimeterAssignmentRunner class already includes the following complete methods:**

- The getPerimeter method has one parameter s of type Shape. Given a shape, this method returns the perimeter of the shape.

- The testPerimeter method has no return value, hence its return type is void. This method is used to select a data file by using the FileResource class, create a shape based on the points from that data file, and then calculate the perimeter of the shape and output its value.

- The triangle method has no return value and creates a triangle that you can use to test the methods you will create in this assignment.

- The printFileNames method, which we will discuss in Assignment 2.

- The main method.

### For this assignment, you will add or modify several methods in the PerimeterAssigmentRunner class.

1. **Complete writing the method getNumPoints** that has one parameter s that is of type Shape. This method returns an integer that is the number of points in Shape s. Hint: You will need to iterate over all the points in the Shape S and count them.

2. **Add code in the method testPerimeter** to call getNumPoints and to print the result.

3. **Complete writing the method getAverageLength** that has one parameter s that is of type Shape. This method returns a number of type double that is the calculated average of all the sides’ lengths in the Shape S.

4. **Add code in the method testPerimeter** to call the method getAverageLength and to print out the result. Note if you were to select the file example1.txt, then the average side length should be 4.0.

5. **Complete writing the method getLargestSide** that has one parameter s that is of type Shape. This method returns a number of type double that is the longest side in the Shape S.

6. **Add code in the method testPerimeter** to call the method getLargestSide and to print out the result. Note if you were to select the file example1.txt, then the longest side should be 5.0.

7. **Complete writing the method getLargestX** that has one parameter s that is of type Shape. This method returns a number of type double that is the largest x value over all the points in the Shape s.

8. **Add code in the method testPerimeter** to call the method getLargestX and to print out the result. Note if you were to select the file example1.txt, then the longest side should be 4.0.
