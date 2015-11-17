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

import ro.ubbcluj.cs.garage.model.Client;

public class ClientRepository extends AbstractClientRepository<Client> implements Serializable,Runnable {

	//Attributes
	
	public ClientRepository() {
	}



	@Override
	public void add(Client obj) {
		// TODO Auto-generated method stub
		super.add(obj);
	}

	private static final long serialVersionUID = 672204661185784437L;

	//Methods
	public Client searchByName(String name) {
		for (Client client : elementsList)
			if (client.getName().equals(name))
				return client;
		return null;
	}
	
	

	@Override
	public void update(Client obj) {
		// TODO Auto-generated method stub

	}

	@Override
	//used to read from binary files
	public ArrayList<Client> readFromFileToArrayList(String filename) {

		ArrayList<Client> clients = new ArrayList<Client>();
		
		try (FileInputStream fileStream = new FileInputStream(filename);
				ObjectInputStream objectStream = new ObjectInputStream(fileStream)) {
				
			this.size = objectStream.readInt();
			
			for(int i = 1; i<=this.size; i++){
				
				Client client =(Client)objectStream.readObject();
				clients.add(client);
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
		
		return clients;

	}
	@Override
	//used to read from binary files
	public void readFromFile(String filename) {

		//ArrayList<Client> clients = new ArrayList<Client>();
		
		try (FileInputStream fileStream = new FileInputStream(filename);
				ObjectInputStream objectStream = new ObjectInputStream(fileStream)) {
				
			this.size = objectStream.readInt();
			
			for(int i = 1; i<=this.size; i++){
				
				Client client =(Client)objectStream.readObject();
				//clients.add(client);
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

			for (Client client : elementsList) {
				outputStream.writeObject(client);
			}

		} catch (FileNotFoundException e) {
			LOGGER.severe("Severe Error:"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e1) {
			LOGGER.severe("Severe Error:"+e1.getMessage());
			e1.printStackTrace();
		}

	}
	
	public List<Client> filterByName(String name){
		// Lambda
		List<Client> clients = elementsList.stream().filter(c -> c.getName().equals(name)).collect(Collectors.toList());
		return clients;
	}



	@Override
	public void run() {
		String filename=null;
		readFromFile(filename);
		
	}



	public Runnable runnable() {
		String filename=null;
		readFromFile(filename);
		return null;
	}


	
	
	
}
