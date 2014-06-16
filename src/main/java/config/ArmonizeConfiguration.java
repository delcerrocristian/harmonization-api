package config;

import api.ArmonizeResource;
import com.yammer.dropwizard.config.Configuration;
import pdfTrat.FullProcessDocument;
import pdfTrat.FullProcessDocumentImp;
import services.iso.IsoService;
import templates.iso.IsoTemplateImp;


public class ArmonizeConfiguration extends Configuration{

    public ArmonizeResource getArmonizeResource(){
        return new ArmonizeResource(getFirstFilterService(), getFullProcessDocument());
    }

    private FullProcessDocument getFullProcessDocument(){
        return new FullProcessDocumentImp(getIsoTemplate());
    }

    private IsoTemplateImp getIsoTemplate(){
        return new IsoTemplateImp(getFirstFilterService());
    }

    private IsoService getFirstFilterService(){
        FirstFilterConfiguration firstFilterConfiguration = new FirstFilterConfiguration();
        return firstFilterConfiguration.getFirstFilterService();
    }


}
