package com.view;

import com.dto.*;

import java.util.List;

public class DataView {

    private InOutClass io = new InOutClass();


    // 서브 메뉴 출력 메소드
    public int showSubMenu() {
        int m = -1;
        io.twoPrint("===============================");
        io.twoPrint("");
        io.twoPrint("\t😀 ENJOY YOUR BOOK LIFE");
        io.twoPrint("");
        io.twoPrint("===============================");
        io.onePrint("1. 도서 추가");
        io.twoPrint("\t\t2. 도서 목록");
        io.onePrint("3. 도서 검색");
        io.twoPrint("\t\t4. 도서 수정");
        io.onePrint("5. 도서 삭제");
        io.twoPrint("\t\t0. 로그아웃");
        m = io.inNum("입력 > ");
        return m;
    }

    // 메인 메뉴 출력 메소드
    public int showMainMenu() {
        int m = -1;
        io.twoPrint("===============================");
        io.twoPrint("");
        io.twoPrint("\t📚 ICIA LIBRARIES");
        io.twoPrint("");
        io.twoPrint("===============================");
        io.twoPrint("1. 로그인");
        io.twoPrint("2. 회원가입");
        io.twoPrint("3. 비밀번호 찾기");
        io.twoPrint("0. 프로그램 종료");
        m = io.inNum("입력 > ");
        return m;
    }

    // 메세지 출력 메소드
    public void printMsg(String msg) {
        io.twoPrint(msg);
    }


    // 회원가입 정보 입력 메소드
    public void inputMData(MemberDto profil) {
        io.twoPrint("===============================");
        io.twoPrint("회원가입을 진행합니다 >>>>");
        io.twoPrint("===============================");
        profil.setM_id(io.inStr("아이디 : "));
        profil.setM_pwd(io.inStr("비밀번호 : "));
        profil.setM_name(io.inStr("이름 : "));
        profil.setM_phone(io.inStr("연락처 : "));
        io.twoPrint("===============================");
    }

    // 회원 로그인 정보 입력 메소드
    public MemberDto searchPwd(MemberDto profil) {
        io.twoPrint("===============================");
        io.twoPrint("로그인을 진행합니다 >>>>");
        io.twoPrint("===============================");
        profil.setM_id(io.inStr("아이디 : "));
        profil.setM_pwd(io.inStr("비밀번호 : "));
        return profil;
    }

    // 회원 비밀번호 찾기 입력 메소드
    public MemberDto findPwd(MemberDto profil) {
        io.twoPrint("===============================");
        io.twoPrint("비밀번호 찾기 >>>>");
        io.twoPrint("===============================");
        profil.setM_id(io.inStr("아이디 : "));
        profil.setM_name(io.inStr("이름 : "));
        return profil;
    }

    // 도서 정보 입력 메소드
    public void inputBdata(BookDto book) {
        io.twoPrint("===============================");
        io.twoPrint("책 정보를 입력바랍니다 >>>>");
        io.twoPrint("===============================");
        book.setB_name(io.inStr("제목 : "));
        book.setB_writer(io.inStr("저자 : "));
        book.setB_genre(io.inStr("장르 : "));
        book.setB_date(io.inStr("출간일(yyyy-mm--dd) : "));
    }

    public void showMemberCard(MemberDto profil) {
        io.twoPrint("===========================================");
        io.twoPrint("==\t 🪪ICIA LIBRARIES MEMBERSHIP CARD  \t ==");
        io.twoPrint("===========================================");
        io.twoPrint("[ID]\t " + profil.getM_id());
        io.twoPrint("[PWD]\t " + profil.getM_pwd());
        io.twoPrint("[NAME]\t " + profil.getM_name());
        io.twoPrint("[PHONE]  " + profil.getM_phone());
        io.twoPrint("===========================================");
    }

    // 도서 정보 목록 출력 메소드
    public void outputBList(List<BookDto> bList) {
        if (bList.size() == 0) {
            io.twoPrint("ERROR 404 데이터가 없습니다");
            io.twoPrint("===============================================================");
            return;
        }
        io.twoPrint("===============================================================");
        io.twoPrint("\t\t\t\t\t🗂BOOK LIST");
        io.twoPrint("===============================================================");
        io.twoPrint("Book.Code\t제목\t\t\t저자\t\t장르\t\t출간일");
        io.twoPrint("===============================================================");
        // 목록 출력(반복)
        for (BookDto b : bList) {
            io.twoPrint("\t" + b.getB_code() + "\t\t" + b.getB_name() + "\t" + b.getB_writer() + "\t" + b.getB_genre() + "\t" + b.getB_date());
            io.twoPrint("---------------------------------------------------------------");
        }
        io.twoPrint("\t\t\t\t\t🗂BOOK LIST");
        io.twoPrint("===============================================================");

    }

    public int searchBCode(String s) {
        int code = 0;
        io.twoPrint("<도서 정보 검색>");
        io.twoPrint("===============================");
        code = io.inNum(s);
        return code;
    }

    public void outputBData(BookDto book) {
        io.twoPrint("<수행 결과>");
        io.twoPrint("===============================================================");
        if (book == null) {
            io.twoPrint("수행 결과 없음");
        } else {
            io.twoPrint("\t" + book.getB_code() + "\t\t" + book.getB_name() + "\t" + book.getB_writer() + "\t" + book.getB_genre() + "\t" + book.getB_date());
            io.twoPrint("===============================================================");
        }
    }


    public void updateBData(BookDto book) {
        io.twoPrint("===============================");
        io.twoPrint("수정할 내용이 없으면 엔터를 누르시오 >>>>");
        io.twoPrint("===============================");
        String ustr = io.inStr("제목 : ");
        if (!ustr.equals("")) {
            book.setB_name(ustr);
        }
        ustr = io.inStr("저자 : ");
        if (!ustr.equals("")) {
            book.setB_writer(ustr);
        }
        ustr = io.inStr("장르 : ");
        if (!ustr.equals("")) {
            book.setB_genre(ustr);
        }
        ustr = io.inStr("출간일(yyyy-mm-dd) : ");
        if (!ustr.equals("")) {
            book.setB_date(ustr);
        }
    }

    public AdminDto getAdminData(AdminDto admin) {
        io.twoPrint("===============================");
        io.twoPrint("관리자 모드로 전환을 진행합니다 >>>>");
        io.twoPrint("===============================");
        admin.setA_no(io.inNum("관리자번호 : "));
        admin.setA_id(io.inStr("아이디 : "));
        admin.setA_pwd(io.inStr("비밀번호 : "));
        return admin;
    }

    public int showMemberMenu() {
        int menu = 0;
        io.twoPrint("===============================");
        io.twoPrint("");
        io.twoPrint("\t👩‍🏫 HOW CAN I HELP YOU?");
        io.twoPrint("");
        io.twoPrint("===============================");
        io.onePrint("1. 도서대출");
        io.twoPrint("\t\t2. 도서반납");
        io.onePrint("3. 예약하기");
        io.twoPrint("\t\t4. 대출목록");
        io.twoPrint("0. 로그아웃");
        menu = io.inNum("입력 > ");

        return menu;
    }

    public String searchBData(String s) {
        String str = null;
        io.twoPrint("<도서 검색> [도서명] 또는 [작가]");
        io.twoPrint("===============================");
        str = io.inStr(s);
        return str;
    }

    public ManagementDto outputSearchList(List<ManagementDto> mList) {
        ManagementDto mData = new ManagementDto();
        io.twoPrint("========================================================================================");
        io.twoPrint("\t\t\t\t\t\t\t🗂CHECK OUT LIST");
        io.twoPrint("========================================================================================");
        io.twoPrint("번호\t| Code\t제목\t\t\t저자\t\t장르\t\t출간일\t\t대출번호\t대출여부");
        io.twoPrint("========================================================================================");
        // 목록 출력(반복)
        for (ManagementDto m : mList) {
            io.twoPrint( (mList.indexOf(m)+1) + "\t| " + m.getB_code() + "\t" + m.getB_name() + "\t" + m.getB_writer() + "\t" + m.getB_genre() + "\t" + m.getB_date() + "\t" + m.getC_serialnum() + "\t" + m.getBook_tf());
            io.twoPrint("---------------------------------------------------------------");
        }
        io.twoPrint("\t\t\t\t\t\t\t🗂CHECK OUT LIST");
        io.twoPrint("========================================================================================");
        int menu = io.inNum("대출할 번호를 입력하세요 : ");
        mData = mList.get(menu-1);
        return mData;
    }
}
