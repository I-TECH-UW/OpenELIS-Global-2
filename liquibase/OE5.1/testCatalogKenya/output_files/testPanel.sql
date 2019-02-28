INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Urine Microscopy') , now(), null,  (select id from clinlims.test where description = 'Yeast Cells (>5/hpf)(Urine)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Urine Microscopy') , now(), null,  (select id from clinlims.test where description = 'Red Blood Cells (>5/hpf)(Urine)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Urine Test Strip') , now(), null,  (select id from clinlims.test where description = 'Leukocytes(Urine)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Urine Microscopy') , now(), null,  (select id from clinlims.test where description = 'Other Microscopic Findings(Urine)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Urine Test Strip') , now(), null,  (select id from clinlims.test where description = 'Glucose(Urine)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Urine Test Strip') , now(), null,  (select id from clinlims.test where description = 'Ketones(Urine)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Urine Test Strip') , now(), null,  (select id from clinlims.test where description = 'Blood(Urine)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Urine Test Strip') , now(), null,  (select id from clinlims.test where description = 'Bilirubin(Urine)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Urine Test Strip') , now(), null,  (select id from clinlims.test where description = 'Specific Gravity(Urine)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Urine Test Strip') , now(), null,  (select id from clinlims.test where description = 'pH(Urine)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Urine Test Strip') , now(), null,  (select id from clinlims.test where description = 'Protein(Urine)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Urine Test Strip') , now(), null,  (select id from clinlims.test where description = 'Nitrite(Urine)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Urine Test Strip') , now(), null,  (select id from clinlims.test where description = 'Urobilinogen Phenlpyruvic Acid(Urine)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Urine Microscopy') , now(), null,  (select id from clinlims.test where description = 'Pus Cells (>5/hpf)(Urine)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Full blood count') , now(), null,  (select id from clinlims.test where description = 'WBC Count(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Full blood count') , now(), null,  (select id from clinlims.test where description = 'Neutrophil, Absolute(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Full blood count') , now(), null,  (select id from clinlims.test where description = 'Lymphocyte, Absolute(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Full blood count') , now(), null,  (select id from clinlims.test where description = 'Monocyte, Absolute(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Full blood count') , now(), null,  (select id from clinlims.test where description = 'Eosinophil, Absolute(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Full blood count') , now(), null,  (select id from clinlims.test where description = 'Basophil, Absolute(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Full blood count') , now(), null,  (select id from clinlims.test where description = 'Neutrophil(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Full blood count') , now(), null,  (select id from clinlims.test where description = 'Lymphocyte(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Full blood count') , now(), null,  (select id from clinlims.test where description = 'Monocyte(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Full blood count') , now(), null,  (select id from clinlims.test where description = 'Eosinophil(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Full blood count') , now(), null,  (select id from clinlims.test where description = 'Basophil(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Full blood count') , now(), null,  (select id from clinlims.test where description = 'RBC Count - Male(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Full blood count') , now(), null,  (select id from clinlims.test where description = 'RBC Count - Female(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Full blood count') , now(), null,  (select id from clinlims.test where description = 'Hemoglobin - Male(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Full blood count') , now(), null,  (select id from clinlims.test where description = 'Hemoglobin - Female(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Full blood count') , now(), null,  (select id from clinlims.test where description = 'Hematocrit - Male(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Full blood count') , now(), null,  (select id from clinlims.test where description = 'Hematocrit - Female(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Full blood count') , now(), null,  (select id from clinlims.test where description = 'MCV(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Full blood count') , now(), null,  (select id from clinlims.test where description = 'MCH(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Full blood count') , now(), null,  (select id from clinlims.test where description = 'MCHC(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Full blood count') , now(), null,  (select id from clinlims.test where description = 'RDW(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Full blood count') , now(), null,  (select id from clinlims.test where description = 'PLT(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Full blood count') , now(), null,  (select id from clinlims.test where description = 'PCT(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Full blood count') , now(), null,  (select id from clinlims.test where description = 'MPV(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Full blood count') , now(), null,  (select id from clinlims.test where description = 'PDW(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Coagulation Test') , now(), null,  (select id from clinlims.test where description = 'Bleeding Time(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Coagulation Test') , now(), null,  (select id from clinlims.test where description = 'Thrombin Clotting Time(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Coagulation Test') , now(), null,  (select id from clinlims.test where description = 'Prothrombin Time(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Coagulation Test') , now(), null,  (select id from clinlims.test where description = 'Partial prothrombin time(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Lymphocyte Subsets') , now(), null,  (select id from clinlims.test where description = 'CD4 %(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Lymphocyte Subsets') , now(), null,  (select id from clinlims.test where description = 'CD4, Absolute(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Lymphocyte Subsets') , now(), null,  (select id from clinlims.test where description = 'CD4:CD8 ratio(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Liver function tests') , now(), null,  (select id from clinlims.test where description = 'Direct bilirubin(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Liver function tests') , now(), null,  (select id from clinlims.test where description = 'Total bilirubin(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Liver function tests') , now(), null,  (select id from clinlims.test where description = 'SGPT/ALT - Male(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Liver function tests') , now(), null,  (select id from clinlims.test where description = 'SGPT/ALT - Female(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Liver function tests') , now(), null,  (select id from clinlims.test where description = 'SGOT/AST(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Liver function tests') , now(), null,  (select id from clinlims.test where description = 'Serum Protein(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Liver function tests') , now(), null,  (select id from clinlims.test where description = 'Albumin(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Liver function tests') , now(), null,  (select id from clinlims.test where description = 'Alkaline Phosphatase - Male(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Liver function tests') , now(), null,  (select id from clinlims.test where description = 'Alkaline Phosphatase - Female(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Renal function tests') , now(), null,  (select id from clinlims.test where description = 'Creatinine(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Renal function tests') , now(), null,  (select id from clinlims.test where description = 'Urea(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Renal function tests') , now(), null,  (select id from clinlims.test where description = 'Sodium(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Renal function tests') , now(), null,  (select id from clinlims.test where description = 'Potassium(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Renal function tests') , now(), null,  (select id from clinlims.test where description = 'Chloride(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Renal function tests') , now(), null,  (select id from clinlims.test where description = 'Bicarbonate(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Lipid profile') , now(), null,  (select id from clinlims.test where description = 'Total cholestrol(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Lipid profile') , now(), null,  (select id from clinlims.test where description = 'Trigycerides(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Lipid profile') , now(), null,  (select id from clinlims.test where description = 'HDL(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Lipid profile') , now(), null,  (select id from clinlims.test where description = 'LDL(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'CSF chemistry') , now(), null,  (select id from clinlims.test where description = 'Proteins(CSF)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'CSF chemistry') , now(), null,  (select id from clinlims.test where description = 'Glucose(CSF)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Thyroid Function Tests') , now(), null,  (select id from clinlims.test where description = 'Triiodothyronine (T3)(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Thyroid Function Tests') , now(), null,  (select id from clinlims.test where description = 'Thyroid-stimulating Hormone (TSH)(Blood)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Cytology') , now(), null,  (select id from clinlims.test where description = 'Tissue Impression(Lymph nodes)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Cytology') , now(), null,  (select id from clinlims.test where description = 'Tissue Impression(Respiratory tract lavage)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Cytology') , now(), null,  (select id from clinlims.test where description = 'Tissue Impression(Pleural fluid)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Cytology') , now(), null,  (select id from clinlims.test where description = 'Tissue Impression(Ascitic fluid)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Cytology') , now(), null,  (select id from clinlims.test where description = 'Tissue Impression(CSF)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Cytology') , now(), null,  (select id from clinlims.test where description = 'Tissue Impression(Liver tissue)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Cytology') , now(), null,  (select id from clinlims.test where description = 'Tissue Impression(Kidney tissue)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Cytology') , now(), null,  (select id from clinlims.test where description = 'Tissue Impression(Vaginal swab)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Histology') , now(), null,  (select id from clinlims.test where description = 'H & E staining(Cervix)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Histology') , now(), null,  (select id from clinlims.test where description = 'H & E staining(Prostrate)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Histology') , now(), null,  (select id from clinlims.test where description = 'H & E staining(Breast)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Histology') , now(), null,  (select id from clinlims.test where description = 'H & E staining(Ovarian cyst)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Histology') , now(), null,  (select id from clinlims.test where description = 'H & E staining(Fibroids)' and is_active = 'Y' ) ); 
INSERT INTO clinlims.panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'clinlims.panel_item_seq' ) , (select id from clinlims.panel where name = 'Histology') , now(), null,  (select id from clinlims.test where description = 'H & E staining(Lymph nodes)' and is_active = 'Y' ) ); 
