--- This file should be run to clean up organizations and projects, before there are any real samples in the database.
--- @author Paul Hill
--- @since  2010-05-27
delete from clinlims.inventory_receipt;
delete from clinlims.sample_projects;
delete from clinlims.organization_organization_type;
delete from clinlims.organization_type;
delete from clinlims.project_organization;
delete from clinlims.project;
delete from clinlims.organization where name != 'Retro-CI';
delete from clinlims.databasechangelog
WHERE ( id='3'  AND author='nixonl' AND filename='CDIRetroCIData.xml' )
   OR ( id='16' AND author='pahill' AND filename='CDIRetroCIData.xml' )
   OR ( id='1' AND author='pahill' AND filename='ARV Centers.xml' )
   OR ( id='1' AND author='pahill' AND filename='RTN Hospital Organizations.xml' )		
   OR ( id='1' AND author='pahill' AND filename='RTN Study Organizations.xml' )		
   OR ( id='1' AND author='pahill' AND filename='EID Study Organizations.xml' );