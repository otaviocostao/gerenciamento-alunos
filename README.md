# Desafio de seleção UnMEP - Sistema de Gestão de Alunos

## Descrição do projeto

Este projeto é a resolução de uma etapa do processo seletivo para a UnMEP. Ele consiste em uma aplicação para listar, tratar e filtrar um arquivo em JSON contendo mais de 1000 registros de alunos.

## Tecnologias utilizadas
- Java 21
- Spring Boot 3.5.4
- Maven 4.0
- Banco de Dados H2
- JPA
- Thymeleaf

## Decisões de Arquitetura
A aplicação foi estruturada seguindo as melhores práticas do ecossistema Spring, com uma clara separação de responsabilidades:

- **`Entity` e `DTO`:** A entidade `Aluno` representa o modelo de dados no banco, enquanto `AlunoDTO` é usado para desserializar o JSON de forma segura, desacoplando a fonte de dados da persistência.
- **`DataLoader` com `CommandLineRunner`:** Os dados são carregados na inicialização da aplicação, garantindo que o banco H2 em memória seja preenchido apenas uma vez.
- **`Repository` com `JPA Specification`:** Para os filtros dinâmicos, foi utilizada a **JPA Specification API**. Essa abordagem permite a construção de queries complexas e combinadas de forma programática, performática e escalável.
- **`Service`:** Camada responsável pela lógica de negócio, tratando as regras dos presets e delegando as consultas dinâmicas para a camada de repositório.
- **`Controller`:** Responsável por mapear as rotas HTTP, receber os parâmetros de filtro e interagir com a camada de serviço para, ao final, renderizar a view com os dados corretos.
- **`Thymeleaf`:** Utilizado para a renderização dinâmica do frontend no lado do servidor, permitindo uma interface funcional sem a necessidade de JavaScript para as funcionalidades principais.

## Funcionalidades Implementadas
- **Carga de Dados:** Leitura de um arquivo JSON local com 1000 registros e persistência em um banco de dados H2 na inicialização da aplicação.
- **Listagem Completa:** Exibição de todos os alunos em uma tabela organizada.
- **Filtros Dinâmicos:**
    - Busca por primeiro ou último nome (case-insensitive).
    - Filtro por média mínima.
    - Filtro por quantidade máxima de faltas.
- **Presets de Filtragem:**
    - Botão para exibir apenas alunos **Aprovados** (Média >= 7.0 E Faltas < 7).
    - Botão para exibir apenas alunos **Reprovados** (Média < 7.0 OU Faltas >= 7).
- **Interface Intuitiva:** Exibição clara da situação do aluno (Aprovado/Reprovado) e o motivo da reprovação, quando aplicável.

## Como rodar a aplicação
  **1 - Clone o repositório**
  
  ```

    git clone https://github.com/otaviocostao/gerenciamento-alunos.git

  ```

  **2 - Navegue até o diretório**

  ```

    cd gerenciamento-alunos

  ```
  3 - Instale as dependencias e rode  o projeto
  
  ```

    mvn spring-boot:run

  ```

  4 - Acesse a página web em:
  
  http://localhost:8080
  
  (A aplicação redirecionará automaticamente para /alunos)
