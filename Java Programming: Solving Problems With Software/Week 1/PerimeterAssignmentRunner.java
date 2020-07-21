import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // A1 - Q1
        // Start with numOfPoints = 0
        int numOfPoints = 0;
        // For each point Pt in the shape,
        for (Point Pt : s.getPoints()) {
            // Count the points
            numOfPoints = numOfPoints + 1;
        }
        // numOfPoints is the answer
        return numOfPoints;
    }

    public double getAverageLength(Shape s) {
        // A1 - Q3
        double totalLength = getPerimeter(s);
        int totalPoints = getNumPoints(s);
        double avgLength = totalLength / totalPoints;
        return avgLength;
    }

    public double getLargestSide(Shape s) {
        // A1 - Q5
        double largestSide = 0.0;
        // Start with pointA = the last point
        Point pointA = s.getLastPoint();
        // For each point pointB in the shape
        for (Point pointB : s.getPoints()) {
            // Calculate sideLengthA between pointA and newPoint 
            double sideLength = pointB.distance(pointA);
            // If sideLength > largestSide
            if (sideLength > largestSide) {
                // Update largestSide
                largestSide = sideLength;
            }
            // Update pointA to newPoint
            pointA = pointB;
        } 
        // largestSide is the answer
        return largestSide;
    }
    public double getLargestX(Shape s) {
        // A1 - Q7
        // Initialise largestX variable
        double largestX = 0.0;
        // Previous point is the last point
        Point prevPt = s.getLastPoint();
        // For each point PtX in the shape
        for (Point PtX : s.getPoints()) {
        // Get X value of PtX
        double x1 = PtX.getX();
            // Compare it to firstPt
            if (x1 > largestX){
            largestX = x1;
            }
        // Update PtX to prevPt
        PtX = prevPt;
       }
        return largestX;    
    }

    public double getLargestPerimeterMultipleFiles() {
        // A2 - Q1
        // Create variable to store largest perimeter
        double largestPerimeter = 0.0;
        // Create DirectoryResource
        DirectoryResource dr = new DirectoryResource();
        // For each file f in the directory
        for (File f : dr.selectedFiles() ) {
        // Convert file into a FileResource
        FileResource fr = new FileResource(f);
        // Create shape from file fr
        Shape s = new Shape (fr);
        // Run getPerimeter on the file, store it as perimeter
        double perimeter = getPerimeter(s);
            // If perimeter > largestPerimeter, largestPerimeter = perimeter
            if (perimeter > largestPerimeter) {
                largestPerimeter = perimeter;
            }
        }
        // Return largestPerimeter over all the files
        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // A2 - Q3
        // Create Directory Resource
        DirectoryResource dr = new DirectoryResource();
        // Create temporary file variable to contain nothing
        File temp = null;
        // Create largest perimeter variable
        double largestPerimeter = 0.0;
        // For each file f in the directory
        for (File f : dr.selectedFiles()) {
        // Create a file resource
        FileResource fr = new FileResource(f);
        // Create a shape from file
        Shape s = new Shape(fr);
        // Calculate perimeter
        double perimeter = getPerimeter(s);
            // If perimeter > largest perimeter
            if (perimeter > largestPerimeter) {
            // Update largestPerimeter to be the new perimeter
            largestPerimeter = perimeter;
            // Update File temp to be current file
            temp = f;
           }
        
        }   
        // Return the name of the file temp
        return temp.getName();
    }
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        // A1 - Q2
        System.out.println(getNumPoints(s));
        // A1 - Q4
        System.out.println(getAverageLength(s));
        // A1 - Q6
        System.out.println(getLargestSide(s));
        // A1 - Q8
        System.out.println(getLargestX(s));
    }
    
    public void testPerimeterMultipleFiles() {
        // A2 - Q2
        // Print the largest perimeter out of the selected files
        System.out.println(getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        // A2 - Q4
        // Print the result of getFileWithLargestPerimeter
        System.out.println(getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
