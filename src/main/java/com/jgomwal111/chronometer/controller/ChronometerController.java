package com.jgomwal111.chronometer.controller;

import com.jgomwal111.chronometer.model.dao.ChronometerDAO;
import com.jgomwal111.chronometer.model.dataObject.Chronometer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class ChronometerController implements Initializable {

    /**
     * Objetos usados para la clase
     */
    private Chronometer c = new Chronometer();
    //private ChronometerDAO cDAO = new ChronometerDAO(c);
    private ChronometerDAO cDAO;
    Thread t = null;

    /**
     * Elementos FXML
     */
    @FXML private Button btnPlay;
    @FXML private Button btnPause;
    @FXML private Button btnReset;
    @FXML private ImageView imgPlay;
    @FXML private ImageView imgPause;
    @FXML private ImageView imgReset;
    @FXML private TextField tfChronometer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfChronometer.setText("00:00:00");
        btnPlay.setVisible(true);
        imgPlay.setVisible(true);
        btnPause.setVisible(false);
        imgPause.setVisible(false);
        btnReset.setVisible(true);
        imgReset.setVisible(true);
    }

    @FXML public void play(){
        c.setTfChronometer(tfChronometer);
        if(t==null) {
            t = new Thread(c);
            t.start();
        }
        if(!t.isInterrupted()) {
            c.getParado().setParado(false);
            btnPlay.setVisible(false);
            imgPlay.setVisible(false);
            btnPause.setVisible(true);
            imgPause.setVisible(true);
            btnReset.setVisible(false);
            imgReset.setVisible(false);
        }
    }

    @FXML public void pause(){
        if(!t.isInterrupted()){
            c.getParado().setParado(true);
            cDAO.save(c);
            //cDAO.insert();
            btnPlay.setVisible(true);
            imgPlay.setVisible(true);
            btnPause.setVisible(false);
            imgPause.setVisible(false);
            btnReset.setVisible(true);
            imgReset.setVisible(true);
        }
    }

    @FXML public void reset(){
            if(!t.isInterrupted()){
                c.getParado().setParado(true);
                tfChronometer.setText("00:00:00");
                btnPlay.setVisible(true);
                imgPlay.setVisible(true);
                btnPause.setVisible(false);
                imgPause.setVisible(false);
                btnReset.setVisible(true);
                imgReset.setVisible(true);
            }
    }


}