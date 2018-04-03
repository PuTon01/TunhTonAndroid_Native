package com.example.teerasaksathu.tungton.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
<<<<<<< HEAD
=======
import android.widget.TextView;
>>>>>>> b7897c6488d258755c694ddccce21fd42ffe3fb6

import com.example.teerasaksathu.tungton.R;

/**
 * Created by Naetirat on 4/3/2018.
 */

public class SaleQuantityAleartDialogFragment extends DialogFragment implements View.OnClickListener {
    Button btnPlusSaleQuantity;
    Button btnMinusSaleQuantity;
    Button btnCloseDialog;
    TextView tvSaleQuantity;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_popup, null);
        Button cancel = view.findViewById(R.id.button8);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        Button addProduct = view.findViewById(R.id.button9);
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add
            }
        });


        builder.setView(view);


<<<<<<< HEAD
=======
        View view = inflater.inflate(R.layout.dialog_fragment_sale_quantity, null);
        initInstances(view);


        builder.setView(view);
>>>>>>> b7897c6488d258755c694ddccce21fd42ffe3fb6
//        // Create the AlertDialog object and return it
        return builder.create();
    }

    private void initInstances(View view) {
        btnPlusSaleQuantity = view.findViewById(R.id.btnPlusSaleQuantity);
        btnMinusSaleQuantity = view.findViewById(R.id.btnMinusSaleQuantity);
        btnCloseDialog = view.findViewById(R.id.btnCloseDialog);
        tvSaleQuantity = view.findViewById(R.id.tvSaleQuantity);

        btnPlusSaleQuantity.setOnClickListener(this);
        btnMinusSaleQuantity.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int saleQuantity = Integer.parseInt(String.valueOf(tvSaleQuantity.getText()));
        if (view == btnPlusSaleQuantity) {
            saleQuantity++;
            tvSaleQuantity.setText(String.valueOf(saleQuantity));
        } else if (view == btnMinusSaleQuantity) {
            saleQuantity--;
            tvSaleQuantity.setText(String.valueOf(saleQuantity));
        } else if (view == btnCloseDialog) {
            getDialog().dismiss();
        }
    }
}