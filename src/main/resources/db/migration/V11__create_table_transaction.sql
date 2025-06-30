create table transactions(

    hash_transaction VARCHAR(40) not null,
    data_operacao date not null,
    produto varchar(100) not null,
    tipo_evento varchar(50) not null,
    quantidade bigint not null,
    preco_unitario DECIMAL(20,2) not null,
    valor_operacao DECIMAL(20,2) not null,
    descricao  varchar(100) not null,

    primary key(hash_transaction)

);