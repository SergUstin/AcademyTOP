// функция сложения
function sum (a, b) {
    console.log(a + b);
};

// функция вычитания
function sub(a, b) {
    console.log(a - b);
};

// функция умножения
function mult(a, b) {
    console.log(a * b);
};

// функция деления
function division(a, b) {
    // проверка деления на ноль
    if (b == 0) {
        console.log("Делить на ноль нельзя!");
    } else {
        console.log(a / b);
    }
};

// возведение в степень
function exponentiation(a, b) {
    console.log(Math.pow(a, b));
};

// извлечение квадратного корня
function rootExtraction(a) {
    console.log(Math.sqrt(a));
};

// тесты
sum(5,10);
sum(5,-5);
sum(-5,5);
sum(0,5);
sum(5,0);
console.log(" ");

sub(5,10);
sub(5,-5);
sub(-5,5);
sub(0,5);
sub(5,0);
console.log(" ");

mult(5,10);
mult(5,-5);
mult(-5,5);
mult(0,5);
mult(5,1);
console.log(" ");

division(5,10);
division(5,-5);
division(-5,5);
division(0,5);
division(5,0);
console.log(" ");

exponentiation(5,10);
exponentiation(5,-5);
exponentiation(-5,5);
exponentiation(0,5);
exponentiation(5,0);
console.log(" ");

rootExtraction(5);
rootExtraction(10);
rootExtraction(16);
rootExtraction(50);
rootExtraction(4);
console.log(" ");

