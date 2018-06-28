import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame implements ActionListener{
	JPanel p=new JPanel();
	static JPanel pan=new JPanel();
	GridBagConstraints gr=new GridBagConstraints();
	 static XOButton buttons[][]=new XOButton[18][18];
	 JButton button=new JButton("nouvelle ");
	 static  String[] names={"a","b","c","d","e","f","g","h","i"};
	 static  personnage player1,player2,player3,player4;
	 static personnage tour;
	 static int nbrPlayer;
	static JLabel l=new JLabel();
	static JLabel l1=new JLabel();
	static JLabel l2=new JLabel();
	 public static void main(String args[]){
		
		
	  //  personnage p3=new personnage("a4","y",Color.blue);
	    
	  
	}
	 private void makeGame(){
		  this.setSize(700,500);
			this.setResizable(false);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setLayout(new BorderLayout(3,3));
			JToolBar tools = new JToolBar();
       		tools.setFloatable(false);
       		this.add(tools, BorderLayout.PAGE_START);
			p.setLayout(new GridBagLayout());
			 tools.add(button);
			 button.addActionListener(this);
			creat();
			add(p,BorderLayout.CENTER);
			naming();
			setVisible(true);
			
			
	 }
	
	public Game(personnage p1,personnage p2){
		super("quoridor");
		makeGame();
		
		this.player1=p1;
		this.player2=p2;
		this.player3=null;
		this.player4=null;
		dessinePersonnage(this.player1);
		dessinePersonnage(this.player2);
		tour=this.player1;
		this.nbrPlayer=2;
		l.setText(player1.nom+" couleur rouge  "+player2.nom+ " couleur verte");
		pan.setLayout(new BorderLayout());
		pan.add(l,BorderLayout.CENTER);
		this.add(pan,BorderLayout.SOUTH);
		l1.setText(" c'est le tour de "+this.tour.nom);
		pan.add(l1,BorderLayout.NORTH);
		l2.setText(player1.nom+"a "+player1.walls+" murets restantes "+player2.nom+" a "+player2.walls+" murets restants" );
		pan.add(l2,BorderLayout.SOUTH);
		nbrPlayer=2;
		
		
	}
	
	Game(personnage player1, personnage player2,personnage player3,personnage player4){
		super("quoridor");	
		makeGame();
		
		this.player1=player1;
		this.player2=player2;
		this.player3=player3;
		this.player4=player4;
		dessinePersonnage(this.player1);
		dessinePersonnage(this.player2);
		dessinePersonnage(this.player3);
		dessinePersonnage(this.player4);
	
		tour=this.player1;
		this.nbrPlayer=4;
		pan.setLayout(new BorderLayout());
		l.setText(this.player1.nom+" couleur rouge  "+this.player2.nom+ " couleur verte  "+this.player3.nom+" couleur bleu  "+this.player4.nom
		+" couleur orange"
		);
		pan.add(l);
		this.add(pan,BorderLayout.SOUTH);
		l1.setText(" c'est le tour de "+this.tour.nom);
		pan.add(l1,BorderLayout.NORTH);
		l2.setText(player1.nom+"a "+player1.walls+" murets restants "+player2.nom+" a "+player2.walls+" murets restants " 
		+player3.nom+" a"+player3.walls+" murets restants "+player4.nom+" a "+player4.walls+" murets restant");
		pan.add(l2,BorderLayout.SOUTH);
		nbrPlayer=4;
	}
	
	
	//la methode qui cree la grille 
	public void creat(){
		int counter=1;
		int counter2=0;
		int co=0;
		int count=1;
		boolean bool=false;
		for(int i=1;i<buttons.length;i++){
			for(int j=1;j<buttons.length;j++){
				XOButton b=new XOButton(p);
				//b.setPreferredSize(new Dimension(7, 7));
				gr.gridx=i;
				gr.gridy=j;
				if(i%2==0 && j%2!=0 ){
					 b.setPreferredSize(new Dimension(12,25));
					 b.setBackground(Color.decode("#EEEEEE"));
					 b.setName(""+counter);
					 counter+=2;
					
					 bool=true;
				}
				else if(j%2==0 && i%2!=0 ){
					 b.setPreferredSize(new Dimension(25,12));
					 b.setBackground(Color.decode("#EEEEEE"));
					 b.setName(""+counter);
					 counter+=2;
					 bool=true;
					
				}
				else{
					
			     b.setPreferredSize(new Dimension(25,25));
			     b.setBackground(Color.white);
			     b.setName("a2");
			    
			   
				}
				if(bool){
					b.setOpaque(true);
					b.setContentAreaFilled(true);
					b.setBorderPainted(false);
					bool=false;
				}
				
				 if(i%2==0 && j%2==0){
					 b.setPreferredSize(new Dimension(12,12));
					 b.setBackground(Color.decode("#EEEEEE"));
					// b.setName("b3");
					 b.setOpaque(true);
					b.setContentAreaFilled(false);
					b.setBorderPainted(false);
					
					
				}
				
				
				buttons[i][j]=b;
				
				p.add(buttons[i][j],gr);
				
				
			}
			counter++;
			
			
		
		}
		
	}
	 public void naming(){
		int co=0,counter=1;
		for(int i=1;i<this.buttons.length;i+=2){
			for(int j=1;j<this.buttons.length;j+=2){
			
				this.buttons[i][j].setName(names[co]+counter);
				counter++;
			
				
			}
			counter=1;
			co++;
		}
	}
	public void  dessinePersonnage( personnage player1){
		for(int i=1;i<this.buttons.length;i++){
			for(int j=1;j<this.buttons.length;j++){
				if(player1.position.equals(this.buttons[i][j].getName())){
					this.buttons[i][j].setBackground(player1.couleur);
					
					return;
				}
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		testGame test=new testGame();
		
		 String t=JOptionPane.showInputDialog("entrer le nombre de joueurs 2/4");
		 test.createGame(t);
		 this.dispose();
		
	}
	
}