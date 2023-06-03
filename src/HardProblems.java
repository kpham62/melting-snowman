public class HardProblems extends Problems{
    private static int num1;
    private static int num2;
    public HardProblems() {
        int problemTypeGenerator = (int)(Math.random() * 3);

        switch(problemTypeGenerator) {
            case 0:
                num1 = (int)(Math.random() * 51) + 50;

                setQuestion("the sum of the first " + num1 + " numbers");
                setAnswer((num1) * (num1 + 1) / 2);
                break;
            case 1:
                num1 = (int)(Math.random() * 5) + 5;
                num2 = (int)(Math.random() * 3) + 3;

                setQuestion(num1 + " ^ " + num2);
                setAnswer(Math.pow(num1, num2));
                break;
            case 2:
                num1 = (int)(Math.random() * 10);

                if (num1 == 0) {
                    setAnswer(1);
                }
                else{
                    int answer = 1;
                    for(int i = 1; i <= num1; i++) {
                        answer *= i;
                    }
                    setAnswer(answer);

                }
                setQuestion(num1 + "!");

        }
    }
}
