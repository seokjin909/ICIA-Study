package com.dao;

import com.dto.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataDao {
    private String drv = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/test";
    private String user = "m_project";
    private String pwd = "Wkd9smsaht!";

    // DB 관련 객체
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    // 드라이버 로드(생성자)
    public DataDao() {
        try {
            Class.forName(drv);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
        }
    }


    public int insertMData(MemberDto profil) {
        int result = 0; // [DAO]에서는 데이터 삽입 후 성공여부를 [service]에게 맡김 그러기 위해 result 값을 설정
        String query = "INSERT INTO memberdata VALUES (?,?,?,?)";

        try {
            conn = DriverManager.getConnection(url, user, pwd);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, profil.getM_id());
            pstmt.setString(2, profil.getM_pwd());
            pstmt.setString(3, profil.getM_name());
            pstmt.setString(4, profil.getM_phone());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            result = 0; // 예외처리가 일어나면 데이터 삽입 실패로 간주하고 반환되는 result 값을 0으로 정해줘서 [service]에 전달한다
        } finally {
            close();
        }
        return result;
    }

    // 입력한 아이디로 해당되는 비밀번호를 가져오는 메소드
    public String getPwd(MemberDto profil) {
        String dbpwd = null;
        String query = "SELECT m_pwd FROM memberdata WHERE m_id = ?";
        try {
            conn = DriverManager.getConnection(url, user, pwd);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, profil.getM_id()); // [id]는 첫 번째 물음표에 넣을 값
            rs = pstmt.executeQuery();
            if (rs.next()) {
                dbpwd = rs.getString(1);
            }
        } catch (SQLException e) {
            dbpwd = null;
        } finally {
            close();
        }
        return dbpwd;
    }

    public String getData(MemberDto dbprofil) {
        String dbpwd = null;
        String query = "SELECT m_pwd FROM memberdata WHERE m_id = ? AND m_name = ?";

        try {
            conn = DriverManager.getConnection(url, user, pwd);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, dbprofil.getM_id());
            pstmt.setString(2, dbprofil.getM_name());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                dbpwd = rs.getString(1);
            }
        } catch (SQLException e) {
            dbpwd = null;
        } finally {
            close();
        }
        return dbpwd;
    }

    public int insertBData(BookDto book) {
        int result = 0;
        String query = "INSERT INTO bookdata VALUES (default,?,?,?,?)";

        try {
            conn = DriverManager.getConnection(url, user, pwd);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, book.getB_name());
            pstmt.setString(2, book.getB_writer());
            pstmt.setString(3, book.getB_genre());
            pstmt.setDate(4, Date.valueOf(book.getB_date()));
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            result = 0;
        } finally {
            close();
        }
        return result;
    }

    public List<BookDto> selectBList() {
        // DB에서 테이블의 내용을 모두 가져와서 List(목록)에 저장
        // 데이터를 담을 공간을 생성
        List<BookDto> bList = new ArrayList<>();

        // 전체 목록을 가져오는 쿼리 작성
        String query = "SELECT * FROM bookdata";
        try {
            conn = DriverManager.getConnection(url, user, pwd);
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                if (bList == null) { // 목록이 없을 경우 목록을 생성
                    bList = new ArrayList<>();
                }
                // 행 단위로 처리 -> 한 행은 하나의 [DTO]로 처리하게끔
                BookDto book = new BookDto();
                book.setB_code(rs.getInt(1));
                book.setB_name(rs.getString(2));
                book.setB_writer(rs.getString(3));
                book.setB_genre(rs.getString(4));
                book.setB_date(rs.getString(5));
                bList.add(book);
            }
        } catch (SQLException e) {
            bList = null;
        } finally {
            close();
        }
        return bList;
    }

    // 입력받은 도서 코드로 해당되는 데이터의 값들을 불러오는 메소드
    public BookDto getBData(int code) {
        BookDto book = null;
        String query = "SELECT * FROM bookdata WHERE b_code = ?";

        try {
            conn = DriverManager.getConnection(url, user, pwd);
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, code);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                book = new BookDto();
                book.setB_code(rs.getInt(1));
                book.setB_name(rs.getString(2));
                book.setB_writer(rs.getString(3));
                book.setB_genre(rs.getString(4));
                book.setB_date(rs.getString(5));
            }
        } catch (SQLException e) {
            book = null;
        } finally {
            close();
        }
        return book;
    }

    public int deleteBData(int code) {
        int result = 0;
        String query = "DELETE FROM bookdata WHERE b_code = ?";

        try {
            conn = DriverManager.getConnection(url, user, pwd);
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, code);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            result = 0;
        } finally {
            close();
        }
        return result;
    }

    public int updateBData(BookDto ubook) {
        int result = 0;
        String query = "UPDATE bookdata SET b_name = ?, b_writer = ?, b_genre = ?, b_date = ? WHERE b_code = ?";

        try {
            conn = DriverManager.getConnection(url, user, pwd);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, ubook.getB_name());
            pstmt.setString(2, ubook.getB_writer());
            pstmt.setString(3, ubook.getB_genre());
            pstmt.setDate(4, Date.valueOf(ubook.getB_date()));
            pstmt.setInt(5, ubook.getB_code());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            result = 0;
        } finally {
            close();
        }
        return result;
    }

    public AdminDto getAdminData(AdminDto admin) {
        AdminDto dbAdmin = null;
        String query = "SELECT a_id, a_pwd, a_name FROM admindata WHERE a_no = ? AND a_id = ? AND a_pwd = ?";

        try {
            conn = DriverManager.getConnection(url, user, pwd);
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, admin.getA_no());
            pstmt.setString(2, admin.getA_id());
            pstmt.setString(3, admin.getA_pwd());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                dbAdmin = new AdminDto();
                dbAdmin.setA_id(rs.getString(1));
                dbAdmin.setA_pwd(rs.getString(2));
                dbAdmin.setA_name(rs.getString(3));
            }
        } catch (SQLException e) {
            dbAdmin = null;
        } finally {
            close();
        }
        return dbAdmin;
    }

    public List<ManagementDto> getSearchBList(String s) {
        List<ManagementDto> mList = new ArrayList<>();
        String query = "SELECT B.b_code, b_name, b_writer, b_genre, b_date, c_serialnum, book_tf FROM b_management BM \n" +
                "JOIN bookdata B ON BM.b_code = B.b_code\n" +
                "WHERE b_writer = ? OR b_name LIKE ?";

        try {
            conn = DriverManager.getConnection(url, user, pwd);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, s);
            s = "%" + s + "%";
            pstmt.setString(2, s);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                if (mList == null) { // 목록이 없을 경우 목록을 생성
                    mList = new ArrayList<>();
                }
                // 행 단위로 처리 -> 한 행은 하나의 [DTO]로 처리하게끔
                ManagementDto mng = new ManagementDto();
                mng.setB_code(rs.getInt(1));
                mng.setB_name(rs.getString(2));
                mng.setB_writer(rs.getString(3));
                mng.setB_genre(rs.getString(4));
                mng.setB_date(rs.getString(5));
                mng.setC_serialnum(rs.getString(6));
                mng.setBook_tf(rs.getString(7));
                mList.add(mng);
            }
        } catch (SQLException e) {
            mList = null;
        }
        return mList;
    }

    public int checkout(ManagementDto mData, MemberDto profil) {
        int result = 0;
        String query = "INSERT INTO checkout VALUES (DEFAULT,?,?,DEFAULT,DEFAULT)";

        try {
            conn = DriverManager.getConnection(url, user, pwd);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, profil.getM_id());
            pstmt.setInt(2, mData.getB_code());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            result = 0;
        } finally {
            close();
        }
        return result;
    }

    public int updateBM(ManagementDto mData, MemberDto profil) {
        int result = 0;
        String query = "UPDATE b_management SET m_id = ?, book_tf = ?, c_serialnum = ? WHERE b_code = ?";

        try {
            conn = DriverManager.getConnection(url, user, pwd);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, profil.getM_id());
            pstmt.setString(2,"Y");
            pstmt.setString(3, mData.getC_serialnum());
            pstmt.setInt(4, mData.getB_code());
            rs = pstmt.executeQuery();
            if (rs.next()){
                mData.setC_serialnum(rs.getString(3));
            }
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            result = 0;
        } finally {
            close();
        }
        return result;
    }
}
