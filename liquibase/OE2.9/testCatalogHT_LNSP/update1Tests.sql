INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='%') , 'Monocytes(Sang)' , 'Monocytes' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Monocytes' ,762 , 'Monocytes' , '26485-3');
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='%') , 'Eosinophiles(Sang)' , 'Eosinophiles' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Eosinophiles' ,764 , 'Eosinophiles' , '26450-7');
INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
	VALUES ( nextval( 'test_seq' ) , ( select id from clinlims.unit_of_measure where name='%') , 'Basophiles(Sang)' , 'Basophiles' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Hematology' ) ,'Basophiles' ,766 , 'Basophiles' , '30180-4');


INSERT INTO panel( id, "name", description, lastupdated, display_key, sort_order,  is_active)
    VALUES ( nextval( 'panel_seq' ) , 'Hemogramme-Manual', 'Hemogramme-Manual', now() , 'panel.name.Hemogramme-Manual', 45, 'Y');

INSERT INTO clinlims.sampletype_panel (id, sample_type_id, panel_id ) VALUES 
	(nextval( 'sample_type_panel_seq') , (select id from clinlims.type_of_sample where description = 'Sang' ) , (select id from clinlims.panel where name = 'Hemogramme-Manual' ) );
   
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme-Manual') , now(), null,  (select id from test where description = 'Neutrophiles(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme-Manual') , now(), null,  (select id from test where description = 'Lymphocytes(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme-Manual') , now(), null,  (select id from test where description = 'Monocytes(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme-Manual') , now(), null,  (select id from test where description = 'Eosinophiles(Sang)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Hemogramme-Manual') , now(), null,  (select id from test where description = 'Basophiles(Sang)' and is_active = 'Y' ) ); 

   
   
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Monocytes(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Eosinophiles(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Basophiles(Sang)' )  ,    (select id from type_of_sample where description = 'Sang')  );
	
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Monocytes(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,2,10,1,30, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Eosinophiles(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,1,4,0,20, now() );
INSERT INTO result_limits(  id, test_id, test_result_type_id, min_age, max_age, gender, low_normal, high_normal, low_valid, high_valid, lastupdated) 
	 VALUES ( nextval( 'result_limits_seq' ) , ( select id from clinlims.test where description = 'Basophiles(Sang)' ) , 
			 (select id from clinlims.type_of_test_result where test_result_type = 'N' ) , 0, 'Infinity' , '' ,0,1,0,4, now() );
	

	