package com.kenneth.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kenneth.dto.MemberVo;
import com.kenneth.dto.ProductVo;
import com.kenneth.util.DBManager;


// 데이터베이스 접근
public class MemberDao {
	// 싱글톤 생성 및 사용
	// 필드
	private static MemberDao instance = new MemberDao();
	
	// 생성자
	private MemberDao() {
		
	}
	
	// 메소드
	public static MemberDao getInstance() {
		return instance;
		
	}
	
	// 로그인(사용자 인증)시 사용
	// 입력값 : 로그인 페이지에서 입력받은 사용자 아이디와 암호
	// 반환값 : result (1: 암호일치), (0: 암호불일치) (-1: 사용자아이디 없음)
	public int checkUser(String userId, String pwd) {
		int result = -1;
//		Statement stmt = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select pwd from member where userid=?";
		
		try {
			conn = DBManager.getConnection();
			
			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			

			// (4 단계) SQL문 실행 및 결과 처리 => executeQuery : 조회(select)
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				System.out.println(rs.getString("pwd"));
		
				// 아이디/ 암호 비교 후 페이지 이동
				if(rs.getString("pwd") != null &&
						rs.getString("pwd").equals(pwd)) {
					result = 1;		// 암호 일치
				} else {
					result = 0;		// 암호 불일치
				}	
			} else {
					result = -1;	// 디비에 userid 없음
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			DBManager.close(conn, pstmt, rs); 
		}
		return result;
	}

	// 회원 가입 : DB에 회원 정보를 삽입.
//	public int insertMember(String name, String id, String pwd, String email, String phone, int admin) {
	public int insertMember(MemberVo mVo) {	

		int result = -1;
		Connection conn = null;
//		Statement stmt = null;				// 정적 쿼리
		// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할때, 매개변수가 많아서 쿼리문 정리 필요
		PreparedStatement pstmt = null;		// 동적 쿼리
		
		
		String sql_insert = "insert into member values(user_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		
		try {
			conn = DBManager.getConnection();

			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql_insert);
			
			pstmt.setString(1, mVo.getName());
			pstmt.setString(2, mVo.getUserid());
			pstmt.setString(3, mVo.getPwd());
			pstmt.setString(4, mVo.getEmail());
			pstmt.setString(5, mVo.getPhone());
			pstmt.setString(6, mVo.getNickname());
			pstmt.setString(7, mVo.getIntroduce());
			pstmt.setString(8, mVo.getUserlatlng());
			
			// (4 단계) SQL문 실행 및 결과 처리
			result = pstmt.executeUpdate();			// 쿼리 수행
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt); 
		}
		return result;
	}

	// 회원 정보 가져오기 : select
	// 입력값 : 사용자id(userId)
	// 반환값 : 해당 회원 정보
	public MemberVo getMember(String userId) {
		int result = -1;
		String sql = "select * from member where userid=?";
		MemberVo mVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			
			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			

			// (4 단계) SQL문 실행 및 결과 처리 => executeQuery : 조회(select)
			rs = pstmt.executeQuery();
			
				if(rs.next()){
				// 디비로부터 회원 정보 획득
				mVo = new MemberVo();
				
				mVo.setCode(rs.getInt("code"));
				mVo.setName(rs.getString("name"));					// DB에서 가져온 정보를 저장
				mVo.setUserid(rs.getString("userid"));
				mVo.setPwd(rs.getString("pwd"));
				mVo.setEmail(rs.getString("email"));
				mVo.setPhone(rs.getString("phone"));
				mVo.setNickname(rs.getString("nickname"));
				mVo.setIntroduce(rs.getString("introduce"));
				mVo.setUserlatlng(rs.getString("userlatlng"));
				
			} else {
					result = -1;	// 디비에 userid 없음
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			DBManager.close(conn, pstmt, rs); 
		}
		return mVo;
	
	}
	
	// 회원 정보 업데이트 : update
	// 입력값 : 회원 테이블 정보
	// 반환값 : 성공여부
	public int updateMember(MemberVo mVo) {
		int result = -1;
		String sql = "update member set pwd=?, email=?, phone=?, nickname=? , introduce=?, userlatlng=? where userid=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			
			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mVo.getPwd());
			pstmt.setString(2, mVo.getEmail());
			pstmt.setString(3, mVo.getPhone());
			pstmt.setString(4, mVo.getNickname());
			pstmt.setString(5, mVo.getIntroduce());
			pstmt.setString(6, mVo.getUserlatlng());
			pstmt.setString(5, mVo.getUserid());
			

			// (4 단계) SQL문 실행 및 결과 처리 => executeUpdate : 수정(update)
			result = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			DBManager.close(conn, pstmt); 
		}
		return result;
	}
	
	// 아이디를 확인
	// 입력값 : 중복 체크하려는 userid
	// 반환값 : 체크한 id가 DB에 존재 여부(1), 존재안함(-1)
	public int confirmID(String userid) {
		int result = -1;
		String sql = "select userid from member where userid=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			

			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			

			// (4 단계) SQL문 실행 및 결과 처리 => executeQuery : 조회(select)
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				result = 1;			// 디비에 userid 있음
				
			} else {
					result = -1;	// 디비에 userid 없음
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			DBManager.close(conn, pstmt, rs); 
		}
		return result;
	}
	

	//특정 회원 검색
	public MemberVo getDetailMember(String name) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM boardMember WHERE name = ?";
			MemberVo dto = null;
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, name);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					MemberVo mVo = new MemberVo();
					mVo.setUserid(rs.getString("userid"));
					mVo.setPwd(rs.getString("pwd"));
					mVo.setName(rs.getString("name"));
					mVo.setEmail(rs.getString("email"));
					mVo.setPhone(rs.getString("phone"));
					mVo.setNickname(rs.getString("nickname"));
					mVo.setIntroduce(rs.getString("introduce"));
					mVo.setUserlatlng(rs.getString("userlatlng"));
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("getDetailmember() Exception!!!");
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			return dto;
		} //getDetailMember()
	
		
//========================멤버 리스트==============================
	public List<MemberVo> selectAllMember() {
			String sql = "select * from member";
			
			
			List<MemberVo> list = new ArrayList<MemberVo>();
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = DBManager.getConnection();
				
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					MemberVo mVo = new MemberVo();	
					// 디비로부터 회원 정보 획득
					mVo.setCode(rs.getInt("code"));
					mVo.setUserid(rs.getString("userid"));		// 컬럼명 name 인 정보를 가져옴
					mVo.setPwd(rs.getString("pwd"));			// DB에서 가져온 정보를 mVo 객체에 저장
					mVo.setName(rs.getString("name"));
					mVo.setEmail(rs.getString("email"));
					mVo.setPhone(rs.getString("phone"));
					mVo.setNickname(rs.getString("nickname"));
					mVo.setIntroduce(rs.getString("introduce"));
					mVo.setUserlatlng(rs.getString("userlatlng"));
					
					list.add(mVo);	// list 객체에 데이터 추가
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt, rs);
			}
			return list;
		}
//========================멤버 삭제===============================
	public void deleteMember(String userid) {
			int result = -1;
			Connection conn = null;
			// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할때, 매개변수가 많아서 쿼리문 정리 필요
			PreparedStatement pstmt = null;		// 동적 쿼리
			
			
			String sql = "delete from member where userid=?";
			
			try {
				conn = DBManager.getConnection();

				// (3 단계) Statement 객체 생성
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, userid);			
				
				// (4 단계) SQL문 실행 및 결과 처리
				result = pstmt.executeUpdate();			// 쿼리 수행
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt); 
			}
		}
		
//========================단일 멤버 조회===========================
	public MemberVo selectMemberByName(String userid) {

		String sql = "select * from member where userid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVo mVo = null;
			
		try {
			conn = DBManager.getConnection();
			
			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			// (4 단계) SQL문 실행 및 결과 처리 => executeQuery : 조회(select)
			rs = pstmt.executeQuery();
			// rs.next() : 다음 행(row)을 확인, rs.getString("컬럼명")
			while(rs.next()){
				// rs.getInt("컬럼명")
				mVo = new MemberVo();				
				// 디비로부터 회원 정보 획득
				mVo.setCode(rs.getInt("code"));
				mVo.setUserid(rs.getString("userid"));		// 컬럼명 userid 인 정보를 가져옴
				mVo.setName(rs.getString("name"));	// DB에서 가져온 정보를 pVo 객체에 저장
				mVo.setPwd(rs.getString("pwd"));
				mVo.setEmail(rs.getString("email"));
				mVo.setPhone(rs.getString("phone"));
				mVo.setNickname(rs.getString("nickname"));
				mVo.setIntroduce(rs.getString("introduce"));
				mVo.setUserlatlng(rs.getString("userlatlng"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			DBManager.close(conn, pstmt, rs); 
		}
		return mVo;
	}
	
//========================특정 멤버 검색===========================
	public List<MemberVo> searchMember(String userid, String name) {

		String sql = "select * from member where " + userid + " like ? ";
		
		List<MemberVo> list = new ArrayList<MemberVo>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			
			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
		

			// (4 단계) SQL문 실행 및 결과 처리 => executeQuery : 조회(select)
			rs = pstmt.executeQuery();
			
			// rs.next() : 다음 행(row)을 확인
			// rs.getString("컬럼명")
			while(rs.next()){
				
				MemberVo mVo = new MemberVo();
				
				mVo.setCode(rs.getInt("code"));
				mVo.setUserid(rs.getString("userid"));		// 컬럼명 userid 인 정보를 가져옴
				mVo.setName(rs.getString("name"));	// DB에서 가져온 정보를 pVo 객체에 저장
				mVo.setPwd(rs.getString("pwd"));
				mVo.setEmail(rs.getString("email"));
				mVo.setPhone(rs.getString("phone"));
				mVo.setNickname(rs.getString("nickname"));
				mVo.setIntroduce(rs.getString("introduce"));
				mVo.setUserlatlng(rs.getString("userlatlng"));
				
				list.add(mVo);	// list 객체에 데이터 추가
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			DBManager.close(conn, pstmt, rs); 
		}
		
		return list;
	}
	
//========================여러 멤버 삭제===========================
	public int multiDelete(String[] chk) {
		int result = 0;
		Connection conn = null;
		// 동일한 쿼리문을 특정 값만 바꿔서 여러번 실행해야 할때, 매개변수가 많아서 쿼리문 정리 필요
		PreparedStatement pstmt = null;		// 동적 쿼리
		
		int[] cnt = null;
		
		String sql = "delete from member where multi=?";
		
		try {
			conn = DBManager.getConnection();

			// (3 단계) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			for(int i=0; i<cnt.length; i++) {
				pstmt.setString(1, chk[i]);
				
				pstmt.addBatch();	// 대용량 데이터 한번에 처리
			}
			
//			pstmt.setInt(1, pVo.getCode());
//			pstmt.setString(1, multi);			
			
			for(int i=0; i<cnt.length; i++) {
				if(cnt[i]== -2) {
					result++;
				}
			}
			
			if(chk.length==result) {
				conn.commit();
			} else {
				conn.rollback();
			}
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


	
}		
		
		
		
		










