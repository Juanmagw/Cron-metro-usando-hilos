package com.jgomwal111.chronometer.model.dataObject;

import javafx.scene.control.TextField;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name="Chronometer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Chronometer implements Runnable, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Atributos de la clase
     */
    @XmlAttribute(name="ID")
    protected int idChrono;
    @XmlAttribute(name="Tiempo")
    protected String time;
    @XmlTransient
    protected List<String> times;
    @XmlTransient
    private TextField tfChronometer;
    @XmlTransient
    private SolicitaParar parado = new SolicitaParar();

    /**
     * Constructores de la clase
     */
    public Chronometer() {
        this(-1,"",null,null);
    }
    public Chronometer(TextField tfChronometer) {
        this.tfChronometer = tfChronometer;
    }
    public Chronometer(int id, String time){
        this.idChrono = id;
        this.time = time;
    }
    public Chronometer(int id, String time, TextField tfChronometer, SolicitaParar parado){
        this.idChrono = id;
        this.time = time;
        this.tfChronometer = tfChronometer;
        this.parado.setParado(false);
    }

    /**
     * Tiempo guardado de cada cronómetro
     * @return el tiempo que ha hecho el cronómetro
     */
    public String getTime() {
        return time;
    }
    /**
     * Método que permite escribir un texto
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }
    /**
     * ID del cronómetro
     * @return la id que posee cada tiempo
     */
    public int getIdChrono() {
        return idChrono;
    }
    /**
     * Método que permite escribir un texto
     * @param idChrono
     */
    public void setIdChrono(int idChrono) {
        this.idChrono = idChrono;
    }
    /**
     * Lista de los tiempos que ha guardado el cronómetro
     * @return
     */
    public List<String> getTimes() {
        return times;
    }
    /**
     * Método para añadir tiempos a la lista
     * @param time tiempo a añadir
     */
    public void addTime(String time){
        if(time.equals(this.time) && !this.times.contains(time) && time!=null){
            this.times.add(time);
        }
    }

    public TextField getTfChronometer() {
        return tfChronometer;
    }

    public void setTfChronometer(TextField tfChronometer) {
        this.tfChronometer = tfChronometer;
    }

    public SolicitaParar getParado() {
        return parado;
    }

    public void setParado(SolicitaParar parado) {
        this.parado = parado;
    }

    /**
     * Método para crear el cronómetro
     * @param seconds tiempo que cuadra el cronómetro
     */
    public void timer(int seconds){
        int hours = seconds/3600;
        seconds %= 3600;
        int minutes = seconds/60;
        seconds %= 60;
        if(seconds<10){
            this.time = hours+":"+minutes+":0"+seconds;
        }
        if(minutes<10){
            this.time = hours+":0"+minutes+":"+seconds;
        }
        if(hours<10){
            this.time = "0"+hours+":"+minutes+":"+seconds;
            }
    }

    @Override
    public void run() {
    Thread t = new Thread(this);
    int seconds = 0;
    try{
        do{
            while(!this.parado.getParado()){
                this.timer(++seconds);
                tfChronometer.setText(this.getTime());
                Thread.sleep(1000);
                this.parado.waiting();
            }
            }while(!t.isInterrupted());
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Chronometer{" +
                "idChrono=" + idChrono +
                ", time='" + time + '\'' +
                '}';
    }
}
