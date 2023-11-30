package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("이름없이 타입으로만 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("구체타입으로 조회")
    void findBeanByName2(){
        // 구체타입을 조회 시 유연성이 떨어질 수 있음에 유의.
        MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("빈이름으로 조회x")
    void findBeanByNameX(){
//        MemberService memberService = ac.getBean("xxx", MemberServiceImpl.class);
//        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        // assertThrows : 우측 로직에서 발생한 오류가 좌측에 기입한 오류와 같으면 테스트 성공. 오류안나도 실패.
        assertThrows(NoSuchBeanDefinitionException.class, ()-> ac.getBean("xxx", MemberService.class));
    }
}
