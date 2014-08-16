package config;

import api.HarmonizeResource;
import com.yammer.dropwizard.config.Configuration;
import pdfTrat.CmmiFullProcessDocumentImp;
import pdfTrat.FullProcessDocument;
import pdfTrat.FullProcessDocumentImp;
import services.cmmi.CmmiService;
import services.iso.IsoService;
import templates.cmmi.CmmiAlgorithm;
import templates.cmmi.CmmiDefaultAlgorithm;
import templates.cmmi.CmmiTemplate;
import templates.cmmi.CmmiTemplateImp;
import templates.iso.IsoTemplateImp;

/**
 * Created by Cristian del Cerro.
 */


public class HarmonizeConfiguration extends Configuration{

    public HarmonizeResource getArmonizeResource(){
        return new HarmonizeResource(getIsoService(), getFullProcessDocument(), getCmmiService(),
                getCmmiFullProcessDocument());
    }

    private FullProcessDocument getFullProcessDocument(){
        return new FullProcessDocumentImp(getIsoTemplate());
    }


    private IsoTemplateImp getIsoTemplate(){
        return new IsoTemplateImp(getIsoService());
    }

    private IsoService getIsoService(){
        FirstFilterConfiguration firstFilterConfiguration = new FirstFilterConfiguration();
        return firstFilterConfiguration.getFirstFilterService();
    }

    private CmmiFullProcessDocumentImp getCmmiFullProcessDocument() {
        return new CmmiFullProcessDocumentImp(getCmmiTemplate());
    }

    private CmmiTemplate getCmmiTemplate(){
        return new CmmiTemplateImp(getCmmiAlgorithm());
    }

    private CmmiAlgorithm getCmmiAlgorithm() {
        return new CmmiDefaultAlgorithm(getCmmiService());
    }

    private CmmiService getCmmiService() {
        CmmiConfiguration cmmiConfiguration = new CmmiConfiguration();
        return cmmiConfiguration.getCmmiService();
    }




}
