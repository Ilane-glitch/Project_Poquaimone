package org.Poquaimones;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.logging.Logger;

public class combat implements Serializable {
	protected dresseur D1;
	protected dresseur D2;
	protected arbitre A;
	protected static int idCmb_;
	protected int idCmb;
	//queue car Le premier combat dans la file est le premier combat qui arrivera
	LinkedList <combat> ListeCmbt;

	protected combat() {
		ListeCmbt = new LinkedList<>();
		idCmb_++;
		this.idCmb=idCmb_;
	}

	public combat(humain... H) {
			this.SetD1((dresseur)H[0]);
			this.SetD2((dresseur)H[1]);
			this.SetA((arbitre)H[2]);
		}
	//préparation de la liste de combat
	public void Planning(combat C) {
		if(this.ListeCmbt==null) this.ListeCmbt = new LinkedList<>();
		this.ListeCmbt.add(C);
	}

	//enchaine le combat suivant
	public void ExecPlanning() {
		int i;
		int taille=ListeCmbt.size();
		for (i=1;i<=taille;i++) {
			combat C_ = this.ListeCmbt.poll();
			Cmb(C_);
		}
	}

	//methode combat qui appelera successivement toutes les méthodes combats
	protected void Cmb (combat C) {
		Logger c = Logger.getLogger("project.Poquaimones.Pqmn");
		//j'utilise des variables locales pour me faciliter la tache
		dresseur D1=C.GetD1(), D2=C.GetD2();
		arbitre A=C.GetA();
		c.info("Le combat qui oppose "+D1.getPrenom()+" et "+D2.getPrenom()+" arbitre par "+A.getNom()+" "+A.getPrenom()+" !" );
		//on vérifie si les instances de dresseurs sont différentes ou non
		if (D1.equals(D2)) {
			//choix des pqmn
			int nbP1=Random(D1);
			int nbP2=Random(D2);
			//copie des pqmn
			pqmn P1,P2;
			P1=D1.listePqmn.get(nbP1);
			P2=D2.listePqmn.get(nbP2);
			//calcul de l'isseur du combat
			boolean issue=Calcul(P1,P2);
			//fin du combat
			if(issue) {
				//cas ou D1 gagne
				D1.setNbG(D1.getNbG()+1);
				P1.setPv(P1.getPv()+3);
				P2.setPv(P2.getPv()-3);
				P1.evolution();
				D2.setNbP(D2.getNbP()+1);
			}
			else {
				//cas ou D1 perds
				D2.setNbG(D2.getNbG()+1);
				P2.setPv(P2.getPv()+3);
				P1.setPv(P1.getPv()+3);
				P2.evolution();
				D1.setNbP(D1.getNbP()+1);
			}
			//on remplace les ancier pqmn par les nouveau
			D1.listePqmn.set(nbP1, P1);
			D2.listePqmn.set(nbP2, P2);
			//arbitre prends arbitrage sup'
			A.setNbA(A.getNbA()+1);
		}
		//on met a jour les objets
		C.SetD1(D1);
		C.SetD2(D2);
		C.SetA(A);
	}

	// calcul l'issue du combat en fonction des pa des pqmn
	protected boolean Calcul (pqmn P1, pqmn P2) {
		Logger c = Logger.getLogger("project.Poquaimones.Pqmn");
		int i;
		//true si P1 gagne sinon false
		boolean calcul = false;
		//cri de Pqmn
		P1.Attaque();
		P2.Attaque();
		//fin cri
		if(P1.getPv()>P2.getPv()) {
			calcul=true;
			c.info(P1.getNom() + " remporte le combat !");
		}
		else if(P2.getPa()>P1.getPa()) {
			//calcul déjà assigné a faux
			c.info(P2.getNom() + " remporte le combat !");
		}
		//choix du gagnant par tirage au sort
		else {
			//total des attaques
			int total = P1.getPa()+P2.getPa();
			//choix d'un nombre entre 1 et total
			int nb = Random(total);
			for (i=1;i<P1.getPa();i++){
				if(i==nb) {
					calcul=true;
					c.info(P1.getNom() + " remporte le combat !");
				}
			}
			for (i=(P1.getPa())+1;i<total;i++) {
				if(i==nb) {
					calcul=false;
					c.info(P2.getNom() + " remporte le combat !");
				}
			}
		}
		return calcul;
	}
	protected boolean IsInFightList(dresseur D){
		int i=0;
		while (i != this.ListeCmbt.size()){
			dresseur d1_=this.ListeCmbt.get(i).GetD1();
			dresseur d2_=this.ListeCmbt.get(i).GetD2();
			if (D.equals(d1_) || D.equals(d2_)){
				return true;
			}
			i++;
		}
		return false;
	}
	// Position du Pqmn selectionné dans la liste de façon aléatoire
	protected int Random(dresseur D) {
		// nombre aléatoire entre 1 et le nombre de pokémon
		return (int)(Math.random() * D.listePqmn.size());

	}
	
	protected int Random(int nb) {
		return 1 + (int)(Math.random() * ((nb - 1) + 1));
	}
	//GETTEUR SETTEUR
	public int GetIdCmb() {
		return this.idCmb;
	}
	public dresseur GetD1(){
		return this.D1;
	}
	public dresseur GetD2(){
		return this.D2;
	}
	public arbitre GetA(){
		return this.A;
	}
	public void SetD1(dresseur d){
		this.D1 = d;
	}
	public void SetD2(dresseur d){
		this.D2 = d;
	}
	public void SetA(arbitre a){
		this.A = a;
	}
	//FIN GETTEUR SETTEUR
}

