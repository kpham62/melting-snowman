public class EasyProblems extends Problems {
    private static int num1;
    private static int num2;
    public EasyProblems() {
        num1 = (int)(Math.random() * 11);
        num2 = (int)(Math.random() * 11);

        Object[] output = randomOperator(num1, num2);
        setQuestion((String)output[0]);
        setAnswer((Integer)output[1]);
        setProblemSize(80);

    }
}
