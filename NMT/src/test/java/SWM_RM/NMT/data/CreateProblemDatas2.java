package SWM_RM.NMT.data;

import SWM_RM.NMT.domain.ProbType;
import SWM_RM.NMT.domain.Problem;
import SWM_RM.NMT.domain.University;
import SWM_RM.NMT.repository.ProbTypeRepository;
import SWM_RM.NMT.repository.ProblemRepository;
import SWM_RM.NMT.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CreateProblemDatas2 {

    public CreateProblemDatas2(ProblemRepository problemRepository, ProbTypeRepository probTypeRepository,
                               UniversityRepository universityRepository){
        this.problemRepository=problemRepository;
        this.probTypeRepository=probTypeRepository;
        this.universityRepository=universityRepository;
    }

    private ProblemRepository problemRepository;
    private ProbTypeRepository probTypeRepository;
    private UniversityRepository universityRepository;

    public void createDatas(){

        //요약', '비교․ 대조', '설명․ 해설', '분석 유형이 존재
        ProbType probType1 = new ProbType();
        probType1.setTypeName("요약");
        ProbType probType2 = new ProbType();
        probType2.setTypeName("비교. 대조");
        ProbType probType3 = new ProbType();
        probType3.setTypeName("설명. 해설");
        ProbType probType4 = new ProbType();
        probType4.setTypeName("분석");

        probTypeRepository.createProbType(probType1);
        probTypeRepository.createProbType(probType2);
        probTypeRepository.createProbType(probType3);
        probTypeRepository.createProbType(probType4);



        //대학교는 서울시립대, 고려대학교, 성균관대학교, 경희대학고, 인하대학교가 존재

        University university1 = new University();
        university1.setUniversityName("고려대학교");
        University university2 = new University();
        university2.setUniversityName("서울시립대학교");
        University university3 = new University();
        university3.setUniversityName("성균관대학교");
        University university4 = new University();
        university4.setUniversityName("경희대학교");
        University university5 = new University();
        university5.setUniversityName("인하대학교");

        universityRepository.createUniversity(university1);
        universityRepository.createUniversity(university2);
        universityRepository.createUniversity(university3);
        universityRepository.createUniversity(university4);
        universityRepository.createUniversity(university5);


        Problem problem1 = setProblemAuto("problem1");
        Problem problem2 = setProblemAuto("problem2");
        Problem problem3 = setProblemAuto("problem3");
        Problem problem4 = setProblemAuto("problem4");
        Problem problem5 = setProblemAuto("problem5");
        Problem problem6 = setProblemAuto("problem6");
        Problem problem7 = setProblemAuto("problem7");
        Problem problem8 = setProblemAuto("problem8");
        Problem problem9 = setProblemAuto("problem9");
        Problem problem10 = setProblemAuto("problem10");

        problemRepository.createProblem(problem1, universityRepository.findUniversityByName("고려대학교"),
                probTypeRepository.findProbTypeByName("요약"));
        problemRepository.createProblem(problem2, universityRepository.findUniversityByName("서울시립대학교"),
                probTypeRepository.findProbTypeByName("비교. 대조"));
        problemRepository.createProblem(problem3, universityRepository.findUniversityByName("성균관대학교"),
                probTypeRepository.findProbTypeByName("설명. 해설"));
        problemRepository.createProblem(problem4, universityRepository.findUniversityByName("경희대학교"),
                probTypeRepository.findProbTypeByName("분석"));
        problemRepository.createProblem(problem5, universityRepository.findUniversityByName("인하대학교"),
                probTypeRepository.findProbTypeByName("요약"));
        problemRepository.createProblem(problem6, universityRepository.findUniversityByName("고려대학교"),
                probTypeRepository.findProbTypeByName("비교. 대조"));
        problemRepository.createProblem(problem7, universityRepository.findUniversityByName("서울시립대학교"),
                probTypeRepository.findProbTypeByName("설명. 해설"));
        problemRepository.createProblem(problem8, universityRepository.findUniversityByName("성균관대학교"),
                probTypeRepository.findProbTypeByName("분석"));
        problemRepository.createProblem(problem9, universityRepository.findUniversityByName("경희대학교"),
                probTypeRepository.findProbTypeByName("요약"));
        problemRepository.createProblem(problem10, universityRepository.findUniversityByName("인하대학교"),
                probTypeRepository.findProbTypeByName("비교. 대조"));

    }

    public static Problem setProblemAuto(String problemName){
        Problem problem = new Problem();
        problem.setCreateYear(2022L);
        problem.setProbExp(problemName+" Exp");
        problem.setBestText(problemName+" Best Text");
        problem.setProbText(problemName+" Prob Text");
        problem.setProbTitle(problemName);
        problem.setCompetetionRate(6.56D);
        return problem;
    }
}
