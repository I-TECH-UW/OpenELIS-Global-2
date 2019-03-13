
insert into clinlims.unit_of_measure (id, name, description, lastupdated)
	values(nextval( 'clinlims.unit_of_measure_seq' ) , 'Bandes présentes', 'Bandes présentes', now());

update clinlims.test set uom_id = (select id from clinlims.unit_of_measure where name = 'Bandes présentes'), lastupdated = now() 
where name = 'VIH Western Blot';
