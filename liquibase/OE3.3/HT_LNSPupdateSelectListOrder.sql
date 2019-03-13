Update clinlims.dictionary set dict_entry = 'ADN VIH-1 Détecté' where dict_entry = 'ADN VIH-1 Detecte';
Update clinlims.dictionary set dict_entry = 'ADN VIH-1 Non-Détecté' where dict_entry = 'ADN VIH-1  Non-Détecté';

UPDATE clinlims.test_result tr
   SET lastupdated= now() , sort_order= 1520
 WHERE tr.test_id = (select id from clinlims.test where description = 'VIH-1 PCR Qualitatif(DBS)' and 
       value = CAST( (select max(id) from clinlims.dictionary where dict_entry = 'PCR1' ) AS varchar));
UPDATE clinlims.test_result tr
   SET lastupdated= now() , sort_order= 1530
 WHERE tr.test_id = (select id from clinlims.test where description = 'VIH-1 PCR Qualitatif(DBS)' and 
       value = CAST( (select max(id) from clinlims.dictionary where dict_entry = 'PCR2' ) AS varchar));
UPDATE clinlims.test_result tr
   SET lastupdated= now() , sort_order= 1540
 WHERE tr.test_id = (select id from clinlims.test where description = 'VIH-1 PCR Qualitatif(DBS)' and 
       value = CAST( (select max(id) from clinlims.dictionary where dict_entry = 'PCR3' ) AS varchar));
UPDATE clinlims.test_result tr
   SET lastupdated= now() , sort_order= 1550
 WHERE tr.test_id = (select id from clinlims.test where description = 'VIH-1 PCR Qualitatif(DBS)' and 
       value = CAST( (select max(id) from clinlims.dictionary where dict_entry = 'ADN VIH-1 Détecté' ) AS varchar));
UPDATE clinlims.test_result tr
   SET lastupdated= now() , sort_order= 1560
 WHERE tr.test_id = (select id from clinlims.test where description = 'VIH-1 PCR Qualitatif(DBS)' and 
       value = CAST( (select max(id) from clinlims.dictionary where dict_entry = 'ADN VIH-1 Non-Détecté' ) AS varchar));

UPDATE clinlims.test_result tr
   SET lastupdated= now() , sort_order= 1570
 WHERE tr.test_id = (select id from clinlims.test where description = 'VIH-1 PCR Qualitatif(Sang Total)' and 
       value = CAST( (select max(id) from clinlims.dictionary where dict_entry = 'PCR1' ) AS varchar));
UPDATE clinlims.test_result tr
   SET lastupdated= now() , sort_order= 1580
 WHERE tr.test_id = (select id from clinlims.test where description = 'VIH-1 PCR Qualitatif(Sang Total)' and 
       value = CAST( (select max(id) from clinlims.dictionary where dict_entry = 'PCR2' ) AS varchar));
UPDATE clinlims.test_result tr
   SET lastupdated= now() , sort_order= 1590
 WHERE tr.test_id = (select id from clinlims.test where description = 'VIH-1 PCR Qualitatif(Sang Total)' and 
       value = CAST( (select max(id) from clinlims.dictionary where dict_entry = 'PCR3' ) AS varchar));
UPDATE clinlims.test_result tr
   SET lastupdated= now() , sort_order= 1600
 WHERE tr.test_id = (select id from clinlims.test where description = 'VIH-1 PCR Qualitatif(Sang Total)' and 
       value = CAST( (select max(id) from clinlims.dictionary where dict_entry = 'ADN VIH-1 Détecté' ) AS varchar));
UPDATE clinlims.test_result tr
   SET lastupdated= now() , sort_order= 1610
 WHERE tr.test_id = (select id from clinlims.test where description = 'VIH-1 PCR Qualitatif(Sang Total)' and 
       value = CAST( (select max(id) from clinlims.dictionary where dict_entry = 'ADN VIH-1 Non-Détecté' ) AS varchar));
