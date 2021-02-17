package com.sweepingloutus.crophoppers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main extends JavaPlugin {

    //get utils files
    private utils utils;
    private MySQL MySQL;

    public utils getUtils() { return utils; }
    public  MySQL getMySQL() {return MySQL;}

    Logger logger = this.getLogger();
    FileConfiguration config = this.getConfig();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        utils = new utils(this);
        if(!(config.getString("database.host").equals(""))){
            getCommand("convert").setExecutor(new convertCommand(this));
            getServer().getPluginManager().registerEvents(new HopperPlace(this),this);
            try {
                MySQL = new MySQL(this);
                Statement statement = this.getMySQL().openConnection().createStatement();
                statement.execute("CREATE TABLE IF NOT EXISTS`hoppers` (\n" +
                        "\t`coord_x` INT NOT NULL,\n" +
                        "\t`coord_y` INT NOT NULL,\n" +
                        "\t`coord_z` INT NOT NULL,\n" +
                        "\t`chunk_x` INT NOT NULL,\n" +
                        "\t`chunk_z` INT NOT NULL,\n" +
                        "\t`type` VARCHAR(255) NOT NULL\n" +
                        ");");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            logger.log(Level.WARNING, "Please Config me!!!!!");
        }

    }

    @Override
    public void onDisable() {

    }
}

