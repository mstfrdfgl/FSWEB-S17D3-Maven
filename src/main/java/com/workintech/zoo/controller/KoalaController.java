package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {
    private Map<Integer, Koala> koalas=new HashMap<>();

    @GetMapping
    public List<Koala> getAllKoalas(){
        return new ArrayList<>(koalas.values());
    }

    @GetMapping("/{id}")
    public Koala getKoalaByID(@PathVariable int id){
        return koalas.get(id);
    }

    @PostMapping
    public ResponseEntity<Koala> addKoala(@RequestBody Koala koala){
        koalas.put(koala.getId(),koala);
        return ResponseEntity.ok(koala);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Koala> updateKoala(@PathVariable int id, @RequestBody Koala koala){
        koalas.put(id,koala);
        return ResponseEntity.ok(koala);
    }

    @DeleteMapping("/{id}")
    public void deleteKoala(@PathVariable int id){
        koalas.remove(id);
    }
}
