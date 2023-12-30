/**
 * @(#)loadAccount.java
 *
 *
 * @author 
 * @version 1.00 2022/12/12
 */
import java.io.*;
import java.util.*;
import javax.swing.*;
public class loadAccount{
	public ArrayList<Event> Events = new ArrayList<Event>();
	public ArrayList<String> names;
	public createAccount account;
	//public int eventCount = Events.size();
    public loadAccount(createAccount a){
    	account = a;
   	}
    public void printSchedule(){
    	String str = "";
    	if (Events.size() == 0){
    		JOptionPane.showMessageDialog(null, "No events at this time");
    		return;
    	}
    	str += "Here are your upcoming events: ";
    	ArrayList<String> titles = new ArrayList<String>();
    	for (int i = 0; i < Events.size(); i++){
    		Event event = Events.get(i);
    		if (!titles.contains(event.title)){
	    		str += "Event title: " + event.title + " ";
	    		str += "Event start time: " + event.startTime + " ";
	    		str += "Event end time: " + event.endTime + " ";
	    		str += "Start date: " + event.startDate + " ";
	    		str += "End date: " + event.endDate + " ";
	    		str += "Event location: " + event.location + " ";
	    		str += "Event details: " + event.details + " ";
	    		str += "People attending the event: " + event.people;
	    		JOptionPane.showMessageDialog(null, str);
	    		str = "";
    		}
    		
    		else{
    			continue;
    		}
    	}	
    }
    public boolean validTitle(String input){
    	for (int i = 0; i < Events.size(); i++){
    		Event event = Events.get(i);
    		String testString = event.title;
    		if (testString.equals(input)){
    			return true;
    		}
    	}
    	return false;
    }
    public void loadEvent() throws IOException{
    	Scanner scanner = new Scanner(new File("Events.txt"));
    	while (scanner.hasNextLine()){
    		String names = "";
    		ArrayList<String> strings = new ArrayList<String>();
    		String a = scanner.nextLine();
    		String b[] = a.split(" ");
    		for (int i = 0; i < b.length; i++){
    			if (i < 8){
    				strings.add(b[i]);
    			}
    			else{
    				names += b[i] + " ";
    			}
    		}
    		strings.add(names);
    		names = "";
    		if (strings.get(0).equals(account.name)){
        		Event e = new Event(strings.get(0), strings.get(2), strings.get(8), strings.get(6), strings.get(7), strings.get(4), strings.get(1), strings.get(3), strings.get(5));
    			Events.add(e);
    		}
    	}
    }
    public boolean isEmpty(String ti){
    	for (int i = 0; i < Events.size(); i++){
    		Event e = Events.get(i);
    		String title = e.title;
    		if (title.equals(ti)){
    			return false;
    		}
    	}
    	return true;
    }
    public void deleteEvent(String t){
    	for (int i = 0; i < Events.size(); i++){
    		Event e = Events.get(i);
    		String title = e.title;
    		if (title.equals(t)){
    			Events.remove(e);
    		}
    	}
    }
    public void updateFile(String title, String name)throws IOException{
    	Scanner scanner = new Scanner(new File("Events.txt"));
    	String str = "";
    	while (scanner.hasNextLine()){
    		String line = scanner.nextLine();
    		if (!line.contains(title) || !line.contains(name)){
    			str += line + "\n";
    		}
    		else{
    			continue;
    		}
    	}
    	File file = new File("Events.txt");
    	FileWriter fw = new FileWriter(file);
    	PrintWriter pw = new PrintWriter(fw);
    	pw.print(str);
    	pw.close();
    	fw.close();
    }
    public void editEvent(String element, String title, String newdata){
    	String[] elements = {"title", "members", "location", "details", "start-date", "start-time", "end-time", "end-date"};
    	for (int i = 0; i < Events.size(); i++){
    		Event e = Events.get(i);
    		String t = e.title;
    		if (title.equals(t)){
    			if (element.equals(elements[0])){
    				e.title = newdata; 
    			}
    			else if (element.equals(elements[1])){
    				e.people = newdata;
    			}
    			else if (element.equals(elements[2])){
    				e.location = newdata;
    			}
    			else if (element.equals(elements[3])){
    				e.details = newdata;
    			}
    			else if (element.equals(elements[4])){
    				e.startDate = newdata;
    			}
    			else if (element.equals(elements[5])){
    				e.startTime = newdata;
    			}
    			else if (element.equals(elements[6])){
    				e.endTime = newdata;
    			}
    			else if (element.equals(elements[7])){
    				e.endDate = newdata;
    			}
    			else{
    				System.out.println("invalid input");
    			}
    		}
    	}
    }
   	public void updateAfterEdit() throws IOException{
   		String constructionString = "";
   		for (int i = 0; i < Events.size(); i++){
   			Event ev = Events.get(i);
   			constructionString +=  ev.name + " " + ev.title + " " + ev.startTime + " " + 
    		ev.endTime + " " + ev.startDate + " " + ev.endDate + " "
    		 + ev.location + " " + ev.details + " " + ev.people + "\n";
   		}
   		File file = new File("Events.txt");
   		FileWriter fw = new FileWriter(file);
   		PrintWriter pw = new PrintWriter(fw);
   		pw.println(constructionString);
   		pw.close();
    	fw.close();
    }
    
    public String printEvents(){
    	String str = "";
    	if (Events.size() == 0){
    		return null;
    	}
    	ArrayList<String> titles = new ArrayList<String>();
    	str += "Your events: ";
    	for (int i = 0; i < Events.size(); i++){
    		Event event = Events.get(i);
	    	str += event.title + " ";
    	}
    	return str;	
    }
    
    public boolean eventExist(String eventTitle){
    	for (int i = 0; i < Events.size(); i++){
    		Event event = Events.get(i);
    		if (event.title.equals(eventTitle)){
    			return true;
    		}
    	}
    	return false;
    }
}