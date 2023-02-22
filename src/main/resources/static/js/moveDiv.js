let count = 1;
let max = 12;//갯수의 끝
function moveDiv(f) {
    let left = document.getElementById("btnLeft");
    let right = document.getElementById("btnRight");
    let leftDiv = document.getElementsByClassName("hotDivBodyDivImg1");
    let rightDiv = document.getElementsByClassName("hotDivBodyDivImg2");
    if(count == (max - 4)){
        rightDiv[0].style.visibility = "hidden";
    }else{
        rightDiv[0].style.visibility = "visible";
    }
    if(f == 'r'){
        let divLeft = document.getElementById("hotDivBodyDiv" + count);
        let divRight = document.getElementById("hotDivBodyDiv" + (count + 4));
        divLeft.style.display = "none";
        divRight.style.display = "block";
        if(count <= (max - 4)){
            count++;
        }
    }
    if(f == 'l'){
        if(count > 1){
            count--;
        }
        let divLeft = document.getElementById("hotDivBodyDiv" + count);
        let divRight = document.getElementById("hotDivBodyDiv" + (count + 4));
        divLeft.style.display = "block";
        divRight.style.display = "none";
    }
    if(count == 1){
        leftDiv[0].style.visibility = "hidden";
    }else{
        leftDiv[0].style.visibility = "visible";
    }
}