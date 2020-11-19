package com.codecool.funstudybackend.entity;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Card {

//    @Autowired
//    @Transient
//    @Column(nullable = true)
//    ObjectMapper mapper;

    @Id
    @GeneratedValue
    private Long cardId;

    @Column(nullable = false)
    private String word;

    @Column(nullable = false)
    private String definition;

    @Column(nullable = true)
    private String imageUrl;

//    public ObjectNode getObjectNode() {
//        ObjectNode objectNode = mapper.createObjectNode();
//
//        objectNode.put("word", word);
//        objectNode.put("definition", definition);
//        objectNode.put("image_url", imageUrl);
//        return objectNode;
//    }


}
