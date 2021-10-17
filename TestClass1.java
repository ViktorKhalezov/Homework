
public class TestClass1 {

    @BeforeSuite
    public void prepareTesting() {
        System.out.println("Подготовка к тестированию");
    }

    @Test(priority = 3)
    public void Test1() {
        System.out.println("Тест №1");
    }

    @Test(priority = 6)
    public void Test2() {
        System.out.println("Тест №2");
    }

    @Test(priority = 6)
    public void Test3() {
        System.out.println("Тест №3");
    }

    @Test(priority = 10)
    public void Test4() {
        System.out.println("Тест №4");
    }

    @Test(priority = 9)
    public void Test5() {
        System.out.println("Тест №5");
    }

    @AfterSuite
    public void completeTesting() {
        System.out.println("Тестирование успешно завершено");
    }


}
