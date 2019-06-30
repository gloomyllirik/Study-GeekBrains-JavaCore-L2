package Lesson_6.server;

import java.sql.*;

public class AuthService {
    private static Connection connection;
    private static Statement stmt;

    public static void connection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:mainDB.db");
            stmt = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getNickByLoginAndPass(String login, String pass, int key) {
        String sql;

        try {
            if (key == 1) {
                sql = String.format("INSERT INTO main (login, password, nickname) VALUES('%s','%s','%s')", login, pass, login);
                if (stmt.executeUpdate(sql) == 0)
                    return "/reg_error";
            }

            sql = String.format("SELECT nickname FROM main where login = '%s' and password = '%s'", login, pass);
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next())
                return rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean checkBlackList(String nick, String ban_nick) {
        String sql = String.format("SELECT id_ban_client FROM blacklist_table WHERE id_client = (SELECT id FROM main" +
                " WHERE nickname = '%s') AND id_ban_client = (SELECT id FROM main WHERE nickname = '%s')", nick, ban_nick);
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
//                System.out.println(rs.getInt(1));
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean addBlackList(String nick, String ban_nick) {
        String sql = String.format("INSERT INTO blacklist_table (id_client,id_ban_client) VALUES ((SELECT id FROM main " +
                "WHERE nickname = '%s'),(SELECT id FROM main WHERE nickname = '%s'))", nick, ban_nick);
        try {
            if (stmt.executeUpdate(sql) == 0)
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
