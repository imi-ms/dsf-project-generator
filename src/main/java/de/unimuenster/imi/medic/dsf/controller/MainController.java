package de.unimuenster.imi.medic.dsf.controller;

import de.unimuenster.imi.medic.dsf.App;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController implements Initializable
{

    @FXML
    private AnchorPane mainWindow;

    //Changes color of window borders when focused.
    @FXML
    private void toggleBorderColor(Boolean focused)
    {
        if (focused)
        {
            mainWindow.getStyleClass().add("purpleBorder");
            mainWindow.getStyleClass().remove("defaultBorder");
        }
        else if (!focused)
        {
            mainWindow.getStyleClass().remove("purpleBorder");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        Stage stage = App.getMainStage();
        stage.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean onHidden, Boolean onShown)
            {
                Boolean focused = ov.getValue();
                toggleBorderColor(focused);
            }
        });
    }
}