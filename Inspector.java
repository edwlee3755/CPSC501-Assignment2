import java.lang.reflect. *;
import java.util. *;

public class Inspector {
	
	public void inspect(Object obj, boolean recursive)
	{
			List<Field> recursiveObjectsToInspect = new ArrayList<Field>();
			
			//Print Declaring Class of Object
			Class objClass = obj.getClass();
			Class superclass = objClass.getSuperclass();	 //return superclass
			
			inspectDeclaringClass(objClass);
			inspectSuperclass(objClass);
			inspectInterfaces(objClass);
			inspectMethods(objClass);
			inspectConstructors(objClass);

			//Get fields
			System.out.println("============================Printing FIELDS information============================");
			Field[] privateFieldName = objClass.getDeclaredFields();
			if (privateFieldName.length == 0)			//Check if any fields
			{
				System.out.println("No Fields Declared");
			}
			else
			{
				for (Field privateField : privateFieldName)
				{
					privateField.setAccessible(true);
					Object array = null;
					boolean checkPrimitive = privateField.getType().isPrimitive();
					try {
						array = privateField.get(obj);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
					
					//if field is an array
					if (privateField.getType().isArray() && array != null)
					{	
						System.out.println("Name of Array: " + privateField.getName());
						System.out.println("Component Type: " + privateField.getType().getComponentType());
						System.out.println("Array length: " + Array.getLength(array));
						
						//print contents of array 
						for (int i = 0; i < Array.getLength(array); i++)
						{
							System.out.println("Contents of Array: " + Array.get(array, i));
						}
					}
					
					//Store objects to fully inspect in an array to do later (after finishing off original print tasks)
					else if ((checkPrimitive == false) && recursive == true && array != null)
					{
						recursiveObjectsToInspect.add(privateField);
					}
					
					//Check if field is an object reference. If true (primitive = false), print the reference value, else print value
					else if ((checkPrimitive == false) && recursive == false && array != null)
					{
						System.out.println("The Reference Value is: " + privateField.getType().getSimpleName() + System.identityHashCode(obj));
					}
					else
					{	
						
						// Print declared field
						String declaredField = privateField.getName();
						System.out.println("The declared Field is: " + declaredField);
						
						//Print the field type
						System.out.println("The Field Type is: " + privateField.getType());
						
						//Print the field modifiers
						int fieldModifiers = privateField.getModifiers();
						System.out.println("Field Modifier: " + Modifier.toString(fieldModifiers));
						
						
						//Print current value of field
						try {
							
							System.out.println("Current Field Value: " + privateField.get(obj) + "\n");
						} catch (IllegalArgumentException | IllegalAccessException e) 
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
					}
				}
				
			}
			
			// iterate through list of objects needing full inspects. 
			if (!(recursiveObjectsToInspect.isEmpty()) && recursive == true)
			{
				Field recursiveField;
				for (int index = 0; index < recursiveObjectsToInspect.size(); index++)
				{
					recursiveField = recursiveObjectsToInspect.get(index);
					System.out.println("Field Name of Recursive Full Inspect: " + recursiveField.getName());
					try {
						inspect(recursiveField.get(obj), recursive);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
				recursiveObjectsToInspect.clear();
			}
		}
	public void inspectDeclaringClass(Class objClass) 
	{
		String className = objClass.getSimpleName();
		System.out.println("The Declaring Class Name is: " + className + "\n");
				
	}	
	
	public void inspectSuperclass(Class objClass)
	{
			//Print immediate SuperClass
			System.out.println("============================Printing SUPERCLASS information============================");
			Class superClass = objClass.getSuperclass();
			String immediateClassName = superClass.getSimpleName();
			System.out.println("Immediate SuperClass: " + immediateClassName + "\n");
	}	

			
	public void inspectInterfaces(Class objClass)
	{
			//The name of the interfaces the class implements
			System.out.println("============================Printing INTERFACES information============================");
			Class[] interfaceClass = objClass.getInterfaces();
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
	}
	
	public void inspectMethods(Class objClass)
	{
			//The methods the class declares
			System.out.println("============================Printing METHOD information============================");
			Method[] methods = objClass.getDeclaredMethods();
			
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
	}
	
	public void inspectConstructors(Class objClass)
	{
			//Constructors the class declares
			System.out.println("============================Printing CONSTRUCTOR information============================");
			Constructor[] constructors = objClass.getConstructors();
			
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

