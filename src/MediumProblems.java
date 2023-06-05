public class MediumProblems extends Problems{
    private static int num1;
    private static int num2;

    public MediumProblems() {
        int problemTypeGenerator = (int)(Math.random() * 3);
        switch(problemTypeGenerator) {
            case 0:
                num1 = (int)(Math.random() * 900) + 100;
                num2 = (int)(Math.random() * 900) + 100;
                Object[] output = randomOperator(num1, num2);
                setQuestion((String)output[0]);
                setAnswer((Integer)output[1]);
                setProblemSize(110);
                break;
            case 1:
                num1 = (int)(Math.random() * 2) + 4;
                num2 = (int)(Math.random() * 2) + 2;

                setQuestion(num1 + "^" + num2);
                setAnswer(Math.pow(num1, num2));
                setProblemSize(100);
                break;
            case 2:
                num1 = (int)(Math.random() * 20)  + 10;
                num2 = (int)(Math.random() * 20) + 10;
                int LCM = 0;

                for(int i = 1; i<= Math.min(num1, num2); i++) {
                    if(num1 % i == 0 && num2 % i == 0) {
                        LCM = i * num1/i * num2/i;
                    }
                }
                setAnswer(LCM);
                setQuestion("The LCM of " + num1 + " and " + num2);
                setProblemSize(200);

        }
    }

}
