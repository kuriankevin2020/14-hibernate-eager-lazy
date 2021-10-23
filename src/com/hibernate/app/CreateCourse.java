package com.hibernate.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Course;
import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;

public class CreateCourse {

	public static void main(String[] args) {

		Instructor instructor1 = new Instructor("Kurian", "Kevin", "kuriankevin@gmail.com");
		InstructorDetail instructorDetail1 = new InstructorDetail("https://www.kuriankevin.com/youtube",
				"Luv 2 Code!!!");
		instructor1.setInstructorDetail(instructorDetail1);

		Instructor instructor2 = new Instructor("Vineeth", "Neelan", "vineethneelan@gmail.com");
		InstructorDetail instructorDetail2 = new InstructorDetail("https://www.vineethneelan.com/youtube",
				"Luv 2 Code!!!");
		instructor2.setInstructorDetail(instructorDetail2);

		Course course1 = new Course("Course1");
		Course course2 = new Course("Course2");

		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			session.save(instructor1);
			session.save(instructor2);
			session.getTransaction().commit();

			session = factory.getCurrentSession();
			session.beginTransaction();
			instructor1.add(course1);
			instructor1.add(course2);
			session.save(course1);
			session.save(course2);
			session.getTransaction().commit();

		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}

	}

}
