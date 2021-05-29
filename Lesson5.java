public class Lesson5 {

    public static void main(String[] args) {

        Employee[] empArray = new Employee[5];
        empArray[0] = new Employee ("Иванов Петр Иванович", "директор", "director@companymail.ru", "8-921-59-19-904", 100000, 60);
        empArray[1] = new Employee ("Фролов Игорь Васильевич", "инженер", "engineer@companymail.ru", "8-921-60-20-903", 60000, 45);
        empArray[2] = new Employee ("Колыванов Денис Игоревич", "программист", "developer@companymail.ru", "8-921-48-18-901", 80000, 30);
        empArray[3] = new Employee ("Петров Борис Сергеевич", "системный администратор", "admin@companymail.ru", "8-921-43-12-906", 40000, 26);
        empArray[4] = new Employee ("Васильева Ирина Александровна", "бухгалтер", "director@companymail.ru", "8-921-53-14-899", 50000, 55);

        for(int i = 0; i < empArray.length; i++) {
            if(empArray[i].getAge() > 40) {
                empArray[i].printInfo();
            }
        }

    }

}
