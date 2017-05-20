package service;

import com.cfw.movies.comment.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cfw on 2017/5/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class CommentServiceTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testGetBean(){
        String classpath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String rmiXmlFilePath = classpath + "rmi/rmiImporter.xml";

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"/rmi/rmiImporter.xml"},true,applicationContext);
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader((BeanDefinitionRegistry)applicationContext.getAutowireCapableBeanFactory());
        xmlBeanDefinitionReader.setEntityResolver(new ResourceEntityResolver(applicationContext));
        xmlBeanDefinitionReader.loadBeanDefinitions("/rmi/rmiImporter.xml");

        applicationContext.getBean("userService");
        context.getBean("userService");

    }

}
