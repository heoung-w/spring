package spring.aop.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MemberAspect {
	
	// pointcut & advice method
	@Pointcut("execution(*test*(..))")
	private void testPC() {
	}
	
	@Around("testPC()")
	public Object around(ProceedingJoinPoint j) throws Throwable{
		// 리턴타입 Object, 리턴은 j.proceed(), 첫번쨰 매개변수 ProceedingJoinPoint 타입 변수
		System.out.println("around aop!!!");
		// ----------Before-------------
		return j.proceed();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
