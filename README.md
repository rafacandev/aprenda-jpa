Aprenda JPA
===========
Material para aprender Java JPA (Jakarta Persistence) com Spring Boot.

Requerimentos:
* Conhecimentos basicos de Java
* Java Development Kit (JDK) 19 
* Git

Tech stack utilizada neste material:
* Java 19
* JPA, também conhecido como Jakarta Persistence
* Maven
* Database H2

Proposta
--------
Vamos desenvolver um sistema de empréstimo de itens no qual pessoas poderão pegar emprestados vários itens e devolvê-los posteriormente.
Para facilitar a identificação dos itens, cada um deles pode ter um QR Code.
Itens semelhantes podem estar associados a uma categoria.

```
  ┌─|PESSOA|───┐
  │            │
  │nome        │
  │email       │
  │vinculo     │
  └──────────┬─┘
             │UmParaMuitos
             │
             │
  ┌─|ITEM|───┴─┐
  │            │MuitosParaMuitos    ┌─|CATEGORIA|─┐
  │nome        ├────────────────────┤             │
  │descricao   │                    │nome         │
  └──────────┬─┘                    └─────────────┘
             │UmParaUm
             │
             │
  ┌─|QrCode|─┴─┐
  │            │
  │codigo      │
  └────────────┘
```

Videos
------
- Aprenda JPA 01 - Criar projeto e configuracoes
- Aprenda JPA 02 - Salvar e buscar por chave primaria
- Aprenda JPA 03 - Relacionamento UmParaUm (@OneToOne)
- Aprenda JPA 04 - Relacionamento UmParaMuitos (@OneToMany)
- Aprenda JPA 05 - Relacionamento MuitosParaMuitos (@ManyToMany)
- Aprenda JPA 06 - Fetch Type: Lazy vs Eager 
- Aprenda JPA 07 - @Transactional
- Aprenda JPA 08 - Busca avancada: Pelo Nome do Metodo
- Aprenda JPA 09 - Busca avancada: @Query
- Aprenda JPA 10 - Busca avancada: Criteria API
- Aprenda JPA 11 - Associação por tabelas e colunas

Desenvolvimento
---------------

Você pode rodar os testes pelo seu editor favorito ou pelo maven com o seguinte comando:
```bash
./mvnw
```

Vamos utilizar um banco de dados H2 rodando em memória que será inicializado toda vez que executarmos os testes.

Referencias
===========
* [Jakarta Persistence Specification](https://jakarta.ee/specifications/persistence/3.1/jakarta-persistence-spec-3.1.html)
* [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Spring Boot Data JPA Reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)