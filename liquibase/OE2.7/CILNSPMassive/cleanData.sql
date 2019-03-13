SET search_path = clinlims, pg_catalog;
             
	 truncate sample_projects   , 
             sample_human , 
             result_inventory , 
             result_signature, 
             result, 
             analysis, 
             analyzer_results, 
             sample_item, 
             observation_history, 
             sample, 
             provider, 
             patient_identity, 
             patient_patient_type, 
             note, 
             sample_requester, 
             sample_qaevent, 
             referral, 
             patient, 
             person, 
             person_address, 
             report_external_export, 
             report_external_import, 
             history,
             document_track,
             sampletype_test,
             test_reflex,
             test_code,
             analyzer_test_map,
             referral_result,
             test_analyte,
             test_result,
             qa_event,
             result_limits,
             test,
             sampletype_panel,
             type_of_sample,
             panel_item,
             panel CASCADE; 
            
            ALTER SEQUENCE panel_seq restart 1;  
            ALTER SEQUENCE panel_item_seq restart 1;  
            ALTER SEQUENCE type_of_sample_seq restart 1;  
            ALTER SEQUENCE sample_type_panel_seq restart 1;  
            ALTER SEQUENCE test_seq restart 1;  
            ALTER SEQUENCE result_limits_seq restart 1;  
            ALTER SEQUENCE qa_event_seq restart 1;  
            ALTER SEQUENCE test_result_seq restart 1;  
			ALTER SEQUENCE test_analyte_seq restart 1;  
			ALTER SEQUENCE referral_result_seq restart 1;  
			ALTER SEQUENCE test_reflex_seq restart 1;  
            ALTER SEQUENCE sample_type_test_seq restart 1; 
	    	ALTER SEQUENCE note_seq restart 1; 
            ALTER SEQUENCE sample_human_seq restart 1; 
            ALTER SEQUENCE result_inventory_seq restart 1; 
            ALTER SEQUENCE result_signature_seq restart 1; 
            ALTER SEQUENCE result_seq restart 1; 
            ALTER SEQUENCE analysis_seq restart 1; 
            ALTER SEQUENCE sample_item_seq restart 1; 
            ALTER SEQUENCE sample_seq restart 1; 
            ALTER SEQUENCE provider_seq restart 1; 
            ALTER SEQUENCE patient_identity_seq restart 1; 
            ALTER SEQUENCE patient_patient_type_seq restart 1; 
            ALTER SEQUENCE patient_seq restart 1; 
            ALTER SEQUENCE person_seq restart 1; 
            ALTER SEQUENCE report_external_import_seq restart 1; 
            ALTER SEQUENCE report_queue_seq restart 1; 
            ALTER SEQUENCE sample_qaevent_seq restart 1; 
            ALTER SEQUENCE sample_proj_seq restart 1; 
            ALTER SEQUENCE history_seq restart 1;
