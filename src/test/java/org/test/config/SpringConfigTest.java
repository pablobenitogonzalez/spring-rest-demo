package org.test.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = MainConfig.class),
        @ContextConfiguration(classes = WebMvcConfig.class) })
@ActiveProfiles("test")
public class SpringConfigTest {

    @Autowired
    private WebApplicationContext wac;

    @Test
    public void springConfiguration() {
        assertNotNull(wac);
    }
}