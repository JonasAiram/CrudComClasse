package com.example.jonas.crudcomclasse;

import android.view.View;
import android.widget.Toast;

/**
 * Created by jonas on 29/08/2017.
 */

public class RetrieveOnLongClickListener implements View.OnLongClickListener{

    @Override
    public boolean onLongClick(View view) {

        Toast.makeText(view.getContext(), "Cliacado em um item da lista", Toast.LENGTH_SHORT).show();
        return false;
    }
}
