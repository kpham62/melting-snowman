public class EasyProblems extends Problems {
    private final static int num1 = (int)(Math.random() * 11);
    private final static int num2 = (int)(Math.random() * 11);
    public EasyProblems() {
        Object[] output = randomOperator(num1, num2);
        setQuestion((String)output[0]);
        setAnswer((Integer)output[1]);


        /*switch (operation) { //goes over each of the cases (0, 1, 2) in order to find the operation used
            case " + ": //represents addition
                setAnswer(num1 + num2); //sets the answer as sum of numbers
                break;
            case " - ": //represents subtraction
                if(num2 > num1) { //checks to see if num2 is bigger than num1 (to avoid negative answers)
                    int dummyVar = num1;
                    num1 = num2;
                    num2 = dummyVar; //switches num2 and num1 if num2 is found to be bigger
                }
                setAnswer(num1 - num2); //sets the answer as difference of numbers
                break;
            case " * ": //represents multiplication
                setAnswer(num1 * num2); //sets the answer as difference of numbers
                break;
            default:
                operation = "Invalid operation"; //checks to see if invalid number is run through
        }

        setQuestion(num1 + operation + num2); //creates the question using the two numbers and previously represented operator*/

    }
}
