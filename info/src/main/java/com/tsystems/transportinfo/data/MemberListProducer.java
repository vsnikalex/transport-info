package com.tsystems.transportinfo.data;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

import com.tsystems.transportinfo.model.Member;

@RequestScoped
public class MemberListProducer {

    private List<Member> members;

    // @Named provides access the return value via the EL variable name "members" in the UI (e.g.,
    // Facelets or JSP view)
    @Produces
    @Named
    public List<Member> getMembers() {
        return members;
    }

    public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Member member) {
        retrieveAllMembersOrderedByName();
    }

    @PostConstruct
    public void retrieveAllMembersOrderedByName() {
        Member member = new Member();
        member.setId(1L);
        member.setName("Vasily");
        member.setEmail("vsnikalex@gmail.com");
        member.setPhoneNumber("8921577496");

        List<Member> mockedMembers = new ArrayList<>();
        mockedMembers.add(member);

        members = mockedMembers;
    }

}
