package split.demo.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member("kim", 1);

        //when
        Member savedMember = memberRepository.save(member);

        //then
        Member result = memberRepository.findById(savedMember.getId());
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("kim1", 1);
        Member member2 = new Member("kim2", 2);

        //when
        memberRepository.save(member1);
        memberRepository.save(member2);
        List<Member> result = memberRepository.findAll();

        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(member1, member2);
    }
}