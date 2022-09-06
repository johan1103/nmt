package SWM_RM.NMT.repository;

import SWM_RM.NMT.domain.ProbType;
import SWM_RM.NMT.domain.University;
import SWM_RM.NMT.domain.UniversityProbType;
import SWM_RM.NMT.domain.compositeKey.UniversityProbTypePK;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UniversityProbTypeRepositoryTest {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private ProbTypeRepository probTypeRepository;
    @Autowired
    private UniversityProbTypeRepository universityProbTypeRepository;

    @Test
    public void createUniversityProbTypeRepositoryTest(){
        Long universityId = universityRepository.createUniversity("Inha");
        University findUniversity;
        findUniversity = em.find(University.class,universityId);
        Long probTypeId = probTypeRepository.createProbType("probType1");
        ProbType findProbType;
        findProbType = em.find(ProbType.class,probTypeId);
        UniversityProbTypePK universityProbTypePK = universityProbTypeRepository
                .createUniversityProbType(findUniversity,findProbType);
        UniversityProbType findUniversityProbType = em.find(UniversityProbType.class,universityProbTypePK);
        System.out.println("-------------------");
        System.out.println(findUniversityProbType.getUniversityProbTypeId().getUniversity().getUniversityName());
        System.out.println(findUniversityProbType.getUniversityProbTypeId().getProbType().getTypeName());
        System.out.println(findUniversityProbType.getInterest());
        System.out.println("-------------------");
    }
}