package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Team team = new Team();
            team.setUsername("TeamA");

            em.persist(team);

            //우선 team 객체를 하나 만들어서 저장한 다음에

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team); //*
            //이렇게 연관관계 주인인 곳에 team값을 넣어줘야 하는 것이다.
            //연관관계 주인이 아닌 곳에 넣을 수는 없음. 조회만 가능하다.

//          member.changeTeam(team);

            //이 관례에 의한 getter setter를 다음과 같이 이름을 바꿔서 표현하는 것도 가능하다.

            //member.changeTeam(team); member를 기준으로 team을 바꾸거나
            //team.addMember(member); team을 기준으로 member를 바꾸거나
            team.addMember(member); //team을 기준으로 한다면 이렇게 잡아주면 된다.


            em.persist(member);
//            team.getMembers().add(member); //* 이 두가지를 같이 넣어야 하는 것이다.

// 지금 이것도 주석 처리가 된다고 만약 친다면
//             team을 통해서 member를 추가하고자 하는 것이다.

//            em.flush(); //를 통해서 영속성 캐시 안에 있는 것을 전부 쿼리로 날려서 맞춰주고
//            em.clear(); //캐쉬를 비워서 database에서 나갈 수 있도록 하다.
            //1차 캐싱 안하면, 상태 그대로 들어가 있음. memory에서만 나간다는 것이다.
            //실제로 저장된것이 없으니까!!

            //1차 캐시가 없는 상태에서 db에서 조회하면, jpa가 fk랑 연관관계 이런것들을 다시 매핑해 오는 것이다.
            //1차 캐시에 넣은 상태에서는 값이 따로 없으면, 버그가 날 수도 (jpa)가 읽을 수 엇음



            //그래서 객체지향 적으로 생각해볼떄, 양쪽으로 넣어주는 것이 좋음

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            //Team team0_ = members = select 쿼리가 한방 나가는데, 이거 같은 경우는 members 데이터 끌고와사 사용한느 시점에, 쿼리를 한번 날린다.
            //Member members0_

            //그레서 add로 값을 안 넣어줘도 출력은 된다.

            //킹치만 안 넣어주면 문제가 생길수도 있는디

            //em.flush();
            //em.clear();

            //이렇게 해서 db가 아니라, 연관관계가 1차캐쉬에 있다고 치면, 1차캐쉬에서 조회된 것이 그대로 튀어나온다.
            // collection에 갑이 없음

            System.out.println("============================");
            for(Member m : members){
                System.out.println("m = " + m.getUsername());
            }
            System.out.println("============================");

            //암것도 없는 이유는. collection이 없고 순수한 객체 상태로 아무것도 없이 있기 때문에
            //객체 지향적으로 봤을때, 양쪽에 다 작성하는 것이 중요
            //그리고 jpa없이 순수하게 test case에서 잘 작동하도록 만들기 위해서

            //양방향 연관관계 할때는 그래서 양쪽에다가 전부 다 값을 세팅 하는 것이 좋음


            //이렇게 해서 한번 하나씩 가져와 보자.

            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}

