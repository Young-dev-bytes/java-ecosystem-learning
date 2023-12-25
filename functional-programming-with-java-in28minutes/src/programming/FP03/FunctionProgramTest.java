package programming.FP03;

public class FunctionProgramTest {

    public static void main(String[] args) {

        FunctionProgram functionT = new FunctionProgram() {
            @Override
            public boolean test(Integer arg) {
                return arg >= 10;
            }
        };
        FunctionProgram functionProgram = number -> number % 2 == 0;

        // methodForFP(functionT);
        // methodForFP(functionT::test);
        methodForFP(item -> functionT.test(item));


    }

    public static void methodForFP(FunctionProgram functionT) {
        System.out.println("functionT...");
    }
}
