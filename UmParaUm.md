Associação UmParaUm - @OneToOne
===============================
Vamos explorar o exemplo onde um Item tem apenas um QR Code.

Padrão JPA
----------

Dado o seguinte diagrama de entidades:
```
  ┌─|ITEM|─────┐
  │            │
  │nome        │
  │descricao   │
  └──────────┬─┘
             │UmParaUm
             │
             │
  ┌─|QrCode|─┴─┐
  │            │
  │codigo      │
  └────────────┘
```

Vamos utilizar a segunte classe:
```java
public class Item {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @OneToOne
    private QrCode qrCode;
}
```

Por padrão, será utilizado o seguinte modelo de banco de dados, onde a associação é feita por meio de uma coluna:
```
  ┌─|ITEM|─────────┐
  │                │
  │id         : PK │
  │nome            │
  │descricao       │
  │qr_code_id : FK │
  └───────────┬────┘
              │
              │
              │
  ┌─|QR_CODE|─┴─┐
  │             │
  │id      : PK │
  │codigo       │
  └─────────────┘
```

Caso o projeto esteja configurado para gerar o banco de dados automaticamente, será utilizada a seguinte Linguagem de Definição de Dados (Data Definition Language (DDL)):
```
create table item (
    id integer not null,
    descricao varchar(255) not null,
    nome varchar(255) not null,
    qr_code_id integer,
    primary key (id)
)

alter table if exists item 
   add constraint FKowqn98yqun39k9h8ebt72uu6i 
   foreign key (qr_code_id) 
   references qr_code

create table qr_code (
    id integer not null,
    codigo varchar(255),
    primary key (id)
)
```

Utilizando uma tabela intermediária
-----------------------------------

Para utilizar uma tabela intermediária, temos que adicionar uma anotação `@JoinTable`.
```java
public class Item {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @OneToOne
    @JoinTable(name = "item_qr_code",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "qr_code_id"))
    private QrCode qrCode;
}
```

Será utilizado o seguinte modelo de banco de dados, onde a associação é feita por meio de uma tabela intermediária:
```
  ┌─|ITEM|───────────┐
  │                  │
  │id           : PK │
  │nome              │
  │descricao         │
  │qr_code_id   : FK │
  └────────────────┬─┘
                   │
                   │
                   │
  ┌─|ITEM_QR_CODE|─┴─┐
  │                  │
  │id                │
  │codigo            │
  └──────────────────┘
```

Caso o projeto esteja configurado para gerar o banco de dados automaticamente, será utilizada a seguinte Linguagem de Definição de Dados (Data Definition Language (DDL)):
```
create table item (
    id integer not null,
    descricao varchar(255) not null,
    nome varchar(255) not null,
    primary key (id)
)

create table item_qr_code (
    qr_code_id integer,
    item_id integer not null,
    primary key (item_id)
)

create table qr_code (
    id integer not null,
    codigo varchar(255),
    primary key (id)
)

alter table if exists item_qr_code 
    add constraint FKbeo9immmayau8u58o4rukhigh 
    foreign key (qr_code_id) 
    references qr_code

alter table if exists item_qr_code 
    add constraint FKdm822r743ki9iof7c56noat48 
    foreign key (item_id) 
    references item
```