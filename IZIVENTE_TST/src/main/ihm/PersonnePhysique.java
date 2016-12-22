package main.ihm;

import java.util.LinkedList;
import java.util.List;

import main.outils.IZIVENTEOutils;
import annotations.BaliseXml;

@BaliseXml(prefixe="_3_3", nom="PersPhys", balisable=false)
public class PersonnePhysique {

//  <_3_3:IdntClntDistr>1210009</_3_3:IdntClntDistr>
	@BaliseXml(prefixe="_3_3", nom="IdntClntDistr", obligatoire=true, libelle="Id client distributeur")
	public String idClientDistributeur = new String("1210009");
	
//<_3_3:DtDernVerfDonn>20120707</_3_3:DtDernVerfDonn>
	@BaliseXml(prefixe="_3_3", nom="DtDernVerfDonn", obligatoire=true, libelle="Date de vérification")
	public String dateVerification = new String("20120707");

//  <_3_3:CdPersJurd>1</_3_3:CdPersJurd>
	@BaliseXml(prefixe="_3_3", nom="CdPersJurd", obligatoire=true, listeValeur={"1:Personne Physique", "2:Entrepreneur individuel"}, libelle="Code personne juridique")
	public String codePersonneJuridique = new String("1");
	
	@BaliseXml(prefixe="_3_3", nom="SignlPersPhys", obligatoire=true, multiple=false, complexe=true, libelle="Personne physique")
	public SignalementPersonnePhysique signalement = new SignalementPersonnePhysique();
	
	@BaliseXml(prefixe="_3_3", nom="ConnPersPhys", obligatoire=true, multiple=false, complexe=true, libelle="Conn. pers. physique")
	public ConnaissancePersonnePhysique connaissance = new ConnaissancePersonnePhysique();
	
	@BaliseXml(prefixe="_3_3", nom="SignEntrIndv", obligatoire=false, multiple=false, complexe=true, libelle="Entrepreneur indiv")
	public SignalementEntrepreneurIndividuel signalementEntrepreneur = new SignalementEntrepreneurIndividuel();
	
	@BaliseXml(prefixe="_3_3", nom="AdrsPers", obligatoire=false, multiple=true, complexe=true, libelle="Adresse", contenu=Adresse.class)
	public List<Adresse> adresses = new LinkedList<Adresse>();
	
	@BaliseXml(prefixe="_3_3", nom="MdiaPers", obligatoire=false, multiple=true, complexe=true, libelle="Média", contenu=Media.class)
	public List<Media> medias = new LinkedList<Media>();
	
	public PersonnePhysique() {
		super();
	}
	
	public PersonnePhysique(String idClient) {
		super();
		idClientDistributeur = idClient;
		initierAdresses();
	}
	
	public PersonnePhysique(String idClient, boolean conjoint) {
		super();
		idClientDistributeur = idClient;
		signalement = new SignalementPersonnePhysique(IZIVENTEOutils.lettreFromNumerique(idClient), conjoint);
		initierAdresses();
		initierMedia();
	}
	
	/**
	 * Permet de s'assurer que la personne aura bien une adresse personelle et professionelle.
	 */
	public void initierAdresses() {
		Adresse adressePerso = new Adresse();
		Adresse adressePro = new Adresse();
		Adresse adresseCorr = new Adresse();
		
		adressePerso.codeTypeAdresse = "1";
		adressePerso.ligneAdresse2 = "LIEU DIT ADRESSE_PERSO";
		adressePro.codeTypeAdresse = "6";
		adressePro.ligneAdresse2 = "LIEU DIT ADRESSE_PRO";
		adresseCorr.codeTypeAdresse = "2";
		adresseCorr.ligneAdresse2 = "LIEU DIT ADRESSE_CORR";
		
		adresses.add(adressePerso);
		adresses.add(adressePro);
		adresses.add(adresseCorr);
	}
	
	/**
	 * Permet de s'assurer que le client à des numéros de téléphone.
	 */
	public void initierMedia() {
		// Numéro de téléphone privé BP
		Media media_BP = new Media();
		media_BP.codeNatureCorrespondance = "P";
		media_BP.codeNatureMedia = "1";
		media_BP.referenceMedia = "0122334455";
		// Numéro de téléphone privé CE
		Media media_CE = new Media();
		media_CE.codeNatureCorrespondance = "0";
		media_CE.codeNatureMedia = "1";
		media_CE.referenceMedia = "0222334455";
		// Ajout des médias
		medias.add(media_CE);
		medias.add(media_BP);
	}
}
