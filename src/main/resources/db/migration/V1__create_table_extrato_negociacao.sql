create table extrato_negociacao(

    hash_negociacao varchar(40) not null ,
    data_negocio date not null,
    tipo_movimentacao varchar(50) not null,
    cod_produto varchar(100) not null,
    instituicao varchar(100) not null,
    quantidade bigint not null,
    preco_unitario DECIMAL(20,2) not null,
    valor_operacao DECIMAL(20,2) not null,

    primary key(hash_negociacao)

);