package edu.alenkin.busyman.repository;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@SpringBootTest
@Sql(scripts = {"classpath:db/populateDB.sql"})
@ActiveProfiles("test")
public class AbstractRepositoryTest {

}
