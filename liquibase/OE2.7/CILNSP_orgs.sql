INSERT INTO organization_type( id, short_name, description, name_display_key, lastupdated)
    VALUES ( nextval( 'organization_type_seq' ), 'Health District', 'District in region hospital is located', 'organization.type.healt.region', now() );

INSERT INTO organization_type( id, short_name, description, name_display_key, lastupdated)
    VALUES ( nextval( 'organization_type_seq' ), 'Health Region', 'Region hospital is located', 'organization.type.healt.region', now() );

INSERT INTO organization(id, "name", short_name,lastupdated, is_active)
    VALUES ( nextval('organization_seq') , 'AGNEBY-TIASSA-ME', '1', now() , 'Y');
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), currval( 'organization_type_seq'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'ADZOPE', '1.1', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'AGBOVILLE', '1.2', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'AKOUPE', '1.3', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'ALEPE', '1.4', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'SIKENSI', '1.5', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'TIASSALE', '1.6', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

--new region
INSERT INTO organization(id, "name", short_name,lastupdated, is_active)
     VALUES( nextval('organization_seq') , 'GBOKLE-NAWA-SAN PÉDRO', '2', now() , 'Y');
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), currval( 'organization_type_seq'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'SAN-PEDRO', '2.1', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'TABOU', '2.2', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'SOUBRE', '2.3', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'SASSANDRA', '2.4', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'GUEYO', '2.5', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

-- new region
INSERT INTO organization(id, "name", short_name,lastupdated, is_active)
     VALUES( nextval('organization_seq') , 'KABADOUGOU-BAFIN-FOLON', '3', now() , 'Y');
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), currval( 'organization_type_seq'));    


INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'ODIENNE', '3.1', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    


INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'TOUBA', '3.2', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

-- new region
INSERT INTO organization(id, "name", short_name,lastupdated, is_active)
     VALUES( nextval('organization_seq') , 'HAUT SASSANDRA', '4', now() , 'Y');
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), currval( 'organization_type_seq'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'DALOA', '4.1', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    


INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'VAVOUA', '4.2', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    


INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'ISSIA', '4.3', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

-- new region
INSERT INTO organization(id, "name", short_name,lastupdated, is_active)
     VALUES( nextval('organization_seq') , 'GOH', '5', now() , 'Y');
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), currval( 'organization_type_seq'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'GAGNOA', '5.1', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    


INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'OUME', '5.2', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

-- new region
INSERT INTO organization(id, "name", short_name,lastupdated, is_active)
     VALUES( nextval('organization_seq') , 'BELIER', '6', now() , 'Y');
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), currval( 'organization_type_seq'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'YAMOUSSOUKRO', '6.1', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'TIEBISSOU', '6.2', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'TOUMODI', '6.3', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'DIDIEVI', '6.4', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

-- new region
INSERT INTO organization(id, "name", short_name,lastupdated, is_active)
     VALUES( nextval('organization_seq') , 'ABIDJAN 1-GRANDS PONTS', '7', now() , 'Y');
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), currval( 'organization_type_seq'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'ADJAME', '7.1', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'PLATEAU', '7.2', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'ATTECOUBE', '7.3', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'YOUPOUGON EST', '7.4', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'YOPOUGON OUST', '7.5', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'SONGON', '7.6', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'DABOU', '7.7', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'JACQUEVILLE', '7.8', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'GRAND-LAHOU', '7.9', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
-- new region
INSERT INTO organization(id, "name", short_name,lastupdated, is_active)
     VALUES( nextval('organization_seq') , 'ABIDJAN 2', '8', now() , 'Y');
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), currval( 'organization_type_seq'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'TREICHVILLE', '8.1', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'MARCORY', '8.2', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'KOUMASSI', '8.3', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'PORT-BOUET', '8.4', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'VRIDI', '8.5', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'COCODY', '8.6', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'BINGERVILLE', '8.7', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'ABOBO EST', '8.8', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'ABOBO OUEST', '8.9', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'ANYAMA', '8.10', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
-- new region
INSERT INTO organization(id, "name", short_name,lastupdated, is_active)
     VALUES( nextval('organization_seq') , 'MARAHOUE', '9', now() , 'Y');
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), currval( 'organization_type_seq'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'BOUAFLE', '9.1', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'SINFRA', '9.2', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'ZUENOULA', '9.3', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
-- new region
INSERT INTO organization(id, "name", short_name,lastupdated, is_active)
     VALUES( nextval('organization_seq') , 'TONPKI', '10', now() , 'Y');
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), currval( 'organization_type_seq'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'MAN', '10.1', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'BIANKOUMAN', '10.2', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'DANANE', '10.3', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'ZOUAN-HOUNIEN', '10.4', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
-- new region
INSERT INTO organization(id, "name", short_name,lastupdated, is_active)
     VALUES( nextval('organization_seq') , 'CAVALLY-GUEMON', '11', now() , 'Y');
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), currval( 'organization_type_seq'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'GUIGLO', '11.1', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'TOULEUPLEU', '11.2', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'DUEKOUE', '11.3', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'BLOLEQUIN', '11.4', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'BANGOLO', '11.5', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'KOUIBLY', '11.6', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
-- new region
INSERT INTO organization(id, "name", short_name,lastupdated, is_active)
     VALUES( nextval('organization_seq') , 'N’ZI-IFOU', '12', now() , 'Y');
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), currval( 'organization_type_seq'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'DIMBOKRO', '12.1', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'BOCANDA', '12.2', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'BONGOUANOU', '12.3', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'DAOUKRO', '12.4', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'M’BAHIAKRO', '12.5', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'PRIKRO', '12.6', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
-- new region
INSERT INTO organization(id, "name", short_name,lastupdated, is_active)
     VALUES( nextval('organization_seq') , 'INDENIE-DJUABLIN', '13', now() , 'Y');
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), currval( 'organization_type_seq'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'ABENGOUROU', '13.1', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
	
INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'AGNIBILEKRO', '13.2', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'BETTIE', '13.3', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
-- new region
INSERT INTO organization(id, "name", short_name,lastupdated, is_active)
     VALUES( nextval('organization_seq') , 'PORO-TCHOLOGO-BAGOUE', '14', now() , 'Y');
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), currval( 'organization_type_seq'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'KORHOGO', '14.1', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'BOUDIALI', '14.2', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'FERKESSEDOUGOU', '14.3', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'TENGRELA', '14.4', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
--new region
INSERT INTO organization(id, "name", short_name,lastupdated, is_active)
     VALUES( nextval('organization_seq') , 'LOH-DJIBOUA', '15', now() , 'Y');
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), currval( 'organization_type_seq'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'DIVO', '15.1', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'LAKOTA', '15.2', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
--new region
INSERT INTO organization(id, "name", short_name,lastupdated, is_active)
     VALUES( nextval('organization_seq') , 'SUD-COMOE', '16', now() , 'Y');
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), currval( 'organization_type_seq'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'ABOISSO', '16.1', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'ADIAKE', '16.2', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'GRAND-BASSAM', '16.3', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
-- new region
INSERT INTO organization(id, "name", short_name,lastupdated, is_active)
     VALUES( nextval('organization_seq') , 'GBÈKÈ', '17', now() , 'Y');
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), currval( 'organization_type_seq'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'BOUAKE NORD-OUEST', '17.1', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'BOUAKE NORD-EST', '17.2', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'BOUAKE SUD', '17.3', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'SAKASSOU', '17.4', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'BEOUMI', '17.5', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
--new region
INSERT INTO organization(id, "name", short_name,lastupdated, is_active)
     VALUES( nextval('organization_seq') , 'HAMBOL', '18', now() , 'Y');
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), currval( 'organization_type_seq'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'DABAKALA', '18.1', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'KATIOLA', '18.2', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'NIAKARAMADOUGOU', '18.3', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
--new region
INSERT INTO organization(id, "name", short_name,lastupdated, is_active)
     VALUES( nextval('organization_seq') , 'WORODOUGOU-BERE', '19', now() , 'Y');
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), currval( 'organization_type_seq'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'SEGUELA', '19.1', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'MANKONO', '19.2', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
--new region
INSERT INTO organization(id, "name", short_name,lastupdated, is_active)
     VALUES( nextval('organization_seq') , 'BOUNKANI-GONTOUGO', '20', now() , 'Y');
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), currval( 'organization_type_seq'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'BONDOUKOU', '20.1', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'BOUNA', '20.2', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'TANDA', '20.3', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    

INSERT INTO organization(id, "name", short_name,lastupdated, is_active, org_id)
    VALUES ( nextval('organization_seq') , 'NASSIAN', '20.4', now() , 'Y', currval( 'organization_seq'));
INSERT INTO organization_organization_type( org_id, org_type_id) VALUES ( currval( 'organization_seq'), (select id from clinlims.organization_type where short_name = 'Health District'));    
