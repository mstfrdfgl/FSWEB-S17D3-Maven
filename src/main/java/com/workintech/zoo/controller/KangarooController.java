package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {
    private Map<Integer, Kangaroo> kangaroos = new HashMap<>();

    @GetMapping
    public List<Kangaroo> getAllKangaroos(){
        return new ArrayList<>(kangaroos.values());
    }

    @GetMapping("/{id}")
    public Kangaroo getKangarooByID(@PathVariable int id){
        if(kangaroos.get(id)==null){
            throw new ZooException("Kangaroo not found",HttpStatus.NOT_FOUND);
        }
        return kangaroos.get(id);
    }

    @PostMapping
    public ResponseEntity<Kangaroo> addKangaroo(@RequestBody Kangaroo kangaroo){
        if(kangaroo.getId()<=0||kangaroo.getName()==null){
            throw new ZooException("Invalid Kangaroo",HttpStatus.BAD_REQUEST);
        }
        kangaroos.put(kangaroo.getId(),kangaroo);
        return ResponseEntity.ok(kangaroo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kangaroo> updateKangaroo(@PathVariable int id,@RequestBody Kangaroo kangaroo){
        kangaroos.put(id,kangaroo);
        return ResponseEntity.ok(kangaroo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Kangaroo> deleteKangaroo(@PathVariable int id){
        if(!kangaroos.containsKey(id)){
            throw new ZooException("Kangaroo not found",HttpStatus.NOT_FOUND);
        }
        kangaroos.remove(id);
        return ResponseEntity.ok(kangaroos.get(id));
    }
}
