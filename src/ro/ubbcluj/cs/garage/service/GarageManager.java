package ro.ubbcluj.cs.garage.service;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import ro.ubbcluj.cs.garage.model.Car;
import ro.ubbcluj.cs.garage.model.Client;
import ro.ubbcluj.cs.garage.repository.CarRepository;
import ro.ubbcluj.cs.garage.repository.ClientRepository;

public class GarageManager {

	// Attributes
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private String fileNameClient;
	private ClientRepository clientRepo;
	private CarRepository carRepo;
	private String fileNameCar;

	// Methods
	public GarageManager(String fileNameClient,String fileNameCar) {
		this.fileNameClient = fileNameClient;
		this.fileNameCar = fileNameCar;
		//this.clientRepo.readFromFile(this.fileNameClient);
		//this.carRepo.readFromfile(this.fileNameCar);
		loadingElements();
	}

	private void loadingElements() {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				clientRepo.readFromFile(fileNameClient);
			}
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				carRepo.readFromfile(fileNameCar);
			}
		});

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			LOGGER.severe("Severe Error:" + e.getMessage());
			e.printStackTrace();
		}
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		//executor.submit(clientRepo.runnable());
		//executor.submit(carRepo.runnable());
		executor.submit(clientRepo);
		executor.submit(carRepo);
		
		try {
			executor.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			LOGGER.severe("Severe Error:"+e.getMessage());
			e.printStackTrace();
		}
		
		
		
		

	}

	public void setClientRepository(ClientRepository clientRepository) {
		this.clientRepo = clientRepository;
	}

	public void setCarRepository(CarRepository carRepository) {
		this.carRepo = carRepository;
	}

	public void addClient(int id, String name, int day, int month, int year, String password) {

		ArrayList<Car> cars = new ArrayList<Car>();
		Client client = new Client(id, name, day, month, year, cars, password);
		clientRepo.add(client);
	}

	public void printTheClients() {
		clientRepo.printAll();

	}

}
