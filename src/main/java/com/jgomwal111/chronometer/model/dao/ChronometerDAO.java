package com.jgomwal111.chronometer.model.dao;

import com.jgomwal111.chronometer.connection.DBConnection;
import com.jgomwal111.chronometer.model.dataObject.Chronometer;

import java.sql.*;

public class ChronometerDAO extends Chronometer {

    /**
     * Atributos
     */
    private final static String INSERT = "INSERT INTO chronometer (time) VALUES (?)";

    /**
     * Constructores
     */
    public ChronometerDAO() {
        super();
    }
    public ChronometerDAO(String time) {
        super(time);
    }
    public ChronometerDAO(Chronometer c) {
        super(c.getTime());
    }

    /**
     * Método para insertar un tiempo del cronómetro
     * @return true si el objeto a sido insertado o false si no lo ha sido
     */
    public boolean insert() {
        boolean result = false;
        Connection miCon = DBConnection.getConnect();
        if(miCon != null){
            PreparedStatement ps;
            try {
                ps = miCon.prepareStatement(INSERT);
                ps.setString(1, this.time);
                ps.executeUpdate();
                ps.close();
                result = true;
            }catch(SQLException e) {
                e.printStackTrace();
                result = false;
            }
        }
        return result;
    }
}
