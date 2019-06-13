package com.cn.jdbc.config;


import com.cn.jdbc.model.PersonRowMapper;
import com.cn.jdbc.service.SplitterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.jdbc.JdbcMessageHandler;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.messaging.MessageHandler;

import javax.sql.DataSource;

@Configuration
public class JdbcConfiguration {


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://local.mysql.com:3306/integration?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }

    @Bean
    public MessageSource<Object> source() {

        JdbcPollingChannelAdapter jdbcPollingChannelAdapter = new JdbcPollingChannelAdapter(dataSource(), "select * from person where mark = 'N'");
        jdbcPollingChannelAdapter.setUpdateSql("update  person set mark = 'Y' where id = :id");
        jdbcPollingChannelAdapter.setRowMapper(new PersonRowMapper());
        return jdbcPollingChannelAdapter;
    }

    @Bean
    public MessageHandler handler() {

        JdbcMessageHandler jdbcMessageHandler = new JdbcMessageHandler(dataSource(), "insert into user (id , name ,age) values (:payload.id,:payload.name,:payload.age)");
        return jdbcMessageHandler;
    }

    @Bean
    public SplitterService splitterService(){
        return new SplitterService();
    }

    @Bean
    public IntegrationFlow doCopy(){
        return IntegrationFlows
                .from(source(),s -> s.poller(Pollers.fixedDelay(10)))
                .split(splitterService())
                .handle(handler())
                .get();
    }


}
