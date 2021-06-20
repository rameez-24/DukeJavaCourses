
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

package module3;

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
    	 records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
    	 FileResource fr = new FileResource(filename);
    	 for (String line : fr.lines()) {
    		 LogEntry entry = WebLogParser.parseEntry(line);
    		 records.add(entry);
    	 }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs() {
    	 ArrayList<String> uniqueIP = new ArrayList<String>();
    	 for (LogEntry le : records) {
    		 String ip = le.getIpAddress();
    		 if (!uniqueIP.contains(ip)) {
    			 uniqueIP.add(ip);
    		 }
    	 }
    	 return uniqueIP.size();
     }
     
     public void printAllHigherThanNum(int num) {
    	 for (LogEntry le : records) {
    		 int status = le.getStatusCode();
    		 if (status>num) {
    			 System.out.println(le);
    		 }
    	 }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
    	 ArrayList<String> uniqueIP = new ArrayList<String>();
    	 for (LogEntry le : records) {
    		 Date d = le.getAccessTime();
    		 String date = d.toString().substring(4,10);
    		 if (date.equals(someday)) {
    			 String ip = le.getIpAddress();
    			 if (!uniqueIP.contains(ip)) {
    				 uniqueIP.add(ip);
    			 }
    		 }
    	 }
    	 return uniqueIP;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
    	 int count = 0;
    	 ArrayList<String> uniqueIP = new ArrayList<String>();
    	 for (LogEntry le : records) {
    		 int status = le.getStatusCode();
    		 if (status >= low && status <= high && !uniqueIP.contains(le.getIpAddress())) {
    			 count++;
    			 uniqueIP.add(le.getIpAddress());
    		 }
    	 }
    	 return count;
     }
     
     public HashMap<String,Integer> countVisitsPerIP() {
    	 HashMap<String, Integer> counts = new HashMap<String, Integer>();
    	 for (LogEntry le : records) {
    		 String ip = le.getIpAddress();
    		 if (! counts.containsKey(ip)) {
    			 counts.put(ip,1);
    		 } else {
    			 counts.put(ip, counts.get(ip)+1);
    		 }
    	 }
    	 return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String,Integer> counts) {
    	 int maxVal = 0;
    	 for (String ip : counts.keySet()) {
    		 int val = counts.get(ip);
    		 if (val>maxVal) {
    			 maxVal = val;
    		 }
    	 }
    	 return maxVal;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts) {
    	 ArrayList<String> maxIP = new ArrayList<String>();
    	 int maxVal = mostNumberVisitsByIP(counts);
    	 for (String ip : counts.keySet()) {
    		 int val = counts.get(ip);
    		 if (val==maxVal) {
    			 maxIP.add(ip);
    		 }
    	 }
    	 return maxIP;
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
    	 HashMap<String, ArrayList<String>> dayIPs = new HashMap<String, ArrayList<String>>();
    	 for (LogEntry le : records) {
    		 String ip = le.getIpAddress();
    		 String date = le.getAccessTime().toString().substring(4,10);
    		 if (! dayIPs.containsKey(date)) {
    			 ArrayList<String> IPs = new ArrayList<String>();
    			 IPs.add(ip);
    			 dayIPs.put(date, IPs);
    		 } else {
    			 ArrayList<String> IPs = dayIPs.get(date);
    			 IPs.add(ip);
    			 dayIPs.put(date, IPs);
    		 }
    	 }
    	 return dayIPs;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dayIPs) {
    	 int maxVal = 0;
    	 String maxDay = null;
    	 for (String day : dayIPs.keySet()) {
    		 int val = dayIPs.get(day).size();
    		 if (val > maxVal) {
    			 maxVal = val;
    			 maxDay = day;
    		 }
    	 }
    	 return maxDay;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dayIPs, String day) {
    	 ArrayList<String> maxIPs = new ArrayList<String>();
    	 ArrayList<String> allIPs = dayIPs.get(day);
    	 HashMap<String,Integer> iPCounts = new HashMap<String,Integer>();
    	 for (int i=0; i<allIPs.size(); i++) {
    		 String ip = allIPs.get(i);
    		 if (! iPCounts.containsKey(ip)) {
    			 iPCounts.put(ip, 1);
    		 } else {
    			 iPCounts.put(ip, iPCounts.get(ip)+1);
    		 }
    	 }
    	 int maxVal = 0;
    	 for (String ip : iPCounts.keySet()) {
    		 int val = iPCounts.get(ip);
    		 if (val > maxVal) {
    			 maxVal = val;
    		 }
    	 }

    	 for (String ip : iPCounts.keySet()) {
    		 if (iPCounts.get(ip) == maxVal) {
    			 maxIPs.add(ip);
    		 }
    	 }
    	 return maxIPs;
     }
     
}
