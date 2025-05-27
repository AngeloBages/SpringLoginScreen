
# Login Screen - Spring MVC Application

Este é um projeto web construído com **Spring MVC**, **Spring Data JPA**, **Hibernate** e **Thymeleaf**. Ele fornece uma interface simples para autenticação de usuários, com recursos de login, registro, redefinição de senha e uma página inicial após o login.

## ✨ Funcionalidades

- Login de usuários
- Registro de novas contas
- Recuperação de senha
- Página inicial protegida
- Validações básicas com mensagens de erro
- Integração com banco de dados MySQL
- Views usando Thymeleaf
- Arquitetura em camadas com boas práticas (Controller, Service, Repository, Domain)

---

## 🛠️ Tecnologias utilizadas

- Java 17+
- Spring Framework (MVC, Data JPA, Transaction Management)
- Hibernate
- Thymeleaf
- MySQL
- Jakarta EE 10 (Servlet API)
- Maven

---

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── com.loginscreen/
│   │       ├── config/             # Configurações do JPA e Web MVC
│   │       ├── controller/         # Controllers para login, cadastro e senha
│   │       ├── model/              # Entidades JPA (ex: User)
│   │       ├── repository/           
│   │       │  ├── implementations/ # Implementações concretas da persistência com JPA
│   │       │  └── interfaces/      # Interfaces dos repositórios e abstrações
│   │       └── service/            # Serviços como autenticação e usuário
│   ├── resources/
│   │   └── application.properties  # Configurações do banco e Hibernate
│   └── webapp/
│       └── WEB-INF/
│           ├── views/              # Templates Thymeleaf (login, register, etc)
│           └── web.xml             # Arquivo de configuração do Servlet
```

---

## ⚙️ Configurações

As configurações de banco de dados e Hibernate estão no arquivo `application.properties`:

```
db.driver = com.mysql.cj.jdbc.Driver
db.url = jdbc:mysql://localhost:3306/LoginScreen?useSSl=false
db.username = root
db.password = root

hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
hibernate.show_sql=true
hibernate.hbm2ddl.auto=update
```

### `web.xml`

```xml
<servlet>
    <servlet-name>dispatcher</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextClass</param-name>
        <param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
    </init-param>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>com.loginscreen.config.WebConfig</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>

<servlet-mapping>
    <servlet-name>dispatcher</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

---

## 🧩 Principais Componentes

- **`User.java`**: Entidade que representa um usuário com campos como `email`, `password`.
- **`UserService.java`**: Lógica de negócio para registro, autenticação e atualização de senha.
- **`AuthenticationService.java`**: Responsável por validar credenciais.
- **`UserJpaRepository.java`**: Interface JPA para acesso a dados.
- **`UserJpaAdapter.java`**: Implementação de acesso a dados conectando a interface `UserGateway` com o repositório JPA.
- **`UserGateway.java`**: Abstração da camada de persistência.
- **`LoginController.java`**, **`PasswordController.java`**, **`HomeController.java`**: Controladores responsáveis pelas rotas principais da aplicação.
- **`GlobalHandler.java`**: Manipulador global de exceções.
- **`JpaConfig.java`**: Configuração do JPA, `DataSource`, `EntityManagerFactory` e `TransactionManager`.
- **`WebConfig.java`**: Configuração Spring MVC e Thymeleaf.

---

## 🌐 Interface Web (Thymeleaf)

### Páginas HTML

- `login.html`: Tela de login com links para registro e recuperação de senha.
- `register.html`: Formulário para criação de nova conta.
- `forgot-password.html`: Formulário para redefinir a senha.
- `home.html`: Página inicial após autenticação.

---

## 📬 Endpoints

| Método | URL                  | Ação                              |
|--------|----------------------|-----------------------------------|
| GET    | `/login`             | Exibe tela de login               |
| POST   | `/login`             | Valida credenciais de acesso      |
| GET    | `/register`          | Exibe tela de registro            |
| POST   | `/register`          | Realiza o registro de um usuário  |
| GET    | `/forgot-password`   | Exibe formulário de redefinição   |
| POST   | `/forgot-password`   | Atualiza a senha do usuário       |
| GET    | `/home`              | Página inicial (pós login)        |

---

## ▶️ Como Executar Localmente

### Pré-requisitos

- JDK 17+
- Maven
- MySQL

### Passos

1. Criar o banco de dados:

```sql
CREATE DATABASE loginscreen;
```

2. Clonar o repositório:

```bash
git clone https://github.com/seu-usuario/loginscreen.git
cd loginscreen
```

3. Compilar e rodar a aplicação:

```bash
mvn clean install
mvn tomcat7:run
```

4. Acesse em: `http://localhost:8080/loginscreen/login`
