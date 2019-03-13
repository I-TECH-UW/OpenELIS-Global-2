INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Transaminases GPT (37°C)(Serum)' ) , 'N' , null , now() , 10);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Transaminases G0T (37°C)(Serum)' ) , 'N' , null , now() , 20);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glucose(Plasma)' ) , 'N' , null , now() , 30);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Créatinine(Serum)' ) , 'N' , null , now() , 40);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amylase(Serum)' ) , 'N' , null , now() , 50);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Albumine recherche miction(Urines)' ) , 'N' , null , now() , 60);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cholestérol total(Serum)' ) , 'N' , null , now() , 70);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cholestérol HDL(Serum)' ) , 'N' , null , now() , 80);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Triglycérides(Serum)' ) , 'N' , null , now() , 90);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Prolans (BHCG) urines de 24 h(Urines)' ) , 'N' , null , now() , 100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test urinaire de grossesse(Urine)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test urinaire de grossesse(Urine)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Protéinurie sur bandelette(Urine)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif +' )  , now() , 130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Protéinurie sur bandelette(Urine)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif ++' )  , now() , 140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Protéinurie sur bandelette(Urine)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif +++' )  , now() , 150);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Protéinurie sur bandelette(Urine)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 160);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Numération des globules blancs(Sang total)' ) , 'N' , null , now() , 170);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Numération des globules rouges(Sang total)' ) , 'N' , null , now() , 180);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hémoglobine(Sang total)' ) , 'N' , null , now() , 190);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hémotocrite(Sang total)' ) , 'N' , null , now() , 200);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Volume Globulaire Moyen(Sang total)' ) , 'N' , null , now() , 210);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Teneur Corpusculaire Moyenne en Hémoglobine(Sang total)' ) , 'N' , null , now() , 220);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Concentration Corpusculaire Moyenne en Hémoglobine(Sang total)' ) , 'N' , null , now() , 230);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Plaquette(Sang total)' ) , 'N' , null , now() , 240);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Polynucléaires Neutrophiles (%)(Sang total)' ) , 'N' , null , now() , 250);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Polynucléaires Neutrophiles (Abs)(Sang total)' ) , 'N' , null , now() , 260);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Polynucléaires Eosinophiles (%)(Sang total)' ) , 'N' , null , now() , 270);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Polynucléaires Eosinophiles (Abs)(Sang total)' ) , 'N' , null , now() , 280);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Polynucléaires basophiles (%)(Sang total)' ) , 'N' , null , now() , 290);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Polynucléaires basophiles (Abs)(Sang total)' ) , 'N' , null , now() , 300);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lymphocytes (%)(Sang total)' ) , 'N' , null , now() , 310);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lymphocytes (Abs)(Sang total)' ) , 'N' , null , now() , 320);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Monocytes (%)(Sang total)' ) , 'N' , null , now() , 330);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Monocytes (Abs)(Sang total)' ) , 'N' , null , now() , 340);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 350);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 1' )  , now() , 360);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 2' )  , now() , 370);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH1 et 2' )  , now() , 380);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indéterminé' )  , now() , 390);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 400);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 1' )  , now() , 410);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 2' )  , now() , 420);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH1 et 2' )  , now() , 430);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indéterminé' )  , now() , 440);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 450);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 1' )  , now() , 460);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 2' )  , now() , 470);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH1 et 2' )  , now() , 480);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test rapide HIV 1 + HIV 2(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indéterminé' )  , now() , 490);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dénombrement des lymphocytes CD4 (mm3)(Sang total)' ) , 'N' , null , now() , 500);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dénombrement des lymphocytes  CD4 (%)(Sang total)' ) , 'N' , null , now() , 510);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HBs AG (antigén australia)(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 520);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HBs AG (antigén australia)(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 530);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mesure de la charge virale(Sang total)' ) , 'N' , null , now() , 540);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Detection de la resistance aux antiretroviraux(Sang total)' ) , 'N' , null , now() , 550);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Western blot VIH(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 560);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Western blot VIH(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH1' )  , now() , 570);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Western blot VIH(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='PositifVIH2' )  , now() , 580);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Western blot VIH(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH1 et VIH2' )  , now() , 590);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Western blot VIH(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 600);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Western blot VIH(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH1' )  , now() , 610);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Western blot VIH(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='PositifVIH2' )  , now() , 620);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Western blot VIH(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH1 et VIH2' )  , now() , 630);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalide' )  , now() , 640);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 650);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH1' )  , now() , 660);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH2' )  , now() , 670);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH1 et VIH2' )  , now() , 680);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalide' )  , now() , 690);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 700);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH1' )  , now() , 710);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH2' )  , now() , 720);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH1 et VIH2' )  , now() , 730);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalide' )  , now() , 740);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 750);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH1' )  , now() , 760);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH2' )  , now() , 770);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH1 et VIH2' )  , now() , 780);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalide' )  , now() , 790);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 800);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH1' )  , now() , 810);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH2' )  , now() , 820);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH1 et VIH2' )  , now() , 830);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalide' )  , now() , 840);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 850);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH1' )  , now() , 860);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH2' )  , now() , 870);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH1 et VIH2' )  , now() , 880);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalide' )  , now() , 890);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 900);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH1' )  , now() , 910);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH2' )  , now() , 920);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie III(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH1 et VIH2' )  , now() , 930);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Murex(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalide' )  , now() , 940);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Murex(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 950);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Murex(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 960);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Murex(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 970);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Murex(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalide' )  , now() , 980);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Murex(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 990);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Murex(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 1000);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Murex(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1010);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Vironostika(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalide' )  , now() , 1020);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Vironostika(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 1030);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Vironostika(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 1040);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Vironostika(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1050);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Vironostika(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalide' )  , now() , 1060);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Vironostika(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 1070);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Vironostika(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 1080);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Vironostika(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1090);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'P24 Ag(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 1100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'P24 Ag(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 1110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'P24 Ag(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif HIV1' )  , now() , 1120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'P24 Ag(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 1130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'P24 Ag(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 1140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'P24 Ag(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif HIV1' )  , now() , 1150);
