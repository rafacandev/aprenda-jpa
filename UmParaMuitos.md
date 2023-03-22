Diagrama de Entidades:
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

Modelo de banco de dados:
```
  ┌─|PESSOA|───┐
  │            │
  │id          │
  │nome        │
  │email       │
  │vinculo     │
  └──────────┬─┘
             │
             │
             │
  ┌─|PESSOA_ITEMS|───┐
  │                  │
  │pessoa_id : PK FK │
  │item_id   : PK FK │
  └──────────────────┘
             │
             │
             │
  ┌─|ITEM|─────────┐
  │                │
  │id              │
  │nome            │
  │descricao       │
  └────────────────┘
```

Linguagem de Definição de Dados: Data Definition Language (DDL)
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