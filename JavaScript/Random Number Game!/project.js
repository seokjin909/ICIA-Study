
const ranNum = document.querySelector("#ranNum");
const imageS = document.querySelector("#image_slider");
const numPad = document.querySelectorAll("span");
const btn = document.querySelector("#str");
const ranList = document.querySelectorAll("li");
const result = document.querySelector("#result");
const rel = document.querySelector(".rel")
btn.addEventListener("click",shuffle);

// 시작을 누르면 화면에 1~9사이의 랜덤한 수가 4번 나타났다가 사라진다
let arr1 = [];
function shuffle(){
    for(var x=0; x<4; x++){
        let rn = Math.floor(Math.random()*9)+1;
        ranList[x].innerHTML = rn;
        arr1.push(rn);
        console.log(arr1);
    }
    const pos = document.querySelectorAll("[type='radio']");
    
    let i = 0; // 슬라이더 번호
    // 슬라이더 제어용 스크립트
    function slide(){
        i++;
        if(i < 5){
            pos[i].checked = true;
        } else if (i >= 5){
            return;
        }
    }
    let inter = setInterval(slide, 500);
    btn.style.display = "none";
    rel.style.display = "inline";

}


// 키패드 입력시 화면에 출력하기 위한 함수 
var answ = [];
function padNumber(number) {
    result.innerHTML+=number;
    answ.push(number);
    if(answ.length>=5){
        alert("4자리수를 입력하여 주십시오!")
        answ.pop(number)
        result.innerHTML=answ.join('');
    }
};

// 버튼을 클릭하면 눌린 숫자를 배열에 집어넣어서 결과확인 버튼을 눌렀을 때 길이, 인덱스 값, value 값이 일치한지 비교

function check(){
    for(var i = 0; i<4; i++){
        answ[i];
        arr1[i];
        if(answ[i]===arr1[i]){
            for(var x=i+1; x<4; x++){
                answ[x];
                arr1[x];
                if(answ[x]===arr1[x]){
                    for(var c=x+1; c<4; c++){
                        answ[c];
                        arr1[c];
                     if(answ[c]===arr1[c]){
                        for(var v=c+1; v<4; v++){
                            answ[v];
                            arr1[v];
                            if(answ[v]===arr1[v]){
                                alert("정답입니다!")
                                return;
                            } else {
                                alert("4번째 자리가 오답입니다!")
                                return;
                            }
                        }
                    } else {
                    alert("3번째 자리가 오답입니다!")
                    return;
                    }
                }
            } else {
                alert("2번째 자리가 오답입니다!")
                return;
            }
        } 
    } else {
    alert("1번째 자리가 오답입니다!")
    return;
    }
}
}

// 다시하기 버튼 
function reload(){
    location.reload();
}

// 초기화 버튼
function reset(){
    result.innerHTML = "";
    answ = [];
}

// 뒤로가기 버튼
function min(){
    answ.pop();
    result.innerHTML = answ.join('');
}

