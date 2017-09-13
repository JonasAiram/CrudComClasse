package com.example.jonas.crudcomclasse;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by jonas on 29/08/2017.
 */

public class RetrieveOnLongClickListener implements View.OnLongClickListener{

    Context context;
    String id;

    @Override
    public boolean onLongClick(View view) {

        context = view.getContext();
        id = view.getTag().toString();

        final CharSequence[] itens = {"edita","deletar"};

        new AlertDialog.Builder(context).setTitle("Detalhes do contato")
                .setItems(itens, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {

                        if (item == 0){
                            //EDITAR
                            editContatoPeloID(Integer.parseInt(id));

                        }else if (item == 1) {
                            //DELETAR

                        }

                    }
                }).show();

        Toast.makeText(view.getContext(), "Cliacado em um item da lista", Toast.LENGTH_SHORT).show();
        return false;
    }

    public void editContatoPeloID(final int contatoID){

        Toast.makeText(context, "Editando "+contatoID,Toast.LENGTH_SHORT).show();
        //CONTATO CONTROLLER
        final ContatoController contatoController = new ContatoController(context);
        //CONTATO VIA DB PELA PK
        final Contato contato = contatoController.buscarPeloID(contatoID);
        //INJETAR O LAYOUT CONTATO_FOMR
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View formContato = inflater.inflate(R.layout.contato_form,null,false);

        //POPULAR NOME E EMAIL COM DADOS DA LISTA
        final EditText editTextNome = (EditText) formContato.findViewById(R.id.editTextContatoNome);
        final EditText editTextemail = (EditText) formContato.findViewById(R.id.editTextEmail);

        editTextNome.setText(contato.getNome());
        editTextemail.setText(contato.getEmail());

        // SHOW DO FORMULARIO COM DADOS PAPULADOS

        new AlertDialog.Builder(context)
                .setView(formContato)
                .setTitle("Editar")
                .setPositiveButton("Atualizar Dados",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int id) {
                                //REGRAS DE NEGOCIOS SALVAR/UPDATE CRUD

                                Contato novoContato = new Contato();
                                novoContato.setId(contatoID);
                                novoContato.setNome(editTextNome.getText().toString());
                                novoContato.setEmail(editTextemail.getText().toString());

                                boolean isUpdate = contatoController.updade(novoContato);

                                if (isUpdate){
                                    Toast.makeText(context, "Dados atualizados com sucesso...", Toast.LENGTH_SHORT).show();

                                    ((MainActivity) context).atualizarListaDeContatos();

                                }else{
                                    Toast.makeText(context, "Falha ao Salvar Contato", Toast.LENGTH_SHORT).show();
                                }

                                dialogInterface.cancel();

                            }

                        }).show();

    }

}
