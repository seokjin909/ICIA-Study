package com.service;

import com.dao.DataDao;
import com.dto.*;

import java.sql.Struct;
import java.util.List;

public class DataService {
    private DataDao dDao = new DataDao();


    // 회원가입시 입력받은 데이터를 DB에 삽입하는 메소드
    public String insertMData(MemberDto profil) {
        String msg = null;

        // [DAO]에게 데이터 삽입 요구하기
        int res = dDao.insertMData(profil);
        // [DAO]에서 데이터가 성공적으로 삽입되었으면 반환된 res 값은 데이터에 값에 따라 반환되었을 것이다
        if (res != 0) {
            msg = "회원가입 성공 >>>>";
        } else {
            msg = "회원가입 실패 >>>>";
        }
        return msg;
    }


    public String getPwd(MemberDto profil) {
        // 입력받은 아이디와 비밀번호가 들어있는 [DTO]를 [DAO]에 전달하여 아이디에 해당되는 비밀번호를 가져오도록 한다
        String dbpwd = dDao.getPwd(profil);
        return dbpwd;
    }

    public String comparePwd(MemberDto profil, String dbpwd) {
        String msg = null;
        if (profil.getM_pwd().equals(dbpwd)) {
            msg = "🔍 로그인 성공 >>>>";
        } else {
            msg = "❌ 로그인 실패 >>>>";
        }
        return msg;
    }

    public String getData(MemberDto dbprofil) {
        String dbpwd = dDao.getData(dbprofil);
        return dbpwd;
    }

    public String insertBData(BookDto book) {
        String msg = null;

        // [DAO] 데이터 삽입 처리 요구하기
        int res = dDao.insertBData(book);
        if (res != 0) {
            msg = "입력 성공";
        } else {
            msg = "입력 실패";
        }
        return msg;
    }

    // 도서 목록의 데이터들을 출력하는 메소드
    public List<BookDto> getBList() {
        // [DAO]의 전체 목록을
        List<BookDto> bList = dDao.selectBList();
        // [DAO]에게서 받은 목록을 다시 [controller]에게 전달
        return bList;
    }

    // 해당 도서 코드의 데이터를 가져오는 메소드
    public BookDto getBData(int code) {
        // 받은 도서 코드로 [DAO]에 전달하여 해당 데이터를 가져오도록 한다
        BookDto book = dDao.getBData(code);
        return book;
    }

    public String deleteBData(int code) {
        String msg = null;

        // [DAO]로 입력한 코드를 넘겨서 DB 삭제
        int res = dDao.deleteBData(code);
        if (res > 0) {
            msg = "삭제 성공";
        } else {
            msg = "삭제 실패";
        }
        return msg;
    }

    public String updateBData(BookDto ubook) {
        String msg = null;

        // [DAO]에게 수정 처리 요구하기
        int res = dDao.updateBData(ubook);
        if (res != 0){
            msg = "수정 완료";
        } else {
            msg = "수정 실패";
        }
        return msg;
    }

    public AdminDto getAdminData(AdminDto admin) {
        AdminDto dbAdmin = new AdminDto();
        dbAdmin = dDao.getAdminData(admin);
        return dbAdmin;
    }

    public String compareAdminData(AdminDto admin, AdminDto dbAdmin) {
        String msg = null;
        if (admin.getA_id().equals(dbAdmin.getA_id()) && admin.getA_pwd().equals(dbAdmin.getA_pwd())){
            msg = "🔍 로그인 성공 >>>>\n" + dbAdmin.getA_name() + "님 환영합니다.";
        } else {
            msg = "❌ 로그인 실패 >>>>";
        }
        return msg;
    }

    public List<ManagementDto> getBSearchList(String s) {
        List<ManagementDto> mList = dDao.getSearchBList(s);
        // [DAO]에게서 받은 목록을 다시 [controller]에게 전달
        return mList;
    }

    public String checkout(ManagementDto mData, MemberDto profil) {
        String msg = null;
        // [DAO]에게 수정 처리 요구하기
        int res = dDao.checkout(mData,profil);
        if (res != 0){
            msg = "대출 완료";
        } else {
            msg = "대출 실패";
        }
        return msg;
    }

    public String updateBM(ManagementDto mData, MemberDto profil) {
        String msg = null;

        int res = dDao.updateBM(mData,profil);
        if (res != 0){
            msg = "수정 완료";
        } else {
            msg = "수정 실패";
        }
        return msg;
    }
}
