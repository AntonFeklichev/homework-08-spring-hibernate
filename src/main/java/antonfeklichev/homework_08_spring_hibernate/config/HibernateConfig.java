package antonfeklichev.homework_08_spring_hibernate.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class HibernateConfig {

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource,
                                         @Value("${hibernate.dialect}") String dialect,
                                         @Value("${hibernate.hbm2ddl.auto}") String ddl,
                                         @Value("${hibernate.show_sql}") String showSql,
                                         @Value("${hibernate.format_sql}") String formatSql,
                                         @Value("${hibernate.use_sql_comments}") String comments) {

        Map<String, Object> settings = new HashMap<>();
        settings.put(Environment.DATASOURCE, dataSource);
        settings.put(Environment.DIALECT, dialect);
        settings.put(Environment.HBM2DDL_AUTO, ddl);
        settings.put(Environment.SHOW_SQL, showSql);
        settings.put(Environment.FORMAT_SQL, formatSql);
        settings.put(Environment.USE_SQL_COMMENTS, comments);

        var registry = new StandardServiceRegistryBuilder()
                .applySettings(settings)
                .build();

        var metadata = new org.hibernate.boot.MetadataSources(registry)
                .addAnnotatedClass(antonfeklichev.homework_08_spring_hibernate.model.User.class)
                .getMetadataBuilder()
                .build();

        return metadata.getSessionFactoryBuilder().build();
    }
}
