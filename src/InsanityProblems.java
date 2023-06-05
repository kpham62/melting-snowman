public class InsanityProblems extends Problems {
    //Binomial Theorem Nonsense
    //HEXADECIMAL TO DECIMAL
    //Matrix Nonsense

    public InsanityProblems() {
        int problemTypeGenerator = (int)(Math.random() * 3);

        switch (problemTypeGenerator) {
            case 0: {
                int coefficient1 = (int)(Math.random() * 20) - 10;
                int coefficient2 = (int)(Math.random() * 20) - 10;
                int exponent = (int)(Math.random() * 4) + 3;

                setQuestion("Sum of coefficients of (" + coefficient1 + "x+" +
                        coefficient2 + "y)^" + exponent);
                setAnswer(Math.pow((coefficient1 + coefficient2), exponent));
                setProblemSize(200);
            }
            break;
            case 1: {
                final int hexadecimalBase = 16;
                int[] digits = new int[3];
                int answer = 0;
                for(int i = 0; i < digits.length; i++) {
                    digits[i] = (int)(Math.random() * 16);
                    answer += digits[i] * Math.pow(hexadecimalBase, digits.length - i - 1);
                }
                String output = "";

                for(int i = 0; i < digits.length; i++) {
                    switch(digits[i]) {
                        case 10: {
                            output += "A";
                        }
                        break;
                        case 11:  {
                            output += "B";
                        }
                        break;
                        case 12: {
                            output += "C";
                        }
                        break;
                        case 13: output += "D";
                        break;
                        case 14: output += "E";
                        break;
                        case 15: output += "F";
                        break;
                        default: output += digits[i];
                    }
                    setQuestion("0x" + output + " in decimal form");
                    setAnswer(answer);
                    setProblemSize(200);

                }
            }
            break;
            case 2: {
                int num = (int)(Math.random() * 10) + 1;
                int exponent = (int)(Math.random() * 90) + 10;
                int modular = (int)(Math.random() * 10) + 10;
                int answer = 1;
                for(int i = 0; i < exponent; i++) {
                    answer *= num;
                    answer %= modular;
                }
                setQuestion(num + " ^ " + exponent + " mod " + modular);
                setAnswer(answer);
                setProblemSize(200);
            }
            break;
        }
    }


}
