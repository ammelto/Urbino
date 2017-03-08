package io.seamoss.urbino.views.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;

import io.seamoss.urbino.R;

/**
 * Created by Alexander Melton on 3/6/2017.
 */

public class CourseCodeDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_course_code, null));
        builder.setPositiveButton(R.string.code_dialog_accept, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.setNegativeButton(R.string.code_dialog_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                CourseCodeDialog.this.getDialog().cancel();
            }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
