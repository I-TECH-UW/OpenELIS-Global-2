Update clinlims.test set is_active = 'N' where name like 'Transaminases G0T%';

UPDATE clinlims.test set description = 'HBs AG (Antigéne australia)(Serum)', 
						 local_abbrev = 'HBs AG',
						 name = 'HBs AG (Antigéne australia)'
where name = 'HBs AG (antigén australia)';
