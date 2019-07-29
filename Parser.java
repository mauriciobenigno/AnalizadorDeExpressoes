package br.com.mauriciobenigno.ae.analisadordeexpresses;


/**
 * Created by MauricioBenigno on 14/03/2018.
 */

public class Parser {

    Token proxToken;
    String saida;
    Scanner scn;
    int contador;

    public Parser(String exp) {
        scn = new Scanner(exp);
        saida = new String();
        contador=0;
    }

    boolean verificar (tipoToken tipo) {
        proxToken = scn.proximo_Token();
        if (proxToken.tipo == tipo)
            return true;
        else {
            saida="ERRO SINTÁTICO: " + tipo + " ESPERADO, MAS " + proxToken.tipo + " ENCONTRADO!";
            return false;
        }
    }

    void verificaParenteses () {
        proxToken = scn.proximo_Token();
        if (proxToken.tipo == tipoToken.PARENTESES_ABERTO){
            contador+=1;
        }
        else if(proxToken.tipo == tipoToken.PARENTESES_FECHADO){
            contador-=1;
        }
    }

    void Expressao () {
        verificaParenteses();
        verificar(tipoToken.NUM);
        Resto();
        verificaParenteses();
        if (verificar(tipoToken.EOF)) {
            if(contador==0)
            {
                saida="ERRO: PARENTESES USADOS DE FORMA INCORETA!";
            }
            else
                saida="EXPRESSÃO CORRETA!";
        }
        else {
            saida="ERRO: SIMBOLOS ENCONTRADOS APÓS FIM DA EXPRESSAO!";
        }
    }

    void Resto() {
        Token prox = scn.proximo_Token();
        if (prox.tipo == tipoToken.SOMA) {
            verificaParenteses();
            verificar(tipoToken.NUM);
            Resto();
            verificaParenteses();
        }
        else if (prox.tipo == tipoToken.SUB) {
            verificaParenteses();
            verificar(tipoToken.NUM);
            Resto();
            verificaParenteses();
        }
        else if (prox.tipo == tipoToken.MUL) {
            verificaParenteses();
            verificar(tipoToken.NUM);
            Resto();
            verificaParenteses();
        }
        else if (prox.tipo == tipoToken.DIV) {
            verificaParenteses();
            verificar(tipoToken.NUM);
            {
                if (proxToken.valor == 0) {
                    saida="ERRO: DIVISAO POR ZERO!";
                    return;
                }
            }
            Resto();
            verificaParenteses();
        }
        else ; //EPSILON!
    }

}

