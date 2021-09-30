<h1> Cafe(Spring boot + hibernate + postgresql+primefaces)</h1>
<p> Cafe - небольшой проект как тестовое задание, цель которого - продемонстрировать мои навыки. </p>
 <h3>Функционал проекта:</h3>
 <ul>
    <li> Получение меню и выбор заказа </li>
    <li> Реализация акциий
      <ul> <li>Бесплатная доставка при сумме заказа Х </li></ul>
      <ul> <li> Каждая Y кружка одного вида кофе бесплатная </li> </ul>
    </li>
    <li> Указание адреса достоавки </li>
    <li> Получение итогово чека </li>
    
 </ul>
 
 
 <h3> Используемые технологии</h3>
 <ul>
    <li><b>Backend:</b></li>
    <ul>
        <li> Docker </li>
        <li>Spring boot (Core, WEB, JPA)</li>
        <li>Hibernate</li>
        <li>Maven</li>
        <li>PostgreSQL</li>
   </ul>
   <li><b>Frontend:</b></li>
    <ul>
        <li> Primefaces </li>    
   </ul>
 </ul>
 
 <h3> Инструкция по использованию</h3>
  <ul>
  <li> Перейти в директорию docker </li>
  <li> Запустить базу данных описанную в docker-compose.yml командой `docker-compose up -d` </li>
  <li> Будет запущена база данных на порту 5422 
    <ul>
    <li> База данных: cafeDB  </li>
    <li> Юзер: appUser </li> 
    <li> Пароль: 13 </li>
    </ul>
   </li> 
  <li> В папке корня проекта запустить sql скрипт для заполнения меню кофе </li>
  <li> Перейти на страницу `/menu.jsf` <li>
   
