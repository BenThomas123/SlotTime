import java.util.*; // Import the java.util package for using utility classes.

import java.io.*; // Import the java.io package for file I/O operations.

public class createAccount { // Start of the createAccount class definition.

    public String name; // Declare a public instance variable to store the name of the user.

    public String password; // Declare a public instance variable to store the user's password.

    public createAccount(String n, String p) { // Constructor for the createAccount class with parameters for name and password.

        name = n; // Initialize the "name" instance variable with the provided name.

        password = p; // Initialize the "password" instance variable with the provided password.
    }

    public void genAccount() throws IOException { // Method to generate a user account and save it to files.

        String str = gatherfile(); // Call the gatherfile method to retrieve existing user data.

        File file = new File("Data.txt"); // Create a file object for "Data.txt."

        FileWriter fw = new FileWriter(file); // Create a FileWriter to write to the file.

        PrintWriter pw = new PrintWriter(fw); // Create a PrintWriter to write data.

        pw.print(str); // Write the existing user data to the file.

        pw.print(name); // Write the new user's name to the file.

        fw.close(); // Close the FileWriter.

        pw.close(); // Close the PrintWriter.

        String s = gatherfile2(); // Call the gatherfile2 method to retrieve existing password data.

        File f = new File("Passwords.txt"); // Create a file object for "Passwords.txt."

        FileWriter fwr = new FileWriter(f); // Create a FileWriter to write to the password file.

        PrintWriter pwr = new PrintWriter(fwr); // Create a PrintWriter to write password data.

        pwr.print(s); // Write the existing password data to the file.

        pwr.print(password); // Write the new user's password to the file.

        fwr.close(); // Close the FileWriter.

        pwr.close(); // Close the PrintWriter.
    }

    private String gatherfile() throws IOException { // Method to gather existing user data from "Data.txt."

        String data = ""; // Initialize an empty string to store user data.

        Scanner scanner = new Scanner(new File("Data.txt")); // Create a scanner to read from the user data file.

        while (scanner.hasNextLine()) { // Iterate through lines in the file.
            data += scanner.nextLine() + "\n"; // Read lines from the file and append them to the data string.
        }

        return data; // Return the gathered user data as a string.
    }

    private String gatherfile2() throws IOException { // Method to gather existing password data from "Passwords.txt."

        String data = ""; // Initialize an empty string to store password data.

        Scanner scanner = new Scanner(new File("Passwords.txt")); // Create a scanner to read from the password data file.

        while (scanner.hasNextLine()) { // Iterate through lines in the file.
            data += scanner.nextLine() + "\n"; // Read lines from the file and append them to the data string.
        }

        return data; // Return the gathered password data as a string.
    }

    public String getUsername() { // Method to retrieve the user's name.
        return name; // Return the user's name.
    }

    public String getPassword() { // Method to retrieve the user's password.
        return password; // Return the user's password.
    }
}
