// Задание номер 1
function noDigits(event) {    
    if("1234567890".indexOf(event.key) != -1) {
        event.preventDefault();
    }
}

// Задание номер 2
const modal = document.getElementById('myModal');
const button = document.getElementById('button');
const modal_btn = document.getElementById('modal_btn');

button.onclick = function() {
    modal.style.display = 'block';
};

modal_btn.onclick = function() {
    modal.style.display = 'none';
}

window.onclick = function(event) {
    if(event.target == modal) {
        modal.style.display = 'none';
    }
}

// Задание номер 3
field.onclick = function(event) {

    // координаты поля относительно окна браузера
    let fieldCoords = this.getBoundingClientRect();

    // мяч имеет абсолютное позиционирование (position:absolute), поле - относительное (position:relative)
    // таким образом, координаты мяча рассчитываются относительно внутреннего, верхнего левого угла поля
    let ballCoords = {
      top: event.clientY - fieldCoords.top - field.clientTop - ball.clientHeight / 2,
      left: event.clientX - fieldCoords.left - field.clientLeft - ball.clientWidth / 2
    };

    // запрещаем пересекать верхнюю границу поля
    if (ballCoords.top < 0) ballCoords.top = 0;

    // запрещаем пересекать левую границу поля
    if (ballCoords.left < 0) ballCoords.left = 0;


    // // запрещаем пересекать правую границу поля
    if (ballCoords.left + ball.clientWidth > field.clientWidth) {
      ballCoords.left = field.clientWidth - ball.clientWidth;
    }

    // запрещаем пересекать нижнюю границу поля
    if (ballCoords.top + ball.clientHeight > field.clientHeight) {
      ballCoords.top = field.clientHeight - ball.clientHeight;
    }

    ball.style.left = ballCoords.left + 'px';
    ball.style.top = ballCoords.top + 'px';
}

// Задание номер 4
const trafficLights = document.querySelector("div.traffic__light");

const redlight = document.getElementById("red");
const yellowlight = document.getElementById("yellow");
const greenlight = document.getElementById("green");
const butn = document.getElementById("btn");

butn.addEventListener("click", function() {
  if (redlight.classList.contains("traffic__red")) {
    redlight.classList.remove("traffic__red");
    yellowlight.classList.add("traffic__yellow");
  } else if (yellowlight.classList.contains("traffic__yellow")) {
    yellowlight.classList.remove("traffic__yellow");
    greenlight.classList.add("traffic__green");
  } else if (greenlight.classList.contains("traffic__green")) {
    greenlight.classList.remove("traffic__green");
    redlight.classList.add("traffic__red");
  }
});

// Задание номер 5
function setSelected(e) {
    if(e.target.tagName == 'OL'){
        var selected = document.querySelector('.bookList .selected');
        if(selected) selected.classList.remove('selected');
        e.target.classList.add('selected');
    }
}
document.querySelector('.bookList').addEventListener('click',setSelected);

// Задание номер 6
let tooltipElem;

document.onmouseover = function(event) {
  let target = event.target;

  // если у нас есть подсказка...
  let tooltipHtml = target.dataset.tooltip;
  if (!tooltipHtml) return;

  // ...создадим элемент для подсказки

  tooltipElem = document.createElement('div');
  tooltipElem.className = 'tooltip';
  tooltipElem.innerHTML = tooltipHtml;
  document.body.append(tooltipElem);

  // спозиционируем его сверху от аннотируемого элемента (top-center)
  let coords = target.getBoundingClientRect();

  let left = coords.left + (target.offsetWidth - tooltipElem.offsetWidth) / 2;
  if (left < 0) left = 0; // не заезжать за левый край окна

  let top = coords.top - tooltipElem.offsetHeight - 5;
  if (top < 0) { // если подсказка не помещается сверху, то отображать её снизу
    top = coords.top + target.offsetHeight + 5;
  }

  tooltipElem.style.left = left + 'px';
  tooltipElem.style.top = top + 'px';
};

document.onmouseout = function(e) {

  if (tooltipElem) {
    tooltipElem.remove();
    tooltipElem = null;
  }

};
