INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test Rapide VIH(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 10);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test Rapide VIH(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 20);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test Rapide VIH(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 30);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test Rapide VIH(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 40);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test Rapide VIH(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 50);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test Rapide VIH(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 60);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Elisa(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 70);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Elisa(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 80);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test Rapide VIH(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 90);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test Rapide VIH(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Elisa(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Elisa(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Elisa(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Elisa(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Elisa(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 150);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Elisa(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 160);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 170);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 180);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 190);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 200);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 210);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 220);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 230);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 240);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 250);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 260);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 270);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 280);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Negatif' )  , now() , 290);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Positif' )  , now() , 300);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Negatif' )  , now() , 310);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Positif' )  , now() , 320);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Douteux' )  , now() , 330);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Douteux' )  , now() , 340);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Negatif' )  , now() , 350);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Positif' )  , now() , 360);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Negatif' )  , now() , 370);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Positif' )  , now() , 380);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Douteux' )  , now() , 390);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Douteux' )  , now() , 400);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Negatif' )  , now() , 410);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Positif' )  , now() , 420);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Negatif' )  , now() , 430);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Positif' )  , now() , 440);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Douteux' )  , now() , 450);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Douteux' )  , now() , 460);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Negatif' )  , now() , 470);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Positif' )  , now() , 480);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Negatif' )  , now() , 490);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Positif' )  , now() , 500);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Douteux' )  , now() , 510);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Douteux' )  , now() , 520);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Negatif' )  , now() , 530);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Positif' )  , now() , 540);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Negatif' )  , now() , 550);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Positif' )  , now() , 560);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Douteux' )  , now() , 570);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Douteux' )  , now() , 580);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Negatif' )  , now() , 590);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Positif' )  , now() , 600);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Negatif' )  , now() , 610);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Positif' )  , now() , 620);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Douteux' )  , now() , 630);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Douteux' )  , now() , 640);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue NS1Ag(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 650);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue NS1Ag(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 660);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue NS1Ag(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Douteux' )  , now() , 670);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue NS1Ag(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 680);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue NS1Ag(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 690);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue NS1Ag(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Douteux' )  , now() , 700);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite A IgM(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 710);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite A IgM(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 720);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite A IgM(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Douteux' )  , now() , 730);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite A IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 740);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite A IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 750);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite A IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Douteux' )  , now() , 760);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite B Ag(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 770);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite B Ag(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 780);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite B Ag(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 790);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite B Ag(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 800);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite C IgM(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 810);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite C IgM(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 820);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite C IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 830);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite C IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 840);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite E IgM(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 850);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite E IgM(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 860);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite E IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 870);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite E IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 880);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Influenza A/Immunofluoresence(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 890);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Influenza A/Immunofluoresence(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 900);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Influenza A/Immunofluoresence(Ecouvillon Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 910);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Influenza A/Immunofluoresence(Ecouvillon Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 920);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Influenza A/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 930);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Influenza A/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 940);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Influenza B/Immunofluoresence(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 950);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Influenza B/Immunofluoresence(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 960);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Influenza B/Immunofluoresence(Ecouvillon Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 970);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Influenza B/Immunofluoresence(Ecouvillon Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 980);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Influenza B/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 990);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Influenza B/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1000);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 1/Immunofluoresence(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1010);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 1/Immunofluoresence(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1020);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 1/Immunofluoresence(Ecouvillon Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1030);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 1/Immunofluoresence(Ecouvillon Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1040);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 1/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1050);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 1/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1060);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 2/Immunofluoresence(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1070);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 2/Immunofluoresence(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1080);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 2/Immunofluoresence(Ecouvillon Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1090);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 2/Immunofluoresence(Ecouvillon Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 2/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 2/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 3/Immunofluoresence(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 3/Immunofluoresence(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 3/Immunofluoresence(Ecouvillon Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1150);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 3/Immunofluoresence(Ecouvillon Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1160);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 3/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1170);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 3/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1180);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire Synctial(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1190);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire Synctial(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1200);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire Synctial(Ecouvillon Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1210);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire Synctial(Ecouvillon Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1220);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire Synctial(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1230);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire Synctial(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1240);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Adenivirus/Immunofluoresence(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1250);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Adenivirus/Immunofluoresence(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1260);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Adenivirus/Immunofluoresence(Ecouvillon Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1270);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Adenivirus/Immunofluoresence(Ecouvillon Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1280);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Adenivirus/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1290);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Adenivirus/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1300);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rotavirus Elisa(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1310);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rotavirus Elisa(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1320);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'SyphilisTest Rapid(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1330);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'SyphilisTest Rapid(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1340);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Polio Selles 1(Selles 1)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1350);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Polio Selles 1(Selles 1)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1360);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Polio Selles 2(Selles 2)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1370);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Polio Selles 2(Selles 2)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1380);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Non-Reactif' )  , now() , 1390);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif' )  , now() , 1400);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2u' )  , now() , 1410);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='4u' )  , now() , 1420);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='8u' )  , now() , 1430);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='16u' )  , now() , 1440);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='32u' )  , now() , 1450);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='64u' )  , now() , 1460);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='128u' )  , now() , 1470);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='256u' )  , now() , 1480);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='> 572' )  , now() , 1490);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis TPHA(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1500);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis TPHA(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1510);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR Qualitatif(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1  Non-Détecté' )  , now() , 1520);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR Qualitatif(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Detecte' )  , now() , 1530);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR Qualitatif(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='PCR1' )  , now() , 1540);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR Qualitatif(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='PCR2' )  , now() , 1550);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR Qualitatif(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='PCR3' )  , now() , 1560);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR Qualitatif(Sang Total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1  Non-Détecté' )  , now() , 1570);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR Qualitatif(Sang Total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='ADN VIH-1 Detecte' )  , now() , 1580);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR Qualitatif(Sang Total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='PCR1' )  , now() , 1590);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR Qualitatif(Sang Total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='PCR2' )  , now() , 1600);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR Qualitatif(Sang Total)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='PCR3' )  , now() , 1610);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 Charge Virale(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetectable <300' )  , now() , 1620);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 Charge Virale(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Detectable' )  , now() , 1630);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 Charge Virale(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetectable <300' )  , now() , 1640);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 Charge Virale(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Detectable' )  , now() , 1650);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Virus Respiratoire Influenza(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Influenza A Negatif' )  , now() , 1660);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Virus Respiratoire Influenza(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Influenza A H1N1v' )  , now() , 1670);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Virus Respiratoire Influenza(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Influenza A H1N1s' )  , now() , 1680);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Virus Respiratoire Influenza(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Influenza A H3N2s' )  , now() , 1690);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Virus Respiratoire Influenza(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Influenza A H5N1' )  , now() , 1700);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Virus Respiratoire Influenza(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='' )  , now() , 1710);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Virus Respiratoire Influenza(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Influenza B Positif' )  , now() , 1720);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Virus Respiratoire Influenza(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Influenza B Negatif' )  , now() , 1730);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Germes Enterogastriques pathogènes(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1740);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Germes Enterogastriques pathogènes(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Adenovirus 40/41' )  , now() , 1750);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Germes Enterogastriques pathogènes(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Campylobacter' )  , now() , 1760);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Germes Enterogastriques pathogènes(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Clostridim difficile toxin A/B' )  , now() , 1770);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Germes Enterogastriques pathogènes(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Cryptosporidium' )  , now() , 1780);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Germes Enterogastriques pathogènes(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Entamoeba histolytica' )  , now() , 1790);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Germes Enterogastriques pathogènes(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='postif pour E. coli 0157' )  , now() , 1800);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Germes Enterogastriques pathogènes(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Enterotoxigenic E. coli (ETEC) LT/ST' )  , now() , 1810);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Germes Enterogastriques pathogènes(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Giargia' )  , now() , 1820);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Germes Enterogastriques pathogènes(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Norovirus GI/GII' )  , now() , 1830);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Germes Enterogastriques pathogènes(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Rotavirus A' )  , now() , 1840);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Germes Enterogastriques pathogènes(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Salmonella' )  , now() , 1850);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Germes Enterogastriques pathogènes(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Shiga-like Toxin producing E.coli (STEC) stx1/stx2' )  , now() , 1860);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Germes Enterogastriques pathogènes(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Shigella' )  , now() , 1870);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Germes Enterogastriques pathogènes(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Vibrio cholerae' )  , now() , 1880);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Germes Enterogastriques pathogènes(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Yersinia Enterocolitica' )  , now() , 1890);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1900);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Influenza A H1' )  , now() , 1910);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Influenza H3' )  , now() , 1920);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Influenza 2009 H1N1' )  , now() , 1930);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Influenza B' )  , now() , 1940);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif  pour Respiratory Syncytial Virus' )  , now() , 1950);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Coronavirus 229E' )  , now() , 1960);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Coronavirus OC43' )  , now() , 1970);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Coronavirus NL63' )  , now() , 1980);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Coronavirus HKU1' )  , now() , 1990);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Parainfluenza virus Parainfluenza 1' )  , now() , 2000);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Parainfluenza virus Parainfluenza 2' )  , now() , 2010);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Parainfluenza virus Parainfluenza 3' )  , now() , 2020);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Parainfluenza virus Parainfluenza 4' )  , now() , 2030);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Human Metapneumovirus' )  , now() , 2040);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Enterovirus/Rhinovirus' )  , now() , 2050);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour Adenovirus' )  , now() , 2060);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Virus Respiratoire(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif pour human Bocavirus' )  , now() , 2070);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Micobacterium tuberculosis Drug Resistant(Culture)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='MTB Complex positif' )  , now() , 2080);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Micobacterium tuberculosis Drug Resistant(Culture)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='MTB Complex Negatif' )  , now() , 2090);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Micobacterium tuberculosis Drug Resistant(Culture)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant a la Rifampicin' )  , now() , 2100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Micobacterium tuberculosis Drug Resistant(Culture)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry =$$Resistant a l'Isoniazid$$ )  , now() , 2110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Micobacterium tuberculosis Drug Resistant(Culture)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible a la Rifampicin' )  , now() , 2120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Micobacterium tuberculosis Drug Resistant(Culture)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry =$$Sensible a l'Isonazid$$ )  , now() , 2130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Micobacterium tuberculosis Drug Resistant(Culture)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalid' )  , now() , 2140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Micobacterium tuberculosis Drug Resistant(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='MTB Complex positif' )  , now() , 2150);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Micobacterium tuberculosis Drug Resistant(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='MTB Complex Negatif' )  , now() , 2160);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Micobacterium tuberculosis Drug Resistant(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant a la Rifampicin' )  , now() , 2170);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Micobacterium tuberculosis Drug Resistant(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry =$$Resistant a l'Isoniazid$$ )  , now() , 2180);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Micobacterium tuberculosis Drug Resistant(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible a la Rifampicin' )  , now() , 2190);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Micobacterium tuberculosis Drug Resistant(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry =$$Sensible a l'Isonazid$$ )  , now() , 2200);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Micobacterium tuberculosis Drug Resistant(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Invalid' )  , now() , 2210);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'CD4  Compte Abs(Sang Total)' ) , 'N' , null , now() , 2220);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'CD4 Compte en %(Sang Total)' ) , 'N' , null , now() , 2230);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Compte des Globules Blancs(Sang)' ) , 'N' , null , now() , 2240);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Compte des Globules Rouges(Sang)' ) , 'N' , null , now() , 2250);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hemoglobine(Sang)' ) , 'N' , null , now() , 2260);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hematocrite(Sang)' ) , 'N' , null , now() , 2270);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VGM(Sang)' ) , 'N' , null , now() , 2280);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'TCMH(Sang)' ) , 'N' , null , now() , 2290);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'CCMH(Sang)' ) , 'N' , null , now() , 2300);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Plaquettes(Sang)' ) , 'N' , null , now() , 2310);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Neutrophiles(Sang)' ) , 'N' , null , now() , 2320);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lymphocytes(Sang)' ) , 'N' , null , now() , 2330);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mixtes(Sang)' ) , 'N' , null , now() , 2340);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'LCR Coloration de Gram  (LCR)' ) , 'R' , null , now() , 2350);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Selles Coloration de Gram(Selles)' ) , 'R' , null , now() , 2360);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coloration de Gram Ecouvillon Pharynge(Ecouvillon Pharynge)' ) , 'R' , null , now() , 2370);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coloration de Gram Ecouvillon Naso-Pharynge(Ecouvillon Naso-Pharynge)' ) , 'R' , null , now() , 2380);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coloration de Gram Sang(Sang)' ) , 'R' , null , now() , 2390);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Immuno Dot Leptospira IgM (Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2400);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Immuno Dot Leptospira IgM (Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2410);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leptospirose IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2420);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leptospirose IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2430);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leptospirose IgM(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2440);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leptospirose IgM(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2450);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leptospirose IgM(Sang capillaire)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2460);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leptospirose IgM(Sang capillaire)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2470);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hemoculture IgM(Sang)' ) , 'R' , null , now() , 2480);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test RapideSalmonelle typhi IgM 09(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2490);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test RapideSalmonelle typhi IgM 09(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2500);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test RapideSalmonelle typhi IgM 09(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Borderline' )  , now() , 2510);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test Rapide Salmonelle typhi  IgM 09(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2520);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test Rapide Salmonelle typhi  IgM 09(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2530);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test Rapide Salmonelle typhi  IgM 09(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Borderline' )  , now() , 2540);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'LCR : Culture(LCR)' ) , 'R' , null , now() , 2550);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'LCR Test Rapide(LCR)' ) , 'R' , null , now() , 2560);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Salmonelle(Selles)' ) , 'R' , null , now() , 2570);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Shigelle(Selles)' ) , 'R' , null , now() , 2580);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de V.cholerae(Selles)' ) , 'R' , null , now() , 2590);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de C. diphteriae(Ecouvillon Pharynge)' ) , 'R' , null , now() , 2600);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de B. pertussis(Ecouvillon Naso-Pharynge)' ) , 'R' , null , now() , 2610);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 2620);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 2630);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 2640);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 2650);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 2660);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 2670);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 2680);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 2690);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 2700);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 2710);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 2720);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 2730);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 2740);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 2750);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 2760);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 2770);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 2780);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide nalidixique 30 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 2790);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide fusidique 10 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 2800);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide fusidique 10 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 2810);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide fusidique 10 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 2820);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide fusidique 10 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 2830);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide fusidique 10 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 2840);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide fusidique 10 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 2850);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide fusidique 10 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 2860);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide fusidique 10 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 2870);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide fusidique 10 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 2880);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide fusidique 10 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 2890);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide fusidique 10 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 2900);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide fusidique 10 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 2910);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide fusidique 10 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 2920);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide fusidique 10 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 2930);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide fusidique 10 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 2940);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide fusidique 10 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 2950);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide fusidique 10 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 2960);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide fusidique 10 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 2970);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amikacine 30 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 2980);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amikacine 30 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 2990);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amikacine 30 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3000);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amikacine 30 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3010);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amikacine 30 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3020);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amikacine 30 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3030);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amikacine 30 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3040);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amikacine 30 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3050);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amikacine 30 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3060);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amikacine 30 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3070);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amikacine 30 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3080);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amikacine 30 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3090);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amikacine 30 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amikacine 30 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amikacine 30 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amikacine 30 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amikacine 30 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amikacine 30 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3150);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline 10 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3160);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline 10 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3170);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline 10 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3180);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline 10 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3190);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline 10 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3200);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline 10 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3210);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline 10 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3220);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline 10 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3230);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline 10 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3240);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline 10 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3250);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline 10 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3260);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline 10 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3270);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline 10 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3280);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline 10 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3290);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline 10 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3300);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline 10 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3310);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline 10 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3320);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline 10 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3330);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline/Sulbactam 10/10 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3340);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline/Sulbactam 10/10 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3350);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline/Sulbactam 10/10 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3360);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline/Sulbactam 10/10 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3370);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline/Sulbactam 10/10 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3380);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline/Sulbactam 10/10 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3390);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline/Sulbactam 10/10 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3400);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline/Sulbactam 10/10 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3410);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline/Sulbactam 10/10 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3420);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline/Sulbactam 10/10 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3430);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline/Sulbactam 10/10 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3440);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline/Sulbactam 10/10 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3450);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline/Sulbactam 10/10 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3460);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline/Sulbactam 10/10 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3470);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline/Sulbactam 10/10 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3480);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline/Sulbactam 10/10 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3490);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline/Sulbactam 10/10 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3500);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ampicilline/Sulbactam 10/10 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3510);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3520);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3530);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3540);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3550);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3560);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3570);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3580);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3590);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3600);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3610);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3620);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3630);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3640);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3650);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3660);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3670);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3680);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Amoxicilline/Acide clavulanique 20/10 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3690);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Azithromicine 15 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3700);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Azithromicine 15 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3710);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Azithromicine 15 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3720);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Azithromicine 15 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3730);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Azithromicine 15 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3740);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Azithromicine 15 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3750);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Azithromicine 15 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3760);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Azithromicine 15 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3770);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Azithromicine 15 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3780);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Azithromicine 15 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3790);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Azithromicine 15 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3800);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Azithromicine 15 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3810);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Azithromicine 15 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3820);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Azithromicine 15 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3830);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Azithromicine 15 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3840);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Azithromicine 15 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3850);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Azithromicine 15 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3860);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Azithromicine 15 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3870);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aztreinam 30 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3880);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aztreinam 30 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3890);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aztreinam 30 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3900);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aztreinam 30 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3910);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aztreinam 30 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3920);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aztreinam 30 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3930);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aztreinam 30 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3940);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aztreinam 30 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3950);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aztreinam 30 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3960);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aztreinam 30 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 3970);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aztreinam 30 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 3980);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aztreinam 30 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 3990);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aztreinam 30 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4000);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aztreinam 30 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4010);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aztreinam 30 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4020);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aztreinam 30 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4030);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aztreinam 30 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4040);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aztreinam 30 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4050);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefalotine 30 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4060);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefalotine 30 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4070);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefalotine 30 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4080);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefalotine 30 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4090);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefalotine 30 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefalotine 30 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefalotine 30 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefalotine 30 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefalotine 30 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefalotine 30 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4150);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefalotine 30 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4160);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefalotine 30 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4170);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefalotine 30 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4180);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefalotine 30 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4190);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefalotine 30 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4200);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefalotine 30 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4210);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefalotine 30 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4220);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefalotine 30 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4230);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefotaxime 30 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4240);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefotaxime 30 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4250);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefotaxime 30 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4260);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefotaxime 30 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4270);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefotaxime 30 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4280);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefotaxime 30 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4290);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefotaxime 30 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4300);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefotaxime 30 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4310);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefotaxime 30 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4320);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefotaxime 30 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4330);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefotaxime 30 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4340);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefotaxime 30 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4350);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefotaxime 30 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4360);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefotaxime 30 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4370);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefotaxime 30 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4380);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefotaxime 30 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4390);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefotaxime 30 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4400);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefotaxime 30 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4410);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftazidime 30 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4420);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftazidime 30 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4430);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftazidime 30 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4440);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftazidime 30 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4450);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftazidime 30 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4460);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftazidime 30 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4470);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftazidime 30 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4480);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftazidime 30 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4490);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftazidime 30 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4500);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftazidime 30 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4510);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftazidime 30 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4520);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftazidime 30 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4530);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftazidime 30 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4540);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftazidime 30 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4550);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftazidime 30 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4560);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftazidime 30 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4570);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftazidime 30 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4580);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftazidime 30 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4590);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4600);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4610);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4620);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4630);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4640);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4650);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4660);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4670);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4680);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4690);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4700);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4710);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4720);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4730);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4740);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4750);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4760);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftriaxone 30 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4770);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefixime10 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4780);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefixime10 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4790);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefixime10 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4800);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefixime10 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4810);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefixime10 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4820);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefixime10 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4830);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefixime10 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4840);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefixime10 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4850);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefixime10 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4860);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefixime10 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4870);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefixime10 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4880);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefixime10 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4890);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefixime10 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4900);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefixime10 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4910);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefixime10 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4920);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefixime10 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4930);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefixime10 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4940);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cefixime10 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4950);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftizoxime 30 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4960);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftizoxime 30 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 4970);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftizoxime 30 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 4980);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftizoxime 30 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 4990);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftizoxime 30 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5000);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftizoxime 30 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5010);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftizoxime 30 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5020);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftizoxime 30 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5030);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftizoxime 30 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5040);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftizoxime 30 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5050);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftizoxime 30 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5060);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftizoxime 30 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5070);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftizoxime 30 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5080);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftizoxime 30 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5090);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftizoxime 30 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftizoxime 30 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftizoxime 30 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ceftizoxime 30 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5150);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5160);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5170);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5180);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5190);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5200);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5210);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5220);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5230);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5240);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5250);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5260);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5270);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5280);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5290);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5300);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chloramfenicol 30 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5310);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5320);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5330);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5340);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5350);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5360);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5370);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5380);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5390);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5400);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5410);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5420);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5430);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5440);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5450);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5460);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5470);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5480);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ciprofloxacine 5 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5490);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colistine 50 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5500);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colistine 50 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5510);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colistine 50 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5520);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colistine 50 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5530);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colistine 50 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5540);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colistine 50 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5550);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colistine 50 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5560);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colistine 50 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5570);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colistine 50 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5580);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colistine 50 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5590);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colistine 50 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5600);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colistine 50 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5610);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colistine 50 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5620);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colistine 50 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5630);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colistine 50 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5640);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colistine 50 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5650);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colistine 50 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5660);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colistine 50 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5670);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Enoxacine 50 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5680);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Enoxacine 50 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5690);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Enoxacine 50 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5700);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Enoxacine 50 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5710);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Enoxacine 50 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5720);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Enoxacine 50 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5730);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Enoxacine 50 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5740);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Enoxacine 50 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5750);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Enoxacine 50 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5760);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Enoxacine 50 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5770);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Enoxacine 50 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5780);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Enoxacine 50 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5790);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Enoxacine 50 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5800);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Enoxacine 50 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5810);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Enoxacine 50 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5820);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Enoxacine 50 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5830);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Enoxacine 50 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5840);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Enoxacine 50 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5850);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Erythromycine 15 UI(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5860);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Erythromycine 15 UI(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5870);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Erythromycine 15 UI(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5880);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Erythromycine 15 UI(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5890);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Erythromycine 15 UI(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5900);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Erythromycine 15 UI(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5910);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Erythromycine 15 UI(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5920);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Erythromycine 15 UI(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5930);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Erythromycine 15 UI(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5940);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Erythromycine 15 UI(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5950);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Erythromycine 15 UI(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5960);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Erythromycine 15 UI(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 5970);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Erythromycine 15 UI(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 5980);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Erythromycine 15 UI(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 5990);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Erythromycine 15 UI(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6000);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Erythromycine 15 UI(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6010);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Erythromycine 15 UI(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6020);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Erythromycine 15 UI(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6030);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Fosfomycine 50 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6040);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Fosfomycine 50 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6050);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Fosfomycine 50 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6060);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Fosfomycine 50 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6070);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Fosfomycine 50 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6080);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Fosfomycine 50 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6090);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Fosfomycine 50 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Fosfomycine 50 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Fosfomycine 50 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Fosfomycine 50 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Fosfomycine 50 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Fosfomycine 50 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6150);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Fosfomycine 50 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6160);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Fosfomycine 50 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6170);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Fosfomycine 50 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6180);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Fosfomycine 50 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6190);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Fosfomycine 50 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6200);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Fosfomycine 50 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6210);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Gentamicine 15 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6220);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Gentamicine 15 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6230);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Gentamicine 15 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6240);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Gentamicine 15 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6250);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Gentamicine 15 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6260);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Gentamicine 15 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6270);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Gentamicine 15 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6280);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Gentamicine 15 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6290);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Gentamicine 15 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6300);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Gentamicine 15 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6310);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Gentamicine 15 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6320);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Gentamicine 15 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6330);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Gentamicine 15 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6340);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Gentamicine 15 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6350);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Gentamicine 15 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6360);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Gentamicine 15 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6370);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Gentamicine 15 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6380);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Gentamicine 15 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6390);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lincomycine 15 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6400);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lincomycine 15 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6410);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lincomycine 15 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6420);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lincomycine 15 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6430);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lincomycine 15 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6440);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lincomycine 15 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6450);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lincomycine 15 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6460);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lincomycine 15 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6470);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lincomycine 15 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6480);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lincomycine 15 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6490);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lincomycine 15 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6500);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lincomycine 15 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6510);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lincomycine 15 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6520);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lincomycine 15 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6530);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lincomycine 15 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6540);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lincomycine 15 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6550);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lincomycine 15 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6560);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lincomycine 15 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6570);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levofloxacine 5 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6580);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levofloxacine 5 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6590);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levofloxacine 5 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6600);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levofloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6610);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levofloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6620);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levofloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6630);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levofloxacine 5 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6640);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levofloxacine 5 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6650);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levofloxacine 5 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6660);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levofloxacine 5 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6670);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levofloxacine 5 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6680);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levofloxacine 5 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6690);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levofloxacine 5 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6700);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levofloxacine 5 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6710);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levofloxacine 5 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6720);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levofloxacine 5 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6730);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levofloxacine 5 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6740);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levofloxacine 5 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6750);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lomefloxacine 5 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6760);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lomefloxacine 5 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6770);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lomefloxacine 5 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6780);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lomefloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6790);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lomefloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6800);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lomefloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6810);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lomefloxacine 5 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6820);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lomefloxacine 5 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6830);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lomefloxacine 5 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6840);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lomefloxacine 5 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6850);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lomefloxacine 5 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6860);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lomefloxacine 5 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6870);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lomefloxacine 5 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6880);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lomefloxacine 5 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6890);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lomefloxacine 5 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6900);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lomefloxacine 5 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6910);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lomefloxacine 5 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6920);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lomefloxacine 5 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6930);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mecillinam 10 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6940);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mecillinam 10 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6950);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mecillinam 10 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6960);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mecillinam 10 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 6970);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mecillinam 10 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 6980);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mecillinam 10 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 6990);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mecillinam 10 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7000);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mecillinam 10 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7010);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mecillinam 10 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7020);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mecillinam 10 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7030);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mecillinam 10 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7040);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mecillinam 10 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7050);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mecillinam 10 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7060);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mecillinam 10 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7070);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mecillinam 10 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7080);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mecillinam 10 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7090);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mecillinam 10 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mecillinam 10 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Meropeneme 10 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Meropeneme 10 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Meropeneme 10 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Meropeneme 10 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7150);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Meropeneme 10 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7160);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Meropeneme 10 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7170);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Meropeneme 10 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7180);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Meropeneme 10 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7190);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Meropeneme 10 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7200);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Meropeneme 10 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7210);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Meropeneme 10 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7220);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Meropeneme 10 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7230);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Meropeneme 10 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7240);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Meropeneme 10 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7250);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Meropeneme 10 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7260);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Meropeneme 10 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7270);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Meropeneme 10 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7280);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Meropeneme 10 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7290);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxalactam(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7300);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxalactam(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7310);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxalactam(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7320);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxalactam(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7330);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxalactam(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7340);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxalactam(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7350);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxalactam(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7360);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxalactam(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7370);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxalactam(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7380);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxalactam(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7390);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxalactam(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7400);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxalactam(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7410);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxalactam(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7420);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxalactam(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7430);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxalactam(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7440);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxalactam(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7450);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxalactam(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7460);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxalactam(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7470);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxifloxacine 5 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7480);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxifloxacine 5 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7490);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxifloxacine 5 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7500);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxifloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7510);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxifloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7520);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxifloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7530);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxifloxacine 5 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7540);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxifloxacine 5 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7550);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxifloxacine 5 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7560);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxifloxacine 5 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7570);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxifloxacine 5 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7580);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxifloxacine 5 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7590);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxifloxacine 5 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7600);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxifloxacine 5 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7610);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Moxifloxacine 5 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7620);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Nitrofuranes 300 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7630);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Nitrofuranes 300 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7640);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Nitrofuranes 300 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7650);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Nitrofuranes 300 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7660);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Nitrofuranes 300 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7670);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Nitrofuranes 300 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7680);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Nitrofuranes 300 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7690);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Nitrofuranes 300 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7700);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Nitrofuranes 300 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7710);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Nitrofuranes 300 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7720);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Nitrofuranes 300 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7730);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Nitrofuranes 300 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7740);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Nitrofuranes 300 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7750);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Nitrofuranes 300 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7760);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Nitrofuranes 300 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7770);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Nitrofuranes 300 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7780);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Nitrofuranes 300 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7790);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Nitrofuranes 300 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7800);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7810);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7820);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7830);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7840);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7850);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7860);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7870);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7880);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7890);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7900);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7910);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7920);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7930);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7940);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7950);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7960);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 7970);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Norfloxacine 5 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 7980);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 1 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 7990);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 1 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8000);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 1 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8010);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 1 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8020);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 1 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8030);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 1 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8040);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 1 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8050);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 1 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8060);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 1 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8070);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 1 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8080);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 1 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8090);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 1 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 1 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 1 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 1 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 1 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 1 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8150);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 1 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8160);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 5 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8170);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 5 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8180);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 5 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8190);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 5 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8200);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 5 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8210);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 5 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8220);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 5 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8230);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 5 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8240);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 5 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8250);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 5 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8260);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 5 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8270);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 5 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8280);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 5 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8290);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 5 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8300);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 5 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8310);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 5 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8320);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 5 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8330);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxacilline 5 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8340);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ofloxacine 5 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8350);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ofloxacine 5 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8360);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ofloxacine 5 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8370);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ofloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8380);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ofloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8390);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ofloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8400);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ofloxacine 5 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8410);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ofloxacine 5 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8420);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ofloxacine 5 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8430);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ofloxacine 5 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8440);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ofloxacine 5 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8450);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ofloxacine 5 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8460);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ofloxacine 5 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8470);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ofloxacine 5 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8480);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ofloxacine 5 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8490);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ofloxacine 5 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8500);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ofloxacine 5 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8510);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ofloxacine 5 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8520);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pefloxacine 5 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8530);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pefloxacine 5 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8540);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pefloxacine 5 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8550);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pefloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8560);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pefloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8570);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pefloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8580);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pefloxacine 5 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8590);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pefloxacine 5 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8600);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pefloxacine 5 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8610);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pefloxacine 5 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8620);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pefloxacine 5 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8630);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pefloxacine 5 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8640);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pefloxacine 5 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8650);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pefloxacine 5 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8660);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pefloxacine 5 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8670);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pefloxacine 5 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8680);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pefloxacine 5 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8690);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pefloxacine 5 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8700);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Penicilline G 6 μg (10 UI)(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8710);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Penicilline G 6 μg (10 UI)(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8720);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Penicilline G 6 μg (10 UI)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8730);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Penicilline G 6 μg (10 UI)(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8740);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Penicilline G 6 μg (10 UI)(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8750);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Penicilline G 6 μg (10 UI)(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8760);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Penicilline G 6 μg (10 UI)(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8770);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Penicilline G 6 μg (10 UI)(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8780);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Penicilline G 6 μg (10 UI)(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8790);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Penicilline G 6 μg (10 UI)(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8800);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Penicilline G 6 μg (10 UI)(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8810);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Penicilline G 6 μg (10 UI)(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8820);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Penicilline G 6 μg (10 UI)(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8830);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Penicilline G 6 μg (10 UI)(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8840);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Penicilline G 6 μg (10 UI)(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8850);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Penicilline G 6 μg (10 UI)(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8860);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Penicilline G 6 μg (10 UI)(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8870);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Penicilline G 6 μg (10 UI)(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8880);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Piperacilline 75 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8890);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Piperacilline 75 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8900);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Piperacilline 75 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8910);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Piperacilline 75 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8920);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Piperacilline 75 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8930);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Piperacilline 75 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8940);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Piperacilline 75 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8950);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Piperacilline 75 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8960);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Piperacilline 75 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 8970);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Piperacilline 75 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 8980);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Piperacilline 75 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 8990);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Piperacilline 75 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9000);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Piperacilline 75 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9010);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Piperacilline 75 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9020);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Piperacilline 75 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9030);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Piperacilline 75 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9040);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Piperacilline 75 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9050);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Piperacilline 75 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9060);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pristinamycine 15 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9070);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pristinamycine 15 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9080);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pristinamycine 15 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9090);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pristinamycine 15 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pristinamycine 15 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pristinamycine 15 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pristinamycine 15 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pristinamycine 15 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pristinamycine 15 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9150);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pristinamycine 15 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9160);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pristinamycine 15 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9170);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pristinamycine 15 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9180);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pristinamycine 15 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9190);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pristinamycine 15 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9200);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pristinamycine 15 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9210);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pristinamycine 15 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9220);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pristinamycine 15 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9230);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Pristinamycine 15 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9240);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Quinupristine- Dalfopristine 15 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9250);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Quinupristine- Dalfopristine 15 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9260);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Quinupristine- Dalfopristine 15 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9270);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Quinupristine- Dalfopristine 15 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9280);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Quinupristine- Dalfopristine 15 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9290);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Quinupristine- Dalfopristine 15 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9300);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Quinupristine- Dalfopristine 15 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9310);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Quinupristine- Dalfopristine 15 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9320);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Quinupristine- Dalfopristine 15 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9330);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Quinupristine- Dalfopristine 15 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9340);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Quinupristine- Dalfopristine 15 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9350);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Quinupristine- Dalfopristine 15 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9360);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Quinupristine- Dalfopristine 15 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9370);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Quinupristine- Dalfopristine 15 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9380);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Quinupristine- Dalfopristine 15 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9390);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Quinupristine- Dalfopristine 15 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9400);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Quinupristine- Dalfopristine 15 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9410);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Quinupristine- Dalfopristine 15 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9420);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rifampicine 30 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9430);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rifampicine 30 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9440);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rifampicine 30 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9450);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rifampicine 30 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9460);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rifampicine 30 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9470);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rifampicine 30 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9480);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rifampicine 30 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9490);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rifampicine 30 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9500);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rifampicine 30 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9510);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rifampicine 30 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9520);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rifampicine 30 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9530);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rifampicine 30 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9540);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rifampicine 30 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9550);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rifampicine 30 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9560);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rifampicine 30 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9570);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rifampicine 30 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9580);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rifampicine 30 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9590);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rifampicine 30 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9600);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sparfloxacine 5 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9610);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sparfloxacine 5 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9620);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sparfloxacine 5 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9630);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sparfloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9640);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sparfloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9650);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sparfloxacine 5 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9660);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sparfloxacine 5 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9670);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sparfloxacine 5 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9680);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sparfloxacine 5 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9690);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sparfloxacine 5 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9700);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sparfloxacine 5 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9710);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sparfloxacine 5 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9720);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sparfloxacine 5 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9730);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sparfloxacine 5 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9740);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sparfloxacine 5 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9750);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sparfloxacine 5 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9760);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sparfloxacine 5 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9770);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sparfloxacine 5 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9780);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9790);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9800);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9810);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9820);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9830);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9840);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9850);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9860);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9870);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9880);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9890);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9900);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9910);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9920);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9930);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9940);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9950);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sulfamethoxazole /Trimethroprime23.75 /1,25 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9960);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Teiclopanine 30 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 9970);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Teiclopanine 30 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 9980);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Teiclopanine 30 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 9990);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Teiclopanine 30 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 10000);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Teiclopanine 30 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 10010);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Teiclopanine 30 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 10020);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Teiclopanine 30 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 10030);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Teiclopanine 30 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 10040);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Teiclopanine 30 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 10050);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Teiclopanine 30 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 10060);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Teiclopanine 30 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 10070);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Teiclopanine 30 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 10080);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Teiclopanine 30 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 10090);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Teiclopanine 30 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 10100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Teiclopanine 30 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 10110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Teiclopanine 30 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 10120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Teiclopanine 30 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 10130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Teiclopanine 30 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 10140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tetracycline 30 UI(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 10150);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tetracycline 30 UI(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 10160);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tetracycline 30 UI(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 10170);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tetracycline 30 UI(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 10180);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tetracycline 30 UI(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 10190);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tetracycline 30 UI(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 10200);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tetracycline 30 UI(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 10210);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tetracycline 30 UI(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 10220);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tetracycline 30 UI(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 10230);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tetracycline 30 UI(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 10240);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tetracycline 30 UI(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 10250);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tetracycline 30 UI(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 10260);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tetracycline 30 UI(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 10270);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tetracycline 30 UI(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 10280);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tetracycline 30 UI(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 10290);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tetracycline 30 UI(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 10300);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tetracycline 30 UI(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 10310);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tetracycline 30 UI(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 10320);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ticarcilline 75 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 10330);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ticarcilline 75 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 10340);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ticarcilline 75 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 10350);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ticarcilline 75 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 10360);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ticarcilline 75 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 10370);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ticarcilline 75 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 10380);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ticarcilline 75 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 10390);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ticarcilline 75 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 10400);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ticarcilline 75 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 10410);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ticarcilline 75 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 10420);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ticarcilline 75 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 10430);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ticarcilline 75 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 10440);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ticarcilline 75 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 10450);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ticarcilline 75 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 10460);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ticarcilline 75 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 10470);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ticarcilline 75 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 10480);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ticarcilline 75 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 10490);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ticarcilline 75 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 10500);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tobramycine 10 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 10510);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tobramycine 10 μg(Selles)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 10520);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tobramycine 10 μg(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 10530);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tobramycine 10 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 10540);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tobramycine 10 μg(Ecouvillon Naso-Pharynge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 10550);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tobramycine 10 μg(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 10560);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tobramycine 10 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 10570);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tobramycine 10 μg(Ecouvillon Pharinge)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 10580);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tobramycine 10 μg(Ecouvillon Pharinge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 10590);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tobramycine 10 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 10600);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tobramycine 10 μg(Ecouvillon Nasal)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 10610);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tobramycine 10 μg(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 10620);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tobramycine 10 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 10630);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tobramycine 10 μg(Eau de riviere)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 10640);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tobramycine 10 μg(Eau de riviere)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 10650);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tobramycine 10 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 10660);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tobramycine 10 μg(Sang)' ) , 'Q' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 10670);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Tobramycine 10 μg(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 10680);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Echantillon(Free text)' ) , 'R' , null , now() , 10690);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colaration de Gram(Free text)' ) , 'R' , null , now() , 10700);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture(Free text)' ) , 'R' , null , now() , 10710);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Noiratre' )  , now() , 10720);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sanguinolant' )  , now() , 10730);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Jaunatre' )  , now() , 10740);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Marron' )  , now() , 10750);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Verdatre' )  , now() , 10760);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Grisatre' )  , now() , 10770);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry =' Autre' )  , now() , 10780);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Consistance(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Liquide' )  , now() , 10790);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Consistance(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Solide' )  , now() , 10800);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Consistance(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='semi liquide' )  , now() , 10810);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Consistance(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='pateuse' )  , now() , 10820);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Consistance(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='molle' )  , now() , 10830);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Consistance(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Glaireuse' )  , now() , 10840);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sang Occulte(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 10850);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sang Occulte(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 10860);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 10870);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 10880);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 10890);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 10900);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures simples(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 10910);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures simples(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 10920);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures simples(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 10930);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures simples(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 10940);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures bourgeonantes(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 10950);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures bourgeonantes(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 10960);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures bourgeonantes(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 10970);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures bourgeonantes(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 10980);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Examen Microscopique direct(Selles)' ) , 'R' , null , now() , 10990);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Examen Microscopique après concentration(Selles)' ) , 'R' , null , now() , 11000);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = $$Recherche d'Oxyures (Scotch Tape)(Selles)$$ ) , 'R' , null , now() , 11010);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ziehl Neelsen modifiee(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 11020);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ziehl Neelsen modifiee(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 11030);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ziehl Neelsen modifiee(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Cryptosporidium' )  , now() , 11040);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ziehl Neelsen modifiee(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Cyclospora' )  , now() , 11050);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ziehl Neelsen modifiee(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='isospora belli' )  , now() , 11060);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 11070);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P. falciparum' )  , now() , 11080);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P. vivax' )  , now() , 11090);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P. malariae' )  , now() , 11100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P. ovale' )  , now() , 11110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Trophozoite' )  , now() , 11120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Gametocyte' )  , now() , 11130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Schizonte' )  , now() , 11140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='1+' )  , now() , 11150);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2+' )  , now() , 11160);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='3+' )  , now() , 11170);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='4+' )  , now() , 11180);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria Test Rapide(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 11190);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria Test Rapide(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 11200);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Microfilaires(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 11210);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Microfilaires(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Wuchereria bancrofti' )  , now() , 11220);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Microfilaires(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Mansonella orzandi' )  , now() , 11230);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 1(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 11240);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 1(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 11250);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 1(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 11260);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 1(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 11270);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 1(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Rare (1-9 BAAR/100ch)' )  , now() , 11280);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 2(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 11290);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 2(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 11300);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 2(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 11310);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 2(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 11320);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 2(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Rare (1-9 BAAR/100ch)' )  , now() , 11330);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 3(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 11340);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 3(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 11350);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 3(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 11360);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 3(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 11370);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 3(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Rare (1-9 BAAR/100ch)' )  , now() , 11380);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 1(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 11390);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 1(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 11400);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 1(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 11410);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 1(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 11420);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 1(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Rare (1-9 BAAR/40ch)' )  , now() , 11430);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 2(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 11440);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 2(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 11450);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 2(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 11460);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 2(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 11470);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 2(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Rare (1-9 BAAR/40ch)' )  , now() , 11480);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 3(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 11490);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 3(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 11500);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 3(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 11510);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 3(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 11520);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 3(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Rare (1-9 BAAR/40ch)' )  , now() , 11530);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture de mycobacteries Solide(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Purulent' )  , now() , 11540);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture de mycobacteries Solide(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sanguinolent' )  , now() , 11550);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture de mycobacteries Solide(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Salivaire' )  , now() , 11560);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture de mycobacteries Solide(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 11570);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture de mycobacteries Solide(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Contamine' )  , now() , 11580);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture de mycobacteries Solide(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 11590);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture de mycobacteries liquide(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Purulent' )  , now() , 11600);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture de mycobacteries liquide(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sanguinolent' )  , now() , 11610);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture de mycobacteries liquide(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Salivaire' )  , now() , 11620);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture de mycobacteries liquide(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 11630);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture de mycobacteries liquide(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Contamine' )  , now() , 11640);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture de mycobacteries liquide(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 11650);
