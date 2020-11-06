package com.example.crypto.DAO;

import com.example.crypto.Model.AlertModel;
import com.mongodb.client.DistinctIterable;

import java.util.List;
import java.util.Optional;

public interface AlertDao {
    AlertModel save(AlertModel am);
    List<AlertModel> getAll(String id);
    DistinctIterable<AlertModel> getAllDistinct();
    List<AlertModel> Existed(String id,String curr);
}
