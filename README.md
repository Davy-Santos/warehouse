# warehouse

📦 Microsserviço Warehouse (Estoque)

Este projeto é o Microsserviço Warehouse, desenvolvido no bootcamp "Primeiros Passos com Java" da Digital Innovation One (DIO)
.
Ele é o coração da gestão de estoque, sendo responsável por:

🧾 Gerenciar as informações de Produtos e seus respectivos Estoques (StockEntity);

🔍 Disponibilizar consultas detalhadas de produtos (incluindo preço e disponibilidade);

🛒 Processar solicitações de compra (purchase) de forma assíncrona, atualizando o estoque (decrementando a quantidade e marcando como UNAVAILABLE quando zerado);

💰 Garantir que as compras sempre utilizem o lote de menor preço de venda (soldPrice) disponível;

🔄 Gerenciar o ciclo de vida dos lotes de estoque, incluindo entrada (save), liberação (release) e inativação (inactive).

🛠️ Tecnologias Utilizadas

O projeto utiliza o ecossistema Java/Spring, com foco em microsserviços e persistência:

Linguagem: Java 21

Framework: Spring Boot 3.x

Gerenciador de Dependências: Gradle (Kotlin DSL - KTS)

Persistência: Spring Data JPA

Banco de Dados: H2 (em memória, para desenvolvimento)

Mensageria: RabbitMQ (para comunicação assíncrona com o Storefront)

Containerização: Docker e Docker Compose

Outros: Lombok, MapStruct, SpringDoc (Swagger UI)

🔁 Comunicação e Integração

O microsserviço Warehouse é o principal fornecedor de dados para o Storefront.

🔹 Comunicação Síncrona (HTTP)

O Storefront consulta o Warehouse para obter detalhes de um produto específico (preço, nome, etc).

Endpoint: /warehouse/products/{id}

🔹 Comunicação Assíncrona (RabbitMQ)

O Warehouse consome mensagens da fila para processar as compras enviadas pelo Storefront, garantindo que o decremento de estoque seja robusto e desacoplado.

Configuração	Valor
Fila Consumida:	product.change.availability.queue
Exchange:	product.change.availability.exchange
Routing Key:	product.change.availability.routing.key
🚀 Como Executar o Projeto

Este projeto é executado via Docker Compose, que já inclui o serviço do RabbitMQ necessário para a comunicação assíncrona.

🧩 Pré-requisitos

Você deve ter instalado:

Docker

Docker Compose

🪜 Passos
1. Clonar o Repositório
git clone https://github.com/seuusuario/warehouse.git
cd warehouse

2. Configurar a Rede Externa

A rede externa ecommerce-net deve existir (usada também pelo microsserviço Storefront).
Crie-a caso ainda não exista:

docker network create ecommerce-net

3. Executar o Docker Compose

No diretório raiz do projeto, execute:

docker-compose up --build


O serviço Warehouse ficará acessível em:
👉 http://localhost:8080

📝 Endpoints (API)

A documentação completa da API estará disponível via Swagger UI após a inicialização do serviço:

🔗 Swagger UI: http://localhost:8080/warehouse/swagger-ui.html

O Warehouse expõe endpoints para o gerenciamento de Produtos e Lotes de Estoque.

🧾 Endpoints de Produtos (/products)
Método	Endpoint	Descrição	Status de Retorno
POST	/products	Cria um novo produto no catálogo.	201 Created
GET	/products/{id}	Retorna detalhes de um produto, incluindo o preço de venda atual (menor soldPrice disponível).	200 OK
POST	/products/{id}/purchase	Endpoint para testes internos. Simula o processamento de uma compra (na arquitetura real, é tratado pelo Listener do RabbitMQ).	204 No Content
📦 Endpoints de Estoque (/stocks)
Método	Endpoint	Descrição	Status de Retorno
POST	/stocks	Registra um novo lote de estoque de um produto (entrada no armazém). O status inicial é IN_CONFERENCE.	201 Created
PUT	/stocks/{id}/release	Altera o status de um lote de estoque para AVAILABLE, tornando-o elegível para venda.	204 No Content
DELETE	/stocks/{id}/release	Altera o status de um lote de estoque para UNAVAILABLE, inativando-o.	204 No Content
⚙️ Detalhes de Configuração
Configuração	Valor
Context Path:	/warehouse
Porta:	8080
🗃️ Banco de Dados H2 (Desenvolvimento)

Para desenvolvimento, o projeto utiliza o H2 Database em memória.

🔗 Console H2: http://localhost:8080/warehouse/h2-console

Configuração	Valor
JDBC URL:	jdbc:h2:mem:warehouse
Username:	sa
Password:	(vazio)
💻 Desenvolvimento e Debug

O docker-compose.yml e o application-dev.yml foram configurados para um ambiente de desenvolvimento eficiente.

🔥 Hot Reload

O spring-boot-devtools está ativo no perfil dev para recarregar automaticamente a aplicação após alterações.

🐞 Debug Remoto

A porta 5005 do contêiner está exposta para debug.
Você pode anexar seu IDE (IntelliJ, VS Code, etc.) a:

localhost:5005


Trecho do docker-compose.yml:

ports:
  - "8080:8080"
  - "5005:5005"  # Porta de debug remoto
volumes:
  - .:/warehouse:z # Volume para sincronizar arquivos

🤝 Contribuições

Este projeto faz parte de um bootcamp de aprendizado.
Sua contribuição é muito bem-vinda — seja para:

Melhorias de código 🧠

Refatorações 🧩

Correções de bugs 🐛

Novas funcionalidades 🚀
