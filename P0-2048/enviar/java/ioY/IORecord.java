package java.ioY;

import java.custom.*;
import java.ioY.*;
import java.ui.*;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.io.IOException;
import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
public class IORecord{
	public static Integer leerRecord(){
	try{
		FileReader fr= new FileReader("temp.txt");
		BufferedReader br= new BufferedReader(fr);
		return Integer.parseInt(br.readLine().trim());
	}
	catch(Exception e){

	}
	return null;
	}
	public static void writeRecord(int record){
		try{
			FileWriter fw= new FileWriter("temp.txt");
			PrintWriter pw= new PrintWriter(fw);
			pw.write(String.valueOf(record));
			pw.close();
		}
		catch(Exception e){

		}
		
	}
	public static void writeAll(Tablero tablero, int record){
		try{

			FileWriter fw= new FileWriter("temp.txt");
			PrintWriter pw= new PrintWriter(fw);
			pw.write(String.valueOf(record));
			for (int i=0;i<tablero.dimensionX;i++){
				StringBuilder sb=new StringBuilder();	
				for (int j=0;j<tablero.dimensionY;j++){
					if (tablero.getElement(i,j)!=null){
						sb.append(String.valueOf(tablero.getElement(i,j).getValue()));
						sb.append(",");
					}
					//sb.append(String.valueOf(tablero[i][j].getValue()));
					
				}
				sb.append("\n");
				pw.write(sb.toString());
			}
			pw.close();
		}
		catch (Exception e) {
			
		}
	}


}