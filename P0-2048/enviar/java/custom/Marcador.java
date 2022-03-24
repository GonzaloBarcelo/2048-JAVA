package java.custom;

import java.custom.*;
import java.ui.*;
import java.ioY.*;

public class Marcador{
	int record;
	int actual;

	public Marcador(){
		this.actual=0;
		this.record=0;
	}
	public void actualizar(int valor){
		this.actual+=valor;
		if (actual>record){
			this.record=actual;
		}
	}
	public void resetActual(){
		this.actual=0;
	}
	public int getActual(){
		return this.actual;
	}
	public int getRecord(){
		return this.record;
	}
	public void setRecord(Integer record){
		if (record!=null)
			this.record=record;
		else{
			this.record=0;
		}
	}
}