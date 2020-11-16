package com.example.crypto.services;

import com.example.crypto.DAO.AlertDao;
import com.example.crypto.Model.AlertModel;
import com.example.crypto.Model.FCM.Body;
import com.example.crypto.Model.FCM.Message;
import com.example.crypto.Model.FCM.NotificationModel;
import com.example.crypto.Model.PairModel;
import com.example.crypto.mongorepo.connection;
import com.mongodb.client.DistinctIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class alertService implements AlertDao {

    private MongoOperations mo = connection.get();

    @Autowired
    private fcmService fs;

    @Override
    public AlertModel save(AlertModel am) {
        return this.mo.save(am);
    }

    @Override
    public List<AlertModel> getAll(String id) {

        Query q = new Query();
        q.addCriteria(Criteria.where("userId").is(id));
        return this.mo.find(q,AlertModel.class);

    }

    @Override
    public List<AlertModel> getAllDistinct(){
        Query q = new Query();
        q.addCriteria(Criteria.where("pair").regex("/(\\w+\\b)(?!.*\\1\\b)/"));
        return this.mo.find(q,AlertModel.class);
    }

    @Override
    public List<AlertModel> Existed(String id,String curr,String quote){
        Query q = new Query();
        q.addCriteria(Criteria.where("userId").is(id).and("currencyName").is(curr).and("currencyPair").is(quote));
        return this.mo.find(q,AlertModel.class);
    }

    @Override
    public void Delete(String id, String pair) {
        Query q = new Query();
        q.addCriteria(Criteria.where("userId").is(id).and("pair").is(pair));
        this.mo.findAndRemove(q,AlertModel.class);
    }

    public void savePair(PairModel p){
        Query q = new Query();
        q.addCriteria(Criteria.where("pair").is(p.getPair()));

        if(this.mo.find(q,PairModel.class).stream().count() > 0){

        }
        else{
            this.mo.save(p);
        }
    }

    public List<PairModel> getPairs(){
        return this.mo.findAll(PairModel.class);
    }


    public void sendNotifcations(){
        NotificationModel nm = new NotificationModel();
        Message m = new Message();
        Body d = new Body();
        this.getPairs().forEach(x -> {
            System.out.println(x.getPair());
            d.setTitle(x.getPair());
            d.setBody("Upto $5");
            m.setNotification(d);
            m.setTopic("cryptassist-"+x.getPair());
            nm.setMessage(m);
            try {
                fs.SendNotificationToTopic(nm);
                System.out.println("Notification sent to "+"cryptassist-"+x.getPair());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error while sending notification to "+"cryptassist-"+x.getPair());
            }
        });

    }
}
