INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Determine', (select name from clinlims.test where guid = '73b429df-6924-4bae-8194-4bb200216b44' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='73b429df-6924-4bae-8194-4bb200216b44';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Determine', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '73b429df-6924-4bae-8194-4bb200216b44' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='73b429df-6924-4bae-8194-4bb200216b44';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Determine', (select name from clinlims.test where guid = '9910e890-6b5e-4a07-961e-2416fb6b8c6a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='9910e890-6b5e-4a07-961e-2416fb6b8c6a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Determine', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '9910e890-6b5e-4a07-961e-2416fb6b8c6a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='9910e890-6b5e-4a07-961e-2416fb6b8c6a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Determine', (select name from clinlims.test where guid = '2f831c64-89bb-438d-8049-f0c88e8fb7fa' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='2f831c64-89bb-438d-8049-f0c88e8fb7fa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Determine', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '2f831c64-89bb-438d-8049-f0c88e8fb7fa' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='2f831c64-89bb-438d-8049-f0c88e8fb7fa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Bioline', (select name from clinlims.test where guid = '967630ae-4a03-4fec-b8dd-e4c59158d97a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='967630ae-4a03-4fec-b8dd-e4c59158d97a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Bioline', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '967630ae-4a03-4fec-b8dd-e4c59158d97a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='967630ae-4a03-4fec-b8dd-e4c59158d97a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Bioline', (select name from clinlims.test where guid = 'f5ca0517-5fa1-4cb3-b039-52a3ac1ef3bf' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='f5ca0517-5fa1-4cb3-b039-52a3ac1ef3bf';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Bioline', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'f5ca0517-5fa1-4cb3-b039-52a3ac1ef3bf' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='f5ca0517-5fa1-4cb3-b039-52a3ac1ef3bf';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Bioline', (select name from clinlims.test where guid = '3c8a481a-1b91-4e00-ba93-93fc139741b3' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3c8a481a-1b91-4e00-ba93-93fc139741b3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Bioline', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '3c8a481a-1b91-4e00-ba93-93fc139741b3' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3c8a481a-1b91-4e00-ba93-93fc139741b3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Stat PaK', (select name from clinlims.test where guid = '575f80f5-420f-4436-8062-5ca2c1a39ae7' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='575f80f5-420f-4436-8062-5ca2c1a39ae7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Stat PaK', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '575f80f5-420f-4436-8062-5ca2c1a39ae7' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='575f80f5-420f-4436-8062-5ca2c1a39ae7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Stat PaK', (select name from clinlims.test where guid = '790b136b-e789-4f0c-a6ce-15cdcef4e228' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='790b136b-e789-4f0c-a6ce-15cdcef4e228';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Stat PaK', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '790b136b-e789-4f0c-a6ce-15cdcef4e228' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='790b136b-e789-4f0c-a6ce-15cdcef4e228';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Stat PaK', (select name from clinlims.test where guid = 'a8390d02-a253-450f-b03d-5b81281a30e5' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a8390d02-a253-450f-b03d-5b81281a30e5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Stat PaK', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'a8390d02-a253-450f-b03d-5b81281a30e5' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a8390d02-a253-450f-b03d-5b81281a30e5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CD4 Absolute count (mm3)', (select name from clinlims.test where guid = '9c07b244-e94f-4e22-886c-7912a54d990b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='9c07b244-e94f-4e22-886c-7912a54d990b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CD4 Absolute count (mm3)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '9c07b244-e94f-4e22-886c-7912a54d990b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='9c07b244-e94f-4e22-886c-7912a54d990b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CD4 percent  (%)', (select name from clinlims.test where guid = '98ade179-afd2-4aed-91cd-12ca2fe30063' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='98ade179-afd2-4aed-91cd-12ca2fe30063';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CD4 percent  (%)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '98ade179-afd2-4aed-91cd-12ca2fe30063' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='98ade179-afd2-4aed-91cd-12ca2fe30063';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'GPT/ALAT', (select name from clinlims.test where guid = '8d2fd129-ac45-4254-a244-1d180b97fbab' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='8d2fd129-ac45-4254-a244-1d180b97fbab';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'GPT/ALAT', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '8d2fd129-ac45-4254-a244-1d180b97fbab' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='8d2fd129-ac45-4254-a244-1d180b97fbab';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'GOT/ASAT', (select name from clinlims.test where guid = '09767094-02ee-4883-9c16-0ec4df27cfad' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='09767094-02ee-4883-9c16-0ec4df27cfad';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'GOT/ASAT', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '09767094-02ee-4883-9c16-0ec4df27cfad' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='09767094-02ee-4883-9c16-0ec4df27cfad';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Glucose', (select name from clinlims.test where guid = '48c97b18-0953-4790-8b25-0b608db640a4' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='48c97b18-0953-4790-8b25-0b608db640a4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Glucose', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '48c97b18-0953-4790-8b25-0b608db640a4' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='48c97b18-0953-4790-8b25-0b608db640a4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Glucose', (select name from clinlims.test where guid = '603dbd94-d252-4891-83cb-07ab56b7d281' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='603dbd94-d252-4891-83cb-07ab56b7d281';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Glucose', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '603dbd94-d252-4891-83cb-07ab56b7d281' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='603dbd94-d252-4891-83cb-07ab56b7d281';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Creatinine', (select name from clinlims.test where guid = 'f71d96b0-e2ea-4833-a259-8faa9aa7ae20' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='f71d96b0-e2ea-4833-a259-8faa9aa7ae20';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Creatinine', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'f71d96b0-e2ea-4833-a259-8faa9aa7ae20' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='f71d96b0-e2ea-4833-a259-8faa9aa7ae20';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Amylase', (select name from clinlims.test where guid = 'ef5dd716-7f79-4b32-8eee-3f4ff5767039' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='ef5dd716-7f79-4b32-8eee-3f4ff5767039';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Amylase', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'ef5dd716-7f79-4b32-8eee-3f4ff5767039' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='ef5dd716-7f79-4b32-8eee-3f4ff5767039';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Albumin', (select name from clinlims.test where guid = '9fa2cca1-79e3-49e1-9f87-9737e9b952de' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='9fa2cca1-79e3-49e1-9f87-9737e9b952de';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Albumin', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '9fa2cca1-79e3-49e1-9f87-9737e9b952de' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='9fa2cca1-79e3-49e1-9f87-9737e9b952de';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Total cholesterol', (select name from clinlims.test where guid = '20051f21-94ad-44ff-ba0c-a6da6245bf01' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='20051f21-94ad-44ff-ba0c-a6da6245bf01';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Total cholesterol', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '20051f21-94ad-44ff-ba0c-a6da6245bf01' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='20051f21-94ad-44ff-ba0c-a6da6245bf01';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HDL cholesterol', (select name from clinlims.test where guid = '55e4b726-86d5-4c18-a36a-9e6c8b0b1f0a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='55e4b726-86d5-4c18-a36a-9e6c8b0b1f0a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HDL cholesterol', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '55e4b726-86d5-4c18-a36a-9e6c8b0b1f0a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='55e4b726-86d5-4c18-a36a-9e6c8b0b1f0a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Triglicerides', (select name from clinlims.test where guid = '9f078029-9b00-4856-b59e-e7604af29a92' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='9f078029-9b00-4856-b59e-e7604af29a92';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Triglicerides', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '9f078029-9b00-4856-b59e-e7604af29a92' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='9f078029-9b00-4856-b59e-e7604af29a92';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Viral load', (select name from clinlims.test where guid = '027dde83-61e2-49f0-b20b-f40bf7b7ee10' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='027dde83-61e2-49f0-b20b-f40bf7b7ee10';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Viral load', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '027dde83-61e2-49f0-b20b-f40bf7b7ee10' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='027dde83-61e2-49f0-b20b-f40bf7b7ee10';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Beta HCG', (select name from clinlims.test where guid = 'b101c41b-922f-4e37-ac01-017230fd9fe7' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='b101c41b-922f-4e37-ac01-017230fd9fe7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Beta HCG', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'b101c41b-922f-4e37-ac01-017230fd9fe7' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='b101c41b-922f-4e37-ac01-017230fd9fe7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'White Blood Cells Count (WBC)', (select name from clinlims.test where guid = 'd3b227d6-53ce-4402-bb0b-8f271e3b27e6' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d3b227d6-53ce-4402-bb0b-8f271e3b27e6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'White Blood Cells Count (WBC)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'd3b227d6-53ce-4402-bb0b-8f271e3b27e6' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d3b227d6-53ce-4402-bb0b-8f271e3b27e6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Red Blood Cells Count (RBC)', (select name from clinlims.test where guid = 'fdae85ea-3034-43af-9e11-55d5032256b5' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='fdae85ea-3034-43af-9e11-55d5032256b5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Red Blood Cells Count (RBC)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'fdae85ea-3034-43af-9e11-55d5032256b5' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='fdae85ea-3034-43af-9e11-55d5032256b5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hemoglobin', (select name from clinlims.test where guid = '781848ae-ada5-465c-9a19-d941dd424962' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='781848ae-ada5-465c-9a19-d941dd424962';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hemoglobin', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '781848ae-ada5-465c-9a19-d941dd424962' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='781848ae-ada5-465c-9a19-d941dd424962';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hematocrit', (select name from clinlims.test where guid = 'dacf059a-bf70-48a6-849a-28d462087f6e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='dacf059a-bf70-48a6-849a-28d462087f6e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hematocrit', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'dacf059a-bf70-48a6-849a-28d462087f6e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='dacf059a-bf70-48a6-849a-28d462087f6e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Medium corpuscular volum', (select name from clinlims.test where guid = '29736377-dd22-42bb-b3ba-2d1e5db8549f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='29736377-dd22-42bb-b3ba-2d1e5db8549f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Medium corpuscular volum', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '29736377-dd22-42bb-b3ba-2d1e5db8549f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='29736377-dd22-42bb-b3ba-2d1e5db8549f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'TMCH', (select name from clinlims.test where guid = '092a766e-6549-4838-a96f-7bfbc547b06d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='092a766e-6549-4838-a96f-7bfbc547b06d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'TMCH', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '092a766e-6549-4838-a96f-7bfbc547b06d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='092a766e-6549-4838-a96f-7bfbc547b06d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CMCH', (select name from clinlims.test where guid = '42d607b3-8283-41ba-9d33-927e31a9f2a4' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='42d607b3-8283-41ba-9d33-927e31a9f2a4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CMCH', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '42d607b3-8283-41ba-9d33-927e31a9f2a4' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='42d607b3-8283-41ba-9d33-927e31a9f2a4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Platelets', (select name from clinlims.test where guid = 'a5ac2b5d-d958-419e-ab48-69328adc74a3' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a5ac2b5d-d958-419e-ab48-69328adc74a3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Platelets', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'a5ac2b5d-d958-419e-ab48-69328adc74a3' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a5ac2b5d-d958-419e-ab48-69328adc74a3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Neutrophiles (%)', (select name from clinlims.test where guid = '37f96e86-6036-4710-83f6-325f0b815d24' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='37f96e86-6036-4710-83f6-325f0b815d24';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Neutrophiles (%)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '37f96e86-6036-4710-83f6-325f0b815d24' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='37f96e86-6036-4710-83f6-325f0b815d24';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Neutrophiles', (select name from clinlims.test where guid = '82172296-a86f-4d90-b2d8-cbd1fe089df7' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='82172296-a86f-4d90-b2d8-cbd1fe089df7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Neutrophiles', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '82172296-a86f-4d90-b2d8-cbd1fe089df7' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='82172296-a86f-4d90-b2d8-cbd1fe089df7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Eosinophiles (%)', (select name from clinlims.test where guid = '77d0d185-9e43-4f6a-bd11-3c34e4419a26' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='77d0d185-9e43-4f6a-bd11-3c34e4419a26';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Eosinophiles (%)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '77d0d185-9e43-4f6a-bd11-3c34e4419a26' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='77d0d185-9e43-4f6a-bd11-3c34e4419a26';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Eosinophiles', (select name from clinlims.test where guid = '30d037e5-fa9e-4bb3-ae42-8c098e1536b4' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='30d037e5-fa9e-4bb3-ae42-8c098e1536b4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Eosinophiles', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '30d037e5-fa9e-4bb3-ae42-8c098e1536b4' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='30d037e5-fa9e-4bb3-ae42-8c098e1536b4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Basophiles (%)', (select name from clinlims.test where guid = '7864e487-94d4-4f2a-9a14-87fdf4814f26' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='7864e487-94d4-4f2a-9a14-87fdf4814f26';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Basophiles (%)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '7864e487-94d4-4f2a-9a14-87fdf4814f26' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='7864e487-94d4-4f2a-9a14-87fdf4814f26';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Basophiles', (select name from clinlims.test where guid = 'e21958b9-17b6-41c6-bfb9-e1017cad627b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='e21958b9-17b6-41c6-bfb9-e1017cad627b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Basophiles', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'e21958b9-17b6-41c6-bfb9-e1017cad627b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='e21958b9-17b6-41c6-bfb9-e1017cad627b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Lymphocytes (%)', (select name from clinlims.test where guid = '03074da7-5f1b-41ed-ba29-f4c22276f3cc' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='03074da7-5f1b-41ed-ba29-f4c22276f3cc';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Lymphocytes (%)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '03074da7-5f1b-41ed-ba29-f4c22276f3cc' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='03074da7-5f1b-41ed-ba29-f4c22276f3cc';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Lymphocytes (Abs)', (select name from clinlims.test where guid = 'd5325b4c-6e38-4214-84cc-6eb3026f653a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d5325b4c-6e38-4214-84cc-6eb3026f653a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Lymphocytes (Abs)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'd5325b4c-6e38-4214-84cc-6eb3026f653a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d5325b4c-6e38-4214-84cc-6eb3026f653a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Monocytes (%)', (select name from clinlims.test where guid = '751a0897-d8a5-4c89-910c-12244193599f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='751a0897-d8a5-4c89-910c-12244193599f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Monocytes (%)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '751a0897-d8a5-4c89-910c-12244193599f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='751a0897-d8a5-4c89-910c-12244193599f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Monocytes (Abs)', (select name from clinlims.test where guid = 'd6ddad61-a22e-471f-af05-a0b3e367c776' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d6ddad61-a22e-471f-af05-a0b3e367c776';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Monocytes (Abs)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'd6ddad61-a22e-471f-af05-a0b3e367c776' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d6ddad61-a22e-471f-af05-a0b3e367c776';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HBsAg (Hepatitis B surface antigen)', (select name from clinlims.test where guid = '6e1bdbb1-ccc7-48ac-bc81-b268be0f4e6f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='6e1bdbb1-ccc7-48ac-bc81-b268be0f4e6f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HBsAg (Hepatitis B surface antigen)', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '6e1bdbb1-ccc7-48ac-bc81-b268be0f4e6f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='6e1bdbb1-ccc7-48ac-bc81-b268be0f4e6f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Urine prenancy test', (select name from clinlims.test where guid = '21e89098-62c7-44c5-bd13-da81c99496eb' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='21e89098-62c7-44c5-bd13-da81c99496eb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Urine prenancy test', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '21e89098-62c7-44c5-bd13-da81c99496eb' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='21e89098-62c7-44c5-bd13-da81c99496eb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Proteinuria dipstick', (select name from clinlims.test where guid = '43ea7a95-242c-4514-ae6e-662c68904181' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='43ea7a95-242c-4514-ae6e-662c68904181';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Proteinuria dipstick', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '43ea7a95-242c-4514-ae6e-662c68904181' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='43ea7a95-242c-4514-ae6e-662c68904181';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '6a8a40ee-5452-4545-89a9-52c21a5d8ad9' ), (select name from clinlims.test where guid = '6a8a40ee-5452-4545-89a9-52c21a5d8ad9' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='6a8a40ee-5452-4545-89a9-52c21a5d8ad9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '6a8a40ee-5452-4545-89a9-52c21a5d8ad9' ), (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '6a8a40ee-5452-4545-89a9-52c21a5d8ad9' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='6a8a40ee-5452-4545-89a9-52c21a5d8ad9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Genie II', (select name from clinlims.test where guid = '3b33e7eb-1572-46b0-95c4-5860540b57b7' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3b33e7eb-1572-46b0-95c4-5860540b57b7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Genie II', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '3b33e7eb-1572-46b0-95c4-5860540b57b7' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3b33e7eb-1572-46b0-95c4-5860540b57b7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Genie II', (select name from clinlims.test where guid = 'e93f41ab-3763-4d70-a28e-890ef24fd391' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='e93f41ab-3763-4d70-a28e-890ef24fd391';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Genie II', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'e93f41ab-3763-4d70-a28e-890ef24fd391' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='e93f41ab-3763-4d70-a28e-890ef24fd391';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Genie II 10', (select name from clinlims.test where guid = 'b62af6bf-9106-4907-824f-4f69e96bdace' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='b62af6bf-9106-4907-824f-4f69e96bdace';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Genie II 10', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'b62af6bf-9106-4907-824f-4f69e96bdace' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='b62af6bf-9106-4907-824f-4f69e96bdace';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Genie II 10', (select name from clinlims.test where guid = '50599045-6e84-4669-83ac-db6b658868b8' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='50599045-6e84-4669-83ac-db6b658868b8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Genie II 10', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '50599045-6e84-4669-83ac-db6b658868b8' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='50599045-6e84-4669-83ac-db6b658868b8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Genie II 100', (select name from clinlims.test where guid = '732813b5-6745-4f9d-bf5f-bf69c6c9df56' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='732813b5-6745-4f9d-bf5f-bf69c6c9df56';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Genie II 100', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '732813b5-6745-4f9d-bf5f-bf69c6c9df56' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='732813b5-6745-4f9d-bf5f-bf69c6c9df56';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Genie II 100', (select name from clinlims.test where guid = '7ed7bb62-04b9-48c7-8b31-ed7ccf86f4f3' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='7ed7bb62-04b9-48c7-8b31-ed7ccf86f4f3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Genie II 100', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '7ed7bb62-04b9-48c7-8b31-ed7ccf86f4f3' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='7ed7bb62-04b9-48c7-8b31-ed7ccf86f4f3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Vironstika', (select name from clinlims.test where guid = '6e1b2678-1e98-4dd3-aa8d-16e5c6e456de' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='6e1b2678-1e98-4dd3-aa8d-16e5c6e456de';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Vironstika', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '6e1b2678-1e98-4dd3-aa8d-16e5c6e456de' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='6e1b2678-1e98-4dd3-aa8d-16e5c6e456de';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Genie III', (select name from clinlims.test where guid = 'e8ace732-3607-40f1-9195-1c974e8086e4' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='e8ace732-3607-40f1-9195-1c974e8086e4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Genie III', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'e8ace732-3607-40f1-9195-1c974e8086e4' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='e8ace732-3607-40f1-9195-1c974e8086e4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Genie III', (select name from clinlims.test where guid = '10c9e9ea-92d6-4769-9441-d79f6593e369' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='10c9e9ea-92d6-4769-9441-d79f6593e369';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Genie III', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '10c9e9ea-92d6-4769-9441-d79f6593e369' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='10c9e9ea-92d6-4769-9441-d79f6593e369';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Genie III', (select name from clinlims.test where guid = '3ad1c0f7-7e47-4db1-b0ed-6abadc776d6e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3ad1c0f7-7e47-4db1-b0ed-6abadc776d6e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Genie III', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '3ad1c0f7-7e47-4db1-b0ed-6abadc776d6e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3ad1c0f7-7e47-4db1-b0ed-6abadc776d6e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Murex', (select name from clinlims.test where guid = '82b7f700-2c80-42c9-aeba-4e19c32b04ee' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='82b7f700-2c80-42c9-aeba-4e19c32b04ee';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Murex', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '82b7f700-2c80-42c9-aeba-4e19c32b04ee' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='82b7f700-2c80-42c9-aeba-4e19c32b04ee';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Integral', (select name from clinlims.test where guid = '80cfa82f-43c5-44f3-8c0f-b920fe65db72' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='80cfa82f-43c5-44f3-8c0f-b920fe65db72';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Integral', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '80cfa82f-43c5-44f3-8c0f-b920fe65db72' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='80cfa82f-43c5-44f3-8c0f-b920fe65db72';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Western blot 1', (select name from clinlims.test where guid = '18e77469-6e2f-4a1d-9e98-20110d401065' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='18e77469-6e2f-4a1d-9e98-20110d401065';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Western blot 1', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '18e77469-6e2f-4a1d-9e98-20110d401065' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='18e77469-6e2f-4a1d-9e98-20110d401065';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Western blot 2', (select name from clinlims.test where guid = 'aa0b9c9c-834a-4663-8ded-c67cd2b6fead' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='aa0b9c9c-834a-4663-8ded-c67cd2b6fead';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Western blot 2', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = 'aa0b9c9c-834a-4663-8ded-c67cd2b6fead' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='aa0b9c9c-834a-4663-8ded-c67cd2b6fead';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'p24Ag', (select name from clinlims.test where guid = '08c6d514-d27d-44b2-9e76-d25a58b1df41' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='08c6d514-d27d-44b2-9e76-d25a58b1df41';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'p24Ag', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '08c6d514-d27d-44b2-9e76-d25a58b1df41' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='08c6d514-d27d-44b2-9e76-d25a58b1df41';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'DNA PCR', (select name from clinlims.test where guid = '27e4527f-1e31-4ddf-b2ba-39b1a11da0d5' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='27e4527f-1e31-4ddf-b2ba-39b1a11da0d5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'DNA PCR', (select case char_length(reporting_description) when 0 then name else reporting_description end from clinlims.test where guid = '27e4527f-1e31-4ddf-b2ba-39b1a11da0d5' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='27e4527f-1e31-4ddf-b2ba-39b1a11da0d5';
