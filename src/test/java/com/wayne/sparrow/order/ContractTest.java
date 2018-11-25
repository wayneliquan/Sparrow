package com.wayne.sparrow.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wayne.sparrow.app.pojo.order.Contract;
import com.wayne.sparrow.app.pojo.order.ContractItem;

import java.util.ArrayList;
import java.util.List;

public class ContractTest {

    public static void main(String[] args) {
        Contract contract = new Contract();
        contract.setId(1L);

        List<ContractItem> itemList = new ArrayList<>();
        contract.setItemList(itemList);
        for (int i = 0; i < 3; i++) {
            ContractItem item = new ContractItem();
            item.setId(1L);
            item.setSurplus(20);
            itemList.add(item);
        }
        System.out.println(JSON.toJSONString(contract, SerializerFeature.NotWriteDefaultValue));
    }
}
