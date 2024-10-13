package org.co.models.store;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Inventory {

    private Map<String, Integer> inventoryStatus = new HashMap<>();

    public Map<String, Integer> getInventoryStatus(){
        return inventoryStatus;
    }

    @JsonAnySetter
    public void setInventoryStatus(String key, Integer value){
        this.inventoryStatus.put(key, value);
    }

    public int getStatus(String status){
        return inventoryStatus.getOrDefault(status, 0);
    }

}
