Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='10^3/µ l') , reporting_description='Globules Blancs',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=10 
	WHERE description = 'Compte des Globules Blancs(Sang)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='10^6/µ l') , reporting_description='Globules Rouges',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=20 
	WHERE description = 'Compte des Globules Rouges(Sang)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='g/dl') , reporting_description='Hb',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=30 
	WHERE description = 'Hemoglobine(Sang)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='%') , reporting_description='Ht',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=40 
	WHERE description = 'Hematocrite(Sang)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='fl') , reporting_description='VGM',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=50 
	WHERE description = 'VGM(Sang)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='pg') , reporting_description='TCMH',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=60 
	WHERE description = 'TCMH(Sang)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='%') , reporting_description='CCMH',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=70 
	WHERE description = 'CCMH(Sang)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='10^3/֬') , reporting_description='Plaquettes',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=80 
	WHERE description = 'Plaquettes(Sang)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='%') , reporting_description='Neutrophiles',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=90 
	WHERE description = 'Neutrophiles(Sang)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='%') , reporting_description='Lymphocytes',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=100 
	WHERE description = 'Lymphocytes(Sang)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='%') , reporting_description='Mixtes',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=110 
	WHERE description = 'Mixtes(Sang)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='%') , reporting_description='Monocytes',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=120 
	WHERE description = 'Monocytes(Sang)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='%') , reporting_description='Eosinophiles',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=130 
	WHERE description = 'Eosinophiles(Sang)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='%') , reporting_description='Basophiles',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=140 
	WHERE description = 'Basophiles(Sang)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mm/heure') , reporting_description='Vitesse de Sedimentation',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=150 
	WHERE description = 'Vitesse de Sedimentation(Sang)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='min') , reporting_description='Temps de Coagulation en tube',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=160 
	WHERE description = 'Temps de Coagulation en tube(Sang)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='min') , reporting_description='Temps de Coagulation',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=170 
	WHERE description = 'Temps de Coagulation(Sang)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='min') , reporting_description='Temps de saignement',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=180 
	WHERE description = 'Temps de saignement(Sang)';
Update test SET uom_id= null , reporting_description=$$Electrophorese de l'hemoglobine$$,is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=190 
	WHERE description = $$Electrophorese de l'hemoglobine(Sang)$$;
Update test SET uom_id= null , reporting_description='Sickling Test',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=200 
	WHERE description = 'Sickling Test(Sang)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='%') , reporting_description='Reticulo-Auto',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=210 
	WHERE description = 'Taux reticulocytes - Auto(Sang)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='%') , reporting_description='Reticulo-Manu',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=220 
	WHERE description = 'Taux reticulocytes - Manual(Sang)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='secondes') , reporting_description='PTT',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=230 
	WHERE description = 'Temps de cephaline Activé(TCA)(Plasma)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='secondes') , reporting_description='PT',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=240 
	WHERE description = 'Temps de Prothrombine(Plasma)';
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'INR(Plasma)' , 'INR' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'INR' ,250 , 'INR' );
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='%') , reporting_description='Facteur VIII',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=260 
	WHERE description = 'Facteur VIII(Plasma)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='%') , reporting_description='Facteur IX',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=270 
	WHERE description = 'Facteur IX(Plasma)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='UI/ml') , reporting_description='Heparinemie',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=280 
	WHERE description = 'Heparinemie(Plasma)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='g/l') , reporting_description='AT III Dosage',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=290 
	WHERE description = 'Anti-Thrombine III (Dosage)(Plasma)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='%') , reporting_description='AT III Active',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=300 
	WHERE description = 'Anti-Thrombine III (Activite)(Plasma)';
Update test SET uom_id= null , reporting_description='Groupe sanguin ABO',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=310 
	WHERE description = 'Groupe Sanguin - ABO(Sang)';
Update test SET uom_id= null , reporting_description='Groupe sanguin Rhesus',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=320 
	WHERE description = 'Groupe Sanguin - Rhesus(Sang)';
Update test SET uom_id= null , reporting_description='crossmatch',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=330 
	WHERE description = 'Test de comptabilite(Sang)';
Update test SET uom_id= null , reporting_description='crossmatch',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=340 
	WHERE description = 'Test de comptabilite(Serum)';
Update test SET uom_id= null , reporting_description='Coombs Direct',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=350 
	WHERE description = 'Coombs Test Direct(Sang)';
Update test SET uom_id= null , reporting_description='Coombs Indirect',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=360 
	WHERE description = 'Coombs Test Indirect(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mg/dl') , reporting_description=$$Azote de l'Uree$$,is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=370 
	WHERE description = $$Azote de l'Uree(Serum)$$;
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mg/dl') , reporting_description='Uree',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=380 
	WHERE description = 'Uree(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mg/dl') , reporting_description='creatinine',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=390 
	WHERE description = 'creatinine(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mg/dl') , reporting_description='Glycemie',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=400 
	WHERE description = 'Glycemie(Serum)';
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/l') , 'Glycemie(LCR/CSF)' , 'Glycemie' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Glycemie' ,410 , 'Glycemie' );
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mg/dl') , reporting_description='Proteines',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=420 
	WHERE description = 'Proteines(LCR/CSF)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mEq/L') , reporting_description='Chlore',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=430 
	WHERE description = 'Chlore(LCR/CSF)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mg/dl') , reporting_description='glycemie',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=440 
	WHERE description = 'glycemie(Plasma)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='g/dl') , reporting_description='Albumine',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=450 
	WHERE description = 'Albumine(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='g/dl') , reporting_description='α1 globuline',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=460 
	WHERE description = 'α1 globuline(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='g/dl') , reporting_description='α2 globuline',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=470 
	WHERE description = 'α2 globuline(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='g/dl') , reporting_description='β globuline',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=480 
	WHERE description = 'β globuline(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='g/dl') , reporting_description='ϒ globuline',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=490 
	WHERE description = 'ϒ globuline(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mEq/ml') , reporting_description='Chlorures',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=510 
	WHERE description = 'Chlorures(Plasma hepariné)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mmol/l 24h') , reporting_description='Chlorures',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=520 
	WHERE description = 'Chlorures(Urines/24 heures)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='U/L a 30у') , reporting_description='CPK',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=530 
	WHERE description = 'CPK(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='U/L') , reporting_description='Amylase',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=540 
	WHERE description = 'Amylase(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='U/L') , reporting_description='Lipase',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=550 
	WHERE description = 'Lipase(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mmol/L') , reporting_description='Chlorures',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=560 
	WHERE description = 'Chlorures(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mEq/l') , reporting_description='Sodium',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=570 
	WHERE description = 'Sodium(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mEq/l') , reporting_description='Potassium',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=580 
	WHERE description = 'Potassium(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mEq/l') , reporting_description='Bicarbonates',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=590 
	WHERE description = 'Bicarbonates(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mg/dl') , reporting_description='Calcium',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=600 
	WHERE description = 'Calcium(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mEq/L') , reporting_description='magnésium',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=610 
	WHERE description = 'magnésium(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mg/dl') , reporting_description='phosphore',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=620 
	WHERE description = 'phosphore(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mmol/L') , reporting_description='Lithium',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=630 
	WHERE description = 'Lithium(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='nmol/L') , reporting_description='Fer Serique',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=640 
	WHERE description = 'Fer Serique(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mg/dl') , reporting_description='Bilirubine totale',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=650 
	WHERE description = 'Bilirubine totale(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mg/dl') , reporting_description='Bilirubine directe',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=660 
	WHERE description = 'Bilirubine directe(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mg/dl') , reporting_description='Bilirubine indirecte',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=670 
	WHERE description = 'Bilirubine indirecte(Serum)';
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='U/L') , 'SGOT/ AST(Serum)' , 'SGOT/ AST' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'SGOT/ AST' ,680 , 'SGOT/ AST' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='U/L') , 'SGPT/ ALT(Serum)' , 'SGPT/ ALT' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'SGPT/ ALT' ,690 , 'SGPT/ ALT' );
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='U/L') , reporting_description='Phosphatase Alcaline',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=700 
	WHERE description = 'Phosphatase Alcaline(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mg/dl') , reporting_description='Cholesterol Total',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=710 
	WHERE description = 'Cholesterol Total(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mg/dl') , reporting_description='Triglyceride',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=720 
	WHERE description = 'Triglyceride(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mg/dl') , reporting_description='Lipide',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=730 
	WHERE description = 'Lipide(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mg/dl') , reporting_description='HDL',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=740 
	WHERE description = 'HDL(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mg/dl') , reporting_description='LDL',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=750 
	WHERE description = 'LDL(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mg/dl') , reporting_description='VLDL',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=760 
	WHERE description = 'VLDL(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mg/dl') , reporting_description='MBG',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=770 
	WHERE description = 'MBG(Serum)';
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='%') , 'Hémoglobine glycolisee(Serum)' , 'Hémoglobine glycosee' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Hémoglobine glycoli' ,780 , 'Hémoglobine glycolisee' );
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='U/L') , reporting_description='LDH',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=790 
	WHERE description = 'LDH(Serum)';
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='ng/ml') , 'Triponine I(Serum)' , 'Triponine I' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Triponine I' ,800 , 'Triponine I' );
Update test SET uom_id= null , reporting_description='Ph',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=810 
	WHERE description = 'Ph(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mm Hg') , reporting_description='PaCO2',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=820 
	WHERE description = 'PaCO2(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mEq/L') , reporting_description='HCO3',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=830 
	WHERE description = 'HCO3(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='%') , reporting_description='O2 Saturation',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=840 
	WHERE description = 'O2 Saturation(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mm Hg') , reporting_description='PaO2',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=850 
	WHERE description = 'PaO2(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mmol/L') , reporting_description='BE',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=860 
	WHERE description = 'BE(Serum)';
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Glycémie(Serum)' , 'Glycémie' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Glycémie' ,870 , 'Glycémie' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Glycemie Provoquee Fasting(Serum)' , 'Glycemie Fasting' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Glycemie Provoquee F' ,880 , 'Glycemie Provoquee Fasting' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Glycémie provoquée 1/2 hre(Serum)' , 'Glycémie provoquee 1/2 hre' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Glycémie provoquée' ,890 , 'Glycémie provoquée 1/2 hre' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Glycémie provoquée 1hre(Serum)' , 'Glycémie provoquee 1hre' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Glycémie provoquée' ,900 , 'Glycémie provoquée 1hre' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Glycémie provoquée 2hres(Serum)' , 'Glycémie provoquee 2hres' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Glycémie provoquée' ,910 , 'Glycémie provoquée 2hres' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Glycémie provoquée 3hres(Serum)' , 'Glycémie provoquee 3 hres' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Glycémie provoquée' ,920 , 'Glycémie provoquée 3hres' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Glycémie provoquée 4hres(Serum)' , 'Glycémie provoquee 4 hres' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Glycémie provoquée' ,930 , 'Glycémie provoquée 4hres' );
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mg/dl') , reporting_description='Glycémie provoquee 5 hres',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Biochemistry' ) ,sort_order=940 
	WHERE description = 'Glycémie provoquée(Serum)';
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Glycemie Postprandiale(Serum)' , 'Glycemie Fastning' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Glycemie Postprandia' ,950 , 'Glycemie Postprandiale' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Azote Urée(Serum)' , 'Azote Urée' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Azote Urée' ,960 , 'Azote Urée' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Urée (calculée)(Serum)' , 'Urée (calculée)' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Urée (calculée)' ,970 , 'Urée (calculée)' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Créatinine(Serum)' , 'Créatinine' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Créatinine' ,980 , 'Créatinine' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='U/L') , 'SGPT (ALT)(Serum)' , 'SGPT (ALT)' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'SGPT (ALT)' ,990 , 'SGPT (ALT)' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='U/L') , 'SGOT (AST)(Serum)' , 'SGOT (AST)' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'SGOT (AST)' ,1000 , 'SGOT (AST)' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Cholestérol total(Serum)' , 'Cholestérol total' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Cholestérol total' ,1010 , 'Cholestérol total' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'HDL-cholestérol(Serum)' , 'HDL-cholestérol' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'HDL-cholestérol' ,1020 , 'HDL-cholestérol' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'LDL-cholesterol (calculée)(Serum)' , 'LDL-cholesterol (calculée)' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'LDL-cholesterol (cal' ,1030 , 'LDL-cholesterol (calculée)' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'VLDL – cholesterol (calculée)(Serum)' , 'VLDL – cholesterol (calculée)' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'VLDL – cholesterol' ,1040 , 'VLDL – cholesterol (calculée)' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Triglycéride(Serum)' , 'Triglycéride' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Triglycéride' ,1050 , 'Triglycéride' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Acide urique(Serum)' , 'Acide urique' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Acide urique' ,1060 , 'Acide urique' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mmol/L') , 'Chlorure(Serum)' , 'Chlorure' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Chlorure' ,1070 , 'Chlorure' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Calcium (Ca++)(Serum)' , 'Calcium (Ca++)' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Calcium (Ca++)' ,1080 , 'Calcium (Ca++)' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='g/dl') , 'Protéines totales(Serum)' , 'Protéines totales' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Protéines totales' ,1090 , 'Protéines totales' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Creatinine(Serum)' , 'Creatinine' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Creatinine' ,1100 , 'Creatinine' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Creatinine(Sang)' , 'Creatinine' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Creatinine' ,1110 , 'Creatinine' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Creatinine(Plasma)' , 'Creatinine' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Creatinine' ,1120 , 'Creatinine' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='U/L') , 'SGPT/ALT(Serum)' , 'SGPT (ALT)' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'SGPT/ALT' ,1130 , 'SGPT/ALT' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='U/L') , 'SGPT/ALT(Sang)' , 'SGPT (ALT)' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'SGPT/ALT' ,1140 , 'SGPT/ALT' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='U/L') , 'SGPT/ALT(Plasma)' , 'SGPT (ALT)' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'SGPT/ALT' ,1150 , 'SGPT/ALT' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='U/L') , 'SGOT/AST(Serum)' , 'SGOT (AST)' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'SGOT/AST' ,1160 , 'SGOT/AST' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='U/L') , 'SGOT/AST(Sang)' , 'SGOT (AST)' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'SGOT/AST' ,1170 , 'SGOT/AST' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='U/L') , 'SGOT/AST(Plasma)' , 'SGOT (AST)' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'SGOT/AST' ,1180 , 'SGOT/AST' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/L') , 'CRP Quantitatif(Serum)' , 'CRP Quantitatif' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'CRP Quantitatif' ,1190 , 'CRP Quantitatif' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/L') , 'CRP Quantitatif(Plasma)' , 'CRP Quantitatif' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'CRP Quantitatif' ,1200 , 'CRP Quantitatif' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='g/L') , 'C3 du Complement(Sang)' , 'C3 du Complement' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'C3 du Complement' ,1210 , 'C3 du Complement' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='g/L') , 'C4 du complement(Serum)' , 'C4 du complement' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'C4 du complement' ,1220 , 'C4 du complement' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='g/L') , 'C3 du Complement(Serum)' , 'C3 du Complement' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'C3 du Complement' ,1230 , 'C3 du Complement' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='g/L') , 'C4 du complement(Sang)' , 'C4 du complement' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'C4 du complement' ,1240 , 'C4 du complement' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='UI/mL') , 'Facteur Rhumatoide(Serum)' , 'Facteur Rhumatoide' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Facteur Rhumatoide' ,1250 , 'Facteur Rhumatoide' );
Update test SET uom_id= null , reporting_description='Bacteries',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1260 
	WHERE description = 'Bacteries(Secretion vaginale)';
Update test SET uom_id= null , reporting_description='Levures Simples',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1270 
	WHERE description = 'Levures Simples(Secretion vaginale)';
Update test SET uom_id= null , reporting_description='Levures Bourgeonantes',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1280 
	WHERE description = 'Levures Bourgeonantes(Secretion vaginale)';
Update test SET uom_id= null , reporting_description='Trichomonas vaginalis',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1290 
	WHERE description = 'Trichomonas vaginalis(Secretion vaginale)';
Update test SET uom_id= null , reporting_description='Leucocytes',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1300 
	WHERE description = 'Globules Blancs(Secretion vaginale)';
Update test SET uom_id= null , reporting_description='Hematies',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1310 
	WHERE description = 'Globules Rouges(Secretion vaginale)';
Update test SET uom_id= null , reporting_description='Filaments Myceliens',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1320 
	WHERE description = 'Filaments Myceliens(Secretion vaginale)';
Update test SET uom_id= null , reporting_description='Cellules Epitheliales',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1330 
	WHERE description = 'Cellules Epitheliales(Secretion vaginale)';
Update test SET uom_id= null , reporting_description='Bacteries',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1340 
	WHERE description = 'Bacteries(Secretion Urethrale)';
Update test SET uom_id= null , reporting_description='Levures Simples',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1350 
	WHERE description = 'Levures Simples(Secretion Urethrale)';
Update test SET uom_id= null , reporting_description='Levures Bourgeonantes',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1360 
	WHERE description = 'Levures Bourgeonantes(Secretion Urethrale)';
Update test SET uom_id= null , reporting_description='Trichomonas hominis',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1370 
	WHERE description = 'Trichomonas hominis(Secretion Urethrale)';
Update test SET uom_id= null , reporting_description='Leucocytes',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1380 
	WHERE description = 'Globules Blancs(Secretion Urethrale)';
Update test SET uom_id= null , reporting_description='Hematies',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1390 
	WHERE description = 'Globules Rouges(Secretion Urethrale)';
Update test SET uom_id= null , reporting_description='Filament Mycelien',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1400 
	WHERE description = 'Filaments Myceliens(Secretion Urethrale)';
Update test SET uom_id= null , reporting_description='Cellules Epitheliales',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1410 
	WHERE description = 'Cellules Epitheliales(Secretion Urethrale)';
Update test SET uom_id= null , reporting_description='KOH',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1420 
	WHERE description = 'KOH(Secretion vaginale)';
Update test SET uom_id= null , reporting_description='Test de Rivalta',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1430 
	WHERE description = 'Test de Rivalta(Secretion vaginale)';
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Catalase(Sang)' , 'Catalase' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,'Catalase' ,1440 , 'Catalase' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Oxydase(Sang)' , 'Oxydase' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,'Oxydase' ,1450 , 'Oxydase' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Coagulase libre(Sang)' , 'Coagulase libre' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,'Coagulase libre' ,1460 , 'Coagulase libre' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'DNAse(Sang)' , 'DNAse' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,'DNAse' ,1470 , 'DNAse' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , $$Hydrolyse de l'esculine(Sang)$$ , $$Hydrolyse de l'esculine$$ , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,$$Hydrolyse de l'escul$$ ,1480 , $$Hydrolyse de l'esculine$$ );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Urée-tryptophane(Sang)' , 'Urée-tryptophane' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,'Urée-tryptophane' ,1490 , 'Urée-tryptophane' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Mobilité(Sang)' , 'Mobilité' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,'Mobilité' ,1500 , 'Mobilité' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Test à la potasse(Sang)' , 'Test à la potasse' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,'Test à la potasse' ,1510 , 'Test à la potasse' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Test à la porphyrine(Sang)' , 'Test à la porphyrine' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,'Test à la porphyrin' ,1520 , 'Test à la porphyrine' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'ONPG(Sang)' , 'ONPG' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,'ONPG' ,1530 , 'ONPG' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Réaction de Voges-Proskauer(Sang)' , 'Réaction de Voges-Proskauer' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,'Réaction de Voges-P' ,1540 , 'Réaction de Voges-Proskauer' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Camp-test(Sang)' , 'Camp-test' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,'Camp-test' ,1550 , 'Camp-test' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , $$Techniques d'agglutination(Sang)$$ , $$Techniques d'agglutination$$ , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,$$Techniques d'aggluti$$ ,1560 , $$Techniques d'agglutination$$ );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Coloration de Gram(Sang)' , 'Coloration de Gram' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,'Coloration de Gram' ,1570 , 'Coloration de Gram' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Coloration de Ziehl-Neelsen(Sang)' , 'Coloration de Ziehl-Neelsen' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,'Coloration de Ziehl-' ,1580 , 'Coloration de Ziehl-Neelsen' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , $$Coloration à l'auramine(Sang)$$ , $$Coloration à l'auramine$$ , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,$$Coloration à l'aura$$ ,1590 , $$Coloration à l'auramine$$ );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , $$Coloration à l'acridine orange(Sang)$$ , $$Coloration à l'acridine orange$$ , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,$$Coloration à l'acri$$ ,1600 , $$Coloration à l'acridine orange$$ );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Coloration de Kinyoun(Sang)' , 'Coloration de Kinyoun' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,'Coloration de Kinyou' ,1610 , 'Coloration de Kinyoun' );
Update test SET uom_id= null , reporting_description='Frottis Vaginal Gram',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1620 
	WHERE description = 'Frottis Vaginal/Gram(Secretion vaginale)';
Update test SET uom_id= null , reporting_description='Frottis Uretral Gram',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1630 
	WHERE description = 'Frottis Uretral/Gram(Secretion Urethrale)';
Update test SET uom_id= null , reporting_description='Cholera Rapide',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1640 
	WHERE description = 'Cholera Test rapide(Selles)';
Update test SET uom_id= null , reporting_description='Coproculture',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1650 
	WHERE description = 'Coproculture(Selles)';
Update test SET uom_id= null , reporting_description='Couleur',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1660 
	WHERE description = 'Couleur(Liquide Spermatique)';
Update test SET uom_id= null , reporting_description='Liquefaction',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1670 
	WHERE description = 'Liquefaction(Liquide Spermatique)';
Update test SET uom_id= null , reporting_description='pH',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1680 
	WHERE description = 'pH(Liquide Spermatique)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mg/dl') , reporting_description='Fructose',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1690 
	WHERE description = 'Fructose(Liquide Spermatique)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='ml') , reporting_description='Volume',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1700 
	WHERE description = 'Volume(Liquide Spermatique)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='millions/ml') , reporting_description='Compte Spermes',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1710 
	WHERE description = 'Compte de spermes(Liquide Spermatique)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='%') , reporting_description='Formes normales',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1720 
	WHERE description = 'Formes normales(Liquide Spermatique)';
Update test SET uom_id= null , reporting_description='Formes anormales',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1730 
	WHERE description = 'Formes anormales(Liquide Spermatique)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='%') , reporting_description='Motilite STAT',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1740 
	WHERE description = 'Motilite STAT(Liquide Spermatique)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='%') , reporting_description='Motilite/heure',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1750 
	WHERE description = 'Motilite 1 heure(Liquide Spermatique)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='%') , reporting_description='Motilite/3heures',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,sort_order=1760 
	WHERE description = 'Motilite 3 heures(Liquide Spermatique)';
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Coloration de Gram(Liquide Spermatique)' , 'Coloration de Gram' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Coloration de Gram' ,1770 , 'Coloration de Gram' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Culture Bacterienne(Liquide Biologique)' , 'Culture Bacterienne' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,'Culture Bacterienne' ,1780 , 'Culture Bacterienne' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Hemoculture(Sang)' , 'Hemoculture' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,'Hemoculture' ,1790 , 'Hemoculture' );
Update test SET uom_id= null , reporting_description='Couleur',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'ECBU' ) ,sort_order=1800 
	WHERE description = 'Couleur(Urines)';
Update test SET uom_id= null , reporting_description='Aspect',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'ECBU' ) ,sort_order=1810 
	WHERE description = 'Aspect(Urines)';
Update test SET uom_id= null , reporting_description='Densite',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'ECBU' ) ,sort_order=1820 
	WHERE description = 'Densite(Urines)';
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'pH(Urines)' , 'pH' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'ECBU' ) ,'pH' ,1830 , 'pH' );
Update test SET uom_id= null , reporting_description='Proteines',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'ECBU' ) ,sort_order=1840 
	WHERE description = 'Proteines(Urines)';
Update test SET uom_id= null , reporting_description='Glucose',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'ECBU' ) ,sort_order=1850 
	WHERE description = 'Glucose(Urines)';
Update test SET uom_id= null , reporting_description='Cetones',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'ECBU' ) ,sort_order=1860 
	WHERE description = 'Cetones(Urines)';
Update test SET uom_id= null , reporting_description='Bilirubine',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'ECBU' ) ,sort_order=1870 
	WHERE description = 'Bilirubine(Urines)';
Update test SET uom_id= null , reporting_description='Sang',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'ECBU' ) ,sort_order=1880 
	WHERE description = 'Sang(Urines)';
Update test SET uom_id= null , reporting_description='Leucocytes',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'ECBU' ) ,sort_order=1890 
	WHERE description = 'Leucocytes(Urines)';
Update test SET uom_id= null , reporting_description='Acide ascorbique',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'ECBU' ) ,sort_order=1900 
	WHERE description = 'Acide ascorbique(Urines)';
Update test SET uom_id= null , reporting_description='Urobilinogene',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'ECBU' ) ,sort_order=1910 
	WHERE description = 'Urobilinogene(Urines)';
Update test SET uom_id= null , reporting_description='Nitrites',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'ECBU' ) ,sort_order=1920 
	WHERE description = 'Nitrites(Urines)';
Update test SET uom_id= null , reporting_description='Hematies',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'ECBU' ) ,sort_order=1930 
	WHERE description = 'Hematies(Urines)';
Update test SET uom_id= null , reporting_description='Cell Epith',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'ECBU' ) ,sort_order=1940 
	WHERE description = 'cellules epitheliales(Urines)';
Update test SET uom_id= null , reporting_description='Bacteries',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'ECBU' ) ,sort_order=1950 
	WHERE description = 'Bacteries(Urines)';
Update test SET uom_id= null , reporting_description='Levures',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'ECBU' ) ,sort_order=1960 
	WHERE description = 'Levures(Urines)';
Update test SET uom_id= null , reporting_description='Mycelium',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'ECBU' ) ,sort_order=1970 
	WHERE description = 'filaments myceliens(Urines)';
Update test SET uom_id= null , reporting_description='spores',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'ECBU' ) ,sort_order=1980 
	WHERE description = 'spores(Urines)';
Update test SET uom_id= null , reporting_description='trichomonas',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'ECBU' ) ,sort_order=1990 
	WHERE description = 'trichomonas(Urines)';
Update test SET uom_id= null , reporting_description='Cylindres',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'ECBU' ) ,sort_order=2000 
	WHERE description = 'Cylindres(Urines)';
Update test SET uom_id= null , reporting_description='Cristaux',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'ECBU' ) ,sort_order=2010 
	WHERE description = 'Cristaux(Urines)';
Update test SET uom_id= null , reporting_description='Couleur',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Parasitology' ) ,sort_order=2020 
	WHERE description = 'Couleur(Selles)';
Update test SET uom_id= null , reporting_description='Aspect',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Parasitology' ) ,sort_order=2030 
	WHERE description = 'Aspect(Selles)';
Update test SET uom_id= null , reporting_description='Sang Occulte',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Parasitology' ) ,sort_order=2040 
	WHERE description = 'Sang Occulte(Selles)';
Update test SET uom_id= null , reporting_description='B. Methylene',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Parasitology' ) ,sort_order=2050 
	WHERE description = 'Bleu de Methylene(Selles)';
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Examen Microscopique direct(Selles)' , 'Examen Microscopique direct' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Parasitology' ) ,'Examen Microscopique' ,2060 , 'Examen Microscopique direct' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Examen Microscopique apres concentration(Selles)' , 'Examen Microscopique apres concentration' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Parasitology' ) ,'Examen Microscopique' ,2070 , 'Examen Microscopique apres concentration' );
Update test SET uom_id= null , reporting_description='Cryptosporidium et Oocystes',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Parasitology' ) ,sort_order=2080 
	WHERE description = 'Recherche de cryptosporidium et Oocyste(Selles)';
Update test SET uom_id= null , reporting_description='Microfiliares',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Parasitology' ) ,sort_order=2090 
	WHERE description = 'Recheche de microfilaire(Sang)';
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Malaria(Sang)' , 'Malaria' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Parasitology' ) ,'Malaria' ,2100 , 'Malaria' );
Update test SET uom_id= null , reporting_description='Malaria Test Rapide',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Parasitology' ) ,sort_order=2110 
	WHERE description = 'Malaria Test Rapide(Sang)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='֬') , reporting_description='CD4 en mm3',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=2120 
	WHERE description = 'CD4 en mm3(Sang)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='%') , reporting_description='CD4 en %',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Hematology' ) ,sort_order=2130 
	WHERE description = 'CD4 en %(Sang)';
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'VIH test rapide(Serum)' , 'VIH test rapide' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'VIH test rapide' ,2140 , 'VIH test rapide' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'VIH test rapide(Plasma)' , 'VIH test rapide' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'VIH test rapide' ,2150 , 'VIH test rapide' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'VIH test rapide(Sang)' , 'VIH test rapide' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'VIH test rapide' ,2160 , 'VIH test rapide' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'VIH Elisa(Serum)' , 'VIH Elisa' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'VIH Elisa' ,2170 , 'VIH Elisa' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'VIH Elisa(Plasma)' , 'VIH Elisa' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'VIH Elisa' ,2180 , 'VIH Elisa' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'VIH Elisa(Sang)' , 'VIH Elisa' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'VIH Elisa' ,2190 , 'VIH Elisa' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Hépatite B Ag(Sang)' , 'Hepatite B Ag' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Hépatite B Ag' ,2200 , 'Hépatite B Ag' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Hépatite B Ag(Plasma)' , 'Hepatite B Ag' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Hépatite B Ag' ,2210 , 'Hépatite B Ag' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Hépatite B Ag(Serum)' , 'Hepatite B Ag' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Hépatite B Ag' ,2220 , 'Hépatite B Ag' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Hépatite C IgM(Sang)' , 'Hepatite C Aby' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Hépatite C IgM' ,2230 , 'Hépatite C IgM' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Hépatite C IgM(Plasma)' , 'Hepatite C Aby' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Hépatite C IgM' ,2240 , 'Hépatite C IgM' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Hépatite C IgM(Serum)' , 'Hepatite C Aby' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Hépatite C IgM' ,2250 , 'Hépatite C IgM' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Dengue NS1 Ag(Serum)' , 'Dengue NS1 Ag' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Dengue NS1 Ag' ,2260 , 'Dengue NS1 Ag' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Dengue NS1 Ag(Plasma)' , 'Dengue NS1 Ag' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Dengue NS1 Ag' ,2270 , 'Dengue NS1 Ag' );
Update test SET uom_id= null , reporting_description='Dengue NS1 Ag',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Immunology' ) ,sort_order=2280 
	WHERE description = 'Dengue(Sang)';
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Recherche de BARR par Ziehl Neelsen Specimen 1(Liquide Pleural)' , 'Recherche BAAR par Ziehl Neelsen Specimen 1' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Mycobacteriology' ) ,'Recherche de BARR pa' ,2290 , 'Recherche de BARR par Ziehl Neelsen Specimen 1' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Recherche de BARR par Ziehl Neelsen Specimen 2(Liquide Pleural)' , 'Recherche BAAR par Ziehl Neelsen Specimen 2' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Mycobacteriology' ) ,'Recherche de BARR pa' ,2300 , 'Recherche de BARR par Ziehl Neelsen Specimen 2' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Recherche de BARR par Ziehl Neelsen Specimen 3(Liquide Pleural)' , 'Recherche BAAR par Ziehl Neelsen Specimen 3' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Mycobacteriology' ) ,'Recherche de BARR pa' ,2310 , 'Recherche de BARR par Ziehl Neelsen Specimen 3' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Recherche de BARR par Fluorochrome Specimen 1(Liquide Pleural)' , 'Recherche de BARR par Fluorochrome Specimen 1' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Mycobacteriology' ) ,'Recherche de BARR pa' ,2320 , 'Recherche de BARR par Fluorochrome Specimen 1' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Recherche de BARR par Fluorochrome Specimen 2(Liquide Pleural)' , 'Recherche de BARR par Fluorochrome Specimen 2' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Mycobacteriology' ) ,'Recherche de BARR pa' ,2330 , 'Recherche de BARR par Fluorochrome Specimen 2' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Recherche de BARR par Fluorochrome Specimen 3(Liquide Pleural)' , 'Recherche de BARR par Fluorochrome Specimen 3' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Mycobacteriology' ) ,'Recherche de BARR pa' ,2340 , 'Recherche de BARR par Fluorochrome Specimen 3' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Recherche de BARR par Ziehl Neelsen Specimen 1(Sputum)' , 'Recherche BAAR Specimen 1' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Mycobacteriology' ) ,'Recherche de BARR pa' ,2350 , 'Recherche de BARR par Ziehl Neelsen Specimen 1' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Recherche de BARR par Ziehl Neelsen Specimen 2(Sputum)' , 'Recherche BAAR Specimen 2' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Mycobacteriology' ) ,'Recherche de BARR pa' ,2360 , 'Recherche de BARR par Ziehl Neelsen Specimen 2' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Recherche de BARR par Ziehl Neelsen Specimen 3(Sputum)' , 'Recherche BAAR Specimen 3' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Mycobacteriology' ) ,'Recherche de BARR pa' ,2370 , 'Recherche de BARR par Ziehl Neelsen Specimen 3' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Recherche de BARR par Fluorochrome Specimen 1(Sputum)' , 'Rech BAAR Fluo 1' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Mycobacteriology' ) ,'Recherche de BARR pa' ,2380 , 'Recherche de BARR par Fluorochrome Specimen 1' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Recherche de BARR par Fluorochrome Specimen 2(Sputum)' , 'Rech BAAR Fluo 2' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Mycobacteriology' ) ,'Recherche de BARR pa' ,2390 , 'Recherche de BARR par Fluorochrome Specimen 2' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Recherche de BARR par Fluorochrome Specimen 3(Sputum)' , 'Rech BAAR Fluo 3' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Mycobacteriology' ) ,'Recherche de BARR pa' ,2400 , 'Recherche de BARR par Fluorochrome Specimen 3' );
Update test SET uom_id= null , reporting_description='Culture BK',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Mycobacteriology' ) ,sort_order=2410 
	WHERE description = 'Culture de M. tuberculosis(Expectoration)';
Update test SET uom_id= null , reporting_description='PPD Qualitatif',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Mycobacteriology' ) ,sort_order=2420 
	WHERE description = 'PPD Qualitaitif(In Vivo)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mm') , reporting_description='PPD Quantitatif',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Mycobacteriology' ) ,sort_order=2430 
	WHERE description = 'PPD Quantitatif(In Vivo)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='֧/l') , reporting_description='Prolactine',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Endocrinologie' ) ,sort_order=2440 
	WHERE description = 'Prolactine(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mUI/ml') , reporting_description='FSH',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Endocrinologie' ) ,sort_order=2450 
	WHERE description = 'FSH(Serum)';	
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mUI/ml') , reporting_description='FSH',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Endocrinologie' ) ,sort_order=2460 
	WHERE description = 'FSH(Plasma hepariné)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mUI/ml') , reporting_description='LH',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Endocrinologie' ) ,sort_order=2470 
	WHERE description = 'LH(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='mUI/ml') , reporting_description='LH',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Endocrinologie' ) ,sort_order=2470 
	WHERE description = 'LH(Plasma hepariné)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='nmol/24 h') , reporting_description='Oestrogene',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Endocrinologie' ) ,sort_order=2490 
	WHERE description = 'Oestrogene(Urines/24 heures)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='nmol/l') , reporting_description='Progesterone',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Endocrinologie' ) ,sort_order=2500 
	WHERE description = 'Progesterone(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='nmol/l') , reporting_description='T3',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Endocrinologie' ) ,sort_order=2510 
	WHERE description = 'T3(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='UI') , reporting_description='B-HCG',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Endocrinologie' ) ,sort_order=2520 
	WHERE description = 'B-HCG(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='UI') , reporting_description='B-HCG',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Endocrinologie' ) ,sort_order=2520 
	WHERE description = 'B-HCG(Urine concentré du matin)';
Update test SET uom_id= null , reporting_description='Test Grossesse',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Endocrinologie' ) ,sort_order=2540 
	WHERE description = 'Test de Grossesse(Urines)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='nmol/l') , reporting_description='T4',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Endocrinologie' ) ,sort_order=2550 
	WHERE description = 'T4(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='֕/ml') , reporting_description='TSH',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Endocrinologie' ) ,sort_order=2560 
	WHERE description = 'TSH(Serum)';
Update test SET uom_id= null , reporting_description='LCR GRAM',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Liquides biologique' ) ,sort_order=2570 
	WHERE description = 'LCR GRAM(LCR/CSF)';
Update test SET uom_id= null , reporting_description='LCR ZIELH',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Liquides biologique' ) ,sort_order=2580 
	WHERE description = 'LCR ZIELH NIELSEN(LCR/CSF)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='UI/ML') , reporting_description='Toxoplasma IgG',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2590 
	WHERE description = 'TOXOPLASMOSE GONDII IgG Ac(Serum)';
Update test SET uom_id= ( select id from clinlims.unit_of_measure where name='UI/ML') , reporting_description='Toxoplasma IgM',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2600 
	WHERE description = 'TOXOPLASMOSE GONDII Ig M Ac(Serum)';
Update test SET uom_id= null , reporting_description='Malaria Test Rapide',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2610 
	WHERE description = 'Malaria Test Rapide(Serum)';
Update test SET uom_id= null , reporting_description='Syphilis Test Rapide',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2620 
	WHERE description = 'Syphilis Test Rapide(Serum)';
Update test SET uom_id= null , reporting_description='Syphilis Test Rapide',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2630 
	WHERE description = 'Syphilis Test Rapide(Plasma)';
Update test SET uom_id= null , reporting_description='Malaria Test Rapide',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2640 
	WHERE description = 'Malaria Test Rapide(Plasma)';
Update test SET uom_id= null , reporting_description='Syphilis Rapide',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2650 
	WHERE description = 'Syphilis Test Rapide(Sang)';
Update test SET uom_id= null , reporting_description='HTLV I et II',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2660 
	WHERE description = 'HTLV I et II(Serum)';
Update test SET uom_id= null , reporting_description='HTLV I et II',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2670 
	WHERE description = 'HTLV I et II(Plasma)';
Update test SET uom_id= null , reporting_description='HTLV I et II',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2680 
	WHERE description = 'HTLV I et II(Sang)';
Update test SET uom_id= null , reporting_description='Syphilis RPR',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2690 
	WHERE description = 'Syphilis RPR(Plasma)';
Update test SET uom_id= null , reporting_description='Syphilis RPR',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2700 
	WHERE description = 'Syphilis RPR(Sang)';
Update test SET uom_id= null , reporting_description='Syphilis TPHA',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2710 
	WHERE description = 'Syphilis TPHA(Serum)';
Update test SET uom_id= null , reporting_description='Syphilis RPR',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2720 
	WHERE description = 'Syphilis RPR(Serum)';
Update test SET uom_id= null , reporting_description='Syphilis TPHA',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2730 
	WHERE description = 'Syphilis TPHA(Plasma)';
Update test SET uom_id= null , reporting_description='Syphilis TPHA',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2740 
	WHERE description = 'Syphilis TPHA(Sang)';
Update test SET uom_id= null , reporting_description='H Pilori',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2750 
	WHERE description = 'Helicobacter Pilori(Serum)';
Update test SET uom_id= null , reporting_description='H Pilori',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2760 
	WHERE description = 'Helicobacter Pilori(Plasma)';
Update test SET uom_id= null , reporting_description='H Pilori',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2770 
	WHERE description = 'Helicobacter Pilori(Sang)';
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Herpes Simplex(Serum)' , 'Herpes Simplex' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Herpes Simplex' ,2780 , 'Herpes Simplex' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Herpes Simplex(Plasma)' , 'Herpes Simplex' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Herpes Simplex' ,2790 , 'Herpes Simplex' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Chlamydia Ab(Serum)' , 'Chlamydia Ab' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Chlamydia Ab' ,2800 , 'Chlamydia Ab' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Chlamydia Ag(Serum)' , 'Chlamydia Ag' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Chlamydia Ag' ,2810 , 'Chlamydia Ag' );
Update test SET uom_id= null , reporting_description='CRP',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2820 
	WHERE description = 'CRP(Serum)';
Update test SET uom_id= null , reporting_description='CRP',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2830 
	WHERE description = 'CRP(Plasma)';
Update test SET uom_id= null , reporting_description='CRP',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2840 
	WHERE description = 'CRP(Sang)';
Update test SET uom_id= null , reporting_description='ASO',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2850 
	WHERE description = 'ASO(Serum)';
Update test SET uom_id= null , reporting_description='ASO',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2860 
	WHERE description = 'ASO(Plasma)';
Update test SET uom_id= null , reporting_description='ASO',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2870 
	WHERE description = 'ASO(Sang)';
Update test SET uom_id= null , reporting_description='Widal Ag O',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2880 
	WHERE description = 'Test de Widal Ag O(Serum)';
Update test SET uom_id= null , reporting_description='Widal Ag H',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2890 
	WHERE description = 'Test de Widal Ag H(Serum)';
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Typhoide Widal Ag O(Serum)' , 'Widal Ag O' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Typhoide Widal Ag O' ,2900 , 'Typhoide Widal Ag O' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Typhoide Widal Ag H(Serum)' , 'Widal Ag H' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Typhoide Widal Ag H' ,2910 , 'Typhoide Widal Ag H' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Mono Test(Serum)' , 'Mono Test' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Mono Test' ,2920 , 'Mono Test' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'LE Cell(Serum)' , 'LE Cell' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'LE Cell' ,2930 , 'LE Cell' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Dengue Ig G(Serum)' , 'Dengue Ig G' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Dengue Ig G' ,2940 , 'Dengue Ig G' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Dengue Ig A(Serum)' , 'Dengue Ig A' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Dengue Ig A' ,2950 , 'Dengue Ig A' );
Update test SET uom_id= null , reporting_description='CMV Ig G',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'Serologie' ) ,sort_order=2960 
	WHERE description = 'CMV Ig G(Serum)';
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'CMV Ig A(Serum)' , 'CMV Ig A' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'CMV Ig A' ,2970 , 'CMV Ig A' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Cryptococcus Antigene dipstick(Serum)' , 'Cryptococcal Ag' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Cryptococcus Antigen' ,2980 , 'Cryptococcus Antigene dipstick' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Clostridium Difficile Toxin A & B(Serum)' , 'Clostridium Difficile Toxin A & B' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Clostridium Difficil' ,2990 , 'Clostridium Difficile Toxin A & B' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Determine VIH(Serum)' , 'Determine VIH' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'VCT' ) ,'Determine VIH' ,3000 , 'Determine VIH' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Determine VIH(Plasma)' , 'Determine VIH' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'VCT' ) ,'Determine VIH' ,3010 , 'Determine VIH' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Determine VIH(Sang)' , 'Determine VIH' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'VCT' ) ,'Determine VIH' ,3020 , 'Determine VIH' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Colloidal Gold / Shangai Kehua VIH(Serum)' , 'Colloidal Gold HIV' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'VCT' ) ,'Colloidal Gold / Sha' ,3030 , 'Colloidal Gold / Shangai Kehua VIH' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Colloidal Gold / Shangai Kehua VIH(Plasma)' , 'Colloidal Gold HIV' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'VCT' ) ,'Colloidal Gold / Sha' ,3040 , 'Colloidal Gold / Shangai Kehua VIH' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Colloidal Gold / Shangai Kehua VIH(Sang)' , 'Colloidal Gold HIV' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'VCT' ) ,'Colloidal Gold / Sha' ,3050 , 'Colloidal Gold / Shangai Kehua VIH' );
Update test SET uom_id= null , reporting_description='Syphilis Bioline',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'VCT' ) ,sort_order=3060 
	WHERE description = 'Syphilis Bioline(Serum)';
Update test SET uom_id= null , reporting_description='Syphilis Bioline',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'VCT' ) ,sort_order=3070 
	WHERE description = 'Syphilis Bioline(Plasma)';
Update test SET uom_id= null , reporting_description='Syphilis Bioline',is_active='Y', lastupdated=now(),test_section_id=(select id from clinlims.test_section where name = 'VCT' ) ,sort_order=3080 
	WHERE description = 'Syphilis Bioline(Sang)';
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'PSA(Serum)' , 'PSA' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'user' ) ,'PSA' ,3090 , 'PSA' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'PSA(Sang)' , 'PSA' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'user' ) ,'PSA' ,3100 , 'PSA' );
