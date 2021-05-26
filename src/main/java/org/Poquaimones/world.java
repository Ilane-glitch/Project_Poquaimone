package org.Poquaimones;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;

public class world implements Serializable {
    //population d'homme et de pqmn
    public List <humain> popH ;
    public List <pqmn> popP ;
    public List <combat> Cmb;
    //creation d'un ID de serailization pour la lecture du fichier
    private static final long serialVersionUID = 667;

    //constructeur monde
    public world(){
        popH = new ArrayList<>();
        popP = new ArrayList<>();
        Cmb = new ArrayList<>();
    }

    //methodes
    //cration nouveau dresseur
    public dresseur nouveauD(){
        dresseur d_;
        Logger a=Logger.getLogger("");
        Scanner sc=new Scanner(System.in);
        a.info("Entrez le nom du dresseur");
        String nom = sc.nextLine();
        a.info("Entrez le prenom du dresseur");
        String prenom = sc.nextLine();
        d_ = new dresseur(nom, prenom);
        Random random = new Random();
        d_.Capture(popP.get(random.nextInt(popP.size())));
        return d_;
    }
    //creation nouveau poqmn
    public pqmn nouveauP(){
        pqmn p_;
        Logger a=Logger.getLogger("");
        Scanner sc=new Scanner(System.in);
        a.info("type du poquaimone");
        String type=sc.nextLine();
        a.info("nom du poquaimone");
        String nom=sc.nextLine();
        //Le poquaimone commence avec une attaque et de la vie inférieure ou égale a 10 de façon aléatoire
        int pv = Random();
        int pa = Random();
        p_=new pqmn(type, nom, pv, pa);
        return p_;
    }
    // nouvel arbitre
    public arbitre nouveauA(){
        arbitre a_;
        Logger a=Logger.getLogger("");
        Scanner sc=new Scanner(System.in);
        a.info("Entrez le nom de l'arbitre");
        String nom_ = sc.nextLine();
        a.info("Entrez le prenom de l'arbitre");
        String prenom_ = sc.nextLine();
        a_=new arbitre(nom_, prenom_);
        return a_;
    }
    // nouvel humain
    public humain nouveauH(){
        humain h_;
        Logger a=Logger.getLogger("");
        Scanner sc=new Scanner(System.in);
        a.info("Entrez le nom de l'humain");
        String nom = sc.nextLine();
        a.info("Entrez le prenom de l'humain");
        String prenom = sc.nextLine();
        h_=new humain(nom,prenom);
        return h_;
    }
    //nombre aléatoire pour les stats du pqmn
    protected int Random() {
        return 1 + (int)(Math.random() * ((10 - 1) + 1));
    }
    // revue des humain
    protected void RevueH(){
        Logger a=Logger.getLogger("");
        int i=0;
        while (i!=popH.size()){
            a.info(" id : "+popH.get(i).getId() +" nom : "+popH.get(i).getNom()+" prenom : "+popH.get(i).getPrenom());
            i++;
        }
    }
    // revue des pmn
    protected void RevueP(){
        Logger a=Logger.getLogger("");
        int i=0;
        while (i!=popP.size()){
            a.info("type "+popP.get(i).getType()+" nom : "+popP.get(i).getNom()+" attaque : "+popP.get(i).getPa()+" vie : "+popP.get(i).getPv());
            i++;
        }
    }
    //classement des meilleurs dresseurs
    protected void BestD(){
        //Override des compare etc a faire + affichage tableau
        Logger a = Logger.getLogger("BestD");
        List <dresseur> palm = new ArrayList<>();
        int i=0;
        //nouvelle arrayList de dresseur
        while (i!=popH.size()){
            if (popH.get(i) instanceof dresseur){
                palm.add((dresseur)popH.get(i));
                i++;
            }
        }
        Collections.sort(palm);
        i=0;
        while (i!=palm.size()){
            a.info("dresseur n°"+ (i+1) +" nom : "+palm.get(i).getNom()+" prenom : "+palm.get(i).getPrenom());
            i++;
        }

    }
    //creation d'un nouveau combat
    protected combat nouveauC(){
        combat c_;
        humain[] H = new humain[5];
        Logger a=Logger.getLogger("");
        Scanner sc=new Scanner(System.in);
        //dresseur courant
        a.info("donnez votre id");
        int id_ = sc.nextInt();
        dresseur D_=new dresseur();
        int i=0;
        while (i!=popH.size()){
            if(popH.get(i).id == id_ && popH.get(i) instanceof dresseur){
                D_=(dresseur)popH.get(i);
            }
            i++;
        }
        //on cherche le dresseur
        a.info("id du dresseur a defier");
        id_ = sc.nextInt();
        dresseur d_=new dresseur();
        arbitre a_ =new arbitre();
        i=0;
        while (i!=popH.size()){
            if(popH.get(i).getId() == id_ && popH.get(i) instanceof dresseur){
                d_=(dresseur)popH.get(i);
            }
            i++;
        }
        //on cherche l'arbitre
        a.info("id de l'arbitre a trouver");
        id_ = sc.nextInt();
        i=0;
        while (i!=popH.size()){
            if(popH.get(i).getId() == id_ && popH.get(i) instanceof arbitre){
                a_=(arbitre)popH.get(i);
            }
            i++;
        }
        //spectateur supplémentaire
        H[4] = spectate(D_);
        c_ = new combat(D_,d_,a_,H[0],H[1],H[2],H[3],H[4]);
        return c_;
    }
    //spectateur pour le combat
    protected humain spectate(dresseur D_){
        humain[] H = new humain[5];
        Logger a=Logger.getLogger("spectate");
        Scanner sc=new Scanner(System.in);
        a.info("inviter des spectateur ami ? oui - (1) - non (autre)");
        int choix = sc.nextInt();
        int j=0;
        while (choix == 1 && j!=5){
            a.info("id du spectateur ami ?");
            int id_ = sc.nextInt();
            int i=0;
            while (i!=popH.size()){
                if(popH.get(i).getId() == id_){
                    H[j]= popH.get(i);
                }
                i++;
            }
            j++;
            a.info("nouveau spectateur ?");
            choix = sc.nextInt();
        }
        return H[4];
    }
    //info sur les humains
    protected void info(){
        Logger a=Logger.getLogger("info");
        int i=0;
        while (i!=popH.size()){
            //information générale
            a.info("id :"+popH.get(i).getId()+" nom : "+popH.get(i).getNom()+" prenom :"+popH.get(i).getPrenom());
            //cas ou instance de dresseur
            if(popH.get(i) instanceof dresseur){
                int j=0;
                a.info(" match gagne : "+((dresseur) popH.get(i)).getNbG()+" match perdu : "+((dresseur) popH.get(i)).getNbP());
                while (j!=((dresseur) popH.get(i)).listePqmn.size()){
                    a.info("pokemon : "+((dresseur) popH.get(i)).listePqmn.get(j).getNom()+" type "+((dresseur) popH.get(i)).listePqmn.get(j).getType());
                    j++;
                }
                afficheCmbD((dresseur)popH.get(i));
            }
            //cas ou instance d'arbitre
            if(popH.get(i) instanceof arbitre){
                a.info("match arbitré : "+((arbitre) popH.get(i)).getNbA());
            }
            i++;
        }
    }
    // affiche combat a venir
    protected void afficheCmbD(dresseur d){
        Logger a=Logger.getLogger("afficheCmbD");
        int i=0;
        dresseur d_;
        while (i!=Cmb.size()){
            int j=0;
            while (j!=Cmb.get(i).ListeCmbt.size()){
                if(d.equals(Cmb.get(i).ListeCmbt.get(j).GetD1()) || d.equals(Cmb.get(i).ListeCmbt.get(j).GetD2())){
                    //pour des questions de simplicité on passe par une variable locale
                    if(d.equals(Cmb.get(i).ListeCmbt.get(j).GetD1())) d_= Cmb.get(i).ListeCmbt.get(j).GetD2();
                    else d_= Cmb.get(i).ListeCmbt.get(j).GetD1();
                    a.info("combat prevu contre "+d_.getNom()+" "+d_.getPrenom());
                }
                j++;
            }
            i++;
        }
    }
    //affiche les donnees du monde en JSON
    protected void WonJSON(){
        int i=0;
        Jsonb jsonb = JsonbBuilder.create();
        Logger a=Logger.getLogger("");
        //population humaine
        while (i!=popH.size()){
            String result = jsonb.toJson(popH.get(i));
            a.info(result);
            i++;
        }
        i=0;
        //population de pqmn
        while (i!=popP.size()){
            String result = jsonb.toJson(popP.get(i));
            a.info(result);
            i++;
        }
    }
    // fichier pour sauvegarde
    protected void SAVE(){
        //on écrit un fichier
        String path ="SAVE.txt"; //on sauvegarde a la source du fichier
        File f = new File (path);
        try {
            //ouverture des flux
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            //Ecriture
            oos.writeObject(this);
            //fermeture des flux
            oos.close();
            fos.close();
            Logger a = Logger.getLogger("SAVE");
            a.info("Sauvegarde réussie");

        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    // fichier de chargement
    protected world LOAD(){
        //on lis un fichier
        Logger a = Logger.getLogger("LOAD");
        String path ="SAVE.txt";
        world w = null;
        File f = new File (path);
        try {
            //ouverture des flux
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            //lecture
            w = (world)ois.readObject();
            //fermeture des flux
            ois.close();
            fis.close();
            a.info("chargement reussi");
            return w;
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        return w;
    }
    //méthode source
    public void cycle(){
        Logger a=Logger.getLogger("");
        Scanner sc=new Scanner(System.in);
        System.out.println("Que voulez vous faire ?");
        System.out.println("Nouvel Humain (1) - nouvel Arbitre (2) - nouveau Dresseur (3) - nouveau Poquaimone (4)");
        System.out.println("revue population Humaine (5) - revue population Pqmn (6) - info monde en JSON (13)");
        System.out.println("classement dresseur du monde (7)");
        System.out.println("nouveau planing de combat (8) - remplir planing combat (9) - exec planing combat (10) - au point dans le test mais pas ici");
        System.out.println("afficher combat prevu (11) - afficher toutes info (12)");
        System.out.println("sauvegarder et quitter (14)");
        int choice = sc.nextInt();
        switch (choice){
            case 1:
                popH.add(nouveauH());
                cycle();
                break;
            case 2 :
                popH.add(nouveauA());
                cycle();
                break;
            case 3:
                popH.add(nouveauD());
                cycle();
                break;
            case 4:
                popP.add(nouveauP());
                cycle();
                break;
            case 5:
                RevueH();
                cycle();
                break;
            case 6:
                RevueP();
                cycle();
                break;
            case 7:
                //A FINIR
                BestD();
                cycle();
                break;
            case 8:
                combat c_=new combat();
                Cmb.add(c_);
                cycle();
                break;
            case 9:
                a.info("id du planing a remplir");
                int id_ = sc.nextInt();
                int i=0;
                while (i!=Cmb.size()){
                    if (Cmb.get(i).GetIdCmb()==id_){
                        c_ = nouveauC();
                        Cmb.get(i).Planning(c_);
                    }
                    i++;
                }
                cycle();
                break;
            case 10:
                a.info("id du planing a executer");
                id_ = sc.nextInt();
                i=0;
                while (i!=Cmb.size()){
                    if (Cmb.get(i).GetIdCmb()==id_){
                        Cmb.get(i).ExecPlanning();
                    }
                    i++;
                }
                cycle();
                break;
            case 11:
                a.info("id de dresseur");
                id_ = sc.nextInt();
                i=0;
                while (i!=popH.size()){
                    if (popH.get(i).getId()==id_) afficheCmbD((dresseur)popH.get(i));
                    i++;
                }
                cycle();
                break;
            case 12:
                info();
                cycle();
                break;
            case 13:
                WonJSON();
                cycle();
                break;
            case 14:
                SAVE();
                break;
            default:
                break;
        }
    }
}
