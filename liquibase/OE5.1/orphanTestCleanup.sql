CREATE TABLE orphan_test AS ( select t.id from clinlims.test t
where t.is_active = 'N' and (
  t.id not in (select stt.test_id from clinlims.sampletype_test stt) OR (
    t.id not in (select a.test_id from clinlims.analysis a where a.test_id is not null) AND
    t.id not in (select rr.test_id from clinlims.referral_result rr where rr.test_id is not null)AND
    t.id not in (select qa.test_id from clinlims.qa_event qa where qa.test_id is not null) AND
    t.id not in (select tr.test_id from clinlims.test_reflex tr where tr.add_test_id is not null) AND
    t.id not in (select id from clinlims.test where name in ('Recherche de plasmodiun - Especes', 'Malaria Test Rapide'))) )
);


delete from clinlims.result_limits where test_id in (select id from clinlims.orphan_test);
delete from clinlims.test_code where test_id in (select id from clinlims.orphan_test);
delete from clinlims.sampletype_test where test_id in (select id from clinlims.orphan_test);
delete from clinlims.test_reflex where test_id in (select id from clinlims.orphan_test) or
                                       add_test_id in (select id from clinlims.orphan_test);
delete from clinlims.test_analyte where test_id in (select id from clinlims.orphan_test);
delete from clinlims.test_dictionary where test_id in (select id from clinlims.orphan_test);
delete from clinlims.test_result where test_id in (select id from clinlims.orphan_test);
DELETE from sampletype_panel where panel_id in ( select id from clinlims.panel where is_active = 'N');
DELETE from clinlims.type_of_sample where is_active = 'N' and
                                          id not in ( select sample_type_id from clinlims.sampletype_test) AND
                                          id <> (select id FROM clinlims.type_of_sample where local_abbrev = 'Variable' );
delete from clinlims.panel_item where test_id in (select id from clinlims.orphan_test);
delete from clinlims.panel where is_active = 'N' AND
                                 id not in (select panel_id from clinlims.panel_item);
DELETE FROM clinlims.test WHERE id in ( select id from clinlims.orphan_test);
DROP TABLE clinlims.orphan_test;