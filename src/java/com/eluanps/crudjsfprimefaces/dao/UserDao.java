package com.eluanps.crudjsfprimefaces.dao;

import com.eluanps.crudjsfprimefaces.connection.ConnectionFactory;
import com.eluanps.crudjsfprimefaces.entity.User;
import com.eluanps.crudjsfprimefaces.util.Errors;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDao implements CrudDao<User> {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;

    List<User> lista = new ArrayList<>();

    @Override
    public void salvar(User user) throws Error {

        String sqlsave = "insert into user (nome, cpf) values(?,?)";
        String sqledit = "update user set nome = ?, cpf = ? where id = ?";

        try {
            conn = new ConnectionFactory().ConexaoDB();
        } catch (Errors ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            if (user.getId() == null) {

                pstm = conn.prepareStatement(sqlsave);

            } else {

                pstm = conn.prepareStatement(sqledit);
                pstm.setInt(5, user.getId());

            }

            pstm.setString(1, user.getNome());
            pstm.setString(2, user.getCpf());

            pstm.execute();
            pstm.close();

        } catch (Exception ex) {

        }
    }

    @Override
    public void deletar(User user) throws Error {

        String sql = "delete from user where id = ?";

        try {
            conn = new ConnectionFactory().ConexaoDB();
        } catch (Errors ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, user.getId());

            pstm.execute();
            pstm.close();

        } catch (Exception e) {

        }

    }

    @Override
    public List<User> read() throws Errors {

        String sql = "select * from user";

        conn = new ConnectionFactory().ConexaoDB();

        try {

            User user = new User();

            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {

                user.setNome(rs.getString("nome"));
                user.setCpf(rs.getString("cpf"));

            }

            lista.add(user);

        } catch (Exception e) {
        }

        return lista;
    }

}
