import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class XOButton extends JButton implements ActionListener,MouseListener{
	
	String [] positions=new String[4];
	 static  char[] names={'a','b','c','d','e','f','g','h','i'};
	 JPanel p;
	public XOButton(JPanel p){
		super();
		this.addActionListener(this);
		this.addMouseListener (this);
		this.p=p;
	
	}	
	//cette methode permet de :
	//changer la couleur d'un muret en noir lorsque celui ci est cliqué
	//trouver les deplacements possible d'un personnage lorsque celui ci est cliqué
	public void actionPerformed(ActionEvent e){

		String name=this.getName();
		char firstChar=name.charAt(0);
		//verifier si le muret a etais cliqué 
		if(firstChar>=48 && firstChar<=57){
			
			int t=Integer.parseInt(name);
			//verifier si le personage possed encore des muret a mettre
			if(Game.tour.walls>=1){
				this.setBackground(Color.black);
				Game.tour.walls--;
				changerTour();
				eraseYellow();
				Game.l1.setText("c'est la tour de "+Game.tour.nom);
				Game.pan.add(Game.l1,BorderLayout.NORTH);
				if(Game.nbrPlayer==2){
					Game.l2.setText(Game.player1.nom+"a "+Game.player1.walls+" murets restantes "+Game.player2.nom+" a "+Game.player2.walls+" murets restants" );
					Game.pan.add(Game.l2,BorderLayout.SOUTH);
				}else{
					Game.l2.setText(Game.player1.nom+"a "+Game.player1.walls+" murets restants "+Game.player2.nom+" a "+Game.player2.walls+" murets restants " 
							+Game.player3.nom+" a"+Game.player3.walls+" murets restants "+Game.player4.nom+" a "+Game.player4.walls+" murets restant");
							Game.pan.add(Game.l2,BorderLayout.SOUTH);
				}
			}else{
				JOptionPane.showMessageDialog(this, "vous avez epuisés votre reserve de murets");
				return;
			}
			
			if(t%2==0){
			check(t,2,true);
			this.setEnabled(false);
			}
			else{
				check(t,1,true);
				this.setEnabled(false);
			}
			//cela veut dire que le personnage a etais clique est non pas un muret
		}else{
			//verifier que le button cliqué est le personnage
			
			if(this.getBackground()!=Color.white && this.getBackground()!=Color.yellow){
				
				if(Game.tour.position.equals(this.getName())){
					try{
						 int x = Character.getNumericValue(name.charAt(1));
						
							 
							 this.positions[0]=firstChar+""+(x-1);
						 
							
							this.positions[1]=firstChar+""+(x+1);
							for(int i=0;i<this.names.length;i++){
								if(firstChar==names[i]){
									this.positions[2]=names[i+1]+""+x;
									this.positions[3]=names[i-1]+""+x;	
								}
							}
							//appeler la methode qui change la couleur des positions possible en jaune
							turnOn();
							
					}catch(Exception f){
						try{
							
							if(firstChar=='a'){
								 int x = Character.getNumericValue(name.charAt(1));
								 this.positions[1]="b"+x;
								 this.positions[1]="a"+(x+1);
								 this.positions[0]="a"+(x-1);
								 //appeler la methode qui change la couleur des positions possible en jaune
								 turnOn();
							
						}else if(firstChar=='i'){
								 int x = Character.getNumericValue(name.charAt(1));
								 this.positions[0]="h"+x;
								 this.positions[3]="i"+(x-1);
								 turnOn();
							}
						}catch(Exception s){
							if(!Game.buttons[2][1].getBackground().equals(Color.black)){
								if(Game.buttons[3][1].getBackground().equals(Color.white)){
								
									Game.buttons[3][1].setBackground(Color.yellow);
								}else if(!Game.buttons[4][1].getBackground().equals(Color.black)){
									Game.buttons[5][1].setBackground(Color.yellow);
								}	
							}	
						}
					}
				}else{
					JOptionPane.showMessageDialog(this, "ce n'est pas votre tour");
					return;
				}
				//cela veut dire que le deplacement a etait cliqué 
				//donc on change la position du personnage a cette endroit la 
			}else if(this.getBackground()==Color.yellow){
				
				this.reset();
				Game.tour.position=this.getName();
				this.setBackground(Game.tour.couleur);
				
				this.eraseYellow();
				checkVictory();
				changerTour();
				Game.l1.setText("c'est la tour de "+Game.tour.nom);
				Game.pan.add(Game.l1,BorderLayout.NORTH);
				
			}		 
		}
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}
	@Override
	//la methode qui permet de transformer un muret en noire lorsque on le survole
	public void mouseEntered(MouseEvent e) {
		int t=0;
		String name=this.getName();
		char firstChar=name.charAt(0);
		//verifier si un muret a etait survolé
		if(firstChar>=48 && firstChar<=57){
			t=Integer.parseInt(name);
			
		}else{
			return;
		}
		
	if(!last(name)){
		if(t%2==0 && this.isEnabled()){
			this.setContentAreaFilled(true);
			this.setBackground(Color.black);
		
			check(t,2,false);
			
		}else if(t%2!=0 && this.isEnabled()){
			this.setContentAreaFilled(true);
			this.setBackground(Color.black);
			
			check(t,1,false);
			
		}
	}else{
		return;
	}
	}
	//la methode qui reset la couleur du muret que l'en vient de survoler
	@Override
	public void mouseExited(MouseEvent e) {
		int t=0;
		String name=this.getName();
		char firstChar=name.charAt(0);
		
		if(firstChar>=48 && firstChar<=57){
			 t=Integer.parseInt(name);
		}else{
			return;
		}
		if(!last(name)){
			if(t%2==0 && this.isEnabled()){
				delete(t,2,true);	
			}
			else if(t%2!=0 && this.isEnabled()){
				delete(t,1,true);
			}
			
			if(this.isEnabled()){
				this.setBackground(Color.decode("#EEEEEE"));
				this.setOpaque(true);
				this.setContentAreaFilled(false);
				this.setBorderPainted(false);
				
			}
			else{
				return;
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent arg0){
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
	//fonction qui permet de changer la couleur du muret adjacent
	public void check(int t,int n,Boolean bool){
		if(n==1){
			t+=36;
		}else{
			t+=2;
		}
		for(int i=1;i<Game.buttons.length;i++){
			for(int j=1;j<Game.buttons.length;j++){
				
					if(Game.buttons[i][j].getName().equals(""+t)){
						if(bool){
							Game.buttons[i][j].setContentAreaFilled(true);
							Game.buttons[i][j].setBackground(Color.black);
							Game.buttons[i][j].setEnabled(false);
						}else{
							Game.buttons[i][j].setContentAreaFilled(true);
							Game.buttons[i][j].setBackground(Color.black);
						}
				}		
			}
		}
	}
	//methode qui permet de rendre le muret adjacent a sa couleur normal
	public void delete(int t,int n,Boolean bool){
		if(n==1){
			t+=36;
		}else{
			t+=2;
		}
		for(int i=1;i<Game.buttons.length;i++){
			for(int j=1;j<Game.buttons.length;j++){
				
				if(Game.buttons[i][j].getName().equals(""+t)){
					if(Game.buttons[i][j].isEnabled()){
						if(bool){
							Game.buttons[i][j].setBackground(Color.decode("#EEEEEE"));
							Game.buttons[i][j].setOpaque(true);
							Game.buttons[i][j].setContentAreaFilled(true);
							Game.buttons[i][j].setBorderPainted(false);
						}else{
							return;
						}	
					}	
				}
			}
		}
	}
	
	//la methode verifie si le button sur lequel on a survolé n'est pas le dernier
	//parceque si c'est le cas ce muret ne doit pas changer de couleur
	public Boolean last(String name){
		Boolean b=false;
		
		for(int i=1;i<Game.buttons.length;i++){
			for(int j=1;j<Game.buttons.length;j++){
				if(name.equals(Game.buttons[i][j].getName())){
					if(i==Game.buttons.length-1 || j==Game.buttons.length-1){
						b=true;
						return b;
					}
				}
		}	
	}
		
		return b;
}//end of last methode
	
	//la methode qui permet de changer tout les deplacements possible en jaune
	public void turnOn(){
		for(int i=1;i<Game.buttons.length;i++){
			for(int j=1;j<Game.buttons.length;j++){
				if(Game.buttons[i][j].getName().equals(positions[0]) ||Game.buttons[i][j].getName().equals(positions[1]) ||
						Game.buttons[i][j].getName().equals(positions[2]) || Game.buttons[i][j].getName().equals(positions[3]))
						{
					
					if(Game.buttons[i][j].getBackground().equals(Color.white)){
						jump(Game.buttons[i][j],i,j);
						//Game.buttons[i][j].setBackground(Color.yellow);
					}else if( !Game.buttons[i][j].getBackground().equals(Color.yellow)){
						
						jump(Game.buttons[i][j],i,j);
					}
				}
			}
		}
	}//end of turnOn

	//cetter methode remet la couleur des deplacements en blanc
	//apres chaque deplacement effectuer
	public void eraseYellow(){
		for(int i=1;i<Game.buttons.length;i++){
			for(int j=1;j<Game.buttons.length;j++){
				if(Game.buttons[i][j].getBackground().equals(Color.yellow)){
					Game.buttons[i][j].setBackground(Color.white);
				}
			}
		}
	}//end of eraseYellow
	
	//methode qui fait le reset de la postion en changant le background de la case en blanc
	public void reset(){
		for(int i=1;i<Game.buttons.length;i++){
			for(int j=1;j<Game.buttons.length;j++){
				if(Game.buttons[i][j].getName().equals(Game.tour.position)){
					Game.buttons[i][j].setBackground(Color.white);
					return;
				}
			}
		}
	}//end of reset
	
	//la methode qui permet de changer le tour 
	private void changerTour(){
		if(Game.tour.couleur.equals(Color.red)){
			Game.tour=Game.player2;
		}else if(Game.tour.couleur.equals(Color.green)){
			if(Game.nbrPlayer==2){
				Game.tour=Game.player1;
			}else{
				Game.tour=Game.player3;
			}
		}else if(Game.tour.couleur.equals(Color.blue)){
			Game.tour=Game.player4;
		}else {
			Game.tour=Game.player1;
		}
	}//end of tour
	
	//la methode jump permet de:
	//verifier si un muret se trouve sur le chemin du personnage
	//si oui ce deplacement doit pas etre possible
	//verifier aussi si un personnage se trouve a l'endroit d'un deplacement possible 
	//si oui le deplacement qui suit se second personnage doit etre allumer en jaune
	private void jump(XOButton b,int indexOne,int indexTwo){
		
			if(indexOne!=1){
				
				if(Game.buttons[indexOne-2][indexTwo].getBackground().equals(Game.tour.couleur)){
					if(indexOne!=17){
						
						if(!Game.buttons[indexOne-1][indexTwo].getBackground().equals(Color.black)){
							if(!Game.buttons[indexOne][indexTwo].getBackground().equals(Color.white)){
								
									if(!Game.buttons[indexOne+1][indexTwo].getBackground().equals(Color.black)){
											Game.buttons[indexOne+2][indexTwo].setBackground(Color.yellow);
									}	
							}else{
								Game.buttons[indexOne][indexTwo].setBackground(Color.yellow);
							}
						}
							
					}else if(!Game.buttons[indexOne-1][indexTwo].getBackground().equals(Color.black)
							&& Game.buttons[indexOne][indexTwo].getBackground().equals(Color.white)){
						Game.buttons[indexOne][indexTwo].setBackground(Color.yellow);
					}
					
				}
				}
				
			if(indexOne!=17){
					
					if(Game.buttons[indexOne+2][indexTwo].getBackground().equals(Game.tour.couleur)){
						
						if(indexOne!=1){
							if(!Game.buttons[indexOne+1][indexTwo].getBackground().equals(Color.black)){
								if(!Game.buttons[indexOne][indexTwo].getBackground().equals(Color.white)){
										if(!Game.buttons[indexOne-1][indexTwo].getBackground().equals(Color.black)){
												Game.buttons[indexOne-2][indexTwo].setBackground(Color.yellow);
											
									}
									
								}else{
									Game.buttons[indexOne][indexTwo].setBackground(Color.yellow);
								}
								
							}	
						}else if(!Game.buttons[indexOne+1][indexTwo].getBackground().equals(Color.black) &&
								Game.buttons[indexOne][indexTwo].getBackground().equals(Color.white)){
							Game.buttons[indexOne][indexTwo].setBackground(Color.yellow);
						}
					}
			}
			
			if(indexTwo!=1){
				if(Game.buttons[indexOne][indexTwo-2].getBackground().equals(Game.tour.couleur)){
					if(indexTwo!=17){
						if(!Game.buttons[indexOne][indexTwo-1].getBackground().equals(Color.black)){
							if(!Game.buttons[indexOne][indexTwo].getBackground().equals(Color.white)){
									if(!Game.buttons[indexOne][indexTwo+1].getBackground().equals(Color.black)){
											Game.buttons[indexOne][indexTwo+2].setBackground(Color.yellow);	
								}
							}else{
								Game.buttons[indexOne][indexTwo].setBackground(Color.yellow);
							}
							
						}
							
					}else{
						if(!Game.buttons[indexOne][indexTwo-1].getBackground().equals(Color.black)
								&& Game.buttons[indexOne][indexTwo].getBackground().equals(Color.white)){
							Game.buttons[indexOne][indexTwo].setBackground(Color.yellow);
						}	
					}
				}
			}
			if(indexTwo!=17){
				if(Game.buttons[indexOne][indexTwo+2].getBackground().equals(Game.tour.couleur)){
					if(indexTwo!=1){	
						if(!Game.buttons[indexOne][indexTwo+1].getBackground().equals(Color.black)){
							if(!Game.buttons[indexOne][indexTwo].getBackground().equals(Color.white)){
									if(!Game.buttons[indexOne][indexTwo-1].getBackground().equals(Color.black)){
											Game.buttons[indexOne][indexTwo-2].setBackground(Color.yellow);
									}	
							}else{
								Game.buttons[indexOne][indexTwo].setBackground(Color.yellow);
							}
						}
							
					}else{
						if(!Game.buttons[indexOne][indexTwo+1].getBackground().equals(Color.black) 
								&& Game.buttons[indexOne][indexTwo].getBackground().equals(Color.white)){
							Game.buttons[indexOne][indexTwo].setBackground(Color.yellow);	
						}
						
					}
				}
			}
		
	} //end of jump
	
	//methode qui verifie si un des personnage a gagné 
	private void checkVictory(){
		
		if(Game.tour.fin==this.getName().charAt(0)){
			JOptionPane.showMessageDialog(this, Game.tour.nom+"  a gagné ");
			disablePanel();
		}
		if(Game.tour.fin==this.getName().charAt(1)){
			JOptionPane.showMessageDialog(this, Game.tour.nom+"  a gagné ");
			disablePanel();
		}
		
		
		
	}	
	//cette methode desactive le panel 
	private void disablePanel(){
		Component[] com = p.getComponents();
		for (int a = 0; a < com.length; a++) {
		     com[a].setEnabled(false);
		}
	}
}
