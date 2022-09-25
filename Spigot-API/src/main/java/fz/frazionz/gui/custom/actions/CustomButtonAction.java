package fz.frazionz.gui.custom.actions;

import org.bukkit.material.Button;

public class CustomButtonAction {

    protected ButtonAction action;

    public CustomButtonAction(ButtonAction action) {
        this.action = action;
    }

    public ButtonAction getAction() {
        return action;
    }

    public void setAction(ButtonAction action) {
        this.action = action;
    }

    public String toJson() {
        return "{\"action\": " + action.toString() + "}";
    }
}
