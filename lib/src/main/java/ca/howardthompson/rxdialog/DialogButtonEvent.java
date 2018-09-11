package ca.howardthompson.rxdialog;

/**
 * Button event class derived from base event class used as generic parent
 * for passing different kinds of events.
 */
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
