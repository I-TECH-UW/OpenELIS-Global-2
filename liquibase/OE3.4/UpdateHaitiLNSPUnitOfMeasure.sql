insert into clinlims.unit_of_measure (id, name, description, lastupdated) values (nextval('clinlims.unit_of_measure_seq'), '10^3/μl', '10^3/μl', now());
update clinlims.test set uom_id = currval('clinlims.unit_of_measure_seq') where name = 'Plaquettes' and uom_id is null;
