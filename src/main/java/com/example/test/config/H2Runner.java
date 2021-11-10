package com.example.test.config;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class H2Runner implements ApplicationRunner {

	private DataSource dataSource;
	 
	@Autowired
    public H2Runner(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
	@Override
	public void run(ApplicationArguments args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
//            System.out.println(connection.getMetaData().getURL());
//            System.out.println(connection.getMetaData().getUserName());
        	
            // 서버 실행 시 검색어 테이블 생성 -> JPA 사용 시 주석 -> @Entity에서 컴파일 시 테이블 생성/검증
//            String sql = "CREATE TABLE SEARCH_RESULT(keyword VARCHAR(255) NOT NULL, search_count BIGINT, PRIMARY KEY(keyword))";
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.executeUpdate();
//            statement.close();
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
	}

}
