package org.Poquaimones;

import java.util.ArrayList;

public class dresseur extends humain implements Comparable<dresseur> {
	//le dresseur étant une "spécialisation" de l'humain il hérite donc de la classe mère Humain
	//Liste de Pqmn (pour Poquaimones) appartenant au dresseur
	public ArrayList <pqmn> listePqmn;
	// nombre de combat gagné et/ou perdu
	public int nbG, nbP;

	//constructeur vide
	protected dresseur() {}

	//constructeur
	public dresseur(String n, String p) {
		//appel a la super classe humain
		super(n,p);
		//instance de la liste d'ami
		listePqmn = new ArrayList<>();
		//au début le de sa carrière le dresseur n'a ni gagné ni perdu
		this.nbG=0;
		this.nbP=0;
	}
	//ajout dans liste
	public void Capture(pqmn P) {
		this.listePqmn.add(P);
	}
	// Liberer un pqmn de la liste
	protected void Liberer(pqmn P) {
		int total = this.listePqmn.size();
		pqmn P_;
		for (int i=1;i<total;i++) {
			P_=this.listePqmn.get(i);
			// on verifie si les 2 instances de pqmn sont identique 
			if(P.equals(P_)) {
				this.listePqmn.remove(i);
			}
		}
	}

	@Override
	public int compareTo(dresseur d) {
		// si positif le dresseur est meilleur si négatif il est moins bon si =0 est égal
		return (d.nbG-this.nbG);
	}

	//GETTEUR SETTEUR
	protected int getNbG() {
		return nbG;
	}

	protected void setNbG(int nbG) {
		this.nbG = nbG;
	}

	protected int getNbP() {
		return nbP;
	}

	protected void setNbP(int nbP) {
		this.nbP = nbP;
	}
	//FIN GETTEUR SETTEUR
}
