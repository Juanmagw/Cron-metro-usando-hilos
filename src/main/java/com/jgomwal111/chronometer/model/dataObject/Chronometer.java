package com.jgomwal111.chronometer.model.dataObject;

import javafx.scene.control.Button;

import java.util.List;

public class Chronometer {

    /**
     * Atributos de la clase
     */
    protected int id;
    protected String time;
    private String toastMessage;
    private List<String> times;

    /**
     * Constructores de la clase
     */
    public Chronometer() {
    }
    public Chronometer(int id, String time){
        this.id = id;
        this.time = time;
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
    public int getId() {
        return id;
    }

    /**
     * Método que permite escribir un texto
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Método que permite escribir un texto
     * @param toastMessage mensaje que saldrá por pantalla
     */
    public void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
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
}
