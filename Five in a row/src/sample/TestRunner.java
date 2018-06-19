package sample;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

    private static void testClass (Class classToTest) {
        Result result = JUnitCore.runClasses(classToTest);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(classToTest.toString() + " Succeeded: " + result.wasSuccessful());
    }

    public static void main(String[] args) {
        testClass(AlertWinnerTest.class);
        testClass(BoardTest.class);
        testClass(CellTest.class);
    }
}
