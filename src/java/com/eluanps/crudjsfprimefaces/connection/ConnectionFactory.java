package com.eluanps.crudjsfprimefaces.connection;

import com.eluanps.crudjsfprimefaces.util.Errors;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection ConexaoDB() throws Errors {

        Connection conn = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsfprimefaces", "root", "");

        } catch (SQLException e) {
            throw new Errors("Erro no banco de dados: ", e);
        } catch (ClassNotFoundException e2) {
            throw new Errors("Driver de conexão não encontrado: ", e2);
        }
        return conn;

    }

}