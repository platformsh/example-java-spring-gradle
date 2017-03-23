package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


@RestController
public class HelloController {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @RequestMapping("/")
    public String index() throws Exception {

        try {
            // This will load the MySQL driver, each DB has its own driver.
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            // spring.datasource.url: jdbc:mysql://HOST/DB_NAME
            connect = DriverManager
                    .getConnection(System.getProperty("spring.datasource.url") + "?"
                            + "user=" + System.getProperty("spring.datasource.username")
                            + "&password=" + System.getProperty("spring.datasource.password"));

            statement = connect.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS banana");
            statement.executeUpdate("CREATE TABLE banana (id INT, name varchar(40))");

            statement.executeUpdate("INSERT INTO banana (id, name) VALUES (1, 'Marco')");
            statement.executeUpdate("INSERT INTO banana (id, name) VALUES (2, 'Crell')");

            resultSet = statement.executeQuery("SELECT * FROM banana");

            String res = formatResultSet(resultSet);

            return res + "<p>Greetings from Spring Boot!</p>\n";

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }

    private String formatResultSet(ResultSet resultSet) throws SQLException {
        String out = new String("<pre>");

        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String name = resultSet.getString("name");

            out += id + " | " + name + "\n";
        }

        out += "</pre>\n";

        return out;
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

}
