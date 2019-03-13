DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Yeast Cells (>5/hpf)(Urine)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Urine') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Yeast Cells (>5/hpf)(Urine)' )  ,    (select id from clinlims.type_of_sample where description = 'Urine')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Red Blood Cells (>5/hpf)(Urine)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Urine') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Red Blood Cells (>5/hpf)(Urine)' )  ,    (select id from clinlims.type_of_sample where description = 'Urine')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Leukocytes(Urine)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Urine') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Leukocytes(Urine)' )  ,    (select id from clinlims.type_of_sample where description = 'Urine')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Other Microscopic Findings(Urine)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Urine') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Other Microscopic Findings(Urine)' )  ,    (select id from clinlims.type_of_sample where description = 'Urine')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Glucose(Urine)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Urine') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Glucose(Urine)' )  ,    (select id from clinlims.type_of_sample where description = 'Urine')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Ketones(Urine)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Urine') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Ketones(Urine)' )  ,    (select id from clinlims.type_of_sample where description = 'Urine')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Blood(Urine)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Urine') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Blood(Urine)' )  ,    (select id from clinlims.type_of_sample where description = 'Urine')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Bilirubin(Urine)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Urine') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Bilirubin(Urine)' )  ,    (select id from clinlims.type_of_sample where description = 'Urine')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Specific Gravity(Urine)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Urine') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Specific Gravity(Urine)' )  ,    (select id from clinlims.type_of_sample where description = 'Urine')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'pH(Urine)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Urine') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'pH(Urine)' )  ,    (select id from clinlims.type_of_sample where description = 'Urine')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Protein(Urine)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Urine') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Protein(Urine)' )  ,    (select id from clinlims.type_of_sample where description = 'Urine')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Nitrite(Urine)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Urine') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Nitrite(Urine)' )  ,    (select id from clinlims.type_of_sample where description = 'Urine')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Urobilinogen Phenlpyruvic Acid(Urine)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Urine') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Urobilinogen Phenlpyruvic Acid(Urine)' )  ,    (select id from clinlims.type_of_sample where description = 'Urine')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Pregnancy Test(Urine)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Urine') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Pregnancy Test(Urine)' )  ,    (select id from clinlims.type_of_sample where description = 'Urine')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Pus Cells (>5/hpf)(Urine)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Urine') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Pus Cells (>5/hpf)(Urine)' )  ,    (select id from clinlims.type_of_sample where description = 'Urine')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'S. haematobium(Genital Swab)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Genital Swab') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'S. haematobium(Genital Swab)' )  ,    (select id from clinlims.type_of_sample where description = 'Genital Swab')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'T. vaginalis(Genital Swab)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Genital Swab') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'T. vaginalis(Genital Swab)' )  ,    (select id from clinlims.type_of_sample where description = 'Genital Swab')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Yeast Cells(Genital Swab)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Genital Swab') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Yeast Cells(Genital Swab)' )  ,    (select id from clinlims.type_of_sample where description = 'Genital Swab')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Thick/Thin Smear for Malaria(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Thick/Thin Smear for Malaria(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Malaria rapid test(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Malaria rapid test(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Blood film for hemoparasites(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Blood film for hemoparasites(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Wet mount microscopy(Genital Specimen)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Genital Specimen') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Wet mount microscopy(Genital Specimen)' )  ,    (select id from clinlims.type_of_sample where description = 'Genital Specimen')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Stool exam(Stool)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Stool') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Stool exam(Stool)' )  ,    (select id from clinlims.type_of_sample where description = 'Stool')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Parasites examination(Skin)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Skin') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Parasites examination(Skin)' )  ,    (select id from clinlims.type_of_sample where description = 'Skin')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Parasites examination(Bone Marrow)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Bone Marrow') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Parasites examination(Bone Marrow)' )  ,    (select id from clinlims.type_of_sample where description = 'Bone Marrow')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'India ink(CSF)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'CSF') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'India ink(CSF)' )  ,    (select id from clinlims.type_of_sample where description = 'CSF')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'KOH(Genital Specimen)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Genital Specimen') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'KOH(Genital Specimen)' )  ,    (select id from clinlims.type_of_sample where description = 'Genital Specimen')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Occult blood(Stool)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Stool') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Occult blood(Stool)' )  ,    (select id from clinlims.type_of_sample where description = 'Stool')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'KOH(Skin)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Skin') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'KOH(Skin)' )  ,    (select id from clinlims.type_of_sample where description = 'Skin')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Bence-Jones Protein(Urine)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Urine') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Bence-Jones Protein(Urine)' )  ,    (select id from clinlims.type_of_sample where description = 'Urine')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'HIV Rapid Test - KHB(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'HIV Rapid Test - KHB(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'HIV Rapid Test - First Response(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'HIV Rapid Test - First Response(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'HIV Rapid Test - Uni-Gold(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'HIV Rapid Test - Uni-Gold(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'HIV EIA(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'HIV EIA(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'HIV Antigen Test(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'HIV Antigen Test(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Measles(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Measles(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Rubella(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Rubella(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Rapid Plasma Reagin(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Rapid Plasma Reagin(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'TPHA(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'TPHA(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'ASO Test(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'ASO Test(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Salmonella antigen test(Stool)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Stool') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Salmonella antigen test(Stool)' )  ,    (select id from clinlims.type_of_sample where description = 'Stool')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Salmonella antigen test(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Salmonella antigen test(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Widal test(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Widal test(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Brucella test(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Brucella test(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Rheumatoid Factor Tests(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Rheumatoid Factor Tests(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Cryptococcal Antigen(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Cryptococcal Antigen(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Cryptococcal Antigen(CSF)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'CSF') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Cryptococcal Antigen(CSF)' )  ,    (select id from clinlims.type_of_sample where description = 'CSF')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Helicobacter pylori test(Stool)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Stool') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Helicobacter pylori test(Stool)' )  ,    (select id from clinlims.type_of_sample where description = 'Stool')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Hepatitis A Test(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Hepatitis A Test(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Hepatitis B Test(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Hepatitis B Test(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Hepatitis C Test(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Hepatitis C Test(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'WBC Count(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'WBC Count(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Neutrophil, Absolute(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Neutrophil, Absolute(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Lymphocyte, Absolute(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Lymphocyte, Absolute(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Monocyte, Absolute(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Monocyte, Absolute(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Eosinophil, Absolute(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Eosinophil, Absolute(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Basophil, Absolute(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Basophil, Absolute(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Neutrophil(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Neutrophil(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Lymphocyte(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Lymphocyte(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Monocyte(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Monocyte(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Eosinophil(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Eosinophil(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Basophil(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Basophil(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'RBC Count - Male(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'RBC Count - Male(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'RBC Count - Female(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'RBC Count - Female(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Hemoglobin - Male(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Hemoglobin - Male(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Hemoglobin - Female(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Hemoglobin - Female(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Hematocrit - Male(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Hematocrit - Male(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Hematocrit - Female(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Hematocrit - Female(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'MCV(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'MCV(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'MCH(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'MCH(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'MCHC(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'MCHC(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'RDW(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'RDW(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'PLT(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'PLT(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'PCT(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'PCT(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'MPV(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'MPV(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'PDW(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'PDW(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Hb electrophoresis(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Hb electrophoresis(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'G6PD screening(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'G6PD screening(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Bleeding Time(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Bleeding Time(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Thrombin Clotting Time(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Thrombin Clotting Time(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Prothrombin Time(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Prothrombin Time(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Partial prothrombin time(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Partial prothrombin time(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Bone Marrow Aspirates(Bone marrow)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Bone marrow') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Bone Marrow Aspirates(Bone marrow)' )  ,    (select id from clinlims.type_of_sample where description = 'Bone marrow')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Erythrocyte Sedimentation rate (ESR)(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Erythrocyte Sedimentation rate (ESR)(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Reticulocyte counts %(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Reticulocyte counts %(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Haemoglobin - HemoCue(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Haemoglobin - HemoCue(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'CD4 %(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'CD4 %(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'CD4, Absolute(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'CD4, Absolute(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'CD4:CD8 ratio(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'CD4:CD8 ratio(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Direct bilirubin(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Direct bilirubin(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Total bilirubin(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Total bilirubin(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'SGPT/ALT - Male(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'SGPT/ALT - Male(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'SGPT/ALT - Female(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'SGPT/ALT - Female(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'SGOT/AST(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'SGOT/AST(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Serum Protein(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Serum Protein(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Albumin(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Albumin(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Alkaline Phosphatase - Male(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Alkaline Phosphatase - Male(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Alkaline Phosphatase - Female(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Alkaline Phosphatase - Female(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Gamma GT(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Gamma GT(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Amylase(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Amylase(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Creatinine(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Creatinine(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Urea(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Urea(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Sodium(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Sodium(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Potassium(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Potassium(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Chloride(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Chloride(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Bicarbonate(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Bicarbonate(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Creatinine Clearance(Urine)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Urine') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Creatinine Clearance(Urine)' )  ,    (select id from clinlims.type_of_sample where description = 'Urine')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Total cholestrol(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Total cholestrol(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Trigycerides(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Trigycerides(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'HDL(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'HDL(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'LDL(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'LDL(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'PSA - Total(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'PSA - Total(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'PSA - Free(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'PSA - Free(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Proteins(CSF)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'CSF') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Proteins(CSF)' )  ,    (select id from clinlims.type_of_sample where description = 'CSF')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Glucose(CSF)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'CSF') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Glucose(CSF)' )  ,    (select id from clinlims.type_of_sample where description = 'CSF')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Glucose - Fasting (Finger-Stick Test)(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Glucose - Fasting (Finger-Stick Test)(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Glucose - Random (Finger-Stick Test)(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Glucose - Random (Finger-Stick Test)(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Glucose-Fasting (Serum/Plasma)(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Glucose-Fasting (Serum/Plasma)(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Glucose- Random(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Glucose- Random(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Glucose-2 HR PC(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Glucose-2 HR PC(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Acid phosphatase(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Acid phosphatase(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Triiodothyronine (T3)(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Triiodothyronine (T3)(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Thyroid-stimulating Hormone (TSH)(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Thyroid-stimulating Hormone (TSH)(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Bacterial culture(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Bacterial culture(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Bacterial culture(Urine)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Urine') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Bacterial culture(Urine)' )  ,    (select id from clinlims.type_of_sample where description = 'Urine')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Bacterial culture(Pus swab)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Pus swab') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Bacterial culture(Pus swab)' )  ,    (select id from clinlims.type_of_sample where description = 'Pus swab')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Bacterial culture(Vaginal swab)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Vaginal swab') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Bacterial culture(Vaginal swab)' )  ,    (select id from clinlims.type_of_sample where description = 'Vaginal swab')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Bacterial culture(Respiratory specimen)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Respiratory specimen') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Bacterial culture(Respiratory specimen)' )  ,    (select id from clinlims.type_of_sample where description = 'Respiratory specimen')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Bacterial culture(Rectal swab)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Rectal swab') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Bacterial culture(Rectal swab)' )  ,    (select id from clinlims.type_of_sample where description = 'Rectal swab')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Bacterial culture(Urethral swab)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Urethral swab') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Bacterial culture(Urethral swab)' )  ,    (select id from clinlims.type_of_sample where description = 'Urethral swab')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Bacterial culture(CSF)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'CSF') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Bacterial culture(CSF)' )  ,    (select id from clinlims.type_of_sample where description = 'CSF')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Bacterial culture(Aspirates)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Aspirates') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Bacterial culture(Aspirates)' )  ,    (select id from clinlims.type_of_sample where description = 'Aspirates')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Bacterial culture(Eye swab)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Eye swab') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Bacterial culture(Eye swab)' )  ,    (select id from clinlims.type_of_sample where description = 'Eye swab')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Bacterial culture(Ear swab)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Ear swab') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Bacterial culture(Ear swab)' )  ,    (select id from clinlims.type_of_sample where description = 'Ear swab')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Drug Sensitivity(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Drug Sensitivity(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Drug Sensitivity(Urine)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Urine') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Drug Sensitivity(Urine)' )  ,    (select id from clinlims.type_of_sample where description = 'Urine')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Drug Sensitivity(Pus swab)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Pus swab') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Drug Sensitivity(Pus swab)' )  ,    (select id from clinlims.type_of_sample where description = 'Pus swab')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Drug Sensitivity(Vaginal swab)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Vaginal swab') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Drug Sensitivity(Vaginal swab)' )  ,    (select id from clinlims.type_of_sample where description = 'Vaginal swab')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Drug Sensitivity(Respiratory specimen)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Respiratory specimen') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Drug Sensitivity(Respiratory specimen)' )  ,    (select id from clinlims.type_of_sample where description = 'Respiratory specimen')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Drug Sensitivity(Rectal swab)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Rectal swab') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Drug Sensitivity(Rectal swab)' )  ,    (select id from clinlims.type_of_sample where description = 'Rectal swab')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Drug Sensitivity(Urethral swab)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Urethral swab') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Drug Sensitivity(Urethral swab)' )  ,    (select id from clinlims.type_of_sample where description = 'Urethral swab')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Drug Sensitivity(CSF)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'CSF') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Drug Sensitivity(CSF)' )  ,    (select id from clinlims.type_of_sample where description = 'CSF')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Drug Sensitivity(Aspirates)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Aspirates') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Drug Sensitivity(Aspirates)' )  ,    (select id from clinlims.type_of_sample where description = 'Aspirates')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Drug Sensitivity(Eye swab)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Eye swab') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Drug Sensitivity(Eye swab)' )  ,    (select id from clinlims.type_of_sample where description = 'Eye swab')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Drug Sensitivity(Ear swab)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Ear swab') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Drug Sensitivity(Ear swab)' )  ,    (select id from clinlims.type_of_sample where description = 'Ear swab')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Gram stain(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Gram stain(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Gram stain(Urine)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Urine') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Gram stain(Urine)' )  ,    (select id from clinlims.type_of_sample where description = 'Urine')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Gram stain(Pus swab)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Pus swab') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Gram stain(Pus swab)' )  ,    (select id from clinlims.type_of_sample where description = 'Pus swab')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Gram stain(Vaginal swab)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Vaginal swab') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Gram stain(Vaginal swab)' )  ,    (select id from clinlims.type_of_sample where description = 'Vaginal swab')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Gram stain(Respiratory specimen)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Respiratory specimen') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Gram stain(Respiratory specimen)' )  ,    (select id from clinlims.type_of_sample where description = 'Respiratory specimen')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Gram stain(Rectal swab)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Rectal swab') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Gram stain(Rectal swab)' )  ,    (select id from clinlims.type_of_sample where description = 'Rectal swab')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Gram stain(Urethral swab)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Urethral swab') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Gram stain(Urethral swab)' )  ,    (select id from clinlims.type_of_sample where description = 'Urethral swab')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Gram stain(CSF)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'CSF') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Gram stain(CSF)' )  ,    (select id from clinlims.type_of_sample where description = 'CSF')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Gram stain(Aspirates)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Aspirates') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Gram stain(Aspirates)' )  ,    (select id from clinlims.type_of_sample where description = 'Aspirates')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Gram stain(Eye swab)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Eye swab') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Gram stain(Eye swab)' )  ,    (select id from clinlims.type_of_sample where description = 'Eye swab')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Gram stain(Ear swab)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Ear swab') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Gram stain(Ear swab)' )  ,    (select id from clinlims.type_of_sample where description = 'Ear swab')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'AFB stain(Respiratory specimen)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Respiratory specimen') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'AFB stain(Respiratory specimen)' )  ,    (select id from clinlims.type_of_sample where description = 'Respiratory specimen')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'TB fluorescence microscopy(Respiratory specimen)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Respiratory specimen') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'TB fluorescence microscopy(Respiratory specimen)' )  ,    (select id from clinlims.type_of_sample where description = 'Respiratory specimen')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'TB culture(Respiratory specimen)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Respiratory specimen') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'TB culture(Respiratory specimen)' )  ,    (select id from clinlims.type_of_sample where description = 'Respiratory specimen')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Early infant diagnosis HIV PCR(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Early infant diagnosis HIV PCR(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Early infant diagnosis HIV PCR(Dried Blood Spots)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Dried Blood Spots') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Early infant diagnosis HIV PCR(Dried Blood Spots)' )  ,    (select id from clinlims.type_of_sample where description = 'Dried Blood Spots')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'HIV Viral Load(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'HIV Viral Load(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Hepatitis C Qualitative PCR(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Hepatitis C Qualitative PCR(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Hepatitis C Viral Load(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Hepatitis C Viral Load(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Hepatitis B Viral Load(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Hepatitis B Viral Load(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Gene Xpert MTB/RIF(Respiratory specimen)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Respiratory specimen') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Gene Xpert MTB/RIF(Respiratory specimen)' )  ,    (select id from clinlims.type_of_sample where description = 'Respiratory specimen')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'ABO grouping(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'ABO grouping(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Rh grouping(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Rh grouping(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Cross matching(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Cross matching(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Direct Coombs test(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Direct Coombs test(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Indirect Coombs test(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Indirect Coombs test(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Du test(Blood)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Blood') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Du test(Blood)' )  ,    (select id from clinlims.type_of_sample where description = 'Blood')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Tissue Impression(Lymph nodes)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Lymph nodes') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Tissue Impression(Lymph nodes)' )  ,    (select id from clinlims.type_of_sample where description = 'Lymph nodes')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Tissue Impression(Respiratory tract lavage)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Respiratory tract lavage') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Tissue Impression(Respiratory tract lavage)' )  ,    (select id from clinlims.type_of_sample where description = 'Respiratory tract lavage')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Tissue Impression(Pleural fluid)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Pleural fluid') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Tissue Impression(Pleural fluid)' )  ,    (select id from clinlims.type_of_sample where description = 'Pleural fluid')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Tissue Impression(Ascitic fluid)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Ascitic fluid') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Tissue Impression(Ascitic fluid)' )  ,    (select id from clinlims.type_of_sample where description = 'Ascitic fluid')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Tissue Impression(CSF)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'CSF') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Tissue Impression(CSF)' )  ,    (select id from clinlims.type_of_sample where description = 'CSF')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Tissue Impression(Liver tissue)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Liver tissue') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Tissue Impression(Liver tissue)' )  ,    (select id from clinlims.type_of_sample where description = 'Liver tissue')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Tissue Impression(Kidney tissue)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Kidney tissue') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Tissue Impression(Kidney tissue)' )  ,    (select id from clinlims.type_of_sample where description = 'Kidney tissue')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Tissue Impression(Vaginal swab)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Vaginal swab') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Tissue Impression(Vaginal swab)' )  ,    (select id from clinlims.type_of_sample where description = 'Vaginal swab')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'H & E staining(Cervix)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Cervix') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'H & E staining(Cervix)' )  ,    (select id from clinlims.type_of_sample where description = 'Cervix')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'H & E staining(Prostrate)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Prostrate') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'H & E staining(Prostrate)' )  ,    (select id from clinlims.type_of_sample where description = 'Prostrate')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'H & E staining(Breast)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Breast') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'H & E staining(Breast)' )  ,    (select id from clinlims.type_of_sample where description = 'Breast')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'H & E staining(Ovarian cyst)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Ovarian cyst') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'H & E staining(Ovarian cyst)' )  ,    (select id from clinlims.type_of_sample where description = 'Ovarian cyst')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'H & E staining(Fibroids)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Fibroids') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'H & E staining(Fibroids)' )  ,    (select id from clinlims.type_of_sample where description = 'Fibroids')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'H & E staining(Lymph nodes)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Lymph nodes') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'H & E staining(Lymph nodes)' )  ,    (select id from clinlims.type_of_sample where description = 'Lymph nodes')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Prussian Blue Staining(Bone Marrow Biopsy)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Bone Marrow Biopsy') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Prussian Blue Staining(Bone Marrow Biopsy)' )  ,    (select id from clinlims.type_of_sample where description = 'Bone Marrow Biopsy')  );
DELETE from clinlims.sampletype_test where test_id =  (select id from clinlims.test where description = 'Pap smear(Cervical smear)' )  and sample_type_id =  (select id from clinlims.type_of_sample where description = 'Cervical smear') ;
INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES 
	(nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Pap smear(Cervical smear)' )  ,    (select id from clinlims.type_of_sample where description = 'Cervical smear')  );
