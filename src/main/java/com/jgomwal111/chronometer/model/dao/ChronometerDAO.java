package com.jgomwal111.chronometer.model.dao;

import com.jgomwal111.chronometer.connection.DBConnection;
import com.jgomwal111.chronometer.model.dataObject.Chronometer;
import com.jgomwal111.chronometer.utils.message.ErrorMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@XmlRootElement(name="ChronometerDAO")
@XmlAccessorType(XmlAccessType.FIELD)
public class ChronometerDAO extends Chronometer {

    private static ChronometerDAO _instance;
    private List<Chronometer> chronometerList;

    private ChronometerDAO(){
        chronometerList = new ArrayList<Chronometer>();
    }

    public static ChronometerDAO getInstance(){
        if(_instance==null){
            _instance=new ChronometerDAO();
        }
        return _instance;
    }

    public List<Chronometer> getChronometerList() {
        return chronometerList;
    }
    public void setChronometerList(List<Chronometer> chronometerList) {
        this.chronometerList = chronometerList;
    }
    public void addChronometer(Chronometer c){
        if(!chronometerList.contains(c)){
            chronometerList.add(c);
        }
    }

    /**
     * Añade un Cronometro a la lista de Cronometros única.
     * @param c Cronometro que se quiere añadir.
     */
    public boolean addChrono(Chronometer c) {
        boolean result=false;
        if(!chronometerList.contains(c) && c!=null) {
            chronometerList.add(c);
            result=true;
        }
        return result;
    }

    /**
     * Muestra a todos los cronometros de la lista de usuarios única.
     */
    public boolean showChrono() {
        boolean result=false;
        Iterator<Chronometer> it=chronometerList.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
            result=true;
        }
        return result;
    }

    /**
     * Borra al cronometro por su id.
     * @param c cronometro que será borrado.
     */
    public boolean deleteChrono(Chronometer c) {
        boolean result=false;
        if(chronometerList.contains(c) && c!=null) {
            chronometerList.remove(c);
            result=true;
        }
        return result;
    }

    public Chronometer searchById(int id) {
        for(Chronometer c:chronometerList) {
            if(c.getIdChrono()==idChrono) {
                return c;
            }
        }
        return null;
    }

    public void save(ChronometerDAO cDAO){
        JAXBContext contexto;
        String chronometerXML = "Chronometer.xml";
        try {
            contexto = JAXBContext.newInstance(ChronometerDAO.class);
            Marshaller m = contexto.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            m.marshal(cDAO, new File(chronometerXML));
        } catch (JAXBException e) {
            new ErrorMessage(e+"");
        }
    }

    public void load(Chronometer c){
        JAXBContext contexto=null;
        String chronometerXML = "";
        List<Chronometer> chronometers = null;
        try {
            contexto = JAXBContext.newInstance(ChronometerDAO.class);
            Unmarshaller um = contexto.createUnmarshaller();
            ChronometerDAO newDAO = (ChronometerDAO) um.unmarshal(new File(chronometerXML));
            chronometers = (List<Chronometer>) newDAO.getChronometerList();

        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "ChronometerDAO{" +
                "times=" + chronometerList +
                '}';
    }
    /**
     * Atributos
     */
    //private final static String INSERT = "INSERT INTO chronometer (time) VALUES (?)";

    /**
     * Constructores
     */
    /*public ChronometerDAO() {
        super();
    }
    public ChronometerDAO(int id, String time) {
        super(id, time);
    }
    public ChronometerDAO(Chronometer c) {
        super(c.getIdChrono(), c.getTime());
    }*/

    /**
     * Método para insertar un tiempo del cronómetro
     * @return true si el objeto a sido insertado o false si no lo ha sido
     */
    /*public boolean insert() {
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
    }*/
}
