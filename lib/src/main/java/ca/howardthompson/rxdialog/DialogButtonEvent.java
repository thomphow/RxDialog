package ca.howardthompson.rxdialog;


public class DialogButtonEvent implements DialogEvent
{
    private final int theButton;

    public DialogButtonEvent(int button)
    {
        this.theButton = button;
    }

    public int getButton()
    {
        return theButton;
    }
}
