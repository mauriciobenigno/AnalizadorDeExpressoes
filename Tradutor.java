package br.com.mauriciobenigno.ae.analisadordeexpresses;

import java.util.Stack;

/**
 * Created by MauricioBenigno on 18/03/2018.
 */

public class Tradutor {

    Token proxToken;
    Scanner scn;
    Stack<Float> pilha;
    String saida;

    public Tradutor(String exp)
    {
        pilha = new Stack<>();
        scn = new Scanner(exp);
        proxToken = scn.proximo_Token();
        saida = new String();
    }

    boolean verificar(tipoToken tipo)
    {
        proxToken = scn.proximo_Token();
        if(proxToken.tipo==tipo)
            return true;
        else
        {
            System.out.println("ERRO SINTÁTICO: "+tipo+" ESPERADO, MAS"+proxToken.tipo+" ENCONTRADO");
            //System.exit(1);
            return false;
        }
    }

    void Expressao()
    {
        Termo();
        RestoExpressao();
    }

    void RestoExpressao()
    {
        // SOMA
        if(proxToken.tipo == tipoToken.SOMA)
        {
            saida +="Empilhando "+proxToken.tipo+"\n";
            proxToken = scn.proximo_Token();
            Termo();
            float op2 = pilha.pop();
            float op1 = pilha.pop();
            float res = op2+op1;
            saida+="Somando: "+op2+"+"+op1+"="+res+"\n";
            pilha.push(res);
            RestoExpressao();
        }
        // SUBTRAÇÃO
        if(proxToken.tipo == tipoToken.SUB)
        {
            saida +="Empilhando "+proxToken.tipo+"\n";
            proxToken = scn.proximo_Token();
            Termo();
            float op2 = pilha.pop();
            float op1 = pilha.pop();
            float res = op2-op1;
            saida +="Subtraindo: "+op2+"-"+op1+"="+res+"\n";
            pilha.push(res);
            RestoExpressao();
        }
        //EPSILON
        else {}
    }

    void Termo()
    {
        Fator();
        RestoFator();
    }

    void Fator()
    {
        if(proxToken.tipo==tipoToken.NUM)
        {
            saida +="Empilhando: "+proxToken.valor+"\n";
            pilha.push(proxToken.valor);
            proxToken = scn.proximo_Token();
        }
        else if(proxToken.tipo==tipoToken.PARENTESES_ABERTO)
        {
            saida +="Parentese "+proxToken.tipo+"\n";
            proxToken = scn.proximo_Token();
            Expressao();
            saida +="Parentese "+proxToken.tipo+"\n";
            proxToken = scn.proximo_Token();

        }
        else
        {
            saida +="Erro: Não é um FATOR válido\n";
        }
    }

    void RestoFator()
    {
        if(proxToken.tipo==tipoToken.MUL)
        {
            saida +="Empilhando "+proxToken.tipo+"\n";
            proxToken = scn.proximo_Token();
            Fator();
            // MULTIPLICAÇÃO
            float op2 = pilha.pop();
            float op1 = pilha.pop();
            float res = op2*op1;
            saida +="Multiplicando: "+op2+"-"+op1+"="+res+"\n";
            pilha.push(res);

            //0----
            // RestoFator();
            //contador++;
        }
        else if(proxToken.tipo==tipoToken.DIV)
        {
            saida +="Empilhando "+proxToken.tipo+"\n";
            proxToken = scn.proximo_Token();
            Fator();
            // DIVIDINDO
            float op2 = pilha.pop();
            float op1 = pilha.pop();
            float res = op2/op1;
            saida +="Dividindo: "+op2+"-"+op1+"="+res+"\n";
            pilha.push(res);
            //0----
            // RestoFator();
            //contador++;
        }
        else ;
    }
}