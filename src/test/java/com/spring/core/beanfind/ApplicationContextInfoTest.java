package com.spring.core.beanfind;

import com.spring.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 스프링 컨테이너에 빈이 제대로 등록되었는지 확인하는 테스트
 */
public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " / object = " + bean);
        }
    }

    /**
     * Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
     * Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
     */
    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // bean 하나 하나에 대한 정보
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // 스프링이 내부에서 작업하기 위해 등록한 빈들이 아니라, 프로그래머가 등록한 빈들 또는 외부 라이브러리들
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " / object = " + bean);
            }
        }
    }
}
