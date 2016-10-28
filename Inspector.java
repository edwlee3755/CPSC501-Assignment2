import java.lang.reflect. *;
import java.util. *;

public class Inspector {
	
	public void inspect(Object obj, boolean recursive)
	{
			
			//Print Declaring Class of Object
			Class ObjClass = obj.getClass();
			String className = ObjClass.getName();
			System.out.println("The Declaring Class Name is: " + className + "\n");
			
			
			//Print immediate SuperClass
			System.out.println("============================Printing SUPERCLASS information============================");
			Class subclass = obj.getClass();
			Class superclass = subclass.getSuperclass();	 //return superclass
				
			String immediateClassName = superclass.getSimpleName();
			System.out.println("Immediate SuperClass: " + immediateClassName + "\n");
			
			
			
			//The name of the interfaces the class implements
			System.out.println("============================Printing INTERFACES information============================");
			Class[] interfaceClass = ObjClass.getInterfaces();
			if (interfaceClass.length == 0)			//Check if an interface is implemented
			{
				System.out.println("No Interfaces Implemented");
			}
			else
			{
				for (Class interfaces : interfaceClass)
				{
					System.out.println("The Interfaces implemented are: " + interfaces.getName() + "\n");
				}
			}
			
			//The methods the class declares
			System.out.println("============================Printing METHOD information============================");
			Method[] methods = ObjClass.getDeclaredMethods();
			
			for(Method method: methods)
			{
				//print name of each method
				System.out.println("method name: " + method.getName());
				
				//print return type of the methods
				System.out.println("Return type of method: " + method.getReturnType());
				
				//Get and print access modifier of each method
				int modifiers = method.getModifiers();
				System.out.println("method modifier: " + Modifier.toString(modifiers));
				
				//get and print parameter types
				Class[] parameterList = method.getParameterTypes();
				if (parameterList.length == 0)			//Check if any parameters
				{
					System.out.println("Method parameter types: No parameters used");
				}
				else
				{
					System.out.println("Method parameter types: ");
					for (Class parameter: parameterList)
					{
						System.out.println(parameter.getName() + " ");
						
					}
					System.out.println();
				}
				//Get and print exception throws by methods
				Class[] exceptionList = method.getExceptionTypes();
				if (exceptionList.length == 0)			//Check if any exceptions thrown
				{
					System.out.println("Exception thrown by method: No Exceptions thrown");
				}
				else
				{
					System.out.println("Exception thrown by method: ");;
					for(Class exception: exceptionList)
					{
						System.out.println(exception.getName() + " ");
					}
				}
				System.out.println();
			}
			
			//Constructors the class declares
			System.out.println("============================Printing CONSTRUCTOR information============================");
			Constructor[] constructors = ObjClass.getConstructors();
			
			if (constructors.length == 0)			//Check if constructor is declared
			{
				System.out.println("Constructor name: No Constructors Declared");
			}
			else
			{
				for(Constructor constructor: constructors)
				{
	
					//print name
					System.out.println("Constructor name: " + constructor.getName());
					
					//get and print access modifier of each constructor
					int modifiers = constructor.getModifiers();
					System.out.println("Constructor modifier: " + Modifier.toString(modifiers));
					
					//Get and print parameter types
					Class[] parameterList = constructor.getParameterTypes();
					
					if (parameterList.length == 0)			//Check if any parameters
					{
						System.out.println("Constructor parameter types: No parameters used");
					}
					else
					{
						System.out.println("Constructor parameter types: ");
						for (Class parameter: parameterList)
						{
							System.out.println(parameter.getName() + " ");
						}
						System.out.println();
						
						//Get and print exception throws by constructors
						Class [] exceptionList = constructor.getExceptionTypes();
						if (exceptionList.length == 0)			//Check if any exceptions thrown
						{
							System.out.println("Exception thrown by Constructor: No Exceptions thrown");
						}
						else
						{
							System.out.println("Exception thrown by constructors: " );
							for(Class exception : exceptionList)
							{
								System.out.println(exception.getName() + " " );
							}
							System.out.println();
							System.out.println("*********************");
						}
					}
				}
			}
					
		}
}
