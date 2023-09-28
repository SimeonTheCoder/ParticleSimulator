package app.ui;

import app.ui.color.Palette;
import app.ui.sensors.actions.ActionCode;
import app.ui.sensors.actions.Actions;

import javax.swing.*;

public class PopUp implements AppWindow{
    public JFrame frame;

    private String prompt;

    private ActionCode actionCode;
    private Ui ui;

    private JTextField field;

    public PopUp(String prompt, Ui ui, String title) {
        this.prompt = prompt;
        this.ui = ui;

        frame = new JFrame();

        frame.setTitle(title == null ? "SEPience" : title);
        frame.setSize(500, 230);

        JPanel panel = new JPanel();

        panel.setBackground(Palette.secondaryColor);
        panel.setForeground(Palette.primaryColor);

        JLabel promptLabel = new JLabel(prompt);
        promptLabel.setBackground(Palette.secondaryColor);
        promptLabel.setForeground(Palette.primaryColor);

        panel.add(promptLabel);

        field = new JTextField(40);
        field.setBackground(Palette.thirdColor);
        field.setForeground(Palette.primaryColor);

        panel.add(field);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new Actions(ActionCode.POPUP_SUBMIT, this));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new Actions(ActionCode.CANCEL_WINDOW, this));

        panel.add(submitButton);
        panel.add(cancelButton);

        frame.add(panel);

        frame.setLocation(400, 400);
        frame.setUndecorated(true);

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
