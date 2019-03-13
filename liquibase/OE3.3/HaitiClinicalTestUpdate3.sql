update clinlims.result_limits set low_valid = 0, lastupdated = now() 
where test_id in (select id from clinlims.test where description in( 'Cholestérol total(Serum)','Triglycéride(Serum)' ) ); 