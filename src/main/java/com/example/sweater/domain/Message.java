package com.example.sweater.domain;

import javax.persistence.*;

@Entity
//Entity показывает что это сущность, которую необходимо сохранять в бд
public class Message {
    //Показываем что поле ID будет идентифиувтором
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String text;
    private String tag;

    //ManyToOne показывает базе, что одному пользователю соответствует множетсво сообщений
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    //Создаем пустой класс, как бы объявляя конструктов, необходимо делать только в @Entity
    public Message(){

    }

    //Создаем конструктор
    public Message(String text, String tag, User user) {
        author = user;
        this.text = text;
        this.tag = tag;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }
    //Геттеры и сеттеры можно сгенерировать автоматически нажав alt+ins
    public User getAuthor() { return author; }

    public void setAuthor(User author) { this.author = author; }

    public Integer getId() { return id; }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
