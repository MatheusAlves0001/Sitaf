# Sitaf Teste Técnico

INSTRUÇÕES PARA RODAR O PROJETO:

-Execute a aplicação TesteSitafApplication.java que se encontra em:
-Sitaf
	-teste-sitaf -
				-Src
				-main
				-java
				-TesteSitafApplication.java
				
-Em seguida execute um navegador de sua preferência.
-Na barra de pesquisa copie cole o seguinte endereço: http://localhost:8080/swagger-ui/index.html
-Você terá acesso a todos os métodos disponíveis na aplicação.

COMO EXECUTAR OS TESTES:

-Para executar os testes unitários e testes de integração, acesse o seguinte repositório dentro de sua IDE:

-Sitaf
	-teste-sitaf -
				-Src
				-teste
				-java
				
-Clique com o botão direito do mouse sobrea a pasta "java" e cliqeu em "Run tests in java"

ATENÇÃO!!

-Por algum motivo, os testes de integração "IntegrationTestCategoryController" falham quando todos os testes são executados
de uma vez. Para evitar o problema, basta executar os testes "IntegrationTestCategoryController" individualmente.


ARQUITETURA

-A arquitetura da aplicação é baseada em 2 layers principais: 

-CONTROLLERS

-São responsáveis por receber as requisições e responde-las. Esta é uma camada simples que é utilizada apenas como forma
de acessar os métodos de processamento, banco de dados, módulos entre outros recursos.

-Os controllers sempre receberão um DTO (data transfer object) que serão modelos de dados não idênticos aos modelos de banco
de dados.

-A resposta à requisição também é feita utilizando DTOs para evitar expor os modelos de banco.

-Foram adicionadas beans validations nos DTOs para que os dados enviados pela view estejam no formato esperado.

-Quando os beans validators encontram problemas em algum objeto enviado pela view, ele retorna uma excessão que é capturada
pelo GlobalExceptionHandler. Há um método que espera exceções do tipo MethodArgumentNotValidException (retornadas pelos
beans validators). Este handler tratará a mensagem de erro para que o usuário saiba qual dado está errado ou faltando no
corpo da requisição.

-SERVICES

-São responsáveis por processar os dados da requisição.

-Após os dados serem processados pelos metodos dos serviços, o objeto de retorno sempre é transformado em DTO com as 
StructureMaps.

-Services utilizam DI para trabalhar com repositórios e outros serviços da aplicação.


-REPOSITORIES

-São interfaces utilizadas para manipular dados do DB. São injetadas nos services e nos testes unitários (neste caso os
repositórios são mockados e não acessam diretamente o banco de dados).

-MODELS

-São modelos do banco de dados.

-SPECIFICATIONS

-Utilizados para criar filtros dinâmicos que serão convertidos em queries para requisições no banco de dados.

-MAPPERS

-São utilizados para transformar dinamicamente modelos de banco em DTOs e vice-versa.


CONCLUSÃO

-Uma das funcionalidades que eu estava prestes a implementar era Autenticação para deixar a API mais interessante. Mas
para decidi enviar a aplicação da forma como está para não extender muito o tempo de entrega.

-O java/SpringBoot é muito semelhante ao C#/.NET. Ao meu ver, a forma como o springBoot configura o banco de dados, modelos
etc é muito mais prático e rápido do que o .NET.

-Me diverti bastante com o projeto e certamente continuarei a explorar esta ferramenta.

-Utilizei o ChatGPT para entender conceitos fundamentais, compreender algumas expressões do java e para automatizar algumas
tarefas repetitivas que eu já sabia o que deveria ser feito.




Obrigado!