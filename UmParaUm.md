Diagrama de Entidades:
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

Modelo de banco de dados:
```
  ┌─|ITEM|─────────┐
  │                │
  │id              │
  │nome            │
  │descricao       │
  │qr_code_id : FK │
  └───────────┬────┘
              │
              │
              │
  ┌─|QR_CODE|─┴─┐
  │             │
  │id           │
  │codigo       │
  └─────────────┘
```

Linguagem de Definição de Dados: Data Definition Language (DDL)
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
