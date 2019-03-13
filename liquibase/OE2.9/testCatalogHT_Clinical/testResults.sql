UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=10
	WHERE test_id= ( select id from clinlims.test where description = 'Compte des Globules Blancs(Sang)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=20
	WHERE test_id= ( select id from clinlims.test where description = 'Compte des Globules Rouges(Sang)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=30
	WHERE test_id= ( select id from clinlims.test where description = 'Hemoglobine(Sang)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=40
	WHERE test_id= ( select id from clinlims.test where description = 'Hematocrite(Sang)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=50
	WHERE test_id= ( select id from clinlims.test where description = 'VGM(Sang)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=60
	WHERE test_id= ( select id from clinlims.test where description = 'TCMH(Sang)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=70
	WHERE test_id= ( select id from clinlims.test where description = 'CCMH(Sang)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=80
	WHERE test_id= ( select id from clinlims.test where description = 'Plaquettes(Sang)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=90
	WHERE test_id= ( select id from clinlims.test where description = 'Neutrophiles(Sang)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=100
	WHERE test_id= ( select id from clinlims.test where description = 'Lymphocytes(Sang)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=110
	WHERE test_id= ( select id from clinlims.test where description = 'Mixtes(Sang)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=120
	WHERE test_id= ( select id from clinlims.test where description = 'Monocytes(Sang)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=130
	WHERE test_id= ( select id from clinlims.test where description = 'Eosinophiles(Sang)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=140
	WHERE test_id= ( select id from clinlims.test where description = 'Basophiles(Sang)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=150
	WHERE test_id= ( select id from clinlims.test where description = 'Vitesse de Sedimentation(Sang)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=160
	WHERE test_id= ( select id from clinlims.test where description = 'Temps de Coagulation en tube(Sang)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=170
	WHERE test_id= ( select id from clinlims.test where description = 'Temps de Coagulation(Sang)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=180
	WHERE test_id= ( select id from clinlims.test where description = 'Temps de saignement(Sang)' );
UPDATE clinlims.test_result SET tst_rslt_type='R', sort_order=190
	WHERE test_id= ( select id from clinlims.test where description = $$Electrophorese de l'hemoglobine(Sang)$$ );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=210
	WHERE test_id= ( select id from clinlims.test where description = 'Taux reticulocytes - Auto(Sang)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=220
	WHERE test_id= ( select id from clinlims.test where description = 'Taux reticulocytes - Manual(Sang)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=230
	WHERE test_id= ( select id from clinlims.test where description = 'Temps de cephaline Activé(TCA)(Plasma)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=240
	WHERE test_id= ( select id from clinlims.test where description = 'Temps de Prothrombine(Plasma)' );
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'INR(Plasma)' ) , 'N' , null , now() , 250);
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=260
	WHERE test_id= ( select id from clinlims.test where description = 'Facteur VIII(Plasma)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=270
	WHERE test_id= ( select id from clinlims.test where description = 'Facteur IX(Plasma)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=280
	WHERE test_id= ( select id from clinlims.test where description = 'Heparinemie(Plasma)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=290
	WHERE test_id= ( select id from clinlims.test where description = 'Anti-Thrombine III (Dosage)(Plasma)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=300
	WHERE test_id= ( select id from clinlims.test where description = 'Anti-Thrombine III (Activite)(Plasma)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=370
	WHERE test_id= ( select id from clinlims.test where description = $$Azote de l'Uree(Serum)$$ );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=380
	WHERE test_id= ( select id from clinlims.test where description = 'Uree(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=390
	WHERE test_id= ( select id from clinlims.test where description = 'creatinine(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=400
	WHERE test_id= ( select id from clinlims.test where description = 'Glycemie(Serum)' );
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glycemie(LCR/CSF)' ) , 'N' , null , now() , 410);
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=420
	WHERE test_id= ( select id from clinlims.test where description = 'Proteines(LCR/CSF)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=430
	WHERE test_id= ( select id from clinlims.test where description = 'Chlore(LCR/CSF)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=440
	WHERE test_id= ( select id from clinlims.test where description = 'glycemie(Plasma)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=450
	WHERE test_id= ( select id from clinlims.test where description = 'Albumine(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=460
	WHERE test_id= ( select id from clinlims.test where description = 'Î±1 globuline(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=470
	WHERE test_id= ( select id from clinlims.test where description = 'Î±2 globuline(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=480
	WHERE test_id= ( select id from clinlims.test where description = 'Î² globuline(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=490
	WHERE test_id= ( select id from clinlims.test where description = 'Ï’ globuline(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=500
	WHERE test_id= ( select id from clinlims.test where description = 'Phosphatase Acide(Serum)' );
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chlorures(Plasma hepariné)' ) , 'N' , null , now() , 510);
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=520
	WHERE test_id= ( select id from clinlims.test where description = 'Chlorures(Urines/24 heures)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=530
	WHERE test_id= ( select id from clinlims.test where description = 'CPK(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=540
	WHERE test_id= ( select id from clinlims.test where description = 'Amylase(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=550
	WHERE test_id= ( select id from clinlims.test where description = 'Lipase(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=560
	WHERE test_id= ( select id from clinlims.test where description = 'Chlorures(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=570
	WHERE test_id= ( select id from clinlims.test where description = 'Sodium(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=580
	WHERE test_id= ( select id from clinlims.test where description = 'Potassium(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=590
	WHERE test_id= ( select id from clinlims.test where description = 'Bicarbonates(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=600
	WHERE test_id= ( select id from clinlims.test where description = 'Calcium(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=610
	WHERE test_id= ( select id from clinlims.test where description = 'magnésium(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=620
	WHERE test_id= ( select id from clinlims.test where description = 'phosphore(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=630
	WHERE test_id= ( select id from clinlims.test where description = 'Lithium(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=640
	WHERE test_id= ( select id from clinlims.test where description = 'Fer Serique(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=650
	WHERE test_id= ( select id from clinlims.test where description = 'Bilirubine totale(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=660
	WHERE test_id= ( select id from clinlims.test where description = 'Bilirubine directe(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=670
	WHERE test_id= ( select id from clinlims.test where description = 'Bilirubine indirecte(Serum)' );
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'SGOT/ AST(Serum)' ) , 'N' , null , now() , 680);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'SGPT/ ALT(Serum)' ) , 'N' , null , now() , 690);
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=700
	WHERE test_id= ( select id from clinlims.test where description = 'Phosphatase Alcaline(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=710
	WHERE test_id= ( select id from clinlims.test where description = 'Cholesterol Total(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=720
	WHERE test_id= ( select id from clinlims.test where description = 'Triglyceride(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=730
	WHERE test_id= ( select id from clinlims.test where description = 'Lipide(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=740
	WHERE test_id= ( select id from clinlims.test where description = 'HDL(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=750
	WHERE test_id= ( select id from clinlims.test where description = 'LDL(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=760
	WHERE test_id= ( select id from clinlims.test where description = 'VLDL(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=770
	WHERE test_id= ( select id from clinlims.test where description = 'MBG(Serum)' );
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hémoglobine glycolisee(Serum)' ) , 'N' , null , now() , 780);
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=790
	WHERE test_id= ( select id from clinlims.test where description = 'LDH(Serum)' );
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Triponine I(Serum)' ) , 'N' , null , now() , 800);
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=810
	WHERE test_id= ( select id from clinlims.test where description = 'Ph(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=820
	WHERE test_id= ( select id from clinlims.test where description = 'PaCO2(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=830
	WHERE test_id= ( select id from clinlims.test where description = 'HCO3(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=840
	WHERE test_id= ( select id from clinlims.test where description = 'O2 Saturation(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=850
	WHERE test_id= ( select id from clinlims.test where description = 'PaO2(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=860
	WHERE test_id= ( select id from clinlims.test where description = 'BE(Serum)' );
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glycémie(Serum)' ) , 'N' , null , now() , 870);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glycemie Provoquee Fasting(Serum)' ) , '' , null , now() , 880);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glycémie provoquée 1/2 hre(Serum)' ) , 'N' , null , now() , 890);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glycémie provoquée 1hre(Serum)' ) , 'N' , null , now() , 900);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glycémie provoquée 2hres(Serum)' ) , 'N' , null , now() , 910);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glycémie provoquée 3hres(Serum)' ) , 'N' , null , now() , 920);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glycémie provoquée 4hres(Serum)' ) , 'N' , null , now() , 930);
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=940
	WHERE test_id= ( select id from clinlims.test where description = 'Glycémie provoquée(Serum)' );
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Glycemie Postprandiale(Serum)' ) , 'N' , null , now() , 950);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Azote Urée(Serum)' ) , 'N' , null , now() , 960);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Urée (calculée)(Serum)' ) , 'N' , null , now() , 970);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Créatinine(Serum)' ) , 'N' , null , now() , 980);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'SGPT (ALT)(Serum)' ) , 'N' , null , now() , 990);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'SGOT (AST)(Serum)' ) , 'N' , null , now() , 1000);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cholestérol total(Serum)' ) , 'N' , null , now() , 1010);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HDL-cholestérol(Serum)' ) , 'N' , null , now() , 1020);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'LDL-cholesterol (calculée)(Serum)' ) , 'N' , null , now() , 1030);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VLDL – cholesterol (calculée)(Serum)' ) , 'N' , null , now() , 1040);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Triglycéride(Serum)' ) , 'N' , null , now() , 1050);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide urique(Serum)' ) , 'N' , null , now() , 1060);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chlorure(Serum)' ) , 'N' , null , now() , 1070);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Calcium (Ca++)(Serum)' ) , 'N' , null , now() , 1080);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Protéines totales(Serum)' ) , 'N' , null , now() , 1090);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Creatinine(Serum)' ) , 'N' , null , now() , 1100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Creatinine(Sang)' ) , 'N' , null , now() , 1110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Creatinine(Plasma)' ) , 'N' , null , now() , 1120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'SGPT/ALT(Serum)' ) , 'N' , null , now() , 1130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'SGPT/ALT(Sang)' ) , 'N' , null , now() , 1140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'SGPT/ALT(Plasma)' ) , 'N' , null , now() , 1150);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'SGOT/AST(Serum)' ) , 'N' , null , now() , 1160);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'SGOT/AST(Sang)' ) , 'N' , null , now() , 1170);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'SGOT/AST(Plasma)' ) , 'N' , null , now() , 1180);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'CRP Quantitatif(Serum)' ) , 'N' , null , now() , 1190);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'CRP Quantitatif(Plasma)' ) , 'N' , null , now() , 1200);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'C3 du Complement(Sang)' ) , 'N' , null , now() , 1210);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'C4 du complement(Serum)' ) , 'N' , null , now() , 1220);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'C3 du Complement(Serum)' ) , 'N' , null , now() , 1230);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'C4 du complement(Sang)' ) , 'N' , null , now() , 1240);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Facteur Rhumatoide(Serum)' ) , 'N' , null , now() , 1250);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Catalase(Sang)' ) , 'R' , null , now() , 1440);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Oxydase(Sang)' ) , 'R' , null , now() , 1450);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coagulase libre(Sang)' ) , 'R' , null , now() , 1460);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'DNAse(Sang)' ) , 'R' , null , now() , 1470);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = $$Hydrolyse de l'esculine(Sang)$$ ) , 'R' , null , now() , 1480);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Urée-tryptophane(Sang)' ) , 'R' , null , now() , 1490);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mobilité(Sang)' ) , 'R' , null , now() , 1500);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test à la potasse(Sang)' ) , 'R' , null , now() , 1510);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test à la porphyrine(Sang)' ) , 'R' , null , now() , 1520);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'ONPG(Sang)' ) , 'R' , null , now() , 1530);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Réaction de Voges-Proskauer(Sang)' ) , 'R' , null , now() , 1540);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Camp-test(Sang)' ) , 'R' , null , now() , 1550);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = $$Techniques d'agglutination(Sang)$$ ) , 'R' , null , now() , 1560);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coloration de Gram(Sang)' ) , 'R' , null , now() , 1570);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coloration de Ziehl-Neelsen(Sang)' ) , 'R' , null , now() , 1580);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = $$Coloration à l'auramine(Sang)$$ ) , 'R' , null , now() , 1590);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = $$Coloration à l'acridine orange(Sang)$$ ) , 'R' , null , now() , 1600);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coloration de Kinyoun(Sang)' ) , 'R' , null , now() , 1610);
UPDATE clinlims.test_result SET tst_rslt_type='R', sort_order=1620
	WHERE test_id= ( select id from clinlims.test where description = 'Frottis Vaginal/Gram(Secretion vaginale)' );
UPDATE clinlims.test_result SET tst_rslt_type='R', sort_order=1630
	WHERE test_id= ( select id from clinlims.test where description = 'Frottis Uretral/Gram(Secretion Urethrale)' );
UPDATE clinlims.test_result SET tst_rslt_type='R', sort_order=1650
	WHERE test_id= ( select id from clinlims.test where description = 'Coproculture(Selles)' );
UPDATE clinlims.test_result SET tst_rslt_type='R', sort_order=1670
	WHERE test_id= ( select id from clinlims.test where description = 'Liquefaction(Liquide Spermatique)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=1680
	WHERE test_id= ( select id from clinlims.test where description = 'pH(Liquide Spermatique)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=1690
	WHERE test_id= ( select id from clinlims.test where description = 'Fructose(Liquide Spermatique)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=1700
	WHERE test_id= ( select id from clinlims.test where description = 'Volume(Liquide Spermatique)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=1710
	WHERE test_id= ( select id from clinlims.test where description = 'Compte de spermes(Liquide Spermatique)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=1720
	WHERE test_id= ( select id from clinlims.test where description = 'Formes normales(Liquide Spermatique)' );
UPDATE clinlims.test_result SET tst_rslt_type='R', sort_order=1730
	WHERE test_id= ( select id from clinlims.test where description = 'Formes anormales(Liquide Spermatique)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=1740
	WHERE test_id= ( select id from clinlims.test where description = 'Motilite STAT(Liquide Spermatique)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=1750
	WHERE test_id= ( select id from clinlims.test where description = 'Motilite 1 heure(Liquide Spermatique)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=1760
	WHERE test_id= ( select id from clinlims.test where description = 'Motilite 3 heures(Liquide Spermatique)' );
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coloration de Gram(Liquide Spermatique)' ) , 'R' , null , now() , 1770);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture Bacterienne(Liquide Biologique)' ) , 'R' , null , now() , 1780);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hemoculture(Sang)' ) , 'R' , null , now() , 1790);
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=1820
	WHERE test_id= ( select id from clinlims.test where description = 'Densite(Urines)' );
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'pH(Urines)' ) , 'N' , null , now() , 1830);
UPDATE clinlims.test_result SET tst_rslt_type='R', sort_order=2050
	WHERE test_id= ( select id from clinlims.test where description = 'Bleu de Methylene(Selles)' );
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Examen Microscopique direct(Selles)' ) , 'R' , null , now() , 2060);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Examen Microscopique apres concentration(Selles)' ) , 'R' , null , now() , 2070);
UPDATE clinlims.test_result SET tst_rslt_type='R', sort_order=2080
	WHERE test_id= ( select id from clinlims.test where description = 'Recherche de cryptosporidium et Oocyste(Selles)' );
UPDATE clinlims.test_result SET tst_rslt_type='R', sort_order=2090
	WHERE test_id= ( select id from clinlims.test where description = 'Recheche de microfilaire(Sang)' );
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P. falciparum' )  , now() , 2110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P. vivax' )  , now() , 2115);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P. malariae' )  , now() , 2120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='P. ovale' )  , now() , 2130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Trophozoite' )  , now() , 2140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Gametocyte' )  , now() , 2150);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Schizonte' )  , now() , 2160);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='1+' )  , now() , 2170);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='2+' )  , now() , 2180);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='3+' )  , now() , 2190);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Malaria(Sang)' ) , 'M' ,  ( select max(id) from clinlims.dictionary where dict_entry ='4+' )  , now() , 2200);
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=2220
	WHERE test_id= ( select id from clinlims.test where description = 'CD4 en mm3(Sang)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=2230
	WHERE test_id= ( select id from clinlims.test where description = 'CD4 en %(Sang)' );
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH test rapide(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2240);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH test rapide(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2250);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH test rapide(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 2260);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH test rapide(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2270);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH test rapide(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2280);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH test rapide(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 2290);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH test rapide(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2300);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH test rapide(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2310);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH test rapide(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 2320);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Elisa(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2330);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Elisa(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2340);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Elisa(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2350);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Elisa(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2360);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Elisa(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2370);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VIH Elisa(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2380);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B Ag(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2390);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B Ag(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2400);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B Ag(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2410);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B Ag(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2420);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B Ag(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2430);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B Ag(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2440);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite C IgM(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2450);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite C IgM(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2460);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite C IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2470);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite C IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2480);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite C IgM(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2490);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite C IgM(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2500);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue NS1 Ag(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2510);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue NS1 Ag(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2520);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue NS1 Ag(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2530);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue NS1 Ag(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2540);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 1(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2560);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 1(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 2570);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 1(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 2580);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 1(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 2590);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 1(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Rare (1-9 BAAR/100ch)' )  , now() , 2600);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 2(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2610);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 2(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 2620);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 2(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 2630);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 2(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 2640);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 2(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Rare (1-9 BAAR/100ch)' )  , now() , 2650);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 3(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2660);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 3(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 2670);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 3(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 2680);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 3(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 2690);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 3(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Rare (1-9 BAAR/100ch)' )  , now() , 2700);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 1(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2710);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 1(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 2720);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 1(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 2730);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 1(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 2740);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 1(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Rare (1-9 BAAR/40ch)' )  , now() , 2750);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 2(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2760);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 2(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 2770);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 2(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 2780);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 2(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 2790);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 2(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Rare (1-9 BAAR/40ch)' )  , now() , 2800);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 3(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2810);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 3(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 2820);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 3(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 2830);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 3(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 2840);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 3(Liquide Pleural)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Rare (1-9 BAAR/40ch)' )  , now() , 2850);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 1(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2860);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 1(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 2870);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 1(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 2880);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 1(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 2890);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 1(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Rare (1-9 BAAR/100ch)' )  , now() , 2900);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 2(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2910);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 2(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 2920);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 2(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 2930);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 2(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 2940);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 2(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Rare (1-9 BAAR/100ch)' )  , now() , 2950);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 3(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2960);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 3(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 2970);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 3(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 2980);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 3(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 2990);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Ziehl Neelsen Specimen 3(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Rare (1-9 BAAR/100ch)' )  , now() , 3000);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 1(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 3010);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 1(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 3020);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 1(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 3030);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 1(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 3040);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 1(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Rare (1-9 BAAR/40ch)' )  , now() , 3050);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 2(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 3060);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 2(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 3070);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 2(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 3080);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 2(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 3090);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 2(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Rare (1-9 BAAR/40ch)' )  , now() , 3100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 3(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 3110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 3(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 1+' )  , now() , 3120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 3(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 2+' )  , now() , 3130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 3(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif 3+' )  , now() , 3140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BARR par Fluorochrome Specimen 3(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Rare (1-9 BAAR/40ch)' )  , now() , 3150);
UPDATE clinlims.test_result SET tst_rslt_type='R', sort_order=3160
	WHERE test_id= ( select id from clinlims.test where description = 'Culture de M. tuberculosis(Expectoration)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=3180
	WHERE test_id= ( select id from clinlims.test where description = 'PPD Quantitatif(In Vivo)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=3190
	WHERE test_id= ( select id from clinlims.test where description = 'Prolactine(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=3200
	WHERE test_id= ( select id from clinlims.test where description = 'FSH(Serum)' );
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'FSH(Plasma hepariné)' ) , 'N' , null , now() , 3210);
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=3220
	WHERE test_id= ( select id from clinlims.test where description = 'LH(Serum)' );
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'LH(Plasma hepariné)' ) , 'N' , null , now() , 3230);
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=3240
	WHERE test_id= ( select id from clinlims.test where description = 'Oestrogene(Urines/24 heures)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=3250
	WHERE test_id= ( select id from clinlims.test where description = 'Progesterone(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=3260
	WHERE test_id= ( select id from clinlims.test where description = 'T3(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=3270
	WHERE test_id= ( select id from clinlims.test where description = 'B-HCG(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=3310
	WHERE test_id= ( select id from clinlims.test where description = 'T4(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=3320
	WHERE test_id= ( select id from clinlims.test where description = 'TSH(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='R', sort_order=3330
	WHERE test_id= ( select id from clinlims.test where description = 'LCR GRAM(LCR/CSF)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=3350
	WHERE test_id= ( select id from clinlims.test where description = 'TOXOPLASMOSE GONDII IgG Ac(Serum)' );
UPDATE clinlims.test_result SET tst_rslt_type='N', sort_order=3360
	WHERE test_id= ( select id from clinlims.test where description = 'TOXOPLASMOSE GONDII Ig M Ac(Serum)' );
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Herpes Simplex(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 3540);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Herpes Simplex(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 3550);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Herpes Simplex(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 3560);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Herpes Simplex(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 3570);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chlamydia Ab(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 3580);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chlamydia Ab(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 3590);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chlamydia Ag(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 3600);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chlamydia Ag(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 3610);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Typhoide Widal Ag O(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Non Reactif' )  , now() , 3700);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Typhoide Widal Ag O(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif' )  , now() , 3710);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Typhoide Widal Ag O(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/2' )  , now() , 3720);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Typhoide Widal Ag O(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/4' )  , now() , 3730);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Typhoide Widal Ag O(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/8' )  , now() , 3740);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Typhoide Widal Ag O(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/16' )  , now() , 3750);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Typhoide Widal Ag O(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/32' )  , now() , 3760);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Typhoide Widal Ag O(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/64' )  , now() , 3770);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Typhoide Widal Ag H(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Non Reactif' )  , now() , 3780);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Typhoide Widal Ag H(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif' )  , now() , 3790);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Typhoide Widal Ag H(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/2' )  , now() , 3800);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Typhoide Widal Ag H(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/4' )  , now() , 3810);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Typhoide Widal Ag H(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/8' )  , now() , 3820);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Typhoide Widal Ag H(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/16' )  , now() , 3830);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Typhoide Widal Ag H(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/32' )  , now() , 3840);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Typhoide Widal Ag H(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Reactif 1/64' )  , now() , 3850);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mono Test(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 3860);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mono Test(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 3870);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mono Test(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 3880);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'LE Cell(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 3890);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'LE Cell(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 3900);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'LE Cell(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 3910);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue Ig G(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 3920);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue Ig G(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 3930);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue Ig A(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 3940);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue Ig A(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 3950);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'CMV Ig A(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 3970);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'CMV Ig A(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 3980);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cryptococcus Antigene dipstick(Serum)' ) , 'R' , null , now() , 3990);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Clostridium Difficile Toxin A & B(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 4000);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Clostridium Difficile Toxin A & B(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 4010);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine VIH(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 4020);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine VIH(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 4030);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine VIH(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 4040);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine VIH(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 4050);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine VIH(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 4060);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine VIH(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 4070);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine VIH(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 4080);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine VIH(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 4090);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Determine VIH(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 4100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua VIH(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 4110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua VIH(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 4120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua VIH(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 4130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua VIH(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 4140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua VIH(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 4150);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua VIH(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 4160);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua VIH(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 4170);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua VIH(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 4180);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Colloidal Gold / Shangai Kehua VIH(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetermine' )  , now() , 4190);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'PSA(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='<4 Negatif' )  , now() , 4230);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'PSA(Serum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='> 4 Positif' )  , now() , 4240);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'PSA(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='<4 Negatif' )  , now() , 4250);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'PSA(Sang)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='> 4 Positif' )  , now() , 4260);
