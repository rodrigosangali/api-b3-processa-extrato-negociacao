create table current_price(

    produto varchar(10) not null,
    valor_operacao DECIMAL(20,2) not null,
    moeda CHAR(3) not null,

    primary key(produto)

);