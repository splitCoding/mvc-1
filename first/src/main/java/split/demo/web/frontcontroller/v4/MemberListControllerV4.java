package split.demo.web.frontcontroller.v4;

import split.demo.domain.member.Member;
import split.demo.domain.member.MemberRepository;
import split.demo.web.frontcontroller.ModelView;
import split.demo.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        List<Member> members = memberRepository.findAll();
        model.put("members", members);
        return "members";
    }
}
