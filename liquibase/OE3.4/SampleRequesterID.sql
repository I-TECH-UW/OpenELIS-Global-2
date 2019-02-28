create sequence clinlims.sample_requester_seq;
alter table clinlims.sample_requester add column id numeric(10,0);
update clinlims.sample_requester set id = nextval( 'sample_requester_seq');

alter table clinlims.sample_requester drop constraint sample_requester_pk;
alter table clinlims.sample_requester add constraint sample_requester_pk primary key ( id );