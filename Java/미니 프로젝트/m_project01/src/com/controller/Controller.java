package com.controller;

import com.dto.*;
import com.service.DataService;
import com.view.DataView;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    // 컨트롤러가 사용할 객체들
    private DataView dView = new DataView(); // 화면 입출력
    private DataService dServ = new DataService(); // 기능 처리

    // 전체 프로그램 제어 메소드
    public void run() {
        int menu = -1;

        // 메뉴 반복 출력
        while (true) {
            menu = dView.showMainMenu();

            if (menu == 0) {
                dView.printMsg("프로그램 종료");
                break;
            }

            switch (menu) {
                case 1:
                    logIn();
                    break;
                case 2:
                    signUp();
                    break;
                case 3:
                    searchPwd();
                    break;
                case 4:
                    admin();
                    break;
                default:
                    dView.printMsg("0~3 사이 숫자 입력!");
                    break;
            }
        } // while end


    } // run end


    // 로그인 메소드
    private void logIn() {
        MemberDto profil = new MemberDto();
        // 로그인하기 위해 [view]에서 아이디와 비밀번호를 입력하고, 입력한 아이디와 비밀번호를 가져온다
        profil = dView.searchPwd(profil);
        // 입력한 아이디로 DB에서 아이디가 일치하는 데이터에서 해당되는 비밀번호를 가져온다
        String dbpwd = dServ.getPwd(profil);
        // 가져온 비밀번호를 다시 [view]로 보내서 비교하는 과정을 거친다
        String msg = dServ.comparePwd(profil, dbpwd);
        dView.printMsg(msg);
        if (dbpwd != null) {
            memberRun(profil);
        }
    }

    // 회원가입 메소드
    private void signUp() {
        // 키보드로 데이터 받기 [view]를 통해서 데이터 입력받기
        // 입력받은 데이터를 담을 공간 만들기
        MemberDto profil = new MemberDto();
        // 공간을 [view]에 넘겨서 입력한 데이터 받아오기
        dView.inputMData(profil);
        // 데이터가 들어있는 공간을 [service]로 전달하기
        String msg = dServ.insertMData(profil);
        // [service]에서 데이터 입력 성공여부에 따라 설정한 메세지가 출력되게 [view]에 전달
        if (msg != null) {
            dView.printMsg(msg);
        }
        if (msg.equals("회원가입 성공 >>>>")) dView.showMemberCard(profil);
    }

    // 비밀번호 찾기 메소드
    private void searchPwd() {
        MemberDto profil = new MemberDto();
        MemberDto dbprofil = new MemberDto();
        // 비밀번호를 변경하기 위해 [view]에서 아이디와 이름을 입력하고 입력한 아이디와 이름을 가져온다
        profil = dView.findPwd(profil); // 입력한 데이터
        String dbpwd = dServ.getData(profil); // 입력한 아이디와 이름으로 DB에서 조회한 비밀번호 (입력한 아이디와 이름이 DB에 없으면 null 이 출력
        if (dbpwd != null) {
            dView.printMsg("검색결과 : " + dbpwd);
        } else {
            dView.printMsg("검색결과가 없습니다 >>>>");
        }
    }

    // 관리자 계정 로그인 메소드
    private void admin() {
        AdminDto admin = new AdminDto();
        // 로그인하기 위해 [view]에서 관리자번호와 아이디, 비밀번호를 입력하고, 입력한 관리자 번호와 일치하는 아이디와 비밀번호를 가져온다
        admin = dView.getAdminData(admin);
        // 입력한 아이디로 DB에서 관리자번호가 일치하는 데이터에서 해당되는 아이디와 비밀번호를 가져온다
        AdminDto dbAdmin = dServ.getAdminData(admin);
        String msg = dServ.compareAdminData(admin, dbAdmin);
        dView.printMsg(msg);
        if (!msg.equals("❌ 로그인 실패 >>>>")) {
            subRun();
        }
    }


    // 관리자 모드 실행 메소드
    private void subRun() {
        int menu = -1;

        while (true) {
            menu = dView.showSubMenu();
            if (menu == 0) {
                dView.printMsg("로그아웃");
                break;
            }

            switch (menu) {
                case 1:
                    insertBData();
                    break;
                case 2:
                    outputBData();
                    break;
                case 3:
                    searchBData();
                    break;
                case 4:
                    updateBData();
                    break;
                case 5:
                    deleteBData();
                    break;
                default:
                    dView.printMsg("0~5 사이 숫자 입력!");
                    break;
            }
        }
    }


    // 도서 정보 입력 메소드
    private void insertBData() {
        BookDto book = new BookDto();
        dView.inputBdata(book);
        String msg = dServ.insertBData(book);
        dView.printMsg(msg);
    }

    // 도서 목록 출력 메소드
    private void outputBData() {
        // service 전체 데이터를 가져오는 메소드, 받는 데이터는 목록 형태 ArrayList 활용
        List<BookDto> bList = dServ.getBList();
        // 받아온 데이터 목록을 출력해야하므로 -> [view]로 전달
        dView.outputBList(bList);
    }

    // 도서 정보 검색 메소드
    private void searchBData() {
        // 검색 : 기본키(b_code)를 통해서 찾아오기
        int code = dView.searchBCode("검색할 코드 : ");
        // 검색 값 입력 -> [service]로 전달하여 입력한 코드에 해당하는 데이터를 검색
        BookDto book = dServ.getBData(code);
        // 검색 결과 출력 -> [view]
        dView.outputBData(book);
    }

    // 도서 정보 수정 메소드
    private void updateBData() {
        int code = dView.searchBCode("수정할 코드 : ");
        BookDto book = dServ.getBData(code);
        dView.outputBData(book);
        BookDto ubook = dServ.getBData(code);
        dView.updateBData(ubook);
        dView.outputBData(ubook);
        String msg = dServ.updateBData(ubook);
        dView.printMsg(msg);
    }

    // 도서 정보 삭제 메소드
    private void deleteBData() {
        // 검색 : 기본키(b_code)를 통해서 찾아오기
        int code = dView.searchBCode("삭제할 코드 : ");
        // 검색 값 입력 -> [service]로 전달하여 입력한 코드에 해당하는 데이터를 검색
        BookDto book = dServ.getBData(code);
        dView.outputBData(book);
        if (book != null) {
            String msg = dServ.deleteBData(code);
            dView.printMsg(msg);
        }
    }


    // 회원 페이지
    private void memberRun(MemberDto profil) {
        int menu = -1;

        while (true) {
            menu = dView.showMemberMenu();
            if (menu == 0) {
                dView.printMsg("로그아웃");
                break;
            }

            switch (menu) {
                case 1:
                    checkoutBook(profil);
                    break;
                case 2:
                    // returnBook();
                    break;
                case 3:
                    // reservationBook();
                    break;
                case 4:
                    // checkoutBookList();
                    break;
                case 5:

                default:
                    dView.printMsg("0~4 사이 숫자 입력!");
                    break;
            }
        }
    }

    // 대출 메소드
    private void checkoutBook(MemberDto profil) {
        /*
        1. 대출하고 싶은 도서명이나 작가명을 입력하면 해당 값을 가지고 있는 데이터들을 리스트 형식으로 받아와서 출력 / 없으면 null 리턴
        검색 : [b_name] or [b_writer]
        2. JOIN 기능 사용해서 책 정보와 대출 정보를 같이 출력
        ex) ===============================================================
            번호 | Book.Code	 제목   저자   대출여부   대출일   반납일   대출여부
            ===============================================================
        3. 현재 로그인한 아이디로 대출한 책의 권수를 표시해주고 5권 이하라면 "대출가능" 표시, 5권 초과라면 "대출한도 초과" 표시
        4. 대출여부가 "Y" 라면 예약을 진행할 수 있게 하고, "N" 라면 해당 책을 대출할 것인지 확인받기
        */

        String searchIndex = dView.searchBData("검색 : ");
        List<ManagementDto> mList = dServ.getBSearchList(searchIndex);
        ManagementDto mData = dView.outputSearchList(mList); // 리스트중에서 대출하고 싶은 책의 [DTO]를 반환받기
        // 선택한 도서를 대출하는 메소드
        String msg = dServ.checkout(mData, profil);
        // 도서관리 테이블에 대여상태 수정하기
        String msg2 = dServ.updateBM(mData, profil);
    }
} // class end


