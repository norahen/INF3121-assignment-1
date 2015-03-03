public class ListeAvPersoner {
    private Person personliste, sistePerson, neste;
    private int antall;
    
    public Person hentPersonliste() {
	return personliste;
    }

    Person lh = new Person("LISTEHODE!!", 1);
    
    ListeAvPersoner() {
	// skal etablere datastrukturen for tom liste:

	personliste = lh;
	sistePerson = lh;
	neste = lh;
	antall = 0;
    }

    /* 
       Invariante tilstandspåstander (skal gjelde før og etter alle metodekall):

       - personliste peker på listehodet
       - første person i lista er første person etter
         listehodet, dvs. personliste.neste
       - sisteperson peker på siste person i lista, dvs.
       - sisteperson.neste er alltid null
       - ingen andre personobjekter har neste som er null

       - Når lista er tom skal (tilstanden etableres av konstruktør):
            - antall innholde tallet 0
            - personliste peke på listehodet
	    - sistePerson peke på listehodet
	    - personliste.neste være null
    */
	
    public boolean erIListe(Person p) {
    
	for(Person peker = personliste; peker.neste != null; peker = peker.neste) {
	    if(peker == p) {
		return true;
	    }
	}
	return false;	
    }
    
    
    public void settInnForst(Person nypers){
	if(!erIListe(nypers)) {
	nypers.neste = personliste.neste;
	personliste.neste = nypers;
	if (sistePerson.neste == nypers) // nyperson er ny siste!
	    sistePerson = nypers;
	antall++;
	}
    }
    
    public void settInnSist(Person inn){
	if(!erIListe(inn)) { 
	sistePerson.neste = inn;
	sistePerson = inn;
	antall++;
	}
    }

    public void settInnEtter(Person denne, Person nypers) {
	if(erIListe(nypers) && !erIListe(denne) && denne != nypers) {
	nypers.neste = denne.neste;
	denne.neste = nypers;
	if (sistePerson.neste == nypers)  // nyperson er ny siste!
	    sistePerson = nypers;
	antall++;
	}
    }

    public Person finnPerson(String s) {
	Person p = personliste.neste;
        for (int i = antall; i>0; i--) {
	    if (p.hentNavn().equals(s)) return p;
	    else p = p.neste;
        }
	return null;
    }

    
    public void skrivAlle() { 
        Person p = personliste.neste;
	System.out.println("------");
        for (int i = antall; i>0; i--) {
     	    System.out.print(antall - i +1 + ": ");
            p.skrivUtAltOmMeg();
            p = p.neste;
        }
	System.out.println("=======");
    }
}



