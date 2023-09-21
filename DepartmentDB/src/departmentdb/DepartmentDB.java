package departmentdb;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Department;
import model.Employee;

/**
 *
 * @author Bukhoree
 */
public class DepartmentDB {

    public static void main(String[] args) {
        // Create Department objects
        Department itDepartment = new Department();
        itDepartment.setName("IT");

        Department hrDepartment = new Department();
        hrDepartment.setName("HR");

        // Create Employee objects
        Employee john = new Employee();
        john.setName("John");
        john.setJob("Network Admin");
        john.setSalary(56789);
        john.setDepartmentid(itDepartment);

        Employee marry = new Employee();
        marry.setName("Marry");
        marry.setJob("HR Manager");
        marry.setSalary(46789);
        marry.setDepartmentid(hrDepartment);

        Employee henry = new Employee();
        henry.setName("Henry");
        henry.setJob("Programmer");
        henry.setSalary(67890);
        henry.setDepartmentid(itDepartment);

        Employee clark = new Employee();
        clark.setName("Clark");
        clark.setJob("HR recruiter");
        clark.setSalary(36789);
        clark.setDepartmentid(hrDepartment);

        // Persist the Department and Employee objects
        persist(itDepartment);
        persist(hrDepartment);
        persist(john);
        persist(marry);
        persist(henry);
        persist(clark);
    }

    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DepartmentDBPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

}
