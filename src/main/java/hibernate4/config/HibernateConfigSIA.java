package hibernate4.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@ComponentScan("hibernate4.data")//try without packages
//Enables Spring's annotation-driven transaction management capability
@EnableTransactionManagement
public class HibernateConfigSIA implements TransactionManagementConfigurer {
	private final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	private final String USERNAME = "raiden";
	private final String PASSWORD = "raiden";
	private final String URL = "jdbc:mysql://localhost:3307/sia_p281_ch10_jdbc";
	
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(DRIVER_CLASS_NAME);
		ds.setUrl(URL);
		ds.setUsername(USERNAME);
		ds.setPassword(PASSWORD);
		return ds;
	}
	
	@Bean
	public SessionFactory sessionFactory(){
		try {
			LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
			sf.setDataSource(dataSource());
			//scan model objects
			sf.setPackagesToScan("hibernate4.domain");
			//sf.setMappingResources("user.hbm.xml"); //if using xml mapping(user.hbm.xml doesn't exist)
			Properties hibernateProps = new Properties();
			hibernateProps.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
			sf.setHibernateProperties(hibernateProps);
			sf.afterPropertiesSet();
			SessionFactory object = sf.getObject();
			return object;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		System.out.println(sessionFactory());
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory());
		return transactionManager;
	}
	
	//adds adviser to any @Repository bean so that platform-specific exceptions
	//are caught and rethrown as a Spring unchecked data-access exceptions
	@Bean
	public BeanPostProcessor persistenceTRanslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
