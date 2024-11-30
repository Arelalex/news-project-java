package db;

import db.dao.impl.*;
import db.dto.CategoryFilter;
import db.dto.NewsFilter;
import db.entity.*;
import db.enums.Statuses;

import java.sql.ResultSetMetaData;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DaoRunner {
    public static void main(String[] args) {

        var portalUserDao = PortalUserDaoImpl.getInstance();
        var roleDao = RoleDaoImpl.getInstance();
        var categoryDao = CategoryDaoImpl.getInstance();
        var statusDao = StatusDaoImpl.getInstance();
        var newsDao = NewsDaoImpl.getInstance();
        var commentDao = CommentDaoImpl.getInstance();

        PortalUserEntity user = new PortalUserEntity();
        user.setId(3);
        CategoryEntity category = new CategoryEntity();
        category.setId(3);
        StatusEntity status = new StatusEntity();
        status.setId(1);
        NewsEntity news = new NewsEntity();
        news.setId(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        // удаление записи
//        commentDao.delete(3L);

        // добавление записей в таблицу
        commentDao.save(new CommentEntity(
                "Интересно почитать",
                createdAt,
                updatedAt,
                null,
                news,
                user,
                status
        ));

        // поиск всех записей таблицы
       // System.out.println(newsDao.findAll());


        // добавление записей в таблицу
//        newsDao.save(new NewsEntity(2,
//                "Революция в Искусственном Интеллекте: Новый Алгоритм Понимания Речи",
//                "Ученые разработали алгоритм, который приближает компьютеры к полному пониманию человеческой речи. Эта технология обещает изменить взаимодействие человека с машинами",
//                "Вчера команда исследователей из Стэнфордского университета представила новый алгоритм искусственного интеллекта, способный не только распознавать слова, но и анализировать их контекст и эмоциональную окраску. Это открытие знаменует собой важный шаг вперед в области естественного языка и машинного обучения",
//                createdAt,
//                updatedAt,
//                null,
//                user,
//                category,
//                status
//        ));

//        // поиск с использованием фильтра по названию
//        CategoryFilter categoryFilter = new CategoryFilter(1,0, "Food");
//        System.out.println(categoryDao.findAllByFilter(categoryFilter));

        // добавление записей в таблицу Категорий
//        categoryDao.save(new CategoryEntity("Путешествия"));
//        categoryDao.save(new CategoryEntity("Спорт"));
//        categoryDao.save(new CategoryEntity("Технологии/ Наука"));
//        categoryDao.save(new CategoryEntity("Культура"));
//        categoryDao.save(new CategoryEntity("Медицина"));
//        categoryDao.save(new CategoryEntity("Еда"));

//        statusDao.save(new StatusEntity(Statuses.APPROVED));
//        statusDao.save(new StatusEntity(Statuses.REJECTED));
//        statusDao.save(new StatusEntity(Statuses.EDITING));
//        statusDao.save(new StatusEntity(Statuses.DELETED));

        //поиск по ID Роли
        //System.out.println(categoryDao.findById(2));

        // обновление записи в таблице Роли
//        var maybeRole = categoryDao.findById(6);
//        System.out.println(maybeRole);
//
//        maybeRole.ifPresent(categoryEntity -> {
//            categoryEntity.setCategory("Food");
//            categoryDao.update(categoryEntity);
//        });


//        // добавление записей в таблицу Ролей
//        roleDao.save(new RoleEntity(Roles.MODERATOR));
//        roleDao.save(new RoleEntity(Roles.USER));
//        roleDao.save(new RoleEntity(Roles.GUEST));
//
//        //поиск по ID Роли
//        System.out.println(roleDao.findById(2));
//
//        // поиск всех записей таблицы Роли
//        System.out.println(roleDao.findAll());
//
//        // поиск с использованием фильтра по названию Роли
//        var roleFilter = new RoleFilter(2, 0, "GUEST");
//        System.out.println(roleDao.findAllByFilter(roleFilter));
//
//        // обновление записи в таблице Роли
//        var maybeRole = roleDao.findById(2);
//        System.out.println(maybeRole);
//
//        maybeRole.ifPresent(roleEntity -> {
//            roleEntity.setRole(Roles.GUEST);
//            roleDao.update(roleEntity);
//        });
//
//        // удаление записи из Роли
//        roleDao.delete(3);
//
//
//        var roleModerator = roleDao.findById(1).orElseThrow();
//        var roleUser = roleDao.findById(2).orElseThrow();
//        var roleGuest = roleDao.findById(3).orElseThrow();
//
//        // добавление записей в таблицу User
//        portalUserDao.save(new PortalUserEntity("John", "Doe", "johndoe", "johndoe@example.com", "password50", "", roleModerator));
//        portalUserDao.save(new PortalUserEntity("Jane", "Smith", "janesmith", "janesmith@example.com", "password22", "", roleUser));
//        portalUserDao.save(new PortalUserEntity("Alex", "Brown", "alexbrown", "alexbrown@example.com", "password77", "", roleUser));
//        portalUserDao.save(new PortalUserEntity("Chris", "Johnson", "chrisjohnson", "chrisjohnson@example.com", "password91", "", roleUser));
//        portalUserDao.save(new PortalUserEntity("Anna", "Davis", "annadavis", "annadavis@example.com", "password17", "", roleUser));
//        portalUserDao.save(new PortalUserEntity("Sam", "Wilson", "samwilson", "samwilson@example.com", "password2", "", roleUser));
//        portalUserDao.save(new PortalUserEntity("Taylor", "Swift", "taylortaylor", "taylortaylor@example.com", "password80", "", roleGuest));
//        portalUserDao.save(new PortalUserEntity("Jordan", "Taylor", "jordantaylor", "jordantaylor@example.com", "password38", "", roleGuest));
//        portalUserDao.save(new PortalUserEntity("Pat", "Thomas", "patthomas", "patthomas@example.com", "password64", "", roleGuest));
//        portalUserDao.save(new PortalUserEntity("Chris", "Anderson", "chrisanderson", "chrisanderson@example.com", "password12", "", roleGuest));
//
//        // обновление записей в таблице User
//        var maybeUser = portalUserDao.findById(2);
//        System.out.println(maybeUser);
//
//        maybeUser.ifPresent(portalUserEntity -> {
//            portalUserEntity.setRole(roleModerator);
//            portalUserDao.update(portalUserEntity);
//        });
//
//        // удаление записи из Роли
//        portalUserDao.delete(10);
//
//        // поиск юзера по Id
//        System.out.println(portalUserDao.findById(1));
//
//        // поиск всех записей таблицы Роли
//        System.out.println(portalUserDao.findAll());

        // поиск с использованием фильтра по пользователю
//        var userFilter = new PortalUserFilter(2, 0, "Alex", "Brown", "alexbrown", "alexbrown@example.com");
//        System.out.println(portalUserDao.findAllByFilter(userFilter));
    }

}
