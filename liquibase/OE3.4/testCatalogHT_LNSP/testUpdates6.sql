


INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Chikungunya Test Rapide(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1350, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Chikungunya Test Rapide(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1360, false);

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche Virus Chikungunya(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1350, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche Virus Chikungunya(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1360, false);


--Score d'adherence(Plasma)
	
INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Score d''adherence(Plasma)' , 'c' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biologie Moleculaire' ) ,'Score d''adherence' ,(select sort_order + 3 from clinlims.test where description = 'VIH-1 Charge Virale(Plasma)') , 'Score d''adherence' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Score d''adherence(Plasma)' )  ,    (select id from clinlims.type_of_sample where description = 'Plasma')  );

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Score d''adherence(Plasma)' ) , 'R' , null , now());
--

-- Date de mise sous ARV(Plasma)	
INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Date de mise sous ARV(Plasma)' , 'c' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Biologie Moleculaire' ) ,'Date de mise sous ARV' ,(select sort_order + 6 from clinlims.test where description = 'VIH-1 Charge Virale(Plasma)') , 'Date de mise sous ARV' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Date de mise sous ARV(Plasma)' )  ,    (select id from clinlims.type_of_sample where description = 'Plasma')  );

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Date de mise sous ARV(Plasma)' ) , 'R' , null , now());
--
-- Polio(Selles)	
INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Polio(Selles)' , 'c' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Virologie' ) ,'Polio' ,(select sort_order + 5 from clinlims.test where description = 'SyphilisTest Rapid(Sérum)') , 'Polio' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Polio(Selles)' )  ,    (select id from clinlims.type_of_sample where description = 'Selles')  );
	
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Polio(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 2350, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Polio(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 2360, false);
--

--Mycobacterium tuberculosis(Sputum)
INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Mycobacterium tuberculosis(Sputum)' , 'c' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Mycobacteriology' ) ,'Mycobacterium tuberculosis' ,(select sort_order + 10 from clinlims.test where description = 'Culture de mycobacteries liquide(Sputum)') , 'Mycobacterium tuberculosis' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Mycobacterium tuberculosis(Sputum)' )  ,    (select id from clinlims.type_of_sample where description = 'Sputum')  );
	
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Mycobacterium tuberculosis(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Non-Detecte' )  , now() , 1350, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Mycobacterium tuberculosis(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Detecte' )  , now() , 1360, false);
--

--Resistance a la Rifampicine(Sputum)
INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Resistance a la Rifampicine(Sputum)' , 'c' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Mycobacteriology' ) ,'Resistance a la Rifampicine' ,(select sort_order + 10 from clinlims.test where description = 'Mycobacterium tuberculosis(Sputum)') , 'Resistance a la Rifampicine' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Resistance a la Rifampicine(Sputum)' )  ,    (select id from clinlims.type_of_sample where description = 'Sputum')  );
	
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Resistance a la Rifampicine(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Non-Detecte' )  , now() , 1350, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Resistance a la Rifampicine(Sputum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Detecte' )  , now() , 1360, false);

--

--Globules Blancs(Selles)
INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Globules Blancs(Selles)' , 'c' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Parasitology' ) ,'Globules Blancs' ,(select sort_order + 2 from clinlims.test where description = 'CD4 Compte en %(Sang Total)') , 'Globules Blancs' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Globules Blancs(Selles)' )  ,    (select id from clinlims.type_of_sample where description = 'Selles')  );
	
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Blancs(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Aucun' )  , now() , 1450, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Blancs(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Peu' )  , now() , 1460, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Blancs(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Modere' )  , now() , 1470, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Blancs(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Beaucoup' )  , now() , 1480, false);

--

--Globules Rouges(Selles)
INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Globules Rouges(Selles)' , 'c' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Parasitology' ) ,'Globules Rouges' ,(select sort_order + 4 from clinlims.test where description = 'CD4 Compte en %(Sang Total)') , 'Globules Rouges' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Globules Rouges(Selles)' )  ,    (select id from clinlims.type_of_sample where description = 'Selles')  );
	
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Rouges(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Aucun' )  , now() , 1450, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Rouges(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Peu' )  , now() , 1460, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Rouges(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Modere' )  , now() , 1470, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Rouges(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Beaucoup' )  , now() , 1480, false);

--

--Cellules Vegetales(Selles)
INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Cellules Vegetales(Selles)' , 'c' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Parasitology' ) ,'Cellules Vegetales' ,(select sort_order + 6 from clinlims.test where description = 'CD4 Compte en %(Sang Total)') , 'Cellules Vegetales' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Cellules Vegetales(Selles)' )  ,    (select id from clinlims.type_of_sample where description = 'Selles')  );
	
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules Vegetales(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Aucun' )  , now() , 1450, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules Vegetales(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Peu' )  , now() , 1460, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules Vegetales(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Modere' )  , now() , 1470, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules Vegetales(Selles)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Beaucoup' )  , now() , 1480, false);

--

--Type d''echantillon(Coloration de Gram)

INSERT INTO clinlims.type_of_sample( id, description, domain, lastupdated, local_abbrev, display_key, is_active )
	VALUES ( nextval( 'clinlims.type_of_sample_seq' ) , 'Coloration de Gram','H', now() , 'Gram Stain', 'sample.type.Coloration.de.Gram', 'Y');

INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Type d''echantillon(Coloration de Gram)' , 'c' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,'Type d''echantillon' ,(select sort_order + 2 from clinlims.test where description = 'Immuno Dot Leptospira IgM%(Sérum)') , 'Type d''echantillon' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Type d''echantillon(Coloration de Gram)' )  ,    currval( 'clinlims.type_of_sample_seq' ) );

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Type d''echantillon(Coloration de Gram)' ) , 'R' , null , now());
--


--Resultats(Coloration de Gram)

INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Resultats(Coloration de Gram)' , 'c' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,'Resultats' ,(select sort_order + 4 from clinlims.test where description = 'Immuno Dot Leptospira IgM%(Sérum)') , 'Resultats' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Resultats(Coloration de Gram)' )  ,    currval( 'clinlims.type_of_sample_seq' ) );

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Resultats(Coloration de Gram)' ) , 'R' , null , now());
--


--Type d''echantillon(Culture)

INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Type d''echantillon(Culture)' , 'c' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,'Type d''echantillon' ,(select sort_order + 6 from clinlims.test where description = 'Immuno Dot Leptospira IgM%(Sérum)') , 'Type d''echantillon' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Type d''echantillon(Culture)' )  ,    (select id from clinlims.type_of_sample where description = 'Culture')  );

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Type d''echantillon(Culture)' ) , 'R' , null , now());
--


--Resultats(Culture)

INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Resultats(Culture)' , 'c' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,'Resultats' ,(select sort_order + 8 from clinlims.test where description = 'Immuno Dot Leptospira IgM%(Sérum)') , 'Resultats' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Resultats(Culture)' )  ,    (select id from clinlims.type_of_sample where description = 'Culture')  );

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Resultats(Culture)' ) , 'R' , null , now());
--

--Culture(Ecouvillon Naso-Pharynge)

INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Culture(Ecouvillon Naso-Pharynge)' , 'c' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,'Culture' ,(select sort_order + 3 from clinlims.test where description = 'Culture des Selles(Selles)') , 'Culture' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Culture(Ecouvillon Naso-Pharynge)' )  ,    (select id from clinlims.type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Culture(Ecouvillon Naso-Pharynge)' ) , 'R' , null , now());

--

--PCR(Ecouvillon Naso-Pharynge)

INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'PCR(Ecouvillon Naso-Pharynge)' , 'c' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Bacteria' ) ,'PCR' ,(select sort_order + 6 from clinlims.test where description = 'PCR des Selles(Selles)') , 'PCR' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'PCR(Ecouvillon Naso-Pharynge)' )  ,    (select id from clinlims.type_of_sample where description = 'Ecouvillon Naso-Pharynge')  );

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'PCR(Ecouvillon Naso-Pharynge)' ) , 'R' , null , now());

--

--Panels
INSERT INTO clinlims.panel( id, name, description, lastupdated, display_key, sort_order) VALUES
  (nextval( 'clinlims.panel_seq' ) , 'Coloration de Gram' , 'Coloration de Gram' , now() ,'panel.name.coloration.de.gram' ,25);

INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES
  (nextval( 'clinlims.sample_type_panel_seq') ,currval( 'clinlims.type_of_sample_seq' ) , (select id from clinlims.panel where description = 'Coloration de Gram' ));

--
INSERT INTO clinlims.panel( id, name, description, lastupdated, display_key, sort_order) VALUES
  (nextval( 'clinlims.panel_seq' ) , 'Culture' , 'Culture' , now() ,'panel.name.culture' ,25);

INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES
  (nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Culture' ) , (select id from clinlims.panel where name = 'Culture' ) );
--
INSERT INTO clinlims.panel( id, name, description, lastupdated, display_key, sort_order) VALUES
  (nextval( 'clinlims.panel_seq' ) , 'Recherche de Bordetella' , 'Recherche de Bordetella' , now() ,'panel.name.rechereche.de.bordetella' ,25);

INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES
  (nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Ecouvillon Naso-Pharynge' ) , (select id from clinlims.panel where name = 'Recherche de Bordetella' ) );

--
INSERT INTO clinlims.panel( id, name, description, lastupdated, display_key, sort_order) VALUES
  (nextval( 'clinlims.panel_seq' ) , 'VIH-1 Charge Virale' , 'VIH-1 Charge Virale' , now() ,'panel.name.vih1.charge.virale' ,25);

INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES
  (nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Plasma' ) , (select id from clinlims.panel where name = 'VIH-1 Charge Virale' ) );
--
INSERT INTO clinlims.panel( id, name, description, lastupdated, display_key, sort_order) VALUES
  (nextval( 'clinlims.panel_seq' ) , 'Xpert MTB/RIF' , 'Xpert MTB/RIF' , now() ,'panel.name.xpert.mtb.rif' ,25);

INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES
  (nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Sputum' ) , (select id from clinlims.panel where name = 'Xpert MTB/RIF' ) );
--

INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , test_id) VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Coloration de Gram'), now(), (select id from clinlims.test where description = 'Type d''echantillon(Coloration de Gram)' and is_active = 'Y' ) );
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , test_id) VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Coloration de Gram'), now(), (select id from clinlims.test where description = 'Resultats(Coloration de Gram)' and is_active = 'Y' ) );
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , test_id) VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Culture'), now(), (select id from clinlims.test where description = 'Type d''echantillon(Culture)' and is_active = 'Y' ) );
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , test_id) VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Culture'), now(), (select id from clinlims.test where description = 'Resultats(Culture)' and is_active = 'Y' ) );
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , test_id) VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Recherche de Bordetella'), now(), (select id from clinlims.test where description = 'Culture(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) );
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , test_id) VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Recherche de Bordetella'), now(), (select id from clinlims.test where description = 'PCR(Ecouvillon Naso-Pharynge)' and is_active = 'Y' ) );
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , test_id) VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'VIH-1 Charge Virale'), now(), (select id from clinlims.test where description = 'Score d''adherence(Plasma)' and is_active = 'Y' ) );
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , test_id) VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'VIH-1 Charge Virale'), now(), (select id from clinlims.test where description = 'Date de mise sous ARV(Plasma)' and is_active = 'Y' ) );
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , test_id) VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Xpert MTB/RIF'), now(), (select id from clinlims.test where description = 'Mycobacterium tuberculosis(Sputum)' and is_active = 'Y' ) );
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , test_id) VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Xpert MTB/RIF'), now(), (select id from clinlims.test where description = 'Resistance a la Rifampicine(Sputum)' and is_active = 'Y' ) );
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , test_id) VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Selles Routine'), now(), (select id from clinlims.test where description = 'Globules Blancs(Selles)' and is_active = 'Y' ) );
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , test_id) VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Selles Routine'), now(), (select id from clinlims.test where description = 'Globules Rouges(Selles)' and is_active = 'Y' ) );
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , test_id) VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Selles Routine'), now(), (select id from clinlims.test where description = 'Cellules Vegetales(Selles)' and is_active = 'Y' ) );
--
