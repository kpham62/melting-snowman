public class HardProblems extends Problems{
    public HardProblems() {
        int problemTypeGenerator = (int)(Math.random() * 3);

        switch(problemTypeGenerator) {
            case 0: {
                int num1 = (int)(Math.random() *  50) + 50;

                setQuestion("1 + 2 + 3 + ... " + " + " + (num1 - 1 ) + " + " + num1);
                setAnswer((num1) * (num1 + 1) / 2);
                setProblemSize(200);
            }
                break;
            case 1: {
                int num1 = (int) (Math.random() * 5) + 5;
                int num2 = (int) (Math.random() * 3) + 3;

                setQuestion(num1 + " ^ " + num2);
                setAnswer(Math.pow(num1, num2));
                setProblemSize(100);
            }
                break;

            case 2: {
                int num1 = (int)(Math.random() * 6) + 5;

                if (num1 == 0) {
                    setAnswer(1);
                } else {
                    int answer = 1;
                    for (int i = 1; i <= num1; i++) {
                        answer *= i;
                    }
                    setAnswer(answer);

                }
                setQuestion(num1 + "! ");
                setProblemSize(100);
            }
                break;

        }
    }
}
