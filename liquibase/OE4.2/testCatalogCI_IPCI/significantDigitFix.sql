UPDATE clinlims.test_result SET significant_digits = 2 WHERE test_id=(select id from clinlims.test where description like 'Cr%atinine(S%rum)');
UPDATE clinlims.test_result SET significant_digits = 2 WHERE test_id=(select id from clinlims.test where description like 'Cholest%rol total(S%rum)');
UPDATE clinlims.test_result SET significant_digits = 2 WHERE test_id=(select id from clinlims.test where description like 'Cholest%rol HDL(S%rum)');
UPDATE clinlims.test_result SET significant_digits = 1 WHERE test_id=(select id from clinlims.test where description like 'Triglyc%rides(S%rum)');
UPDATE clinlims.test_result SET significant_digits = 2 WHERE test_id=(select id from clinlims.test where description like 'Prolans (BHCG) urines de 24 h%');
UPDATE clinlims.test_result SET significant_digits = 1 WHERE test_id=(select id from clinlims.test where description like 'Num%ration des globules blancs%');
UPDATE clinlims.test_result SET significant_digits = 2 WHERE test_id=(select id from clinlims.test where description like 'Num%ration des globules rouges%');
UPDATE clinlims.test_result SET significant_digits = 2 WHERE test_id=(select id from clinlims.test where description like 'H%moglobine%');
UPDATE clinlims.test_result SET significant_digits = 1 WHERE test_id=(select id from clinlims.test where guid = 'da0456cd-6ecf-4aa2-a526-d3843e588110');
UPDATE clinlims.test_result SET significant_digits = 2 WHERE test_id=(select id from clinlims.test where description like 'Titre IgG%');
UPDATE clinlims.test_result SET significant_digits = 4 WHERE test_id=(select id from clinlims.test where description like 'Titre Ac anti-amibien (BERHING)%');
UPDATE clinlims.test_result SET significant_digits = 4 WHERE test_id=(select id from clinlims.test where description like 'Titre Ac anti-amibien (FUMOUZE)%');


