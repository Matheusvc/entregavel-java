package com.matheus.pagamentos;

/**
 * Situacao possivel de um pagamento.
 * Usar enum deixa o status seguro (so aceita valores validos) e mais legivel.
 */
public enum StatusPagamento {
    PENDENTE,
    APROVADO,
    RECUSADO
}
