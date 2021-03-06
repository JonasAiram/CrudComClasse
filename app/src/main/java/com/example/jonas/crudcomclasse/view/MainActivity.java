package com.example.jonas.crudcomclasse.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jonas.crudcomclasse.R;
import com.example.jonas.crudcomclasse.controller.ContatoController;
import com.example.jonas.crudcomclasse.model.Contato;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnCriarContato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCriarContato = (Button) findViewById(R.id.btnCriarContato);
        btnCriarContato.setOnClickListener(new OnClickListener());

        contadorDeRegistros();
        atualizarListaDeContatos();

        new ContatoController(this).buscarPeloID(10);
    }

    public void contadorDeRegistros(){

        String msg = "";

        int contador = new ContatoController(this).totalDeContatos();

        TextView txtContadorContatos = (TextView) findViewById(R.id.txtContadorContatos);

        if (contador == 0){
            msg = "Nenhum contato cadastrado.";

        }else if (contador == 1){
            msg = contador + " contato cadastrado.";

        }else {
            msg = contador + " contatos cadastrados.";

        }

        txtContadorContatos.setText(msg);

    }

    public void atualizarListaDeContatos(){

        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.objetosContatos);
        linearLayoutRecords.removeAllViews();

        List<Contato> students = new ContatoController(this).listarContatos();

        if (students.size() > 0) {

            for (Contato obj : students) {

                int id = obj.getId();
                String nome = obj.getNome();
                String email = obj.getEmail();

                String textViewContents = nome + " - " + email;

                TextView textViewContatoItem = new TextView(this);
                textViewContatoItem.setPadding(0, 10 , 0, 10);
                textViewContatoItem.setText(textViewContents);
                textViewContatoItem.setTag(Integer.toString(id));

                linearLayoutRecords.addView(textViewContatoItem);
                textViewContatoItem.setOnLongClickListener(new OnLongClickListener());

            }

        }

    }
}
