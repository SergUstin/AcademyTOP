// Работа с объектом Date

// получить текущую дату без параметров
function getCurrentDate() {
  let date = new Date();
  let day = date.getDay();
  let month = date.getMonth();
  let year = date.getFullYear();
  return `${day}.${month}.${year}`; 
};


let curentDate = new Date();
let dateNow = getCurrentDate(curentDate);

// получить текущую дату с параметром
function getFormattedDate(date) {
  let day = date.getDay();
  let month = date.getMonth();
  let year = date.getFullYear();
  return `${year}-${month}-${day}`; 
}

let date = new Date();
let formatDate = getFormattedDate(date);

// Работа с массивами
// заполним массив 5 произвольными числами
function fillArray (array) {
  for (let i = 0; i <= 5; i++) {
    array.push(Math.floor(Math.random() * 10)); 
  }
};

// функция возращаюшая сумму всех чисел массива
function getSumOfNumbers(array) {
  let sum = 0;
  for (let i = 0; i < array.length; i++) {
    sum += array[i]; 
  }
  return sum;
}

// функция возвращает максимальное значение массива
function getMaxNumber(array) {
  let max = array[0];
  array.forEach(function(e) {
    if (e > max) {
      max = e;
    }
  });
  return max;
}

// Работа с объектом Math
// функция поиска площади круга
function calculateCircleArea(radius) {
  return Math.round(Math.PI * Math.pow(radius, 2)); 
}

// функция генерации случайного целого числа
function generateRandomNumber(min, max) {
  return Math.round(Math.random() * (max - min) + min);
}

// функция возведения в степень
function calculatePower(base, exponent) {
  return Math.pow(base, exponent);
}

// создаем массив и заполняем 5-ю произвольными числами
let numbers = [];
fillArray(numbers);
console.log(numbers);

// выводим сумму значений массива
let sum = getSumOfNumbers(numbers);
console.log(sum);

// выводим максимальное значение массива
let max = getMaxNumber(numbers);
console.log(max)

// выводим площадь круга
let S = calculateCircleArea(4);
console.log(S);

// вывод случайного целого числа
let ceilNumber = generateRandomNumber(1, 10);
console.log(ceilNumber);

// вывод возведенного в степень числа
let powNaumber = calculatePower(2,2);
console.log(powNaumber);

let item7 = document.createElement("p");
item7.innerText = JSON.stringify(dateNow, null, 0);
item7.style.fontSize = "30px";
document.getElementById('function_seven').appendChild(item7);

let item8 = document.createElement("p");
item8.innerText = JSON.stringify(formatDate, null, 0);
item8.style.fontSize = "30px";
document.getElementById('function_eight').appendChild(item8);

let item1 = document.createElement("p");
item1.innerText = JSON.stringify(numbers, null, 0);
item1.style.fontSize = "30px";
document.getElementById('function_one').appendChild(item1);

let item3 = document.createElement("p");
item3.innerText = JSON.stringify(max, null, 0);
item3.style.fontSize = "30px";
document.getElementById('function_three').appendChild(item3);

let item2 = document.createElement("p");
item2.innerText = JSON.stringify(sum, null, 0);
item2.style.fontSize = "30px";
document.getElementById('function_two').appendChild(item2);

let item4 = document.createElement("p");
item4.innerText = JSON.stringify(S, null, 0);
item4.style.fontSize = "30px";
document.getElementById('function_four').appendChild(item4);

let item5 = document.createElement("p");
item5.innerText = JSON.stringify(ceilNumber, null, 0);
item5.style.fontSize = "30px";
document.getElementById('function_five').appendChild(item5);

let item6 = document.createElement("p");
item6.innerText = JSON.stringify(powNaumber, null, 0);
item6.style.fontSize = "30px";
document.getElementById('function_six').appendChild(item6);