//Role : Gère les opérations CRUD pour les objets Star.

package com.example.starrate.service;

import com.example.starrate.beans.Star;
import com.example.starrate.dao.IDao;

import java.util.ArrayList;
import java.util.List;

public class StarService implements IDao<Star> {
    private List<Star> stars;
    private static StarService instance;

    private StarService() {
        this.stars = new ArrayList<>();
        stars.add(new Star("Yoo Ji-Tae", "https://upload.wikimedia.org/wikipedia/commons/e/e7/190502_%EC%9C%A0%EC%A7%80%ED%83%9C.jpg", 4.8f));
        stars.add(new Star("Cillian Murphy", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/75/Cillian_Murphy-2014.jpg/255px-Cillian_Murphy-2014.jpg", 4.8f));
        stars.add(new Star("Ji Chang-wook", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/Ji_Chang-wook_in_2017_Feb.jpg/330px-Ji_Chang-wook_in_2017_Feb.jpg", 4.5f));
        stars.add(new Star("Joseph Morgan", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/Joseph_Morgan_-_Monte-Carlo_Television_Festival.jpg/255px-Joseph_Morgan_-_Monte-Carlo_Television_Festival.jpg", 4.5f));
        stars.add(new Star("Johnny Depp", "https://ffrf.org/wp-content/uploads/1980/06/bab57453bc1e926e80c4cd433a8b44bd.jpg", 4.5f));
        stars.add(new Star("Norman Reedus", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0e/Norman_Reedus_Gage_Skidmore_2.jpg/255px-Norman_Reedus_Gage_Skidmore_2.jpg", 4.8f));
        stars.add(new Star("Chris Hemsworth", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/69/Chris_Hemsworth_by_Gage_Skidmore_3.jpg/330px-Chris_Hemsworth_by_Gage_Skidmore_3.jpg", 4.7f));
        stars.add(new Star("Kristen Stewart", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d6/Kristen_Stewart_at_Berlinale_2024-1_Ausschnitt.jpg/330px-Kristen_Stewart_at_Berlinale_2024-1_Ausschnitt.jpg", 4.5f));
        stars.add(new Star("Tom Cruise", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/Tom_Cruise_by_Gage_Skidmore_2.jpg/330px-Tom_Cruise_by_Gage_Skidmore_2.jpg", 4.8f));
        stars.add(new Star("Keanu Reeves", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/Reuni%C3%A3o_com_o_ator_norte-americano_Keanu_Reeves_%2846806576944%29_%28cropped%29.jpg/330px-Reuni%C3%A3o_com_o_ator_norte-americano_Keanu_Reeves_%2846806576944%29_%28cropped%29.jpg", 4.5f));
        stars.add(new Star("Jon Bernthal", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/aa/Jon_Bernthal_%2853983124020%29.jpg/330px-Jon_Bernthal_%2853983124020%29.jpg", 4.8f));
        stars.add(new Star("Josh Holloway", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Josh_Holloway_Comic-Con_cropped.jpg/255px-Josh_Holloway_Comic-Con_cropped.jpg", 4.5f));
        stars.add(new Star("Ian Somerhalder", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f8/Ian_Somerhalder_February_2015.jpg/255px-Ian_Somerhalder_February_2015.jpg", 4.8f));
    }

    public static StarService getInstance() {
        if (instance == null) {
            instance = new StarService();
        }
        return instance;
    }

    @Override
    public boolean create(Star o) {
        return stars.add(o);
    }

    @Override
    public boolean update(Star o) {
        for (Star s : stars) {
            if (s.getId() == o.getId()) {
                s.setImg(o.getImg());
                s.setName(o.getName());
                s.setStar(o.getStar());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Star o) {
        return stars.remove(o);
    }

    @Override
    public Star findById(int id) {
        for (Star s : stars) {
            if (s.getId() == id)
                return s;
        }
        return null;
    }

    @Override
    public List<Star> findAll() {
        return stars;
    }
}
