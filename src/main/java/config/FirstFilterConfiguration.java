package config;

import persistence.firstfilter.dao.*;
import services.FirstFilterService;
import services.FirstFilterServiceImp;

/**
 * Created by spukyn on 6/05/14.
 */
public class FirstFilterConfiguration {

    public FirstFilterService getFirstFilterService() {
        return new FirstFilterServiceImp(getStandardDao(), getProcessDao(), getActivityDao(), getTaskDao());
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
