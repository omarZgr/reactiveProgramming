DROP table product ;

CREATE table product
(
    id serial primary key ,
    description varchar(500),
    price numeric (10,2) not null
)  ;