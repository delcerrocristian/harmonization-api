package config;

import api.ArmonizeResource;
import com.yammer.dropwizard.config.Configuration;
import persistence.firstfilter.dao.*;
import services.FirstFilterService;
import services.FirstFilterServiceImp;


public class ArmonizeConfiguration extends Configuration{

    public ArmonizeResource getArmonizeResource(){
        FirstFilterConfiguration firstFilterConfiguration = new FirstFilterConfiguration();
        return new ArmonizeResource(firstFilterConfiguration.getFirstFilterService());
    }



}
