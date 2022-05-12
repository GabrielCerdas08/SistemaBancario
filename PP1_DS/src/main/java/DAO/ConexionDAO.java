

package DAO;
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.SQLException;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.Date;
    import java.time.LocalDate;
    import java.util.ArrayList;

public class ConexionDAO {
 private String url;
 public  Connection connect;

 
  public ConexionDAO (String pUrl) {
      pUrl =  "jdbc:sqlserver://;databaseName=BDBanco;user=administrador;password=diseno2022!";
      //pUrl = "jdbc:sqlserver://serverbanking.database.windows.net:1433;database=bdBanco;user=administrador@serverbanking;password=diseno2022!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
      this.url = pUrl;
   }
  
  /**
   * Método para conectarse a la base de datos por medio del driver JDBC para realizar operaciones.
   */
  public void abrirConexioDAO() {
      try {
          connect = DriverManager.getConnection(url);
          /*if (connect != null) {
             System.out.print("conectado");
          }*/
      } catch (SQLException ex) {
      }
  }
  
  //** Metdo para cerrar la conexion sql server**//
  public void cerrarConexionDAO() {
      try {
        connect.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
  }
}
     
  
