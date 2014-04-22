package api;

import com.yammer.dropwizard.config.Configuration;
import persistence.firstfilter.dao.*;
import services.FirstFilterService;
import services.FirstFilterServiceImp;


public class ArmonizeConfiguration extends Configuration{

    public ArmonizeResource getArmonizeResource(){
        return new ArmonizeResource(getFirstFilterService());
    }

    public StandardDao getStandardDao() {
        return new StandardDaoImp();
    }

    public MainSentenceDao getMainSentenceDao() {
        return new MainSentenceDaoImp();
    }

    public EnumSentenceDao getEnumSentenceDao() {
        return new EnumSentenceDaoImp();
    }

    public MethodToDataBase getMethodToDataBase() {
        return new MethodToDataBaseImp();
    }

    public FirstFilterService getFirstFilterService() {
        return new FirstFilterServiceImp(getStandardDao(), getMainSentenceDao(), getEnumSentenceDao(), getMethodToDataBase());
    }

}
