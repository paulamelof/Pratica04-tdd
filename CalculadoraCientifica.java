public class CalculadoraCientifica extends Calculadora {

    // todos os angulos sao em graus, por isso converte pra radianos antes

    public float sen(float angulo) {
        return (float) Math.sin(Math.toRadians(angulo));
    }

    public float cos(float angulo) {
        return (float) Math.cos(Math.toRadians(angulo));
    }

    public float tg(float angulo) {

        float resto = angulo % 180;
        if (resto == 90 || resto == -90) {
            throw new ArithmeticException("Tangente nao existe para " + angulo + " graus");
        }
        return (float) Math.tan(Math.toRadians(angulo));
    }

    // java nao tem parametro com valor padrao, entao fiz sobrecarga
    // log(x) chama o log(x, base) com base 10
    public float log(float x) {
        return log(x, 10);
    }

    public float log(float x, float base) {
        if (x <= 0) {
            throw new IllegalArgumentException("x tem que ser maior que 0");
        }
        if (base <= 0 || base == 1) {
            throw new IllegalArgumentException("base invalida");
        }
        // mudanca de base: log_b(x) = ln(x) / ln(b)
        return (float) (Math.log(x) / Math.log(base));
    }

    // mesma ideia do log: root(x) eh a raiz quadrada (indice 2)
    public float root(float x) throws ComplexRootException {
        return root(x, 2);
    }

    public float root(float x, float indice) throws ComplexRootException {
        if (x < 0) {
            throw new ComplexRootException("Raiz de numero negativo da numero complexo");
        }
        
        return (float) Math.pow(x, 1 / indice);
    }
}
