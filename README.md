# Localizador de Museus em Java com Spring Boot
Este projeto em Java consiste em uma API Rest desenvolvida com Spring Boot para facilitar a busca por museus com base na localização do usuário e em uma série histórica de dados.

## Descrição
O Localizador de Museus em Java com Spring Boot é uma API Rest que permite aos usuários encontrar museus próximos com base em sua localização atual. Ele utiliza uma série histórica de dados para fornecer uma ampla gama de opções de museus.

## Funcionalidades
Localização de museus próximos com base na localização do usuário.
Filtragem de resultados por categoria de museu (por exemplo, arte, história, ciência, etc.).
Exibição de informações detalhadas sobre os museus encontrados, como nome, endereço e classificação.
Tratamento de exceções customizadas.
Gerenciamento de erros para tratamento de exceções da API.
Testes unitários para cobertura de código.
Utilização de injeção de dependências.
Configuração Docker para a aplicação.
Conceitos Abordados
Este projeto aborda os seguintes conceitos:

## Desenvolvimento de API Rest com Spring Boot.
Tratamento de exceções e erros.
Testes unitários para garantir a qualidade do código.
Utilização de injeção de dependências para tornar o código mais modular e testável.
Configuração de Docker para facilitar a implantação da aplicação em diferentes ambientes.
Por que isso é importante?
Este projeto coloca em prática todo o conteúdo estudado sobre Spring Boot, incluindo o desenvolvimento de APIs Rest, tratamento de exceções, testes unitários e configuração Docker. Ele oferece uma oportunidade para os desenvolvedores praticarem habilidades avançadas de desenvolvimento de software em Java.

## Requisitos Desenvolvidos

### 1 - Crie as classes de Modelo e DTO

<details>
  <summary>Crie as classes de Modelo e DTO com os atributos dos tipos especificados, além dos getters e setters</summary><br />

Para que o projeto possa compilar, precisamos implementar as classes de Modelo e DTO. Você deve criar essas classes, incluindo seus atributos, getters e setters. **Não** implemente-as com `record`, pois o projeto está estruturado para utilizar os métodos de uma classe comum.

A classe de modelo deverá:
- Ser chamada `com.betrybe.museumfinder.model.Museum`
- Conter os seguintes atributos, com seus getters e setters:
    - `id`: `Long`
    - `name`: `String`
    - `description`: `String`
    - `address`: `String`
    - `collectionType`: `String`
    - `subject`: `String`
    - `url`: `String`
    - `coordinate`: `com.betrybe.museumfinder.model.Coordinate`
    - `legacyId`: `Long`

Você deverá criar duas classes de DTO:
- `com.betrybe.museumfinder.dto.MuseumDto`
  - Deve ser implementada utilizando o `record` do Java;
  - Deve conter os mesmos atributos que a classe de modelo, com exceção do `legacyId`;
- `com.betrybe.museumfinder.dto.MuseumCreationDto`
  - Deve ser implementada utilizando o `record` do Java;
  - Deve conter os mesmos atributos que a classe de modelo, com exceção do `id` e do `legacyId`;

O primeiro DTO será utilizado nos próximos requisitos como resposta da API, enquanto o segundo será utilizado para receber a requisição de criação de um novo museu.

Notas sobre os atributos:
- O tipo `com.betrybe.museumfinder.model.Coordinate` já existe no projeto.
- O atributo `legacyId` representa uma situação hipotética em que poderíamos ter um `id` associado a um sistema legado, e por isso não queremos expor essa informação nos DTOs.

Dica 👀: disponibilizamos uma classe utilitária em `com.betrybe.museumfinder.util.ModelDtoConverter`, que pode ser usada para conversão entre o modelo e os DTOs. Mas atenção ao usá-la, pois para ela funcionar corretamente a ordem dos atributos nos DTOs deve ser estritamente respeitada.

</details>


### 2 - Habilite o Spring Boot Actuator

<details>
  <summary>Habilite o Spring Boot Actuator com a rota de verificação da saúde da aplicação</summary><br />

Neste requisito você deve habilitar o Spring Boot Starter Actuator, de forma que a rota `/actuator/health` retorne:

```json
{"status": "UP"}
```

Dica 👀: após editar seu `pom.xml`, certifique-se de atualizar o projeto na sua IDE, para que as alterações nas dependências se reflitam no seu projeto.

</details>


### 3 - Implemente o método createMuseum da camada de serviço

<details>
  <summary>Crie a classe da camada de serviço seguindo a interface e implemente o método createMuseum</summary><br />

1. Crie uma classe para a camada de serviço. Você deve:
- Implementar sua classe em `com.betrybe.museumfinder.service.MuseumService`
- Marcar a classe como um componente do Spring do tipo `Service`
- Configurar a classe para receber um bean do tipo `com.betrybe.museumfinder.database.MuseumFakeDatabase` por injeção de dependência (você pode escolher a forma).
- Garantir que a classe implementa a interface `com.betrybe.museumfinder.service.MuseumServiceInterface` (disponibilizada com o projeto). Os métodos podem ficar vazios inicialmente (implementaremos o `createMuseum` ainda neste requisito, logo abaixo)

2. Implemente o método `createMuseum`, que criará um novo museu. Nesse método você deve:
- Receber um objeto do tipo `Museum`
- Verificar se as coordenadas presentes no objeto são válidas. 
  - Para isso você pode usar a classe `com.betrybe.museumfinder.util.CoordinateUtil`, disponibilizada com o projeto.
  - Caso as coordenadas não sejam válidas, você deve lançar uma exceção `com.betrybe.museumfinder.exception.InvalidCoordinateException`. 
    - Você deverá criar a classe da exceção como do tipo `unchecked`.
- Caso tudo esteja certo, chamar o bean do `MuseumFakeDatabase` para salvar o objeto através do método `saveMuseum`.
- Retornar o novo objeto retornado pelo método `saveMuseum`.

</details>


### 4 - Implemente o método getClosestMuseum da camada de serviço

<details>
  <summary>Implemente o método getClosestMuseum na classe que você criou para a camada de serviço</summary><br />

O método `getClosestMuseum` irá receber como parâmetro uma coordenada e uma distância máxima em quilômetros. Ele deve retornar o museu mais próximo daquela coordenada, dentro da distância especificada, utilizando o método correspondente no bean do `MuseumFakeDatabase`. 

Na sua implementação você deve:
- Validar as coordenadas e lançar exceção, da mesma forma que no requisito anterior
- Usar o bean do banco de dados falso (`MuseumFakeDatabase`) para fazer busca pelo museu mais próximo
- Caso um museu seja encontrado, retorná-lo
- Caso nenhum museu seja encontrado, você deve lançar uma exceção `com.betrybe.museumfinder.exception.MuseumNotFoundException`
  - Você deverá criar a classe da exceção como do tipo `unchecked`.

</details>


### 5 - Criar classe _controller_ para `/museums` com rota POST

<details>
  <summary>Criar classe controller para `/museums` com rota POST</summary><br />

Neste requisito, você deve começar criando uma classe para a camada de controle. Sua implementação deve:
- Implementar sua classe em `com.betrybe.museumfinder.controller.MuseumController`
- Configurar a classe para ser um _controller_ do Spring para a rota base `/museums`
- Receber um bean do tipo `MuseumServiceInterface` por injeção de dependência
  - **Importante**: o bean deve ser referenciado pela interface, e não pela classe concreta que você criou. Do contrário, os testes não a reconhecerão.

Além disso, você deve definir uma rota POST para `/museums` que: 
- Recebe um objeto do tipo DTO pelo corpo da requisição
- Salva o objeto utilizando o bean de _service_ configurado
- Retorna como resposta o status 201 (CREATED) com o objeto criado no corpo da resposta.
  - Lembre-se que o método `createMuseum` do serviço retorna um novo objeto.

Você ainda não precisa tratar a exceção lançada pelo service, isso será feito em outro requisito.

</details>

### 6 - Criar rota GET `/museums/closest`

<details>
  <summary>Criar rota GET /museums/closest, que retornará o museu mais próximo</summary><br />

Neste requisito você criará a rota GET  `/museums/closest`, que receberá uma localização (latitude e longitude) e uma distância máxima (em quilômetros), e retornará as informações do museu mais próximo dentro da distância, se houver algum. 

Para isso, você deve: 
- Receber na rota os seguintes valores por _query string_:
  - `lat`: a latitude
  - `lng`: a longitude
  - `max_dist_km`: a distância máxima em quilômetros
- Utilizar o método implementado anteriormente do bean de serviço para fazer a busca;
- Retornar o DTO com o museu encontrado, com status code 200 (OK).

Algumas informações adicionais:
- Note que os parâmetros da _query string_ não estão seguindo a convenção de nomes do Java. Você terá que mapear esses nomes para os do Java, por exemplo usando a opção [`name`](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RequestParam.html#name()) da anotação.
- Você ainda não precisa tratar a exceção lançada pelo service, isso será feito em outro requisito.

Um exemplo de chamada:
- URL: `http://localhost:8080/museums/closest?lat=-20.4435&lng=-54.6478&max_dist_km=10`
- Resposta:
```json
{
    "name": "Parque Estadual das Várzeas do Rio Ivinhema",
    "description": "Parque Estadual.",
    "address": "Rua Desembargador Leão Neto, s/n, Setor 3, Quadra 3, Parque dos Poderes, 79031-902, Campo Grande, MS",
    "collectionType": "Não informada",
    "subject": "Não informada",
    "url": "",
    "coordinate": {
        "latitude": -20.4439029100578,
        "longitude": -54.5663452148438
    }
}
```

</details>

### 7 - Criar um ControllerAdvice para tratar erros

<details>
  <summary>Crie uma classe com ControllerAdvice para tratar as exceções e gerar respostas</summary><br />

Neste requisito você deve criar uma classe e marcá-la como _ControllerAdvice_ para tratar as exceções que sua aplicação pode lançar.

Você deve tratar os seguintes erros:
- Exceções do tipo `InvalidCoordinateException`: retornar um _status code_ 400 (BAD REQUEST) com o corpo contendo apenas a string `Coordenada inválida!`.
- Exceções do tipo `MuseumNotFoundException`: retornar um _status code_ 404 (NOT FOUND) com o corpo contendo apenas a string `Museu não encontrado!`.
- Qualquer outra exceção: retornar um _status code_ 500 (INTERNAL SERVER ERROR) com o corpo contendo apenas a string `Erro interno!`.
  - Dica: utilize a hierarquia de tratamento. Caso não haja um _handler_ para uma exceção específica, o _handler_ de uma exceção genérica é usado.

</details>


### 8 - Implemente testes para as classes CollectionTypeController e CollectionTypeService para atingir cobertura de 80% das linhas

<details>
  <summary>Utilize o MockMvc para implementar testes unitários para as classes CollectionTypeController e CollectionTypeService, de forma a atingir cobertura de 80% das linhas</summary><br />

Neste requisito, você deve implementar testes unitários para atingir cobertura de 80% no projeto. Para isso, você criará testes para uma nova API que já começou a ser implementada, conforme abaixo. Seus testes devem ser implementados no pacote `com.betrybe.museumfinder.solution`, não altere os testes do projeto!

Os dados dos museus contém informação sobre o tipo de coleção que eles possuem. Uma API com esses dados já começou a ser implementada na rota base `/collections`, através das classes `com.betrybe.museumfinder.controller.CollectionTypeController` e `com.betrybe.museumfinder.service.CollectionTypeService`.

Por enquanto, a única rota que existe é a `/collections/count/{typesList}`, que realiza a contagem do número de museus cujo tipo de coleção contém o(s) tipo(s) especificado(s). O parâmetro de caminho `typesList` é uma string contendo um ou mais tipos, separados por vírgula.

Por exemplo, a rota `/collections/count/história` vai retornar o número de museus cujo tipo de coleção contém a substring `história` (_case insensitive_). Já a rota `/collections/count/hist,imag` vai retornar os que contém a substring `hist` ou a substring `imag`. No segundo exemplo, uma resposta possível seria:
```json
{
    "collectionTypes": [
        "hist",
        "imag"
    ],
    "count": 492
}
```
O objetivo neste requisito é atingir os 80% de cobertura. Não há funcionalidades específicas que precisam ser testadas, mas você deve escolher apropriadamente o que irá testar nas classes indicadas.

Por fim, você vai notar que a implementação atual dessas classes possuem diversas estruturas redundantes. No entanto, queremos implementar os testes para garantir que tudo está funcionando, e posteriormente solicitar uma refatoração, com a confiança de que nada será quebrado no processo. :)

**Importante**:
 - Os nomes dos arquivos de teste sempre devem terminar com `Test`, por exemplo: `MeuArquivoTest.java`
 - Recomendamos que você utilize o MockMvc para realizar os testes nas rotas desta API. Você também pode utilizar outras funcionalidades de testes (como o MockBean) caso julgue necessário.
 - Você pode utilizar as ferramentas de cobertura de código da sua IDE para identificar partes do código que ainda não foram testadas. No entanto, a porcentagem de cobertura considerada será a que os testes com o Maven reportam. Assim, garanta que os testes oficiais do projeto estão passando.
 - Os testes de cobertura são executados com comandos que dependem do shell `sh`, e podem não funcionar em sistemas Windows.

</details>


### 9 - Implemente controller, service e testes para rota GET `/museums/{id}`

<details>
  <summary>Crie a rota GET /museums/{id}, implementando os métodos necessários nas classes MuseumController e MuseumService, e garantindo cobertura de 80% das linhas</summary><br />

Neste requisito, você vai criar a rota GET `/museums/{id}`. Para isso, você deve:
- Receber a variável de caminho `id`
- Chamar um novo método do bean de serviço para buscar o museu pelo `id`
  - Você também deve implementar esse novo método, que fará a chamada a um método existente do bean do banco de dados
- Retornar o objeto do museu caso encontrado, ou lançar a exceção apropriada caso não seja encontrado

Ao implementar essa rota, a cobertura dos testes para as classes `MuseumController` e `MuseumService` pode ter sido reduzida para um valor abaixo de 80%. Você deve criar testes unitários para o que implementou aqui, de forma a manter a cobertura acima de 80% das linhas dessas classes. Seus testes devem ser implementados no pacote `com.betrybe.museumfinder.solution`, não altere os testes do projeto!

</details>


### 10 - Crie um Dockerfile para sua aplicação

<details>
  <summary>Crie um Dockerfile multi-estágio configurando a imagem Docker da sua aplicação</summary><br />

Finalmente, você deve construir um `Dockerfile` para rodar a sua aplicação no Docker.

Seu `Dockerfile`:

- Deve ser multi-estágio
- O primeiro estágio deve se chamar `build-image` e deve ser utilizado para a construção do pacote da sua aplicação, contendo:
  - Um diretório de trabalho (workdir) chamado `/to-build-app`
  - A cópia dos arquivos necessários
  - A instalação das dependências utilizando Maven
    - Aqui, se quiser você pode utilizar o goal `dependency:go-offline` do Maven, que vai baixar todas as dependências e pode ajudar o Docker a criar um cache que agilize o processo de re-criação da imagem. 
  - A construção do pacote JAR utilizando Maven com o goal `package`. Utilize também o parâmetro `-DskipTests` do Maven, para evitar ter problemas com os testes durante a construção da sua imagem. 
- O segundo estágio deve ser utilizado para a construção da imagem final, contendo:
  - Um diretório de trabalho (workdir) chamado `/app`
  - A cópia dos arquivos necessários a partir da imagem do primeiro estágio
  - A exposição da porta `8080`
  - Um ponto de entrada (entrypoint) executando o pacote da aplicação

Notas:
1. Você pode usar as imagens de base que preferir para cada estágio. Uma possibilidade é utilizar a `maven:3-openjdk-17` para o estágio de construção, pois já traz o Maven instalado. Já para o estágio final você pode usar uma imagem de tamanho reduzido, como a `eclipse-temurin:17-jre-alpine`, por exemplo.
2. Apesar de o Maven já instalar as dependências na construção do pacote, como mencionado é útil termos uma execução da instalação separada da construção no primeiro estágio, para termos os benefícios de cache do Docker e reduzir o tempo de reconstrução.
3. Quando for testar sua imagem, lembre-se que a exposição da porta no Dockerfile não faz o mapeamento automaticamente (diferente do `docker-compose`). Nesse caso, é necessário passar o mapeamento por parâmetro para o docker na hora da execução da imagem.

</details>

## Como usar
1-Clone o repositório para o seu ambiente local:
git clone https://github.com/marianeCandian/Project-Java-Localizador-de-Museus.git

2-Abra o projeto em sua IDE Java preferida.

3-Execute o programa principal para iniciar a API Rest.

4-Utilize ferramentas como Postman ou curl para enviar requisições para as rotas disponíveis na API.

5-Consulte a documentação da API ou os testes unitários para obter informações detalhadas sobre como usar cada rota.

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests para melhorar este projeto.

