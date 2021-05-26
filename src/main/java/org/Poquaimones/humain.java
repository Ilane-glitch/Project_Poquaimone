package org.Poquaimones;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Entity
public class humain implements Serializable {
	@Id
    //nom de l'humain
	public String nom;
	// prenom de l'humain
	public String prenom;
	// id BRUT du dernier dresseur crée
	protected static int id_;
	// id du dresseur
	protected int id;
	// Liste d'amis de l'humain
	public List <Integer> ListeAmis ;

	//constructeur vide
	protected humain() {}

	//constructeur
	public humain(String n, String p) {
		this.setNom(n);
		this.setPrenom(p);
		id_++;
		this.id=id_;
		ListeAmis = new ArrayList<>();
	}
	// ajout d'un Humain (et donc dresseur/Arbitre) en ami
	public void AjoutAmis(humain H1, humain H2) {
		Logger c = Logger.getLogger("AjoutAmis");
		H1.ListeAmis.add(H2.getId());
		H2.ListeAmis.add(H1.getId());
		c.info(H1.getNom() + " est devenu ami avec "+ H2.getNom() + " !");
	}
	//retirer un humain de la liste d'ami
	protected void RetirerAmis(humain H1, humain H2) {
		Logger c = Logger.getLogger("");
		//on retire H2 de la liste de H1
		int i=0,j=0;
		while (i!=H1.ListeAmis.size()){
			if(H1.ListeAmis.get(i)==H2.getId()){
				//on recupère l'indice de i -> position de H2
				j=i;
			}
			i++;
		}
		H1.ListeAmis.remove(j);
		i=0;
		while (i!=H2.ListeAmis.size()){
			if(H2.ListeAmis.get(i)==H1.getId()){
				//on recupère l'indice de i -> position de H2
				j=i;
			}
			i++;
		}
		//on retire H1 de la liste de H2
		H2.ListeAmis.remove(j);
		c.info(H1.getNom() + " n'est plus ami avec "+ H2.getNom() + " !");
	}
	
	//redefinition de equals 
	@Override
	public boolean equals(Object H) {
		if(H!=null){
			if(this==H && this.getId() == ((humain) H).getId()){
				return true;
			}
		}
		return false;
	}
	
	//GETTEUR SETTEUR
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getId() {
		return this.id;
	}
	//FIN GETTEUR SETTEUR
}
