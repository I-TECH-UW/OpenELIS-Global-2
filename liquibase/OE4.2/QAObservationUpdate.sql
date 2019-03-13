UPDATE clinlims.qa_observation set value_type='K', value = 'test.section.mycology'
where qa_observation_type_id = (select id from clinlims.qa_observation_type where name = 'section') and value in ('Mycology','Mycologie');

UPDATE clinlims.qa_observation set value_type='K', value = 'test.section.biochemistry'
where qa_observation_type_id = (select id from clinlims.qa_observation_type where name = 'section') and value  in ('Biochemistry','Biochimie');

UPDATE clinlims.qa_observation set value_type='K', value = 'test.section.immunology'
where qa_observation_type_id = (select id from clinlims.qa_observation_type where name = 'section') and value  in ('Immunology','Immunologie','Immuno-Virologie','Sérologie-Virologie');

UPDATE clinlims.qa_observation set value_type='K', value = 'test.section.ECBU'
where qa_observation_type_id = (select id from clinlims.qa_observation_type where name = 'section') and value  = 'ECBU';

UPDATE clinlims.qa_observation set value_type='K', value = 'test.section.VCT'
where qa_observation_type_id = (select id from clinlims.qa_observation_type where name = 'section') and value  in ('VCT','CDV');

UPDATE clinlims.qa_observation set value_type='K', value = 'test.section.virologie'
where qa_observation_type_id = (select id from clinlims.qa_observation_type where name = 'section') and value  in ('Virology','Virologie','Sérologie-Virologie');

UPDATE clinlims.qa_observation set value_type='K', value = 'test.section.Moleoularbiology'
where qa_observation_type_id = (select id from clinlims.qa_observation_type where name = 'section') and value  in ('Molecular Biology','Biologie Moleculaire');

UPDATE clinlims.qa_observation set value_type='K', value = 'test.section.hematology'
where qa_observation_type_id = (select id from clinlims.qa_observation_type where name = 'section') and value  in ('Hematology','Hématologie');

UPDATE clinlims.qa_observation set value_type='K', value = 'test.section.bacteria'
where qa_observation_type_id = (select id from clinlims.qa_observation_type where name = 'section') and value  in ('Bacteria','Bactériologie', 'Bacteriologie');

UPDATE clinlims.qa_observation set value_type='K', value = 'test.section.parasitology'
where qa_observation_type_id = (select id from clinlims.qa_observation_type where name = 'section') and value  in ('Parasitology','Parasitologie');

UPDATE clinlims.qa_observation set value_type='K', value = 'test.section.mycobacteriology'
where qa_observation_type_id = (select id from clinlims.qa_observation_type where name = 'section') and value  in ('Mycobacteriology','Mycobactériologie');

UPDATE clinlims.qa_observation set value_type='K', value = 'testSection.Serology'
where qa_observation_type_id = (select id from clinlims.qa_observation_type where name = 'section') and value  in ('Serology','Sérologie');

UPDATE clinlims.qa_observation set value_type='K', value = 'test.section.hemato-immunology'
where qa_observation_type_id = (select id from clinlims.qa_observation_type where name = 'section') and value  in ('Hemato-Immunology','Hémato-Immunologie');

UPDATE clinlims.qa_observation set value_type='K', value = 'test.section.serology-immunology'
where qa_observation_type_id = (select id from clinlims.qa_observation_type where name = 'section') and value  in ('Serology-Immunology','Sérologie-Immunologie');

UPDATE clinlims.qa_observation set value_type='K', value = 'test.section.malaria'
where qa_observation_type_id = (select id from clinlims.qa_observation_type where name = 'section') and value  = 'Malaria';

UPDATE clinlims.qa_observation set value_type='K', value = 'test.section.Liquidbiology'
where qa_observation_type_id = (select id from clinlims.qa_observation_type where name = 'section') and value  in ('Liquid Biology','Liquides biologique');

UPDATE clinlims.qa_observation set value_type='K', value = 'test.section.test.section.Endocrinology'
where qa_observation_type_id = (select id from clinlims.qa_observation_type where name = 'section') and value  in ('Endocrinology','Endocrinologie');

UPDATE clinlims.qa_observation set value_type='K', value = 'test.section.Cytobacteriologie'
where qa_observation_type_id = (select id from clinlims.qa_observation_type where name = 'section') and value  in ('Cytobacteriology','Cytobacteriologie');

UPDATE clinlims.qa_observation set value_type='K', value = 'testSection.Reception'
where qa_observation_type_id = (select id from clinlims.qa_observation_type where name = 'section') and value  in ('Reception','Réception');

UPDATE clinlims.qa_observation set value_type='K', value = 'testSection.SampleCollection'
where qa_observation_type_id = (select id from clinlims.qa_observation_type where name = 'section') and value  in ('Sample Collection','Unite de Prélèvement');


COMMENT ON COLUMN qa_observation.value_type IS 'Dictionary, literal or localization key, D, L, K';


