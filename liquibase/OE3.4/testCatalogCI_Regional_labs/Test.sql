INSERT INTO test( id,  description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc, orderable )
	VALUES ( nextval( 'test_seq' ) , 'Stat-Pak(Plasma)' , 'Stat-Pak-Plasma' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serology-Immunology' ) ,'Stat-Pak' ,434 , 'Stat-Pak' , '', false),
  ( nextval( 'test_seq' ) , 'Stat-Pak(Sérum)' , 'Stat-Pak-Sérum' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serology-Immunology' ) ,'Stat-Pak' ,435 , 'Stat-Pak' , '', false),
  ( nextval( 'test_seq' ) , 'Stat-Pak(Sang total)' , 'Stat-Pak-Sang total' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serology-Immunology' ) ,'Stat-Pak' ,436 , 'Stat-Pak' , '', false),
  ( nextval( 'test_seq' ) , 'Determine(Plasma)' , 'Determine-Plasma' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serology-Immunology' ) ,'Determine' ,470 , 'Determine' , '', true),
  ( nextval( 'test_seq' ) , 'Determine(Sérum)' , 'Determine-Sérum' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serology-Immunology' ) ,'Determine' ,480 , 'Determine' , '', true),
  ( nextval( 'test_seq' ) , 'Determine(Sang total)' , 'Determine-Sang total' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Serology-Immunology' ) ,'Determine' ,490 , 'Determine' , '', true);

