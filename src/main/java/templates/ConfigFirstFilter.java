package templates;

import persistence.firstfilter.dao.*;
import services.FirstFilterService;
import services.FirstFilterServiceImp;

/**
 * Created by spukyn on 10/04/14.
 */
public class ConfigFirstFilter {

    public StandardDao getStandardDao() {
        return new StandardDaoImp();
    }

    public MainSentenceDao getMainSentenceDao() {
        return new MainSentenceDaoImp();
    }

    public EnumSentenceDao getEnumSentenceDao() {
        return new EnumSentenceDaoImp();
    }

    public FirstFilterService getFirstFilterService() {
        return new FirstFilterServiceImp(getStandardDao(), getMainSentenceDao(), getEnumSentenceDao());
    }
}
