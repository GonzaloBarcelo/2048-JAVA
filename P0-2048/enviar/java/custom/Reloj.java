package java.custom;

import java.custom.*;
import java.ui.*;
import java.ioY.*;

public class Reloj extends Thread{

	int temporizador=0;
	String nombre;

	public Reloj(String nombre){
		this.nombre= nombre;
	}

	public void run(){
		while (true){
			Util.wait(1);
			temporizador+=1;
		}
		
	}
	public int getTemporizador(){
		return temporizador;
	}
}