package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean(BeanA.class);
        Assertions.assertThat(beanA).isNotNull();

        assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("BeanB",BeanB.class)
        );

    }

    @Configuration
    @ComponentScan(
        includeFilters = @ComponentScan.Filter(classes = MyIncludeComponent.class),
        excludeFilters = @ComponentScan.Filter(classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig{

    }
}
