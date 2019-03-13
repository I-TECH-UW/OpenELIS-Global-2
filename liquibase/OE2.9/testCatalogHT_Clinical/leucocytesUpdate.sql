UPDATE test set description = 'Leucocytes (Urines)', is_active='N' where description = 'Leucocytes(Urines)';

INSERT INTO test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name )
	VALUES ( nextval( 'test_seq' ) , null , 'Leucocytes(Urines)' , 'Leucocytes' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'ECBU' ) ,'Leucocytes' ,1891 , 'Leucocytes' );
	
UPDATE panel_item SET test_id = ( select id from test where description = 'Leucocytes(Urines)' ) where test_id = ( select id from test where description = 'Leucocytes (Urines)' );
UPDATE sampletype_test SET test_id = ( select id from test where description = 'Leucocytes(Urines)' ) where test_id = ( select id from test where description = 'Leucocytes (Urines)' );
