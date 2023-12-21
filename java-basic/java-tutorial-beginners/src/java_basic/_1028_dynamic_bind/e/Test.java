package java_basic._1028_dynamic_bind.e;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/28 18:14
 */

public class Test {
    public static void main(String[] args) {

        Test test = new Test();
        Manager jonas = new Manager("jonas", 1000, 2000);
        Worker jack = new Worker("jack", 100);

        System.out.println(test.showEmpAnnual(jonas));
        System.out.println(test.showEmpAnnual(jack));

        test.testWork(jonas);
        test.testWork(jack);
    }

    public double showEmpAnnual(Employee employee) {
        return employee.getAnnual();
    }

    public void testWork(Employee employee) {
        if(employee instanceof Worker) {
            ((Worker)employee).work();
        }
        if(employee instanceof Manager) {
            ((Manager)employee).manager();
        }
        "123".equals("345");
    }
}
