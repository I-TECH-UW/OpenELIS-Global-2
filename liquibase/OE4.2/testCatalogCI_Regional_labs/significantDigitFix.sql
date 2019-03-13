UPDATE clinlims.test_result SET significant_digits = 2 WHERE test_id=(select id from clinlims.test where guid = 'eea822b4-5535-4e25-b9ef-fd492bef4349');
UPDATE clinlims.test_result SET significant_digits = 1 WHERE test_id=(select id from clinlims.test where guid = 'e08bdd35-b7e4-4910-ae73-da5b6447e901');
UPDATE clinlims.test_result SET significant_digits = 2 WHERE test_id=(select id from clinlims.test where guid = '25249ec2-0dbb-4a45-8c97-836c175ab183');
UPDATE clinlims.test_result SET significant_digits = 2 WHERE test_id=(select id from clinlims.test where guid = '466b3775-e117-4268-92a7-3d3de95d43b3');
UPDATE clinlims.test_result SET significant_digits = 1 WHERE test_id=(select id from clinlims.test where guid = 'e79dba96-ce3e-4b3c-945b-a73f7fa4b862');
