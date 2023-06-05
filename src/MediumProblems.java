public class MediumProblems extends Problems{ //new class MediumProblems inherits from Problems

    public MediumProblems() { //new constructor for MediumProblems
        //new int problemTypeGenerator randomly generates numbers 0, 1 or 2
        int problemTypeGenerator = (int)(Math.random() * 3);
        switch(problemTypeGenerator) { //creates a switch case to compare values of problemTypeGenerator
            case 0: //case when problemTypeGenerator is equal to 0 -> HARDER ARITHMETIC
                int num1 = (int)(Math.random() * 900) + 100; //generates a random number between 100 and 999 inclusive
                int num2 = (int)(Math.random() * 900) + 100; //generates a random number between 100 and 999 inclusive
                //uses randomOperator method to get Object array with a String and an Integer
                Object[] output = randomOperator(num1, num2);
                setQuestion((String)output[0]); //uses the 1st term of array as the question
                setAnswer((Integer)output[1]); //uses the 2nd term of array as the answer
                setProblemSize(110); //sets the problemSize for problemField
                break;
            case 1: //case when problemTypeGenerator is equal to 1 -> EASY EXPONENTS
                int base = (int)(Math.random() * 5) + 1; //generates a random number between 1 and 5 inclusive for base
                int exponent = (int)(Math.random() * 3) + 2; //generates a random number between 2 and 4 inclusive for exponent

                setQuestion(base + "^" + exponent); //sets the question using base, exponent operator, and exponent
                setAnswer(Math.pow(base, exponent));//sets the answer using Math.pow
                setProblemSize(100); //sets the problemSize for problemField
                break; //creates a break in the switch case (to avoid stacking on top of each other)
            case 2: //case when problemTypeGenerator is equal to 2 -> LCM
                num1 = (int)(Math.random() * 20)  + 10; //generates a random number between 10 and 29 inclusive
                num2 = (int)(Math.random() * 20) + 10; //generates a random number between 10 and 29 inclusive
                int LCM = 0; //initializes LCM outside for loop

                for(int i = 1; i<= Math.min(num1, num2); i++) { //iterates for loop until it reaches the smaller number
                    if(num1 % i == 0 && num2 % i == 0) { //checks to see if each number is divisible by both numbers (for GCF)
                        LCM = i * num1/i * num2/i; //the LCM will equal the GCF multiplied by each number divided by GCF
                    }
                }
                setAnswer(LCM); //sets the answer as the LCM obtained in line 31
                setQuestion("The LCM of " + num1 + " and " + num2); //sets question using num1 and num2
                setProblemSize(200); //sets the problemSize for problemField

        }
    }

}
