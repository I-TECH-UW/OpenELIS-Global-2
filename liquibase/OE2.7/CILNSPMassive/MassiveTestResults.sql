INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 10);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 1' )  , now() , 20);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 2' )  , now() , 30);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH1 et 2' )  , now() , 40);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 50);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 60);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 1' )  , now() , 70);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 2' )  , now() , 80);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH1 et 2' )  , now() , 90);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 1' )  , now() , 120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 2' )  , now() , 130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH1 et 2' )  , now() , 140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 150);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dénombrement des lymphocytes CD4 (mm3)(Sang total)' ) , 'N' , null , now() , 160);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dénombrement des lymphocytes  CD4 (%)(Sang total)' ) , 'N' , null , now() , 170);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Transaminases GPT (37°C)(Serum)' ) , 'N' , null , now() , 180);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Transaminases G0T (37°C)(Serum)' ) , 'N' , null , now() , 190);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glucose(Plasma)' ) , 'N' , null , now() , 200);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Créatinine(Serum)' ) , 'N' , null , now() , 210);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amylase(Serum)' ) , 'N' , null , now() , 240);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Albumine recherche miction(Urines)' ) , 'N' , null , now() , 250);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cholestérol total(Serum)' ) , 'N' , null , now() , 260);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cholestérol HDL(Serum)' ) , 'N' , null , now() , 270);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Triglycérides(Serum)' ) , 'N' , null , now() , 280);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mesure de la charge virale(Sang total)' ) , 'N' , null , now() , 290);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Prolans (BHCG) urines de 24 h(Urines)' ) , 'N' , null , now() , 300);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Numération des globules blancs(Sang total)' ) , 'N' , null , now() , 310);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Numération des globules rouges(Sang total)' ) , 'N' , null , now() , 320);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hémoglobine(Sang total)' ) , 'N' , null , now() , 330);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hémotocrite(Sang total)' ) , 'N' , null , now() , 340);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Volume Globulaire Moyen(Sang total)' ) , 'N' , null , now() , 350);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Teneur Corpusculaire Moyenne en Hémoglobine(Sang total)' ) , 'N' , null , now() , 360);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Concentration Corpusculaire Moyenne en Hémoglobine(Sang total)' ) , 'N' , null , now() , 370);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Plaquette(Sang total)' ) , 'N' , null , now() , 380);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Polynucléaires Neutrophiles (%)(Sang total)' ) , 'N' , null , now() , 390);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Polynucléaires Neutrophiles (Abs)(Sang total)' ) , 'N' , null , now() , 400);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Polynucléaires Eosinophiles (%)(Sang total)' ) , 'N' , null , now() , 410);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Polynucléaires Eosinophiles (Abs)(Sang total)' ) , 'N' , null , now() , 420);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Polynucléaires basophiles (%)(Sang total)' ) , 'N' , null , now() , 430);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Polynucléaires basophiles (Abs)(Sang total)' ) , 'N' , null , now() , 440);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lymphocytes (%)(Sang total)' ) , 'N' , null , now() , 450);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lymphocytes (Abs)(Sang total)' ) , 'N' , null , now() , 460);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Monocytes (%)(Sang total)' ) , 'N' , null , now() , 470);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Monocytes (Abs)(Sang total)' ) , 'N' , null , now() , 480);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HBs AG (antigén australia)(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 500);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HBs AG (antigén australia)(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 490);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test urinaire de grossesse(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 510);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test urinaire de grossesse(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 505);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Protéinurie sur bandelette(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif (+)' )  , now() , 530);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Protéinurie sur bandelette(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif (++)' )  , now() , 540);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Protéinurie sur bandelette(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif (+++)' )  , now() , 550);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Protéinurie sur bandelette(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 520);
