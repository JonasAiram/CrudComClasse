package com.example.jonas.crudcomclasse;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by jonas on 29/08/2017.
 */

public class CreateContatoOnClickListener implements View.OnClickListener{

    @Override
    public void onClick(View v) {

        final Context context = v.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.contato_form, null, false);

        final EditText editTextContatoNome = (EditText) formElementsView.findViewById(R.id.editTextContatoNome);
        final EditText editTextEmail = (EditText) formElementsView.findViewById(R.id.editTextEmail);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Criar Contato")
                .setPositiveButton("Incluir",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int id) {
                                // REGRAS DE NEGOCIOS PARA INCLUIR OS NOVOS CONTATOS

                                String contatoNome = editTextContatoNome.getText().toString();
                                String contatoEmail = editTextEmail.getText().toString();

                                Contato contato = new Contato();
                                contato.setNome(contatoNome);
                                contato.setEmail(contatoEmail);

                                boolean criadoComSucesso = new ContatoController(context).create(contato);

                                if (criadoComSucesso) {
                                    Toast.makeText(context, "Contato incluído com sucesso.", Toast.LENGTH_SHORT).show();

                                    ((MainActivity) context).contadorDeRegistros();
                                    ((MainActivity) context).atualizarListaDeContatos();


                                }else
                                    Toast.makeText(context, "Não foi possivel incluir o contato.", Toast.LENGTH_SHORT).show();

                                dialogInterface.cancel();
                            }
                        }).show();

    }
}
