package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {
//    AnnotationConfigApplicationContext ac =new AnnotationConfigApplicationContext(AppConfig.class);

//    @Test
//    @DisplayName("빈 설정 메타 정보 확인")
//    void findApplicationBean(){
//        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
//
//            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
//                System.out.println("beanDefinitionName = " + beanDefinitionName +" beanDefinition = " + beanDefinition);
//                // Definition을 통해 scope 판별(싱글톤 판별), autowired 쓰는지 안쓰는지 .. 등의 속성을 확인할 수 있음.
//                // BeanDefinition에 대해서는 너무 깊이있게 이해하기 보다는, 스프링이 다양한 형태의 설정 정보를
//                //BeanDefinition으로 추상화해서 사용하는 것 정도만 이해하면 된다.
//                // 메타 정보를 기반으로 실제 인스턴스를 생성할 수 있다는 것을 알면 된다.
//            }
//        }
//
//    }

        GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");


    @Test
    @DisplayName("빈 설정 메타 정보 확인")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinitionName = " + beanDefinitionName +" beanDefinition = " + beanDefinition);
                // Definition을 통해 scope 판별(싱글톤 판별), autowired 쓰는지 안쓰는지 .. 등의 속성을 확인할 수 있음.
                // BeanDefinition에 대해서는 너무 깊이있게 이해하기 보다는, 스프링이 다양한 형태의 설정 정보를
                //BeanDefinition으로 추상화해서 사용하는 것 정도만 이해하면 된다.
                // 메타 정보를 기반으로 실제 인스턴스를 생성할 수 있다는 것을 알면 된다.
            }
        }

    }

    // xml으로 했을 땐 빈에 대한 정보가 명확하게 들어가있다.
}
