
update clinlims.test set is_active = 'Y' where description = 'Culture(Ecouvillon Naso-Pharynge)';
update clinlims.test set is_active = 'Y' where description = 'VIH-1 Charge Virale(DBS)';
update clinlims.type_of_sample set is_active = 'f' where description = 'Ecouvillon Nasal';


--Score d'adherence(DBS)
INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Score d''adherence(DBS)' , 'Score d''adherence' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Virologie' ) ,'Score d''adherence' ,(select sort_order + 20 from clinlims.test where description = 'VIH-1 Charge Virale(DBS)') , 'Score d''adherence' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Score d''adherence(DBS)' )  ,    (select id from clinlims.type_of_sample where description = 'DBS')  );

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Score d''adherence(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetectable <300' )  , now() , 1350, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Score d''adherence(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Detectable' )  , now() , 1360, false);
--


--Date de mise sous ARV(DBS)
INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Date de mise sous ARV(DBS)' , 'Date de mise sous ARV' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Virologie' ) ,'Date de mise sous ARV' ,(select sort_order + 40 from clinlims.test where description = 'VIH-1 Charge Virale(DBS)') , 'Date de mise sous ARV' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Date de mise sous ARV(DBS)' )  ,    (select id from clinlims.type_of_sample where description = 'DBS')  );

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Date de mise sous ARV(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Indetectable <300' )  , now() , 1350, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Date de mise sous ARV(DBS)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Detectable' )  , now() , 1360, false);
--


INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES
  (nextval( 'clinlims.sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'DBS' ) , (select id from clinlims.panel where name = 'VIH-1 Charge Virale' ) );

INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , test_id)
VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'VIH-1 Charge Virale') , now(), (select id from clinlims.test where description = 'VIH-1 Charge Virale(DBS)' and is_active = 'Y'));

INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , test_id)
VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'VIH-1 Charge Virale') , now(), (select id from clinlims.test where description = 'Score d''adherence(DBS)' and is_active = 'Y'));

INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , test_id)
VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'VIH-1 Charge Virale') , now(), (select id from clinlims.test where description = 'Date de mise sous ARV(DBS)' and is_active = 'Y'));

