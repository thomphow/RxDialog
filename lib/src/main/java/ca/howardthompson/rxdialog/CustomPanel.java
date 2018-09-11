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
import android.graphics.Color;
import android.support.annotation.DrawableRes;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import io.reactivex.Observable;

/**
 * This is a builder class used to build a Custom View
 * to be added to the Dialog.
 */

public class CustomPanel {


    public static final int NO_FIELD_LIMIT = 0;
    private View lastView = null;
    private ScrollView vScroll = null;
    private RelativeLayout vLayout = null;
    private Context panelContext = null;
    private int paddingLeft = 0;
    private int paddingTop = 0;
    private int paddingBottom = 0;
    private int paddingRight = 0;

    /**
     * Constructor for Panel.
     * Note: Need to fix exception
     * @param con Context for creation of the panels views.
     */

    public CustomPanel(Context con) {
        vScroll = new ScrollView(con);
        lastView = null;
        vLayout = new RelativeLayout(con);
        vScroll.addView(vLayout);
        panelContext = con;
    }

    /**
     *
     * @return Returns the outer most ScrollView of the panel.
     */

    public final View getView() {
        return vScroll;
    }

    /**
     * Sets Padding values for outter most Scroll View for the panel.
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @return Returns the builder panel
     */
    public CustomPanel setPanelPadding(int left, int top, int right, int bottom) {
        vScroll.setPadding(left, top,right,bottom);
        return this;
    }

    /**
     * Sets the padding for the next view(s) being added to the panel
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @return the builder panel
     */
    public CustomPanel setViewPadding(int left, int top, int right, int bottom) {
        paddingLeft = left;
        paddingTop = top;
        paddingBottom = bottom;
        paddingRight = right;
        return this;
    }

    /**
     * Aligns the passed view to be left of the previously added view
     * @param view the view to be aligned
     */
    private void adjustView(View view) {
        if (lastView != null) {
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            if (Constants.debug)
                Log.d(Constants.TAG,"New View id [" + view.getId() + "] LastView id [" + lastView.getId() + "]");
            lp.addRule(RelativeLayout.BELOW, lastView.getId());
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            view.setLayoutParams(lp);
        }
        lastView = view;
    }

    /**
     * Sets a drawable as the content of the panel
     * @param resId drawable icon resource
     * @return the builder panel
     */

    public CustomPanel addIcon(@DrawableRes int resId) {
        ImageView iv = new ImageView(panelContext);
        iv.setImageResource(resId);
        iv.setId(View.generateViewId());
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.gravity=Gravity.CENTER_HORIZONTAL;
        iv.setLayoutParams(layoutParams);
        adjustView(iv);
        iv.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        vLayout.addView(iv);
        return this;
    }

    /**
     * Adds the passed view to the panel
     * @param msgView view to be added
     * @return the builder panel
     */
    public CustomPanel addMessageView(View msgView) {
        msgView.setId(View.generateViewId());
        adjustView(msgView);
        msgView.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        vLayout.addView(msgView);
        return this;
    }


    /**
     * Adds an EditText to the panel
     * @param label text label
     * @param inputType see TextView class
     * @param length length of field
     * @return the builder panel
     */
    public Observable<CharSequence> addEditText(String label, int inputType, int length) {
        TextView tv;
        EditText et;
        tv = new TextView(panelContext);
        tv.setText(label);
        tv.setTextSize(12);
        tv.setId(View.generateViewId());
        adjustView(tv);
        tv.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        vLayout.addView(tv);
        et = new EditText(panelContext);
        et.setInputType(inputType);
        et.setTextSize(12);
        et.setGravity(Gravity.LEFT);
        et.setId(View.generateViewId());
        adjustView(et);
        if (length != NO_FIELD_LIMIT)
            et.setMinEms(length);
        else
            et.setMinEms(20);
        if (length == NO_FIELD_LIMIT)
            et.setMaxEms(250);
        vLayout.addView(et);

        return RxTextView.textChanges(et);
    }

    /**
     * Adds an TextView to the panel
     * @param txtParm text for the field
     * @param id identifier
     * @param color text color
     * @return the builder panel
     */
    private CustomPanel addText(String txtParm, int id, int color) {

        TextView tv;
        tv = new TextView(panelContext);
        tv.setText(txtParm);
        tv.setTextSize(12);
        tv.setId(id);
        tv.setTextColor(color);
        adjustView(tv);
        tv.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        vLayout.addView(tv);
        return this;
    }

    /**
     * Adds an TextView to the panel with generated view id.
     * @param txtParm
     * @param color
     * @return the builder panel
     */
    public CustomPanel addText(String txtParm,  int color) {

        return addText(txtParm, View.generateViewId(), color);
    }

    /**
     * Adds an TextView to the panel with generated view id and default
     * text color of black
     * @param txtParm text for the field
     * @return the builder panel
     */
    public CustomPanel addText(String txtParm) {
        return addText(txtParm, Color.BLACK);
    }


}
