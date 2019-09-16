var board = [[9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9],
[9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
[9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
[9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
[9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
[9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
[9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
[9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
[9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
[9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
[9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]]
function gameInitial() {
    //reset the board
    let myTable = document.getElementById('boardTable');
    for (let i = 1; i <= 10; i++) {
        for (let j = 1; j <= 10; j++) {
            board[i][j] =0;
            myTable.rows[i].cells[j].innerHTML = '';
            myTable.rows[i].cells[j].style.backgroundColor = 'white';
            myTable.rows[i].cells[j].style.color = 'black';
        }
    }

    for (let index = 0; index < 3; index++) {
        let shipRowLocation = Math.floor(Math.random() * 6 + 1);
        let shipColunmLocation = Math.floor(Math.random() * 6 + 1);
        let horiOrVeti = Math.floor(Math.random() * 2);
        let duplicate = false;
        for (let icheck = 0; icheck < 5; icheck++) {
            if(horiOrVeti == 0){
                if(board[shipRowLocation][shipColunmLocation + icheck]==1){
                    duplicate = true;
                }
            }else if(horiOrVeti == 1){
                if(board[shipRowLocation+icheck][shipColunmLocation]==1){
                    duplicate = true;
                }
            }
        }
        if(duplicate){
            index--;
            continue;
        }
        for (let index1 = 0; index1 < 5; index1++) {
            if (horiOrVeti == 0) {//horizontal
                board[shipRowLocation][shipColunmLocation + index1] = 1
            } else {//vertical
                board[shipRowLocation + index1][shipColunmLocation] = 1;
            }
        }
    }
}

function showAnswer() {
    let myTable = document.getElementById('boardTable');
    for (let i = 1; i <= 10; i++) {
        for (let j = 1; j <= 10; j++) {
            if (board[i][j] != 0 && myTable.rows[i].cells[j].innerHTML != 'O') {
                myTable.rows[i].cells[j].innerHTML = board[i][j];
                myTable.rows[i].cells[j].style.backgroundColor = 'orange';
                myTable.rows[i].cells[j].style.color = 'snow';
            }else if(myTable.rows[i].cells[j].innerHTML == 'X' || myTable.rows[i].cells[j].innerHTML == 'O') {
                
            }else{
                myTable.rows[i].cells[j].innerHTML = '';
                myTable.rows[i].cells[j].style.backgroundColor = 'white';
                myTable.rows[i].cells[j].style.color = 'black';
            }
        }
    }
}

function play() {
    let myTable = document.getElementById('boardTable');
    let row = document.getElementById('rowChoose').value;
    let col = document.getElementById('colChoose').value;
    if (board[row][col] == 1) {
        myTable.rows[row].cells[col].innerHTML = 'O';
        myTable.rows[row].cells[col].style.backgroundColor = 'green';
        myTable.rows[row].cells[col].style.color = 'snow';
        document.getElementById('hitOrMiss').value = 'Hit';
    } else {
        myTable.rows[row].cells[col].innerHTML = 'X';
        myTable.rows[row].cells[col].style.backgroundColor = 'red';
        myTable.rows[row].cells[col].style.color = 'snow';
        document.getElementById('hitOrMiss').value = 'Miss';
    }
}

function ranPlay(){
    document.getElementById('rowChoose').value = Math.floor(Math.random()*10+1)
    document.getElementById('colChoose').value = Math.floor(Math.random()*10+1)
    play();
}