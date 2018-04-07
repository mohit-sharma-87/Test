package com.codelife.test.dataService;


import org.greenrobot.eventbus.EventBus;

/**
 * Created by mohitsharma on 29/08/17.
 */

public class DataService {

    private EndPoints apiClient;
    private EventBus eventBus;


    protected DataService(EventBus bus, EndPoints endPoints) {
        eventBus = bus;
        apiClient = endPoints;
    }


}
