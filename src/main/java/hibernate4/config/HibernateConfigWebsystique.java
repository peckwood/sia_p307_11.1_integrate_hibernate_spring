package hibernate4.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
//project url: http://websystique.com/springmvc/spring-4-mvc-and-hibernate4-integration-example-using-annotations/
@Configuration
@ComponentScan("hibernate4.data")
@EnableTransactionManagement
public class HibernateConfigWebsystique {
	private final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	private final String USERNAME = "raiden";
	private final String PASSWORD = "raiden";
	private final String URL = "jdbc:mysql://localhost:3307/sia_p281_ch10_jdbc";
@Bean
public LocalSessionFactoryBean sessionFactory(){
	LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	sessionFactory.setDataSource(dataSource());
	sessionFactory.setPackagesToScan("hibernate4.domain");
	sessionFactory.setHibernateProperties(hibernateProperties());
	return sessionFactory;
}

private Properties hibernateProperties() {
	Properties hibernateProps = new Properties();
	hibernateProps.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
	return hibernateProps;
}

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
@Autowired
public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
	System.out.println(sessionFactory);
	HibernateTransactionManager txManager = new HibernateTransactionManager();
	txManager.setSessionFactory(sessionFactory);
	return txManager;
}
	
	
}
