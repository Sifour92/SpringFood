package ru.javawebinar.topjava.web.user;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import ru.javawebinar.topjava.AllActiveProfileResolver;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import javax.annotation.PostConstruct;

@SpringJUnitWebConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-mvc.xml",
        "classpath:spring/spring-db.xml"
})

//@WebAppConfiguration
//@ExtendWith(SpringExtension.class)
@Transactional //перед каждым тестом транзакция начинается - после тестов - откат
//минусы - транзакция в транзакции
//может быт ьмного ошибок
@ActiveProfiles(resolver = AllActiveProfileResolver.class)
abstract public class AbstractControllerTest {

    private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER = new CharacterEncodingFilter();

    static {
        CHARACTER_ENCODING_FILTER.setEncoding("UTF-8");
        CHARACTER_ENCODING_FILTER.setForceEncoding(true);
    }
    protected MockMvc mockMvc; //SpringTest

//    @Autowired
//    private CacheManager cacheManager;
//    @Autowired(required = false)
//    private JpaUtil jpaUtil;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(CHARACTER_ENCODING_FILTER)
                .build();
    }

//    @BeforeEach
//    public void setUp() {
//        cacheManager.getCache("users").clear();
//        if (jpaUtil != null) {
//            jpaUtil.clear2ndLevelHibernateCache();
//        }
//    }
}