package test.spring.csv;

import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import au.com.bytecode.opencsv.CSVWriter;
// 실제로 CSV 파일로 저장하고 읽어들이는 클래스 작성
// 즉, CSV 파일관련 비지니스로직 클래스 만들기

public class CsvServiceBean {

		// #1. 파일 쓰기 메서드 ( 리스트 타입의 데이터, 파일경로 받기)
		public void writeCSV(List<String[]> data, String filename) {	// 이 메서드를 호출하는 곳에서
			// OpenCSV 라이브러리에 있는 CSWriter 클래스 객체 생성
			CSVWriter cw = null;
			
			try{
				// 새파일명으로 FileWriter객체, 구분자, 따옴표문자(quotechar) 지정
				cw = new CSVWriter(new FileWriter(filename),',','"');
				
				// 한번에 전체 데이터 작성 해주는 방법
				cw.writeAll(data);
				cw.flush();
				cw.close();
				
//				// 리스트 요소 하나씩 꺼내서 일일히 작성 해주는 방법
//				Iterator<String[]> it = data.iterator();
//				try {
//					while(it.hasNext()){
//						String[] s = (String[])it.next();
//						cw.writeNext(s);
//					}
//				}finally {
//					cw.close();
//				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
}
