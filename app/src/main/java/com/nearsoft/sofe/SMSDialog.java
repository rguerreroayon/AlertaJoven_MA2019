package com.nearsoft.sofe;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class SMSDialog extends AppCompatDialogFragment {
    private EditText editSms;
    private SMSDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (SMSDialogListener)  context;
        }catch(ClassCastException e){
            throw new  ClassCastException(context.toString());
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.sms_dialog,null);
        builder.setView(view)
                .setTitle("SMS")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sms = editSms.getText().toString();
                        listener.applySMS(sms);

                    }
                });

        editSms = view.findViewById(R.id.numerosms);
        return builder.create();
    }

    public interface SMSDialogListener{
        void applySMS(String sms);
    }

}
