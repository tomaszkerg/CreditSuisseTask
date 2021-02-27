package en.dbtask.dao;

import en.dbtask.entity.Occurrence;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
public class OccurrenceDao {

    private static final String URL = "jdbc:hsqldb:hsql://localhost/taskdb";
    private static final String USER = "sa";
    private static final String PASS = "";
    private Connection connection;
    private final String sqlSave = "INSERT INTO occurrence(id,duration,type,host,alert) values (?,?,?,?,?)";
    private final String sqlTable = "CREATE TABLE occurrence(\n" +
            "    id varchar(20) not null,\n" +
            "    duration int not null,\n" +
            "    type varchar(20),\n" +
            "    host varchar(20),\n" +
            "    alert boolean)";
    public OccurrenceDao() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection(URL, USER, PASS);
            log.info("connecting to database");
            dropAndCreateTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void create(Occurrence occurrence){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSave);
            preparedStatement.setString(1, occurrence.getId());
            preparedStatement.setString(2, occurrence.getDuration().toString());
            preparedStatement.setString(3, occurrence.getType());
            preparedStatement.setString(4, occurrence.getHost());
            preparedStatement.setString(5, occurrence.getAlert().toString());
            preparedStatement.executeUpdate();
            log.info("added to database log with id "+occurrence.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dropAndCreateTable(){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlTable);
            preparedStatement.execute();
            log.info("dropped and created new table");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
            log.info("connection with db closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
