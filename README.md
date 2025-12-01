<h1 align="center">🏦 Banco Digital – API Completa em Spring Boot</h1>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17+-red?logo=openjdk"/>
  <img src="https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?logo=springboot"/>
  <img src="https://img.shields.io/badge/Maven-Build-blue?logo=apachemaven"/>
  <img src="https://img.shields.io/badge/SQL_Server-Database-lightgrey?logo=microsoftsqlserver"/>
  <img src="https://img.shields.io/badge/Status-Em_Desenvolvimento-yellow"/>
</p>

<hr/>

<h2>📌 Sobre o Projeto</h2>

<p>
A API simula um banco digital completo utilizando Spring Boot seguindo princípios de arquitetura limpa, SOLID e padrão MVC.<br>
Inclui funcionalidades como:
</p>

<ul>
  <li>Cadastro de clientes (Comum, Premium, Super)</li>
  <li>Contas bancárias integradas</li>
  <li>Cartões de Crédito e Débito</li>
  <li>Seguros (fraude e viagem)</li>
  <li>Pagamentos, depósitos, saques e transferências</li>
</ul>

<hr/>

<h2>🚀 Tecnologias Utilizadas</h2>

<ul>
  <li><b>Java 17+</b></li>
  <li><b>Spring Boot 3.x</b></li>
  <li><b>Spring Data JPA</b></li>
  <li><b>SQL Server</b></li>
  <li><b>Lombok</b></li>
  <li><b>Maven</b></li>
</ul>

<hr/>

<h2>📂 Arquitetura do Projeto</h2>

<pre>
src/main/java
 └── br.com.cdb.bancodigital
      ├── controller   → Endpoints REST
      ├── service      → Regras de negócio
      ├── entity       → Entidades JPA
      ├── repository   → Interfaces do banco
      ├── dto          → Objetos de transferência
      └── enums        → Tipos/Constantes
</pre>

<hr/>

<h2>📡 Tabela de Endpoints</h2>

<table>
  <thead>
    <tr>
      <th>Recurso</th>
      <th>Método</th>
      <th>Endpoint</th>
      <th>Descrição</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Clientes</td>
      <td>POST</td>
      <td>/clientes</td>
      <td>Criar novo cliente</td>
    </tr>
    <tr>
      <td>Clientes</td>
      <td>GET</td>
      <td>/clientes/{id}</td>
      <td>Buscar cliente por ID</td>
    </tr>
    <tr>
      <td>Contas</td>
      <td>POST</td>
      <td>/contas/{idCliente}</td>
      <td>Criar conta associada ao cliente</td>
    </tr>
    <tr>
      <td>Contas</td>
      <td>POST</td>
      <td>/contas/depositar</td>
      <td>Realizar depósito</td>
    </tr>
    <tr>
      <td>Contas</td>
      <td>POST</td>
      <td>/contas/sacar</td>
      <td>Realizar saque</td>
    </tr>
    <tr>
      <td>Contas</td>
      <td>POST</td>
      <td>/contas/transferir</td>
      <td>Transferir entre contas</td>
    </tr>
    <tr>
      <td>Cartão Débito</td>
      <td>POST</td>
      <td>/cartoes/debito/{idConta}</td>
      <td>Criar cartão de débito para conta</td>
    </tr>
    <tr>
      <td>Cartão Débito</td>
      <td>POST</td>
      <td>/cartoes/debito/pagar</td>
      <td>Realizar pagamento com débito</td>
    </tr>
    <tr>
      <td>Cartão Crédito</td>
      <td>POST</td>
      <td>/cartoes/credito/{idConta}</td>
      <td>Criar cartão de crédito</td>
    </tr>
    <tr>
      <td>Seguro</td>
      <td>POST</td>
      <td>/seguros/{idCliente}</td>
      <td>Ativar seguro do cliente</td>
    </tr>
  </tbody>
</table>

<hr/>

<h2>🗄️ Configurações do Banco de Dados</h2>

<p>Configuração usada no arquivo <code>application.properties</code>:</p>

<pre>
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=bancodigitalcdb;encrypt=true;trustServerCertificate=true
spring.datasource.username=sa
spring.datasource.password=**********
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.SQLServerDialect
spring.jpa.show-sql=true
</pre>

<hr/>

<h2>▶️ Como Rodar o Projeto</h2>

<h3>1️⃣ Clonar o repositório</h3>

<pre>
git clone https://github.com/souzzdev/api-bancodigital
</pre>

<h3>2️⃣ Abrir no IntelliJ IDEA</h3>

<p>Menu → <b>File</b> → <b>Open</b> → selecione a pasta do projeto.</p>

<h3>3️⃣ Rodar</h3>

<p>Abra a classe:</p>

<pre>BancoDigitalApplication</pre>

<p>e execute ➤</p>

<h3>4️⃣ Acessar a API</h3>

<pre>
http://localhost:8080/bancodigital
</pre>

<hr/>

<h2>📬 Contato</h2>

<p>
Desenvolvido por <b>souzzdev</b>  
<br/>
GitHub: <a href="https://github.com/souzzdev">https://github.com/souzzdev</a>
</p>

<hr/>

<h3 align="center">💚 Obrigado por utilizar esta API! 🚀</h3>
