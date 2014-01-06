package api;


import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

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
        environment.addResource(new ArmonizeResource());
    }

}


