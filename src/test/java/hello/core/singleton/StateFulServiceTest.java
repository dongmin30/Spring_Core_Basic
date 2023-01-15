package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StateFulServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StateFulService stateFulService1 = ac.getBean(StateFulService.class);
        StateFulService stateFulService2 = ac.getBean(StateFulService.class);

        //ThreadA: A 사용자 10000원 주문
        int userAPrice = stateFulService1.order("userA", 10000);

        //ThreadB: B 사용자 20000원 주문
        int userBPrice = stateFulService2.order("userB", 20000);

        //ThreadA: A 사용자 주문 금액 조회 10000원을 기대했지만, 기대와 다르게 20000원 출력
        //int price = stateFulService1.getPrice();
        System.out.println("price = " + userAPrice);

        //Assertions.assertThat(stateFulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StateFulService stateFulService() {
            return new StateFulService();
        }
    }
}