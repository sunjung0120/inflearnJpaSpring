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

            //member.changeTeam(team); member를 기준으로 team을 바꾸거나
            //team.addMember(member); team을 기준으로 member를 바꾸거나

            em.persist(member);
//            team.getMembers().add(member);

//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            System.out.println("============================");
            for(Member m : members){
                System.out.println("m = " + m.getUsername());
            }
            System.out.println("============================");
            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}

