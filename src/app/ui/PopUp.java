package app.ui;

import app.ui.sensors.actions.ActionCode;
import app.ui.sensors.actions.Actions;

import javax.swing.*;

public class PopUp implements AppWindow{
    public JFrame frame;

    private String prompt;

    private ActionCode actionCode;
    private Ui ui;

    private JTextField field;

    public PopUp(String prompt, Ui ui) {
        this.prompt = prompt;
        this.ui = ui;

        frame = new JFrame();

        frame.setTitle("SEPience");
        frame.setSize(500, 200);

        JPanel panel = new JPanel();

        panel.add(new JLabel(prompt));

        field = new JTextField(40);

        panel.add(field);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new Actions(ActionCode.POPUP_SUBMIT, this));

        panel.add(submitButton);

        frame.add(panel);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void setVisible(boolean visible) {
        this.frame.setVisible(visible);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public Ui getUi() {
        return ui;
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }

    public ActionCode getActionCode() {
        return actionCode;
    }

    public void setActionCode(ActionCode actionCode) {
        this.actionCode = actionCode;
    }

    public JTextField getField() {
        return field;
    }

    public void setField(JTextField field) {
        this.field = field;
    }
}
