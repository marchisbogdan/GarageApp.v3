package ro.ubbcluj.cs.garage.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import utilities.Repository;

public abstract class AbstractCarRepository<E> implements Repository<E>,Runnable {

	//Attributes
	protected static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	protected int size;
	protected List<E> elementsList = new ArrayList<>();
	
	//Methods
	@Override
	public void add(E obj){
		elementsList.add(obj);
	}
		
	@Override
	public void delete(E obj){
		for(E element:elementsList)
			if(element.equals(obj))
				elementsList.remove(element);
	}
	
	@Override
	public abstract void update(E obj);
	
	@Override
	public List<E> returnAll(){
		return elementsList;
	}
	
	public abstract ArrayList<E> readFromFileToArrayList(String filename);
	
	public abstract void readFromfile(String filename);
	
	public abstract void writeToFile(String filename);
	
	public void printElements(List<E> elements){
		elements.forEach(c -> System.out.println(c));
	}
	

	
}
