INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mille/mm3') , 'Compte des Globules Blancs(Sang)' , 'WBC' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Compte des Globules ' ,10 , 'Compte des Globules Blancs' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='million/mm3') , 'Copmte des Globules Rouges(Sang)' , 'RBC' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Copmte des Globules ' ,20 , 'Copmte des Globules Rouges' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='g/dl') , 'Hemoglobine(Sang)' , 'Hb' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Hemoglobine' ,30 , 'Hemoglobine' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='%') , 'Hematocrite(Sang)' , 'Ht' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Hematocrite' ,40 , 'Hematocrite' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='fl') , 'VGM(Sang)' , 'VGM' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'VGM' ,50 , 'VGM' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='pg') , 'TCMH(Sang)' , 'TCMH' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'TCMH' ,60 , 'TCMH' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='%') , 'CCMH(Sang)' , 'CCMH' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'CCMH' ,70 , 'CCMH' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mm3') , 'Plaquettes(Sang)' , 'Plaquettes' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Plaquettes' ,80 , 'Plaquettes' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='%') , 'Neutrophiles(Sang)' , 'Neutrophiles' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Neutrophiles' ,90 , 'Neutrophiles' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='%') , 'Lymphocytes(Sang)' , 'Lymphocytes' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Lymphocytes' ,100 , 'Lymphocytes' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='%') , 'Mixtes(Sang)' , 'Mixtes' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Mixtes' ,110 , 'Mixtes' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='%') , 'Monocytes(Sang)' , 'Monocytes' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Monocytes' ,120 , 'Monocytes' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='%') , 'Eosinophiles(Sang)' , 'Eosinophiles' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Eosinophiles' ,130 , 'Eosinophiles' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='%') , 'Basophiles(Sang)' , 'Basophiles' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Basophiles' ,140 , 'Basophiles' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mm/heure') , 'Vitesse de Sedimentation(Sang)' , 'Sedimentation' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Sedimentation' ,150 , 'Vitesse de Sedimentation' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mn') , 'Temps de Coagulation en tube(Sang)' , 'Coag en tube' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Temps de Coagulation' ,160 , 'Temps de Coagulation en tube' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mn') , 'Temps de Coagulation(Sang)' , 'Temps de Coag' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Temps de Coag' ,170 , 'Temps de Coagulation' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mn') , 'temps de saignement(Sang)' , 'Temps Saignement' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'temps de saignement' ,180 , 'temps de saignement' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='%') , $$Electrophorese de l'hemoglobine(Sang)$$ , 'Electrophor Hb' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,$$Electrophorese de l'$$ ,190 , $$Electrophorese de l'hemoglobine$$ );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Sickling Test(Sang)' , 'Sickling Test' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Sickling Test' ,200 , 'Sickling Test' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='%') , 'Taux reticulocytes - Auto(Sang)' , 'Reticulo-Auto' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Taux reticulocytes -' ,210 , 'Taux reticulocytes - Auto' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='%') , 'Taux reticulocytes - Manual(Sang)' , 'Reticulo-Manu' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Taux reticulocytes -' ,220 , 'Taux reticulocytes - Manual' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='Secondes') , 'Temps de cephaline Activé(TCA)(Plasma)' , 'PTT' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Temps de cephaline A' ,230 , 'Temps de cephaline Activé(TCA)' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Fibrinogene(Plasma)' , 'Fibrinogene' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Fibrinogene' ,240 , 'Fibrinogene' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Facteur VIII(Plasma)' , 'Facteur VIII' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Facteur VIII' ,250 , 'Facteur VIII' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='%') , 'Facteur IX(Plasma)' , 'Facteur IX' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Facteur IX' ,260 , 'Facteur IX' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='UI/ml') , 'Heparinemie(Plasma)' , 'Heparinemie' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Heparinemie' ,270 , 'Heparinemie' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Anti-Thrombine III (Dosage)(Plasma)' , 'AT III Dosage' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Anti-Thrombine III (' ,280 , 'Anti-Thrombine III (Dosage)' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='g/l  %') , 'Anti-Thrombine III (Activite)(Plasma)' , 'AT III Active' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Anti-Thrombine III (' ,290 , 'Anti-Thrombine III (Activite)' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Groupe Sanguin - ABO(Sang)' , 'Groupe ABO' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Groupe Sanguin - ABO' ,300 , 'Groupe Sanguin - ABO' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Groupe Sanguin - Rhesus(Sang)' , 'Groupe Rhesus' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Groupe Sanguin - Rhe' ,310 , 'Groupe Sanguin - Rhesus' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Coombs Test Direct(Sang)' , 'Coombs Direct' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Coombs Test Direct' ,320 , 'Coombs Test Direct' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Coombs Test Direct(Serum)' , 'Coombs Direct' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Coombs Test Direct' ,330 , 'Coombs Test Direct' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Coombs Test Indirect(Sang)' , 'Coombs Indirect' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Coombs Test Indirect' ,340 , 'Coombs Test Indirect' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Coombs Test Indirect(Serum)' , 'Coombs Indirect' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Coombs Test Indirect' ,350 , 'Coombs Test Indirect' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , $$Azote de l'Uree(Serum)$$ , $$Azote de l'Uree$$ , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,$$Azote de l'Uree$$ ,360 , $$Azote de l'Uree$$ );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Uree(Serum)' , 'Uree' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Uree' ,370 , 'Uree' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'creatinine(Serum)' , 'creatinine' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'creatinine' ,380 , 'creatinine' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Glycemie(Serum)' , 'Glycemie' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Glycemie' ,390 , 'Glycemie' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='g/l') , 'Glucose(LCR/CSF)' , 'Glucose' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Glucose' ,400 , 'Glucose' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='g/l') , 'Proteines(LCR/CSF)' , 'Proteines' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Proteines' ,410 , 'Proteines' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='g/l') , 'Chlore(LCR/CSF)' , 'Chlore' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Chlore' ,420 , 'Chlore' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='μg/l') , 'Alpha-foetoproteine(Liquide Amniotique)' , 'α-foetoprotiene' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Alpha-foetoproteine' ,430 , 'Alpha-foetoproteine' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='g/l') , 'dosage des phospholipides(Liquide Amniotique)' , 'PhosLipDosage' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'dosage des phospholi' ,440 , 'dosage des phospholipides' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/l') , 'creatinine(Liquide Amniotique)' , 'creatinine' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'creatinine' ,450 , 'creatinine' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='g/100ml') , 'Proteines(Liquide Ascite)' , 'Proteines' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Proteines' ,460 , 'Proteines' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Albumine(Liquide Ascite)' , 'Albumine' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Albumine' ,470 , 'Albumine' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/100mol') , 'glucose(Liquide Synovial)' , 'glucose' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'glucose' ,480 , 'glucose' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='g/l') , 'Uree(Liquide Synovial)' , 'Uree' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Uree' ,490 , 'Uree' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Acide urique(Liquide Synovial)' , 'Acide urique' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Acide urique' ,500 , 'Acide urique' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='g/l') , 'proteine total(Liquide Synovial)' , 'proteine total' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'proteine total' ,510 , 'proteine total' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'glycemie(Plasma)' , 'glycemie' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'glycemie' ,520 , 'glycemie' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='g/l') , 'Albumine(Serum)' , 'Albumine' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Albumine' ,530 , 'Albumine' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='g/l') , 'α1 globuline(Serum)' , 'α1 globuline' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'α1 globuline' ,540 , 'α1 globuline' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='g/l') , 'α2 globuline(Serum)' , 'α2 globuline' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'α2 globuline' ,550 , 'α2 globuline' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='g/l') , 'β globuline(Serum)' , 'β globuline' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'β globuline' ,560 , 'β globuline' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='g/l') , 'ϒ globuline(Serum)' , 'ϒ globuline' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'ϒ globuline' ,570 , 'ϒ globuline' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='UI/l') , 'Phosphatase Acide(Serum)' , 'Phosphatase Acid' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Phosphatase Acide' ,580 , 'Phosphatase Acide' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mmol/l 24h ') , 'Chlorures(Plasma hepariné)' , 'Chlorures' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Chlorures' ,590 , 'Chlorures' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mmol/l 24h') , 'Chlorures(LCR/CSF)' , 'Chlorures' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Chlorures' ,600 , 'Chlorures' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mmol/l 24h') , 'Chlorures(Urines/24 heures)' , 'Chlorures' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Chlorures' ,610 , 'Chlorures' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='UI/l a 30°C') , 'CPK(Serum)' , 'CPK' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'CPK' ,620 , 'CPK' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='UI/L') , 'Amylase(Serum)' , 'Amylase' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Amylase' ,630 , 'Amylase' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='U/I') , 'Lipase(Serum)' , 'Lipase' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Lipase' ,640 , 'Lipase' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mEq/l') , 'Chlorures(Serum)' , 'Chlorures' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Chlorures' ,650 , 'Chlorures' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mEq/l') , 'Sodium(Serum)' , 'Sodium' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Sodium' ,660 , 'Sodium' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mEq/l') , 'Potassium(Serum)' , 'Potassium' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Potassium' ,670 , 'Potassium' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mEq/l') , 'Bicarbonates(Serum)' , 'Bicarbonates' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Bicarbonates' ,680 , 'Bicarbonates' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/l') , 'Calcium(Serum)' , 'Calcium' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Calcium' ,690 , 'Calcium' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'magnésium(Serum)' , 'magnésium' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'magnésium' ,700 , 'magnésium' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'phosphore(Serum)' , 'phosphore' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'phosphore' ,710 , 'phosphore' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='millimol/L') , 'Lithium(Serum)' , 'Lithium' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Lithium' ,720 , 'Lithium' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='nmol/L') , 'Fer Serique(Serum)' , 'Fer Serique' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Fer Serique' ,730 , 'Fer Serique' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Bilirubine totale(Serum)' , 'BiliTotale' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Bilirubine totale' ,740 , 'Bilirubine totale' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Bilirubine directe(Serum)' , 'BiliDirect' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Bilirubine directe' ,750 , 'Bilirubine directe' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Bilirubine indirecte(Serum)' , 'BiliIndirect' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Bilirubine indirecte' ,760 , 'Bilirubine indirecte' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='UI/L') , 'SGOT(Serum)' , 'SGOT' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'SGOT' ,770 , 'SGOT' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='UI/L') , 'SGPT(Serum)' , 'SGPT' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'SGPT' ,780 , 'SGPT' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='UI/L') , 'Phosphatase Alcaline(Serum)' , 'Phosphatase Alka' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Phosphatase Alcaline' ,790 , 'Phosphatase Alcaline' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Cholesterol Total(Serum)' , 'CholesterolTot' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Cholesterol Total' ,800 , 'Cholesterol Total' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Triglyceride(Serum)' , 'Triglyceride' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Triglyceride' ,810 , 'Triglyceride' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Lipide(Serum)' , 'Lipide' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Lipide' ,820 , 'Lipide' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'HDL(Serum)' , 'HDL' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'HDL' ,830 , 'HDL' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'LDL(Serum)' , 'LDL' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'LDL' ,840 , 'LDL' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'VLDL(Serum)' , 'VLDL' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'VLDL' ,850 , 'VLDL' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'MBG(Serum)' , 'MBG' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'MBG' ,860 , 'MBG' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='%AIC') , 'Hémoglobine glyqué(Serum)' , 'HbGlyque' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Hémoglobine glyqué' ,870 , 'Hémoglobine glyqué' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='N') , 'Ck-mB(Serum)' , 'Ck-mb' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Ck-mB' ,880 , 'Ck-mB' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='u/l') , 'Ck total(Serum)' , 'Ck total' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Ck total' ,890 , 'Ck total' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='UI/l') , 'ASAT 30° C(Serum)' , 'ASAT 30° C' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'ASAT 30° C' ,900 , 'ASAT 30° C' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='UI/l') , 'ASAT 37° C(Serum)' , 'ASAT 37° C' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'ASAT 37° C' ,910 , 'ASAT 37° C' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='UI/l') , 'ALAT 30° C(Serum)' , 'ALAT 30° C' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'ALAT 30° C' ,920 , 'ALAT 30° C' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='UI/l') , 'ALAT 37° C(Serum)' , 'ALAT 37° C' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'ALAT 37° C' ,930 , 'ALAT 37° C' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'LDH(Serum)' , 'LDH' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'LDH' ,940 , 'LDH' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='%') , 'Troponine 1(Serum)' , 'Troponine 1' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Troponine 1' ,950 , 'Troponine 1' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Glycémie provoquée(Serum)' , 'GPP' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Glycémie provoquée' ,960 , 'Glycémie provoquée' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Ph(Serum)' , 'Ph' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'Ph' ,970 , 'Ph' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mm Hg') , 'PaCO2(Serum)' , 'PaCO2' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'PaCO2' ,980 , 'PaCO2' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mEq/L') , 'HCO3(Serum)' , 'HCO3' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'HCO3' ,990 , 'HCO3' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='%') , 'O2 Saturation(Serum)' , 'O2 Saturation' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'O2 Saturation' ,1000 , 'O2 Saturation' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mm Hg') , 'PaO2(Serum)' , 'PaO2' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'PaO2' ,1010 , 'PaO2' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mmol/L') , 'BE(Serum)' , 'BE' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biochemistry' ) ,'BE' ,1020 , 'BE' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Bacteries(Secretion vaginale)' , 'Bacteries' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Bacteries' ,1030 , 'Bacteries' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Levures Simples(Secretion vaginale)' , 'Levures Simples' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Levures Simples' ,1040 , 'Levures Simples' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Levures Bourgeonantes(Secretion vaginale)' , 'Lev Bourg' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Levures Bourgeonante' ,1050 , 'Levures Bourgeonantes' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Trichomonas vaginalis(Secretion vaginale)' , 'Tricho Vag' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Trichomonas vaginali' ,1060 , 'Trichomonas vaginalis' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Globules Blancs(Secretion vaginale)' , 'Leucocytes' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Globules Blancs' ,1070 , 'Globules Blancs' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Globules Rouges(Secretion vaginale)' , 'Hematies' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Globules Rouges' ,1080 , 'Globules Rouges' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Filaments Myceliens(Secretion vaginale)' , 'Mycelium' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Filaments Myceliens' ,1090 , 'Filaments Myceliens' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Cellules Epitheliales(Secretion vaginale)' , 'Cell Epith' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Cellules Epitheliale' ,1100 , 'Cellules Epitheliales' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Bacteries(Secretion Urethrale)' , 'Bacteries' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Bacteries' ,1110 , 'Bacteries' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Levures Simples(Secretion Urethrale)' , 'Levures Simples' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Levures Simples' ,1120 , 'Levures Simples' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Levures Bourgeonantes(Secretion Urethrale)' , 'Lev Bourg' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Levures Bourgeonante' ,1130 , 'Levures Bourgeonantes' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Trichomonas vaginalis(Secretion Urethrale)' , 'Tricho Vag' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Trichomonas vaginali' ,1140 , 'Trichomonas vaginalis' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Globules Blancs(Secretion Urethrale)' , 'Leucocytes' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Globules Blancs' ,1150 , 'Globules Blancs' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Globules Rouges(Secretion Urethrale)' , 'Hematies' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Globules Rouges' ,1160 , 'Globules Rouges' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Filaments Myceliens(Secretion Urethrale)' , 'Mycelium' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Filaments Myceliens' ,1170 , 'Filaments Myceliens' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Cellules Epitheliales(Secretion Urethrale)' , 'Cell Epith' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Cellules Epitheliale' ,1180 , 'Cellules Epitheliales' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'KOH(Secretion vaginale)' , 'KOH' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'KOH' ,1190 , 'KOH' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Test de Rivalta(Secretion vaginale)' , 'Test de Rivalta' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Test de Rivalta' ,1200 , 'Test de Rivalta' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Bacilles Gram+(Secretion vaginale)' , 'Bacilles Gram+' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Bacilles Gram+' ,1210 , 'Bacilles Gram+' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Bacilles Gram-(Secretion vaginale)' , 'Bacilles Gram-' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Bacilles Gram-' ,1220 , 'Bacilles Gram-' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Cocci Gram+(Secretion vaginale)' , 'Cocci Gram+' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Cocci Gram+' ,1230 , 'Cocci Gram+' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Cocci Gram-(Secretion vaginale)' , 'Cocci Gram-' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Cocci Gram-' ,1240 , 'Cocci Gram-' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Cocobacilles Gram -(Secretion vaginale)' , 'CoccoBac Gram-' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Cocobacilles Gram -' ,1250 , 'Cocobacilles Gram -' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Cellules epitheliales cloutees(Secretion vaginale)' , 'Clue Cells' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Cellules epitheliale' ,1260 , 'Cellules epitheliales cloutees' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Diplocoque Gram (-) Intra cellulaire(Secretion vaginale)' , 'IntraDiploGram-' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Diplocoque Gram (-) ' ,1270 , 'Diplocoque Gram (-) Intra cellulaire' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Diplocoque Gram (-) Extra cellulaire(Secretion vaginale)' , 'ExtraDiploGram-' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Diplocoque Gram (-) ' ,1280 , 'Diplocoque Gram (-) Extra cellulaire' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Bacilles Gram+(Secretion Urethrale)' , 'Bacilles Gram+' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Bacilles Gram+' ,1290 , 'Bacilles Gram+' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Bacilles Gram-(Secretion Urethrale)' , 'Bacilles Gram-' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Bacilles Gram-' ,1300 , 'Bacilles Gram-' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Cocci Gram+(Secretion Urethrale)' , 'Cocci Gram+' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Cocci Gram+' ,1310 , 'Cocci Gram+' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Cocci Gram-(Secretion Urethrale)' , 'Cocci Gram-' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Cocci Gram-' ,1320 , 'Cocci Gram-' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Cocobacilles Gram -(Secretion Urethrale)' , 'Cocobact Gram-' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Cocobacilles Gram -' ,1330 , 'Cocobacilles Gram -' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Cellules epitheliales cloutees(Secretion Urethrale)' , 'Clue Cells' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Cellules epitheliale' ,1340 , 'Cellules epitheliales cloutees' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Diplocoque Gram (-) Intra cellulaire(Secretion Urethrale)' , 'IntraDiploGram-' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Diplocoque Gram (-) ' ,1350 , 'Diplocoque Gram (-) Intra cellulaire' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Diplocoque Gram (-) Extra cellulaire(Secretion Urethrale)' , 'ExtraDiploGram-' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Diplocoque Gram (-) ' ,1360 , 'Diplocoque Gram (-) Extra cellulaire' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Frottis Vaginal/Gram(Secretion vaginale)' , 'Frot VagGram' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Frottis Vaginal/Gram' ,1370 , 'Frottis Vaginal/Gram' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Frottis Uretral/Gram(Secretion Urethrale)' , 'Frot Ureth Gram' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Frottis Uretral/Gram' ,1380 , 'Frottis Uretral/Gram' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Cholera Test rapide(Selles)' , 'Cholera Rapide' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Cholera Test rapide' ,1390 , 'Cholera Test rapide' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Coproculture(Selles)' , 'Coproculture' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Coproculture' ,1400 , 'Coproculture' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Couleur(Liquide Spermatique)' , 'Couleur' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Couleur' ,1410 , 'Couleur' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Liquefaction(Liquide Spermatique)' , 'Liquefaction' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Liquefaction' ,1420 , 'Liquefaction' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'pH(Liquide Spermatique)' , 'pH' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'pH' ,1430 , 'pH' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mg/dl') , 'Fructose(Liquide Spermatique)' , 'Fructose' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Fructose' ,1440 , 'Fructose' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='ml') , 'Volume(Liquide Spermatique)' , 'Volume' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Volume' ,1450 , 'Volume' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='millions/ml') , 'Compte de spermes(Liquide Spermatique)' , 'Compte Spermes' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Compte de spermes' ,1460 , 'Compte de spermes' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='%') , 'Formes normales(Liquide Spermatique)' , 'Formes normales' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Formes normales' ,1470 , 'Formes normales' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Formes anormales(Liquide Spermatique)' , 'Formes anormales' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Formes anormales' ,1480 , 'Formes anormales' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='%') , 'Motilite STAT(Liquide Spermatique)' , 'Motilite STAT' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Motilite STAT' ,1490 , 'Motilite STAT' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='%') , 'Motilite 1 heure(Liquide Spermatique)' , 'Motilite/heure' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Motilite 1 heure' ,1500 , 'Motilite 1 heure' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='%') , 'Motilite 3 heures(Liquide Spermatique)' , 'Motilite/3heures' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Motilite 3 heures' ,1510 , 'Motilite 3 heures' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Gram(Liquide Spermatique)' , 'Gram' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Cytobacteriologie' ) ,'Gram' ,1520 , 'Gram' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Couleur(Urines)' , 'Couleur' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'ECBU' ) ,'Couleur' ,1530 , 'Couleur' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Aspect(Urines)' , 'Aspect' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'ECBU' ) ,'Aspect' ,1540 , 'Aspect' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Densite(Urines)' , 'Densite' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'ECBU' ) ,'Densite' ,1550 , 'Densite' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Ph(Urines)' , 'Ph' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'ECBU' ) ,'Ph' ,1560 , 'Ph' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Proteines(Urines)' , 'Proteines' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'ECBU' ) ,'Proteines' ,1570 , 'Proteines' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Glucose(Urines)' , 'Glucose' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'ECBU' ) ,'Glucose' ,1580 , 'Glucose' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Cetones(Urines)' , 'Cetones' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'ECBU' ) ,'Cetones' ,1590 , 'Cetones' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Bilirubine(Urines)' , 'Bilirubine' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'ECBU' ) ,'Bilirubine' ,1600 , 'Bilirubine' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Sang(Urines)' , 'Sang' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'ECBU' ) ,'Sang' ,1610 , 'Sang' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Acide ascorbique(Urines)' , 'Acide ascorbique' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'ECBU' ) ,'Acide ascorbique' ,1620 , 'Acide ascorbique' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Urobilinogene(Urines)' , 'Urobilinogene' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'ECBU' ) ,'Urobilinogene' ,1630 , 'Urobilinogene' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Nitrites(Urines)' , 'Nitrites' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'ECBU' ) ,'Nitrites' ,1640 , 'Nitrites' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Hematies(Urines)' , 'Hematies' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'ECBU' ) ,'Hematies' ,1650 , 'Hematies' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Leucocytes(Urines)' , 'Leucocytes' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'ECBU' ) ,'Leucocytes' ,1660 , 'Leucocytes' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'cellules epitheliales(Urines)' , 'Cell Epith' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'ECBU' ) ,'cellules epitheliale' ,1670 , 'cellules epitheliales' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Bacteries(Urines)' , 'Bacteries' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'ECBU' ) ,'Bacteries' ,1680 , 'Bacteries' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Levures(Urines)' , 'Levures' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'ECBU' ) ,'Levures' ,1690 , 'Levures' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'filaments myceliens(Urines)' , 'Mycelium' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'ECBU' ) ,'filaments myceliens' ,1700 , 'filaments myceliens' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'spores(Urines)' , 'spores' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'ECBU' ) ,'spores' ,1710 , 'spores' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'trichomonas(Urines)' , 'trichomonas' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'ECBU' ) ,'trichomonas' ,1720 , 'trichomonas' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Cylindres(Urines)' , 'Cylindres' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'ECBU' ) ,'Cylindres' ,1730 , 'Cylindres' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Cristaux(Urines)' , 'Cristaux' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'ECBU' ) ,'Cristaux' ,1740 , 'Cristaux' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Couleur(Selles)' , 'Couleur' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Parasitology' ) ,'Couleur' ,1750 , 'Couleur' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Aspect(Selles)' , 'Aspect' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Parasitology' ) ,'Aspect' ,1760 , 'Aspect' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Sang Occulte(Selles)' , 'Sang Occulte' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Parasitology' ) ,'Sang Occulte' ,1770 , 'Sang Occulte' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Bacteries(Selles)' , 'Bacteries' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Parasitology' ) ,'Bacteries' ,1780 , 'Bacteries' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'cellules vegetales(Selles)' , 'Cell Vegetales' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Parasitology' ) ,'cellules vegetales' ,1790 , 'cellules vegetales' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Protozoaires(Selles)' , 'Protozoaires' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Parasitology' ) ,'Protozoaires' ,1800 , 'Protozoaires' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Metazoaires(Selles)' , 'Metazoaires' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Parasitology' ) ,'Metazoaires' ,1810 , 'Metazoaires' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Recherche de cryptosporidium et Oocyste(Selles)' , 'Crypto Zielh' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Parasitology' ) ,'Recherche de cryptos' ,1820 , 'Recherche de cryptosporidium et Oocyste' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Recheche de microfilaire(Sang)' , 'Microfiliares' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Parasitology' ) ,'Recheche de microfil' ,1830 , 'Recheche de microfilaire' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Recherche de plasmodiun - Especes(Sang)' , 'Plasmodiun Esp' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Parasitology' ) ,'Recherche de plasmod' ,1840 , 'Recherche de plasmodiun - Especes' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Recherche de plasmodiun - Trophozoit(Sang)' , 'Plasmodiun Troph' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Parasitology' ) ,'Recherche de plasmod' ,1850 , 'Recherche de plasmodiun - Trophozoit' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Recherche de plasmodiun - Gametocyte(Sang)' , 'Plasmodiun Game' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Parasitology' ) ,'Recherche de plasmod' ,1860 , 'Recherche de plasmodiun - Gametocyte' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Recherche de plasmodiun - Schizonte(Sang)' , 'Plasmodium Sch' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Parasitology' ) ,'Recherche de plasmod' ,1870 , 'Recherche de plasmodiun - Schizonte' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mm3') , 'CD4 en mm3(Sang)' , 'CD4 en mm3' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'CD4 en mm3' ,1880 , 'CD4 en mm3' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='%') , 'CD4 en %(Sang)' , 'CD4 en %' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'CD4 en %' ,1890 , 'CD4 en %' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='UI/ML') , 'CMV Ig G(Serum)' , 'CMV Ig G' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'CMV Ig G' ,1900 , 'CMV Ig G' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='UI/ML') , 'CMV Ig M(Serum)' , 'CMV Ig M' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'CMV Ig M' ,1910 , 'CMV Ig M' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='UI/ML') , 'RUBELLA Ig G(Serum)' , 'RUBELLA Ig G' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'RUBELLA Ig G' ,1920 , 'RUBELLA Ig G' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='UI/ML') , 'RUBELLA Ig M(Serum)' , 'RUBELLA Ig M' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'RUBELLA Ig M' ,1930 , 'RUBELLA Ig M' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'HERPESTYPE I   Ig G(Serum)' , 'Herpes I lg G' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'HERPESTYPE I   Ig G' ,1940 , 'HERPESTYPE I   Ig G' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'HERPESTYPE II Ig M(Serum)' , 'Herpes II lg M' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'HERPESTYPE II Ig M' ,1950 , 'HERPESTYPE II Ig M' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'HIV test rapide(Serum)' , 'HIV test rapide' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'HIV test rapide' ,1960 , 'HIV test rapide' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'HIV test rapide(Plasma)' , 'HIV test rapide' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'HIV test rapide' ,1970 , 'HIV test rapide' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'HIV test rapide(Sang)' , 'HIV test rapide' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'HIV test rapide' ,1980 , 'HIV test rapide' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'HIV Elisa(Serum)' , 'HIV Elisa' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'HIV Elisa' ,1990 , 'HIV Elisa' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'HIV Elisa(Plasma)' , 'HIV Elisa' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'HIV Elisa' ,2000 , 'HIV Elisa' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'HIV Elisa(Sang)' , 'HIV Elisa' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'HIV Elisa' ,2010 , 'HIV Elisa' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'HBsAg - Hépatite B(Sang)' , 'HBsAg - IgG' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'HBsAg - Hépatite B' ,2020 , 'HBsAg - Hépatite B' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'HBsAg - Hépatite B(Plasma)' , 'HBsAg - IgG' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'HBsAg - Hépatite B' ,2030 , 'HBsAg - Hépatite B' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'HBsAg - Hépatite B(Serum)' , 'HBsAg - IgG' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'HBsAg - Hépatite B' ,2040 , 'HBsAg - Hépatite B' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'HCV - Hépatite C(Sang)' , 'HCV - Hépatite C' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'HCV - Hépatite C' ,2050 , 'HCV - Hépatite C' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'HCV - Hépatite C(Plasma)' , 'HCV - Hépatite C' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'HCV - Hépatite C' ,2060 , 'HCV - Hépatite C' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'HCV - Hépatite C(Serum)' , 'HCV - Hépatite C' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'HCV - Hépatite C' ,2070 , 'HCV - Hépatite C' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Dengue(Serum)' , 'Dengue' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Dengue' ,2080 , 'Dengue' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Dengue(Plasma)' , 'Dengue' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Dengue' ,2090 , 'Dengue' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Dengue(Sang)' , 'Dengue' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Dengue' ,2100 , 'Dengue' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Toxoplasmose(Serum)' , 'Toxoplasmose' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Toxoplasmose' ,2110 , 'Toxoplasmose' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Toxoplasmose(Plasma)' , 'Toxoplasmose' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Toxoplasmose' ,2120 , 'Toxoplasmose' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Toxoplasmose(Sang)' , 'Toxoplasmose' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Toxoplasmose' ,2130 , 'Toxoplasmose' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Rubeole(Serum)' , 'Rubeole' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Rubeole' ,2140 , 'Rubeole' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Rubeole(Plasma)' , 'Rubeole' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Rubeole' ,2150 , 'Rubeole' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Rubeole(Sang)' , 'Rubeole' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Rubeole' ,2160 , 'Rubeole' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'HIV Western Blot(Serum)' , 'HIV Western Blot' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'HIV Western Blot' ,2170 , 'HIV Western Blot' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'HIV Western Blot(Plasma)' , 'HIV Western Blot' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'HIV Western Blot' ,2180 , 'HIV Western Blot' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'HIV Western Blot(Sang)' , 'HIV Western Blot' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'HIV Western Blot' ,2190 , 'HIV Western Blot' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Hépatite B - HBsAg IGG(Serum)' , 'HBsAg IGG' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Hépatite B - HBsAg ' ,2200 , 'Hépatite B - HBsAg IGG' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Hépatite B - HBsAg IGG(Plasma)' , 'HBsAg IGG' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Hépatite B - HBsAg ' ,2210 , 'Hépatite B - HBsAg IGG' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Hépatite B - HBsAg IGG(Sang)' , 'HBsAg IGG' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Hépatite B - HBsAg ' ,2220 , 'Hépatite B - HBsAg IGG' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Hépatite B - HBV :AcHbC,AcHbe(Serum)' , 'HBV AcHbC AcHbe' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Hépatite B - HBV :A' ,2230 , 'Hépatite B - HBV :AcHbC,AcHbe' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Hépatite B - HBV :AcHbC,AcHbe(Plasma)' , 'HBV AcHbC AcHbe' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Hépatite B - HBV :A' ,2240 , 'Hépatite B - HBV :AcHbC,AcHbe' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Hépatite B - HBV :AcHbC,AcHbe(Sang)' , 'HBV AcHbC AcHbe' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Hépatite B - HBV :A' ,2250 , 'Hépatite B - HBV :AcHbC,AcHbe' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Hépatite C - HCV IGG(Serum)' , 'HCV IGG' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Hépatite C - HCV IG' ,2260 , 'Hépatite C - HCV IGG' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Hépatite C - HCV IGG(Plasma)' , 'HCV IGG' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Hépatite C - HCV IG' ,2270 , 'Hépatite C - HCV IGG' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Hépatite C - HCV IGG(Sang)' , 'HCV IGG' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Hépatite C - HCV IG' ,2280 , 'Hépatite C - HCV IGG' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Hépatite B - HCV IGM(Serum)' , 'HCV IGM' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Hépatite B - HCV IG' ,2290 , 'Hépatite B - HCV IGM' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Hépatite B - HCV IGM(Plasma)' , 'HCV IGM' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Hépatite B - HCV IG' ,2300 , 'Hépatite B - HCV IGM' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Hépatite B - HCV IGM(Sang)' , 'HCV IGM' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'Hépatite B - HCV IG' ,2310 , 'Hépatite B - HCV IGM' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'P24(Serum)' , 'P24' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Immunology' ) ,'P24' ,2320 , 'P24' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Charge Virale(Plasma)' , 'Charge Virale' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biologie Moleculaire' ) ,'Charge Virale' ,2330 , 'Charge Virale' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'ADN VIH-1(DBS)' , 'ADN VIH-1' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biologie Moleculaire' ) ,'ADN VIH-1' ,2340 , 'ADN VIH-1' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'PCR Resistance TB(Expectoration)' , 'PCR MDR TB' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biologie Moleculaire' ) ,'PCR Resistance TB' ,2350 , 'PCR Resistance TB' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Culture(LCR/CSF)' , 'Culture' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'Culture' ,2360 , 'Culture' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Antibiogramme(LCR/CSF)' , 'Antibiogramme' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'Antibiogramme' ,2370 , 'Antibiogramme' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'recherche des bacteries(Liquide Pleural)' , 'Bacteries' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'recherche des bacter' ,2380 , 'recherche des bacteries' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'pneumocoques(Liquide Pleural)' , 'pneumocoques' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'pneumocoques' ,2390 , 'pneumocoques' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'streptocoques(Liquide Pleural)' , 'streptocoques' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'streptocoques' ,2400 , 'streptocoques' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'staphylocoques(Liquide Pleural)' , 'staphylocoques' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'staphylocoques' ,2410 , 'staphylocoques' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Recherche de BAAR Auramine(Liquide Pleural)' , 'BAAR Auramine' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'Recherche de BAAR Au' ,2420 , 'Recherche de BAAR Auramine' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Recherche de BAAR Zielh Neelsen(Liquide Pleural)' , 'BAAR Zielh' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'Recherche de BAAR Zi' ,2430 , 'Recherche de BAAR Zielh Neelsen' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'culture BK(Liquide Pleural)' , 'culture BK' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'culture BK' ,2440 , 'culture BK' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Antibiogramme(Liquide Pleural)' , 'Antibiogramme' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'Antibiogramme' ,2450 , 'Antibiogramme' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'culture(Liquide Synovial)' , 'culture' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'culture' ,2460 , 'culture' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , $$cristaux d'urates(Liquide Synovial)$$ , 'Cristal Urate' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,$$cristaux d'urates$$ ,2470 , $$cristaux d'urates$$ );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'cristaux de cholesterol(Liquide Synovial)' , 'Cristal Cholest' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'cristaux de choleste' ,2480 , 'cristaux de cholesterol' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Examen direct(Pus)' , 'Examen direct' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'Examen direct' ,2490 , 'Examen direct' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Culture(Pus)' , 'Culture' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'Culture' ,2500 , 'Culture' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Antibiogramme(Pus)' , 'Antibiogramme' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'Antibiogramme' ,2510 , 'Antibiogramme' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Liquide Ascite Recherche des Lymphocytes(Liquide Ascite)' , 'LymphoAscites' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'Liquide Ascite Reche' ,2520 , 'Liquide Ascite Recherche des Lymphocytes' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Liquide Ascite GRAM(Liquide Ascite)' , 'GRAM Ascites' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'Liquide Ascite GRAM' ,2530 , 'Liquide Ascite GRAM' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Liquide Ascite ZIELH NIELSEN(Liquide Ascite)' , 'ZIELH Ascites' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'Liquide Ascite ZIELH' ,2540 , 'Liquide Ascite ZIELH NIELSEN' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Recherche des Lymphocytes(Liquide Pleural)' , 'LymphoPleural' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'Recherche des Lympho' ,2550 , 'Recherche des Lymphocytes' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Eosinophile(Liquide Pleural)' , 'Eosinophile' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'Eosinophile' ,2560 , 'Eosinophile' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'cellules atypiques/polynucleaires(Liquide Pleural)' , 'Atypique Cell' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'cellules atypiques/p' ,2570 , 'cellules atypiques/polynucleaires' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='/μl') , 'globules rouges(Liquide Pleural)' , 'globules rouges' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'globules rouges' ,2580 , 'globules rouges' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'GRAM(Liquide Pleural)' , 'GRAM' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'GRAM' ,2590 , 'GRAM' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'ZIELH NIELSEN(Liquide Pleural)' , 'ZIELH NIELSEN' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'ZIELH NIELSEN' ,2600 , 'ZIELH NIELSEN' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mm3') , 'Cellules polynucleaires(Liquide Synovial)' , 'Polynuclear Cell' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'Cellules polynucleai' ,2610 , 'Cellules polynucleaires' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='cells/mm3') , 'leucocytes(Liquide Synovial)' , 'leucocytes' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'leucocytes' ,2620 , 'leucocytes' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mm3') , 'Numeration des globules blancs(LCR/CSF)' , 'leucocytes' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'Numeration des globu' ,2630 , 'Numeration des globules blancs' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'LCR GRAM(LCR/CSF)' , 'LCR GRAM' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'LCR GRAM' ,2640 , 'LCR GRAM' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'LCR ZIELH NIELSEN(LCR/CSF)' , 'LCR ZIELH' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Liquides biologique' ) ,'LCR ZIELH NIELSEN' ,2650 , 'LCR ZIELH NIELSEN' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Recherche de BAAR(Expectoration)' , 'ExpectoZIELH' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Mycrobacteriology' ) ,'Recherche de BAAR' ,2660 , 'Recherche de BAAR' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Recherche de BK Auramine(Expectoration)' , 'ExpectoAURAMINE' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Mycrobacteriology' ) ,'Recherche de BK Aura' ,2670 , 'Recherche de BK Auramine' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Culture de M. tuberculosis(Expectoration)' , 'Culture BK' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Mycrobacteriology' ) ,'Culture de M. tuberc' ,2680 , 'Culture de M. tuberculosis' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='μg/l') , 'Prolactine(Serum)' , 'Prolactine' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Endocrinologie' ) ,'Prolactine' ,2690 , 'Prolactine' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mUI/ml') , 'FSH(Serum)' , 'FSH' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Endocrinologie' ) ,'FSH' ,2700 , 'FSH' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mUI/ml') , 'FSH(Plasma hepariné)' , 'FSH' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Endocrinologie' ) ,'FSH' ,2710 , 'FSH' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mUI/ml') , 'LH(Serum)' , 'LH' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Endocrinologie' ) ,'LH' ,2720 , 'LH' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='mUI/ml') , 'LH(Plasma hepariné)' , 'LH' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Endocrinologie' ) ,'LH' ,2730 , 'LH' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='nmol/24 h') , 'Oestrogene(Urines/24 heures)' , 'Oestrogene' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Endocrinologie' ) ,'Oestrogene' ,2740 , 'Oestrogene' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='nmol/l') , 'Progesterone(Serum)' , 'Progesterone' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Endocrinologie' ) ,'Progesterone' ,2750 , 'Progesterone' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='nmol/l') , 'T3(Serum)' , 'T3' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Endocrinologie' ) ,'T3' ,2760 , 'T3' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='UI') , 'B-HCG(Serum)' , 'B-HCG' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Endocrinologie' ) ,'B-HCG' ,2770 , 'B-HCG' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'B-HCG(Urine concentré du matin)' , 'B-HCG' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Endocrinologie' ) ,'B-HCG' ,2780 , 'B-HCG' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Test de Grossesse(Urines)' , 'Test Grossesse' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Endocrinologie' ) ,'Test de Grossesse' ,2790 , 'Test de Grossesse' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='nmol/l') , 'T4(Serum)' , 'T4' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Endocrinologie' ) ,'T4' ,2800 , 'T4' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='μU/ml') , 'TSH(Serum)' , 'TSH' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Endocrinologie' ) ,'TSH' ,2810 , 'TSH' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='UI/ML') , 'TOXOPLASMOSE GONDII IgG Ac(Serum)' , 'TOXO IgG' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'TOXOPLASMOSE GONDII ' ,2820 , 'TOXOPLASMOSE GONDII IgG Ac' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='UI/ML') , 'TOXOPLASMOSE GONDII Ig M Ac(Serum)' , 'TOXO IgM' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'TOXOPLASMOSE GONDII ' ,2830 , 'TOXOPLASMOSE GONDII Ig M Ac' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Malaria Test Rapide(Serum)' , 'Malaria Test Rapide' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Malaria Test Rapide' ,2840 , 'Malaria Test Rapide' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Malaria Test Rapide(Plasma)' , 'Malaria Test Rapide' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Malaria Test Rapide' ,2850 , 'Malaria Test Rapide' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Malaria Test Rapide(Sang)' , 'Malaria Test Rapide' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Malaria Test Rapide' ,2860 , 'Malaria Test Rapide' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Syphilis Test Rapide(Serum)' , 'Syphilis Rapide' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Syphilis Test Rapide' ,2870 , 'Syphilis Test Rapide' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Syphilis Test Rapide(Plasma)' , 'Syphilis Rapide' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Syphilis Test Rapide' ,2880 , 'Syphilis Test Rapide' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Syphilis Test Rapide(Sang)' , 'Syphilis Rapide' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Syphilis Test Rapide' ,2890 , 'Syphilis Test Rapide' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'HTLV I et II(Serum)' , 'HTLV I et II' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'HTLV I et II' ,2900 , 'HTLV I et II' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'HTLV I et II(Plasma)' , 'HTLV I et II' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'HTLV I et II' ,2910 , 'HTLV I et II' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'HTLV I et II(Sang)' , 'HTLV I et II' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'HTLV I et II' ,2920 , 'HTLV I et II' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Syphilis RPR(Serum)' , 'Syphilis RPR' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Syphilis RPR' ,2930 , 'Syphilis RPR' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Syphilis RPR(Plasma)' , 'Syphilis RPR' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Syphilis RPR' ,2940 , 'Syphilis RPR' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Syphilis RPR(Sang)' , 'Syphilis RPR' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Syphilis RPR' ,2950 , 'Syphilis RPR' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Syphilis TPHA(Serum)' , 'Syphilis TPHA' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Syphilis TPHA' ,2960 , 'Syphilis TPHA' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Syphilis TPHA(Plasma)' , 'Syphilis TPHA' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Syphilis TPHA' ,2970 , 'Syphilis TPHA' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Syphilis TPHA(Sang)' , 'Syphilis TPHA' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Syphilis TPHA' ,2980 , 'Syphilis TPHA' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Helicobacter Pilori(Serum)' , 'H Pilori' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Helicobacter Pilori' ,2990 , 'Helicobacter Pilori' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Helicobacter Pilori(Plasma)' , 'H Pilori' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Helicobacter Pilori' ,3000 , 'Helicobacter Pilori' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Helicobacter Pilori(Sang)' , 'H Pilori' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Helicobacter Pilori' ,3010 , 'Helicobacter Pilori' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'CRP(Serum)' , 'CRP' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'CRP' ,3020 , 'CRP' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'CRP(Plasma)' , 'CRP' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'CRP' ,3030 , 'CRP' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'CRP(Sang)' , 'CRP' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'CRP' ,3040 , 'CRP' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'ASO(Serum)' , 'ASO' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'ASO' ,3050 , 'ASO' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'ASO(Plasma)' , 'ASO' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'ASO' ,3060 , 'ASO' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'ASO(Sang)' , 'ASO' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'ASO' ,3070 , 'ASO' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Test de Widal Ag O(Serum)' , 'Widal Ag O' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Test de Widal Ag O' ,3080 , 'Test de Widal Ag O' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Test de Widal Ag H(Serum)' , 'Widal Ag H' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Test de Widal Ag H' ,3090 , 'Test de Widal Ag H' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Typhoide Test Rapide(Serum)' , 'Widal Ag O' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serologie' ) ,'Typhoide Test Rapide' ,3100 , 'Typhoide Test Rapide' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Determine(Serum)' , 'Determine' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'VCT' ) ,'Determine' ,3110 , 'Determine' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Determine(Plasma)' , 'Determine' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'VCT' ) ,'Determine' ,3120 , 'Determine' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Determine(Sang)' , 'Determine' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'VCT' ) ,'Determine' ,3130 , 'Determine' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Colloidal Gold / Shangai Kehua(Serum)' , 'Colloidal Gold' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'VCT' ) ,'Colloidal Gold / Sha' ,3140 , 'Colloidal Gold / Shangai Kehua' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Colloidal Gold / Shangai Kehua(Plasma)' , 'Colloidal Gold' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'VCT' ) ,'Colloidal Gold / Sha' ,3150 , 'Colloidal Gold / Shangai Kehua' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Colloidal Gold / Shangai Kehua(Sang)' , 'Colloidal Gold' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'VCT' ) ,'Colloidal Gold / Sha' ,3160 , 'Colloidal Gold / Shangai Kehua' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Syphilis Bioline(Serum)' , 'Syphilis Bioline' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'VCT' ) ,'Syphilis Bioline' ,3170 , 'Syphilis Bioline' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Syphilis Bioline(Plasma)' , 'Syphilis Bioline' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'VCT' ) ,'Syphilis Bioline' ,3180 , 'Syphilis Bioline' );
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Syphilis Bioline(Sang)' , 'Syphilis Bioline' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'VCT' ) ,'Syphilis Bioline' ,3190 , 'Syphilis Bioline' );
