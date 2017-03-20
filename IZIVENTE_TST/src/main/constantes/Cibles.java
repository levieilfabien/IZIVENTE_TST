package main.constantes;

import beans.CibleBean;
import constantes.Clefs;

public class Cibles {

	//////////////////////////////////////////////////////CIBLE A USAGE UNIQUE ///////////////////////////////////////////////////////////////
	/**
	 * Identification de la zone de saisie pour un Jeton.
	 */
	public static final CibleBean SAISIE_JETON =  new CibleBean(Clefs.NAME, "reroutage:data:data");
	
	/**
	 * Identification pour la validation d'un jeton saisie dans l'interface.
	 */
	public static final CibleBean VALIDER_SAISIE_JETON = new CibleBean(Clefs.ID, "reroutage:doReroutage");
	
	/**
	 * Identification du bouton "Face à face" dans la popup affichée lors de l'accès à un dossier.
	 */
	public static final CibleBean BOUTON_POPUP_FACE_A_FACE = new CibleBean(Clefs.VALEUR, "Face à face");
	
	/**
	 * Identification du bouton "Face à Face" dans la popup affichée lors de la redirection depuis l'IHM suite à une signature electronique.
	 */
	public static final CibleBean BOUTON_POPUP_FACE_A_FACE_MAJ = new CibleBean(Clefs.VALEUR, "Face à Face");
	
	/**
	 * Bouton situé dans le premier menu permettant de choisir l'option "Ouverture d'un dossier".
	 */
	public static final CibleBean BOUTON_MENU_OUVERTURE_DOSSIER = new CibleBean(Clefs.TEXTE_COMPLET, "Ouverture d'un dossier");
	
	/**
	 * Bouton situé dans le premier menu permettant de choisir l'option "Ouverture d'un dossier" pour un full crédit CREODIS
	 */
	public static final CibleBean BOUTON_MENU_OUVERTURE_DOSSIER_FC = new CibleBean(Clefs.CRITERES_ITERATIF, "*", "id=homeForm:izv_cp_fc_instr", CibleBean.RECHERCHE, CibleBean.CRITERE_TEXTE + "=Ouverture d'un dossier");
	/**
	 * Bouton situé dans le premier menu permettant de choisir l'option "Ouverture FACELIA" pour un crédit renouvelable FACELIA.
	 */
	public static final CibleBean BOUTON_MENU_OUVERTURE_DOSSIER_FACELIA = new CibleBean(Clefs.TEXTE_COMPLET, "Ouverture FACELIA");
	/**
	 * Bouton situé dans le premier menu permettant de choisir l'option "Ouverture IZICARTE".
	 */
	public static final CibleBean BOUTON_MENU_OUVERTURE_IZICARTE = new CibleBean(Clefs.TEXTE_COMPLET, "Ouverture IZICARTE");
	
	/**
	 * Bouton situé dans l'écran principal permettant de choisir l'option de création d'un nouveau dossier.
	 */
	public static final CibleBean BOUTON_MENU_NOUVEAU_DOSSIER = new CibleBean(Clefs.VALEUR, "Nouveau dossier");
	
	/**
	 * Bouton situé dans l'écran principal permettant la reprise d'un dossier en cours
	 */
	public static final CibleBean BOUTON_MENU_REPRISE_DOSSIER = new CibleBean(Clefs.TEXTE_COMPLET, "Reprise d'un dossier en cours");
	
	/**
	 * Element du menu pour l'accès aux dossiers amortissables (PP).
	 */
	public static final CibleBean ELEMENT_MENU_CREDIT_AMORTISSABLE = new CibleBean(Clefs.TEXTE_PARTIEL, "Crédit amortissable");
	
	/**
	 * Element du menu pour l'accès à la liste des CA.
	 */
	public static final CibleBean ELEMENT_MENU_LISTE_CA = new CibleBean(Clefs.NAME, "menuForm:izv_ca_list");
	
	/**
	 * Selecteur pour le choix d'univers de crédit (ex : Travaux).
	 */
	public static final CibleBean SELECTEUR_UNIVERS_CREDIT = new CibleBean(Clefs.NAME, "form_demande:universField:univers");
	
	/**
	 * Selecteur pour le choix d'une situation de vente pour un produit CR (ex : Produit d'épargne).
	 */
	public static final CibleBean SELECTEUR_SITUATION_VENTE_CR = new CibleBean("form_demandeCR:situationVenteField:situationVente");
	
	/**
	 * Case à cocher pour l'absence de financement
	 */
	public static final CibleBean CASE_SELECTION_AUCUN_FINANCEMENT = new CibleBean("form_demandeCR:financementLabel:j_id230");
	
	/**
	 * Champ de saisie pour le montant du premier financement CR.
	 */
	public static final CibleBean SAISIE_MONTANT_PREMIER_FINANCEMENT_CR = new CibleBean("form_demandeCR:montant1erFinancementFieldRequired:montant1erFinancement");
	
	/**
	 * Champ de saisie pour la mensualité souhaité du PP
	 */
	public static final CibleBean SAISIE_MENSUALITE_PP = new CibleBean("form_demande:mensualiteField:mensualite");
	
	/**
	 * Champ de saisie pour la mensualité souhaitée du CR.
	 */
	public static final CibleBean SAISIE_MENSUALITE_CR = new CibleBean("form_demandeCR:mensualiteField:mensualite");
	/**
	 * Selecteur pour le choix de l'offre de crédit (ex : CREDIT TRAVAUX).
	 */
	public static final CibleBean SELECTEUR_OFFRE_CREDIT = new CibleBean("form_demande:offreField:offre");
	/**
	 * Selecteur pour le choix de l'offre de crédit pour un CR (ex : FACELIA).
	 */
	public static final CibleBean SELECTEUR_OFFRE_CREDIT_CR = new CibleBean("form_demandeCR:offreField:offre");
	/**
	 * Selecteur pour le choix de l'objet à financer (Ex : Cuisine).
	 */
	public static final CibleBean SELECTEUR_OBJET_FINANCE = new CibleBean(Clefs.NAME, "form_demande:objetAfinancerField:objetAfinancer");
	
	/**
	 * Champs de saisie libre pour le cout du projet lors de la création d'un dossier.
	 */
	public static final CibleBean SAISIE_COUT_PROJET = new CibleBean("form_demande:coutField:cout");
	/**
	 * Selecteur pour le choix d'un report pour la première mensualité.
	 */
	public static final CibleBean SELECTEUR_REPORT_PREMIERE_MENS = new CibleBean("form_demande:report_premiere_mensualiteField:report_mensualitee");
	
	/**
	 * Champs de saisie pour le montant de la demande.
	 */
	public static final CibleBean SAISIE_MONTANT_DEMANDE = new CibleBean("form_demande:montant_demandeField:montant_demande");
	
	/**
	 * Selecteur permettan le choix d'un couple "durée/montant".
	 */
	public static final CibleBean SELECTEUR_MONTANT_DUREE_DEMANDE = new CibleBean("form_demande:montant_dureeField:montant_duree");
	
	/**
	 * Champs de saisie pour la durée du différé partiel.
	 */
	public static final CibleBean SAISIE_DUREE_DIFFERE_PARTIEL = new CibleBean("form_demande:differe_amortissementField:moisAmor");
	
	/**
	 * Champs de saisie pour la durée souhaitée.
	 */
	public static final CibleBean SAISIE_DUREE_DEMANDE = new CibleBean("form_demande:dureeField:duree");
	
	/**
	 * Libelle de l'onglet pour l'ajout d'un participant lors de la saisie d'un dossier.
	 */
	public static final CibleBean LIBELLE_ONGLET_AJOUT_PARTICIPANT = new CibleBean("ongletAjoutParticipant_lbl");
	
	/**
	 * Bouton d'ajout d'un conjoint ou d'un concubin pour un dossier en cours de saisie.
	 */
	public static final CibleBean BOUTON_AJOUT_CONJOINT = new CibleBean(Clefs.VALEUR, "Ajouter le conjoint / concubin");
	
	/**
	 * Radio bouton pour la selection du participant 1 au crédit.
	 */
	public static final CibleBean RADIO_SELECTION_PARTICIPANT1 = new CibleBean("form_ongletSyntheseParticipant:tableParticipants:1:radio_selection:0");
	//form_ongletSyntheseParticipant:tableParticipants:1:radio_selection:0
																				
	/**
	 * Radio bouton pour la selection du participant 0 au crédit.
	 */
	public static final CibleBean RADIO_SELECTION_PARTICIPANT0 = new CibleBean("form_ongletSyntheseParticipant:tableParticipants:0:radio_selection:0");
																				
	/**
	 * Radio bouton pour la selection du participant 2 au crédit.
	 */
	public static final CibleBean RADIO_SELECTION_PARTICIPANT2 = new CibleBean("form_ongletSyntheseParticipant:tableParticipants:2:radio_selection:0");
	
	/**
	 * Radio bouton pour la selection du participant 3 au crédit.
	 */
	public static final CibleBean RADIO_SELECTION_PARTICIPANT3 = new CibleBean("form_ongletSyntheseParticipant:tableParticipants:3:radio_selection:0");
	
	/**
	 * Radio bouton pour la sélection "Sans assurance" pour un dossier CREODIS
	 */
	public static final CibleBean RADIO_SELECTION_SANS_ASS_CR = new CibleBean("form_assurance:tableAssurance:0:radio_selection:0");
	/**
	 * Radio bouton pour la sélection de l'assurance "Option 3" pour un dossier CREODIS
	 */
	public static final CibleBean RADIO_SELECTION_ASS_1_CR = new CibleBean("form_assurance:tableAssurance:1:radio_selection:0");
	
	/**
	 * Radio bouton pour la sélection "Sans assurance" pour un dossier FACELIA
	 */
	public static final CibleBean RADIO_SELECTION_SANS_ASS_FAC = new CibleBean("form_ongletSyntheseParticipant:j_id1648:j_id1659:0");

	/**
	 * Entete pour le tableau d'assurance CE.
	 */
	public static final CibleBean ELEMENT_ENTETE_TABLEAU_ASSURANCE_CE = new CibleBean(Clefs.CLASSE, "enteteTableauAssurancesCE");
	
	/**
	 * Cases des réponses pour la saisie d'assurance d'une personne senior (>61 ans) 
	 * //input[@class="questionnaireerreurIntcheck"]/..[contains(text(),"Non")]
	 */
	public static final CibleBean CASE_SELECTION_REPONSE_ASSURANCE_NON = new CibleBean(Clefs.CRITERES_ITERATIF, "*", "text=Non", CibleBean.PERE,  "input", "class=questionnaireerreurIntcheck"); 

	/**
	 * Radio bouton pour la sélection d'assurance DIM pour un Senior éligible aux assurances
	 */
	public static final CibleBean RADIO_SELECTION_ASSURANCE_DIM = new CibleBean("form_ongletSyntheseParticipant:tableChoixAssurance:0:radio_selection_ass:0");
		
	/**
	 * Selectionner la première assurance proposée
	 */
	
	/**
	 * Element identifiant du tableau de notation.
	 */
	public static final CibleBean ELEMENT_TABLEAU_NOTATION = new CibleBean("form_notation:tab_notation:tb");
	
	/**
	 * Element identifiant du tableau de notation.
	 */
	public static final CibleBean ELEMENT_TABLEAU_PRELEVEMENT = new CibleBean("validation:panel_compte_header");
	
	/**
	 * Element identifiant que l'on est dans la fin d'instruction.
	 */
	public static final CibleBean ELEMENT_FIN_INSTRUCTION = new CibleBean("panelPopupFinInstruction_header");
	
	/**
	 * Element de la popup de confirlmation de validation d'un OPC CR.
	 */
	public static final CibleBean ELEMENT_POPUP_CONFIRMATION_VALIDATION_OPC_CR = new CibleBean("id_popupRecapCDiv");
	
	/**
	 * Element contenant le numéro FFI à récupérer.
	 */
	public static final CibleBean ELEMENT_SPAN_NUMERO_FFI = new CibleBean("numero_dossierField:numero_dossier");
	
	/**
	 * Element contenant le numéro FFI à récupérer pour un FACELIA
	 */
	public static final CibleBean ELEMENT_SPAN_NUMERO_FFI_CR = new CibleBean("edition:numero_dossierField:numero_dossier");
		
	/**
	 * Element indiquant l'état d'avancement final du chargement de la signature electronique.
	 */
	public static final CibleBean ELEMENT_POPUP_BARRE_CHARGEMENT_SIGNATURE_ELECTRONIQUE = new CibleBean("progressBarTermine");
			//new CibleBean(Clefs.CRITERES_ITERATIF, "*", "id=divProgressBarTermine", "text=terminé");
	
	/**
	 * Element indiquant la présence du formulaire de complétude (contrôle des confirmités)
	 */
	public static final CibleBean ELEMENT_FORMULAIRE_COMPLETUDE = new CibleBean("completude_form");
	
	/**
	 * Bouton pour l'ajout d'un nouveau participant.
	 */
	public static final CibleBean BOUTON_AJOUT_AUTRE_PARTICIPANT = new CibleBean("form_ongletSyntheseParticipant:nouvelAjout");
	
	/**
	 * Bouton pour l'ajout d'un participant tiers suite à une recherche.
	 */
	public static final CibleBean BOUTON_AJOUT_TIERS = new CibleBean("formParticipant:ajouterTiers");
	
	/**
	 * Bouton pour choisir de ne pas ajouter de co emprunteur.
	 */
	public static final CibleBean BOUTON_AUCUN_COEMPRUNTEUR = new CibleBean(Clefs.VALEUR, "Aucun co-emprunteur");
	
	/**
	 * Selecteur pour le choix d'identification du nouveau participant.
	 */
	public static final CibleBean SELECTEUR_IDENTIFICATION_PARTICIPANT = new CibleBean("formParticipant:selection:optionPart");
	/**
	 * Champs de saisie pour le numéro de personne physique.
	 */
	public static final CibleBean SAISIE_NUMERO_PERS_PHY = new CibleBean("formParticipant:numero_compteRech:numeroField");
	
	/**
	 * Bouton pour la validation des détails du tiers 1.
	 */
	public static final CibleBean BOUTON_VALIDATION_DETAILS_TIERS_1 = new CibleBean("form_ongletDetailsTiers:valider1");
	
	/**
	 * Bouton pour la validation des détails du tiers sur BP.
	 */
	public static final CibleBean BOUTON_VALIDATION_DETAILS_TIERS_BP = new CibleBean ("form_ongletDetailsTiers:validerTiersEmp");
	
	/**
	 * Bouton pour la validation de la synthèse tiers.
	 */
	public static final CibleBean BOUTON_VALIDER_SYNTHESE_TIERS = new CibleBean(Clefs.XPATH, "//div[@id=\"popupSyntheseContentDiv\"]//input[@value=\"Valider\"]");
	
	/**
	 * Bouton pour répondre "Oui" à la DES.
	 */
	public static final CibleBean BOUTON_OUI_DES = new CibleBean("form_ongletSyntheseParticipant:btOuiDES");
	
	/**
	 * Bouton radio pour la sélection de l'assurance
	 */
	public static final CibleBean RADIO_ASS_OUI = new CibleBean("form_ongletSyntheseParticipant:tableChoixAssurance:0:radio_selection_ass:0");
	
	/**
	 * Selecteur pour le choix du role du participant.
	 */
	public static final CibleBean SELECTEUR_ROLE_PARTICIPANT = new CibleBean("form_ongletSyntheseParticipant:roleCA:roleParticipant");
	
	/**
	 * Bouton pour la validation de la liste des participants.
	 */
	public static final CibleBean BOUTON_VALIDER_LISTE_PARTICIPANT = new CibleBean("form_buttons:validerListParticipants");
	
	/**
	 * Bouton d'accès à la validation.
	 */
	public static final CibleBean BOUTON_ACCES_VALIDATION_OPC = new CibleBean("goValidate");
	
	/**
	 * Bouton pour la validation de l'OPC.
	 */
	public static final CibleBean BOUTON_VALIDER_OPC = new CibleBean("id_confirme_opc");
	
	/**
	 * Bouton de la validation de l'OPC pour un CR couplé.
	 */
	public static final CibleBean BOUTON_VALIDER_OPC_CR = new CibleBean("validation:id_confirme_opc");
	
	/**
	 * Bouton de la validation de l'OPC pour un Creodis sans co emprunteur.
	 */
	public static final CibleBean BOUTON_VALIDER_CREODIS_CR = new CibleBean("validation:id_valider");
	
	/**
	 * Bout de validation de la simulation pour retour à l'écran de validation de l'offre
	 */
	public static final CibleBean BOUTON_VALIDER_SIMULATION_PP = new CibleBean("resultDossier:Valider");
	/**
	 * Bouton pour la validaiton de la popup des justificatifs.
	 */
	public static final CibleBean BOUTON_POPUP_VALIDER_JUSTIFICATIFS = new CibleBean("form_justif:goValider");
	
	/**
	 * Bouton pour l'impression de la synthèse.
	 */
	public static final CibleBean BOUTON_IMPRIMER_SYNTHESE = new CibleBean("form_synthese_client:imprimer");
	
	/**
	 * Bouton pour l'impression de la synthèse (depuis l'instruction).
	 */
	public static final CibleBean BOUTON_IMPRIMER_SYNTHESE_INSTRUCTION = new CibleBean("imprimer_synthese");
	
	/**
	 * Le bouton pour l'impression de la liasse.
	 */
	public static final CibleBean BOUTON_IMPRIMER_LIASSE = new CibleBean("form_contrat_imprimer:sefas");
	
	/**
	 * Le bouton pour l'impression de la liasse pour un CR Couplé.
	 */
	public static final CibleBean BOUTON_IMPRIMER_LIASSE_CR = new CibleBean(Clefs.VALEUR, "Editer");
	
	/**
	 * Le bouton de finalisation de l'édition et de passage à l'octroi.
	 */
	public static final CibleBean BOUTON_TERMINER_EDITION = new CibleBean("form_contrat_imprimer:goTerminer");
	
	/**
	 * Le bouton de finalisation de l'édition et de passage à l'octroi pour un CR.
	 */
	public static final CibleBean BOUTON_TERMINER_EDITION_CR = new CibleBean("goTerminer");
	
	/**
	 * Le bouton de passage à l'octroi depuis la liste des dossiers.
	 */
	public static final CibleBean BOUTON_PASSAGE_OCTROI = new CibleBean("resultDossier:Octroi");
	
	/**
	 * Le bouton pour le passage à l'octroi pour un CR couplé.
	 */
	public static final CibleBean BOUTON_PASSAGE_OCTROI_CR = new CibleBean("goAfficherPopupOctroi");
	
	/**
	 * La liste des coches dans le bloc "Liste des dossiers".
	 */
	public static final CibleBean COCHES_LISTE_DOSSIER = new CibleBean(Clefs.XPATH, ".//*[@id='resultDossier:tableDossierEnre:tb']/tr/td/table/tbody/tr/td/input");
	
	/**
	 * Libelle de choix "Acception/Refus" pour le choix "Acceptation".
	 */
	public static final CibleBean LIBELLE_ACCEPTATION = new CibleBean(Clefs.TEXTE_PARTIEL, "Acceptation");
	
	/**
	 * Champs pour la saisie de la date associée à la decision.
	 */
	public static final CibleBean SAISIE_DATE_DECISION = new CibleBean("dateFinancement:dateFieldInputDate");
	
	/**
	 * Bouton pour la finalisation de la saisie du dossier
	 */
	public static final CibleBean BOUTON_FINALISATION_OCTROI = new CibleBean("finish");
	
	/**
	 * Bouton pour la finalisation de la saisie du dossier pour un CR
	 */
	public static final CibleBean BOUTON_FINALISATION_OCTROI_CR = new CibleBean(Clefs.VALEUR, "Terminer");
	
	/**
	 * Bouton pour la finalisation de la saisie d'un dossier FACELIA
	 */
	public static final CibleBean BOUTON_FINALISATION_OCTROI_FACELIA = new CibleBean("decisionCR:finish");
	/**
	 * Bouton dans la liste des dossier pour la mise en force d'un CR.
	 */
	public static final CibleBean BOUTON_MISE_EN_FORCE_CR = new CibleBean("resultDossier:btnForce");
	
	/**
	 * Bouton pour le choix oui dans la popup finale.
	 */
	public static final CibleBean BOUTON_POPUP_OUI_CONFIRMATION_OCTROI = new CibleBean("form_finalOctroi:goYes");
	/**
	 * Bouton pour le choix terminer dans la popup finale.
	 */
	public static final CibleBean BOUTON_POPUP_TERMINER_CONFIRMATION_OCTROI = new CibleBean("form_finalOctroi:goTerminer");
	
	/**
	 * Bouton dans la popup de signature electronique pour la vente d'un CR Couplé.
	 */
	public static final CibleBean BOUTON_POPUP_INSTRUIRE_CR_COUPLE = new CibleBean(Clefs.VALEUR, "Instruire un crédit renouvelable");
	
	/**
	 * Bouton pour la vente d'un CR IZICARTE couplé avec le PP déjà saisie.
	 */
	public static final CibleBean BOUTON_INSTRUIRE_IZICARTE_COUPLE = new CibleBean("form_contrat_imprimer:goIzicarte");
	
	/**
	 * Choix "Avec assurance" pour un participant à un CR couplé IZICARTE.
	 */
	public static final CibleBean RADIO_AVEC_ASS_CR = new CibleBean(Clefs.TEXTE_PARTIEL, "Avec assurance");
	
	/**
	 * Choix d'une assurance pour un participant à un FACELIA.
	 */
	public static final CibleBean RADIO_AVEC_ASS_FACELIA = new CibleBean("form_ongletSyntheseParticipant:j_id1665:0");
	
	/**
	 * Choix d'une assurance pour un participant à une IZICARTE
	 */
	public static final CibleBean RADIO_AVEC_ASS_IZICARTE = new CibleBean("form_ongletSyntheseParticipant:j_id1704:0");
	
	/**
	 * Choix d'une assurance décès pour l'emprunteur d'un FACELIA
	 */
	//public static final CibleBean RADIO_ASSURANCE_DECES_FACELIA_OUI = new CibleBean("form_ongletSyntheseParticipant:j_id1682:0");
	public static final CibleBean RADIO_ASSURANCE_DECES_FACELIA_OUI = new CibleBean(Clefs.XPATH, ".//*[text()='- Décès :']/../../../td[2]/table/tbody/tr/td[1]/input");
	
	/**
	 * Absence d'une assurance décès pour l'emprunteur d'un FACELIA
	 */
	//public static final CibleBean RADIO_ASSURANCE_DECES_FACELIA_NON = new CibleBean("form_ongletSyntheseParticipant:j_id1682:1");
	public static final CibleBean RADIO_ASSURANCE_DECES_FACELIA_NON = new CibleBean(Clefs.XPATH, ".//*[text()='- Décès :']/../../../td[2]/table/tbody/tr/td[2]/input");
	
	/**
	 * Choix d'une assurance décès pour l'emprunteur d'une IZICARTE
	 */
	public static final CibleBean RADIO_ASSURANCE_DECES_IZICARTE_OUI = new CibleBean("form_ongletSyntheseParticipant:j_id1718:0");
	
	/**
	 * Absence d'une assurance décès pour l'emprunteur d'une IZICARTE
	 */
	public static final CibleBean RADIO_ASSURANCE_DECES_IZICARTE_NON = new CibleBean("form_ongletSyntheseParticipant:j_id1718:1");
	
	/**
	 * Choix d'une assurance invalidité pour l'emprunteur d'un FACELIA
	 */
	public static final CibleBean RADIO_ASSURANCE_INVALD_FACELIA_OUI = new CibleBean("form_ongletSyntheseParticipant:j_id1690:0");
	
	/**
	 * Absence d'une assurance invalidité pour l'emprunteur d'un FACELIA
	 */
	public static final CibleBean RADIO_ASSURANCE_INVALD_FACELIA_NON = new CibleBean("form_ongletSyntheseParticipant:j_id1690:1");
	
	/**
	 * Choix d'une assurance invalidité pour l'emprunteur d'un IZICARTE
	 */
	public static final CibleBean RADIO_ASSURANCE_INVALD_IZICARTE_OUI = new CibleBean("form_ongletSyntheseParticipant:j_id1726:0");
	
	/**
	 * Absence d'une assurance invalidité pour l'emprunteur d'un IZICARTE
	 */
	public static final CibleBean RADIO_ASSURANCE_INVALD_IZICARTE_NON = new CibleBean("form_ongletSyntheseParticipant:j_id1726:1");
	
	/**
	 * Choix d'une assurance incapacité pour l'emprunteur d'un FACELIA
	 */
	public static final CibleBean RADIO_ASSURANCE_INCAP_FACELIA_OUI = new CibleBean("form_ongletSyntheseParticipant:j_id1698:0");
	
	/**
	 * Absence d'une assurance incapacité pour l'emprunteur d'un FACELIA
	 */
	public static final CibleBean RADIO_ASSURANCE_INCAP_FACELIA_NON = new CibleBean("form_ongletSyntheseParticipant:j_id1698:1");
	
	/**
	 * Choix d'une assurance maladie pour l'emprunteur d'un IZICARTE
	 */
	public static final CibleBean RADIO_ASSURANCE_MALA_IZICARTE_OUI = new CibleBean("form_ongletSyntheseParticipant:j_id1734:0");
	
	/**
	 * Absence d'une assurance maladie pour l'emprunteur d'un IZICARTE
	 */
	public static final CibleBean RADIO_ASSURANCE_MALA_IZICARTE_NON = new CibleBean("form_ongletSyntheseParticipant:j_id1734:1");
	
	/**
	 * Choix d'une assurance perte d'emploi pour l'emprunteur d'un FACELIA
	 */
	public static final CibleBean RADIO_ASSURANCE_PERTE_FACELIA_OUI = new CibleBean("form_ongletSyntheseParticipant:j_id1706:0");
	
	/**	
	 * Choix d'une assurance perte d'emploi pour l'emprunteur d'un FACELIA
	 */
	public static final CibleBean RADIO_ASSURANCE_PERTE_FACELIA_NON = new CibleBean("form_ongletSyntheseParticipant:j_id1706:1");

	/**
	 * Choix d'une assurance perte d'emploi pour l'emprunteur d'un IZICARTE
	 */
	public static final CibleBean RADIO_ASSURANCE_PERTE_IZICARTE_OUI = new CibleBean("form_ongletSyntheseParticipant:j_id1742:0");
	
	/**
	 * Choix d'une assurance perte d'emploi pour l'emprunteur d'un IZICARTE
	 */
	public static final CibleBean RADIO_ASSURANCE_PERTE_IZICARTE_NON = new CibleBean("form_ongletSyntheseParticipant:j_id1742:1");
	
	/**
	 * Bouton de validation du choix d'une assurance pour un CR (Facelia et IZICARTE)
	 */
	public static final CibleBean BOUTON_VALIDATION_ASS_CR = new CibleBean("form_ongletSyntheseParticipant:btOuiGARVald");
	/**
	 * Choix "Sans assurance" pour un participant à un CR
	 */
	public static final CibleBean RADIO_SANS_ASS_CR = new CibleBean(Clefs.TEXTE_PARTIEL, "Sans assurance");
	
	/**
	 * Choix "Sans assurance" pour un participant à un FACELIA
	 */
	public static final CibleBean RADIO_SANS_ASS_FACELIA = new CibleBean("form_ongletSyntheseParticipant:j_id1665:1");
	
	/**
	 * Champs de saisie (en lecture seule) indiquant le CMA pour le CR couplé en cours de saisie.
	 * Si ce montant de CMA est inférieur au montant saisie, alors il est impossibnle de valider le formulaire de CR.
	 */
	public static final CibleBean SAISIE_MONTANT_CMA_CR = new CibleBean("form_demandeCR:cmaField1:cmacontrat");
	
	////////////////////////////////////////////////////// CIBLE REUTILISABLE ///////////////////////////////////////////////////////////////
	/**
	 * Bouton situé dans une popup pour fermer.
	 */
	public static final CibleBean BOUTON_POPUP_FERMER = new CibleBean(Clefs.VALEUR, "Fermer");
	
	/**
	 * Bouton de formulaire permettant de passer à l'étape suivante (attention présence multiple possible).
	 */
	public static final CibleBean BOUTON_POPUP_SUIVANT = new CibleBean(Clefs.VALEUR, "Suivant");
	/**
	 * Bouton de popup de fin de préparation du dossier
	 */
	public static final CibleBean BOUTON_POPUP_SUIVANT_FIN = new CibleBean(Clefs.XPATH, "//*[contains(@id, 'progressBtnSuivant')]");

	/**
	 * Bouton de formulaire permettant de passer à l'étape suivante (attention présence multiple possible).
	 */
	public static final CibleBean BOUTON_SUIVANT = new CibleBean(Clefs.VALEUR, "Suivant");
	
	/**
	 * Bouton permettant de passer à l'étape suivante de validation des données client
	 */
	public static final CibleBean BOUTON_SUIVANT_CLIENT = new CibleBean("form_donnees_client:goNext");

	/**
	 * Bouton de formulaire permettant de valider (attention présence multiple possible).
	 */
	public static final CibleBean BOUTON_VALIDER = new CibleBean(Clefs.VALEUR, "Valider");
	
	/**
	 * Bouton de popup permettant de valider (dans popupSyntheseContentTable).
	 * .//*[@id='popupSyntheseContentTable']//*[@value='Valider']
	 */
	public static final CibleBean BOUTON_POPUP_VALIDER_SYNTHESE_TIERS = new CibleBean(Clefs.CRITERES_ITERATIF, "*", "id=popupSyntheseContentTable", CibleBean.RECHERCHE , "value=Valider");
	
	/**
	 *Bouton de suppression du deuxième co emprunteur dans la synthèse de participaant 
	 */
	public static final CibleBean BOUTON_SUPP_PARTICIPANT_2_CE = new CibleBean(Clefs.NAME, "form_ongletSyntheseParticipant:tableParticipants:2:j_id1682");
	
	/**
	 *Bouton de suppression du deuxième co emprunteur dans la synthèse de participaant 
	 */
	public static final CibleBean BOUTON_SUPP_PARTICIPANT_2_BP = new CibleBean(Clefs.NAME, "form_ongletSyntheseParticipant:tableParticipants:2:j_id1643");
	
	/**
	 * Tableau des participants.
	 */
	public static final CibleBean TABLEAU_PARTICIPANTS = new CibleBean("form_ongletSyntheseParticipant:tableParticipants:tb");
	
	/**
	 * Bouton de formulaire permettant la validation d'une recherche.
	 */
	public static final CibleBean BOUTON_RECHERCHER = new CibleBean(Clefs.VALEUR, "Rechercher");
	
	/**
	 * Libelle de choix "Oui/Non" pour le choix "Oui".
	 */
	public static final CibleBean BOUTON_POPUP_OUI_MAJ = new CibleBean(Clefs.VALEUR, "Oui");
	
	/**
	 * Libelle de choix "Ok" pour le choix "Ok".
	 */
	public static final CibleBean BOUTON_POPUP_OK_MAJ = new CibleBean(Clefs.VALEUR, "Ok");
	
	/**
	 * Libelle de choix "Oui/Non" pour le choix "non".
	 */
	public static final CibleBean LIBELLE_CHOIX_NON = new CibleBean(Clefs.TEXTE_PARTIEL, "non");
	
	/**
	 * Libelle de choix "Oui/Non" pour le choix "Non".
	 */
	public static final CibleBean LIBELLE_CHOIX_NON_MAJ = new CibleBean(Clefs.TEXTE_PARTIEL, "Non");
	
	/**
	 * Libelle de choix "Oui/Non" pour le choix "oui".
	 */
	public static final CibleBean LIBELLE_CHOIX_OUI = new CibleBean(Clefs.TEXTE_PARTIEL, "oui");
	
	/**
	 * Libelle de choix "Oui/Non" pour le choix "Oui".
	 */
	public static final CibleBean LIBELLE_CHOIX_OUI_MAJ = new CibleBean(Clefs.TEXTE_PARTIEL, "Oui");
	
	/**
	 * Libelle du choix "Vérifié/Non Vérifié" pourle choix "Vérifié".
	 */
	//public static final CibleBean LIBELLE_CHOIX_VERIFIE = new CibleBean(Clefs.TEXTE_PARTIEL, "Vérifié");
	public static final CibleBean LIBELLE_CHOIX_VERIFIE = new CibleBean(Clefs.VALEUR, "V");
	
	/**
	 * Libelle de la popup d'alerte.
	 */
	public static final CibleBean LIBELLE_POPUP_ALERTES_ = new CibleBean(Clefs.CRITERES_ITERATIF, "*", "id=popupMessagesErreursPopupContentTable", CibleBean.RECHERCHE, CibleBean.CRITERE_TEXTE + "=Alerte(s)");

	/**
	 * Element pour la récupération du BIC du compte emprunteur
	 */
	public static final CibleBean ELEMENT_SPAN_BIC = new CibleBean(Clefs.XPATH, ".//*[text()='BIC']/../../../../tbody/tr/td[2]");;

	/**
	 * Element pour la récupération l'IBAN du compte emprunteur
	 */
	public static final CibleBean ELEMENT_SPAN_IBAN = new CibleBean(Clefs.XPATH, ".//*[text()='BIC']/../../../../tbody/tr/td[3]");
	
	/**
	 * Bouton de rafraichissement des informations client
	 */
	public static final CibleBean BOUTON_RAFRAICHISSEMENT_INFOS_CLIENT = new CibleBean("form_donnees_client:refreshClient1");
	//public static final CibleBean BOUTON_RAFRAICHISSEMENT_INFOS_CLIENT = new CibleBean(Clefs.VALEUR, "Rafraîchir");
}
