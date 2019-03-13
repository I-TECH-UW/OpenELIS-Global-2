INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'GPT/ALAT', (select name from clinlims.test where guid = '76d7b3bf-8aa4-4ff4-89a9-b4cc080f538c' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='76d7b3bf-8aa4-4ff4-89a9-b4cc080f538c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'GPT/ALAT', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '76d7b3bf-8aa4-4ff4-89a9-b4cc080f538c' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='76d7b3bf-8aa4-4ff4-89a9-b4cc080f538c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'GOT/ASAT', (select name from clinlims.test where guid = '5afa14cc-318e-436a-b120-a8b7cf48179f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='5afa14cc-318e-436a-b120-a8b7cf48179f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'GOT/ASAT', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '5afa14cc-318e-436a-b120-a8b7cf48179f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='5afa14cc-318e-436a-b120-a8b7cf48179f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Glucose', (select name from clinlims.test where guid = '10890eac-4952-4d87-adb9-b6518ff5747f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='10890eac-4952-4d87-adb9-b6518ff5747f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Glucose', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '10890eac-4952-4d87-adb9-b6518ff5747f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='10890eac-4952-4d87-adb9-b6518ff5747f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Creatinine', (select name from clinlims.test where guid = 'fa7322b2-213a-4516-a8b4-be5f9edeedf8' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='fa7322b2-213a-4516-a8b4-be5f9edeedf8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Creatinine', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'fa7322b2-213a-4516-a8b4-be5f9edeedf8' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='fa7322b2-213a-4516-a8b4-be5f9edeedf8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Amylase', (select name from clinlims.test where guid = 'e83d1ad7-cf15-42eb-bbcd-bf14d4742932' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='e83d1ad7-cf15-42eb-bbcd-bf14d4742932';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Amylase', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'e83d1ad7-cf15-42eb-bbcd-bf14d4742932' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='e83d1ad7-cf15-42eb-bbcd-bf14d4742932';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Albumin', (select name from clinlims.test where guid = '6509fe9e-008f-43b2-9504-8a011940883f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='6509fe9e-008f-43b2-9504-8a011940883f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Albumin', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '6509fe9e-008f-43b2-9504-8a011940883f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='6509fe9e-008f-43b2-9504-8a011940883f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Total cholesterol', (select name from clinlims.test where guid = 'cdeeb973-3936-42a6-94cb-3b64ff6fbfd4' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='cdeeb973-3936-42a6-94cb-3b64ff6fbfd4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Total cholesterol', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'cdeeb973-3936-42a6-94cb-3b64ff6fbfd4' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='cdeeb973-3936-42a6-94cb-3b64ff6fbfd4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HDL cholesterol', (select name from clinlims.test where guid = '66cde6af-39fe-46e7-98b3-249e7cd96d18' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='66cde6af-39fe-46e7-98b3-249e7cd96d18';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HDL cholesterol', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '66cde6af-39fe-46e7-98b3-249e7cd96d18' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='66cde6af-39fe-46e7-98b3-249e7cd96d18';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Triglicerides', (select name from clinlims.test where guid = 'dc8bd050-ae38-4826-b01d-4f2c1f4662d7' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='dc8bd050-ae38-4826-b01d-4f2c1f4662d7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Triglicerides', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'dc8bd050-ae38-4826-b01d-4f2c1f4662d7' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='dc8bd050-ae38-4826-b01d-4f2c1f4662d7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Beta HCG', (select name from clinlims.test where guid = '9b4b8d12-25ec-4064-b635-af3dfd3f2d18' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='9b4b8d12-25ec-4064-b635-af3dfd3f2d18';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Beta HCG', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '9b4b8d12-25ec-4064-b635-af3dfd3f2d18' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='9b4b8d12-25ec-4064-b635-af3dfd3f2d18';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Urine prenancy test', (select name from clinlims.test where guid = 'a9372bc3-72d6-452f-8e17-9e87ec5b6885' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a9372bc3-72d6-452f-8e17-9e87ec5b6885';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Urine prenancy test', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'a9372bc3-72d6-452f-8e17-9e87ec5b6885' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a9372bc3-72d6-452f-8e17-9e87ec5b6885';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Proteinuria dipstick', (select name from clinlims.test where guid = '9232e700-5c43-4d95-ad52-da3afec750be' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='9232e700-5c43-4d95-ad52-da3afec750be';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Proteinuria dipstick', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '9232e700-5c43-4d95-ad52-da3afec750be' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='9232e700-5c43-4d95-ad52-da3afec750be';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'White Blood Cells Count (WBC)', (select name from clinlims.test where guid = '6bfe0fd2-ed2a-4442-8995-dbe3b9025545' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='6bfe0fd2-ed2a-4442-8995-dbe3b9025545';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'White Blood Cells Count (WBC)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '6bfe0fd2-ed2a-4442-8995-dbe3b9025545' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='6bfe0fd2-ed2a-4442-8995-dbe3b9025545';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Red Blood Cells Count (RBC)', (select name from clinlims.test where guid = '52a6bc4e-a7f8-4bb0-a30e-bc75bf1069e1' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='52a6bc4e-a7f8-4bb0-a30e-bc75bf1069e1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Red Blood Cells Count (RBC)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '52a6bc4e-a7f8-4bb0-a30e-bc75bf1069e1' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='52a6bc4e-a7f8-4bb0-a30e-bc75bf1069e1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hemoglobin', (select name from clinlims.test where guid = '7b2f2cd4-852f-46cd-bb4d-14bdf7edb7bb' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='7b2f2cd4-852f-46cd-bb4d-14bdf7edb7bb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hemoglobin', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '7b2f2cd4-852f-46cd-bb4d-14bdf7edb7bb' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='7b2f2cd4-852f-46cd-bb4d-14bdf7edb7bb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hematocrit', (select name from clinlims.test where guid = '34e8dfa8-7971-47fe-b259-b55dac61bcf5' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='34e8dfa8-7971-47fe-b259-b55dac61bcf5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hematocrit', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '34e8dfa8-7971-47fe-b259-b55dac61bcf5' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='34e8dfa8-7971-47fe-b259-b55dac61bcf5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Medium corpuscular volum', (select name from clinlims.test where guid = 'e280b836-77f4-488b-8d5e-4cc835535a87' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='e280b836-77f4-488b-8d5e-4cc835535a87';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Medium corpuscular volum', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'e280b836-77f4-488b-8d5e-4cc835535a87' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='e280b836-77f4-488b-8d5e-4cc835535a87';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'TMCH', (select name from clinlims.test where guid = '2d1487d5-4c36-4518-8a9d-87a66ba2ec5c' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='2d1487d5-4c36-4518-8a9d-87a66ba2ec5c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'TMCH', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '2d1487d5-4c36-4518-8a9d-87a66ba2ec5c' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='2d1487d5-4c36-4518-8a9d-87a66ba2ec5c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CMCH', (select name from clinlims.test where guid = '81f3ecbb-9f81-4061-a0be-4ae66974dc67' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='81f3ecbb-9f81-4061-a0be-4ae66974dc67';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CMCH', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '81f3ecbb-9f81-4061-a0be-4ae66974dc67' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='81f3ecbb-9f81-4061-a0be-4ae66974dc67';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Platelets', (select name from clinlims.test where guid = '1e9d1eb7-5916-4420-b9d1-981bcc65b730' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='1e9d1eb7-5916-4420-b9d1-981bcc65b730';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Platelets', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '1e9d1eb7-5916-4420-b9d1-981bcc65b730' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='1e9d1eb7-5916-4420-b9d1-981bcc65b730';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Neutrophiles (%)', (select name from clinlims.test where guid = '97193f15-1bc8-4325-a90b-3201749d86d3' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='97193f15-1bc8-4325-a90b-3201749d86d3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Neutrophiles (%)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '97193f15-1bc8-4325-a90b-3201749d86d3' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='97193f15-1bc8-4325-a90b-3201749d86d3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Neutrophiles', (select name from clinlims.test where guid = '6c79d339-e05e-4604-99d4-12a471965424' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='6c79d339-e05e-4604-99d4-12a471965424';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Neutrophiles', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '6c79d339-e05e-4604-99d4-12a471965424' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='6c79d339-e05e-4604-99d4-12a471965424';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Eosinophiles (%)', (select name from clinlims.test where guid = '265ec36e-a7cf-4bd5-9e0e-cee88fdb1706' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='265ec36e-a7cf-4bd5-9e0e-cee88fdb1706';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Eosinophiles (%)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '265ec36e-a7cf-4bd5-9e0e-cee88fdb1706' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='265ec36e-a7cf-4bd5-9e0e-cee88fdb1706';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Eosinophiles', (select name from clinlims.test where guid = 'a12deafc-eef0-4530-8506-96a141466187' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a12deafc-eef0-4530-8506-96a141466187';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Eosinophiles', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'a12deafc-eef0-4530-8506-96a141466187' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a12deafc-eef0-4530-8506-96a141466187';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Basophiles (%)', (select name from clinlims.test where guid = 'da0456cd-6ecf-4aa2-a526-d3843e588110' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='da0456cd-6ecf-4aa2-a526-d3843e588110';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Basophiles (%)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'da0456cd-6ecf-4aa2-a526-d3843e588110' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='da0456cd-6ecf-4aa2-a526-d3843e588110';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Basophiles', (select name from clinlims.test where guid = 'b4804952-12b2-4d37-900f-6a475d5071ba' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='b4804952-12b2-4d37-900f-6a475d5071ba';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Basophiles', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'b4804952-12b2-4d37-900f-6a475d5071ba' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='b4804952-12b2-4d37-900f-6a475d5071ba';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Lymphocytes (%)', (select name from clinlims.test where guid = '3f679586-d37e-4f5d-8752-23dd7a0d8573' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3f679586-d37e-4f5d-8752-23dd7a0d8573';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Lymphocytes (%)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '3f679586-d37e-4f5d-8752-23dd7a0d8573' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3f679586-d37e-4f5d-8752-23dd7a0d8573';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Lymphocytes (Abs)', (select name from clinlims.test where guid = 'ece8dabd-b89d-4771-9b10-5c88cf10a8ee' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='ece8dabd-b89d-4771-9b10-5c88cf10a8ee';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Lymphocytes (Abs)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'ece8dabd-b89d-4771-9b10-5c88cf10a8ee' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='ece8dabd-b89d-4771-9b10-5c88cf10a8ee';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Monocytes (%)', (select name from clinlims.test where guid = '0f2e0c93-28e6-4595-91ed-a1b4a00e3396' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='0f2e0c93-28e6-4595-91ed-a1b4a00e3396';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Monocytes (%)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '0f2e0c93-28e6-4595-91ed-a1b4a00e3396' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='0f2e0c93-28e6-4595-91ed-a1b4a00e3396';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Monocytes (Abs)', (select name from clinlims.test where guid = 'd5a7484b-b97d-4c03-a837-f36f6d1f318b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d5a7484b-b97d-4c03-a837-f36f6d1f318b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Monocytes (Abs)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'd5a7484b-b97d-4c03-a837-f36f6d1f318b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d5a7484b-b97d-4c03-a837-f36f6d1f318b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV rapid test HIV', (select name from clinlims.test where guid = 'b69a551f-ccf9-4c88-ace9-1039fe49fd8d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='b69a551f-ccf9-4c88-ace9-1039fe49fd8d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV rapid test HIV', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'b69a551f-ccf9-4c88-ace9-1039fe49fd8d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='b69a551f-ccf9-4c88-ace9-1039fe49fd8d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV rapid test HIV', (select name from clinlims.test where guid = '8e3e8455-499b-4626-b866-1c3fe2302ea5' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='8e3e8455-499b-4626-b866-1c3fe2302ea5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV rapid test HIV', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '8e3e8455-499b-4626-b866-1c3fe2302ea5' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='8e3e8455-499b-4626-b866-1c3fe2302ea5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV rapid test HIV', (select name from clinlims.test where guid = '817539b5-e2ee-4407-888f-3695917d40cc' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='817539b5-e2ee-4407-888f-3695917d40cc';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV rapid test HIV', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '817539b5-e2ee-4407-888f-3695917d40cc' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='817539b5-e2ee-4407-888f-3695917d40cc';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CD4 Absolute count (mm3)', (select name from clinlims.test where guid = '8416a8c7-0a42-4f2a-b96b-2335f7660f3e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='8416a8c7-0a42-4f2a-b96b-2335f7660f3e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CD4 Absolute count (mm3)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '8416a8c7-0a42-4f2a-b96b-2335f7660f3e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='8416a8c7-0a42-4f2a-b96b-2335f7660f3e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CD4 percent  (%)', (select name from clinlims.test where guid = '94e1365c-ed29-44bd-bcad-181c6a616867' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='94e1365c-ed29-44bd-bcad-181c6a616867';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CD4 percent  (%)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '94e1365c-ed29-44bd-bcad-181c6a616867' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='94e1365c-ed29-44bd-bcad-181c6a616867';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HBsAg (Hepatitis B surface antigen)', (select name from clinlims.test where guid = '135d11cd-50e9-41c8-a1d8-2c1ca3fcb7f4' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='135d11cd-50e9-41c8-a1d8-2c1ca3fcb7f4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HBsAg (Hepatitis B surface antigen)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '135d11cd-50e9-41c8-a1d8-2c1ca3fcb7f4' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='135d11cd-50e9-41c8-a1d8-2c1ca3fcb7f4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Viral load', (select name from clinlims.test where guid = '34b061a8-d71f-450f-bc54-f728980d0c37' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='34b061a8-d71f-450f-bc54-f728980d0c37';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Viral load', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '34b061a8-d71f-450f-bc54-f728980d0c37' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='34b061a8-d71f-450f-bc54-f728980d0c37';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'ARV resistance', (select name from clinlims.test where guid = '73e029c6-0f66-428f-a873-5ed0ad219a1e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='73e029c6-0f66-428f-a873-5ed0ad219a1e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'ARV resistance', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '73e029c6-0f66-428f-a873-5ed0ad219a1e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='73e029c6-0f66-428f-a873-5ed0ad219a1e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Western blot HIV', (select name from clinlims.test where guid = '5ebaea9e-ce42-456b-ace2-08d720ff5133' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='5ebaea9e-ce42-456b-ace2-08d720ff5133';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Western blot HIV', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '5ebaea9e-ce42-456b-ace2-08d720ff5133' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='5ebaea9e-ce42-456b-ace2-08d720ff5133';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Western blot HIV', (select name from clinlims.test where guid = '8db22711-be11-4d1c-ab9c-5538af6bfe94' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='8db22711-be11-4d1c-ab9c-5538af6bfe94';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Western blot HIV', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '8db22711-be11-4d1c-ab9c-5538af6bfe94' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='8db22711-be11-4d1c-ab9c-5538af6bfe94';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Bioline', (select name from clinlims.test where guid = 'c56874d2-c745-4a7e-884e-c0949cd4ce36' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='c56874d2-c745-4a7e-884e-c0949cd4ce36';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Bioline', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'c56874d2-c745-4a7e-884e-c0949cd4ce36' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='c56874d2-c745-4a7e-884e-c0949cd4ce36';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Bioline', (select name from clinlims.test where guid = '3a9fe4d6-7240-4c79-be8b-8c4eee22e6b5' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3a9fe4d6-7240-4c79-be8b-8c4eee22e6b5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Bioline', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '3a9fe4d6-7240-4c79-be8b-8c4eee22e6b5' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3a9fe4d6-7240-4c79-be8b-8c4eee22e6b5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Bioline', (select name from clinlims.test where guid = '7809cc03-e7bd-461d-b2a7-10e50a92060e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='7809cc03-e7bd-461d-b2a7-10e50a92060e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Bioline', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '7809cc03-e7bd-461d-b2a7-10e50a92060e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='7809cc03-e7bd-461d-b2a7-10e50a92060e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Genie III', (select name from clinlims.test where guid = 'a270db7d-1487-425d-8022-4609a7cd134b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a270db7d-1487-425d-8022-4609a7cd134b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Genie III', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'a270db7d-1487-425d-8022-4609a7cd134b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a270db7d-1487-425d-8022-4609a7cd134b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Genie III', (select name from clinlims.test where guid = 'e0d7e8b3-17c6-4729-ac76-0c5354950e01' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='e0d7e8b3-17c6-4729-ac76-0c5354950e01';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Genie III', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'e0d7e8b3-17c6-4729-ac76-0c5354950e01' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='e0d7e8b3-17c6-4729-ac76-0c5354950e01';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Genie III', (select name from clinlims.test where guid = 'a794f0e4-39ed-407f-8235-50126608fce9' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a794f0e4-39ed-407f-8235-50126608fce9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Genie III', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'a794f0e4-39ed-407f-8235-50126608fce9' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a794f0e4-39ed-407f-8235-50126608fce9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Murex', (select name from clinlims.test where guid = 'a31c1d93-d8b1-4111-856b-7dab623d184a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a31c1d93-d8b1-4111-856b-7dab623d184a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Murex', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'a31c1d93-d8b1-4111-856b-7dab623d184a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a31c1d93-d8b1-4111-856b-7dab623d184a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Murex', (select name from clinlims.test where guid = '5c28051b-05b8-4ace-83d8-2922ea8d5015' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='5c28051b-05b8-4ace-83d8-2922ea8d5015';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Murex', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '5c28051b-05b8-4ace-83d8-2922ea8d5015' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='5c28051b-05b8-4ace-83d8-2922ea8d5015';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Vironostika', (select name from clinlims.test where guid = '0342b450-c089-49e8-9a83-48a3a5e27db4' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='0342b450-c089-49e8-9a83-48a3a5e27db4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Vironostika', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '0342b450-c089-49e8-9a83-48a3a5e27db4' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='0342b450-c089-49e8-9a83-48a3a5e27db4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Vironostika', (select name from clinlims.test where guid = '1da6f79f-c9d7-4439-a000-21254b44ed4b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='1da6f79f-c9d7-4439-a000-21254b44ed4b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Vironostika', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '1da6f79f-c9d7-4439-a000-21254b44ed4b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='1da6f79f-c9d7-4439-a000-21254b44ed4b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'P24 Ag', (select name from clinlims.test where guid = '455023eb-1dab-4581-bbd9-1fdf20458fbd' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='455023eb-1dab-4581-bbd9-1fdf20458fbd';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'P24 Ag', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '455023eb-1dab-4581-bbd9-1fdf20458fbd' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='455023eb-1dab-4581-bbd9-1fdf20458fbd';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'P24 Ag', (select name from clinlims.test where guid = '9f573e06-6b2c-471e-87c4-5274b990e5ff' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='9f573e06-6b2c-471e-87c4-5274b990e5ff';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'P24 Ag', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '9f573e06-6b2c-471e-87c4-5274b990e5ff' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='9f573e06-6b2c-471e-87c4-5274b990e5ff';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Thick Film', (select name from clinlims.test where guid = '2d615af1-035e-49da-954e-b81fd6e500eb' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='2d615af1-035e-49da-954e-b81fd6e500eb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Thick Film', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '2d615af1-035e-49da-954e-b81fd6e500eb' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='2d615af1-035e-49da-954e-b81fd6e500eb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'parasitemy', (select name from clinlims.test where guid = '77202c1f-43f4-4974-88d3-2adbd2c625aa' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='77202c1f-43f4-4974-88d3-2adbd2c625aa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'parasitemy', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '77202c1f-43f4-4974-88d3-2adbd2c625aa' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='77202c1f-43f4-4974-88d3-2adbd2c625aa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Thil film', (select name from clinlims.test where guid = 'df01b2e1-6617-4fc0-8fe1-357ab78a34f8' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='df01b2e1-6617-4fc0-8fe1-357ab78a34f8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Thil film', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'df01b2e1-6617-4fc0-8fe1-357ab78a34f8' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='df01b2e1-6617-4fc0-8fe1-357ab78a34f8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'others species', (select name from clinlims.test where guid = 'd3d6b718-4685-4d7b-9cd1-65eb287927c9' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d3d6b718-4685-4d7b-9cd1-65eb287927c9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'others species', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'd3d6b718-4685-4d7b-9cd1-65eb287927c9' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d3d6b718-4685-4d7b-9cd1-65eb287927c9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '9d410f00-ca32-422f-b158-1ace822535e7' ), (select name from clinlims.test where guid = '9d410f00-ca32-422f-b158-1ace822535e7' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='9d410f00-ca32-422f-b158-1ace822535e7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '9d410f00-ca32-422f-b158-1ace822535e7' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '9d410f00-ca32-422f-b158-1ace822535e7' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='9d410f00-ca32-422f-b158-1ace822535e7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '1fcded5c-da6a-467f-9abe-cb3e3d9d681f' ), (select name from clinlims.test where guid = '1fcded5c-da6a-467f-9abe-cb3e3d9d681f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='1fcded5c-da6a-467f-9abe-cb3e3d9d681f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '1fcded5c-da6a-467f-9abe-cb3e3d9d681f' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '1fcded5c-da6a-467f-9abe-cb3e3d9d681f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='1fcded5c-da6a-467f-9abe-cb3e3d9d681f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'ee1455ec-a757-4556-89a5-e1dcd98321cb' ), (select name from clinlims.test where guid = 'ee1455ec-a757-4556-89a5-e1dcd98321cb' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='ee1455ec-a757-4556-89a5-e1dcd98321cb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'ee1455ec-a757-4556-89a5-e1dcd98321cb' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'ee1455ec-a757-4556-89a5-e1dcd98321cb' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='ee1455ec-a757-4556-89a5-e1dcd98321cb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '08953786-92e4-4baf-9d8a-65f9eb2c73c7' ), (select name from clinlims.test where guid = '08953786-92e4-4baf-9d8a-65f9eb2c73c7' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='08953786-92e4-4baf-9d8a-65f9eb2c73c7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '08953786-92e4-4baf-9d8a-65f9eb2c73c7' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '08953786-92e4-4baf-9d8a-65f9eb2c73c7' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='08953786-92e4-4baf-9d8a-65f9eb2c73c7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'da5dad2f-8c7d-441f-bd57-0aa29a5684eb' ), (select name from clinlims.test where guid = 'da5dad2f-8c7d-441f-bd57-0aa29a5684eb' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='da5dad2f-8c7d-441f-bd57-0aa29a5684eb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'da5dad2f-8c7d-441f-bd57-0aa29a5684eb' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'da5dad2f-8c7d-441f-bd57-0aa29a5684eb' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='da5dad2f-8c7d-441f-bd57-0aa29a5684eb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '7c52c087-2ecc-41ae-8723-789fedf4233b' ), (select name from clinlims.test where guid = '7c52c087-2ecc-41ae-8723-789fedf4233b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='7c52c087-2ecc-41ae-8723-789fedf4233b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '7c52c087-2ecc-41ae-8723-789fedf4233b' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '7c52c087-2ecc-41ae-8723-789fedf4233b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='7c52c087-2ecc-41ae-8723-789fedf4233b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '1ed6e8c1-d2be-4bdf-ad0f-6cfaeb415671' ), (select name from clinlims.test where guid = '1ed6e8c1-d2be-4bdf-ad0f-6cfaeb415671' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='1ed6e8c1-d2be-4bdf-ad0f-6cfaeb415671';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '1ed6e8c1-d2be-4bdf-ad0f-6cfaeb415671' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '1ed6e8c1-d2be-4bdf-ad0f-6cfaeb415671' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='1ed6e8c1-d2be-4bdf-ad0f-6cfaeb415671';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '96573779-c641-4cc5-8713-c4a714726832' ), (select name from clinlims.test where guid = '96573779-c641-4cc5-8713-c4a714726832' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='96573779-c641-4cc5-8713-c4a714726832';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '96573779-c641-4cc5-8713-c4a714726832' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '96573779-c641-4cc5-8713-c4a714726832' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='96573779-c641-4cc5-8713-c4a714726832';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'c576e0da-704d-4130-8852-4f95dbd5fbfb' ), (select name from clinlims.test where guid = 'c576e0da-704d-4130-8852-4f95dbd5fbfb' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='c576e0da-704d-4130-8852-4f95dbd5fbfb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'c576e0da-704d-4130-8852-4f95dbd5fbfb' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'c576e0da-704d-4130-8852-4f95dbd5fbfb' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='c576e0da-704d-4130-8852-4f95dbd5fbfb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '11d6de4a-c653-47e7-a806-ed5333c7ab9d' ), (select name from clinlims.test where guid = '11d6de4a-c653-47e7-a806-ed5333c7ab9d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='11d6de4a-c653-47e7-a806-ed5333c7ab9d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '11d6de4a-c653-47e7-a806-ed5333c7ab9d' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '11d6de4a-c653-47e7-a806-ed5333c7ab9d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='11d6de4a-c653-47e7-a806-ed5333c7ab9d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '7270ff15-1fdd-4d8a-b331-983af6852cb4' ), (select name from clinlims.test where guid = '7270ff15-1fdd-4d8a-b331-983af6852cb4' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='7270ff15-1fdd-4d8a-b331-983af6852cb4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '7270ff15-1fdd-4d8a-b331-983af6852cb4' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '7270ff15-1fdd-4d8a-b331-983af6852cb4' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='7270ff15-1fdd-4d8a-b331-983af6852cb4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '1374faad-259c-4aa0-a786-46ba6c2aeb90' ), (select name from clinlims.test where guid = '1374faad-259c-4aa0-a786-46ba6c2aeb90' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='1374faad-259c-4aa0-a786-46ba6c2aeb90';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '1374faad-259c-4aa0-a786-46ba6c2aeb90' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '1374faad-259c-4aa0-a786-46ba6c2aeb90' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='1374faad-259c-4aa0-a786-46ba6c2aeb90';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'bfac8938-b393-479b-9d47-685a7b704582' ), (select name from clinlims.test where guid = 'bfac8938-b393-479b-9d47-685a7b704582' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='bfac8938-b393-479b-9d47-685a7b704582';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'bfac8938-b393-479b-9d47-685a7b704582' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'bfac8938-b393-479b-9d47-685a7b704582' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='bfac8938-b393-479b-9d47-685a7b704582';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '628b81a6-5fdd-4a05-b3ac-5a4e8a1d1906' ), (select name from clinlims.test where guid = '628b81a6-5fdd-4a05-b3ac-5a4e8a1d1906' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='628b81a6-5fdd-4a05-b3ac-5a4e8a1d1906';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '628b81a6-5fdd-4a05-b3ac-5a4e8a1d1906' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '628b81a6-5fdd-4a05-b3ac-5a4e8a1d1906' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='628b81a6-5fdd-4a05-b3ac-5a4e8a1d1906';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '96a926a6-6ee2-4ae9-850f-b491cac6423e' ), (select name from clinlims.test where guid = '96a926a6-6ee2-4ae9-850f-b491cac6423e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='96a926a6-6ee2-4ae9-850f-b491cac6423e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '96a926a6-6ee2-4ae9-850f-b491cac6423e' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '96a926a6-6ee2-4ae9-850f-b491cac6423e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='96a926a6-6ee2-4ae9-850f-b491cac6423e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '60ff4523-c72e-4cc8-8647-8d3a1e7d237d' ), (select name from clinlims.test where guid = '60ff4523-c72e-4cc8-8647-8d3a1e7d237d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='60ff4523-c72e-4cc8-8647-8d3a1e7d237d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '60ff4523-c72e-4cc8-8647-8d3a1e7d237d' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '60ff4523-c72e-4cc8-8647-8d3a1e7d237d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='60ff4523-c72e-4cc8-8647-8d3a1e7d237d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '0ea4b6ad-ee21-498a-970f-681b5048c526' ), (select name from clinlims.test where guid = '0ea4b6ad-ee21-498a-970f-681b5048c526' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='0ea4b6ad-ee21-498a-970f-681b5048c526';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '0ea4b6ad-ee21-498a-970f-681b5048c526' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '0ea4b6ad-ee21-498a-970f-681b5048c526' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='0ea4b6ad-ee21-498a-970f-681b5048c526';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'be4295e2-aeae-4573-961c-e09d9e8f4821' ), (select name from clinlims.test where guid = 'be4295e2-aeae-4573-961c-e09d9e8f4821' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='be4295e2-aeae-4573-961c-e09d9e8f4821';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'be4295e2-aeae-4573-961c-e09d9e8f4821' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'be4295e2-aeae-4573-961c-e09d9e8f4821' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='be4295e2-aeae-4573-961c-e09d9e8f4821';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '5fdd7dd0-df4f-4cfc-92a9-9686c00edaf1' ), (select name from clinlims.test where guid = '5fdd7dd0-df4f-4cfc-92a9-9686c00edaf1' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='5fdd7dd0-df4f-4cfc-92a9-9686c00edaf1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '5fdd7dd0-df4f-4cfc-92a9-9686c00edaf1' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '5fdd7dd0-df4f-4cfc-92a9-9686c00edaf1' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='5fdd7dd0-df4f-4cfc-92a9-9686c00edaf1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '5528bf06-9e9a-4c61-b1d3-425a28574bda' ), (select name from clinlims.test where guid = '5528bf06-9e9a-4c61-b1d3-425a28574bda' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='5528bf06-9e9a-4c61-b1d3-425a28574bda';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '5528bf06-9e9a-4c61-b1d3-425a28574bda' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '5528bf06-9e9a-4c61-b1d3-425a28574bda' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='5528bf06-9e9a-4c61-b1d3-425a28574bda';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'ef766869-5dae-41f0-a7dc-4777b9faa9d4' ), (select name from clinlims.test where guid = 'ef766869-5dae-41f0-a7dc-4777b9faa9d4' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='ef766869-5dae-41f0-a7dc-4777b9faa9d4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'ef766869-5dae-41f0-a7dc-4777b9faa9d4' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'ef766869-5dae-41f0-a7dc-4777b9faa9d4' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='ef766869-5dae-41f0-a7dc-4777b9faa9d4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '27da1af0-9289-4fc1-8173-23f36d53084d' ), (select name from clinlims.test where guid = '27da1af0-9289-4fc1-8173-23f36d53084d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='27da1af0-9289-4fc1-8173-23f36d53084d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '27da1af0-9289-4fc1-8173-23f36d53084d' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '27da1af0-9289-4fc1-8173-23f36d53084d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='27da1af0-9289-4fc1-8173-23f36d53084d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '1116f3c1-ef6d-4477-99e5-da96491aa674' ), (select name from clinlims.test where guid = '1116f3c1-ef6d-4477-99e5-da96491aa674' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='1116f3c1-ef6d-4477-99e5-da96491aa674';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '1116f3c1-ef6d-4477-99e5-da96491aa674' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '1116f3c1-ef6d-4477-99e5-da96491aa674' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='1116f3c1-ef6d-4477-99e5-da96491aa674';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '28b386eb-a604-4d35-a0ec-deef0b3add42' ), (select name from clinlims.test where guid = '28b386eb-a604-4d35-a0ec-deef0b3add42' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='28b386eb-a604-4d35-a0ec-deef0b3add42';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '28b386eb-a604-4d35-a0ec-deef0b3add42' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '28b386eb-a604-4d35-a0ec-deef0b3add42' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='28b386eb-a604-4d35-a0ec-deef0b3add42';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'fef166b1-ecd0-4b20-8b83-51caed628b40' ), (select name from clinlims.test where guid = 'fef166b1-ecd0-4b20-8b83-51caed628b40' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='fef166b1-ecd0-4b20-8b83-51caed628b40';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'fef166b1-ecd0-4b20-8b83-51caed628b40' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'fef166b1-ecd0-4b20-8b83-51caed628b40' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='fef166b1-ecd0-4b20-8b83-51caed628b40';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'cca5e201-629a-4af8-a5f0-19a6c934d33f' ), (select name from clinlims.test where guid = 'cca5e201-629a-4af8-a5f0-19a6c934d33f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='cca5e201-629a-4af8-a5f0-19a6c934d33f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'cca5e201-629a-4af8-a5f0-19a6c934d33f' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'cca5e201-629a-4af8-a5f0-19a6c934d33f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='cca5e201-629a-4af8-a5f0-19a6c934d33f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '888ebffd-5ddf-4cdb-891a-f1c2605ba0de' ), (select name from clinlims.test where guid = '888ebffd-5ddf-4cdb-891a-f1c2605ba0de' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='888ebffd-5ddf-4cdb-891a-f1c2605ba0de';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '888ebffd-5ddf-4cdb-891a-f1c2605ba0de' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '888ebffd-5ddf-4cdb-891a-f1c2605ba0de' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='888ebffd-5ddf-4cdb-891a-f1c2605ba0de';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '957e1fad-3a0e-4408-b686-124da04c395c' ), (select name from clinlims.test where guid = '957e1fad-3a0e-4408-b686-124da04c395c' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='957e1fad-3a0e-4408-b686-124da04c395c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '957e1fad-3a0e-4408-b686-124da04c395c' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '957e1fad-3a0e-4408-b686-124da04c395c' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='957e1fad-3a0e-4408-b686-124da04c395c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '3b343dc2-0370-4d59-91c5-692364488c67' ), (select name from clinlims.test where guid = '3b343dc2-0370-4d59-91c5-692364488c67' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3b343dc2-0370-4d59-91c5-692364488c67';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '3b343dc2-0370-4d59-91c5-692364488c67' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '3b343dc2-0370-4d59-91c5-692364488c67' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3b343dc2-0370-4d59-91c5-692364488c67';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '3a10ab5a-55cc-43e6-a3b8-f5c6398dd99f' ), (select name from clinlims.test where guid = '3a10ab5a-55cc-43e6-a3b8-f5c6398dd99f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3a10ab5a-55cc-43e6-a3b8-f5c6398dd99f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '3a10ab5a-55cc-43e6-a3b8-f5c6398dd99f' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '3a10ab5a-55cc-43e6-a3b8-f5c6398dd99f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3a10ab5a-55cc-43e6-a3b8-f5c6398dd99f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'd5abab4a-8c74-4286-9480-98f1c2020ab5' ), (select name from clinlims.test where guid = 'd5abab4a-8c74-4286-9480-98f1c2020ab5' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d5abab4a-8c74-4286-9480-98f1c2020ab5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'd5abab4a-8c74-4286-9480-98f1c2020ab5' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'd5abab4a-8c74-4286-9480-98f1c2020ab5' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d5abab4a-8c74-4286-9480-98f1c2020ab5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'dec0e336-9bdd-4ac8-aa53-13bb9d895130' ), (select name from clinlims.test where guid = 'dec0e336-9bdd-4ac8-aa53-13bb9d895130' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='dec0e336-9bdd-4ac8-aa53-13bb9d895130';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'dec0e336-9bdd-4ac8-aa53-13bb9d895130' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'dec0e336-9bdd-4ac8-aa53-13bb9d895130' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='dec0e336-9bdd-4ac8-aa53-13bb9d895130';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'e7d2d95a-2692-42dd-8e96-db527a1ef3d1' ), (select name from clinlims.test where guid = 'e7d2d95a-2692-42dd-8e96-db527a1ef3d1' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='e7d2d95a-2692-42dd-8e96-db527a1ef3d1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'e7d2d95a-2692-42dd-8e96-db527a1ef3d1' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'e7d2d95a-2692-42dd-8e96-db527a1ef3d1' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='e7d2d95a-2692-42dd-8e96-db527a1ef3d1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'a56c7a39-ecea-4342-b63e-b2cbd5dc93ee' ), (select name from clinlims.test where guid = 'a56c7a39-ecea-4342-b63e-b2cbd5dc93ee' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a56c7a39-ecea-4342-b63e-b2cbd5dc93ee';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'a56c7a39-ecea-4342-b63e-b2cbd5dc93ee' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'a56c7a39-ecea-4342-b63e-b2cbd5dc93ee' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a56c7a39-ecea-4342-b63e-b2cbd5dc93ee';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '943ffc3c-91a7-4bdf-82d9-6a739f85e190' ), (select name from clinlims.test where guid = '943ffc3c-91a7-4bdf-82d9-6a739f85e190' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='943ffc3c-91a7-4bdf-82d9-6a739f85e190';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '943ffc3c-91a7-4bdf-82d9-6a739f85e190' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '943ffc3c-91a7-4bdf-82d9-6a739f85e190' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='943ffc3c-91a7-4bdf-82d9-6a739f85e190';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '884ca42a-5a77-40a5-a937-519efeeab561' ), (select name from clinlims.test where guid = '884ca42a-5a77-40a5-a937-519efeeab561' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='884ca42a-5a77-40a5-a937-519efeeab561';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '884ca42a-5a77-40a5-a937-519efeeab561' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '884ca42a-5a77-40a5-a937-519efeeab561' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='884ca42a-5a77-40a5-a937-519efeeab561';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'cfdf8ffb-810f-4ce9-a1eb-6a3311d20f3f' ), (select name from clinlims.test where guid = 'cfdf8ffb-810f-4ce9-a1eb-6a3311d20f3f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='cfdf8ffb-810f-4ce9-a1eb-6a3311d20f3f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'cfdf8ffb-810f-4ce9-a1eb-6a3311d20f3f' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'cfdf8ffb-810f-4ce9-a1eb-6a3311d20f3f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='cfdf8ffb-810f-4ce9-a1eb-6a3311d20f3f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '4f03894e-f750-49f9-a0b5-b0bf4e0b132a' ), (select name from clinlims.test where guid = '4f03894e-f750-49f9-a0b5-b0bf4e0b132a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='4f03894e-f750-49f9-a0b5-b0bf4e0b132a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '4f03894e-f750-49f9-a0b5-b0bf4e0b132a' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '4f03894e-f750-49f9-a0b5-b0bf4e0b132a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='4f03894e-f750-49f9-a0b5-b0bf4e0b132a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '9815fe7f-b639-4827-9a40-b1cf11e90ffa' ), (select name from clinlims.test where guid = '9815fe7f-b639-4827-9a40-b1cf11e90ffa' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='9815fe7f-b639-4827-9a40-b1cf11e90ffa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '9815fe7f-b639-4827-9a40-b1cf11e90ffa' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '9815fe7f-b639-4827-9a40-b1cf11e90ffa' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='9815fe7f-b639-4827-9a40-b1cf11e90ffa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '372b73ff-a662-401d-9ca3-8d0686511e9f' ), (select name from clinlims.test where guid = '372b73ff-a662-401d-9ca3-8d0686511e9f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='372b73ff-a662-401d-9ca3-8d0686511e9f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '372b73ff-a662-401d-9ca3-8d0686511e9f' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '372b73ff-a662-401d-9ca3-8d0686511e9f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='372b73ff-a662-401d-9ca3-8d0686511e9f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '42713a24-69a7-40a1-b618-c0246a1a22a0' ), (select name from clinlims.test where guid = '42713a24-69a7-40a1-b618-c0246a1a22a0' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='42713a24-69a7-40a1-b618-c0246a1a22a0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '42713a24-69a7-40a1-b618-c0246a1a22a0' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '42713a24-69a7-40a1-b618-c0246a1a22a0' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='42713a24-69a7-40a1-b618-c0246a1a22a0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '08d85b6f-927b-4f88-8fef-8a0270b458fe' ), (select name from clinlims.test where guid = '08d85b6f-927b-4f88-8fef-8a0270b458fe' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='08d85b6f-927b-4f88-8fef-8a0270b458fe';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '08d85b6f-927b-4f88-8fef-8a0270b458fe' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '08d85b6f-927b-4f88-8fef-8a0270b458fe' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='08d85b6f-927b-4f88-8fef-8a0270b458fe';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'a5a64fb5-2ca0-4544-bc54-e35540130d15' ), (select name from clinlims.test where guid = 'a5a64fb5-2ca0-4544-bc54-e35540130d15' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a5a64fb5-2ca0-4544-bc54-e35540130d15';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'a5a64fb5-2ca0-4544-bc54-e35540130d15' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'a5a64fb5-2ca0-4544-bc54-e35540130d15' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a5a64fb5-2ca0-4544-bc54-e35540130d15';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'a847d7c2-f853-4367-bcc1-b2f7a4cfef87' ), (select name from clinlims.test where guid = 'a847d7c2-f853-4367-bcc1-b2f7a4cfef87' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a847d7c2-f853-4367-bcc1-b2f7a4cfef87';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'a847d7c2-f853-4367-bcc1-b2f7a4cfef87' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'a847d7c2-f853-4367-bcc1-b2f7a4cfef87' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a847d7c2-f853-4367-bcc1-b2f7a4cfef87';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '5819d60d-5cdb-4adb-a448-64933b554644' ), (select name from clinlims.test where guid = '5819d60d-5cdb-4adb-a448-64933b554644' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='5819d60d-5cdb-4adb-a448-64933b554644';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '5819d60d-5cdb-4adb-a448-64933b554644' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '5819d60d-5cdb-4adb-a448-64933b554644' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='5819d60d-5cdb-4adb-a448-64933b554644';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'cc670524-887f-4121-903b-33a90820a35a' ), (select name from clinlims.test where guid = 'cc670524-887f-4121-903b-33a90820a35a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='cc670524-887f-4121-903b-33a90820a35a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'cc670524-887f-4121-903b-33a90820a35a' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'cc670524-887f-4121-903b-33a90820a35a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='cc670524-887f-4121-903b-33a90820a35a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'e3912d64-578c-4218-836b-d4813a76b8d4' ), (select name from clinlims.test where guid = 'e3912d64-578c-4218-836b-d4813a76b8d4' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='e3912d64-578c-4218-836b-d4813a76b8d4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'e3912d64-578c-4218-836b-d4813a76b8d4' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'e3912d64-578c-4218-836b-d4813a76b8d4' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='e3912d64-578c-4218-836b-d4813a76b8d4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '84f709c2-c3f1-4835-90e5-8d8c0ad17a28' ), (select name from clinlims.test where guid = '84f709c2-c3f1-4835-90e5-8d8c0ad17a28' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='84f709c2-c3f1-4835-90e5-8d8c0ad17a28';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '84f709c2-c3f1-4835-90e5-8d8c0ad17a28' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '84f709c2-c3f1-4835-90e5-8d8c0ad17a28' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='84f709c2-c3f1-4835-90e5-8d8c0ad17a28';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '4eb348dd-6094-4b12-a919-7dd48cbf5747' ), (select name from clinlims.test where guid = '4eb348dd-6094-4b12-a919-7dd48cbf5747' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='4eb348dd-6094-4b12-a919-7dd48cbf5747';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '4eb348dd-6094-4b12-a919-7dd48cbf5747' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '4eb348dd-6094-4b12-a919-7dd48cbf5747' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='4eb348dd-6094-4b12-a919-7dd48cbf5747';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '5ffc134e-f243-49d1-8785-51dfa4c75eea' ), (select name from clinlims.test where guid = '5ffc134e-f243-49d1-8785-51dfa4c75eea' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='5ffc134e-f243-49d1-8785-51dfa4c75eea';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '5ffc134e-f243-49d1-8785-51dfa4c75eea' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '5ffc134e-f243-49d1-8785-51dfa4c75eea' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='5ffc134e-f243-49d1-8785-51dfa4c75eea';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '1f5fa9f2-d21e-4e53-a51c-f11e2ab826c7' ), (select name from clinlims.test where guid = '1f5fa9f2-d21e-4e53-a51c-f11e2ab826c7' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='1f5fa9f2-d21e-4e53-a51c-f11e2ab826c7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '1f5fa9f2-d21e-4e53-a51c-f11e2ab826c7' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '1f5fa9f2-d21e-4e53-a51c-f11e2ab826c7' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='1f5fa9f2-d21e-4e53-a51c-f11e2ab826c7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'd390b32c-3ecd-4165-86eb-205a4e01b5cb' ), (select name from clinlims.test where guid = 'd390b32c-3ecd-4165-86eb-205a4e01b5cb' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d390b32c-3ecd-4165-86eb-205a4e01b5cb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'd390b32c-3ecd-4165-86eb-205a4e01b5cb' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'd390b32c-3ecd-4165-86eb-205a4e01b5cb' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d390b32c-3ecd-4165-86eb-205a4e01b5cb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'c358afa6-ce39-4492-b18b-d70e59ed9cce' ), (select name from clinlims.test where guid = 'c358afa6-ce39-4492-b18b-d70e59ed9cce' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='c358afa6-ce39-4492-b18b-d70e59ed9cce';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'c358afa6-ce39-4492-b18b-d70e59ed9cce' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'c358afa6-ce39-4492-b18b-d70e59ed9cce' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='c358afa6-ce39-4492-b18b-d70e59ed9cce';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '7330be3d-3612-49da-a808-d330d8d66120' ), (select name from clinlims.test where guid = '7330be3d-3612-49da-a808-d330d8d66120' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='7330be3d-3612-49da-a808-d330d8d66120';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '7330be3d-3612-49da-a808-d330d8d66120' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '7330be3d-3612-49da-a808-d330d8d66120' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='7330be3d-3612-49da-a808-d330d8d66120';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'f74a4a34-8a8a-484a-88f4-20cb05bf959a' ), (select name from clinlims.test where guid = 'f74a4a34-8a8a-484a-88f4-20cb05bf959a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='f74a4a34-8a8a-484a-88f4-20cb05bf959a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'f74a4a34-8a8a-484a-88f4-20cb05bf959a' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'f74a4a34-8a8a-484a-88f4-20cb05bf959a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='f74a4a34-8a8a-484a-88f4-20cb05bf959a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'ea694d7d-f065-4911-b9a6-bdc2104ee9be' ), (select name from clinlims.test where guid = 'ea694d7d-f065-4911-b9a6-bdc2104ee9be' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='ea694d7d-f065-4911-b9a6-bdc2104ee9be';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'ea694d7d-f065-4911-b9a6-bdc2104ee9be' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'ea694d7d-f065-4911-b9a6-bdc2104ee9be' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='ea694d7d-f065-4911-b9a6-bdc2104ee9be';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '578a6271-4e12-403b-bfbd-5931550fda02' ), (select name from clinlims.test where guid = '578a6271-4e12-403b-bfbd-5931550fda02' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='578a6271-4e12-403b-bfbd-5931550fda02';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '578a6271-4e12-403b-bfbd-5931550fda02' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '578a6271-4e12-403b-bfbd-5931550fda02' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='578a6271-4e12-403b-bfbd-5931550fda02';
