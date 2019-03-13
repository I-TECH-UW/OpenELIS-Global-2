update clinlims.test set is_active = 'N' where id  = (select t.id from clinlims.test t, clinlims.sampletype_test st where t.name = 'Coloration de Gram Sang'  and st.sample_type_id = (select id from clinlims.type_of_sample where description = 'Sang Total') and t.id = st.test_id);
update clinlims.test set is_active = 'N' where id  = (select t.id from clinlims.test t, clinlims.sampletype_test st where t.name = 'Dengue'  and st.sample_type_id = (select id from clinlims.type_of_sample where description = 'Plasma') and t.id = st.test_id); 
update clinlims.test set is_active = 'N' where id  = (select t.id from clinlims.test t, clinlims.sampletype_test st where t.name = 'Dengue'  and st.sample_type_id = (select id from clinlims.type_of_sample where description = 'Sérum') and t.id = st.test_id); 

update clinlims.test set is_active = 'N' where id  = (select t.id from clinlims.test t, clinlims.sampletype_test st where t.name = 'Rougeole'  and st.sample_type_id = (select id from clinlims.type_of_sample where description = 'Plasma') and t.id = st.test_id); 
update clinlims.test set is_active = 'N' where id  = (select t.id from clinlims.test t, clinlims.sampletype_test st where t.name = 'Rougeole'  and st.sample_type_id = (select id from clinlims.type_of_sample where description = 'Sérum') and t.id = st.test_id); 

update clinlims.test set is_active = 'N' where id  = (select t.id from clinlims.test t, clinlims.sampletype_test st where t.name = 'Rubeole'  and st.sample_type_id = (select id from clinlims.type_of_sample where description = 'Plasma') and t.id = st.test_id); 
update clinlims.test set is_active = 'N' where id  = (select t.id from clinlims.test t, clinlims.sampletype_test st where t.name = 'Rubeole'  and st.sample_type_id = (select id from clinlims.type_of_sample where description = 'Sérum') and t.id = st.test_id); 

--increase sort_order by 10 fold
update clinlims.test set sort_order = sort_order * 10;
--
--------------------------------------
--Rougeole IgM(Sérum)
INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Rougeole IgM(Sérum)' , 'c' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Virologie' ) ,'Rougeole IgM' ,(select sort_order + 5 from clinlims.test where description = 'VIH Western Blot(DBS)') , 'Rougeole IgM' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Rougeole IgM(Sérum)' )  ,    (select id from clinlims.type_of_sample where description = 'Sérum')  );

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole IgM(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1350, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole IgM(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1360, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole IgM(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Douteux' )  , now() , 1370, false);

--
--Rougeole IgG(Sérum)
INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Rougeole IgG(Sérum)' , 'c' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Virologie' ) ,'Rougeole IgG' ,(select sort_order + 10 from clinlims.test where description = 'VIH Western Blot(DBS)') , 'Rougeole IgG' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Rougeole IgG(Sérum)' )  ,    (select id from clinlims.type_of_sample where description = 'Sérum')  );

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole IgG(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1350, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole IgG(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1360, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole IgG(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Douteux' )  , now() , 1370, false);

--
--Rougeole IgM(Plasma)
INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Rougeole IgM(Plasma)' , 'c' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Virologie' ) ,'Rougeole IgM' ,(select sort_order + 15 from clinlims.test where description = 'VIH Western Blot(DBS)') , 'Rougeole IgM' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Rougeole IgM(Plasma)' )  ,    (select id from clinlims.type_of_sample where description = 'Plasma')  );

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1350, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1360, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Douteux' )  , now() , 1370, false);


--

--Rougeole IgG(Plasma)
INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Rougeole IgG(Plasma)' , 'c' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Virologie' ) ,'Rougeole IgG' ,(select sort_order + 20 from clinlims.test where description = 'VIH Western Blot(DBS)') , 'Rougeole IgG' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Rougeole IgG(Plasma)' )  ,    (select id from clinlims.type_of_sample where description = 'Plasma')  );

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole IgG(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1350, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole IgG(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1360, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Rougeole IgG(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Douteux' )  , now() , 1370, false);

--

---
--Dengue IgM(Sérum)
INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Dengue IgM(Sérum)' , 'c' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Virologie' ) ,'Dengue IgM' ,(select sort_order + 25 from clinlims.test where description = 'VIH Western Blot(DBS)') , 'Dengue IgM' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Dengue IgM(Sérum)' )  ,    (select id from clinlims.type_of_sample where description = 'Sérum')  );

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue IgM(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1350, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue IgM(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1360, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue IgM(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Douteux' )  , now() , 1370, false);

--
--Dengue IgG(Sérum)
INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Dengue IgG(Sérum)' , 'c' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Virologie' ) ,'Dengue IgG' ,(select sort_order + 30 from clinlims.test where description = 'VIH Western Blot(DBS)') , 'Dengue IgG' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Dengue IgG(Sérum)' )  ,    (select id from clinlims.type_of_sample where description = 'Sérum')  );

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue IgG(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1350, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue IgG(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1360, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue IgG(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Douteux' )  , now() , 1370, false);

--
--Dengue IgM(Plasma)
INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Dengue IgM(Plasma)' , 'c' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Virologie' ) ,'Dengue IgM' ,(select sort_order + 35 from clinlims.test where description = 'VIH Western Blot(DBS)') , 'Dengue IgM' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Dengue IgM(Plasma)' )  ,    (select id from clinlims.type_of_sample where description = 'Plasma')  );

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1350, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1360, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Douteux' )  , now() , 1370, false);


--

--Dengue IgG(Plasma)
INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Dengue IgG(Plasma)' , 'c' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Virologie' ) ,'Dengue IgG' ,(select sort_order + 40 from clinlims.test where description = 'VIH Western Blot(DBS)') , 'Dengue IgG' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Dengue IgG(Plasma)' )  ,    (select id from clinlims.type_of_sample where description = 'Plasma')  );

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue IgG(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1350, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue IgG(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1360, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Dengue IgG(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Douteux' )  , now() , 1370, false);

--
--------------------------
--Rubeole IgM(Sérum)
INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Rubeole IgM(Sérum)' , 'c' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Virologie' ) ,'Rubeole IgM' ,(select sort_order + 45 from clinlims.test where description = 'VIH Western Blot(DBS)') , 'Rubeole IgM' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Rubeole IgM(Sérum)' )  ,    (select id from clinlims.type_of_sample where description = 'Sérum')  );

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole IgM(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1350, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole IgM(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1360, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole IgM(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Douteux' )  , now() , 1370, false);

--
--Rubeole IgG(Sérum)
INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Rubeole IgG(Sérum)' , 'c' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Virologie' ) ,'Rubeole IgG' ,(select sort_order + 50 from clinlims.test where description = 'VIH Western Blot(DBS)') , 'Rubeole IgG' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Rubeole IgG(Sérum)' )  ,    (select id from clinlims.type_of_sample where description = 'Sérum')  );

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole IgG(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1350, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole IgG(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1360, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole IgG(Sérum)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Douteux' )  , now() , 1370, false);

--
--Rubeole IgM(Plasma)
INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Rubeole IgM(Plasma)' , 'c' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Virologie' ) ,'Rubeole IgM' ,(select sort_order + 55 from clinlims.test where description = 'VIH Western Blot(DBS)') , 'Rubeole IgM' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Rubeole IgM(Plasma)' )  ,    (select id from clinlims.type_of_sample where description = 'Plasma')  );

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1350, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1360, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole IgM(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Douteux' )  , now() , 1370, false);


--

--Rubeole IgG(Plasma)
INSERT INTO clinlims.test( id,  uom_id, description, reporting_description, is_active, is_reportable, lastupdated, test_section_id, local_abbrev, sort_order, name, loinc )
VALUES ( nextval( 'clinlims.test_seq' ) , null , 'Rubeole IgG(Plasma)' , 'c' , 'Y' , 'N' , now() , (select id from clinlims.test_section where name = 'Virologie' ) ,'Rubeole IgG' ,(select sort_order + 60 from clinlims.test where description = 'VIH Western Blot(DBS)') , 'Rubeole IgG' , '');

INSERT INTO clinlims.sampletype_test (id, test_id , sample_type_id) VALUES
  (nextval( 'clinlims.sample_type_test_seq' ) , (select id from clinlims.test where description = 'Rubeole IgG(Plasma)' )  ,    (select id from clinlims.type_of_sample where description = 'Plasma')  );

INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole IgG(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Negatif' )  , now() , 1350, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole IgG(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Positif' )  , now() , 1360, false);
INSERT INTO clinlims.test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order, is_quantifiable)
	 VALUES ( nextval( 'clinlims.test_result_seq' ) , ( select id from clinlims.test where description = 'Rubeole IgG(Plasma)' ) , 'D' ,  ( select max(id) from clinlims.dictionary where dict_entry ='Douteux' )  , now() , 1370, false);

--
---Germes Pathogènes Gastroenterogastriques
UPDATE clinlims.test
            SET  description=replace(description,'Germes Enterogastriques pathogènes','Germes Pathogènes Gastroenterogastriques'),
            reporting_description=replace(reporting_description,'Germes Enterogastriques pathogènes','new name'),
            name=replace(name,'Germes Enterogastriques pathogènes','Germes Pathogènes Gastroenterogastriques'), lastupdated = now()
            WHERE name='Germes Enterogastriques pathogènes';

--
--LCR Coloration de Gram(LCR)
update clinlims.test set is_active = 'Y' where id  = (select t.id from clinlims.test t, clinlims.sampletype_test st where t.name = 'LCR Coloration de Gram  '  and st.sample_type_id = (select id from clinlims.type_of_sample where description = 'LCR') and t.id = st.test_id); 

UPDATE clinlims.test
            SET  description=replace(description,'LCR Coloration de Gram','Coloration de Gram du LCR'),
            reporting_description=replace(reporting_description,'LCR Coloration de Gram','new name'),
            name=replace(name,'LCR Coloration de Gram','Coloration de Gram du LCR'), lastupdated = now()
            WHERE name='LCR Coloration de Gram';

--
--Sang capillaire
update clinlims.type_of_sample set is_active = 'f' where description = 'Sang capillaire';
--

--Coloration de Gram Sang(Sang Total)
update clinlims.test set is_active = 'N' where id  = (select t.id from clinlims.test t, clinlims.sampletype_test st where t.name = 'Coloration de Gram Sang'  and st.sample_type_id = (select id from clinlims.type_of_sample where description = 'Sang Total') and t.id = st.test_id); 
--

--Leptospirose IgM(serum)
UPDATE clinlims.test
            SET  description=replace(description,'Immuno Dot Leptospira IgM ','Leptospirose IgM'),
            reporting_description=replace(reporting_description,'Immuno Dot Leptospira IgM ','new name'),
            name=replace(name,'Immuno Dot Leptospira IgM ','Leptospirose IgM'), lastupdated = now()
            WHERE name='Immuno Dot Leptospira IgM ';
--

--Recherche de Microfilaires(Sang Total)
update clinlims.test set is_active = 'Y' where  description = 'Recherche de Microfilaires(Sang Total)';
--

--Cryptococcus test rapide
UPDATE clinlims.test
            SET  description=replace(description,'Criptococcus test rapide','Cryptococcus test rapide'),
            reporting_description=replace(reporting_description,'Criptococcus test rapide','new name'),
            name=replace(name,'Criptococcus test rapide','Cryptococcus test rapide'), lastupdated = now()
            WHERE name='Criptococcus test rapide';

--

--Salmonelle typhi IgM 09 Test Rapide 
UPDATE clinlims.test
            SET  description=replace(description,'Test Rapide Salmonelle typhi IgM 09','Salmonelle typhi IgM 09 Test Rapide '),
            reporting_description=replace(reporting_description,'Test Rapide Salmonelle typhi IgM 09','new name'),
            name=replace(name,'Test Rapide Salmonelle typhi IgM 09','Salmonelle typhi IgM 09 Test Rapide '), lastupdated = now()
            WHERE name='Test Rapide Salmonelle typhi IgM 09';

--

--Syphilis Test Rapide
UPDATE clinlims.test
            SET  description=replace(description,'SyphilisTest Rapid','Syphilis Test Rapide'),
            reporting_description=replace(reporting_description,'SyphilisTest Rapid','new name'),
            name=replace(name,'SyphilisTest Rapid','Syphilis Test Rapide'), lastupdated = now()
            WHERE name='SyphilisTest Rapid';

--

--VIH Test Rapide
UPDATE clinlims.test
            SET  description=replace(description,'Test Rapide VIH','VIH Test Rapide'),
            reporting_description=replace(reporting_description,'Test Rapide VIH','new name'),
            name=replace(name,'Test Rapide VIH','VIH Test Rapide'), lastupdated = now()
            WHERE name='Test Rapide VIH';

--

--Hemoculture
UPDATE clinlims.test
            SET  description=replace(description,'Hemoculture IgM','Hemoculture'),
            reporting_description=replace(reporting_description,'Hemoculture IgM','new name'),
            name=replace(name,'Hemoculture IgM','Hemoculture'), lastupdated = now()
            WHERE name='Hemoculture IgM';

--

--Culture du LCR
UPDATE clinlims.test
            SET  description=replace(description,'LCR : Culture','Culture du LCR'),
            reporting_description=replace(reporting_description,'LCR : Culture','new name'),
            name=replace(name,'LCR : Culture','Culture du LCR'), lastupdated = now()
            WHERE name='LCR : Culture';

--

--Recherche du Virus Respiratoire
UPDATE clinlims.test
            SET  description=replace(description,'Recherche de Virus Respiratoire Influenza','Recherche du Virus Respiratoire'),
            reporting_description=replace(reporting_description,'Recherche de Virus Respiratoire Influenza','new name'),
            name=replace(name,'Recherche de Virus Respiratoire Influenza','Recherche du Virus Respiratoire'), lastupdated = now()
            WHERE name='Recherche de Virus Respiratoire Influenza';

--

--Recherche du Virus Chikungunya
UPDATE clinlims.test
            SET  description=replace(description,'Recherche Virus Chikungunya','Recherche du Virus Chikungunya'),
            reporting_description=replace(reporting_description,'Recherche Virus Chikungunya','new name'),
            name=replace(name,'Recherche Virus Chikungunya','Recherche du Virus Chikungunya'), lastupdated = now()
            WHERE name='Recherche Virus Chikungunya';

--

--Rotavirus
UPDATE clinlims.test
            SET  description=replace(description,'Rotavirus Elisa','Rotavirus'),
            reporting_description=replace(reporting_description,'Rotavirus Elisa','new name'),
            name=replace(name,'Rotavirus Elisa','Rotavirus'), lastupdated = now()
            WHERE name='Rotavirus Elisa';

--

update clinlims.test set is_active = 'N' where description in ('Culture(Ecouvillon Naso-Pharynge)','Culture de mycobacteries liquide(Sputum)','Culture de mycobacteries Solide(Sputum)','Dengue(Plasma)','Dengue(Sérum)','Examen Microscopique après concentration(Selles)','Examen Microscopique direct(Selles)','Germes Enterogastriques pathogènes(Selles)','Mycobacterium tuberculosis résistant(Culture)','Mycobacterium tuberculosis résistant(Sputum)','Test Rapide Rotavirus(Selles)','Virus Respiratoire(Ecouvillon Naso-Pharynge)');

update clinlims.test_result set is_quantifiable = 't' where id in (select id from clinlims.test_result where test_id in (select id from clinlims.test where name in ('VIH Western Blot'))) and value = (select trim(to_char(max(id),'999')) from clinlims.dictionary where dict_entry = 'Positif');

INSERT INTO panel_item( id, panel_id, lastupdated , test_id)
VALUES ( nextval( 'panel_item_seq' ) , (select id from clinlims.panel where name = 'VIH-1 Charge Virale') , now(), (select id from clinlims.test where description = 'VIH-1 Charge Virale(Plasma)' and is_active = 'Y' ) );

update clinlims.test set reporting_description = 'Score d''adherence' where name = 'Score d''adherence';

update clinlims.test set reporting_description = 'Date de mise sous ARV' where name = 'Date de mise sous ARV';

update clinlims.test_result set significant_digits = 1 where test_id = (select id from clinlims.test where name like 'Compte des Globules Blancs');

insert into clinlims.unit_of_measure (id, name, description, lastupdated)
	values(nextval( 'clinlims.unit_of_measure_seq' ) , '10^6/µl', '10^6/µl', now());
	
update clinlims.test set uom_id = (select id from clinlims.unit_of_measure where name = '10^3/µl'), lastupdated = now() 
where description = 'Compte des Globules Blancs(Sang Total)';

update clinlims.test set uom_id = (select id from clinlims.unit_of_measure where name = '10^6/µl'), lastupdated = now() 
where description = 'Compte des Globules Rouges(Sang Total)';

update clinlims.test set uom_id = (select id from clinlims.unit_of_measure where name = '10^3/µl'), lastupdated = now() 
where description = 'Plaquettes(Sang Total)';

