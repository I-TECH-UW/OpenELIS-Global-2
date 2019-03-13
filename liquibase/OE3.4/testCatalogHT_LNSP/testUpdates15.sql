delete from clinlims.result_signature where result_id in (select id from clinlims.result where test_result_id in (select id from clinlims.test_result where test_id in (select id from clinlims.test where description = 'Score d''adherence(DBS)' )));
delete from clinlims.result_signature where result_id in (select id from clinlims.result where test_result_id in (select id from clinlims.test_result where test_id in (select id from clinlims.test where description = 'Date de mise sous ARV(DBS)' )));

delete from clinlims.result where test_result_id in (select id from clinlims.test_result where test_id in (select id from clinlims.test where description = 'Score d''adherence(DBS)' ));

delete from clinlims.result where test_result_id in (select id from clinlims.test_result where test_id in (select id from clinlims.test where description = 'Date de mise sous ARV(DBS)' ));

delete from clinlims.test_result where test_id in (select id from clinlims.test where description = 'Score d''adherence(DBS)' );

delete from clinlims.test_result where test_id in (select id from clinlims.test where description = 'Date de mise sous ARV(DBS)' );



INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, lastupdated)
VALUES ( nextval( 'clinlims.test_result_seq' ) , (select id from clinlims.test where description like 'Score d_adherence(DBS)' ) ,
         'R' ,  now() ) ;

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, lastupdated)
VALUES ( nextval( 'clinlims.test_result_seq' ) , (select id from clinlims.test where description = 'Date de mise sous ARV(DBS)' ) ,
         'R' ,  now() ) ;

update clinlims.test set sort_order = (select sort_order + 50 from clinlims.test where description = 'Culture(Ecouvillon Naso-Pharynge)') where id = (select id from clinlims.test where description = 'PCR(Ecouvillon Naso-Pharynge)');

