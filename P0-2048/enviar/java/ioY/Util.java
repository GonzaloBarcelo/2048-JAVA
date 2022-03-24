package java.ioY;

import java.custom.*;
import java.ioY.*;
import java.ui.*;


/** Clase de funcionalidad variada que proporciona una ayuda al alumno */
public class Util
{
	/** 
		Detiene el programa el tiempo especificado
		@param segundos número de segundos a esperar 
	*/
	public static void wait(int segundos)
	{
		try
		{
			Thread.sleep(segundos*1000);
		}
		catch(Exception e)
		{

		}
	}
	public void wait1(){
		try{
			Thread.sleep(1000);
		}
		catch(Exception e){

		}
	}
}
