package com.example.cornconvert.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.example.cornconvert.mapper"})
@EnableTransactionManagement
public class DataBaseConfig {
    @Value("${mybatis.mapper-locations}")
    private String mapperLocation;

    @Bean
    @Primary
    @Qualifier("postgresHikariConfig")
    @ConfigurationProperties(prefix="spring.datasource")
    public HikariConfig postgresConfig() {
        return new HikariConfig();
    }

    @Bean
    @Primary
    @Qualifier("postgresDatasource")
    public DataSource postgresDataSource() {
        return new HikariDataSource(postgresConfig());
    }
	
	@Primary
	@Bean(name="postgresSqlSessionFactory")
	@Qualifier("postgresSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("postgresDatasource") DataSource dataSource, ApplicationContext context)throws Exception{

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(context.getResource("classpath:/MapperConfig.xml"));
//        sqlSessionFactoryBean.setMapperLocations(context.getResources("classpath:/finger/econtract/mapper/*Mapper.xml"));
        sqlSessionFactoryBean.setMapperLocations(context.getResources(mapperLocation));
//        sqlSessionFactoryBean.setMapperLocations(context.getResources("classpath:/finger/*/mapper/*.xml"));

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        sqlSessionFactory.getConfiguration().setAggressiveLazyLoading(true);
        sqlSessionFactory.getConfiguration().setCacheEnabled(false);
        sqlSessionFactory.getConfiguration().setJdbcTypeForNull(JdbcType.VARCHAR); // 입력값이 null 인경우 방지
        sqlSessionFactory.getConfiguration().setCallSettersOnNulls(true); // 조회값자체가 null 인경우 column은 나오도록
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true); // a_b to aB
//        sqlSessionFactory.getConfiguration().setDefaultExecutorType(ExecutorType.BATCH);
        sqlSessionFactory.getConfiguration().setDatabaseId("postgresql");

        return sqlSessionFactory;
//        return sqlSessionFactoryBean.getObject();
    }
	
	@Primary
	@Bean(name="postgresSqlSessionTemplate")
	@Qualifier("postgresSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("postgresSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}