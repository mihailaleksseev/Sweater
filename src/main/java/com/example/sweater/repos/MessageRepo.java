package com.example.sweater.repos;

import com.example.sweater.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//Репозиторий позволяет получить полный список полей, либо найти их по id
public interface MessageRepo extends CrudRepository<Message, Long> {

    //Данный метод сформаирован следующим образом: указываем ключевое слово find и tag по какому полю мы ищем
    List<Message> findByTag(String tag);

}
