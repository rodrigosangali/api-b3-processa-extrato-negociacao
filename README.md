    ## Rotas
        extrato/negociação

        grava na base de dados mysql, na tabela extrato_negociação com os campos do model ExtratoNegociacaoDTO
        São todas as operações de compra e todas as operaçoes de venda por instituição

    ## Procedimentos
        Depois de executar a rota extato e negociação, tem que executar a rota 
        de split para verificar se há split de ações e corrigir a quantidade de ações na base