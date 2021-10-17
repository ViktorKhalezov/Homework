import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TestClass1 testClass1 = new TestClass1();
        Class clazz = testClass1.getClass();

        start(clazz);

        TestClass2 testClass2 = new TestClass2();

       start(testClass2.getClass().getSimpleName());

    }

    public static void start(Class clazz) {
        Method beforeMethod = null;
        Method afterMethod = null;
        List<Method> testMethodsList = new ArrayList<>();
        Method[] methods = clazz.getDeclaredMethods();
        int beforeSuiteCount = 0;
        int afterSuiteCount = 0;

        for(Method m : methods) {
            if(m.isAnnotationPresent(BeforeSuite.class)) {
                beforeSuiteCount++;
                beforeMethod = m;
            }
            if(m.isAnnotationPresent(Test.class)) {
                testMethodsList.add(m);
            }
            if(m.isAnnotationPresent(AfterSuite.class)) {
                afterSuiteCount++;
                afterMethod = m;
            }
        }

        if(beforeSuiteCount != 1 || afterSuiteCount != 1) {
            throw new RuntimeException();
        }

        Collections.sort(testMethodsList, new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                return o2.getAnnotation(Test.class).priority() - o1.getAnnotation(Test.class).priority();
            }
        });

        try {
            Object obj = clazz.newInstance();
            beforeMethod.invoke(obj);
            for(Method m : testMethodsList) {
                m.invoke(obj);
            }
            afterMethod.invoke(obj);
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void start(String className) {
        Class clazz = null;
        try {
            if(className.contains(".")) {
                String[] fullNameArray = className.split(".");
                clazz = Class.forName(fullNameArray[fullNameArray.length - 1]);
            } else {
                clazz = Class.forName(className);
            }
            start(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
