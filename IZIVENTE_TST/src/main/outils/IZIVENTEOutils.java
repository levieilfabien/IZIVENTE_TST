package main.outils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import outils.PropertiesOutil;
import main.constantes.Constantes;

public class IZIVENTEOutils {

	/**
	 * Transforme un identifiant numérique unique en identifiant texte unique.
	 * @param numero le libelle à transcoder contenant un numéro (les autres caractères seront ignorés).
	 * @return la chaine transcodée (pour sa partie numérique).
	 */
	public static String lettreFromNumerique(String numero) {
		String retour = "";
		
		for (int i = 0; i < numero.length(); i++) {
			switch(numero.charAt(i)) {
				case '0' : retour = retour.concat("Z"); break;
				case '1' : retour = retour.concat("U"); break;
				case '2' : retour = retour.concat("D"); break;
				case '3' : retour = retour.concat("T"); break;
				case '4' : retour = retour.concat("Q"); break;
				case '5' : retour = retour.concat("C"); break;
				case '6' : retour = retour.concat("S"); break;
				case '7' : retour = retour.concat("E"); break;
				case '8' : retour = retour.concat("H"); break;
				case '9' : retour = retour.concat("N"); break;
				default : retour = retour + numero.charAt(i); break;
			}
		}	
		return retour;
	}
	
	public static List<String> consultation(String siocID, int distributeur) {
		boolean reussite = true;
		String temp = "";
		List<String> retour = new LinkedList<String>();
		
		try {
			int lecture = 0;
			boolean ajout = false;
			
			// Connexion à IZIBOX recette
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connect = null;
			if (distributeur == Constantes.CAS_BP)  {
				connect = DriverManager.getConnection("jdbc:sqlserver://SWUDFRSQL2441.child.net:30206", "parc_sisp", "Ana_th3me");
			} else {
				connect = DriverManager.getConnection("jdbc:sqlserver://SWUDFRSQL2442.child.net:30208", "parc_sisp", "Ana_th3me");
			}
			Statement st = connect.createStatement();
			
			// Récupération de la date de signature
			String SQL = "select sioc_id, SIOC_DATE_SIGNATURE_CONTRAT from [IZIBOX].[dbo].[SIMULATION_OPC_CONTRAT] simu where sioc_id = " + siocID;
			ResultSet rs = st.executeQuery(SQL);
			while (rs.next()) {
				retour.add(rs.getString("SIOC_DATE_SIGNATURE_CONTRAT"));
				ajout = true;
				break;
			} 
			if(!ajout) {
				retour.add("");
			}
			ajout = false;
			// Récupération de la date de l'évènement TRA
			SQL = "select sioc_id, even_date, EVEN_RANG, EVEN_SOURCE from [IZIBOX].[dbo].evenement where sioc_id = " + siocID + "and even_source = 'FFITRA07';";
			rs = st.executeQuery(SQL);
			while (rs.next()) {
				retour.add(rs.getString("even_date"));
				ajout = true;
				break;
			} 			
			if(!ajout) {
				retour.add("");
			}
			ajout = false;
			// Récupération de la date de financement (PP)
			SQL = "select sioc_id, fina_date, FINA_FLAG_SUPP, FINA_ALERTE_REJET, FINA_FLAG_TRANSMIS_GESTION from [IZIBOX].[dbo].FINANCEMENT where sioc_id = "+ siocID;
			rs = st.executeQuery(SQL);
			while (rs.next()) {
				retour.add(rs.getString("fina_date")); 
				ajout = true;
				break;
			}
			if(!ajout) {
				retour.add("");
			}
			ajout = false;
			// Récupération de la date de fin de délai de retractation (CR)
			SQL = "select SIOC_DATE_FIN_DELAI_RETRA from [IZIBOX].[dbo].[SIMULATION_OPC_CONTRAT] where sioc_id = " + siocID;
			rs = st.executeQuery(SQL);
			while (rs.next()) {
				retour.add(rs.getString("SIOC_DATE_FIN_DELAI_RETRA"));
				ajout = true;
				break;
			}
			if(!ajout) {
				retour.add("");
			}
			ajout = false;

			connect.close();
		
			
		} catch (SQLException e) {
			e.printStackTrace();
			reussite = false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			reussite = false;
		}
		
		
		return retour;
	}
	
	public static boolean murissement(String siocID, int distributeur, boolean creditRenouvelable, String dateSaisie) {
		boolean retour = true;
		
		try {
			// Déterminer la date de remplacement
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.add(GregorianCalendar.DAY_OF_YEAR, -15);
			Date date = calendar.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
			//System.out.println("On utilisera pour le murissement la date : " + sdf.format(date));
			String nouvelleDate = null;
			String dateJour = null;
			int modification = 0;

			// Si une date est imposée on la prend si on utilise une nouvelle date à partir du formatage de la date -14j
			if (dateSaisie == null) {
				nouvelleDate = sdf.format(date);
			} else if (sdf.parse(dateSaisie) == null) {
				nouvelleDate = sdf.format(date);
			} else {
				nouvelleDate = dateSaisie;
			}
			dateJour = sdf.format(new Date());
			
			System.out.println("On utilisera pour le murissement la date : " + nouvelleDate);
			
			// Connexion à IZIBOX recette
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connect = null;
			if (distributeur == Constantes.CAS_BP)  {
				connect = DriverManager.getConnection(PropertiesOutil.getInfoEnvConstante("URL_BDD_IZIBOX_BP"), PropertiesOutil.getInfoEnvConstante("USER_BDD_IZIBOX"), PropertiesOutil.getInfoEnvConstante("PWD_BDD_IZIBOX"));
			} else {
				connect = DriverManager.getConnection(PropertiesOutil.getInfoEnvConstante("URL_BDD_IZIBOX_CE"), PropertiesOutil.getInfoEnvConstante("USER_BDD_IZIBOX"), PropertiesOutil.getInfoEnvConstante("PWD_BDD_IZIBOX"));
			}
			Statement st = connect.createStatement();
			
			// Modification de la date de signature
			String SQL = "select sioc_id, SIOC_DATE_SIGNATURE_CONTRAT from [IZIBOX].[dbo].[SIMULATION_OPC_CONTRAT] simu where sioc_id = " + siocID;
			ResultSet rs = st.executeQuery(SQL);
			while (rs.next()) {
				System.out.println("La date de signature du contrat initiale est " + rs.getString("SIOC_DATE_SIGNATURE_CONTRAT"));
			}
			
			SQL ="update [IZIBOX].[dbo].[SIMULATION_OPC_CONTRAT] set SIOC_DATE_SIGNATURE_CONTRAT = '" + nouvelleDate + "' where sioc_id = " + siocID;
			modification = st.executeUpdate(SQL);
			
			SQL = "update [IZIBOX].[dbo].evenement set even_date = '" + nouvelleDate + "' where sioc_id = " + siocID + " and even_source = 'FFITRA07';";
			modification += st.executeUpdate(SQL);
			
			if (creditRenouvelable) {
				// Pour les crédits renouvelables on modifie la date de fin de délai de retractation
				SQL = "update [IZIBOX].[dbo].[SIMULATION_OPC_CONTRAT] set SIOC_DATE_FIN_DELAI_RETRA = '" + dateJour + "' where sioc_id = " + siocID;
			} else {
				// Pour les crédit personnels on modifie la date de financement
				SQL = "update [IZIBOX].[dbo].FINANCEMENT set fina_date = '" + dateJour + "' where sioc_id = " + siocID + " and FINA_RANG_FINANCEMENT = 1;";
			}
			modification += st.executeUpdate(SQL);
			
			connect.close();
			
			if (modification < 3) {
				retour = false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			retour = false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			retour = false;
		} catch (ParseException e) {
			retour = false;
			e.printStackTrace();
		} 
		
		
		return retour;
	}
	
	public static void main(String[] argv) {
		//System.out.println(IZIVENTEOutils.lettreFromNumerique("NM_9384464"));
		murissement("5884749", Constantes.CAS_BP, false, null);
	}
	/**
	 * Permet de récupérer les n derniers caractères d'une chaîne
	 * @param chaine la chaine de caractère dont on doit récupérer les n derniers caractères
	 * @param nombre nombre de caractères à récupérer en fin de chaine.
	 * @return la chaine de caractère composé les n derniers caractères de la chaine en paramètre
	 */
	public static final String derniersCaracteres(String chaine, int nombre) {
	    if (chaine.length() <= nombre)
	       return(chaine);
	    else
	        return(chaine.substring(chaine.length() - nombre));
	}
}
