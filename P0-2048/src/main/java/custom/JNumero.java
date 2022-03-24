package java.custom;

import java.custom.*;
import java.ui.*;
import java.ioY.*;

import java.awt.Color;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

public class JNumero{
	private int valor;
	private JNumero temp;

	public JNumero(int valor){

		this.valor=valor;
	}
	public int getValue(){
		return this.valor;
	}

	@Override
	public boolean equals(Object o){
		if (o instanceof JNumero){
			temp=(JNumero) temp;
			if(temp.getValue()==valor){
				return true;
			}
		}
		
		return false; 
	}

	@Override
	public String toString(){
		return String.valueOf(this.valor);
	}

}