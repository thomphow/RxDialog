package ca.howardthompson.rxdialog;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

/**
 * Dialog Dialog event is an event which contains a reference to the AlertDialog itself,
 * useful for further modifications. The Dialog Dialog event class is derived from base
 * event class used as generic parent for passing different kinds of events.
 * (Note:Why "Dialog Dialog Event".... the base class is just DialogEvent.... Dialog within a
 * Dialog...)
 */
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
