public class InsanityProblems extends Problems { //creates InsanityProblems class that inherits Problems
    public InsanityProblems() { //public constructor for InsanityProblems
        //new int problemTypeGenerator randomly generates numbers 0, 1 or 2
        int problemTypeGenerator = (int)(Math.random() * 3);

        switch (problemTypeGenerator) { //creates a switch case to compare values of problemTypeGenerator
            case 0: { //case when problemTypeGenerator equals 0 -> SUM OF COEFFICIENTS OF BINOMIAL EXPANSION
                int coefficient1 = (int)(Math.random() * 20) - 10; //sets the first coefficient between -10 and 19 inclusive
                int coefficient2 = (int)(Math.random() * 20) - 10; //sets the second coefficient between -10 and 19 inclusive
                int exponent = (int)(Math.random() * 4) + 3; //sets the exponent between 3 and 6 inclusive

                setQuestion("Sum of coefficients of (" + coefficient1 + "x+" +
                        coefficient2 + "y)^" + exponent); //sets the question using coefficients and exponent

                /*sets the answer using theorem (similar to summation of choose formulas) where sum of coefficients
                to the exponent power is equal to sum of coefficients of expansion
                 */
                setAnswer(Math.pow((coefficient1 + coefficient2), exponent));
                setProblemSize(200); //sets the problemSize for problemField
            }
            break; //creates a break in the switch case in order to avoid stacking cases
            case 1: { //case when problemTypeGenerator equals 1 -> HEXADECIMAL TO DECIMAL BASE CONVERSION
                final int hexadecimalBase = 16; //constant hexadecimal base number 16
                int[] digits = new int[3]; //creates an int array for 3 digits
                int answer = 0; //initializes the answer at 0
                for(int i = 0; i < digits.length; i++) { //iterates through the for loop for all the elements in digits
                    digits[i] = (int)(Math.random() * 16); //creates a random number between 0 and 15 (inclusive)

                    //increments the answer by multiplying digit by hexadecimal place value
                    answer += digits[i] * Math.pow(hexadecimalBase, digits.length - i - 1);
                }
                String output = ""; //creates empty String output

                for(int i = 0; i < digits.length; i++) { //iterates through the for loop for all the elements in digits
                    switch(digits[i]) { //creates a switch case depending on the "digit"
                        case 10: { //case if "digit" is equal to 10
                            output += "A"; //concatenate digit A to output
                        }
                        break; //creates break in the switch case in order to avoid stacking cases
                        case 11:  { //case if "digit" is equal to 11
                            output += "B"; //concatenate digit B to output
                        }
                        break; //creates break in the switch case in order to avoid stacking cases
                        case 12: { //case if "digit" is equal to 10
                            output += "C"; //concatenate digit C to output
                        }
                        break; //creates break in the switch case in order to avoid stacking cases
                        case 13: { //case if "digit" is equal to 13
                            output += "D"; //concatenate digit D to output
                        }
                        break; //creates break in the switch case in order to avoid stacking cases
                        case 14: { //case if "digit" is equal to 14
                            output += "E"; //concatenate digit E to output
                        }
                        break; //creates break in the switch case in order to avoid stacking cases
                        case 15: { //case if "digit" is equal to 15
                            output += "F"; //concatenate digit F to output
                        }
                        break; //creates break in the switch case in order to avoid stacking cases
                        default: { //the default case (if the digit is less than 10)
                            output += digits[i]; //concatenates the digit to output
                        }
                        break; //creates break in the switch case in order to avoid stacking cases
                    }
                    setQuestion("0x" + output + " in decimal form"); //sets question using output
                    setAnswer(answer); //sets answer using answer obtained after line 30
                    setProblemSize(200); //sets problemSize for problemField

                }
            }
            break; //creates break in the switch case in order to avoid stacking cases
            case 2: { //case when problemTypeGenerator equals 2 -> MODULAR ARITHMETIC
                int num = (int)(Math.random() * 10) + 1; //creates a random number between 1 and 10 inclusive
                int exponent = (int)(Math.random() * 90) + 10; //creates a random exponent between 10 and 99 inclusive
                int modular = (int)(Math.random() * 10) + 10; //creates a random mod number between 10 and 19 inclusive
                int answer = 1; //sets the answer equal to 1
                for(int i = 0; i < exponent; i++) { //for loop iterates through each value from 0 to exponent
                    answer *= num; //multiplies the answer by num (allowed due to properties of modular multiplication
                    answer %= modular; //takes the mod of the answer afterwards to simplify and prepare to multiply again
                }
                setQuestion(num + " ^ " + exponent + " mod " + modular); //sets question using num, exponent, and modular
                setAnswer(answer); //sets the answer using int answer obtained in line 79
                setProblemSize(200); //sets problemSize for problemField
            }
            break; //creates break in the switch case in order to avoid stacking cases
        }
    }


}
