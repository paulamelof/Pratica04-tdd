// Testes da pratica 04 - TDD
// rodar com: java -ea TesteCalculadoras
public class TesteCalculadoras {

    static final float ERRO = 0.0001f;

    static boolean quaseIgual(float a, float b) {
        return Math.abs(a - b) < ERRO;
    }

    // ================== CALCULADORA CIENTIFICA ==================

    static void testeSen() {
        CalculadoraCientifica c = new CalculadoraCientifica();
        assert quaseIgual(c.sen(0), 0) : "sen(0) deveria ser 0";
        assert quaseIgual(c.sen(30), 0.5f) : "sen(30) deveria ser 0.5";
        assert quaseIgual(c.sen(90), 1) : "sen(90) deveria ser 1";
        assert quaseIgual(c.sen(270), -1) : "sen(270) deveria ser -1";
        System.out.println("testeSen ok");
    }

    static void testeCos() {
        CalculadoraCientifica c = new CalculadoraCientifica();
        assert quaseIgual(c.cos(0), 1) : "cos(0) deveria ser 1";
        assert quaseIgual(c.cos(60), 0.5f) : "cos(60) deveria ser 0.5";
        assert quaseIgual(c.cos(90), 0) : "cos(90) deveria ser 0";
        assert quaseIgual(c.cos(180), -1) : "cos(180) deveria ser -1";
        System.out.println("testeCos ok");
    }

    static void testeTg() {
        CalculadoraCientifica c = new CalculadoraCientifica();
        assert quaseIgual(c.tg(0), 0) : "tg(0) deveria ser 0";
        assert quaseIgual(c.tg(45), 1) : "tg(45) deveria ser 1";
        assert quaseIgual(c.tg(-45), -1) : "tg(-45) deveria ser -1";
        assert quaseIgual(c.tg(180), 0) : "tg(180) deveria ser 0";
        System.out.println("testeTg ok");
    }

    static void testeTg90() {
        CalculadoraCientifica c = new CalculadoraCientifica();
        try {
            c.tg(90);
            assert false : "tg(90) deveria lancar ArithmeticException";
        } catch (ArithmeticException e) {
        }
        System.out.println("testeTg90 ok");
    }

    static void testeTg270() {
        CalculadoraCientifica c = new CalculadoraCientifica();
        try {
            c.tg(270);
            assert false : "tg(270) deveria lancar ArithmeticException";
        } catch (ArithmeticException e) {
        }
        System.out.println("testeTg270 ok");
    }

    static void testeTgMenos90() {
        CalculadoraCientifica c = new CalculadoraCientifica();
        try {
            c.tg(-90);
            assert false : "tg(-90) deveria lancar ArithmeticException";
        } catch (ArithmeticException e) {
        }
        System.out.println("testeTgMenos90 ok");
    }

    static void testeLogBasePadrao() {
        CalculadoraCientifica c = new CalculadoraCientifica();
        assert quaseIgual(c.log(100), 2) : "log(100) deveria ser 2 (base 10)";
        assert quaseIgual(c.log(1000), 3) : "log(1000) deveria ser 3 (base 10)";
        assert quaseIgual(c.log(1), 0) : "log(1) deveria ser 0";
        System.out.println("testeLogBasePadrao ok");
    }

    static void testeLogOutraBase() {
        CalculadoraCientifica c = new CalculadoraCientifica();
        assert quaseIgual(c.log(8, 2), 3) : "log de 8 na base 2 deveria ser 3";
        assert quaseIgual(c.log(81, 3), 4) : "log de 81 na base 3 deveria ser 4";
        assert quaseIgual(c.log(0.5f, 2), -1) : "log de 0.5 na base 2 deveria ser -1";
        System.out.println("testeLogOutraBase ok");
    }

    static void testeLogXZero() {
        CalculadoraCientifica c = new CalculadoraCientifica();
        try {
            c.log(0, 10);
            assert false : "log com x = 0 deveria lancar IllegalArgumentException";
        } catch (IllegalArgumentException e) {
        }
        System.out.println("testeLogXZero ok");
    }

    static void testeLogXNegativo() {
        CalculadoraCientifica c = new CalculadoraCientifica();
        try {
            c.log(-5);
            assert false : "log com x negativo deveria lancar IllegalArgumentException";
        } catch (IllegalArgumentException e) {
        }
        System.out.println("testeLogXNegativo ok");
    }

    static void testeLogBaseZero() {
        CalculadoraCientifica c = new CalculadoraCientifica();
        try {
            c.log(10, 0);
            assert false : "log com base 0 deveria lancar IllegalArgumentException";
        } catch (IllegalArgumentException e) {
        }
        System.out.println("testeLogBaseZero ok");
    }

    static void testeLogBaseNegativa() {
        CalculadoraCientifica c = new CalculadoraCientifica();
        try {
            c.log(10, -2);
            assert false : "log com base negativa deveria lancar IllegalArgumentException";
        } catch (IllegalArgumentException e) {
        }
        System.out.println("testeLogBaseNegativa ok");
    }

    static void testeLogBaseUm() {
        CalculadoraCientifica c = new CalculadoraCientifica();
        try {
            c.log(10, 1);
            assert false : "log com base 1 deveria lancar IllegalArgumentException";
        } catch (IllegalArgumentException e) {
        }
        System.out.println("testeLogBaseUm ok");
    }

    static void testeRootPadrao() throws ComplexRootException {
        CalculadoraCientifica c = new CalculadoraCientifica();
        assert quaseIgual(c.root(9), 3) : "root(9) deveria ser 3 (raiz quadrada)";
        assert quaseIgual(c.root(2), 1.41421f) : "root(2) deveria ser 1.41421...";
        assert quaseIgual(c.root(0), 0) : "root(0) deveria ser 0";
        System.out.println("testeRootPadrao ok");
    }

    static void testeRootOutroIndice() throws ComplexRootException {
        CalculadoraCientifica c = new CalculadoraCientifica();
        assert quaseIgual(c.root(27, 3), 3) : "raiz cubica de 27 deveria ser 3";
        assert quaseIgual(c.root(16, 4), 2) : "raiz quarta de 16 deveria ser 2";
        assert quaseIgual(c.root(25, 2), 5) : "root(25, 2) deveria ser 5";
        System.out.println("testeRootOutroIndice ok");
    }

    static void testeRootNegativo() {
        CalculadoraCientifica c = new CalculadoraCientifica();
        try {
            c.root(-4);
            assert false : "root(-4) deveria lancar ComplexRootException";
        } catch (ComplexRootException e) {
        }
        System.out.println("testeRootNegativo ok");
    }

    static void testeRootNegativoOutroIndice() {
        CalculadoraCientifica c = new CalculadoraCientifica();
        try {
            c.root(-8, 3);
            assert false : "root(-8, 3) deveria lancar ComplexRootException";
        } catch (ComplexRootException e) {
        }
        System.out.println("testeRootNegativoOutroIndice ok");
    }

    // ================== CALCULADORA NOTAS ==================

    // ---- 3 notas ----

    static void testeAprovado() {
        CalculadoraNotas c = new CalculadoraNotas();
        assert c.checarSituacao(7, 8, 9).equals("APROVADO") : "7, 8, 9 deveria ser APROVADO";
        assert c.checarSituacao(10, 10, 10).equals("APROVADO") : "10, 10, 10 deveria ser APROVADO";
        System.out.println("testeAprovado ok");
    }

    static void testeAprovadoMediaExata6() {
        CalculadoraNotas c = new CalculadoraNotas();
        assert c.checarSituacao(6, 6, 6).equals("APROVADO") : "media exatamente 6 deveria ser APROVADO";
        assert c.checarSituacao(5, 6, 7).equals("APROVADO") : "5, 6, 7 (media 6) deveria ser APROVADO";
        System.out.println("testeAprovadoMediaExata6 ok");
    }

    static void testeAprovadoComNotaExata4() {
        CalculadoraNotas c = new CalculadoraNotas();
        assert c.checarSituacao(4, 7, 7).equals("APROVADO") : "4, 7, 7 deveria ser APROVADO";
        System.out.println("testeAprovadoComNotaExata4 ok");
    }

    static void testeRecuperacaoMediaEntre4e6() {
        CalculadoraNotas c = new CalculadoraNotas();
        assert c.checarSituacao(5, 5, 5).equals("RECUPERACAO") : "media 5 deveria ser RECUPERACAO";
        assert c.checarSituacao(4, 5, 6).equals("RECUPERACAO") : "4, 5, 6 deveria ser RECUPERACAO";
        System.out.println("testeRecuperacaoMediaEntre4e6 ok");
    }

    static void testeRecuperacaoMediaExata4() {
        CalculadoraNotas c = new CalculadoraNotas();
        assert c.checarSituacao(4, 4, 4).equals("RECUPERACAO") : "media exatamente 4 deveria ser RECUPERACAO";
        System.out.println("testeRecuperacaoMediaExata4 ok");
    }

    static void testeRecuperacaoMedia6ComUmaNotaBaixa() {
        CalculadoraNotas c = new CalculadoraNotas();
        assert c.checarSituacao(3, 8, 8).equals("RECUPERACAO") : "3, 8, 8 deveria ser RECUPERACAO";
        assert c.checarSituacao(2, 8, 8).equals("RECUPERACAO") : "2, 8, 8 deveria ser RECUPERACAO";
        System.out.println("testeRecuperacaoMedia6ComUmaNotaBaixa ok");
    }

    static void testeReprovadoMediaMenor4() {
        CalculadoraNotas c = new CalculadoraNotas();
        assert c.checarSituacao(2, 3, 5).equals("REPROVADO") : "media < 4 deveria ser REPROVADO";
        assert c.checarSituacao(0, 0, 0).equals("REPROVADO") : "0, 0, 0 deveria ser REPROVADO";
        System.out.println("testeReprovadoMediaMenor4 ok");
    }

    static void testeReprovadoDuasNotasBaixas() {
        CalculadoraNotas c = new CalculadoraNotas();
        assert c.checarSituacao(3, 3, 10).equals("REPROVADO") : "3, 3, 10 deveria ser REPROVADO";
        System.out.println("testeReprovadoDuasNotasBaixas ok");
    }

    static void testeReprovadoMediaMenor6ComUmaNotaBaixa() {
        CalculadoraNotas c = new CalculadoraNotas();

        assert c.checarSituacao(3, 6, 6).equals("REPROVADO") : "3, 6, 6 deveria ser REPROVADO";
        System.out.println("testeReprovadoMediaMenor6ComUmaNotaBaixa ok");
    }

    // ---- 2 notas ----

    static void testeDuasNotasExemplos() {
        CalculadoraNotas c = new CalculadoraNotas();
        assert c.checarSituacao(6, 7).equals("5.0") : "6, 7 deveria retornar 5.0";
        assert c.checarSituacao(9, 9).equals("4.0") : "9, 9 deveria retornar 4.0";
        System.out.println("testeDuasNotasExemplos ok");
    }

    static void testeDuasNotasPrecisaExatamente10() {
        CalculadoraNotas c = new CalculadoraNotas();
        assert c.checarSituacao(4, 4).equals("10.0") : "4, 4 deveria retornar 10.0";
        System.out.println("testeDuasNotasPrecisaExatamente10 ok");
    }

    static void testeDuasNotasPrecisaExatamente4() {
        CalculadoraNotas c = new CalculadoraNotas();
        // 18 - 14 = 4 certinho
        assert c.checarSituacao(7, 7).equals("4.0") : "7, 7 deveria retornar 4.0";
        System.out.println("testeDuasNotasPrecisaExatamente4 ok");
    }

    static void testeDuasNotasComNotaExata4() {
        CalculadoraNotas c = new CalculadoraNotas();
        assert c.checarSituacao(4, 8).equals("6.0") : "4, 8 deveria retornar 6.0";
        System.out.println("testeDuasNotasComNotaExata4 ok");
    }

    static void testeDuasNotasUmaBaixa() {
        CalculadoraNotas c = new CalculadoraNotas();
        assert c.checarSituacao(3, 8).equals("RECUPERACAO") : "3, 8 deveria ser RECUPERACAO";
        assert c.checarSituacao(10, 0).equals("RECUPERACAO") : "10, 0 deveria ser RECUPERACAO";
        System.out.println("testeDuasNotasUmaBaixa ok");
    }

    static void testeDuasNotasAmbasBaixas() {
        CalculadoraNotas c = new CalculadoraNotas();
        assert c.checarSituacao(2, 3).equals("REPROVADO") : "2, 3 deveria ser REPROVADO";
        System.out.println("testeDuasNotasAmbasBaixas ok");
    }

    // ---- excecoes ----

    static void testeNotaMaiorQue10() {
        CalculadoraNotas c = new CalculadoraNotas();
        try {
            c.checarSituacao(11, 5, 5);
            assert false : "nota 11 deveria lancar IllegalArgumentException";
        } catch (IllegalArgumentException e) {
        }
        System.out.println("testeNotaMaiorQue10 ok");
    }

    static void testeNotaNegativa() {
        CalculadoraNotas c = new CalculadoraNotas();
        try {
            c.checarSituacao(-1, 5);
            assert false : "nota -1 deveria lancar IllegalArgumentException";
        } catch (IllegalArgumentException e) {
        }
        System.out.println("testeNotaNegativa ok");
    }

    static void testeMaisDeTresNotas() {
        CalculadoraNotas c = new CalculadoraNotas();
        try {
            c.checarSituacao(5, 6, 7, 8);
            assert false : "4 notas deveria lancar IllegalArgumentException";
        } catch (IllegalArgumentException e) {
        }
        System.out.println("testeMaisDeTresNotas ok");
    }

    static void testeUmaNota() {
        CalculadoraNotas c = new CalculadoraNotas();
        try {
            c.checarSituacao(8);
            assert false : "1 nota deveria lancar NotasInsuficientesException";
        } catch (NotasInsuficientesException e) {
        }
        System.out.println("testeUmaNota ok");
    }

    static void testeNenhumaNota() {
        CalculadoraNotas c = new CalculadoraNotas();
        try {
            c.checarSituacao();
            assert false : "nenhuma nota deveria lancar NotasInsuficientesException";
        } catch (NotasInsuficientesException e) {
        }
        System.out.println("testeNenhumaNota ok");
    }

    public static void main(String[] args) throws ComplexRootException {
        System.out.println("=== CalculadoraCientifica ===");
        testeSen();
        testeCos();
        testeTg();
        testeTg90();
        testeTg270();
        testeTgMenos90();
        testeLogBasePadrao();
        testeLogOutraBase();
        testeLogXZero();
        testeLogXNegativo();
        testeLogBaseZero();
        testeLogBaseNegativa();
        testeLogBaseUm();
        testeRootPadrao();
        testeRootOutroIndice();
        testeRootNegativo();
        testeRootNegativoOutroIndice();

        System.out.println("\n=== CalculadoraNotas ===");
        testeAprovado();
        testeAprovadoMediaExata6();
        testeAprovadoComNotaExata4();
        testeRecuperacaoMediaEntre4e6();
        testeRecuperacaoMediaExata4();
        testeRecuperacaoMedia6ComUmaNotaBaixa();
        testeReprovadoMediaMenor4();
        testeReprovadoDuasNotasBaixas();
        testeReprovadoMediaMenor6ComUmaNotaBaixa();
        testeDuasNotasExemplos();
        testeDuasNotasPrecisaExatamente10();
        testeDuasNotasPrecisaExatamente4();
        testeDuasNotasComNotaExata4();
        testeDuasNotasUmaBaixa();
        testeDuasNotasAmbasBaixas();
        testeNotaMaiorQue10();
        testeNotaNegativa();
        testeMaisDeTresNotas();
        testeUmaNota();
        testeNenhumaNota();

        System.out.println("\nTodos os testes passaram!");
    }
}
