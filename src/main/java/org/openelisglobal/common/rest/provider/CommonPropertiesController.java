package org.openelisglobal.common.rest.provider;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;

import java.util.Map;
import java.util.HashMap; 

@RestController
@RequestMapping("/rest/properties")
public class CommonPropertiesController {

     @Autowired
    private ConfigurableEnvironment env;

    @GetMapping
    public ResponseEntity<Map<String, String>> getProperties() {
        Map<String, String> properties = new HashMap<>();
        env.getPropertySources().forEach(propertySource -> {
            if (propertySource instanceof MapPropertySource) {
                MapPropertySource mapPropertySource = (MapPropertySource) propertySource;
                Arrays.stream(mapPropertySource.getPropertyNames()).forEach(propertyName -> {
                    // Filter properties with prefix starting "org.openelis"
                    if (propertyName.startsWith("org.openelisglobal")) {
                        properties.put(propertyName, mapPropertySource.getProperty(propertyName).toString());
                    }
                });
            }
        });
        return ResponseEntity.ok(properties);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> updateProperties(@RequestBody Map<String, String> updatedProperties) {
        Map<String, String> response = new HashMap<>();

        org.springframework.core.env.MutablePropertySources propertySources = env.getPropertySources();

        updatedProperties.forEach((key, value) -> {
            for (org.springframework.core.env.PropertySource<?> propertySource : propertySources) {
                if (propertySource instanceof org.springframework.core.env.MapPropertySource) {
                    org.springframework.core.env.MapPropertySource mapPropertySource = (org.springframework.core.env.MapPropertySource) propertySource;
                    if (mapPropertySource.containsProperty(key)) {

                        Map<String, Object> updatedSource = new HashMap<>(mapPropertySource.getSource());
                        updatedSource.put(key, value);

                        propertySources.replace(mapPropertySource.getName(), new MapPropertySource(mapPropertySource.getName(), updatedSource));

                        response.put(key, value);
                        break;
                    }
                }
            }
        });

        return ResponseEntity.ok(response);
    }
}
    

