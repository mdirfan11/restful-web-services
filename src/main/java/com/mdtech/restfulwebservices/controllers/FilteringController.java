package com.mdtech.restfulwebservices.controllers;

import com.mdtech.restfulwebservices.beans.SomeBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filter")
    public SomeBean getFiltering() {
        return new SomeBean("field1", "field2", "field3", "field4");
    }

    @GetMapping("/filter-list")
    public List<SomeBean> getFilterList() {
        return Arrays.asList(
                new SomeBean("field1", "field2", "field3", "field4"),
                new SomeBean("field12", "field23", "field34", "field45"),
                new SomeBean("field31", "field32", "field33", "field34"),
                new SomeBean("field41", "field21", "field31", "field41"),
                new SomeBean("field50", "field52", "field53", "field54")
        );
    }
}
