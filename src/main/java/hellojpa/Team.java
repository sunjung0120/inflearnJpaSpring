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
    //나의 반대편에는 team으로 매핑이 되어있다..이런 의미로 보면 된다.
    //team에 의해서 관리가 된다.
    //그러므로, 이쪽에 뭔가를 넣어 봐야 아무일도 일어나지 않는 다는 의미이다.
    //db에 넣을때만 값에 변경이 있는 것이다.

    //여기에만 값을 집어넣고 왜 db에 안 들어가는지 이런 문제도 생기는 것이다.



    private List<Member> members = new ArrayList<>();
    //이렇게 초기화를 해 두어야 null이 안 생기도록 할 수 있다..?
    //ArrayList할떄 초기화를 해 둬야 add할떄 null point가 안 뜬다.

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
}
