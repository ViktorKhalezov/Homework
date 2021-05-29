public class Employee {

    private String fullName;
    private String position;
    private String email;
    private String phoneNumber;
    private int salaryAmount;
    private int age;

    public Employee (String fullName, String position, String email, String phoneNumber, int salaryAmount, int age){
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salaryAmount = salaryAmount;
        this.age = age;
       }

    public void printInfo (){
System.out.println("ФИО: " + fullName + ", должность: " + position + ", электронная почта: " + email + ", номер телефона: " + phoneNumber + ", зарплата: " + salaryAmount + ", возраст: " + age);
    }

    public int getAge() {
        return age;
    }

}
