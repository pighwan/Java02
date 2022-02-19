package j17_fileIO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

//** 문자스트림
// => 메모장 화일 만들기 & 내용 출력하기

public class Ex05_02FileRW {

	public static void main(String[] args) throws IOException {
		// ** 스트림 생성
		// => 없는 화일로 Test (생성순서 중요)
		Writer out = new FileWriter("memoTest.txt");
		Reader in = new FileReader("memoTest.txt");
		try {
			// ** write
			out.write("** 안녕하세요 ~~ **\n");
			out.write("꿈에 미치면 신화가 된다");
			out.append("."); // 문자추가
			out.flush();
			// ** read
			int c;
			while ( (c=in.read()) != -1 ) {
				System.out.print((char)c);
			}
		} finally {
			// ** 스트림 소멸
			if (in!=null) in.close();
			if (out!=null) out.close();
		}
		System.out.println("\n** FileReader / FileWriter Test2 종료 **");
	} //main
} //class
