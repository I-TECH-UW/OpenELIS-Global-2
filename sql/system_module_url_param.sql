CREATE TABLE clinlims.system_module_param (
  id numeric(10,0) PRIMARY KEY,
  name varchar(20) NOT NULL,
  value varchar(20) NOT NULL
); 

CREATE TABLE clinlims.system_module_url (
  id numeric(10,0) PRIMARY KEY,
  url_path varchar(40) NOT NULL,
  system_module_id numeric(10,0) NOT NULL,
  system_module_param_id numeric(10,0),
  CONSTRAINT param_fk FOREIGN KEY (system_module_param_id) REFERENCES clinlims.system_module_param (id),
  CONSTRAINT system_module_id_fk FOREIGN KEY (system_module_id) REFERENCES clinlims.system_module (id)
);


CREATE SEQUENCE clinlims.system_module_url_seq;

ALTER SEQUENCE clinlims.system_module_url_seq
    OWNER TO clinlims;
    
CREATE SEQUENCE clinlims.system_module_param_seq;

ALTER SEQUENCE clinlims.system_module_param_seq
    OWNER TO clinlims;

ALTER TABLE clinlims.system_module_param OWNER TO clinlims;
ALTER TABLE clinlims.system_module_url OWNER TO clinlims;

UPDATE clinlims.system_module SET name = 'SampleBatchEntry' WHERE name='SampleBatchEntrySetup';