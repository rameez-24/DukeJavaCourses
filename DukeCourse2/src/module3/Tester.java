
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

package module3;

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile("testData/mod3/short-test_log");
    	la.printAll();
    }
    
    public void testUniqueIP() {
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile("testData/mod3/weblog2_log");
    	System.out.println(la.countUniqueIPs());
    }
    
    public void testPrintAllHigherThanNum() {
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile("testData/mod3/weblog1_log");
    	la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPOnDay() {
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile("testData/mod3/weblog2_log");
    	ArrayList<String> uniqueIP = la.uniqueIPVisitsOnDay("Sep 27");
    	for (String ip : uniqueIP) {
    		System.out.println(ip);
    	}
    	System.out.println(uniqueIP.size());
    }
    
    public void testUniqueIPsInRange() {
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile("testData/mod3/weblog2_log");
    	System.out.println(la.countUniqueIPsInRange(400,499));
    }
    
    public void testCountVisitsPerIP() {
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile("testData/mod3/short-test_log");
    	System.out.println(la.countVisitsPerIP());
    }
    
    public void testMostNumberVisitsByIP() {
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile("testData/mod3/weblog2_log");
    	System.out.println(la.mostNumberVisitsByIP(la.countVisitsPerIP()));
    }
    
    public void testIPsMostVisits() {
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile("testData/mod3/weblog2_log");
    	System.out.println(la.iPsMostVisits(la.countVisitsPerIP()));
    }
    
    public void testIPsForDays() {
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile("testData/mod3/weblog3-short_log");
    	System.out.println(la.iPsForDays());
    }
    
    public void testDayWithMostIPVisits() {
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile("testData/mod3/weblog2_log");
    	System.out.println(la.dayWithMostIPVisits(la.iPsForDays()));
    }
    
    public void testIPsWithMostVisitsOnDay() {
    	LogAnalyzer la = new LogAnalyzer();
    	la.readFile("testData/mod3/weblog2_log");
    	System.out.println(la.iPsWithMostVisitsOnDay(la.iPsForDays(), "Sep 29"));
    }
    
    public static void main(String[] args) {
    	Tester t = new Tester();
//    	t.testUniqueIP();
//    	t.testUniqueIPsInRange();
//    	t.testPrintAllHigherThanNum();
//    	t.testUniqueIPOnDay();
//    	t.testCountVisitsPerIP();
//    	t.testMostNumberVisitsByIP();
//    	t.testIPsMostVisits();
//    	t.testIPsForDays();
//    	t.testDayWithMostIPVisits();
    	t.testIPsWithMostVisitsOnDay();
    }
}
