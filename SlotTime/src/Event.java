/**
 * @(#)Event.java
 *
 *
 * @author 
 * @version 1.00 2022/12/28
 */

import java.util.*; // Import the java.util package, which provides utility classes.

import java.io.*; // Import the java.io package for file I/O operations.

public class Event { // Start of the Event class definition.

    public String name; // Declare a public instance variable to store the name of the event creator.

    public String startTime; // Declare a public instance variable to store the start time of the event.

    public String endTime; // Declare a public instance variable to store the end time of the event.

    public String startDate; // Declare a public instance variable to store the start date of the event.

    public String endDate; // Declare a public instance variable to store the end date of the event.

    public String people; // Declare a public instance variable to store information about people associated with the event.

    public String location; // Declare a public instance variable to store the location of the event.

    public String details; // Declare a public instance variable to store additional details or a description of the event.

    public String title; // Declare a public instance variable to store the title of the event.

    public Event(String p, String t, String peo, String l, String de, String d, String ti, String et, String end) { 
        // Constructor for the Event class, with multiple parameters for initialization.

        startTime = t; // Assign the value of the "t" parameter to the "startTime" instance variable.

        startDate = d; // Assign the value of the "d" parameter to the "startDate" instance variable.

        details = de; // Assign the value of the "de" parameter to the "details" instance variable.

        people = peo; // Assign the value of the "peo" parameter to the "people" instance variable.

        location = l; // Assign the value of the "l" parameter to the "location" instance variable.

        name = p; // Assign the value of the "p" parameter to the "name" instance variable.

        title = ti; // Assign the value of the "ti" parameter to the "title" instance variable.

        endTime = et; // Assign the value of the "et" parameter to the "endTime" instance variable.

        endDate = end; // Assign the value of the "end" parameter to the "endDate" instance variable.
    }

    public void saveEvent() throws IOException {
        // Method to save the event's information to a file.

        String str = ""; //declare empty string to hold content
        
        //add the string to the content
        str += name + " " + title + " " + startTime + " " + endTime + " " + startDate + " " 
        	+ endDate + " " + location + " " + details + " " + people + "\n";

        String a = gatherFile(); // Call the gatherFile method to get existing file content.

        File file = new File("Events.txt"); // Create a new file object for "Events.txt."

        FileWriter fw = new FileWriter(file); // Create a FileWriter to write to the file.

        PrintWriter pw = new PrintWriter(fw); // Create a PrintWriter to write data.

        pw.print(a); // Write the existing content.

        pw.print(str); // Write the event information.

        pw.close(); // Close the PrintWriter.

        fw.close(); // Close the FileWriter.
    }

    public String gatherFile() throws IOException {
        // Method to gather existing content from the "Events.txt" file.

        String str = "";
        Scanner scanner = new Scanner(new File("Events.txt")); // Create a scanner to read from the file.

        while (scanner.hasNextLine()) {
            str += scanner.nextLine() + "\n"; // Read lines from the file and append them to the string.
        }

        return str; // Return the gathered file content as a string.
    }
}

