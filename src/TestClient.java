import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 * This class uses GET and POST to find and retrieve data och send data to an application's database.
 * It includes a Main method that creates a client that targets the applications url.
 * 
 * The Main method includes logic to toggle between finding students in the database and adding new students
 * to the database. 
 *  
 * @author Oscar Nordgren & Patrik Hjortberg
 * @returns response Build for GET and POST
 */

public class TestClient {
	public static void main(String[] args) {
		
		System.out.println("Welcome to the Student Management System.");
		
		whileloop: while (true) {
			
			printMenu();

			switch (menuSelection()) {

			case 1:

				/*
				 * 
				 * // GET findStudentById (as Json-object)
				 * 
				 * Client client = ClientBuilder.newClient(); WebTarget target =
				 * client.target("http://localhost:8080/StudentDb/webservice/students/1");
				 * Invocation invocation = target.request().buildGet(); Response response =
				 * invocation.invoke();
				 * 
				 * System.out.println(response.readEntity(String.class));
				 * 
				 * response.close();
				 * 
				 */
				
				String targetURLid = "http://localhost:8080/StudentDb/webservice/students/" + inputId(); 
				
				// GET findStudentById() (as Java-object)

				Client clientOne = ClientBuilder.newClient();
				WebTarget targetOne = clientOne.target(targetURLid);
				Invocation invocationOne = targetOne.request().buildGet();
				Response responseOne = invocationOne.invoke();
				
				//System.out.println(responseOne.getHeaders().toString());
				//System.out.println(responseOne.getStatus());
				
				Student student = responseOne.readEntity(Student.class);
				System.out.println(student);

				responseOne.close();
				break;

			case 2:

				// GET findAllStudents() (as Java-object)

				Client clientTwo = ClientBuilder.newClient();
				WebTarget targetTwo = clientTwo.target("http://localhost:8080/StudentDb/webservice/students");
				Invocation invocationTwo = targetTwo.request().buildGet();
				Response responseTwo = invocationTwo.invoke();

				List<Student> students = responseTwo.readEntity(new GenericType<List<Student>>() {
				});

				for (Student s : students) {
					System.out.println(s);
				}

				responseTwo.close();
				
				break;

			case 3:

				// POST insertStudent()

				Client clientThree = ClientBuilder.newClient();
				
				Entity entityThree = Entity.entity(newStudentInfo(), "application/JSON");
				Response response = clientThree.target("http://localhost:8080/StudentDb/webservice/students").request()
						.buildPost(entityThree).invoke();
				System.out.println(response.readEntity(Student.class));
				response.close();
				
				break;
				
			case 4:
				
				String targetURLidToBeUpdated = "http://localhost:8080/StudentDb/webservice/students/" + inputId();
				
				Client clientFour = ClientBuilder.newClient();
				
				Entity entityFour = Entity.entity(updatedStudent(),"application/JSON");
				Response responseFour = clientFour.target(targetURLidToBeUpdated).request()
						.buildPut(entityFour).invoke();
				responseFour.close();
				
				break;
				
			case 5:
				
				String targetURLidToDelete = "http://localhost:8080/StudentDb/webservice/students/" + inputId(); 
				
				// DELETE deleteStudent()
				
				Client clientFive = ClientBuilder.newClient();
				Response responseFive = clientFive.target(targetURLidToDelete)
				.request().buildDelete().invoke();
				responseFive.close();
				
				break;
				
			case 6: 
				break whileloop;
				
			}
		}	
		
		System.out.println("Good Bye!");
	}
	
	
	public static void printMenu() {
		System.out.println();
		System.out.println("Choose from the following options.");
		System.out.println("[1] - Find student by ID");
		System.out.println("[2] - Find all students");
		System.out.println("[3] - Insert new student");
		System.out.println("[4] - Update student");
		System.out.println("[5] - Delete student");
		System.out.println("[6] - Exit program");
	}
	
	public static int menuSelection() {
		
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		return choice;
	}
	
	public static String inputId() {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter student ID: ");
		String id = null;
		try {
			id = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public static Student newStudentInfo() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Student newStudent = new Student();
		
		try {
			System.out.println("Enter student first name: ");
			newStudent.setFirstName(reader.readLine());
			System.out.println("Enter student last name: ");
			newStudent.setLastName(reader.readLine());
			System.out.println("Enter school: ");
			newStudent.setSchool(reader.readLine());
			System.out.println("Enter admission year");
			newStudent.setAdmissionYear(reader.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return newStudent;
	}
	
	public static Student updatedStudent() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Student updatedStudent = new Student();
		
		try {
			System.out.println("Enter new school: ");
			updatedStudent.setSchool(reader.readLine());
			System.out.println("Enter new admission year: ");
			updatedStudent.setAdmissionYear(reader.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return updatedStudent;
	}
}
