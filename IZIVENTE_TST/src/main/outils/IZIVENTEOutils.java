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
	 * Transforme un identifiant num�rique unique en identifiant texte unique.
	 * @param numero le libelle � transcoder contenant un num�ro (les autres caract�res seront ignor�s).
	 * @return la chaine transcod�e (pour sa partie num�rique).
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
			
			// Connexion � IZIBOX recette
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connect = null;
			if (distributeur == Constantes.CAS_BP)  {
				connect = DriverManager.getConnection("jdbc:sqlserver://SWUDFRSQL2441.child.net:30206", "parc_sisp", "Ana_th3me");
			} else {
				connect = DriverManager.getConnection("jdbc:sqlserver://SWUDFRSQL2442.child.net:30208", "parc_sisp", "Ana_th3me");
			}
			Statement st = connect.createStatement();
			
			// R�cup�ration de la date de signature
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
			// R�cup�ration de la date de l'�v�nement TRA
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
			// R�cup�ration de la date de financement (PP)
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
			// R�cup�ration de la date de fin de d�lai de retractation (CR)
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
			// D�terminer la date de remplacement
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.add(GregorianCalendar.DAY_OF_YEAR, -15);
			Date date = calendar.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
			//System.out.println("On utilisera pour le murissement la date : " + sdf.format(date));
			String nouvelleDate = null;
			String dateJour = null;
			int modification = 0;

			// Si une date est impos�e on la prend si on utilise une nouvelle date � partir du formatage de la date -14j
			if (dateSaisie == null) {
				nouvelleDate = sdf.format(date);
			} else if (sdf.parse(dateSaisie) == null) {
				nouvelleDate = sdf.format(date);
			} else {
				nouvelleDate = dateSaisie;
			}
			dateJour = sdf.format(new Date());
			
			System.out.println("On utilisera pour le murissement la date : " + nouvelleDate);
			
			// Connexion � IZIBOX recette
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
				// Pour les cr�dits renouvelables on modifie la date de fin de d�lai de retractation
				SQL = "update [IZIBOX].[dbo].[SIMULATION_OPC_CONTRAT] set SIOC_DATE_FIN_DELAI_RETRA = '" + dateJour + "' where sioc_id = " + siocID;
			} else {
				// Pour les cr�dit personnels on modifie la date de financement
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
	 * Permet de r�cup�rer les n derniers caract�res d'une cha�ne
	 * @param chaine la chaine de caract�re dont on doit r�cup�rer les n derniers caract�res
	 * @param nombre nombre de caract�res � r�cup�rer en fin de chaine.
	 * @return la chaine de caract�re compos� les n derniers caract�res de la chaine en param�tre
	 */
	public static final String derniersCaracteres(String chaine, int nombre) {
	    if (chaine.length() <= nombre)
	       return(chaine);
	    else
	        return(chaine.substring(chaine.length() - nombre));
	}
}
