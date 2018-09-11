package ca.howardthompson.rxdialog;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;

import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.content.DialogInterface.BUTTON_NEUTRAL;
import static android.content.DialogInterface.BUTTON_POSITIVE;
import static ca.howardthompson.rxdialog.CustomPanel.NO_FIELD_LIMIT;


public class MainActivity extends AppCompatActivity {

    private CompositeDisposable compDisp = null;

    MainActivity parent = null;

    private class DialogTextEvent implements DialogEvent {

        String getText() {
            return text;
        }

        private final String text;

        DialogTextEvent(String text) {
            this.text = text;
        }
    }

    private class DialogNumEvent implements DialogEvent {

        String getNum() {
            return text;
        }

        private final String text;

        DialogNumEvent(String text) {
            this.text = text;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parent = this;

        // Pass compile debug setting to library
        Constants.setDebug(BuildConfig.DEBUG);

        setContentView(R.layout.activity_main);

        compDisp = new CompositeDisposable();

        Button generateButton = (Button) findViewById(R.id.generate_dialog);
        generateButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                displayDialog(new RxDialogBuilder(parent, false));
            }
        });

        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (Constants.debug)
                Log.d(Constants.TAG, "MainActivity PlugIn Event [" + throwable.getMessage() + "]");
            }
        });
    }


    void doDispose() {
        if (compDisp != null)
            compDisp.dispose();
        compDisp = null;
    }


    @Override
    protected void onDestroy() {
        doDispose();

        super.onDestroy();
    }


    @Override
    protected void onPause() {
        super.onPause();
        doDispose();

    }


    @Override
    protected void onStop() {
        super.onStop();
        doDispose();

    }


    void displayEvent(String txt) {
            LinearLayout parentll = (LinearLayout) parent.findViewById(R.id.parentll);
            LayoutInflater inflater = LayoutInflater.from(parent);
            final TextView newEventView = (TextView) inflater.inflate(R.layout.event, null);
            newEventView.setText(txt);
            parentll.addView(newEventView);
    }

    private void displayDialog(RxDialogBuilder builder)
    {

        try {

            Observable<DialogEvent> dialogEventObservable;

            CustomPanel cp = new CustomPanel(this)
                    .setPanelPadding(50, 50, 50, 50)
                    .addText("Our Application Dialog")
                    .addIcon(R.drawable.ic_android_black_24dp)
                    .addText("Please complete our form and hit submit")
                    .setViewPadding(0, 20, 0, 0);


                    Observable<DialogEvent> ObText = cp.addEditText("Input Prompt" + " (alphanumeric)", InputType.TYPE_TEXT_VARIATION_NORMAL, NO_FIELD_LIMIT)
                    .doOnError(new Consumer<Throwable>() {
                        public void accept(Throwable t) {
                            if (Constants.debug)
                            Log.d(Constants.TAG, "Error on alphanumeric field [" + t.toString() + "]");
                        }
                    })
                    .map(new Function<CharSequence, DialogEvent>() {
                        @Override
                        public DialogEvent apply(CharSequence chars) {
                            return new DialogTextEvent(chars.toString());
                        }
                    });

                    Observable<DialogEvent> ObNum = cp.addEditText("Input Prompt" + " (numeric)", InputType.TYPE_CLASS_NUMBER, 8)
                    .doOnError(new Consumer<Throwable>() {
                        public void accept(Throwable t) {
                            if (Constants.debug)
                            Log.d(Constants.TAG, "Error on numeric field [" + t.toString() + "]");
                        }
                    })
                    .map(new Function<CharSequence, DialogEvent>() {
                        @Override
                        public DialogEvent apply(CharSequence chars) {
                            return new DialogNumEvent(chars.toString());
                        }
                    });

            // RxDialogBuilder

            Switch posBSwitch = (Switch) parent.findViewById(R.id.posbutton);
            Switch negBSwitch = (Switch) parent.findViewById(R.id.negbutton);
            Switch neutralBSwitch = (Switch) parent.findViewById(R.id.neutralbutton);
            Switch cancellableSwitch = (Switch) parent.findViewById(R.id.cancellable);
            Switch cancellableOutsideSwitch = (Switch) parent.findViewById(R.id.cancel_touch_outside);


            if (posBSwitch.isChecked())
                builder.positiveButton(getString(R.string.pos_button_disp), true);

            if (negBSwitch.isChecked())
                builder.negativeButton(getString(R.string.neg_button_disp), true );

            if (neutralBSwitch.isChecked())
                builder.neutralButton(getString(R.string.neutral_button_disp), true);

            builder.setCanceledOnTouchOutside(cancellableOutsideSwitch.isChecked());
            builder.setView(cp.getView());
            builder.setCancelable(cancellableSwitch.isChecked());
            dialogEventObservable = builder.createObservable();
            dialogEventObservable = Observable.merge(dialogEventObservable, ObText, ObNum)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(
                        new Consumer<DialogEvent>() {
                            public void accept(DialogEvent event) throws Exception {
                                if (Constants.debug)
                                Log.d(Constants.TAG, "Got Event [" + event.toString() + "]");
                                if (event instanceof DialogTextEvent) {
                                    displayEvent("Text Field Entered [" + ((DialogTextEvent)event).getText() + "]");
                                } else
                                if (event instanceof DialogNumEvent) {
                                    displayEvent("Numeric Field Entered [" + ((DialogNumEvent)event).getNum() + "]");
                                } else
                                if (event instanceof DialogButtonEvent) {
                                    if (((DialogButtonEvent) event).getButton() == BUTTON_POSITIVE)
                                        displayEvent("BUTTON_POSITIVE" + " Event");
                                    else
                                    if (((DialogButtonEvent) event).getButton() == BUTTON_NEGATIVE)
                                        displayEvent("BUTTON_NEGATIVE" + " Event");
                                    else
                                    if (((DialogButtonEvent) event).getButton() == BUTTON_NEUTRAL)
                                        displayEvent("BUTTON_NEUTRAL" + " Event");
                                } else {
                                    displayEvent(event.getClass().getName() + " Event");
                                }
                            }
                        })
                .doOnError(new Consumer<Throwable>() {
                    public void accept(Throwable t) {
                        if (Constants.debug)
                        Log.d(Constants.TAG, "Error in Dialog [" + t.toString() + "]");
                        displayEvent("Error [" + t.toString() + "]");

                    }
                });


            if (compDisp != null)
                compDisp.add(dialogEventObservable
                        .subscribe());
        } catch (Exception e) {
            if (Constants.debug)
            Log.d(Constants.TAG,"Error [" + e.getMessage() + "]");
        }
    }

    }
