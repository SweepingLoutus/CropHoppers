package com.sweepingloutus.crophoppers;

import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    static Connection connection;

    private final main main;
    public MySQL(main mainArg){main = mainArg;}

    public Connection openConnection(){
        FileConfiguration config = main.getConfig();

        String host = config.getString("database.host");
        String port = config.getString("database.port");
        String database = config.getString("database.database");
        String username = config.getString("database.username");
        String password = config.getString("database.password");
        try{
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"
                            + host + ":" + port + "/" + database,
                    username, password);
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    return null;}

}
