--Sickling Test(Sang)
UPDATE test_result
   SET tst_rslt_type='D', value=(select max(id) from dictionary where dict_entry='Negatif' ) , lastupdated=now(), sort_order=200
   WHERE test_id = (select id from test where description = 'Sickling Test(Sang)');
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sickling Test(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 205);

--Coombs Test Direct(Sang)	 
UPDATE test_result
   SET value=(select max(id) from dictionary where dict_entry='Positif' ) , lastupdated=now()
   WHERE test_id = (select id from test where description = 'Coombs Test Direct(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='Compatible' ) as varchar);
UPDATE test_result
   SET value=(select max(id) from dictionary where dict_entry='Negatif' ) , lastupdated=now()
   WHERE test_id = (select id from test where description = 'Coombs Test Direct(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='Incompatible' ) as varchar);
   
--Coombs Test Indirect(Serum)
UPDATE test_result
   SET value=(select max(id) from dictionary where dict_entry='Positif' ) , lastupdated=now()
   WHERE test_id = (select id from test where description = 'Coombs Test Indirect(Serum)') AND value = cast((select max(id) from dictionary where dict_entry='Compatible' ) as varchar);
UPDATE test_result
   SET value=(select max(id) from dictionary where dict_entry='Negatif' ) , lastupdated=now()
   WHERE test_id = (select id from test where description = 'Coombs Test Indirect(Serum)') AND value = cast((select max(id) from dictionary where dict_entry='Incompatible' ) as varchar);
   
--Trichomonas hominis(Secretion Urethrale)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='>100/ch' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Trichomonas hominis(Secretion Urethrale)') AND value = cast((select max(id) from dictionary where dict_entry='Superieur a 100/ch' ) as varchar);

--Globules Blancs(Secretion Urethrale)	
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='>100/ch' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Globules Blancs(Secretion Urethrale)') AND value = cast((select max(id) from dictionary where dict_entry='Superieur a 100/ch' ) as varchar);
   
--Globules Rouges(Secretion Urethrale)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='>100/ch' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Globules Rouges(Secretion Urethrale)') AND value = cast((select max(id) from dictionary where dict_entry='Superieur a 100/ch' ) as varchar);

--Leucocytes(Urines)
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leucocytes(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 205);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leucocytes(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='trace' )  , now() , 205);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leucocytes(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 205);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leucocytes(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 205);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Leucocytes(Urines)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 205);

--Sang Occulte(Selles)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Positif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Sang Occulte(Selles)') AND value = cast((select max(id) from dictionary where dict_entry='POSITIF' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Negatif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Sang Occulte(Selles)') AND value = cast((select max(id) from dictionary where dict_entry='NEGATIF' ) as varchar);
	
--Dengue(Sang)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Positif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Dengue(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='POSITIF' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Negatif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Dengue(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='NEGATIF' ) as varchar);

--PPD Qualitaitif(In Vivo)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Non-Reactif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'PPD Qualitaitif(In Vivo)') AND value = cast((select max(id) from dictionary where dict_entry='Non Reactif' ) as varchar);
	
--LCR ZIELH NIELSEN(LCR/CSF)
UPDATE test_result
   SET tst_rslt_type='D', value=(select max(id) from dictionary where dict_entry='Negatif' ) , lastupdated=now(), sort_order=3340
   WHERE test_id = (select id from test where description = 'LCR ZIELH NIELSEN(LCR/CSF)');
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'LCR ZIELH NIELSEN(LCR/CSF)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 3342);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'LCR ZIELH NIELSEN(LCR/CSF)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 3344);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'LCR ZIELH NIELSEN(LCR/CSF)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 3346);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'LCR ZIELH NIELSEN(LCR/CSF)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Rare (1-9 BAAR/100ch)' )  , now() , 3348);

--Malaria Test Rapide(Serum)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Positif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Malaria Test Rapide(Serum)') AND value = cast((select max(id) from dictionary where dict_entry='POS' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Negatif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Malaria Test Rapide(Serum)') AND value = cast((select max(id) from dictionary where dict_entry='NEG' ) as varchar);

--Malaria Test Rapide(Plasma)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Positif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Malaria Test Rapide(Plasma)') AND value = cast((select max(id) from dictionary where dict_entry='POS' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Negatif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Malaria Test Rapide(Plasma)') AND value = cast((select max(id) from dictionary where dict_entry='NEG' ) as varchar);

--Malaria Test Rapide(Sang)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Positif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Malaria Test Rapide(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='POS' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Negatif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Malaria Test Rapide(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='NEG' ) as varchar);

--Syphilis Test Rapide(Serum)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Positif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Syphilis Test Rapide(Serum)') AND value = cast((select max(id) from dictionary where dict_entry='POS' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Negatif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Syphilis Test Rapide(Serum)') AND value = cast((select max(id) from dictionary where dict_entry='NEG' ) as varchar);

--Syphilis Test Rapide(Plasma)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Positif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Syphilis Test Rapide(Plasma)') AND value = cast((select max(id) from dictionary where dict_entry='POS' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Negatif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Syphilis Test Rapide(Plasma)') AND value = cast((select max(id) from dictionary where dict_entry='NEG' ) as varchar);
	
--Syphilis Test Rapide(Sang)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Positif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Syphilis Test Rapide(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='POS' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Negatif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Syphilis Test Rapide(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='NEG' ) as varchar);

--HTLV I et II(Serum)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Positif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'HTLV I et II(Serum)') AND value = cast((select max(id) from dictionary where dict_entry='POS' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Negatif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'HTLV I et II(Serum)') AND value = cast((select max(id) from dictionary where dict_entry='NEG' ) as varchar);

--HTLV I et II(Plasma)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Positif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'HTLV I et II(Plasma)') AND value = cast((select max(id) from dictionary where dict_entry='POS' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Negatif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'HTLV I et II(Plasma)') AND value = cast((select max(id) from dictionary where dict_entry='NEG' ) as varchar);
	
--HTLV I et II(Sang)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Positif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'HTLV I et II(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='POS' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Negatif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'HTLV I et II(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='NEG' ) as varchar);

--Syphilis RPR(Serum)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Non-Reactif' ) , lastupdated=now(), sort_order=3450
	WHERE test_id = (select id from test where description = 'Syphilis RPR(Serum)') AND value = cast((select max(id) from dictionary where dict_entry='Non Reactif' ) as varchar);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif' )  , now() , 3451);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='2u' ) , lastupdated=now(), sort_order=3452
	WHERE test_id = (select id from test where description = 'Syphilis RPR(Serum)') AND value = cast((select max(id) from dictionary where dict_entry='Reactif 1/2' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='4u' ) , lastupdated=now(), sort_order=3453
	WHERE test_id = (select id from test where description = 'Syphilis RPR(Serum)') AND value = cast((select max(id) from dictionary where dict_entry='Reactif 1/4' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='8u' ) , lastupdated=now(), sort_order=3454
	WHERE test_id = (select id from test where description = 'Syphilis RPR(Serum)') AND value = cast((select max(id) from dictionary where dict_entry='Reactif 1/8' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='16u' ) , lastupdated=now(), sort_order=3455
	WHERE test_id = (select id from test where description = 'Syphilis RPR(Serum)') AND value = cast((select max(id) from dictionary where dict_entry='Reactif 1/16' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='32u' ) , lastupdated=now(), sort_order=3456
	WHERE test_id = (select id from test where description = 'Syphilis RPR(Serum)') AND value = cast((select max(id) from dictionary where dict_entry='Reactif 1/32' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='64u' ) , lastupdated=now(), sort_order=3457
	WHERE test_id = (select id from test where description = 'Syphilis RPR(Serum)') AND value = cast((select max(id) from dictionary where dict_entry='Reactif 1/64' ) as varchar);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='128u' )  , now() , 3458);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='256u' )  , now() , 3459);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='> 572' )  , now() , 3460);

--Syphilis RPR(Plasma)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Non-Reactif' ) , lastupdated=now(), sort_order=3460
	WHERE test_id = (select id from test where description = 'Syphilis RPR(Plasma)') AND value = cast((select max(id) from dictionary where dict_entry='Non Reactif' ) as varchar);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif' )  , now() , 3461);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='2u' ) , lastupdated=now(), sort_order=3462
	WHERE test_id = (select id from test where description = 'Syphilis RPR(Plasma)') AND value = cast((select max(id) from dictionary where dict_entry='Reactif 1/2' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='4u' ) , lastupdated=now(), sort_order=3463
	WHERE test_id = (select id from test where description = 'Syphilis RPR(Plasma)') AND value = cast((select max(id) from dictionary where dict_entry='Reactif 1/4' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='8u' ) , lastupdated=now(), sort_order=3464
	WHERE test_id = (select id from test where description = 'Syphilis RPR(Plasma)') AND value = cast((select max(id) from dictionary where dict_entry='Reactif 1/8' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='16u' ) , lastupdated=now(), sort_order=3465
	WHERE test_id = (select id from test where description = 'Syphilis RPR(Plasma)') AND value = cast((select max(id) from dictionary where dict_entry='Reactif 1/16' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='32u' ) , lastupdated=now(), sort_order=3466
	WHERE test_id = (select id from test where description = 'Syphilis RPR(Plasma)') AND value = cast((select max(id) from dictionary where dict_entry='Reactif 1/32' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='64u' ) , lastupdated=now(), sort_order=3467
	WHERE test_id = (select id from test where description = 'Syphilis RPR(Plasma)') AND value = cast((select max(id) from dictionary where dict_entry='Reactif 1/64' ) as varchar);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='128u' )  , now() , 3468);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='256u' )  , now() , 3469);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='> 572' )  , now() , 3470);

--Syphilis RPR(Sang)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Non-Reactif' ) , lastupdated=now(), sort_order=3470
	WHERE test_id = (select id from test where description = 'Syphilis RPR(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='Non Reactif' ) as varchar);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif' )  , now() , 3471);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='2u' ) , lastupdated=now(), sort_order=3472
	WHERE test_id = (select id from test where description = 'Syphilis RPR(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='Reactif 1/2' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='4u' ) , lastupdated=now(), sort_order=3473
	WHERE test_id = (select id from test where description = 'Syphilis RPR(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='Reactif 1/4' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='8u' ) , lastupdated=now(), sort_order=3474
	WHERE test_id = (select id from test where description = 'Syphilis RPR(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='Reactif 1/8' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='16u' ) , lastupdated=now(), sort_order=3475
	WHERE test_id = (select id from test where description = 'Syphilis RPR(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='Reactif 1/16' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='32u' ) , lastupdated=now(), sort_order=3476
	WHERE test_id = (select id from test where description = 'Syphilis RPR(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='Reactif 1/32' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='64u' ) , lastupdated=now(), sort_order=3477
	WHERE test_id = (select id from test where description = 'Syphilis RPR(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='Reactif 1/64' ) as varchar);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='128u' )  , now() , 3478);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='256u' )  , now() , 3479);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Syphilis RPR(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='> 572' )  , now() , 3480);

--Helicobacter Pilori(Serum)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Positif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Helicobacter Pilori(Serum)') AND value = cast((select max(id) from dictionary where dict_entry='POS' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Negatif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Helicobacter Pilori(Serum)') AND value = cast((select max(id) from dictionary where dict_entry='NEG' ) as varchar);

--Helicobacter Pilori(Plasma)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Positif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Helicobacter Pilori(Plasma)') AND value = cast((select max(id) from dictionary where dict_entry='POS' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Negatif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Helicobacter Pilori(Plasma)') AND value = cast((select max(id) from dictionary where dict_entry='NEG' ) as varchar);
	 
--Helicobacter Pilori(Sang)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Positif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Helicobacter Pilori(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='POS' ) as varchar);
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Negatif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Helicobacter Pilori(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='NEG' ) as varchar);

--CRP(Serum)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Positif > 200 unités/ml' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'CRP(Serum)') AND value = cast((select max(id) from dictionary where dict_entry='POS  > 200 unites/ml' ) as varchar);	
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Negatif  < 200 unites/ml' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'CRP(Serum)') AND value = cast((select max(id) from dictionary where dict_entry='NEG  < 200 unites/ml' ) as varchar);

--CRP(Plasma)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Positif > 200 unités/ml' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'CRP(Plasma)') AND value = cast((select max(id) from dictionary where dict_entry='POS  > 200 unites/ml' ) as varchar);	
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Negatif  < 200 unites/ml' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'CRP(Plasma)') AND value = cast((select max(id) from dictionary where dict_entry='NEG  < 200 unites/ml' ) as varchar);
	
--CRP(Sang)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Positif > 200 unités/ml' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'CRP(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='POS  > 200 unites/ml' ) as varchar);	
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Negatif  < 200 unites/ml' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'CRP(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='NEG  < 200 unites/ml' ) as varchar);

--ASO(Serum)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Positif  > 200 U ASLO/ml' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'ASO(Serum)') AND value = cast((select max(id) from dictionary where dict_entry='POS  > 200 U ASLO/ml' ) as varchar);	
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Negatif  < 200 U ASLO/ml' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'ASO(Serum)') AND value = cast((select max(id) from dictionary where dict_entry='NEG  > 200 U ASLO/ml' ) as varchar);	
	
--ASO(Plasma)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Positif  > 200 U ASLO/ml' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'ASO(Plasma)') AND value = cast((select max(id) from dictionary where dict_entry='POS  > 200 U ASLO/ml' ) as varchar);	
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Negatif  < 200 U ASLO/ml' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'ASO(Plasma)') AND value = cast((select max(id) from dictionary where dict_entry='NEG  > 200 U ASLO/ml' ) as varchar);	
	
--ASO(Serum)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Positif  > 200 U ASLO/ml' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'ASO(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='POS  > 200 U ASLO/ml' ) as varchar);	
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Negatif  < 200 U ASLO/ml' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'ASO(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='NEG  > 200 U ASLO/ml' ) as varchar);	
	
--Syphilis Bioline(Serum)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Positif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Syphilis Bioline(Serum)') AND value = cast((select max(id) from dictionary where dict_entry='POSITIF' ) as varchar);	
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Negatif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Syphilis Bioline(Serum)') AND value = cast((select max(id) from dictionary where dict_entry='NEGATIF' ) as varchar);	
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Indetermine' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Syphilis Bioline(Serum)') AND value = cast((select max(id) from dictionary where dict_entry='INDETERMINE' ) as varchar);	

--Syphilis Bioline(Plasma)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Positif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Syphilis Bioline(Plasma)') AND value = cast((select max(id) from dictionary where dict_entry='POSITIF' ) as varchar);	
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Negatif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Syphilis Bioline(Plasma)') AND value = cast((select max(id) from dictionary where dict_entry='NEGATIF' ) as varchar);	
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Indetermine' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Syphilis Bioline(Plasma)') AND value = cast((select max(id) from dictionary where dict_entry='INDETERMINE' ) as varchar);	

--Syphilis Bioline(Sang)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Positif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Syphilis Bioline(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='POSITIF' ) as varchar);	
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Negatif' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Syphilis Bioline(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='NEGATIF' ) as varchar);	
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Indetermine' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Syphilis Bioline(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='INDETERMINE' ) as varchar);	

--Test de comptabilite(Sang)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Compatible' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Test de comptabilite(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='Positif' ) as varchar);	
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Incompatible' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Test de comptabilite(Sang)') AND value = cast((select max(id) from dictionary where dict_entry='Negatif' ) as varchar);	

--Test de comptabilite(Serum)
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Compatible' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Test de comptabilite(Serum)') AND value = cast((select max(id) from dictionary where dict_entry='Positif' ) as varchar);	
UPDATE test_result
	SET value=(select max(id) from dictionary where dict_entry='Incompatible' ) , lastupdated=now()
	WHERE test_id = (select id from test where description = 'Test de comptabilite(Serum)') AND value = cast((select max(id) from dictionary where dict_entry='Negatif' ) as varchar);	
	
--CMV Ig G(Serum)
UPDATE test_result
   SET tst_rslt_type='D', value=(select max(id) from dictionary where dict_entry='Negatif' ) , lastupdated=now(), sort_order=3960
   WHERE test_id = (select id from test where description = 'CMV Ig G(Serum)');
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'CMV Ig G(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 3965);
