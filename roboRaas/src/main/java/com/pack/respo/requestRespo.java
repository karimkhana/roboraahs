package com.pack.respo;

import org.springframework.data.repository.CrudRepository;

//import org.springframework.data.repository.CrudRepository;

import com.pack.entityss.ChatbotRequest;

public interface requestRespo    extends CrudRepository<ChatbotRequest, Integer>{

}
