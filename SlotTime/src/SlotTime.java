/**
 * @(#)SlotTime.java
 * SlotTime application
 *
 * @Ben Thomas
 * @version 1.00 2022/11/18
 */
import java.io.*; // Import for file input/output operations.
import java.util.*; // Import for various utilities.
import javax.swing.*; // Import for creating a graphical user interface.

public class SlotTime {
    // The main class that manages user accounts and events.

    public static ArrayList<createAccount> datahorse = new ArrayList<createAccount>();
    // The datahorse ArrayList stores user accounts and their associated events.

    public static void main(String[] args) throws IOException {
        // The main method responsible for running the program.

        ArrayList<String> usernames = getPasswords(); // Get user passwords from a file.
        ArrayList<String> passwords = getUsernames(); // Get usernames from a file.

        for (int i = 0; i < usernames.size(); i++) {
            // Loop to create user accounts and populate datahorse.

            createAccount account = new createAccount(usernames.get(i), passwords.get(i));
            datahorse.add(account);
        }

        // Various boolean flags to manage program flow.
        boolean stillEditingEvent = true;
        boolean choosing = true;
        boolean running = true;
        boolean loggedin = true;
        boolean stillEditing = true;
        boolean justLoggedIn = true;

        String choice, Username, Password;
        loadAccount yourAccount;

        // Arrays for event attributes and elements.
        String[] suffix = {"title", "end date", "location", "details", "start-date", "start-time", "end-time", "members"};
        String[] elements = new String[8];

        while (running) {
            // Main loop to manage program options.

            choice = JOptionPane.showInputDialog("Welcome to SlotTime! To login, enter 'Login', to create a new account, enter 'register', or to exit, enter 'exit'. What would you like to do: ");

            if (choice.equalsIgnoreCase("login")) {
                // Login section.
                Username = JOptionPane.showInputDialog("Enter your username: ");
                Password = JOptionPane.showInputDialog("Enter your password: ");

                if (searchDataHorse(Username, Password, datahorse)) {
                    // If the username and password match an account in datahorse.
                    loggedin = true;
                    JOptionPane.showMessageDialog(null, "Welcome " + Username);
					justLoggedIn = true;
                    while (loggedin) {
                        // Loop to manage logged-in user options.

                        yourAccount = getyourAccount(Username);
                        yourAccount.loadEvent();
                        
                        if (justLoggedIn == true){
                       		yourAccount.printSchedule();
                        }
						justLoggedIn = false;
                        String newEventoption = JOptionPane.showInputDialog("Create a new event (type 'c') or to edit an event (type 'e') or delete an event (type 'd') or logout (type 'l') or veiw events (type 'v'): ");

                        
                        if (newEventoption.equals("c")) {
                            // Create a new event.
                            for (int i = 0; i < suffix.length; i++) {
                                if (i == 0 && yourAccount.Events.size() != 0) {
                                    choosing = true;
                                    while (choosing) {
                                        elements[i] = JOptionPane.showInputDialog("Enter the event " + suffix[i] + ": ");
                                        if (!yourAccount.validTitle(elements[i])) {
                                            choosing = false;
                                        } else {
                                            continue;
                                        }
                                    }
                                } else {
                                    elements[i] = JOptionPane.showInputDialog("Enter the event " + suffix[i] + ": ");
                                }
                            }

                            Event ev = new Event(Username, elements[5], elements[7], elements[2], elements[3], elements[4], elements[0], elements[6], elements[1]);
                            ev.saveEvent();
                        } 
                        else if (newEventoption.equals("d")) {
                            // Delete an event.
                            String eventsContainer = "";
                            for (int i = 0; i < yourAccount.Events.size(); i++) {
                                Event eventInstance = yourAccount.Events.get(i);
                                eventsContainer += eventInstance.title + " ";
                            }
                            String eventTitle = JOptionPane.showInputDialog(eventsContainer + "Enter the title of the event you would like to delete: ");
                            while (!yourAccount.eventExist(eventTitle)){
                           		eventTitle = JOptionPane.showInputDialog(eventsContainer + "Enter the title of the event you would like to delete: ");
                            }
                            yourAccount.updateFile(eventTitle, Username);
                            yourAccount.deleteEvent(eventTitle);
                        } 
                        else if (newEventoption.equals("v")){
                        	yourAccount.printSchedule();
                        	String keepViewing = JOptionPane.showInputDialog("Would you like to keep veiwing events 'n' for no any other key for yes: ");
                        	while (!keepViewing.equals("n")){
                        		yourAccount.printSchedule();
                        		keepViewing = JOptionPane.showInputDialog("Would you like to keep veiwing events 'n' for no any other key for yes: ");	
                        	}
                        }
                        else if (newEventoption.equals("e")) {
                        	stillEditing = true;
                            // Edit an event.
                            while (stillEditing) {
                                String eventsOptions = yourAccount.printEvents();
                                String eventChoice = JOptionPane.showInputDialog(eventsOptions + "Which event would you like to edit: ");
                                while (!yourAccount.eventExist(eventChoice)){
                           			eventChoice = JOptionPane.showInputDialog(eventsOptions + "Which event would you like to edit: ");
                            	}
                                stillEditingEvent = true;

                                while (stillEditingEvent) {
                                    String str = "Which part of the event would you like to edit, enter something from the list: ";
                                    for (int i = 0; i < suffix.length; i++) {
                                        str += suffix[i] + " ";
                                    }
                                    str += ": ";

                                    String eventElementChoice = JOptionPane.showInputDialog(str);

                                    if (eventElementChoice.equals("members")) {
                                        String newInformation = JOptionPane.showInputDialog("What would you like to change about that: ");
                                        yourAccount.editEvent(eventElementChoice, eventChoice, newInformation);
                                        yourAccount.updateAfterEdit();
                                        yourAccount.printSchedule();
                                        newInformation = "";
                                    } else if (eventElementChoice.equals("title")) {
                                        choosing = true;
                                        while (choosing) {
                                            String newInformation = JOptionPane.showInputDialog("What would you like to change about that: ");
                                            if (!yourAccount.validTitle(newInformation)) {
                                                yourAccount.editEvent(eventElementChoice, eventChoice, newInformation);
                                                yourAccount.updateAfterEdit();
                                                choosing = false;
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Invalid input; try again");
                                                continue;
                                            }
                                        }
                                    } else {
                                        String newInformation = JOptionPane.showInputDialog("What would you like to change about that: ");
                                        yourAccount.editEvent(eventElementChoice, eventChoice, newInformation);
                                        yourAccount.updateAfterEdit();
                                        yourAccount.printSchedule();
                                    }

                                    String editingContinuation = JOptionPane.showInputDialog("Would you like to keep editing this event 'y' for yes and 'n' for no: ");
                                    if (editingContinuation.equals("n")) {
                                        stillEditingEvent = false;
                                    }
                                }

                                String editingWholeContinuation = JOptionPane.showInputDialog("Would you like to keep editing events 'n' for no, any other key for yes: ");
                                if (editingWholeContinuation.equals("n")) {
                                    stillEditing = false;
                                }
                            }
                        } else if (newEventoption.equals("l")) {
                            loggedin = false;
                        }
                        else{
                        	JOptionPane.showMessageDialog(null, "Invalid Choice");
                        	continue;
                        }
                    }
                }
                else{
                	JOptionPane.showMessageDialog(null, "Invalid Login information");
                }
            }

            while (choice.equalsIgnoreCase("register")) {
                // Register a new account.
                Username = JOptionPane.showInputDialog("Enter desired Username: ");
                Password = JOptionPane.showInputDialog("Enter desired password: ");

                for (int i = 0; i < datahorse.size(); i++) {
                    createAccount data = datahorse.get(i);
                    while (data.getUsername().equals(Username)) {
                        Username = JOptionPane.showInputDialog("Enter desired Username: ");
                        if (!Username.equals(data.getUsername())){
                            break;
                        }
                    }
                }

                createAccount account = new createAccount(Username, Password);
                account.genAccount();
                datahorse.add(account);
                JOptionPane.showMessageDialog(null, "Your account has been created");
                choice = "login";
            }

            if (choice.equalsIgnoreCase("exit")) {
                running = false;
            }
        }
    }

    public static ArrayList<String> getPasswords() throws IOException {
        // Load user passwords from a file.
        ArrayList<String> h = new ArrayList<String>();
        Scanner scanner = new Scanner(new File("Data.txt"));
        while (scanner.hasNext()) {
            h.add(scanner.nextLine());
        }
        return h;
    }

    public static ArrayList<String> getUsernames() throws IOException {
        // Load usernames from a file.
        ArrayList<String> h = new ArrayList<String>();
        Scanner scanner = new Scanner(new File("Passwords.txt"));
        while (scanner.hasNext()) {
            h.add(scanner.nextLine());
        }
        return h;
    }

    public static boolean searchDataHorse(String name, String password, ArrayList<createAccount> datahorse) {
        // Search the datahorse for a matching account.
        for (int i = 0; i < datahorse.size(); i++) {
            createAccount data = datahorse.get(i);
            if (data.getUsername().equals(name) && data.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static loadAccount getyourAccount(String Username) {
    	//This method gets your account
        boolean flag = false;
        int index = 0;
        createAccount yourAccount = null;
        for (int i = 0; i < datahorse.size(); i++) {
            createAccount data = datahorse.get(i);
            String name = data.getUsername();
            if (name.equals(Username)) {
                yourAccount = data;
                flag = true;
                index = i;
            }
        }
        loadAccount theAccount = new loadAccount(datahorse.get(index));
        return theAccount;
    }
}
