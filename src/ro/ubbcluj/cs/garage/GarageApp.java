package ro.ubbcluj.cs.garage;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import ro.ubbcluj.cs.garage.repository.CarRepository;
import ro.ubbcluj.cs.garage.repository.ClientRepository;
import ro.ubbcluj.cs.garage.service.GarageManager;
import ro.ubbcluj.cs.garage.ui.GarageUI;

public class GarageApp {

	//private static final Logger logger = Logger.getLogger(GarageApp.class.getName());
	protected static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private static FileHandler fh;
	private static String fileNameClient = "Clients.txt";
	private static String fileNameCar = "Car.txt";
	
	
	public static void main(String[] args) {
		/*TODO uncomment the code which verify's the parameters
		 * +add threads
		 * +add XML files (+reader and writer)
		 * +create client-server application
		 * +create the UI
		 * +create the Manager
		 * +add validate class
		*/
		
		//if(args.length == 0){
		//	System.out.println("Name parameter is invalid");
		//	System.exit(1);
		//}
		
		// creating a Logger and redirecting the output to the indicated file
		try {
			GarageApp.loggerSetup();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		LOGGER.info("Stating application...");
		//String name = args[0];
		//String name = null;
		
		try{
			System.out.println("...");
			GarageUI garageUI = new GarageUI();
			GarageManager garageManager = new GarageManager(fileNameClient,fileNameCar);
			garageUI.setGarageManager(garageManager);
			garageManager.setClientRepository(new ClientRepository());
			garageManager.setCarRepository(new CarRepository());
			garageUI.start();
		}catch (Exception e){
			System.out.println("the app has encountered an error!: "+e.getMessage());
		}
		LOGGER.info("Application stopped successfully\n");
	}
	
	static public void loggerSetup() throws SecurityException, IOException{
		// get the global logger to configure it
		Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		// suppress the logging output to a file 
		Logger rootLogger = Logger.getLogger("");
		
		Handler[] handlers = rootLogger.getHandlers();
		//removes the console handler used by the Logger
		if(handlers[0] instanceof ConsoleHandler){
			rootLogger.removeHandler(handlers[0]);
		}
		
		//sets the loggers level and adds the file handler
		logger.setLevel(Level.INFO);
		// if the boolean is true the Logger will append to the previous messages
		fh = new FileHandler("GarageAppMainLogger.txt", false);
		fh.setFormatter(new SimpleFormatter());
		logger.addHandler(fh);
	}
	

}
