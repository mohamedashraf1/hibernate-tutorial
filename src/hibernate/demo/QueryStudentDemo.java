package hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
										
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// start a transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			// display the students
			displayStudents(theStudents);
			
			// query students: lastName = "ashraf"
			theStudents = session.createQuery("from Student s where s.lastName='ashraf'")
									.getResultList();
			
			// display the students
			System.out.println("\n\nStudents who have last name of ashraf");
			displayStudents(theStudents);
			
			// query students: lastName="diab" 	OR firstName="karim"
			theStudents = 
					session.createQuery("from Student s where" 
										+" s.lastName='diab' OR s.firstName = 'karim'")
					.getResultList();	
			
			System.out.println("\n\nquery students: lastName=\"diab\" 	OR firstName=\"karim\"");
			displayStudents(theStudents);
			
			// query students where email LIKE '%gmail.com'
			theStudents = session.createQuery("from Student s where "+
									"s.email LIKE '%gmail.com'").getResultList();
			
			System.out.println("\n\nquery students where email LIKE '%gmail.com'");
			displayStudents(theStudents);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} finally {
			factory.close();
		} 
		

	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student temStudent : theStudents) {
			System.out.println(temStudent);
		}
	}

}
