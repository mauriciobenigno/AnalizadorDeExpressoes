package br.com.mauriciobenigno.ae.analisadordeexpresses;

/**
 * Created by MauricioBenigno on 14/03/2018.
 */

import static java.lang.Character.isDigit;

public class Scanner {
    String entrada;
    int posicaoChar;

    public Scanner(String entrada) {
        this.entrada = entrada;
        this.posicaoChar = 0;
    }

    public Token proximo_Token() {
        Token novoToken;
        String valorNumero = "";
        if (posicaoChar == entrada.length()) { //SE O VALOR DA EXPRESSÃO FOR IGUAL A 0, ELE FINALIZA A EXPRESSÃO
            novoToken = new Token(tipoToken.EOF);
            return novoToken;
        }
        while (entrada.charAt(posicaoChar) == ' ' || entrada.charAt(posicaoChar) == '\t' || entrada.charAt(posicaoChar) == '\n' )
            posicaoChar++;//ENQUANTO TIVER TABULAÇÃO, ESPAÇO OU FIM DE LINHA, ELE AVANÇA O PONTO

        if (entrada.charAt(posicaoChar) == '-')
        {
            posicaoChar++;
            if(!isDigit(entrada.charAt(posicaoChar)))
            {
                novoToken = new Token(tipoToken.SUB);
            }
            else
            {
                valorNumero = "-" + entrada.charAt(posicaoChar);
                posicaoChar++;
                while ((posicaoChar<entrada.length() && isDigit(entrada.charAt(posicaoChar))))//||entrada.charAt(posicaoChar)=='.')
                {
                    valorNumero += entrada.charAt(posicaoChar);
                    posicaoChar++;
                }
                try
                {
                    if(entrada.charAt(posicaoChar)=='.')
                    {
                        valorNumero += entrada.charAt(posicaoChar);
                        posicaoChar++;
                    }
                    while ((posicaoChar < entrada.length() && isDigit(entrada.charAt(posicaoChar))))
                    {
                        valorNumero += entrada.charAt(posicaoChar);
                        posicaoChar++;
                    }
                    novoToken = new Token(tipoToken.NUM, Float.parseFloat(valorNumero)); //Converte a String em Float
                }
                catch (NumberFormatException erro)
                {
                    System.err.println("Número inválido!");
                    novoToken = new Token(tipoToken.ERRO);
                    //System.exit(1);
                }
            }
        }
        else if (entrada.charAt(posicaoChar) == '+')
        {
            posicaoChar++;
            novoToken = new Token(tipoToken.SOMA);
        }
        else if (entrada.charAt(posicaoChar) == '*')
        {
            posicaoChar++;
            novoToken = new Token(tipoToken.MUL);
        }
        else if (entrada.charAt(posicaoChar) == '/')
        {
            posicaoChar++;
            novoToken = new Token(tipoToken.DIV);
        }
        else if (entrada.charAt(posicaoChar) == '(')
        {
            posicaoChar++;
            novoToken = new Token(tipoToken.PARENTESES_ABERTO);
        }
        else if (entrada.charAt(posicaoChar) == ')')
        {
            posicaoChar++;
            novoToken = new Token(tipoToken.PARENTESES_FECHADO);
        }
        else if (isDigit(entrada.charAt(posicaoChar)))
        {
            valorNumero += entrada.charAt(posicaoChar);
            posicaoChar++;
            while ((posicaoChar < entrada.length()))
            {
                if(!isDigit(entrada.charAt(posicaoChar)))
                    break;

                valorNumero += entrada.charAt(posicaoChar);
                posicaoChar++;
            }
            try
            {
                if(entrada.charAt(posicaoChar)=='.')
                {
                    valorNumero += entrada.charAt(posicaoChar);
                    posicaoChar++;
                }
                while (posicaoChar < entrada.length() && isDigit(entrada.charAt(posicaoChar)))
                {
                   /* if(!isDigit(entrada.charAt(posicaoChar)))
                        break;*/
                    valorNumero += entrada.charAt(posicaoChar);
                    posicaoChar++;
                }
                novoToken = new Token(tipoToken.NUM, Float.parseFloat(valorNumero)); //Converte a String em Float
            }
            catch (NumberFormatException erro)
            {
                System.err.println("Número inválido!");
                novoToken = new Token(tipoToken.ERRO);
                //System.exit(1);
            }
        }
        //Default - ERRO LÉXICO
        else
        {
            novoToken = new Token(tipoToken.ERRO);
            posicaoChar++;
            //System.exit(1);
        }
        return novoToken;
    }
}
