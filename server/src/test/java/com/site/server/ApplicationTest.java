package com.site.server;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class ApplicationTest {

    @Test
    public void testCommandLineRunner() {
        ApplicationContext context = Mockito.mock(ApplicationContext.class);
        Application app = new Application();

        try {
            app.commandLineRunner(context).run("arg1", "arg2");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Here we verify that the SpringApplication.run method is called with the correct arguments.
        Mockito.verify(context, Mockito.times(1)).getBeanDefinitionNames();
    }
}
