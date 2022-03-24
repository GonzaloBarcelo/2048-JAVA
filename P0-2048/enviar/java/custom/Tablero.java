package java.custom;

import java.custom.*;
import java.ui.*;
import java.ioY.*;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Canvas;
import java.util.HashMap;
import java.awt.Font;

public class Tablero  extends Canvas{

	/*final static*/ public int dimensionX=3;
	/*final static*/ public int dimensionY=3;

	private JVentana ventana;
	int dimensionX_window;
	int dimensionY_window;

	HashMap<Integer,Color> color;

	int k;

	public JNumero[][] tablero= new JNumero[dimensionX][dimensionY];
	Random rand= new Random();
	Marcador marcador= new Marcador();

/*
	public Tablero(JVentana ventana){

		this.ventana=ventana;
		
		for (int i=0;i<dimensionX;i++){
			for (int j=0;j<dimensionY;j++){
				tablero[i][j]= new JNumero(0);
			}
		}
		insertarEnCasillaVacia();
		insertarEnCasillaVacia();
		//printTablero();
		
	}

*/
	public Tablero(JVentana ventana, int dimensionX, int dimensionY){
		this.ventana=ventana;
		this.dimensionX=dimensionX;
		this.dimensionY=dimensionY;

		marcador.setRecord(IORecord.leerRecord());

		this.tablero= new JNumero[dimensionX][dimensionY];
		for (int i=0;i<this.dimensionX;i++){
			for (int j=0;j<this.dimensionY;j++){
				tablero[i][j]= new JNumero(0);
			}
		}
		insertarEnCasillaVacia();
		insertarEnCasillaVacia();
	}
/*
	public Tablero(int dimensionX, int dimensionY){

		
		this.dimensionX=dimensionX;
		this.dimensionY=dimensionY;

		this.tablero= new JNumero[dimensionX][dimensionY];
		for (int i=0;i<this.dimensionX;i++){
			for (int j=0;j<this.dimensionY;j++){
				tablero[i][j]= new JNumero(0);
			}
		}
		insertarEnCasillaVacia();
		insertarEnCasillaVacia();
	}

	*/
	//mostrar en consola
	public Tablero(int a){
		tablero[0][0]=new JNumero(2);
		tablero[0][1]=new JNumero(8);
		tablero[0][2]=new JNumero(2);
		tablero[1][0]=new JNumero(4);
		tablero[1][1]=new JNumero(32);
		tablero[1][2]=new JNumero(64);
		tablero[2][0]=new JNumero(2);
		tablero[2][1]=new JNumero(4);
		tablero[2][2]=new JNumero(4);
	}

	public void resize(int dimension){
		this.dimensionX=dimension;
		this.dimensionY=dimension;
		
		this.tablero= new JNumero[dimensionX][dimensionY];
		for (int i=0;i<this.dimensionX;i++){
			for (int j=0;j<this.dimensionY;j++){
				tablero[i][j]= new JNumero(0);
			}
		}
		insertarEnCasillaVacia();
		insertarEnCasillaVacia();

	}


	public Boolean isFinish(){
		for (int i=0;i<dimensionX;i++){
			for (int j=0;j<dimensionY;j++){
				if(tablero[i][j].getValue()==0){
					return false;
				}
				//Para las esquinas
				else if (i==0 && j==0){
					if(revisaDer(i,j) || revisaAbajo(i,j)){
						return false;
					}
				}
				else if(i==dimensionX-1 && j==0){
					if (revisaDer(i,j) || revisaArriba(i,j)){
						return false;
					}
				}
				else if(i==0 && j==dimensionY-1){
					if(revisaIzq(i,j) || revisaAbajo(i,j)){
						return false;
					}
				}

				else if(i==dimensionX-1 && j==dimensionY-1){
					if(revisaIzq(i,j) || revisaArriba(i,j)){
						return false;
					}
				}
				
				//Para los bordes superiores
				else if(i==0){
					if(revisaAbajo(i,j) || revisaIzq(i,j) || revisaDer(i,j)){
						return false;
					}

				}
				else if(i==dimensionX){
					if(revisaArriba(i,j) || revisaIzq(i,j) || revisaDer(i,j)){
						return false;
					}
				}
				else if(j==0){
					if(revisaIzq(i,j) || revisaArriba(i,j) || revisaAbajo(i,j)){
						return false;
					}
				}
				else if(j==dimensionY){
					if(revisaDer(i,j) || revisaArriba(i,j) || revisaAbajo(i,j)){
						return false;
					}
				}
				else{
					if(revisaDer(i,j) || revisaIzq(i,j) || revisaArriba(i,j) || revisaAbajo(i,j)){
						return false;
					}
				}
				
			}
		}
		return true;
	}

	public JNumero getElement(int i,int j){
		try{
			return tablero[i][j];
		}
		catch(Exception e){
			return null;
		}
	}
	//devuelve true cuando sigue existiendo una posibilidad de movimiento
	public Boolean revisaDer(int i, int j){
		JNumero original= tablero[i][j];
		for (int a=j+1;a<dimensionY;a++){
			if(tablero[i][a].getValue()==0){
				return true;
			}
			else if (original.getValue()==tablero[i][a].getValue()){
				return true;
			}
			else{
				return false;
			}
		}
		
		return false;
	}
	public Boolean revisaIzq(int i, int j){
		JNumero original= tablero[i][j];
		for (int a=j-1;a>=0;a--){
			if(tablero[i][a].getValue()==0){
				return true;
			}
			else if (original.getValue()==tablero[i][a].getValue()){

				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}

	public Boolean revisaAbajo(int i, int j){
		JNumero original= tablero[i][j];
		for (int a=i+1;a<dimensionX;a++){
			if(tablero[a][j].getValue()==0){
				return true;
			}
			else if (original.getValue()==tablero[a][j].getValue()){
				
				return true;
			}
			else{
				return false;
			}
		}

		return false;
	}

	public Boolean revisaArriba(int i,int j){
		JNumero original= tablero[i][j];
		for (int a=i-1;a>=0;a--){
			if(tablero[a][j].getValue()==0){
				return true;
			}
			else if (original.getValue()==tablero[a][j].getValue()){
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}
	
	public void insertarEnCasillaVacia(){
		LinkedList<Coords> casillas_vacias= new LinkedList<Coords>();
		for (int i=0;i<dimensionX;i++){
			for (int j=0;j<dimensionY;j++){
				if (tablero[i][j].getValue()==0){
					casillas_vacias.add(new Coords(i,j));
				}
			}
		}
		if (casillas_vacias.size()!=0){
			Coords casilla = casillas_vacias.get(rand.nextInt(casillas_vacias.size()));
			tablero[casilla.getX()][casilla.getY()]= new JNumero(2);
		}
	}
	public void setColores(HashMap<Integer,Color> colores){
		this.color=colores;
	}

	public void arriba(){
		for (int j=0;j<dimensionY;j++){
			for (int i=0;i<dimensionX;i++){

				if (i!=dimensionX-1 && tablero[i][j].getValue()!=0){
						for (int k=i+1;k<dimensionX;k++){
							if (tablero[k][j].getValue()!=0 && tablero[k][j].getValue()!=tablero[i][j].getValue()){
								k=dimensionX;
							}
							else if(tablero[k][j].getValue()!=0 && tablero[k][j].getValue()==tablero[i][j].getValue()){
								
								int temp1=tablero[k][j].getValue();
								int temp2=tablero[i][j].getValue();
								tablero[i][j]=new JNumero(temp1+temp2);
								tablero[k][j]=new JNumero(0);
								k=dimensionX;

								marcador.actualizar(temp1+temp2);
							}
						}
				}
			}
		}
		//Desplazamiento hacia arriba
		for (int j=0;j<dimensionY;j++){
			for (int i=0;i<dimensionX;i++){
				if(tablero[i][j].getValue()==0 && i!=dimensionX-1){
					for (k=i+1;k<dimensionX;k++){
						if(tablero[k][j].getValue()!=0){
							tablero[i][j]=tablero[k][j];
							tablero[k][j]=new JNumero(0);
							k=dimensionX;
						}
					}
				}
			}
		}
		//Calculamos como quedaria la matriz despues de sumar todos los numeros que tocan
	}

	public void abajo(){
		for (int j=0;j<dimensionY;j++){
			for (int i=dimensionX-1;i>=0;i--){
				//De abajo a arriba
				if (i!=0 && tablero[i][j].getValue()!=0){
					//Confirmamos que la casilla por la que pasamos no esta a cero ni forma parte dela ultima fila
						
						for (int k=i-1;k>=0;k--){
							//Cogemos el valor de la casilla de arriba
							if (tablero[k][j].getValue()!=0 && tablero[k][j].getValue()!=tablero[i][j].getValue()){
								k=-1;
							}
							else if(tablero[k][j].getValue()!=0 && tablero[k][j].getValue()==tablero[i][j].getValue()){
								
								int temp1=tablero[i][j].getValue();
								int temp2=tablero[k][j].getValue();
								tablero[i][j]=new JNumero(temp1+temp2);
								tablero[k][j]=new JNumero(0);
								k=-1;
								marcador.actualizar(temp1+temp2);
							}
						}
				}
			}
		}
		//Desplazamiento hacia abajo
		for (int j=0;j<dimensionY;j++){
			for (int i=dimensionX-1;i>=0;i--){
				if(tablero[i][j].getValue()==0 && i!=0){
					for (int k=i-1;k>=0;k--){
						if(tablero[k][j].getValue()!=0){
							tablero[i][j]=tablero[k][j];
							tablero[k][j]=new JNumero(0);
							k=-1;
						}
					}
				}
			}
		}
	}
	public void izquierda(){
		for (int i=0;i<dimensionX;i++){
			for (int j=0;j<dimensionY;j++){
				//De abajo a arriba
				if (j!=dimensionY-1 && tablero[i][j].getValue()!=0){
					//Confirmamos que la casilla por la que pasamos no esta a cero ni forma parte dela ultima fila
						
						for (int k=j+1;k<dimensionY;k++){
							//Cogemos el valor de la casilla de arriba
							if (tablero[i][k].getValue()!=0 && tablero[i][k].getValue()!=tablero[i][j].getValue()){
								k=dimensionY;
							}
							else if(tablero[i][k].getValue()!=0 && tablero[i][k].getValue()==tablero[i][j].getValue()){
								
								int temp1=tablero[i][j].getValue();
								int temp2=tablero[i][k].getValue();
								tablero[i][j]=new JNumero(temp1+temp2);
								tablero[i][k]=new JNumero(0);
								k=dimensionY;
								marcador.actualizar(temp1+temp2);
							}
						}
				}
			}
		}
		//Desplazamiento hacia izquierda
		for (int i=0;i<dimensionX;i++){
			for (int j=0;j<dimensionY;j++){
				if(tablero[i][j].getValue()==0 && j!=dimensionY-1){
					for (k=j+1;k<dimensionY;k++){
						if(tablero[i][k].getValue()!=0){
							tablero[i][j]=tablero[i][k];
							tablero[i][k]=new JNumero(0);
							k=dimensionY;
				
						}
					}
				}
			}
		}
	}

	public void derecha(){
		for (int i=0;i<dimensionX;i++){
			for (int j=dimensionY-1;j>=0;j--){
				//De abajo a arriba
				if (j!=0 && tablero[i][j].getValue()!=0){
					//Confirmamos que la casilla por la que pasamos no esta a cero ni forma parte dela ultima fila
						
						for (int k=j-1;k>=0;k--){
							//Cogemos el valor de la casilla de arriba
							if (tablero[i][k].getValue()!=0 && tablero[i][k].getValue()!=tablero[i][j].getValue()){
								k=-1;
							}
							else if(tablero[i][k].getValue()!=0 && tablero[i][k].getValue()==tablero[i][j].getValue()){
								
								int temp1=tablero[i][j].getValue();
								int temp2=tablero[i][k].getValue();
								tablero[i][j]=new JNumero(temp1+temp2);
								tablero[i][k]=new JNumero(0);
								k=-1;
								marcador.actualizar(temp1+temp2);
							}
						}
				}
			}
		}
		//Desplazamiento hacia izquierda
		for (int i=0;i<dimensionX;i++){
			for (int j=dimensionY-1;j>=0;j--){
				if(tablero[i][j].getValue()==0 && j!=0){
					for (k=j-1;k>=0;k--){
						if(tablero[i][k].getValue()!=0){
							tablero[i][j]=tablero[i][k];
							tablero[i][k]=new JNumero(0);
							k=-1;
						}
					}
				}
			}
		}
	}

	//Metodos para la consola
	public void printTablero(){
		for (int i=0;i<dimensionX;i++){
			for(int j=0;j<dimensionY;j++){
				System.out.print(tablero[i][j].getValue());
				System.out.print("  ");
			}
			System.out.println("\n");
		}
	}
	public void print(String s){
		System.out.println(s);
	}
	public void print(int i){
		System.out.print(i);
	}

	public JNumero[][] getTablero(){
		return tablero;
	}

	public String getMarcadorActual(){
		return ""+marcador.getActual();
	}

	public String getMarcadorRecord(){
		return ""+marcador.getRecord();
	}


	public void paint(Graphics g){

		getWSize(ventana);
		//System.out.println(dimensionX_window);
		//System.out.println(dimensionY_window);
		
		
		Graphics2D g2= (Graphics2D) g;
		g2.setColor( Color.gray );

        g2.fillRoundRect( dimensionX_window/2 - (4+ dimensionY*(62))/2, 0,4+ dimensionY*(62),4+ dimensionX*(62), 15, 15);
		
        //g2.fillRect( dimensionX_window/2 - (4+ dimensionX*(62))/2, 0,4+ dimensionX*(62),4+ dimensionY*(62));
		for(int i=0;i<dimensionX;i++){
			for (int j=0;j<dimensionY;j++){
				//dibuja(g,tablero[i][j], j * 60 + 150, i * 60 + 60 );
				dibuja(g,tablero[i][j], dimensionX_window/2 - (4+ dimensionX*(62))/2+j * 60+12, i * 60+12);
			}
		}


	}
	public void dibuja(Graphics g,JNumero numero,int x,int y){
		Graphics2D g2 = (Graphics2D)g;
		if (numero.getValue()!=0){
			//pintar el cuadrado
			if (color.get(Integer.valueOf(numero.getValue())).equals(new Color(0,0,0))){

				Color randomColor = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
			}
			g2.setColor(color.get(Integer.valueOf(numero.getValue())));
			g2.fillRoundRect( x, y, 50, 50, 15, 15 );
			//g2.fillOval( x, y, 50, 50 );
			
			//pintar el numero
            g2.setColor( Color.black );
            Font fuente= new Font("TimesRoman",Font.BOLD,12);
            g.setFont(fuente);
            g.drawString( "" + numero.getValue(), x + 25 - 3 *String.valueOf( numero.getValue() ).length() , y + 28 );

		}
		else{
			g2.setColor(new Color(Color.gray.getRed()+2,Color.gray.getGreen()+2,Color.gray.getBlue()+2));
			//g2.fillOval(x,y,50,50);
			g2.fillRoundRect(x,y,50,50,5,5);
		}
	}

	public void addColor(Integer i,Color col){
		color.put(i,col);
	}

	public void getWSize(JVentana ventana){
		this.dimensionX_window=ventana.getContentPane().getWidth();
		this.dimensionY_window=ventana.getContentPane().getHeight();
	}

	
	public void resetTablero(){
		marcador.resetActual();
		for (int i=0;i<dimensionX;i++){
			for (int j=0;j<dimensionY;j++){
				tablero[i][j]= new JNumero(0);
			}
		}
		insertarEnCasillaVacia();
		insertarEnCasillaVacia();
	}

	public boolean posibilidadMovimiento(String s){

		if (s.equals("Arriba")){
			for (int i=0; i<dimensionX;i++){
				for (int j=0; j<dimensionY;j++){
					if(i!=0){
						if(tablero[i][j].getValue()!=0){
							if(tablero[i-1][j].getValue()==0||tablero[i-1][j].getValue()==tablero[i][j].getValue()){
								return true;
							}
						}
					}
				}
			}
		}
		else if (s.equals("Abajo")){
			for (int i=0; i<dimensionX;i++){
				for (int j=0; j<dimensionY;j++){
					if(i!=dimensionX-1){
						if(tablero[i][j].getValue()!=0){
							//System.out.println("Se esta verificando los valores de (Arriba) "+tablero[i-1][j].toString()+ "y la casilla "+tablero[i][j].toString());
							if(tablero[i+1][j].getValue()==0||tablero[i+1][j].getValue()==tablero[i][j].getValue()){
								//System.out.println("---------");
								return true;
							}
						}
					}
				}
			}
		}
		else if (s.equals("Izquierda")){
			for (int i=0; i<dimensionX;i++){
				for (int j=0; j<dimensionY;j++){
					if(j!=0){
						if(tablero[i][j].getValue()!=0){
							//System.out.println("Se esta verificando los valores de (Arriba) "+tablero[i-1][j].toString()+ "y la casilla "+tablero[i][j].toString());
							if(tablero[i][j-1].getValue()==0||tablero[i][j-1].getValue()==tablero[i][j].getValue()){
								//System.out.println("---------");
								return true;
							}
						}
					}
				}
			}
		}
		else if (s.equals("Derecha")){
			for (int i=0; i<dimensionX;i++){
				for (int j=0; j<dimensionY;j++){
					if(j!=dimensionY-1){
						if(tablero[i][j].getValue()!=0){
							//System.out.println("Se esta verificando los valores de (Arriba) "+tablero[i-1][j].toString()+ "y la casilla "+tablero[i][j].toString());
							if(tablero[i][j+1].getValue()==0||tablero[i][j+1].getValue()==tablero[i][j].getValue()){
								//System.out.println("---------");
								return true;
							}
						}
					}
				}
			}
		}




		return false;

	}
}