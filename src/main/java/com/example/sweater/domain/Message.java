package com.example.sweater.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
//Entity показывает что это сущность, которую необходимо сохранять в бд
public class Message {
    //Показываем что поле ID будет идентифиувтором
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String text;
    private String tag;

    //Создаем пустой класс, как бы объявляя конструктов, необходимо делать только в @Entity
    public Message(){

    }

    //Создаем конструктор
    public Message(String text, String tag) {
        this.text = text;
        this.tag = tag;
    }

    //Геттеры и сеттеры можно сгенерировать автоматически нажав alt+ins
    public Integer getId() {
        return id;
    }

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
