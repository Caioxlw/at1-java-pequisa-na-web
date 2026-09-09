de registro também aceita um objeto de filtro de consulta.
# Consulta IA

## 1. Prompt

> Me explique melhor o que ele quer aqui:
>
>O pacote websearch no código inicial anexado contém código parcial para um mecanismo de busca na Web simulado. Ele vai ler um arquivo de dados e "fingir" que cada linha é uma consulta que alguém enviou a um mecanismo de pesquisa. O objetivo é extrair, em tempo real, consultas "interessantes" que atendam a determinadas condições.
>
>Observe que o código já usa objetos observadores para notificar o Snooper de cada consulta. O Snooper então imprime tudo.
>
>Altere o código para que ele use o padrão Strategy da seguinte forma:
>
>- No modelo de busca, crie uma nova interface que descreve a interface para um objeto que definirá um filtro de consulta.
>- Um objeto de filtro de consulta terá um método que receberá uma string (a consulta) e retornará `true` se o modelo de busca notificar o observador sobre essa consulta; retornará `false` se o observador não estiver interessado nesta string (a consulta).
>- Altere o modelo de busca para que, quando um observador for registrado, o método de registro também aceite um objeto de filtro de consulta.
>- Altere o modelo de busca para que, para cada consulta (string do arquivo), verifique se um observador está interessado na consulta antes de notificá-la.
>- Altere o cliente (`Snooper.java`) para criar dois observadores de consulta:
	- Um imprime `Oh Yes! <consulta>` sempre que a consulta contém a palavra `friend` (não diferencia maiúsculas de minúsculas).
	- Um imprime `So long <consulta>` sempre que a consulta tiver mais de 60 caracteres.
>
>### Observações
>
>- O modelo de busca não deve saber nada sobre a implementação dos objetos de política de filtro de consulta, a não ser que implementem a interface necessária.

## 2. IA e modelo

- **IA usada:** Gemini Pro

## 3. Resposta

O exercício pede para você combinar o padrão **Strategy** com o padrão **Observer**, que já está parcialmente implementado no código.

### Ideia principal

Hoje, o mecanismo de busca lê o arquivo e avisa os observadores sobre **todas** as consultas. A proposta é usar o Strategy para criar **filtros**, de modo que cada observador só receba as consultas que realmente interessam.

### O que fazer

#### 1. Criar a interface da estratégia

Crie uma interface que defina o contrato do filtro. Ela será responsável por decidir se uma consulta deve ou não ser mostrada.

- Exemplo: criar uma interface como `QueryFilter` com um único método.
- Esse método deve receber uma `String` e retornar um `boolean`.

#### 2. Criar os filtros concretos

Em vez de colocar vários `if`s no código principal, crie classes que implementem essa interface.

- Filtro 1: retorna `true` se a string contiver a palavra `friend`, sem diferenciar maiúsculas de minúsculas.
- Filtro 2: retorna `true` se a string tiver mais de 60 caracteres.

#### 3. Alterar o mecanismo de busca

O mecanismo de busca precisa associar cada observador ao seu filtro.

- No registro: o método de cadastro do observador deve receber o observador e o filtro dele.
- Na notificação: antes de avisar um observador, o modelo deve perguntar ao filtro se aquela consulta passa ou não.
- O modelo não deve conhecer a lógica interna do filtro, apenas chamar o método da interface.

#### 4. Alterar o `Snooper.java`

No cliente, você deve criar os dois filtros e registrar os dois observadores com comportamentos diferentes.

- Um imprime `Oh Yes! <consulta>` quando houver `friend` na consulta.
- Outro imprime `So long <consulta>` quando a consulta tiver mais de 60 caracteres.

### Resumo

O Strategy serve para injetar a regra de filtro de fora para dentro. O modelo só pergunta: "essa consulta passa?", sem saber como a resposta foi calculada.