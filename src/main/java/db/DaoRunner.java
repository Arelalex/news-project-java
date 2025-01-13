package db;

import db.dao.*;
import db.dao.impl.*;
import db.dto.NewsDto;
import db.dto.RoleDto;
import db.entity.*;
import db.enums.Statuses;
import db.service.impl.NewsServiceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class DaoRunner {
    public static void main(String[] args) {

        PortalUserDao<Integer, PortalUserEntity> portalUserDao = PortalUserDaoImpl.getInstance();
        RoleDao<Integer, RoleEntity> roleDao = RoleDaoImpl.getInstance();
        CategoryDao<Integer, CategoryEntity> categoryDao = CategoryDaoImpl.getInstance();
        StatusDao<Integer, StatusesEntity> statusDao = StatusDaoImpl.getInstance();
        NewsDao<Long, NewsEntity> newsDao = NewsDaoImpl.getInstance();
        CommentDao<Long, CommentEntity> commentDao = CommentDaoImpl.getInstance();
        NewsServiceImpl newsService = NewsServiceImpl.getInstance();

        PortalUserEntity user = new PortalUserEntity();
        user.setUserId(8);
        CategoryEntity category = new CategoryEntity();
        category.setCategoryId(4);
        StatusesEntity status = new StatusesEntity();
        status.setStatusId(1);
        NewsEntity news = new NewsEntity();
        news.setNewsId(4L);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        System.out.println(createdAt);

        boolean isUpdated = newsService.updateStatus(3L, 2, "Rejected");
        System.out.println("обновление успешно " + isUpdated);

        // поиск с использованием фильтра по названию Роли
 //       System.out.println(newsDao.findAllByFilter(new NewsDto(Statuses.APPROVED.getId())));

//        commentDao.save(new CommentEntity("Круто", createdAt, updatedAt, null , news, user, status));

//        // обновление записи в таблице Роли
//        var maybeNews = newsDao.findById(6L);
//        System.out.println(maybeNews);
//
//        maybeNews.ifPresent(newsEntity -> {
//            newsEntity.setCreatedAt(createdAt);
//            newsEntity.setUpdatedAt(updatedAt);
//            newsDao.update(newsEntity);
//        });

        // поиск всех записей таблицы
        // System.out.println(portalUserDao.findAll());

        // удаление записи
//        commentDao.delete(3L);

        // добавление записей в таблицу
//        commentDao.save(new CommentEntity(
//                "Интересно почитать",
//                createdAt,
//                updatedAt,
//                null,
//                news,
//                user,
//                status
//        ));

        // поиск всех записей таблицы
       // System.out.println(newsDao.findAll());

        // обновление записей в таблице User
//        var maybeUser = newsDao.findById(5L);
//        System.out.println(maybeUser);
//
//        maybeUser.ifPresent(newsEntity -> {
//            newsEntity.setTitle("Будущее медицины: новые технологии, меняющие здоровье человека");
//            newsEntity.setDescription("Медицина стремительно развивается, и новые технологии уже сегодня открывают невероятные возможности для улучшения здоровья и качества жизни");
//            newsEntity.setContent("Современная медицина переживает эпоху невероятных открытий. Развитие технологий позволяет значительно улучшить диагностику, лечение и профилактику заболеваний. Рассмотрим несколько ключевых достижений и трендов в медицине, которые влияют на наше будущее.\n" +
//                                  "\n" +
//                                  "Искусственный интеллект в диагностике\n" +
//                                  "Искусственный интеллект (ИИ) уже сейчас активно используется в области медицины для диагностики заболеваний. Алгоритмы могут анализировать медицинские снимки (рентген, МРТ, УЗИ), что позволяет быстрее и точнее ставить диагноз. ИИ также помогает в выявлении скрытых заболеваний на ранних стадиях.\n" +
//                                  "\n" +
//                                  "Генетические исследования и персонализированная медицина\n" +
//                                  "Прогресс в области генетики позволяет нам лучше понимать, как наши гены влияют на здоровье. Это открывает путь к персонализированному лечению, когда врачи могут подбирать препараты и методы лечения, основанные на уникальных генетических особенностях пациента.\n" +
//                                  "\n" +
//                                  "Телемедицина: лечение на расстоянии\n" +
//                                  "Телемедицина стала особенно актуальной во времена пандемий. Современные технологии позволяют пациентам получать консультации от врачей дистанционно, не выходя из дома. Это делает медицинскую помощь доступной и эффективной для людей в отдалённых районах.\n" +
//                                  "\n" +
//                                  "3D-печать для медицинских нужд\n" +
//                                  "3D-печать уже используется для создания имплантов, протезов, а также в хирургии для разработки индивидуальных моделей органов. Это помогает пациентам быстрее восстанавливаться после операций и улучшает качество их жизни.\n" +
//                                  "\n" +
//                                  "Роботизированная хирургия\n" +
//                                  "Роботизированные системы позволяют проводить операции с минимальными разрезами, что сокращает риск осложнений и ускоряет восстановление пациентов. Эти технологии уже используются во многих крупных клиниках по всему миру.\n" +
//                                  "\n" +
//                                  "Медицина не стоит на месте, и с каждым годом мы всё ближе подходим к созданию технологий, которые могут не только лечить болезни, но и предотвращать их.");
//            newsEntity.setUser(user);
//            newsEntity.setCategory(category);
//            newsDao.update(newsEntity);
//        });

        // добавление записей в таблицу
//        newsDao.save(new NewsEntity(
//                "123",
//                "123",
//                "123",
//                createdAt,
//                updatedAt,
//                null,
//                user,
//                category,
//                Statuses.ON_MODERATION
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
