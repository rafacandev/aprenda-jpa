Diagrama de Entidades:
```
  ┌─|ITEM|─────┐
  │            │MuitosParaMuitos    ┌─|CATEGORIA|─┐
  │nome        ├────────────────────┤             │
  │descricao   │                    │nome         │
  └────────────┘                    └─────────────┘
```

Modelo de banco de dados:
```
  ┌─|ITEM|─────────┐       ┌─|ITEM_CATEGORIAS|───┐      ┌─|CATEGORIA|────┐
  │                │       │                     │      │                │
  │id         : PK │       │item_id      : PK FK │      │id         : PK │
  │nome            │───────│categoria_id : PK FK │──────│nome            │
  │descricao       │       └─────────────────────┘      │descricao       │
  └────────────────┘                                    └────────────────┘
```

Linguagem de Definição de Dados: Data Definition Language (DDL)
```
create table item (
    id integer not null,
    descricao varchar(255) not null,
    nome varchar(255) not null,
    primary key (id)
)

create table item_categorias (
    item_id integer not null,
    categorias_id integer not null,
    primary key (item_id, categorias_id)
)
    
alter table if exists item_categorias 
    drop constraint if exists UK_rrjlk2ry5opvgpxdyj1eyd0ar

alter table if exists item_categorias 
    add constraint UK_rrjlk2ry5opvgpxdyj1eyd0ar unique (categorias_id)

alter table if exists item_categorias 
    add constraint FKdj3oxp3t3y1skl84a1g3e3af3 
    foreign key (categorias_id) 
    references categoria

alter table if exists item_categorias 
    add constraint FKnahruaf9ygpkm6bglistt5ubq 
    foreign key (item_id) 
    references item
    
create table categoria (
    id integer not null,
    nome varchar(255),
    primary key (id)
)

alter table if exists categoria 
    drop constraint if exists UK_prx5elpv558ah8pk8x18u56yc
    
alter table if exists categoria 
    add constraint UK_prx5elpv558ah8pk8x18u56yc unique (nome) 
```
