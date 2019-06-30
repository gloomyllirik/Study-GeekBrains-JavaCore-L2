package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.text.DefaultCaret;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    @FXML
    TextArea textArea;

    @FXML
    TextField textField;

    public void sendMsg(){
        String str = textField.getText();
        if (str.isEmpty())
            textArea.appendText("Пароль не может быть пустым!!! :((\n\n");
        else
            textArea.appendText(str + "\n\n");
        if (check_pswd(str))
            textArea.appendText("Пароль соответствует требованиям! :)\n\n");
        else
            textArea.appendText("Пароль не соответствует требованиям! :((\n\n");
        textField.clear();
        textField.requestFocus();
    }

    public static boolean check_pswd(String str){
        String regex_str = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern p = Pattern.compile(regex_str);
        Matcher m = p.matcher(str);

        return m.matches();
    }
}
