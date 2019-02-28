INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'GPT/ALAT', (select name from clinlims.test where guid = '3a3661a1-a166-4590-90bc-937912789739' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3a3661a1-a166-4590-90bc-937912789739';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'GPT/ALAT', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '3a3661a1-a166-4590-90bc-937912789739' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3a3661a1-a166-4590-90bc-937912789739';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'GOT/ASAT', (select name from clinlims.test where guid = '71a02f4a-70b8-47da-9527-b6d604a92921' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='71a02f4a-70b8-47da-9527-b6d604a92921';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'GOT/ASAT', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '71a02f4a-70b8-47da-9527-b6d604a92921' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='71a02f4a-70b8-47da-9527-b6d604a92921';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Glucose', (select name from clinlims.test where guid = '8410a83b-d09a-475d-a71c-1fcbcca94e58' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='8410a83b-d09a-475d-a71c-1fcbcca94e58';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Glucose', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '8410a83b-d09a-475d-a71c-1fcbcca94e58' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='8410a83b-d09a-475d-a71c-1fcbcca94e58';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Creatinine', (select name from clinlims.test where guid = 'd7f672c4-52ea-4c26-bdf0-e9527d2ba95f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d7f672c4-52ea-4c26-bdf0-e9527d2ba95f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Creatinine', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'd7f672c4-52ea-4c26-bdf0-e9527d2ba95f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d7f672c4-52ea-4c26-bdf0-e9527d2ba95f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Amylase', (select name from clinlims.test where guid = 'b45cace4-5436-41c3-a4df-bcc9c11395eb' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='b45cace4-5436-41c3-a4df-bcc9c11395eb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Amylase', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'b45cace4-5436-41c3-a4df-bcc9c11395eb' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='b45cace4-5436-41c3-a4df-bcc9c11395eb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Albumin', (select name from clinlims.test where guid = '3578f53d-8e5c-4e16-9dd3-65fe4d1a1f4d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3578f53d-8e5c-4e16-9dd3-65fe4d1a1f4d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Albumin', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '3578f53d-8e5c-4e16-9dd3-65fe4d1a1f4d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3578f53d-8e5c-4e16-9dd3-65fe4d1a1f4d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Total cholesterol', (select name from clinlims.test where guid = '4cd86c73-eca4-4968-a410-ee9d61f5da11' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='4cd86c73-eca4-4968-a410-ee9d61f5da11';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Total cholesterol', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '4cd86c73-eca4-4968-a410-ee9d61f5da11' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='4cd86c73-eca4-4968-a410-ee9d61f5da11';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HDL cholesterol', (select name from clinlims.test where guid = 'eea822b4-5535-4e25-b9ef-fd492bef4349' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='eea822b4-5535-4e25-b9ef-fd492bef4349';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HDL cholesterol', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'eea822b4-5535-4e25-b9ef-fd492bef4349' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='eea822b4-5535-4e25-b9ef-fd492bef4349';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Triglicerides', (select name from clinlims.test where guid = '59321bd7-ab24-43a2-b47c-557f283548ff' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='59321bd7-ab24-43a2-b47c-557f283548ff';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Triglicerides', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '59321bd7-ab24-43a2-b47c-557f283548ff' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='59321bd7-ab24-43a2-b47c-557f283548ff';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Beta HCG', (select name from clinlims.test where guid = 'fd50b89e-0f16-485f-a599-8ed989efa855' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='fd50b89e-0f16-485f-a599-8ed989efa855';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Beta HCG', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'fd50b89e-0f16-485f-a599-8ed989efa855' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='fd50b89e-0f16-485f-a599-8ed989efa855';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Urine prenancy test', (select name from clinlims.test where guid = '18ee4ac6-9a1c-4bfa-ad8a-08968a412785' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='18ee4ac6-9a1c-4bfa-ad8a-08968a412785';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Urine prenancy test', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '18ee4ac6-9a1c-4bfa-ad8a-08968a412785' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='18ee4ac6-9a1c-4bfa-ad8a-08968a412785';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Proteinuria dipstick', (select name from clinlims.test where guid = 'a44239e6-bd4d-4ffa-98eb-549b5102207a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a44239e6-bd4d-4ffa-98eb-549b5102207a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Proteinuria dipstick', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'a44239e6-bd4d-4ffa-98eb-549b5102207a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a44239e6-bd4d-4ffa-98eb-549b5102207a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'White Blood Cells Count (WBC)', (select name from clinlims.test where guid = 'e08bdd35-b7e4-4910-ae73-da5b6447e901' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='e08bdd35-b7e4-4910-ae73-da5b6447e901';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'White Blood Cells Count (WBC)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'e08bdd35-b7e4-4910-ae73-da5b6447e901' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='e08bdd35-b7e4-4910-ae73-da5b6447e901';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Red Blood Cells Count (RBC)', (select name from clinlims.test where guid = '25249ec2-0dbb-4a45-8c97-836c175ab183' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='25249ec2-0dbb-4a45-8c97-836c175ab183';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Red Blood Cells Count (RBC)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '25249ec2-0dbb-4a45-8c97-836c175ab183' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='25249ec2-0dbb-4a45-8c97-836c175ab183';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hemoglobin', (select name from clinlims.test where guid = '466b3775-e117-4268-92a7-3d3de95d43b3' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='466b3775-e117-4268-92a7-3d3de95d43b3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hemoglobin', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '466b3775-e117-4268-92a7-3d3de95d43b3' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='466b3775-e117-4268-92a7-3d3de95d43b3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hematocrit', (select name from clinlims.test where guid = '4dea29d7-09aa-4ae4-92e9-aed3cde44462' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='4dea29d7-09aa-4ae4-92e9-aed3cde44462';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hematocrit', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '4dea29d7-09aa-4ae4-92e9-aed3cde44462' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='4dea29d7-09aa-4ae4-92e9-aed3cde44462';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Medium corpuscular volum', (select name from clinlims.test where guid = '8980331d-7d69-4364-a793-e1855ea58360' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='8980331d-7d69-4364-a793-e1855ea58360';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Medium corpuscular volum', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '8980331d-7d69-4364-a793-e1855ea58360' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='8980331d-7d69-4364-a793-e1855ea58360';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'TMCH', (select name from clinlims.test where guid = '78e49ba2-72f8-49df-99f1-5fc2a3c0914c' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='78e49ba2-72f8-49df-99f1-5fc2a3c0914c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'TMCH', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '78e49ba2-72f8-49df-99f1-5fc2a3c0914c' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='78e49ba2-72f8-49df-99f1-5fc2a3c0914c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CMCH', (select name from clinlims.test where guid = 'a7d20177-a559-4829-ad77-94b9b1ff025b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a7d20177-a559-4829-ad77-94b9b1ff025b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CMCH', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'a7d20177-a559-4829-ad77-94b9b1ff025b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a7d20177-a559-4829-ad77-94b9b1ff025b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Platelets', (select name from clinlims.test where guid = '17ff4ca7-b8b6-44a1-bae0-97f38affc35c' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='17ff4ca7-b8b6-44a1-bae0-97f38affc35c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Platelets', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '17ff4ca7-b8b6-44a1-bae0-97f38affc35c' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='17ff4ca7-b8b6-44a1-bae0-97f38affc35c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Neutrophiles (%)', (select name from clinlims.test where guid = 'c8979eb1-f975-4b77-9963-1328e95c5338' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='c8979eb1-f975-4b77-9963-1328e95c5338';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Neutrophiles (%)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'c8979eb1-f975-4b77-9963-1328e95c5338' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='c8979eb1-f975-4b77-9963-1328e95c5338';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Neutrophiles', (select name from clinlims.test where guid = '5bf892d4-4b5d-4f7f-9fea-d54c8d9631df' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='5bf892d4-4b5d-4f7f-9fea-d54c8d9631df';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Neutrophiles', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '5bf892d4-4b5d-4f7f-9fea-d54c8d9631df' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='5bf892d4-4b5d-4f7f-9fea-d54c8d9631df';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Eosinophiles (%)', (select name from clinlims.test where guid = '0cdc2eed-19ea-4c36-beed-662273852506' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='0cdc2eed-19ea-4c36-beed-662273852506';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Eosinophiles (%)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '0cdc2eed-19ea-4c36-beed-662273852506' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='0cdc2eed-19ea-4c36-beed-662273852506';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Eosinophiles', (select name from clinlims.test where guid = 'f0bbc211-66d2-4219-a377-79a9869a8413' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='f0bbc211-66d2-4219-a377-79a9869a8413';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Eosinophiles', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'f0bbc211-66d2-4219-a377-79a9869a8413' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='f0bbc211-66d2-4219-a377-79a9869a8413';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Basophiles (%)', (select name from clinlims.test where guid = 'e79dba96-ce3e-4b3c-945b-a73f7fa4b862' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='e79dba96-ce3e-4b3c-945b-a73f7fa4b862';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Basophiles (%)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'e79dba96-ce3e-4b3c-945b-a73f7fa4b862' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='e79dba96-ce3e-4b3c-945b-a73f7fa4b862';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Basophiles', (select name from clinlims.test where guid = 'febdc29b-78ac-48f8-afba-b7da2a1fb3c2' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='febdc29b-78ac-48f8-afba-b7da2a1fb3c2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Basophiles', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'febdc29b-78ac-48f8-afba-b7da2a1fb3c2' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='febdc29b-78ac-48f8-afba-b7da2a1fb3c2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Lymphocytes (%)', (select name from clinlims.test where guid = '7a4f53a3-b1ab-457b-b928-6c69f30aeb27' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='7a4f53a3-b1ab-457b-b928-6c69f30aeb27';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Lymphocytes (%)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '7a4f53a3-b1ab-457b-b928-6c69f30aeb27' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='7a4f53a3-b1ab-457b-b928-6c69f30aeb27';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Lymphocytes (Abs)', (select name from clinlims.test where guid = 'e182fd13-38e6-4c5e-9ea7-f3635a957a78' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='e182fd13-38e6-4c5e-9ea7-f3635a957a78';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Lymphocytes (Abs)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'e182fd13-38e6-4c5e-9ea7-f3635a957a78' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='e182fd13-38e6-4c5e-9ea7-f3635a957a78';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Monocytes (%)', (select name from clinlims.test where guid = '79391ea9-9a96-484c-b8d7-c261a5cfffc0' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='79391ea9-9a96-484c-b8d7-c261a5cfffc0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Monocytes (%)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '79391ea9-9a96-484c-b8d7-c261a5cfffc0' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='79391ea9-9a96-484c-b8d7-c261a5cfffc0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Monocytes (Abs)', (select name from clinlims.test where guid = '545d87fd-7959-4d53-bf9a-c87a7d2af680' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='545d87fd-7959-4d53-bf9a-c87a7d2af680';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Monocytes (Abs)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '545d87fd-7959-4d53-bf9a-c87a7d2af680' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='545d87fd-7959-4d53-bf9a-c87a7d2af680';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV rapid test HIV', (select name from clinlims.test where guid = 'c200173b-d972-4e54-9c4f-5271290a8ed8' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='c200173b-d972-4e54-9c4f-5271290a8ed8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV rapid test HIV', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'c200173b-d972-4e54-9c4f-5271290a8ed8' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='c200173b-d972-4e54-9c4f-5271290a8ed8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV rapid test HIV', (select name from clinlims.test where guid = 'd0ec0286-44cd-485d-ac0c-87d3664198a6' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d0ec0286-44cd-485d-ac0c-87d3664198a6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV rapid test HIV', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'd0ec0286-44cd-485d-ac0c-87d3664198a6' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d0ec0286-44cd-485d-ac0c-87d3664198a6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV rapid test HIV', (select name from clinlims.test where guid = '0ac0b77e-672c-4eee-ae71-c05a0fee086b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='0ac0b77e-672c-4eee-ae71-c05a0fee086b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV rapid test HIV', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '0ac0b77e-672c-4eee-ae71-c05a0fee086b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='0ac0b77e-672c-4eee-ae71-c05a0fee086b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CD4 Absolute count (mm3)', (select name from clinlims.test where guid = '1d329af4-e1af-43c8-a533-5000bfdd868a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='1d329af4-e1af-43c8-a533-5000bfdd868a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CD4 Absolute count (mm3)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '1d329af4-e1af-43c8-a533-5000bfdd868a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='1d329af4-e1af-43c8-a533-5000bfdd868a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CD4 percent  (%)', (select name from clinlims.test where guid = '614652de-5e04-4fe7-a897-77d976317d2b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='614652de-5e04-4fe7-a897-77d976317d2b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CD4 percent  (%)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '614652de-5e04-4fe7-a897-77d976317d2b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='614652de-5e04-4fe7-a897-77d976317d2b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HBsAg (Hepatitis B surface antigen)', (select name from clinlims.test where guid = 'bc3ab337-3287-477b-9f52-cb0d0db4f06a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='bc3ab337-3287-477b-9f52-cb0d0db4f06a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HBsAg (Hepatitis B surface antigen)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'bc3ab337-3287-477b-9f52-cb0d0db4f06a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='bc3ab337-3287-477b-9f52-cb0d0db4f06a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Viral load', (select name from clinlims.test where guid = '5c37ba62-1e04-46ab-8db6-82db9c6fbb5e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='5c37ba62-1e04-46ab-8db6-82db9c6fbb5e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Viral load', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '5c37ba62-1e04-46ab-8db6-82db9c6fbb5e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='5c37ba62-1e04-46ab-8db6-82db9c6fbb5e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'ARV resistance', (select name from clinlims.test where guid = 'd83c247c-ccf4-4b9a-9016-357085672fac' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d83c247c-ccf4-4b9a-9016-357085672fac';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'ARV resistance', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'd83c247c-ccf4-4b9a-9016-357085672fac' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d83c247c-ccf4-4b9a-9016-357085672fac';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Western blot HIV', (select name from clinlims.test where guid = '6e654c26-0a55-4867-a168-e55e6516fd1e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='6e654c26-0a55-4867-a168-e55e6516fd1e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Western blot HIV', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '6e654c26-0a55-4867-a168-e55e6516fd1e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='6e654c26-0a55-4867-a168-e55e6516fd1e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Western blot HIV', (select name from clinlims.test where guid = 'ace3f934-4b94-4fbe-8a82-1f4cec317347' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='ace3f934-4b94-4fbe-8a82-1f4cec317347';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Western blot HIV', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'ace3f934-4b94-4fbe-8a82-1f4cec317347' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='ace3f934-4b94-4fbe-8a82-1f4cec317347';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Bioline', (select name from clinlims.test where guid = '9b43af6e-25e4-4ebf-9724-d7bdf71a62c5' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='9b43af6e-25e4-4ebf-9724-d7bdf71a62c5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Bioline', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '9b43af6e-25e4-4ebf-9724-d7bdf71a62c5' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='9b43af6e-25e4-4ebf-9724-d7bdf71a62c5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Bioline', (select name from clinlims.test where guid = '2e225bfc-e9a3-4ce2-aefb-16364cb2df3b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='2e225bfc-e9a3-4ce2-aefb-16364cb2df3b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Bioline', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '2e225bfc-e9a3-4ce2-aefb-16364cb2df3b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='2e225bfc-e9a3-4ce2-aefb-16364cb2df3b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Bioline', (select name from clinlims.test where guid = 'd7384982-3646-409e-a37d-019da248623f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d7384982-3646-409e-a37d-019da248623f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Bioline', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'd7384982-3646-409e-a37d-019da248623f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d7384982-3646-409e-a37d-019da248623f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Genie III', (select name from clinlims.test where guid = '8a529b1a-72e6-4562-96ac-885b758f3280' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='8a529b1a-72e6-4562-96ac-885b758f3280';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Genie III', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '8a529b1a-72e6-4562-96ac-885b758f3280' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='8a529b1a-72e6-4562-96ac-885b758f3280';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Genie III', (select name from clinlims.test where guid = '098def0a-779e-496a-afd2-b19ca94c4c94' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='098def0a-779e-496a-afd2-b19ca94c4c94';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Genie III', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '098def0a-779e-496a-afd2-b19ca94c4c94' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='098def0a-779e-496a-afd2-b19ca94c4c94';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Genie III', (select name from clinlims.test where guid = 'bea00384-ce9f-44ec-aca1-81c58edadb43' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='bea00384-ce9f-44ec-aca1-81c58edadb43';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Genie III', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'bea00384-ce9f-44ec-aca1-81c58edadb43' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='bea00384-ce9f-44ec-aca1-81c58edadb43';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Murex', (select name from clinlims.test where guid = '74bb0da6-c7fe-44f8-810c-a72d38fd8fc7' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='74bb0da6-c7fe-44f8-810c-a72d38fd8fc7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Murex', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '74bb0da6-c7fe-44f8-810c-a72d38fd8fc7' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='74bb0da6-c7fe-44f8-810c-a72d38fd8fc7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Murex', (select name from clinlims.test where guid = '2587b1f1-4b82-4d6f-a64e-e4fcc2230e1c' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='2587b1f1-4b82-4d6f-a64e-e4fcc2230e1c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Murex', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '2587b1f1-4b82-4d6f-a64e-e4fcc2230e1c' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='2587b1f1-4b82-4d6f-a64e-e4fcc2230e1c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Vironostika', (select name from clinlims.test where guid = '01f90ef2-6afe-4928-8249-2e64e4c02e88' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='01f90ef2-6afe-4928-8249-2e64e4c02e88';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Vironostika', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '01f90ef2-6afe-4928-8249-2e64e4c02e88' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='01f90ef2-6afe-4928-8249-2e64e4c02e88';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Vironostika', (select name from clinlims.test where guid = '704c585b-8e95-4550-9b11-573ab1c2cfb5' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='704c585b-8e95-4550-9b11-573ab1c2cfb5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Vironostika', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '704c585b-8e95-4550-9b11-573ab1c2cfb5' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='704c585b-8e95-4550-9b11-573ab1c2cfb5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'P24 Ag', (select name from clinlims.test where guid = 'ac8003af-6187-4ea0-8410-c320fb9d7dda' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='ac8003af-6187-4ea0-8410-c320fb9d7dda';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'P24 Ag', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'ac8003af-6187-4ea0-8410-c320fb9d7dda' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='ac8003af-6187-4ea0-8410-c320fb9d7dda';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'P24 Ag', (select name from clinlims.test where guid = '103fc942-99f1-4991-858b-bd66aa9c3374' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='103fc942-99f1-4991-858b-bd66aa9c3374';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'P24 Ag', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '103fc942-99f1-4991-858b-bd66aa9c3374' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='103fc942-99f1-4991-858b-bd66aa9c3374';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Stat-Pak', (select name from clinlims.test where guid = '05ab0ed9-b9cf-4d9b-9362-8c4f4ebbd614' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='05ab0ed9-b9cf-4d9b-9362-8c4f4ebbd614';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Stat-Pak', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '05ab0ed9-b9cf-4d9b-9362-8c4f4ebbd614' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='05ab0ed9-b9cf-4d9b-9362-8c4f4ebbd614';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Stat-Pak', (select name from clinlims.test where guid = 'df43c9a3-adc5-4f39-946c-0fdc63692a8d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='df43c9a3-adc5-4f39-946c-0fdc63692a8d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Stat-Pak', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'df43c9a3-adc5-4f39-946c-0fdc63692a8d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='df43c9a3-adc5-4f39-946c-0fdc63692a8d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Stat-Pak', (select name from clinlims.test where guid = 'd7c06cb7-f038-4a73-a432-7ca9e25062c5' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d7c06cb7-f038-4a73-a432-7ca9e25062c5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Stat-Pak', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'd7c06cb7-f038-4a73-a432-7ca9e25062c5' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d7c06cb7-f038-4a73-a432-7ca9e25062c5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Determine', (select name from clinlims.test where guid = '679972ca-dce3-4e2d-bd13-8c70b24c299e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='679972ca-dce3-4e2d-bd13-8c70b24c299e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Determine', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '679972ca-dce3-4e2d-bd13-8c70b24c299e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='679972ca-dce3-4e2d-bd13-8c70b24c299e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Murex', (select name from clinlims.test where guid = '585a6c2a-93ca-454f-ace3-f9a5fa61d33d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='585a6c2a-93ca-454f-ace3-f9a5fa61d33d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Murex', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '585a6c2a-93ca-454f-ace3-f9a5fa61d33d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='585a6c2a-93ca-454f-ace3-f9a5fa61d33d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Vironostika', (select name from clinlims.test where guid = '1f6ebdb4-0305-4fe6-8112-4b8cc8644298' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='1f6ebdb4-0305-4fe6-8112-4b8cc8644298';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Vironostika', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '1f6ebdb4-0305-4fe6-8112-4b8cc8644298' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='1f6ebdb4-0305-4fe6-8112-4b8cc8644298';
