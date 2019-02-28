INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Stat-Pak(Plasma)' )  ,    (select id from type_of_sample where local_abbrev = 'Plasma')  ),
(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Stat-Pak(Sérum)' )  ,    (select id from type_of_sample where display_key = 'sample.type.Serum')  ),
(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Stat-Pak(Sang total)' )  ,    (select id from type_of_sample where local_abbrev = 'Sang total')  ),
(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Determine(Plasma)' )  ,    (select id from type_of_sample where local_abbrev = 'Plasma')  ),
(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Determine(Sérum)' )  ,    (select id from type_of_sample where display_key = 'sample.type.Serum')  ),
(nextval( 'sample_type_test_seq' ) , (select id from test where description = 'Determine(Sang total)' )  ,    (select id from type_of_sample where local_abbrev = 'Sang total')  );
