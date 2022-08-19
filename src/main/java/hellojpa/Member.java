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

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;
    //반대편 사이트에 이렇게 team으로 매핑이 되어 있다, 이런식으로 하는 것이다.

    //이런식으로 객체 클래스를 직접 내부에 생성하는 방식이ㅣ 단방향 이다.
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
        //meeber에 setteam을 할때
        team.getMembers().add(this);
        //이걸 넣어서, 하나만 호출해도 걸리도록 하는 것이다.
        //나자신, member의 instence를 넣어야 하니까 this로 넣기
    }

// 단순한 getter setter관례랑 이름을 통해 분리가 가능한 것이다.

//    public void changeTeam(Team team){
//        this.team = team;
//        team.getMembers().add(this);
//    }




}


