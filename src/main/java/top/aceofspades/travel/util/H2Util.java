package top.aceofspades.travel.util;

import org.h2.jdbcx.JdbcConnectionPool;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author duanbt
 * @version 1.0
 **/
public class H2Util {

    private static DataSource dataSource;



    static {
        dataSource = JdbcConnectionPool.create("jdbc:h2:./data/travel;AUTO_SERVER=TRUE", "travel", "travel");
        InputStream in = H2Util.class.getClassLoader().getResourceAsStream("h2init.properties");
        Properties prop = new Properties();
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String schemaInit = prop.getProperty("schema.init", "true");
        String schemaSql = prop.getProperty("schema.sql", null);
        if (Boolean.parseBoolean(schemaInit)) {
            if (schemaSql != null) {
                execSql(schemaSql);
            }

            String dataInit = prop.getProperty("data.init", "true");
            String dataSql = prop.getProperty("data.sql", null);
            if (Boolean.parseBoolean(dataInit)) {
                if (dataSql != null) {
                    execSql(dataSql);
                }
            }
        }
    }

    private H2Util(){
    }

    private static void execSql(String sqlFile) {
        InputStream sqlInput = H2Util.class.getClassLoader().getResourceAsStream(sqlFile);
        if (sqlInput == null) {
            throw new IllegalStateException(sqlFile + " can not found");
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(sqlInput));
        String sql = reader.lines().collect(Collectors.joining("\n"));
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
