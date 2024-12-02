package com.ra2444338.indicadores;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Homepage extends AppCompatActivity {

    private Button btnAdicionarVendas;
    private TextView GetVendido, PA, PM, TM, Taxa, PossibilidadeVendas, Diferenca, ResultadoVendas;
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        btnAdicionarVendas = findViewById(R.id.btnAdicionarVendas);
        GetVendido = findViewById(R.id.GetVendido);
        PA = findViewById(R.id.PA);
        PM = findViewById(R.id.PM);
        TM = findViewById(R.id.TM);
        Taxa = findViewById(R.id.taxadeconver);
        PossibilidadeVendas = findViewById(R.id.PossibilidadeVendas);
        Diferenca = findViewById(R.id.Diferenca);
        ResultadoVendas = findViewById(R.id.ResultadoVendas);

        btnAdicionarVendas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, DadosVendas.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            double valorpa = data.getDoubleExtra("PA", 0.0);
            double valorpm = data.getDoubleExtra("PM", 0.0);
            double valortm = data.getDoubleExtra("TM", 0.0);
            double valorvendido = data.getDoubleExtra("valorLiquido", 0.0);
            double valortaxa = data.getDoubleExtra("Taxa", 0.0);
            double possibilidadeDeVendas = data.getDoubleExtra("possibilidadeDeVendas", 0.0);
            double diferenca = data.getDoubleExtra("diferenca", 0.0);
            String resultadoVendas = data.getStringExtra("resultadoVendas");

            GetVendido.setText(String.format("R$ %.2f", valorvendido));
            PA.setText(String.format("%.2f", valorpa));
            PM.setText(String.format("R$ %.2f", valorpm));
            TM.setText(String.format("R$ %.2f", valortm));
            Taxa.setText(String.format("%.2f%%", valortaxa));
            PossibilidadeVendas.setText(String.format("R$ %.2f", possibilidadeDeVendas));
            Diferenca.setText(String.format("R$ %.2f", diferenca));
            ResultadoVendas.setText(resultadoVendas);
        }
    }
}
