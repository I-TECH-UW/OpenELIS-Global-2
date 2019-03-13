INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'GE / FS') , now(), null,  (select id from test where description = 'Goutte épaisse(Sang total)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'GE / FS') , now(), null,  (select id from test where description = 'Parasitémie(Sang total)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'GE / FS') , now(), null,  (select id from test where description = 'Frottis Mince(Sang total)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'GE / FS') , now(), null,  (select id from test where description = 'Autres espèces(Sang total)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'KOP') , now(), null,  (select id from test where description = 'Aspect des selles(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'KOP') , now(), null,  (select id from test where description = 'Parasites(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'KOP') , now(), null,  (select id from test where description = 'Eléments fongiques(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'KOP') , now(), null,  (select id from test where description = 'Concentration des selles(Selles)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Serologie Toxoplasmique') , now(), null,  (select id from test where description = 'IgM(Sérum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Serologie Toxoplasmique') , now(), null,  (select id from test where description = 'Index IgM(Sérum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Serologie Toxoplasmique') , now(), null,  (select id from test where description = 'IgG(Sérum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Serologie Toxoplasmique') , now(), null,  (select id from test where description = 'Titre IgG(Sérum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Serologie Amibienne') , now(), null,  (select id from test where description = 'HAI amibiase(Sérum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Serologie Amibienne') , now(), null,  (select id from test where description = 'Titre Ac anti-amibien (BERHING)(Sérum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Serologie Amibienne') , now(), null,  (select id from test where description = 'Titre Ac anti-amibien (FUMOUZE)(Sérum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Serologie Bilharzienne') , now(), null,  (select id from test where description = 'Résultat HAI bilharziose(Sérum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Serologie Bilharzienne') , now(), null,  (select id from test where description = 'Titre Ac antibilharzien (BERHING)(Sérum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Serologie Bilharzienne') , now(), null,  (select id from test where description = 'Titre Ac antibilharzien (FUMOUZE)(Sérum)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = $$Examen d'urines$$) , now(), null,  (select id from test where description = 'Aspect des urines(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = $$Examen d'urines$$) , now(), null,  (select id from test where description = 'Parasites (examen direct)(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = $$Examen d'urines$$) , now(), null,  (select id from test where description = 'Eléments fongiques (examen direct)(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = $$Examen d'urines$$) , now(), null,  (select id from test where description = 'Parasites (après centrifugation)(Urines)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Mycoses Profondes') , now(), null,  (select id from test where description = 'Filaments - mycoses profondes' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Mycoses Profondes') , now(), null,  (select id from test where description = 'Leucocytes' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Mycoses Profondes') , now(), null,  (select id from test where description = 'Test encre chine(LCR)' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Mycoses Profondes') , now(), null,  (select id from test where description = 'Hématies' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Mycoses Profondes') , now(), null,  (select id from test where description = 'Parasites - mycoses profondes' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Mycoses Profondes') , now(), null,  (select id from test where description = 'Sur milieu Sc' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Mycoses Profondes') , now(), null,  (select id from test where description = 'Sur milieu SAC  - mycoses profondes' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antifongigramme') , now(), null,  (select id from test where description = 'Germe isolé antifongigramme' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antifongigramme') , now(), null,  (select id from test where description = 'Flucytosine 1 ug' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antifongigramme') , now(), null,  (select id from test where description = 'Flucytosine 10 ug' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antifongigramme') , now(), null,  (select id from test where description = 'Amphotéricine B' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antifongigramme') , now(), null,  (select id from test where description = 'Fluconazole' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antifongigramme') , now(), null,  (select id from test where description = 'Miconazole' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antifongigramme') , now(), null,  (select id from test where description = 'Econazole' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antifongigramme') , now(), null,  (select id from test where description = 'Kétéconazole' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antifongigramme') , now(), null,  (select id from test where description = 'Nystatine' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antifongigramme') , now(), null,  (select id from test where description = 'Voriconazole' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antifongigramme') , now(), null,  (select id from test where description = 'Itraconazole' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Antifongigramme') , now(), null,  (select id from test where description = 'Clotrimazole' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Mycose Superficielle') , now(), null,  (select id from test where description = 'Filaments - mycose superficielle' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Mycoses Profondes') , now(), null,  (select id from test where description = 'Germe isolé mycose profonde' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Mycose Superficielle') , now(), null,  (select id from test where description = 'Grains' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Mycose Superficielle') , now(), null,  (select id from test where description = 'Spores' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Mycose Superficielle') , now(), null,  (select id from test where description = 'Parasites - mycoses superficielles' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Mycose Superficielle') , now(), null,  (select id from test where description = 'Attaque pilaire' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Mycose Superficielle') , now(), null,  (select id from test where description = 'Sur milieu SC' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Mycose Superficielle') , now(), null,  (select id from test where description = 'Sur milieu SAC  - mycose superficielle' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Mycose Superficielle') , now(), null,  (select id from test where description = 'Sur milieu taplin' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Mycose Superficielle') , now(), null,  (select id from test where description = 'Levure mycose superficielle' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Mycoses Profondes') , now(), null,  (select id from test where description = 'Levure mycose profonde' and is_active = 'Y' ) ); 
INSERT INTO panel_item( id, panel_id, lastupdated , sort_order, test_id)
	VALUES ( nextval( 'panel_item_seq' ) , (select id from panel where name = 'Mycose Superficielle') , now(), null,  (select id from test where description = 'Germe isolé mycose superficielle' and is_active = 'Y' ) ); 
