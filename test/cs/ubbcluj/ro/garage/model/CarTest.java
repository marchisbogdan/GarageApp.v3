package cs.ubbcluj.ro.garage.model;

import junit.framework.TestCase;
import ro.ubbcluj.cs.garage.model.Car;

public class CarTest extends TestCase {

	public void testCreateCar(){
		new MockFactory();
		Car car = MockFactory.createTesla();
		assertEquals(MockFactory.TESLA, car.getType());
	}
	
	@Override
	protected void tearDown() throws Exception {
		testCreateCar();
	}
}
