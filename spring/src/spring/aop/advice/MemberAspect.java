package spring.aop.advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
public class MemberAspect {
	
	// advice 메서드의 () 안에는 pointcut 어노테이션이 적용도니 메서드명 또는 pointcut 표현식이 올수 있다.
	@Around("execution(* test*(..))")
	public Object around(ProceedingJoinPoint j) throws Throwable{
		System.out.println("around!!!!!");
		/*
		// 로그인 체크
		// ProceedingJoinPoint 로 HttpServletRequest 꺼내기
		// 타겟메서드의 매개변수에도 HttpServletRequest request 가 존재해야함.
		// 리턴타입이 동일해야하는데 현재 타겟메서드의 리턴타입 String
		Object [] obj = j.getArgs();	// 타겟 메서드로 매개변수 꺼내줌
		for(Object o : obj) {
			if(o instanceof HttpServletRequest) {
				HttpServletRequest request = (HttpServletRequest)o;
				HttpSession session = request.getSession();
				String memId = (String)session.getAttribute("memId");
				if(memId == null) { // 로그인 안된상태
					System.out.println("로그인 하세요");
					return "aopMember/loginForm";	// 바로 로그인 폼으로 이동하게 해준다
					// 리턴형태가 타겟메서드 (핵심메서드) 리턴타입과 동일하게 처리해줘
				}
			}
			
		}*/
		
		// 로그인 체크 : MVC에서 HttpServletRequest 꺼내기
		ServletRequestAttributes sa = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = sa.getRequest();
		HttpSession session = request.getSession();
		if(session.getAttribute("memId") == null) {
			System.out.println("로그인하세요");
			return "aopMember/loginForm";
		}
		
		
		
		
		
		
		return j.proceed();
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//	// pointcut & advice method
//	@Pointcut("execution(*test*(..))")
//	private void testPC() {
//	}
//	
//	@Around("testPC()")
//	public Object around(ProceedingJoinPoint j) throws Throwable{
//		// 리턴타입 Object, 리턴은 j.proceed(), 첫번쨰 매개변수 ProceedingJoinPoint 타입 변수
//		System.out.println("around aop!!!");
//		// ----------Before-------------
//		return j.proceed();
//	}
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
