package ro.ubbcluj.cs.garage.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Logger;

import ro.ubbcluj.cs.garage.service.GarageManager;

public class GarageUI {

	// Attributes
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private GarageManager manager;
	private Scanner input = new Scanner(System.in);

	// Methods
	public void setGarageManager(GarageManager garageManager) {
		this.manager = garageManager;

	}
	
	public void start(){
		while(true){
			LOGGER.info("Stating UI...");
			printMeniu();
			System.out.println("Alegeti o optiune:");
			int opt = input.nextInt();
			
			if(opt==0){
				System.out.println("Logged out.");
				break;
			}
			
			switch(opt){
			case 1:
				printTheClients();
				break;
			case 2:
				LOGGER.info("the second case called...");
				addClient();
				break;
			case 3:
				System.out.println();
				addCar();
				break;
			default:
				System.out.println("Optiune invalida!");
				break;
			}
		}
		input.close();
		LOGGER.info("UI stopped  Status:OK");
	}

	private void printTheClients() {
		manager.printTheClients();
		
	}

	private void printTheCars() {
		// TODO Auto-generated method stub
		
	}

	private void addClient() {
		System.out.println("~~~Adaugare Client~~~");
		System.out.println("Client format:id/name/day/month/year/password");
		
		/*System.out.println("ID:");
		String id = input.nextLine();
		System.out.println("Name:");
		String name = input.nextLine();
		System.out.println("Date(dd/mm/yyyy):");
		System.out.println("Day:");
		String day = input.nextLine();
		System.out.println("Month:");
		String month = input.nextLine();
		System.out.println("Year:");
		String year = input.nextLine();
		System.out.println("Password:");
		String password = input .nextLine();
		
		manager.addClient(Integer.parseInt(id),name,Integer.parseInt(day),Integer.parseInt(month),Integer.parseInt(year),password);
		*/
		
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		try {
			line = stdin.readLine();
		} catch (IOException e) {
			LOGGER.severe("Severe Error:"+e.getMessage());
			e.printStackTrace();
		}
		String[] in = line.split(" ");
		LOGGER.info("Adding client:"+in.toString());
		System.out.println(Integer.parseInt(in[0]));
		System.out.println(in[1]);
		System.out.println(Integer.parseInt(in[2]));
		System.out.println(Integer.parseInt(in[3]));
		System.out.println(Integer.parseInt(in[4]));
		System.out.println(in[5]);
		manager.addClient(Integer.parseInt(in[0]),in[1],Integer.parseInt(in[2]),Integer.parseInt(in[3]),Integer.parseInt(in[4]),in[5]);
	}

	private void addCar() {
		// TODO Auto-generated method stub
		
	}

	private void printMeniu() {
		System.out.println("~~~Meniu~~~");
		System.out.println("\t 1.Print the clients");
		System.out.println("\t 2.Add Client");
		System.out.println("\t 3.Add Car");
		System.out.println("\t 0.Exit");
		System.out.println();
		
	}

}
