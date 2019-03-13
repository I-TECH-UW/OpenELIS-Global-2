UPDATE clinlims.localization
SET french= 'Test rapide VIH',
  lastupdated = now()
WHERE id in (SELECT name_localization_id FROM clinlims.test WHERE guid in ('b69a551f-ccf9-4c88-ace9-1039fe49fd8d','8e3e8455-499b-4626-b866-1c3fe2302ea5','817539b5-e2ee-4407-888f-3695917d40cc'));

UPDATE clinlims.localization
SET french= replace(french, 'HIV', 'VIH'),
  lastupdated = now()
WHERE id in (SELECT reporting_name_localization_id FROM clinlims.test WHERE guid in ('b69a551f-ccf9-4c88-ace9-1039fe49fd8d','8e3e8455-499b-4626-b866-1c3fe2302ea5','817539b5-e2ee-4407-888f-3695917d40cc'));
