//package com.SuyogHospital.Config;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataSourceConfig {
//
//    @Bean(name = "oldDataSource")
//    public DataSource oldDataSource() {
//        return DataSourceBuilder.create()
//                .driverClassName("com.mysql.cj.jdbc.Driver")
//                .url("jdbc:mysql://localhost:3306/palashrao_db")
//                .username("root")
//                .password("root")
//                .build();
//    }
//
//    @Bean(name = "newDataSource")
//    public DataSource newDataSource() {
//        return DataSourceBuilder.create()
//                .driverClassName("com.mysql.cj.jdbc.Driver")
//                .url("jdbc:mysql://localhost:3306/suyog")
//                .username("root")
//                .password("root")
//                .build();
//    }
//
//    @Bean(name = "oldJdbcTemplate")
//    public JdbcTemplate oldJdbcTemplate(@Qualifier("oldDataSource") DataSource ds) {
//        return new JdbcTemplate(ds);
//    }
//
//    @Bean(name = "newJdbcTemplate")
//    public JdbcTemplate newJdbcTemplate(@Qualifier("newDataSource") DataSource ds) {
//        return new JdbcTemplate(ds);
//    }
//}
