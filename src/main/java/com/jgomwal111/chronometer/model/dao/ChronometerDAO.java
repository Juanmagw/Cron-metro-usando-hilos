package com.jgomwal111.chronometer.model.dao;

import com.jgomwal111.chronometer.connection.DBConnection;
import com.jgomwal111.chronometer.model.dataObject.Chronometer;

import java.sql.*;

public class ChronometerDAO extends Chronometer {

    /**
     * Atributos
     */
    private final static String INSERT = "INSERT INTO chronometer (time) VALUES (?,?)";

    /**
     * Constructores
     */
    public ChronometerDAO() {
        super();
    }
    /*public ChronometerDAO(int id, String time) {
        super(id, time);
    }
    public ChronometerDAO(Chronometer c) {
        super(c.getIdChrono(), c.getTime());
    }*/

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
                ps = miCon.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, this.time);
                ps.executeUpdate();
                ResultSet rs= ps.getGeneratedKeys();
                if(rs.next()){
                    this.idChrono =rs.getInt(1);
                }
                ps.close();
                rs.close();
                result = true;
            }catch(SQLException e) {
                e.printStackTrace();
                result = false;
            }
        }
        return result;
    }
}
