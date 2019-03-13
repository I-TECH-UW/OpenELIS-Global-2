update test_result set tst_rslt_type='M' where test_id in 
       ( select id from test where description in ('Culture de mycobacteries Solide(Sputum)','Culture de mycobacteries liquide(Sputum)'));	 