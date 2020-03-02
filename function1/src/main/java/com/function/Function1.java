package com.function;

import org.apache.geode.cache.execute.Function;
import org.apache.geode.cache.execute.FunctionContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.support.LazyWiringDeclarableSupport;
import org.springframework.data.gemfire.support.SpringContextBootstrappingInitializer;

import java.util.Properties;

@Configuration
//@EnableBeanFactoryLocator
@ComponentScan("com.extra")
public class Function1 extends LazyWiringDeclarableSupport implements Function {

	@Override
	public void execute(FunctionContext functionContext) {

		//ApplicationContext c = SpringContextHolder.getContext();

//		ApplicationContext context = new AnnotationConfigApplicationContext(Function1.class) {
//			@Override
//			public ClassLoader getClassLoader() {
//				return Function1.class.getClassLoader();
//			}
//		};

		Properties properties = new Properties();
		properties.setProperty("basePackages", "com.function");
		//properties.setProperty("contextConfigLocations", "");

		SpringContextBootstrappingInitializer init = new SpringContextBootstrappingInitializer();
		SpringContextBootstrappingInitializer.register(Function1.class);
		SpringContextBootstrappingInitializer.setBeanClassLoader(Function1.class.getClassLoader());
		init.init(properties);
		Long subtract = (Long) SpringContextBootstrappingInitializer.getApplicationContext().getBean("Sub");

		//subtract = (Long) context.getBean("Sub");

		functionContext.getResultSender().lastResult(subtract);
	}

	public String getId() {
		return "fun1";
	}

	@Bean("Sub")
	public Long getSubtract() {
		return 23L;
	}
}