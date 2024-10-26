package Mathano.Plugins.oneBlock.database.statements;

public class DataOneBlocksStatements {
    public static final String CREATE_DATAONEBLOCKS_TABLE = "CREATE TABLE IF NOT EXISTS dataoneblocks ("
            + "uuid             VARCHAR(36)         NOT NULL PRIMARY KEY,"
            + "location         VARCHAR(255)                NOT NULL);";

    public static final String LOAD_ONEBLOCKS_CACHE = "SELECT uuid, location FROM dataoneblocks;";

    public static final String INSERT_ONEBLOCK = "INSERT INTO dataoneblocks VALUES (?, ?);";
}
