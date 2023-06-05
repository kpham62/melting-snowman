public class HardProblems extends Problems{ //creates new class HardProblems that extends Problems
    public HardProblems() { //creates new constructor HardProblems
        //new int problemTypeGenerator randomly generates numbers 0, 1 or 2
        int problemTypeGenerator = (int)(Math.random() * 3);

        switch(problemTypeGenerator) { //creates a switch case to compare values of problemTypeGenerator
            case 0: { //case when problemTypeGenerator is equal to 0 -> SUM OF FIRST N NUMBERS
                int num1 = (int)(Math.random() *  50) + 50; //randomly generates number between 50 and 99 inclusive


                setQuestion("1 + 2 + 3 + ... " + " + " + (num1 - 1 ) + " + " + num1); //sets question of the problem
                setAnswer((num1) * (num1 + 1) / 2); //sets the answer of the problem (using arithmetic sum formula)
                setProblemSize(200); //sets the problemSize for problemField
            }
                break; //creates a break in the switch case (to avoid stacking on top of each other)
            case 1: { //case when problemTypeGenerator is equal to 1 -> EXPONENTS
                int base  = (int) (Math.random() * 5) + 5; //randomly generates number between 5 and 9 inclusive (base)
                int exponent = (int) (Math.random() * 3) + 3; //randomly generates number between 3 and 5 inclusive (exponent)

                setQuestion(base + " ^ " + exponent); //sets question using base, exponent operator, and exponent
                setAnswer(Math.pow(base, exponent)); //sets answer using Math.pow
                setProblemSize(100); //sets the problemSize for problemField
            }
                break; //creates a break in the switch case (to avoid stacking on top of each other)

            case 2: { //case when problemTypeGenerator is equal to 2 -> FACTORIALS
                int num1 = (int)(Math.random() * 6) + 5; //randomly generates number between 5 and 10 (inclusive)

                if (num1 == 0) { //checks if number is equal to 0
                    setAnswer(1); //0! is 1, sets answer equal to 1
                } else { //case when number is not 0
                    int answer = 1; //sets answer to 1
                    for (int i = 1; i <= num1; i++) { //for loop runs through all values from 1 to num1
                        answer *= i; //multiplies the answer by each value of i
                    }
                    setAnswer(answer); //sets the answer using int answer

                }
                setQuestion(num1 + "! "); //sets the question using the factorial operator
                setProblemSize(100); //sets the problemSize for problemTextField
            }
                break; //creates a break in the switch case (to avoid stacking on top of each other)

        }
    }
}
