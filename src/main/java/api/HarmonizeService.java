package api;


import api.filter.CorsHeadersFilter;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import config.HarmonizeConfiguration;

public class HarmonizeService extends Service<HarmonizeConfiguration> {


	public static void main(String[] args) throws Exception {
        new HarmonizeService().run(args);
    }
	@Override
    public void initialize(Bootstrap bootstrap) {
        bootstrap.setName("Armonize");
    }

    @Override
    public void run(HarmonizeConfiguration configuration ,Environment environment) {
        environment.addResource(configuration.getArmonizeResource());
        environment.addFilter(new CorsHeadersFilter(), "/*");
    }


}


