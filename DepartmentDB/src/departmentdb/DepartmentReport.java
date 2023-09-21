package departmentdb;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.Department;
import model.Employee;

/**
 *
 * @author Bukhoree
 */
public class DepartmentReport {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DepartmentDBPU");
        EntityManager em = emf.createEntityManager();

        try {
            // Generate report by ID
            System.out.println("All employees (by ID)");
            System.out.println("---------------------------");
            TypedQuery<Employee> queryById = em.createQuery("SELECT e FROM Employee e ORDER BY e.employeeid", Employee.class);
            List<Employee> employeesById = queryById.getResultList();
            for (Employee employee : employeesById) {
                printEmployeeInfo(employee);
            }

            // Generate report by Department
            System.out.println("All employees (by Department)");
            System.out.println("---------------------------");
            TypedQuery<Department> queryByDepartment = em.createQuery("SELECT d FROM Department d", Department.class);
            List<Department> departments = queryByDepartment.getResultList();
            for (Department department : departments) {
                System.out.println("Department ID: " + department.getDepartmentid() + " Department Name: " + department.getName());
                TypedQuery<Employee> queryByDepartmentId = em.createQuery(
                        "SELECT e FROM Employee e WHERE e.departmentid = :department ORDER BY e.employeeid",
                        Employee.class
                );
                queryByDepartmentId.setParameter("department", department);
                List<Employee> employeesByDepartment = queryByDepartmentId.getResultList();
                for (Employee employee : employeesByDepartment) {
                    printEmployeeInfo(employee);
                }
            }
        } finally {
            em.close();
            emf.close();
        }
    }

    private static void printEmployeeInfo(Employee employee) {
        System.out.println("ID: " + employee.getEmployeeid());
        System.out.println("Name: " + employee.getName());
        System.out.println("Job: " + employee.getJob());
        System.out.println("Salary: " + employee.getSalary());
        if (employee.getDepartmentid() != null) {
            System.out.println("Department: " + employee.getDepartmentid().getName());
        }
        System.out.println("---------------------------");
    }
}
