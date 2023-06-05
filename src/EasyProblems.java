public class EasyProblems extends Problems { //creates new class EasyProblems that inherits from Problems
    private static int num1; //creates a datafield integer num1
    private static int num2; //creates another datafield integer num2
    public EasyProblems() {
        num1 = (int)(Math.random() * 11); //randomizes num1 with a number between 0 and 10 inclusive
        num2 = (int)(Math.random() * 11); //randomizes num2 with a number between 0 and 10 inclusive

        //calls randomOperator method from Problems to generate a question and answer in the form of an Object array
        Object[] output = randomOperator(num1, num2);
        setQuestion((String)output[0]); //sets the question using first element of output
        setAnswer((Integer)output[1]); //sets the question using second element of output
        setProblemSize(80); //sets the problemSize to 80 (for textField)

    }
}
