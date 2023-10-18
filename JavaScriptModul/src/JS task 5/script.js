// Решение заданий из файла, который решали на уроке
// Задание 1
const cont = document.getElementById('task01-1');

function invisibleBlock() {
    form = document.querySelector("#form"),
    text = document.querySelector(".name"),
    check = document.querySelector("#remember").checked;
    let checked = "";
    
    check ? (checked = "запомнил") : (checked = "не запомнил");
    
    if (text !== "") {
        form.style.display = "none";
        cont.innerHTML = `
        <div class='row'>
          <h1>Привет, ${text.value}! Я тебя ${checked}</h1>
        </div>
        <button class='btn' onclick="reset()">Вернуться назад</button>`;
    }
}
  
function reset() {
    cont.innerHTML = `    
    <h2>Первое задание файла с урока</h2>
    <div class="container">
        <div id="form">
            <div class="row" >
                <input type="text" class="name" placeholder="your name">
            </div>
            <div class="row">
                <input type="checkbox" id='remember'> запомнить
            </div>
            <div class="row">
                <button class="btn" onclick="invisibleBlock()">login</button>
            </div>
        </div>
    </div>`;
}
  
reset()

// Задание 2
const myForm = document.getElementById('myForm');

myForm.addEventListener('submit', function(e) {
    e.preventDefault();
    const email = document.getElementById('email').value;
    alert(`На почту ${email} отправлено письмо с подтверждением`);
});



// Решение заданий из фала на дом
// Задание номер 1
const button = document.getElementById("button");
const input = document.getElementById("input");
const container = document.querySelector("#container");
button.addEventListener("click", () => {
    if (input.value) {
        container.insertAdjacentHTML("beforeend", `<p>${input.value}</p>`);
        input.value = "";
    }
});

// Задание номер 2
function summary() {
    let count = document.getElementsByClassName('question').length; //Количество вопросов
    let answers = document.querySelectorAll('.question'); //Все элементы div с вопросами (???)
    let score = 0; //Количество верных ответов
    let rightAnswers = ["a_1", "a_3", "a_1"] //Список верных ответов
  
    for (let i = 0; i < count; i++) {
        let chosenAnswer = answers[i].querySelector("input[type='radio']:checked"); //Из массива вопросов выбираем элемент радио, который выбрал пользователь
        if (chosenAnswer && chosenAnswer.id == rightAnswers[i]) { //Думал что будет работать если будет свреять по html разметке, но зря      
            score++;
        }
        console.log("Выбранный ответ: " + chosenAnswer && chosenAnswer.parentNode.textContent);
        console.log("Правильный ответ: " + rightAnswers[i]);
        console.log("Очки: " + score);
    }
}

// Задание номер 3
let btn = document.getElementById("btn");
let text = document.getElementById("output");

btn.addEventListener("click", function(){
    let checkbox = document.querySelectorAll('input[type="checkbox"], input[type="radio"]');
    // Назначаем `text.style` конкатенацию всех атрибутов `style` `checkbox`, которые `checked`:
    text.style = [...checkbox].filter(c => c.checked).reduce((a,b) => a + b.getAttribute('style') + ';', '');
});
