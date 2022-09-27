package SWM_RM.NMT.mockCreate;

import SWM_RM.NMT.config.auth.Role;
import SWM_RM.NMT.domain.Problem;
import SWM_RM.NMT.domain.University;
import SWM_RM.NMT.domain.User;
import SWM_RM.NMT.domain.UserGrade;
import SWM_RM.NMT.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;


@Repository
@Transactional
public class CreateMockData {
    @Autowired
    private EntityManager em;
    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserGradeRepository userGradeRepository;
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private ProbTypeRepository probTypeRepository;
    public CreateMockData(){
    }

    public void createUser(){
        for(int i=0;i<10;i++){
        }
    }

    public void createUniversity(){
        for(int i=0;i<5;i++){
            universityRepository.createUniversity("university"+i);
        }
    }
    public void createProbType(){
        for(int i=0;i<3;i++){
            probTypeRepository.createProbType("probType"+i);
        }
    }
    public void createProblem(){
        for(int i=0;i<20;i++){
            Problem problem = new Problem();
            problem.setProbTitle("problem"+i);
            problem.setProbText("problem text"+i);
            problem.setCreateYear(2020L);
            problem.setProbExp("problem explain "+i);
            problem.setBestText("problem bestText "+i);
        }
    }

}
