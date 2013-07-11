package org.kevoree.usecase;

import org.kevoree.annotation.*;
import org.kevoree.framework.AbstractComponentType;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: jed
 * Date: 18/04/13
 * Time: 14:36
 */
@Library(name = "tam")
@ComponentType
@Provides({
        @ProvidedPort(name = "request", type = PortType.SERVICE, className = MySQLInterface.class)
})
@DictionaryType({
        @DictionaryAttribute(name = "properties", defaultValue = "./mysql.properties")
})
public class MySQLWrapper extends AbstractComponentType implements MySQLInterface {

    private java.sql.Connection connection;

    @Start
    public void start() throws ClassNotFoundException, SQLException, IOException {
        String dbUrl = getDictionary().get("databaseURL").toString();
        String dbClass = getDictionary().get("driverClass").toString();
        Properties credentials = new Properties();
        credentials.load(getClass().getClassLoader().getResourceAsStream(getDictionary().get("credentials").toString()));
        Class.forName(credentials.getProperty("driverClass"));
        connection = DriverManager.getConnection(credentials.getProperty("databaseURL"), credentials.getProperty("username"), credentials.getProperty("password"));
    }

    @Stop
    public void stop() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

/*    @Update
    public void update() {


    }*/

    @Override
    @Port(name = "request", method = "query")
    public ResultSet query(String query) throws SQLException {
        Statement statement = connection.createStatement();
        // TODO maybe the ResultSet is not a good return type especially if the interactino with this port comes from remote component
        return statement.executeQuery(query);
    }
}
