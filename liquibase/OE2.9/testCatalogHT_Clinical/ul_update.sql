update clinlims.unit_of_measure set name = 'µl', description = 'µl'
	where id = (select uom_id from clinlims.test where description = 'CD4 Compte Absolu(Sang)' );