package br.com.mauriciobenigno.ae.analisadordeexpresses;

/**
 * Created by MauricioBenigno on 14/03/2018.
 */

enum tipoToken { NUM, SOMA, SUB, MUL, DIV, ERRO, EOF,PARENTESES_ABERTO,PARENTESES_FECHADO };

public class Token {
    tipoToken tipo;
    float valor;

    public Token(tipoToken tipo) {
        this.tipo = tipo;
        this.valor= 0;
    }

    public Token (tipoToken tipo, float valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

}