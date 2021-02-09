Тема на курсовата работа - Система за пилоти от формула 1.(MVC)

1. Стъпки за създаване на курсовата работа.

- База данни
Изпълнявам команда в phpMyAdmin за създаване на база данни със следните атрибути:
CREATE TABLE competitors( 
comp_id int(11) NOT NULL, 
comp_firstname varchar(15) NOT NULL, 
comp_lastname varchar(15) NOT NULL ) 
ENGINE=InnoDB DEFAULT CHARTSET=latin1 ROW_FORMAT=COMPACT
- Създаването на проекта става чрез сайта start.spring.io, където се отбелязват нужните библиотеки за разработка, а именно (Maven project, Java Language, Spring Book 2.3.1, Project Metadata with Packaging Jar, Java 11, Spring Web Dependency, Spring Data JPA Dependency, MySQL Driver Dependency, Thymeleaf Dependency).

Влизам в pom.xml да проверя дали са генерирани автоматично нужните корекции, също проверявам и aplication.properties  където се намира информация за базата данни(връзка към базата, име и парола ако има такава).

- Създаване на папка model с Driver клас. Тук се генерират getter-и и setter-и.

- Създаване на папка repository с DriverRepository интерфейс, правещ връзката между модела и базата данни.

- Създаване на папка controller с DriverController файл. В него се определят роот-овете при задаване на страница.

- Създаване на папка service с DriverService файл. Във този файл са описани CRUD операциите като преглеждане, добавяне,
изтриване и редактиране.

Проектът съдържа 3 view-та (.html страници) - index,insert,update. Те служат за визуализация.

- index.html съдържа заглавие, бутон за излизане от системата (Logout), background picture, линк за преминаване към формата  за добавяне на играч, търсачка (филтър) и таблица, изобразяваща наличните записи на пилоти към този момент в базата данни.

- insert.html страницата е със същия хедър, като вместо линк към страницата за добавяне на пилот, background picture, има линк, водещ към главната страница (списъка с пилоти). На тази страница има 2 input полета (с подходящи title-и), както и бутон за добавяне на пилоти, който изпраща данните към БД. Полетата разполагат и с валидация. Те са задължителни за попълване, а условията, които трябва да са изпълнени, за да бъде завършено добавянето, са да бъдат използвани само букви, както и символите да са между 1 и 15.

-update.html страницата използва същия хедър и линк, който препраща към главната страница. При натискане на бутона за  ъпдейт се отваря вече съществуващ запис. При промяна на запис бутона ъпдейт изпраща заявка към базата данни и променя съдържанието в нея.
Логването в системата се извършва чрез предоставено от Spring dependency, което използва име по подразбиране (user) и генерирана парола в output-а на проекта.

2. Описание на функционалността на системата.

Driver.java(Model)

В този клас показваме, че той изпозлзва competitors базата с данни. Декларираме останалите полета от нея, а именно firstname и lastname. Добавяме get и set пропъртита и методи, за да можем да ги използваме.
 
@Entity и @Table са анотации, които се поставят над декларацията на класа.

@Entity показва, че класът е persistent class.

@Table анотацията се използва, за да "мап-не" базата с данни.

@Id указва че даденото пропърти от базата данни е primary key

@GeneratedValue специфицира primary key анотацията

GenerationType.AUTO указва че полето в базата данни е auto increment

@Column анотацията се използва, за да "мап-не" колоните на базата с данни.

DriverController.java(Controller)

В контролера се декларира инстанция на DriverService, която позволява използването на CRUD заявки.
Чрез метода viewIndexPage можем да видим всички записи от списъка Driver(driverList).
Тук е имплементацията на филтъра, който преглежда дали в записите съществува думата, която търсим и ако съществува, то да се извеждат резултатите само съдържащи нея, а в противен случай - всички.

Методът viewDriverForm отвежда до insert.html страницата, откъдето добавяме нови пилоти.

Методът addNewDriver прави нов запис в базата данни, според въведени параметри. При изпълнение на метода се връщаме обратно в страницата.

Методът viewEditDriverForm ни препраща към едит страницата, откъдето можем да редактираме запис според id-то на записа.

Методът deleteDriver изтрива запис според неговото id.

DriverRepository.java(Repository)

В Repository класа става връзката между модела и базата с данни. За да бъде осъществена се използва @Query параметър 
със заявка към базата данни.
select * from competitors e where e.comp_firstname like %:keyword% or e.comp_lastname like %:keyword
Тази заявка извлича определени записи, които отговарят на търсеното според ключова дума.

DriverService.java(Service)

В този клас се създава инстанция на репозиторито, и в него пишем логиката на CRUD операциите.
В този клас използвам @Autowired за DriverRepository Interface class object injection. Spring Data JPA 
може да направи прототип на DriverRepository по време на рън-тайм и да го inject-не във обект от тип DriverService.
