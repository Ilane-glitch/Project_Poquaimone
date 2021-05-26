package org.Poquaimones;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class pqmn implements Serializable {
	public String type; //peut être une classe "type" a voir...
	public String nom;
	public int pv; // point de vie total de la créature
	public int pa; // point d'attaque de la créature
	protected boolean EVO1;
	protected boolean EVO2;
	
	//constructeur vide
	public pqmn() {}
	
	//constructeur
	public pqmn(String t, String n, int pv, int pa) {
		this.type=t;
		this.nom=n;
		this.pv=pv;
		this.pa=pa;
		//on part du principe que chaque pqmn est au niveau le plus bas au debut
		this.EVO1=false;
		this.EVO2=false;
	}
	
	// Evolution du pqmn -> set.nom set.pv etc... Si pv > 50 ou pv > 100
	public void evolution() {
		Logger a = Logger.getLogger("project.Poquaimones.Pqmn");
		if (this.pv>=50 && this.pv<100 && !(this.isEVO1())) {
			//EVOLUTION 1
			this.setEVO1(true);
			String nom_=this.nom;
			this.setNom(this.nom+ " max");
			this.setPa(this.pa+20);
			a.info(nom_ + " evolue en "+this.nom+" !");
		}
		if (this.pv>=100 && !(this.isEVO2())) {
			//EVOLUTION 2
			this.setEVO2(true);
			String nom_=this.nom;
			this.setNom("SUPER " + this.nom);
			this.setPa(this.pa+20);
			a.info(nom_+" evolue en " +this.nom+" !");
		}
	}
	
	//attaques
	public void Attaque() {
		//attaque 1
		Logger a = Logger.getLogger("project.Poquaimones.Pqmn");
		String attk1=this.nom + " : Ca merite une bonne note";
		a.info(attk1);
		//attaque 2
		if (this.pv>=50) {
			String attk2= this.nom + " : Ca merite une encore meilleure note";
			a.warning(attk2);
		}
		//attaque 3
		if(this.pv>=100) {
			String attk3=this.nom +" : Ca merite une excellente note";
			a.log(Level.SEVERE,attk3);
		}
	}

	
	@Override
	public boolean equals(Object P) {
		if(P==null)return false;
		if(P instanceof pqmn && this == P) return true;
		return true;
	}
	
	//GETTEUR SETTEUR
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getPv() {
		return pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public int getPa() {
		return pa;
	}

	public void setPa(int pa) {
		this.pa = pa;
	}

	public boolean isEVO1() {
		return EVO1;
	}

	public void setEVO1(boolean eVO1) {
		EVO1 = eVO1;
	}

	public boolean isEVO2() {
		return EVO2;
	}

	public void setEVO2(boolean eVO2) {
		EVO2 = eVO2;
	}
	
	//FIN GETTEUR SETTEUR
}
