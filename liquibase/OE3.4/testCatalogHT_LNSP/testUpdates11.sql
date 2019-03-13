

update clinlims.test set reporting_description = name where reporting_description in ( 'c', 'new name');

update clinlims.test set uom_id = (select id from clinlims.unit_of_measure where name like '10^3/_l'), lastupdated = now() 
where description = 'Compte des Globules Blancs(Sang Total)';

update clinlims.test set uom_id = (select id from clinlims.unit_of_measure where name like '10^6/_l'), lastupdated = now() 
where description = 'Compte des Globules Rouges(Sang Total)';

update clinlims.test set uom_id = (select id from clinlims.unit_of_measure where name like '10^3/_l'), lastupdated = now() 
where description = 'Plaquettes(Sang Total)';

UPDATE clinlims.test
            SET  description=replace(description,'LCR Coloration de Gram  ','Coloration de Gram du LCR'),
            reporting_description=replace(reporting_description,'LCR Coloration de Gram  ','new name'),
            name=replace(name,'LCR Coloration de Gram  ','Coloration de Gram du LCR'), lastupdated = now()
            WHERE name='LCR Coloration de Gram  ';


UPDATE clinlims.test
            SET  description=replace(description,'Cryptococcus test rapide','Cryptococcus Test Rapide'),
            reporting_description=replace(reporting_description,'Cryptococcus test rapide','Cryptococcus Test Rapide'),
            name=replace(name,'Cryptococcus test rapide','Cryptococcus Test Rapide'), lastupdated = now()
            WHERE name='Cryptococcus test rapide';
            
update clinlims.panel set is_active='N',  lastupdated=now() where name in ('Recherche de virus respiratoire par immunofuorescence directe','Hemogramme-Manual','Miscellaneous  Bacteriologie');            


update clinlims.test set sort_order = (select sort_order + 120 from clinlims.test where name = 'Coloration de Gram Sang') where description = 'Resultats(Coloration de Gram)';

update clinlims.test set sort_order = (select sort_order + 110 from clinlims.test where name = 'Coloration de Gram Sang') where description = 'Type d''echantillon(Coloration de Gram)';

update clinlims.test set sort_order = (select sort_order + 140 from clinlims.test where name = 'Coloration de Gram Sang') where description = 'Resultats(Culture)';

update clinlims.test set sort_order = (select sort_order + 130 from clinlims.test where name = 'Coloration de Gram Sang') where description = 'Type d''echantillon(Culture)';



