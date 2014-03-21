package api;


import api.filter.CorsHeadersFilter;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import org.springframework.context.ApplicationContext;
import persistence.firstfilter.dao.StandardDaoImp;

public class ArmonizeService extends Service<ArmonizeConfiguration> {


	public static void main(String[] args) throws Exception {
        new ArmonizeService().run(args);
    }
	@Override
    public void initialize(Bootstrap bootstrap) {
        bootstrap.setName("Armonize");
    }

    @Override
    public void run(ArmonizeConfiguration configuration ,Environment environment) {
        environment.addResource(configuration.getArmonizeResource());
        environment.addFilter(new CorsHeadersFilter(), "/*");
    }


}


