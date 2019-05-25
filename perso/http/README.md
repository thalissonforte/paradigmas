# HTTP Requests em Java

## Questões
### 1. Quais são as referências para objetos declaradas no código? De que classes são essas referências?
### 2. A classe [URL](https://docs.oracle.com/javase/8/docs/api/java/net/URL.html) é pré-definida em Java. Em qual pacote ela está definida? Quantos construtores estão definidos nesta classe? Quantos métodos estão definidos nesta classe?
### 3. O código [DemoPost.java](DemoPost.java) usa `new` para criar um objeto da classe URL, na linha 9. Nas linhas seguintes, não há mais uso de `new`, mas mesmo assim são usadas outras referências para objetos das classes HttpURLConnection e String. Como isso é possível?
### 4. Altere o código para chamar o método `getClass()` do objeto referenciado por `con` e mostrar o resultado com `System.out.println`. Qual o resultado? Onde o método `getClass()` está definido?
### 5. Em `con.getOutputStrem().write`, o método `write` se aplica a um objeto de qual classe?
### 6. O que acontece se removermos os termos `throws IOException` deste código?