package hellojpa;
import org.hibernate.annotations.common.reflection.XMember;

import javax.persistence.*;
import java.security.PrivilegedAction;

@Entity
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;
    public Member() {
    }

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

// 단순한 getter setter관례랑 이름을 통해 분리가 가능한 것이다.

//    public void changeTeam(Team team){
//        this.team = team;
//        team.getMembers().add(this);
//    }
}


