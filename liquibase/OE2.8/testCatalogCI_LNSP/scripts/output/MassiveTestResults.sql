INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 10);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 20);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indéterminé')  , now() , 30);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 40);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 50);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indéterminé')  , now() , 60);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 70);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 80);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indéterminé')  , now() , 90);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 1' )  , now() , 110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 2' )  , now() , 120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH1 et 2' )  , now() , 130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indéterminé')  , now() , 140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 150);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 1' )  , now() , 160);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 2' )  , now() , 170);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH1 et 2' )  , now() , 180);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indéterminé')  , now() , 190);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 200);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 1' )  , now() , 210);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH 2' )  , now() , 220);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif VIH1 et 2' )  , now() , 230);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bioline(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indéterminé')  , now() , 240);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Stat PaK(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 250);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Stat PaK(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 260);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Stat PaK(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indéterminé')  , now() , 270);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Stat PaK(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 280);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Stat PaK(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 290);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Stat PaK(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indéterminé')  , now() , 300);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Stat PaK(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 310);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Stat PaK(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 320);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Stat PaK(Sang total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indéterminé')  , now() , 330);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dénombrement des lymphocytes CD4 (mm3)(Sang total)' ) , 'N' , null , now() , 340);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dénombrement des lymphocytes  CD4 (%)(Sang total)' ) , 'N' , null , now() , 350);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Transaminases GPT (37°C)(Serum)' ) , 'N' , null , now() , 360);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Transaminases G0T (37°C)(Serum)' ) , 'N' , null , now() , 370);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glucose(Plasma)' ) , 'N' , null , now() , 380);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glucose(Serum)' ) , 'N' , null , now() , 390);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Créatinine(Serum)' ) , 'N' , null , now() , 400);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amylase(Serum)' ) , 'N' , null , now() , 410);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Albumine recherche miction(Urines)' ) , 'N' , null , now() , 420);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cholestérol total(Serum)' ) , 'N' , null , now() , 430);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cholestérol HDL(Serum)' ) , 'N' , null , now() , 440);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Triglycérides(Serum)' ) , 'N' , null , now() , 450);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mesure de la charge virale(Sang total)' ) , 'N' , null , now() , 460);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Prolans (BHCG) urines de 24 h(Urines)' ) , 'N' , null , now() , 470);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Numération des globules blancs(Sang total)' ) , 'N' , null , now() , 480);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Numération des globules rouges(Sang total)' ) , 'N' , null , now() , 490);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hémoglobine(Sang total)' ) , 'N' , null , now() , 500);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hémotocrite(Sang total)' ) , 'N' , null , now() , 510);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Volume Globulaire Moyen(Sang total)' ) , 'N' , null , now() , 520);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Teneur Corpusculaire Moyenne en Hémoglobine(Sang total)' ) , 'N' , null , now() , 530);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Concentration Corpusculaire Moyenne en Hémoglobine(Sang total)' ) , 'N' , null , now() , 540);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Plaquette(Sang total)' ) , 'N' , null , now() , 550);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Polynucléaires Neutrophiles (%)(Sang total)' ) , 'N' , null , now() , 560);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Polynucléaires Neutrophiles (Abs)(Sang total)' ) , 'N' , null , now() , 570);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Polynucléaires Eosinophiles (%)(Sang total)' ) , 'N' , null , now() , 580);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Polynucléaires Eosinophiles (Abs)(Sang total)' ) , 'N' , null , now() , 590);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Polynucléaires basophiles (%)(Sang total)' ) , 'N' , null , now() , 600);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Polynucléaires basophiles (Abs)(Sang total)' ) , 'N' , null , now() , 610);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lymphocytes (%)(Sang total)' ) , 'N' , null , now() , 620);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lymphocytes (Abs)(Sang total)' ) , 'N' , null , now() , 630);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Monocytes (%)(Sang total)' ) , 'N' , null , now() , 640);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Monocytes (Abs)(Sang total)' ) , 'N' , null , now() , 650);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HBs AG (antigén australia)(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 660);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HBs AG (antigén australia)(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 670);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test urinaire de grossesse(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 680);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test urinaire de grossesse(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 690);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Protéinurie sur bandelette(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif +' )  , now() , 700);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Protéinurie sur bandelette(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif ++' )  , now() , 710);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Protéinurie sur bandelette(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif +++' )  , now() , 720);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Protéinurie sur bandelette(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 730);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 740);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='HIV1' )  , now() , 750);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='HIV2' )  , now() , 760);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='HIVD' )  , now() , 770);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indéterminé'' )  , now() , 780);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalid' )  , now() , 790);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 800);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='HIV1' )  , now() , 810);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='HIV2' )  , now() , 820);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='HIVD' )  , now() , 830);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indéterminé'' )  , now() , 840);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalid' )  , now() , 850);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/10(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 860);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/10(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='HIV1' )  , now() , 870);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/10(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='HIV2' )  , now() , 880);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/10(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='HIVD' )  , now() , 890);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/10(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indéterminé'' )  , now() , 900);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/10(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalid' )  , now() , 910);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/10(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 920);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/10(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='HIV1' )  , now() , 930);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/10(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='HIV2' )  , now() , 940);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/10(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='HIVD' )  , now() , 950);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/10(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indéterminé'' )  , now() , 960);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/10(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalid' )  , now() , 970);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/100(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 980);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/100(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='HIV1' )  , now() , 990);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/100(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='HIV2' )  , now() , 1000);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/100(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='HIVD' )  , now() , 1010);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/100(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indéterminé'' )  , now() , 1020);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/100(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalid' )  , now() , 1030);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/100(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 1040);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/100(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='HIV1' )  , now() , 1050);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/100(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='HIV2' )  , now() , 1060);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/100(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='HIVD' )  , now() , 1070);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/100(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indéterminé'' )  , now() , 1080);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Genie II 1/100(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalid' )  , now() , 1090);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Vironstika(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Vironstika(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 1110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Vironstika(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indéterminé'' )  , now() , 1120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Vironstika(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalid' )  , now() , 1130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Murex(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Murex(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 1150);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Murex(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indéterminé'' )  , now() , 1160);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Murex(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalid' )  , now() , 1170);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Integral(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1180);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Integral(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 1190);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Integral(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indéterminé'' )  , now() , 1200);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Integral(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalid' )  , now() , 1210);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Western blot 1(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1220);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Western blot 1(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 1230);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Western blot 1(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indéterminé'' )  , now() , 1240);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Western blot 1(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalid' )  , now() , 1250);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Western blot 2(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1260);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Western blot 2(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 1270);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Western blot 2(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indéterminé'' )  , now() , 1280);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Western blot 2(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalid' )  , now() , 1290);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'p24Ag(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='HIV1' )  , now() , 1300);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'p24Ag(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 1310);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'p24Ag(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indéterminé'' )  , now() , 1320);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'DNA PCR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1330);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'DNA PCR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Négatif' )  , now() , 1340);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'DNA PCR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indéterminé'' )  , now() , 1350);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'DNA PCR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalid' )  , now() , 1360);
