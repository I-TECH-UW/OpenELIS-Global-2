INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'White Blood Cell Count (WBC)', (select name from clinlims.test where guid = 'eb682185-ce5f-4637-ab09-c679204be9f6' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='eb682185-ce5f-4637-ab09-c679204be9f6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'White Blood Cell Count (WBC)', (select reporting_description from clinlims.test where guid = 'eb682185-ce5f-4637-ab09-c679204be9f6' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='eb682185-ce5f-4637-ab09-c679204be9f6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Red Blood Cell Count (RBC)', (select name from clinlims.test where guid = '5b4cd536-12e0-4317-880d-6f8ca731193e' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='5b4cd536-12e0-4317-880d-6f8ca731193e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Red Blood Cell Count (RBC)', (select reporting_description from clinlims.test where guid = '5b4cd536-12e0-4317-880d-6f8ca731193e' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='5b4cd536-12e0-4317-880d-6f8ca731193e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hemoglobin (Hb)', (select name from clinlims.test where guid = '7dd6e10e-c0b3-4812-a100-be873c02fe07' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='7dd6e10e-c0b3-4812-a100-be873c02fe07';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hemoglobin (Hb)', (select reporting_description from clinlims.test where guid = '7dd6e10e-c0b3-4812-a100-be873c02fe07' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='7dd6e10e-c0b3-4812-a100-be873c02fe07';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hematocrit (HCT)', (select name from clinlims.test where guid = '26f8cfd5-1da7-4ebc-b4ca-5626fd96b359' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='26f8cfd5-1da7-4ebc-b4ca-5626fd96b359';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hematocrit (HCT)', (select reporting_description from clinlims.test where guid = '26f8cfd5-1da7-4ebc-b4ca-5626fd96b359' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='26f8cfd5-1da7-4ebc-b4ca-5626fd96b359';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'MCV', (select name from clinlims.test where guid = '3c78fa57-5571-4692-b10c-121377c58c4f' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='3c78fa57-5571-4692-b10c-121377c58c4f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'MCV', (select reporting_description from clinlims.test where guid = '3c78fa57-5571-4692-b10c-121377c58c4f' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='3c78fa57-5571-4692-b10c-121377c58c4f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'TMCH', (select name from clinlims.test where guid = '6fd76a61-5a9d-4967-a889-3d0c3b9eec73' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='6fd76a61-5a9d-4967-a889-3d0c3b9eec73';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'TMCH', (select reporting_description from clinlims.test where guid = '6fd76a61-5a9d-4967-a889-3d0c3b9eec73' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='6fd76a61-5a9d-4967-a889-3d0c3b9eec73';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CMCH', (select name from clinlims.test where guid = 'b5075ca9-c16c-42df-8a44-95d122e7c84a' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='b5075ca9-c16c-42df-8a44-95d122e7c84a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CMCH', (select reporting_description from clinlims.test where guid = 'b5075ca9-c16c-42df-8a44-95d122e7c84a' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='b5075ca9-c16c-42df-8a44-95d122e7c84a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Platelets', (select name from clinlims.test where guid = '1558ac00-7378-4edd-b29a-ae8c636ac04c' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='1558ac00-7378-4edd-b29a-ae8c636ac04c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Platelets', (select reporting_description from clinlims.test where guid = '1558ac00-7378-4edd-b29a-ae8c636ac04c' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='1558ac00-7378-4edd-b29a-ae8c636ac04c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Neutrophiles', (select name from clinlims.test where guid = 'b9779212-0ca4-4a5b-97bd-4bd9f5f805be' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='b9779212-0ca4-4a5b-97bd-4bd9f5f805be';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Neutrophiles', (select reporting_description from clinlims.test where guid = 'b9779212-0ca4-4a5b-97bd-4bd9f5f805be' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='b9779212-0ca4-4a5b-97bd-4bd9f5f805be';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Lymphocytes', (select name from clinlims.test where guid = 'e007eb0b-ddc8-4871-9fcb-71efb9a83de1' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='e007eb0b-ddc8-4871-9fcb-71efb9a83de1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Lymphocytes', (select reporting_description from clinlims.test where guid = 'e007eb0b-ddc8-4871-9fcb-71efb9a83de1' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='e007eb0b-ddc8-4871-9fcb-71efb9a83de1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Mixtes', (select name from clinlims.test where guid = 'f087ff67-f8b5-416d-80c3-bcc188f2438d' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='f087ff67-f8b5-416d-80c3-bcc188f2438d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Mixtes', (select reporting_description from clinlims.test where guid = 'f087ff67-f8b5-416d-80c3-bcc188f2438d' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='f087ff67-f8b5-416d-80c3-bcc188f2438d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Monocytes', (select name from clinlims.test where guid = '13f67461-d3ce-48c5-b39f-5b013f6f9ae4' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='13f67461-d3ce-48c5-b39f-5b013f6f9ae4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Monocytes', (select reporting_description from clinlims.test where guid = '13f67461-d3ce-48c5-b39f-5b013f6f9ae4' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='13f67461-d3ce-48c5-b39f-5b013f6f9ae4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Eosinophiles', (select name from clinlims.test where guid = '58e72047-fb75-4a7e-a9d4-f8547b91aa44' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='58e72047-fb75-4a7e-a9d4-f8547b91aa44';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Eosinophiles', (select reporting_description from clinlims.test where guid = '58e72047-fb75-4a7e-a9d4-f8547b91aa44' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='58e72047-fb75-4a7e-a9d4-f8547b91aa44';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Basophiles', (select name from clinlims.test where guid = '1a0084af-7708-49c0-b7cd-58bf67c64ae0' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='1a0084af-7708-49c0-b7cd-58bf67c64ae0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Basophiles', (select reporting_description from clinlims.test where guid = '1a0084af-7708-49c0-b7cd-58bf67c64ae0' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='1a0084af-7708-49c0-b7cd-58bf67c64ae0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Erythrocyte sedimentation rate', (select name from clinlims.test where guid = '7d2e3ab2-b58d-4a42-bd44-d96c15be73b6' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='7d2e3ab2-b58d-4a42-bd44-d96c15be73b6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Erythrocyte sedimentation rate', (select reporting_description from clinlims.test where guid = '7d2e3ab2-b58d-4a42-bd44-d96c15be73b6' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='7d2e3ab2-b58d-4a42-bd44-d96c15be73b6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Tube Coagulation Time', (select name from clinlims.test where guid = 'c8a08e36-55ce-4054-bda5-0da1f852d90d' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='c8a08e36-55ce-4054-bda5-0da1f852d90d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Tube Coagulation Time', (select reporting_description from clinlims.test where guid = 'c8a08e36-55ce-4054-bda5-0da1f852d90d' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='c8a08e36-55ce-4054-bda5-0da1f852d90d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Coagulation Time', (select name from clinlims.test where guid = '926fd266-0c19-4ed1-a70d-161fbf58ba0f' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='926fd266-0c19-4ed1-a70d-161fbf58ba0f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Coagulation Time', (select reporting_description from clinlims.test where guid = '926fd266-0c19-4ed1-a70d-161fbf58ba0f' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='926fd266-0c19-4ed1-a70d-161fbf58ba0f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Bleeding Time', (select name from clinlims.test where guid = '8796682a-8fe7-4886-a35a-a9e09c77a24a' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='8796682a-8fe7-4886-a35a-a9e09c77a24a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Bleeding Time', (select reporting_description from clinlims.test where guid = '8796682a-8fe7-4886-a35a-a9e09c77a24a' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='8796682a-8fe7-4886-a35a-a9e09c77a24a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hemoglobin electrophoresis', (select name from clinlims.test where guid = 'd5ec09e1-a048-4ce3-a2f6-6bd1a2e46870' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='d5ec09e1-a048-4ce3-a2f6-6bd1a2e46870';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hemoglobin electrophoresis', (select reporting_description from clinlims.test where guid = 'd5ec09e1-a048-4ce3-a2f6-6bd1a2e46870' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='d5ec09e1-a048-4ce3-a2f6-6bd1a2e46870';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Sickling test', (select name from clinlims.test where guid = 'b4e04c2e-3c2f-403b-a1e1-26f355e0307d' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='b4e04c2e-3c2f-403b-a1e1-26f355e0307d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Sickling test', (select reporting_description from clinlims.test where guid = 'b4e04c2e-3c2f-403b-a1e1-26f355e0307d' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='b4e04c2e-3c2f-403b-a1e1-26f355e0307d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Reticulocyte rate - Auto', (select name from clinlims.test where guid = '7d4f4fde-22e9-40b6-beb8-8d85d77d0f5b' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='7d4f4fde-22e9-40b6-beb8-8d85d77d0f5b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Reticulocyte rate - Auto', (select reporting_description from clinlims.test where guid = '7d4f4fde-22e9-40b6-beb8-8d85d77d0f5b' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='7d4f4fde-22e9-40b6-beb8-8d85d77d0f5b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Reticulocyte rate - Manual', (select name from clinlims.test where guid = 'e7bd1789-66a3-4c69-a58c-603c36e1e514' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='e7bd1789-66a3-4c69-a58c-603c36e1e514';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Reticulocyte rate - Manual', (select reporting_description from clinlims.test where guid = 'e7bd1789-66a3-4c69-a58c-603c36e1e514' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='e7bd1789-66a3-4c69-a58c-603c36e1e514';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Partial thromboplastin time', (select name from clinlims.test where guid = '4c7f2820-08f8-4d45-9ded-2463d69d5d76' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='4c7f2820-08f8-4d45-9ded-2463d69d5d76';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Partial thromboplastin time', (select reporting_description from clinlims.test where guid = '4c7f2820-08f8-4d45-9ded-2463d69d5d76' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='4c7f2820-08f8-4d45-9ded-2463d69d5d76';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Prothrombine Time', (select name from clinlims.test where guid = '0cdbd706-67b7-4378-bf51-d862739f1a18' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='0cdbd706-67b7-4378-bf51-d862739f1a18';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Prothrombine Time', (select reporting_description from clinlims.test where guid = '0cdbd706-67b7-4378-bf51-d862739f1a18' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='0cdbd706-67b7-4378-bf51-d862739f1a18';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'INR', (select name from clinlims.test where guid = '8fb35603-6de7-4c1e-9110-fdd6b6feae7a' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='8fb35603-6de7-4c1e-9110-fdd6b6feae7a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'INR', (select reporting_description from clinlims.test where guid = '8fb35603-6de7-4c1e-9110-fdd6b6feae7a' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='8fb35603-6de7-4c1e-9110-fdd6b6feae7a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Factor VIII', (select name from clinlims.test where guid = 'aceb1924-f086-47d1-b2d5-f821ec4cd46c' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='aceb1924-f086-47d1-b2d5-f821ec4cd46c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Factor VIII', (select reporting_description from clinlims.test where guid = 'aceb1924-f086-47d1-b2d5-f821ec4cd46c' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='aceb1924-f086-47d1-b2d5-f821ec4cd46c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Factor IX', (select name from clinlims.test where guid = 'fbdcf3ba-b4c8-4781-a1a9-a3064923ca37' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='fbdcf3ba-b4c8-4781-a1a9-a3064923ca37';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Factor IX', (select reporting_description from clinlims.test where guid = 'fbdcf3ba-b4c8-4781-a1a9-a3064923ca37' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='fbdcf3ba-b4c8-4781-a1a9-a3064923ca37';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Heparin rate', (select name from clinlims.test where guid = '2f3d5461-4ec1-40aa-a160-07a9173e834b' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='2f3d5461-4ec1-40aa-a160-07a9173e834b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Heparin rate', (select reporting_description from clinlims.test where guid = '2f3d5461-4ec1-40aa-a160-07a9173e834b' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='2f3d5461-4ec1-40aa-a160-07a9173e834b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Antithrombin III (Measure)', (select name from clinlims.test where guid = '65ca9a24-02d2-4215-ab18-bd3bc110ad18' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='65ca9a24-02d2-4215-ab18-bd3bc110ad18';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Antithrombin III (Measure)', (select reporting_description from clinlims.test where guid = '65ca9a24-02d2-4215-ab18-bd3bc110ad18' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='65ca9a24-02d2-4215-ab18-bd3bc110ad18';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Antithrombin III (Activity)', (select name from clinlims.test where guid = '4af61b07-fbf7-4ec5-8ba0-94f9bc0074ae' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='4af61b07-fbf7-4ec5-8ba0-94f9bc0074ae';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Antithrombin III (Activity)', (select reporting_description from clinlims.test where guid = '4af61b07-fbf7-4ec5-8ba0-94f9bc0074ae' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='4af61b07-fbf7-4ec5-8ba0-94f9bc0074ae';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Blood type ABO', (select name from clinlims.test where guid = '50185ead-3a67-450a-8acd-489cea71da30' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='50185ead-3a67-450a-8acd-489cea71da30';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Blood type ABO', (select reporting_description from clinlims.test where guid = '50185ead-3a67-450a-8acd-489cea71da30' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='50185ead-3a67-450a-8acd-489cea71da30';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Blood type Rh D', (select name from clinlims.test where guid = 'a331c789-e595-45d8-815a-ee8361b89c38' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='a331c789-e595-45d8-815a-ee8361b89c38';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Blood type Rh D', (select reporting_description from clinlims.test where guid = 'a331c789-e595-45d8-815a-ee8361b89c38' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='a331c789-e595-45d8-815a-ee8361b89c38';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Crossmatch Test', (select name from clinlims.test where guid = '8475c5df-0f1a-4d99-8331-667502a312fc' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='8475c5df-0f1a-4d99-8331-667502a312fc';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Crossmatch Test', (select reporting_description from clinlims.test where guid = '8475c5df-0f1a-4d99-8331-667502a312fc' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='8475c5df-0f1a-4d99-8331-667502a312fc';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Crossmatch Test', (select name from clinlims.test where guid = '13946efe-aa7b-446e-a1dc-87432d71fb53' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='13946efe-aa7b-446e-a1dc-87432d71fb53';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Crossmatch Test', (select reporting_description from clinlims.test where guid = '13946efe-aa7b-446e-a1dc-87432d71fb53' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='13946efe-aa7b-446e-a1dc-87432d71fb53';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Coombs Test Direct', (select name from clinlims.test where guid = 'cbd07c5f-475e-435e-aee8-e950e30f6b3c' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='cbd07c5f-475e-435e-aee8-e950e30f6b3c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Coombs Test Direct', (select reporting_description from clinlims.test where guid = 'cbd07c5f-475e-435e-aee8-e950e30f6b3c' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='cbd07c5f-475e-435e-aee8-e950e30f6b3c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Coombs Test Indirect', (select name from clinlims.test where guid = '61b76c30-8152-4fa6-9055-1fd10dcea101' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='61b76c30-8152-4fa6-9055-1fd10dcea101';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Coombs Test Indirect', (select reporting_description from clinlims.test where guid = '61b76c30-8152-4fa6-9055-1fd10dcea101' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='61b76c30-8152-4fa6-9055-1fd10dcea101';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Blood urea nitrogen', (select name from clinlims.test where guid = '7752a1e4-d4d4-49f3-949f-a73885498448' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='7752a1e4-d4d4-49f3-949f-a73885498448';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Blood urea nitrogen', (select reporting_description from clinlims.test where guid = '7752a1e4-d4d4-49f3-949f-a73885498448' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='7752a1e4-d4d4-49f3-949f-a73885498448';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Urea', (select name from clinlims.test where guid = '5e977789-9678-49a9-b93d-5121fb59f34c' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='5e977789-9678-49a9-b93d-5121fb59f34c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Urea', (select reporting_description from clinlims.test where guid = '5e977789-9678-49a9-b93d-5121fb59f34c' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='5e977789-9678-49a9-b93d-5121fb59f34c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Creatinine', (select name from clinlims.test where guid = '99a98e0b-ba0a-44a9-92a1-9ade0ce53613' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='99a98e0b-ba0a-44a9-92a1-9ade0ce53613';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Creatinine', (select reporting_description from clinlims.test where guid = '99a98e0b-ba0a-44a9-92a1-9ade0ce53613' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='99a98e0b-ba0a-44a9-92a1-9ade0ce53613';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Glycemia', (select name from clinlims.test where guid = 'c65cca03-824a-4460-ac22-ff41b2428981' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='c65cca03-824a-4460-ac22-ff41b2428981';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Glycemia', (select reporting_description from clinlims.test where guid = 'c65cca03-824a-4460-ac22-ff41b2428981' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='c65cca03-824a-4460-ac22-ff41b2428981';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Glucose', (select name from clinlims.test where guid = '747c7e0b-0408-4ca7-93d1-0e8f4c1a6407' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='747c7e0b-0408-4ca7-93d1-0e8f4c1a6407';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Glucose', (select reporting_description from clinlims.test where guid = '747c7e0b-0408-4ca7-93d1-0e8f4c1a6407' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='747c7e0b-0408-4ca7-93d1-0e8f4c1a6407';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Protein', (select name from clinlims.test where guid = '88a3d732-9f31-4e4a-8d1c-6df5bf39b8b2' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='88a3d732-9f31-4e4a-8d1c-6df5bf39b8b2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Protein', (select reporting_description from clinlims.test where guid = '88a3d732-9f31-4e4a-8d1c-6df5bf39b8b2' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='88a3d732-9f31-4e4a-8d1c-6df5bf39b8b2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Chlorine', (select name from clinlims.test where guid = '62da39e8-77ab-4fb9-85d3-6d0964803481' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='62da39e8-77ab-4fb9-85d3-6d0964803481';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Chlorine', (select reporting_description from clinlims.test where guid = '62da39e8-77ab-4fb9-85d3-6d0964803481' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='62da39e8-77ab-4fb9-85d3-6d0964803481';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Blood sugar level/glycemia', (select name from clinlims.test where guid = 'b2a61a48-04c2-47ed-96ed-d67058578164' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='b2a61a48-04c2-47ed-96ed-d67058578164';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Blood sugar level/glycemia', (select reporting_description from clinlims.test where guid = 'b2a61a48-04c2-47ed-96ed-d67058578164' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='b2a61a48-04c2-47ed-96ed-d67058578164';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Albumin', (select name from clinlims.test where guid = '1317256b-581f-4bf7-837f-dd136bf0fcfe' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='1317256b-581f-4bf7-837f-dd136bf0fcfe';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Albumin', (select reporting_description from clinlims.test where guid = '1317256b-581f-4bf7-837f-dd136bf0fcfe' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='1317256b-581f-4bf7-837f-dd136bf0fcfe';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'α1 globulins', (select name from clinlims.test where guid = 'fbc9b9a7-7be9-4f8b-9f64-803d3575328a' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='fbc9b9a7-7be9-4f8b-9f64-803d3575328a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'α1 globulins', (select reporting_description from clinlims.test where guid = 'fbc9b9a7-7be9-4f8b-9f64-803d3575328a' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='fbc9b9a7-7be9-4f8b-9f64-803d3575328a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'α2 globulins', (select name from clinlims.test where guid = 'aa2b6d20-37d5-4d4e-ac38-50a645b836c1' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='aa2b6d20-37d5-4d4e-ac38-50a645b836c1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'α2 globulins', (select reporting_description from clinlims.test where guid = 'aa2b6d20-37d5-4d4e-ac38-50a645b836c1' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='aa2b6d20-37d5-4d4e-ac38-50a645b836c1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'ß globulins', (select name from clinlims.test where guid = '2d04056b-4b2d-4c77-9587-7002199c08ee' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='2d04056b-4b2d-4c77-9587-7002199c08ee';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'ß globulins', (select reporting_description from clinlims.test where guid = '2d04056b-4b2d-4c77-9587-7002199c08ee' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='2d04056b-4b2d-4c77-9587-7002199c08ee';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'ϒ globulins', (select name from clinlims.test where guid = '1e6ee5e3-44f4-417e-88e4-9c76bb439cd8' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='1e6ee5e3-44f4-417e-88e4-9c76bb439cd8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'ϒ globulins', (select reporting_description from clinlims.test where guid = '1e6ee5e3-44f4-417e-88e4-9c76bb439cd8' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='1e6ee5e3-44f4-417e-88e4-9c76bb439cd8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Acid phosphatase', (select name from clinlims.test where guid = '06d96dfd-4bd4-4a0c-98f9-677d88792bc3' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='06d96dfd-4bd4-4a0c-98f9-677d88792bc3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Acid phosphatase', (select reporting_description from clinlims.test where guid = '06d96dfd-4bd4-4a0c-98f9-677d88792bc3' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='06d96dfd-4bd4-4a0c-98f9-677d88792bc3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Chloride', (select name from clinlims.test where guid = '52a96c35-af9c-498e-91a8-89be50add646' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='52a96c35-af9c-498e-91a8-89be50add646';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Chloride', (select reporting_description from clinlims.test where guid = '52a96c35-af9c-498e-91a8-89be50add646' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='52a96c35-af9c-498e-91a8-89be50add646';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Chloride', (select name from clinlims.test where guid = 'c105dd99-daf9-45a2-9cea-e1888273b501' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='c105dd99-daf9-45a2-9cea-e1888273b501';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Chloride', (select reporting_description from clinlims.test where guid = 'c105dd99-daf9-45a2-9cea-e1888273b501' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='c105dd99-daf9-45a2-9cea-e1888273b501';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CPK', (select name from clinlims.test where guid = '44e4b1af-d04b-4c5c-921c-607ca1046b82' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='44e4b1af-d04b-4c5c-921c-607ca1046b82';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CPK', (select reporting_description from clinlims.test where guid = '44e4b1af-d04b-4c5c-921c-607ca1046b82' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='44e4b1af-d04b-4c5c-921c-607ca1046b82';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Amylase', (select name from clinlims.test where guid = 'eef359f7-984d-4d78-b738-c39505d22385' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='eef359f7-984d-4d78-b738-c39505d22385';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Amylase', (select reporting_description from clinlims.test where guid = 'eef359f7-984d-4d78-b738-c39505d22385' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='eef359f7-984d-4d78-b738-c39505d22385';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Lipase', (select name from clinlims.test where guid = 'faffae46-ab00-4fab-9612-86989f25a862' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='faffae46-ab00-4fab-9612-86989f25a862';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Lipase', (select reporting_description from clinlims.test where guid = 'faffae46-ab00-4fab-9612-86989f25a862' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='faffae46-ab00-4fab-9612-86989f25a862';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Chloride (Cl)', (select name from clinlims.test where guid = '739329e7-096f-431b-b08d-e25449eaae04' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='739329e7-096f-431b-b08d-e25449eaae04';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Chloride (Cl)', (select reporting_description from clinlims.test where guid = '739329e7-096f-431b-b08d-e25449eaae04' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='739329e7-096f-431b-b08d-e25449eaae04';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'sodium (Na )', (select name from clinlims.test where guid = '6f135868-6c99-4f6b-99f3-9ef2fea56e36' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='6f135868-6c99-4f6b-99f3-9ef2fea56e36';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'sodium (Na )', (select reporting_description from clinlims.test where guid = '6f135868-6c99-4f6b-99f3-9ef2fea56e36' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='6f135868-6c99-4f6b-99f3-9ef2fea56e36';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'potassium (K )', (select name from clinlims.test where guid = '02271c8a-b785-4620-9d79-0f75476e473d' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='02271c8a-b785-4620-9d79-0f75476e473d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'potassium (K )', (select reporting_description from clinlims.test where guid = '02271c8a-b785-4620-9d79-0f75476e473d' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='02271c8a-b785-4620-9d79-0f75476e473d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'bicarbonates', (select name from clinlims.test where guid = '8d8dc288-cd1e-49bb-bbe5-66648fb72cdc' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='8d8dc288-cd1e-49bb-bbe5-66648fb72cdc';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'bicarbonates', (select reporting_description from clinlims.test where guid = '8d8dc288-cd1e-49bb-bbe5-66648fb72cdc' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='8d8dc288-cd1e-49bb-bbe5-66648fb72cdc';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'calcium (Ca )', (select name from clinlims.test where guid = '240a5fa6-21fc-4ae0-8fe2-6ebe55261c21' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='240a5fa6-21fc-4ae0-8fe2-6ebe55261c21';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'calcium (Ca )', (select reporting_description from clinlims.test where guid = '240a5fa6-21fc-4ae0-8fe2-6ebe55261c21' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='240a5fa6-21fc-4ae0-8fe2-6ebe55261c21';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'magnésium', (select name from clinlims.test where guid = 'daa1cf74-65d1-4f9b-b7cc-fc542a81717e' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='daa1cf74-65d1-4f9b-b7cc-fc542a81717e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'magnésium', (select reporting_description from clinlims.test where guid = 'daa1cf74-65d1-4f9b-b7cc-fc542a81717e' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='daa1cf74-65d1-4f9b-b7cc-fc542a81717e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'phosphore (P )', (select name from clinlims.test where guid = 'ee00d336-9011-4df7-87e3-8e20bd7b7fe9' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='ee00d336-9011-4df7-87e3-8e20bd7b7fe9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'phosphore (P )', (select reporting_description from clinlims.test where guid = 'ee00d336-9011-4df7-87e3-8e20bd7b7fe9' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='ee00d336-9011-4df7-87e3-8e20bd7b7fe9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Lithium', (select name from clinlims.test where guid = '3732bc71-18fe-46ee-b817-8e49adee325e' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='3732bc71-18fe-46ee-b817-8e49adee325e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Lithium', (select reporting_description from clinlims.test where guid = '3732bc71-18fe-46ee-b817-8e49adee325e' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='3732bc71-18fe-46ee-b817-8e49adee325e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Serum Iron', (select name from clinlims.test where guid = 'd96b9abb-1709-4896-9180-487246aaf7b2' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='d96b9abb-1709-4896-9180-487246aaf7b2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Serum Iron', (select reporting_description from clinlims.test where guid = 'd96b9abb-1709-4896-9180-487246aaf7b2' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='d96b9abb-1709-4896-9180-487246aaf7b2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Total Bilirubin', (select name from clinlims.test where guid = '9603171b-e2e5-4624-b0f7-f12ec3b9ef88' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='9603171b-e2e5-4624-b0f7-f12ec3b9ef88';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Total Bilirubin', (select reporting_description from clinlims.test where guid = '9603171b-e2e5-4624-b0f7-f12ec3b9ef88' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='9603171b-e2e5-4624-b0f7-f12ec3b9ef88';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Direct Bilirubin', (select name from clinlims.test where guid = 'b2c507d7-d394-4103-9f08-1cc72170240e' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='b2c507d7-d394-4103-9f08-1cc72170240e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Direct Bilirubin', (select reporting_description from clinlims.test where guid = 'b2c507d7-d394-4103-9f08-1cc72170240e' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='b2c507d7-d394-4103-9f08-1cc72170240e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Indirect Bilirubin', (select name from clinlims.test where guid = '9d0dd00c-ff58-49c5-baa2-5ceb6f9bacde' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='9d0dd00c-ff58-49c5-baa2-5ceb6f9bacde';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Indirect Bilirubin', (select reporting_description from clinlims.test where guid = '9d0dd00c-ff58-49c5-baa2-5ceb6f9bacde' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='9d0dd00c-ff58-49c5-baa2-5ceb6f9bacde';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'SGOT', (select name from clinlims.test where guid = '6a27a360-b6cd-436a-af1b-66a5f5862fbb' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='6a27a360-b6cd-436a-af1b-66a5f5862fbb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'SGOT', (select reporting_description from clinlims.test where guid = '6a27a360-b6cd-436a-af1b-66a5f5862fbb' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='6a27a360-b6cd-436a-af1b-66a5f5862fbb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'SGPT', (select name from clinlims.test where guid = 'c22b7db9-cf41-41bb-88d1-4d28a3fa001f' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='c22b7db9-cf41-41bb-88d1-4d28a3fa001f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'SGPT', (select reporting_description from clinlims.test where guid = 'c22b7db9-cf41-41bb-88d1-4d28a3fa001f' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='c22b7db9-cf41-41bb-88d1-4d28a3fa001f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Alkaline phosphatase', (select name from clinlims.test where guid = '5e4f53c3-8d99-4769-bc42-eefa9a540533' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='5e4f53c3-8d99-4769-bc42-eefa9a540533';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Alkaline phosphatase', (select reporting_description from clinlims.test where guid = '5e4f53c3-8d99-4769-bc42-eefa9a540533' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='5e4f53c3-8d99-4769-bc42-eefa9a540533';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Total Cholesterol', (select name from clinlims.test where guid = '3a4f6064-919b-448e-b625-fc7621dc58f5' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='3a4f6064-919b-448e-b625-fc7621dc58f5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Total Cholesterol', (select reporting_description from clinlims.test where guid = '3a4f6064-919b-448e-b625-fc7621dc58f5' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='3a4f6064-919b-448e-b625-fc7621dc58f5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Triglyceride', (select name from clinlims.test where guid = '3a632ea8-8bd0-4e88-afff-c500c295824d' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='3a632ea8-8bd0-4e88-afff-c500c295824d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Triglyceride', (select reporting_description from clinlims.test where guid = '3a632ea8-8bd0-4e88-afff-c500c295824d' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='3a632ea8-8bd0-4e88-afff-c500c295824d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Lipid', (select name from clinlims.test where guid = '53297be9-7f37-470a-ab40-fe211f928cf9' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='53297be9-7f37-470a-ab40-fe211f928cf9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Lipid', (select reporting_description from clinlims.test where guid = '53297be9-7f37-470a-ab40-fe211f928cf9' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='53297be9-7f37-470a-ab40-fe211f928cf9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HDL', (select name from clinlims.test where guid = '204615e7-2c78-4de9-9fbd-55bf71a8283b' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='204615e7-2c78-4de9-9fbd-55bf71a8283b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HDL', (select reporting_description from clinlims.test where guid = '204615e7-2c78-4de9-9fbd-55bf71a8283b' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='204615e7-2c78-4de9-9fbd-55bf71a8283b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'LDL', (select name from clinlims.test where guid = 'fd70e329-f5cf-402d-9920-cbc105badc00' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='fd70e329-f5cf-402d-9920-cbc105badc00';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'LDL', (select reporting_description from clinlims.test where guid = 'fd70e329-f5cf-402d-9920-cbc105badc00' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='fd70e329-f5cf-402d-9920-cbc105badc00';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'VLDL', (select name from clinlims.test where guid = '3423ee83-6bb0-474a-add1-fd234b68233c' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='3423ee83-6bb0-474a-add1-fd234b68233c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'VLDL', (select reporting_description from clinlims.test where guid = '3423ee83-6bb0-474a-add1-fd234b68233c' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='3423ee83-6bb0-474a-add1-fd234b68233c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'MBG', (select name from clinlims.test where guid = '832b7cd6-17a5-4816-88d5-8edd27b76578' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='832b7cd6-17a5-4816-88d5-8edd27b76578';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'MBG', (select reporting_description from clinlims.test where guid = '832b7cd6-17a5-4816-88d5-8edd27b76578' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='832b7cd6-17a5-4816-88d5-8edd27b76578';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Glycated hemoglobin', (select name from clinlims.test where guid = '999ca803-9249-44cb-8e91-b2846631e84a' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='999ca803-9249-44cb-8e91-b2846631e84a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Glycated hemoglobin', (select reporting_description from clinlims.test where guid = '999ca803-9249-44cb-8e91-b2846631e84a' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='999ca803-9249-44cb-8e91-b2846631e84a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'LDH', (select name from clinlims.test where guid = '5531e417-fa46-4cce-8126-14fece3118f7' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='5531e417-fa46-4cce-8126-14fece3118f7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'LDH', (select reporting_description from clinlims.test where guid = '5531e417-fa46-4cce-8126-14fece3118f7' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='5531e417-fa46-4cce-8126-14fece3118f7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Triponin I', (select name from clinlims.test where guid = '111519b3-3050-4e71-b760-0beae50c0d48' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='111519b3-3050-4e71-b760-0beae50c0d48';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Triponin I', (select reporting_description from clinlims.test where guid = '111519b3-3050-4e71-b760-0beae50c0d48' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='111519b3-3050-4e71-b760-0beae50c0d48';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Ph', (select name from clinlims.test where guid = '52c4c294-e659-4530-aabd-270f0474a9e1' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='52c4c294-e659-4530-aabd-270f0474a9e1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Ph', (select reporting_description from clinlims.test where guid = '52c4c294-e659-4530-aabd-270f0474a9e1' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='52c4c294-e659-4530-aabd-270f0474a9e1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'PaCO3', (select name from clinlims.test where guid = 'ca2403f1-be93-4427-8bd9-1faf64311734' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='ca2403f1-be93-4427-8bd9-1faf64311734';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'PaCO3', (select reporting_description from clinlims.test where guid = 'ca2403f1-be93-4427-8bd9-1faf64311734' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='ca2403f1-be93-4427-8bd9-1faf64311734';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HCO3', (select name from clinlims.test where guid = '69ac5a10-21de-46a3-b784-f6f289041eec' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='69ac5a10-21de-46a3-b784-f6f289041eec';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HCO3', (select reporting_description from clinlims.test where guid = '69ac5a10-21de-46a3-b784-f6f289041eec' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='69ac5a10-21de-46a3-b784-f6f289041eec';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'O2 Saturation', (select name from clinlims.test where guid = '1a33f1e1-03c7-4f38-ba08-2d17efc96b67' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='1a33f1e1-03c7-4f38-ba08-2d17efc96b67';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'O2 Saturation', (select reporting_description from clinlims.test where guid = '1a33f1e1-03c7-4f38-ba08-2d17efc96b67' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='1a33f1e1-03c7-4f38-ba08-2d17efc96b67';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'PaO2', (select name from clinlims.test where guid = 'c0f1469c-72c4-4576-b4cf-1219fc78326b' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='c0f1469c-72c4-4576-b4cf-1219fc78326b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'PaO2', (select reporting_description from clinlims.test where guid = 'c0f1469c-72c4-4576-b4cf-1219fc78326b' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='c0f1469c-72c4-4576-b4cf-1219fc78326b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'BE', (select name from clinlims.test where guid = '23845403-46de-4bc1-89c4-c47b3cbce8c6' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='23845403-46de-4bc1-89c4-c47b3cbce8c6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'BE', (select reporting_description from clinlims.test where guid = '23845403-46de-4bc1-89c4-c47b3cbce8c6' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='23845403-46de-4bc1-89c4-c47b3cbce8c6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Glucose', (select name from clinlims.test where guid = '9ba7a73a-f0fa-4343-b8b6-e32af07c0fe9' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='9ba7a73a-f0fa-4343-b8b6-e32af07c0fe9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Glucose', (select reporting_description from clinlims.test where guid = '9ba7a73a-f0fa-4343-b8b6-e32af07c0fe9' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='9ba7a73a-f0fa-4343-b8b6-e32af07c0fe9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'GTT', (select name from clinlims.test where guid = 'c0aae398-ba28-4d61-9fc8-6c9235c1e16b' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='c0aae398-ba28-4d61-9fc8-6c9235c1e16b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'GTT', (select reporting_description from clinlims.test where guid = 'c0aae398-ba28-4d61-9fc8-6c9235c1e16b' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='c0aae398-ba28-4d61-9fc8-6c9235c1e16b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'GTT', (select name from clinlims.test where guid = '00f41c24-5573-49cd-8e18-6cf70d6829a5' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='00f41c24-5573-49cd-8e18-6cf70d6829a5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'GTT', (select reporting_description from clinlims.test where guid = '00f41c24-5573-49cd-8e18-6cf70d6829a5' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='00f41c24-5573-49cd-8e18-6cf70d6829a5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'GTT', (select name from clinlims.test where guid = '43c9c8f3-f871-4266-97e3-30213c26da6d' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='43c9c8f3-f871-4266-97e3-30213c26da6d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'GTT', (select reporting_description from clinlims.test where guid = '43c9c8f3-f871-4266-97e3-30213c26da6d' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='43c9c8f3-f871-4266-97e3-30213c26da6d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'GTT', (select name from clinlims.test where guid = 'fe6309ae-89c2-4964-8765-f5624ae16b95' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='fe6309ae-89c2-4964-8765-f5624ae16b95';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'GTT', (select reporting_description from clinlims.test where guid = 'fe6309ae-89c2-4964-8765-f5624ae16b95' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='fe6309ae-89c2-4964-8765-f5624ae16b95';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'GTT', (select name from clinlims.test where guid = '1742cce8-7575-48b6-8008-b9724a93759f' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='1742cce8-7575-48b6-8008-b9724a93759f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'GTT', (select reporting_description from clinlims.test where guid = '1742cce8-7575-48b6-8008-b9724a93759f' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='1742cce8-7575-48b6-8008-b9724a93759f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'GTT', (select name from clinlims.test where guid = '7b04c361-de06-499d-9226-a16e759da51a' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='7b04c361-de06-499d-9226-a16e759da51a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'GTT', (select reporting_description from clinlims.test where guid = '7b04c361-de06-499d-9226-a16e759da51a' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='7b04c361-de06-499d-9226-a16e759da51a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'GTT', (select name from clinlims.test where guid = 'd6e20942-26d4-41bb-8d6b-ca359b7ca23a' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='d6e20942-26d4-41bb-8d6b-ca359b7ca23a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'GTT', (select reporting_description from clinlims.test where guid = 'd6e20942-26d4-41bb-8d6b-ca359b7ca23a' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='d6e20942-26d4-41bb-8d6b-ca359b7ca23a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'GPP', (select name from clinlims.test where guid = '6eeb96c2-6add-4009-97c1-15072f47d50a' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='6eeb96c2-6add-4009-97c1-15072f47d50a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'GPP', (select reporting_description from clinlims.test where guid = '6eeb96c2-6add-4009-97c1-15072f47d50a' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='6eeb96c2-6add-4009-97c1-15072f47d50a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'BUN', (select name from clinlims.test where guid = '0cd87dc2-3b49-4d5f-901e-253db90e311b' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='0cd87dc2-3b49-4d5f-901e-253db90e311b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'BUN', (select reporting_description from clinlims.test where guid = '0cd87dc2-3b49-4d5f-901e-253db90e311b' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='0cd87dc2-3b49-4d5f-901e-253db90e311b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Nitrogen', (select name from clinlims.test where guid = '3d235055-f650-4791-a20f-79487c976213' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='3d235055-f650-4791-a20f-79487c976213';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Nitrogen', (select reporting_description from clinlims.test where guid = '3d235055-f650-4791-a20f-79487c976213' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='3d235055-f650-4791-a20f-79487c976213';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Creatinine', (select name from clinlims.test where guid = '6a317484-723e-456e-9977-c8c753738a92' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='6a317484-723e-456e-9977-c8c753738a92';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Creatinine', (select reporting_description from clinlims.test where guid = '6a317484-723e-456e-9977-c8c753738a92' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='6a317484-723e-456e-9977-c8c753738a92';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'ALT', (select name from clinlims.test where guid = '854af1b5-0bd1-4276-9640-f8501734940c' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='854af1b5-0bd1-4276-9640-f8501734940c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'ALT', (select reporting_description from clinlims.test where guid = '854af1b5-0bd1-4276-9640-f8501734940c' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='854af1b5-0bd1-4276-9640-f8501734940c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'AST', (select name from clinlims.test where guid = '48929ead-20f3-461b-9b2b-f8d365d0e2c9' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='48929ead-20f3-461b-9b2b-f8d365d0e2c9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'AST', (select reporting_description from clinlims.test where guid = '48929ead-20f3-461b-9b2b-f8d365d0e2c9' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='48929ead-20f3-461b-9b2b-f8d365d0e2c9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'total cholesterol', (select name from clinlims.test where guid = 'ee7fc567-b1ee-4ed0-9a6e-65960e8b7b3c' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='ee7fc567-b1ee-4ed0-9a6e-65960e8b7b3c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'total cholesterol', (select reporting_description from clinlims.test where guid = 'ee7fc567-b1ee-4ed0-9a6e-65960e8b7b3c' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='ee7fc567-b1ee-4ed0-9a6e-65960e8b7b3c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HDL cholesterol', (select name from clinlims.test where guid = '651426de-30cf-4bc6-8bd2-7d13a52b9f58' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='651426de-30cf-4bc6-8bd2-7d13a52b9f58';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HDL cholesterol', (select reporting_description from clinlims.test where guid = '651426de-30cf-4bc6-8bd2-7d13a52b9f58' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='651426de-30cf-4bc6-8bd2-7d13a52b9f58';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'LDL cholesterol', (select name from clinlims.test where guid = '36a830b2-ed42-4ca6-9f48-bf858cd40547' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='36a830b2-ed42-4ca6-9f48-bf858cd40547';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'LDL cholesterol', (select reporting_description from clinlims.test where guid = '36a830b2-ed42-4ca6-9f48-bf858cd40547' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='36a830b2-ed42-4ca6-9f48-bf858cd40547';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'VLDL - Calculate', (select name from clinlims.test where guid = '1368808b-bc6c-4d02-8571-a1986b270e73' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='1368808b-bc6c-4d02-8571-a1986b270e73';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'VLDL - Calculate', (select reporting_description from clinlims.test where guid = '1368808b-bc6c-4d02-8571-a1986b270e73' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='1368808b-bc6c-4d02-8571-a1986b270e73';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Triglicerides', (select name from clinlims.test where guid = '2301cd74-e218-41f9-be5d-2fdd4b84a62c' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='2301cd74-e218-41f9-be5d-2fdd4b84a62c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Triglicerides', (select reporting_description from clinlims.test where guid = '2301cd74-e218-41f9-be5d-2fdd4b84a62c' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='2301cd74-e218-41f9-be5d-2fdd4b84a62c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Uric Acid', (select name from clinlims.test where guid = '6b2cd446-602d-4e7f-a32a-d06af64c256e' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='6b2cd446-602d-4e7f-a32a-d06af64c256e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Uric Acid', (select reporting_description from clinlims.test where guid = '6b2cd446-602d-4e7f-a32a-d06af64c256e' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='6b2cd446-602d-4e7f-a32a-d06af64c256e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Ion Chlorure Test', (select name from clinlims.test where guid = '0805e33a-954c-4b1a-857d-3109bbf7e7a5' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='0805e33a-954c-4b1a-857d-3109bbf7e7a5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Ion Chlorure Test', (select reporting_description from clinlims.test where guid = '0805e33a-954c-4b1a-857d-3109bbf7e7a5' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='0805e33a-954c-4b1a-857d-3109bbf7e7a5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Calcium', (select name from clinlims.test where guid = '9b44c088-446f-404a-83fd-637f33354b15' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='9b44c088-446f-404a-83fd-637f33354b15';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Calcium', (select reporting_description from clinlims.test where guid = '9b44c088-446f-404a-83fd-637f33354b15' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='9b44c088-446f-404a-83fd-637f33354b15';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Total protein', (select name from clinlims.test where guid = 'e0019ce7-2fe0-4da1-841f-967111118f68' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='e0019ce7-2fe0-4da1-841f-967111118f68';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Total protein', (select reporting_description from clinlims.test where guid = 'e0019ce7-2fe0-4da1-841f-967111118f68' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='e0019ce7-2fe0-4da1-841f-967111118f68';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Creatinin', (select name from clinlims.test where guid = 'd2cf9377-7ed5-4be6-911f-06e66181ac3e' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='d2cf9377-7ed5-4be6-911f-06e66181ac3e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Creatinin', (select reporting_description from clinlims.test where guid = 'd2cf9377-7ed5-4be6-911f-06e66181ac3e' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='d2cf9377-7ed5-4be6-911f-06e66181ac3e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Creatinin', (select name from clinlims.test where guid = '39ea31e0-f734-4442-b02c-2a7154c1d6bd' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='39ea31e0-f734-4442-b02c-2a7154c1d6bd';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Creatinin', (select reporting_description from clinlims.test where guid = '39ea31e0-f734-4442-b02c-2a7154c1d6bd' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='39ea31e0-f734-4442-b02c-2a7154c1d6bd';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Creatinin', (select name from clinlims.test where guid = '1b8b5ea5-0d80-4f1d-9c9e-7a97044e2131' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='1b8b5ea5-0d80-4f1d-9c9e-7a97044e2131';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Creatinin', (select reporting_description from clinlims.test where guid = '1b8b5ea5-0d80-4f1d-9c9e-7a97044e2131' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='1b8b5ea5-0d80-4f1d-9c9e-7a97044e2131';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'SGPT/ALT', (select name from clinlims.test where guid = '843d1329-d0ae-4763-bb88-7600afdbcccc' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='843d1329-d0ae-4763-bb88-7600afdbcccc';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'SGPT/ALT', (select reporting_description from clinlims.test where guid = '843d1329-d0ae-4763-bb88-7600afdbcccc' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='843d1329-d0ae-4763-bb88-7600afdbcccc';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'SGPT/ALT', (select name from clinlims.test where guid = '3d7b12c4-174f-4a74-a306-803a4cfd8b1a' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='3d7b12c4-174f-4a74-a306-803a4cfd8b1a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'SGPT/ALT', (select reporting_description from clinlims.test where guid = '3d7b12c4-174f-4a74-a306-803a4cfd8b1a' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='3d7b12c4-174f-4a74-a306-803a4cfd8b1a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'SGPT/ALT', (select name from clinlims.test where guid = '3019d193-f831-44d7-aeab-72d5d7bcfc32' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='3019d193-f831-44d7-aeab-72d5d7bcfc32';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'SGPT/ALT', (select reporting_description from clinlims.test where guid = '3019d193-f831-44d7-aeab-72d5d7bcfc32' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='3019d193-f831-44d7-aeab-72d5d7bcfc32';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'SGOT/AST', (select name from clinlims.test where guid = '294e6827-1668-44d3-a985-abc4f375f5d1' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='294e6827-1668-44d3-a985-abc4f375f5d1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'SGOT/AST', (select reporting_description from clinlims.test where guid = '294e6827-1668-44d3-a985-abc4f375f5d1' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='294e6827-1668-44d3-a985-abc4f375f5d1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'SGOT/AST', (select name from clinlims.test where guid = '5554c9d0-a7b1-4acf-8686-b5f0074945a3' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='5554c9d0-a7b1-4acf-8686-b5f0074945a3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'SGOT/AST', (select reporting_description from clinlims.test where guid = '5554c9d0-a7b1-4acf-8686-b5f0074945a3' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='5554c9d0-a7b1-4acf-8686-b5f0074945a3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'SGOT/AST', (select name from clinlims.test where guid = '16eb84b7-3696-4ba5-b3a0-7706003cf6d8' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='16eb84b7-3696-4ba5-b3a0-7706003cf6d8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'SGOT/AST', (select reporting_description from clinlims.test where guid = '16eb84b7-3696-4ba5-b3a0-7706003cf6d8' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='16eb84b7-3696-4ba5-b3a0-7706003cf6d8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CRP Quantitatif', (select name from clinlims.test where guid = '1fae3704-6882-4512-8714-d6e8c9d18bfd' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='1fae3704-6882-4512-8714-d6e8c9d18bfd';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CRP Quantitatif', (select reporting_description from clinlims.test where guid = '1fae3704-6882-4512-8714-d6e8c9d18bfd' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='1fae3704-6882-4512-8714-d6e8c9d18bfd';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CRP Quantitatif', (select name from clinlims.test where guid = '49be795d-35f3-4132-bce3-d32b24427bae' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='49be795d-35f3-4132-bce3-d32b24427bae';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CRP Quantitatif', (select reporting_description from clinlims.test where guid = '49be795d-35f3-4132-bce3-d32b24427bae' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='49be795d-35f3-4132-bce3-d32b24427bae';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'C3  Complement', (select name from clinlims.test where guid = '076ca16e-bff8-463b-89f1-b1d2e59c5065' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='076ca16e-bff8-463b-89f1-b1d2e59c5065';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'C3  Complement', (select reporting_description from clinlims.test where guid = '076ca16e-bff8-463b-89f1-b1d2e59c5065' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='076ca16e-bff8-463b-89f1-b1d2e59c5065';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'C4 complement', (select name from clinlims.test where guid = 'a3a0ceda-cb0c-48ef-b419-990350f2f350' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='a3a0ceda-cb0c-48ef-b419-990350f2f350';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'C4 complement', (select reporting_description from clinlims.test where guid = 'a3a0ceda-cb0c-48ef-b419-990350f2f350' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='a3a0ceda-cb0c-48ef-b419-990350f2f350';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'C3  Complement', (select name from clinlims.test where guid = '0d456528-7995-4ccd-aded-10b31eb9eae9' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='0d456528-7995-4ccd-aded-10b31eb9eae9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'C3  Complement', (select reporting_description from clinlims.test where guid = '0d456528-7995-4ccd-aded-10b31eb9eae9' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='0d456528-7995-4ccd-aded-10b31eb9eae9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'C4 complement', (select name from clinlims.test where guid = '37652aad-6ac8-45de-9086-1bc687b7fb84' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='37652aad-6ac8-45de-9086-1bc687b7fb84';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'C4 complement', (select reporting_description from clinlims.test where guid = '37652aad-6ac8-45de-9086-1bc687b7fb84' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='37652aad-6ac8-45de-9086-1bc687b7fb84';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Rhumatoid Factor', (select name from clinlims.test where guid = 'b1e6e724-1eda-4315-bb28-5546bc6f53d8' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='b1e6e724-1eda-4315-bb28-5546bc6f53d8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Rhumatoid Factor', (select reporting_description from clinlims.test where guid = 'b1e6e724-1eda-4315-bb28-5546bc6f53d8' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='b1e6e724-1eda-4315-bb28-5546bc6f53d8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Bacteria research', (select name from clinlims.test where guid = '6459833b-c21f-4ff7-9efb-478900e979d1' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='6459833b-c21f-4ff7-9efb-478900e979d1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Bacteria research', (select reporting_description from clinlims.test where guid = '6459833b-c21f-4ff7-9efb-478900e979d1' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='6459833b-c21f-4ff7-9efb-478900e979d1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Simple Yeast', (select name from clinlims.test where guid = 'e70ed356-507b-4ffb-8ab2-26650a670876' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='e70ed356-507b-4ffb-8ab2-26650a670876';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Simple Yeast', (select reporting_description from clinlims.test where guid = 'e70ed356-507b-4ffb-8ab2-26650a670876' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='e70ed356-507b-4ffb-8ab2-26650a670876';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Budding Yeast', (select name from clinlims.test where guid = '1739cdf8-f441-42af-9c32-de2bc871fb47' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='1739cdf8-f441-42af-9c32-de2bc871fb47';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Budding Yeast', (select reporting_description from clinlims.test where guid = '1739cdf8-f441-42af-9c32-de2bc871fb47' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='1739cdf8-f441-42af-9c32-de2bc871fb47';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Trichomonas vaginalis', (select name from clinlims.test where guid = '0388358d-0821-43d5-a4be-9cef813eb351' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='0388358d-0821-43d5-a4be-9cef813eb351';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Trichomonas vaginalis', (select reporting_description from clinlims.test where guid = '0388358d-0821-43d5-a4be-9cef813eb351' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='0388358d-0821-43d5-a4be-9cef813eb351';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'White Blood Cell', (select name from clinlims.test where guid = '7d1cfd1b-8b0c-4056-b838-3803953edf16' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='7d1cfd1b-8b0c-4056-b838-3803953edf16';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'White Blood Cell', (select reporting_description from clinlims.test where guid = '7d1cfd1b-8b0c-4056-b838-3803953edf16' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='7d1cfd1b-8b0c-4056-b838-3803953edf16';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Red Blood Cell', (select name from clinlims.test where guid = '2eb668a0-694d-4f09-a76e-e46dc2aa5101' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='2eb668a0-694d-4f09-a76e-e46dc2aa5101';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Red Blood Cell', (select reporting_description from clinlims.test where guid = '2eb668a0-694d-4f09-a76e-e46dc2aa5101' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='2eb668a0-694d-4f09-a76e-e46dc2aa5101';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hyphae', (select name from clinlims.test where guid = '7725c80a-58b6-4236-addf-cb26274ac715' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='7725c80a-58b6-4236-addf-cb26274ac715';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hyphae', (select reporting_description from clinlims.test where guid = '7725c80a-58b6-4236-addf-cb26274ac715' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='7725c80a-58b6-4236-addf-cb26274ac715';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Epithelial Cells', (select name from clinlims.test where guid = 'c70abe04-3392-468e-9bd1-579920892e70' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='c70abe04-3392-468e-9bd1-579920892e70';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Epithelial Cells', (select reporting_description from clinlims.test where guid = 'c70abe04-3392-468e-9bd1-579920892e70' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='c70abe04-3392-468e-9bd1-579920892e70';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Bacteria research', (select name from clinlims.test where guid = '4096abfd-0870-4d05-b325-db108143869a' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='4096abfd-0870-4d05-b325-db108143869a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Bacteria research', (select reporting_description from clinlims.test where guid = '4096abfd-0870-4d05-b325-db108143869a' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='4096abfd-0870-4d05-b325-db108143869a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Simple Yeast', (select name from clinlims.test where guid = '862a450c-b404-486c-a1fb-7bbf9784ac5c' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='862a450c-b404-486c-a1fb-7bbf9784ac5c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Simple Yeast', (select reporting_description from clinlims.test where guid = '862a450c-b404-486c-a1fb-7bbf9784ac5c' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='862a450c-b404-486c-a1fb-7bbf9784ac5c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Budding Yeast', (select name from clinlims.test where guid = '5f66c8a0-c2c8-4b99-b568-854acde29b57' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='5f66c8a0-c2c8-4b99-b568-854acde29b57';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Budding Yeast', (select reporting_description from clinlims.test where guid = '5f66c8a0-c2c8-4b99-b568-854acde29b57' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='5f66c8a0-c2c8-4b99-b568-854acde29b57';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Trichomonas hominis', (select name from clinlims.test where guid = 'a6c9a5fd-d307-49f9-808f-2e54c3250e7e' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='a6c9a5fd-d307-49f9-808f-2e54c3250e7e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Trichomonas hominis', (select reporting_description from clinlims.test where guid = 'a6c9a5fd-d307-49f9-808f-2e54c3250e7e' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='a6c9a5fd-d307-49f9-808f-2e54c3250e7e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'White Blood Cell', (select name from clinlims.test where guid = '481b1b27-a093-4bba-89ca-a59414336613' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='481b1b27-a093-4bba-89ca-a59414336613';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'White Blood Cell', (select reporting_description from clinlims.test where guid = '481b1b27-a093-4bba-89ca-a59414336613' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='481b1b27-a093-4bba-89ca-a59414336613';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Red Blood Cell', (select name from clinlims.test where guid = '1308de85-31f2-40a2-bf76-ee6bd767cf02' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='1308de85-31f2-40a2-bf76-ee6bd767cf02';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Red Blood Cell', (select reporting_description from clinlims.test where guid = '1308de85-31f2-40a2-bf76-ee6bd767cf02' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='1308de85-31f2-40a2-bf76-ee6bd767cf02';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hyphae', (select name from clinlims.test where guid = 'eeecc2d0-c9d2-4889-9303-0862207ca24f' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='eeecc2d0-c9d2-4889-9303-0862207ca24f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hyphae', (select reporting_description from clinlims.test where guid = 'eeecc2d0-c9d2-4889-9303-0862207ca24f' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='eeecc2d0-c9d2-4889-9303-0862207ca24f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Epithelial Cells', (select name from clinlims.test where guid = '0fa2dee9-9b31-459c-8cea-d606a3d9b689' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='0fa2dee9-9b31-459c-8cea-d606a3d9b689';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Epithelial Cells', (select reporting_description from clinlims.test where guid = '0fa2dee9-9b31-459c-8cea-d606a3d9b689' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='0fa2dee9-9b31-459c-8cea-d606a3d9b689';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'KOH', (select name from clinlims.test where guid = '990b6ef5-3f41-42e4-9dda-5323abccd90f' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='990b6ef5-3f41-42e4-9dda-5323abccd90f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'KOH', (select reporting_description from clinlims.test where guid = '990b6ef5-3f41-42e4-9dda-5323abccd90f' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='990b6ef5-3f41-42e4-9dda-5323abccd90f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Rivalta', (select name from clinlims.test where guid = '881c91f1-ba99-4610-b43a-d230b13f03ab' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='881c91f1-ba99-4610-b43a-d230b13f03ab';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Rivalta', (select reporting_description from clinlims.test where guid = '881c91f1-ba99-4610-b43a-d230b13f03ab' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='881c91f1-ba99-4610-b43a-d230b13f03ab';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Catalase', (select name from clinlims.test where guid = '83c2b67d-a6f9-4b1c-a748-4025cda59f45' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='83c2b67d-a6f9-4b1c-a748-4025cda59f45';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Catalase', (select reporting_description from clinlims.test where guid = '83c2b67d-a6f9-4b1c-a748-4025cda59f45' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='83c2b67d-a6f9-4b1c-a748-4025cda59f45';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Oxydase', (select name from clinlims.test where guid = '39920ded-7aef-4550-b82e-76b87ae72f29' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='39920ded-7aef-4550-b82e-76b87ae72f29';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Oxydase', (select reporting_description from clinlims.test where guid = '39920ded-7aef-4550-b82e-76b87ae72f29' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='39920ded-7aef-4550-b82e-76b87ae72f29';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Coagulase', (select name from clinlims.test where guid = 'e2deeb4a-ce81-4b66-acf0-f1745bb9f165' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='e2deeb4a-ce81-4b66-acf0-f1745bb9f165';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Coagulase', (select reporting_description from clinlims.test where guid = 'e2deeb4a-ce81-4b66-acf0-f1745bb9f165' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='e2deeb4a-ce81-4b66-acf0-f1745bb9f165';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'DNAse', (select name from clinlims.test where guid = '253c4850-427f-4390-be58-b7359ac05db7' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='253c4850-427f-4390-be58-b7359ac05db7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'DNAse', (select reporting_description from clinlims.test where guid = '253c4850-427f-4390-be58-b7359ac05db7' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='253c4850-427f-4390-be58-b7359ac05db7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'b878543f-db93-4768-9d7d-cf8b0d7a240b' ), (select name from clinlims.test where guid = 'b878543f-db93-4768-9d7d-cf8b0d7a240b' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='b878543f-db93-4768-9d7d-cf8b0d7a240b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'b878543f-db93-4768-9d7d-cf8b0d7a240b' ), (select reporting_description from clinlims.test where guid = 'b878543f-db93-4768-9d7d-cf8b0d7a240b' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='b878543f-db93-4768-9d7d-cf8b0d7a240b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Urée-tryptophane', (select name from clinlims.test where guid = '224666d5-db5d-4064-8ac1-1df0bad73e34' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='224666d5-db5d-4064-8ac1-1df0bad73e34';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Urée-tryptophane', (select reporting_description from clinlims.test where guid = '224666d5-db5d-4064-8ac1-1df0bad73e34' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='224666d5-db5d-4064-8ac1-1df0bad73e34';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Mobility', (select name from clinlims.test where guid = '681f960c-8ec2-46c7-b8c3-2df4e73475a2' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='681f960c-8ec2-46c7-b8c3-2df4e73475a2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Mobility', (select reporting_description from clinlims.test where guid = '681f960c-8ec2-46c7-b8c3-2df4e73475a2' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='681f960c-8ec2-46c7-b8c3-2df4e73475a2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'potasse test', (select name from clinlims.test where guid = 'babd9558-d97d-4f17-8682-34918d5582bb' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='babd9558-d97d-4f17-8682-34918d5582bb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'potasse test', (select reporting_description from clinlims.test where guid = 'babd9558-d97d-4f17-8682-34918d5582bb' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='babd9558-d97d-4f17-8682-34918d5582bb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'porphyrine test', (select name from clinlims.test where guid = '337d6911-cbc0-4192-8917-a99172770303' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='337d6911-cbc0-4192-8917-a99172770303';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'porphyrine test', (select reporting_description from clinlims.test where guid = '337d6911-cbc0-4192-8917-a99172770303' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='337d6911-cbc0-4192-8917-a99172770303';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'ONPG', (select name from clinlims.test where guid = '5afdae38-1ee0-4bf3-8b24-3ecc3e238175' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='5afdae38-1ee0-4bf3-8b24-3ecc3e238175';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'ONPG', (select reporting_description from clinlims.test where guid = '5afdae38-1ee0-4bf3-8b24-3ecc3e238175' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='5afdae38-1ee0-4bf3-8b24-3ecc3e238175';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Voges-Proskauer reaction', (select name from clinlims.test where guid = '08b4bd56-51d3-4151-bf69-2c9fc9d7a6b8' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='08b4bd56-51d3-4151-bf69-2c9fc9d7a6b8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Voges-Proskauer reaction', (select reporting_description from clinlims.test where guid = '08b4bd56-51d3-4151-bf69-2c9fc9d7a6b8' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='08b4bd56-51d3-4151-bf69-2c9fc9d7a6b8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Camp-test', (select name from clinlims.test where guid = '6ded73d8-61c1-4a01-8cb0-5c9dc18bde08' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='6ded73d8-61c1-4a01-8cb0-5c9dc18bde08';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Camp-test', (select reporting_description from clinlims.test where guid = '6ded73d8-61c1-4a01-8cb0-5c9dc18bde08' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='6ded73d8-61c1-4a01-8cb0-5c9dc18bde08';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'agglutination technique', (select name from clinlims.test where guid = 'f62e0523-ef87-4ac9-b85f-8d7f15fbfb33' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='f62e0523-ef87-4ac9-b85f-8d7f15fbfb33';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'agglutination technique', (select reporting_description from clinlims.test where guid = 'f62e0523-ef87-4ac9-b85f-8d7f15fbfb33' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='f62e0523-ef87-4ac9-b85f-8d7f15fbfb33';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Gram Stain', (select name from clinlims.test where guid = 'bf1083f8-e69f-4bed-9ca6-8e7e4f525be1' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='bf1083f8-e69f-4bed-9ca6-8e7e4f525be1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Gram Stain', (select reporting_description from clinlims.test where guid = 'bf1083f8-e69f-4bed-9ca6-8e7e4f525be1' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='bf1083f8-e69f-4bed-9ca6-8e7e4f525be1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'AFB', (select name from clinlims.test where guid = 'a0b5db4f-7fda-46bd-b6c7-2c7c0ea2e53c' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='a0b5db4f-7fda-46bd-b6c7-2c7c0ea2e53c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'AFB', (select reporting_description from clinlims.test where guid = 'a0b5db4f-7fda-46bd-b6c7-2c7c0ea2e53c' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='a0b5db4f-7fda-46bd-b6c7-2c7c0ea2e53c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Auramine', (select name from clinlims.test where guid = 'ecabc039-7443-48bf-8dc9-11e139465b15' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='ecabc039-7443-48bf-8dc9-11e139465b15';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Auramine', (select reporting_description from clinlims.test where guid = 'ecabc039-7443-48bf-8dc9-11e139465b15' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='ecabc039-7443-48bf-8dc9-11e139465b15';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Acridine Orange', (select name from clinlims.test where guid = '1a569eda-cc72-47a0-92b4-73a31e2d8ea3' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='1a569eda-cc72-47a0-92b4-73a31e2d8ea3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Acridine Orange', (select reporting_description from clinlims.test where guid = '1a569eda-cc72-47a0-92b4-73a31e2d8ea3' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='1a569eda-cc72-47a0-92b4-73a31e2d8ea3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Kinyoun', (select name from clinlims.test where guid = '8d570695-bc52-4aca-81a1-9e7d6a65cefa' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='8d570695-bc52-4aca-81a1-9e7d6a65cefa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Kinyoun', (select reporting_description from clinlims.test where guid = '8d570695-bc52-4aca-81a1-9e7d6a65cefa' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='8d570695-bc52-4aca-81a1-9e7d6a65cefa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Vaginal smear Gram stain', (select name from clinlims.test where guid = '3f1f7e22-3e4b-472e-86c7-7e69319433fa' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='3f1f7e22-3e4b-472e-86c7-7e69319433fa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Vaginal smear Gram stain', (select reporting_description from clinlims.test where guid = '3f1f7e22-3e4b-472e-86c7-7e69319433fa' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='3f1f7e22-3e4b-472e-86c7-7e69319433fa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Urethral smear Gram stain', (select name from clinlims.test where guid = '35323952-5215-4aee-aadc-00f1efa52814' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='35323952-5215-4aee-aadc-00f1efa52814';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Urethral smear Gram stain', (select reporting_description from clinlims.test where guid = '35323952-5215-4aee-aadc-00f1efa52814' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='35323952-5215-4aee-aadc-00f1efa52814';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Cholera Rapid Test', (select name from clinlims.test where guid = 'fdbe5fc9-8fae-44a2-aeb4-c5ed567b9317' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='fdbe5fc9-8fae-44a2-aeb4-c5ed567b9317';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Cholera Rapid Test', (select reporting_description from clinlims.test where guid = 'fdbe5fc9-8fae-44a2-aeb4-c5ed567b9317' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='fdbe5fc9-8fae-44a2-aeb4-c5ed567b9317';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Coproculture', (select name from clinlims.test where guid = '48a0bb75-8018-481c-8810-7ffa81fdd8e4' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='48a0bb75-8018-481c-8810-7ffa81fdd8e4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Coproculture', (select reporting_description from clinlims.test where guid = '48a0bb75-8018-481c-8810-7ffa81fdd8e4' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='48a0bb75-8018-481c-8810-7ffa81fdd8e4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Color', (select name from clinlims.test where guid = 'f51b6e0f-c29c-4bb9-8912-5340b9ffa82d' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='f51b6e0f-c29c-4bb9-8912-5340b9ffa82d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Color', (select reporting_description from clinlims.test where guid = 'f51b6e0f-c29c-4bb9-8912-5340b9ffa82d' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='f51b6e0f-c29c-4bb9-8912-5340b9ffa82d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Liquifaction', (select name from clinlims.test where guid = 'e928106e-04cc-46e2-93a6-9fd4b8820315' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='e928106e-04cc-46e2-93a6-9fd4b8820315';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Liquifaction', (select reporting_description from clinlims.test where guid = 'e928106e-04cc-46e2-93a6-9fd4b8820315' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='e928106e-04cc-46e2-93a6-9fd4b8820315';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'pH', (select name from clinlims.test where guid = 'ee2bc85f-ca3a-4173-b99b-e0f8adc13daa' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='ee2bc85f-ca3a-4173-b99b-e0f8adc13daa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'pH', (select reporting_description from clinlims.test where guid = 'ee2bc85f-ca3a-4173-b99b-e0f8adc13daa' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='ee2bc85f-ca3a-4173-b99b-e0f8adc13daa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Fructose', (select name from clinlims.test where guid = '56e722bb-9982-4291-bd9f-abdbb911897a' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='56e722bb-9982-4291-bd9f-abdbb911897a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Fructose', (select reporting_description from clinlims.test where guid = '56e722bb-9982-4291-bd9f-abdbb911897a' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='56e722bb-9982-4291-bd9f-abdbb911897a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Volume', (select name from clinlims.test where guid = 'fbf1a890-8430-464d-8f9e-b3dc5f14abc9' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='fbf1a890-8430-464d-8f9e-b3dc5f14abc9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Volume', (select reporting_description from clinlims.test where guid = 'fbf1a890-8430-464d-8f9e-b3dc5f14abc9' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='fbf1a890-8430-464d-8f9e-b3dc5f14abc9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Sperm count', (select name from clinlims.test where guid = 'e8abd188-3034-4624-bfc4-efa4ecbdbfc7' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='e8abd188-3034-4624-bfc4-efa4ecbdbfc7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Sperm count', (select reporting_description from clinlims.test where guid = 'e8abd188-3034-4624-bfc4-efa4ecbdbfc7' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='e8abd188-3034-4624-bfc4-efa4ecbdbfc7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Normal form', (select name from clinlims.test where guid = 'dc801444-32a6-4cad-8a29-092ccfc3995e' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='dc801444-32a6-4cad-8a29-092ccfc3995e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Normal form', (select reporting_description from clinlims.test where guid = 'dc801444-32a6-4cad-8a29-092ccfc3995e' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='dc801444-32a6-4cad-8a29-092ccfc3995e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Abnormal form', (select name from clinlims.test where guid = '0955fcd9-c7d8-4743-bfa1-d0c01f3dda96' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='0955fcd9-c7d8-4743-bfa1-d0c01f3dda96';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Abnormal form', (select reporting_description from clinlims.test where guid = '0955fcd9-c7d8-4743-bfa1-d0c01f3dda96' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='0955fcd9-c7d8-4743-bfa1-d0c01f3dda96';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'STAT motility', (select name from clinlims.test where guid = '649098bc-f145-4ec6-ab96-33eaa6c044bb' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='649098bc-f145-4ec6-ab96-33eaa6c044bb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'STAT motility', (select reporting_description from clinlims.test where guid = '649098bc-f145-4ec6-ab96-33eaa6c044bb' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='649098bc-f145-4ec6-ab96-33eaa6c044bb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', '1 hour motility', (select name from clinlims.test where guid = '056e076f-b4ef-4427-abc3-db5f55e38c91' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='056e076f-b4ef-4427-abc3-db5f55e38c91';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', '1 hour motility', (select reporting_description from clinlims.test where guid = '056e076f-b4ef-4427-abc3-db5f55e38c91' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='056e076f-b4ef-4427-abc3-db5f55e38c91';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', '3 hour motility', (select name from clinlims.test where guid = 'ac9d3c05-758d-4112-967a-387688c703b4' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='ac9d3c05-758d-4112-967a-387688c703b4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', '3 hour motility', (select reporting_description from clinlims.test where guid = 'ac9d3c05-758d-4112-967a-387688c703b4' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='ac9d3c05-758d-4112-967a-387688c703b4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Gram Stain', (select name from clinlims.test where guid = 'ed21ca11-580b-48e1-9ce7-bd9ffc3cc2c2' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='ed21ca11-580b-48e1-9ce7-bd9ffc3cc2c2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Gram Stain', (select reporting_description from clinlims.test where guid = 'ed21ca11-580b-48e1-9ce7-bd9ffc3cc2c2' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='ed21ca11-580b-48e1-9ce7-bd9ffc3cc2c2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Culture Bacterienne', (select name from clinlims.test where guid = '0ba6a6c0-76f3-4586-946b-b8378f2d1190' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='0ba6a6c0-76f3-4586-946b-b8378f2d1190';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Culture Bacterienne', (select reporting_description from clinlims.test where guid = '0ba6a6c0-76f3-4586-946b-b8378f2d1190' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='0ba6a6c0-76f3-4586-946b-b8378f2d1190';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hemoculture', (select name from clinlims.test where guid = '9ad1b07f-17da-4743-b8fe-a2d84a83de72' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='9ad1b07f-17da-4743-b8fe-a2d84a83de72';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hemoculture', (select reporting_description from clinlims.test where guid = '9ad1b07f-17da-4743-b8fe-a2d84a83de72' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='9ad1b07f-17da-4743-b8fe-a2d84a83de72';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Color', (select name from clinlims.test where guid = '614423e6-b3b1-46a2-970d-ba8ebb10b000' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='614423e6-b3b1-46a2-970d-ba8ebb10b000';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Color', (select reporting_description from clinlims.test where guid = '614423e6-b3b1-46a2-970d-ba8ebb10b000' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='614423e6-b3b1-46a2-970d-ba8ebb10b000';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Appearance', (select name from clinlims.test where guid = '618be06a-446a-4257-a60f-fb554fc595be' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='618be06a-446a-4257-a60f-fb554fc595be';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Appearance', (select reporting_description from clinlims.test where guid = '618be06a-446a-4257-a60f-fb554fc595be' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='618be06a-446a-4257-a60f-fb554fc595be';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Density', (select name from clinlims.test where guid = 'bcfb9665-ce23-44c0-86e6-a68e9939abd6' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='bcfb9665-ce23-44c0-86e6-a68e9939abd6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Density', (select reporting_description from clinlims.test where guid = 'bcfb9665-ce23-44c0-86e6-a68e9939abd6' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='bcfb9665-ce23-44c0-86e6-a68e9939abd6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'pH', (select name from clinlims.test where guid = '4448b108-f79e-454b-8d33-3e6af4077e8a' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='4448b108-f79e-454b-8d33-3e6af4077e8a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'pH', (select reporting_description from clinlims.test where guid = '4448b108-f79e-454b-8d33-3e6af4077e8a' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='4448b108-f79e-454b-8d33-3e6af4077e8a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Protein', (select name from clinlims.test where guid = '15b7f7dd-d8ad-4b2f-9764-6dffbc176a86' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='15b7f7dd-d8ad-4b2f-9764-6dffbc176a86';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Protein', (select reporting_description from clinlims.test where guid = '15b7f7dd-d8ad-4b2f-9764-6dffbc176a86' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='15b7f7dd-d8ad-4b2f-9764-6dffbc176a86';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Glucose', (select name from clinlims.test where guid = '0214a294-9309-47e3-a788-27a74d637613' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='0214a294-9309-47e3-a788-27a74d637613';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Glucose', (select reporting_description from clinlims.test where guid = '0214a294-9309-47e3-a788-27a74d637613' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='0214a294-9309-47e3-a788-27a74d637613';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Ketone', (select name from clinlims.test where guid = '537e95a5-9418-44f4-bd27-f7751b77d051' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='537e95a5-9418-44f4-bd27-f7751b77d051';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Ketone', (select reporting_description from clinlims.test where guid = '537e95a5-9418-44f4-bd27-f7751b77d051' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='537e95a5-9418-44f4-bd27-f7751b77d051';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Bilirubin', (select name from clinlims.test where guid = '2659561d-b60b-4bc4-84b4-e3a75a5877df' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='2659561d-b60b-4bc4-84b4-e3a75a5877df';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Bilirubin', (select reporting_description from clinlims.test where guid = '2659561d-b60b-4bc4-84b4-e3a75a5877df' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='2659561d-b60b-4bc4-84b4-e3a75a5877df';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Blood', (select name from clinlims.test where guid = 'f5338c41-8d92-45ce-927b-518ea4c020b7' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='f5338c41-8d92-45ce-927b-518ea4c020b7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Blood', (select reporting_description from clinlims.test where guid = 'f5338c41-8d92-45ce-927b-518ea4c020b7' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='f5338c41-8d92-45ce-927b-518ea4c020b7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Leucocytes', (select name from clinlims.test where guid = '27f7bdce-df34-4895-ab00-86bf2571b4e2' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='27f7bdce-df34-4895-ab00-86bf2571b4e2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Leucocytes', (select reporting_description from clinlims.test where guid = '27f7bdce-df34-4895-ab00-86bf2571b4e2' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='27f7bdce-df34-4895-ab00-86bf2571b4e2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Ascorbic acid', (select name from clinlims.test where guid = '1d331eba-e0d9-46ec-be57-5bd06d21da64' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='1d331eba-e0d9-46ec-be57-5bd06d21da64';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Ascorbic acid', (select reporting_description from clinlims.test where guid = '1d331eba-e0d9-46ec-be57-5bd06d21da64' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='1d331eba-e0d9-46ec-be57-5bd06d21da64';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Urobilirubinogen', (select name from clinlims.test where guid = '5bfbf697-5325-464f-8252-2cd43c434d29' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='5bfbf697-5325-464f-8252-2cd43c434d29';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Urobilirubinogen', (select reporting_description from clinlims.test where guid = '5bfbf697-5325-464f-8252-2cd43c434d29' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='5bfbf697-5325-464f-8252-2cd43c434d29';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Nitrite', (select name from clinlims.test where guid = 'efc5a9bd-0d75-43f1-8f40-fbf1e1c09a68' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='efc5a9bd-0d75-43f1-8f40-fbf1e1c09a68';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Nitrite', (select reporting_description from clinlims.test where guid = 'efc5a9bd-0d75-43f1-8f40-fbf1e1c09a68' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='efc5a9bd-0d75-43f1-8f40-fbf1e1c09a68';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Red blood cell', (select name from clinlims.test where guid = '763eaed9-ffaf-45e4-a4cb-dcbfe1431de7' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='763eaed9-ffaf-45e4-a4cb-dcbfe1431de7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Red blood cell', (select reporting_description from clinlims.test where guid = '763eaed9-ffaf-45e4-a4cb-dcbfe1431de7' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='763eaed9-ffaf-45e4-a4cb-dcbfe1431de7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Epithelium', (select name from clinlims.test where guid = 'b7a48efe-8414-46e9-ad4b-94c7f76c429a' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='b7a48efe-8414-46e9-ad4b-94c7f76c429a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Epithelium', (select reporting_description from clinlims.test where guid = 'b7a48efe-8414-46e9-ad4b-94c7f76c429a' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='b7a48efe-8414-46e9-ad4b-94c7f76c429a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Bacteria', (select name from clinlims.test where guid = 'fb1d90b6-004e-499e-b617-091b57f85558' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='fb1d90b6-004e-499e-b617-091b57f85558';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Bacteria', (select reporting_description from clinlims.test where guid = 'fb1d90b6-004e-499e-b617-091b57f85558' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='fb1d90b6-004e-499e-b617-091b57f85558';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Yeast', (select name from clinlims.test where guid = 'f20bcf27-e4cd-442c-9c7f-360e3c509e5a' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='f20bcf27-e4cd-442c-9c7f-360e3c509e5a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Yeast', (select reporting_description from clinlims.test where guid = 'f20bcf27-e4cd-442c-9c7f-360e3c509e5a' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='f20bcf27-e4cd-442c-9c7f-360e3c509e5a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hyphae', (select name from clinlims.test where guid = 'f46980ac-de8c-4f22-94db-458119237f76' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='f46980ac-de8c-4f22-94db-458119237f76';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hyphae', (select reporting_description from clinlims.test where guid = 'f46980ac-de8c-4f22-94db-458119237f76' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='f46980ac-de8c-4f22-94db-458119237f76';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'spores', (select name from clinlims.test where guid = '5fc63a29-cab8-445a-8ff4-63304e7b19c5' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='5fc63a29-cab8-445a-8ff4-63304e7b19c5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'spores', (select reporting_description from clinlims.test where guid = '5fc63a29-cab8-445a-8ff4-63304e7b19c5' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='5fc63a29-cab8-445a-8ff4-63304e7b19c5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Trichomonas vaginalis', (select name from clinlims.test where guid = '5666090c-19e7-4a3b-b718-328b2975c55c' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='5666090c-19e7-4a3b-b718-328b2975c55c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Trichomonas vaginalis', (select reporting_description from clinlims.test where guid = '5666090c-19e7-4a3b-b718-328b2975c55c' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='5666090c-19e7-4a3b-b718-328b2975c55c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Casts', (select name from clinlims.test where guid = '10466fd8-b401-4a06-99cb-38e84b912f53' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='10466fd8-b401-4a06-99cb-38e84b912f53';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Casts', (select reporting_description from clinlims.test where guid = '10466fd8-b401-4a06-99cb-38e84b912f53' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='10466fd8-b401-4a06-99cb-38e84b912f53';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Crystals', (select name from clinlims.test where guid = '814a80f1-f147-4027-a9d6-896fa34bbd66' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='814a80f1-f147-4027-a9d6-896fa34bbd66';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Crystals', (select reporting_description from clinlims.test where guid = '814a80f1-f147-4027-a9d6-896fa34bbd66' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='814a80f1-f147-4027-a9d6-896fa34bbd66';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Color', (select name from clinlims.test where guid = '23fe5314-795d-4052-916a-9156a3a7df06' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='23fe5314-795d-4052-916a-9156a3a7df06';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Color', (select reporting_description from clinlims.test where guid = '23fe5314-795d-4052-916a-9156a3a7df06' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='23fe5314-795d-4052-916a-9156a3a7df06';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Appearance', (select name from clinlims.test where guid = '69b8e340-400a-45e7-bd76-d30a28dd7f4c' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='69b8e340-400a-45e7-bd76-d30a28dd7f4c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Appearance', (select reporting_description from clinlims.test where guid = '69b8e340-400a-45e7-bd76-d30a28dd7f4c' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='69b8e340-400a-45e7-bd76-d30a28dd7f4c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Occult blood', (select name from clinlims.test where guid = 'a5492ec3-39f2-40a7-9d70-e3ef842e563a' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='a5492ec3-39f2-40a7-9d70-e3ef842e563a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Occult blood', (select reporting_description from clinlims.test where guid = 'a5492ec3-39f2-40a7-9d70-e3ef842e563a' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='a5492ec3-39f2-40a7-9d70-e3ef842e563a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Methylene Stain', (select name from clinlims.test where guid = '1b55e067-84ad-4c9c-acbb-bbdb1185765c' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='1b55e067-84ad-4c9c-acbb-bbdb1185765c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Methylene Stain', (select reporting_description from clinlims.test where guid = '1b55e067-84ad-4c9c-acbb-bbdb1185765c' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='1b55e067-84ad-4c9c-acbb-bbdb1185765c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Protozoa search', (select name from clinlims.test where guid = 'e45f7c1a-6e94-4b1c-9a42-3d5b19e40ccc' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='e45f7c1a-6e94-4b1c-9a42-3d5b19e40ccc';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Protozoa search', (select reporting_description from clinlims.test where guid = 'e45f7c1a-6e94-4b1c-9a42-3d5b19e40ccc' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='e45f7c1a-6e94-4b1c-9a42-3d5b19e40ccc';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Metazoa search', (select name from clinlims.test where guid = '39d82939-0699-4004-b0f6-bd4fccdc5bdc' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='39d82939-0699-4004-b0f6-bd4fccdc5bdc';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Metazoa search', (select reporting_description from clinlims.test where guid = '39d82939-0699-4004-b0f6-bd4fccdc5bdc' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='39d82939-0699-4004-b0f6-bd4fccdc5bdc';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Cryptosporidium and Oocyst search', (select name from clinlims.test where guid = 'eeb9ade7-d7ea-4a44-84a0-3e703f0bcc1c' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='eeb9ade7-d7ea-4a44-84a0-3e703f0bcc1c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Cryptosporidium and Oocyst search', (select reporting_description from clinlims.test where guid = 'eeb9ade7-d7ea-4a44-84a0-3e703f0bcc1c' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='eeb9ade7-d7ea-4a44-84a0-3e703f0bcc1c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Filariasis search', (select name from clinlims.test where guid = 'b0867c66-d15d-46d4-8a19-4efde41b79cb' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='b0867c66-d15d-46d4-8a19-4efde41b79cb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Filariasis search', (select reporting_description from clinlims.test where guid = 'b0867c66-d15d-46d4-8a19-4efde41b79cb' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='b0867c66-d15d-46d4-8a19-4efde41b79cb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Malaria', (select name from clinlims.test where guid = '7c0bd3bf-7403-4988-a4eb-5f00126ec6ee' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='7c0bd3bf-7403-4988-a4eb-5f00126ec6ee';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Malaria', (select reporting_description from clinlims.test where guid = '7c0bd3bf-7403-4988-a4eb-5f00126ec6ee' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='7c0bd3bf-7403-4988-a4eb-5f00126ec6ee';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Malaria Rapid Test', (select name from clinlims.test where guid = '58c7e35a-11f8-4d2b-8666-e1238fc1b832' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='58c7e35a-11f8-4d2b-8666-e1238fc1b832';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Malaria Rapid Test', (select reporting_description from clinlims.test where guid = '58c7e35a-11f8-4d2b-8666-e1238fc1b832' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='58c7e35a-11f8-4d2b-8666-e1238fc1b832';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CD4 Absolute Count', (select name from clinlims.test where guid = '7f777bc8-93d6-43e2-a50b-8ef4242cbc13' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='7f777bc8-93d6-43e2-a50b-8ef4242cbc13';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CD4 Absolute Count', (select reporting_description from clinlims.test where guid = '7f777bc8-93d6-43e2-a50b-8ef4242cbc13' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='7f777bc8-93d6-43e2-a50b-8ef4242cbc13';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CD4 % Count', (select name from clinlims.test where guid = 'a9f57003-736f-4428-96fd-3a775e0630fc' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='a9f57003-736f-4428-96fd-3a775e0630fc';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CD4 % Count', (select reporting_description from clinlims.test where guid = 'a9f57003-736f-4428-96fd-3a775e0630fc' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='a9f57003-736f-4428-96fd-3a775e0630fc';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV Rapid Test', (select name from clinlims.test where guid = 'd5f842e2-be24-4c5d-bf9c-12109d8eeb87' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='d5f842e2-be24-4c5d-bf9c-12109d8eeb87';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV Rapid Test', (select reporting_description from clinlims.test where guid = 'd5f842e2-be24-4c5d-bf9c-12109d8eeb87' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='d5f842e2-be24-4c5d-bf9c-12109d8eeb87';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV Rapid Test', (select name from clinlims.test where guid = '6097a004-b327-4a21-a572-6a9f0b98ae20' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='6097a004-b327-4a21-a572-6a9f0b98ae20';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV Rapid Test', (select reporting_description from clinlims.test where guid = '6097a004-b327-4a21-a572-6a9f0b98ae20' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='6097a004-b327-4a21-a572-6a9f0b98ae20';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV Rapid Test', (select name from clinlims.test where guid = 'd99486c8-10ac-47b0-8ad9-e41f4ef832a3' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='d99486c8-10ac-47b0-8ad9-e41f4ef832a3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV Rapid Test', (select reporting_description from clinlims.test where guid = 'd99486c8-10ac-47b0-8ad9-e41f4ef832a3' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='d99486c8-10ac-47b0-8ad9-e41f4ef832a3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV Elisa', (select name from clinlims.test where guid = 'a001dfa5-f050-4387-9b70-a3f84e0309ef' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='a001dfa5-f050-4387-9b70-a3f84e0309ef';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV Elisa', (select reporting_description from clinlims.test where guid = 'a001dfa5-f050-4387-9b70-a3f84e0309ef' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='a001dfa5-f050-4387-9b70-a3f84e0309ef';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV Elisa', (select name from clinlims.test where guid = 'df06f96b-08ea-4fc9-93e1-968d2abd97e8' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='df06f96b-08ea-4fc9-93e1-968d2abd97e8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV Elisa', (select reporting_description from clinlims.test where guid = 'df06f96b-08ea-4fc9-93e1-968d2abd97e8' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='df06f96b-08ea-4fc9-93e1-968d2abd97e8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV Elisa', (select name from clinlims.test where guid = '25bdde6f-cead-498f-9410-3a211a6d78c2' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='25bdde6f-cead-498f-9410-3a211a6d78c2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV Elisa', (select reporting_description from clinlims.test where guid = '25bdde6f-cead-498f-9410-3a211a6d78c2' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='25bdde6f-cead-498f-9410-3a211a6d78c2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hépatite B Ag', (select name from clinlims.test where guid = '12deccc1-379e-4173-a9c4-5bca4afd8a94' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='12deccc1-379e-4173-a9c4-5bca4afd8a94';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hépatite B Ag', (select reporting_description from clinlims.test where guid = '12deccc1-379e-4173-a9c4-5bca4afd8a94' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='12deccc1-379e-4173-a9c4-5bca4afd8a94';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hépatite B Ag', (select name from clinlims.test where guid = 'f1b8e81e-0806-4774-9577-34e160757d10' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='f1b8e81e-0806-4774-9577-34e160757d10';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hépatite B Ag', (select reporting_description from clinlims.test where guid = 'f1b8e81e-0806-4774-9577-34e160757d10' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='f1b8e81e-0806-4774-9577-34e160757d10';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hépatite B Ag', (select name from clinlims.test where guid = 'ebf3504d-f044-49f5-8496-b31fe33e6181' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='ebf3504d-f044-49f5-8496-b31fe33e6181';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hépatite B Ag', (select reporting_description from clinlims.test where guid = 'ebf3504d-f044-49f5-8496-b31fe33e6181' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='ebf3504d-f044-49f5-8496-b31fe33e6181';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HCV - Hépatite C', (select name from clinlims.test where guid = '8b359ef7-989f-428f-b802-e36e103097f8' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='8b359ef7-989f-428f-b802-e36e103097f8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HCV - Hépatite C', (select reporting_description from clinlims.test where guid = '8b359ef7-989f-428f-b802-e36e103097f8' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='8b359ef7-989f-428f-b802-e36e103097f8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HCV - Hépatite C', (select name from clinlims.test where guid = '7acb26b2-ac50-460c-8067-10b8114364de' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='7acb26b2-ac50-460c-8067-10b8114364de';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HCV - Hépatite C', (select reporting_description from clinlims.test where guid = '7acb26b2-ac50-460c-8067-10b8114364de' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='7acb26b2-ac50-460c-8067-10b8114364de';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HCV - Hépatite C', (select name from clinlims.test where guid = 'db83de30-f545-4771-815c-01bc4c082c6f' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='db83de30-f545-4771-815c-01bc4c082c6f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HCV - Hépatite C', (select reporting_description from clinlims.test where guid = 'db83de30-f545-4771-815c-01bc4c082c6f' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='db83de30-f545-4771-815c-01bc4c082c6f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Dengue NS1 Ag', (select name from clinlims.test where guid = 'f3655877-c5ef-4e28-94ae-fee7e21e7553' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='f3655877-c5ef-4e28-94ae-fee7e21e7553';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Dengue NS1 Ag', (select reporting_description from clinlims.test where guid = 'f3655877-c5ef-4e28-94ae-fee7e21e7553' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='f3655877-c5ef-4e28-94ae-fee7e21e7553';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Dengue NS1 Ag', (select name from clinlims.test where guid = '3e90b328-f821-4de6-865a-a55c8b0fc2df' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='3e90b328-f821-4de6-865a-a55c8b0fc2df';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Dengue NS1 Ag', (select reporting_description from clinlims.test where guid = '3e90b328-f821-4de6-865a-a55c8b0fc2df' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='3e90b328-f821-4de6-865a-a55c8b0fc2df';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Dengue NS1 Ag', (select name from clinlims.test where guid = 'b1052d85-ab24-472d-bf20-99efd72e8271' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='b1052d85-ab24-472d-bf20-99efd72e8271';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Dengue NS1 Ag', (select reporting_description from clinlims.test where guid = 'b1052d85-ab24-472d-bf20-99efd72e8271' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='b1052d85-ab24-472d-bf20-99efd72e8271';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'AFB by Ziehl Neelsen', (select name from clinlims.test where guid = '6040645c-29e6-41f9-9737-37c175934dc2' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='6040645c-29e6-41f9-9737-37c175934dc2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'AFB by Ziehl Neelsen', (select reporting_description from clinlims.test where guid = '6040645c-29e6-41f9-9737-37c175934dc2' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='6040645c-29e6-41f9-9737-37c175934dc2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'AFB by Ziehl Neelsen', (select name from clinlims.test where guid = '76cfced0-1361-497e-9ed8-e27115eeab4f' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='76cfced0-1361-497e-9ed8-e27115eeab4f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'AFB by Ziehl Neelsen', (select reporting_description from clinlims.test where guid = '76cfced0-1361-497e-9ed8-e27115eeab4f' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='76cfced0-1361-497e-9ed8-e27115eeab4f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'AFB by Ziehl Neelsen', (select name from clinlims.test where guid = '27622acf-903f-48ad-8ada-48ddea32b460' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='27622acf-903f-48ad-8ada-48ddea32b460';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'AFB by Ziehl Neelsen', (select reporting_description from clinlims.test where guid = '27622acf-903f-48ad-8ada-48ddea32b460' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='27622acf-903f-48ad-8ada-48ddea32b460';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'AFB by Fluorochrom', (select name from clinlims.test where guid = 'fd4bfae3-c869-4e00-a012-4b145f016777' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='fd4bfae3-c869-4e00-a012-4b145f016777';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'AFB by Fluorochrom', (select reporting_description from clinlims.test where guid = 'fd4bfae3-c869-4e00-a012-4b145f016777' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='fd4bfae3-c869-4e00-a012-4b145f016777';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'AFB by Fluorochrom', (select name from clinlims.test where guid = '94b78aea-bdfa-4404-bc94-bb7cac68933c' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='94b78aea-bdfa-4404-bc94-bb7cac68933c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'AFB by Fluorochrom', (select reporting_description from clinlims.test where guid = '94b78aea-bdfa-4404-bc94-bb7cac68933c' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='94b78aea-bdfa-4404-bc94-bb7cac68933c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'AFB by Fluorochrom', (select name from clinlims.test where guid = 'f09cd0a8-f795-4092-a5e9-5c4385700a61' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='f09cd0a8-f795-4092-a5e9-5c4385700a61';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'AFB by Fluorochrom', (select reporting_description from clinlims.test where guid = 'f09cd0a8-f795-4092-a5e9-5c4385700a61' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='f09cd0a8-f795-4092-a5e9-5c4385700a61';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'AFB by Ziehl Neelsen', (select name from clinlims.test where guid = 'bb4ef86b-646c-43ae-8a3f-73a4c503539d' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='bb4ef86b-646c-43ae-8a3f-73a4c503539d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'AFB by Ziehl Neelsen', (select reporting_description from clinlims.test where guid = 'bb4ef86b-646c-43ae-8a3f-73a4c503539d' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='bb4ef86b-646c-43ae-8a3f-73a4c503539d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'AFB by Ziehl Neelsen', (select name from clinlims.test where guid = 'da92a2f9-c9e9-42a1-89c3-78f7bed85401' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='da92a2f9-c9e9-42a1-89c3-78f7bed85401';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'AFB by Ziehl Neelsen', (select reporting_description from clinlims.test where guid = 'da92a2f9-c9e9-42a1-89c3-78f7bed85401' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='da92a2f9-c9e9-42a1-89c3-78f7bed85401';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'AFB by Ziehl Neelsen', (select name from clinlims.test where guid = '9b02f7d7-37f5-4690-aa98-2c1af4d3f8a3' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='9b02f7d7-37f5-4690-aa98-2c1af4d3f8a3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'AFB by Ziehl Neelsen', (select reporting_description from clinlims.test where guid = '9b02f7d7-37f5-4690-aa98-2c1af4d3f8a3' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='9b02f7d7-37f5-4690-aa98-2c1af4d3f8a3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'BK Auramine Stain', (select name from clinlims.test where guid = 'fa49cf4b-dafc-48c9-98fe-58e1a426cc04' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='fa49cf4b-dafc-48c9-98fe-58e1a426cc04';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'BK Auramine Stain', (select reporting_description from clinlims.test where guid = 'fa49cf4b-dafc-48c9-98fe-58e1a426cc04' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='fa49cf4b-dafc-48c9-98fe-58e1a426cc04';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'BK Auramine Stain', (select name from clinlims.test where guid = 'df3c5a4b-2694-4379-8721-351d5d15fb20' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='df3c5a4b-2694-4379-8721-351d5d15fb20';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'BK Auramine Stain', (select reporting_description from clinlims.test where guid = 'df3c5a4b-2694-4379-8721-351d5d15fb20' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='df3c5a4b-2694-4379-8721-351d5d15fb20';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'BK Auramine Stain', (select name from clinlims.test where guid = '04c3e30e-3ae8-4356-a5e6-22a31f619385' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='04c3e30e-3ae8-4356-a5e6-22a31f619385';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'BK Auramine Stain', (select reporting_description from clinlims.test where guid = '04c3e30e-3ae8-4356-a5e6-22a31f619385' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='04c3e30e-3ae8-4356-a5e6-22a31f619385';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'M. Tuberculosis Culture', (select name from clinlims.test where guid = 'abbd0f77-88cc-4d2e-9dad-5e804f210660' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='abbd0f77-88cc-4d2e-9dad-5e804f210660';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'M. Tuberculosis Culture', (select reporting_description from clinlims.test where guid = 'abbd0f77-88cc-4d2e-9dad-5e804f210660' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='abbd0f77-88cc-4d2e-9dad-5e804f210660';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'PPD Qualitative', (select name from clinlims.test where guid = '57417e1c-7485-456e-8e85-2542f12dbc9e' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='57417e1c-7485-456e-8e85-2542f12dbc9e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'PPD Qualitative', (select reporting_description from clinlims.test where guid = '57417e1c-7485-456e-8e85-2542f12dbc9e' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='57417e1c-7485-456e-8e85-2542f12dbc9e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'PPD Quantitative', (select name from clinlims.test where guid = '6fd2a551-b838-4717-b25f-43120d664f45' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='6fd2a551-b838-4717-b25f-43120d664f45';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'PPD Quantitative', (select reporting_description from clinlims.test where guid = '6fd2a551-b838-4717-b25f-43120d664f45' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='6fd2a551-b838-4717-b25f-43120d664f45';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Prolactine', (select name from clinlims.test where guid = '34be214b-4cf0-4dde-bbca-b567656cf97e' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='34be214b-4cf0-4dde-bbca-b567656cf97e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Prolactine', (select reporting_description from clinlims.test where guid = '34be214b-4cf0-4dde-bbca-b567656cf97e' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='34be214b-4cf0-4dde-bbca-b567656cf97e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'FSH', (select name from clinlims.test where guid = '250b7b43-ee6e-4285-8532-ebcb088d22d2' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='250b7b43-ee6e-4285-8532-ebcb088d22d2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'FSH', (select reporting_description from clinlims.test where guid = '250b7b43-ee6e-4285-8532-ebcb088d22d2' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='250b7b43-ee6e-4285-8532-ebcb088d22d2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'FSH', (select name from clinlims.test where guid = 'ae6ceb4d-2f4e-40a8-bc51-2d7dc4ff0121' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='ae6ceb4d-2f4e-40a8-bc51-2d7dc4ff0121';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'FSH', (select reporting_description from clinlims.test where guid = 'ae6ceb4d-2f4e-40a8-bc51-2d7dc4ff0121' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='ae6ceb4d-2f4e-40a8-bc51-2d7dc4ff0121';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'LH', (select name from clinlims.test where guid = '3b455516-b53d-414b-868e-f1039c617834' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='3b455516-b53d-414b-868e-f1039c617834';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'LH', (select reporting_description from clinlims.test where guid = '3b455516-b53d-414b-868e-f1039c617834' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='3b455516-b53d-414b-868e-f1039c617834';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'LH', (select name from clinlims.test where guid = '695cb9fd-9c8f-4e02-b08d-2851fc91eafe' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='695cb9fd-9c8f-4e02-b08d-2851fc91eafe';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'LH', (select reporting_description from clinlims.test where guid = '695cb9fd-9c8f-4e02-b08d-2851fc91eafe' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='695cb9fd-9c8f-4e02-b08d-2851fc91eafe';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Oestrogene', (select name from clinlims.test where guid = '2713531e-3470-4e69-af45-58a74280f38e' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='2713531e-3470-4e69-af45-58a74280f38e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Oestrogene', (select reporting_description from clinlims.test where guid = '2713531e-3470-4e69-af45-58a74280f38e' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='2713531e-3470-4e69-af45-58a74280f38e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Progesterone', (select name from clinlims.test where guid = '7d5e5b5f-b2ff-4f61-943b-665b4796924a' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='7d5e5b5f-b2ff-4f61-943b-665b4796924a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Progesterone', (select reporting_description from clinlims.test where guid = '7d5e5b5f-b2ff-4f61-943b-665b4796924a' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='7d5e5b5f-b2ff-4f61-943b-665b4796924a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'T3', (select name from clinlims.test where guid = '03f80347-9e87-40d8-898f-b4e1a15b7424' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='03f80347-9e87-40d8-898f-b4e1a15b7424';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'T3', (select reporting_description from clinlims.test where guid = '03f80347-9e87-40d8-898f-b4e1a15b7424' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='03f80347-9e87-40d8-898f-b4e1a15b7424';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'B-HCG', (select name from clinlims.test where guid = 'b9e0f1e1-b908-42ae-ae2c-edff62386a70' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='b9e0f1e1-b908-42ae-ae2c-edff62386a70';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'B-HCG', (select reporting_description from clinlims.test where guid = 'b9e0f1e1-b908-42ae-ae2c-edff62386a70' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='b9e0f1e1-b908-42ae-ae2c-edff62386a70';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'B-HCG', (select name from clinlims.test where guid = '4eca03e8-9ff8-4451-9b61-6157450aae0a' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='4eca03e8-9ff8-4451-9b61-6157450aae0a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'B-HCG', (select reporting_description from clinlims.test where guid = '4eca03e8-9ff8-4451-9b61-6157450aae0a' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='4eca03e8-9ff8-4451-9b61-6157450aae0a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Pregnancy test', (select name from clinlims.test where guid = '38264b6a-148d-4a7d-ad7d-036130bd5ada' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='38264b6a-148d-4a7d-ad7d-036130bd5ada';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Pregnancy test', (select reporting_description from clinlims.test where guid = '38264b6a-148d-4a7d-ad7d-036130bd5ada' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='38264b6a-148d-4a7d-ad7d-036130bd5ada';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'T4', (select name from clinlims.test where guid = 'ffcb1f56-97f1-4ea2-9d0a-38e8e2a205ea' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='ffcb1f56-97f1-4ea2-9d0a-38e8e2a205ea';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'T4', (select reporting_description from clinlims.test where guid = 'ffcb1f56-97f1-4ea2-9d0a-38e8e2a205ea' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='ffcb1f56-97f1-4ea2-9d0a-38e8e2a205ea';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'TSH', (select name from clinlims.test where guid = '2394e56e-b20f-4169-82d2-53f0a4690c4f' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='2394e56e-b20f-4169-82d2-53f0a4690c4f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'TSH', (select reporting_description from clinlims.test where guid = '2394e56e-b20f-4169-82d2-53f0a4690c4f' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='2394e56e-b20f-4169-82d2-53f0a4690c4f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CSF / GRAM', (select name from clinlims.test where guid = '2d140a88-0340-42e1-bf7b-1277d62590aa' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='2d140a88-0340-42e1-bf7b-1277d62590aa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CSF / GRAM', (select reporting_description from clinlims.test where guid = '2d140a88-0340-42e1-bf7b-1277d62590aa' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='2d140a88-0340-42e1-bf7b-1277d62590aa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CSF / AFB', (select name from clinlims.test where guid = '3d6d1760-1532-4bc5-bf24-605d13f71330' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='3d6d1760-1532-4bc5-bf24-605d13f71330';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CSF / AFB', (select reporting_description from clinlims.test where guid = '3d6d1760-1532-4bc5-bf24-605d13f71330' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='3d6d1760-1532-4bc5-bf24-605d13f71330';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'TOXOPLASMA GONDII IgG Ab', (select name from clinlims.test where guid = '46d415f4-e454-4aeb-81ec-c030a580de63' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='46d415f4-e454-4aeb-81ec-c030a580de63';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'TOXOPLASMA GONDII IgG Ab', (select reporting_description from clinlims.test where guid = '46d415f4-e454-4aeb-81ec-c030a580de63' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='46d415f4-e454-4aeb-81ec-c030a580de63';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'TOXOPLASMA GONDII Ig M Ab', (select name from clinlims.test where guid = '21413513-c33f-442b-b2ee-8f6596e31e25' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='21413513-c33f-442b-b2ee-8f6596e31e25';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'TOXOPLASMA GONDII Ig M Ab', (select reporting_description from clinlims.test where guid = '21413513-c33f-442b-b2ee-8f6596e31e25' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='21413513-c33f-442b-b2ee-8f6596e31e25';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Malaria Rapid Test', (select name from clinlims.test where guid = '0c7688a5-a365-4d17-a34a-f9475c6389fc' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='0c7688a5-a365-4d17-a34a-f9475c6389fc';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Malaria Rapid Test', (select reporting_description from clinlims.test where guid = '0c7688a5-a365-4d17-a34a-f9475c6389fc' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='0c7688a5-a365-4d17-a34a-f9475c6389fc';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Syphilis Test Rapide', (select name from clinlims.test where guid = '54dc59ce-0073-4d45-b48e-63b3c2d0c9b7' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='54dc59ce-0073-4d45-b48e-63b3c2d0c9b7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Syphilis Test Rapide', (select reporting_description from clinlims.test where guid = '54dc59ce-0073-4d45-b48e-63b3c2d0c9b7' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='54dc59ce-0073-4d45-b48e-63b3c2d0c9b7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Syphilis Test Rapide', (select name from clinlims.test where guid = 'fd2b6bb2-086c-44a8-9a20-09422677c3df' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='fd2b6bb2-086c-44a8-9a20-09422677c3df';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Syphilis Test Rapide', (select reporting_description from clinlims.test where guid = 'fd2b6bb2-086c-44a8-9a20-09422677c3df' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='fd2b6bb2-086c-44a8-9a20-09422677c3df';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Malaria Rapid Test', (select name from clinlims.test where guid = 'd04a32fa-db05-4f62-b09e-3b724d7d64b7' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='d04a32fa-db05-4f62-b09e-3b724d7d64b7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Malaria Rapid Test', (select reporting_description from clinlims.test where guid = 'd04a32fa-db05-4f62-b09e-3b724d7d64b7' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='d04a32fa-db05-4f62-b09e-3b724d7d64b7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Syphilis Test Rapide', (select name from clinlims.test where guid = 'f1a077ec-2942-4c00-9449-32b68aa2390e' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='f1a077ec-2942-4c00-9449-32b68aa2390e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Syphilis Test Rapide', (select reporting_description from clinlims.test where guid = 'f1a077ec-2942-4c00-9449-32b68aa2390e' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='f1a077ec-2942-4c00-9449-32b68aa2390e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HTLV I et II', (select name from clinlims.test where guid = 'ba2c5e6e-b9ce-44eb-956e-d31d5fd324f3' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='ba2c5e6e-b9ce-44eb-956e-d31d5fd324f3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HTLV I et II', (select reporting_description from clinlims.test where guid = 'ba2c5e6e-b9ce-44eb-956e-d31d5fd324f3' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='ba2c5e6e-b9ce-44eb-956e-d31d5fd324f3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HTLV I et II', (select name from clinlims.test where guid = 'd3e72e22-36b9-4f74-a0bf-9fdeaf90c2ee' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='d3e72e22-36b9-4f74-a0bf-9fdeaf90c2ee';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HTLV I et II', (select reporting_description from clinlims.test where guid = 'd3e72e22-36b9-4f74-a0bf-9fdeaf90c2ee' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='d3e72e22-36b9-4f74-a0bf-9fdeaf90c2ee';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HTLV I et II', (select name from clinlims.test where guid = '1cb71dbf-6678-4ae1-b335-022812b256d3' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='1cb71dbf-6678-4ae1-b335-022812b256d3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HTLV I et II', (select reporting_description from clinlims.test where guid = '1cb71dbf-6678-4ae1-b335-022812b256d3' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='1cb71dbf-6678-4ae1-b335-022812b256d3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Syphilis RPR', (select name from clinlims.test where guid = 'c442ce15-5ddf-4504-b632-c3d3ec93a96c' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='c442ce15-5ddf-4504-b632-c3d3ec93a96c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Syphilis RPR', (select reporting_description from clinlims.test where guid = 'c442ce15-5ddf-4504-b632-c3d3ec93a96c' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='c442ce15-5ddf-4504-b632-c3d3ec93a96c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Syphilis RPR', (select name from clinlims.test where guid = '445d81a1-3fcd-48aa-925d-86c2273540f9' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='445d81a1-3fcd-48aa-925d-86c2273540f9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Syphilis RPR', (select reporting_description from clinlims.test where guid = '445d81a1-3fcd-48aa-925d-86c2273540f9' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='445d81a1-3fcd-48aa-925d-86c2273540f9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Syphilis TPHA', (select name from clinlims.test where guid = '2b9d6033-9029-400f-a5ea-e3c4f4df4954' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='2b9d6033-9029-400f-a5ea-e3c4f4df4954';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Syphilis TPHA', (select reporting_description from clinlims.test where guid = '2b9d6033-9029-400f-a5ea-e3c4f4df4954' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='2b9d6033-9029-400f-a5ea-e3c4f4df4954';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Syphilis RPR', (select name from clinlims.test where guid = '738f7936-f2bb-4f9d-ab8b-90e78bd03630' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='738f7936-f2bb-4f9d-ab8b-90e78bd03630';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Syphilis RPR', (select reporting_description from clinlims.test where guid = '738f7936-f2bb-4f9d-ab8b-90e78bd03630' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='738f7936-f2bb-4f9d-ab8b-90e78bd03630';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Syphilis TPHA', (select name from clinlims.test where guid = '78e33aad-c834-4020-86ca-696ef608af36' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='78e33aad-c834-4020-86ca-696ef608af36';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Syphilis TPHA', (select reporting_description from clinlims.test where guid = '78e33aad-c834-4020-86ca-696ef608af36' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='78e33aad-c834-4020-86ca-696ef608af36';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Syphilis TPHA', (select name from clinlims.test where guid = '07bad6aa-a777-4960-9032-561691f03355' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='07bad6aa-a777-4960-9032-561691f03355';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Syphilis TPHA', (select reporting_description from clinlims.test where guid = '07bad6aa-a777-4960-9032-561691f03355' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='07bad6aa-a777-4960-9032-561691f03355';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Helicobacter Pilori', (select name from clinlims.test where guid = '60ff7e39-60b5-4fa1-9075-459e39087169' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='60ff7e39-60b5-4fa1-9075-459e39087169';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Helicobacter Pilori', (select reporting_description from clinlims.test where guid = '60ff7e39-60b5-4fa1-9075-459e39087169' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='60ff7e39-60b5-4fa1-9075-459e39087169';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Helicobacter Pilori', (select name from clinlims.test where guid = 'c188f7d9-318d-470a-a90f-ec1a2eb4318b' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='c188f7d9-318d-470a-a90f-ec1a2eb4318b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Helicobacter Pilori', (select reporting_description from clinlims.test where guid = 'c188f7d9-318d-470a-a90f-ec1a2eb4318b' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='c188f7d9-318d-470a-a90f-ec1a2eb4318b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Helicobacter Pilori', (select name from clinlims.test where guid = '980f8696-9404-4dae-a091-c8cb89563aae' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='980f8696-9404-4dae-a091-c8cb89563aae';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Helicobacter Pilori', (select reporting_description from clinlims.test where guid = '980f8696-9404-4dae-a091-c8cb89563aae' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='980f8696-9404-4dae-a091-c8cb89563aae';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Herpes Simplex', (select name from clinlims.test where guid = '129bfcf0-c7de-44c4-a6b3-082965e2aa5d' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='129bfcf0-c7de-44c4-a6b3-082965e2aa5d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Herpes Simplex', (select reporting_description from clinlims.test where guid = '129bfcf0-c7de-44c4-a6b3-082965e2aa5d' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='129bfcf0-c7de-44c4-a6b3-082965e2aa5d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Herpes Simplex', (select name from clinlims.test where guid = '43333a46-caf0-46da-869d-2c2db11074a0' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='43333a46-caf0-46da-869d-2c2db11074a0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Herpes Simplex', (select reporting_description from clinlims.test where guid = '43333a46-caf0-46da-869d-2c2db11074a0' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='43333a46-caf0-46da-869d-2c2db11074a0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Chlamydia Ab', (select name from clinlims.test where guid = '96b996f5-55cc-40d9-84ce-d0ede1a2de4f' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='96b996f5-55cc-40d9-84ce-d0ede1a2de4f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Chlamydia Ab', (select reporting_description from clinlims.test where guid = '96b996f5-55cc-40d9-84ce-d0ede1a2de4f' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='96b996f5-55cc-40d9-84ce-d0ede1a2de4f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Chlamydia Ag', (select name from clinlims.test where guid = '3a887085-6775-4476-8326-c6f91b29ad71' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='3a887085-6775-4476-8326-c6f91b29ad71';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Chlamydia Ag', (select reporting_description from clinlims.test where guid = '3a887085-6775-4476-8326-c6f91b29ad71' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='3a887085-6775-4476-8326-c6f91b29ad71';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CRP', (select name from clinlims.test where guid = '8a5e2b94-1872-49b6-89f6-bec0345f1fa3' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='8a5e2b94-1872-49b6-89f6-bec0345f1fa3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CRP', (select reporting_description from clinlims.test where guid = '8a5e2b94-1872-49b6-89f6-bec0345f1fa3' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='8a5e2b94-1872-49b6-89f6-bec0345f1fa3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CRP', (select name from clinlims.test where guid = 'e9aa3ac3-8817-407b-b170-5f15dac0a938' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='e9aa3ac3-8817-407b-b170-5f15dac0a938';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CRP', (select reporting_description from clinlims.test where guid = 'e9aa3ac3-8817-407b-b170-5f15dac0a938' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='e9aa3ac3-8817-407b-b170-5f15dac0a938';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CRP', (select name from clinlims.test where guid = '9d475682-3445-4abe-88ef-da2f99d4eb5e' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='9d475682-3445-4abe-88ef-da2f99d4eb5e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CRP', (select reporting_description from clinlims.test where guid = '9d475682-3445-4abe-88ef-da2f99d4eb5e' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='9d475682-3445-4abe-88ef-da2f99d4eb5e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'ASO', (select name from clinlims.test where guid = '0b6dbffc-3f41-4521-83d9-44afa95114cb' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='0b6dbffc-3f41-4521-83d9-44afa95114cb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'ASO', (select reporting_description from clinlims.test where guid = '0b6dbffc-3f41-4521-83d9-44afa95114cb' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='0b6dbffc-3f41-4521-83d9-44afa95114cb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'ASO', (select name from clinlims.test where guid = '6355ba87-ccab-4bd4-94fa-c03e7fffa056' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='6355ba87-ccab-4bd4-94fa-c03e7fffa056';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'ASO', (select reporting_description from clinlims.test where guid = '6355ba87-ccab-4bd4-94fa-c03e7fffa056' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='6355ba87-ccab-4bd4-94fa-c03e7fffa056';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'ASO', (select name from clinlims.test where guid = '4b59b743-c5f0-4e11-a7df-37dc8fea7c50' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='4b59b743-c5f0-4e11-a7df-37dc8fea7c50';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'ASO', (select reporting_description from clinlims.test where guid = '4b59b743-c5f0-4e11-a7df-37dc8fea7c50' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='4b59b743-c5f0-4e11-a7df-37dc8fea7c50';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Widal Test Ag O', (select name from clinlims.test where guid = 'df57caf4-8e46-4ff8-a328-2e8b4df2e7d8' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='df57caf4-8e46-4ff8-a328-2e8b4df2e7d8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Widal Test Ag O', (select reporting_description from clinlims.test where guid = 'df57caf4-8e46-4ff8-a328-2e8b4df2e7d8' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='df57caf4-8e46-4ff8-a328-2e8b4df2e7d8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Widal Test Ag H', (select name from clinlims.test where guid = '1a95d981-cc46-4a52-a350-59a3de1eed61' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='1a95d981-cc46-4a52-a350-59a3de1eed61';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Widal Test Ag H', (select reporting_description from clinlims.test where guid = '1a95d981-cc46-4a52-a350-59a3de1eed61' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='1a95d981-cc46-4a52-a350-59a3de1eed61';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Typhoide Test Rapide', (select name from clinlims.test where guid = '2b6bc211-bc13-45e3-9c96-0e209cb5a79f' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='2b6bc211-bc13-45e3-9c96-0e209cb5a79f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Typhoide Test Rapide', (select reporting_description from clinlims.test where guid = '2b6bc211-bc13-45e3-9c96-0e209cb5a79f' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='2b6bc211-bc13-45e3-9c96-0e209cb5a79f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Typhoide Test Rapide', (select name from clinlims.test where guid = '8a4970c4-64a4-4f79-a735-2a1037017b35' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='8a4970c4-64a4-4f79-a735-2a1037017b35';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Typhoide Test Rapide', (select reporting_description from clinlims.test where guid = '8a4970c4-64a4-4f79-a735-2a1037017b35' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='8a4970c4-64a4-4f79-a735-2a1037017b35';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Mono Test', (select name from clinlims.test where guid = '996308d3-983e-404a-a004-82bca6df21a7' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='996308d3-983e-404a-a004-82bca6df21a7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Mono Test', (select reporting_description from clinlims.test where guid = '996308d3-983e-404a-a004-82bca6df21a7' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='996308d3-983e-404a-a004-82bca6df21a7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'LE Cell', (select name from clinlims.test where guid = '0a7d7e31-2e5b-45f5-b8aa-14db5228bbd0' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='0a7d7e31-2e5b-45f5-b8aa-14db5228bbd0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'LE Cell', (select reporting_description from clinlims.test where guid = '0a7d7e31-2e5b-45f5-b8aa-14db5228bbd0' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='0a7d7e31-2e5b-45f5-b8aa-14db5228bbd0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Dengue Ig G', (select name from clinlims.test where guid = 'ddd6f878-df03-4512-a3e5-a5d856e55a17' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='ddd6f878-df03-4512-a3e5-a5d856e55a17';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Dengue Ig G', (select reporting_description from clinlims.test where guid = 'ddd6f878-df03-4512-a3e5-a5d856e55a17' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='ddd6f878-df03-4512-a3e5-a5d856e55a17';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Dengue Ig A', (select name from clinlims.test where guid = 'f348d407-f3d5-4fb7-bf20-95ab3679c197' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='f348d407-f3d5-4fb7-bf20-95ab3679c197';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Dengue Ig A', (select reporting_description from clinlims.test where guid = 'f348d407-f3d5-4fb7-bf20-95ab3679c197' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='f348d407-f3d5-4fb7-bf20-95ab3679c197';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CMV Ig G', (select name from clinlims.test where guid = '8ab8592c-dffb-401d-b6a7-483399c282bb' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='8ab8592c-dffb-401d-b6a7-483399c282bb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CMV Ig G', (select reporting_description from clinlims.test where guid = '8ab8592c-dffb-401d-b6a7-483399c282bb' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='8ab8592c-dffb-401d-b6a7-483399c282bb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CMV Ig A', (select name from clinlims.test where guid = 'd2ee50db-530f-4864-ac0b-9aa7bd43d51f' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='d2ee50db-530f-4864-ac0b-9aa7bd43d51f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CMV Ig A', (select reporting_description from clinlims.test where guid = 'd2ee50db-530f-4864-ac0b-9aa7bd43d51f' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='d2ee50db-530f-4864-ac0b-9aa7bd43d51f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Cryptococcus Antigen dipstick', (select name from clinlims.test where guid = '779b8dc3-bf04-4f85-91b2-3b9613a4ae5e' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='779b8dc3-bf04-4f85-91b2-3b9613a4ae5e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Cryptococcus Antigen dipstick', (select reporting_description from clinlims.test where guid = '779b8dc3-bf04-4f85-91b2-3b9613a4ae5e' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='779b8dc3-bf04-4f85-91b2-3b9613a4ae5e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Clostridium Difficile Toxin A & B', (select name from clinlims.test where guid = 'bd6f0ae5-6575-47f3-9715-82dc8f276468' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='bd6f0ae5-6575-47f3-9715-82dc8f276468';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Clostridium Difficile Toxin A & B', (select reporting_description from clinlims.test where guid = 'bd6f0ae5-6575-47f3-9715-82dc8f276468' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='bd6f0ae5-6575-47f3-9715-82dc8f276468';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Determine HIV', (select name from clinlims.test where guid = 'fd9a6dc5-8d3a-40d7-b1a1-866983ffd56c' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='fd9a6dc5-8d3a-40d7-b1a1-866983ffd56c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Determine HIV', (select reporting_description from clinlims.test where guid = 'fd9a6dc5-8d3a-40d7-b1a1-866983ffd56c' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='fd9a6dc5-8d3a-40d7-b1a1-866983ffd56c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Determine HIV', (select name from clinlims.test where guid = '6b7c8893-fb6f-4430-85ed-a240186e56d6' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='6b7c8893-fb6f-4430-85ed-a240186e56d6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Determine HIV', (select reporting_description from clinlims.test where guid = '6b7c8893-fb6f-4430-85ed-a240186e56d6' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='6b7c8893-fb6f-4430-85ed-a240186e56d6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Determine HIV', (select name from clinlims.test where guid = '9e4b17f6-6654-4697-ba37-aba2d0f6a9ff' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='9e4b17f6-6654-4697-ba37-aba2d0f6a9ff';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Determine HIV', (select reporting_description from clinlims.test where guid = '9e4b17f6-6654-4697-ba37-aba2d0f6a9ff' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='9e4b17f6-6654-4697-ba37-aba2d0f6a9ff';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Colloidal Gold / Shangai Kehua HIV', (select name from clinlims.test where guid = '97a1d6ca-e035-46be-986b-0c43e7c836a1' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='97a1d6ca-e035-46be-986b-0c43e7c836a1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Colloidal Gold / Shangai Kehua HIV', (select reporting_description from clinlims.test where guid = '97a1d6ca-e035-46be-986b-0c43e7c836a1' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='97a1d6ca-e035-46be-986b-0c43e7c836a1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Colloidal Gold / Shangai Kehua HIV', (select name from clinlims.test where guid = '9c42a539-3e81-4713-938e-abfe1dd4b0d6' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='9c42a539-3e81-4713-938e-abfe1dd4b0d6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Colloidal Gold / Shangai Kehua HIV', (select reporting_description from clinlims.test where guid = '9c42a539-3e81-4713-938e-abfe1dd4b0d6' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='9c42a539-3e81-4713-938e-abfe1dd4b0d6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Colloidal Gold / Shangai Kehua HIV', (select name from clinlims.test where guid = 'd24255e6-becb-4b04-8da0-5a1c45673f0e' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='d24255e6-becb-4b04-8da0-5a1c45673f0e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Colloidal Gold / Shangai Kehua HIV', (select reporting_description from clinlims.test where guid = 'd24255e6-becb-4b04-8da0-5a1c45673f0e' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='d24255e6-becb-4b04-8da0-5a1c45673f0e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Syphilis Bioline', (select name from clinlims.test where guid = '810374ae-e050-43c2-9c39-ded5440a7f3b' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='810374ae-e050-43c2-9c39-ded5440a7f3b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Syphilis Bioline', (select reporting_description from clinlims.test where guid = '810374ae-e050-43c2-9c39-ded5440a7f3b' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='810374ae-e050-43c2-9c39-ded5440a7f3b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Syphilis Bioline', (select name from clinlims.test where guid = '0d959e13-9cda-4cb8-b40a-7599bd371eb4' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='0d959e13-9cda-4cb8-b40a-7599bd371eb4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Syphilis Bioline', (select reporting_description from clinlims.test where guid = '0d959e13-9cda-4cb8-b40a-7599bd371eb4' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='0d959e13-9cda-4cb8-b40a-7599bd371eb4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Syphilis Bioline', (select name from clinlims.test where guid = 'c08b09ed-e1a4-441f-9b54-2022c58f5fb6' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='c08b09ed-e1a4-441f-9b54-2022c58f5fb6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Syphilis Bioline', (select reporting_description from clinlims.test where guid = 'c08b09ed-e1a4-441f-9b54-2022c58f5fb6' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='c08b09ed-e1a4-441f-9b54-2022c58f5fb6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'PSA', (select name from clinlims.test where guid = '2dd48204-f928-4260-a04d-1ec777b77137' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='2dd48204-f928-4260-a04d-1ec777b77137';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'PSA', (select reporting_description from clinlims.test where guid = '2dd48204-f928-4260-a04d-1ec777b77137' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='2dd48204-f928-4260-a04d-1ec777b77137';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'PSA', (select name from clinlims.test where guid = '7a308acc-b443-4377-81d3-ff7626113c5d' ), now());
update clinlims.test set name_localization_id = currval('localization_seq') where guid ='7a308acc-b443-4377-81d3-ff7626113c5d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'PSA', (select reporting_description from clinlims.test where guid = '7a308acc-b443-4377-81d3-ff7626113c5d' ), now());
update clinlims.test set reporting_name_localization_id = currval('localization_seq') where guid ='7a308acc-b443-4377-81d3-ff7626113c5d';
