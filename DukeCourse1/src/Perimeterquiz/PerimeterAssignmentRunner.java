package Perimeterquiz;

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
        // Put code here
    	int NumPoints = 0;
    	for (Point currPt : s.getPoints()) {
    		NumPoints = NumPoints + 1;
    	}
        return NumPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
    	double Peri = getPerimeter(s);
    	int Num = getNumPoints(s);
    	double Avglen = Peri/Num;
        return Avglen;
    }

    public double getLargestSide(Shape s) {
        // Put code here
    	double LrgSide = 0.0;
    	Point prevPt = s.getLastPoint();
    	for (Point currPt : s.getPoints()) {
    		double currDist = prevPt.distance(currPt);
    		if (currDist > LrgSide) {
    			LrgSide = currDist;
    		}
    		prevPt = currPt;
    	}
        return LrgSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double LrgX = 0;
    	for (Point currPt : s.getPoints()) {
    		int newX = currPt.getX();
    		if (newX > LrgX) {
    			LrgX = newX;
    		}
    	}
        return LrgX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
    	double LrgPeri = 0.0;
    	DirectoryResource dr = new DirectoryResource();
    	for (File f : dr.selectedFiles()) {
    		FileResource fr = new FileResource(f);
    		Shape s = new Shape(fr);
    		double Peri = getPerimeter(s);
    		if (Peri > LrgPeri) {
    			LrgPeri = Peri;
    		}
    	}
        return LrgPeri;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
    	double LrgPeri = 0.0;
        File temp = null;    // replace this code
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
        	FileResource fr = new FileResource(f);
        	Shape s = new Shape(fr);
        	double peri = getPerimeter(s);
        	if (peri > LrgPeri) {
        		LrgPeri = peri;
        		temp = f;
        	}
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int NumPoint = getNumPoints(s);
        System.out.println("NumPoints = " + NumPoint);
        double Avglen = getAverageLength(s);
        System.out.println("Avglen = " + Avglen);
        double LrgSide = getLargestSide(s);
        System.out.println("Largest side = " + LrgSide);
        double LrgX = getLargestX(s);
        System.out.println("Largest X = " + LrgX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
    	System.out.println(getLargestPerimeterMultipleFiles());
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
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
//        pr.testFileWithLargestPerimeter();
//        pr.testPerimeterMultipleFiles();
    }
}
