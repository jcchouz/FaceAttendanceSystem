package com.fas.service;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.fas.dao.AttendanceDao;
import com.fas.dao.StudentDao;
import com.fas.util.PasswordEncryptUtil;
import com.fas.vo.Attendance;
import com.fas.vo.Student;
import com.sun.accessibility.internal.resources.accessibility;

public class StudentService {

	/**
	 * @Title: AndroidRegisterService
	 * @Description: ͨ����׿APPע��
	 * @param id
	 * @param name
	 * @param password
	 * @param email
	 * @return
	 * @exception null
	 */
	public String AndroidRegisterService(String id, String name, String password, String email) {

		Student s = new Student();
		StudentDao stuDao = new StudentDao();

		s = stuDao.queryStuById(id);
		if (s.getStuId() == null) {
			// ��ǰ�û�id������
			s.setStuId(id);
			s.setStuName(name);
			s.setStuPassword(password);
			s.setStuEmail(email);
			stuDao.insertStu(s);
			return "success";
		} else {
			return "id already exists.";
		}

	}

	/**
	 * @Title: AndroidLoginService
	 * @Description: ͨ����׿APP��½
	 * @param id
	 * @param password
	 * @return
	 * @exception null
	 */
	public String AndroidLoginService(String id, String password) {

		Student s = new Student();
		StudentDao stuDao = new StudentDao();
		s = stuDao.queryStuById(id);

		String salt = s.getStuSalt();
		String password_encrypt = PasswordEncryptUtil.SHA512((PasswordEncryptUtil.SHA512(password, "SHA-512") + salt),"SHA-512");

		if (id.equals(s.getStuId()) && password_encrypt.equals(s.getStuPassword())) {
			return "success";
		} else {
			return "fail";
		}

	}
	
	/**
 	 * @Title: AndroidIfRegisteredFaceService 
	 * @Description: �ж��Ƿ�ע�������
	 * @param id
	 * @return      
	 * @exception null
	 */
	public String AndroidIfRegisteredFaceService(String id) {
		
        StudentDao stuDao = new StudentDao();
        Student s = new Student();
        s=stuDao.queryStuById(id);
        
        System.out.println(s.getStuFaceIsRegistered());
        if(s.getStuFaceIsRegistered().equals("false")){
        	return "success";
        }else{
        	return "fail";
        }
		
	}
	
	/**
	 * @Title: AndroidFaceRegisterService 
	 * @Description: ע������
	 * @param id
	 * @param registereFace
	 * @return      
	 * @exception null
	 */
	public String AndroidFaceRegisterService(String id) {
		
		StudentDao stuDao = new StudentDao();
		Student s = new Student();
        s=stuDao.queryStuById(id);
        
        if(s.getStuFaceIsRegistered().equals("false")){
        	s.setStuFaceIsRegistered("true");
        	stuDao.updateStu(s);
        	return "success";
        }else{
        	return "fail";
        }
	}
	
	
	/**
	 * @Title: AndroidAttendanceService
	 * @Description: ѧ���򿨿���
	 * @param id
	 * @param judge
	 * @return
	 * @exception null
	 */
	public String AndroidAttendanceService(String id, String judge) {

		if (judge.equals("true")) {

			// ȷ���򿨵�ѧ��
			StudentDao stuDao = new StudentDao();
			Student s = new Student();
			s = stuDao.queryStuById(id);

			// ��¼���������Ϣ
			AttendanceDao attendaceDao = new AttendanceDao();
			Attendance attendance = new Attendance();
			attendance.setId(id);
			attendance.setName(s.getStuName());
			attendance.setDate(new java.sql.Date(new java.util.Date().getTime()));
			attendance.setTime(new java.sql.Time(System.currentTimeMillis()));

			// �������ݿ�
			attendaceDao.insertAttendance(attendance);
			return "success";
		} else {
			return "fail";
		}
	}
}