INSERT INTO analyte( id, "name", is_active,  lastupdated )
    VALUES ( nextval('analyte_seq'), 'Test encre chine Results', 'Y', now() ),
           ( nextval('analyte_seq'), 'Levure Superficielle Result', 'Y', now() ),
           ( nextval('analyte_seq'), 'Levure Profonde Result', 'Y', now() );

INSERT INTO test_analyte(  id, test_id, analyte_id, result_group, sort_order,   lastupdated )
  VALUES ( nextval('test_analyte_seq'),
           (select id from clinlims.test where description = 'Test encre chine(LCR)') ,
           (select id from clinlims.analyte where name = 'Test encre chine Results'),
           10, 1, now() ),
          ( nextval('test_analyte_seq'),
            (select id from clinlims.test where description = 'Levure mycose superficielle') ,
            (select id from clinlims.analyte where name = 'Levure Superficielle Result'),
            10, 1, now() ),
          ( nextval('test_analyte_seq'),
           (select id from clinlims.test where description = 'Levure mycose profonde') ,
           (select id from clinlims.analyte where name = 'Levure Profonde Result'),
           10, 1, now() );

INSERT INTO test_reflex(  id, tst_rslt_id, flags, lastupdated, test_analyte_id, test_id,  add_test_id, sibling_reflex, scriptlet_id)
  VALUES ( nextval('test_reflex_seq'),
           (select id from clinlims.test_result where test_id =
                                                      (select id from clinlims.test where description = 'Test encre chine(LCR)' ) and
                                                       value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence De Levures Du Genre Cryptocoque') as varchar )),
           '',  now() ,
           ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Test encre chine(LCR)' )),
           ( select id from clinlims.test where description = 'Test encre chine(LCR)' ),
           ( select id from clinlims.test where description = 'Test ag soluble(LCR)') , null, null ),
  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Levure mycose superficielle' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Levure mycose superficielle' )),
    ( select id from clinlims.test where description = 'Levure mycose superficielle' ),
    ( select id from clinlims.test where description = 'Germe isolé antifongigramme') , null, null ),

  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Levure mycose superficielle' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Levure mycose superficielle' )),
    ( select id from clinlims.test where description = 'Levure mycose superficielle' ),
    ( select id from clinlims.test where description = 'Flucytosine 1 ug') , null, null ),

  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Levure mycose superficielle' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Levure mycose superficielle' )),
    ( select id from clinlims.test where description = 'Levure mycose superficielle' ),
    ( select id from clinlims.test where description = 'Flucytosine 10 ug') , null, null ),

  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Levure mycose superficielle' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Levure mycose superficielle' )),
    ( select id from clinlims.test where description = 'Levure mycose superficielle' ),
    ( select id from clinlims.test where description = 'Amphotéricine B') , null, null ),

  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Levure mycose superficielle' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Levure mycose superficielle' )),
    ( select id from clinlims.test where description = 'Levure mycose superficielle' ),
    ( select id from clinlims.test where description = 'Fluconazole') , null, null ),

  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Levure mycose superficielle' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Levure mycose superficielle' )),
    ( select id from clinlims.test where description = 'Levure mycose superficielle' ),
    ( select id from clinlims.test where description = 'Miconazole') , null, null ),

  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Levure mycose superficielle' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Levure mycose superficielle' )),
    ( select id from clinlims.test where description = 'Levure mycose superficielle' ),
    ( select id from clinlims.test where description = 'Econazole') , null, null ),

  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Levure mycose superficielle' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Levure mycose superficielle' )),
    ( select id from clinlims.test where description = 'Levure mycose superficielle' ),
    ( select id from clinlims.test where description = 'Kétéconazole') , null, null ),

  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Levure mycose superficielle' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Levure mycose superficielle' )),
    ( select id from clinlims.test where description = 'Levure mycose superficielle' ),
    ( select id from clinlims.test where description = 'Nystatine') , null, null ),

  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Levure mycose superficielle' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Levure mycose superficielle' )),
    ( select id from clinlims.test where description = 'Levure mycose superficielle' ),
    ( select id from clinlims.test where description = 'Voriconazole') , null, null ),

  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Levure mycose superficielle' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Levure mycose superficielle' )),
    ( select id from clinlims.test where description = 'Levure mycose superficielle' ),
    ( select id from clinlims.test where description = 'Itraconazole') , null, null ),

  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Levure mycose superficielle' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Levure mycose superficielle' )),
    ( select id from clinlims.test where description = 'Levure mycose superficielle' ),
    ( select id from clinlims.test where description = 'Clotrimazole') , null, null ),

  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Levure mycose superficielle' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Levure mycose superficielle' )),
    ( select id from clinlims.test where description = 'Levure mycose superficielle' ),
    ( select id from clinlims.test where description = 'Germe isolé mycose superficielle') , null, null ),

  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Levure mycose profonde' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence De Levures') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Levure mycose profonde' )),
    ( select id from clinlims.test where description = 'Levure mycose profonde' ),
    ( select id from clinlims.test where description = 'Germe isolé antifongigramme') , null, null ),

  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Levure mycose profonde' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence De Levures') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Levure mycose profonde' )),
    ( select id from clinlims.test where description = 'Levure mycose profonde' ),
    ( select id from clinlims.test where description = 'Flucytosine 1 ug') , null, null ),

  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Levure mycose profonde' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence De Levures') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Levure mycose profonde' )),
    ( select id from clinlims.test where description = 'Levure mycose profonde' ),
    ( select id from clinlims.test where description = 'Flucytosine 10 ug') , null, null ),

  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Levure mycose profonde' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence De Levures') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Levure mycose profonde' )),
    ( select id from clinlims.test where description = 'Levure mycose profonde' ),
    ( select id from clinlims.test where description = 'Amphotéricine B') , null, null ),

  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Levure mycose profonde' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence De Levures') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Levure mycose profonde' )),
    ( select id from clinlims.test where description = 'Levure mycose profonde' ),
    ( select id from clinlims.test where description = 'Fluconazole') , null, null ),

  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Levure mycose profonde' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence De Levures') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Levure mycose profonde' )),
    ( select id from clinlims.test where description = 'Levure mycose profonde' ),
    ( select id from clinlims.test where description = 'Miconazole') , null, null ),

  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Levure mycose profonde' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence De Levures') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Levure mycose profonde' )),
    ( select id from clinlims.test where description = 'Levure mycose profonde' ),
    ( select id from clinlims.test where description = 'Econazole') , null, null ),

  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Levure mycose profonde' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence De Levures') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Levure mycose profonde' )),
    ( select id from clinlims.test where description = 'Levure mycose profonde' ),
    ( select id from clinlims.test where description = 'Kétéconazole') , null, null ),

  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Levure mycose profonde' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence De Levures') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Levure mycose profonde' )),
    ( select id from clinlims.test where description = 'Levure mycose profonde' ),
    ( select id from clinlims.test where description = 'Nystatine') , null, null ),

  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Levure mycose profonde' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence De Levures') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Levure mycose profonde' )),
    ( select id from clinlims.test where description = 'Levure mycose profonde' ),
    ( select id from clinlims.test where description = 'Voriconazole') , null, null ),

  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Levure mycose profonde' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence De Levures') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Levure mycose profonde' )),
    ( select id from clinlims.test where description = 'Levure mycose profonde' ),
    ( select id from clinlims.test where description = 'Itraconazole') , null, null ),

  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Levure mycose profonde' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence De Levures') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Levure mycose profonde' )),
    ( select id from clinlims.test where description = 'Levure mycose profonde' ),
    ( select id from clinlims.test where description = 'Clotrimazole') , null, null ),

  ( nextval('test_reflex_seq'),
    (select id from clinlims.test_result where test_id =
                                               (select id from clinlims.test where description = 'Levure mycose profonde' ) and
                                               value = CAST( (select MAX( id ) from clinlims.dictionary where dict_entry = 'Présence De Levures') as varchar )),
    '',  now() ,
    ( select id from clinlims.test_analyte where test_id =  (select id from clinlims.test where description = 'Levure mycose profonde' )),
    ( select id from clinlims.test where description = 'Levure mycose profonde' ),
    ( select id from clinlims.test where description = 'Germe isolé mycose superficielle') , null, null );


