import java.awt.dnd.InvalidDnDOperationException;

public class Problems {
    private String question; //encapsulation of both the question and the answer
    private double answer;

    //method compares input (given by the user) and the answer data field to check if input is correct
    public boolean check(double input) {
        if(input == answer) {
            return true;
        }
        else{
            return false;
        }
    }

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

    public static Object[] randomOperator(int num1, int num2) {
        Object[] output = new Object[2];

        int operation = (int)(Math.random() * 3);
        switch (operation) {
            case 0:
                output[0] = num1 + " + " + num2;
                output[1] = num1 + num2;
                break;

            case 1:
                output[0] = Integer.max(num1, num2) + " - " + Integer.min(num1, num2);
                output[1] = Math.abs(num1 - num2);
                break;

            case 2:
                output[0] = num1 + " * " + num2;
                output[1] = num1 * num2;
                break;
            default:
                throw new InvalidDnDOperationException("Invalid operator generated!");

        }
        return output;
    }
}