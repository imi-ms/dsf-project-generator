package { packageName }.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import { packageName }.listener.ProcessPluginDeploymentListenerImpl;
import { packageName }.service.HelloWorld;
import { packageName }.service.LogUserTaskResponse;
import dev.dsf.bpe.v2.ProcessPluginDeploymentListener;
import dev.dsf.bpe.v2.spring.ActivityPrototypeBeanCreator;

@Configuration
public class HelloWorldConfig
{
	@Bean
	public static ActivityPrototypeBeanCreator activityPrototypeBeanCreator()
	{
		return new ActivityPrototypeBeanCreator(HelloWorld.class, LogUserTaskResponse.class);
	}

	@Bean
	public ProcessPluginDeploymentListener deploymentListener()
	{
		return new ProcessPluginDeploymentListenerImpl();
	}
}
