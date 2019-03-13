INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Compte des Globules Blancs(Sang)' ) , 'N' , null , now() , 1);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Copmte des Globules Rouges(Sang)' ) , 'N' , null , now() , 2);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hemoglobine(Sang)' ) , 'N' , null , now() , 3);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hematocrite(Sang)' ) , 'N' , null , now() , 4);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VGM(Sang)' ) , 'N' , null , now() , 5);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'TCMH(Sang)' ) , 'N' , null , now() , 6);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'CCMH(Sang)' ) , 'N' , null , now() , 7);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Plaquettes(Sang)' ) , 'N' , null , now() , 8);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Neutrophiles(Sang)' ) , 'N' , null , now() , 9);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lymphocytes(Sang)' ) , 'N' , null , now() , 10);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mixtes(Sang)' ) , 'N' , null , now() , 11);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Monocytes(Sang)' ) , 'N' , null , now() , 12);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Eosinophiles(Sang)' ) , 'N' , null , now() , 13);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Basophiles(Sang)' ) , 'N' , null , now() , 14);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Vitesse de Sedimentation(Sang)' ) , 'N' , null , now() , 15);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Temps de Coagulation en tube(Sang)' ) , 'N' , null , now() , 16);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Temps de Coagulation(Sang)' ) , 'N' , null , now() , 17);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'temps de saignement(Sang)' ) , 'N' , null , now() , 18);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = $$Electrophorese de l'hemoglobine(Sang)$$ ) , 'R' , null , now() , 19);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sickling Test(Sang)' ) , 'R' , null , now() , 20);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Taux reticulocytes - Auto(Sang)' ) , 'N' , null , now() , 21);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Taux reticulocytes - Manual(Sang)' ) , 'N' , null , now() , 22);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Temps de cephaline Activé(TCA)(Plasma)' ) , 'N' , null , now() , 23);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Fibrinogene(Plasma)' ) , 'N' , null , now() , 24);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Facteur VIII(Plasma)' ) , '' , null , now() , 25);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Facteur IX(Plasma)' ) , 'N' , null , now() , 26);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Heparinemie(Plasma)' ) , 'N' , null , now() , 27);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Anti-Thrombine III (Dosage)(Plasma)' ) , '' , null , now() , 28);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Anti-Thrombine III (Activite)(Plasma)' ) , 'N' , null , now() , 29);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Groupe Sanguin - ABO(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='A' )  , now() , 30);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Groupe Sanguin - ABO(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='B' )  , now() , 31);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Groupe Sanguin - ABO(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='AB' )  , now() , 32);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Groupe Sanguin - ABO(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='O' )  , now() , 33);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Groupe Sanguin - Rhesus(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='RH Pos' )  , now() , 34);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Groupe Sanguin - Rhesus(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='RH Neg' )  , now() , 35);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coombs Test Direct(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Compatible' )  , now() , 36);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coombs Test Direct(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Incompatible' )  , now() , 37);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coombs Test Direct(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Compatible' )  , now() , 38);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coombs Test Direct(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Incompatible' )  , now() , 39);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coombs Test Indirect(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Compatible' )  , now() , 40);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coombs Test Indirect(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Incompatible' )  , now() , 41);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coombs Test Indirect(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Compatible' )  , now() , 42);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coombs Test Indirect(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Incompatible' )  , now() , 43);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = $$Azote de l'Uree(Serum)$$ ) , 'N' , null , now() , 44);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Uree(Serum)' ) , 'N' , null , now() , 45);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'creatinine(Serum)' ) , 'N' , null , now() , 46);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glycemie(Serum)' ) , 'N' , null , now() , 47);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glucose(LCR/CSF)' ) , 'N' , null , now() , 48);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Proteines(LCR/CSF)' ) , 'N' , null , now() , 49);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chlore(LCR/CSF)' ) , 'N' , null , now() , 50);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Alpha-foetoproteine(Liquide Amniotique)' ) , 'N' , null , now() , 51);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'dosage des phospholipides(Liquide Amniotique)' ) , 'N' , null , now() , 52);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'creatinine(Liquide Amniotique)' ) , 'N' , null , now() , 53);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Proteines(Liquide Ascite)' ) , 'N' , null , now() , 54);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Albumine(Liquide Ascite)' ) , 'N' , null , now() , 55);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'glucose(Liquide Synovial)' ) , 'N' , null , now() , 56);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Uree(Liquide Synovial)' ) , 'N' , null , now() , 57);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide urique(Liquide Synovial)' ) , 'N' , null , now() , 58);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'proteine total(Liquide Synovial)' ) , 'N' , null , now() , 59);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'glycemie(Plasma)' ) , 'N' , null , now() , 60);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Albumine(Serum)' ) , 'N' , null , now() , 61);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'α1 globuline(Serum)' ) , 'N' , null , now() , 62);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'α2 globuline(Serum)' ) , 'N' , null , now() , 63);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'β globuline(Serum)' ) , 'N' , null , now() , 64);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'ϒ globuline(Serum)' ) , 'N' , null , now() , 65);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Phosphatase Acide(Serum)' ) , 'N' , null , now() , 66);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chlorures(Plasma hepariné)' ) , 'N' , null , now() , 67);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chlorures(LCR/CSF)' ) , 'N' , null , now() , 68);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chlorures(Urines/24 heures)' ) , 'N' , null , now() , 69);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'CPK(Serum)' ) , 'N' , null , now() , 70);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amylase(Serum)' ) , 'N' , null , now() , 71);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lipase(Serum)' ) , 'N' , null , now() , 72);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chlorures(Serum)' ) , 'N' , null , now() , 73);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sodium(Serum)' ) , 'N' , null , now() , 74);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Potassium(Serum)' ) , 'N' , null , now() , 75);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bicarbonates(Serum)' ) , 'N' , null , now() , 76);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Calcium(Serum)' ) , 'N' , null , now() , 77);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'magnésium(Serum)' ) , 'N' , null , now() , 78);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'phosphore(Serum)' ) , 'N' , null , now() , 79);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lithium(Serum)' ) , 'N' , null , now() , 80);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Fer Serique(Serum)' ) , 'N' , null , now() , 81);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bilirubine totale(Serum)' ) , 'N' , null , now() , 82);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bilirubine directe(Serum)' ) , 'N' , null , now() , 83);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bilirubine indirecte(Serum)' ) , 'N' , null , now() , 84);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'SGOT(Serum)' ) , 'N' , null , now() , 85);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'SGPT(Serum)' ) , 'N' , null , now() , 86);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Phosphatase Alcaline(Serum)' ) , 'N' , null , now() , 87);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cholesterol Total(Serum)' ) , 'N' , null , now() , 88);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Triglyceride(Serum)' ) , 'N' , null , now() , 89);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lipide(Serum)' ) , 'N' , null , now() , 90);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HDL(Serum)' ) , 'N' , null , now() , 91);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'LDL(Serum)' ) , 'N' , null , now() , 92);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VLDL(Serum)' ) , 'N' , null , now() , 93);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'MBG(Serum)' ) , 'N' , null , now() , 94);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hémoglobine glyqué(Serum)' ) , 'N' , null , now() , 95);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ck-mB(Serum)' ) , 'N' , null , now() , 96);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ck total(Serum)' ) , 'N' , null , now() , 97);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'ASAT 30° C(Serum)' ) , 'N' , null , now() , 98);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'ASAT 37° C(Serum)' ) , 'N' , null , now() , 99);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'ALAT 30° C(Serum)' ) , 'N' , null , now() , 100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'ALAT 37° C(Serum)' ) , 'N' , null , now() , 101);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'LDH(Serum)' ) , 'N' , null , now() , 102);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Troponine 1(Serum)' ) , 'N' , null , now() , 103);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glycémie provoquée(Serum)' ) , 'N' , null , now() , 104);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ph(Serum)' ) , 'N' , null , now() , 105);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'PaCO2(Serum)' ) , 'N' , null , now() , 106);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HCO3(Serum)' ) , 'N' , null , now() , 107);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'O2 Saturation(Serum)' ) , 'N' , null , now() , 108);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'PaO2(Serum)' ) , 'N' , null , now() , 109);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'BE(Serum)' ) , 'N' , null , now() , 110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 111);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 112);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 113);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 114);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Simples(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 115);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Simples(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 116);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Simples(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 117);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Simples(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 118);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Bourgeonantes(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 119);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Bourgeonantes(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Bourgeonantes(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 121);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Bourgeonantes(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 122);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Trichomonas vaginalis(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 123);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Trichomonas vaginalis(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 124);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Trichomonas vaginalis(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 125);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Trichomonas vaginalis(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 126);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Blancs(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 127);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Blancs(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 128);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Blancs(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 129);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Blancs(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Rouges(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 131);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Rouges(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 132);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Rouges(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 133);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Rouges(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 134);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Filaments Myceliens(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 135);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Filaments Myceliens(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 136);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Filaments Myceliens(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 137);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Filaments Myceliens(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 138);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules Epitheliales(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 139);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules Epitheliales(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules Epitheliales(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 141);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules Epitheliales(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 142);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 143);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 144);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 145);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 146);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Simples(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 147);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Simples(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 148);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Simples(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 149);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Simples(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 150);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Bourgeonantes(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 151);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Bourgeonantes(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 152);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Bourgeonantes(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 153);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Bourgeonantes(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 154);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Trichomonas vaginalis(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 155);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Trichomonas vaginalis(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 156);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Trichomonas vaginalis(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 157);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Trichomonas vaginalis(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 158);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Blancs(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 159);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Blancs(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 160);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Blancs(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 161);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Blancs(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 162);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Rouges(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 163);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Rouges(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 164);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Rouges(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 165);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Rouges(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 166);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Filaments Myceliens(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 167);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Filaments Myceliens(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 168);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Filaments Myceliens(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 169);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Filaments Myceliens(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 170);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules Epitheliales(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 171);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules Epitheliales(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 172);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules Epitheliales(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 173);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules Epitheliales(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 174);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'KOH(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 175);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'KOH(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 176);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test de Rivalta(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 177);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test de Rivalta(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 178);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram+(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 179);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram+(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 180);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram+(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 181);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram+(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 182);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram-(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 183);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram-(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 184);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram-(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 185);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram-(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 186);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram+(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 187);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram+(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 188);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram+(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 189);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram+(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 190);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram-(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 191);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram-(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 192);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram-(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 193);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram-(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 194);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocobacilles Gram -(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 195);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocobacilles Gram -(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 196);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocobacilles Gram -(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 197);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocobacilles Gram -(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 198);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules epitheliales cloutees(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 199);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules epitheliales cloutees(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 200);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules epitheliales cloutees(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 201);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules epitheliales cloutees(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 202);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Diplocoque Gram (-) Intra cellulaire(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 203);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Diplocoque Gram (-) Intra cellulaire(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 204);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Diplocoque Gram (-) Intra cellulaire(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 205);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Diplocoque Gram (-) Intra cellulaire(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 206);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Diplocoque Gram (-) Extra cellulaire(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 207);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Diplocoque Gram (-) Extra cellulaire(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 208);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Diplocoque Gram (-) Extra cellulaire(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 209);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Diplocoque Gram (-) Extra cellulaire(Secretion vaginale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 210);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram+(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 211);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram+(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 212);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram+(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 213);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram+(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 214);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram-(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 215);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram-(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 216);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram-(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 217);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram-(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 218);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram+(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 219);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram+(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 220);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram+(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 221);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram+(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 222);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram-(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 223);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram-(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 224);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram-(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 225);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram-(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 226);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocobacilles Gram -(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 227);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocobacilles Gram -(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 228);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocobacilles Gram -(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 229);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocobacilles Gram -(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 230);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules epitheliales cloutees(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 231);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules epitheliales cloutees(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 232);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules epitheliales cloutees(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 233);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules epitheliales cloutees(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 234);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Diplocoque Gram (-) Intra cellulaire(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 235);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Diplocoque Gram (-) Intra cellulaire(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 236);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Diplocoque Gram (-) Intra cellulaire(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 237);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Diplocoque Gram (-) Intra cellulaire(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 238);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Diplocoque Gram (-) Extra cellulaire(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 239);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Diplocoque Gram (-) Extra cellulaire(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 240);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Diplocoque Gram (-) Extra cellulaire(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 241);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Diplocoque Gram (-) Extra cellulaire(Secretion Urethrale)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 242);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Frottis Vaginal/Gram(Secretion vaginale)' ) , 'R' , null , now() , 243);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Frottis Uretral/Gram(Secretion Urethrale)' ) , 'R' , null , now() , 244);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cholera Test rapide(Selles)' ) , 'R' , null , now() , 245);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coproculture(Selles)' ) , 'R' , null , now() , 246);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Liquide Spermatique)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Blanc jaunatre' )  , now() , 247);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Liquide Spermatique)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='jaune paille' )  , now() , 248);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Liquide Spermatique)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='jaune citrin' )  , now() , 249);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Liquide Spermatique)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ambre' )  , now() , 250);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Liquide Spermatique)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='orange' )  , now() , 251);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Liquide Spermatique)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='jaune fonce' )  , now() , 252);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Liquide Spermatique)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='rouge' )  , now() , 253);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Liquide Spermatique)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='autre' )  , now() , 254);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Liquefaction(Liquide Spermatique)' ) , 'R' , null , now() , 255);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'pH(Liquide Spermatique)' ) , 'N' , null , now() , 256);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Fructose(Liquide Spermatique)' ) , 'N' , null , now() , 257);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Volume(Liquide Spermatique)' ) , 'N' , null , now() , 258);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Compte de spermes(Liquide Spermatique)' ) , 'N' , null , now() , 259);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Formes normales(Liquide Spermatique)' ) , 'N' , null , now() , 260);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Formes anormales(Liquide Spermatique)' ) , 'N' , null , now() , 261);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Motilite STAT(Liquide Spermatique)' ) , 'N' , null , now() , 262);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Motilite 1 heure(Liquide Spermatique)' ) , 'N' , null , now() , 263);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Motilite 3 heures(Liquide Spermatique)' ) , 'N' , null , now() , 264);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Gram(Liquide Spermatique)' ) , 'R' , null , now() , 265);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Jaune Paille' )  , now() , 266);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Jaune citrin' )  , now() , 267);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Ambre' )  , now() , 268);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Orange' )  , now() , 269);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Jaune Fonce' )  , now() , 270);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Rouge' )  , now() , 271);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Autre' )  , now() , 272);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aspect(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Clair' )  , now() , 273);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aspect(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Trouble' )  , now() , 274);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aspect(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Icterique' )  , now() , 275);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aspect(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='normal' )  , now() , 276);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aspect(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Fonce' )  , now() , 277);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aspect(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Autre' )  , now() , 278);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Densite(Urines)' ) , 'N' , null , now() , 279);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ph(Urines)' ) , 'N' , null , now() , 280);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Proteines(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 281);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Proteines(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='trace' )  , now() , 282);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Proteines(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 283);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Proteines(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 284);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Proteines(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 285);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glucose(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 286);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glucose(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='trace' )  , now() , 287);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glucose(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 288);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glucose(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 289);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glucose(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 290);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cetones(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 291);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cetones(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='trace' )  , now() , 292);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cetones(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 293);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cetones(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 294);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cetones(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 295);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bilirubine(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 296);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bilirubine(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='trace' )  , now() , 297);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bilirubine(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 298);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bilirubine(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 299);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bilirubine(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 300);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sang(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 301);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sang(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='trace' )  , now() , 302);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sang(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 303);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sang(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 304);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sang(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 305);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide ascorbique(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 306);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide ascorbique(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='trace' )  , now() , 307);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide ascorbique(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 308);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide ascorbique(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 309);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide ascorbique(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 310);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Urobilinogene(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 311);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Urobilinogene(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='trace' )  , now() , 312);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Urobilinogene(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 313);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Urobilinogene(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 314);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Urobilinogene(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 315);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Nitrites(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 316);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Nitrites(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='trace' )  , now() , 317);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Nitrites(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 318);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Nitrites(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 319);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Nitrites(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 320);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hematies(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 321);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hematies(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='En amas' )  , now() , 322);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hematies(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='0 à 1/ champs' )  , now() , 323);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hematies(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2 à 5 / Champs' )  , now() , 324);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hematies(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='5 à 10/ Champs' )  , now() , 325);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leucocytes(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 326);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leucocytes(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='En amas' )  , now() , 327);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leucocytes(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='0 à 1/ champs' )  , now() , 328);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leucocytes(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2 à 5 / Champs' )  , now() , 329);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leucocytes(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='5 à 10/ Champs' )  , now() , 330);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'cellules epitheliales(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 331);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'cellules epitheliales(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='En amas' )  , now() , 332);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'cellules epitheliales(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='0 à 1/ champs' )  , now() , 333);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'cellules epitheliales(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2 à 5 / Champs' )  , now() , 334);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'cellules epitheliales(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='5 à 10/ Champs' )  , now() , 335);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 336);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='En amas' )  , now() , 337);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='0 à 1/ champs' )  , now() , 338);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2 à 5 / Champs' )  , now() , 339);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='5 à 10/ Champs' )  , now() , 340);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 341);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='En amas' )  , now() , 342);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='0 à 1/ champs' )  , now() , 343);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2 à 5 / Champs' )  , now() , 344);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='5 à 10/ Champs' )  , now() , 345);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'filaments myceliens(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 346);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'filaments myceliens(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='En amas' )  , now() , 347);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'filaments myceliens(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='0 à 1/ champs' )  , now() , 348);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'filaments myceliens(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2 à 5 / Champs' )  , now() , 349);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'filaments myceliens(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='5 à 10/ Champs' )  , now() , 350);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'spores(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 351);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'spores(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='En amas' )  , now() , 352);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'spores(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='0 à 1/ champs' )  , now() , 353);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'spores(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2 à 5 / Champs' )  , now() , 354);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'spores(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='5 à 10/ Champs' )  , now() , 355);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'trichomonas(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 356);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'trichomonas(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='En amas' )  , now() , 357);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'trichomonas(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='0 à 1/ champs' )  , now() , 358);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'trichomonas(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2 à 5 / Champs' )  , now() , 359);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'trichomonas(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='5 à 10/ Champs' )  , now() , 360);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cylindres(Urines)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 361);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cylindres(Urines)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='En amas' )  , now() , 362);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cylindres(Urines)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='0 à 1/ champs' )  , now() , 363);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cylindres(Urines)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2 à 5 / Champs' )  , now() , 364);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cylindres(Urines)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='5 à 10/ Champs' )  , now() , 365);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cylindres(Urines)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Hyalins' )  , now() , 366);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cylindres(Urines)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Granuleux' )  , now() , 367);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cylindres(Urines)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Graisseux' )  , now() , 368);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cylindres(Urines)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Epitheliales' )  , now() , 369);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cylindres(Urines)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Leucocytaires' )  , now() , 370);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cylindres(Urines)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Cireux' )  , now() , 371);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cristaux(Urines)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 372);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cristaux(Urines)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='En amas' )  , now() , 373);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cristaux(Urines)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='0 à 1/ champs' )  , now() , 374);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cristaux(Urines)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2 à 5 / Champs' )  , now() , 375);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cristaux(Urines)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='5 à 10/ Champs' )  , now() , 376);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cristaux(Urines)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry =' Oxalate de Calcium' )  , now() , 377);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cristaux(Urines)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Acide Urique' )  , now() , 378);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cristaux(Urines)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Cystine' )  , now() , 379);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cristaux(Urines)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Phosphate de Calcium' )  , now() , 380);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cristaux(Urines)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Urates Amorphes' )  , now() , 381);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cristaux(Urines)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Triple phosphate' )  , now() , 382);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cristaux(Urines)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Phosphate de Ca' )  , now() , 383);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cristaux(Urines)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Phosphate de Mg' )  , now() , 384);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cristaux(Urines)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sulfate de Ca' )  , now() , 385);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cristaux(Urines)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry =$$Urate d'Ammonium $$ )  , now() , 386);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Noiratre' )  , now() , 387);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sang/Whole Blooduinolant' )  , now() , 388);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Jaunatre' )  , now() , 389);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Maron' )  , now() , 390);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Verdatre' )  , now() , 391);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Autre' )  , now() , 392);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aspect(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Liquide' )  , now() , 393);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aspect(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Solide' )  , now() , 394);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aspect(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='semi liquide' )  , now() , 395);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aspect(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='pateuse' )  , now() , 396);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sang Occulte(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 397);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sang Occulte(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 398);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 399);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 400);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 401);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 402);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'cellules vegetales(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 403);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'cellules vegetales(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 404);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'cellules vegetales(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 405);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'cellules vegetales(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 406);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Protozoaires(Selles)' ) , 'R' , null , now() , 407);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Metazoaires(Selles)' ) , 'R' , null , now() , 408);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de cryptosporidium et Oocyste(Selles)' ) , 'R' , null , now() , 409);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recheche de microfilaire(Sang)' ) , 'R' , null , now() , 410);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de plasmodiun - Especes(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 411);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de plasmodiun - Especes(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P. Falciparum' )  , now() , 412);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de plasmodiun - Especes(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P. Vivax' )  , now() , 413);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de plasmodiun - Especes(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P. Malariae' )  , now() , 414);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de plasmodiun - Especes(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P.Ovale' )  , now() , 415);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de plasmodiun - Trophozoit(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 416);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de plasmodiun - Trophozoit(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 417);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de plasmodiun - Trophozoit(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 418);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de plasmodiun - Trophozoit(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 419);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de plasmodiun - Gametocyte(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 420);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de plasmodiun - Gametocyte(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 421);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de plasmodiun - Gametocyte(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 422);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de plasmodiun - Gametocyte(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 423);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de plasmodiun - Schizonte(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 424);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de plasmodiun - Schizonte(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 425);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de plasmodiun - Schizonte(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 426);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de plasmodiun - Schizonte(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 427);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'CD4 en mm3(Sang)' ) , 'N' , null , now() , 428);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'CD4 en %(Sang)' ) , 'N' , null , now() , 429);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'CMV Ig G(Serum)' ) , 'N' , null , now() , 430);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'CMV Ig M(Serum)' ) , 'N' , null , now() , 431);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'RUBELLA Ig G(Serum)' ) , 'N' , null , now() , 432);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'RUBELLA Ig M(Serum)' ) , 'N' , null , now() , 433);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HERPESTYPE I   Ig G(Serum)' ) , 'N' , null , now() , 434);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HERPESTYPE II Ig M(Serum)' ) , 'N' , null , now() , 435);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HIV test rapide(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 436);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HIV test rapide(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 437);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HIV test rapide(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='INDETERMINE' )  , now() , 438);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HIV test rapide(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 439);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HIV test rapide(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 440);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HIV test rapide(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='INDETERMINE' )  , now() , 441);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HIV test rapide(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 442);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HIV test rapide(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 443);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HIV test rapide(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='INDETERMINE' )  , now() , 444);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HIV Elisa(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 445);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HIV Elisa(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 446);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HIV Elisa(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 447);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HIV Elisa(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 448);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HIV Elisa(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 449);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HIV Elisa(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 450);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HBsAg - Hépatite B(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 451);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HBsAg - Hépatite B(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 452);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HBsAg - Hépatite B(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 453);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HBsAg - Hépatite B(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 454);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HBsAg - Hépatite B(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 455);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HBsAg - Hépatite B(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 456);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HCV - Hépatite C(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 457);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HCV - Hépatite C(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 458);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HCV - Hépatite C(Plasma)' ) , '' , null , now() , 459);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HCV - Hépatite C(Serum)' ) , '' , null , now() , 460);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 461);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 462);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 463);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 464);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 465);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 466);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Toxoplasmose(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 467);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Toxoplasmose(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 468);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Toxoplasmose(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 469);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Toxoplasmose(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 470);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Toxoplasmose(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 471);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Toxoplasmose(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 472);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 473);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 474);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 475);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 476);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 477);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 478);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HIV Western Blot(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 479);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HIV Western Blot(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 480);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HIV Western Blot(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 481);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HIV Western Blot(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 482);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HIV Western Blot(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 483);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HIV Western Blot(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 484);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HBsAg IGG(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 485);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HBsAg IGG(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 486);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HBsAg IGG(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 487);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HBsAg IGG(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 488);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HBsAg IGG(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 489);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HBsAg IGG(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 490);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HBV :AcHbC,AcHbe(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 491);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HBV :AcHbC,AcHbe(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 492);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HBV :AcHbC,AcHbe(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 493);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HBV :AcHbC,AcHbe(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 494);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HBV :AcHbC,AcHbe(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 495);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HBV :AcHbC,AcHbe(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 496);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite C - HCV IGG(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 497);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite C - HCV IGG(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 498);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite C - HCV IGG(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 499);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite C - HCV IGG(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 500);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite C - HCV IGG(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 501);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite C - HCV IGG(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 502);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HCV IGM(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 503);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HCV IGM(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 504);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HCV IGM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 505);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HCV IGM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 506);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HCV IGM(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 507);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HCV IGM(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 508);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'P24(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 509);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'P24(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 510);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Charge Virale(Plasma)' ) , 'N' , null , now() , 511);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'ADN VIH-1(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Detecte' )  , now() , 512);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'ADN VIH-1(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Non Detecte' )  , now() , 513);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'ADN VIH-1(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 514);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'PCR Resistance TB(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Detecte' )  , now() , 515);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'PCR Resistance TB(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Non Detecte' )  , now() , 516);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'PCR Resistance TB(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 517);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture(LCR/CSF)' ) , 'R' , null , now() , 518);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Antibiogramme(LCR/CSF)' ) , 'R' , null , now() , 519);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'recherche des bacteries(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 520);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'recherche des bacteries(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Rares' )  , now() , 521);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'recherche des bacteries(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 522);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'recherche des bacteries(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 523);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'recherche des bacteries(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 524);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'pneumocoques(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POS' )  , now() , 525);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'pneumocoques(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEG' )  , now() , 526);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'streptocoques(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POS' )  , now() , 527);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'streptocoques(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEG' )  , now() , 528);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'staphylocoques(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POS' )  , now() , 529);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'staphylocoques(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEG' )  , now() , 530);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR Auramine(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Abs BK' )  , now() , 532);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR Auramine(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Pres BK 1+' )  , now() , 533);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR Auramine(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Pres BK 2+' )  , now() , 534);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR Auramine(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Pres BK 3+' )  , now() , 535);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR Zielh Neelsen(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Abs BK' )  , now() , 538);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR Zielh Neelsen(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Pres BK 1+' )  , now() , 539);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR Zielh Neelsen(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Pres BK 2+' )  , now() , 540);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR Zielh Neelsen(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Pres BK 3+' )  , now() , 541);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'culture BK(Liquide Pleural)' ) , 'R' , null , now() , 543);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Antibiogramme(Liquide Pleural)' ) , 'R' , null , now() , 544);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'culture(Liquide Synovial)' ) , 'R' , null , now() , 545);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = $$cristaux d'urates(Liquide Synovial)$$ ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POS' )  , now() , 546);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = $$cristaux d'urates(Liquide Synovial)$$ ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEG' )  , now() , 547);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'cristaux de cholesterol(Liquide Synovial)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POS' )  , now() , 548);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'cristaux de cholesterol(Liquide Synovial)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEG' )  , now() , 549);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Examen direct(Pus)' ) , 'R' , null , now() , 550);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture(Pus)' ) , 'R' , null , now() , 551);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Antibiogramme(Pus)' ) , 'R' , null , now() , 552);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Liquide Ascite Recherche des Lymphocytes(Liquide Ascite)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Pres ence des lymphocytes' )  , now() , 553);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Liquide Ascite Recherche des Lymphocytes(Liquide Ascite)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Absence des lymphocytes' )  , now() , 554);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Liquide Ascite GRAM(Liquide Ascite)' ) , 'R' , null , now() , 555);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Liquide Ascite ZIELH NIELSEN(Liquide Ascite)' ) , 'R' , null , now() , 556);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche des Lymphocytes(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Pres ence des lymphocytes' )  , now() , 557);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche des Lymphocytes(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Absence des lymphocytes' )  , now() , 558);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Eosinophile(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Pres eosinophiles' )  , now() , 559);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'cellules atypiques/polynucleaires(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Pres ence des cellules atypiques' )  , now() , 560);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'cellules atypiques/polynucleaires(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Absence des cellules atypiques' )  , now() , 561);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'globules rouges(Liquide Pleural)' ) , 'N' , null , now() , 562);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'GRAM(Liquide Pleural)' ) , 'R' , null , now() , 563);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'ZIELH NIELSEN(Liquide Pleural)' ) , 'R' , null , now() , 564);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules polynucleaires(Liquide Synovial)' ) , 'N' , null , now() , 565);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'leucocytes(Liquide Synovial)' ) , 'N' , null , now() , 566);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Numeration des globules blancs(LCR/CSF)' ) , 'N' , null , now() , 567);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'LCR GRAM(LCR/CSF)' ) , 'R' , null , now() , 568);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'LCR ZIELH NIELSEN(LCR/CSF)' ) , 'R' , null , now() , 569);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 570);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Rares' )  , now() , 571);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 572);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 573);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 574);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BK Auramine(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 575);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BK Auramine(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Rares' )  , now() , 576);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BK Auramine(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 577);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BK Auramine(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 578);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BK Auramine(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 579);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture de M. tuberculosis(Expectoration)' ) , 'R' , null , now() , 580);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Prolactine(Serum)' ) , 'N' , null , now() , 581);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'FSH(Serum)' ) , 'N' , null , now() , 582);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'FSH(Plasma hepariné)' ) , 'N' , null , now() , 583);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'LH(Serum)' ) , 'N' , null , now() , 584);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'LH(Plasma hepariné)' ) , 'N' , null , now() , 585);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oestrogene(Urines/24 heures)' ) , 'N' , null , now() , 586);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Progesterone(Serum)' ) , 'N' , null , now() , 587);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'T3(Serum)' ) , 'N' , null , now() , 588);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'B-HCG(Serum)' ) , 'N' ,  null , now() , 589);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'B-HCG(Urine concentré du matin)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 590);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'B-HCG(Urine concentré du matin)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 591);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test de Grossesse(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 592);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test de Grossesse(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 593);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'T4(Serum)' ) , 'N' , null , now() , 594);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'TSH(Serum)' ) , 'N' , null , now() , 594);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'TOXOPLASMOSE GONDII IgG Ac(Serum)' ) , 'N' , null , now() , 595);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'TOXOPLASMOSE GONDII Ig M Ac(Serum)' ) , 'N' , null , now() , 596);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria Test Rapide(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POS' )  , now() , 597);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria Test Rapide(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEG' )  , now() , 598);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria Test Rapide(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POS' )  , now() , 599);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria Test Rapide(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEG' )  , now() , 600);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria Test Rapide(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POS' )  , now() , 601);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria Test Rapide(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEG' )  , now() , 602);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis Test Rapide(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POS' )  , now() , 603);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis Test Rapide(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEG' )  , now() , 604);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis Test Rapide(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POS' )  , now() , 605);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis Test Rapide(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEG' )  , now() , 606);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis Test Rapide(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POS' )  , now() , 607);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis Test Rapide(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEG' )  , now() , 608);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HTLV I et II(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POS' )  , now() , 609);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HTLV I et II(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEG' )  , now() , 610);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HTLV I et II(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POS' )  , now() , 611);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HTLV I et II(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEG' )  , now() , 612);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HTLV I et II(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POS' )  , now() , 613);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HTLV I et II(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEG' )  , now() , 614);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Non Reactif' )  , now() , 615);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif' )  , now() , 616);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/2' )  , now() , 617);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/4' )  , now() , 618);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/8' )  , now() , 619);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/16' )  , now() , 620);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/32' )  , now() , 621);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/64' )  , now() , 622);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Non Reactif' )  , now() , 623);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif' )  , now() , 624);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/2' )  , now() , 625);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/4' )  , now() , 626);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/8' )  , now() , 627);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/16' )  , now() , 628);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/32' )  , now() , 629);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/64' )  , now() , 630);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Non Reactif' )  , now() , 631);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif' )  , now() , 632);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/2' )  , now() , 633);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/4' )  , now() , 634);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/8' )  , now() , 635);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/16' )  , now() , 636);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/32' )  , now() , 637);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/64' )  , now() , 638);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis TPHA(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 639);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis TPHA(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 640);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis TPHA(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 641);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis TPHA(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 642);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis TPHA(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 643);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis TPHA(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 644);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Helicobacter Pilori(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POS' )  , now() , 645);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Helicobacter Pilori(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEG' )  , now() , 646);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Helicobacter Pilori(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POS' )  , now() , 647);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Helicobacter Pilori(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEG' )  , now() , 648);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Helicobacter Pilori(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POS' )  , now() , 649);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Helicobacter Pilori(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEG' )  , now() , 650);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'CRP(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POS  > 200 unites/ml' )  , now() , 651);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'CRP(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEG  < 200 unites/ml' )  , now() , 652);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'CRP(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POS  > 200 unites/ml' )  , now() , 653);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'CRP(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEG  < 200 unites/ml' )  , now() , 654);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'CRP(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POS  > 200 unites/ml' )  , now() , 655);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'CRP(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEG  < 200 unites/ml' )  , now() , 656);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'ASO(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POS  > 200 U ASLO/ml' )  , now() , 657);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'ASO(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEG  < 200 U ASLO/ml' )  , now() , 658);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'ASO(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POS  > 200 U ASLO/ml' )  , now() , 659);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'ASO(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEG  < 200 U ASLO/ml' )  , now() , 660);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'ASO(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POS  > 200 U ASLO/ml' )  , now() , 661);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'ASO(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEG  < 200 U ASLO/ml' )  , now() , 662);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test de Widal Ag O(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Non Reactif' )  , now() , 663);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test de Widal Ag O(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif' )  , now() , 664);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test de Widal Ag O(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/20' )  , now() , 665);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test de Widal Ag O(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/40' )  , now() , 666);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test de Widal Ag O(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/80' )  , now() , 667);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test de Widal Ag O(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/160' )  , now() , 668);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test de Widal Ag O(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/320' )  , now() , 669);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test de Widal Ag O(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/640' )  , now() , 670);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test de Widal Ag H(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Non Reactif' )  , now() , 671);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test de Widal Ag H(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif' )  , now() , 672);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test de Widal Ag H(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/20' )  , now() , 673);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test de Widal Ag H(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/40' )  , now() , 674);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test de Widal Ag H(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/80' )  , now() , 675);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test de Widal Ag H(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/160' )  , now() , 676);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test de Widal Ag H(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/320' )  , now() , 677);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test de Widal Ag H(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/640' )  , now() , 678);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Typhoide Test Rapide(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Non Reactif' )  , now() , 679);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Typhoide Test Rapide(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif' )  , now() , 680);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Typhoide Test Rapide(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/2' )  , now() , 681);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Typhoide Test Rapide(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/4' )  , now() , 682);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Typhoide Test Rapide(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/8' )  , now() , 683);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Typhoide Test Rapide(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/16' )  , now() , 684);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Typhoide Test Rapide(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/32' )  , now() , 685);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Typhoide Test Rapide(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/64' )  , now() , 686);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 687);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 688);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='INDETERMINE' )  , now() , 689);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 690);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 691);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='INDETERMINE' )  , now() , 692);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 693);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 694);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='INDETERMINE' )  , now() , 695);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 696);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 697);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='INDETERMINE' )  , now() , 698);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 699);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 700);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='INDETERMINE' )  , now() , 701);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 702);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 703);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='INDETERMINE' )  , now() , 704);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis Bioline(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 705);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis Bioline(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 706);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis Bioline(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='INDETERMINE' )  , now() , 707);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis Bioline(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 708);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis Bioline(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 709);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis Bioline(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='INDETERMINE' )  , now() , 710);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis Bioline(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='POSITIF' )  , now() , 711);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis Bioline(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='NEGATIF' )  , now() , 712);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis Bioline(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='INDETERMINE' )  , now() , 713);
