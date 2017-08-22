package ca.howardthompson.rxdialog;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;


public class DialogDialogEvent implements DialogEvent
{
    @NonNull
    private final AlertDialog dialog;

    public DialogDialogEvent(@NonNull AlertDialog dialog)
    {
        this.dialog = dialog;
    }

    @NonNull
    public AlertDialog getDialog()
    {
        return dialog;
    }
}
