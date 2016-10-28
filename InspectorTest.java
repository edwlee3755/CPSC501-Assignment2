import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class InspectorTest {

	@Test
	public void testInspectDeclaringClass() {
		Inspector object = new Inspector();
		Object objectTest = new Object();
		String className = objectTest.getClass().getSimpleName();
		assertEquals("Object", className);
	}
	
	@Test 
	public void testInspectMethods()
	{
		Inspector object = new Inspector();
		ClassD test = new ClassD();
		String methodName = test.getClass().getSimpleName();
		assertEquals("ClassD", methodName);
	}
	
	@Test
	public void testImmediateSuperclass()
	{
		Inspector object = new Inspector();
		ClassB test = null;
		try {
			test = new ClassB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String className = test.getClass().getSuperclass().getSimpleName();
		assertEquals("ClassC", className);
	}
	
	@Test
	public void inspectInterFace()
	{
	//	Inspector object = new Inspector();
		//Class[] interfaceClass = new interfaceClass();
	}
	

}
