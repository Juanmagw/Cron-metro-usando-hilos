package com.jgomwal111.chronometer.model.dataObject;

import javafx.application.Platform;
import javafx.scene.control.TextField;

import java.util.List;

public class Chronometer extends Thread{

    /**
     * Atributos de la clase
     */
    protected int idChrono;
    protected String time;
    private List<String> times;

    private TextField tfChronometer;

    /**
     * Constructores de la clase
     */
    public Chronometer() {
    }
    public Chronometer(int id, String time, TextField tfChronometer){
        this.idChrono = id;
        this.time = time;
        this.tfChronometer = tfChronometer;
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

    /**
     * Método para crear el cronómetro
     * @param seconds tiempo que cuadra el cronómetro
     */
    public void timer(int seconds){
        int hours = seconds/3600;
        seconds %= 3600;
        int minutes = seconds/60;
        seconds %= 60;
        this.time = hours+":"+minutes+":"+seconds;
    }

    @Override
    public void run() {

            int seconds = 0;
            try{
                do{
                    this.timer(++seconds);
                    tfChronometer.setText(this.getTime());
                    Thread.sleep(1000);
                }while(!this.isInterrupted());
            }catch(Exception e){
                e.printStackTrace();
            }



    }
}
