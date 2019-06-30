package Lesson_6.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class Controller {
    @FXML
    VBox vboxChat;
    @FXML
    TextField textField;
    @FXML
    Button btn1;
    @FXML
    Button btn_auth;
    @FXML
    Button btn_reg;
    @FXML
    HBox bottomPanel;
    @FXML
    HBox upperPanel;
    @FXML
    TextField loginField;
    @FXML
    TextField passwordField;
    @FXML
    ListView<String> clientList;

    private boolean isAuthorized;

    public void setAuthorized(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
        if(!isAuthorized) {
            upperPanel.setVisible(true);
            upperPanel.setManaged(true);
            bottomPanel.setVisible(false);
            bottomPanel.setManaged(false);
            clientList.setVisible(false);
            clientList.setManaged(false);
        } else {
            upperPanel.setVisible(false);
            upperPanel.setManaged(false);
            bottomPanel.setVisible(true);
            bottomPanel.setManaged(true);
            clientList.setVisible(true);
            clientList.setManaged(true);
        }
    }

    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    String[] authData;
    boolean errorSocket = false;

    final String IP_ADRESS = "localhost";
    final int PORT = 8189;

    public void autoConnect(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (errorSocket && socket.isClosed() && authData != null){
                        Random rnd = new Random();
                        System.out.println("\n\nТаймаут в мс: " + (rnd.nextInt(7000) + 3000) + "\n\n");
                        try {
                            Thread.sleep((rnd.nextInt(7000) + 3000));
                            btn_auth.fire();
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        if (!socket.isClosed()){
                            System.out.println("Соединение с сервером восстановлено");
                            errorSocket = false;
                            break;
                        }
                    }
                }
            }
        }).start();
    }

    public void connect() {
        try {
            socket = new Socket(IP_ADRESS, PORT);

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/authok")) {
                                authData = str.split(" ");
                                setAuthorized(true);
                                break;
                            } else {
//                                textArea.appendText(str + "\n");
                                appendStrToChat(str);
                            }
                        }

                        while (true) {
                            String str = in.readUTF();
                            if (str.equals("/serverclosed")){
                                System.out.println("Отключились от сервера");
                                break;
                            }
                            if (str.startsWith("/clientlist")) {
                                String[] tokens = str.split(" ");
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        clientList.getItems().clear();
                                        for (int i = 1; i < tokens.length; i++) {
                                            clientList.getItems().add(tokens[i]);
                                        }
                                    }
                                });
                            } else {
//                                textArea.appendText(str + "\n");
                                appendStrToChat(str);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        errorSocket = true;
                        autoConnect();
                    } finally {
                        try {
                            socket.close();
                            setAuthorized(false);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void Dispose() {
        try {
            if(isAuthorized && !socket.isClosed() && out != null) {
                System.out.println("Отправляем сообщение о закрытии");
                out.writeUTF("/end");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg() {
        try {
            out.writeUTF(textField.getText());
            textField.clear();
            textField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tryToAuthReg(ActionEvent actionEvent) {
        if (errorSocket && authData != null){
            loginField.setText(authData[1]);
            passwordField.setText(authData[2]);
        }
        if (!loginField.getText().isEmpty() && !passwordField.getText().isEmpty()) {

            String str = "/auth " + loginField.getText() + " " + passwordField.getText();

            if (actionEvent.getSource().equals(btn_reg))
                str = "/reg " + loginField.getText() + " " + passwordField.getText();

            loginField.clear();
            passwordField.clear();

            if (socket == null || socket.isClosed()) {
                connect();
            }

            try {
                out.writeUTF(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            appendStrToChat("Не заполнены поля авторизации/регистрации!");
    }

    public void selectClient(MouseEvent mouseEvent) {
        if(mouseEvent.getClickCount() == 2) {
            textField.setText("/w " + clientList.getSelectionModel().getSelectedItem() + " " + textField.getText());
            textField.requestFocus();
            textField.deselect();
            textField.positionCaret(textField.getLength());
        }
    }

    public void appendStrToChat(String msg){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                VBox vBox = new VBox();
                Label label = new Label(msg + "\n");
                String[] str = msg.split(" ");

                vBox.setAlignment(Pos.TOP_LEFT);
                if (authData != null && str[0].equals(authData[3] + ":"))
                    vBox.setAlignment(Pos.TOP_RIGHT);
                vBox.getChildren().add(label);
                vboxChat.getChildren().add(vBox);
            }
        });
    }
}
