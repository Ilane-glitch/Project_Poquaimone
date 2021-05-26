package org.Poquaimones;

public class arbitre extends humain {
    //nombre d'arbitrage
	public int nbA;
	
	//constructeur vide
	protected arbitre() {}
	
	//constructeur 
	public arbitre(String n, String p) {
		super(n,p);
		//au début de sa carrière l'arbitre n'a aucun match arbitré a son actif
		this.nbA=0;
		// instanciation de la liste de prochain combat au quel l'arbitre devra participer
	}
	
	//GETTEUR SETTEUR
	public int getNbA() {
		return nbA;
	}

	public void setNbA(int nbA) {
		this.nbA = nbA;
	}
	//FIN GETTEUR SETTEUR
}
