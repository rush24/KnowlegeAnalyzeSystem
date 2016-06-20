package edu.fjnu.test.service;

import org.apache.commons.dbutils.QueryRunner;

import org.junit.Test;

import cn.itcast.jdbc.TxQueryRunner;
import edu.fjnu.dao.GradeDao;
import edu.fjnu.domain.Student;
import edu.fjnu.domain.Teacher;
import edu.fjnu.service.RelationService;

public class TestService {
//	QueryRunner qr = new TxQueryRunner();
//	Teacher teacher = new Teacher();
//	Student student = new Student();
	
//	TeacherService teacherService = new TeacherService();
//	GradeDao gradeImpl = new GradeDao();
	
//	@Test
//	public void testStudentService(){
//		Student student = new Student();
//		student.setSname("admin");
//		student.setSpassword("admin");
//		StudentService stuService = new StudentService();
//		stuService.checkInfo(student);
//	}
//	
//	@Test
//	public void fun(){
//		String abc = "223";
//		
//		if(abc.substring(0 ,1).equals("1")){
//			System.out.println("ok");
//		} else if (abc.substring(0,1).equals("2")){
//			System.out.println("error");
//		}else{
//			System.out.println("none");
//		}
////		System.out.println(abc.substring(0,2));
//	}
//	
//	@Test
//	public void checkInfo(){
//		Student stu = new Student();
//		stu.setStudentID("2");
//		stu.setSpassword("student");
////		StudentDaoImpl stuImpl = new StudentDaoImpl();
////		System.out.println(stuImpl.checkInfo(stu));
//		StudentService stuService = new StudentService();
//		if(stuService.checkInfo(stu)){
//			System.out.println("成功");
//		}else{
//			System.out.println("失败");
//		}
////		if(stuImpl.checkInfo(stu) == null){
////			System.out.println("登录失败");
////		}else{
////			System.out.println("登录成功");
////		}
//	}
//	
//	@Test
//	public void searchTeaInfo(){
//		Teacher teacher = new Teacher();
//		teacher.setTeacherID("01");
//		teacher.setTpassword("123");
//		
//		TeacherDao teaImpl = new TeacherDao();
//		teacher = teaImpl.teacherInfo(teacher);
//		String tname = teacher.getTname();
//		String course = teacher.getCourse();
//		String tsex = teacher.getTsex();
//		System.out.println(tname + course + tsex);
//	}
//	@Test
////	测试查询学生成绩
//	public void getScoreInfo(){
//		String sql = "select * from student,scourse,sscore where "
//				+ "student.studentID=sscore.studentID "
//				+ "and scourse.courseID=sscore.courseID "
//				+ "and student.studentID=?";
//		QueryRunner qr = new TxQueryRunner();
//		
//		try {
//			Map map = qr.query(sql, new MapHandler(), "201401001");
//			Student student = CommonUtils.toBean(map, Student.class);
//			Scourse scourse = CommonUtils.toBean(map, Scourse.class);
//			Sscore sscore = CommonUtils.toBean(map, Sscore.class);
//			
//			student.setSscore(sscore);//学生表关联成绩表
//			student.setScourse(scourse);//学生表关联课程表
//			
//			scourse = student.getScourse();
//			System.out.println(scourse);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
//	
//	@Test
//	public void getInfo1(){
//		String sql = "select * from student,scourse,sscore where "
//				+ "student.studentID=sscore.studentID "
//				+ "and scourse.courseID=sscore.courseID "
//				+ "and student.studentID=?";
//		QueryRunner qr = new TxQueryRunner();
//		try {
//			Map map = qr.query(sql, new MapHandler(), "201401001");
//			Student student = CommonUtils.toBean(map, Student.class);
//			System.out.println(student);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
//	@Ignore
//	public void teaInfo(){
////		TeacherDaoImpl teaImpl = new TeacherDaoImpl();
////		Teacher teacher = new Teacher();
////		teacher.setTeacherID("01");
////		teacher = teaImpl.teacherInfo(teacher);
////		Scourse scourse = teacher.getScourse();
////		String classyear = scourse.getClassyear();
////		String teachername = teacher.getTname();
////		String course = teacher.getCourse();
////		String tclass = teacher.getTclass();
////		System.out.println(teachername + course + tclass + classyear);
//		
//		Teacher teacher = new Teacher();
//		teacher.setTeacherID("01");
//		TeacherService teacherService = new TeacherService();
//		System.out.println(teacherService.teacherInfo(teacher));
//	}
//	@Test
//	public void teaInfo2(){
//		String sql = "SELECT distinct tname, classyear, tclass,course FROM teacher,scourse,stcourse where  teacher.teacherID=stcourse.teacherID AND stcourse.courseID=scourse.courseID AND  teacher.teacherID='01'";
//		Teacher teacher = new Teacher();
//		Scourse scourse = new Scourse();
//		Stcourse stcourse = new Stcourse();
//		
//		QueryRunner qr = new TxQueryRunner();
//		try {
//			Map map = qr.query(sql, new MapHandler());
//			teacher = CommonUtils.toBean(map, Teacher.class);
//			scourse = CommonUtils.toBean(map, Scourse.class);
//			stcourse = CommonUtils.toBean(map, Stcourse.class);
//			teacher.setScourse(scourse);
//			teacher.setStcourse(stcourse);
//			System.out.println(teacher);
//		} catch (Exception e) {
//		}
//	}
//	
//	@Ignore
//	public void teaInfo3(){
//		Teacher teacher = new Teacher();
//		teacher.setTeacherID("01");
//		TeacherDao tdao = new TeacherDao();
//		teacher = tdao.teacherInfo(teacher);
//		Scourse scourse = teacher.getScourse();
//		Stcourse stcourse = teacher.getStcourse();
//		System.out.println(scourse);
//		System.out.println(stcourse);
//	}
//	@Test
//	public void teaStuList() throws SQLException{
//		Teacher teacher = new Teacher();
//		String[] studentList = null;
//		String sql = "select distinct sname from student ,teacher,stcourse where student.studentID=stcourse.studentID " 
//				+ "and teacher.teacherID=stcourse.teacherID and teacher.teacherID= '01'";
//		List stuList = qr.query(sql, new BeanListHandler<Student>(Student.class));
//		for(int i=0; i<stuList.size(); i++){
//			Student student = (Student) stuList.get(i);
////			System.out.println(student.getSname());
//			System.out.println(stuList.get(i));
//		}
//	}
//	
//	@Ignore
//	public void getScore() throws SQLException{
//		String sql = "select * from student, scourse, sscore where"
//				+" student.studentID = sscore.studentID "
//				+" and scourse.courseID = sscore.courseID "
//				+" and student.studentID='201401001'";
//		Map map = qr.query(sql, new MapHandler());
//		Sscore score = CommonUtils.toBean(map, Sscore.class);
//		System.out.println(score);
//	}
//	@Test
//	public void stuScore() throws SQLException{
////		String sql = "select max(score) ,testID from sscore where courseid ='30101'  group by testid";
////		Map map = qr.query(sql, new MapHandler());
////		String sql = "select testid,max(score) from sscore group by testid";
////		String sql1 = "select score,testid from sscore order by score desc";
////		List<Map<String, Object>> map = qr.query(sql, new MapListHandler());
////		Sscore score = qr.query(sql, new BeanHandler<Sscore>(Sscore.class));
////		Sscore score = CommonUtils.toBean(map, Sscore.class);
////		List<Sscore> scoreList = qr.query(sql1, new BeanListHandler<Sscore>(Sscore.class));
////		for(int i=0; i<scoreList.size(); i++){
////			System.out.println(scoreList.get(i));
////		}
////		String sql1 = "select distinct testid from sscore order by testid";
////		List<Sscore> testidList = qr.query(sql1, new BeanListHandler<Sscore>(Sscore.class));
////		
////		for(int i=0; i<testidList.size(); i++){
////			String testid = testidList.get(i).getTestID();
////			String sql2 = "select score from sscore  where testid = ? order by score desc";
////			
////		}
//		String sql = "select max(score) as score,testid from sscore group by testid";
//		List<Sscore> list = qr.query(sql, new BeanListHandler<Sscore>(Sscore.class));
//		
//		for(int i=0; i<list.size(); i++){
//			Sscore sscore = new Sscore();
//			sscore  = list.get(i);
//			System.out.println(sscore.getScore() + " \t" + sscore.getTestID());
//		}
//	}
//	
//	@Test
//	public void getMaxList(){
//		GradeDao daoImpl = new GradeDao();
//		Scourse scourse = new Scourse();
//		scourse.setCourseID("30101");
//		System.out.println(daoImpl.getMaxScoreList(scourse));
//	}
//	@Test
//	public void stuInfo(){
//		teacher.setTeacherID("01");
//		System.out.println(teacherService.getStuList(teacher.getTeacherID()));;
//		
//	}
//	@Test
//	public void teaInfo1(){
//		teacher.setTeacherID("01");
//		System.out.println(teacherService.teacherInfo(teacher));
//	}
	
//	@Test
//	public void chinesePR(){
//		Student student = new Student();
//		student.setStudentID("201401001");
//		Scourse scourse = new Scourse();
//		scourse.setSubject("语文");
//		List<Score> list = gradeImpl.getChinesePR(student, scourse);
//		System.out.println(list);
//	}
	
	//输入学生id 查询学生年级
//	@Test
//	public void getGradeBySID(){
//		StudentTestService sts = new StudentTestService();
//		List<VScope> vScopeList = sts.getStudentGradeList(2560);
//		String gradeName = vScopeList.get(3).getName();
//		System.out.println(gradeName);
//		String studentName = "林彦希";
//		String subject = "语文";
//		List<VTestMain> vTestMainList= sts.getTestMainsList(subject, studentName, gradeName);
//		System.out.println(vTestMainList);
//	}
//	@Test
//	public void getNameBysID(){
//		StudentDao sd = new StudentDao();
//		Student student = new Student();
//		student.setStudentID("2560");
//		Student studentInfo = sd.studentInfo(student);
//		System.out.println(studentInfo);
//	}
//	
//	@Test 
//	public void getGradeList(){
//		RelationService rs = new RelationService();// 用来处理关联分析模块的d3图以及表格数据
//		String[] keywordArr = rs.keywordArray("语文","三年级（上）");// 设置d3的点
//		Object[][] stvArr = rs.stvArray();
//		for (String string : keywordArr) {
//			System.out.println(string);
//		}
//		System.out.println("-----------------------------");
//		for (Object[] objects : stvArr) {
//			
//		}
//	}
}



