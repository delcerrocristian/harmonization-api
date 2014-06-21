package config;

import persistence.firstfilter.cmmi.dao.*;
import persistence.firstfilter.cmmi.model.SpecificGoal;
import persistence.firstfilter.iso.dao.ActivityDao;
import services.cmmi.CmmiService;
import services.cmmi.CmmiServiceImp;

/**
 * Created by Cristian del Cerro.
 */
public class CmmiConfiguration {
    public CmmiService getCmmiService() {
        return new CmmiServiceImp(getStandardDao(), getSpecificGoalDao(), getProcessDao(), getSpecificPracticeDao(),
                getWorkProductDao());
    }

    private StandardDao getStandardDao() {
        return new StandardDaoImp();
    }

    private ProcessDao getProcessDao() {
        return new ProcessDaoImp();
    }

    private SpecificGoalDao getSpecificGoalDao() {
        return new SpecificGoalDaoImp();
    }

    private SpecificPracticeDao getSpecificPracticeDao() {
        return new SpecificPracticeDaoImp();
    }

    private WorkProductDao getWorkProductDao() {
        return new WorkProductDaoImp();
    }
}
