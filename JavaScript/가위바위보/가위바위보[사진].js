
const rsp = ["가위","바위","보자기"];
const rspImg = ["가위.gif","주먹.gif","보자기.gif"]
const res = document.querySelector("#res");
const res2 = document.querySelector("#res2");
const btn = document.querySelectorAll("button");
const image = document.createElement("img");
const main = document.getElementById("main");

let com = setInterval(function(){
    const ranImg = rspImg[Math.floor(Math.random()*3)];
    image.src = `images/${ranImg}`;
    main.appendChild(image);
},100);


btn[0].addEventListener("click",function(){
    clearInterval(com);
    let ran = rsp[Math.floor(Math.random()*3)];
    res.innerHTML = (`상대편 : ${ran} <br>본인 : 가위`);
    if(ran=="가위"){
        res2.innerHTML = "비겼습니다!"
    } else if(ran=="바위") {
        res2.innerHTML = "졌습니다!"
    } else {
        res2.innerHTML = "이겼습니다!";
    }
});
btn[1].addEventListener("click",function(){
    clearInterval(com);
    let ran = rsp[Math.floor(Math.random()*3)];
    res.innerHTML = (`상대편 : ${ran} <br>본인 : 바위`);
    if(ran=="가위"){
        res2.innerHTML = "이겼습니다!"
    } else if(ran=="바위") {
        res2.innerHTML = "비겼습니다!"
    } else {
        res2.innerHTML = "졌습니다!";
    }
});
btn[2].addEventListener("click",function(){
    clearInterval(com);
    let ran = rsp[Math.floor(Math.random()*3)];
    res.innerHTML = (`상대편 : ${ran} <br>본인 : 보자기`);
    if(ran=="가위"){
        res2.innerHTML = "졌습니다!"
    } else if(ran=="바위") {
        res2.innerHTML = "이겼습니다!"
    } else {
        res2.innerHTML = "비겼습니다!";
    }
});

