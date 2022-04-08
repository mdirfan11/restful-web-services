package com.mdtech.restfulwebservices.controllers;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.mdtech.restfulwebservices.beans.SomeBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filter")
    public MappingJacksonValue getFiltering() {
        SomeBean someBean = new SomeBean("field1", "field2", "field3", "field4");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        mapping.setFilters(filterProvider);
        return mapping;
    }

    @GetMapping("/filter-list")
    public MappingJacksonValue getFilterList() {
        List<SomeBean> lists =  Arrays.asList(
                new SomeBean("field1", "field2", "field3", "field4"),
                new SomeBean("field12", "field23", "field34", "field45"),
                new SomeBean("field31", "field32", "field33", "field34"),
                new SomeBean("field41", "field21", "field31", "field41"),
                new SomeBean("field50", "field52", "field53", "field54")
        );

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field3", "field4");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(lists);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }
}
