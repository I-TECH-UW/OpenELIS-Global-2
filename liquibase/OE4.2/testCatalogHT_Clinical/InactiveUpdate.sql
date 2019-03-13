ALTER TABLE clinlims.test DROP CONSTRAINT name_fk;
ALTER TABLE clinlims.test DROP CONSTRAINT reporting_name_fk;

update clinlims.test set name_localization_id = nextval( 'localization_seq' ) where name_localization_id is null;
update clinlims.test set reporting_name_localization_id = nextval( 'localization_seq' ) where reporting_name_localization_id is null;

insert into clinlims.localization(id, description, english,french, lastupdated)
  select name_localization_id, 'test name', name, name, now() from clinlims.test where guid is null;

insert into clinlims.localization(id, description, english,french, lastupdated)
  select reporting_name_localization_id, 'test report name', reporting_description, reporting_description, now() from clinlims.test where guid is null;

ALTER TABLE clinlims.test ADD CONSTRAINT name_fk FOREIGN KEY (name_localization_id) REFERENCES clinlims.localization (id) ON UPDATE NO ACTION ON DELETE CASCADE;
ALTER TABLE clinlims.test ADD CONSTRAINT reporting_name_fk FOREIGN KEY (reporting_name_localization_id) REFERENCES clinlims.localization (id) ON UPDATE NO ACTION ON DELETE CASCADE;

update clinlims.test set guid = 'PSEUDO_GUID_' || id where guid is null;