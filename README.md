Aprenda JPA
===========
Material para aprender Java JPA (Jakarta Persistence) com Spring Boot.

Tech stack utilizada neste material:
* Java 19
* JPA, também conhecido como Jakarta Persistence 
* Maven (opicional)
* Database H2

Proposta
--------
Vamos desenvolver um sistema de empréstimo de itens no qual as pessoas poderão pegar emprestados vários itens e devolvê-los posteriormente.
Para facilitar a identificação dos itens, cada um deles pode ter um QR Code.

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

- Aprenda JPA 01 - Salvar e buscar por chave primaria
- Aprenda JPA 02 - Relacionamente UmParaUm (@OneToOne)
- Aprenda JPA 03 - Relacionamente UmParaMuitos (@OneToMany)
- Aprenda JPA 04 - Relacionamente MuitosParaMuitos (@ManyToMany)
- Aprenda JPA 05 - Melhorando JPA Testes
- Aprenda JPA 06 - Busca personalizada



Desenvolvimento
---------------
Voce pode rodar os testes pelo seu editor favorito ou pelo maven:
```bash
./mvnw
```

Vamos utilizar um banco de dados H2 em arquivos.
O arquivo do banco de dados fica localizado em `target/h2.mv.db`.

Para apagar o banco de dados, basta apagar o arquivo `target/h2.mv.db`:
```bash
rm target/h2.mv.db
```

Ou limpar o projeto pelo maven:
```bash
./mvnw clean
```

Referencias
===========
* [Jakarta Persistence Specification](https://jakarta.ee/specifications/persistence/3.1/jakarta-persistence-spec-3.1.html)
* [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Spring Boot Data JPA Reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)