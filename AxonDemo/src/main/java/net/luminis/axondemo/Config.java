package net.luminis.axondemo;

import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventhandling.tokenstore.jdbc.GenericTokenTableFactory;
import org.axonframework.eventhandling.tokenstore.jdbc.JdbcTokenStore;
import org.axonframework.serialization.Serializer;
import org.axonframework.spring.jdbc.SpringDataSourceConnectionProvider;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;

@Configuration
@EnableSwagger2
public class Config {

    @Bean
    public TokenStore tokenStore(Serializer serializer) {
        JdbcTokenStore tokenStore = JdbcTokenStore.builder()
                .connectionProvider(new SpringDataSourceConnectionProvider(mysqlDataSource()))
                .serializer(serializer)
                .build();
        tokenStore.createSchema(new GenericTokenTableFactory());
        return tokenStore;
    }

    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/axondemo");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource; // cool
    }

//    @Bean
//    public MongoTemplate axonMongoTemplate() {
//        return new MongoTemplate()
//        return DefaultMongoTemplate.builder().mongoDatabase(mongo(), mongoDatabase).build();
//    }
//
//    @Bean
//    public MongoClient mongo() {
//        MongoFactory mongoFactory = new MongoFactory();
//        mongoFactory.setMongoAddresses(Collections.singletonList(new ServerAddress(mongoHost, mongoPort)));
//        return mongoFactory.createMongo();
//    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
