// 학생 인원 수 입력 > 화면에 인워수 만큼 input 태그 추가 > 점수를 입력 > 계산 (버튼) > 총점, 평균 출력


   // 태그의 이름으로 요소를 가져온다.
   // 같은 태그 요소를 모두 가져와서 배열로 저장한다.
   const nums = document.querySelector("#num");
   const btns = document.getElementsByTagName("button");
   const sp = document.querySelector("#space");
   const res = document.querySelector("#result");
   const res2 = document.querySelector("#result2");
   const gra = document.querySelector("#grade");
   // addEventListener : 자바스크립트로 요소의 이벤트를 처리
   btns[0].addEventListener("click",function(){ // 추가 버튼
      // 자바스크립트로 html 요소 추가 단계 
      // 1. 요소 생성(createElement)
      // 2. 부모 요소에 생성한 요소를 자식 요소로 추가 (appendChild)
      // 3. 요소에 글자 내용을 넣고 싶을 경우 TextNode를 생성하여 추가 (createTextNode)
      if(nums.value==""){
        alert("정수를 입력하시오!")
      } else if (nums.value<0){
        alert("정수를 입력하시오!")
      }
    for(let z = 1; z<=nums.value; z++){
      let ptag = document.createElement("p");
      let tn = document.createTextNode("점수 입력 : ");
      let itag = document.createElement("input");
      itag.type = "number";
      itag.className = "nin"
      ptag.appendChild(tn);
      ptag.appendChild(itag);
      sp.appendChild(ptag);
      sp.classList.remove("hidden")
    }
   });

   btns[1].addEventListener("click",function(){ // 합산 버튼
    if(nums.value==""){
        alert("과목을 먼저 추가하시오!")
        return;
      }
      const inputs = document.querySelectorAll(".nin"); 
      // 입력한 값들이 들어있는 클래스"nin"을 배열의 형식으로 저장해서 변수 inputs로 저장

      // 합산 버튼을 눌렀을 때 값 입력창이 공백이라면 경고 문구가 나오도록 하기!
    for(var i = 0; i<inputs.length; i++){
        let blank = inputs[i].value;
        if(blank==""){
            alert("빈 칸 없이 모두 입력하시오!")
            return;
        } else if(blank<0){
            alert("자연수를 입력하시오!")
            return;
        } else if(blank>100){
            alert("100점보다 낮은 점수를 입력하시오!")
            return;
        }
    }
      // 클래스 이름을 nin으로 가지는 요소를 inputs이라는 변수에 배열의 형식으로 저장한다.
      let sum = 0; // 합산 값 저장
      for(let x of inputs){
         sum += Number(x.value);
      }
      let bal = sum/inputs.length;

      let ptag2 = document.createElement("p");
      let ts = document.createTextNode(`총점 : ${sum}`);
      let ptag3 = document.createElement("p");
      let av = document.createTextNode(`평균 : ${bal}`);
      
      let grade = "";
      if(bal>=90){
          grade = "A학점"
        } else if(bal>=80){
            grade = "B학점"
        } else if(bal>=70){
            grade = "C학점"
        } else if(bal>=60){
            grade = "D학점"
        } else {
            grade = "F학점"
        }

        let ptag4 = document.createElement("p");
        let gr = document.createTextNode(`학점 : ${grade}`)
        
      ptag2.appendChild(ts);
      ptag3.appendChild(av);
      ptag4.appendChild(gr);
      res.appendChild(ptag2);
      res.appendChild(ptag3);
      res.appendChild(ptag4);
      gra.classList.remove("hidden")
      res.classList.remove("hidden")
      res2.classList.remove("hidden")
   })

   btns[2].addEventListener("click",function(){
        window.location.reload() 
   })


