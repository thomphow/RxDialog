/*
       GNU LESSER GENERAL PUBLIC LICENSE
                       Version 3, 29 June 2007

 Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 Everyone is permitted to copy and distribute verbatim copies
 of this license document, but changing it is not allowed.


  This version of the GNU Lesser General Public License incorporates
the terms and conditions of version 3 of the GNU General Public
License, supplemented by the additional permissions listed below.

  0. Additional Definitions.

  As used herein, "this License" refers to version 3 of the GNU Lesser
General Public License, and the "GNU GPL" refers to version 3 of the GNU
General Public License.

  "The Library" refers to a covered work governed by this License,
other than an Application or a Combined Work as defined below.

  An "Application" is any work that makes use of an interface provided
by the Library, but which is not otherwise based on the Library.
Defining a subclass of a class defined by the Library is deemed a mode
of using an interface provided by the Library.

  A "Combined Work" is a work produced by combining or linking an
Application with the Library.  The particular version of the Library
with which the Combined Work was made is also called the "Linked
Version".

  The "Minimal Corresponding Source" for a Combined Work means the
Corresponding Source for the Combined Work, excluding any source code
for portions of the Combined Work that, considered in isolation, are
based on the Application, and not on the Linked Version.

  The "Corresponding Application Code" for a Combined Work means the
object code and/or source code for the Application, including any data
and utility programs needed for reproducing the Combined Work from the
Application, but excluding the System Libraries of the Combined Work.

  1. Exception to Section 3 of the GNU GPL.

  You may convey a covered work under sections 3 and 4 of this License
without being bound by section 3 of the GNU GPL.

  2. Conveying Modified Versions.

  If you modify a copy of the Library, and, in your modifications, a
facility refers to a function or data to be supplied by an Application
that uses the facility (other than as an argument passed when the
facility is invoked), then you may convey a copy of the modified
version:

   a) under this License, provided that you make a good faith effort to
   ensure that, in the event an Application does not supply the
   function or data, the facility still operates, and performs
   whatever part of its purpose remains meaningful, or

   b) under the GNU GPL, with none of the additional permissions of
   this License applicable to that copy.

  3. Object Code Incorporating Material from Library Header Files.

  The object code form of an Application may incorporate material from
a header file that is part of the Library.  You may convey such object
code under terms of your choice, provided that, if the incorporated
material is not limited to numerical parameters, data structure
layouts and accessors, or small macros, inline functions and templates
(ten or fewer lines in length), you do both of the following:

   a) Give prominent notice with each copy of the object code that the
   Library is used in it and that the Library and its use are
   covered by this License.

   b) Accompany the object code with a copy of the GNU GPL and this license
   document.

  4. Combined Works.

  You may convey a Combined Work under terms of your choice that,
taken together, effectively do not restrict modification of the
portions of the Library contained in the Combined Work and reverse
engineering for debugging such modifications, if you also do each of
the following:

   a) Give prominent notice with each copy of the Combined Work that
   the Library is used in it and that the Library and its use are
   covered by this License.

   b) Accompany the Combined Work with a copy of the GNU GPL and this license
   document.

   c) For a Combined Work that displays copyright notices during
   execution, include the copyright notice for the Library among
   these notices, as well as a reference directing the user to the
   copies of the GNU GPL and this license document.

   d) Do one of the following:

       0) Convey the Minimal Corresponding Source under the terms of this
       License, and the Corresponding Application Code in a form
       suitable for, and under terms that permit, the user to
       recombine or relink the Application with a modified version of
       the Linked Version to produce a modified Combined Work, in the
       manner specified by section 6 of the GNU GPL for conveying
       Corresponding Source.

       1) Use a suitable shared library mechanism for linking with the
       Library.  A suitable mechanism is one that (a) uses at run time
       a copy of the Library already present on the user's computer
       system, and (b) will operate properly with a modified version
       of the Library that is interface-compatible with the Linked
       Version.

   e) Provide Installation Information, but only if you would otherwise
   be required to provide such information under section 6 of the
   GNU GPL, and only to the extent that such information is
   necessary to install and execute a modified version of the
   Combined Work produced by recombining or relinking the
   Application with a modified version of the Linked Version. (If
   you use option 4d0, the Installation Information must accompany
   the Minimal Corresponding Source and Corresponding Application
   Code. If you use option 4d1, you must provide the Installation
   Information in the manner specified by section 6 of the GNU GPL
   for conveying Corresponding Source.)

  5. Combined Libraries.

  You may place library facilities that are a work based on the
Library side by side in a single library together with other library
facilities that are not Applications and are not covered by this
License, and convey such a combined library under terms of your
choice, if you do both of the following:

   a) Accompany the combined library with a copy of the same work based
   on the Library, uncombined with any other library facilities,
   conveyed under the terms of this License.

   b) Give prominent notice with the combined library that part of it
   is a work based on the Library, and explaining where to find the
   accompanying uncombined form of the same work.

  6. Revised Versions of the GNU Lesser General Public License.

  The Free Software Foundation may publish revised and/or new versions
of the GNU Lesser General Public License from time to time. Such new
versions will be similar in spirit to the present version, but may
differ in detail to address new problems or concerns.

  Each version is given a distinguishing version number. If the
Library as you received it specifies that a certain numbered version
of the GNU Lesser General Public License "or any later version"
applies to it, you have the option of following the terms and
conditions either of that published version or of any later version
published by the Free Software Foundation. If the Library as you
received it does not specify a version number of the GNU Lesser
General Public License, you may choose any version of the GNU Lesser
General Public License ever published by the Free Software Foundation.

  If the Library as you received it specifies that a proxy can decide
whether future versions of the GNU Lesser General Public License shall
apply, that proxy's public statement of acceptance of any version is
permanent authorization for you to choose that version for the
Library.
 */

package ca.howardthompson.rxdialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import java.util.Stack;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
/*
* This class is an extension of AlertDialog.Builder where methods are called
* to build a dialog. Instead of create being called to create to dialog,
* createObservable is called and returned.
*
* Additional functionality is provided to enable and disable buttons as well as
* change their text both at configuration and at run time using static methods.
 */
public class RxDialogBuilder extends AlertDialog.Builder
{

    /*
    * Stack of dialogs to ensure they are dismissed.
     */
    private static Stack<AlertDialog> dialogStack = new Stack<>();

    public static void clearDialogStack() {
        if (dialogStack != null)
            dialogStack.clear();
    }


    public RxDialogBuilder(Context context, Boolean completeOnDismiss) {
        super(context);
        setSendComplete(completeOnDismiss);
    }


    private String positiveButton = "";

    Boolean getSendComplete() {
        return sendComplete;
    }

    private void setSendComplete(Boolean sendComplete) {
        this.sendComplete = sendComplete;
    }

    private Boolean sendComplete = false;


    @NonNull
    public RxDialogBuilder positiveButton(@Nullable String positiveButton, boolean enabled)
    {
        this.positiveButton = positiveButton;
        this.positiveEnabled = enabled;
        return this;
    }

    @Nullable
    String getPositiveButton()
    {
        return positiveButton;
    }


    private String negativeButton = "";

    @NonNull
    public RxDialogBuilder negativeButton(@Nullable String negativeButton, boolean enabled)
    {
        this.negativeButton = negativeButton;
        this.negativeEnabled = enabled;
        return this;
    }

    @Nullable
    String getNegativeButton()
    {
        return negativeButton;
    }


    private String neutralButton = "";

    @NonNull
    public RxDialogBuilder neutralButton(@Nullable String neutralButton, boolean enabled)
    {
        this.neutralButton = neutralButton;
        this.neutralEnabled = enabled;
        return this;
    }

    @Nullable
    String getNeutralButton()
    {
        return neutralButton;
    }



    private boolean positiveEnabled;

    private boolean negativeEnabled;

    private boolean neutralEnabled;

    private boolean isPositiveEnabled() {
        return positiveEnabled;
    }

    private boolean isNegativeEnabled() {
        return negativeEnabled;
    }

    private boolean isNeutralEnabled() {
        return neutralEnabled;
    }

    //Possibly of value in the future.
    public int getPaddingA() {
        return paddingA;
    }

    public int getPaddingB() {
        return paddingB;
    }

    public int getPaddingC() {
        return paddingC;
    }

    public int getPaddingD() {
        return paddingD;
    }

    private int paddingA;
    private int paddingB;
    private int paddingC;
    private int paddingD;
    private boolean paddingSet = false;

    protected void setPadding(int a, int b, int c, int d) {
        paddingA = a;
        paddingB = b;
        paddingC = c;
        paddingD = d;
        paddingSet = true;
    }


    Boolean getCanceledOnTouchOutside() {
        return CanceledOnTouchOutside;
    }

    // The customer can disable the default of cancellation if the user
    // touches outside the dialog.
    public RxDialogBuilder setCanceledOnTouchOutside(Boolean canceledOnTouchOutside) {
        CanceledOnTouchOutside = canceledOnTouchOutside;
        return this;
    }

    private Boolean CanceledOnTouchOutside = false;

    void updateButtons(AlertDialog dialog) {

        Button tmpB = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        if (tmpB != null)
                tmpB.setEnabled(isPositiveEnabled());

        tmpB = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        if (tmpB != null)
                tmpB.setEnabled(isNegativeEnabled());

        tmpB = dialog.getButton(AlertDialog.BUTTON_NEUTRAL);
        if (tmpB != null)
                tmpB.setEnabled(isNeutralEnabled());
    }


    public static void updateButtonText(AlertDialog dialog, int which, String text) {
        // neutralEnabled = enabled;
        if (dialog != null) {
            Button tmpB = dialog.getButton(which);
            if (tmpB != null) {
                tmpB.setText(text);
            }
        } else
            Log.d(Constants.TAG,"Dialog is null when trying to update button text");
    }


    public static void updateButton(AlertDialog dialog, int which, boolean enabled) {
        // neutralEnabled = enabled;
        if (dialog != null) {
            Button tmpB = dialog.getButton(which);
            if (tmpB != null)
                tmpB.setEnabled(enabled);
        } else
            Log.d(Constants.TAG,"Dialog is null when trying to update button");
    }


    // Was create, change due to conflict with AlertDialog.Builder.create
    @NonNull
    public Observable<DialogEvent> createObservable()
    {
        return Observable.create(new DialogBuilderOnSubscribe(this))
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(new io.reactivex.functions.Action(){
                    public void run() {
                        if (!dialogStack.empty()) {
                            AlertDialog tmpD = dialogStack.pop();
                            tmpD.dismiss();
                            Log.d(Constants.TAG, "Pop called on dialog stack");

                        } else
                            Log.d(Constants.TAG, "Pop called on EMPTY dialog stack");
                    }

                })
                .doOnError(new Consumer<Throwable>() {
                    public void accept(Throwable t) {
                        Log.d(Constants.TAG, "Error on Dialog Builder [" + t.toString() + "]");
                    }
                })
                .doOnNext(new io.reactivex.functions.Consumer<DialogEvent>()
                {
                    public void accept(DialogEvent dialogEvent) throws Exception {

                        if (dialogEvent instanceof DialogDialogEvent)
                        {
                            DialogDialogEvent dde = (DialogDialogEvent) dialogEvent;
                            AlertDialog tmpD = dde.getDialog();

                            tmpD.getWindow().clearFlags(
                                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                            | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                            tmpD.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

                            dialogStack.push(tmpD);
                            Log.d(Constants.TAG, "Push called on dialog stack");

                            tmpD.show();
                            updateButtons(tmpD);
                        }

                    }
                });
    }
}

