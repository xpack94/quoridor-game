//programme fait par NAHNAH abdesselam et CHEBBOUB sofiane
import java.awt.Color;


import javax.swing.JOptionPane;


public class testGame {
	public static final int nbrMurets=20;
	
	
	public static void main(String[] args) {
		testGame test=new testGame();
	   String t=JOptionPane.showInputDialog("entrer le nombre de joueurs 2/4");
	   createGame(t);

	
	}
	public static void createGame(String t){
		try{
			if(t!=null){
				int nbrJoueurs=Integer.parseInt(t);
				
				if(nbrJoueurs==2){
					String firstName=JOptionPane.showInputDialog("entrer le nom du premier joueur");
					String secondName=JOptionPane.showInputDialog("entrer le nom du deuiemme joueur");
					personnage p1=new personnage("e1",firstName,Color.red, nbrMurets/nbrJoueurs,'9');
				    personnage p2=new personnage("e9",secondName,Color.green,nbrMurets/nbrJoueurs,'1');
					new Game(p1,p2);
				}else if(nbrJoueurs==4){
					String firstName=JOptionPane.showInputDialog("entrer le nom du premier joueur");
					String secondName=JOptionPane.showInputDialog("entrer le nom du deuiemme joueur");
					String thirdName=JOptionPane.showInputDialog("entrer le nom du troisiemmejoueur");
					String fourthName=JOptionPane.showInputDialog("entrer le nom du quatriemme joueur");
					personnage p1=new personnage("e1",firstName,Color.red, nbrMurets/nbrJoueurs,'9');
				    personnage p2=new personnage("e9",secondName,Color.green,nbrMurets/nbrJoueurs,'1');
				    personnage p3=new personnage("a5",thirdName,Color.blue, nbrMurets/nbrJoueurs,'i');
				    personnage p4=new personnage("i5",fourthName,Color.orange,nbrMurets/nbrJoueurs,'a');
				    new Game(p1,p2,p3,p4);
				}else{
					System.exit(0);
				}
			}
			

			}catch( Exception d){
				String f=JOptionPane.showInputDialog("entrer un nombre valide");
				createGame(f);
			}
	}

	
}
