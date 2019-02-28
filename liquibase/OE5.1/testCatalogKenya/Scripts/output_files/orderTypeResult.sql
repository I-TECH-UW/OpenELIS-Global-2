INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Glucose(Urine)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Ketones(Urine)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Blood(Urine)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Bilirubin(Urine)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Urobilinogen Phenlpyruvic Acid(Urine)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'HGC(Urine)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Pus Cells (>5/hpf)(Urine)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'S. haematobium(Urine)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'T. vaginalis(Swab/Urine)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Yeast Cells(Swab/Urine)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Yeast Cells(Swab/Urine)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Red blood cells(Urine)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Red blood cells(Urine)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Bacteria(Urine)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Bacteria(Urine)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Spermatozoa(Urine)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Spermatozoa(Urine)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Fasting blood sugar(Urine/Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Fasting blood sugar(Urine/Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Random blood sugar(Urine/Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Random blood sugar(Urine/Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'OGTT(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'OGTT(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Renal function tests(Urine)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Creatinine(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Urea(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Sodium(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Potassium(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Chloride(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Direct bilirubin(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Total bilirubin(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Total bilirubin(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'SGPT/ALAT(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'SGPT/ALAT(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'SGOT/ASAT(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'SGOT/ASAT(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Serum Protein(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Serum Protein(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Albumin(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Albumin(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Alkaline Phodphate(Urine)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Alkaline Phodphate(Urine)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Gamma GT(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Gamma GT(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Amylase(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Amylase(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Total cholestrol(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Total cholestrol(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Trigycerides(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Trigycerides(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'HDL(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'HDL(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'LDE(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'LDE(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'PSA(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'PSA(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'CSF Proteins(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'CSF Proteins(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'CSF Glucose(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'CSF Glucose(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Proteins(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Proteins(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Glucose(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Glucose(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Acid phosphatase(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Acid phosphatase(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Bence jones protein(Urine)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Bence jones protein(Urine)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Triiodothyronine(T3)(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Triiodothyronine(T3)(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Thyroid-stimulating Hormone(TSH)(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Thyroid-stimulating Hormone(TSH)(Blood)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Falciparum(Smear)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Falciparum(Smear)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Ovale(Smear)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Ovale(Smear)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Malariae(Smear)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Malariae(Smear)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Vivax(Smear)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Vivax(Smear)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Borrelia(Smear)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Borrelia(Smear)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_firstVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Trypanosomes(Smear)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Trypanosomes(Smear)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'T. vaginalis(Genital Smears)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'S. haematobium(Genital Smears)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Yeast cells(Genital Smears)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'L. donovani(Bone Marrow)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'Taenia spp.(Stool)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'H. nana(Stool)'),'DISPLAY');
INSERT INTO clinlims.lab_order_item(id, lab_order_type_id, table_ref, record_id, action ) 
	VALUES ( nextval( 'lab_order_item_seq' ) , (select id from clinlims.lab_order_type where type = 'HIV_followupVisit'),( select id from clinlims.reference_tables where name='TEST'  ), ( select id from clinlims.test where description = 'H. diminuta(Stool)'),'DISPLAY');
