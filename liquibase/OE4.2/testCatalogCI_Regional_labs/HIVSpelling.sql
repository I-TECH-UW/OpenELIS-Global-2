UPDATE clinlims.localization
SET french= 'Test rapide VIH',
  lastupdated = now()
WHERE id in (SELECT name_localization_id FROM clinlims.test WHERE guid in ('c200173b-d972-4e54-9c4f-5271290a8ed8','d0ec0286-44cd-485d-ac0c-87d3664198a6','0ac0b77e-672c-4eee-ae71-c05a0fee086b'));

UPDATE clinlims.localization
SET french= replace(french, 'HIV', 'VIH'),
  lastupdated = now()
WHERE id in (SELECT reporting_name_localization_id FROM clinlims.test WHERE guid in ('c200173b-d972-4e54-9c4f-5271290a8ed8','d0ec0286-44cd-485d-ac0c-87d3664198a6','0ac0b77e-672c-4eee-ae71-c05a0fee086b'));