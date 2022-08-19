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
            member.setTeam(team);

            //이렇게 바로 team으로 연결할 수 있다.

            em.persist(member);

            em.flush(); //를 통해서 영속성 캐시 안에 있는 것을 전부 쿼리로 날려서 맞춰주고
            em.clear(); //캐쉬를 비워서 database에서 나갈 수 있도록 하다.

            //이걸 이용해서 db에서 깔끔하게 값을 가져오도록 한다.
            //반대 방향으로도 이렇게 탐색이 가능해진다.


            //조회할때도, getTeam이라고 해서 바로 끄집어 내면 된다.
            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();

            //team을 불러서 바로 member 찾기

            for(Member m : members){
                System.out.println("m = " + m.getUsername());
            }

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

