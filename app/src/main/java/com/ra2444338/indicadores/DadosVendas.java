package com.ra2444338.indicadores;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class DadosVendas extends AppCompatActivity {

    private EditText ediValorLiquido, ediQtdLiq, ediTickets, ediAbordados, TMIdeal;
    private Button btnsalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_vendas);

        ediValorLiquido = findViewById(R.id.EdiValorLiquido);
        ediQtdLiq = findViewById(R.id.EdiQtdLiq);
        ediTickets = findViewById(R.id.EdiTickets);
        ediAbordados = findViewById(R.id.EdiAbordados);
        TMIdeal = findViewById(R.id.TMIDEAL);
        btnsalvar = findViewById(R.id.btnsalvar);

        btnsalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double valorLiquido = Double.parseDouble(ediValorLiquido.getText().toString());
                int qtdLiq = Integer.parseInt(ediQtdLiq.getText().toString());
                int totalTickets = Integer.parseInt(ediTickets.getText().toString());
                int totalAbordados = Integer.parseInt(ediAbordados.getText().toString());
                double tmIdealValue = Double.parseDouble(TMIdeal.getText().toString());

                double valorpa = (double) qtdLiq / totalTickets;
                double valorpm = valorLiquido / qtdLiq;
                double valortm = valorLiquido / totalTickets;
                double valortaxa = (double) totalTickets / totalAbordados * 100;

                double possibilidadeDeVendas = tmIdealValue * totalTickets;

                double diferenca = valorLiquido - possibilidadeDeVendas;
                String resultadoVendas;
                if (diferenca > 0) {
                    resultadoVendas = "Vendeu a mais 😊";
                } else {
                    resultadoVendas = "Vendeu a menos 😞";
                }

                Intent intent = new Intent();
                intent.putExtra("PA", valorpa);
                intent.putExtra("PM", valorpm);
                intent.putExtra("TM", valortm);
                intent.putExtra("valorLiquido", valorLiquido);
                intent.putExtra("Taxa", valortaxa);
                intent.putExtra("possibilidadeDeVendas", possibilidadeDeVendas);
                intent.putExtra("diferenca", diferenca);
                intent.putExtra("resultadoVendas", resultadoVendas);
                setResult(RESULT_OK, intent);
                finish();

                Toast.makeText(DadosVendas.this, "Dados salvos!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
