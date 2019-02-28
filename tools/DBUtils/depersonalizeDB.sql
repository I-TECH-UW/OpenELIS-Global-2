--obvuscate names
update clinlims.person p set 
	first_name = ('firstwehf'::varchar || p.id::varchar ),
	middle_name = null,
	last_name = ('lastwehf'::varchar || p.id::varchar ) 
 where not (p.last_name = 'UNKNOWN_' OR p.last_name is null) ; 

--obvuscate addresses
update clinlims.person p set 
        multiple_unit = null,
        street_address = null,
	city = null,
	state = null,
	zip_code = null,
	country = null,
	work_phone = null,
	home_phone = null,
	cell_phone = null,
	fax = null,
	email = null;


--obvuscate internal and external subject numbers
update clinlims.patient p set
	national_id = ('sn'::varchar || p.id::varchar ),
	birth_date = p.birth_date + interval '3 month',
        gender = CASE WHEN random() > 0.5 THEN 'F' ELSE 'M' END
where id != (select id from clinlims.person where last_name = 'UNKNOWN_');
	 
update clinlims.patient p set
	external_id = ('exj'::varchar || p.id::varchar )
where not external_id is null;

--remove address table dictionary and text types
update clinlims.person_address set value = '' where type = 'T';
delete from clinlims.person_address where type = 'D';

--obfuscate entered_birth_date on patient table 
update clinlims.patient set entered_birth_date = overlay(entered_birth_date placing 'xx/xx' from 1 for 5) 
	where entered_birth_date is not null and length(entered_birth_date ) = 10;
update clinlims.patient set entered_birth_date = overlay(entered_birth_date placing 'xx/xx' from 1 for 4) 
	where entered_birth_date is not null and length(entered_birth_date ) = 9;
	
--obvuscate accessionNumbers, keeping the first four chars

CREATE TABLE clinlims."tmp"
(
  id numeric(10) NOT NULL,
  value numeric 
);

insert into clinlims.tmp values (1, floor(random()*1000));

DROP INDEX clinlims.accnum_uk;

update clinlims.sample set accession_number = substring( accession_number, 1, 4 )  || to_char(current_date, 'YY')
|| 100000 + id + (select value from clinlims.tmp where id = 1 );

CREATE UNIQUE INDEX accnum_uk
  ON clinlims.sample
  USING btree
  (accession_number);

DROP TABLE clinlims."tmp";



	