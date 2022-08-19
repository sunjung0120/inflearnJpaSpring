package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String username;

    //mappedBy = 일대다 매핑에서 뭐랑 연결이 되어 있지를 보기 위한 것이다.
    @OneToMany(mappedBy = "team") //반대로 일대 다가 되는 것이다.

    private List<Member> members = new ArrayList<>();
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public void addMember(Member member){
        member.setTeam(this);
        members.add(member);
    }
}
