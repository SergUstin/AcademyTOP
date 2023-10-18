// Первый пример
// При нажатии выпадает форма
document.getElementById('button1').addEventListener('click', function() {
    document.getElementById('example1').style.display = 'block';
});

// При нажатии выводит сообщение
document.getElementById('showAlertButton').addEventListener('click', function() {
    alert('Кнопка нажата!');
});

// Второй пример
// При нажатии выпадает форма
document.getElementById('button2').addEventListener('click', function() {
    document.getElementById('example2').style.display = 'block';
});

// При правильном заполение формы выводим данные введенные в поля
document.getElementById('example2Form').addEventListener('submit', function(e) {
    // Собитие при отправке пустой формы
    e.preventDefault();

    // Вывод введенных данных
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;    
    alert(`Имя: ${name}\nEmail: ${email}\nПароль: ${password}`)
});

// Третий пример
// При нажатии выпадает блок
document.getElementById('button3').addEventListener('click', function() {
    document.getElementById('example3').style.display = 'block';
});

// Меняем текс по нажатию
document.getElementById('changeTextButton').addEventListener('click', function() {
    document.getElementById('textToChange').textContent = 'Текст изменен!';
});

// Четвертый пример
// При нажатии выпадает блок
document.getElementById('button4').addEventListener('click', function() {
    document.getElementById('example4').style.display = 'block';
});

// При нажатии подклюяаем/отключаем обработчик событий
let clickListenerAttached = false;
let addClickListenerButton = document.getElementById('addClickListenerButton');
const listenerStatus = document.getElementById('listenerStatus');

addClickListenerButton.addEventListener('click', function() {
    if (!clickListenerAttached) {
        document.getElementById('listenerStatus').textContent = 'Статус обработчика событий: Подключен!';
        clickListenerAttached = true;
    } else {
        document.getElementById('listenerStatus').textContent = 'Статус обработчика событий: Отключен!';
        clickListenerAttached = false;
    }
});

// Пятый пример
document.getElementById('button5').addEventListener('click', function() {
    // При нажатии выпадает блок
    document.getElementById('example5').style.display = 'block';
    
    // Выводит сообщение
    const savedName = localStorage.getItem('name');
    const localStorageStatus = document.getElementById('localStorageStatus');
    if (savedName) {
        localStorageStatus.textContent = `Имя в локальном хранилище: ${savedName}`;
    } else {
        localStorageStatus.textContent = `Имя в локальном хранилище: Нет`;
    }
});

// При нажатии сохраняем имя в localStorage и выводим его в сообщение
document.getElementById('saveNameButton').addEventListener('click', function() {
    const nameToSave = document.getElementById('localStorageName').value;
    localStorage.setItem('name', nameToSave);
    const localStorageStatus = document.getElementById('localStorageStatus');
    localStorageStatus.textContent = `Имя в локальном хранилище: ${nameToSave}`;
});

// Шестой пример
// При нажатии выпадает блок
document.getElementById('button6').addEventListener('click', function() {
    document.getElementById('example6').style.display = 'block';
});