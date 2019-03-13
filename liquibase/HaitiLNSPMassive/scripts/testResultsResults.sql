INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mixtes(Sang)' ) , 'N' , null , now() , 1);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Mixtes(Whole Blood)' ) , 'N' , null , now() , 2);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Temps de Coagulation en tube(Sang)' ) , 'N' , null , now() , 3);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Temps de Coagulation en tube(Whole Blood)' ) , 'N' , null , now() , 4);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Groupe Sang/Whole Blooduin ABO/Rhesus D(Sang)' ) , 'D' , 'A' , now() , 5);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Groupe Sang/Whole Blooduin ABO/Rhesus D(Sang)' ) , 'D' , 'B' , now() , 6);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Groupe Sang/Whole Blooduin ABO/Rhesus D(Sang)' ) , 'D' , 'AB' , now() , 7);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Groupe Sang/Whole Blooduin ABO/Rhesus D(Sang)' ) , 'D' , 'O                                Rhesus positif' , now() , 8);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Groupe Sang/Whole Blooduin ABO/Rhesus D(Sang)' ) , 'D' , 'Rhesus Negatif' , now() , 9);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Groupe Sang/Whole Blooduin ABO/Rhesus D(Whole Blood)' ) , 'D' , 'A' , now() , 10);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Groupe Sang/Whole Blooduin ABO/Rhesus D(Whole Blood)' ) , 'D' , 'B' , now() , 11);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Groupe Sang/Whole Blooduin ABO/Rhesus D(Whole Blood)' ) , 'D' , 'AB' , now() , 12);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Groupe Sang/Whole Blooduin ABO/Rhesus D(Whole Blood)' ) , 'D' , 'O                                Rhesus positif' , now() , 13);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Groupe Sang/Whole Blooduin ABO/Rhesus D(Whole Blood)' ) , 'D' , 'Rhesus Negatif' , now() , 14);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coombs Test Direct(Sang)' ) , 'D' , 'Compatible' , now() , 15);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coombs Test Direct(Sang)' ) , 'D' , 'Incompatible' , now() , 16);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coombs Test Direct(Serum)' ) , 'D' , 'Compatible' , now() , 17);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coombs Test Direct(Serum)' ) , 'D' , 'Incompatible' , now() , 18);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coombs Test Indirect(Sang)' ) , 'D' , 'Compatible' , now() , 19);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coombs Test Indirect(Sang)' ) , 'D' , 'Incompatible' , now() , 20);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coombs Test Indirect(Serum)' ) , 'D' , 'Compatible' , now() , 21);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coombs Test Indirect(Serum)' ) , 'D' , 'Incompatible' , now() , 22);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chlore(LCR)' ) , 'N' , null , now() , 23);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Chlore(CSF)' ) , 'N' , null , now() , 24);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'creatinine(Liquide Amniotique)' ) , 'N' , null , now() , 25);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Proteines(Liquide Ascite)' ) , 'N' , null , now() , 26);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Albumine(Liquide Ascite)' ) , 'N' , null , now() , 27);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'glucose(Liquide Synovial)' ) , 'N' , null , now() , 28);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Uree(Liquide Synovial)' ) , 'N' , null , now() , 29);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Acide urique(Liquide Synovial)' ) , 'N' , null , now() , 30);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'proteine total(Liquide Synovial)' ) , 'N' , null , now() , 31);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'glycemie(Plasma)' ) , 'N' , null , now() , 32);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Sodium(Serum)' ) , 'N' , null , now() , 33);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Potassium(Serum)' ) , 'N' , null , now() , 34);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bicarbonates(Serum)' ) , 'N' , null , now() , 35);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Calcium(Serum)' ) , 'N' , null , now() , 36);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'magnésium(Serum)' ) , 'N' , null , now() , 37);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'phosphore(Serum)' ) , 'N' , null , now() , 38);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lithium(Serum)' ) , 'N' , null , now() , 39);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Fer Serique(Serum)' ) , 'N' , null , now() , 40);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bilirubine indirecte(Serum)' ) , 'N' , null , now() , 41);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Lipide(Serum)' ) , 'N' , null , now() , 42);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'VLDL(Serum)' ) , 'N' , null , now() , 43);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'MBG(Serum)' ) , 'N' , null , now() , 44);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ck-mB(Serum)' ) , 'N' , null , now() , 45);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ck total(Serum)' ) , 'N' , null , now() , 46);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'ASAT 30° C(Serum)' ) , 'N' , null , now() , 47);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'ASAT 37° C(Serum)' ) , 'N' , null , now() , 48);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'ALAT 30° C(Serum)' ) , 'N' , null , now() , 49);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'ALAT 37° C(Serum)' ) , 'N' , null , now() , 50);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'LDH(Serum)' ) , 'N' , null , now() , 51);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Troponine 1(Serum)' ) , 'N' , null , now() , 52);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Ph(Serum)' ) , 'N' , null , now() , 53);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'PaCO2(Serum)' ) , 'N' , null , now() , 54);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HCO3(Serum)' ) , 'N' , null , now() , 55);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'O2 Saturation(Serum)' ) , 'N' , null , now() , 56);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'PaO2(Serum)' ) , 'N' , null , now() , 57);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'BE(Serum)' ) , 'N' , null , now() , 58);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Secretion vaginale)' ) , 'D' , '1+' , now() , 59);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Secretion vaginale)' ) , 'D' , '2+' , now() , 60);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Secretion vaginale)' ) , 'D' , '3+' , now() , 61);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Secretion vaginale)' ) , 'D' , '4+' , now() , 62);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Simples(Secretion vaginale)' ) , 'D' , '1+' , now() , 63);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Simples(Secretion vaginale)' ) , 'D' , '2+' , now() , 64);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Simples(Secretion vaginale)' ) , 'D' , '3+' , now() , 65);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Simples(Secretion vaginale)' ) , 'D' , '4+' , now() , 66);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Bourgeonantes(Secretion vaginale)' ) , 'D' , '1+' , now() , 67);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Bourgeonantes(Secretion vaginale)' ) , 'D' , '2+' , now() , 68);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Bourgeonantes(Secretion vaginale)' ) , 'D' , '3+' , now() , 69);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Bourgeonantes(Secretion vaginale)' ) , 'D' , '4+' , now() , 70);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Trichomonas vaginalis(Secretion vaginale)' ) , 'D' , '1+' , now() , 71);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Trichomonas vaginalis(Secretion vaginale)' ) , 'D' , '2+' , now() , 72);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Trichomonas vaginalis(Secretion vaginale)' ) , 'D' , '3+' , now() , 73);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Trichomonas vaginalis(Secretion vaginale)' ) , 'D' , '4+' , now() , 74);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Blancs(Secretion vaginale)' ) , 'D' , '1+' , now() , 75);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Blancs(Secretion vaginale)' ) , 'D' , '2+' , now() , 76);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Blancs(Secretion vaginale)' ) , 'D' , '3+' , now() , 77);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Blancs(Secretion vaginale)' ) , 'D' , '4+' , now() , 78);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Rouges(Secretion vaginale)' ) , 'D' , '1+' , now() , 79);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Rouges(Secretion vaginale)' ) , 'D' , '2+' , now() , 80);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Rouges(Secretion vaginale)' ) , 'D' , '3+' , now() , 81);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Rouges(Secretion vaginale)' ) , 'D' , '4+' , now() , 82);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Filaments Myceliens(Secretion vaginale)' ) , 'D' , '1+' , now() , 83);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Filaments Myceliens(Secretion vaginale)' ) , 'D' , '2+' , now() , 84);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Filaments Myceliens(Secretion vaginale)' ) , 'D' , '3+' , now() , 85);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Filaments Myceliens(Secretion vaginale)' ) , 'D' , '4+' , now() , 86);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules Epitheliales(Secretion vaginale)' ) , 'D' , '1+' , now() , 87);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules Epitheliales(Secretion vaginale)' ) , 'D' , '2+' , now() , 88);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules Epitheliales(Secretion vaginale)' ) , 'D' , '3+' , now() , 89);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules Epitheliales(Secretion vaginale)' ) , 'D' , '4+' , now() , 90);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Secretion Urethrale)' ) , 'D' , '1+' , now() , 91);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Secretion Urethrale)' ) , 'D' , '2+' , now() , 92);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Secretion Urethrale)' ) , 'D' , '3+' , now() , 93);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacteries(Secretion Urethrale)' ) , 'D' , '4+' , now() , 94);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Simples(Secretion Urethrale)' ) , 'D' , '1+' , now() , 95);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Simples(Secretion Urethrale)' ) , 'D' , '2+' , now() , 96);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Simples(Secretion Urethrale)' ) , 'D' , '3+' , now() , 97);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Simples(Secretion Urethrale)' ) , 'D' , '4+' , now() , 98);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Bourgeonantes(Secretion Urethrale)' ) , 'D' , '1+' , now() , 99);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Bourgeonantes(Secretion Urethrale)' ) , 'D' , '2+' , now() , 100);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Bourgeonantes(Secretion Urethrale)' ) , 'D' , '3+' , now() , 101);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Levures Bourgeonantes(Secretion Urethrale)' ) , 'D' , '4+' , now() , 102);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Trichomonas vaginalis(Secretion Urethrale)' ) , 'D' , 'Positif' , now() , 103);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Trichomonas vaginalis(Secretion Urethrale)' ) , 'D' , 'Negatif' , now() , 104);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Blancs(Secretion Urethrale)' ) , 'D' , 'Positif' , now() , 105);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Blancs(Secretion Urethrale)' ) , 'D' , 'Negatif' , now() , 106);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Rouges(Secretion Urethrale)' ) , 'D' , '1+' , now() , 107);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Rouges(Secretion Urethrale)' ) , 'D' , '2+' , now() , 108);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Rouges(Secretion Urethrale)' ) , 'D' , '3+' , now() , 109);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Globules Rouges(Secretion Urethrale)' ) , 'D' , '4+' , now() , 110);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Filaments Myceliens(Secretion Urethrale)' ) , 'D' , '1+' , now() , 111);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Filaments Myceliens(Secretion Urethrale)' ) , 'D' , '2+' , now() , 112);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Filaments Myceliens(Secretion Urethrale)' ) , 'D' , '3+' , now() , 113);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Filaments Myceliens(Secretion Urethrale)' ) , 'D' , '4+' , now() , 114);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules Epitheliales(Secretion Urethrale)' ) , 'D' , '1+' , now() , 115);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules Epitheliales(Secretion Urethrale)' ) , 'D' , '2+' , now() , 116);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules Epitheliales(Secretion Urethrale)' ) , 'D' , '3+' , now() , 117);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules Epitheliales(Secretion Urethrale)' ) , 'D' , '4+' , now() , 118);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'KOH(Secretion vaginale)' ) , 'D' , '1+' , now() , 119);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'KOH(Secretion vaginale)' ) , 'D' , '2+' , now() , 120);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'KOH(Secretion vaginale)' ) , 'D' , '3+' , now() , 121);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'KOH(Secretion vaginale)' ) , 'D' , '4+' , now() , 122);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test de Rivalta(Secretion vaginale)' ) , 'D' , '1+' , now() , 123);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test de Rivalta(Secretion vaginale)' ) , 'D' , '2+' , now() , 124);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test de Rivalta(Secretion vaginale)' ) , 'D' , '3+' , now() , 125);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Test de Rivalta(Secretion vaginale)' ) , 'D' , '4+' , now() , 126);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram+(Secretion vaginale)' ) , 'D' , '1+' , now() , 127);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram+(Secretion vaginale)' ) , 'D' , '2+' , now() , 128);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram+(Secretion vaginale)' ) , 'D' , '3+' , now() , 129);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram+(Secretion vaginale)' ) , 'D' , '4+' , now() , 130);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram-(Secretion vaginale)' ) , 'D' , '1+' , now() , 131);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram-(Secretion vaginale)' ) , 'D' , '2+' , now() , 132);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram-(Secretion vaginale)' ) , 'D' , '3+' , now() , 133);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram-(Secretion vaginale)' ) , 'D' , '4+' , now() , 134);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram+(Secretion vaginale)' ) , 'D' , '1+' , now() , 135);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram+(Secretion vaginale)' ) , 'D' , '2+' , now() , 136);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram+(Secretion vaginale)' ) , 'D' , '3+' , now() , 137);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram+(Secretion vaginale)' ) , 'D' , '4+' , now() , 138);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram-(Secretion vaginale)' ) , 'D' , '1+' , now() , 139);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram-(Secretion vaginale)' ) , 'D' , '2+' , now() , 140);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram-(Secretion vaginale)' ) , 'D' , '3+' , now() , 141);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram-(Secretion vaginale)' ) , 'D' , '4+' , now() , 142);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocobacilles Gram -(Secretion vaginale)' ) , 'D' , '1+' , now() , 143);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocobacilles Gram -(Secretion vaginale)' ) , 'D' , '2+' , now() , 144);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocobacilles Gram -(Secretion vaginale)' ) , 'D' , '3+' , now() , 145);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocobacilles Gram -(Secretion vaginale)' ) , 'D' , '4+' , now() , 146);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules epitheliales cloutees(Secretion vaginale)' ) , 'D' , '1+' , now() , 147);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules epitheliales cloutees(Secretion vaginale)' ) , 'D' , '2+' , now() , 148);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules epitheliales cloutees(Secretion vaginale)' ) , 'D' , '3+' , now() , 149);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules epitheliales cloutees(Secretion vaginale)' ) , 'D' , '4+' , now() , 150);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Diplocoque Gram (-) Intra cellulaire(Secretion vaginale)' ) , 'D' , '1+' , now() , 151);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Diplocoque Gram (-) Intra cellulaire(Secretion vaginale)' ) , 'D' , '2+' , now() , 152);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Diplocoque Gram (-) Intra cellulaire(Secretion vaginale)' ) , 'D' , '3+' , now() , 153);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Diplocoque Gram (-) Intra cellulaire(Secretion vaginale)' ) , 'D' , '4+' , now() , 154);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Diplocoque Gram (-) Extra cellulaire(Secretion vaginale)' ) , 'D' , '1+' , now() , 155);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Diplocoque Gram (-) Extra cellulaire(Secretion vaginale)' ) , 'D' , '2+' , now() , 156);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Diplocoque Gram (-) Extra cellulaire(Secretion vaginale)' ) , 'D' , '3+' , now() , 157);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Diplocoque Gram (-) Extra cellulaire(Secretion vaginale)' ) , 'D' , '4+' , now() , 158);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram+(Secretion Urethrale)' ) , 'D' , '1+' , now() , 159);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram+(Secretion Urethrale)' ) , 'D' , '2+' , now() , 160);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram+(Secretion Urethrale)' ) , 'D' , '3+' , now() , 161);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram+(Secretion Urethrale)' ) , 'D' , '4+' , now() , 162);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram-(Secretion Urethrale)' ) , 'D' , '1+' , now() , 163);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram-(Secretion Urethrale)' ) , 'D' , '2+' , now() , 164);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram-(Secretion Urethrale)' ) , 'D' , '3+' , now() , 165);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Bacilles Gram-(Secretion Urethrale)' ) , 'D' , '4+' , now() , 166);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram+(Secretion Urethrale)' ) , 'D' , '1+' , now() , 167);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram+(Secretion Urethrale)' ) , 'D' , '2+' , now() , 168);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram+(Secretion Urethrale)' ) , 'D' , '3+' , now() , 169);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram+(Secretion Urethrale)' ) , 'D' , '4+' , now() , 170);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram-(Secretion Urethrale)' ) , 'D' , '1+' , now() , 171);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram-(Secretion Urethrale)' ) , 'D' , '2+' , now() , 172);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram-(Secretion Urethrale)' ) , 'D' , '3+' , now() , 173);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocci Gram-(Secretion Urethrale)' ) , 'D' , '4+' , now() , 174);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocobacilles Gram -(Secretion Urethrale)' ) , 'D' , '1+' , now() , 175);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocobacilles Gram -(Secretion Urethrale)' ) , 'D' , '2+' , now() , 176);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocobacilles Gram -(Secretion Urethrale)' ) , 'D' , '3+' , now() , 177);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cocobacilles Gram -(Secretion Urethrale)' ) , 'D' , '4+' , now() , 178);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules epitheliales cloutees(Secretion Urethrale)' ) , 'R' , null , now() , 179);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Diplocoque Gram (-) Intra cellulaire(Secretion Urethrale)' ) , 'R' , null , now() , 180);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Diplocoque Gram (-) Extra cellulaire(Secretion Urethrale)' ) , '' , null , now() , 181);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Frottis Vaginal/Gram(Secretion vaginale)' ) , 'D' , 'Jaune Paille' , now() , 182);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Frottis Vaginal/Gram(Secretion vaginale)' ) , 'D' , 'Jaune citrin' , now() , 183);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Frottis Vaginal/Gram(Secretion vaginale)' ) , 'D' , 'Ambre' , now() , 184);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Frottis Vaginal/Gram(Secretion vaginale)' ) , 'D' , 'Orange' , now() , 185);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Frottis Vaginal/Gram(Secretion vaginale)' ) , 'D' , 'Jaune Fonce' , now() , 186);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Frottis Vaginal/Gram(Secretion vaginale)' ) , 'D' , 'Rouge' , now() , 187);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Frottis Vaginal/Gram(Secretion vaginale)' ) , 'D' , 'Autre' , now() , 188);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Frottis Uretral/Gram(Secretion Urethrale)' ) , 'D' , 'Clair' , now() , 189);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Frottis Uretral/Gram(Secretion Urethrale)' ) , 'D' , 'Trouble' , now() , 190);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Frottis Uretral/Gram(Secretion Urethrale)' ) , 'D' , 'Icterique' , now() , 191);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Frottis Uretral/Gram(Secretion Urethrale)' ) , 'D' , 'normal' , now() , 192);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Frottis Uretral/Gram(Secretion Urethrale)' ) , 'D' , 'Fonce' , now() , 193);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Frottis Uretral/Gram(Secretion Urethrale)' ) , 'D' , 'Autre' , now() , 194);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cholera Test rapide(Selles)' ) , 'N' , null , now() , 195);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Coproculture(Selles)' ) , 'N' , null , now() , 196);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'CMV Ig G(Serum)' ) , 'N' , null , now() , 197);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'CMV Ig M(Serum)' ) , 'D' , 'POSITIF' , now() , 198);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'CMV Ig M(Serum)' ) , 'D' , 'NEGATIF' , now() , 199);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'CMV Ig M(Serum)' ) , 'D' , 'INDETERMINE' , now() , 200);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'RUBELLA Ig G(Serum)' ) , 'D' , 'POSITIF' , now() , 201);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'RUBELLA Ig G(Serum)' ) , 'D' , 'NEGATIF' , now() , 202);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'RUBELLA Ig M(Serum)' ) , 'D' , 'POSITIF' , now() , 203);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'RUBELLA Ig M(Serum)' ) , 'D' , 'NEGATIF' , now() , 204);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HERPESTYPE I   Ig G(Serum)' ) , 'D' , 'POSITIF' , now() , 205);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HERPESTYPE I   Ig G(Serum)' ) , 'D' , 'NEGATIF' , now() , 206);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HERPESTYPE II Ig M(Serum)' ) , 'D' , 'POSITIF' , now() , 207);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'HERPESTYPE II Ig M(Serum)' ) , 'D' , 'NEGATIF' , now() , 208);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HBsAg IGG(Serum)' ) , '' , null , now() , 209);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HBsAg IGG(Plasma)' ) , '' , null , now() , 210);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HBsAg IGG(Sang)' ) , '' , null , now() , 211);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HBV :AcHbC,AcHbe(Serum)' ) , '' , null , now() , 212);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HBV :AcHbC,AcHbe(Plasma)' ) , '' , null , now() , 213);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HBV :AcHbC,AcHbe(Sang)' ) , '' , null , now() , 214);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite C - HCV IGG(Serum)' ) , 'N' , null , now() , 215);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite C - HCV IGG(Plasma)' ) , 'N' , null , now() , 216);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite C - HCV IGG(Sang)' ) , 'N' , null , now() , 217);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HCV IGM(Serum)' ) , 'D' , 'Detecte' , now() , 218);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HCV IGM(Serum)' ) , 'D' , 'Non Detecte' , now() , 219);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HCV IGM(Serum)' ) , 'D' , 'Indetermine' , now() , 220);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HCV IGM(Plasma)' ) , 'D' , 'Detecte' , now() , 221);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HCV IGM(Plasma)' ) , 'D' , 'Non Detecte' , now() , 222);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HCV IGM(Plasma)' ) , 'D' , 'Indetermine' , now() , 223);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HCV IGM(Sang)' ) , 'D' , 'Detecte' , now() , 224);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HCV IGM(Sang)' ) , 'D' , 'Non Detecte' , now() , 225);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Hépatite B - HCV IGM(Sang)' ) , 'D' , 'Indetermine' , now() , 226);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'P24(Serum)' ) , 'D' , 'Detecte' , now() , 227);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'P24(Serum)' ) , 'D' , 'Non Detecte' , now() , 228);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'P24(Serum)' ) , 'D' , 'Indetermine' , now() , 229);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture(LCR)' ) , 'D' , 'POS' , now() , 230);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture(LCR)' ) , 'D' , 'NEG' , now() , 231);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture(CSF)' ) , 'D' , 'POS' , now() , 232);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture(CSF)' ) , 'D' , 'NEG' , now() , 233);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Antibiogramme(LCR)' ) , 'D' , 'Pres BK' , now() , 234);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Antibiogramme(LCR)' ) , 'D' , 'Abs BK' , now() , 235);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Antibiogramme(LCR)' ) , 'D' , '1+' , now() , 236);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Antibiogramme(LCR)' ) , 'D' , '2+' , now() , 237);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Antibiogramme(LCR)' ) , 'D' , '3+' , now() , 238);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Antibiogramme(LCR)' ) , 'D' , 'etc' , now() , 239);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Antibiogramme(CSF)' ) , 'D' , 'Pres BK' , now() , 240);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Antibiogramme(CSF)' ) , 'D' , 'Abs BK' , now() , 241);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Antibiogramme(CSF)' ) , 'D' , '1+' , now() , 242);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Antibiogramme(CSF)' ) , 'D' , '2+' , now() , 243);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Antibiogramme(CSF)' ) , 'D' , '3+' , now() , 244);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Antibiogramme(CSF)' ) , 'D' , 'etc' , now() , 245);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'recherche des bacteries(Liquide Pleural)' ) , 'R' , null , now() , 246);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Antibiogramme(Liquide Pleural)' ) , 'R' , null , now() , 247);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'cristaux d'urates(Liquide Synovial)' ) , 'D' , 'Pres ence des lymphocytes' , now() , 248);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'cristaux d'urates(Liquide Synovial)' ) , 'D' , 'Absence des lymphocytes' , now() , 249);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'cristaux de cholesterol(Liquide Synovial)' ) , 'R' , null , now() , 250);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Examen direct(Pus)' ) , 'R' , null , now() , 251);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture(Pus)' ) , 'D' , 'Pres ence des lymphocytes' , now() , 252);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Culture(Pus)' ) , 'D' , 'Absence des lymphocytes' , now() , 253);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Antibiogramme(Pus)' ) , 'D' , 'Pres eosinophiles' , now() , 254);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Liquide Ascite Recherche des Lymphocytes(Liquide Ascite)' ) , 'D' , 'Pres ence des cellules atypiques' , now() , 255);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Liquide Ascite Recherche des Lymphocytes(Liquide Ascite)' ) , 'D' , 'Absence des cellules atypiques' , now() , 256);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Liquide Ascite GRAM(Liquide Ascite)' ) , 'N' , null , now() , 257);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Liquide Ascite ZIELH NIELSEN(Liquide Ascite)' ) , 'R' , null , now() , 258);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche des Lymphocytes(Liquide Pleural)' ) , 'R' , null , now() , 259);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Eosinophile(Liquide Pleural)' ) , 'N' , null , now() , 260);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'cellules atypiques/polynucleaires(Liquide Pleural)' ) , 'N' , null , now() , 261);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'globules rouges(Liquide Pleural)' ) , 'N' , null , now() , 262);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'GRAM(Liquide Pleural)' ) , 'R' , null , now() , 263);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'ZIELH NIELSEN(Liquide Pleural)' ) , 'R' , null , now() , 264);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Cellules polynucleaires(Liquide Synovial)' ) , '' , null , now() , 265);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'LCR GRAM(LCR)' ) , 'R' , null , now() , 266);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'LCR GRAM(CSF)' ) , 'R' , null , now() , 267);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'LCR ZIELH NIELSEN(LCR)' ) , '' , null , now() , 268);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'LCR ZIELH NIELSEN(CSF)' ) , '' , null , now() , 269);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BK Auramine(Expectoration)' ) , 'N' , null , now() , 270);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'Recherche de BK Auramine(sputum)' ) , 'N' , null , now() , 271);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'TOXOPLASMOSE GONDII IgG Ac(Serum)' ) , 'D' , 'Negatif' , now() , 272);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'TOXOPLASMOSE GONDII IgG Ac(Serum)' ) , 'D' , 'Positif' , now() , 273);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'TOXOPLASMOSE GONDII Ig M Ac(Serum)' ) , 'D' , 'POS' , now() , 274);
INSERT INTO test_result( id, test_id, tst_rslt_type, value , lastupdated, sort_order)
	 nextval( 'test_result_seq' ) , ( select id from clinlims.test where description = 'TOXOPLASMOSE GONDII Ig M Ac(Serum)' ) , 'D' , 'NEG' , now() , 275);
