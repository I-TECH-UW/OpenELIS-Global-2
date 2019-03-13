INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV Rapid Test', (select name from clinlims.test where guid = 'da9939e0-7a03-485a-ae4e-e08cccf5d82e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='da9939e0-7a03-485a-ae4e-e08cccf5d82e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV Rapid Test', (select reporting_description from clinlims.test where guid = 'da9939e0-7a03-485a-ae4e-e08cccf5d82e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='da9939e0-7a03-485a-ae4e-e08cccf5d82e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV Rapid Test', (select name from clinlims.test where guid = '99fa53f7-f133-492f-b160-a54e91c2d187' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='99fa53f7-f133-492f-b160-a54e91c2d187';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV Rapid Test', (select reporting_description from clinlims.test where guid = '99fa53f7-f133-492f-b160-a54e91c2d187' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='99fa53f7-f133-492f-b160-a54e91c2d187';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV Rapid Test', (select name from clinlims.test where guid = '02c807ab-5f7d-4a55-a808-dd23d97822de' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='02c807ab-5f7d-4a55-a808-dd23d97822de';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV Rapid Test', (select reporting_description from clinlims.test where guid = '02c807ab-5f7d-4a55-a808-dd23d97822de' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='02c807ab-5f7d-4a55-a808-dd23d97822de';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Elisa HIV', (select name from clinlims.test where guid = '23b1918a-6eb2-442a-8a46-298604cc8751' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='23b1918a-6eb2-442a-8a46-298604cc8751';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Elisa HIV', (select reporting_description from clinlims.test where guid = '23b1918a-6eb2-442a-8a46-298604cc8751' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='23b1918a-6eb2-442a-8a46-298604cc8751';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV Rapid Test', (select name from clinlims.test where guid = 'b914228e-2efe-40cb-8e88-28563a43b06b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='b914228e-2efe-40cb-8e88-28563a43b06b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV Rapid Test', (select reporting_description from clinlims.test where guid = 'b914228e-2efe-40cb-8e88-28563a43b06b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='b914228e-2efe-40cb-8e88-28563a43b06b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Elisa HIV', (select name from clinlims.test where guid = '09922696-515b-4582-99b7-a40ee763616b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='09922696-515b-4582-99b7-a40ee763616b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Elisa HIV', (select reporting_description from clinlims.test where guid = '09922696-515b-4582-99b7-a40ee763616b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='09922696-515b-4582-99b7-a40ee763616b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Elisa HIV', (select name from clinlims.test where guid = 'b247d11d-8652-4417-9f5a-80e009abe547' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='b247d11d-8652-4417-9f5a-80e009abe547';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Elisa HIV', (select reporting_description from clinlims.test where guid = 'b247d11d-8652-4417-9f5a-80e009abe547' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='b247d11d-8652-4417-9f5a-80e009abe547';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Elisa HIV', (select name from clinlims.test where guid = '490388ca-71ba-4b84-8681-f00da033b92c' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='490388ca-71ba-4b84-8681-f00da033b92c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Elisa HIV', (select reporting_description from clinlims.test where guid = '490388ca-71ba-4b84-8681-f00da033b92c' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='490388ca-71ba-4b84-8681-f00da033b92c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Western Blot HIV', (select name from clinlims.test where guid = '1c4c1373-1237-4e4a-8323-3657a745fe00' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='1c4c1373-1237-4e4a-8323-3657a745fe00';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Western Blot HIV', (select reporting_description from clinlims.test where guid = '1c4c1373-1237-4e4a-8323-3657a745fe00' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='1c4c1373-1237-4e4a-8323-3657a745fe00';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Western Blot HIV', (select name from clinlims.test where guid = '87c7a6ff-f11e-4c72-ade1-3c855e375d9b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='87c7a6ff-f11e-4c72-ade1-3c855e375d9b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Western Blot HIV', (select reporting_description from clinlims.test where guid = '87c7a6ff-f11e-4c72-ade1-3c855e375d9b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='87c7a6ff-f11e-4c72-ade1-3c855e375d9b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Western Blot HIV', (select name from clinlims.test where guid = '8f1a332c-b0f7-4539-8210-3311e8f6e339' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='8f1a332c-b0f7-4539-8210-3311e8f6e339';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Western Blot HIV', (select reporting_description from clinlims.test where guid = '8f1a332c-b0f7-4539-8210-3311e8f6e339' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='8f1a332c-b0f7-4539-8210-3311e8f6e339';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Western Blot HIV', (select name from clinlims.test where guid = 'd21eb51b-e333-4805-b46f-382cd7ef6a83' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d21eb51b-e333-4805-b46f-382cd7ef6a83';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Western Blot HIV', (select reporting_description from clinlims.test where guid = 'd21eb51b-e333-4805-b46f-382cd7ef6a83' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d21eb51b-e333-4805-b46f-382cd7ef6a83';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Measles', (select name from clinlims.test where guid = 'e40940db-3587-4470-bd69-65a3fcd1ff06' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='e40940db-3587-4470-bd69-65a3fcd1ff06';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Measles', (select reporting_description from clinlims.test where guid = 'e40940db-3587-4470-bd69-65a3fcd1ff06' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='e40940db-3587-4470-bd69-65a3fcd1ff06';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Measles', (select name from clinlims.test where guid = 'bd616125-db99-4211-8bd1-7b02bc1c169d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='bd616125-db99-4211-8bd1-7b02bc1c169d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Measles', (select reporting_description from clinlims.test where guid = 'bd616125-db99-4211-8bd1-7b02bc1c169d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='bd616125-db99-4211-8bd1-7b02bc1c169d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Measles', (select name from clinlims.test where guid = '148c0e3d-107a-4e55-9948-7382361708c2' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='148c0e3d-107a-4e55-9948-7382361708c2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Measles', (select reporting_description from clinlims.test where guid = '148c0e3d-107a-4e55-9948-7382361708c2' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='148c0e3d-107a-4e55-9948-7382361708c2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'c72fe720-427d-4b68-87ac-1d70b98f1b15' ), (select name from clinlims.test where guid = 'c72fe720-427d-4b68-87ac-1d70b98f1b15' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='c72fe720-427d-4b68-87ac-1d70b98f1b15';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'c72fe720-427d-4b68-87ac-1d70b98f1b15' ), (select reporting_description from clinlims.test where guid = 'c72fe720-427d-4b68-87ac-1d70b98f1b15' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='c72fe720-427d-4b68-87ac-1d70b98f1b15';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Dengue', (select name from clinlims.test where guid = '95bec0d4-d8fb-4ca3-bf61-cf23b9ea7cdf' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='95bec0d4-d8fb-4ca3-bf61-cf23b9ea7cdf';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Dengue', (select reporting_description from clinlims.test where guid = '95bec0d4-d8fb-4ca3-bf61-cf23b9ea7cdf' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='95bec0d4-d8fb-4ca3-bf61-cf23b9ea7cdf';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '61ce06ff-26bf-4ed5-a69c-3920a71a9f65' ), (select name from clinlims.test where guid = '61ce06ff-26bf-4ed5-a69c-3920a71a9f65' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='61ce06ff-26bf-4ed5-a69c-3920a71a9f65';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '61ce06ff-26bf-4ed5-a69c-3920a71a9f65' ), (select reporting_description from clinlims.test where guid = '61ce06ff-26bf-4ed5-a69c-3920a71a9f65' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='61ce06ff-26bf-4ed5-a69c-3920a71a9f65';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Dengue', (select name from clinlims.test where guid = '66a936f5-8f22-4693-94f2-ab14c3ebfd02' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='66a936f5-8f22-4693-94f2-ab14c3ebfd02';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Dengue', (select reporting_description from clinlims.test where guid = '66a936f5-8f22-4693-94f2-ab14c3ebfd02' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='66a936f5-8f22-4693-94f2-ab14c3ebfd02';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'a9216dd0-8e1b-4bb5-8e57-d6272395f3f2' ), (select name from clinlims.test where guid = 'a9216dd0-8e1b-4bb5-8e57-d6272395f3f2' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a9216dd0-8e1b-4bb5-8e57-d6272395f3f2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'a9216dd0-8e1b-4bb5-8e57-d6272395f3f2' ), (select reporting_description from clinlims.test where guid = 'a9216dd0-8e1b-4bb5-8e57-d6272395f3f2' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a9216dd0-8e1b-4bb5-8e57-d6272395f3f2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Rubella', (select name from clinlims.test where guid = 'ef21bc3a-64af-4d52-9e50-16da57b0a1c8' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='ef21bc3a-64af-4d52-9e50-16da57b0a1c8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Rubella', (select reporting_description from clinlims.test where guid = 'ef21bc3a-64af-4d52-9e50-16da57b0a1c8' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='ef21bc3a-64af-4d52-9e50-16da57b0a1c8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '751b6f37-b75a-44b0-b615-fac816e6ab85' ), (select name from clinlims.test where guid = '751b6f37-b75a-44b0-b615-fac816e6ab85' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='751b6f37-b75a-44b0-b615-fac816e6ab85';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '751b6f37-b75a-44b0-b615-fac816e6ab85' ), (select reporting_description from clinlims.test where guid = '751b6f37-b75a-44b0-b615-fac816e6ab85' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='751b6f37-b75a-44b0-b615-fac816e6ab85';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Rubella', (select name from clinlims.test where guid = '6d1f8560-8dee-4ae9-82e8-9f92a3923ed1' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='6d1f8560-8dee-4ae9-82e8-9f92a3923ed1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Rubella', (select reporting_description from clinlims.test where guid = '6d1f8560-8dee-4ae9-82e8-9f92a3923ed1' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='6d1f8560-8dee-4ae9-82e8-9f92a3923ed1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '826b95c9-b43b-4690-adfb-b707ab838ff2' ), (select name from clinlims.test where guid = '826b95c9-b43b-4690-adfb-b707ab838ff2' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='826b95c9-b43b-4690-adfb-b707ab838ff2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '826b95c9-b43b-4690-adfb-b707ab838ff2' ), (select reporting_description from clinlims.test where guid = '826b95c9-b43b-4690-adfb-b707ab838ff2' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='826b95c9-b43b-4690-adfb-b707ab838ff2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Dengue NS1Ag', (select name from clinlims.test where guid = 'fcd7e0fb-4f4e-49cd-9205-d7f4c181c93b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='fcd7e0fb-4f4e-49cd-9205-d7f4c181c93b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Dengue NS1Ag', (select reporting_description from clinlims.test where guid = 'fcd7e0fb-4f4e-49cd-9205-d7f4c181c93b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='fcd7e0fb-4f4e-49cd-9205-d7f4c181c93b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Dengue NS1Ag', (select name from clinlims.test where guid = 'd12bc927-24e2-4c3b-8c8c-29b65f018678' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d12bc927-24e2-4c3b-8c8c-29b65f018678';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Dengue NS1Ag', (select reporting_description from clinlims.test where guid = 'd12bc927-24e2-4c3b-8c8c-29b65f018678' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d12bc927-24e2-4c3b-8c8c-29b65f018678';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hepatatis A IgM', (select name from clinlims.test where guid = 'f1d90fd0-4794-46c1-aece-f653d2f4aa83' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='f1d90fd0-4794-46c1-aece-f653d2f4aa83';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hepatatis A IgM', (select reporting_description from clinlims.test where guid = 'f1d90fd0-4794-46c1-aece-f653d2f4aa83' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='f1d90fd0-4794-46c1-aece-f653d2f4aa83';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hepatatis A IgM', (select name from clinlims.test where guid = 'ed32c3b7-b98e-4394-82a5-fbb88b3eb3df' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='ed32c3b7-b98e-4394-82a5-fbb88b3eb3df';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hepatatis A IgM', (select reporting_description from clinlims.test where guid = 'ed32c3b7-b98e-4394-82a5-fbb88b3eb3df' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='ed32c3b7-b98e-4394-82a5-fbb88b3eb3df';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hepatatis B Ag', (select name from clinlims.test where guid = 'e94eb63b-288b-4fbd-b9e4-3f29fdde0038' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='e94eb63b-288b-4fbd-b9e4-3f29fdde0038';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hepatatis B Ag', (select reporting_description from clinlims.test where guid = 'e94eb63b-288b-4fbd-b9e4-3f29fdde0038' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='e94eb63b-288b-4fbd-b9e4-3f29fdde0038';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hepatatis B Ag', (select name from clinlims.test where guid = '0158614b-ff57-41af-82e5-c3dd6a91d3e0' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='0158614b-ff57-41af-82e5-c3dd6a91d3e0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hepatatis B Ag', (select reporting_description from clinlims.test where guid = '0158614b-ff57-41af-82e5-c3dd6a91d3e0' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='0158614b-ff57-41af-82e5-c3dd6a91d3e0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hepatatis C IgM', (select name from clinlims.test where guid = 'c8831f0b-ec06-45ca-be73-6deb822d0d52' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='c8831f0b-ec06-45ca-be73-6deb822d0d52';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hepatatis C IgM', (select reporting_description from clinlims.test where guid = 'c8831f0b-ec06-45ca-be73-6deb822d0d52' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='c8831f0b-ec06-45ca-be73-6deb822d0d52';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hepatatis C IgM', (select name from clinlims.test where guid = '9939853a-56b6-4537-a051-8fcb04fb4743' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='9939853a-56b6-4537-a051-8fcb04fb4743';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hepatatis C IgM', (select reporting_description from clinlims.test where guid = '9939853a-56b6-4537-a051-8fcb04fb4743' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='9939853a-56b6-4537-a051-8fcb04fb4743';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hepatatis E IgM', (select name from clinlims.test where guid = 'cb920c6b-19d5-4487-88f0-5c340f00bb8a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='cb920c6b-19d5-4487-88f0-5c340f00bb8a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hepatatis E IgM', (select reporting_description from clinlims.test where guid = 'cb920c6b-19d5-4487-88f0-5c340f00bb8a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='cb920c6b-19d5-4487-88f0-5c340f00bb8a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hepatatis E IgM', (select name from clinlims.test where guid = 'c4f91b7d-38f8-4a76-906e-0e7904d35d46' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='c4f91b7d-38f8-4a76-906e-0e7904d35d46';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hepatatis E IgM', (select reporting_description from clinlims.test where guid = 'c4f91b7d-38f8-4a76-906e-0e7904d35d46' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='c4f91b7d-38f8-4a76-906e-0e7904d35d46';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Influenza A/Immunofluoresence', (select name from clinlims.test where guid = '79db3bc2-a8be-4f89-8a35-3c44a100fde9' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='79db3bc2-a8be-4f89-8a35-3c44a100fde9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Influenza A/Immunofluoresence', (select reporting_description from clinlims.test where guid = '79db3bc2-a8be-4f89-8a35-3c44a100fde9' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='79db3bc2-a8be-4f89-8a35-3c44a100fde9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Influenza A/Immunofluoresence', (select name from clinlims.test where guid = '3cb35cfe-af02-46bb-ae0a-9eaeac2df29d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3cb35cfe-af02-46bb-ae0a-9eaeac2df29d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Influenza A/Immunofluoresence', (select reporting_description from clinlims.test where guid = '3cb35cfe-af02-46bb-ae0a-9eaeac2df29d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3cb35cfe-af02-46bb-ae0a-9eaeac2df29d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Influenza A/Immunofluoresence', (select name from clinlims.test where guid = '3e16cde4-69cf-413a-8899-a262193106ce' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3e16cde4-69cf-413a-8899-a262193106ce';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Influenza A/Immunofluoresence', (select reporting_description from clinlims.test where guid = '3e16cde4-69cf-413a-8899-a262193106ce' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3e16cde4-69cf-413a-8899-a262193106ce';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Influenza B/Immunofluoresence', (select name from clinlims.test where guid = '2a41408f-1b9e-43ae-b840-92ef768b119d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='2a41408f-1b9e-43ae-b840-92ef768b119d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Influenza B/Immunofluoresence', (select reporting_description from clinlims.test where guid = '2a41408f-1b9e-43ae-b840-92ef768b119d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='2a41408f-1b9e-43ae-b840-92ef768b119d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Influenza B/Immunofluoresence', (select name from clinlims.test where guid = '8b698e63-b8ea-4393-a37c-02ba1e160866' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='8b698e63-b8ea-4393-a37c-02ba1e160866';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Influenza B/Immunofluoresence', (select reporting_description from clinlims.test where guid = '8b698e63-b8ea-4393-a37c-02ba1e160866' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='8b698e63-b8ea-4393-a37c-02ba1e160866';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Influenza B/Immunofluoresence', (select name from clinlims.test where guid = '72e9c2af-e8a6-431e-a63d-1cb62a4fbb6e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='72e9c2af-e8a6-431e-a63d-1cb62a4fbb6e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Influenza B/Immunofluoresence', (select reporting_description from clinlims.test where guid = '72e9c2af-e8a6-431e-a63d-1cb62a4fbb6e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='72e9c2af-e8a6-431e-a63d-1cb62a4fbb6e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Para Influenza 1/Immunofluoresence', (select name from clinlims.test where guid = '5cf47728-a4ca-4fcd-b94b-7b32afe98a26' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='5cf47728-a4ca-4fcd-b94b-7b32afe98a26';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Para Influenza 1/Immunofluoresence', (select reporting_description from clinlims.test where guid = '5cf47728-a4ca-4fcd-b94b-7b32afe98a26' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='5cf47728-a4ca-4fcd-b94b-7b32afe98a26';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Para Influenza 1/Immunofluoresence', (select name from clinlims.test where guid = 'd04371a0-263b-45af-baba-f091ae267e54' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d04371a0-263b-45af-baba-f091ae267e54';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Para Influenza 1/Immunofluoresence', (select reporting_description from clinlims.test where guid = 'd04371a0-263b-45af-baba-f091ae267e54' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d04371a0-263b-45af-baba-f091ae267e54';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Para Influenza 1/Immunofluoresence', (select name from clinlims.test where guid = '6e35a2b3-9e18-4c1e-9e66-305790de9115' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='6e35a2b3-9e18-4c1e-9e66-305790de9115';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Para Influenza 1/Immunofluoresence', (select reporting_description from clinlims.test where guid = '6e35a2b3-9e18-4c1e-9e66-305790de9115' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='6e35a2b3-9e18-4c1e-9e66-305790de9115';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Para Influenza 2/Immunofluoresence', (select name from clinlims.test where guid = 'b291a131-8d83-4bf3-94fe-a152e1105c62' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='b291a131-8d83-4bf3-94fe-a152e1105c62';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Para Influenza 2/Immunofluoresence', (select reporting_description from clinlims.test where guid = 'b291a131-8d83-4bf3-94fe-a152e1105c62' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='b291a131-8d83-4bf3-94fe-a152e1105c62';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Para Influenza 2/Immunofluoresence', (select name from clinlims.test where guid = 'c05ec767-8bc8-41e5-aeae-89f1f2c2d429' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='c05ec767-8bc8-41e5-aeae-89f1f2c2d429';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Para Influenza 2/Immunofluoresence', (select reporting_description from clinlims.test where guid = 'c05ec767-8bc8-41e5-aeae-89f1f2c2d429' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='c05ec767-8bc8-41e5-aeae-89f1f2c2d429';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Para Influenza 2/Immunofluoresence', (select name from clinlims.test where guid = '8e84893b-612b-4ec3-92b7-3649abc34a41' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='8e84893b-612b-4ec3-92b7-3649abc34a41';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Para Influenza 2/Immunofluoresence', (select reporting_description from clinlims.test where guid = '8e84893b-612b-4ec3-92b7-3649abc34a41' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='8e84893b-612b-4ec3-92b7-3649abc34a41';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Para Influenza 3/Immunofluoresence', (select name from clinlims.test where guid = '67f5cff1-91e0-4fcd-b811-5cf02bdb5859' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='67f5cff1-91e0-4fcd-b811-5cf02bdb5859';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Para Influenza 3/Immunofluoresence', (select reporting_description from clinlims.test where guid = '67f5cff1-91e0-4fcd-b811-5cf02bdb5859' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='67f5cff1-91e0-4fcd-b811-5cf02bdb5859';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Para Influenza 3/Immunofluoresence', (select name from clinlims.test where guid = '698118bc-12e8-4667-8c12-5a0d44c40116' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='698118bc-12e8-4667-8c12-5a0d44c40116';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Para Influenza 3/Immunofluoresence', (select reporting_description from clinlims.test where guid = '698118bc-12e8-4667-8c12-5a0d44c40116' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='698118bc-12e8-4667-8c12-5a0d44c40116';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Para Influenza 3/Immunofluoresence', (select name from clinlims.test where guid = '969ca750-b4ad-4010-b684-d72db9f639cf' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='969ca750-b4ad-4010-b684-d72db9f639cf';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Para Influenza 3/Immunofluoresence', (select reporting_description from clinlims.test where guid = '969ca750-b4ad-4010-b684-d72db9f639cf' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='969ca750-b4ad-4010-b684-d72db9f639cf';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Synctial respiratory virus', (select name from clinlims.test where guid = '7a689abe-4c3a-450b-af20-d9c301f2a032' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='7a689abe-4c3a-450b-af20-d9c301f2a032';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Synctial respiratory virus', (select reporting_description from clinlims.test where guid = '7a689abe-4c3a-450b-af20-d9c301f2a032' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='7a689abe-4c3a-450b-af20-d9c301f2a032';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Synctial respiratory virus', (select name from clinlims.test where guid = '86a57229-2f40-4486-8d38-ec736bf57885' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='86a57229-2f40-4486-8d38-ec736bf57885';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Synctial respiratory virus', (select reporting_description from clinlims.test where guid = '86a57229-2f40-4486-8d38-ec736bf57885' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='86a57229-2f40-4486-8d38-ec736bf57885';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Synctial respiratory virus', (select name from clinlims.test where guid = '0d11de63-239f-4f6a-a705-c482ea82eb1b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='0d11de63-239f-4f6a-a705-c482ea82eb1b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Synctial respiratory virus', (select reporting_description from clinlims.test where guid = '0d11de63-239f-4f6a-a705-c482ea82eb1b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='0d11de63-239f-4f6a-a705-c482ea82eb1b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Adenovirus', (select name from clinlims.test where guid = '930fe5d9-800b-4a9b-ad10-63b1efc88537' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='930fe5d9-800b-4a9b-ad10-63b1efc88537';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Adenovirus', (select reporting_description from clinlims.test where guid = '930fe5d9-800b-4a9b-ad10-63b1efc88537' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='930fe5d9-800b-4a9b-ad10-63b1efc88537';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Adenovirus', (select name from clinlims.test where guid = '84547baf-49b7-435f-a8a4-008f5f12fb2e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='84547baf-49b7-435f-a8a4-008f5f12fb2e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Adenovirus', (select reporting_description from clinlims.test where guid = '84547baf-49b7-435f-a8a4-008f5f12fb2e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='84547baf-49b7-435f-a8a4-008f5f12fb2e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Adenovirus', (select name from clinlims.test where guid = 'af8f3b80-3c0a-49e6-abf6-91b67d7a4e29' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='af8f3b80-3c0a-49e6-abf6-91b67d7a4e29';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Adenovirus', (select reporting_description from clinlims.test where guid = 'af8f3b80-3c0a-49e6-abf6-91b67d7a4e29' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='af8f3b80-3c0a-49e6-abf6-91b67d7a4e29';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Rotavirus', (select name from clinlims.test where guid = 'bf39e949-3477-451f-8791-3a88f5f27740' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='bf39e949-3477-451f-8791-3a88f5f27740';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Rotavirus', (select reporting_description from clinlims.test where guid = 'bf39e949-3477-451f-8791-3a88f5f27740' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='bf39e949-3477-451f-8791-3a88f5f27740';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Rotavirus Rapid test', (select name from clinlims.test where guid = 'ed557a11-58aa-4203-bd9f-3f24cebd9851' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='ed557a11-58aa-4203-bd9f-3f24cebd9851';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Rotavirus Rapid test', (select reporting_description from clinlims.test where guid = 'ed557a11-58aa-4203-bd9f-3f24cebd9851' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='ed557a11-58aa-4203-bd9f-3f24cebd9851';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Stool Culture', (select name from clinlims.test where guid = 'd9f57b70-d3fd-4a75-886d-6778341c2238' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d9f57b70-d3fd-4a75-886d-6778341c2238';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Stool Culture', (select reporting_description from clinlims.test where guid = 'd9f57b70-d3fd-4a75-886d-6778341c2238' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d9f57b70-d3fd-4a75-886d-6778341c2238';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Syphilis Rapid Test', (select name from clinlims.test where guid = '4eabf075-eaae-4cf1-a300-7d96ad638e5c' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='4eabf075-eaae-4cf1-a300-7d96ad638e5c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Syphilis Rapid Test', (select reporting_description from clinlims.test where guid = '4eabf075-eaae-4cf1-a300-7d96ad638e5c' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='4eabf075-eaae-4cf1-a300-7d96ad638e5c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Polio', (select name from clinlims.test where guid = 'f76c9ded-a117-45ea-ab34-87da35477607' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='f76c9ded-a117-45ea-ab34-87da35477607';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Polio', (select reporting_description from clinlims.test where guid = 'f76c9ded-a117-45ea-ab34-87da35477607' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='f76c9ded-a117-45ea-ab34-87da35477607';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Polio stool 1', (select name from clinlims.test where guid = '0ec575ce-ef9c-48bd-8ca5-4ca81612e344' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='0ec575ce-ef9c-48bd-8ca5-4ca81612e344';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Polio stool 1', (select reporting_description from clinlims.test where guid = '0ec575ce-ef9c-48bd-8ca5-4ca81612e344' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='0ec575ce-ef9c-48bd-8ca5-4ca81612e344';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Polio stool 2', (select name from clinlims.test where guid = '6a72c9f1-32aa-4355-b9de-1cf8dafd7591' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='6a72c9f1-32aa-4355-b9de-1cf8dafd7591';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Polio stool 2', (select reporting_description from clinlims.test where guid = '6a72c9f1-32aa-4355-b9de-1cf8dafd7591' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='6a72c9f1-32aa-4355-b9de-1cf8dafd7591';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Syphilis RPR', (select name from clinlims.test where guid = 'ec00d14b-976f-49c5-a552-a2ff59ff0213' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='ec00d14b-976f-49c5-a552-a2ff59ff0213';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Syphilis RPR', (select reporting_description from clinlims.test where guid = 'ec00d14b-976f-49c5-a552-a2ff59ff0213' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='ec00d14b-976f-49c5-a552-a2ff59ff0213';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Syphilis TPHA', (select name from clinlims.test where guid = 'a792852a-01f2-4b79-9f1a-4259c9aaa369' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a792852a-01f2-4b79-9f1a-4259c9aaa369';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Syphilis TPHA', (select reporting_description from clinlims.test where guid = 'a792852a-01f2-4b79-9f1a-4259c9aaa369' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a792852a-01f2-4b79-9f1a-4259c9aaa369';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Cryptococcus rapid test', (select name from clinlims.test where guid = '7504d2ae-f2e8-4863-8164-f14ac9d450fd' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='7504d2ae-f2e8-4863-8164-f14ac9d450fd';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Cryptococcus rapid test', (select reporting_description from clinlims.test where guid = '7504d2ae-f2e8-4863-8164-f14ac9d450fd' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='7504d2ae-f2e8-4863-8164-f14ac9d450fd';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Cryptococcus rapid test', (select name from clinlims.test where guid = '45b0d7a8-0af8-4693-b609-9db06a2a087e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='45b0d7a8-0af8-4693-b609-9db06a2a087e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Cryptococcus rapid test', (select reporting_description from clinlims.test where guid = '45b0d7a8-0af8-4693-b609-9db06a2a087e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='45b0d7a8-0af8-4693-b609-9db06a2a087e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Cryptococcus rapid test', (select name from clinlims.test where guid = '34b4fb44-c9e9-40ef-9e82-65cf4ce99c9a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='34b4fb44-c9e9-40ef-9e82-65cf4ce99c9a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Cryptococcus rapid test', (select reporting_description from clinlims.test where guid = '34b4fb44-c9e9-40ef-9e82-65cf4ce99c9a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='34b4fb44-c9e9-40ef-9e82-65cf4ce99c9a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '847e0fdd-8c2f-4f94-a176-b0ce7c577500' ), (select name from clinlims.test where guid = '847e0fdd-8c2f-4f94-a176-b0ce7c577500' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='847e0fdd-8c2f-4f94-a176-b0ce7c577500';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '847e0fdd-8c2f-4f94-a176-b0ce7c577500' ), (select reporting_description from clinlims.test where guid = '847e0fdd-8c2f-4f94-a176-b0ce7c577500' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='847e0fdd-8c2f-4f94-a176-b0ce7c577500';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV DNA PCR', (select name from clinlims.test where guid = 'fcfc82f3-e4cd-4d4f-ba95-741b08f684d9' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='fcfc82f3-e4cd-4d4f-ba95-741b08f684d9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV DNA PCR', (select reporting_description from clinlims.test where guid = 'fcfc82f3-e4cd-4d4f-ba95-741b08f684d9' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='fcfc82f3-e4cd-4d4f-ba95-741b08f684d9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV DNA PCR', (select name from clinlims.test where guid = '10ac3033-1506-48a3-9aaf-520ac1a302d6' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='10ac3033-1506-48a3-9aaf-520ac1a302d6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV DNA PCR', (select reporting_description from clinlims.test where guid = '10ac3033-1506-48a3-9aaf-520ac1a302d6' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='10ac3033-1506-48a3-9aaf-520ac1a302d6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV DNA PCR', (select name from clinlims.test where guid = 'a8073139-1fe6-45d9-b3ac-d39d8493fd00' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a8073139-1fe6-45d9-b3ac-d39d8493fd00';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV DNA PCR', (select reporting_description from clinlims.test where guid = 'a8073139-1fe6-45d9-b3ac-d39d8493fd00' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a8073139-1fe6-45d9-b3ac-d39d8493fd00';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV DNA PCR', (select name from clinlims.test where guid = '9486e580-9faf-4809-87dc-c3456d9c7353' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='9486e580-9faf-4809-87dc-c3456d9c7353';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV DNA PCR', (select reporting_description from clinlims.test where guid = '9486e580-9faf-4809-87dc-c3456d9c7353' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='9486e580-9faf-4809-87dc-c3456d9c7353';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV DNA PCR', (select name from clinlims.test where guid = '0371b585-40ec-4800-a41f-9bdb2876dad8' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='0371b585-40ec-4800-a41f-9bdb2876dad8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV DNA PCR', (select reporting_description from clinlims.test where guid = '0371b585-40ec-4800-a41f-9bdb2876dad8' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='0371b585-40ec-4800-a41f-9bdb2876dad8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV DNA PCR', (select name from clinlims.test where guid = 'fdaa78e8-15b4-4995-ae75-2e1e98cbe2e1' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='fdaa78e8-15b4-4995-ae75-2e1e98cbe2e1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV DNA PCR', (select reporting_description from clinlims.test where guid = 'fdaa78e8-15b4-4995-ae75-2e1e98cbe2e1' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='fdaa78e8-15b4-4995-ae75-2e1e98cbe2e1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV DNA PCR', (select name from clinlims.test where guid = 'b0859ed8-d98b-4006-b7af-9c8e59fb2604' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='b0859ed8-d98b-4006-b7af-9c8e59fb2604';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV DNA PCR', (select reporting_description from clinlims.test where guid = 'b0859ed8-d98b-4006-b7af-9c8e59fb2604' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='b0859ed8-d98b-4006-b7af-9c8e59fb2604';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV DNA PCR', (select name from clinlims.test where guid = 'c54e1097-e9e3-48f4-b6ff-d444076595a0' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='c54e1097-e9e3-48f4-b6ff-d444076595a0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV DNA PCR', (select reporting_description from clinlims.test where guid = 'c54e1097-e9e3-48f4-b6ff-d444076595a0' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='c54e1097-e9e3-48f4-b6ff-d444076595a0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV DNA PCR', (select name from clinlims.test where guid = 'a01d467a-95ae-4b80-b585-d4d0e26d03fd' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a01d467a-95ae-4b80-b585-d4d0e26d03fd';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV DNA PCR', (select reporting_description from clinlims.test where guid = 'a01d467a-95ae-4b80-b585-d4d0e26d03fd' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a01d467a-95ae-4b80-b585-d4d0e26d03fd';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV DNA PCR', (select name from clinlims.test where guid = 'e6b78371-2749-4870-9af9-6ce75d70cb61' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='e6b78371-2749-4870-9af9-6ce75d70cb61';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV DNA PCR', (select reporting_description from clinlims.test where guid = 'e6b78371-2749-4870-9af9-6ce75d70cb61' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='e6b78371-2749-4870-9af9-6ce75d70cb61';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV Viral Load', (select name from clinlims.test where guid = 'cdbbe306-c27f-44a9-af9f-393e974fa86a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='cdbbe306-c27f-44a9-af9f-393e974fa86a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV Viral Load', (select reporting_description from clinlims.test where guid = 'cdbbe306-c27f-44a9-af9f-393e974fa86a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='cdbbe306-c27f-44a9-af9f-393e974fa86a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'a1f4ac1d-8d6b-45ff-a7ce-59b304ca12d8' ), (select name from clinlims.test where guid = 'a1f4ac1d-8d6b-45ff-a7ce-59b304ca12d8' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a1f4ac1d-8d6b-45ff-a7ce-59b304ca12d8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'a1f4ac1d-8d6b-45ff-a7ce-59b304ca12d8' ), (select reporting_description from clinlims.test where guid = 'a1f4ac1d-8d6b-45ff-a7ce-59b304ca12d8' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a1f4ac1d-8d6b-45ff-a7ce-59b304ca12d8';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '8b68a9d4-1c7f-4b77-bc29-a0f597283d03' ), (select name from clinlims.test where guid = '8b68a9d4-1c7f-4b77-bc29-a0f597283d03' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='8b68a9d4-1c7f-4b77-bc29-a0f597283d03';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '8b68a9d4-1c7f-4b77-bc29-a0f597283d03' ), (select reporting_description from clinlims.test where guid = '8b68a9d4-1c7f-4b77-bc29-a0f597283d03' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='8b68a9d4-1c7f-4b77-bc29-a0f597283d03';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '45210967-661b-4966-88c4-0a9f18621e76' ), (select name from clinlims.test where guid = '45210967-661b-4966-88c4-0a9f18621e76' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='45210967-661b-4966-88c4-0a9f18621e76';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '45210967-661b-4966-88c4-0a9f18621e76' ), (select reporting_description from clinlims.test where guid = '45210967-661b-4966-88c4-0a9f18621e76' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='45210967-661b-4966-88c4-0a9f18621e76';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '05348c58-c10e-4aef-9b46-edcc135791c5' ), (select name from clinlims.test where guid = '05348c58-c10e-4aef-9b46-edcc135791c5' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='05348c58-c10e-4aef-9b46-edcc135791c5';
INSERT INTO localization(  id, description, english, french, lastupdated)
VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '05348c58-c10e-4aef-9b46-edcc135791c5' ), (select reporting_description from clinlims.test where guid = '05348c58-c10e-4aef-9b46-edcc135791c5' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='05348c58-c10e-4aef-9b46-edcc135791c5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'HIV Viral Load', (select name from clinlims.test where guid = '1a82bb14-563b-4003-9c78-c9ab6b9b680e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='1a82bb14-563b-4003-9c78-c9ab6b9b680e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'HIV Viral Load', (select reporting_description from clinlims.test where guid = '1a82bb14-563b-4003-9c78-c9ab6b9b680e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='1a82bb14-563b-4003-9c78-c9ab6b9b680e';
--INSERT INTO localization(  id, description, english, french, lastupdated)
--	VALUES ( nextval('localization_seq'), 'test name', 'Influenza A rt-PCR', (select name from clinlims.test where guid = '5149a0a6-7e0e-4c76-9308-c335c3945a21' ), now());
--update clinlims.test set name_localization_id = lastval() where guid ='5149a0a6-7e0e-4c76-9308-c335c3945a21';
--INSERT INTO localization(  id, description, english, french, lastupdated)
--	VALUES ( nextval('localization_seq'), 'test report name', 'Influenza A rt-PCR', (select reporting_description from clinlims.test where guid = '5149a0a6-7e0e-4c76-9308-c335c3945a21' ), now());
--update clinlims.test set reporting_name_localization_id = lastval() where guid ='5149a0a6-7e0e-4c76-9308-c335c3945a21';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Gastroenteric Pathogens', (select name from clinlims.test where guid = '57317977-61e0-4284-815d-deb71afe4401' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='57317977-61e0-4284-815d-deb71afe4401';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Gastroenteric Pathogens', (select reporting_description from clinlims.test where guid = '57317977-61e0-4284-815d-deb71afe4401' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='57317977-61e0-4284-815d-deb71afe4401';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Repiratory Virus', (select name from clinlims.test where guid = 'bd8bfebf-d92c-4cda-b68a-00d26bcbef44' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='bd8bfebf-d92c-4cda-b68a-00d26bcbef44';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Repiratory Virus', (select reporting_description from clinlims.test where guid = 'bd8bfebf-d92c-4cda-b68a-00d26bcbef44' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='bd8bfebf-d92c-4cda-b68a-00d26bcbef44';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Mycobacteium tuberculosis Drug Resistant', (select name from clinlims.test where guid = '205550ea-bfb4-48bf-8ecb-bdadd1b495bd' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='205550ea-bfb4-48bf-8ecb-bdadd1b495bd';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Mycobacteium tuberculosis Drug Resistant', (select reporting_description from clinlims.test where guid = '205550ea-bfb4-48bf-8ecb-bdadd1b495bd' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='205550ea-bfb4-48bf-8ecb-bdadd1b495bd';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Mycobacteium tuberculosis Drug Resistant', (select name from clinlims.test where guid = '04819bc9-7208-4522-9baa-452603728d6d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='04819bc9-7208-4522-9baa-452603728d6d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Mycobacteium tuberculosis Drug Resistant', (select reporting_description from clinlims.test where guid = '04819bc9-7208-4522-9baa-452603728d6d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='04819bc9-7208-4522-9baa-452603728d6d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '95cdd9fe-6a01-43c5-9912-25358122201d' ), (select name from clinlims.test where guid = '95cdd9fe-6a01-43c5-9912-25358122201d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='95cdd9fe-6a01-43c5-9912-25358122201d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '95cdd9fe-6a01-43c5-9912-25358122201d' ), (select reporting_description from clinlims.test where guid = '95cdd9fe-6a01-43c5-9912-25358122201d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='95cdd9fe-6a01-43c5-9912-25358122201d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '84bed9d9-5da9-41df-9bd6-d32c5b3aabdd' ), (select name from clinlims.test where guid = '84bed9d9-5da9-41df-9bd6-d32c5b3aabdd' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='84bed9d9-5da9-41df-9bd6-d32c5b3aabdd';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '84bed9d9-5da9-41df-9bd6-d32c5b3aabdd' ), (select reporting_description from clinlims.test where guid = '84bed9d9-5da9-41df-9bd6-d32c5b3aabdd' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='84bed9d9-5da9-41df-9bd6-d32c5b3aabdd';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'ed14bbe2-3b53-4bc4-8a71-f81a487ee3c5' ), (select name from clinlims.test where guid = 'ed14bbe2-3b53-4bc4-8a71-f81a487ee3c5' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='ed14bbe2-3b53-4bc4-8a71-f81a487ee3c5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'ed14bbe2-3b53-4bc4-8a71-f81a487ee3c5' ), (select reporting_description from clinlims.test where guid = 'ed14bbe2-3b53-4bc4-8a71-f81a487ee3c5' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='ed14bbe2-3b53-4bc4-8a71-f81a487ee3c5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '03f55673-170b-444a-a5e2-b7b060c15763' ), (select name from clinlims.test where guid = '03f55673-170b-444a-a5e2-b7b060c15763' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='03f55673-170b-444a-a5e2-b7b060c15763';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '03f55673-170b-444a-a5e2-b7b060c15763' ), (select reporting_description from clinlims.test where guid = '03f55673-170b-444a-a5e2-b7b060c15763' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='03f55673-170b-444a-a5e2-b7b060c15763';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CD4 Absolute Count', (select name from clinlims.test where guid = '6b984b82-4c23-43c8-9206-9bf3aef2f4aa' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='6b984b82-4c23-43c8-9206-9bf3aef2f4aa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CD4 Absolute Count', (select reporting_description from clinlims.test where guid = '6b984b82-4c23-43c8-9206-9bf3aef2f4aa' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='6b984b82-4c23-43c8-9206-9bf3aef2f4aa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CD4 % Count', (select name from clinlims.test where guid = '5725e288-fb8d-4c8f-9c3c-bdbaba01c251' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='5725e288-fb8d-4c8f-9c3c-bdbaba01c251';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CD4 % Count', (select reporting_description from clinlims.test where guid = '5725e288-fb8d-4c8f-9c3c-bdbaba01c251' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='5725e288-fb8d-4c8f-9c3c-bdbaba01c251';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Compte des Globules Blancs', (select name from clinlims.test where guid = 'b8dc850f-2b38-4438-8e52-434ebd82911b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='b8dc850f-2b38-4438-8e52-434ebd82911b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Compte des Globules Blancs', (select reporting_description from clinlims.test where guid = 'b8dc850f-2b38-4438-8e52-434ebd82911b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='b8dc850f-2b38-4438-8e52-434ebd82911b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Compte des Globules Rouges', (select name from clinlims.test where guid = 'd71ebe97-3275-4359-a980-461eab85ed21' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d71ebe97-3275-4359-a980-461eab85ed21';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Compte des Globules Rouges', (select reporting_description from clinlims.test where guid = 'd71ebe97-3275-4359-a980-461eab85ed21' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d71ebe97-3275-4359-a980-461eab85ed21';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hemoglobine', (select name from clinlims.test where guid = '3ac8318c-7816-42bf-a429-d2849b117712' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3ac8318c-7816-42bf-a429-d2849b117712';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hemoglobine', (select reporting_description from clinlims.test where guid = '3ac8318c-7816-42bf-a429-d2849b117712' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3ac8318c-7816-42bf-a429-d2849b117712';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hematocrite', (select name from clinlims.test where guid = '9adeaa4c-c8ca-47b8-ab0e-597d7dbb3368' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='9adeaa4c-c8ca-47b8-ab0e-597d7dbb3368';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hematocrite', (select reporting_description from clinlims.test where guid = '9adeaa4c-c8ca-47b8-ab0e-597d7dbb3368' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='9adeaa4c-c8ca-47b8-ab0e-597d7dbb3368';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'VGM', (select name from clinlims.test where guid = 'b1bb4aca-1f0b-44c5-9570-6daf699739bb' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='b1bb4aca-1f0b-44c5-9570-6daf699739bb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'VGM', (select reporting_description from clinlims.test where guid = 'b1bb4aca-1f0b-44c5-9570-6daf699739bb' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='b1bb4aca-1f0b-44c5-9570-6daf699739bb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'TCMH', (select name from clinlims.test where guid = '9e8d5064-d9db-449c-b257-4f76b0fc9745' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='9e8d5064-d9db-449c-b257-4f76b0fc9745';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'TCMH', (select reporting_description from clinlims.test where guid = '9e8d5064-d9db-449c-b257-4f76b0fc9745' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='9e8d5064-d9db-449c-b257-4f76b0fc9745';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CCMH', (select name from clinlims.test where guid = '3ac4256d-81ab-44cf-95d4-1e77f162b4ea' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3ac4256d-81ab-44cf-95d4-1e77f162b4ea';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CCMH', (select reporting_description from clinlims.test where guid = '3ac4256d-81ab-44cf-95d4-1e77f162b4ea' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3ac4256d-81ab-44cf-95d4-1e77f162b4ea';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Plaquettes', (select name from clinlims.test where guid = '2a05a98f-8455-4b8e-9e1c-8d9b3676b98e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='2a05a98f-8455-4b8e-9e1c-8d9b3676b98e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Plaquettes', (select reporting_description from clinlims.test where guid = '2a05a98f-8455-4b8e-9e1c-8d9b3676b98e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='2a05a98f-8455-4b8e-9e1c-8d9b3676b98e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Neutrophiles', (select name from clinlims.test where guid = '6cdb44b5-e62e-4254-ae51-f5ca2ae12c25' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='6cdb44b5-e62e-4254-ae51-f5ca2ae12c25';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Neutrophiles', (select reporting_description from clinlims.test where guid = '6cdb44b5-e62e-4254-ae51-f5ca2ae12c25' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='6cdb44b5-e62e-4254-ae51-f5ca2ae12c25';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Lymphocytes', (select name from clinlims.test where guid = '57309e88-8b68-4d5a-8642-59e6b2339461' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='57309e88-8b68-4d5a-8642-59e6b2339461';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Lymphocytes', (select reporting_description from clinlims.test where guid = '57309e88-8b68-4d5a-8642-59e6b2339461' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='57309e88-8b68-4d5a-8642-59e6b2339461';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Mixtes', (select name from clinlims.test where guid = '298af222-035d-4b44-b68e-ac183c51c46c' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='298af222-035d-4b44-b68e-ac183c51c46c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Mixtes', (select reporting_description from clinlims.test where guid = '298af222-035d-4b44-b68e-ac183c51c46c' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='298af222-035d-4b44-b68e-ac183c51c46c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '087d33c1-37c2-4442-80e0-d8e054f0ad21' ), (select name from clinlims.test where guid = '087d33c1-37c2-4442-80e0-d8e054f0ad21' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='087d33c1-37c2-4442-80e0-d8e054f0ad21';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '087d33c1-37c2-4442-80e0-d8e054f0ad21' ), (select reporting_description from clinlims.test where guid = '087d33c1-37c2-4442-80e0-d8e054f0ad21' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='087d33c1-37c2-4442-80e0-d8e054f0ad21';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '00e4ba7a-dbc7-4ace-b8c8-18ddd7f41f0f' ), (select name from clinlims.test where guid = '00e4ba7a-dbc7-4ace-b8c8-18ddd7f41f0f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='00e4ba7a-dbc7-4ace-b8c8-18ddd7f41f0f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '00e4ba7a-dbc7-4ace-b8c8-18ddd7f41f0f' ), (select reporting_description from clinlims.test where guid = '00e4ba7a-dbc7-4ace-b8c8-18ddd7f41f0f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='00e4ba7a-dbc7-4ace-b8c8-18ddd7f41f0f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '1b5132af-2ab1-4842-9bf0-4d9b44a2f92f' ), (select name from clinlims.test where guid = '1b5132af-2ab1-4842-9bf0-4d9b44a2f92f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='1b5132af-2ab1-4842-9bf0-4d9b44a2f92f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '1b5132af-2ab1-4842-9bf0-4d9b44a2f92f' ), (select reporting_description from clinlims.test where guid = '1b5132af-2ab1-4842-9bf0-4d9b44a2f92f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='1b5132af-2ab1-4842-9bf0-4d9b44a2f92f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Gram Stain', (select name from clinlims.test where guid = '27404560-a8d1-415e-923f-d12cc43b7ca1' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='27404560-a8d1-415e-923f-d12cc43b7ca1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Gram Stain', (select reporting_description from clinlims.test where guid = '27404560-a8d1-415e-923f-d12cc43b7ca1' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='27404560-a8d1-415e-923f-d12cc43b7ca1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Gram Stain', (select name from clinlims.test where guid = '62cb7ea4-7e99-4027-81a8-1bf7b490e80e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='62cb7ea4-7e99-4027-81a8-1bf7b490e80e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Gram Stain', (select reporting_description from clinlims.test where guid = '62cb7ea4-7e99-4027-81a8-1bf7b490e80e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='62cb7ea4-7e99-4027-81a8-1bf7b490e80e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Gram Stain', (select name from clinlims.test where guid = 'da092880-8826-471b-970e-cf754a992c9a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='da092880-8826-471b-970e-cf754a992c9a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Gram Stain', (select reporting_description from clinlims.test where guid = 'da092880-8826-471b-970e-cf754a992c9a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='da092880-8826-471b-970e-cf754a992c9a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Gram Stain', (select name from clinlims.test where guid = 'b0e89da1-4594-46d4-a04b-4be7468660dc' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='b0e89da1-4594-46d4-a04b-4be7468660dc';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Gram Stain', (select reporting_description from clinlims.test where guid = 'b0e89da1-4594-46d4-a04b-4be7468660dc' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='b0e89da1-4594-46d4-a04b-4be7468660dc';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Gramm Stain', (select name from clinlims.test where guid = '4e9c5384-2b5c-4f2e-bf65-f7274f924814' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='4e9c5384-2b5c-4f2e-bf65-f7274f924814';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Gramm Stain', (select reporting_description from clinlims.test where guid = '4e9c5384-2b5c-4f2e-bf65-f7274f924814' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='4e9c5384-2b5c-4f2e-bf65-f7274f924814';
--INSERT INTO localization(  id, description, english, french, lastupdated)
--	VALUES ( nextval('localization_seq'), 'test name', 'Leptospirosis', (select name from clinlims.test where guid = '511c40fe-8da6-45cb-b7be-85e67e72de4c' ), now());
--update clinlims.test set name_localization_id = lastval() where guid ='511c40fe-8da6-45cb-b7be-85e67e72de4c';
--INSERT INTO localization(  id, description, english, french, lastupdated)
--	VALUES ( nextval('localization_seq'), 'test report name', 'Leptospirosis', (select reporting_description from clinlims.test where guid = '511c40fe-8da6-45cb-b7be-85e67e72de4c' ), now());
--update clinlims.test set reporting_name_localization_id = lastval() where guid ='511c40fe-8da6-45cb-b7be-85e67e72de4c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '28ad9618-367a-40c2-bf85-656766652f3c' ), (select name from clinlims.test where guid = '28ad9618-367a-40c2-bf85-656766652f3c' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='28ad9618-367a-40c2-bf85-656766652f3c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '28ad9618-367a-40c2-bf85-656766652f3c' ), (select reporting_description from clinlims.test where guid = '28ad9618-367a-40c2-bf85-656766652f3c' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='28ad9618-367a-40c2-bf85-656766652f3c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '27722696-f4a3-473d-b6c5-92bf317de3f8' ), (select name from clinlims.test where guid = '27722696-f4a3-473d-b6c5-92bf317de3f8' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='27722696-f4a3-473d-b6c5-92bf317de3f8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '27722696-f4a3-473d-b6c5-92bf317de3f8' ), (select reporting_description from clinlims.test where guid = '27722696-f4a3-473d-b6c5-92bf317de3f8' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='27722696-f4a3-473d-b6c5-92bf317de3f8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'ddddcc73-1b6a-49fc-83ca-0025bbc4455e' ), (select name from clinlims.test where guid = 'ddddcc73-1b6a-49fc-83ca-0025bbc4455e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='ddddcc73-1b6a-49fc-83ca-0025bbc4455e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'ddddcc73-1b6a-49fc-83ca-0025bbc4455e' ), (select reporting_description from clinlims.test where guid = 'ddddcc73-1b6a-49fc-83ca-0025bbc4455e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='ddddcc73-1b6a-49fc-83ca-0025bbc4455e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'f2be901c-0d42-4ac8-9f98-342091af3c94' ), (select name from clinlims.test where guid = 'f2be901c-0d42-4ac8-9f98-342091af3c94' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='f2be901c-0d42-4ac8-9f98-342091af3c94';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'f2be901c-0d42-4ac8-9f98-342091af3c94' ), (select reporting_description from clinlims.test where guid = 'f2be901c-0d42-4ac8-9f98-342091af3c94' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='f2be901c-0d42-4ac8-9f98-342091af3c94';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Leptospirosis', (select name from clinlims.test where guid = 'dcc367cf-eb75-42dc-bfb6-65ea43569a4d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='dcc367cf-eb75-42dc-bfb6-65ea43569a4d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Leptospirosis', (select reporting_description from clinlims.test where guid = 'dcc367cf-eb75-42dc-bfb6-65ea43569a4d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='dcc367cf-eb75-42dc-bfb6-65ea43569a4d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Leptospirosis', (select name from clinlims.test where guid = '6b0c0119-314f-4e77-98d2-f3f210606daa' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='6b0c0119-314f-4e77-98d2-f3f210606daa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Leptospirosis', (select reporting_description from clinlims.test where guid = '6b0c0119-314f-4e77-98d2-f3f210606daa' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='6b0c0119-314f-4e77-98d2-f3f210606daa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '9d8035cd-7d92-4419-949f-03ec08ec3b9a' ), (select name from clinlims.test where guid = '9d8035cd-7d92-4419-949f-03ec08ec3b9a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='9d8035cd-7d92-4419-949f-03ec08ec3b9a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '9d8035cd-7d92-4419-949f-03ec08ec3b9a' ), (select reporting_description from clinlims.test where guid = '9d8035cd-7d92-4419-949f-03ec08ec3b9a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='9d8035cd-7d92-4419-949f-03ec08ec3b9a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Leptospirosis', (select name from clinlims.test where guid = '3e477a18-1e1c-4c9f-bb8d-8f2b72100b3f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3e477a18-1e1c-4c9f-bb8d-8f2b72100b3f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Leptospirosis', (select reporting_description from clinlims.test where guid = '3e477a18-1e1c-4c9f-bb8d-8f2b72100b3f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3e477a18-1e1c-4c9f-bb8d-8f2b72100b3f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Hemoculture', (select name from clinlims.test where guid = 'aed757d4-0589-47ab-801f-38b6a6e45b88' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='aed757d4-0589-47ab-801f-38b6a6e45b88';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Hemoculture', (select reporting_description from clinlims.test where guid = 'aed757d4-0589-47ab-801f-38b6a6e45b88' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='aed757d4-0589-47ab-801f-38b6a6e45b88';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Salmonela', (select name from clinlims.test where guid = 'ed963ac1-d329-416f-8dd7-e97428e27fe4' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='ed963ac1-d329-416f-8dd7-e97428e27fe4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Salmonela', (select reporting_description from clinlims.test where guid = 'ed963ac1-d329-416f-8dd7-e97428e27fe4' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='ed963ac1-d329-416f-8dd7-e97428e27fe4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Salmonela', (select name from clinlims.test where guid = '08581fac-770c-42d2-a040-f9c9914a779f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='08581fac-770c-42d2-a040-f9c9914a779f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Salmonela', (select reporting_description from clinlims.test where guid = '08581fac-770c-42d2-a040-f9c9914a779f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='08581fac-770c-42d2-a040-f9c9914a779f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CSF culture', (select name from clinlims.test where guid = 'd63c7606-a245-443c-aa92-b134ec5cb3bf' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d63c7606-a245-443c-aa92-b134ec5cb3bf';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CSF culture', (select reporting_description from clinlims.test where guid = 'd63c7606-a245-443c-aa92-b134ec5cb3bf' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d63c7606-a245-443c-aa92-b134ec5cb3bf';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'CSF Rapid Test', (select name from clinlims.test where guid = '53b5c75c-ab16-4c9d-a5f8-bbb17e9ce6ef' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='53b5c75c-ab16-4c9d-a5f8-bbb17e9ce6ef';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'CSF Rapid Test', (select reporting_description from clinlims.test where guid = '53b5c75c-ab16-4c9d-a5f8-bbb17e9ce6ef' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='53b5c75c-ab16-4c9d-a5f8-bbb17e9ce6ef';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Salmonela', (select name from clinlims.test where guid = 'b7f0c18d-012e-48d9-ab04-9218ed71b0ef' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='b7f0c18d-012e-48d9-ab04-9218ed71b0ef';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Salmonela', (select reporting_description from clinlims.test where guid = 'b7f0c18d-012e-48d9-ab04-9218ed71b0ef' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='b7f0c18d-012e-48d9-ab04-9218ed71b0ef';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Shigella', (select name from clinlims.test where guid = 'fd66e042-9b6d-4158-bb4b-244b0acf1100' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='fd66e042-9b6d-4158-bb4b-244b0acf1100';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Shigella', (select reporting_description from clinlims.test where guid = 'fd66e042-9b6d-4158-bb4b-244b0acf1100' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='fd66e042-9b6d-4158-bb4b-244b0acf1100';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Detection of Vibrio Cholerae', (select name from clinlims.test where guid = 'bb36ce3d-e699-4804-9b6d-acd911975d9b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='bb36ce3d-e699-4804-9b6d-acd911975d9b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Detection of Vibrio Cholerae', (select reporting_description from clinlims.test where guid = 'bb36ce3d-e699-4804-9b6d-acd911975d9b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='bb36ce3d-e699-4804-9b6d-acd911975d9b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Detection of Corynebacterium Diphteriae', (select name from clinlims.test where guid = '08db9ce2-4837-4af7-b1f0-82a3b81dc14a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='08db9ce2-4837-4af7-b1f0-82a3b81dc14a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Detection of Corynebacterium Diphteriae', (select reporting_description from clinlims.test where guid = '08db9ce2-4837-4af7-b1f0-82a3b81dc14a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='08db9ce2-4837-4af7-b1f0-82a3b81dc14a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Detection of B. Pertussis', (select name from clinlims.test where guid = 'c54cadd2-4595-4695-b3f8-a9b9b5238e54' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='c54cadd2-4595-4695-b3f8-a9b9b5238e54';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Detection of B. Pertussis', (select reporting_description from clinlims.test where guid = 'c54cadd2-4595-4695-b3f8-a9b9b5238e54' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='c54cadd2-4595-4695-b3f8-a9b9b5238e54';
--INSERT INTO localization(  id, description, english, french, lastupdated)
--	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'ae89c46b-62ca-4d2c-bb8a-d2735ad885a9' ), (select name from clinlims.test where guid = 'ae89c46b-62ca-4d2c-bb8a-d2735ad885a9' ), now());
--update clinlims.test set name_localization_id = lastval() where guid ='ae89c46b-62ca-4d2c-bb8a-d2735ad885a9';
--INSERT INTO localization(  id, description, english, french, lastupdated)
--	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'ae89c46b-62ca-4d2c-bb8a-d2735ad885a9' ), (select reporting_description from clinlims.test where guid = 'ae89c46b-62ca-4d2c-bb8a-d2735ad885a9' ), now());
--update clinlims.test set reporting_name_localization_id = lastval() where guid ='ae89c46b-62ca-4d2c-bb8a-d2735ad885a9';
--INSERT INTO localization(  id, description, english, french, lastupdated)
--	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '2678f166-4fef-4bde-a25a-b0bd2891fe4c' ), (select name from clinlims.test where guid = '2678f166-4fef-4bde-a25a-b0bd2891fe4c' ), now());
--update clinlims.test set name_localization_id = lastval() where guid ='2678f166-4fef-4bde-a25a-b0bd2891fe4c';
--INSERT INTO localization(  id, description, english, french, lastupdated)
--	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '2678f166-4fef-4bde-a25a-b0bd2891fe4c' ), (select reporting_description from clinlims.test where guid = '2678f166-4fef-4bde-a25a-b0bd2891fe4c' ), now());
--update clinlims.test set reporting_name_localization_id = lastval() where guid ='2678f166-4fef-4bde-a25a-b0bd2891fe4c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '6428c31f-a02b-46b0-974e-3e4bb8f385bb' ), (select name from clinlims.test where guid = '6428c31f-a02b-46b0-974e-3e4bb8f385bb' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='6428c31f-a02b-46b0-974e-3e4bb8f385bb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '6428c31f-a02b-46b0-974e-3e4bb8f385bb' ), (select reporting_description from clinlims.test where guid = '6428c31f-a02b-46b0-974e-3e4bb8f385bb' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='6428c31f-a02b-46b0-974e-3e4bb8f385bb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'daea2da7-d16b-48c1-8eb6-d0eaa81fba21' ), (select name from clinlims.test where guid = 'daea2da7-d16b-48c1-8eb6-d0eaa81fba21' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='daea2da7-d16b-48c1-8eb6-d0eaa81fba21';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'daea2da7-d16b-48c1-8eb6-d0eaa81fba21' ), (select reporting_description from clinlims.test where guid = 'daea2da7-d16b-48c1-8eb6-d0eaa81fba21' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='daea2da7-d16b-48c1-8eb6-d0eaa81fba21';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '806d1f8e-3619-44b5-bdb3-8924487c0253' ), (select name from clinlims.test where guid = '806d1f8e-3619-44b5-bdb3-8924487c0253' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='806d1f8e-3619-44b5-bdb3-8924487c0253';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '806d1f8e-3619-44b5-bdb3-8924487c0253' ), (select reporting_description from clinlims.test where guid = '806d1f8e-3619-44b5-bdb3-8924487c0253' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='806d1f8e-3619-44b5-bdb3-8924487c0253';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '844fcef4-9b5d-4898-9ac3-788d5e804382' ), (select name from clinlims.test where guid = '844fcef4-9b5d-4898-9ac3-788d5e804382' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='844fcef4-9b5d-4898-9ac3-788d5e804382';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '844fcef4-9b5d-4898-9ac3-788d5e804382' ), (select reporting_description from clinlims.test where guid = '844fcef4-9b5d-4898-9ac3-788d5e804382' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='844fcef4-9b5d-4898-9ac3-788d5e804382';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '8905441c-6f99-429e-88f3-90db2e00fc15' ), (select name from clinlims.test where guid = '8905441c-6f99-429e-88f3-90db2e00fc15' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='8905441c-6f99-429e-88f3-90db2e00fc15';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '8905441c-6f99-429e-88f3-90db2e00fc15' ), (select reporting_description from clinlims.test where guid = '8905441c-6f99-429e-88f3-90db2e00fc15' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='8905441c-6f99-429e-88f3-90db2e00fc15';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '85beb0c2-d5f1-4bf6-bb8e-78c5a3d934ce' ), (select name from clinlims.test where guid = '85beb0c2-d5f1-4bf6-bb8e-78c5a3d934ce' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='85beb0c2-d5f1-4bf6-bb8e-78c5a3d934ce';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '85beb0c2-d5f1-4bf6-bb8e-78c5a3d934ce' ), (select reporting_description from clinlims.test where guid = '85beb0c2-d5f1-4bf6-bb8e-78c5a3d934ce' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='85beb0c2-d5f1-4bf6-bb8e-78c5a3d934ce';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '6e4b6ae5-6f6a-4a97-88a1-dc4562c1221d' ), (select name from clinlims.test where guid = '6e4b6ae5-6f6a-4a97-88a1-dc4562c1221d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='6e4b6ae5-6f6a-4a97-88a1-dc4562c1221d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '6e4b6ae5-6f6a-4a97-88a1-dc4562c1221d' ), (select reporting_description from clinlims.test where guid = '6e4b6ae5-6f6a-4a97-88a1-dc4562c1221d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='6e4b6ae5-6f6a-4a97-88a1-dc4562c1221d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '3a469d4e-d84f-4ef7-9ff5-76cd2f525f30' ), (select name from clinlims.test where guid = '3a469d4e-d84f-4ef7-9ff5-76cd2f525f30' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3a469d4e-d84f-4ef7-9ff5-76cd2f525f30';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '3a469d4e-d84f-4ef7-9ff5-76cd2f525f30' ), (select reporting_description from clinlims.test where guid = '3a469d4e-d84f-4ef7-9ff5-76cd2f525f30' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3a469d4e-d84f-4ef7-9ff5-76cd2f525f30';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'f81bb72a-c4fe-4106-a006-147eb16e52af' ), (select name from clinlims.test where guid = 'f81bb72a-c4fe-4106-a006-147eb16e52af' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='f81bb72a-c4fe-4106-a006-147eb16e52af';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'f81bb72a-c4fe-4106-a006-147eb16e52af' ), (select reporting_description from clinlims.test where guid = 'f81bb72a-c4fe-4106-a006-147eb16e52af' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='f81bb72a-c4fe-4106-a006-147eb16e52af';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'b8c6fc2c-2f90-4e38-b8c0-682d48b1a3e5' ), (select name from clinlims.test where guid = 'b8c6fc2c-2f90-4e38-b8c0-682d48b1a3e5' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='b8c6fc2c-2f90-4e38-b8c0-682d48b1a3e5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'b8c6fc2c-2f90-4e38-b8c0-682d48b1a3e5' ), (select reporting_description from clinlims.test where guid = 'b8c6fc2c-2f90-4e38-b8c0-682d48b1a3e5' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='b8c6fc2c-2f90-4e38-b8c0-682d48b1a3e5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'a0b394c5-395b-478c-a0b6-e0b890e6697e' ), (select name from clinlims.test where guid = 'a0b394c5-395b-478c-a0b6-e0b890e6697e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a0b394c5-395b-478c-a0b6-e0b890e6697e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'a0b394c5-395b-478c-a0b6-e0b890e6697e' ), (select reporting_description from clinlims.test where guid = 'a0b394c5-395b-478c-a0b6-e0b890e6697e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a0b394c5-395b-478c-a0b6-e0b890e6697e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'cbb8ff4a-8682-4484-a38e-7849e70b7b99' ), (select name from clinlims.test where guid = 'cbb8ff4a-8682-4484-a38e-7849e70b7b99' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='cbb8ff4a-8682-4484-a38e-7849e70b7b99';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'cbb8ff4a-8682-4484-a38e-7849e70b7b99' ), (select reporting_description from clinlims.test where guid = 'cbb8ff4a-8682-4484-a38e-7849e70b7b99' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='cbb8ff4a-8682-4484-a38e-7849e70b7b99';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '2c452003-fec5-419a-81c8-2ff647acab4f' ), (select name from clinlims.test where guid = '2c452003-fec5-419a-81c8-2ff647acab4f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='2c452003-fec5-419a-81c8-2ff647acab4f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '2c452003-fec5-419a-81c8-2ff647acab4f' ), (select reporting_description from clinlims.test where guid = '2c452003-fec5-419a-81c8-2ff647acab4f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='2c452003-fec5-419a-81c8-2ff647acab4f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'da4d865e-c98c-41cf-b638-c0c5d151539e' ), (select name from clinlims.test where guid = 'da4d865e-c98c-41cf-b638-c0c5d151539e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='da4d865e-c98c-41cf-b638-c0c5d151539e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'da4d865e-c98c-41cf-b638-c0c5d151539e' ), (select reporting_description from clinlims.test where guid = 'da4d865e-c98c-41cf-b638-c0c5d151539e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='da4d865e-c98c-41cf-b638-c0c5d151539e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '57c96193-5390-46d7-a3ab-02a7268dc057' ), (select name from clinlims.test where guid = '57c96193-5390-46d7-a3ab-02a7268dc057' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='57c96193-5390-46d7-a3ab-02a7268dc057';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '57c96193-5390-46d7-a3ab-02a7268dc057' ), (select reporting_description from clinlims.test where guid = '57c96193-5390-46d7-a3ab-02a7268dc057' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='57c96193-5390-46d7-a3ab-02a7268dc057';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'f35206a2-90c1-4226-a8c3-e16b6ddae583' ), (select name from clinlims.test where guid = 'f35206a2-90c1-4226-a8c3-e16b6ddae583' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='f35206a2-90c1-4226-a8c3-e16b6ddae583';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'f35206a2-90c1-4226-a8c3-e16b6ddae583' ), (select reporting_description from clinlims.test where guid = 'f35206a2-90c1-4226-a8c3-e16b6ddae583' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='f35206a2-90c1-4226-a8c3-e16b6ddae583';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '3baf17b4-e49c-410e-922a-90730da9b2e2' ), (select name from clinlims.test where guid = '3baf17b4-e49c-410e-922a-90730da9b2e2' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3baf17b4-e49c-410e-922a-90730da9b2e2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '3baf17b4-e49c-410e-922a-90730da9b2e2' ), (select reporting_description from clinlims.test where guid = '3baf17b4-e49c-410e-922a-90730da9b2e2' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3baf17b4-e49c-410e-922a-90730da9b2e2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '378e5817-4c08-4006-97dc-ab45fc6a8526' ), (select name from clinlims.test where guid = '378e5817-4c08-4006-97dc-ab45fc6a8526' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='378e5817-4c08-4006-97dc-ab45fc6a8526';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '378e5817-4c08-4006-97dc-ab45fc6a8526' ), (select reporting_description from clinlims.test where guid = '378e5817-4c08-4006-97dc-ab45fc6a8526' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='378e5817-4c08-4006-97dc-ab45fc6a8526';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '08986f8f-4e36-49b9-bb0f-e0391086009f' ), (select name from clinlims.test where guid = '08986f8f-4e36-49b9-bb0f-e0391086009f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='08986f8f-4e36-49b9-bb0f-e0391086009f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '08986f8f-4e36-49b9-bb0f-e0391086009f' ), (select reporting_description from clinlims.test where guid = '08986f8f-4e36-49b9-bb0f-e0391086009f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='08986f8f-4e36-49b9-bb0f-e0391086009f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '286b4ed9-2fe3-4c4d-b9cb-dd823e2ce63e' ), (select name from clinlims.test where guid = '286b4ed9-2fe3-4c4d-b9cb-dd823e2ce63e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='286b4ed9-2fe3-4c4d-b9cb-dd823e2ce63e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '286b4ed9-2fe3-4c4d-b9cb-dd823e2ce63e' ), (select reporting_description from clinlims.test where guid = '286b4ed9-2fe3-4c4d-b9cb-dd823e2ce63e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='286b4ed9-2fe3-4c4d-b9cb-dd823e2ce63e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'f56ed65c-0f23-40cc-bb8a-54e298353ec0' ), (select name from clinlims.test where guid = 'f56ed65c-0f23-40cc-bb8a-54e298353ec0' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='f56ed65c-0f23-40cc-bb8a-54e298353ec0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'f56ed65c-0f23-40cc-bb8a-54e298353ec0' ), (select reporting_description from clinlims.test where guid = 'f56ed65c-0f23-40cc-bb8a-54e298353ec0' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='f56ed65c-0f23-40cc-bb8a-54e298353ec0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'fcefe8b2-99aa-4c3a-babb-f84a9cb46392' ), (select name from clinlims.test where guid = 'fcefe8b2-99aa-4c3a-babb-f84a9cb46392' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='fcefe8b2-99aa-4c3a-babb-f84a9cb46392';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'fcefe8b2-99aa-4c3a-babb-f84a9cb46392' ), (select reporting_description from clinlims.test where guid = 'fcefe8b2-99aa-4c3a-babb-f84a9cb46392' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='fcefe8b2-99aa-4c3a-babb-f84a9cb46392';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '84d4f8b4-74e7-4b9d-988e-cb9ead3d3fca' ), (select name from clinlims.test where guid = '84d4f8b4-74e7-4b9d-988e-cb9ead3d3fca' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='84d4f8b4-74e7-4b9d-988e-cb9ead3d3fca';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '84d4f8b4-74e7-4b9d-988e-cb9ead3d3fca' ), (select reporting_description from clinlims.test where guid = '84d4f8b4-74e7-4b9d-988e-cb9ead3d3fca' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='84d4f8b4-74e7-4b9d-988e-cb9ead3d3fca';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'a33258ea-3c8d-40bd-b0cb-cebc4adab252' ), (select name from clinlims.test where guid = 'a33258ea-3c8d-40bd-b0cb-cebc4adab252' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a33258ea-3c8d-40bd-b0cb-cebc4adab252';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'a33258ea-3c8d-40bd-b0cb-cebc4adab252' ), (select reporting_description from clinlims.test where guid = 'a33258ea-3c8d-40bd-b0cb-cebc4adab252' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a33258ea-3c8d-40bd-b0cb-cebc4adab252';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '076aca08-ce92-421d-b47a-807776893395' ), (select name from clinlims.test where guid = '076aca08-ce92-421d-b47a-807776893395' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='076aca08-ce92-421d-b47a-807776893395';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '076aca08-ce92-421d-b47a-807776893395' ), (select reporting_description from clinlims.test where guid = '076aca08-ce92-421d-b47a-807776893395' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='076aca08-ce92-421d-b47a-807776893395';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '8d35ebdb-29fd-4d80-aa0a-2fe0433d2e26' ), (select name from clinlims.test where guid = '8d35ebdb-29fd-4d80-aa0a-2fe0433d2e26' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='8d35ebdb-29fd-4d80-aa0a-2fe0433d2e26';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '8d35ebdb-29fd-4d80-aa0a-2fe0433d2e26' ), (select reporting_description from clinlims.test where guid = '8d35ebdb-29fd-4d80-aa0a-2fe0433d2e26' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='8d35ebdb-29fd-4d80-aa0a-2fe0433d2e26';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '3050d04c-ab22-465f-9ced-ad1659369523' ), (select name from clinlims.test where guid = '3050d04c-ab22-465f-9ced-ad1659369523' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3050d04c-ab22-465f-9ced-ad1659369523';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '3050d04c-ab22-465f-9ced-ad1659369523' ), (select reporting_description from clinlims.test where guid = '3050d04c-ab22-465f-9ced-ad1659369523' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3050d04c-ab22-465f-9ced-ad1659369523';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '38500ab0-8f9e-44a8-a77a-f467c2be04bc' ), (select name from clinlims.test where guid = '38500ab0-8f9e-44a8-a77a-f467c2be04bc' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='38500ab0-8f9e-44a8-a77a-f467c2be04bc';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '38500ab0-8f9e-44a8-a77a-f467c2be04bc' ), (select reporting_description from clinlims.test where guid = '38500ab0-8f9e-44a8-a77a-f467c2be04bc' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='38500ab0-8f9e-44a8-a77a-f467c2be04bc';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'f4609817-58f3-4da0-8daf-d7425f4e5cac' ), (select name from clinlims.test where guid = 'f4609817-58f3-4da0-8daf-d7425f4e5cac' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='f4609817-58f3-4da0-8daf-d7425f4e5cac';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'f4609817-58f3-4da0-8daf-d7425f4e5cac' ), (select reporting_description from clinlims.test where guid = 'f4609817-58f3-4da0-8daf-d7425f4e5cac' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='f4609817-58f3-4da0-8daf-d7425f4e5cac';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '8cc38196-7d25-4529-bbb6-2b2435e8e753' ), (select name from clinlims.test where guid = '8cc38196-7d25-4529-bbb6-2b2435e8e753' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='8cc38196-7d25-4529-bbb6-2b2435e8e753';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '8cc38196-7d25-4529-bbb6-2b2435e8e753' ), (select reporting_description from clinlims.test where guid = '8cc38196-7d25-4529-bbb6-2b2435e8e753' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='8cc38196-7d25-4529-bbb6-2b2435e8e753';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '3bc3b4b1-bcc8-4132-9460-6e77d36ffa0b' ), (select name from clinlims.test where guid = '3bc3b4b1-bcc8-4132-9460-6e77d36ffa0b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3bc3b4b1-bcc8-4132-9460-6e77d36ffa0b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '3bc3b4b1-bcc8-4132-9460-6e77d36ffa0b' ), (select reporting_description from clinlims.test where guid = '3bc3b4b1-bcc8-4132-9460-6e77d36ffa0b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3bc3b4b1-bcc8-4132-9460-6e77d36ffa0b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '630b6ca5-097f-4b06-93d8-c9c97c66fa53' ), (select name from clinlims.test where guid = '630b6ca5-097f-4b06-93d8-c9c97c66fa53' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='630b6ca5-097f-4b06-93d8-c9c97c66fa53';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '630b6ca5-097f-4b06-93d8-c9c97c66fa53' ), (select reporting_description from clinlims.test where guid = '630b6ca5-097f-4b06-93d8-c9c97c66fa53' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='630b6ca5-097f-4b06-93d8-c9c97c66fa53';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '72fd780a-9523-4d3a-be0c-ef55da931a63' ), (select name from clinlims.test where guid = '72fd780a-9523-4d3a-be0c-ef55da931a63' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='72fd780a-9523-4d3a-be0c-ef55da931a63';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '72fd780a-9523-4d3a-be0c-ef55da931a63' ), (select reporting_description from clinlims.test where guid = '72fd780a-9523-4d3a-be0c-ef55da931a63' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='72fd780a-9523-4d3a-be0c-ef55da931a63';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '9e494cee-6202-45e4-acd4-c2238c403476' ), (select name from clinlims.test where guid = '9e494cee-6202-45e4-acd4-c2238c403476' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='9e494cee-6202-45e4-acd4-c2238c403476';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '9e494cee-6202-45e4-acd4-c2238c403476' ), (select reporting_description from clinlims.test where guid = '9e494cee-6202-45e4-acd4-c2238c403476' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='9e494cee-6202-45e4-acd4-c2238c403476';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'c24bf46c-388e-4cfd-a6b5-747cdda6e95f' ), (select name from clinlims.test where guid = 'c24bf46c-388e-4cfd-a6b5-747cdda6e95f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='c24bf46c-388e-4cfd-a6b5-747cdda6e95f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'c24bf46c-388e-4cfd-a6b5-747cdda6e95f' ), (select reporting_description from clinlims.test where guid = 'c24bf46c-388e-4cfd-a6b5-747cdda6e95f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='c24bf46c-388e-4cfd-a6b5-747cdda6e95f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '5ab0472a-a1bc-4955-b1a6-57437492c640' ), (select name from clinlims.test where guid = '5ab0472a-a1bc-4955-b1a6-57437492c640' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='5ab0472a-a1bc-4955-b1a6-57437492c640';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '5ab0472a-a1bc-4955-b1a6-57437492c640' ), (select reporting_description from clinlims.test where guid = '5ab0472a-a1bc-4955-b1a6-57437492c640' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='5ab0472a-a1bc-4955-b1a6-57437492c640';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '09e19be3-71f5-494f-bc3e-2013153dc141' ), (select name from clinlims.test where guid = '09e19be3-71f5-494f-bc3e-2013153dc141' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='09e19be3-71f5-494f-bc3e-2013153dc141';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '09e19be3-71f5-494f-bc3e-2013153dc141' ), (select reporting_description from clinlims.test where guid = '09e19be3-71f5-494f-bc3e-2013153dc141' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='09e19be3-71f5-494f-bc3e-2013153dc141';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '0d20ca36-2543-4be3-bd1e-a8fbcf3cad5f' ), (select name from clinlims.test where guid = '0d20ca36-2543-4be3-bd1e-a8fbcf3cad5f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='0d20ca36-2543-4be3-bd1e-a8fbcf3cad5f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '0d20ca36-2543-4be3-bd1e-a8fbcf3cad5f' ), (select reporting_description from clinlims.test where guid = '0d20ca36-2543-4be3-bd1e-a8fbcf3cad5f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='0d20ca36-2543-4be3-bd1e-a8fbcf3cad5f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'f70aa569-9a97-4497-a702-74c9db4849a9' ), (select name from clinlims.test where guid = 'f70aa569-9a97-4497-a702-74c9db4849a9' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='f70aa569-9a97-4497-a702-74c9db4849a9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'f70aa569-9a97-4497-a702-74c9db4849a9' ), (select reporting_description from clinlims.test where guid = 'f70aa569-9a97-4497-a702-74c9db4849a9' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='f70aa569-9a97-4497-a702-74c9db4849a9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'db3792c6-7f49-4527-b7ca-110546a81cb0' ), (select name from clinlims.test where guid = 'db3792c6-7f49-4527-b7ca-110546a81cb0' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='db3792c6-7f49-4527-b7ca-110546a81cb0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'db3792c6-7f49-4527-b7ca-110546a81cb0' ), (select reporting_description from clinlims.test where guid = 'db3792c6-7f49-4527-b7ca-110546a81cb0' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='db3792c6-7f49-4527-b7ca-110546a81cb0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '08bb0159-0533-47f0-801c-6cc51d7b9415' ), (select name from clinlims.test where guid = '08bb0159-0533-47f0-801c-6cc51d7b9415' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='08bb0159-0533-47f0-801c-6cc51d7b9415';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '08bb0159-0533-47f0-801c-6cc51d7b9415' ), (select reporting_description from clinlims.test where guid = '08bb0159-0533-47f0-801c-6cc51d7b9415' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='08bb0159-0533-47f0-801c-6cc51d7b9415';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'a7268f16-0f7c-4852-9ac7-257fa6622585' ), (select name from clinlims.test where guid = 'a7268f16-0f7c-4852-9ac7-257fa6622585' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a7268f16-0f7c-4852-9ac7-257fa6622585';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'a7268f16-0f7c-4852-9ac7-257fa6622585' ), (select reporting_description from clinlims.test where guid = 'a7268f16-0f7c-4852-9ac7-257fa6622585' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a7268f16-0f7c-4852-9ac7-257fa6622585';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '213977d8-aa03-4d60-8679-0077ef29b8e0' ), (select name from clinlims.test where guid = '213977d8-aa03-4d60-8679-0077ef29b8e0' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='213977d8-aa03-4d60-8679-0077ef29b8e0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '213977d8-aa03-4d60-8679-0077ef29b8e0' ), (select reporting_description from clinlims.test where guid = '213977d8-aa03-4d60-8679-0077ef29b8e0' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='213977d8-aa03-4d60-8679-0077ef29b8e0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'c05ab032-965d-4001-9a79-6fda8296565d' ), (select name from clinlims.test where guid = 'c05ab032-965d-4001-9a79-6fda8296565d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='c05ab032-965d-4001-9a79-6fda8296565d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'c05ab032-965d-4001-9a79-6fda8296565d' ), (select reporting_description from clinlims.test where guid = 'c05ab032-965d-4001-9a79-6fda8296565d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='c05ab032-965d-4001-9a79-6fda8296565d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '96ee6e88-eac4-4bbf-9eb3-cda3076cca2e' ), (select name from clinlims.test where guid = '96ee6e88-eac4-4bbf-9eb3-cda3076cca2e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='96ee6e88-eac4-4bbf-9eb3-cda3076cca2e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '96ee6e88-eac4-4bbf-9eb3-cda3076cca2e' ), (select reporting_description from clinlims.test where guid = '96ee6e88-eac4-4bbf-9eb3-cda3076cca2e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='96ee6e88-eac4-4bbf-9eb3-cda3076cca2e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '6013f196-22cb-44e9-83e5-61f92f98ccdc' ), (select name from clinlims.test where guid = '6013f196-22cb-44e9-83e5-61f92f98ccdc' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='6013f196-22cb-44e9-83e5-61f92f98ccdc';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '6013f196-22cb-44e9-83e5-61f92f98ccdc' ), (select reporting_description from clinlims.test where guid = '6013f196-22cb-44e9-83e5-61f92f98ccdc' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='6013f196-22cb-44e9-83e5-61f92f98ccdc';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'd9dbd755-881c-4229-9497-4e767f0fe9d6' ), (select name from clinlims.test where guid = 'd9dbd755-881c-4229-9497-4e767f0fe9d6' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d9dbd755-881c-4229-9497-4e767f0fe9d6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'd9dbd755-881c-4229-9497-4e767f0fe9d6' ), (select reporting_description from clinlims.test where guid = 'd9dbd755-881c-4229-9497-4e767f0fe9d6' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d9dbd755-881c-4229-9497-4e767f0fe9d6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '26c412c7-b82d-4fd9-b4a1-c052162c5496' ), (select name from clinlims.test where guid = '26c412c7-b82d-4fd9-b4a1-c052162c5496' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='26c412c7-b82d-4fd9-b4a1-c052162c5496';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '26c412c7-b82d-4fd9-b4a1-c052162c5496' ), (select reporting_description from clinlims.test where guid = '26c412c7-b82d-4fd9-b4a1-c052162c5496' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='26c412c7-b82d-4fd9-b4a1-c052162c5496';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'd9f213e8-351a-4e69-b8d8-ade56bc09914' ), (select name from clinlims.test where guid = 'd9f213e8-351a-4e69-b8d8-ade56bc09914' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d9f213e8-351a-4e69-b8d8-ade56bc09914';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'd9f213e8-351a-4e69-b8d8-ade56bc09914' ), (select reporting_description from clinlims.test where guid = 'd9f213e8-351a-4e69-b8d8-ade56bc09914' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d9f213e8-351a-4e69-b8d8-ade56bc09914';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'c28b009a-5c98-49f0-ae71-d8332a113faa' ), (select name from clinlims.test where guid = 'c28b009a-5c98-49f0-ae71-d8332a113faa' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='c28b009a-5c98-49f0-ae71-d8332a113faa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'c28b009a-5c98-49f0-ae71-d8332a113faa' ), (select reporting_description from clinlims.test where guid = 'c28b009a-5c98-49f0-ae71-d8332a113faa' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='c28b009a-5c98-49f0-ae71-d8332a113faa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '5c871952-01c6-4027-a0af-f8f70faf1cd7' ), (select name from clinlims.test where guid = '5c871952-01c6-4027-a0af-f8f70faf1cd7' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='5c871952-01c6-4027-a0af-f8f70faf1cd7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '5c871952-01c6-4027-a0af-f8f70faf1cd7' ), (select reporting_description from clinlims.test where guid = '5c871952-01c6-4027-a0af-f8f70faf1cd7' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='5c871952-01c6-4027-a0af-f8f70faf1cd7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '79e61255-6d36-4120-89da-1a5cecd2195a' ), (select name from clinlims.test where guid = '79e61255-6d36-4120-89da-1a5cecd2195a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='79e61255-6d36-4120-89da-1a5cecd2195a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '79e61255-6d36-4120-89da-1a5cecd2195a' ), (select reporting_description from clinlims.test where guid = '79e61255-6d36-4120-89da-1a5cecd2195a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='79e61255-6d36-4120-89da-1a5cecd2195a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'f11a6e3e-8c83-4dc0-9fd3-0f06f13b15eb' ), (select name from clinlims.test where guid = 'f11a6e3e-8c83-4dc0-9fd3-0f06f13b15eb' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='f11a6e3e-8c83-4dc0-9fd3-0f06f13b15eb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'f11a6e3e-8c83-4dc0-9fd3-0f06f13b15eb' ), (select reporting_description from clinlims.test where guid = 'f11a6e3e-8c83-4dc0-9fd3-0f06f13b15eb' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='f11a6e3e-8c83-4dc0-9fd3-0f06f13b15eb';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '898fc3c7-e1d8-496b-a87c-91d706add6b7' ), (select name from clinlims.test where guid = '898fc3c7-e1d8-496b-a87c-91d706add6b7' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='898fc3c7-e1d8-496b-a87c-91d706add6b7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '898fc3c7-e1d8-496b-a87c-91d706add6b7' ), (select reporting_description from clinlims.test where guid = '898fc3c7-e1d8-496b-a87c-91d706add6b7' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='898fc3c7-e1d8-496b-a87c-91d706add6b7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '8edfe1dd-f79c-4d74-8089-a25b3ed2429f' ), (select name from clinlims.test where guid = '8edfe1dd-f79c-4d74-8089-a25b3ed2429f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='8edfe1dd-f79c-4d74-8089-a25b3ed2429f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '8edfe1dd-f79c-4d74-8089-a25b3ed2429f' ), (select reporting_description from clinlims.test where guid = '8edfe1dd-f79c-4d74-8089-a25b3ed2429f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='8edfe1dd-f79c-4d74-8089-a25b3ed2429f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '60421891-52ed-4b8f-9e1b-b9946206615b' ), (select name from clinlims.test where guid = '60421891-52ed-4b8f-9e1b-b9946206615b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='60421891-52ed-4b8f-9e1b-b9946206615b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '60421891-52ed-4b8f-9e1b-b9946206615b' ), (select reporting_description from clinlims.test where guid = '60421891-52ed-4b8f-9e1b-b9946206615b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='60421891-52ed-4b8f-9e1b-b9946206615b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '7a9f7c32-38de-4ace-b33f-a8576ef4e91e' ), (select name from clinlims.test where guid = '7a9f7c32-38de-4ace-b33f-a8576ef4e91e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='7a9f7c32-38de-4ace-b33f-a8576ef4e91e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '7a9f7c32-38de-4ace-b33f-a8576ef4e91e' ), (select reporting_description from clinlims.test where guid = '7a9f7c32-38de-4ace-b33f-a8576ef4e91e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='7a9f7c32-38de-4ace-b33f-a8576ef4e91e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '6ae966a3-5eca-4ca7-8b5e-58c6c5679e4c' ), (select name from clinlims.test where guid = '6ae966a3-5eca-4ca7-8b5e-58c6c5679e4c' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='6ae966a3-5eca-4ca7-8b5e-58c6c5679e4c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '6ae966a3-5eca-4ca7-8b5e-58c6c5679e4c' ), (select reporting_description from clinlims.test where guid = '6ae966a3-5eca-4ca7-8b5e-58c6c5679e4c' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='6ae966a3-5eca-4ca7-8b5e-58c6c5679e4c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '6f49eaa7-a150-4a4f-b795-b2b01284e917' ), (select name from clinlims.test where guid = '6f49eaa7-a150-4a4f-b795-b2b01284e917' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='6f49eaa7-a150-4a4f-b795-b2b01284e917';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '6f49eaa7-a150-4a4f-b795-b2b01284e917' ), (select reporting_description from clinlims.test where guid = '6f49eaa7-a150-4a4f-b795-b2b01284e917' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='6f49eaa7-a150-4a4f-b795-b2b01284e917';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '7951cdaf-12e1-4bd6-a1d0-640869f757cd' ), (select name from clinlims.test where guid = '7951cdaf-12e1-4bd6-a1d0-640869f757cd' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='7951cdaf-12e1-4bd6-a1d0-640869f757cd';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '7951cdaf-12e1-4bd6-a1d0-640869f757cd' ), (select reporting_description from clinlims.test where guid = '7951cdaf-12e1-4bd6-a1d0-640869f757cd' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='7951cdaf-12e1-4bd6-a1d0-640869f757cd';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'f3949436-9608-490f-b52a-1216486fb8d0' ), (select name from clinlims.test where guid = 'f3949436-9608-490f-b52a-1216486fb8d0' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='f3949436-9608-490f-b52a-1216486fb8d0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'f3949436-9608-490f-b52a-1216486fb8d0' ), (select reporting_description from clinlims.test where guid = 'f3949436-9608-490f-b52a-1216486fb8d0' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='f3949436-9608-490f-b52a-1216486fb8d0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'f7144e5d-836a-4cc7-8621-4436dfd69ce0' ), (select name from clinlims.test where guid = 'f7144e5d-836a-4cc7-8621-4436dfd69ce0' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='f7144e5d-836a-4cc7-8621-4436dfd69ce0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'f7144e5d-836a-4cc7-8621-4436dfd69ce0' ), (select reporting_description from clinlims.test where guid = 'f7144e5d-836a-4cc7-8621-4436dfd69ce0' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='f7144e5d-836a-4cc7-8621-4436dfd69ce0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '13d2edb8-bcaa-417c-8f38-953977e7b1a1' ), (select name from clinlims.test where guid = '13d2edb8-bcaa-417c-8f38-953977e7b1a1' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='13d2edb8-bcaa-417c-8f38-953977e7b1a1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '13d2edb8-bcaa-417c-8f38-953977e7b1a1' ), (select reporting_description from clinlims.test where guid = '13d2edb8-bcaa-417c-8f38-953977e7b1a1' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='13d2edb8-bcaa-417c-8f38-953977e7b1a1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '19e559a4-bd36-4508-95bc-9ffe53fefc76' ), (select name from clinlims.test where guid = '19e559a4-bd36-4508-95bc-9ffe53fefc76' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='19e559a4-bd36-4508-95bc-9ffe53fefc76';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '19e559a4-bd36-4508-95bc-9ffe53fefc76' ), (select reporting_description from clinlims.test where guid = '19e559a4-bd36-4508-95bc-9ffe53fefc76' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='19e559a4-bd36-4508-95bc-9ffe53fefc76';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'd603ebce-28b7-42b8-a861-762f36f6b084' ), (select name from clinlims.test where guid = 'd603ebce-28b7-42b8-a861-762f36f6b084' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d603ebce-28b7-42b8-a861-762f36f6b084';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'd603ebce-28b7-42b8-a861-762f36f6b084' ), (select reporting_description from clinlims.test where guid = 'd603ebce-28b7-42b8-a861-762f36f6b084' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d603ebce-28b7-42b8-a861-762f36f6b084';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '066a5a55-e2a0-4fa6-ad32-ecc0a832ec24' ), (select name from clinlims.test where guid = '066a5a55-e2a0-4fa6-ad32-ecc0a832ec24' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='066a5a55-e2a0-4fa6-ad32-ecc0a832ec24';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '066a5a55-e2a0-4fa6-ad32-ecc0a832ec24' ), (select reporting_description from clinlims.test where guid = '066a5a55-e2a0-4fa6-ad32-ecc0a832ec24' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='066a5a55-e2a0-4fa6-ad32-ecc0a832ec24';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'd5c28134-60ac-4e4c-b4c1-f15355aad380' ), (select name from clinlims.test where guid = 'd5c28134-60ac-4e4c-b4c1-f15355aad380' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d5c28134-60ac-4e4c-b4c1-f15355aad380';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'd5c28134-60ac-4e4c-b4c1-f15355aad380' ), (select reporting_description from clinlims.test where guid = 'd5c28134-60ac-4e4c-b4c1-f15355aad380' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d5c28134-60ac-4e4c-b4c1-f15355aad380';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'e719fcd5-1a20-4592-881e-bd2700f8e829' ), (select name from clinlims.test where guid = 'e719fcd5-1a20-4592-881e-bd2700f8e829' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='e719fcd5-1a20-4592-881e-bd2700f8e829';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'e719fcd5-1a20-4592-881e-bd2700f8e829' ), (select reporting_description from clinlims.test where guid = 'e719fcd5-1a20-4592-881e-bd2700f8e829' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='e719fcd5-1a20-4592-881e-bd2700f8e829';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'f47f26c8-241a-438b-86b6-02140453f56c' ), (select name from clinlims.test where guid = 'f47f26c8-241a-438b-86b6-02140453f56c' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='f47f26c8-241a-438b-86b6-02140453f56c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'f47f26c8-241a-438b-86b6-02140453f56c' ), (select reporting_description from clinlims.test where guid = 'f47f26c8-241a-438b-86b6-02140453f56c' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='f47f26c8-241a-438b-86b6-02140453f56c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'd84a0f45-304c-4de9-8a87-a5707ad8475e' ), (select name from clinlims.test where guid = 'd84a0f45-304c-4de9-8a87-a5707ad8475e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d84a0f45-304c-4de9-8a87-a5707ad8475e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'd84a0f45-304c-4de9-8a87-a5707ad8475e' ), (select reporting_description from clinlims.test where guid = 'd84a0f45-304c-4de9-8a87-a5707ad8475e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d84a0f45-304c-4de9-8a87-a5707ad8475e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '19657cb2-30ac-4dca-8aab-670c2788bee3' ), (select name from clinlims.test where guid = '19657cb2-30ac-4dca-8aab-670c2788bee3' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='19657cb2-30ac-4dca-8aab-670c2788bee3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '19657cb2-30ac-4dca-8aab-670c2788bee3' ), (select reporting_description from clinlims.test where guid = '19657cb2-30ac-4dca-8aab-670c2788bee3' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='19657cb2-30ac-4dca-8aab-670c2788bee3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'd044d062-18e2-42b9-a97a-8083488bf583' ), (select name from clinlims.test where guid = 'd044d062-18e2-42b9-a97a-8083488bf583' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d044d062-18e2-42b9-a97a-8083488bf583';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'd044d062-18e2-42b9-a97a-8083488bf583' ), (select reporting_description from clinlims.test where guid = 'd044d062-18e2-42b9-a97a-8083488bf583' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d044d062-18e2-42b9-a97a-8083488bf583';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'b8045bf9-2a70-48c6-b399-e66b845c6681' ), (select name from clinlims.test where guid = 'b8045bf9-2a70-48c6-b399-e66b845c6681' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='b8045bf9-2a70-48c6-b399-e66b845c6681';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'b8045bf9-2a70-48c6-b399-e66b845c6681' ), (select reporting_description from clinlims.test where guid = 'b8045bf9-2a70-48c6-b399-e66b845c6681' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='b8045bf9-2a70-48c6-b399-e66b845c6681';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '4a0f36b5-940a-4f78-9e7c-54fc349bee79' ), (select name from clinlims.test where guid = '4a0f36b5-940a-4f78-9e7c-54fc349bee79' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='4a0f36b5-940a-4f78-9e7c-54fc349bee79';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '4a0f36b5-940a-4f78-9e7c-54fc349bee79' ), (select reporting_description from clinlims.test where guid = '4a0f36b5-940a-4f78-9e7c-54fc349bee79' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='4a0f36b5-940a-4f78-9e7c-54fc349bee79';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '61a0f70b-4478-4e5b-92e8-fcc007a72db4' ), (select name from clinlims.test where guid = '61a0f70b-4478-4e5b-92e8-fcc007a72db4' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='61a0f70b-4478-4e5b-92e8-fcc007a72db4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '61a0f70b-4478-4e5b-92e8-fcc007a72db4' ), (select reporting_description from clinlims.test where guid = '61a0f70b-4478-4e5b-92e8-fcc007a72db4' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='61a0f70b-4478-4e5b-92e8-fcc007a72db4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'f840512f-65b1-482b-90b5-a51928dc51b1' ), (select name from clinlims.test where guid = 'f840512f-65b1-482b-90b5-a51928dc51b1' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='f840512f-65b1-482b-90b5-a51928dc51b1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'f840512f-65b1-482b-90b5-a51928dc51b1' ), (select reporting_description from clinlims.test where guid = 'f840512f-65b1-482b-90b5-a51928dc51b1' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='f840512f-65b1-482b-90b5-a51928dc51b1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '56de039e-b2dc-439d-8e9b-c419beefbf11' ), (select name from clinlims.test where guid = '56de039e-b2dc-439d-8e9b-c419beefbf11' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='56de039e-b2dc-439d-8e9b-c419beefbf11';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '56de039e-b2dc-439d-8e9b-c419beefbf11' ), (select reporting_description from clinlims.test where guid = '56de039e-b2dc-439d-8e9b-c419beefbf11' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='56de039e-b2dc-439d-8e9b-c419beefbf11';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '707e9e46-6308-4565-9a1a-6304891e0c29' ), (select name from clinlims.test where guid = '707e9e46-6308-4565-9a1a-6304891e0c29' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='707e9e46-6308-4565-9a1a-6304891e0c29';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '707e9e46-6308-4565-9a1a-6304891e0c29' ), (select reporting_description from clinlims.test where guid = '707e9e46-6308-4565-9a1a-6304891e0c29' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='707e9e46-6308-4565-9a1a-6304891e0c29';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '21a7a115-c4b0-4a22-bdbe-d36ac1d11f03' ), (select name from clinlims.test where guid = '21a7a115-c4b0-4a22-bdbe-d36ac1d11f03' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='21a7a115-c4b0-4a22-bdbe-d36ac1d11f03';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '21a7a115-c4b0-4a22-bdbe-d36ac1d11f03' ), (select reporting_description from clinlims.test where guid = '21a7a115-c4b0-4a22-bdbe-d36ac1d11f03' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='21a7a115-c4b0-4a22-bdbe-d36ac1d11f03';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '1232eb4c-f086-45d4-8641-23282e54dda0' ), (select name from clinlims.test where guid = '1232eb4c-f086-45d4-8641-23282e54dda0' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='1232eb4c-f086-45d4-8641-23282e54dda0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '1232eb4c-f086-45d4-8641-23282e54dda0' ), (select reporting_description from clinlims.test where guid = '1232eb4c-f086-45d4-8641-23282e54dda0' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='1232eb4c-f086-45d4-8641-23282e54dda0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'e044944a-a532-465d-88d5-eb4f131338dd' ), (select name from clinlims.test where guid = 'e044944a-a532-465d-88d5-eb4f131338dd' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='e044944a-a532-465d-88d5-eb4f131338dd';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'e044944a-a532-465d-88d5-eb4f131338dd' ), (select reporting_description from clinlims.test where guid = 'e044944a-a532-465d-88d5-eb4f131338dd' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='e044944a-a532-465d-88d5-eb4f131338dd';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '4dbce535-7f1b-47ee-808a-a8b781b663cf' ), (select name from clinlims.test where guid = '4dbce535-7f1b-47ee-808a-a8b781b663cf' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='4dbce535-7f1b-47ee-808a-a8b781b663cf';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '4dbce535-7f1b-47ee-808a-a8b781b663cf' ), (select reporting_description from clinlims.test where guid = '4dbce535-7f1b-47ee-808a-a8b781b663cf' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='4dbce535-7f1b-47ee-808a-a8b781b663cf';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '285cbb97-5b3b-4710-b9ee-205a0ad76fae' ), (select name from clinlims.test where guid = '285cbb97-5b3b-4710-b9ee-205a0ad76fae' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='285cbb97-5b3b-4710-b9ee-205a0ad76fae';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '285cbb97-5b3b-4710-b9ee-205a0ad76fae' ), (select reporting_description from clinlims.test where guid = '285cbb97-5b3b-4710-b9ee-205a0ad76fae' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='285cbb97-5b3b-4710-b9ee-205a0ad76fae';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'f81605a3-b4f1-471a-8854-d26ec0649081' ), (select name from clinlims.test where guid = 'f81605a3-b4f1-471a-8854-d26ec0649081' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='f81605a3-b4f1-471a-8854-d26ec0649081';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'f81605a3-b4f1-471a-8854-d26ec0649081' ), (select reporting_description from clinlims.test where guid = 'f81605a3-b4f1-471a-8854-d26ec0649081' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='f81605a3-b4f1-471a-8854-d26ec0649081';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '62c1b618-3fb6-426f-a276-ca80dfc1df7a' ), (select name from clinlims.test where guid = '62c1b618-3fb6-426f-a276-ca80dfc1df7a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='62c1b618-3fb6-426f-a276-ca80dfc1df7a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '62c1b618-3fb6-426f-a276-ca80dfc1df7a' ), (select reporting_description from clinlims.test where guid = '62c1b618-3fb6-426f-a276-ca80dfc1df7a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='62c1b618-3fb6-426f-a276-ca80dfc1df7a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '3e5b6732-cc34-42b7-b690-ef49c2a0f590' ), (select name from clinlims.test where guid = '3e5b6732-cc34-42b7-b690-ef49c2a0f590' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3e5b6732-cc34-42b7-b690-ef49c2a0f590';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '3e5b6732-cc34-42b7-b690-ef49c2a0f590' ), (select reporting_description from clinlims.test where guid = '3e5b6732-cc34-42b7-b690-ef49c2a0f590' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3e5b6732-cc34-42b7-b690-ef49c2a0f590';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '31069e5e-9c28-4761-90cd-d60c9f2fa103' ), (select name from clinlims.test where guid = '31069e5e-9c28-4761-90cd-d60c9f2fa103' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='31069e5e-9c28-4761-90cd-d60c9f2fa103';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '31069e5e-9c28-4761-90cd-d60c9f2fa103' ), (select reporting_description from clinlims.test where guid = '31069e5e-9c28-4761-90cd-d60c9f2fa103' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='31069e5e-9c28-4761-90cd-d60c9f2fa103';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'a9119acf-45d1-4037-96ce-7ca82d3a36e3' ), (select name from clinlims.test where guid = 'a9119acf-45d1-4037-96ce-7ca82d3a36e3' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a9119acf-45d1-4037-96ce-7ca82d3a36e3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'a9119acf-45d1-4037-96ce-7ca82d3a36e3' ), (select reporting_description from clinlims.test where guid = 'a9119acf-45d1-4037-96ce-7ca82d3a36e3' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a9119acf-45d1-4037-96ce-7ca82d3a36e3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '036eb131-035f-44db-b63f-f7665fdc4cee' ), (select name from clinlims.test where guid = '036eb131-035f-44db-b63f-f7665fdc4cee' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='036eb131-035f-44db-b63f-f7665fdc4cee';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '036eb131-035f-44db-b63f-f7665fdc4cee' ), (select reporting_description from clinlims.test where guid = '036eb131-035f-44db-b63f-f7665fdc4cee' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='036eb131-035f-44db-b63f-f7665fdc4cee';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '09a74297-da90-46c5-952b-d4c22a735b6b' ), (select name from clinlims.test where guid = '09a74297-da90-46c5-952b-d4c22a735b6b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='09a74297-da90-46c5-952b-d4c22a735b6b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '09a74297-da90-46c5-952b-d4c22a735b6b' ), (select reporting_description from clinlims.test where guid = '09a74297-da90-46c5-952b-d4c22a735b6b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='09a74297-da90-46c5-952b-d4c22a735b6b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'f23cdb28-03d8-448c-a57e-b5c0d9f412d0' ), (select name from clinlims.test where guid = 'f23cdb28-03d8-448c-a57e-b5c0d9f412d0' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='f23cdb28-03d8-448c-a57e-b5c0d9f412d0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'f23cdb28-03d8-448c-a57e-b5c0d9f412d0' ), (select reporting_description from clinlims.test where guid = 'f23cdb28-03d8-448c-a57e-b5c0d9f412d0' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='f23cdb28-03d8-448c-a57e-b5c0d9f412d0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '82bb626d-31f2-4829-9848-916ca234d86b' ), (select name from clinlims.test where guid = '82bb626d-31f2-4829-9848-916ca234d86b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='82bb626d-31f2-4829-9848-916ca234d86b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '82bb626d-31f2-4829-9848-916ca234d86b' ), (select reporting_description from clinlims.test where guid = '82bb626d-31f2-4829-9848-916ca234d86b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='82bb626d-31f2-4829-9848-916ca234d86b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'b39b4926-9070-40c3-91b9-f59b273f337d' ), (select name from clinlims.test where guid = 'b39b4926-9070-40c3-91b9-f59b273f337d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='b39b4926-9070-40c3-91b9-f59b273f337d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'b39b4926-9070-40c3-91b9-f59b273f337d' ), (select reporting_description from clinlims.test where guid = 'b39b4926-9070-40c3-91b9-f59b273f337d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='b39b4926-9070-40c3-91b9-f59b273f337d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '71d27585-1a8b-4136-9f95-8f5f77d211e7' ), (select name from clinlims.test where guid = '71d27585-1a8b-4136-9f95-8f5f77d211e7' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='71d27585-1a8b-4136-9f95-8f5f77d211e7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '71d27585-1a8b-4136-9f95-8f5f77d211e7' ), (select reporting_description from clinlims.test where guid = '71d27585-1a8b-4136-9f95-8f5f77d211e7' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='71d27585-1a8b-4136-9f95-8f5f77d211e7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '2a9ef358-e82f-4e85-8225-5731b37714a4' ), (select name from clinlims.test where guid = '2a9ef358-e82f-4e85-8225-5731b37714a4' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='2a9ef358-e82f-4e85-8225-5731b37714a4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '2a9ef358-e82f-4e85-8225-5731b37714a4' ), (select reporting_description from clinlims.test where guid = '2a9ef358-e82f-4e85-8225-5731b37714a4' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='2a9ef358-e82f-4e85-8225-5731b37714a4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '75f209a7-e48a-49f8-80d6-2f1a0ead2a4b' ), (select name from clinlims.test where guid = '75f209a7-e48a-49f8-80d6-2f1a0ead2a4b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='75f209a7-e48a-49f8-80d6-2f1a0ead2a4b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '75f209a7-e48a-49f8-80d6-2f1a0ead2a4b' ), (select reporting_description from clinlims.test where guid = '75f209a7-e48a-49f8-80d6-2f1a0ead2a4b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='75f209a7-e48a-49f8-80d6-2f1a0ead2a4b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '3007e200-15e8-432d-b0c9-ec06a6f256c0' ), (select name from clinlims.test where guid = '3007e200-15e8-432d-b0c9-ec06a6f256c0' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3007e200-15e8-432d-b0c9-ec06a6f256c0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '3007e200-15e8-432d-b0c9-ec06a6f256c0' ), (select reporting_description from clinlims.test where guid = '3007e200-15e8-432d-b0c9-ec06a6f256c0' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3007e200-15e8-432d-b0c9-ec06a6f256c0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'db5866b3-e6e6-42db-9731-1898b5e040ec' ), (select name from clinlims.test where guid = 'db5866b3-e6e6-42db-9731-1898b5e040ec' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='db5866b3-e6e6-42db-9731-1898b5e040ec';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'db5866b3-e6e6-42db-9731-1898b5e040ec' ), (select reporting_description from clinlims.test where guid = 'db5866b3-e6e6-42db-9731-1898b5e040ec' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='db5866b3-e6e6-42db-9731-1898b5e040ec';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '6a80d081-b614-41c3-8434-485efb8898b0' ), (select name from clinlims.test where guid = '6a80d081-b614-41c3-8434-485efb8898b0' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='6a80d081-b614-41c3-8434-485efb8898b0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '6a80d081-b614-41c3-8434-485efb8898b0' ), (select reporting_description from clinlims.test where guid = '6a80d081-b614-41c3-8434-485efb8898b0' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='6a80d081-b614-41c3-8434-485efb8898b0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'a7c1a863-c23d-452a-a2dc-715edfe3ffee' ), (select name from clinlims.test where guid = 'a7c1a863-c23d-452a-a2dc-715edfe3ffee' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a7c1a863-c23d-452a-a2dc-715edfe3ffee';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'a7c1a863-c23d-452a-a2dc-715edfe3ffee' ), (select reporting_description from clinlims.test where guid = 'a7c1a863-c23d-452a-a2dc-715edfe3ffee' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a7c1a863-c23d-452a-a2dc-715edfe3ffee';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '376373cb-96cb-4a96-b4bc-3ef788ab0681' ), (select name from clinlims.test where guid = '376373cb-96cb-4a96-b4bc-3ef788ab0681' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='376373cb-96cb-4a96-b4bc-3ef788ab0681';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '376373cb-96cb-4a96-b4bc-3ef788ab0681' ), (select reporting_description from clinlims.test where guid = '376373cb-96cb-4a96-b4bc-3ef788ab0681' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='376373cb-96cb-4a96-b4bc-3ef788ab0681';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '6a0ff096-02d3-49fc-b516-ae229f8d978d' ), (select name from clinlims.test where guid = '6a0ff096-02d3-49fc-b516-ae229f8d978d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='6a0ff096-02d3-49fc-b516-ae229f8d978d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '6a0ff096-02d3-49fc-b516-ae229f8d978d' ), (select reporting_description from clinlims.test where guid = '6a0ff096-02d3-49fc-b516-ae229f8d978d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='6a0ff096-02d3-49fc-b516-ae229f8d978d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '884d63e4-28d0-49e1-a2ad-27a50f3f0087' ), (select name from clinlims.test where guid = '884d63e4-28d0-49e1-a2ad-27a50f3f0087' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='884d63e4-28d0-49e1-a2ad-27a50f3f0087';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '884d63e4-28d0-49e1-a2ad-27a50f3f0087' ), (select reporting_description from clinlims.test where guid = '884d63e4-28d0-49e1-a2ad-27a50f3f0087' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='884d63e4-28d0-49e1-a2ad-27a50f3f0087';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '6509c22e-8137-4b77-afec-a14b80e18ddc' ), (select name from clinlims.test where guid = '6509c22e-8137-4b77-afec-a14b80e18ddc' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='6509c22e-8137-4b77-afec-a14b80e18ddc';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '6509c22e-8137-4b77-afec-a14b80e18ddc' ), (select reporting_description from clinlims.test where guid = '6509c22e-8137-4b77-afec-a14b80e18ddc' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='6509c22e-8137-4b77-afec-a14b80e18ddc';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'ecdc2d0d-a2de-416a-ac62-0719b562e297' ), (select name from clinlims.test where guid = 'ecdc2d0d-a2de-416a-ac62-0719b562e297' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='ecdc2d0d-a2de-416a-ac62-0719b562e297';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'ecdc2d0d-a2de-416a-ac62-0719b562e297' ), (select reporting_description from clinlims.test where guid = 'ecdc2d0d-a2de-416a-ac62-0719b562e297' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='ecdc2d0d-a2de-416a-ac62-0719b562e297';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '8eaf5cdb-c701-4a88-9078-877aea2224e0' ), (select name from clinlims.test where guid = '8eaf5cdb-c701-4a88-9078-877aea2224e0' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='8eaf5cdb-c701-4a88-9078-877aea2224e0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '8eaf5cdb-c701-4a88-9078-877aea2224e0' ), (select reporting_description from clinlims.test where guid = '8eaf5cdb-c701-4a88-9078-877aea2224e0' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='8eaf5cdb-c701-4a88-9078-877aea2224e0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '5973b021-abe4-4aa4-a1b1-cc9cb135b7d2' ), (select name from clinlims.test where guid = '5973b021-abe4-4aa4-a1b1-cc9cb135b7d2' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='5973b021-abe4-4aa4-a1b1-cc9cb135b7d2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '5973b021-abe4-4aa4-a1b1-cc9cb135b7d2' ), (select reporting_description from clinlims.test where guid = '5973b021-abe4-4aa4-a1b1-cc9cb135b7d2' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='5973b021-abe4-4aa4-a1b1-cc9cb135b7d2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '252e69dc-051d-4043-afd4-56db003e940b' ), (select name from clinlims.test where guid = '252e69dc-051d-4043-afd4-56db003e940b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='252e69dc-051d-4043-afd4-56db003e940b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '252e69dc-051d-4043-afd4-56db003e940b' ), (select reporting_description from clinlims.test where guid = '252e69dc-051d-4043-afd4-56db003e940b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='252e69dc-051d-4043-afd4-56db003e940b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '84446236-d07f-4a48-91e4-251d4be8ea9f' ), (select name from clinlims.test where guid = '84446236-d07f-4a48-91e4-251d4be8ea9f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='84446236-d07f-4a48-91e4-251d4be8ea9f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '84446236-d07f-4a48-91e4-251d4be8ea9f' ), (select reporting_description from clinlims.test where guid = '84446236-d07f-4a48-91e4-251d4be8ea9f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='84446236-d07f-4a48-91e4-251d4be8ea9f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'd75d601c-fc27-4934-983f-0e1cf25efc29' ), (select name from clinlims.test where guid = 'd75d601c-fc27-4934-983f-0e1cf25efc29' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d75d601c-fc27-4934-983f-0e1cf25efc29';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'd75d601c-fc27-4934-983f-0e1cf25efc29' ), (select reporting_description from clinlims.test where guid = 'd75d601c-fc27-4934-983f-0e1cf25efc29' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d75d601c-fc27-4934-983f-0e1cf25efc29';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '33f83b67-7185-4034-af91-5234e8ec98e8' ), (select name from clinlims.test where guid = '33f83b67-7185-4034-af91-5234e8ec98e8' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='33f83b67-7185-4034-af91-5234e8ec98e8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '33f83b67-7185-4034-af91-5234e8ec98e8' ), (select reporting_description from clinlims.test where guid = '33f83b67-7185-4034-af91-5234e8ec98e8' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='33f83b67-7185-4034-af91-5234e8ec98e8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '911cbb44-6d31-4253-8e18-56d1172ea453' ), (select name from clinlims.test where guid = '911cbb44-6d31-4253-8e18-56d1172ea453' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='911cbb44-6d31-4253-8e18-56d1172ea453';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '911cbb44-6d31-4253-8e18-56d1172ea453' ), (select reporting_description from clinlims.test where guid = '911cbb44-6d31-4253-8e18-56d1172ea453' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='911cbb44-6d31-4253-8e18-56d1172ea453';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '15c16121-01fb-4961-b740-f76516cab0bd' ), (select name from clinlims.test where guid = '15c16121-01fb-4961-b740-f76516cab0bd' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='15c16121-01fb-4961-b740-f76516cab0bd';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '15c16121-01fb-4961-b740-f76516cab0bd' ), (select reporting_description from clinlims.test where guid = '15c16121-01fb-4961-b740-f76516cab0bd' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='15c16121-01fb-4961-b740-f76516cab0bd';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '0375a0c8-74a6-489b-a24c-61763208dc3d' ), (select name from clinlims.test where guid = '0375a0c8-74a6-489b-a24c-61763208dc3d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='0375a0c8-74a6-489b-a24c-61763208dc3d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '0375a0c8-74a6-489b-a24c-61763208dc3d' ), (select reporting_description from clinlims.test where guid = '0375a0c8-74a6-489b-a24c-61763208dc3d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='0375a0c8-74a6-489b-a24c-61763208dc3d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '257e729b-69d6-46c2-8cba-8f5ccf3f5c7b' ), (select name from clinlims.test where guid = '257e729b-69d6-46c2-8cba-8f5ccf3f5c7b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='257e729b-69d6-46c2-8cba-8f5ccf3f5c7b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '257e729b-69d6-46c2-8cba-8f5ccf3f5c7b' ), (select reporting_description from clinlims.test where guid = '257e729b-69d6-46c2-8cba-8f5ccf3f5c7b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='257e729b-69d6-46c2-8cba-8f5ccf3f5c7b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '7e484aec-8b91-4a04-b734-de4a2ba87ee0' ), (select name from clinlims.test where guid = '7e484aec-8b91-4a04-b734-de4a2ba87ee0' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='7e484aec-8b91-4a04-b734-de4a2ba87ee0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '7e484aec-8b91-4a04-b734-de4a2ba87ee0' ), (select reporting_description from clinlims.test where guid = '7e484aec-8b91-4a04-b734-de4a2ba87ee0' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='7e484aec-8b91-4a04-b734-de4a2ba87ee0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '3f0f7167-0c29-4da3-bd6e-cb6598bc47a8' ), (select name from clinlims.test where guid = '3f0f7167-0c29-4da3-bd6e-cb6598bc47a8' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3f0f7167-0c29-4da3-bd6e-cb6598bc47a8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '3f0f7167-0c29-4da3-bd6e-cb6598bc47a8' ), (select reporting_description from clinlims.test where guid = '3f0f7167-0c29-4da3-bd6e-cb6598bc47a8' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3f0f7167-0c29-4da3-bd6e-cb6598bc47a8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '37f9015e-bebe-4183-b0f2-ccd4ffef7060' ), (select name from clinlims.test where guid = '37f9015e-bebe-4183-b0f2-ccd4ffef7060' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='37f9015e-bebe-4183-b0f2-ccd4ffef7060';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '37f9015e-bebe-4183-b0f2-ccd4ffef7060' ), (select reporting_description from clinlims.test where guid = '37f9015e-bebe-4183-b0f2-ccd4ffef7060' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='37f9015e-bebe-4183-b0f2-ccd4ffef7060';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '1f33fc24-b399-4de5-b301-1e6a468fb8a2' ), (select name from clinlims.test where guid = '1f33fc24-b399-4de5-b301-1e6a468fb8a2' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='1f33fc24-b399-4de5-b301-1e6a468fb8a2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '1f33fc24-b399-4de5-b301-1e6a468fb8a2' ), (select reporting_description from clinlims.test where guid = '1f33fc24-b399-4de5-b301-1e6a468fb8a2' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='1f33fc24-b399-4de5-b301-1e6a468fb8a2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '2b020fb0-517b-4e08-a6d2-1b629c20ffd9' ), (select name from clinlims.test where guid = '2b020fb0-517b-4e08-a6d2-1b629c20ffd9' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='2b020fb0-517b-4e08-a6d2-1b629c20ffd9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '2b020fb0-517b-4e08-a6d2-1b629c20ffd9' ), (select reporting_description from clinlims.test where guid = '2b020fb0-517b-4e08-a6d2-1b629c20ffd9' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='2b020fb0-517b-4e08-a6d2-1b629c20ffd9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '0ad968cf-07bf-41f3-a903-33e0e5059435' ), (select name from clinlims.test where guid = '0ad968cf-07bf-41f3-a903-33e0e5059435' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='0ad968cf-07bf-41f3-a903-33e0e5059435';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '0ad968cf-07bf-41f3-a903-33e0e5059435' ), (select reporting_description from clinlims.test where guid = '0ad968cf-07bf-41f3-a903-33e0e5059435' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='0ad968cf-07bf-41f3-a903-33e0e5059435';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '8157714f-4c7d-4cc4-81f3-8cbc5ee66779' ), (select name from clinlims.test where guid = '8157714f-4c7d-4cc4-81f3-8cbc5ee66779' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='8157714f-4c7d-4cc4-81f3-8cbc5ee66779';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '8157714f-4c7d-4cc4-81f3-8cbc5ee66779' ), (select reporting_description from clinlims.test where guid = '8157714f-4c7d-4cc4-81f3-8cbc5ee66779' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='8157714f-4c7d-4cc4-81f3-8cbc5ee66779';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '092341b0-793e-4b81-845a-d846b73845d8' ), (select name from clinlims.test where guid = '092341b0-793e-4b81-845a-d846b73845d8' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='092341b0-793e-4b81-845a-d846b73845d8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '092341b0-793e-4b81-845a-d846b73845d8' ), (select reporting_description from clinlims.test where guid = '092341b0-793e-4b81-845a-d846b73845d8' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='092341b0-793e-4b81-845a-d846b73845d8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '1e153090-1b3c-4991-83ef-6be8b327fa04' ), (select name from clinlims.test where guid = '1e153090-1b3c-4991-83ef-6be8b327fa04' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='1e153090-1b3c-4991-83ef-6be8b327fa04';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '1e153090-1b3c-4991-83ef-6be8b327fa04' ), (select reporting_description from clinlims.test where guid = '1e153090-1b3c-4991-83ef-6be8b327fa04' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='1e153090-1b3c-4991-83ef-6be8b327fa04';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '7fcdc7ff-a43d-43d8-a991-7243fe9a458b' ), (select name from clinlims.test where guid = '7fcdc7ff-a43d-43d8-a991-7243fe9a458b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='7fcdc7ff-a43d-43d8-a991-7243fe9a458b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '7fcdc7ff-a43d-43d8-a991-7243fe9a458b' ), (select reporting_description from clinlims.test where guid = '7fcdc7ff-a43d-43d8-a991-7243fe9a458b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='7fcdc7ff-a43d-43d8-a991-7243fe9a458b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '0db31ee1-e4a4-4940-8a74-d6940e426ba4' ), (select name from clinlims.test where guid = '0db31ee1-e4a4-4940-8a74-d6940e426ba4' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='0db31ee1-e4a4-4940-8a74-d6940e426ba4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '0db31ee1-e4a4-4940-8a74-d6940e426ba4' ), (select reporting_description from clinlims.test where guid = '0db31ee1-e4a4-4940-8a74-d6940e426ba4' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='0db31ee1-e4a4-4940-8a74-d6940e426ba4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'bff54631-7af5-468e-af44-9ea7d7222194' ), (select name from clinlims.test where guid = 'bff54631-7af5-468e-af44-9ea7d7222194' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='bff54631-7af5-468e-af44-9ea7d7222194';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'bff54631-7af5-468e-af44-9ea7d7222194' ), (select reporting_description from clinlims.test where guid = 'bff54631-7af5-468e-af44-9ea7d7222194' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='bff54631-7af5-468e-af44-9ea7d7222194';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '6ba09e67-921f-4186-a218-5f57f94318e0' ), (select name from clinlims.test where guid = '6ba09e67-921f-4186-a218-5f57f94318e0' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='6ba09e67-921f-4186-a218-5f57f94318e0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '6ba09e67-921f-4186-a218-5f57f94318e0' ), (select reporting_description from clinlims.test where guid = '6ba09e67-921f-4186-a218-5f57f94318e0' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='6ba09e67-921f-4186-a218-5f57f94318e0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'b8704e16-4578-4bc1-a55e-a4283a420d81' ), (select name from clinlims.test where guid = 'b8704e16-4578-4bc1-a55e-a4283a420d81' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='b8704e16-4578-4bc1-a55e-a4283a420d81';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'b8704e16-4578-4bc1-a55e-a4283a420d81' ), (select reporting_description from clinlims.test where guid = 'b8704e16-4578-4bc1-a55e-a4283a420d81' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='b8704e16-4578-4bc1-a55e-a4283a420d81';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '58c063dd-0edf-483e-9874-2e86ed25d000' ), (select name from clinlims.test where guid = '58c063dd-0edf-483e-9874-2e86ed25d000' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='58c063dd-0edf-483e-9874-2e86ed25d000';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '58c063dd-0edf-483e-9874-2e86ed25d000' ), (select reporting_description from clinlims.test where guid = '58c063dd-0edf-483e-9874-2e86ed25d000' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='58c063dd-0edf-483e-9874-2e86ed25d000';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '0e80af55-0c34-4bae-ab7f-ea088db90db0' ), (select name from clinlims.test where guid = '0e80af55-0c34-4bae-ab7f-ea088db90db0' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='0e80af55-0c34-4bae-ab7f-ea088db90db0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '0e80af55-0c34-4bae-ab7f-ea088db90db0' ), (select reporting_description from clinlims.test where guid = '0e80af55-0c34-4bae-ab7f-ea088db90db0' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='0e80af55-0c34-4bae-ab7f-ea088db90db0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'd2c6aa56-1e01-4fa2-aaf7-eb4ac0e0cc9f' ), (select name from clinlims.test where guid = 'd2c6aa56-1e01-4fa2-aaf7-eb4ac0e0cc9f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d2c6aa56-1e01-4fa2-aaf7-eb4ac0e0cc9f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'd2c6aa56-1e01-4fa2-aaf7-eb4ac0e0cc9f' ), (select reporting_description from clinlims.test where guid = 'd2c6aa56-1e01-4fa2-aaf7-eb4ac0e0cc9f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d2c6aa56-1e01-4fa2-aaf7-eb4ac0e0cc9f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'e2d74ada-9234-42ea-a28b-8d74d2be7194' ), (select name from clinlims.test where guid = 'e2d74ada-9234-42ea-a28b-8d74d2be7194' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='e2d74ada-9234-42ea-a28b-8d74d2be7194';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'e2d74ada-9234-42ea-a28b-8d74d2be7194' ), (select reporting_description from clinlims.test where guid = 'e2d74ada-9234-42ea-a28b-8d74d2be7194' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='e2d74ada-9234-42ea-a28b-8d74d2be7194';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '43ceac51-14e2-4b0d-8498-b16018d24022' ), (select name from clinlims.test where guid = '43ceac51-14e2-4b0d-8498-b16018d24022' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='43ceac51-14e2-4b0d-8498-b16018d24022';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '43ceac51-14e2-4b0d-8498-b16018d24022' ), (select reporting_description from clinlims.test where guid = '43ceac51-14e2-4b0d-8498-b16018d24022' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='43ceac51-14e2-4b0d-8498-b16018d24022';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'ddff9009-a7d9-40be-8666-53c9cb72b992' ), (select name from clinlims.test where guid = 'ddff9009-a7d9-40be-8666-53c9cb72b992' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='ddff9009-a7d9-40be-8666-53c9cb72b992';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'ddff9009-a7d9-40be-8666-53c9cb72b992' ), (select reporting_description from clinlims.test where guid = 'ddff9009-a7d9-40be-8666-53c9cb72b992' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='ddff9009-a7d9-40be-8666-53c9cb72b992';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'b04aa3c4-b074-4d59-99de-24e5286c1927' ), (select name from clinlims.test where guid = 'b04aa3c4-b074-4d59-99de-24e5286c1927' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='b04aa3c4-b074-4d59-99de-24e5286c1927';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'b04aa3c4-b074-4d59-99de-24e5286c1927' ), (select reporting_description from clinlims.test where guid = 'b04aa3c4-b074-4d59-99de-24e5286c1927' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='b04aa3c4-b074-4d59-99de-24e5286c1927';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'a038ce9d-b599-4b99-ba84-278021e82f0a' ), (select name from clinlims.test where guid = 'a038ce9d-b599-4b99-ba84-278021e82f0a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a038ce9d-b599-4b99-ba84-278021e82f0a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'a038ce9d-b599-4b99-ba84-278021e82f0a' ), (select reporting_description from clinlims.test where guid = 'a038ce9d-b599-4b99-ba84-278021e82f0a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a038ce9d-b599-4b99-ba84-278021e82f0a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '123b78c9-d6bb-483a-b300-a4c21de35dfa' ), (select name from clinlims.test where guid = '123b78c9-d6bb-483a-b300-a4c21de35dfa' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='123b78c9-d6bb-483a-b300-a4c21de35dfa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '123b78c9-d6bb-483a-b300-a4c21de35dfa' ), (select reporting_description from clinlims.test where guid = '123b78c9-d6bb-483a-b300-a4c21de35dfa' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='123b78c9-d6bb-483a-b300-a4c21de35dfa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '5d33ac3f-d41f-4909-bc89-1a574230aaff' ), (select name from clinlims.test where guid = '5d33ac3f-d41f-4909-bc89-1a574230aaff' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='5d33ac3f-d41f-4909-bc89-1a574230aaff';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '5d33ac3f-d41f-4909-bc89-1a574230aaff' ), (select reporting_description from clinlims.test where guid = '5d33ac3f-d41f-4909-bc89-1a574230aaff' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='5d33ac3f-d41f-4909-bc89-1a574230aaff';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'c692886e-a5c8-4975-bfc3-374677bd1ecd' ), (select name from clinlims.test where guid = 'c692886e-a5c8-4975-bfc3-374677bd1ecd' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='c692886e-a5c8-4975-bfc3-374677bd1ecd';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'c692886e-a5c8-4975-bfc3-374677bd1ecd' ), (select reporting_description from clinlims.test where guid = 'c692886e-a5c8-4975-bfc3-374677bd1ecd' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='c692886e-a5c8-4975-bfc3-374677bd1ecd';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '2e53100a-bd23-4bf9-885c-5d1575b50ce6' ), (select name from clinlims.test where guid = '2e53100a-bd23-4bf9-885c-5d1575b50ce6' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='2e53100a-bd23-4bf9-885c-5d1575b50ce6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '2e53100a-bd23-4bf9-885c-5d1575b50ce6' ), (select reporting_description from clinlims.test where guid = '2e53100a-bd23-4bf9-885c-5d1575b50ce6' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='2e53100a-bd23-4bf9-885c-5d1575b50ce6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '4e50f06f-b771-4b30-a3a2-843f2ed64d5a' ), (select name from clinlims.test where guid = '4e50f06f-b771-4b30-a3a2-843f2ed64d5a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='4e50f06f-b771-4b30-a3a2-843f2ed64d5a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '4e50f06f-b771-4b30-a3a2-843f2ed64d5a' ), (select reporting_description from clinlims.test where guid = '4e50f06f-b771-4b30-a3a2-843f2ed64d5a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='4e50f06f-b771-4b30-a3a2-843f2ed64d5a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'cebf8454-e24f-4a87-bc4f-1fe678edf372' ), (select name from clinlims.test where guid = 'cebf8454-e24f-4a87-bc4f-1fe678edf372' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='cebf8454-e24f-4a87-bc4f-1fe678edf372';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'cebf8454-e24f-4a87-bc4f-1fe678edf372' ), (select reporting_description from clinlims.test where guid = 'cebf8454-e24f-4a87-bc4f-1fe678edf372' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='cebf8454-e24f-4a87-bc4f-1fe678edf372';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '4b233eb5-3ff7-48e2-83e0-fb2886a0f15b' ), (select name from clinlims.test where guid = '4b233eb5-3ff7-48e2-83e0-fb2886a0f15b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='4b233eb5-3ff7-48e2-83e0-fb2886a0f15b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '4b233eb5-3ff7-48e2-83e0-fb2886a0f15b' ), (select reporting_description from clinlims.test where guid = '4b233eb5-3ff7-48e2-83e0-fb2886a0f15b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='4b233eb5-3ff7-48e2-83e0-fb2886a0f15b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '744362bb-4587-425f-901d-20aa4467563d' ), (select name from clinlims.test where guid = '744362bb-4587-425f-901d-20aa4467563d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='744362bb-4587-425f-901d-20aa4467563d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '744362bb-4587-425f-901d-20aa4467563d' ), (select reporting_description from clinlims.test where guid = '744362bb-4587-425f-901d-20aa4467563d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='744362bb-4587-425f-901d-20aa4467563d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '7c6ed8c2-700d-4c5d-bc61-82de8c416ce9' ), (select name from clinlims.test where guid = '7c6ed8c2-700d-4c5d-bc61-82de8c416ce9' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='7c6ed8c2-700d-4c5d-bc61-82de8c416ce9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '7c6ed8c2-700d-4c5d-bc61-82de8c416ce9' ), (select reporting_description from clinlims.test where guid = '7c6ed8c2-700d-4c5d-bc61-82de8c416ce9' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='7c6ed8c2-700d-4c5d-bc61-82de8c416ce9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '962fe4ea-144f-430d-89fc-2ea9322d4850' ), (select name from clinlims.test where guid = '962fe4ea-144f-430d-89fc-2ea9322d4850' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='962fe4ea-144f-430d-89fc-2ea9322d4850';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '962fe4ea-144f-430d-89fc-2ea9322d4850' ), (select reporting_description from clinlims.test where guid = '962fe4ea-144f-430d-89fc-2ea9322d4850' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='962fe4ea-144f-430d-89fc-2ea9322d4850';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '2783d442-03db-452c-baa7-8e0aa4aa44d5' ), (select name from clinlims.test where guid = '2783d442-03db-452c-baa7-8e0aa4aa44d5' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='2783d442-03db-452c-baa7-8e0aa4aa44d5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '2783d442-03db-452c-baa7-8e0aa4aa44d5' ), (select reporting_description from clinlims.test where guid = '2783d442-03db-452c-baa7-8e0aa4aa44d5' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='2783d442-03db-452c-baa7-8e0aa4aa44d5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '0fcbbf41-f828-438a-b3a0-304c2967bb6c' ), (select name from clinlims.test where guid = '0fcbbf41-f828-438a-b3a0-304c2967bb6c' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='0fcbbf41-f828-438a-b3a0-304c2967bb6c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '0fcbbf41-f828-438a-b3a0-304c2967bb6c' ), (select reporting_description from clinlims.test where guid = '0fcbbf41-f828-438a-b3a0-304c2967bb6c' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='0fcbbf41-f828-438a-b3a0-304c2967bb6c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'c5c9170b-e6dd-4547-9b9e-f2a59032b3a0' ), (select name from clinlims.test where guid = 'c5c9170b-e6dd-4547-9b9e-f2a59032b3a0' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='c5c9170b-e6dd-4547-9b9e-f2a59032b3a0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'c5c9170b-e6dd-4547-9b9e-f2a59032b3a0' ), (select reporting_description from clinlims.test where guid = 'c5c9170b-e6dd-4547-9b9e-f2a59032b3a0' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='c5c9170b-e6dd-4547-9b9e-f2a59032b3a0';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '714cd221-b710-44b5-a3fb-e58ee70018a6' ), (select name from clinlims.test where guid = '714cd221-b710-44b5-a3fb-e58ee70018a6' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='714cd221-b710-44b5-a3fb-e58ee70018a6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '714cd221-b710-44b5-a3fb-e58ee70018a6' ), (select reporting_description from clinlims.test where guid = '714cd221-b710-44b5-a3fb-e58ee70018a6' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='714cd221-b710-44b5-a3fb-e58ee70018a6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '9402293e-d410-4523-b5e8-ca7707e1fee1' ), (select name from clinlims.test where guid = '9402293e-d410-4523-b5e8-ca7707e1fee1' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='9402293e-d410-4523-b5e8-ca7707e1fee1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '9402293e-d410-4523-b5e8-ca7707e1fee1' ), (select reporting_description from clinlims.test where guid = '9402293e-d410-4523-b5e8-ca7707e1fee1' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='9402293e-d410-4523-b5e8-ca7707e1fee1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '301f4e08-9722-476b-aae7-f7049b6cf146' ), (select name from clinlims.test where guid = '301f4e08-9722-476b-aae7-f7049b6cf146' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='301f4e08-9722-476b-aae7-f7049b6cf146';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '301f4e08-9722-476b-aae7-f7049b6cf146' ), (select reporting_description from clinlims.test where guid = '301f4e08-9722-476b-aae7-f7049b6cf146' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='301f4e08-9722-476b-aae7-f7049b6cf146';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'c235990f-02e4-4b18-ad48-939a69a982d6' ), (select name from clinlims.test where guid = 'c235990f-02e4-4b18-ad48-939a69a982d6' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='c235990f-02e4-4b18-ad48-939a69a982d6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'c235990f-02e4-4b18-ad48-939a69a982d6' ), (select reporting_description from clinlims.test where guid = 'c235990f-02e4-4b18-ad48-939a69a982d6' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='c235990f-02e4-4b18-ad48-939a69a982d6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '4c76b225-8bf5-441f-b3e6-b1b8209262ba' ), (select name from clinlims.test where guid = '4c76b225-8bf5-441f-b3e6-b1b8209262ba' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='4c76b225-8bf5-441f-b3e6-b1b8209262ba';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '4c76b225-8bf5-441f-b3e6-b1b8209262ba' ), (select reporting_description from clinlims.test where guid = '4c76b225-8bf5-441f-b3e6-b1b8209262ba' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='4c76b225-8bf5-441f-b3e6-b1b8209262ba';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '3d41f1f6-9598-4e2d-91c0-e24fa845a9d2' ), (select name from clinlims.test where guid = '3d41f1f6-9598-4e2d-91c0-e24fa845a9d2' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3d41f1f6-9598-4e2d-91c0-e24fa845a9d2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '3d41f1f6-9598-4e2d-91c0-e24fa845a9d2' ), (select reporting_description from clinlims.test where guid = '3d41f1f6-9598-4e2d-91c0-e24fa845a9d2' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3d41f1f6-9598-4e2d-91c0-e24fa845a9d2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'd2222de5-fc6d-420a-9280-4358a5256129' ), (select name from clinlims.test where guid = 'd2222de5-fc6d-420a-9280-4358a5256129' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d2222de5-fc6d-420a-9280-4358a5256129';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'd2222de5-fc6d-420a-9280-4358a5256129' ), (select reporting_description from clinlims.test where guid = 'd2222de5-fc6d-420a-9280-4358a5256129' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d2222de5-fc6d-420a-9280-4358a5256129';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '63240731-9e15-4269-a87c-26b8ff6f9938' ), (select name from clinlims.test where guid = '63240731-9e15-4269-a87c-26b8ff6f9938' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='63240731-9e15-4269-a87c-26b8ff6f9938';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '63240731-9e15-4269-a87c-26b8ff6f9938' ), (select reporting_description from clinlims.test where guid = '63240731-9e15-4269-a87c-26b8ff6f9938' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='63240731-9e15-4269-a87c-26b8ff6f9938';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '40219785-8a32-422b-b1bd-101f7372d493' ), (select name from clinlims.test where guid = '40219785-8a32-422b-b1bd-101f7372d493' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='40219785-8a32-422b-b1bd-101f7372d493';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '40219785-8a32-422b-b1bd-101f7372d493' ), (select reporting_description from clinlims.test where guid = '40219785-8a32-422b-b1bd-101f7372d493' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='40219785-8a32-422b-b1bd-101f7372d493';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '55aee126-5920-4777-bd35-b8f1c31e2a8d' ), (select name from clinlims.test where guid = '55aee126-5920-4777-bd35-b8f1c31e2a8d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='55aee126-5920-4777-bd35-b8f1c31e2a8d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '55aee126-5920-4777-bd35-b8f1c31e2a8d' ), (select reporting_description from clinlims.test where guid = '55aee126-5920-4777-bd35-b8f1c31e2a8d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='55aee126-5920-4777-bd35-b8f1c31e2a8d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '77c76364-e829-4403-ae1d-8e2a647c2abd' ), (select name from clinlims.test where guid = '77c76364-e829-4403-ae1d-8e2a647c2abd' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='77c76364-e829-4403-ae1d-8e2a647c2abd';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '77c76364-e829-4403-ae1d-8e2a647c2abd' ), (select reporting_description from clinlims.test where guid = '77c76364-e829-4403-ae1d-8e2a647c2abd' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='77c76364-e829-4403-ae1d-8e2a647c2abd';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '5981283f-b102-4650-99f5-edbe439ab5aa' ), (select name from clinlims.test where guid = '5981283f-b102-4650-99f5-edbe439ab5aa' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='5981283f-b102-4650-99f5-edbe439ab5aa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '5981283f-b102-4650-99f5-edbe439ab5aa' ), (select reporting_description from clinlims.test where guid = '5981283f-b102-4650-99f5-edbe439ab5aa' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='5981283f-b102-4650-99f5-edbe439ab5aa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'c2f77345-66aa-480e-874c-5d7706dbfa14' ), (select name from clinlims.test where guid = 'c2f77345-66aa-480e-874c-5d7706dbfa14' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='c2f77345-66aa-480e-874c-5d7706dbfa14';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'c2f77345-66aa-480e-874c-5d7706dbfa14' ), (select reporting_description from clinlims.test where guid = 'c2f77345-66aa-480e-874c-5d7706dbfa14' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='c2f77345-66aa-480e-874c-5d7706dbfa14';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '767a5e43-2559-4847-8bd3-815d3cac7153' ), (select name from clinlims.test where guid = '767a5e43-2559-4847-8bd3-815d3cac7153' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='767a5e43-2559-4847-8bd3-815d3cac7153';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '767a5e43-2559-4847-8bd3-815d3cac7153' ), (select reporting_description from clinlims.test where guid = '767a5e43-2559-4847-8bd3-815d3cac7153' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='767a5e43-2559-4847-8bd3-815d3cac7153';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'f55d2b11-6472-4f09-bc06-31ba4493914d' ), (select name from clinlims.test where guid = 'f55d2b11-6472-4f09-bc06-31ba4493914d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='f55d2b11-6472-4f09-bc06-31ba4493914d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'f55d2b11-6472-4f09-bc06-31ba4493914d' ), (select reporting_description from clinlims.test where guid = 'f55d2b11-6472-4f09-bc06-31ba4493914d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='f55d2b11-6472-4f09-bc06-31ba4493914d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'f205b2ea-3bdd-49d7-a493-92af98cf6659' ), (select name from clinlims.test where guid = 'f205b2ea-3bdd-49d7-a493-92af98cf6659' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='f205b2ea-3bdd-49d7-a493-92af98cf6659';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'f205b2ea-3bdd-49d7-a493-92af98cf6659' ), (select reporting_description from clinlims.test where guid = 'f205b2ea-3bdd-49d7-a493-92af98cf6659' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='f205b2ea-3bdd-49d7-a493-92af98cf6659';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'e9021692-b1b9-4f4e-b2f2-84d10e3bdcff' ), (select name from clinlims.test where guid = 'e9021692-b1b9-4f4e-b2f2-84d10e3bdcff' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='e9021692-b1b9-4f4e-b2f2-84d10e3bdcff';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'e9021692-b1b9-4f4e-b2f2-84d10e3bdcff' ), (select reporting_description from clinlims.test where guid = 'e9021692-b1b9-4f4e-b2f2-84d10e3bdcff' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='e9021692-b1b9-4f4e-b2f2-84d10e3bdcff';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'f6dfa58b-aac1-490f-a735-d8925ea71a00' ), (select name from clinlims.test where guid = 'f6dfa58b-aac1-490f-a735-d8925ea71a00' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='f6dfa58b-aac1-490f-a735-d8925ea71a00';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'f6dfa58b-aac1-490f-a735-d8925ea71a00' ), (select reporting_description from clinlims.test where guid = 'f6dfa58b-aac1-490f-a735-d8925ea71a00' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='f6dfa58b-aac1-490f-a735-d8925ea71a00';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '5885b593-315a-4dc9-8ff0-6af78c806d52' ), (select name from clinlims.test where guid = '5885b593-315a-4dc9-8ff0-6af78c806d52' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='5885b593-315a-4dc9-8ff0-6af78c806d52';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '5885b593-315a-4dc9-8ff0-6af78c806d52' ), (select reporting_description from clinlims.test where guid = '5885b593-315a-4dc9-8ff0-6af78c806d52' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='5885b593-315a-4dc9-8ff0-6af78c806d52';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'cf0ba539-2caf-4a6e-8e4d-c28af7af2c63' ), (select name from clinlims.test where guid = 'cf0ba539-2caf-4a6e-8e4d-c28af7af2c63' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='cf0ba539-2caf-4a6e-8e4d-c28af7af2c63';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'cf0ba539-2caf-4a6e-8e4d-c28af7af2c63' ), (select reporting_description from clinlims.test where guid = 'cf0ba539-2caf-4a6e-8e4d-c28af7af2c63' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='cf0ba539-2caf-4a6e-8e4d-c28af7af2c63';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '3491258e-ac1f-457e-a87c-c2fa88bdf76c' ), (select name from clinlims.test where guid = '3491258e-ac1f-457e-a87c-c2fa88bdf76c' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3491258e-ac1f-457e-a87c-c2fa88bdf76c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '3491258e-ac1f-457e-a87c-c2fa88bdf76c' ), (select reporting_description from clinlims.test where guid = '3491258e-ac1f-457e-a87c-c2fa88bdf76c' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3491258e-ac1f-457e-a87c-c2fa88bdf76c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'b5b976cb-288a-4ab8-b4cb-474efbbcc244' ), (select name from clinlims.test where guid = 'b5b976cb-288a-4ab8-b4cb-474efbbcc244' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='b5b976cb-288a-4ab8-b4cb-474efbbcc244';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'b5b976cb-288a-4ab8-b4cb-474efbbcc244' ), (select reporting_description from clinlims.test where guid = 'b5b976cb-288a-4ab8-b4cb-474efbbcc244' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='b5b976cb-288a-4ab8-b4cb-474efbbcc244';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '5bdf585f-8435-4b01-b5a2-5ecbbdb84dd6' ), (select name from clinlims.test where guid = '5bdf585f-8435-4b01-b5a2-5ecbbdb84dd6' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='5bdf585f-8435-4b01-b5a2-5ecbbdb84dd6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '5bdf585f-8435-4b01-b5a2-5ecbbdb84dd6' ), (select reporting_description from clinlims.test where guid = '5bdf585f-8435-4b01-b5a2-5ecbbdb84dd6' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='5bdf585f-8435-4b01-b5a2-5ecbbdb84dd6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'fabc7360-4bcf-47e2-9cac-6fab454614e1' ), (select name from clinlims.test where guid = 'fabc7360-4bcf-47e2-9cac-6fab454614e1' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='fabc7360-4bcf-47e2-9cac-6fab454614e1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'fabc7360-4bcf-47e2-9cac-6fab454614e1' ), (select reporting_description from clinlims.test where guid = 'fabc7360-4bcf-47e2-9cac-6fab454614e1' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='fabc7360-4bcf-47e2-9cac-6fab454614e1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '8929d3f9-1ab3-442d-a8b7-1b2317932249' ), (select name from clinlims.test where guid = '8929d3f9-1ab3-442d-a8b7-1b2317932249' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='8929d3f9-1ab3-442d-a8b7-1b2317932249';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '8929d3f9-1ab3-442d-a8b7-1b2317932249' ), (select reporting_description from clinlims.test where guid = '8929d3f9-1ab3-442d-a8b7-1b2317932249' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='8929d3f9-1ab3-442d-a8b7-1b2317932249';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '0c066a7c-c74d-4035-beb7-016ab4f9b96e' ), (select name from clinlims.test where guid = '0c066a7c-c74d-4035-beb7-016ab4f9b96e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='0c066a7c-c74d-4035-beb7-016ab4f9b96e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '0c066a7c-c74d-4035-beb7-016ab4f9b96e' ), (select reporting_description from clinlims.test where guid = '0c066a7c-c74d-4035-beb7-016ab4f9b96e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='0c066a7c-c74d-4035-beb7-016ab4f9b96e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '96d40376-dcf8-44bb-a8d6-17ddb00bb961' ), (select name from clinlims.test where guid = '96d40376-dcf8-44bb-a8d6-17ddb00bb961' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='96d40376-dcf8-44bb-a8d6-17ddb00bb961';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '96d40376-dcf8-44bb-a8d6-17ddb00bb961' ), (select reporting_description from clinlims.test where guid = '96d40376-dcf8-44bb-a8d6-17ddb00bb961' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='96d40376-dcf8-44bb-a8d6-17ddb00bb961';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '7cfd7702-925c-40bb-8ce2-bdba13287ecf' ), (select name from clinlims.test where guid = '7cfd7702-925c-40bb-8ce2-bdba13287ecf' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='7cfd7702-925c-40bb-8ce2-bdba13287ecf';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '7cfd7702-925c-40bb-8ce2-bdba13287ecf' ), (select reporting_description from clinlims.test where guid = '7cfd7702-925c-40bb-8ce2-bdba13287ecf' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='7cfd7702-925c-40bb-8ce2-bdba13287ecf';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '5066d6c9-e139-4141-9cc1-56c260afdf3d' ), (select name from clinlims.test where guid = '5066d6c9-e139-4141-9cc1-56c260afdf3d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='5066d6c9-e139-4141-9cc1-56c260afdf3d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '5066d6c9-e139-4141-9cc1-56c260afdf3d' ), (select reporting_description from clinlims.test where guid = '5066d6c9-e139-4141-9cc1-56c260afdf3d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='5066d6c9-e139-4141-9cc1-56c260afdf3d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '3d2e401b-9678-4ff7-a2bf-04818090f0f7' ), (select name from clinlims.test where guid = '3d2e401b-9678-4ff7-a2bf-04818090f0f7' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3d2e401b-9678-4ff7-a2bf-04818090f0f7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '3d2e401b-9678-4ff7-a2bf-04818090f0f7' ), (select reporting_description from clinlims.test where guid = '3d2e401b-9678-4ff7-a2bf-04818090f0f7' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3d2e401b-9678-4ff7-a2bf-04818090f0f7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'e6e29040-77dd-4475-a54f-6668f03e4325' ), (select name from clinlims.test where guid = 'e6e29040-77dd-4475-a54f-6668f03e4325' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='e6e29040-77dd-4475-a54f-6668f03e4325';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'e6e29040-77dd-4475-a54f-6668f03e4325' ), (select reporting_description from clinlims.test where guid = 'e6e29040-77dd-4475-a54f-6668f03e4325' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='e6e29040-77dd-4475-a54f-6668f03e4325';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '2274203d-f2ef-4e55-8e3b-4bb666612581' ), (select name from clinlims.test where guid = '2274203d-f2ef-4e55-8e3b-4bb666612581' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='2274203d-f2ef-4e55-8e3b-4bb666612581';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '2274203d-f2ef-4e55-8e3b-4bb666612581' ), (select reporting_description from clinlims.test where guid = '2274203d-f2ef-4e55-8e3b-4bb666612581' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='2274203d-f2ef-4e55-8e3b-4bb666612581';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '5cc24738-b159-40e3-99e9-1cca39b30d07' ), (select name from clinlims.test where guid = '5cc24738-b159-40e3-99e9-1cca39b30d07' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='5cc24738-b159-40e3-99e9-1cca39b30d07';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '5cc24738-b159-40e3-99e9-1cca39b30d07' ), (select reporting_description from clinlims.test where guid = '5cc24738-b159-40e3-99e9-1cca39b30d07' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='5cc24738-b159-40e3-99e9-1cca39b30d07';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'dbf45e62-d508-4f56-8e2e-a9148ab2d6bd' ), (select name from clinlims.test where guid = 'dbf45e62-d508-4f56-8e2e-a9148ab2d6bd' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='dbf45e62-d508-4f56-8e2e-a9148ab2d6bd';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'dbf45e62-d508-4f56-8e2e-a9148ab2d6bd' ), (select reporting_description from clinlims.test where guid = 'dbf45e62-d508-4f56-8e2e-a9148ab2d6bd' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='dbf45e62-d508-4f56-8e2e-a9148ab2d6bd';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '89aef902-2a0d-49c8-a495-aae2d6a4ffba' ), (select name from clinlims.test where guid = '89aef902-2a0d-49c8-a495-aae2d6a4ffba' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='89aef902-2a0d-49c8-a495-aae2d6a4ffba';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '89aef902-2a0d-49c8-a495-aae2d6a4ffba' ), (select reporting_description from clinlims.test where guid = '89aef902-2a0d-49c8-a495-aae2d6a4ffba' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='89aef902-2a0d-49c8-a495-aae2d6a4ffba';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'c5d03f2a-01f0-4dcf-b9fb-13cce493c1ec' ), (select name from clinlims.test where guid = 'c5d03f2a-01f0-4dcf-b9fb-13cce493c1ec' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='c5d03f2a-01f0-4dcf-b9fb-13cce493c1ec';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'c5d03f2a-01f0-4dcf-b9fb-13cce493c1ec' ), (select reporting_description from clinlims.test where guid = 'c5d03f2a-01f0-4dcf-b9fb-13cce493c1ec' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='c5d03f2a-01f0-4dcf-b9fb-13cce493c1ec';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '218cf7ba-af46-4bc9-9b90-ee61ea42fb1d' ), (select name from clinlims.test where guid = '218cf7ba-af46-4bc9-9b90-ee61ea42fb1d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='218cf7ba-af46-4bc9-9b90-ee61ea42fb1d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '218cf7ba-af46-4bc9-9b90-ee61ea42fb1d' ), (select reporting_description from clinlims.test where guid = '218cf7ba-af46-4bc9-9b90-ee61ea42fb1d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='218cf7ba-af46-4bc9-9b90-ee61ea42fb1d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '2331e7db-eea9-404c-99d9-4bfc79b9407e' ), (select name from clinlims.test where guid = '2331e7db-eea9-404c-99d9-4bfc79b9407e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='2331e7db-eea9-404c-99d9-4bfc79b9407e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '2331e7db-eea9-404c-99d9-4bfc79b9407e' ), (select reporting_description from clinlims.test where guid = '2331e7db-eea9-404c-99d9-4bfc79b9407e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='2331e7db-eea9-404c-99d9-4bfc79b9407e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '20f7a3f0-b9c6-4aa1-b961-7bf5542bf022' ), (select name from clinlims.test where guid = '20f7a3f0-b9c6-4aa1-b961-7bf5542bf022' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='20f7a3f0-b9c6-4aa1-b961-7bf5542bf022';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '20f7a3f0-b9c6-4aa1-b961-7bf5542bf022' ), (select reporting_description from clinlims.test where guid = '20f7a3f0-b9c6-4aa1-b961-7bf5542bf022' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='20f7a3f0-b9c6-4aa1-b961-7bf5542bf022';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'fc686ed7-2ae8-418b-8e4e-d51a132e9a6f' ), (select name from clinlims.test where guid = 'fc686ed7-2ae8-418b-8e4e-d51a132e9a6f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='fc686ed7-2ae8-418b-8e4e-d51a132e9a6f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'fc686ed7-2ae8-418b-8e4e-d51a132e9a6f' ), (select reporting_description from clinlims.test where guid = 'fc686ed7-2ae8-418b-8e4e-d51a132e9a6f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='fc686ed7-2ae8-418b-8e4e-d51a132e9a6f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'fc317edb-6393-49d4-b49b-5f3e860e4bb4' ), (select name from clinlims.test where guid = 'fc317edb-6393-49d4-b49b-5f3e860e4bb4' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='fc317edb-6393-49d4-b49b-5f3e860e4bb4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'fc317edb-6393-49d4-b49b-5f3e860e4bb4' ), (select reporting_description from clinlims.test where guid = 'fc317edb-6393-49d4-b49b-5f3e860e4bb4' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='fc317edb-6393-49d4-b49b-5f3e860e4bb4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'e7b7c98b-ef77-4f5d-84b7-5043578f446f' ), (select name from clinlims.test where guid = 'e7b7c98b-ef77-4f5d-84b7-5043578f446f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='e7b7c98b-ef77-4f5d-84b7-5043578f446f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'e7b7c98b-ef77-4f5d-84b7-5043578f446f' ), (select reporting_description from clinlims.test where guid = 'e7b7c98b-ef77-4f5d-84b7-5043578f446f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='e7b7c98b-ef77-4f5d-84b7-5043578f446f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '330bb1d6-ba15-4627-a279-59a8ffced06f' ), (select name from clinlims.test where guid = '330bb1d6-ba15-4627-a279-59a8ffced06f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='330bb1d6-ba15-4627-a279-59a8ffced06f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '330bb1d6-ba15-4627-a279-59a8ffced06f' ), (select reporting_description from clinlims.test where guid = '330bb1d6-ba15-4627-a279-59a8ffced06f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='330bb1d6-ba15-4627-a279-59a8ffced06f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '681090cd-94d1-4ad1-aef2-6301132b19af' ), (select name from clinlims.test where guid = '681090cd-94d1-4ad1-aef2-6301132b19af' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='681090cd-94d1-4ad1-aef2-6301132b19af';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '681090cd-94d1-4ad1-aef2-6301132b19af' ), (select reporting_description from clinlims.test where guid = '681090cd-94d1-4ad1-aef2-6301132b19af' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='681090cd-94d1-4ad1-aef2-6301132b19af';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '59b0022d-a7ea-4d47-b26a-901a26b44725' ), (select name from clinlims.test where guid = '59b0022d-a7ea-4d47-b26a-901a26b44725' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='59b0022d-a7ea-4d47-b26a-901a26b44725';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '59b0022d-a7ea-4d47-b26a-901a26b44725' ), (select reporting_description from clinlims.test where guid = '59b0022d-a7ea-4d47-b26a-901a26b44725' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='59b0022d-a7ea-4d47-b26a-901a26b44725';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '9bf514df-4c96-4bd6-817b-8d2f09a1063d' ), (select name from clinlims.test where guid = '9bf514df-4c96-4bd6-817b-8d2f09a1063d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='9bf514df-4c96-4bd6-817b-8d2f09a1063d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '9bf514df-4c96-4bd6-817b-8d2f09a1063d' ), (select reporting_description from clinlims.test where guid = '9bf514df-4c96-4bd6-817b-8d2f09a1063d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='9bf514df-4c96-4bd6-817b-8d2f09a1063d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '84be9637-0342-4e41-9356-55e83c2a9bb3' ), (select name from clinlims.test where guid = '84be9637-0342-4e41-9356-55e83c2a9bb3' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='84be9637-0342-4e41-9356-55e83c2a9bb3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '84be9637-0342-4e41-9356-55e83c2a9bb3' ), (select reporting_description from clinlims.test where guid = '84be9637-0342-4e41-9356-55e83c2a9bb3' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='84be9637-0342-4e41-9356-55e83c2a9bb3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '0f23691d-bd71-48f8-9f84-b4f3a16c4791' ), (select name from clinlims.test where guid = '0f23691d-bd71-48f8-9f84-b4f3a16c4791' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='0f23691d-bd71-48f8-9f84-b4f3a16c4791';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '0f23691d-bd71-48f8-9f84-b4f3a16c4791' ), (select reporting_description from clinlims.test where guid = '0f23691d-bd71-48f8-9f84-b4f3a16c4791' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='0f23691d-bd71-48f8-9f84-b4f3a16c4791';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '738e76f0-d699-4934-823e-dba3fbc5fa3f' ), (select name from clinlims.test where guid = '738e76f0-d699-4934-823e-dba3fbc5fa3f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='738e76f0-d699-4934-823e-dba3fbc5fa3f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '738e76f0-d699-4934-823e-dba3fbc5fa3f' ), (select reporting_description from clinlims.test where guid = '738e76f0-d699-4934-823e-dba3fbc5fa3f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='738e76f0-d699-4934-823e-dba3fbc5fa3f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '7d6cfee7-9b25-4ffa-b3e0-8e29ba9333d2' ), (select name from clinlims.test where guid = '7d6cfee7-9b25-4ffa-b3e0-8e29ba9333d2' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='7d6cfee7-9b25-4ffa-b3e0-8e29ba9333d2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '7d6cfee7-9b25-4ffa-b3e0-8e29ba9333d2' ), (select reporting_description from clinlims.test where guid = '7d6cfee7-9b25-4ffa-b3e0-8e29ba9333d2' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='7d6cfee7-9b25-4ffa-b3e0-8e29ba9333d2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '3ca25c54-2d44-48e0-a5cf-6b459fc9474d' ), (select name from clinlims.test where guid = '3ca25c54-2d44-48e0-a5cf-6b459fc9474d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3ca25c54-2d44-48e0-a5cf-6b459fc9474d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '3ca25c54-2d44-48e0-a5cf-6b459fc9474d' ), (select reporting_description from clinlims.test where guid = '3ca25c54-2d44-48e0-a5cf-6b459fc9474d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3ca25c54-2d44-48e0-a5cf-6b459fc9474d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '97609956-815a-40e9-a7aa-e4ab747927ce' ), (select name from clinlims.test where guid = '97609956-815a-40e9-a7aa-e4ab747927ce' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='97609956-815a-40e9-a7aa-e4ab747927ce';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '97609956-815a-40e9-a7aa-e4ab747927ce' ), (select reporting_description from clinlims.test where guid = '97609956-815a-40e9-a7aa-e4ab747927ce' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='97609956-815a-40e9-a7aa-e4ab747927ce';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '0f28a281-019f-4ae2-8eea-21795bbff815' ), (select name from clinlims.test where guid = '0f28a281-019f-4ae2-8eea-21795bbff815' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='0f28a281-019f-4ae2-8eea-21795bbff815';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '0f28a281-019f-4ae2-8eea-21795bbff815' ), (select reporting_description from clinlims.test where guid = '0f28a281-019f-4ae2-8eea-21795bbff815' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='0f28a281-019f-4ae2-8eea-21795bbff815';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'fe28dc3b-700e-422b-a25e-4564ca5ed6ad' ), (select name from clinlims.test where guid = 'fe28dc3b-700e-422b-a25e-4564ca5ed6ad' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='fe28dc3b-700e-422b-a25e-4564ca5ed6ad';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'fe28dc3b-700e-422b-a25e-4564ca5ed6ad' ), (select reporting_description from clinlims.test where guid = 'fe28dc3b-700e-422b-a25e-4564ca5ed6ad' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='fe28dc3b-700e-422b-a25e-4564ca5ed6ad';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'ae769fd9-e7c1-46b2-866b-733ed7f2fe38' ), (select name from clinlims.test where guid = 'ae769fd9-e7c1-46b2-866b-733ed7f2fe38' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='ae769fd9-e7c1-46b2-866b-733ed7f2fe38';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'ae769fd9-e7c1-46b2-866b-733ed7f2fe38' ), (select reporting_description from clinlims.test where guid = 'ae769fd9-e7c1-46b2-866b-733ed7f2fe38' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='ae769fd9-e7c1-46b2-866b-733ed7f2fe38';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '5d0e85ad-ba81-4353-a15c-cf063adf1c0c' ), (select name from clinlims.test where guid = '5d0e85ad-ba81-4353-a15c-cf063adf1c0c' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='5d0e85ad-ba81-4353-a15c-cf063adf1c0c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '5d0e85ad-ba81-4353-a15c-cf063adf1c0c' ), (select reporting_description from clinlims.test where guid = '5d0e85ad-ba81-4353-a15c-cf063adf1c0c' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='5d0e85ad-ba81-4353-a15c-cf063adf1c0c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'a2b6c6b2-be30-4094-86fc-6dd1eacb09fa' ), (select name from clinlims.test where guid = 'a2b6c6b2-be30-4094-86fc-6dd1eacb09fa' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a2b6c6b2-be30-4094-86fc-6dd1eacb09fa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'a2b6c6b2-be30-4094-86fc-6dd1eacb09fa' ), (select reporting_description from clinlims.test where guid = 'a2b6c6b2-be30-4094-86fc-6dd1eacb09fa' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a2b6c6b2-be30-4094-86fc-6dd1eacb09fa';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '32819dde-c3ff-485e-b4e9-d7028bb5ff11' ), (select name from clinlims.test where guid = '32819dde-c3ff-485e-b4e9-d7028bb5ff11' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='32819dde-c3ff-485e-b4e9-d7028bb5ff11';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '32819dde-c3ff-485e-b4e9-d7028bb5ff11' ), (select reporting_description from clinlims.test where guid = '32819dde-c3ff-485e-b4e9-d7028bb5ff11' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='32819dde-c3ff-485e-b4e9-d7028bb5ff11';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '45b9d455-bc10-4e3b-92a1-edb929250911' ), (select name from clinlims.test where guid = '45b9d455-bc10-4e3b-92a1-edb929250911' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='45b9d455-bc10-4e3b-92a1-edb929250911';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '45b9d455-bc10-4e3b-92a1-edb929250911' ), (select reporting_description from clinlims.test where guid = '45b9d455-bc10-4e3b-92a1-edb929250911' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='45b9d455-bc10-4e3b-92a1-edb929250911';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '924a8e72-01a4-4f45-af1d-0478aaf52642' ), (select name from clinlims.test where guid = '924a8e72-01a4-4f45-af1d-0478aaf52642' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='924a8e72-01a4-4f45-af1d-0478aaf52642';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '924a8e72-01a4-4f45-af1d-0478aaf52642' ), (select reporting_description from clinlims.test where guid = '924a8e72-01a4-4f45-af1d-0478aaf52642' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='924a8e72-01a4-4f45-af1d-0478aaf52642';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '52f83df8-9e57-4193-9746-fc999e063db5' ), (select name from clinlims.test where guid = '52f83df8-9e57-4193-9746-fc999e063db5' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='52f83df8-9e57-4193-9746-fc999e063db5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '52f83df8-9e57-4193-9746-fc999e063db5' ), (select reporting_description from clinlims.test where guid = '52f83df8-9e57-4193-9746-fc999e063db5' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='52f83df8-9e57-4193-9746-fc999e063db5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'd2906e5a-1e52-40f6-82af-22aad60b0b30' ), (select name from clinlims.test where guid = 'd2906e5a-1e52-40f6-82af-22aad60b0b30' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d2906e5a-1e52-40f6-82af-22aad60b0b30';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'd2906e5a-1e52-40f6-82af-22aad60b0b30' ), (select reporting_description from clinlims.test where guid = 'd2906e5a-1e52-40f6-82af-22aad60b0b30' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d2906e5a-1e52-40f6-82af-22aad60b0b30';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '0c6fdb51-a501-41c3-a6ef-cf8b4a546eb2' ), (select name from clinlims.test where guid = '0c6fdb51-a501-41c3-a6ef-cf8b4a546eb2' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='0c6fdb51-a501-41c3-a6ef-cf8b4a546eb2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '0c6fdb51-a501-41c3-a6ef-cf8b4a546eb2' ), (select reporting_description from clinlims.test where guid = '0c6fdb51-a501-41c3-a6ef-cf8b4a546eb2' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='0c6fdb51-a501-41c3-a6ef-cf8b4a546eb2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '55b401b5-1cae-4765-a922-72e413808db7' ), (select name from clinlims.test where guid = '55b401b5-1cae-4765-a922-72e413808db7' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='55b401b5-1cae-4765-a922-72e413808db7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '55b401b5-1cae-4765-a922-72e413808db7' ), (select reporting_description from clinlims.test where guid = '55b401b5-1cae-4765-a922-72e413808db7' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='55b401b5-1cae-4765-a922-72e413808db7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '337bad6a-956e-4e48-bdd4-c683ce186a9e' ), (select name from clinlims.test where guid = '337bad6a-956e-4e48-bdd4-c683ce186a9e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='337bad6a-956e-4e48-bdd4-c683ce186a9e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '337bad6a-956e-4e48-bdd4-c683ce186a9e' ), (select reporting_description from clinlims.test where guid = '337bad6a-956e-4e48-bdd4-c683ce186a9e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='337bad6a-956e-4e48-bdd4-c683ce186a9e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'f17a13da-050a-4380-874f-5a9b319eab28' ), (select name from clinlims.test where guid = 'f17a13da-050a-4380-874f-5a9b319eab28' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='f17a13da-050a-4380-874f-5a9b319eab28';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'f17a13da-050a-4380-874f-5a9b319eab28' ), (select reporting_description from clinlims.test where guid = 'f17a13da-050a-4380-874f-5a9b319eab28' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='f17a13da-050a-4380-874f-5a9b319eab28';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'a5ac96e2-0848-4619-a769-7a9ca569b71a' ), (select name from clinlims.test where guid = 'a5ac96e2-0848-4619-a769-7a9ca569b71a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a5ac96e2-0848-4619-a769-7a9ca569b71a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'a5ac96e2-0848-4619-a769-7a9ca569b71a' ), (select reporting_description from clinlims.test where guid = 'a5ac96e2-0848-4619-a769-7a9ca569b71a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a5ac96e2-0848-4619-a769-7a9ca569b71a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '1e1b4ca5-f97d-4204-8054-4ce999296cc4' ), (select name from clinlims.test where guid = '1e1b4ca5-f97d-4204-8054-4ce999296cc4' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='1e1b4ca5-f97d-4204-8054-4ce999296cc4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '1e1b4ca5-f97d-4204-8054-4ce999296cc4' ), (select reporting_description from clinlims.test where guid = '1e1b4ca5-f97d-4204-8054-4ce999296cc4' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='1e1b4ca5-f97d-4204-8054-4ce999296cc4';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '4e63a51a-ff24-45c4-a7f3-a1544c8a2284' ), (select name from clinlims.test where guid = '4e63a51a-ff24-45c4-a7f3-a1544c8a2284' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='4e63a51a-ff24-45c4-a7f3-a1544c8a2284';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '4e63a51a-ff24-45c4-a7f3-a1544c8a2284' ), (select reporting_description from clinlims.test where guid = '4e63a51a-ff24-45c4-a7f3-a1544c8a2284' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='4e63a51a-ff24-45c4-a7f3-a1544c8a2284';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'd6a9bb41-49f7-477d-8709-7d9ff8916bf2' ), (select name from clinlims.test where guid = 'd6a9bb41-49f7-477d-8709-7d9ff8916bf2' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d6a9bb41-49f7-477d-8709-7d9ff8916bf2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'd6a9bb41-49f7-477d-8709-7d9ff8916bf2' ), (select reporting_description from clinlims.test where guid = 'd6a9bb41-49f7-477d-8709-7d9ff8916bf2' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d6a9bb41-49f7-477d-8709-7d9ff8916bf2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '035496bf-b0d0-40c2-95e5-c59c516bf48f' ), (select name from clinlims.test where guid = '035496bf-b0d0-40c2-95e5-c59c516bf48f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='035496bf-b0d0-40c2-95e5-c59c516bf48f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '035496bf-b0d0-40c2-95e5-c59c516bf48f' ), (select reporting_description from clinlims.test where guid = '035496bf-b0d0-40c2-95e5-c59c516bf48f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='035496bf-b0d0-40c2-95e5-c59c516bf48f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'c6f3d8f2-3269-41c0-9301-ceac45670b21' ), (select name from clinlims.test where guid = 'c6f3d8f2-3269-41c0-9301-ceac45670b21' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='c6f3d8f2-3269-41c0-9301-ceac45670b21';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'c6f3d8f2-3269-41c0-9301-ceac45670b21' ), (select reporting_description from clinlims.test where guid = 'c6f3d8f2-3269-41c0-9301-ceac45670b21' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='c6f3d8f2-3269-41c0-9301-ceac45670b21';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'f3315523-0ff5-432e-85a5-db26fb96969f' ), (select name from clinlims.test where guid = 'f3315523-0ff5-432e-85a5-db26fb96969f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='f3315523-0ff5-432e-85a5-db26fb96969f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'f3315523-0ff5-432e-85a5-db26fb96969f' ), (select reporting_description from clinlims.test where guid = 'f3315523-0ff5-432e-85a5-db26fb96969f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='f3315523-0ff5-432e-85a5-db26fb96969f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '5839bf75-8a50-499b-99d4-2f6bc2bd3513' ), (select name from clinlims.test where guid = '5839bf75-8a50-499b-99d4-2f6bc2bd3513' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='5839bf75-8a50-499b-99d4-2f6bc2bd3513';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '5839bf75-8a50-499b-99d4-2f6bc2bd3513' ), (select reporting_description from clinlims.test where guid = '5839bf75-8a50-499b-99d4-2f6bc2bd3513' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='5839bf75-8a50-499b-99d4-2f6bc2bd3513';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '9b3f8dcd-2b80-4de5-9a73-2d6868e70e4d' ), (select name from clinlims.test where guid = '9b3f8dcd-2b80-4de5-9a73-2d6868e70e4d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='9b3f8dcd-2b80-4de5-9a73-2d6868e70e4d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '9b3f8dcd-2b80-4de5-9a73-2d6868e70e4d' ), (select reporting_description from clinlims.test where guid = '9b3f8dcd-2b80-4de5-9a73-2d6868e70e4d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='9b3f8dcd-2b80-4de5-9a73-2d6868e70e4d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'e28d7a63-51e9-430f-9eb5-ec8cb6c125f6' ), (select name from clinlims.test where guid = 'e28d7a63-51e9-430f-9eb5-ec8cb6c125f6' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='e28d7a63-51e9-430f-9eb5-ec8cb6c125f6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'e28d7a63-51e9-430f-9eb5-ec8cb6c125f6' ), (select reporting_description from clinlims.test where guid = 'e28d7a63-51e9-430f-9eb5-ec8cb6c125f6' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='e28d7a63-51e9-430f-9eb5-ec8cb6c125f6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '13816b0a-5421-49bd-8866-2b24a95247d3' ), (select name from clinlims.test where guid = '13816b0a-5421-49bd-8866-2b24a95247d3' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='13816b0a-5421-49bd-8866-2b24a95247d3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '13816b0a-5421-49bd-8866-2b24a95247d3' ), (select reporting_description from clinlims.test where guid = '13816b0a-5421-49bd-8866-2b24a95247d3' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='13816b0a-5421-49bd-8866-2b24a95247d3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '4644ff5e-5388-4229-96a2-be4fb0728144' ), (select name from clinlims.test where guid = '4644ff5e-5388-4229-96a2-be4fb0728144' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='4644ff5e-5388-4229-96a2-be4fb0728144';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '4644ff5e-5388-4229-96a2-be4fb0728144' ), (select reporting_description from clinlims.test where guid = '4644ff5e-5388-4229-96a2-be4fb0728144' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='4644ff5e-5388-4229-96a2-be4fb0728144';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'e558f9eb-99be-49ca-9ced-6ac853e38c0b' ), (select name from clinlims.test where guid = 'e558f9eb-99be-49ca-9ced-6ac853e38c0b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='e558f9eb-99be-49ca-9ced-6ac853e38c0b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'e558f9eb-99be-49ca-9ced-6ac853e38c0b' ), (select reporting_description from clinlims.test where guid = 'e558f9eb-99be-49ca-9ced-6ac853e38c0b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='e558f9eb-99be-49ca-9ced-6ac853e38c0b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'ff41012f-a876-4371-8ba1-7bdf4669b76b' ), (select name from clinlims.test where guid = 'ff41012f-a876-4371-8ba1-7bdf4669b76b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='ff41012f-a876-4371-8ba1-7bdf4669b76b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'ff41012f-a876-4371-8ba1-7bdf4669b76b' ), (select reporting_description from clinlims.test where guid = 'ff41012f-a876-4371-8ba1-7bdf4669b76b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='ff41012f-a876-4371-8ba1-7bdf4669b76b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'e267ccef-fb05-4d5a-9513-6f6fd57ba718' ), (select name from clinlims.test where guid = 'e267ccef-fb05-4d5a-9513-6f6fd57ba718' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='e267ccef-fb05-4d5a-9513-6f6fd57ba718';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'e267ccef-fb05-4d5a-9513-6f6fd57ba718' ), (select reporting_description from clinlims.test where guid = 'e267ccef-fb05-4d5a-9513-6f6fd57ba718' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='e267ccef-fb05-4d5a-9513-6f6fd57ba718';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '1c212ca5-461f-4206-9019-4efb636a743e' ), (select name from clinlims.test where guid = '1c212ca5-461f-4206-9019-4efb636a743e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='1c212ca5-461f-4206-9019-4efb636a743e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '1c212ca5-461f-4206-9019-4efb636a743e' ), (select reporting_description from clinlims.test where guid = '1c212ca5-461f-4206-9019-4efb636a743e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='1c212ca5-461f-4206-9019-4efb636a743e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '9babff43-e328-4404-b5bc-0e11942ccf26' ), (select name from clinlims.test where guid = '9babff43-e328-4404-b5bc-0e11942ccf26' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='9babff43-e328-4404-b5bc-0e11942ccf26';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '9babff43-e328-4404-b5bc-0e11942ccf26' ), (select reporting_description from clinlims.test where guid = '9babff43-e328-4404-b5bc-0e11942ccf26' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='9babff43-e328-4404-b5bc-0e11942ccf26';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '6b1411ba-2b99-4de6-9891-c5cd8cbe582d' ), (select name from clinlims.test where guid = '6b1411ba-2b99-4de6-9891-c5cd8cbe582d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='6b1411ba-2b99-4de6-9891-c5cd8cbe582d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '6b1411ba-2b99-4de6-9891-c5cd8cbe582d' ), (select reporting_description from clinlims.test where guid = '6b1411ba-2b99-4de6-9891-c5cd8cbe582d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='6b1411ba-2b99-4de6-9891-c5cd8cbe582d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'c57c63a0-764b-43e4-88f8-c6e545f796c9' ), (select name from clinlims.test where guid = 'c57c63a0-764b-43e4-88f8-c6e545f796c9' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='c57c63a0-764b-43e4-88f8-c6e545f796c9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'c57c63a0-764b-43e4-88f8-c6e545f796c9' ), (select reporting_description from clinlims.test where guid = 'c57c63a0-764b-43e4-88f8-c6e545f796c9' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='c57c63a0-764b-43e4-88f8-c6e545f796c9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'fd2848f8-ea9a-4314-ac73-5b8322c62e34' ), (select name from clinlims.test where guid = 'fd2848f8-ea9a-4314-ac73-5b8322c62e34' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='fd2848f8-ea9a-4314-ac73-5b8322c62e34';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'fd2848f8-ea9a-4314-ac73-5b8322c62e34' ), (select reporting_description from clinlims.test where guid = 'fd2848f8-ea9a-4314-ac73-5b8322c62e34' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='fd2848f8-ea9a-4314-ac73-5b8322c62e34';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '2f4ca81c-4da9-43b6-a2a8-58e6bc0b25b9' ), (select name from clinlims.test where guid = '2f4ca81c-4da9-43b6-a2a8-58e6bc0b25b9' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='2f4ca81c-4da9-43b6-a2a8-58e6bc0b25b9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '2f4ca81c-4da9-43b6-a2a8-58e6bc0b25b9' ), (select reporting_description from clinlims.test where guid = '2f4ca81c-4da9-43b6-a2a8-58e6bc0b25b9' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='2f4ca81c-4da9-43b6-a2a8-58e6bc0b25b9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'a611b55a-b720-4415-8b8a-aaa74d2260ad' ), (select name from clinlims.test where guid = 'a611b55a-b720-4415-8b8a-aaa74d2260ad' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a611b55a-b720-4415-8b8a-aaa74d2260ad';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'a611b55a-b720-4415-8b8a-aaa74d2260ad' ), (select reporting_description from clinlims.test where guid = 'a611b55a-b720-4415-8b8a-aaa74d2260ad' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a611b55a-b720-4415-8b8a-aaa74d2260ad';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'c4c84284-b220-44dc-b636-c1a952dad637' ), (select name from clinlims.test where guid = 'c4c84284-b220-44dc-b636-c1a952dad637' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='c4c84284-b220-44dc-b636-c1a952dad637';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'c4c84284-b220-44dc-b636-c1a952dad637' ), (select reporting_description from clinlims.test where guid = 'c4c84284-b220-44dc-b636-c1a952dad637' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='c4c84284-b220-44dc-b636-c1a952dad637';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '67b449fc-86bf-45e5-8c8a-dec9bc8452a8' ), (select name from clinlims.test where guid = '67b449fc-86bf-45e5-8c8a-dec9bc8452a8' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='67b449fc-86bf-45e5-8c8a-dec9bc8452a8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '67b449fc-86bf-45e5-8c8a-dec9bc8452a8' ), (select reporting_description from clinlims.test where guid = '67b449fc-86bf-45e5-8c8a-dec9bc8452a8' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='67b449fc-86bf-45e5-8c8a-dec9bc8452a8';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'aa84857e-29a9-4d23-a716-aaa69bf3262e' ), (select name from clinlims.test where guid = 'aa84857e-29a9-4d23-a716-aaa69bf3262e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='aa84857e-29a9-4d23-a716-aaa69bf3262e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'aa84857e-29a9-4d23-a716-aaa69bf3262e' ), (select reporting_description from clinlims.test where guid = 'aa84857e-29a9-4d23-a716-aaa69bf3262e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='aa84857e-29a9-4d23-a716-aaa69bf3262e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'b6af5f95-0d6e-47fe-b807-fee697562494' ), (select name from clinlims.test where guid = 'b6af5f95-0d6e-47fe-b807-fee697562494' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='b6af5f95-0d6e-47fe-b807-fee697562494';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'b6af5f95-0d6e-47fe-b807-fee697562494' ), (select reporting_description from clinlims.test where guid = 'b6af5f95-0d6e-47fe-b807-fee697562494' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='b6af5f95-0d6e-47fe-b807-fee697562494';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'd140b7bf-4ed8-4c65-ac4c-457aaaf8ee83' ), (select name from clinlims.test where guid = 'd140b7bf-4ed8-4c65-ac4c-457aaaf8ee83' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d140b7bf-4ed8-4c65-ac4c-457aaaf8ee83';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'd140b7bf-4ed8-4c65-ac4c-457aaaf8ee83' ), (select reporting_description from clinlims.test where guid = 'd140b7bf-4ed8-4c65-ac4c-457aaaf8ee83' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d140b7bf-4ed8-4c65-ac4c-457aaaf8ee83';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '7f8a2d7d-83e6-49c6-a67a-eee593d4c713' ), (select name from clinlims.test where guid = '7f8a2d7d-83e6-49c6-a67a-eee593d4c713' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='7f8a2d7d-83e6-49c6-a67a-eee593d4c713';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '7f8a2d7d-83e6-49c6-a67a-eee593d4c713' ), (select reporting_description from clinlims.test where guid = '7f8a2d7d-83e6-49c6-a67a-eee593d4c713' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='7f8a2d7d-83e6-49c6-a67a-eee593d4c713';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'd9ca5ac5-8e27-4185-b015-f13f6f5c1389' ), (select name from clinlims.test where guid = 'd9ca5ac5-8e27-4185-b015-f13f6f5c1389' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d9ca5ac5-8e27-4185-b015-f13f6f5c1389';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'd9ca5ac5-8e27-4185-b015-f13f6f5c1389' ), (select reporting_description from clinlims.test where guid = 'd9ca5ac5-8e27-4185-b015-f13f6f5c1389' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d9ca5ac5-8e27-4185-b015-f13f6f5c1389';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'b149f11e-e940-4da0-8a48-5f6750c76984' ), (select name from clinlims.test where guid = 'b149f11e-e940-4da0-8a48-5f6750c76984' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='b149f11e-e940-4da0-8a48-5f6750c76984';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'b149f11e-e940-4da0-8a48-5f6750c76984' ), (select reporting_description from clinlims.test where guid = 'b149f11e-e940-4da0-8a48-5f6750c76984' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='b149f11e-e940-4da0-8a48-5f6750c76984';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'f8fcdfc7-40fd-4efd-91ef-fe2a78d82337' ), (select name from clinlims.test where guid = 'f8fcdfc7-40fd-4efd-91ef-fe2a78d82337' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='f8fcdfc7-40fd-4efd-91ef-fe2a78d82337';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'f8fcdfc7-40fd-4efd-91ef-fe2a78d82337' ), (select reporting_description from clinlims.test where guid = 'f8fcdfc7-40fd-4efd-91ef-fe2a78d82337' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='f8fcdfc7-40fd-4efd-91ef-fe2a78d82337';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '3ee39f16-d7d4-4463-a799-46ee5440a3a6' ), (select name from clinlims.test where guid = '3ee39f16-d7d4-4463-a799-46ee5440a3a6' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3ee39f16-d7d4-4463-a799-46ee5440a3a6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '3ee39f16-d7d4-4463-a799-46ee5440a3a6' ), (select reporting_description from clinlims.test where guid = '3ee39f16-d7d4-4463-a799-46ee5440a3a6' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3ee39f16-d7d4-4463-a799-46ee5440a3a6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '7cb9e673-1d1c-48ed-9503-bd2aceebde3c' ), (select name from clinlims.test where guid = '7cb9e673-1d1c-48ed-9503-bd2aceebde3c' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='7cb9e673-1d1c-48ed-9503-bd2aceebde3c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '7cb9e673-1d1c-48ed-9503-bd2aceebde3c' ), (select reporting_description from clinlims.test where guid = '7cb9e673-1d1c-48ed-9503-bd2aceebde3c' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='7cb9e673-1d1c-48ed-9503-bd2aceebde3c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'ceccf466-05d7-4603-bf73-e87b82a2147a' ), (select name from clinlims.test where guid = 'ceccf466-05d7-4603-bf73-e87b82a2147a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='ceccf466-05d7-4603-bf73-e87b82a2147a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'ceccf466-05d7-4603-bf73-e87b82a2147a' ), (select reporting_description from clinlims.test where guid = 'ceccf466-05d7-4603-bf73-e87b82a2147a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='ceccf466-05d7-4603-bf73-e87b82a2147a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'ee350d9b-68fb-40c4-9fd7-4fdf25f6bd75' ), (select name from clinlims.test where guid = 'ee350d9b-68fb-40c4-9fd7-4fdf25f6bd75' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='ee350d9b-68fb-40c4-9fd7-4fdf25f6bd75';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'ee350d9b-68fb-40c4-9fd7-4fdf25f6bd75' ), (select reporting_description from clinlims.test where guid = 'ee350d9b-68fb-40c4-9fd7-4fdf25f6bd75' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='ee350d9b-68fb-40c4-9fd7-4fdf25f6bd75';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '0b7c5a45-82c4-4f23-a9da-4201da88bd06' ), (select name from clinlims.test where guid = '0b7c5a45-82c4-4f23-a9da-4201da88bd06' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='0b7c5a45-82c4-4f23-a9da-4201da88bd06';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '0b7c5a45-82c4-4f23-a9da-4201da88bd06' ), (select reporting_description from clinlims.test where guid = '0b7c5a45-82c4-4f23-a9da-4201da88bd06' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='0b7c5a45-82c4-4f23-a9da-4201da88bd06';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'b0bdc1e6-18b5-4902-ad16-d2ac24cc97e7' ), (select name from clinlims.test where guid = 'b0bdc1e6-18b5-4902-ad16-d2ac24cc97e7' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='b0bdc1e6-18b5-4902-ad16-d2ac24cc97e7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'b0bdc1e6-18b5-4902-ad16-d2ac24cc97e7' ), (select reporting_description from clinlims.test where guid = 'b0bdc1e6-18b5-4902-ad16-d2ac24cc97e7' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='b0bdc1e6-18b5-4902-ad16-d2ac24cc97e7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '3cf8dbb7-d5b5-49bc-ad62-14483c63471f' ), (select name from clinlims.test where guid = '3cf8dbb7-d5b5-49bc-ad62-14483c63471f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3cf8dbb7-d5b5-49bc-ad62-14483c63471f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '3cf8dbb7-d5b5-49bc-ad62-14483c63471f' ), (select reporting_description from clinlims.test where guid = '3cf8dbb7-d5b5-49bc-ad62-14483c63471f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3cf8dbb7-d5b5-49bc-ad62-14483c63471f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '312c368e-cffa-4fab-85ec-f33c24fde5e3' ), (select name from clinlims.test where guid = '312c368e-cffa-4fab-85ec-f33c24fde5e3' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='312c368e-cffa-4fab-85ec-f33c24fde5e3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '312c368e-cffa-4fab-85ec-f33c24fde5e3' ), (select reporting_description from clinlims.test where guid = '312c368e-cffa-4fab-85ec-f33c24fde5e3' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='312c368e-cffa-4fab-85ec-f33c24fde5e3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'fbec00cf-a90d-4dee-8dd3-1415bca5d0e5' ), (select name from clinlims.test where guid = 'fbec00cf-a90d-4dee-8dd3-1415bca5d0e5' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='fbec00cf-a90d-4dee-8dd3-1415bca5d0e5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'fbec00cf-a90d-4dee-8dd3-1415bca5d0e5' ), (select reporting_description from clinlims.test where guid = 'fbec00cf-a90d-4dee-8dd3-1415bca5d0e5' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='fbec00cf-a90d-4dee-8dd3-1415bca5d0e5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '44197e54-6023-4de3-a8b7-e5cc44ad5d9e' ), (select name from clinlims.test where guid = '44197e54-6023-4de3-a8b7-e5cc44ad5d9e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='44197e54-6023-4de3-a8b7-e5cc44ad5d9e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '44197e54-6023-4de3-a8b7-e5cc44ad5d9e' ), (select reporting_description from clinlims.test where guid = '44197e54-6023-4de3-a8b7-e5cc44ad5d9e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='44197e54-6023-4de3-a8b7-e5cc44ad5d9e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'b5e98406-f27e-4380-8b12-9f20b19df220' ), (select name from clinlims.test where guid = 'b5e98406-f27e-4380-8b12-9f20b19df220' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='b5e98406-f27e-4380-8b12-9f20b19df220';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'b5e98406-f27e-4380-8b12-9f20b19df220' ), (select reporting_description from clinlims.test where guid = 'b5e98406-f27e-4380-8b12-9f20b19df220' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='b5e98406-f27e-4380-8b12-9f20b19df220';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '1ef4c686-89da-4bec-b86b-a144b8c0003d' ), (select name from clinlims.test where guid = '1ef4c686-89da-4bec-b86b-a144b8c0003d' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='1ef4c686-89da-4bec-b86b-a144b8c0003d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '1ef4c686-89da-4bec-b86b-a144b8c0003d' ), (select reporting_description from clinlims.test where guid = '1ef4c686-89da-4bec-b86b-a144b8c0003d' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='1ef4c686-89da-4bec-b86b-a144b8c0003d';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '0dc9e82a-9cc2-44fe-9e40-719d5ef0282c' ), (select name from clinlims.test where guid = '0dc9e82a-9cc2-44fe-9e40-719d5ef0282c' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='0dc9e82a-9cc2-44fe-9e40-719d5ef0282c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '0dc9e82a-9cc2-44fe-9e40-719d5ef0282c' ), (select reporting_description from clinlims.test where guid = '0dc9e82a-9cc2-44fe-9e40-719d5ef0282c' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='0dc9e82a-9cc2-44fe-9e40-719d5ef0282c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'e48c9a02-c6c8-41f9-ab5b-f75fdcb610ab' ), (select name from clinlims.test where guid = 'e48c9a02-c6c8-41f9-ab5b-f75fdcb610ab' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='e48c9a02-c6c8-41f9-ab5b-f75fdcb610ab';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'e48c9a02-c6c8-41f9-ab5b-f75fdcb610ab' ), (select reporting_description from clinlims.test where guid = 'e48c9a02-c6c8-41f9-ab5b-f75fdcb610ab' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='e48c9a02-c6c8-41f9-ab5b-f75fdcb610ab';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'f6112e71-e35b-4e8f-b7a5-d040fae746f3' ), (select name from clinlims.test where guid = 'f6112e71-e35b-4e8f-b7a5-d040fae746f3' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='f6112e71-e35b-4e8f-b7a5-d040fae746f3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'f6112e71-e35b-4e8f-b7a5-d040fae746f3' ), (select reporting_description from clinlims.test where guid = 'f6112e71-e35b-4e8f-b7a5-d040fae746f3' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='f6112e71-e35b-4e8f-b7a5-d040fae746f3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '34d32a6e-4025-480b-8c28-3c2eb4721a2e' ), (select name from clinlims.test where guid = '34d32a6e-4025-480b-8c28-3c2eb4721a2e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='34d32a6e-4025-480b-8c28-3c2eb4721a2e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '34d32a6e-4025-480b-8c28-3c2eb4721a2e' ), (select reporting_description from clinlims.test where guid = '34d32a6e-4025-480b-8c28-3c2eb4721a2e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='34d32a6e-4025-480b-8c28-3c2eb4721a2e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '8fef4c5f-bda3-4b11-b856-5dff11d47073' ), (select name from clinlims.test where guid = '8fef4c5f-bda3-4b11-b856-5dff11d47073' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='8fef4c5f-bda3-4b11-b856-5dff11d47073';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '8fef4c5f-bda3-4b11-b856-5dff11d47073' ), (select reporting_description from clinlims.test where guid = '8fef4c5f-bda3-4b11-b856-5dff11d47073' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='8fef4c5f-bda3-4b11-b856-5dff11d47073';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '6cea5299-f1fd-4f27-b103-f5f869dbc599' ), (select name from clinlims.test where guid = '6cea5299-f1fd-4f27-b103-f5f869dbc599' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='6cea5299-f1fd-4f27-b103-f5f869dbc599';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '6cea5299-f1fd-4f27-b103-f5f869dbc599' ), (select reporting_description from clinlims.test where guid = '6cea5299-f1fd-4f27-b103-f5f869dbc599' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='6cea5299-f1fd-4f27-b103-f5f869dbc599';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '845ccd49-34ff-4441-bf18-028896b28a48' ), (select name from clinlims.test where guid = '845ccd49-34ff-4441-bf18-028896b28a48' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='845ccd49-34ff-4441-bf18-028896b28a48';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '845ccd49-34ff-4441-bf18-028896b28a48' ), (select reporting_description from clinlims.test where guid = '845ccd49-34ff-4441-bf18-028896b28a48' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='845ccd49-34ff-4441-bf18-028896b28a48';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'ccdde51a-0f5a-4c93-938e-b4028f536d8c' ), (select name from clinlims.test where guid = 'ccdde51a-0f5a-4c93-938e-b4028f536d8c' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='ccdde51a-0f5a-4c93-938e-b4028f536d8c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'ccdde51a-0f5a-4c93-938e-b4028f536d8c' ), (select reporting_description from clinlims.test where guid = 'ccdde51a-0f5a-4c93-938e-b4028f536d8c' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='ccdde51a-0f5a-4c93-938e-b4028f536d8c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'bde9f4d4-9376-49a5-81f8-e553c5adb47c' ), (select name from clinlims.test where guid = 'bde9f4d4-9376-49a5-81f8-e553c5adb47c' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='bde9f4d4-9376-49a5-81f8-e553c5adb47c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'bde9f4d4-9376-49a5-81f8-e553c5adb47c' ), (select reporting_description from clinlims.test where guid = 'bde9f4d4-9376-49a5-81f8-e553c5adb47c' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='bde9f4d4-9376-49a5-81f8-e553c5adb47c';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'be643fe6-bb89-48e6-8df9-002a4650e015' ), (select name from clinlims.test where guid = 'be643fe6-bb89-48e6-8df9-002a4650e015' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='be643fe6-bb89-48e6-8df9-002a4650e015';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'be643fe6-bb89-48e6-8df9-002a4650e015' ), (select reporting_description from clinlims.test where guid = 'be643fe6-bb89-48e6-8df9-002a4650e015' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='be643fe6-bb89-48e6-8df9-002a4650e015';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '04985866-73b0-47aa-afe4-16d7514a16d7' ), (select name from clinlims.test where guid = '04985866-73b0-47aa-afe4-16d7514a16d7' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='04985866-73b0-47aa-afe4-16d7514a16d7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '04985866-73b0-47aa-afe4-16d7514a16d7' ), (select reporting_description from clinlims.test where guid = '04985866-73b0-47aa-afe4-16d7514a16d7' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='04985866-73b0-47aa-afe4-16d7514a16d7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '4e041aed-f9af-44f7-8175-a872e2999bd1' ), (select name from clinlims.test where guid = '4e041aed-f9af-44f7-8175-a872e2999bd1' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='4e041aed-f9af-44f7-8175-a872e2999bd1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '4e041aed-f9af-44f7-8175-a872e2999bd1' ), (select reporting_description from clinlims.test where guid = '4e041aed-f9af-44f7-8175-a872e2999bd1' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='4e041aed-f9af-44f7-8175-a872e2999bd1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '891ee1a4-b75d-487f-80c8-43eb8d9609ec' ), (select name from clinlims.test where guid = '891ee1a4-b75d-487f-80c8-43eb8d9609ec' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='891ee1a4-b75d-487f-80c8-43eb8d9609ec';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '891ee1a4-b75d-487f-80c8-43eb8d9609ec' ), (select reporting_description from clinlims.test where guid = '891ee1a4-b75d-487f-80c8-43eb8d9609ec' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='891ee1a4-b75d-487f-80c8-43eb8d9609ec';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'd915a18b-342b-4ddb-9b91-086abed6c020' ), (select name from clinlims.test where guid = 'd915a18b-342b-4ddb-9b91-086abed6c020' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d915a18b-342b-4ddb-9b91-086abed6c020';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'd915a18b-342b-4ddb-9b91-086abed6c020' ), (select reporting_description from clinlims.test where guid = 'd915a18b-342b-4ddb-9b91-086abed6c020' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d915a18b-342b-4ddb-9b91-086abed6c020';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '01dee550-2b71-4708-a4ec-fb9e9c2edee9' ), (select name from clinlims.test where guid = '01dee550-2b71-4708-a4ec-fb9e9c2edee9' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='01dee550-2b71-4708-a4ec-fb9e9c2edee9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '01dee550-2b71-4708-a4ec-fb9e9c2edee9' ), (select reporting_description from clinlims.test where guid = '01dee550-2b71-4708-a4ec-fb9e9c2edee9' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='01dee550-2b71-4708-a4ec-fb9e9c2edee9';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Color', (select name from clinlims.test where guid = '7b4ede29-807a-418a-bb2c-c04c37b6efce' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='7b4ede29-807a-418a-bb2c-c04c37b6efce';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Color', (select reporting_description from clinlims.test where guid = '7b4ede29-807a-418a-bb2c-c04c37b6efce' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='7b4ede29-807a-418a-bb2c-c04c37b6efce';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Consistance', (select name from clinlims.test where guid = '5115de8a-c698-4863-9929-625c741492af' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='5115de8a-c698-4863-9929-625c741492af';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Consistance', (select reporting_description from clinlims.test where guid = '5115de8a-c698-4863-9929-625c741492af' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='5115de8a-c698-4863-9929-625c741492af';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '09bfb2ae-2827-4ea6-b416-305b3339cdff' ), (select name from clinlims.test where guid = '09bfb2ae-2827-4ea6-b416-305b3339cdff' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='09bfb2ae-2827-4ea6-b416-305b3339cdff';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '09bfb2ae-2827-4ea6-b416-305b3339cdff' ), (select reporting_description from clinlims.test where guid = '09bfb2ae-2827-4ea6-b416-305b3339cdff' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='09bfb2ae-2827-4ea6-b416-305b3339cdff';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '8fa92250-a092-4cc9-a77b-998a582249be' ), (select name from clinlims.test where guid = '8fa92250-a092-4cc9-a77b-998a582249be' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='8fa92250-a092-4cc9-a77b-998a582249be';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '8fa92250-a092-4cc9-a77b-998a582249be' ), (select reporting_description from clinlims.test where guid = '8fa92250-a092-4cc9-a77b-998a582249be' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='8fa92250-a092-4cc9-a77b-998a582249be';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'd5a11a80-fff8-4ca9-a71c-79b29089306b' ), (select name from clinlims.test where guid = 'd5a11a80-fff8-4ca9-a71c-79b29089306b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d5a11a80-fff8-4ca9-a71c-79b29089306b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'd5a11a80-fff8-4ca9-a71c-79b29089306b' ), (select reporting_description from clinlims.test where guid = 'd5a11a80-fff8-4ca9-a71c-79b29089306b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d5a11a80-fff8-4ca9-a71c-79b29089306b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Occult blood', (select name from clinlims.test where guid = 'ced863ff-44a5-428c-8eef-0c91e449415e' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='ced863ff-44a5-428c-8eef-0c91e449415e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Occult blood', (select reporting_description from clinlims.test where guid = 'ced863ff-44a5-428c-8eef-0c91e449415e' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='ced863ff-44a5-428c-8eef-0c91e449415e';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Bacteria', (select name from clinlims.test where guid = '99641693-e6ab-4c91-9f35-c532b571168a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='99641693-e6ab-4c91-9f35-c532b571168a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Bacteria', (select reporting_description from clinlims.test where guid = '99641693-e6ab-4c91-9f35-c532b571168a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='99641693-e6ab-4c91-9f35-c532b571168a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Simple yeast', (select name from clinlims.test where guid = '7242e18a-a118-4b14-ac21-1617d9dbe5e1' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='7242e18a-a118-4b14-ac21-1617d9dbe5e1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Simple yeast', (select reporting_description from clinlims.test where guid = '7242e18a-a118-4b14-ac21-1617d9dbe5e1' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='7242e18a-a118-4b14-ac21-1617d9dbe5e1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Yeast', (select name from clinlims.test where guid = 'a05849d1-91dc-4af7-a1e7-2e7b8b44b292' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a05849d1-91dc-4af7-a1e7-2e7b8b44b292';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Yeast', (select reporting_description from clinlims.test where guid = 'a05849d1-91dc-4af7-a1e7-2e7b8b44b292' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a05849d1-91dc-4af7-a1e7-2e7b8b44b292';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Protozoa search', (select name from clinlims.test where guid = '3bc658fc-a5a3-4cbe-8267-e6a5a1f300a2' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3bc658fc-a5a3-4cbe-8267-e6a5a1f300a2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Protozoa search', (select reporting_description from clinlims.test where guid = '3bc658fc-a5a3-4cbe-8267-e6a5a1f300a2' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3bc658fc-a5a3-4cbe-8267-e6a5a1f300a2';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Metazoa search', (select name from clinlims.test where guid = 'c061b0bb-d97e-49dd-b0af-c33263f13877' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='c061b0bb-d97e-49dd-b0af-c33263f13877';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Metazoa search', (select reporting_description from clinlims.test where guid = 'c061b0bb-d97e-49dd-b0af-c33263f13877' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='c061b0bb-d97e-49dd-b0af-c33263f13877';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = '12d88b2a-b25e-4376-a3b4-cdc79d221ef1' ), (select name from clinlims.test where guid = '12d88b2a-b25e-4376-a3b4-cdc79d221ef1' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='12d88b2a-b25e-4376-a3b4-cdc79d221ef1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = '12d88b2a-b25e-4376-a3b4-cdc79d221ef1' ), (select reporting_description from clinlims.test where guid = '12d88b2a-b25e-4376-a3b4-cdc79d221ef1' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='12d88b2a-b25e-4376-a3b4-cdc79d221ef1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Scotch tape', (select name from clinlims.test where guid = 'e86fb344-6e19-44b8-aa87-cb0a2fe528b7' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='e86fb344-6e19-44b8-aa87-cb0a2fe528b7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Scotch tape', (select reporting_description from clinlims.test where guid = 'e86fb344-6e19-44b8-aa87-cb0a2fe528b7' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='e86fb344-6e19-44b8-aa87-cb0a2fe528b7';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Modified Ziehl Neelsen', (select name from clinlims.test where guid = 'ae8074b8-50cd-44f1-90dc-3279e37e4260' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='ae8074b8-50cd-44f1-90dc-3279e37e4260';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Modified Ziehl Neelsen', (select reporting_description from clinlims.test where guid = 'ae8074b8-50cd-44f1-90dc-3279e37e4260' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='ae8074b8-50cd-44f1-90dc-3279e37e4260';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Malaria Smear - Species', (select name from clinlims.test where guid = 'c2262c09-7559-4431-9443-d29c93e438b1' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='c2262c09-7559-4431-9443-d29c93e438b1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Malaria Smear - Species', (select reporting_description from clinlims.test where guid = 'c2262c09-7559-4431-9443-d29c93e438b1' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='c2262c09-7559-4431-9443-d29c93e438b1';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Malaria Rapid Test', (select name from clinlims.test where guid = '442e4bc6-5290-4933-ba6e-9b794850747f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='442e4bc6-5290-4933-ba6e-9b794850747f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Malaria Rapid Test', (select reporting_description from clinlims.test where guid = '442e4bc6-5290-4933-ba6e-9b794850747f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='442e4bc6-5290-4933-ba6e-9b794850747f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Microfilaire Search', (select name from clinlims.test where guid = '16e19781-d1eb-4515-8704-b5aa3fb34731' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='16e19781-d1eb-4515-8704-b5aa3fb34731';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Microfilaire Search', (select reporting_description from clinlims.test where guid = '16e19781-d1eb-4515-8704-b5aa3fb34731' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='16e19781-d1eb-4515-8704-b5aa3fb34731';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'AFB by Ziehl Neelsen', (select name from clinlims.test where guid = '6a6ada6f-3345-471c-9216-1d4294d5567f' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='6a6ada6f-3345-471c-9216-1d4294d5567f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'AFB by Ziehl Neelsen', (select reporting_description from clinlims.test where guid = '6a6ada6f-3345-471c-9216-1d4294d5567f' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='6a6ada6f-3345-471c-9216-1d4294d5567f';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'AFB by Ziehl Neelsen', (select name from clinlims.test where guid = '9fee3c5a-5a96-4148-a7ad-7f73e786f8f3' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='9fee3c5a-5a96-4148-a7ad-7f73e786f8f3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'AFB by Ziehl Neelsen', (select reporting_description from clinlims.test where guid = '9fee3c5a-5a96-4148-a7ad-7f73e786f8f3' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='9fee3c5a-5a96-4148-a7ad-7f73e786f8f3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'AFB by Ziehl Neelsen', (select name from clinlims.test where guid = '997fb1ad-a2b3-4ca1-b68a-cd4615c7d3d6' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='997fb1ad-a2b3-4ca1-b68a-cd4615c7d3d6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'AFB by Ziehl Neelsen', (select reporting_description from clinlims.test where guid = '997fb1ad-a2b3-4ca1-b68a-cd4615c7d3d6' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='997fb1ad-a2b3-4ca1-b68a-cd4615c7d3d6';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'AFB by Fluorochrom', (select name from clinlims.test where guid = '3d1754f7-9f43-40dd-92ac-3eca63962dea' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='3d1754f7-9f43-40dd-92ac-3eca63962dea';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'AFB by Fluorochrom', (select reporting_description from clinlims.test where guid = '3d1754f7-9f43-40dd-92ac-3eca63962dea' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='3d1754f7-9f43-40dd-92ac-3eca63962dea';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'AFB by Fluorochrom', (select name from clinlims.test where guid = 'fc2e60aa-9851-4e58-bb35-8df55babe27b' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='fc2e60aa-9851-4e58-bb35-8df55babe27b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'AFB by Fluorochrom', (select reporting_description from clinlims.test where guid = 'fc2e60aa-9851-4e58-bb35-8df55babe27b' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='fc2e60aa-9851-4e58-bb35-8df55babe27b';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'AFB by Fluorochrom', (select name from clinlims.test where guid = 'd058707c-63e9-4e3e-bd2c-b3b7395505b5' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='d058707c-63e9-4e3e-bd2c-b3b7395505b5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'AFB by Fluorochrom', (select reporting_description from clinlims.test where guid = 'd058707c-63e9-4e3e-bd2c-b3b7395505b5' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='d058707c-63e9-4e3e-bd2c-b3b7395505b5';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'LJ Culture', (select name from clinlims.test where guid = '2707fa96-8119-484e-9ec8-36e43d8790ba' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='2707fa96-8119-484e-9ec8-36e43d8790ba';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'LJ Culture', (select reporting_description from clinlims.test where guid = '2707fa96-8119-484e-9ec8-36e43d8790ba' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='2707fa96-8119-484e-9ec8-36e43d8790ba';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', 'Milieu Mgit', (select name from clinlims.test where guid = '229f403c-5989-4e69-aab6-17576ede1c37' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='229f403c-5989-4e69-aab6-17576ede1c37';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', 'Milieu Mgit', (select reporting_description from clinlims.test where guid = '229f403c-5989-4e69-aab6-17576ede1c37' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='229f403c-5989-4e69-aab6-17576ede1c37';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'a54de6b5-f02d-4a28-bc18-fa6b2f6ce4d3' ), (select name from clinlims.test where guid = 'a54de6b5-f02d-4a28-bc18-fa6b2f6ce4d3' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='a54de6b5-f02d-4a28-bc18-fa6b2f6ce4d3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'a54de6b5-f02d-4a28-bc18-fa6b2f6ce4d3' ), (select reporting_description from clinlims.test where guid = 'a54de6b5-f02d-4a28-bc18-fa6b2f6ce4d3' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='a54de6b5-f02d-4a28-bc18-fa6b2f6ce4d3';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test name', (select name from clinlims.test where guid = 'bc377184-0706-43c1-b077-7f842d9f429a' ), (select name from clinlims.test where guid = 'bc377184-0706-43c1-b077-7f842d9f429a' ), now());
update clinlims.test set name_localization_id = lastval() where guid ='bc377184-0706-43c1-b077-7f842d9f429a';
INSERT INTO localization(  id, description, english, french, lastupdated)
	VALUES ( nextval('localization_seq'), 'test report name', (select reporting_description from clinlims.test where guid = 'bc377184-0706-43c1-b077-7f842d9f429a' ), (select reporting_description from clinlims.test where guid = 'bc377184-0706-43c1-b077-7f842d9f429a' ), now());
update clinlims.test set reporting_name_localization_id = lastval() where guid ='bc377184-0706-43c1-b077-7f842d9f429a';
