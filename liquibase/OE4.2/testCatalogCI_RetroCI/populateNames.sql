INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Glycémie', (select name from clinlims.test where guid = '3cf20999-c453-4246-b5d4-a39060101b79' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3cf20999-c453-4246-b5d4-a39060101b79';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Glycémie', (select reporting_description from clinlims.test where guid = '3cf20999-c453-4246-b5d4-a39060101b79' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3cf20999-c453-4246-b5d4-a39060101b79';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Créatininémie', (select name from clinlims.test where guid = 'db25d5fe-4a7a-4e94-8dec-d57b8c13108a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='db25d5fe-4a7a-4e94-8dec-d57b8c13108a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Créatininémie', (select reporting_description from clinlims.test where guid = 'db25d5fe-4a7a-4e94-8dec-d57b8c13108a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='db25d5fe-4a7a-4e94-8dec-d57b8c13108a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Transaminases ALTL', (select name from clinlims.test where guid = '5f1c1a36-5147-45a8-8c7b-162ff43e5145' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='5f1c1a36-5147-45a8-8c7b-162ff43e5145';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Transaminases ALTL', (select reporting_description from clinlims.test where guid = '5f1c1a36-5147-45a8-8c7b-162ff43e5145' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='5f1c1a36-5147-45a8-8c7b-162ff43e5145';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'p24 Ag', (select name from clinlims.test where guid = 'd215dd9d-16aa-4c1f-8289-94c4c7ed040b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d215dd9d-16aa-4c1f-8289-94c4c7ed040b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'p24 Ag', (select reporting_description from clinlims.test where guid = 'd215dd9d-16aa-4c1f-8289-94c4c7ed040b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d215dd9d-16aa-4c1f-8289-94c4c7ed040b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Western Blot 2', (select name from clinlims.test where guid = '74b2c59e-1ae0-4a3d-8451-6103b341c85c' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='74b2c59e-1ae0-4a3d-8451-6103b341c85c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Western Blot 2', (select reporting_description from clinlims.test where guid = '74b2c59e-1ae0-4a3d-8451-6103b341c85c' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='74b2c59e-1ae0-4a3d-8451-6103b341c85c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Western Blot 1', (select name from clinlims.test where guid = 'a1eac50b-2ef8-4e90-b1a8-9748cf94af7d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a1eac50b-2ef8-4e90-b1a8-9748cf94af7d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Western Blot 1', (select reporting_description from clinlims.test where guid = 'a1eac50b-2ef8-4e90-b1a8-9748cf94af7d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a1eac50b-2ef8-4e90-b1a8-9748cf94af7d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Genie II 10', (select name from clinlims.test where guid = 'd7aac329-ba07-4dfe-b8eb-9b8f679115fb' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d7aac329-ba07-4dfe-b8eb-9b8f679115fb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Genie II 10', (select reporting_description from clinlims.test where guid = 'd7aac329-ba07-4dfe-b8eb-9b8f679115fb' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d7aac329-ba07-4dfe-b8eb-9b8f679115fb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Genie II 100', (select name from clinlims.test where guid = 'cd8b425e-b504-421a-a053-a0b62704c68d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='cd8b425e-b504-421a-a053-a0b62704c68d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Genie II 100', (select reporting_description from clinlims.test where guid = 'cd8b425e-b504-421a-a053-a0b62704c68d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='cd8b425e-b504-421a-a053-a0b62704c68d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Genie II', (select name from clinlims.test where guid = '0ae4006c-3aa9-41f2-9659-c3289c394e2b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='0ae4006c-3aa9-41f2-9659-c3289c394e2b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Genie II', (select reporting_description from clinlims.test where guid = '0ae4006c-3aa9-41f2-9659-c3289c394e2b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='0ae4006c-3aa9-41f2-9659-c3289c394e2b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Vironostika', (select name from clinlims.test where guid = '2b97c89d-3f97-495c-90e1-d1c5c6a4d8fa' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='2b97c89d-3f97-495c-90e1-d1c5c6a4d8fa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Vironostika', (select reporting_description from clinlims.test where guid = '2b97c89d-3f97-495c-90e1-d1c5c6a4d8fa' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='2b97c89d-3f97-495c-90e1-d1c5c6a4d8fa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Murex', (select name from clinlims.test where guid = '4b758755-a41e-45d3-8558-73a532253b71' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='4b758755-a41e-45d3-8558-73a532253b71';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Murex', (select reporting_description from clinlims.test where guid = '4b758755-a41e-45d3-8558-73a532253b71' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='4b758755-a41e-45d3-8558-73a532253b71';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Integral', (select name from clinlims.test where guid = '61bc9b10-8d97-46bb-8e04-04eb1b990433' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='61bc9b10-8d97-46bb-8e04-04eb1b990433';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Integral', (select reporting_description from clinlims.test where guid = '61bc9b10-8d97-46bb-8e04-04eb1b990433' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='61bc9b10-8d97-46bb-8e04-04eb1b990433';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CD4', (select name from clinlims.test where guid = 'acfe5351-f531-4011-acd1-7c473b3f5da3' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='acfe5351-f531-4011-acd1-7c473b3f5da3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CD4', (select reporting_description from clinlims.test where guid = 'acfe5351-f531-4011-acd1-7c473b3f5da3' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='acfe5351-f531-4011-acd1-7c473b3f5da3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Génotypage', (select name from clinlims.test where guid = '9df504a9-297c-4137-9928-4ad8101cd690' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='9df504a9-297c-4137-9928-4ad8101cd690';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Génotypage', (select reporting_description from clinlims.test where guid = '9df504a9-297c-4137-9928-4ad8101cd690' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='9df504a9-297c-4137-9928-4ad8101cd690';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Viral Load', (select name from clinlims.test where guid = 'b50d156e-0f6f-40cd-921c-4e831602a623' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='b50d156e-0f6f-40cd-921c-4e831602a623';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Viral Load', (select reporting_description from clinlims.test where guid = 'b50d156e-0f6f-40cd-921c-4e831602a623' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='b50d156e-0f6f-40cd-921c-4e831602a623';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'DNA PCR', (select name from clinlims.test where guid = 'c1afd23c-c30f-42d7-af48-4321c069f48f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='c1afd23c-c30f-42d7-af48-4321c069f48f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'DNA PCR', (select reporting_description from clinlims.test where guid = 'c1afd23c-c30f-42d7-af48-4321c069f48f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='c1afd23c-c30f-42d7-af48-4321c069f48f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Transaminases ASTL', (select name from clinlims.test where guid = '1d094fe0-7fc7-42eb-ba54-15b24ba44ae9' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='1d094fe0-7fc7-42eb-ba54-15b24ba44ae9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Transaminases ASTL', (select reporting_description from clinlims.test where guid = '1d094fe0-7fc7-42eb-ba54-15b24ba44ae9' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='1d094fe0-7fc7-42eb-ba54-15b24ba44ae9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'GB', (select name from clinlims.test where guid = '0e240569-c095-41c7-bfd2-049527452f16' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='0e240569-c095-41c7-bfd2-049527452f16';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'GB', (select reporting_description from clinlims.test where guid = '0e240569-c095-41c7-bfd2-049527452f16' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='0e240569-c095-41c7-bfd2-049527452f16';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'PLQ', (select name from clinlims.test where guid = '88b7d8d3-e82b-441f-aff3-1410ba2850a5' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='88b7d8d3-e82b-441f-aff3-1410ba2850a5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'PLQ', (select reporting_description from clinlims.test where guid = '88b7d8d3-e82b-441f-aff3-1410ba2850a5' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='88b7d8d3-e82b-441f-aff3-1410ba2850a5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Neut %', (select name from clinlims.test where guid = '0c25692f-a321-4e9c-9722-ca73f6625cb9' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='0c25692f-a321-4e9c-9722-ca73f6625cb9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Neut %', (select reporting_description from clinlims.test where guid = '0c25692f-a321-4e9c-9722-ca73f6625cb9' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='0c25692f-a321-4e9c-9722-ca73f6625cb9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Lymph %', (select name from clinlims.test where guid = 'eede92e7-d141-4c76-ab6e-b24ccfc84215' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='eede92e7-d141-4c76-ab6e-b24ccfc84215';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Lymph %', (select reporting_description from clinlims.test where guid = 'eede92e7-d141-4c76-ab6e-b24ccfc84215' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='eede92e7-d141-4c76-ab6e-b24ccfc84215';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Mono %', (select name from clinlims.test where guid = '9eece97f-04f3-4381-b378-2a9ac08a535a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='9eece97f-04f3-4381-b378-2a9ac08a535a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Mono %', (select reporting_description from clinlims.test where guid = '9eece97f-04f3-4381-b378-2a9ac08a535a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='9eece97f-04f3-4381-b378-2a9ac08a535a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Eo %', (select name from clinlims.test where guid = '50b568e8-e9da-428d-9697-8080bca7377b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='50b568e8-e9da-428d-9697-8080bca7377b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Eo %', (select reporting_description from clinlims.test where guid = '50b568e8-e9da-428d-9697-8080bca7377b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='50b568e8-e9da-428d-9697-8080bca7377b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Baso %', (select name from clinlims.test where guid = 'a41fcfb4-e3ba-4add-ac5d-56fae322cb9e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a41fcfb4-e3ba-4add-ac5d-56fae322cb9e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Baso %', (select reporting_description from clinlims.test where guid = 'a41fcfb4-e3ba-4add-ac5d-56fae322cb9e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a41fcfb4-e3ba-4add-ac5d-56fae322cb9e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HCT', (select name from clinlims.test where guid = '6792a51e-050b-4493-88ca-6f490c20cc5c' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='6792a51e-050b-4493-88ca-6f490c20cc5c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HCT', (select reporting_description from clinlims.test where guid = '6792a51e-050b-4493-88ca-6f490c20cc5c' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='6792a51e-050b-4493-88ca-6f490c20cc5c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CCMH', (select name from clinlims.test where guid = '8ab87a81-6b6b-4d4b-b53b-fac57109e393' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='8ab87a81-6b6b-4d4b-b53b-fac57109e393';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CCMH', (select reporting_description from clinlims.test where guid = '8ab87a81-6b6b-4d4b-b53b-fac57109e393' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='8ab87a81-6b6b-4d4b-b53b-fac57109e393';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'GR', (select name from clinlims.test where guid = 'fe6405c8-f96b-491b-95c9-b1f635339d6a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='fe6405c8-f96b-491b-95c9-b1f635339d6a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'GR', (select reporting_description from clinlims.test where guid = 'fe6405c8-f96b-491b-95c9-b1f635339d6a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='fe6405c8-f96b-491b-95c9-b1f635339d6a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hb', (select name from clinlims.test where guid = 'cecea358-1fa0-44b2-8185-d8c010315f78' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='cecea358-1fa0-44b2-8185-d8c010315f78';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hb', (select reporting_description from clinlims.test where guid = 'cecea358-1fa0-44b2-8185-d8c010315f78' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='cecea358-1fa0-44b2-8185-d8c010315f78';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'VGM', (select name from clinlims.test where guid = 'ddce6c12-e319-455f-9f48-2f6ff363a246' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='ddce6c12-e319-455f-9f48-2f6ff363a246';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'VGM', (select reporting_description from clinlims.test where guid = 'ddce6c12-e319-455f-9f48-2f6ff363a246' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='ddce6c12-e319-455f-9f48-2f6ff363a246';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'TCMH', (select name from clinlims.test where guid = 'bf497153-ba88-4fe8-83ee-c144229d7628' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='bf497153-ba88-4fe8-83ee-c144229d7628';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'TCMH', (select reporting_description from clinlims.test where guid = 'bf497153-ba88-4fe8-83ee-c144229d7628' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='bf497153-ba88-4fe8-83ee-c144229d7628';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CD3 percentage count', (select name from clinlims.test where guid = 'ab362fc9-56bb-4f65-93d4-aed5bd8e3c8c' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='ab362fc9-56bb-4f65-93d4-aed5bd8e3c8c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CD3 percentage count', (select reporting_description from clinlims.test where guid = 'ab362fc9-56bb-4f65-93d4-aed5bd8e3c8c' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='ab362fc9-56bb-4f65-93d4-aed5bd8e3c8c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CD4 percentage count', (select name from clinlims.test where guid = 'cb6029e2-ff76-4df0-812d-88539cccba28' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='cb6029e2-ff76-4df0-812d-88539cccba28';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CD4 percentage count', (select reporting_description from clinlims.test where guid = 'cb6029e2-ff76-4df0-812d-88539cccba28' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='cb6029e2-ff76-4df0-812d-88539cccba28';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CD4 absolute count', (select name from clinlims.test where guid = 'a6718123-8d56-4103-9bbe-26b19306b83d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a6718123-8d56-4103-9bbe-26b19306b83d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CD4 absolute count', (select reporting_description from clinlims.test where guid = 'a6718123-8d56-4103-9bbe-26b19306b83d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a6718123-8d56-4103-9bbe-26b19306b83d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'NE#', (select name from clinlims.test where guid = '53736980-edf1-4abf-aa6b-7ab2dbf3e7c4' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='53736980-edf1-4abf-aa6b-7ab2dbf3e7c4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'NE#', (select reporting_description from clinlims.test where guid = '53736980-edf1-4abf-aa6b-7ab2dbf3e7c4' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='53736980-edf1-4abf-aa6b-7ab2dbf3e7c4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'MO#', (select name from clinlims.test where guid = 'a11305b0-5c64-4661-941b-de4c3ef5e61e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a11305b0-5c64-4661-941b-de4c3ef5e61e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'MO#', (select reporting_description from clinlims.test where guid = 'a11305b0-5c64-4661-941b-de4c3ef5e61e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a11305b0-5c64-4661-941b-de4c3ef5e61e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'BA#', (select name from clinlims.test where guid = 'ee97e167-6269-4095-99a1-19f5c75bb94b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='ee97e167-6269-4095-99a1-19f5c75bb94b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'BA#', (select reporting_description from clinlims.test where guid = 'ee97e167-6269-4095-99a1-19f5c75bb94b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='ee97e167-6269-4095-99a1-19f5c75bb94b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'LY#', (select name from clinlims.test where guid = '496e3a90-968a-4c54-a101-72fb43d0b4ee' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='496e3a90-968a-4c54-a101-72fb43d0b4ee';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'LY#', (select reporting_description from clinlims.test where guid = '496e3a90-968a-4c54-a101-72fb43d0b4ee' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='496e3a90-968a-4c54-a101-72fb43d0b4ee';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'EO#', (select name from clinlims.test where guid = 'cd73ec28-f5d8-47d6-b079-e2ba8f506f8f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='cd73ec28-f5d8-47d6-b079-e2ba8f506f8f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'EO#', (select reporting_description from clinlims.test where guid = 'cd73ec28-f5d8-47d6-b079-e2ba8f506f8f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='cd73ec28-f5d8-47d6-b079-e2ba8f506f8f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Bioline', (select name from clinlims.test where guid = 'c98ef346-76e1-4d16-9964-71cca4396de5' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='c98ef346-76e1-4d16-9964-71cca4396de5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Bioline', (select reporting_description from clinlims.test where guid = 'c98ef346-76e1-4d16-9964-71cca4396de5' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='c98ef346-76e1-4d16-9964-71cca4396de5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Innolia', (select name from clinlims.test where guid = 'de47583b-3dcc-46bf-bfa2-f03d8bc8670d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='de47583b-3dcc-46bf-bfa2-f03d8bc8670d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Innolia', (select reporting_description from clinlims.test where guid = 'de47583b-3dcc-46bf-bfa2-f03d8bc8670d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='de47583b-3dcc-46bf-bfa2-f03d8bc8670d';
