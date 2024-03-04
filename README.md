

## Table of Contents


1. [Assignment Objective](#assignment-objective) 
2. [Problem Analysis, Modeling, Scenarios, Use Cases](#problem-analysis-modeling-scenarios-use-cases) 
3. [Design](#design) 
4. [Implementation](#implementation) 
5. [Results](#results) 
6. [Conclusions](#conclusions) 
7. [Bibliography](#bibliography)

## Assignment Objective

&nbsp;&nbsp;&nbsp;&nbsp;The primary task of our project is to develop an Orders Management application that will streamline the process of handling client orders for a warehouse. Our chosen architectural framework for this application is a layered pattern, and we will be utilizing relational databases for data storage.

&nbsp;&nbsp;&nbsp;&nbsp;The development journey will encompass several key objectives. First, we must design a robust data model that accurately represents the fundamental entities within our system: Products, Clients, and Orders. This will involve defining these objects and their interrelationships in a manner that encapsulates the essence of our operations.

&nbsp;&nbsp;&nbsp;&nbsp;Following that, we will develop a Data Access Layer tasked with managing all interactions with the database. This layer will furnish our application with crucial methods to retrieve and store data, thereby promoting seamless communication with our underlying database system. The diligent execution of these sub-objectives will be instrumental in the successful realization of our Orders Management application.

## Problem Analysis, Modeling, Scenarios, Use Cases

&nbsp;&nbsp;&nbsp;&nbsp;The functional requirements for our Order Management System can be encapsulated into four core use-cases. The first use case is "Manage Products", which includes tasks such as adding, editing, and deleting product details from the system's inventory. The second use case, "Manage Clients", involves the creation, editing, and deletion of client records. The system should facilitate the seamless handling of all client-related information. The third use case, "Create Order", is central to the system's operation. This use case allows for the creation of new client orders, inclusive of selecting specific products and specifying the necessary quantities. Lastly, the "Process Order" use case ensures the orders are prepared and dispatched accordingly. This part of the process includes the system validating product availability, decrementing stock quantities upon order confirmation, and updating the order status as dispatched. The implementation of these use cases and the successful execution of their associated tasks will be crucial in fulfilling the functional requirements of our Order Management System.

## Design

![uml_diagram](https://github.com/vladvadean/Orders-Management-Database-App/assets/126804850/ce412099-0f5d-4c69-b1f9-c149bc6e0bdf)



## Implementation

&nbsp;&nbsp;&nbsp;&nbsp;The Model package contains all the object required to fill in the tables present in the SQL dump. The Data Access package contains the classes needed to write execute and update SQL statements. Most of the methods are generic and implemented in the Abstract DAO. The rest of the classes in the package use the generic methods and extends the Abstract DAO and adapt them if needed. The Business Logic package contains class for each class in the Model package and which are used to gather and update the database if needed. The Presentation package contains the controller and view classes needed to create a main menu window and a window for each table.
```java
private String createInsertQuery() {  
	StringBuilder sb = new StringBuilder();  
	sb.append("INSERT INTO ");  
	sb.append(type.getSimpleName());  
	sb.append(" VALUES(");  
	for (Field field : type.getDeclaredFields()) {  
		sb.append("?, ");  
	}  
	sb.replace(sb.length() - 2, sb.length(), " )");  
	return sb.toString();  
}
```

A generic method which implements the insert query generic for all the classes that call the method modifying the statement depending on the number of attributes that class has.
```java
private List<T> createObjects(ResultSet resultSet) {  
	  List<T> list = new ArrayList<T>(); 
	  Constructor[] ctors = type.getDeclaredConstructors(); 
	  Constructor ctor = null;  
	  for (int i = 0; i < ctors.length; i++) {  
		  ctor = ctors[i];  
		  if (ctor.getGenericParameterTypes().length == 0) 
			  break; 
	  } 
	  try { 
		  while (resultSet.next()) {  
			  ctor.setAccessible(true); 
			  T instance = (T) ctor.newInstance();  
			  for (Field : type.getDeclaredFields()) {  
				  String fieldName = field.getName(); 
				  Object value = resultSet.getObject(fieldName); 
				  PropertyDescriptor = new PropertyDescriptor(fieldName, type); 
				  Method method = propertyDescriptor.getWriteMethod(); method.invoke(instance, value); 
			  }  
			  list.add(instance); 
		  }  
	  } 
	  catch (InstantiationException | IntrospectionException | SQLException | InvocationTargetException |  
	  IllegalArgumentException | SecurityException | IllegalAccessException e) {  
		  e.printStackTrace(); 
	  } 
	  return list; 
}
```
A generic method to construct an object depending on the class that calls the method. This method is called by any insert or update method in the Abstract DAO class.
```java
public DefaultTableModel createTable(List<T> list) throws IllegalAccessException { 
	T object = list.get(0); 
	String[] column = new String[object.getClass().getDeclaredFields().length];
	Object[][] data = new Object[list.size()][object.getClass().getDeclaredFields().length];  
	int index = 0;  
	for (Field : object.getClass().getDeclaredFields()) {  
		column[index++] = field.getName(); 
	} 
	for(int i=0;i<list.size();i++){  
		Object aux = list.get(i);  
	    int k=0;  
		for(Field field: aux.getClass().getDeclaredFields()){  
		    field.setAccessible(true); 
		    data[i][k++]=  field.get(aux); 
		}  
	} 
	return new DefaultTableModel(data, column);
}
```
This generic method creates a default table model with which the JTable of every view is filled. This method is reflexive gathering information from every field of the object and adding it to an object matrix called data. The name of the columns are stored in the array of strings that gather the name of all the attributes .
```java
public int updateOrderIdProduct(String changeValue, String conditionValue) { 
	int success = orderDAO.updateField("productId", changeValue, "id", conditionValue); ProductDAO = new ProductDAO();  
    if (productDAO.findById(Integer._parseInt_(changeValue)) == null) { 
	    throw new NoSuchElementException("The product wasn't found and the order wasn't updated"); 
	} 
	if (success == 0) { 
		throw new NoSuchElementException("The order wasn't updated"); 
	} 
	return success; 
}
```
This function is found in the OrderBLL class and by using the generic method update Field, the values with which the order is updated are first validated then by calling the write field to change and the id field the order is updated but the bill that was created for the order is not modified.
```java
public List<Orders> selectAll() {  
	List<Orders> orders = orderDAO.findAll();  
	if (orders == null) { 
		throw new NoSuchElementException("There are no orders in the table!"); 
	} 
	return orders; 
}
```
This method returns a list of orders that are then used to create the jtabel in the order view if the show all button is pressed

## Results

&nbsp;&nbsp;&nbsp;&nbsp;Every operation works well and all the examples can be seen in the SQL dump in this repo. All of the operations were run from the GUI of the project and not the MySQL Workbench program.

## Conclusions

&nbsp;&nbsp;&nbsp;&nbsp;The concept of creating classes that correspond to database tables was a key learning point. It provided insights into how object-relational mapping works in practice, providing a foundation for more advanced topics like Hibernate or Spring Data JPA. The layered architectural approach taken in this assignment was instrumental in learning about separating concerns and building modular software. We also gained insights into managing the relationships and interactions between these layers, further reinforcing the principles of high cohesion and low coupling.

&nbsp;&nbsp;&nbsp;&nbsp;In summary, this assignment served as an effective exercise in applying software engineering principles to a practical problem, demonstrating how well-designed software can facilitate the management of complex business processes. This experience underscores the importance of proper design and architecture in building maintainable and scalable software applications.

## Bibliography

Java swing tutorial: [https://www.javatpoint.com/java-swing]  
Reflection technique: [https://www.oracle.com/technical-resources/articles/java/javareflection.html]  
Java and SQL connection: [https://www3.ntu.edu.sg/home/ehchua/programming/java/jdbc_basic.html]
