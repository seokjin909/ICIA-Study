const logInF = document.querySelector("#logInF");
const logInForm = document.querySelector("#logInForm");
const logInForm2 = document.querySelector("#logInForm2");
const logInId = document.querySelector("#serialNum");
const logInIdCheck = document.querySelector("#serialNumCheck")
const result = document.querySelector("#result");
 
const DISPLAY_HIDDEN = "hidden"
const savedUsernumber = localStorage.getItem("usernumber");




    function LogIn(event){
        event.preventDefault();
        const serialNum = logInId.value;
        localStorage.setItem("usernumber",serialNum);
        logInForm.classList.add("none"); 
    }
    
    function LogCheck(){
        event.preventDefault();
        const serialNumCheck = logInIdCheck.value; 
        if(serialNumCheck.value==savedUsernumber){
            logInF.classList.add(DISPLAY_HIDDEN);
            loadingName();
        } else {
            alert("번호가 일치하지 않습니다");
        }
    }

    function loadingName(){
        result.innerHTML = `SERIAL NUMBER 확인되었습니다`;
        result.classList.remove(DISPLAY_HIDDEN);
    }

    if(savedUsernumber==null){
        logInF.classList.remove(DISPLAY_HIDDEN);
        logInForm.addEventListener("submit",LogIn);
    } else {
        loadingName(savedUsernumber);

    }