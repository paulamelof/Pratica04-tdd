public class CalculadoraNotas extends Calculadora {

    public String checarSituacao(float... notas) {
        if (notas.length > 3) {
            throw new IllegalArgumentException("No maximo 3 notas");
        }
        if (notas.length < 2) {
            throw new NotasInsuficientesException("Precisa de pelo menos 2 notas");
        }

        // confere se as notas estao entre 0 e 10 e ja conta quantas sao menores que 4
        float soma = 0;
        int abaixoDe4 = 0;
        for (float nota : notas) {
            if (nota < 0 || nota > 10) {
                throw new IllegalArgumentException("Nota invalida: " + nota);
            }
            soma += nota;
            if (nota < 4) {
                abaixoDe4++;
            }
        }

        // calcula quanto precisa na 3a
        if (notas.length == 2) {
            if (abaixoDe4 == 2) {
                return "REPROVADO";
            }
            if (abaixoDe4 == 1) {
                return "RECUPERACAO";
            }

            float falta = 18 - soma;
            if (falta < 4) {
                falta = 4; 
            }
            return String.valueOf(falta);
        }

        // caso de 3 notas
        float media = soma / 3;

        if (media >= 6 && abaixoDe4 == 0) {
            return "APROVADO";
        }
        if (media >= 4 && abaixoDe4 == 0) {
            return "RECUPERACAO";
        }
        if (media >= 6 && abaixoDe4 == 1) {
            return "RECUPERACAO";
        }
        return "REPROVADO";
    }
}
