const buttom = document.getElementById('myBotton');
const output = document.getElementById('output');

buttom.addEventListener('click', function() {
    output.textContent = 'Кнопка была нажата'
});

const div2 = document.getElementById('myDiv2');
div2.addEventListener('dblclick', function() {
    alert('Вы нажали двойной клие!')
});

const div3 = document.getElementById('myDiv3');
div3.addEventListener('mouseover', function() {
    div3.style.backgroundColor = 'yellow';
});
div3.addEventListener('mouseout', function() {
    div3.style.backgroundColor = '';
});

const input1 = document.getElementById('myInput1');
const output1 = document.getElementById('output1');

input1.addEventListener('focus', function() {
    output1.textContent = 'Фокус установлен';
});

input1.addEventListener('blur', function() {
    output1.textContent = 'Фокус потерян';
});

const input2 = document.getElementById('myInput2');
const output2 = document.getElementById('output2');
input2.addEventListener('input', function() {
    output2.textContent = 'Изменено: ' + input2.value;
});

const input3 = document.getElementById('myInput3');
const output3 = document.getElementById('output3');
input3.addEventListener('keydown', function(event) {
    output3.textContent = 'Нажата клавиша: ' + event.key;
});

const myList = document.getElementById();
const output4 = document.getElementById();

myList7.addEventListener('click', function(event) {
    if(event.target.tagname === 'LI') {
        output4.textContent = 'Выбран элемент: ' + event.target.textContent;
    }
})

const myForm = document.getElementById('muForm');
const output5 = document.getElementById('output5');

myForm.addEventListener('submit', function(event) {
    event.preventDefault();
    output5.textContent = "Форма отправлена!";
});

const myElement = document.getElementById('myElement');
let isDragging = false;
let offsetX, offsetY;

myElement.addEventListener('mousedown', function(event) {
    isDragging = true;
    offsetX = event.clientX - myElement.getBoundingClientRect().left;
    offsetY = event.clientY - myElement.getBoundingClientRect().right;
    myElement.style.cursor = 'grabbing';
});

document.addEventListener('mousemove', function(event) {
    if (isDragging) {
        myElement.style.left = event.clientX - offsetX + 'px';
        myElement.style.top = event.clientY - offsetY + 'px';
    }
});

document.addEventListener('mouseup', function() {
    if (isDragging) {
        isDragging = false;
        myElement.style.cursor = 'grab';
    }
});