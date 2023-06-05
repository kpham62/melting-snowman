import java.awt.dnd.InvalidDnDOperationException;

public class Problems { // defining a class named Problems
    private String question; //encapsulation of both the question and the answer
    private double answer;  // private instance variable for holding an answer
    private int problemSize; // private instance variable for holding the size of a problem

    public String getQuestion() { //implementing the getter method for the question
        return question;
    }

    public double getAnswer() { //implementing the getter method for the answer
        return answer;
    }

    public void setQuestion(String question) { //implementing the setter method for the question
        this.question = question;
    }

    public void setAnswer(double answer) { //implementing the setter method for the answer
        this.answer = answer;
    }

    //function to create a random math problem with 2 int
    public static Object[] randomOperator(int num1, int num2) {
        //initialize object array 
        Object[] output = new Object[2];

        //random numbers from 0-2 are generated
        int operation = (int)(Math.random() * 3);
        switch (operation) {
            case 0: //performs addition
                output[0] = num1 + " + " + num2;
                output[1] = num1 + num2;
                break;

            case 1://performs subtraction
                output[0] = Integer.max(num1, num2) + " - " + Integer.min(num1, num2);
                output[1] = Math.abs(num1 - num2);
                break;

            case 2://performs multiplication
                output[0] = num1 + " * " + num2;
                output[1] = num1 * num2;
                break;
            default: //exception thrown
                throw new InvalidDnDOperationException("Invalid operator generated!");

        }
        //array containing opertaion and result returned
        return output;
    }

    //setter method for problem size
    public void setProblemSize(int problemSize) {
        this.problemSize = problemSize;
    }
    //getter method fopr the problem size
    public int getProblemSize() {
        return problemSize;
    }

    //override method to return question
    @Override
    public String toString() {
        return question;
    }
}
