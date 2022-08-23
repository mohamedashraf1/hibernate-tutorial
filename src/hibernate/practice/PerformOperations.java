package hibernate.practice;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import hibernate.practice.entity.Employee;

public class PerformOperations {

	public static void main(String[] args) {
		
		// setting up the configuration file
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Employee.class)
										.buildSessionFactory();
		
		Employee employee1 = new Employee("mohamed", "ashraf", "army");
		Employee employee2 = new Employee("ahmed", "ashraf", "edu");
		Employee employee3 = new Employee("mohamed", "ta7a", "rawafed");
		Employee employee4 = new Employee("mohamed", "moussa", "rawafed");
		
		
		try {
		
			// saving objects
			Session session = sessionFactory.getCurrentSession();
			
			session.beginTransaction();
			
			session.save(employee1);
			session.save(employee2);
			session.save(employee3);
			session.save(employee4);
			
			session.getTransaction().commit();
			
			
			// retrieving object by id
			session.beginTransaction();
			
			Employee tmp = session.get(Employee.class, employee1.getId());
			System.out.println(employee1.getId());
			System.out.println(tmp);
			
			session.getTransaction().commit();
			
			
			// retrieving all employees who work at 'rawafed'
			session.beginTransaction();
			
			List<Employee> employees = session
					.createQuery("from Employee e where e.company = 'rawafed'").getResultList();
			
			for(Employee e: employees) {
				System.out.println(e);
			}
			session.getTransaction().commit();
			
			
			// deleting object with id = 18
			session.beginTransaction();
			
			session.createQuery("delete from Employee where id = 18").executeUpdate();
			
			session.getTransaction().commit();
			
		} finally {
			sessionFactory.close();
		} 

	}

}
