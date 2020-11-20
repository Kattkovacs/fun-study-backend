package com.codecool.funstudybackend.util;

import com.codecool.funstudybackend.entity.Card;
import com.codecool.funstudybackend.repository.CardRepository;
import com.codecool.funstudybackend.service.APIService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.dhiraj072.randomwordgenerator.RandomWordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class DbInit {
    @Autowired
    private CardRepository cardRepository;


    public void initDb() {
        List<HashMap<String, String>> words = new ArrayList<>();
        HashMap<String, String> ant = new HashMap<>();
        ant.put("word", "ant");
        ant.put("definition", "a small insect typically having a sting and living in a complex social colony with one or more breeding queens. It is wingless except for fertile adults, which form large mating swarms, and is proverbial for its industriousness.");
        ant.put("image_url", "https://media.owlbot.info/dictionary/images/tttttw.jpg.400x400_q85_box-0,0,225,225_crop_detail.jpg");
        words.add(ant);
        HashMap<String, String> lamp = new HashMap<>();
        lamp.put("word", "lamp");
        lamp.put("definition", "a device for giving light, either one consisting of an electric bulb together with its holder and shade or cover, or one burning gas or oil and consisting of a wick or mantle and a glass shade.");
        lamp.put("image_url", "https://media.owlbot.info/dictionary/images/567.jpg.400x400_q85_box-0,0,225,225_crop_detail.jpg");
        words.add(lamp);
        HashMap<String, String> dog = new HashMap<>();
        dog.put("word", "dog");
        dog.put("definition", "a domesticated carnivorous mammal that typically has a long snout, an acute sense of smell, non-retractile claws, and a barking, howling, or whining voice.");
        dog.put("image_url", "https://media.owlbot.info/dictionary/images/qa.jpg.400x400_q85_box-281,0,840,558_crop_detail.jpg");
        words.add(dog);
        HashMap<String, String> cat = new HashMap<>();
        cat.put("word", "cat");
        cat.put("definition", "a small domesticated carnivorous mammal with soft fur, a short snout, and retractile claws. It is widely kept as a pet or for catching mice, and many breeds have been developed.");
        cat.put("image_url", "https://media.owlbot.info/dictionary/images/rk.jpg.400x400_q85_box-0,0,225,225_crop_detail.jpg");
        words.add(cat);
        HashMap<String, String> fish = new HashMap<>();
        fish.put("word", "fish");
        fish.put("definition", "a limbless cold-blooded vertebrate animal with gills and fins living wholly in water.");
        fish.put("image_url", "https://media.owlbot.info/dictionary/images/iiiiiv.jpg.400x400_q85_box-0,0,225,225_crop_detail.jpg");
        words.add(fish);
        HashMap<String, String> horse = new HashMap<>();
        horse.put("word", "horse");
        horse.put("definition", "a solid-hoofed plant-eating domesticated mammal with a flowing mane and tail, used for riding, racing, and to carry and pull loads.");
        horse.put("image_url", "https://media.owlbot.info/dictionary/images/mi.jpg.400x400_q85_box-0,0,1920,1920_crop_detail.jpg");
        words.add(horse);
        HashMap<String, String> crab = new HashMap<>();
        crab.put("word", "crab");
        crab.put("definition", "a crustacean, found chiefly on seashores, with a broad carapace, stalked eyes, and five pairs of legs, the first pair of which are modified as pincers.");
        crab.put("image_url", "https://media.owlbot.info/dictionary/images/qq.jpg.400x400_q85_box-0,0,225,225_crop_detail.jpg");
        words.add(crab);
        HashMap<String, String> red = new HashMap<>();
        red.put("word", "red");
        red.put("definition", "of a colour at the end of the spectrum next to orange and opposite violet, as of blood, fire, or rubies.");
        red.put("image_url", "https://media.owlbot.info/dictionary/images/red.png.400x400_q85_box-0,0,500,500_crop_detail.png");
        words.add(red);
        HashMap<String, String> raincoat = new HashMap<>();
        raincoat.put("word", "raincoat");
        raincoat.put("definition", "a long coat, typically having a belt, made from waterproofed or water-resistant fabric.");
        raincoat.put("image_url", "https://media.owlbot.info/dictionary/images/fffffy.jpg.400x400_q85_box-0,73,950,1022_crop_detail.jpg");
        words.add(raincoat);
        HashMap<String, String> shark = new HashMap<>();
        shark.put("word", "shark");
        shark.put("definition", "a long-bodied chiefly marine fish with a cartilaginous skeleton, a prominent dorsal fin, and tooth-like scales. Most sharks are predatory, though the largest kinds feed on plankton, and some can grow to a large size.");
        shark.put("image_url", "https://media.owlbot.info/dictionary/images/dn.jpg.400x400_q85_box-576,0,1226,649_crop_detail.jpg");
        words.add(shark);
        HashMap<String, String> world = new HashMap<>();
        world.put("word", "world");
        world.put("definition", "the earth, together with all of its countries and peoples.");
        world.put("image_url", null);
        words.add(world);
        HashMap<String, String> man = new HashMap<>();
        man.put("word", "man");
        man.put("definition", "a human being of either sex; a person.");
        man.put("image_url", null);
        words.add(man);
        HashMap<String, String> woman = new HashMap<>();
        woman.put("word", "woman");
        woman.put("definition", "an adult human female.");
        woman.put("image_url", null);
        words.add(woman);
        HashMap<String, String> window = new HashMap<>();
        window.put("word", "window");
        window.put("definition", "a transparent panel on an envelope to show an address.");
        window.put("image_url", null);
        words.add(window);
        HashMap<String, String> door = new HashMap<>();
        door.put("word", "door");
        door.put("definition", "a hinged, sliding, or revolving barrier at the entrance to a building, room, or vehicle, or in the framework of a cupboard.");
        door.put("image_url", null);
        words.add(door);
        HashMap<String, String> skateboard = new HashMap<>();
        skateboard.put("word", "skateboard");
        skateboard.put("definition", "a short narrow board with two small wheels fixed to the bottom of either end, on which a person can ride in a standing or crouching position, propelling themselves by occasionally pushing one foot against the ground.");
        skateboard.put("image_url", null);
        words.add(skateboard);
        HashMap<String, String> sky = new HashMap<>();
        sky.put("word", "sky");
        sky.put("definition", "the region of the atmosphere and outer space seen from the earth.");
        sky.put("image_url", null);
        words.add(sky);
        HashMap<String, String> microphone = new HashMap<>();
        microphone.put("word", "microphone");
        microphone.put("definition", "an instrument for converting sound waves into electrical energy variations which may then be amplified, transmitted, or recorded.");
        microphone.put("image_url", null);
        words.add(microphone);
        HashMap<String, String> water = new HashMap<>();
        water.put("word", "water");
        water.put("definition", "a stretch or area of water, such as a river, sea, or lake.");
        water.put("image_url", null);
        words.add(water);
        HashMap<String, String> gas = new HashMap<>();
        gas.put("word", "gas");
        gas.put("definition", "an air-like fluid substance which expands freely to fill any space available, irrespective of its quantity.");
        gas.put("image_url", null);
        words.add(gas);


        for (HashMap<String, String> word : words) {
            Card tempCard = Card.builder()
                    .word(word.get("word"))
                    .definition(word.get("definition"))
                    .imageUrl(word.get("image_url"))
                    .build();
            cardRepository.save(tempCard);
        }
    }

}

