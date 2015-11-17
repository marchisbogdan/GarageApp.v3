package ro.ubbcluj.cs.garage.repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ro.ubbcluj.cs.garage.model.Car;

public class CarRepository extends AbstractCarRepository<Car> implements Serializable {
	
	//Attributes
	private static final long serialVersionUID = 3132057076448388319L;
	
	//Methods
	public Car findByModel(String model) {

		for (Car car : elementsList)
			if (car.getModel().equals(model))
				return car;
		return null;

	}

	@Override
	public void update(Car obj) {

	}

	@Override
	//used to read from binary files
	public ArrayList<Car> readFromFileToArrayList(String filename) {

		ArrayList<Car> cars = new ArrayList<Car>();

		try (FileInputStream fileStream = new FileInputStream(filename);
				ObjectInputStream objectStream = new ObjectInputStream(fileStream)) {

			this.size = objectStream.readInt();

			for (int i = 1; i <= this.size; i++) {

				Car client = (Car) objectStream.readObject();
				cars.add(client);

			}

		} catch (FileNotFoundException e) {
			LOGGER.severe("Severe Error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e1) {
			LOGGER.severe("Severe Error:"+e1.getMessage());
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			LOGGER.severe("Severe Error:"+e.getMessage());
			e.printStackTrace();
		}

		return cars;

	}

	@Override
	public void writeToFile(String filename) {
		/*
		 * The method writes to the binary file : 1 - the number of objects contained
		 * in the Array 2 - the objects contained in the Array
		 */

		try (FileOutputStream fileStream = new FileOutputStream(filename);
				ObjectOutputStream outputStream = new ObjectOutputStream(fileStream)) {

			this.size = elementsList.size();

			outputStream.writeInt(this.size);

			for (Car car : elementsList) {
				outputStream.writeObject(car);
			}
			

		} catch (FileNotFoundException e) {
			LOGGER.severe("Severe Error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e1) {
			LOGGER.severe("Severe Error:"+e1.getMessage());
			e1.printStackTrace();
		}

	}

	public List<Car> filterByType(String type) {
		// Lambda
		List<Car> cars = elementsList.stream().filter(c -> c.getType().equals(type)).collect(Collectors.toList());
		return cars;
		
	}

	public List<Car> filterByModel(String model) {
		// Lambda
		List<Car> cars = elementsList.stream().filter(c -> c.getModel().equals(model)).collect(Collectors.toList());
		return cars;
	}

	@Override
	public void run() {
		String filename = null;
		readFromfile(filename);
	}

	@Override
	public void readFromfile(String filename) {
		try (FileInputStream fileStream = new FileInputStream(filename);
				ObjectInputStream objectStream = new ObjectInputStream(fileStream)) {

			this.size = objectStream.readInt();

			for (int i = 1; i <= this.size; i++) {

				Car client = (Car) objectStream.readObject();
				elementsList.add(client);

			}

		} catch (FileNotFoundException e) {
			LOGGER.severe("Severe Error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e1) {
			LOGGER.severe("Severe Error:"+e1.getMessage());
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			LOGGER.severe("Severe Error:"+e.getMessage());
			e.printStackTrace();
		}
	}

	public Runnable runnable() {
		String filename = null;
		readFromfile(filename);
		return null;
	}
}
