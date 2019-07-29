package br.com.mauriciobenigno.ae.analisadordeexpresses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Botões
    Button btParser;
    Button btScanner;
    Button btCalcular;
    Button btLimpar;
    Button btProblemas;
    // Campo de texto
    EditText campoExpressao;
    // Texto
    TextView textoSaida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // LINK DOS BOTÕES
        btParser=(Button)findViewById(R.id.btParser);
        btScanner=(Button)findViewById(R.id.btScanner);
        btCalcular=(Button)findViewById(R.id.btCalcular);
        btLimpar=(Button)findViewById(R.id.btLimpar);
        btProblemas=(Button)findViewById(R.id.btProblemas);
        //LINK DO CAMPO DE TEXTO
        campoExpressao=(EditText)findViewById(R.id.campoExpressao);
        //LINK TEXTO SAIDA
        textoSaida=(TextView)findViewById(R.id.textoSaida);


        btParser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*String exp = campoExpressao.getText().toString();
                Parser pars = new Parser(exp);
                pars.Expressao( );
                textoSaida.setText(pars.saida+" - cont "+pars.contador);*/
                Toast.makeText(getApplicationContext(),"Função com problemas!\nA execução não foi iniciada",Toast.LENGTH_SHORT).show();
            }
        });

        btScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String exp = campoExpressao.getText().toString();
                if(exp.charAt(exp.length()-2)!='.') exp+=".0";
                Scanner lex = new Scanner(exp);
                Token proxToken = lex.proximo_Token();
                String saida = new String();
                while (proxToken.tipo != tipoToken.EOF) {
                    saida+="[" + proxToken.tipo + "," + proxToken.valor + "]\n";
                    proxToken = lex.proximo_Token();
                }
                textoSaida.setText(saida);
            }
        });

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String exp = campoExpressao.getText().toString();
                if(exp.charAt(exp.length()-2)!='.') exp+=".0";
                Tradutor trad = new Tradutor(exp);
                trad.Expressao();
                textoSaida.setText(trad.saida+"Resultado: "+trad.pilha.pop());
            }
        });


        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                campoExpressao.setText("");
                textoSaida.setText("Resultado:");
            }
        });

        btProblemas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String saida = new String();
                saida+="Log PROBLEMAS:\n";
                saida+="1 - Botão Parser: A função Parser não está funcionando,a logica\n";
                saida+="usada era a da verificação de abertura e fechamentos de parenteses\n";
                saida+="através de um contador. Cada verificação de parenteses era feita\n";
                saida+="antes de numeros e após sinais.\n";
                saida+="2 - Botão Scanner: A função Scanner está funcionando parcialmente.\n";
                saida+="O unico problema identificado até o momento se trata de colocar \n";
                saida+="parenteses no final da expressão. Ainda não consegui descobrir o\n";
                saida+="motivo, porém o App fecha ao identificar o parenteses\n";
                textoSaida.setText(saida);
            }
        });

    }
}
