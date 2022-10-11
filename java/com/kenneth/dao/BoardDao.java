package com.kenneth.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kenneth.dto.BoardVo;
import com.kenneth.dto.ProductVo;
import com.kenneth.util.DBManager;

public class BoardDao {
	// 싱글톤
	// 1. 생성자 private : 객체를 외부에서 만들지 못하도록
	// 2. 객체 생성 private static : 자신이 객체를 생성
	// 3. 객체 제공 기능 getInstance() : 자신의 객체(단지 1개)를 사용할 수 있도록 제공 
	private BoardDao() {
		
	}
	
	private static BoardDao instance = new BoardDao();
	
	public static BoardDao getInstance() {
		return instance;
	}
	
	// 게시판 등록
		// 입력값 : 전체 상품 정보
		// 반환값 : 쿼리 수행 결과
		public int insertBoard(BoardVo bVo) {


			int result = -1;
			Connection conn = null;
			// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할때, 매개변수가 많아서 쿼리문 정리 필요
			PreparedStatement pstmt = null;		// 동적 쿼리
			
			
			String sql_insert = "insert into board values(product_seq.nextval, ?, ?, ?, ?, ?)";
			
			try {
				conn = DBManager.getConnection();

				// (3 단계) Statement 객체 생성
				pstmt = conn.prepareStatement(sql_insert);
				
//				pstmt.setInt(1, pVo.getCode());
				pstmt.setString(1, bVo.getName());			
				pstmt.setString(2, bVo.getTitle());
				pstmt.setString(3, bVo.getPictureurl());
				pstmt.setString(4, bVo.getDescription());	// 문자형
				pstmt.setDate(5, bVo.getReg_date());		// 날짜형

				// (4 단계) SQL문 실행 및 결과 처리
				// executeUpdate : 삽입(insert/update/delete)
				result = pstmt.executeUpdate();			// 쿼리 수행
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt); 
			}
			return result;
		}
	
		// 전체 게시판 조회
		public List<BoardVo> selectAllBoard() {
			String sql = "select * from board order by code desc";
			
			List<BoardVo> list = new ArrayList<BoardVo>();	// List 컬렉션 객체 생성
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = DBManager.getConnection();
				
				// (3 단계) Statement 객체 생성
				pstmt = conn.prepareStatement(sql);
			

				// (4 단계) SQL문 실행 및 결과 처리 => executeQuery : 조회(select)
				rs = pstmt.executeQuery();
				
				// rs.next() : 다음 행(row)을 확인
				// rs.getString("컬럼명")
				while(rs.next()){
					BoardVo bVo = new BoardVo();				
					// 디비로부터 회원 정보 획득
					bVo.setCode(rs.getInt("code"));		// 컬럼명 name 인 정보를 가져옴
					bVo.setTitle(rs.getString("title"));
					bVo.setName(rs.getString("name"));	// DB에서 가져온 정보를 pVo 객체에 저장
					bVo.setPictureurl(rs.getString("pictureurl"));
					bVo.setDescription(rs.getString("description"));
					bVo.setReg_date(rs.getDate("reg_date"));
					
					list.add(bVo);	// list 객체에 데이터 추가
					
				}
				
			} catch(Exception e) {
				e.printStackTrace();
				
			} finally {
				DBManager.close(conn, pstmt, rs); 
			}
			return list;
		}
		
//	 	단일 게시판 조회 (code) => 단일 상품 정보 반환
		public BoardVo selectBoardByCode(String code) {
			String sql = "select * from board where code=?";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			BoardVo bVo = null;
			
			try {
				conn = DBManager.getConnection();
				
				// (3 단계) Statement 객체 생성
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, code);
				
				// (4 단계) SQL문 실행 및 결과 처리 => executeQuery : 조회(select)
				rs = pstmt.executeQuery();
				// rs.next() : 다음 행(row)을 확인, rs.getString("컬럼명")
				while(rs.next()){
					// rs.getInt("컬럼명")
					bVo = new BoardVo();				
					// 디비로부터 회원 정보 획득
					bVo.setCode(rs.getInt("code"));		// 컬럼명 name 인 정보를 가져옴
					bVo.setName(rs.getString("name"));	// DB에서 가져온 정보를 pVo 객체에 저장
					bVo.setTitle(rs.getString("title"));
					bVo.setPictureurl(rs.getString("pictureurl"));
					bVo.setDescription(rs.getString("description"));
					bVo.setReg_date(rs.getDate("reg_date"));
				}
				
			} catch(Exception e) {
				e.printStackTrace();
				
			} finally {
				DBManager.close(conn, pstmt, rs); 
			}
			return bVo;
		}
		
		// 게시판 수정 (수정정보) => 디비에 정상 반영 여부 반환
		public int updateBoard (BoardVo bVo) {
			int result = -1;
			Connection conn = null;
			// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할때, 매개변수가 많아서 쿼리문 정리 필요
			PreparedStatement pstmt = null;		// 동적 쿼리
			
			
			String sql = "update Board set name=?, title=?, pictureurl=?, description=?, reg_date=? where code=? ";
			
			try {
				conn = DBManager.getConnection();
				
				// (3 단계) Statement 객체 생성
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bVo.getName());
				pstmt.setString(2, bVo.getTitle());
				pstmt.setString(3, bVo.getPictureurl());
				pstmt.setString(4, bVo.getDescription());
				pstmt.setDate(5, bVo.getReg_date());
				pstmt.setInt(6, bVo.getCode());
				
				// (4 단계) SQL문 실행 및 결과 처리
				// executeUpdate : 삽입(insert/update/delete)
				result = pstmt.executeUpdate();			// 쿼리 수행
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt); 
			}
			return result;
		}
		
		// 게시판 삭제
		public void deleteBoard(String code) {
			int result = -1;
			Connection conn = null;
			// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할때, 매개변수가 많아서 쿼리문 정리 필요
			PreparedStatement pstmt = null;		// 동적 쿼리
			
			
			String sql = "delete from board where code=?";
			
			try {
				conn = DBManager.getConnection();

				// (3 단계) Statement 객체 생성
				pstmt = conn.prepareStatement(sql);
				
//				pstmt.setInt(1, pVo.getCode());
				pstmt.setString(1, code);			
				
				// (4 단계) SQL문 실행 및 결과 처리
				// executeUpdate : 삽입(insert/update/delete)
				result = pstmt.executeUpdate();			// 쿼리 수행
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt); 
			}
			
		}
		
		// 게시판 검색
		// 입력값: column: 검색 대상(분야), keyword: 검색어
		// 반환값: 검색 결과 리스트
		public List<BoardVo> searchBoard(String column, String keyword) {
			String sql = "select * from board where "+ column + " like ? order by code desc";
			
			List<BoardVo> list = new ArrayList<BoardVo>();
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = DBManager.getConnection();
				
				// (3 단계) Statement 객체 생성
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + keyword + "%");
			

				// (4 단계) SQL문 실행 및 결과 처리 => executeQuery : 조회(select)
				rs = pstmt.executeQuery();
				
				// rs.next() : 다음 행(row)을 확인
				// rs.getString("컬럼명")
				while(rs.next()){
					
					BoardVo bVo = new BoardVo();				
					// 디비로부터 회원 정보 획득
					bVo.setCode(rs.getInt("code"));		// 컬럼명 name 인 정보를 가져옴
					bVo.setTitle(rs.getString("title"));
					bVo.setName(rs.getString("name"));	// DB에서 가져온 정보를 pVo 객체에 저장
					bVo.setPictureurl(rs.getString("pictureurl"));
					bVo.setDescription(rs.getString("description"));
					bVo.setReg_date(rs.getDate("reg_date"));
					
					list.add(bVo);	// list 객체에 데이터 추가
					
				}
				
			} catch(Exception e) {
				e.printStackTrace();
				
			} finally {
				DBManager.close(conn, pstmt, rs); 
			}
			
			return list;
		}
		
}