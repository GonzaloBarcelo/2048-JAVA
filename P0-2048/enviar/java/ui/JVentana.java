package java.custom;

import java.custom.*;
import java.ui.*;
import java.ioY.*;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList; 

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;


import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Container;



import java.awt.*;
import javax.swing.*;



public class JVentana extends JFrame implements KeyListener,Runnable{
	private JButton btnEmpezar;
	private static HashMap<Integer,Color> colores= new HashMap<Integer,Color>();

	private static Color[] escala_azul={new Color(200,234,245),new Color(158,212,230),new Color(86,145,216),new Color(38,181,200), new Color(11,231,240),new Color(14,238,215),new Color(20,234,181),new Color(28,230,145), new Color(28,230,100),new Color(66,228,100),new Color(155,228,66),new Color(255,255,153),new Color(255,213,153), new Color(225,108,108),new Color(225,108,158)};

	private int dimensionW_X;
	private int dimensionW_Y;
	private int dimensionT_X;
	private int dimensionT_Y;
	
	private JPanel panel;

	private Tablero tablero= new Tablero(this,4,4);

	String dimension="4x4";

	private JLabel lblRecord2;
	private JLabel lblActual2;
	private JLabel lblReinicio;

	private JButton btnReset;
	private static JVentana ventana;

	int temporizador;

	private Boolean stop=false;
	//private Tablero tablero= new Tablero();

	private JLabel lblCredits1;

	public static void main(String[] args) {

		ventana= new JVentana();
	}

	public static void print(String s){
		System.out.println(s);
	}
	public static void print(int i){
		System.out.print(i);
	}

	public JVentana(){

		
		super("2048");


		
		EscalasColor();

		Thread reloj=new Thread(this);
		reloj.start();

		tablero.setColores(colores);
		


		JPanel tab= new JPanel(new GridLayout(1,1));
		
		JLabel lblTitulo= new JLabel("2048",JLabel.CENTER);
		
		//Tratamiento de la etiqueta del titulo
		Font fuente= new Font("Agency FB",Font.BOLD,26);

		lblTitulo.setForeground(new Color(108,137,225));
		lblTitulo.setFont(fuente);
		

		JLabel lblActual= new JLabel("Actual: ");
		lblActual2= new JLabel(tablero.getMarcadorActual());
		JLabel lblRecord= new JLabel("Record: ");
		lblRecord2= new JLabel(tablero.getMarcadorRecord());
		


		tab.add(tablero);

		Font labelFont = lblTitulo.getFont();


		JPanel pnlNorte= new JPanel(new GridLayout(2,1));
		JPanel pnlmarcador=new JPanel(new FlowLayout());
		JPanel pnlSur= new JPanel(new GridLayout(2,1));
		JPanel pnlSur1= new JPanel(new FlowLayout());	
		JPanel pnlSur2= new JPanel(new GridLayout(1,3));

		pnlmarcador.add(lblActual);
		pnlmarcador.add(lblActual2);
		pnlmarcador.add(lblRecord);
		pnlmarcador.add(lblRecord2);
		


		pnlNorte.add(lblTitulo);
		pnlNorte.add(pnlmarcador);


		lblReinicio= new JLabel("",JLabel.CENTER);
		//JLabel lblCredits1 = new JLabel("Created by GBA");
		lblCredits1 = new JLabel(CalcularTiempo(temporizador));
		
		JLabel lblCredits2 = new JLabel("© All rights reserved",JLabel.RIGHT);
		pnlSur2.add(lblCredits1);

		lblReinicio= new JLabel("",JLabel.CENTER);
		pnlSur2.add(lblReinicio);
		pnlSur2.add(lblCredits2);


		

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(this);

		btnReset= new JButton("Reset/Resize");
		btnReset.setFocusable(false);
		pnlSur1.add(btnReset);
		pnlSur.add(pnlSur1);
		pnlSur.add(pnlSur2);
        this.setLayout(new BorderLayout()); //JFrame
        this.add(pnlNorte,BorderLayout.NORTH);
        this.add(tab, BorderLayout.CENTER);
        this.add(pnlSur,BorderLayout.SOUTH);
    	
    	btnReset.addActionListener(new ActionListener(){
    			@Override
    			public void actionPerformed(ActionEvent e){
    				stop=true;
    				Object[] possibleValues = { "2x2", "3x3", "4x4" ,"5x5","6x6","7x7","8x8","9x9"};

 					dimension = (String)JOptionPane.showInputDialog(null,
            						"Choose one", "Reset/Resize",
             						JOptionPane.INFORMATION_MESSAGE, null,
             						possibleValues, dimension);
 					if (dimension!=null){
 						if (dimension.equals("3x3")){
 							tablero.resize(3);
 							tablero.repaint();
 						}
 						else if (dimension.equals("2x2")){
 							tablero.resize(2);
 							tablero.repaint();
 						}	 
 							
 						else if (dimension.equals("4x4")){
 							tablero.resize(4);
 							tablero.repaint();
 						}
 						else if (dimension.equals("5x5")){
 							tablero.resize(5);
 							tablero.repaint();
 						}
 						else if (dimension.equals("6x6")){
 							tablero.resize(6);
 							tablero.repaint();
 						}	 
 							
 						else if (dimension.equals("7x7")){
 							tablero.resize(7);
 							tablero.repaint();
 						}
 						else if (dimension.equals("8x8")){
 							tablero.resize(8);
 							tablero.repaint();
 						}
 						else if (dimension.equals("9x9")){
 							tablero.resize(9);
 							tablero.repaint();
 						}
 						stop=false;
 						temporizador=0;
						lblCredits1.setText(String.valueOf(temporizador));
 						btnReset.setVisible(false);
 						lblActual2.setText("0");
 						setSize(getDimensionX(tablero)*100,getDimensionY(tablero)*100+50);
 						Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
						setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
 					}
    			}
    	});

    	this.addWindowListener(new WindowAdapter(){
    		@Override
    		public void windowClosing(WindowEvent e){
    			//Object [] possibleValues={"Guardar y salir","Salir sin guardar","Cancelar"};
    			//int i=JOptionPane.showOptionDialog(null, possibleValues,"¿Desea guardar el record actual?",JOptionPane.DEFAULT_OPTION);
    			
    			Object[] possibleValues = { "Guardar y salir", "Salir sin guardar", "Cancelar" };
				Object selectedValue = JOptionPane.showInputDialog(null,
         		"Choose one", "Salir",
         		JOptionPane.INFORMATION_MESSAGE, null,
         		possibleValues, possibleValues[0]);

         		if (selectedValue.equals("Guardar y salir")){
         			IORecord.writeRecord(Integer.valueOf(tablero.getMarcadorRecord()));
                    System.exit(0);
         		}
         		else if (selectedValue.equals("Salir sin guardar")){
         			System.exit(0);
         		}
    		}
    	});

    	this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		//this.pack();
    	this.setIconImage (new ImageIcon("loren.ico").getImage());
		this.setMinimumSize(new Dimension(400,400));
		this.setSize(getDimensionX(tablero)*100,getDimensionY(tablero)*100+50);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		getContentPane().setBackground(new Color(158,212,230));
		//btnReset.setVisible(false);
		this.setVisible(true);

	}


	@Override
	public void keyPressed(KeyEvent d){

		if (d.getKeyChar()=='2' && tablero.isFinish()==false){
			btnReset.setVisible(true);
			if (tablero.posibilidadMovimiento("Abajo")){
				//System.out.println("Existe posibilidad de movimiento");
				tablero.abajo();
				tablero.insertarEnCasillaVacia();
				tablero.repaint();
			}
			//tablero.printTablero();
			//System.out.println("---------------");
		}
		else if(d.getKeyChar()=='4'&& tablero.isFinish()==false){
			btnReset.setVisible(true);

			if (tablero.posibilidadMovimiento("Izquierda")){
				//System.out.println("Existe posibilidad de movimiento");
				tablero.izquierda();
				tablero.insertarEnCasillaVacia();
				tablero.repaint();
			}
			//tablero.printTablero();
			//System.out.println("---------------");
		}
		else if(d.getKeyChar()=='8'&& tablero.isFinish()==false){
			btnReset.setVisible(true);
			if (tablero.posibilidadMovimiento("Arriba")){
				//System.out.println("Existe posibilidad de movimiento");
				tablero.arriba();
				tablero.insertarEnCasillaVacia();
				tablero.repaint();
			}
			//tablero.printTablero();
			//System.out.println("---------------");
		}
		else if(d.getKeyChar()=='6'&& tablero.isFinish()==false){
			btnReset.setVisible(true);
			if (tablero.posibilidadMovimiento("Derecha")){
				//System.out.println("Existe posibilidad de movimiento");
				tablero.derecha();
				tablero.insertarEnCasillaVacia();
				tablero.repaint();
			}
			//tablero.printTablero();
			//System.out.println("---------------");
		}
		else if(d.getKeyCode()== KeyEvent.VK_UP && tablero.isFinish()==false){
			btnReset.setVisible(true);
			if (tablero.posibilidadMovimiento("Arriba")){
				//System.out.println("Existe posibilidad de movimiento");
				tablero.arriba();
				tablero.insertarEnCasillaVacia();
				tablero.repaint();
			}
			//tablero.printTablero();
			//System.out.println("---------------");
		}
		else if(d.getKeyCode()== KeyEvent.VK_DOWN && tablero.isFinish()==false){
			btnReset.setVisible(true);
			if (tablero.posibilidadMovimiento("Abajo")){
				//System.out.println("Existe posibilidad de movimiento");
				tablero.abajo();
				tablero.insertarEnCasillaVacia();
				tablero.repaint();
			}
			//tablero.printTablero();
			//System.out.println("---------------");
		}
		else if(d.getKeyCode()== KeyEvent.VK_RIGHT && tablero.isFinish()==false){
			btnReset.setVisible(true);
			if (tablero.posibilidadMovimiento("Derecha")){
				//System.out.println("Existe posibilidad de movimiento");
				tablero.derecha();
				tablero.insertarEnCasillaVacia();
				tablero.repaint();
			}
			//tablero.printTablero();
			//System.out.println("---------------");
		}
		else if(d.getKeyCode()== KeyEvent.VK_LEFT && tablero.isFinish()==false){
			btnReset.setVisible(true);
			if (tablero.posibilidadMovimiento("Izquierda")){
				//System.out.println("Existe posibilidad de movimiento");
				tablero.izquierda();
				tablero.insertarEnCasillaVacia();
				tablero.repaint();
			}
			//tablero.printTablero();
			//System.out.println("---------------");
		}

		if (tablero.isFinish()){
			stop=true;
			lblReinicio.setText("Fin de partida, pulsa enter para volver a empezar.");
			
		}

		if(tablero.isFinish()&&d.getKeyCode()==KeyEvent.VK_ENTER){
			tablero.resetTablero();
			stop=false;
			temporizador=0;
			tablero.repaint();
			lblReinicio.setText("");
			btnReset.setVisible(false);
		}

		
		lblActual2.setText(tablero.getMarcadorActual());
		lblRecord2.setText(tablero.getMarcadorRecord());

				
			}
	@Override
		public void keyReleased(KeyEvent d){
	}
	@Override
		public void keyTyped(KeyEvent d){
	
	}


	public static ArrayList<Integer> getRand(){

		ArrayList<Integer> numeros = new ArrayList<Integer>();

		numeros.add(255);
		numeros.add(55);
		numeros.add(104);
		numeros.add(153);
		numeros.add(204);
		numeros.add(51);


		Random rand= new Random();

		ArrayList<Integer> devolver = new ArrayList<Integer>();
		devolver.add(numeros.get(rand.nextInt(numeros.size())).intValue());
		devolver.add(numeros.get(rand.nextInt(numeros.size())).intValue());
		devolver.add(numeros.get(rand.nextInt(numeros.size())).intValue());
		
		return devolver;
	}

	public static void CrearListaColor(){
		
		int j=0;

		for (int i=2;i<4096;i=i*2){
			j=j+1;

			Integer temp= Integer.valueOf(i);
			ArrayList<Integer> valores= getRand();
			Color col= new Color(valores.get(0).intValue(),valores.get(1).intValue(),valores.get(2).intValue());
			colores.put(temp,col);

		}
	}

	public static void EscalasColor(){
		int j=0;
		for (int i=2;i<=4096;i=i*2){
			colores.put(i,escala_azul[j]);
			j=j+1;
		}
		
	}
	

	public int getDimensionX(Tablero tablero){
		JNumero[][] matriz= tablero.getTablero();
		int dimensionX=matriz.length;
		int dimensionY=matriz[0].length;
		return dimensionX;
	}

	public int getDimensionY(Tablero tablero){
		JNumero[][] matriz = tablero.getTablero();
		int dimensionX=matriz.length;
		int dimensionY=matriz[0].length;
		return dimensionY;
	}

	public void pintarTablero(){

		tablero.repaint();
	}

	public void resize(int dimension){
		tablero.resetTablero();
		tablero=new Tablero(this,dimension,dimension);
		tablero.repaint();
		lblReinicio.setText("");
	}
	@Override
	public void run()
		{
			while(true)
			{
				try{
					Thread.sleep(1000);
					if (stop){
						temporizador=temporizador;
					}
					else{
						temporizador+=1;
					}
					lblCredits1.setText(String.valueOf(CalcularTiempo(temporizador)));
				}
				catch(Exception e){

				}
				
			}		
		}
	private String CalcularTiempo(int tsegundos)
{
    int horas = (tsegundos / 3600);
    int minutos = ((tsegundos-horas*3600)/60);
    int segundos = tsegundos-(horas*3600+minutos*60);
    return Recap(horas) + ":" + Recap(minutos) + ":" + Recap(segundos);
}
	private String Recap(int numero)
	{
		if (numero>=10){
			return String.valueOf(numero);
		}
		else{
			return "0"+String.valueOf(numero);
		}
	}

} 

