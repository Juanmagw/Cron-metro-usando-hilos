package com.jgomwal111.chronometer.model.dataObject;

public class SolicitaParar {
	private boolean parado;
	
	public boolean getParado() {
		return parado;
	}
	
	
	public synchronized void setParado(boolean b) {
		this.parado =b;
		
		notifyAll();
	}
	
	public synchronized void waiting() throws InterruptedException {
		while(this.parado) {
			wait();
		}
	}
	
}
