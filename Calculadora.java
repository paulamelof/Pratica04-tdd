public class Calculadora {

    public int soma(int a, int b) { return a + b; }
    public int subtracao(int a, int b){ return a - b; }
    public int produto(int a, int b) { return a * b; }

    public int divisao(int a, int b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Divisão por zero.");
        }
        return a / b;
    }
}
