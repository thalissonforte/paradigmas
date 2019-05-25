# HTTP Requests em Java

##### 1. Quais são as referências para objetos declaradas no código? De que classes são essas referências?
É criado um objeto da classe URL e sua referência fica em `url`.  

##### 2. A classe [URL](https://docs.oracle.com/javase/8/docs/api/java/net/URL.html) é pré-definida em Java. Em qual pacote ela está definida? Quantos construtores estão definidos nesta classe? Quantos métodos estão definidos nesta classe?
A classe URL está definida no pacote `java.net`. Estão definidos 6 construtores e 24 métodos para esta classe.

##### 3. O código [DemoPost.java](src/DemoPost.java) usa `new` para criar um objeto da classe URL, na linha 9. Nas linhas seguintes, não há mais uso de `new`, mas mesmo assim são usadas outras referências para objetos das classes HttpURLConnection e String. Como isso é possível?
A classe `HttpURLConnection` é uma extensão da classe abstrata `URLConnection`. Quando usamos `url.openConnection()` é criada uma instância de `URLConnection`, que forçamos a partir do cast a nos retornar o tipo `HttpURLConnection`.
    ```
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    ```
    
##### 4. Altere o código para chamar o método `getClass()` do objeto referenciado por `con` e mostrar o resultado com `System.out.println`. Qual o resultado? Onde o método `getClass()` está definido?
O resultado mostrou que o objeto referenciado por `con` pertence a classe `sun.net.www.protocol.https.HttpsURLConnectionImpl`.

##### 5. Em `con.getOutputStream().write`, o método `write` se aplica a um objeto de qual classe?
Nesse caso o método `write` se aplica a um objeto da classe OutputStream  

##### 6. O que acontece se removermos os termos `throws IOException` deste código?
Precisaremos tratar as exceções de entrada e saída de forma particular.
