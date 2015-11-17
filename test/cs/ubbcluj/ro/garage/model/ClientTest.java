package cs.ubbcluj.ro.garage.model;

import junit.framework.TestCase;
import ro.ubbcluj.cs.garage.model.Client;

public class ClientTest extends TestCase {

	public void testCreateClient(){
		Client client = MockFactory.createMike();
		assertEquals(MockFactory.MIKE,client.getName());
	}
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		testCreateClient();
	}
}
