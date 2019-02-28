INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test Rapide VIH(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 10);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test Rapide VIH(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 20);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test Rapide VIH(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 30);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test Rapide VIH(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 40);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test Rapide VIH(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 50);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test Rapide VIH(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 60);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test Rapide VIH(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 70);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test Rapide VIH(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 80);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Elisa(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 90);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Elisa(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Elisa(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Elisa(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Elisa(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Elisa(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Elisa(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 150);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Elisa(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 160);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P17' )  , now() , 170);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P24' )  , now() , 180);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P31' )  , now() , 190);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='GP41' )  , now() , 200);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P51' )  , now() , 210);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P55' )  , now() , 220);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P66' )  , now() , 230);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='GP120' )  , now() , 240);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='GP160' )  , now() , 250);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 260);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 270);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 280);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Serum)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P17' )  , now() , 290);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Serum)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P24' )  , now() , 300);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Serum)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P31' )  , now() , 310);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Serum)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='GP41' )  , now() , 320);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Serum)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P51' )  , now() , 330);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Serum)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P55' )  , now() , 340);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Serum)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P66' )  , now() , 350);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Serum)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='GP120' )  , now() , 360);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Serum)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='GP160' )  , now() , 370);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Serum)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 380);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Serum)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 390);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Serum)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 400);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Plasma)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P17' )  , now() , 410);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Plasma)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P24' )  , now() , 420);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Plasma)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P31' )  , now() , 430);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Plasma)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='GP41' )  , now() , 440);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Plasma)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P51' )  , now() , 450);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Plasma)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P55' )  , now() , 460);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Plasma)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P66' )  , now() , 470);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Plasma)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='GP120' )  , now() , 480);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Plasma)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='GP160' )  , now() , 490);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Plasma)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 500);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Plasma)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 510);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(Plasma)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 520);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(DBS)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P17' )  , now() , 530);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(DBS)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P24' )  , now() , 540);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(DBS)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P31' )  , now() , 550);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(DBS)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='GP41' )  , now() , 560);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(DBS)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P51' )  , now() , 570);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(DBS)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P55' )  , now() , 580);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(DBS)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P66' )  , now() , 590);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(DBS)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='GP120' )  , now() , 600);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(DBS)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='GP160' )  , now() , 610);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(DBS)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 620);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(DBS)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 630);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Western Blot(DBS)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 640);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Negatif' )  , now() , 650);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Positif' )  , now() , 660);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Negatif' )  , now() , 670);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Positif' )  , now() , 680);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Douteux' )  , now() , 690);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Douteux' )  , now() , 700);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Negatif' )  , now() , 710);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Positif' )  , now() , 720);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Negatif' )  , now() , 730);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Positif' )  , now() , 740);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Douteux' )  , now() , 750);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Douteux' )  , now() , 760);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Negatif' )  , now() , 770);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Positif' )  , now() , 780);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Negatif' )  , now() , 790);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Positif' )  , now() , 800);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Douteux' )  , now() , 810);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Douteux' )  , now() , 820);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Negatif' )  , now() , 830);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Positif' )  , now() , 840);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Negatif' )  , now() , 850);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Positif' )  , now() , 860);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Douteux' )  , now() , 870);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Douteux' )  , now() , 880);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Negatif' )  , now() , 890);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Positif' )  , now() , 900);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Negatif' )  , now() , 910);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Positif' )  , now() , 920);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Douteux' )  , now() , 930);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Douteux' )  , now() , 940);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Negatif' )  , now() , 950);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Positif' )  , now() , 960);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Negatif' )  , now() , 970);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Positif' )  , now() , 980);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgM Douteux' )  , now() , 990);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='IgG Douteux' )  , now() , 1000);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue NS1Ag(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1010);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue NS1Ag(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1020);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue NS1Ag(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Douteux' )  , now() , 1030);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue NS1Ag(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1040);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue NS1Ag(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1050);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue NS1Ag(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Douteux' )  , now() , 1060);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite A IgM(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1070);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite A IgM(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1080);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite A IgM(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Douteux' )  , now() , 1090);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite A IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite A IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite A IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Douteux' )  , now() , 1120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite B Ag(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite B Ag(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite B Ag(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1150);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite B Ag(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1160);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite C IgM(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Non Reactif' )  , now() , 1170);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite C IgM(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif' )  , now() , 1180);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite C IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Non Reactif' )  , now() , 1190);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite C IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif' )  , now() , 1200);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite E IgM(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1210);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite E IgM(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1220);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite E IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1230);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hepatite E IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1240);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Influenza A/Immunofluoresence(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1250);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Influenza A/Immunofluoresence(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1260);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Influenza A/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1270);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Influenza A/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1280);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Influenza A/Immunofluoresence(Aspiration Naso-Pharyngee)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1290);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Influenza A/Immunofluoresence(Aspiration Naso-Pharyngee)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1300);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Influenza B/Immunofluoresence(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1310);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Influenza B/Immunofluoresence(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1320);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Influenza B/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1330);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Influenza B/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1340);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Influenza B/Immunofluoresence(Aspiration Naso-Pharyngee)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1350);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Influenza B/Immunofluoresence(Aspiration Naso-Pharyngee)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1360);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 1/Immunofluoresence(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1370);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 1/Immunofluoresence(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1380);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 1/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1390);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 1/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1400);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 1/Immunofluoresence(Aspiration Naso-Pharyngee)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1410);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 1/Immunofluoresence(Aspiration Naso-Pharyngee)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1420);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 2/Immunofluoresence(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1430);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 2/Immunofluoresence(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1440);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 2/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1450);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 2/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1460);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 2/Immunofluoresence(Aspiration Naso-Pharyngee)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1470);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 2/Immunofluoresence(Aspiration Naso-Pharyngee)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1480);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 3/Immunofluoresence(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1490);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 3/Immunofluoresence(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1500);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 3/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1510);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 3/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1520);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 3/Immunofluoresence(Aspiration Naso-Pharyngee)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1530);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Para Influenza 3/Immunofluoresence(Aspiration Naso-Pharyngee)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1540);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VR/ Immunofluoresence(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1550);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VR/ Immunofluoresence(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1560);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VR/ Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1570);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VR/ Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1580);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VR/ Immunofluoresence(Aspiration Naso-Pharyngee)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1590);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VR/ Immunofluoresence(Aspiration Naso-Pharyngee)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1600);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Adenovirus/Immunofluoresence(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1610);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Adenovirus/Immunofluoresence(Ecouvillon Nasal)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1620);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Adenovirus/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1630);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Adenovirus/Immunofluoresence(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1640);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Adenovirus/Immunofluoresence(Aspiration Naso-Pharyngee)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1650);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Adenovirus/Immunofluoresence(Aspiration Naso-Pharyngee)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1660);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Polio(Selles 1)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1670);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Polio(Selles 1)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1680);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Polio(Selles 2)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1690);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Polio(Selles 2)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1700);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rotavirus(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1710);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Rotavirus(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1720);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Non-Reactif' )  , now() , 1730);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif' )  , now() , 1740);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2u' )  , now() , 1750);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='4u' )  , now() , 1760);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='16u' )  , now() , 1770);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='32u' )  , now() , 1780);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='64u' )  , now() , 1790);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='128u' )  , now() , 1800);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='256u' )  , now() , 1810);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis TPHA(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1820);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis TPHA(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1830);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR Qualitative(DBS)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='.ADN VIH-1  Non-Détecté' )  , now() , 1840);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR Qualitative(DBS)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='.ADN VIH-1 Detecte' )  , now() , 1850);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR Qualitative(DBS)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='PCR1' )  , now() , 1860);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR Qualitative(DBS)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='PCR2' )  , now() , 1870);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR Qualitative(DBS)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='PCR3' )  , now() , 1880);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR Qualitative(Sang Total)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='.ADN VIH-1  Non-Détecté' )  , now() , 1890);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR Qualitative(Sang Total)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='.ADN VIH-1 Detecte' )  , now() , 1900);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR Qualitative(Sang Total)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='PCR1' )  , now() , 1910);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR Qualitative(Sang Total)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='PCR2' )  , now() , 1920);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 PCR Qualitative(Sang Total)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='PCR3' )  , now() , 1930);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH-1 Charge Virale(Plasma)' ) , 'N' , null , now() , 1940);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Virus Respiratoire(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Influenza A H1N1v' )  , now() , 1950);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Virus Respiratoire(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Influenza A H1N1s' )  , now() , 1960);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Virus Respiratoire(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Influenza A H3N2s' )  , now() , 1970);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Virus Respiratoire(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Influenza A H5N1' )  , now() , 1980);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Virus Respiratoire(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Influenza A Negatif' )  , now() , 1990);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'CD4  Compte Abs(Sang Total)' ) , 'N' , null , now() , 2000);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'CD4 Compte en %(Sang Total)' ) , 'N' , null , now() , 2010);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coloration de Gramm(LCR)' ) , 'R' , null , now() , 2020);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coloration de Gramm(Selles)' ) , 'R' , null , now() , 2030);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coloration de Gramm(Ecouvillon Pharynge)' ) , 'R' , null , now() , 2040);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coloration de Gramm(Ecouvillon Naso-Pharynge)' ) , 'R' , null , now() , 2050);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coloration de Gramm(Sang)' ) , 'R' , null , now() , 2060);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leptospirose(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2070);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leptospirose(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2080);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leptospirose(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2090);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leptospirose(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leptospirose(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leptospirose(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leptospirose(Sang capillaire)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leptospirose(Sang capillaire)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hemoculture(Sang)' ) , 'R' , null , now() , 2150);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Serologie Salmonelle(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2160);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Serologie Salmonelle(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2170);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Serologie Salmonelle(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Border Line' )  , now() , 2180);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Serologie Salmonelle(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2190);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Serologie Salmonelle(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2200);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Serologie Salmonelle(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Border Line' )  , now() , 2210);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Meningite Culture(LCR)' ) , 'R' , null , now() , 2220);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Meningite Test Rapide(LCR)' ) , 'R' , null , now() , 2230);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Salmonelle(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2240);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Salmonelle(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2250);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Shigelle(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2260);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Shigelle(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2270);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de V.cholerea(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2280);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de V.cholerea(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2290);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de C. diphteriae(Ecouvillon Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2300);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de C. diphteriae(Ecouvillon Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2310);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de B. pertussis(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2320);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de B. pertussis(Ecouvillon Naso-Pharynge)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2330);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = $$Sensibilite a l'Acide Nalixidique (NA 30)(Selles)$$ ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 2340);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = $$Sensibilite a l'Acide Nalixidique (NA 30)(Selles)$$ ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Inteermediaire' )  , now() , 2350);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = $$Sensibilite a l'Acide Nalixidique (NA 30)(Selles)$$ ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 2360);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = $$Sensibilite a l'Ampiciline (AM10)(Selles)$$ ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 2370);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = $$Sensibilite a l'Ampiciline (AM10)(Selles)$$ ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Inteermediaire' )  , now() , 2380);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = $$Sensibilite a l'Ampiciline (AM10)(Selles)$$ ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 2390);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite a Amoxyline/Acide Clavulanique (NAC 30)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 2400);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite a Amoxyline/Acide Clavulanique (NAC 30)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 2410);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite a Amoxyline/Acide Clavulanique (NAC 30)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 2420);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite Azithromycine (AZM15)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 2430);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite Azithromycine (AZM15)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 2440);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite Azithromycine (AZM15)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 2450);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite a la Sulfaméthoxazole/triméthoprime (SXT25)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 2460);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite a la Sulfaméthoxazole/triméthoprime (SXT25)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 2470);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite a la Sulfaméthoxazole/triméthoprime (SXT25)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 2480);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite a la Tetracycline (TE 30)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 2490);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite a la Tetracycline (TE 30)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 2500);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite a la Tetracycline (TE 30)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 2510);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite a la Ciprofloxacine (CIP5)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 2520);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite a la Ciprofloxacine (CIP5)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 2530);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite a la Ciprofloxacine (CIP5)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 2540);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite Amoxyline/Acide Clavulanique (AMC 30)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 2550);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite Amoxyline/Acide Clavulanique (AMC 30)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 2560);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite Amoxyline/Acide Clavulanique (AMC 30)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 2570);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite a la Ceftriaxone (CRO30)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 2580);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite a la Ceftriaxone (CRO30)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 2590);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite a la Ceftriaxone (CRO30)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 2600);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite Gentamycine (CN10)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 2610);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite Gentamycine (CN10)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 2620);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite Gentamycine (CN10)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 2630);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite Chloramphénicol (C30)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 2640);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite Chloramphénicol (C30)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 2650);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite Chloramphénicol (C30)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 2660);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite Céfalotine (CF30)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 2670);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite Céfalotine (CF30)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 2680);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite Céfalotine (CF30)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 2690);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite a la Ceftazidime (CAZ30)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sensible' )  , now() , 2700);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite a la Ceftazidime (CAZ30)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Intermediaire' )  , now() , 2710);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sensibilite a la Ceftazidime (CAZ30)(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Resistant' )  , now() , 2720);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Noiratre' )  , now() , 2730);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sanguinolant' )  , now() , 2740);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Jaunatre' )  , now() , 2750);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Marron' )  , now() , 2760);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Verdatre' )  , now() , 2770);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Grisatre' )  , now() , 2780);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Couleur(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry =' Autre' )  , now() , 2790);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aspect(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Liquide' )  , now() , 2800);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aspect(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Solide' )  , now() , 2810);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aspect(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='semi liquide' )  , now() , 2820);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aspect(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='pateuse' )  , now() , 2830);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aspect(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='molle' )  , now() , 2840);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Aspect(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Glaireuse' )  , now() , 2850);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sang Occulte(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2860);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sang Occulte(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2870);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2880);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 2890);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 2900);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 2910);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures simples(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2920);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures simples(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 2930);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures simples(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 2940);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures simples(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 2950);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures bourgeonantes(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2960);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures bourgeonantes(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 2970);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures bourgeonantes(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 2980);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures bourgeonantes(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 2990);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Protozoaires(Selles)' ) , 'R' , null , now() , 3000);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'recherche de Metazoaires(Selles)' ) , 'R' , null , now() , 3010);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Scotch Tape(Selles)' ) , 'R' , null , now() , 3020);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Methode Kato(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 3030);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Methode Kato(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 3040);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Methode Kato(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Ascaris feconde' )  , now() , 3050);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Methode Kato(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Ascaris non feconde' )  , now() , 3060);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Methode Kato(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Trichuris trichiura' )  , now() , 3070);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Methode Kato(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Ankylostoma doudenale' )  , now() , 3080);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Methode Willis(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 3090);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Methode Willis(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 3100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Methode Willis(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Ascaris feconde' )  , now() , 3110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Methode Willis(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Ascaris non feconde' )  , now() , 3120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Methode Willis(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Trichuris trichiura' )  , now() , 3130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Methode Willis(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Ankylostoma doudenale' )  , now() , 3140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Methode Willis(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry =$$Oeuf d'oxyure$$ )  , now() , 3150);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Methode Willis(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Hymenolepis nana' )  , now() , 3160);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Methode Baermann(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 3170);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Methode Baermann(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Postif' )  , now() , 3180);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Methode Baermann(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry =$$Larve d'Anguilllule$$ )  , now() , 3190);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Methode Baermann(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry =$$Larve d'Ankylostome$$ )  , now() , 3200);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ritchie(Selles)' ) , 'R' , null , now() , 3210);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ziehl Neelsen modifiee(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 3220);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ziehl Neelsen modifiee(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 3230);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ziehl Neelsen modifiee(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Cryptosporidium' )  , now() , 3240);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ziehl Neelsen modifiee(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Cyclospora' )  , now() , 3250);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ziehl Neelsen modifiee(Selles)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='isospora belli' )  , now() , 3260);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Plasmodium- Especes(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 3270);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Plasmodium- Especes(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P. Falciparum' )  , now() , 3280);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Plasmodium- Especes(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P. Vivax' )  , now() , 3290);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Plasmodium- Especes(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P. Malariae' )  , now() , 3300);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Plasmodium- Especes(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P.Ovale' )  , now() , 3310);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Plasmodium- Trophozoite(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 3320);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Plasmodium- Trophozoite(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 3330);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Plasmodium- Trophozoite(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 3340);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Plasmodium- Trophozoite(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 3350);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Plasmodium- Trophozoite(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 4+' )  , now() , 3360);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Plasmodium- Gametocyte(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 3370);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Plasmodium- Gametocyte(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 3380);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Plasmodium- Gametocyte(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 3390);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Plasmodium- Gametocyte(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 3400);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Plasmodium- Gametocyte(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 4+' )  , now() , 3410);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Plasmodium- Schizonte(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 3420);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Plasmodium- Schizonte(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 3430);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Plasmodium- Schizonte(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 3440);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Plasmodium- Schizonte(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 3450);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Plasmodium- Schizonte(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 4+' )  , now() , 3460);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria Test Rapide(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 3470);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria Test Rapide(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 3480);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Microfilaires(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 3490);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Microfilaires(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 3500);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Microfilaires(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Wuchereria bancrofti' )  , now() , 3510);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de Microfilaires(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Mansonella spp' )  , now() , 3520);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 1(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 3530);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 1(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='1 BAAR/ch' )  , now() , 3540);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 1(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2 BAAR/ch' )  , now() , 3550);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 1(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='3 BAAR/ch' )  , now() , 3560);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 1(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='4 BAAR/ch' )  , now() , 3570);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 1(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='5 BAAR/ch' )  , now() , 3580);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 1(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='6 BAAR/ch' )  , now() , 3590);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 1(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='7 BAAR/ch' )  , now() , 3600);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 1(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='8 BAAR/ch' )  , now() , 3610);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 1(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='9 BAAR/ch' )  , now() , 3620);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 1(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='1+' )  , now() , 3630);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 1(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2+' )  , now() , 3640);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 1(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='3+' )  , now() , 3650);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 2(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 3660);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 2(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='1 BAAR/ch' )  , now() , 3670);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 2(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2 BAAR/ch' )  , now() , 3680);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 2(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='3 BAAR/ch' )  , now() , 3690);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 2(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='4 BAAR/ch' )  , now() , 3700);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 2(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='5 BAAR/ch' )  , now() , 3710);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 2(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='6 BAAR/ch' )  , now() , 3720);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 2(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='7 BAAR/ch' )  , now() , 3730);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 2(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='8 BAAR/ch' )  , now() , 3740);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 2(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='9 BAAR/ch' )  , now() , 3750);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 2(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='1+' )  , now() , 3760);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 2(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2+' )  , now() , 3770);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 2(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='3+' )  , now() , 3780);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 3(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 3790);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 3(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='1 BAAR/ch' )  , now() , 3800);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 3(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2 BAAR/ch' )  , now() , 3810);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 3(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='3 BAAR/ch' )  , now() , 3820);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 3(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='4 BAAR/ch' )  , now() , 3830);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 3(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='5 BAAR/ch' )  , now() , 3840);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 3(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='6 BAAR/ch' )  , now() , 3850);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 3(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='7 BAAR/ch' )  , now() , 3860);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 3(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='8 BAAR/ch' )  , now() , 3870);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 3(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='9 BAAR/ch' )  , now() , 3880);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 3(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='1+' )  , now() , 3890);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 3(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2+' )  , now() , 3900);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Zeihl Neelsen Specimen 3(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='3+' )  , now() , 3910);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 1(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 3920);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 1(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='1 BAAR/ch' )  , now() , 3930);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 1(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2 BAAR/ch' )  , now() , 3940);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 1(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='3 BAAR/ch' )  , now() , 3950);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 1(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='4 BAAR/ch' )  , now() , 3960);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 1(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='5 BAAR/ch' )  , now() , 3970);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 1(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='6 BAAR/ch' )  , now() , 3980);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 1(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='7 BAAR/ch' )  , now() , 3990);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 1(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='8 BAAR/ch' )  , now() , 4000);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 1(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='9 BAAR/ch' )  , now() , 4010);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 1(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='1+' )  , now() , 4020);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 1(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2+' )  , now() , 4030);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 1(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='3+' )  , now() , 4040);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 2(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 4050);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 2(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='1 BAAR/ch' )  , now() , 4060);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 2(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2 BAAR/ch' )  , now() , 4070);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 2(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='3 BAAR/ch' )  , now() , 4080);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 2(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='4 BAAR/ch' )  , now() , 4090);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 2(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='5 BAAR/ch' )  , now() , 4100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 2(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='6 BAAR/ch' )  , now() , 4110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 2(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='7 BAAR/ch' )  , now() , 4120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 2(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='8 BAAR/ch' )  , now() , 4130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 2(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='9 BAAR/ch' )  , now() , 4140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 2(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='1+' )  , now() , 4150);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 2(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2+' )  , now() , 4160);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 2(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='3+' )  , now() , 4170);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 3(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 4180);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 3(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='1 BAAR/ch' )  , now() , 4190);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 3(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2 BAAR/ch' )  , now() , 4200);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 3(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='3 BAAR/ch' )  , now() , 4210);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 3(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='4 BAAR/ch' )  , now() , 4220);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 3(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='5 BAAR/ch' )  , now() , 4230);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 3(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='6 BAAR/ch' )  , now() , 4240);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 3(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='7 BAAR/ch' )  , now() , 4250);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 3(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='8 BAAR/ch' )  , now() , 4260);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 3(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='9 BAAR/ch' )  , now() , 4270);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 3(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='1+' )  , now() , 4280);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 3(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2+' )  , now() , 4290);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BAAR par Fluorochrome Specimen 3(Expectoration)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='3+' )  , now() , 4300);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture LJ/Ogawa(Expectoration)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Purulent' )  , now() , 4310);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture LJ/Ogawa(Expectoration)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sanguinolent' )  , now() , 4320);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture LJ/Ogawa(Expectoration)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='salivaire' )  , now() , 4330);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture LJ/Ogawa(Expectoration)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='negatif' )  , now() , 4340);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture LJ/Ogawa(Expectoration)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='contamine' )  , now() , 4350);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture LJ/Ogawa(Expectoration)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='1-9 colonies' )  , now() , 4360);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture LJ/Ogawa(Expectoration)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 4370);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture LJ/Ogawa(Expectoration)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 4380);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture LJ/Ogawa(Expectoration)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 4390);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture en milieu liquide(Expectoration)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Purulent' )  , now() , 4400);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture en milieu liquide(Expectoration)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Sanguinolent' )  , now() , 4410);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture en milieu liquide(Expectoration)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='salivaire' )  , now() , 4420);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture en milieu liquide(Expectoration)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='negatif' )  , now() , 4430);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture en milieu liquide(Expectoration)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='contamine' )  , now() , 4440);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture en milieu liquide(Expectoration)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='1-9 colonies' )  , now() , 4450);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture en milieu liquide(Expectoration)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 4460);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture en milieu liquide(Expectoration)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 4470);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture en milieu liquide(Expectoration)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 4480);
