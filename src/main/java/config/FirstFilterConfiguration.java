package config;

import persistence.firstfilter.iso.dao.*;
import services.iso.IsoService;
import services.iso.IsoServiceImp;

/**
 * Created by Cristian del Cerro
 */
public class FirstFilterConfiguration {

    public IsoService getFirstFilterService() {
        return new IsoServiceImp(getStandardDao(), getProcessDao(), getActivityDao(), getTaskDao());
    }

    private StandardDao getStandardDao() {
        return new StandardDaoImp();
    }

    private ProcessDao getProcessDao() {
        return new ProcessDaoImp();
    }

    private ActivityDao getActivityDao() {
        return new ActivityDaoImp();
    }

    private TaskDao getTaskDao(){
        return new TaskDaoImp();
    }
}
