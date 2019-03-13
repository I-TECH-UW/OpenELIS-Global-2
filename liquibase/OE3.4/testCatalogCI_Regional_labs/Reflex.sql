INSERT INTO analyte( id, "name", is_active,  lastupdated )
    VALUES ( nextval('analyte_seq'), 'Determine Results', 'Y', now() ),
           ( nextval('analyte_seq'), 'Genie III Result', 'Y', now() );

INSERT INTO test_analyte(  id, test_id, analyte_id, result_group, sort_order,   lastupdated )
  VALUES
  ( nextval('test_analyte_seq'),
    (select id from clinlims.test where description = 'Determine(Plasma)') ,
    (select id from clinlims.analyte where name = 'Determine Results'),
    10, 1, now() ),
  ( nextval('test_analyte_seq'),
    (select id from clinlims.test where description = 'Determine(Sérum)') ,
    (select id from clinlims.analyte where name = 'Determine Results'),
    10, 1, now() ),
  ( nextval('test_analyte_seq'),
    (select id from clinlims.test where description = 'Determine(Sang total)') ,
    (select id from clinlims.analyte where name = 'Determine Results'),
    10, 1, now() ),
  ( nextval('test_analyte_seq'),
    (select id from clinlims.test where description = 'Genie III(Plasma)') ,
    (select id from clinlims.analyte where name = 'Genie III Result'),
    10, 1, now() ),
  ( nextval('test_analyte_seq'),
    (select id from clinlims.test where description = 'Genie III(Serum)') ,
    (select id from clinlims.analyte where name = 'Genie III Result'),
    10, 1, now() ),
  ( nextval('test_analyte_seq'),
    (select id from clinlims.test where description = 'Genie III(Sang total)') ,
    (select id from clinlims.analyte where name = 'Genie III Result'),
    10, 1, now() );

INSERT INTO test_reflex(  id, tst_rslt_id, flags, lastupdated, test_analyte_id, test_id,  add_test_id, sibling_reflex, scriptlet_id)
  VALUES
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Determine(Plasma)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positif') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Determine(Plasma)' )),
    ( select id from clinlims.test where description = 'Determine(Plasma)' ),
    ( select id from clinlims.test where description = 'Genie III(Plasma)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Determine(Sérum)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positif') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Determine(Sérum)' )),
    ( select id from clinlims.test where description = 'Determine(Sérum)' ),
    ( select id from clinlims.test where description = 'Genie III(Serum)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Determine(Sang total)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Positif') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Determine(Sang total)' )),
    ( select id from clinlims.test where description = 'Determine(Sang total)' ),
    ( select id from clinlims.test where description = 'Genie III(Sang total)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Genie III(Plasma)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Négatif') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Genie III(Plasma)' )),
    ( select id from clinlims.test where description = 'Genie III(Plasma)' ),
    ( select id from clinlims.test where description = 'Stat-Pak(Plasma)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Genie III(Serum)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Négatif') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Genie III(Serum)' )),
    ( select id from clinlims.test where description = 'Genie III(Serum)' ),
    ( select id from clinlims.test where description = 'Stat-Pak(Sérum)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Genie III(Sang total)' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Négatif') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Genie III(Sang total)' )),
    ( select id from clinlims.test where description = 'Genie III(Sang total)' ),
    ( select id from clinlims.test where description = 'Stat-Pak(Sang total)') , null, null );
