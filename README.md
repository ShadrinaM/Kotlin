# Kotlin Mini Projects Repository

Этот репозиторий содержит несколько учебных мини-проектов, написанных на языке **Kotlin**. Каждый проект демонстрирует отдельные аспекты разработки Android-приложений, включая работу с UI, навигацией между активностями, ViewModel, Room Database и пр.

## Содержание проектов

### 📘 LR1 — Simple Text Display
Простое приложение, в котором:
- Пользователь вводит текст в `EditText`
- Нажимает кнопку
- Введённый текст отображается в `TextView`

![image](https://github.com/user-attachments/assets/db159b2a-cc32-4243-b959-83b3b5f939ad)


### 📗 LR2 — Викторина с подсказками
Приложение-опросник, которое:
- Позволяет выбрать вопрос и ответить на него кнопками "Правильно" / "Ага, щас)"
- Проверяет, правильный ли ответ и выводит результат в Toast-уведомление
- Имеет кнопку для подсказки (новая активность)
  - Если пользователь посмотрел подсказку, его ответ не засчитывается, о чём сообщается в Toast-уведомлении

**Используемые технологии:**
- Intent
- Toast-уведомление

![image](https://github.com/user-attachments/assets/2b0d8414-442c-4a13-8a6c-9b0f97c408c0)

### 📙 LR3 — CRUD со списком факультетов
Приложение, реализующее работу со списком факультетов с возможностью:
- Просмотра всех факультетов
- Добавления нового
- Редактирования существующего
- Удаления факультета

**Используемые технологии:**
- MVVM архитектура
- Room Database
- LiveData и ViewModel
- RecyclerView + фрагменты

![image](https://github.com/user-attachments/assets/71e1e582-bbae-4ea8-96f7-0f977d754b4f)

### 🔢 PR1_Calculator
Простой калькулятор с базовыми функциями:
- Очистка поля ввода, изменение знака текущего числа, удаление последнего символа, деление, умножение, вычитание, сложение 
- Ввод через цифровые кнопки
- Обработка десятичных чисел
- Ограничение ввода букв
- Обработка ошибок
- Отображение результата последнего действия
- Отображение последнего введённого знака
- По нажатию на "=" результат последнего действия выводится с основное поле ввода

![image](https://github.com/user-attachments/assets/ce23b058-92a3-4457-9e28-c2f5380ad43d)

### 📝 PR2_Form — Редактирование формы студента
Приложение с двумя активностями:
- В первой отображаются данные студента
- Кнопка "Изменить" открывает вторую активность
- Во второй активности можно изменить данные и сохранить

![image](https://github.com/user-attachments/assets/3b4d3809-57bb-4bfc-8557-00fd805ba156)

## Как запустить

1. Откройте проект в Android Studio
2. Выберите нужный модуль (например, `LR3`)
3. Соберите и запустите проект на эмуляторе или устройстве

## Автор

ShadrinaMM
