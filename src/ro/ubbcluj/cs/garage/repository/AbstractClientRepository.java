package ro.ubbcluj.cs.garage.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import utilities.Repository;

public abstract class AbstractClientRepository<E> implements Repository<E>,Runnable {

	//Attributes
	protected static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	protected int size;
	protected List<E> elementsList = new ArrayList<>();
	
	//Methods
	@Override
	public void add(E obj){
		try{
			elementsList.add(obj);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		LOGGER.info("A new Client has been added"+obj.toString());
	}
	
	public boolean searchElement(E element){
		if (elementsList.contains(element))
			return true;
		return false;
		
	}
		
	@Override
	public void delete(E obj){
		for(E element:elementsList)
			if(element.equals(obj)){
				elementsList.remove(element);
				LOGGER.info("The following element has been deleted:"+element.toString());
			}
	}
	
	@Override
	public abstract void update(E obj);
	
	@Override
	public List<E> returnAll(){
		return elementsList;
	}
	
	public abstract ArrayList<E> readFromFileToArrayList(String filename);
	
	public abstract void readFromFile(String filename);
	
	public abstract void writeToFile(String filename);
	
	public void printElements(List<E> elements){
		elements.forEach(e -> System.out.println(e));
	}
	public void printAll(){
		elementsList.forEach(e -> System.out.println(e));
		LOGGER.info("Printed the ArrayList containing the 'Client' Objects.");
	}
	
}
