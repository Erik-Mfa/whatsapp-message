package com.example.whatsapp_message;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity {

    private TextView celular;
    private TextView mensagem;
    private Button enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        celular = findViewById(R.id.celular);
        mensagem = findViewById(R.id.mensagem);
        enviar = findViewById(R.id.enviar);

        enviar.setOnClickListener(view -> {
            if(celular.getText().length() == 0 || mensagem.getText().length() == 0) {
                this.popUp("Campos em branco", "Alguns campos estão em branco, por favor preenchê-los!");
            } else {
                this.sendWhats();
            }
        });
    }

    protected void sendWhats() {
        Uri whatsAppLink = Uri.parse(MessageFormat.format("https://wa.me/55{0}?text={1}", celular.getText(), mensagem.getText()));
        Intent whatsAppIntent = new Intent(Intent.ACTION_VIEW, whatsAppLink);
        startActivity(whatsAppIntent);
    }

    protected void popUp(String titulo, String mensagem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titulo);
        builder.setMessage(mensagem);
        builder.setPositiveButton("Tentar novamente", null);
        AlertDialog alerta = builder.create();
        alerta.show();
    }

}