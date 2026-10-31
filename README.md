# Prática 04 - Testes Unitários com assert e TDD

**Disciplina:** Linguagem de Programação II
**Aluna:** Ana Paula Melo

## Como rodar

```
javac *.java
java -ea TesteCalculadoras
```

Tem que rodar com o `-ea`, senão os `assert` são ignorados e parece que tudo passou.

## Arquivos

- `Calculadora.java` - classe base que veio da Aula 10 (não mexi)
- `CalculadoraCientifica.java` - sen, cos, tg, log e root
- `CalculadoraNotas.java` - checarSituacao
- `ComplexRootException.java` - exceção da raiz de número negativo (extends Exception)
- `NotasInsuficientesException.java` - exceção pra 1 nota ou nenhuma
- `TesteCalculadoras.java` - todos os testes + main

## Cenários de teste planejados (antes de implementar)

Antes de escrever o código das classes eu listei os cenários abaixo, a partir das regras do enunciado. Cada um virou um método de teste no `TesteCalculadoras`.

### CalculadoraCientifica

**sen / cos (ângulo em graus)**
1. sen(0) = 0, sen(30) = 0.5, sen(90) = 1, sen(270) = -1
2. cos(0) = 1, cos(60) = 0.5, cos(90) = 0, cos(180) = -1

**tg**
3. tg(0) = 0, tg(45) = 1, tg(-45) = -1, tg(180) = 0
4. tg(90) lança ArithmeticException
5. tg(270) lança ArithmeticException
6. tg(-90) lança ArithmeticException

**log**
7. log(x) sem base usa base 10: log(100) = 2, log(1000) = 3, log(1) = 0
8. log com outra base: log(8, 2) = 3, log(81, 3) = 4, log(0.5, 2) = -1
9. x = 0 lança IllegalArgumentException
10. x negativo lança IllegalArgumentException
11. base = 0 lança IllegalArgumentException
12. base negativa lança IllegalArgumentException
13. base = 1 lança IllegalArgumentException

**root**
14. root(x) sem índice é raiz quadrada: root(9) = 3, root(2) ≈ 1.41421, root(0) = 0
15. root com outro índice: root(27, 3) = 3, root(16, 4) = 2, root(25, 2) = 5
16. root(-4) lança ComplexRootException
17. root(-8, 3) lança ComplexRootException

### CalculadoraNotas

**Com três notas**
18. Aprovado normal: (7, 8, 9) e (10, 10, 10) → APROVADO
19. Média exatamente 6: (6, 6, 6) e (5, 6, 7) → APROVADO
20. Nota exatamente 4 com média ≥ 6: (4, 7, 7) → APROVADO (4 não conta como nota baixa)
21. Média entre 4 e 6 sem nota baixa: (5, 5, 5) e (4, 5, 6) → RECUPERACAO
22. Média exatamente 4: (4, 4, 4) → RECUPERACAO
23. Média ≥ 6 com uma nota < 4: (3, 8, 8) e (2, 8, 8) (média exatamente 6) → RECUPERACAO
24. Média < 4: (2, 3, 5) e (0, 0, 0) → REPROVADO
25. Duas notas < 4 mesmo com média ≥ 4: (3, 3, 10) → REPROVADO
26. Média < 6 com uma nota < 4: (3, 6, 6) → REPROVADO

**Com duas notas**
27. Exemplos do enunciado: (6, 7) → "5.0" e (9, 9) → "4.0"
28. Nota necessária exatamente 10: (4, 4) → "10.0"
29. Nota necessária exatamente 4: (7, 7) → "4.0"
30. Uma das notas exatamente 4: (4, 8) → "6.0"
31. Uma nota < 4: (3, 8) e (10, 0) → RECUPERACAO
32. As duas < 4: (2, 3) → REPROVADO

**Exceções**
33. Nota maior que 10 → IllegalArgumentException
34. Nota negativa → IllegalArgumentException
35. Mais de três notas → IllegalArgumentException
36. Uma nota só → NotasInsuficientesException
37. Nenhuma nota → NotasInsuficientesException

## Como foi o TDD

Fui fazendo no ciclo vermelho → verde → refatorar, um método de cada vez.

1. **Primeiro os testes.** Escrevi os cenários acima e depois os métodos de teste. No começo nem compilava porque as classes não existiam, então criei as classes só com os métodos "vazios" (retornando 0 ou `""`) pra compilar.
2. **Vi os testes falharem.** Rodando com `java -ea TesteCalculadoras` o primeiro teste já quebrava com `AssertionError` (ex.: "sen(30) deveria ser 0.5"). Nos testes de exceção, como o método não lançava nada, caía no `assert false` e falhava também, que era o esperado.
3. **Implementei o mínimo pra passar.** Fui fazendo um método por vez: sen, cos, tg, depois a exceção da tg(90), log, root... e na CalculadoraNotas primeiro o caso de 3 notas, depois o de 2 notas e por último as exceções.
4. **Refatorei.** Algumas coisas que mudei depois que os testes já passavam:
   - Comparar `float` com `==` dava problema (ex.: sen(30) dá 0.49999997), então criei o `quaseIgual` com uma margem de erro.
   - No checarSituacao eu tinha um `for` só pra validar as notas e outro pra contar as notas menores que 4. Juntei tudo em um `for` só que valida, soma e conta.
   - Na tg eu tinha colocado um `if` pra 90, 270, -90... separado, depois troquei por `angulo % 180` que pega todos os casos.

### Decisões que tomei

- **Valor padrão do log e do root:** Java não tem parâmetro com valor padrão igual Python/C++, então resolvi com **sobrecarga**: `log(x)` chama `log(x, 10)` e `root(x)` chama `root(x, 2)`.
- **log em outra base:** usei a mudança de base, `log_b(x) = ln(x) / ln(b)`.
- **root:** raiz de índice n é `x^(1/n)`, então usei `Math.pow(x, 1 / indice)`.
- **tg:** a tangente não existe quando o cosseno é 0, ou seja, em 90° + k·180°. Com `angulo % 180` o resto dá 90 ou -90 nesses casos.
- **NotasInsuficientesException:** deixei como `RuntimeException` porque no diagrama o `checarSituacao` não tem `throws`. A `ComplexRootException` é `Exception` (checada) como o enunciado pede, por isso o `root` tem `throws ComplexRootException`.
- **Ordem das validações no checarSituacao:** primeiro vejo a quantidade de notas (mais de 3 ou menos de 2) e depois se cada nota está entre 0 e 10.
- Para duas notas, a soma das três precisa dar 18 pra média ser 6, então a nota que falta é `18 - (n1 + n2)`, e se der menos que 4 retorna 4.

## Resultado

Todos os 37 cenários passam rodando com `java -ea TesteCalculadoras`.
