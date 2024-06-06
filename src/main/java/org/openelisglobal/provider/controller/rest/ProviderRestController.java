package org.openelisglobal.provider.controller;

import org.openelisglobal.person.service.PersonService;
import org.openelisglobal.person.valueholder.Person;
import org.openelisglobal.provider.service.ProviderService;
import org.openelisglobal.provider.valueholder.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/rest")
public class ProviderRestController {

    @Autowired
    private ProviderService providerService;
    @Autowired
    private PersonService personService;

    @GetMapping(value = "/Provider/raw/{id}")
    @ResponseBody
    public Provider getProvider(@PathVariable String id) {
        Provider provider = providerService.get(id);
        return provider;
    }

    @GetMapping(value = "/Provider/Person/{id}")
    @ResponseBody
    public Person getPerson(@PathVariable String id) {
        Person person = personService.get(id);
        return person;
    }
}
