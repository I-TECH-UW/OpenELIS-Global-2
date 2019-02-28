INSERT INTO test_result( id, test_id, tst_rslt_type, lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Monocytes(Sang)' ) , 'N' ,   now() , 2331);
INSERT INTO test_result( id, test_id, tst_rslt_type, lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Eosinophiles(Sang)' ) , 'N' , now() , 2332);
INSERT INTO test_result( id, test_id, tst_rslt_type, lastupdated, sort_order)
	 VALUES ( nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Basophiles(Sang)' ) , 'N' , now() , 2334);
