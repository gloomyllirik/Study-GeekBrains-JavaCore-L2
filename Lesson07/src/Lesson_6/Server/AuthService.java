package Lesson_6.Server;

import java.sql.*;

public class AuthService {

    private static Connection connection;
    private static Statement stmt;

    public static void connect() throws SQLException {
        try {
            // обращение к драйверу
            Class.forName("org.sqlite.JDBC");
            // установка подключения
            connection = DriverManager.getConnection("jdbc:sqlite:DBUsers.db");
            // создание Statement для возможности оправки запросов
            stmt = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getNickByLoginAndPass(String login, String pass) {
        // формирование запроса
        String sql = String.format("SELECT isOnline, nickname FROM main where login = '%s' and password = '%s'", login, pass);
        String nick;

        try {
            // оправка запроса и получение ответа
            ResultSet rs = stmt.executeQuery(sql);

            // если есть строка возвращаем результат если нет то вернется null
            if(rs.next()) {
                // если данный логин уже онлайн, то завершаем метод, возвращаем null
                if(rs.getBoolean(1)) {
                    return "/ONLINE_ALLREADY";
                }
                else {
                    nick = rs.getString(2);
                    // формируем и выполняем новый SQL запрос для регистрации логина в БД
                    sql = String.format("UPDATE main SET isOnline = 1 WHERE login = '%s' and password = '%s'", login, pass);
                    stmt.executeUpdate(sql);
                    return nick;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void setOffline(String nick) {
        // формирование запроса
        String sql = String.format("UPDATE main SET isOnline = 0 WHERE nickname = '%s'", nick);

        try {
            // оправка запроса
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            // закрываем соединение
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
