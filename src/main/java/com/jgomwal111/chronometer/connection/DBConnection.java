package com.jgomwal111.chronometer.connection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    /**
     * Atributos de clase
     */
    private static Connection miCon;
    private static DBConnection _newInstance;

    /**
     * Metodo para realizar la conexion
     */
    private DBConnection() {
        try {
            DataConnection dc = load();
            miCon = DriverManager.getConnection(dc.getServer()+"/"+dc.getDatabase(), dc.getUsername(), dc.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
            miCon =null;
        }
    }

    /**
     * Metodo que realiza la instancia de la clase
     * @return Devuelve el objeto con inicializado
     */
    public static Connection getConnect() {
        if(_newInstance==null) {
            _newInstance=new DBConnection();
        }
        return miCon;
    }

    /**
     * Obtiene de un archivo xml los datos para necesario para realizar la conexion con la bbdd
     * @return Objeto con los datos para realizar la conexion
     */
    public DataConnection load() {
        DataConnection dc = new DataConnection();
        JAXBContext context;
        try {
            context=JAXBContext.newInstance(DataConnection.class);
            Unmarshaller um = context.createUnmarshaller();
            dc = (DataConnection) um.unmarshal(DBConnection.class.getResource("/connectionData/connection.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return dc;
    }

    /**
     * Procedimiento el cual finaliza la conexión
     */
    public static void close() {
        if(miCon != null) {
            try {
                miCon.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
