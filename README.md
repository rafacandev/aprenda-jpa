Aprenda JPA
===========
Material para aprender Java JPA com Spring Boot.

Tech stack utilizada neste material:
* Java 19
* JPA, também conhecido como Jakarta Persistence 
* Maven (opicional)
* Database H2

Proposta
--------
Vamos construir um sistema de empréstimo de itens.
Nesse sistema, as pessoas podem pegar um item emprestado e depois devolvê-lo.
Quando o item é devolvido, o sistema registra o local onde o item está guardado.

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
             │UmParaMuitos
             │
             │
  ┌─|LOCAL|──┴─┐
  │            │
  │nome        │
  │descricao   │
  └────────────┘
```

Referencias
===========
* [Jakarta Persistence Specification](https://jakarta.ee/specifications/persistence/3.1/jakarta-persistence-spec-3.1.html)
* [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Spring Boot Data JPA Reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)