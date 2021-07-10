package library.management.system;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {

    Connection c;

    Conn() {
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            c = DriverManager.getConnection("jdbc:mysql:///library_management_system", "root", "");
            c = DriverManager.getConnection("jdbc:sqlserver://mohibur.database.windows.net:1433;database=library_management_system;user=mohibur@mohibur;password=Server_25101996;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");

        } catch (Exception e) {

        }
    }
}
