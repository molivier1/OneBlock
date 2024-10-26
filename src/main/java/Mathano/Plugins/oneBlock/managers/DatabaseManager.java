package Mathano.Plugins.oneBlock.managers;

import Mathano.Plugins.oneBlock.OneBlock;
import Mathano.Plugins.oneBlock.database.statements.DataOneBlocksStatements;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.configuration.ConfigurationSection;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {
    public static HikariDataSource DATABASE;

    public static DatabaseManager INSTANCE;

    public DatabaseManager() {
        INSTANCE = this;

        HikariConfig config = new HikariConfig();

        // Obvious failure until configuration is all done...
        ConfigurationSection section = OneBlock.INSTANCE.getConfig().getConfigurationSection("database");
        config.setJdbcUrl("jdbc:mysql://" + section.getString("url"));
        config.setUsername(section.getString("username"));
        config.setPassword(section.getString("password"));
        config.setDriverClassName("com.mysql.jdbc.Driver");

        DATABASE = new HikariDataSource(config);

        createTables();
    }

    public Connection getConnection() throws SQLException {
        return DATABASE.getConnection();
    }

    public void close() {
        DATABASE.close();
    }

    public void createTables() {
        try(Connection connection = getConnection()) {
            connection.prepareStatement(DataOneBlocksStatements.CREATE_DATAONEBLOCKS_TABLE).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
