update clinlims.test_section set is_active='N', sort_order=2147483647;

update clinlims.test_section set sort_order=10, is_active='Y' where name = 'Virologie';
update clinlims.test_section set sort_order=20, is_active='Y' where name = 'Biologie Moleculaire';
update clinlims.test_section set sort_order=30, is_active='Y' where name = 'Hematology';
update clinlims.test_section set sort_order=40, is_active='Y' where name = 'Bacteria';
update clinlims.test_section set sort_order=50, is_active='Y' where name = 'Parasitology';
update clinlims.test_section set sort_order=60, is_active='Y' where name = 'Mycobacteriology';
