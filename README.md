# Teste prático Iniflex

Este projeto foi desenvolvido como resolução de um teste prático em Java. O objetivo foi modelar pessoas e funcionários e, a partir de uma lista de funcionários, realizar operações como remoção, reajuste salarial, ordenação, filtragem e cáculos.

Durante a implementação, procurei organizar cada etapa em métodos menores para deixar o fluxo da classe `Main` mais legível e facilitar a verificação dos resultados.

## Estrutura do projeto

- `Pessoa`: classe base, com nome e data de nascimento.
- `Funcionario`: herda de `Pessoa` e acrescenta salário e função.
- `Main`: cadastra os funcionários e executa as operações solicitadas.

## Linha de raciocínio

### Modelagem de Pessoa e Funcionário

Comecei pela classe `Pessoa`, pois ela concentra os atributos que também pertencem a um funcionário: nome e data de nascimento. Os atributos foram definidos como privados e seu acesso é feito por meio de construtor, getters e setters.

Em seguida, criei a classe `Funcionario`, que estende `Pessoa` e adiciona os atributos salário e função. No construtor de `Funcionario`, utilizei `super(nome, dataNascimento)` para chamar o construtor da classe pai e inicializar os atributos herdados.

Para o salário, escolhi `BigDecimal`. Tipos como `double` e `float` representam valores em ponto flutuante binário e podem introduzir aproximações indesejadas em cálculos monetários. O `BigDecimal` representa o valor decimal por meio de um valor inteiro sem escala e de uma escala decimal, oferecendo maior controle sobre precisão e arredondamento. Os valores foram criados a partir de `String` para evitar que uma aproximação de `double` fosse incorporada ao objeto.

### Cadastro dos funcionários

Criei um `ArrayList<Funcionario>` inicialmente vazio e adicionei os funcionários conforme os dados fornecidos na tabela. No primeiro momento, criei Maria em uma variável separada antes de adicioná-la à lista, como forma de visualizar a criação da instância. Depois, padronizei os cadastros, criando cada objeto diretamente no método `add` para evitar repetição desnecessária.

### Impressão e formatação

Antes de remover João, imprimi a lista completa para conferir se todos os dados tinham sido cadastrados corretamente. Sobrescrevi o método `toString` de `Funcionario` para exibir os dados do objeto em vez de sua representação padrão em memória.

Nesse método, utilizei:

- `DateTimeFormatter` para exibir a data no padrão `dd/MM/yyyy`;
- `NumberFormat` com a localidade `pt-BR` para exibir o salário em reais;
- `String.format` para organizar as informações em uma única linha.

Como a lista precisa ser impressa em mais de um momento, extraí a repetição para o método `imprimirFuncionarios`.

### Remoção de João

Para remover João, utilizei `removeIf` com uma expressão lambda. A condição verifica o nome de cada funcionário e remove aquele cujo nome seja igual a `João`. Preferi essa solução em vez de remover por índice, pois ela expressa a regra do requisito e não depende da posição do funcionário na lista.

### Aumento salarial

Criei um método específico para aplicar o aumento de 10% e manter o método principal mais organizado. O fator de reajuste foi representado por `new BigDecimal("1.10")` e aplicado a cada salário com o método `multiply`.

Como `BigDecimal` é imutável, a multiplicação não altera o objeto original: ela devolve um novo valor. Por esse motivo, o resultado é armazenado em `novoSalario` e atribuído novamente ao funcionário por meio do setter.

### Agrupamento por função

O requisito de criar um `Map` cuja chave seja a função e cujo valor seja uma lista de funcionários não foi concluído nesta versão. Compreendo o conceito introdutório de `Map` como uma estrutura de chave e valor, semelhante a um dicionário, mas ainda não tenho familiaridade suficiente para aplicar com segurança o agrupamento solicitado.

Como alternativa parcial para a visualização, ordenei a lista pela função e imprimi nome e função. Essa ordenação aproxima visualmente os funcionários com a mesma função, mas reconheço que não substitui o agrupamento em `Map` exigido nos itens 3.5 e 3.6. Esse é o principal ponto que pretendo estudar e implementar como próxima evolução do projeto.

O item 3.7 não foi implementado porque não existe um requisito 3.7 na descrição recebida.

### Aniversariantes de outubro e dezembro

Criei uma lista auxiliar para armazenar os aniversariantes. Percorri os funcionários e consultei o mês da data de nascimento com `LocalDate`. Quando o mês era outubro ou dezembro, o funcionário era adicionado à lista de aniversariantes. Na saída, mostrei apenas nome e data de nascimento para manter o resultado objetivo.

### Funcionário mais velho

Para localizar o funcionário mais velho, comecei considerando o primeiro elemento da lista como o mais velho. Em seguida, percorri os demais funcionários e comparei as datas completas com `isBefore`. Sempre que encontrava uma data de nascimento anterior, atualizava a referência do funcionário mais velho.

A idade foi calculada com `Period.between`, considerando a data de nascimento e a data atual. Essa abordagem evita o erro de apenas subtrair os anos sem verificar se o aniversário já ocorreu no ano corrente.

### Ordenação alfabética

Para imprimir os funcionários em ordem alfabética, utilizei `List.sort` com `Comparator.comparing(Funcionario::getNome)`. A mesma estratégia foi utilizada na ordenação por função, alterando apenas o atributo usado pelo comparador. Essas operações modificam a ordem da lista original.

### Total dos salários

Para calcular o total, criei um acumulador iniciado com `BigDecimal.ZERO`. Em seguida, percorri a lista e somei cada salário com `add`. Como `BigDecimal` é imutável, atribuo o resultado de cada soma novamente ao acumulador. Por fim, formatei o total em reais.

Considerei inicialmente utilizar uma redução, por já conhecer o conceito de `reduce` do JavaScript. Como ainda não dominava sua aplicação em Java, optei pelo laço e pelo acumulador, que tornaram a lógica mais explícita para mim.

### Quantidade de salários mínimos

O valor do salário mínimo foi declarado como uma constante `BigDecimal`. Para cada funcionário, dividi o salário por essa constante usando `divide`, definindo duas casas decimais e `RoundingMode.HALF_UP`. A saída mostra somente o nome e a quantidade de salários mínimos, evitando informações que não são necessárias nesse resultado.

## Situação dos requisitos

- [x] 3.1 — Cadastro dos funcionários.
- [x] 3.2 — Remoção de João.
- [x] 3.3 — Impressão com data e valor monetário formatados.
- [x] 3.4 — Aumento salarial de 10%.
- [ ] 3.5 — Agrupamento em `Map` por função.
- [ ] 3.6 — Impressão a partir do agrupamento em `Map` (foi feita uma ordenação por função como alternativa parcial).
- [x] 3.8 — Aniversariantes dos meses 10 e 12.
- [x] 3.9 — Funcionário mais velho, com nome e idade.
- [x] 3.10 — Funcionários em ordem alfabética.
- [x] 3.11 — Soma dos salários.
- [x] 3.12 — Quantidade de salários mínimos por funcionário.

## Execução

O projeto pode ser aberto no IntelliJ IDEA. Para acompanhar os resultados, basta executar a classe `Main`, que cadastra os funcionários e apresenta cada etapa no console.

## Considerações finais

Durante o desenvolvimento, preferi utilizar soluções que eu compreendesse e conseguisse explicar, mesmo quando existiam alternativas mais curtas. A separação das operações em métodos ajudou a organizar a classe principal, e os commits foram feitos por requisito para registrar a evolução do projeto.

O principal aprendizado pendente é aprofundar o uso de `Map` e de agrupamentos de coleções em Java. Minha próxima etapa é estudar esse conceito e completar os itens 3.5 e 3.6.
