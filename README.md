<h1 align="center">🏦 Banco Digital – API Completa em Spring Boot</h1>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17+-red?logo=openjdk"/>
  <img src="https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?logo=springboot"/>
  <img src="https://img.shields.io/badge/Maven-Build-blue?logo=apachemaven"/>
  <img src="https://img.shields.io/badge/SQL_Server-Database-lightgrey?logo=microsoftsqlserver"/>
  <img src="https://img.shields.io/badge/Status-Em_Desenvolvimento-yellow"/>
</p>

<hr/>

<h2>📘 Sumário</h2>
<ul>
  <li><a href="#sobre">📌 Sobre o Projeto</a></li>
  <li><a href="#tecnologias">🚀 Tecnologias Utilizadas</a></li>
  <li><a href="#arquitetura">📂 Arquitetura</a></li>
  <li><a href="#erd">🗄️ Diagrama ER</a></li>
  <li><a href="#endpoints">📡 Tabela de Endpoints</a></li>
  <li><a href="#rodar">▶️ Como Rodar o Projeto</a></li>
  <li><a href="#exemplos">🧪 Exemplos de Requisição</a></li>
  <li><a href="#autor">🙋‍♂️ Autor</a></li>
</ul>

<hr/>

<h2 id="sobre">📌 Sobre o Projeto</h2>

<p>
Este projeto é uma API completa que simula um banco digital, contando com funcionalidades reais como:
</p>

<ul>
  <li>Cadastro de clientes (COMUM, PREMIUM, SUPER)</li>
  <li>Criação de contas bancárias</li>
  <li>Depósitos, saques e transferências</li>
  <li>Cartões de crédito e débito</li>
  <li>Controle de limites</li>
  <li>Alteração de senha dos cartões</li>
  <li>Sistema de seguros (fraude e viagem)</li>
  <li>Arquitetura limpa, SOLID e MVC</li>
</ul>

<hr/>

<h2 id="tecnologias">🚀 Tecnologias Utilizadas</h2>

<ul>
  <li><b>Java 17+</b></li>
  <li><b>Spring Boot 3</b></li>
  <li><b>Spring Web</b></li>
  <li><b>Spring Data JPA</b></li>
  <li><b>Hibernate</b></li>
  <li><b>SQL Server</b></li>
  <li><b>Maven</b></li>
  <li><b>Lombok</b></li>
</ul>

<hr/>

<h2 id="arquitetura">📂 Arquitetura do Projeto</h2>

<pre>
src/main/java/br/com/cdb/bancodigital
 ├── controller      # Endpoints REST
 ├── service         # Regras de negócio (SOLID)
 ├── entity          # Entidades JPA
 ├── dto             # DTOs de requisição/resposta
 ├── repository      # Repositórios JPA
 └── enums           # Enums do sistema
</pre>

<hr/>

<h2 id="erd">🗄️ Diagrama ER (ASCII)</h2>

<pre>
        +-------------+
        |   CLIENTE   |
        +-------------+
        | cpf (PK)    |
        | nome        |
        | tipoCliente |
        +------+------+
               |
               | 1:N
               |
      +--------v--------+
      |  CONTA BANCARIA |
      +-----------------+
      | id (PK)         |
      | saldo           |
      | tipoConta       |
      +--------+--------+
               |
               | 1:N
               |
        +------v------+
        |   CARTAO    |
        +-------------+
        | id (PK)     |
        | senha       |
        | ativo       |
        | limite      |
        +-------------+

        +-------------+
        |   SEGURO    |
        +-------------+
        | id (PK)     |
        | valorMensal |
        | fraude      |
        | viagem      |
        +-------------+
</pre>

<hr/>

<h2 id="endpoints">📡 Tabela de Endpoints</h2>

<h3>🧍 Clientes</h3>

<table>
  <tr><th>Método</th><th>Endpoint</th><th>Descrição</th></tr>
  <tr><td>POST</td><td>/clientes</td><td>Cadastrar cliente</td></tr>
  <tr><td>GET</td><td>/clientes</td><td>Listar clientes</td></tr>
  <tr><td>GET</td><td>/clientes/{cpf}</td><td>Buscar cliente por CPF</td></tr>
  <tr><td>PUT</td><td>/clientes/{cpf}</td><td>Atualizar dados</td></tr>
  <tr><td>DELETE</td><td>/clientes/{cpf}</td><td>Excluir cliente</td></tr>
</table>

<h3>🏦 Contas</h3>

<table>
  <tr><th>Método</th><th>Endpoint</th><th>Descrição</th></tr>
  <tr><td>POST</td><td>/contas/criar</td><td>Criar conta bancária</td></tr>
  <tr><td>GET</td><td>/contas/{id}</td><td>Buscar conta por ID</td></tr>
  <tr><td>PUT</td><td>/contas/{id}/depositar</td><td>Realizar depósito</td></tr>
  <tr><td>PUT</td><td>/contas/{id}/sacar</td><td>Realizar saque</td></tr>
  <tr><td>PUT</td><td>/contas/{idOrigem}/transferir/{idDestino}</td><td>Transferir valores</td></tr>
  <tr><td>PUT</td><td>/contas/poupanca/{id}/aplicar-rendimento</td><td>Aplicar rendimento</td></tr>
</table>

<h3>💳 Cartões de Débito</h3>

<table>
  <tr><th>Método</th><th>Endpoint</th><th>Descrição</th></tr>
  <tr><td>POST</td><td>/cartoes/debito/criar</td><td>Criar cartão de débito</td></tr>
  <tr><td>PUT</td><td>/cartoes/debito/{id}/ativar</td><td>Ativar cartão</td></tr>
  <tr><td>PUT</td><td>/cartoes/debito/{id}/desativar</td><td>Desativar cartão</td></tr>
  <tr><td>PUT</td><td>/cartoes/debito/{id}/alterar-senha</td><td>Alterar senha</td></tr>
  <tr><td>PUT</td><td>/cartoes/debito/{id}/limite</td><td>Alterar limite diário</td></tr>
  <tr><td>PUT</td><td>/cartoes/debito/{id}/pagar</td><td>Realizar pagamento</td></tr>
</table>

<h3>💳 Cartões de Crédito</h3>

<table>
  <tr><th>Método</th><th>Endpoint</th><th>Descrição</th></tr>
  <tr><td>POST</td><td>/cartoes/credito/criar</td><td>Criar cartão de crédito</td></tr>
  <tr><td>PUT</td><td>/cartoes/credito/{id}/ativar</td><td>Ativar cartão</td></tr>
  <tr><td>PUT</td><td>/cartoes/credito/{id}/desativar</td><td>Desativar cartão</td></tr>
  <tr><td>PUT</td><td>/cartoes/credito/{id}/alterar-senha</td><td>Alterar senha</td></tr>
  <tr><td>PUT</td><td>/cartoes/credito/{id}/limite</td><td>Alterar limite</td></tr>
  <tr><td>PUT</td><td>/cartoes/credito/{id}/pagar</td><td>Efetuar compra</td></tr>
</table>

<h3>🛡️ Seguros</h3>

<table>
  <tr><th>Método</th><th>Endpoint</th><th>Descrição</th></tr>
  <tr><td>POST</td><td>/seguros/criar</td><td>Criar seguro</td></tr>
  <tr><td>GET</td><td>/seguros/{id}</td><td>Buscar seguro</td></tr>
</table>

<hr/>

<h2 id="rodar">▶️ Como Rodar o Projeto</h2>

<h3>1️⃣ Clonar o repositório</h3>

<pre>
git clone https://github.com/souzzdev/api-bancodigital.git
</pre>

<h3>2️⃣ Criar banco no SQL Server</h3>

<pre>
CREATE DATABASE bancodigitalcdb;
</pre>

<h3>3️⃣ Configurar o <code>application.properties</code></h3>

<pre>
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=bancodigitalcdb;encrypt=true;trustServerCertificate=true
spring.datasource.username=sa
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
</pre>

<h3>4️⃣ Rodar o projeto</h3>

<pre>mvn spring-boot:run</pre>

ou pelo IntelliJ → Run ▶️

<hr/>

<h2 id="exemplos">🧪 Exemplos de Requisições</h2>

<h3>📌 Criar cliente</h3>
<pre>
POST /clientes

{
  "nome": "João Silva",
  "cpf": "12345678900",
  "dataNascimento": "10/10/1990",
  "endereco": "Rua A, 123",
  "tipoCliente": "PREMIUM"
}
</pre>

<h3>📌 Criar conta</h3>
<pre>
POST /contas/criar

{
  "cpfCliente": "12345678900",
  "tipoConta": "CORRENTE"
}
</pre>

<h3>📌 Alterar senha do cartão</h3>
<pre>
PUT /cartoes/debito/1/alterar-senha

{
  "senhaAtual": "1234",
  "novaSenha": "9999"
}
</pre>

<hr/>

<h2 id="autor">🙋‍♂️ Autor</h2>

<p>
<b>Guilherme Souza (souzzdev)</b><br/>
Desenvolvedor Backend Java<br/>
Linkedin: <a href="https://www.linkedin.com/in/guilhermesouzadev/">https://github.com/souzzdev</a>
</p>

<hr/>

<h3 align="center">💚 Obrigado por visitar esta API! 🚀</h3>
