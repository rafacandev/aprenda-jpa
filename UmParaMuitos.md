Associação UmParaMuitos - @OneToMany
====================================
Vamos explorar o exemplo onde uma Pessoa pode ter muitos Items.


## Padrao JPA

Dado o seguinte diagrama de entidades:
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
  ┌─|ITEM|─────┐
  │            │
  │nome        │
  │descricao   │
  └────────────┘
```

Vamos utilizar a segunte classe:

```java
public class Pessoa {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Vinculo vinculo = Vinculo.VISITANTE;

    @OneToMany
    private Set<Item> items = new HashSet<>();
}
```

Por padrão, será utilizado o seguinte modelo de banco de dados, onde a associação é feita por meio de uma tabela intermediária:
```
  ┌─|PESSOA|─────┐
  │              │
  │id       : Pk │
  │nome          │
  │email         │
  │vinculo       │
  └──────────┬───┘
             │
             │
             │
  ┌─|PESSOA_ITEMS|───┐
  │                  │
  │pessoa_id : PK FK │
  │items_id  : PK FK │
  └──────────────────┘
             │
             │
             │
  ┌─|ITEM|─────────┐
  │                │
  │id         : PK │
  │nome            │
  │descricao       │
  └────────────────┘
```

Caso o projeto esteja configurado para gerar o banco de dados automaticamente, será utilizada a seguinte Linguagem de Definição de Dados (Data Definition Language (DDL)):
```
create table pessoa (
    id integer not null,
    email varchar(255) not null,
    nome varchar(255) not null,
    vinculo varchar(255) not null,
    primary key (id)
)

create table pessoa_items (
    pessoa_id integer not null,
    items_id integer not null,
    primary key (pessoa_id, items_id)
)

alter table if exists pessoa_items 
    add constraint UK_o0higbikgd1r5g74ok07vkvq7 unique (items_id)
   
alter table if exists pessoa_items 
    add constraint FK9bt8u9skofrob5fbfwm43v8oy 
    foreign key (items_id) 
    references item
    
alter table if exists pessoa_items 
    add constraint FKlh9iog46jm7lye2n9v9yirqn1 
    foreign key (pessoa_id) 
    references pessoa

create table item (
    id integer not null,
    descricao varchar(255) not null,
    nome varchar(255) not null,
    primary key (id)
)
```


Utilizando uma coluna associativa
---------------------------------

Para utilizar uma coluna associativa, temos que adicionar uma anotação `@JoinColumn`.
```java
public class Pessoa {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Vinculo vinculo = Vinculo.VISITANTE;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Set<Item> items = new HashSet<>();
}
```

Será utilizado o seguinte modelo de banco de dados, onde a associação é feita por meio de uma coluna associativa:
```
  ┌─|PESSOA|─────┐
  │              │
  │id       : PK │
  │nome          │
  │email         │
  │vinculo       │
  └──────────┬───┘
             │
             │
             │
  ┌─|ITEM|─────────┐
  │                │
  │id         : PK │
  │nome            │
  │descricao       │
  |pessoa_id  : FK │
  └────────────────┘
```

Caso o projeto esteja configurado para gerar o banco de dados automaticamente, será utilizada a seguinte Linguagem de Definição de Dados (Data Definition Language (DDL)):
```
create table item (
    id integer not null,
    descricao varchar(255) not null,
    nome varchar(255) not null,
    person_id integer,
    primary key (id)
)

create table pessoa (
    id integer not null,
    email varchar(255) not null,
    nome varchar(255) not null,
    vinculo varchar(255) not null,
    primary key (id)
)

alter table if exists item 
    add constraint FKrnfxsvjotfar6gp5nfblj6lei 
    foreign key (person_id) 
    references pessoa
```
