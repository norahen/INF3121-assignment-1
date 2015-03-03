/Samarbeidet med benediwa i noen metoder.

class Oblig1 {

    public static void main(String[] args) {


	Test test = new Test();
    }
}

class Test {
    
    private Bok[] bokSamling;
    private Plate[] plateSamling;
    private Person[] pers = new Person[8];
    ListeAvPersoner liste = new ListeAvPersoner();
    GaveLager nyGave = new GaveLager();    
    Personer pListe = new Personer();


	Test() {

	leggerInnPersoner();
	tilbyGave();
	liste.skrivAlle();
	}


    public void leggerInnPersoner() {
	Person p;

	while((p = pListe.hentPerson())!= null) {
	    liste.settInnSist(p);  
	}
    }
    
    public void tilbyGave() {
	while (nyGave.checkGave()) {
	    for(String s : pListe.hentPersonnavn()) {
		if(s != null) {
		    liste.finnPerson(s).vilDuHaGaven(nyGave.hentGave());
		}
	    }
	}
    } 
  
} //Slutt Test klasse

class Person {

    //Oblig1
    private String navn;
    private Person[] kjenner = new Person[100];
    private Person[] likerikke = new Person[10];
    private Person forelsketi;
  
    //Oblig2
    private Bok[] bokSamling;
    private Plate[] plateSamling;
    private int faveldre;
    private String favart;
    private boolean smlrPlate;
    private boolean smlrBok;
    private Plate byttet = null;
   
    //Oblig3
    Person neste;
    private String kategori;
    private Person sammenMed;
    private String interessert;
    private Gave[] gaveSamling;
    private int gaveTeller = 0;
    private boolean smlrVin;
    private int ant;
   
    Person(String n, int lengde) { //Konstruktor
	navn = n;
	kjenner = new Person[100];
	likerikke = new Person[10];
	neste = neste;
    }
   
    public void samlerAv(String kategori, int ant) {

	gaveSamling = new Gave[ant];
	interessert = kategori;
    }

    public Gave vilDuHaGaven(Gave gave) {
	
	gaveTeller = 0;
	if(gave != null && gave.kategori().equals(interessert)) {
	    for(int i = 0; i < gaveSamling.length; i++) {
		if(gaveSamling[i] == null) {
		    gaveSamling[i] = gave;
		    return null;
		}
		gaveTeller++;
	    }

	    if(gaveTeller == gaveSamling.length && sammenMed != null) {
		if(gave.kategori().equals(sammenMed.interessert)) {
		    for(int i = 0; i < sammenMed.gaveSamling.length; i++) {
			if(sammenMed.gaveSamling[i] == null) {
			    sammenMed.gaveSamling[i] = gave;
			    return null;
			}
		    }
		}

		if(gaveTeller == gaveSamling.length && forelsketi != null) {
		    if(gave.kategori().equals(forelsketi.interessert)) {
			for(int i = 0; i < forelsketi.gaveSamling.length; i++) {
			    if(forelsketi.gaveSamling[i] == null) {
				forelsketi.gaveSamling[i] = gave;
				return null;
			    }
			}
		    }
		}
	    
		Person [] venner = hentVenner();
		if(gaveTeller == gaveSamling.length && venner != null) {
		    for(int i = 0; i < venner.length; i++) {
			if(gave.kategori().equals(venner[i].interessert)) {
			    for(int j = 0; j < venner[i].gaveSamling.length; j++) {
				if(venner[i].gaveSamling[j] == null) {
				    venner[i].gaveSamling[j] = gave;
				    return null;
				}
			    }
			}
		    }
		}
	    }
	}
	return gave;
    }
  


    public String getNavn() {
	return navn;
    }
    
    public String interessertI() {
	return interessert;
    }

    //------ Metoder fra forrige oblig1 -------

    public String hentNavn() {
	return navn; 
    }

    public boolean erKjentMed(Person p) {
	for(int i = 0; i < kjenner.length; i++) {
	    if(kjenner[i] == p) {
		return true;
	    }
	}
	return false;
    }

    public void blirKjentMed(Person p) {
	if(this != p) {
	    for(int i = 0; i < kjenner.length; i++) {
		if(kjenner[i] == null) {
		    kjenner[i] = p;
		    break;
		}
	    }
	}
    }
    	
    public void blirForelsketI(Person p) {
	if(this != p) {
	    for(int i = 0; i < likerikke.length; i++) {
		if(p == likerikke[i]) {
		    forelsketi = null;
		    break;
		}
	    }
	    forelsketi = p;
	}
    }
    
    public void blirSammenMed(Person p) {
	if(this != p && sammenMed == null) {
	    sammenMed = p;
	    p.sammenMed = this;
	}
    }

    public Person hentSammenMed() {
	return sammenMed;
    }
	    
    public void blirUvennMed(Person p) {
	if(this != p) {
	    for(int i = 0; i < likerikke.length; i++) {	    
		if(likerikke[i] == null) {
		    likerikke[i] = p;
		    break;
		}
	    }
	}
    }

    public boolean erVennMed(Person p) {
	for(int i = 0; i < kjenner.length; i++) {
	    if(p != null && kjenner[i] != p && p != likerikke[i]) {
		return true;
	    }
	}
	return false;
    }
	
    public void blirVennMed(Person p) {
	if(this != p) {
	    for(int i = 0; i < likerikke.length; i++) {
		if(likerikke[i] == p) {
		    likerikke[i] = null;
		}
	    }
	}
    }
		
    public void skrivUtVenner() {
	for(Person x: kjenner) {
	    if(x != null) {
		System.out.println("Danas venner: " + (x.hentNavn()));
	    }
	}
    }

    public Person hentBestevenn() {
	return kjenner[0];
    }

    public Person[] hentVenner() {
	int teller = 0;
	for(int i = 0; i < kjenner.length; i++) {
	    if(kjenner[i] != null) {
		teller++;
	    }
	}

	Person[] venner = new Person[teller];
	teller = 0;
	for(int i = 0; i < kjenner.length; i++) {
	    if(kjenner[i] != null) {
		venner[teller] = kjenner[i];
		teller++;
	    }
	}
	return venner;
    }
	
    public int antVenner() {
	int teller = 0;
	for(int i = 0; i < kjenner.length; i++) {
	    if(kjenner[i] != null) {
		teller++;	
	    }
	}
	return teller;
    }

    public void skrivUtKjenninger() {
	for(Person p: kjenner) {
	    if(p != null) { 
		System.out.print(p.hentNavn() + ", ");
	    }
	}
	System.out.println("");
    }

    public void skrivUtLikerIkke() {
	for(Person p: likerikke) {
	    if(p != null) {
		System.out.print(p.hentNavn() + ", ");
	    }
	}
	System.out.println("");
    }

    public void skrivUtGaver() {
	for(Gave g : gaveSamling) {
	    if(g != null) {
		System.out.print("kategori: " + g.kategori() + ", tittel: " + g.gaveId());
		System.out.println("");
	    }
	}
    }

    public void skrivUtAltOmMeg() {
	
	System.out.print(navn + "\nkjenner: ");
	skrivUtKjenninger();
	System.out.println("");

	if(sammenMed != null) {
	    System.out.println(navn + " er sammen med "+ sammenMed.hentNavn());
	}

	if(forelsketi != null) {
	    System.out.println("");
	    System.out.println(navn + " er forelsket i " + forelsketi.hentNavn());
	    System.out.println("");
	    System.out.print(navn + " liker ikke "); 
	    skrivUtLikerIkke();
	    System.out.println("");
	}
	
	if(gaveSamling != null) {
	    for(int i = 0; i < gaveSamling.length; i++) {
		if(gaveSamling[i] != null) {
		    System.out.println("Kategori: " + gaveSamling[i].kategori() + ", tittel: " + gaveSamling[i].gaveId());
		}
	    }
	    System.out.println("");
	}		
	
    }
}
